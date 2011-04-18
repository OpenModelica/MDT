package org.openmodelica.modelicaml.view.valuebindings.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.profile.handlers.CreateValueMediatorsContainerHandler;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.ElementSelectionDialog;
import org.openmodelica.modelicaml.view.valuebindings.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.utls.TreeBuilder;
import org.openmodelica.modelicaml.view.valuebindings.utls.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.utls.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.utls.ViewLabelProviderStyledCell;

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

public class ValueBindingsView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	
	private Action actionAssociateValueClient;
	private Action actionAssociateValueProvider;
	
	private Action actionDeleteReference;
	
	private Action doubleClickAction;

	private Action actionReload;
	private Action actionLinkWithEditor;

	private Action actionAddValueMediator;
	private Action actionAddValueMediatorContainer;
	private Action actionCollapsAll;
	
	private TreeParent invisibleRoot = null;
	
	public TreeBuilder tree = new TreeBuilder();

	private Action actionDeleteModelElement;


	
	
	
	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
//	class TreeObject implements IAdaptable {
//		private String name;
//		private TreeParent parent;
//		
//		public TreeObject(String name) {
//			this.name = name;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setParent(TreeParent parent) {
//			this.parent = parent;
//		}
//		public TreeParent getParent() {
//			return parent;
//		}
//		public String toString() {
//			return getName();
//		}
//
//		public Object getAdapter(java.lang.Class adapter) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	}
	
//	class TreeParent extends TreeObject {
//		private ArrayList children;
//		public TreeParent(String name) {
//			super(name);
//			children = new ArrayList();
//		}
//		public void addChild(TreeObject child) {
//			children.add(child);
//			child.setParent(this);
//		}
//		public void removeChild(TreeObject child) {
//			children.remove(child);
//			child.setParent(null);
//		}
//		public TreeObject [] getChildren() {
//			return (TreeObject [])children.toArray(new TreeObject[children.size()]);
//		}
//		public boolean hasChildren() {
//			return children.size()>0;
//		}
//	}

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
//		private TreeParent invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				if (invisibleRoot==null) initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject)child).getParent();
			}
			return null;
		}
		public Object [] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent)parent).getChildren();
			}
			return new Object[0];
		}
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent)parent).hasChildren();
			return false;
		}
/*
 * We will set up a dummy model to initialize tree heararchy.
 * In a real code, you will connect to a real model and
 * expose its hierarchy.
 */
		private void initialize() {
			// add the selection listener
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

//			TreeObject to1 = new TreeObject("Leaf 1");
//			TreeObject to2 = new TreeObject("Leaf 2");
//			TreeObject to3 = new TreeObject("Leaf 3");
//			TreeParent p1 = new TreeParent("Parent 1");
//			p1.addChild(to1);
//			p1.addChild(to2);
//			p1.addChild(to3);
//			
//			TreeObject to4 = new TreeObject("Leaf 4");
//			TreeParent p2 = new TreeParent("Parent 2");
//			p2.addChild(to4);
			
//			TreeParent root = new TreeParent("Root");
//			root.addChild(p1);
//			root.addChild(p2);

			invisibleRoot = new TreeParent("");
			tree.buildTree(invisibleRoot);
			
			
//			invisibleRoot = new TreeParent("");
//			invisibleRoot.addChild(root);
		}
	}
	
	
//	class ViewLabelProvider extends LabelProvider {
//
//		public String getText(Object obj) {
//			return obj.toString();
//		}
//		public Image getImage(Object obj) {
////			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			if (obj instanceof TreeParent){
//				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/valueMediatorsContainer.png"); 
////				imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			}
//			else {
//				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/valueMediator.png");
//			}
////			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
//		}
//	}
	
	
	
	
//	class NameSorter extends ViewerSorter {
//	}
//
//	/**
//	 * The constructor.
//	 */
//	public ValueBindingsView() {
//	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
//		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setLabelProvider(new ViewLabelProviderStyledCell());
//		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.view.valuebindings.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		
		// add a selection provider.
		getSite().setSelectionProvider(viewer); 
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ValueBindingsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(actionAssociateValueClient);
		manager.add(new Separator());
		manager.add(actionAssociateValueProvider);
	}

	private void fillContextMenu(IMenuManager manager) {
		ISelection selection = viewer.getSelection();
		Object selectedTreeObject = ((IStructuredSelection)selection).getFirstElement();
		if (selectedTreeObject instanceof TreeObject) {
			if (((TreeObject)selectedTreeObject).isPackage()) {
				actionAddValueMediatorContainer.setText("Add Value Mediator Container to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				manager.add(actionAddValueMediatorContainer);
				
				manager.add(new Separator());
				
				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
				manager.add(actionDeleteModelElement);
			}
			else if (((TreeObject)selectedTreeObject).isValueMediatorContainer()) {
				actionAddValueMediator.setText("Add Value Mediator to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				manager.add(actionAddValueMediator);
				
				manager.add(new Separator());
				
				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
				manager.add(actionDeleteModelElement);
			}
			else if (((TreeObject)selectedTreeObject).isValueMediator()) {
				actionAssociateValueClient.setText("Add Value Client to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				manager.add(actionAssociateValueClient);
				
				actionAssociateValueProvider.setText("Add Value Provider to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				manager.add(actionAssociateValueProvider);
				
				manager.add(new Separator());
				
				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName());
				manager.add(actionDeleteModelElement);
			}
			else if ( ((TreeObject)selectedTreeObject).isValueClient() || ((TreeObject)selectedTreeObject).isValueProvider()) {
				actionDeleteReference.setText("Delete the referecen to '" + ((TreeObject)selectedTreeObject).getParent().getParent().getName() + "'");
				manager.add(actionDeleteReference);
			}
		}

		manager.add(new Separator());
		
		drillDownAdapter.addNavigationActions(manager);
		
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(actionAssociateValueClient);
//		manager.add(actionAssociateValueProvider);
		manager.add(actionReload);
		manager.add(actionCollapsAll);
		manager.add(actionLinkWithEditor);
		manager.add(new Separator());
		
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		
		actionReload = new Action("actionReload") {
			public void run() {
//				showMessage("The view will be reloaded");

				TreeObject[] children = invisibleRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					invisibleRoot.removeChild(children[i]);
				}
				tree.buildTree(invisibleRoot);
				viewer.setInput(getViewSite());
				viewer.expandToLevel(2);
			}
		};
		actionReload.setText("(Re)load");
		actionReload.setToolTipText("(Re)load");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/reload.png"));
		
		
		actionCollapsAll = new Action("actionCollapsAll") { //obviously a check box style
			public void run() {
				actionReload.run();
//				viewer.setInput(getViewSite());
//				viewer.expandToLevel(1);
			}
		};
		actionCollapsAll.setText("Collaps all");
		actionCollapsAll.setToolTipText("Collaps all");
		actionCollapsAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));

		
		
		
		actionLinkWithEditor = new Action("actionLinkWithEditor", 2) { //obviously a check box style
			public void run() {

			}
		};
//		actionLinkWithEditor.setChecked(true);
		actionLinkWithEditor.setText("Link with other Model Views");
		actionLinkWithEditor.setToolTipText("Link with other Model Views");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));

		
		
		
		actionAssociateValueClient = new Action() {
			public void run() {
				showMessage("actionAssociateValueClient 2 is not implemented yet.");
				
				ISelection selection = viewer.getSelection();
				Object selectedTreeParent = ((IStructuredSelection)selection).getFirstElement();
				if (selectedTreeParent instanceof TreeParent) {
					List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
					listOfAllowedMetaClassesNames.add("Property");
					String title = "Value Client Selection";
					String message = "Click on a model element to be associated as Value Client to the Value Mediator '" + ((TreeParent)selectedTreeParent).getName() + "'.";
					String mode = "addValueClient";

					ElementSelectionDialog dialog = new ElementSelectionDialog(
							new Shell(), 
							SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectOnly.png"), 
							title, 
							message, 
							listOfAllowedMetaClassesNames,
							(TreeParent)selectedTreeParent,
							viewer,
							mode);
					
					dialog.open();
				}
			}
		};
		actionAssociateValueClient.setText("Add Value Client");
		actionAssociateValueClient.setToolTipText("Add Value Client");
//		actionAssociateValueClient.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		actionAssociateValueClient.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/addValueClient.png"));
		
		
		
		
		actionAssociateValueProvider = new Action() {
			public void run() {
				showMessage("actionAssociateValueProvider 2 is not implemented yet.");
				
				ISelection selection = viewer.getSelection();
				Object selectedTreeParent = ((IStructuredSelection)selection).getFirstElement();
				
				if (selectedTreeParent instanceof TreeParent) {
					List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
					listOfAllowedMetaClassesNames.add("Property");
					String title = "Value Provider Selection";
					String message = "Click on a model element to be associated as Value Provider to the Value Mediator '" + ((TreeParent)selectedTreeParent).getName() + "'.";
					String mode = "addValueProvider";
					ElementSelectionDialog dialog = new ElementSelectionDialog(
							new Shell(), 
							SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectOnly.png"), 
							title, 
							message, 
							listOfAllowedMetaClassesNames,
							(TreeParent)selectedTreeParent,
							viewer,
							mode);
					
					dialog.open();
				}
			}
		};
		actionAssociateValueProvider.setText("Add Value Provider");
		actionAssociateValueProvider.setToolTipText("Add Value Provider");
//		actionAssociateValueProvider.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		actionAssociateValueProvider.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/addValueProviders.png"));

		
		
		
		actionAddValueMediator= new Action("actionValueMediator") {
			public void run() {
				showMessage("actionValueMediator is not implemented yet.");
			}
		};
		actionAddValueMediator.setText("Add Value Mediator");
		actionAddValueMediator.setToolTipText("Add Value Mediator");
		actionAddValueMediator.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/valueMediator.png"));

		
		
		actionAddValueMediatorContainer= new Action("actionAddValueMediatorContainer") {
			public void run() {
				CreateValueMediatorsContainerHandler h = new CreateValueMediatorsContainerHandler();
				try {
					Object createdElement = h.execute(null);
					if (createdElement instanceof NamedElement) {
						ISelection selection = viewer.getSelection();
						Object obj = ((IStructuredSelection)selection).getFirstElement();
						TreeParent item  = new TreeParent( ((NamedElement)createdElement ).getName() );
						item.setUmlElement((NamedElement)createdElement);
						viewer.add(obj, item);
					}
					else {
						showMessage("Could not finish the action AddValueMediatorContainer ...");
					}
					
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage("Could not finish the action AddValueMediatorContainer ...");
				}
//				showMessage("actionValueMediatorContainer is not implemented yet.");
			}
		};
		actionAddValueMediatorContainer.setText("Add Value Mediator Container");
		actionAddValueMediatorContainer.setToolTipText("Add Value Mediator Container");
		actionAddValueMediatorContainer.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/valueMediatorsContainer.png"));

		
		
		actionDeleteReference = new Action("actionDeleteReference") {
			public void run() {
				showMessage("actionDeleteReference is not implemented yet.");
			}
		};
		actionDeleteReference.setText("Delete this reference");
		actionDeleteReference.setToolTipText("Delete this reference");
		actionDeleteReference.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_DELETE));
		
		

		actionDeleteModelElement = new Action("actionDeleteModelElement") {
			public void run() {
				showMessage("actionDeleteModelElement is not implemented yet.");
			}
		};
		actionDeleteModelElement.setText("Delete");
		actionDeleteModelElement.setToolTipText("Delete");
		actionDeleteModelElement.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_DELETE));

		
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click action for " + obj.toString() + " is not implemented yet.");
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(), "Value Bindings View", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	
	
	private HashSet<Object> findTreeItem(EObject selectedElement, TreeParent parent, HashSet<Object> list) {
		TreeObject[] items = parent.getChildren();
		
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() == selectedElement) {
				list.add(items[i]);
			}
			else {
				if (items[i] instanceof TreeParent) {
					list.addAll(findTreeItem(selectedElement, (TreeParent)items[i], list));
				}
			}
		}
		return list;
	}
	
	
	 private ISelectionListener selectionListener = new ISelectionListener() {
		 public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			 if (actionLinkWithEditor.isChecked() && sourcepart != ValueBindingsView.this && selection instanceof IStructuredSelection) {
	        	EObject selectedElement = null;
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
					selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				}
				if ( selectedElement != null ) {
					HashSet<Object> objects = findTreeItem(selectedElement, invisibleRoot, new HashSet<Object>());
					List<Object> items = new ArrayList<Object>();
					items.addAll(objects);
					viewer.setSelection(new StructuredSelection(items), true);
				}
	        }
	    }
	};
	
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
		return null;
	}
	
	
	protected EObject adaptSelectedElement( Object selection) {
		EObject eObject = null;
		if(selection != null) {
			
			if (selection instanceof org.openmodelica.modelicaml.common.instantiation.TreeParent) { // this is an object from components tree view plugin
				return ((org.openmodelica.modelicaml.common.instantiation.TreeParent)selection).getProperty();
			}
			
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}
	
}