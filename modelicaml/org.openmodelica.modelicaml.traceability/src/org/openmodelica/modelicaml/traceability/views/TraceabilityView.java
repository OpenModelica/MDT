package org.openmodelica.modelicaml.traceability.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
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
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.services.ServicesRegistry;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.ServiceUtils;
import org.eclipse.papyrus.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.helper.impl.VeMGeneratorScenariosBased;
import org.openmodelica.modelicaml.traceability.Activator;
import org.openmodelica.modelicaml.traceability.views.dialogs.ElementSelectionDialog;
import org.openmodelica.modelicaml.traceability.views.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.traceability.views.helper.VerModelForRequirementsWithoutScenarioCreator;
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

	
	private Object[] expandedRequirementsViewElements;
	private TreePath[] expandedRequirementsViewTreePaths;

	private Object[] expandedScenariosViewElements;
	private TreePath[] expandedScenariosViewTreePaths;

	
	private TreeParent invisibleRoot;
	
	TreeBuilder treeBuilder = new TreeBuilder();
	public final int DEFAULT_EXPAND_LEVEL = 2;
	
	//##################### Selection handling
	 private ISelectionListener selectionListener = new ISelectionListener() {
		 public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			 if (actionLinkWithEditor.isChecked() && sourcepart != TraceabilityView.this && selection instanceof IStructuredSelection) {
	        	List<Object> selectedElements = getCurrentSelections();
				List<Object> selectedElementsAdopted = new ArrayList<Object>();

	        	if (selectedElements != null && selectedElements.size() > 0 ) {
					for (Object object : selectedElements) {
						EObject eObj = adaptSelectedElement(object);
						if (eObj != null) {
							selectedElementsAdopted.add(eObj);
						}
					}
				}
	        	
	        	List<TreeObject> list = new ArrayList<TreeObject>();
	        	list.addAll(treeBuilder.findTreeItems(selectedElementsAdopted.toArray()));
				
	        	if ( selectedElementsAdopted.size() > 0 ) {
					viewer.setSelection(new StructuredSelection(list), true);
				}
	        }
	    }
	};
	
	
	

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
			// add the selection listener
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

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
		
		manager.add(actionLocateInPapyrusModelExplorer);
		manager.add(new Separator());

		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection)selection).getFirstElement();
		if (obj instanceof ScenarioItem || (obj instanceof RequirementItem && !treeBuilder.hasScenarios((TreeObject) obj))) {
			manager.add(actionCreateVerificationModel);
		}
		
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
		manager.add(actionLinkWithEditor);
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

	private Action actionLinkWithEditor;



	
	
	
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
						//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), // don't use this because it will block the window underneath 
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
					final TreeParent treeItem = (TreeParent) obj;
					if (treeItem.getUmlElement() != null && treeBuilder.getSelectedElement() != null) {
						
						ServicesRegistry serviceRegistry;
						try {
							serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
							TransactionalEditingDomain  editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

							CompoundCommand cc = new CompoundCommand("actionCreateVerificationModel");
							Command command = new RecordingCommand(editingDomain) {
								@Override
								protected void doExecute() {
									VeMGeneratorScenariosBased mg = new VeMGeneratorScenariosBased();
									
									mg.setRequirementsDiscarded(treeBuilder.getRequirementsWithUnsatisfiedClients());
									mg.setRequirementsToBeInstantiated(treeBuilder.getRequirementsValid());
									
									mg.setTestScenariosDiscarded(treeBuilder.getScenariosDiscarded());
									HashSet<Element> scenariosToBeInstantiated = new HashSet<Element>();
									scenariosToBeInstantiated.add(treeItem.getUmlElement());
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
				
				/*
				 * If it is a requirement that has no associated requirements
				 */
				else if (obj instanceof RequirementItem && !treeBuilder.hasScenarios((TreeObject) obj)) {
					
					// create a model that only contains the system design model, the selected requirements and all additional models
					final TreeParent treeItem = (TreeParent) obj;
					boolean go = MessageDialog.openQuestion(getSite().getShell(), "Model Creation Confirmation", "This helper will create " +
							"a model that will instantiate the following models: " +
							"\n                 - System model: '" + treeBuilder.getSelectedElement().getName() + "'," +
							"\n                 - Requirement: '" + treeItem.getName()+ "'," +
							"\n                 - and all additional models that are required." +
							"\n\nDo you want to proceed? ");
					
					if (go && treeItem.getUmlElement() != null && treeBuilder.getSelectedElement() != null) {
						
						TransactionalEditingDomain  editingDomain = PapyrusServices.getPapyrusEditingDomain();
						
						if (editingDomain != null) {
							List<Element> elementsToBeInstantiated = new ArrayList<Element>();
							elementsToBeInstantiated.add(treeItem.getUmlElement());
							
							final VerModelForRequirementsWithoutScenarioCreator mc = new VerModelForRequirementsWithoutScenarioCreator(
									treeBuilder.getSelectedElement(), 
									elementsToBeInstantiated, 
									(Package) treeBuilder.getTargetPackage(), 
									treeBuilder.getTargetPackage());
							
							CompoundCommand cc = new CompoundCommand("Model Creation From Traceability View");
							Command command = new RecordingCommand(editingDomain) {
								@Override
								protected void doExecute() {
									EObject createdModel = mc.createModel();
//									PapyrusServices.locateWithReselection(createdModel);
									PapyrusServices.locateInModelExplorer(createdModel, true);
								}
							};
							cc.append(command);
							editingDomain.getCommandStack().execute(cc);
							
						}
						else {
							MessageDialog.openError(new Shell(), "Error in Model Creation From Traceability View", "Could not access the Papyrus editing domain");
						}
						
						
//						ServicesRegistry serviceRegistry;
//						try {
//							
////							serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
////							TransactionalEditingDomain  editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
//
//							
//						} catch (ServiceException e) {
//							e.printStackTrace();
//						}
					}
				}
			}
		};
		actionCreateVerificationModel.setText("Create Verification Model");
		actionCreateVerificationModel.setToolTipText("Create Verification Model");
//		actionCreateVerificationModel.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/generateSimMopdels.png"));
		actionCreateVerificationModel.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "/resources/icons/icons16/new_testsuite.gif")));
		

		
		
		actionShowNotValidElements = new Action("actionShowNotValidElements", 8) { 
			public void run() {
				if (actionShowNotValidElements.isChecked()) {
					ViewerFilter[] filters = {notValidElementFilter};
					viewer.setFilters(filters);
				}
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
		
		
		
		actionLinkWithEditor = new Action("actionLinkWithEditor", 2) { //check box 
			public void run() {
			}
		};
		actionLinkWithEditor.setText("Link with Model Explorer");
		actionLinkWithEditor.setToolTipText("Link with Model Explorer");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));

		
		
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
			@SuppressWarnings("unchecked")
			public void run() {
				if (actionShowRequirementsView.isChecked()) {
					if (treeBuilder.getReqTreeRoot() == null) {
						actionReload.run();
					}
					else {
						
						expandedScenariosViewElements = viewer.getExpandedElements();
						expandedScenariosViewTreePaths = viewer.getExpandedTreePaths();
						
						ISelection selectedElements = viewer.getSelection();
						List<Object> selectedTreeItems = null;
						if (selectedElements instanceof IStructuredSelection) {
							selectedTreeItems = ((IStructuredSelection)selectedElements).toList();
						}
						
						treeBuilder.showTree(invisibleRoot, treeBuilder.MODE_REQUIREMENTS_VIEW);
						viewer.refresh();
						
						if (expandedRequirementsViewElements == null) {
							viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
						}
						else {
							viewer.setExpandedElements(expandedRequirementsViewElements);
							viewer.setExpandedTreePaths(expandedRequirementsViewTreePaths);
						}
						viewer.setSelection( new StructuredSelection(treeBuilder.findTreeItems(selectedTreeItems.toArray())), true);
					}
				}
			}
		};
		actionShowRequirementsView.setText("Requirements View");
		actionShowRequirementsView.setToolTipText("Requirements View");
		actionShowRequirementsView.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/r.png"));
		actionShowRequirementsView.setChecked(true);
		
		
		actionShowScenariosView = new Action("actionShowScenariosView", 8) {
			@SuppressWarnings("unchecked")
			public void run() {
				if (actionShowScenariosView.isChecked()) {
					if (treeBuilder.getScenTreeRoot() == null) {
						actionReload.run();
					}
					else {
						expandedRequirementsViewElements = viewer.getExpandedElements();
						expandedRequirementsViewTreePaths = viewer.getExpandedTreePaths();

						ISelection selectedElements = viewer.getSelection();
						List<Object> selectedTreeItems = null;
						if (selectedElements instanceof IStructuredSelection) {
							selectedTreeItems = ((IStructuredSelection)selectedElements).toList();
						}

						treeBuilder.showTree(invisibleRoot, treeBuilder.MODE_SCENARIOS_VIEW);
						viewer.refresh();
						
						
						if (expandedScenariosViewElements == null) {
							viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
						}
						else {
							viewer.setExpandedElements(expandedScenariosViewElements);
							viewer.setExpandedTreePaths(expandedScenariosViewTreePaths);
						}
						viewer.setSelection( new StructuredSelection(treeBuilder.findTreeItems(selectedTreeItems.toArray())), true);
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
		actionLocateInPapyrusModelExplorer.setText("Locate in Model Explorer");
		actionLocateInPapyrusModelExplorer.setToolTipText("Locate in Model Explorer");
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
	
//	private void showMessage(String message) {
//		MessageDialog.openInformation(
//			viewer.getControl().getShell(),
//			"Traceability",
//			message);
//	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	@SuppressWarnings("unchecked")
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