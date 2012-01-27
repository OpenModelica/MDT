package org.openmodelica.modelicaml.traceability.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.services.ServicesRegistry;
import org.eclipse.papyrus.core.utils.ServiceUtils;
import org.eclipse.papyrus.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.helper.impl.VerificationModelsGenerator;
import org.openmodelica.modelicaml.traceability.Activator;
import org.openmodelica.modelicaml.traceability.views.dialogs.ElementSelectionDialog;
import org.openmodelica.modelicaml.traceability.views.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.traceability.views.model.RequirementItem;
import org.openmodelica.modelicaml.traceability.views.model.ScenarioItem;
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
	private Action actionLocateInPapyrusModelExplorer;
	private Action actionShowNotValidElements;
	private Action actionCollapseAll;
	private Action actionShowValidElements;
	private Action actionShowAllElements;
	private Action actionCreateVerificationModel;

	
	private TreeParent invisibleRoot;
	
	TreeBuilder treeBuilder = new TreeBuilder();
	public final int DEFAULT_EXPAND_LEVEL = 2;
	

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
			invisibleRoot = new TreeParent("");
		}
		
	}
	
//	class ViewLabelProvider extends LabelProvider {
//
//		public String getText(Object obj) {
//			return obj.toString();
//		}
//		public Image getImage(Object obj) {
//			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			if (obj instanceof TreeParent)
//			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
//		}
//	}
	
	
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
		
		
		// double click for opening/closing the sub tree
		viewer.addOpenListener(new IOpenListener() {
			public void open(final OpenEvent event) {
				Object firstElement = ((IStructuredSelection)
				event.getSelection()).getFirstElement();
				if (viewer.getExpandedState(firstElement)) {
					viewer.collapseToLevel(firstElement, AbstractTreeViewer.ALL_LEVELS);
				} 
				else {
					viewer.expandToLevel(firstElement, 1);
				}
			}
		});
		
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
//		manager.add(actionShowRequirementsView);
//		manager.add(actionShowScenariosView);
		manager.add(actionShowAllElements);
		manager.add(actionShowValidElements);
		manager.add(actionShowNotValidElements);
	}

	private void fillContextMenu(IMenuManager manager) {
		
		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection)selection).getFirstElement();
		if (obj instanceof ScenarioItem) {
			manager.add(actionCreateVerificationModel);
		}
		manager.add(new Separator());
		
		
		manager.add(actionLocateInPapyrusModelExplorer);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionReload);
		manager.add(new Separator());

		manager.add(actionShowRequirementsView);
		manager.add(actionShowScenariosView);
		manager.add(new Separator());
		
		manager.add(actionCollapseAll);
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
	
	
	class NotValidElementFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof RequirementItem) {
				RequirementItem item = ((RequirementItem)element); 
				if ( item.isValid() ) { 
					return false;
				}
			}
			if (element instanceof ScenarioItem) {
				ScenarioItem item = ((ScenarioItem)element); 
				if ( item.isValid() ) { 
					return false;
				}
			}
			return true;
		}
	}
	NotValidElementFilter notValidElementFilter = new NotValidElementFilter();

	
	class ValidElementFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof RequirementItem) {
				RequirementItem item = ((RequirementItem)element); 
				if ( !item.isValid() ) { 
					return false;
				}
			}
			else if (element instanceof ScenarioItem) {
				ScenarioItem item = ((ScenarioItem)element); 
				if ( !item.isValid() ) { 
					return false;
				}
			}
			return true;
		}
	}
	ValidElementFilter validElementFilter = new ValidElementFilter();



	
	
	
//	class UnknownElementFilter extends ViewerFilter {
//		@Override
//		public boolean select(Viewer viewer, Object parentElement, Object element) {
//			if (element instanceof RequirementItem) {
//				RequirementItem item = ((RequirementItem)element); 
//				if ( item.isUnknown() ) { 
//					return false;
//				}
//			}
//			return true;
//		}
//	}
//	UnknownElementFilter unknownElementFilter = new UnknownElementFilter();
	
	
	private void makeActions() {
		
		actionReload = new Action() {
			public void run() {
				ElementSelectionDialog dialog = new ElementSelectionDialog(
						new Shell(), 
						SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectItem.gif"), 
						"Element Selection", 
						"Select the design model to be analyzed", 
						viewer, 
						treeBuilder.getSelectedElement(), 
						treeBuilder,
						invisibleRoot,
						getMode());
				
				dialog.open();
			}
		};
		actionReload.setText("Reload");
		actionReload.setToolTipText("Reload");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/refresh.gif"));

		
		actionCreateVerificationModel = new Action() {
			public void run() {
				
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				if (obj instanceof ScenarioItem) {
					final ScenarioItem scenarioItem = (ScenarioItem) obj;
					if (scenarioItem.getUmlElement() != null && treeBuilder.getSelectedElement() != null) {
						
						ServicesRegistry serviceRegistry;
						try {
							serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
							TransactionalEditingDomain  editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

							CompoundCommand cc = new CompoundCommand("Add value mediator reference");
							Command command = new RecordingCommand(editingDomain) {
								@Override
								protected void doExecute() {
									VerificationModelsGenerator mg = new VerificationModelsGenerator();
									
									mg.setRequirementsDiscarded(treeBuilder.getRequirementsWithUnsatisfiedClients());
									mg.setRequirementsToBeInstantiated(treeBuilder.getRequirementsValid());
									
									mg.setTestScenariosDiscarded(treeBuilder.getScenariosDiscarded());
									HashSet<Element> scenariosToBeInstantiated = new HashSet<Element>();
									scenariosToBeInstantiated.add(scenarioItem.getUmlElement());
									mg.setTestScenariosToBeInstantiated(scenariosToBeInstantiated);

									mg.setVsc(treeBuilder.getVsc());
									
									mg.setScenarioToVerificationModelCombination(treeBuilder.getScenarioToVerificationModelCombination());
									
									mg.setTargetPackage(treeBuilder.getTargetPackage());
									
									mg.createSimulationModels(treeBuilder.getSelectedElement());
								}
							};
							cc.append(command);
							editingDomain.getCommandStack().execute(cc);
						} catch (ServiceException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		actionCreateVerificationModel.setText("Create Verification Model");
		actionCreateVerificationModel.setToolTipText("Create Verification Model");
		actionCreateVerificationModel.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/generateSimMopdels.png"));

		
		
		actionShowNotValidElements = new Action("actionShowNotValidElements", 8) { 
			public void run() {
				if (actionShowNotValidElements.isChecked()) {
					ViewerFilter[] filters = {notValidElementFilter};
					viewer.setFilters(filters);
				}
//				else {
//					ViewerFilter[] filters = {};
//					viewer.setFilters(filters);
//				}
			}
		};
		actionShowNotValidElements.setText("Show only NOT valid elements");
		actionShowNotValidElements.setToolTipText("Show only NOT valid elements");
		actionShowNotValidElements.setChecked(false);
//		actionShowNotValidElements.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/filter.gif"));

		actionShowValidElements = new Action("actionShowValidElements", 8) { 
			public void run() {
				if (actionShowValidElements.isChecked()) {
					ViewerFilter[] filters = {validElementFilter};
					viewer.setFilters(filters);
				}
//				else {
//					ViewerFilter[] filters = {};
//					viewer.setFilters(filters);
//				}
			}
		};
		actionShowValidElements.setText("Show only valid elements");
		actionShowValidElements.setToolTipText("Show not valid elements");
		actionShowValidElements.setChecked(false);
//		actionShowValidElements.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/filter.gif"));
		
		
		actionShowAllElements = new Action("actionShowAllElements", 8) { 
			public void run() {
				ViewerFilter[] filters = {};
				viewer.setFilters(filters);
			}
		};
		actionShowAllElements.setText("Show all elements");
		actionShowAllElements.setToolTipText("Show all elements");
		actionShowAllElements.setChecked(true);
//		actionShowAllElements.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/filter.gif"));
		
		
		actionCollapseAll = new Action("actionCollapseAll") { //obviously a check box style
			public void run() {
//				actionReload.run();
				TreeObject[] children = invisibleRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					if (children[i] instanceof TreeParent) {
						TreeObject[] children2Level = ((TreeParent) children[i]).getChildren();
						for (TreeObject treeObject : children2Level) {
							viewer.collapseToLevel(treeObject, AbstractTreeViewer.ALL_LEVELS);	
						}
					}
					else {
						viewer.collapseToLevel(children[i], AbstractTreeViewer.ALL_LEVELS);	
					}
					
				}
			}
		};
		actionCollapseAll.setText("Collapse all");
		actionCollapseAll.setToolTipText("Collapse all");
		actionCollapseAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));

		
		
		actionShowRequirementsView = new Action("mode: Requirement", 8) {
			public void run() {
				if (actionShowRequirementsView.isChecked()) {
					if (treeBuilder.getReqTreeRoot() == null) {
						actionReload.run();
					}
					else {
						Object[] expandedElements = viewer.getExpandedElements();
						TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();

						treeBuilder.showTree(invisibleRoot, treeBuilder.MODE_REQUIREMENTS_VIEW);
						viewer.refresh();
						
						viewer.setExpandedElements(expandedElements);
						viewer.setExpandedTreePaths(expandedTreePaths);
						
						viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
					}
				}
			}
		};
		actionShowRequirementsView.setText("Requirements View");
		actionShowRequirementsView.setToolTipText("Requirements View");
		actionShowRequirementsView.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/r.png"));
		actionShowRequirementsView.setChecked(true);
		
		
		actionShowScenariosView = new Action("actionShowScenariosView", 8) {
			public void run() {
				if (actionShowScenariosView.isChecked()) {
					if (treeBuilder.getScenTreeRoot() == null) {
						actionReload.run();
					}
					else {
						Object[] expandedElements = viewer.getExpandedElements();
						TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();

						treeBuilder.showTree(invisibleRoot, treeBuilder.MODE_SCENARIOS_VIEW);
						viewer.refresh();
						
						viewer.setExpandedElements(expandedElements);
						viewer.setExpandedTreePaths(expandedTreePaths);

						viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
					}
				}
			}
		};
		actionShowScenariosView.setText("Scenarios View");
		actionShowScenariosView.setToolTipText("Scenarios View");
		actionShowScenariosView.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/s.png"));

		
		
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
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
//				showMessage("Double-click detected on "+obj.toString());
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