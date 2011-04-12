package org.modelica.uml.sysml.diagram2.views;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.part.*;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.StatusKind;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorPlugin;
import org.modelica.uml.sysml.diagram2.part.SysmlElementChooserDialog;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

/**
 * @author Administrator
 *
 */
public class RequirementQuerryView extends ViewPart {
//	private TableViewer viewer;
	ScrolledComposite sc; 
	private Action action1;
	private Action action2;
	private Action doubleClickAction;
	
	private int[] levels;
	private int[] priorities;
	private String[] statuses;
	private boolean fromProject;
	
	private Text customLevelsInput;
	private Text customPriorityInput;
	private Button fileCheck;
	private IFile file;
	private Text fileLocation;
	private Button projectCheck;
	private IProject project;
	private Text projectName;
	private Button workspace;
	private Button negated;
	private Button search;
	
	static private List selectedStatusesList;
	static private List statusList;
	static private Table resultTable;
	
	private ITreeContentProvider myWorkbenchContentProvider = new WorkbenchContentProvider();
	
	private AdapterFactoryContentProvider myAdapterFctoryContentProvier = new AdapterFactoryContentProvider(
			SysmlDiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());
	
	private TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
	.createEditingDomain();
	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			int i = 5;
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			return new String[] { "One", "Two", "Three" };
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public RequirementQuerryView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		sc = new ScrolledComposite(parent, SWT.H_SCROLL |   
				SWT.V_SCROLL | SWT.BORDER);
		
		Composite composite = new Composite(sc, SWT.NONE);
//		composite.setLayout(new FillLayout());
		composite.setSize(400,400);
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(1000, 1000));
		
		int levelX = 0, levelY = 0, levelH = 35, levelW = 100;
		createLevelGroup(composite,levelX,levelY,levelH,levelW );
		
		int priorityX = levelW + 3, priorityY = levelY;
		int priorityH = levelH, priorityW = levelW;
		createPriorityGroup(composite, priorityX, priorityY, priorityW, priorityH);
		
		int lgX = 0, lgY = priorityY + priorityH;
		int lgW =  priorityX + priorityW, lgH = 130;
		createLocationGroup(composite, lgX, lgY, lgW, lgH);
		
		int slX = priorityX + priorityW + 5, slY = 0;
		int slW = 200, slH = 200;
		createStatusGroup(composite, slX, slY, slW, slH);
		
		int rtW = 400, rtH = 200;
		resultTable = new Table(composite, SWT.MULTI | SWT.BORDER);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		resultTable.setLayoutData(data);
		String[] titles = {"Name", "Level", "Priority", "Status", "Project", "File"};
		for (int c=0; c<titles.length; c++) {
			TableColumn column = new TableColumn (resultTable, SWT.NONE);
			column.setText (titles [c]);
		}		

		resultTable.setBounds(slX + slW + 10, 0, rtW, rtH);
		resultTable.setLinesVisible(true);
		resultTable.setHeaderVisible(true);
		for (int j=0; j<titles.length; j++) {
			resultTable.getColumn (j).pack ();
		}

//		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		viewer.setContentProvider(new ViewContentProvider());
//		viewer.setLabelProvider(new ViewLabelProvider());
//		viewer.setSorter(new NameSorter());
//		viewer.setInput(getViewSite());
		
		negated = new Button(composite, SWT.CHECK);
		negated.setBounds(lgX, lgY + lgH + 5, 20, 20);
		
		Label negatedLabel = new Label(composite, SWT.NONE);
		negatedLabel.setBounds(lgX + 20, lgY + lgH + 5, 50, 20);
		negatedLabel.setText("Negated");
		
		search = new Button(composite, SWT.PUSH);
		search.setBounds(lgX + 70, lgY + lgH + 5, 50, 20);
		search.setText("Search");

		addListeners();
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	public void createLevelGroup(Composite composite,int levelX,int levelY,
			int levelH,int levelW ) {

		Group levelGroup = new Group(composite, SWT.SHADOW_ETCHED_IN);

		levelGroup.setBounds(levelX, levelY, levelW, levelH);
		levelGroup.setText("Levels");

		customLevelsInput = new Text(levelGroup, SWT.SINGLE|SWT.BORDER);
		int cliX = 0, cliY = 15;
		int cliH = 20, cliW = 100;
		customLevelsInput.setBounds(cliX, cliY, cliW, cliH);
	}
	
	public void createPriorityGroup(Composite composite, int x, int y, int w, int h) {
		Group priorityGroup = new Group(composite, SWT.SHADOW_ETCHED_IN);
		
		priorityGroup.setBounds(x, y, w, h);
		priorityGroup.setText("Priorities");

		customPriorityInput = new Text(priorityGroup, SWT.SINGLE|SWT.BORDER);
		int cpiX = 0, cpiY = 15;
		int cpiW = w, cpiH = 20;
		customPriorityInput.setBounds(cpiX, cpiY, cpiW, cpiH);		

	}
	
	public void createLocationGroup(final Composite composite,int x,int y,int w,int h) {
		Group locationGroup = new Group(composite, SWT.SHADOW_ETCHED_IN);
	
		locationGroup.setBounds(x,y,w,h);
		locationGroup.setText("Location");
		
		int buttonSize = 20;
		
		fileCheck = new Button(locationGroup,SWT.RADIO);
		int fX = 0, fY = 15, fW = 20, fH = 20;
		fileCheck.setBounds(fX, fY, fW, fH);
		fileCheck.setSelection(true);
		
		Label fileLabel = new Label(locationGroup, SWT.NONE);
		fileLabel.setBounds(fX + 20, fY , 50, 17);
		fileLabel.setText("File");

		fileLocation = new Text(locationGroup, SWT.BORDER|SWT.READ_ONLY);
		int flX = 0, flY = fY + fH, flW = w - buttonSize, flH = 20;
		fileLocation.setBounds(flX, flY, flW, flH);
		
		final Button fileButton = new Button(locationGroup, SWT.PUSH);
		fileButton.setBounds(flW, flY, buttonSize, buttonSize);
		fileButton.setText("...");
		
		fileButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				View view = null;
				SysmlElementChooserDialog dialog = 
					new SysmlElementChooserDialog(composite.getShell(), view);
				dialog.setMyFilter(new FilesFilter());
				dialog.setAsModelObject(false);
				dialog.open();
				Object obj = dialog.getMySelectedElement();
				if(obj instanceof IFile) {
					file = (IFile)obj;
					String str =file.getParent().getName()+ "\\" + file.getName();
					fileLocation.setText(str);
				}
				
			}
		});

		projectCheck = new Button(locationGroup, SWT.RADIO);
		int pX = 0, pY = flY + flH, pW = 20, pH = 20;
		projectCheck.setBounds(pX, pY, pW, pH);

		Label projectLabel = new Label(locationGroup, SWT.NONE);
		projectLabel.setBounds(pX + 20, pY , 50, 17);
		projectLabel.setText("Project");

		projectName = new Text(locationGroup, SWT.BORDER|SWT.READ_ONLY);
		int pnX = 0, pnY = pY + pH, pnW = w - buttonSize, pnH = 20;
		projectName.setBounds(pnX, pnY, pnW, pnH);
		
		final Button projectButton = new Button(locationGroup, SWT.PUSH);
		projectButton.setBounds(pnW, pnY, buttonSize, buttonSize);
		projectButton.setText("...");
		projectButton.setEnabled(false);

		projectButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				View view = null;
				SysmlElementChooserDialog dialog = 
					new SysmlElementChooserDialog(composite.getShell(), view);
				dialog.setMyFilter(new ProjectFilter());
				dialog.setAsModelObject(false);
				dialog.open();
				Object obj = dialog.getMySelectedElement();
				if(obj instanceof Project) {
					project = (Project)obj;
					projectName.setText(project.getName());
				}
			}
		});
		
		workspace = new Button(locationGroup, SWT.RADIO);
		int wX = 0, wY = pnY + pnH, wW = 20, wH = 20;
		workspace.setBounds(wX, wY, wW, wH);

		Label wsLabel = new Label(locationGroup, SWT.NONE);
		int wslX = wW, wslY = wY, wslH = 20, wslW = 60;
		wsLabel.setBounds(wslX, wslY, wslW, wslH);
		wsLabel.setText("Workspace");
	
		fileCheck.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				boolean tmp = fileCheck.getSelection();				
				fileButton.setEnabled(tmp);
				fileLocation.setEnabled(tmp);
			}
		});
		
		projectCheck.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				boolean tmp = projectCheck.getSelection();
				projectButton.setEnabled(tmp);
				projectName.setEnabled(tmp);
			}
		});

	}
	
	private void createStatusGroup(Composite composite,int x,int y,int w,int h) {
		int buttonSize = 20;
		Group statusGroup = new Group(composite, SWT.SHADOW_ETCHED_IN);
		statusGroup.setBounds(x, y, w, h);
		statusGroup.setText("Status");
		
		statusList = new List(statusGroup, SWT.MULTI|SWT.BORDER);
		int slX = 0, slY = 15, slW = (w - buttonSize - 10)/2;
		statusList.setBounds(slX, slY, slW, h - slY);

//		StatusKind statusKind = StatusKind.DONE_LITERAL;
		Iterator i = StatusKind.VALUES.iterator();
		while(i.hasNext())
			statusList.add(((StatusKind)(i.next())).getName());

		Button in = new Button(statusGroup, SWT.ARROW|SWT.RIGHT);
		int inX = slX + slW + 5, inY = slY+h/2;
		int inW = buttonSize, inH = buttonSize;
		in.setBounds(inX, inY, inW, inH);

		Button out = new Button(statusGroup, SWT.ARROW|SWT.LEFT);
		int outX = slX + slW+ 5, outY = slY+h/2 + 20;
		int outW = buttonSize, outH = buttonSize;
		out.setBounds(outX, outY, outW, outH);

		selectedStatusesList = new List(statusGroup, SWT.MULTI|SWT.BORDER);
		int sslX = outX + outW + 5, sslY = slY;
		int sslW = slW, sslH = h - sslY;
		selectedStatusesList.setBounds(sslX, sslY, sslW, sslH);
		
		selectedStatusesList.addKeyListener(new KeyListener () {
			  public void keyPressed(KeyEvent e) {
				  int[] selected = selectedStatusesList.getSelectionIndices();
				  int prevIndex = selected[0] - 1;
				  if (prevIndex < 0) prevIndex = 0;
				  
				  if (e.keyCode == SWT.DEL)
					  selectedStatusesList.remove(selected);
				  selectedStatusesList.select(prevIndex);
			  }

			  public void keyReleased(KeyEvent e) {
				  
			  }
		  });
		
		  out.addListener (SWT.Selection, new Listener () {
			  public void handleEvent 	(Event event) {
				  int[] selected = selectedStatusesList.getSelectionIndices();
				  int prevIndex = selected[0] - 1;
				  if (prevIndex < 0) prevIndex = 0;
				  
				  selectedStatusesList.remove(selected);
				  selectedStatusesList.select(prevIndex);
			  }			  
		  });

		in.addListener (SWT.Selection, new Listener () {
			public void handleEvent 	(Event event) {
				moveSelected(statusList, selectedStatusesList);
			}			  
		});
	}
	
	/**
	 * Adds custom listeners to the global objects.
	 */
	private void addListeners() {
		search.addListener(SWT.Selection, new SearchListener());
		statusList.addMouseListener(new MoveSelectionListener());
	}


	/**
	 * Moves selected items in source to target. If the item already exist in 
	 * target it is skipped.
	 * @param source
	 * @param target
	 */
	public void moveSelected(List source, List target) {
		String[] selClasses = source.getSelection();
		for (int i = 0; i<selClasses.length; i++) {
			String[] items = target.getItems();
			boolean canAdd = true;
			for (int j = 0; j<items.length; j++) {
				String text = items[j];
				if(text.equals(selClasses[i]))
					canAdd = false;
			}
			if (canAdd) {
				target.add(selClasses[i]);
			}
		}
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		Object[] result = myWorkbenchContentProvider
		.getChildren(parentElement);
		if (result != null && result.length > 0) {
			return result;
		}
		if (parentElement instanceof IFile) {
			IFile modelFile = (IFile) parentElement;
			IPath resourcePath = modelFile.getFullPath();
			ResourceSet resourceSet = myEditingDomain.getResourceSet();
			try {
				Resource modelResource = resourceSet.getResource(URI
						.createPlatformResourceURI(resourcePath.toString(),
								true), true);
				return myAdapterFctoryContentProvier
				.getChildren(modelResource);
			} catch (WrappedException e) {
				SysmlDiagramEditorPlugin
				.getInstance()
				.logError(
						"Unable to load resource: " + resourcePath.toString(), e); //$NON-NLS-1$
			}
			return Collections.EMPTY_LIST.toArray();
		}
		return myAdapterFctoryContentProvier.getChildren(parentElement);
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		Object parent = myWorkbenchContentProvider.getParent(element);
		if (parent != null) {
			return parent;
		}
		if (element instanceof EObject) {
			EObject eObject = (EObject) element;
			if (eObject.eContainer() == null
					&& eObject.eResource().getURI().isFile()) {
				String path = eObject.eResource().getURI().path();
				return ResourcesPlugin.getWorkspace().getRoot()
				.getFileForLocation(new Path(path));
			}
			return myAdapterFctoryContentProvier.getParent(eObject);
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof IFile) {
			return isValidModelFile((IFile) element);
		}
		return myWorkbenchContentProvider.hasChildren(element)
		|| myAdapterFctoryContentProvier.hasChildren(element);
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		Object[] elements = myWorkbenchContentProvider
		.getElements(inputElement);
		return elements;
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkbenchContentProvider.dispose();
		myAdapterFctoryContentProvier.dispose();
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myWorkbenchContentProvider.inputChanged(viewer, oldInput, newInput);
		myAdapterFctoryContentProvier.inputChanged(viewer, oldInput,
				newInput);
	}

	private boolean isValidModelFile(IFile file) {
		String fileExtension = file.getFullPath().getFileExtension();
		return "sysml".equals(fileExtension); //$NON-NLS-1$
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				RequirementQuerryView.this.fillContextMenu(manager);
			}
		});
//		Menu menu = menuMgr.createContextMenu(viewer.getControl());
//		viewer.getControl().setMenu(menu);
//		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
//				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//		public void doubleClick(DoubleClickEvent event) {
//		doubleClickAction.run();
//		}
//		});
	}
	private void showMessage(String message) {
//		MessageDialog.openInformation(
//		viewer.getControl().getShell(),
//		"Requirement Querry",
//		message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
//		viewer.getControl().setFocus();
		sc.setFocus();
	}

	class SearchListener implements Listener {
		public void handleEvent (Event event) {
			resultTable.removeAll();
			myEditingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
			Object obj = ResourcesPlugin.getWorkspace().getRoot();
			Object[] elements = myWorkbenchContentProvider.getElements(obj);
			levels = getTextToInt(customLevelsInput);			
			priorities = getTextToInt(customPriorityInput);
			statuses = selectedStatusesList.getItems();
			fromProject = projectCheck.getSelection();
//			boolean changed = false;
			TableEntry[] entries = new TableEntry[0];
			
			if(fileCheck.getSelection())
				entries = querryFile(file);
			else if(projectCheck.getSelection())
				entries = querryProject(project);
			else if(workspace.getSelection())
				entries = querryWorkspace();
			if(entries.length > 0) {				
				for(int j = 0;j<entries.length;j++) {
					Requirement req = entries[j].getRequirement();
					IFile file = entries[j].getFile();
					putRequirement(req, file);
				}
				int headers =resultTable.getColumnCount();
				for (int j=0; j<headers; j++) {
					resultTable.getColumn (j).pack ();
				}	
			}
			
			int i = 4;
		}
		
		private TableEntry[] querryFile(IFile file) {
			Object[] objects = getChildren(file);
			boolean changed = false;
			boolean negatedSearch = negated.getSelection();
			ArrayList requirements = new ArrayList();
			for(int i = 0;i<objects.length;i++)
				if(objects[i] instanceof ModelImpl) {
					Object[] nObjects = getChildren(objects[i]);
					for(int j = 0;j<nObjects.length;j++) {
						if(nObjects[j] instanceof Requirement) {
							String status = ((Requirement)nObjects[j]).getStatus().getName();
							boolean valid= false;
							if(contains(statuses, status))
								valid = true;
							else if(negatedSearch) valid = true; 

							int level = ((Requirement)nObjects[j]).getLevel();
							if(contains(levels, level))
								valid = true;

							int priority = ((Requirement)nObjects[j]).getPriority();
							if(contains(priorities, priority))
								valid = true;

							if(valid) {
								requirements.add(new TableEntry((Requirement)nObjects[j],file));
//								putRequirement(((Requirement)nObjects[j]),file);
//								changed = true;
							}
						}
					}
					int ii = 5;
				}
			TableEntry[] entries = new TableEntry[requirements.size()];
			for(int i = 0;i<requirements.size();i++)
				if(requirements.get(i) instanceof TableEntry)
					entries[i] = (TableEntry)requirements.get(i);
			
			return entries;
		}
		
		private void putRequirement(Requirement req, IFile file) {
			String[] titles = {"Name", "Level", "Priority", "Status", "Project", "File"};
			TableItem item = new TableItem (resultTable, SWT.NONE);
			item.setText (0, req.getName());
			item.setText (1, String.valueOf(req.getLevel()));
			item.setText (2, String.valueOf(req.getPriority()));
			item.setText (3, req.getStatus().getName());
			Object project = getParent(file);
			if(project instanceof Project)
				item.setText (4, ((Project)project).getName());
			item.setText (5, file.getName());
		}
		
		private TableEntry[] querryProject(IProject project) {
			Object[] children = getChildren(project);
			TableEntry[] entries = new TableEntry[0];
			for(int i = 0;i<children.length;i++)
				if(children[i] instanceof IFile) {
					TableEntry[] set = querryFile((IFile)children[i]); 
					entries = TableEntry.union(entries, set);
				}
			return entries;
		}
		
		private TableEntry[] querryWorkspace() {
			Object obj = ResourcesPlugin.getWorkspace().getRoot();
			Object[] elements = getChildren(obj);
			TableEntry[] entries = new TableEntry[0];
			for(int i = 0;i<elements.length;i++) {
				if(elements[i] instanceof IProject) {
					TableEntry[] set = querryProject((IProject)elements[i]);
					entries = TableEntry.union(entries, set);
				}
			}
			return entries;
		}
	}

	class MoveSelectionListener extends MouseAdapter{
		public void mouseDoubleClick(MouseEvent e) {				  
			moveSelected(statusList, selectedStatusesList);
		}
	}

	private int[] getTextToInt(Text in) {
		ArrayList set = new ArrayList();
		String s = in.getText();
		boolean valid = validateString(s);
		int cursor = 0;
		while(valid && s.length() - cursor > 0) {
			int lineIndx = s.indexOf('-', cursor), commaIndx = s.indexOf(',', cursor);
			int secondLineIndx = -1;
			if(lineIndx>=0)
				secondLineIndx = s.indexOf('-', lineIndx + 1);
			if(lineIndx < 0)
				lineIndx = s.length();
			if(commaIndx < 0)
				commaIndx = s.length();
			if (secondLineIndx >= 0 && commaIndx > secondLineIndx)
				commaIndx = secondLineIndx;
			if(commaIndx > lineIndx) {
				String substring = s.substring(cursor, lineIndx);
				int first = -1;
				try {
					first = Integer.parseInt(substring);
				}catch(Exception e) {}
				substring = s.substring(lineIndx + 1, commaIndx);
				int second = -1;
				try {
					second = Integer.parseInt(substring);
				}catch(Exception e) {}
				while(first <= second && first >= 0){
					set.add(first);
					first++;
				}
				cursor = commaIndx + 1;
			} else if (lineIndx > commaIndx){
				String substr = s.substring(cursor, commaIndx);
				cursor = commaIndx + 1;
				int n = -1;
				try{
					n = Integer.parseInt(substr);
				}catch(Exception e) {}
				if (n >= 0) set.add(n);
			} else {
				String subString = s.substring(cursor);
				cursor = s.length();
				int n = -1;
				try {
					n = Integer.parseInt(subString);
				} catch (Exception e) {}
				if(n >= 0) set.add(n);
			}
		}

		int[] intSet = new int[set.size()]; 
		for(int i = 0;i<set.size();i++) {
			if(set.get(i) instanceof Integer){
				intSet[i] = (Integer)set.get(i);
			}
		}
		return intSet;
	}

	public boolean validateString(String s) {
		for(int i = 0; i<s.length();i++) {
			char c = s.charAt(i);
			String cs = String.valueOf(c);
			int n = -1;
			try {
				n = Integer.parseInt(cs);
			}
			catch (Exception e) {

			}
			boolean valid = false;
			if(c == ',' || c == '-' || c == ' ' || n>=0) 
				valid = true;
			if (!valid) return false;

		}
		return true;
	}
	
	
	static public boolean contains(Object[] set, Object element) {
		for(int i = 0;i<set.length;i++)
			if(element.equals(set[i]))
				return true;
		return false;
	}
	
	static public boolean contains(int[] set, int element) {
		for(int i = 0;i<set.length;i++)
			if(element == set[i])
				return true;
		return false;
	}
}

class TableEntry {
	private Requirement requirement;
	private IFile file;
	
	public TableEntry(Requirement req, IFile file) {
		setRequirement(req);
		setFile(file);
	}
	
	public Requirement getRequirement() {
		return requirement;
	}
	
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	
	public IFile getFile() {
		return file;
	}
	
	public void setFile(IFile file) {
		this.file = file;
	}
	
	static final TableEntry[] union(TableEntry[]a, TableEntry[] b) {
		TableEntry[] union = new TableEntry[a.length + b.length];
		for(int i = 0; i<a.length;i++)
			union[i] = a[i];
		
		for(int i = 0; i<b.length;i++)
			union[a.length + i] = b[i];

		return union;
	}
}

class FilesFilter extends ViewerFilter {
	public boolean select(Viewer viewer, Object parentElement,
			Object element) {
		if(element instanceof Folder) return false;
		if (element instanceof IContainer) {
			return true;
		}
		
		if (element instanceof IFile) {
			IFile file = (IFile) element;
			return isValidModelFile(file);
		}
		return false;
	}

	private boolean isValidModelFile(IFile file) {
		String fileExtension = file.getFullPath().getFileExtension();
		return "sysml".equals(fileExtension); //$NON-NLS-1$
	}
}

class ProjectFilter extends ViewerFilter {
	public boolean select(Viewer viewer, Object parentElement,
			Object element) {
		if(element instanceof Folder) return false;
		if (element instanceof IContainer) {
			return true;
		}

		if (element instanceof IFile) {
			return false;
		}
		return false;
	}
}
