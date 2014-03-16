
package org.modelica.mdt.ui.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.CompilerProxy;
import org.modelica.mdt.internal.core.ModelicaSourceFile;
import org.modelica.mdt.ui.editor.EditorUtility;

/**
 * This class sets up the view and creates a graph-environment for
 * nodes and connections to be presented in. It is also responsible
 * for tracking and responding to user input. In some cases it handles
 * the response by itself and sometimes it is passed on to another class.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaGraphView extends ViewPart {

	private static Composite permaParent;
	private static Graph graph;
	private static String oldFileName = "";
	private static String newFileName = "";
	private static String objName = "";
	private String source;
	private boolean isDetached = false;
	public static boolean loadedModelica = false;

	String viewId = "org.modelica.mdt.ui.graph.ModelicaGraphView";

	/**
	 * This will be called when the Graph View-view is created. It will
	 * create a new graph and add listeners.
	 *
	 * @param parent
	 *            the parent that the part is made in
	 */
	@SuppressWarnings("restriction")
	public void createPartControl(Composite parent) {
		System.out.println("[GraphView initial] Creating a new Graph View");

		// TODO: There seems to be an issue with this

		/*

		// If the parent shell doesn't have a title it has been detached from the main window
		if(parent.getShell().getText().length() != 0) {
			// In Eclipse 3.x

			WorkbenchPage page = ((WorkbenchPage) getSite().getPage());
			IViewReference ref = page.findViewReference("org.modelica.mdt.ui.graph.ModelicaGraphView");
			page.getActivePerspective().getPresentation().detachPart(ref);

			// TODO: In Eclipse 4.x the following should be done instead

		EModelService s = (EModelService) getSite().getService(EModelService.class);
		MPartSashContainerElement p = (MPart) getSite().getService(MPart.class);
		if (p.getCurSharedRef() != null)
			p = p.getCurSharedRef();
		s.detach(p, 100, 100, 300, 300);

		*/

		permaParent = parent;
		graph = new Graph(permaParent, SWT.NONE);
		listenerAdder();
	}

	/**
	 * This will add listeners to the graph so that the system will react different
	 * to different kinds of mouse actions.
	 * On double-click it will open up the reference to a clicked class.
	 * On right-click it will show a list of actions from a node, or if it was a
	 * connection it will show a list of all lines of code that made the selected
	 * dependency(s)
	 */
	private void listenerAdder() {
		graph.addMouseListener(
				new MouseListener() {
					@Override
					public void mouseDoubleClick(MouseEvent e) {
						if (ModelicaGraphAnalyzer.getModelicaCompiler() != null && !graph.getSelection().isEmpty()) {
							int grab = graph.getSelection().get(0).toString().length();
							String select = graph.getSelection().get(0).toString().substring(16, grab);
							try
							{
								doubleClickHandler(select);
							} catch (ConnectException e1)
							{
								e1.printStackTrace();
							} catch (UnexpectedReplyException e1)
							{
								e1.printStackTrace();
							} catch (InvocationError e1)
							{
								e1.printStackTrace();
							}
						}
					}

					@Override
					public void mouseDown(MouseEvent e) { }

					@Override
					public void mouseUp(MouseEvent e)
					{
						// right-click
						if (e.button == 3) {
							rightClickHandler(e);
						}
					}
				});
	}

	/**
	 * This will handle the action of a double-click from the user.
	 * What happends is that it will check if the double-clicked node
	 * is expandable, and in that case tell GraphAnalyzer and
	 * GraphGenerator to analyze/expand. Otherwise it should
	 * analyze/destroy nodes and connections by the same classes.
	 *
	 * @param select
	 *            the name of the selected node
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 * @exception InvocationException
	 *                if the Modelica compiler replies with an unexpected error in results
	 */
	private void doubleClickHandler(String select)
			throws ConnectException, UnexpectedReplyException, InvocationError {
		for (int i = 0; i < ModelicaGraphGenerator.nodes.size(); i++) {
			if (ModelicaGraphGenerator.nodes.get(i).getName().equals(select)) {
				// Workaround for exception in Zest when removing a selected node
				graph.setSelection(null);

				if (ModelicaGraphGenerator.nodes.get(i).isExpandable()) {
					// Expand node dependencies
					ModelicaGraphAnalyzer.analyzeClasses(i, select, false);
					ModelicaGraphGenerator.nodes.get(i).expandable = false;
					ModelicaGraphGenerator.generateExpanding(graph, ModelicaGraphGenerator.nodes.get(i).getName());
					// TODO: Set back selection after generation?
				} else {
					// Shrink node dependencies
					ModelicaGraphGenerator.nodes.get(i).expandable = true;
					ArrayList<Integer> destructedConnections = ModelicaGraphAnalyzer.destructClasses(i, select);
					ModelicaGraphGenerator.removeDependencies(graph, destructedConnections, i);
				}
			}
		}
	}

	/**
	 * This will handle the action of a right-click from the user.
	 * What happens is that it will check if the right-clicked object
	 * is a node or a connection. If it is a node it will present a list
	 * with all lines of code from the source file that made the dependency.
	 * If it was a node it will display a list of actions. This function
	 * also attaches a listener to whatever list is created.
	 *
	 * @param e
	 *            the event from the mouse listener
	 */
	private void rightClickHandler(MouseEvent e) {
		if (!graph.getSelection().isEmpty()) {
			final ArrayList<Integer> listItems = new ArrayList<Integer>();
			Object selectedObject = graph.getSelection().get(0);

			if (selectedObject instanceof GraphNode) {
				final List l = new List(graph, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
				l.setBounds (e.x, e.y, 400, 100);
				l.add("Open selected reference");
				l.add("Analyze operations");
				addMyTrackListener(l);

				final String referencedClass = ((GraphNode) selectedObject).getText();

				l.addListener (SWT.DefaultSelection, new Listener () {
					public void handleEvent (Event e) {
						if (l.getFocusIndex() == 0) {
							openSelectedReference(referencedClass, 0);
						}
						else if (l.getFocusIndex() == 1) {
							IWorkbenchPage activePage = getSite().getWorkbenchWindow().getActivePage();
							if (activePage != null) {

								IHandlerService handlerService = (IHandlerService) getSite().getService(IHandlerService.class);
								try {
									handlerService.executeCommand("org.modelica.mdt.ui.actions.commanddetailedgraphopener", null);
								} catch (Exception ex) {
								}

								try
								{
									ModelicaDetailedGraphView.createEquationButtons(referencedClass);
								} catch (ConnectException e1)
								{
									e1.printStackTrace();
								} catch (UnexpectedReplyException e1)
								{
									e1.printStackTrace();
								}
							}
						}

					}
				});

			} else if (selectedObject instanceof GraphConnection) {
				final List l = new List(graph, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
				l.setBounds (e.x, e.y, 400, 100);

				for (int i = 0; i < ModelicaGraphGenerator.connections.size(); i++) {
					if(((GraphConnection) selectedObject).getSource().getText().equals(ModelicaGraphGenerator.connections.get(i).source.getName()) &&
							((GraphConnection) selectedObject).getDestination().getText().equals(ModelicaGraphGenerator.connections.get(i).destination.getName())){

						source = ((GraphConnection) selectedObject).getSource().getText();

						Set<String> keys = ModelicaGraphGenerator.connections.get(i).lineRefs.keySet();
						for (Iterator<String> it = keys.iterator(); it.hasNext();) {
							String key =  it.next();
							Object ia[] = ModelicaGraphGenerator.connections.get(i).lineRefs.get(key).toArray();
							// TODO: Should also be able to highlight multiple types of dependencies
							//       Is this a possible case?

							// TODO: DEBUG: This is not working with dependencies to packages

							for (int index = 0; index < ia.length; index++){
								listItems.add(ModelicaGraphGenerator.connections.get(i).lineRefs.get(key).get(index));
								l.add(key + "<line: " + ModelicaGraphGenerator.connections.get(i).lineRefs.get(key).get(index) + ">");
							}
						}
					}
				}

				// TODO: Hover-highlighting
				addMyTrackListener(l);

				l.addListener (SWT.DefaultSelection, new Listener () {
					public void handleEvent (Event e) {
						openSelectedReference(source, listItems.get(l.getFocusIndex()));
					}
				});
			}
		}
	}

	private void addMyTrackListener(final List l) {
		l.addMouseTrackListener(new MouseTrackListener() {
			public void mouseHover(MouseEvent e) {
				// TODO: Is this needed?
				l.redraw();
			}
			public void mouseExit(MouseEvent e) {
				l.dispose();
			}
			public void mouseEnter(MouseEvent e) {
			}
		});
	}

	/**
	 * This will open up the selected class's file and
	 * point to the line in where the reference was found.
	 *
	 * @param ref
	 *            the name of the class
	 * @param lineNumber
	 *            the line number in that class file
	 * @throws CoreException
	 *                if the Modelica compiler can't be loaded
	 * @throws ConnectException
	 *                if the Modelica compiler returns something strange
	 * @throws UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 * @throws InvocationError
	 *                if the Modelica compiler returns something strange
	 */
	public void openSelectedReference(String ref, int lineNumber) {
		// TODO: Find a better way of locating files outside workspace
		//       Right now we create a External Files-folder with the file in it
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IProject project = ws.getRoot().getProject("External Files");
		if (!project.exists())
			try
		{
				project.create(null);
		} catch (CoreException e)
		{
			e.printStackTrace();
		}
		if (!project.isOpen())
			try
		{
				project.open(null);
		} catch (CoreException e)
		{
			e.printStackTrace();
		}
		IPath location = null;
		try
		{
			location = new Path(ModelicaGraphAnalyzer.getModelicaCompiler().getClassLocation(ref).getPath());
		} catch (ConnectException e)
		{
			e.printStackTrace();
		} catch (UnexpectedReplyException e)
		{
			e.printStackTrace();
		} catch (InvocationError e)
		{
			e.printStackTrace();
		}
		IFile file = project.getFile(location.lastSegment());

		if (!file.exists()){
			try
			{
				file.createLink(location, IResource.NONE, null);
			} catch (CoreException e)
			{
				e.printStackTrace();
			}
		}
		IEditorPart editor;
		try
		{
			editor = EditorUtility.openInEditor(file);
			goToLine(editor, lineNumber);
		} catch (PartInitException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This is a listener that will be called upon when
	 * something is selected. If something new was selected
	 * it will start a new thread and update the graph
	 * in case it was a modelica file.
	 */
	public static void selectionChanged(final ISelection final_selection) {
		Job job = new Job("Dependency Graph Loading") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {

				objName = "";
				try {
					IStructuredSelection newSelection = (IStructuredSelection) final_selection;
					Object obj = newSelection.getFirstElement();
					objName = obj.getClass().getName();
				} catch (ClassCastException e) {}
				catch (NullPointerException e) {}

				if (objName.equals("org.modelica.mdt.internal.core.ModelicaSourceFile")) {
					System.out.println("[Change Selection] Found a Modelica-file!");
					updateGraph(final_selection);
				}
				else {
					System.out.println("[Change Selection] Not a Modelia-file");
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	/**
	 * This will remove all visual graph objects from the graph
	 * and will perform this asynchronous with Eclips UI thread.
	 *
	 * @param g
	 *                the graph that should be cleared from
	 *                all visual objects
	 */
	static void clearGraph(Graph g) {
		final Object[] cObjects = g.getConnections().toArray() ;
		final Object[] gObjects = g.getNodes().toArray() ;

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				for ( int x = 0 ; x < cObjects.length ; x++ ) {
					((GraphConnection) cObjects[x]).dispose() ;
				}
				for ( int x = 0 ; x < gObjects.length ; x++ ) {
					((GraphNode) gObjects[x]).dispose();
				}
			}
		});
	}

	/**
	 * This will update the graph whenever something has
	 * been selected. The update consist of first clearing the
	 * nodes/connection-storages and then call for a new analyze.
	 * When there is a new set of data it will then continue on
	 * with clearing all old visual objects and generate new
	 * objects.
	 *
	 * @param select
	 *            the selected files
	 */
	public static void updateGraph(ISelection... select) {
		ISelection selection = null;
		for (ISelection s : select )
			selection = s;

		if(selection!=null && !selection.isEmpty()) {
			IStructuredSelection selectedFileSelection = (IStructuredSelection) selection;
			Object obj = selectedFileSelection.getFirstElement();
			ModelicaSourceFile selectedFile=(ModelicaSourceFile)obj;

			String selectedString = selectedFile.getElementName();
			IFile selectedIFile = selectedFile.getResource();
			IPath selectedPath = selectedIFile.getLocation();
			newFileName = selectedString;
			if (!newFileName.equals(oldFileName)){
				// TODO: Move this?
				// Reset data
				ModelicaGraphAnalyzer.setNid(0);
				ModelicaGraphAnalyzer.setCid(0);
				ModelicaGraphGenerator.connections = new ArrayList<ModelicaConnection>();
				ModelicaGraphGenerator.nodes = new ArrayList<ModelicaNode>();

				try {
					CompilerProxy.getCompiler();
				} catch (CompilerInstantiationException e) {
					e.printStackTrace();
				}
				String fileName = selectedString.replace(".mo", "");
				try
				{
					ModelicaGraphAnalyzer.initAnalyze(fileName, selectedPath);

				} catch (ConnectException e1)
				{
					e1.printStackTrace();
				} catch (UnexpectedReplyException e1)
				{
					e1.printStackTrace();
				} catch (InvocationError e1)
				{
					e1.printStackTrace();
				}

				if (ModelicaGraphAnalyzer.getModelicaCompiler() != null){

					clearGraph(graph);
					try
					{
						ModelicaGraphGenerator.generateNodes(graph, selectedString);
					} catch (ConnectException e)
					{
						e.printStackTrace();
					} catch (UnexpectedReplyException e)
					{
						e.printStackTrace();
					}
				}
			}
			oldFileName = newFileName;
		} else {
			System.out.println("[Selection Error] Couldn't find the Projects View or Selection");
		}
	}

	/**
	 * This will go to a certain line in the editor.
	 * The selected line will be highlighted to the user.
	 *
	 * @param editorPart
	 *            the editor with an opened file
	 * @param lineNumber
	 *            the line number in that class file
	 */
	private static void goToLine(IEditorPart editorPart, int lineNumber) {
		if (!(editorPart instanceof ITextEditor) || lineNumber <= 0) {
			return;
		}
		ITextEditor editor = (ITextEditor) editorPart;
		IDocument document = editor.getDocumentProvider().getDocument(
				editor.getEditorInput());
		if (document != null) {
			IRegion lineInfo = null;
			try {
				// line count internally starts with 0, and not with 1
				lineInfo = document.getLineInformation(lineNumber - 1);
			} catch (BadLocationException e) {
				// ignored because line number may not really exist in document
			}
			if (lineInfo != null) {
				editor.selectAndReveal(lineInfo.getOffset(), lineInfo.getLength());
			}
		}
	}

	public static Graph getGraph() {
		return graph;
	}

	public void setFocus() {
	}

	public void dispose() {
		super.dispose();
	}
}
