/*
 * 
 */
package org.openmodelica.simulation.environment.view.project;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * Lists all available projects from the application workspace.
 * Lists all last used projects which are explicitly stored into another folder by the user.
 * Offers management of projects such as delete, new, import, export
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationProjectListingExtendedView extends ViewPart implements Observer{

	/** The viewer. */
	private TreeViewer viewer;
	
	/** The drill down adapter. */
	private DrillDownAdapter drillDownAdapter;
	
	/** The action view detail. */
	private Action actionViewDetail;
	
	/** The action delete project. */
	private Action actionDeleteProject;
	
	/** The double click action. */
	private Action doubleClickAction;
	
	/** The parent. */
	Composite parent;
	
	/** The default bg. */
	private RGB defaultBG = null;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	
	/**
	 * The Class TreeObject.
	 */
	class TreeObject implements IAdaptable {
		
		/** The name. */
		private String name;
		
		/** The full qualified name of the property. */
		private String fullQualifiedName;
		
		/** The parent. */
		private TreeParent parent;
		
		/**
		 * Instantiates a new tree object.
		 *
		 * @param name the name
		 * @param fullQualifiedName the full qualified name
		 */
		public TreeObject(String name, String fullQualifiedName) {
			this.name = name;
			this.fullQualifiedName = fullQualifiedName;
		}
		
		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Gets the full qualified name.
		 *
		 * @return the full qualified name
		 */
		public String getFullQualifiedName() {
			return fullQualifiedName;
		}
		
		/**
		 * Sets the parent.
		 *
		 * @param parent the new parent
		 */
		public void setParent(TreeParent parent) {
			this.parent = parent;
		}
		
		/**
		 * Gets the parent.
		 *
		 * @return the parent
		 */
		public TreeParent getParent() {
			return parent;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return getName();
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
		 */
		@SuppressWarnings({ "rawtypes" })
		public Object getAdapter(Class key) {
			return null;
		}
	}
	
	/**
	 * The Class TreeParent.
	 */
	class TreeParent extends TreeObject{
		
		/** The children. */
		private ArrayList<TreeObject> children;
		
		/**
		 * Instantiates a new tree parent.
		 *
		 * @param name the name
		 */
		public TreeParent(String name) {
			super(name, "");
			children = new ArrayList<TreeObject>();
		}
		
		/**
		 * Adds the child.
		 *
		 * @param child the child
		 */
		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}
//		public void removeChild(TreeObject child) {
//			children.remove(child);
//			child.setParent(null);
//		}
		/**
 * Gets the children.
 *
 * @return the children
 */
public TreeObject [] getChildren() {
			return (TreeObject [])children.toArray(new TreeObject[children.size()]);
		}
		
		/**
		 * Checks for children.
		 *
		 * @return true, if successful
		 */
		public boolean hasChildren() {
			return !children.isEmpty();
		}
	}

	/**
	 * The Class ViewContentProvider.
	 */
	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {
		
		/** The project root. */
		private TreeParent projectRoot;

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
//				if (projectRoot==null) 
				initialize();
				return getChildren(projectRoot);
			}
			return getChildren(parent);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject)child).getParent();
			}
			return null;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object [] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent)parent).getChildren();
			}
			return new Object[0];
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent)parent).hasChildren();
			return false;
		}
		
		/**
		 * Initialize.
		 */
		private void initialize() {
			projectRoot = new TreeParent("Simulation Project");
//			Tree treeModel = new Tree(tc, SWT.NONE);
//			buildTreeModel(treeModel);
			
			{
				for(String simprojName : Activator.getSimulationProjectCenter().getSimulationProjects()){
					
					Calendar cal = Calendar.getInstance(); 
					cal.setTimeInMillis(Activator.getSimulationProjectCenter().getFileLastModifiedDate(simprojName));
					DateFormat formatter = new SimpleDateFormat();
					
					String date = (formatter.format(cal.getTime()));
//					TreeItem projectItem = new TreeItem(treeProjects, 0);
//					projectItem.setText(simprojName + " [" + date + "]"); 
//					projectItem.setData(simprojName);
					
					TreeObject child = new TreeObject(simprojName + " [" + date + "]", simprojName);
//					child.setParent(parent);
					projectRoot.addChild(child);
				}
			}
			
//			createItems(projectRoot, treeModel.getItem(0));
			
//			TreeObject to1 = new TreeObject("Leaf 1");
//			TreeObject to2 = new TreeObject("Leaf 2");
//			TreeObject to3 = new TreeObject("Leaf 3");
//			TreeParent p1 = new TreeParent("Parent 1");
//			TreeParent p4 = new TreeParent("Parent 1");
//			p1.addChild(to1);
//			p1.addChild(to2);
//			p1.addChild(to3);
//			p1.addChild(p4);
//			
//			TreeObject to4 = new TreeObject("Leaf 4");
//			TreeParent p2 = new TreeParent("Parent 2");
//			p2.addChild(to4);
//			
//			TreeParent root = new TreeParent("Root");
//			root.addChild(p1);
//			root.addChild(p2);
//			
//			mainClassRoot = new TreeParent("");
//			mainClassRoot.addChild(root);
		}
		//First Run: mainClass as TreeParent, MainClass as item
//		private void createItems(TreeParent parent, TreeItem item){
//			if(item.getItems().length != 0){//Is a parent Node
//				for (TreeItem item2 : item.getItems()){
//					if(item2.getItems().length != 0){ 
//						TreeParent p1 = new TreeParent(item2.getText());
////						p1.setParent(parent);
//						parent.addChild(p1);
//						createItems(p1, item2);
//					}
//					else{
//						createItems(parent, item2);
//					}
//				}
//			} else{
//				System.out.println(item.getText());
//				TreeObject child = new TreeObject(item.getText());
////				child.setParent(parent);
//				parent.addChild(child);
//			}
//		}
		
//		public void buildTreeModel(Tree treeProjects) {
//			{
//				for(String simprojName : Activator.getSimulationProjectCenter().getSimulationProjects()){
//					
//					Calendar cal = Calendar.getInstance(); 
//					cal.setTimeInMillis(Activator.getSimulationProjectCenter().getFileLastModifiedDate(simprojName));
//					DateFormat formatter = new SimpleDateFormat();
//					
//					String date = (formatter.format(cal.getTime()));
//					TreeItem projectItem = new TreeItem(treeProjects, 0);
//					projectItem.setText(simprojName + " [" + date + "]"); 
//					projectItem.setData(simprojName);
//				}
//			}
//		}
	}
	
	/**
	 * The Class ViewLabelProvider.
	 */
	class ViewLabelProvider extends LabelProvider {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object obj) {
			return obj.toString();
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}
	
	/**
	 * The Class NameSorter.
	 */
	class NameSorter extends ViewerSorter {
	}
	
	/**
	 * Hook context menu.
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SimulationProjectListingExtendedView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
		defaultBG = viewer.getTree().getBackground().getRGB();
	}

	/**
	 * Contribute to action bars.
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	/**
	 * Fill local pull down.
	 *
	 * @param manager the manager
	 */
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(actionViewDetail);
		manager.add(new Separator());
		manager.add(actionDeleteProject);
	}

	/**
	 * Fill context menu.
	 *
	 * @param manager the manager
	 */
	private void fillContextMenu(IMenuManager manager) {
		manager.add(actionViewDetail);
		manager.add(actionDeleteProject);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	/**
	 * Fill local tool bar.
	 *
	 * @param manager the manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionViewDetail);
		manager.add(actionDeleteProject);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	/**
	 * Make actions.
	 */
	private void makeActions() {
		actionViewDetail = new Action() {
			public void run() {
				
				if(viewer.getTree().getSelection().length != 0){
					
					TreeItem ti = viewer.getTree().getSelection()[0];

					Scanner sc = new Scanner(ti.getText());
					sc.useDelimiter("\\[");
					
					String projectName = sc.next().trim();
					
					
					SimulationProject sp = Activator.getSimulationProjectCenter().getSimulationProject(projectName);
					
					showMessage(projectName + "\nVersion: " + sp.getProjectVersion() + "\nAuthor: " + sp.getProjectAuthor());
				}
			}
		};
		actionViewDetail.setText("Details");
		actionViewDetail.setToolTipText("View Details");
		actionViewDetail.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		actionDeleteProject = new Action() {
			public void run() {
				if(viewer.getTree().getSelection().length != 0){
					deleteProject();
				}
			}
		};
		actionDeleteProject.setText("Delete");
		actionDeleteProject.setToolTipText("Deletes a selected project");
		actionDeleteProject.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				
				for(TreeItem ti : viewer.getTree().getItems()){
					ti.setBackground(new Color(Display.getCurrent(), defaultBG));
				}
				
				viewer.getTree().getSelection()[0].setBackground(new org.eclipse.swt.graphics.Color(Activator.getDefault().getWorkbench().getDisplay(), new RGB(120, 200, 30)));

				Scanner sc = new Scanner(obj.toString());
				sc.useDelimiter("\\[");
				
				Activator.getSimulationProjectCenter().setSelectedSimulationProject(sc.next().trim());
//				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}
	
	/**
	 * Hook double click action.
	 */
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	
	/**
	 * Delete project.
	 */
	private void deleteProject() {

		TreeItem ti = viewer.getTree().getSelection()[0];

		Scanner sc = new Scanner(ti.getText());
		sc.useDelimiter("\\[");
		
		String projectName = sc.next().trim();
		
		String pathToSelectedSimulationProject = Activator
				.getSimulationProjectCenter().getSimulationProjectPath(
						projectName);
		
		MessageDialog messageDialog = new MessageDialog(viewer.getControl()
				.getShell(), "Delete selected Project", null,
				"Are you sure you want to delete " + projectName
						+ "?",
				MessageDialog.QUESTION, new String[] { "Delete", "Cancel" }, 1);
		if (messageDialog.open() == 0) {
			File projectFolder = new File(pathToSelectedSimulationProject);

			deleteAll(projectFolder);

			projectFolder.delete();
			Activator.getSimulationProjectCenter().setSelectedSimulationProject(null);
			Activator.getSimulationProjectCenter().changeInWorkspace();
			
		}
	}

	/**
	 * Recursive method to delete contend files from a Directory.
	 *
	 * @param f the f
	 */
	private void deleteAll(File f) {
		if (f.isFile())
			f.delete();
		else if (f.isDirectory() && f.listFiles().length == 0)
			f.delete();
		else {
			for (File childF : f.listFiles())
				deleteAll(childF);
			f.delete();
		}
	}

	/**
	 * Show message.
	 *
	 * @param message the message
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Project View",
			message);
	}
	
	/**
	 * Instantiates a new simulation project listing extended view.
	 */
	public SimulationProjectListingExtendedView() {
		Activator.getSimulationProjectCenter().addObserverToSimProCenter(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.simulation.environment.view.project.simulationprojectlistingextended");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		viewer.setContentProvider(new ViewContentProvider());
	}
}
