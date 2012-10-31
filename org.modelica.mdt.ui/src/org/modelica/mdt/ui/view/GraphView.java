
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.ModelicaSourceFile;
import org.modelica.mdt.ui.editor.EditorUtility;

public class GraphView extends ViewPart {
	public static final String ID = "org.modelica.mdt.ui.view.GraphView";
	private Graph graph;
	public static Composite permaParent;
	private String oldFileName = "";
	private String newFileName = "";
	private String objName = "";
	private String source;

	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			objName = "";
			try {
				IStructuredSelection newSelection = (IStructuredSelection) selection; 
				Object obj = newSelection.getFirstElement();
				objName = obj.getClass().getName();
			} 
			catch (NullPointerException e) {}
			catch (ClassCastException e) {}

			if (sourcepart != GraphView.this &&
					objName.equals("org.modelica.mdt.internal.core.ModelicaSourceFile")) {
				System.out.println("[Change Selection] Found a Modelica-file!");

				// TODO: This update can be triggered mutiple times
				updateGraph();
			}
			else {
				System.out.println("[Change Selection] Not a Modelia-file");
				System.out.println(objName + "  " + sourcepart.getTitle());
			}
		}
	};

	static void clearGraph(Graph g) {

		Object[] objects = g.getConnections().toArray() ;
		for ( int x = 0 ; x < objects.length ; x++ ) {
			((GraphConnection) objects[x]).dispose() ;
		}
		objects = g.getNodes().toArray() ;
		for ( int x = 0 ; x < objects.length ; x++ ) {
			((GraphNode) objects[x]).dispose();
		}

	}

	public void updateGraph() {
		// TODO: Change this later on

		System.out.println("[Graph Operation]  Check if update is possible");
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelection selection = null;
		try {
			selection = window.getSelectionService().getSelection("org.modelica.mdt.ui.view.ModelicaProjectsView");
		} catch (NullPointerException e) {
			// Nothing was selected
		}
		if(selection!=null && !selection.isEmpty()) {
			IStructuredSelection selectedFileSelection = (IStructuredSelection) selection;
			Object obj = selectedFileSelection.getFirstElement();
			ModelicaSourceFile selectedFile=(ModelicaSourceFile)obj;

			String selectedString = selectedFile.getElementName();
			IFile selectedIFile = selectedFile.getResource();
			IPath selectedPath = selectedIFile.getLocation();
			newFileName = selectedString;
			if (!newFileName.equals(oldFileName)){
				System.out.println("[Graph Operation]  Updating Graph!");

				// TODO: Move this?
				// Reset data
				CrossAnalyzer.nid = 0;
				CrossAnalyzer.cid = 0;
				CrossUtil.connections = new ArrayList<MyConnection>();
				CrossUtil.nodes = new ArrayList<MyNode>();

				try {
					CompilerProxy.getCompiler();
				} catch (CompilerInstantiationException e) {
					e.printStackTrace();
				}
				String fileName = selectedString.replace(".mo", "");
				CrossAnalyzer.initAnalyze(fileName, selectedPath);
				try
				{
					CrossAnalyzer.analyzeError();
				} catch (ConnectException e)
				{
					e.printStackTrace();
				} catch (UnexpectedReplyException e)
				{
					e.printStackTrace();
				}

				if (CrossAnalyzer.currentCompiler != null){

					clearGraph(graph);
					try
					{
						CrossUtil.generateNodes(graph, selectedString);
					} catch (ConnectException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnexpectedReplyException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			oldFileName = newFileName;
		} else {
			System.out.println("[Selection Error] Couldn't find the Projects View or Selection");
		}
	}

	public void createPartControl(Composite parent) {
		permaParent = parent;
		graph = new Graph(permaParent, SWT.NONE);

		Job job = new Job("My Job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				updateGraph();
				getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(listener);


				/*
				 * graph.translateToAbsolute(graph.getBounds().getLocation())
				 */

				return Status.OK_STATUS;
			}
		};

		job.schedule(); 


		// TODO: Put this in a new function med throw exception
		try{
			graph.addMouseListener(
					new MouseListener(){

						@Override
						public void mouseDoubleClick(MouseEvent e) {
							System.out.println("------------ NEW OPERATION ----------");
							if (CrossAnalyzer.currentCompiler != null && !graph.getSelection().isEmpty()) {
								String select = graph.getSelection().get(0).toString().substring(16, graph.getSelection().get(0).toString().length());
								for (int i = 0; i < CrossUtil.nodes.size(); i++) {
									if (CrossUtil.nodes.get(i).getName().equals(select)) {
										// Workaround for exception in Zest when removing a selected node
										graph.setSelection(null);

										
										if (CrossUtil.nodes.get(i).isExpandable()) {
											// Expand node's dependencies
											try
											{
												// This only analyze from the selected node (not everything)
												System.out.println("[Graph Operation] Expanding " + select);
												//int size = CrossUtil.nodes.size();

												CrossAnalyzer.analyzeClasses(i, select, false);
												CrossUtil.nodes.get(i).expandable = false;

												
												//clearGraph(graph);
												CrossUtil.generateExpanding(graph, CrossUtil.nodes.get(i).getName());

												// TODO: Set back selection after generation?

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
										} else {
											// Shrink node's dependencies
											
											System.out.println("[Graph Operation] Shrinking " + select);
											
											CrossUtil.nodes.get(i).expandable = true;

											ArrayList<Integer> destructedConnections = CrossAnalyzer.destructClasses(i, select);
											
											try
											{
												CrossUtil.removeDependencies(graph, destructedConnections, i);
											} catch (ConnectException e1)
											{
												// TODO Auto-generated catch block
												e1.printStackTrace();
											} catch (UnexpectedReplyException e1)
											{
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}

										}
									} 
								}
							}
						}

						@Override
						public void mouseDown(MouseEvent e)
						{
						}

						@Override
						public void mouseUp(MouseEvent e)
						{

							if (e.button == 3) { // right-click
								System.out.println("------------ NEW OPERATION ----------");
								if (!graph.getSelection().isEmpty()) {
									final ArrayList<Integer> test = new ArrayList<Integer>();

									// TODO: Sometimes we want to go a line even in a code
									// i.e. inside a package or a class that contains other classes i.e. test1.BC (BC definied inside test1)
									Object selectedObject = graph.getSelection().get(0);

									if (selectedObject instanceof GraphNode) {

										openSelectedReference(((GraphNode) selectedObject).getText(), 0);

									} else if (selectedObject instanceof GraphConnection) {							
										final List l = new List(graph, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
										l.setBounds (e.x, e.y, 400, 100);

										for (int i = 0; i < CrossUtil.connections.size(); i++) {
											if(((GraphConnection) selectedObject).getSource().getText().equals(CrossUtil.connections.get(i).source.getName()) &&
													((GraphConnection) selectedObject).getDestination().getText().equals(CrossUtil.connections.get(i).destination.getName())){

												source = ((GraphConnection) selectedObject).getSource().getText();

												Set<String> keys = CrossUtil.connections.get(i).lineRefs.keySet();
												for (Iterator<String> it = keys.iterator(); it.hasNext();) {
													String key =  it.next();
													Object ia[] = CrossUtil.connections.get(i).lineRefs.get(key).toArray();
													// TODO: Should also be able to highlight multiple types of dependencies
													//       Is this a possible case?

													// TODO: DEBUG: This is not working with dependencies to packages

													for (int index = 0; index < ia.length; index++){
														test.add(CrossUtil.connections.get(i).lineRefs.get(key).get(index));
														l.add(key + "<line: " + CrossUtil.connections.get(i).lineRefs.get(key).get(index) + ">");
													}
												}
											}
										}

										// TODO: Hover-highlighting

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

										l.addListener (SWT.DefaultSelection, new Listener () {
											public void handleEvent (Event e) {
												openSelectedReference(source, test.get(l.getFocusIndex()));									
											}
										});
									}	
								}
							}
						}
					});
		} catch (SWTException e){
			System.out.println("View-listener Error: " + e);
		}
	}


	public void openSelectedReference(String ref, int lineNumber){
		System.out.println("------------ NEW OPERATION ----------");
		System.out.println("[Graph Operation] Open up a new view referenced to the line " + ref);

		// TODO: Find a better way of locating files outside workspace
		//       Right now we create a External Files-folder with the file in it
		try
		{
			IWorkspace ws = ResourcesPlugin.getWorkspace();
			IProject project = ws.getRoot().getProject("External Files");
			if (!project.exists())
				project.create(null);
			if (!project.isOpen())
				project.open(null);
			IPath location = new Path(CrossAnalyzer.currentCompiler.getClassLocation(ref).getPath());
			IFile file = project.getFile(location.lastSegment());

			if (!file.exists()){
				file.createLink(location, IResource.NONE, null);
			}

			try
			{
				IEditorPart editor = EditorUtility.openInEditor(file);
				goToLine(editor, lineNumber);

			} catch (CoreException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ConnectException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnexpectedReplyException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationError e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if lineNumber != 0 { track the line number in that file and highlight it through the editor }
	}

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
				// line count internally starts with 0, and not with 1 like in
				lineInfo = document.getLineInformation(lineNumber - 1);
			} catch (BadLocationException e) {
				// ignored because line number may not really exist in document
			}
			if (lineInfo != null) {
				editor.selectAndReveal(lineInfo.getOffset(), lineInfo.getLength());
			}
		}
	}

	public void setFocus() {
	}

	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(listener);
		super.dispose();
	}	
}
