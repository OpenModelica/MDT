package org.openmodelica.modelicaml.traceability.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.traceability.Activator;
import org.openmodelica.modelicaml.traceability.views.dialogs.ElementSelectionDialog;
import org.openmodelica.modelicaml.traceability.views.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.traceability.views.model.TreeBuilder;
import org.openmodelica.modelicaml.traceability.views.model.TreeObject;
import org.openmodelica.modelicaml.traceability.views.model.TreeParent;

public class TraceabilityView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.openmodelica.modelicaml.traceability.views.TraceabilityView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;

	private Action doubleClickAction;

	private Action actionReload;
	private Action actionShowRequirementsView;
	private Action actionShowScenariosView;
	
	private TreeParent invisibleRoot;
	
	TreeBuilder treeBuilder = new TreeBuilder();

	private Action actionLocateInPapyrusModelExplorer;

	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {
		

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

		private void initialize() {
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
//			
//			TreeParent root = new TreeParent("Root");
//			root.addChild(p1);
//			root.addChild(p2);
//			
			invisibleRoot = new TreeParent("");
//			invisibleRoot.addChild(root);
		}
	}
	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public TraceabilityView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProviderStyledCell());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.traceability.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				TraceabilityView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(actionShowRequirementsView);
		manager.add(actionShowScenariosView);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(actionLocateInPapyrusModelExplorer);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionReload);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private int getMode(){
		if (actionShowRequirementsView.isChecked()) {
			return treeBuilder.MODE_REQUIREMENTS_VIEW;
		}
		if (actionShowScenariosView.isChecked()) {
			return treeBuilder.MODE_SCENARIOS_VIEW;
		}
		return 0;
	}
	
	private void makeActions() {
		
		actionReload = new Action() {
			public void run() {
				ElementSelectionDialog dialog = new ElementSelectionDialog(
						new Shell(), 
						SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectItem.gif"), 
						"Element Selection", 
						"Select the design model to be analyzed", 
						viewer, 
						null, 
						treeBuilder,
						invisibleRoot,
						getMode());
				
				dialog.open();
				
//				Object[] expandedElements = viewer.getExpandedElements();
//				TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
//				
//				TreeObject[] children = invisibleRoot.getChildren();
//				for (int i = 0; i < children.length; i++) {
//					invisibleRoot.removeChild(children[i]);
//				}
//				
////				if (actionInstantiatedClassMode.isChecked()) {
////					treeBuilder.buildTreeFromInstantiatedClass(invisibleRoot, org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot);
////				}
////				else {
////					treeBuilder.buildTreeFromUmlModel(invisibleRoot);	
////				}
//				viewer.setInput(getViewSite());
//				
//				viewer.setExpandedElements(expandedElements);
//				viewer.setExpandedTreePaths(expandedTreePaths);
				
			}
		};
		actionReload.setText("Reload");
		actionReload.setToolTipText("Reload");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/refresh.gif"));

		
		actionShowRequirementsView = new Action("mode: Requirement", 8) {
			public void run() {
				if (actionShowRequirementsView.isChecked()) {
					treeBuilder.showTree(invisibleRoot, treeBuilder.MODE_REQUIREMENTS_VIEW);
					viewer.refresh();
				}
			}
		};
		actionShowRequirementsView.setText("Requirements View");
		actionShowRequirementsView.setToolTipText("Requirements View");
//		actionReqMode.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/refresh.gif"));
		actionShowRequirementsView.setChecked(true);
		
		actionShowScenariosView = new Action("actionShowScenariosView", 8) {
			public void run() {
				if (actionShowScenariosView.isChecked()) {
					treeBuilder.showTree(invisibleRoot, treeBuilder.MODE_SCENARIOS_VIEW);
					viewer.refresh();
				}
			}
		};
		actionShowScenariosView.setText("Scenarios View");
		actionShowScenariosView.setToolTipText("Scenarios View");
//		actionReqMode.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/refresh.gif"));
		
		
		actionLocateInPapyrusModelExplorer = new Action("actionLocateInPapyrusModelExplorer") {
			public void run() {
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if (obj instanceof TreeObject ) {
						Object object = ((TreeObject)obj).getUmlElement();
						
						if (object instanceof EObject) {
							CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
							List<Object> items = new ArrayList<Object>();
							items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
							
							modelExplorerView.getControl().setFocus();
							modelExplorerView.setSelection(new StructuredSelection(items), true);
						}
					}
				}
			}
		};
		actionLocateInPapyrusModelExplorer.setText("Locate in Papyrus");
		actionLocateInPapyrusModelExplorer.setToolTipText("Locate in Papyrus Model Explorer");
		actionLocateInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.eclipse.papyrus.modelexplorer", "/icons/ModelExplorer.gif")));
		
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
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
			viewer.getControl().getShell(),
			"Traceability",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}