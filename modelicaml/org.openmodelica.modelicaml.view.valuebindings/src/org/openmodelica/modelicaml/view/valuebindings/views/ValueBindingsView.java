package org.openmodelica.modelicaml.view.valuebindings.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.eclipse.papyrus.core.utils.EditorUtils;
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
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.openmodelica.modelicaml.profile.handlers.CreateValueMediatorHandler;
import org.openmodelica.modelicaml.profile.handlers.CreateValueMediatorsContainerHandler;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.ElementSelectionDialog;
import org.openmodelica.modelicaml.view.valuebindings.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.view.valuebindings.handlers.DeleteCommandHandler;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeBuilder;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.properties.Constants;
import org.openmodelica.modelicaml.view.valuebindings.utls.ResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.utls.SWTResourceManager;

import org.eclipse.emf.transaction.RecordingCommand;
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
	private Action actionDeleteModelElement;
	private Action actionLocateInPapyrusModelExplorer;
	
	private TreeParent invisibleRoot = null;
	public TreeBuilder tree = new TreeBuilder();

	

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

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

			invisibleRoot = new TreeParent("");
			tree.buildTree(invisibleRoot);
		}
	}
	
	
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

//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(actionAssociateValueClient);
//		manager.add(new Separator());
//		manager.add(actionAssociateValueProvider);
//	}

	private void fillContextMenu(IMenuManager manager) {
		ISelection selection = viewer.getSelection();
		Object selectedTreeObject = ((IStructuredSelection)selection).getFirstElement();
		if (selectedTreeObject instanceof TreeObject) {
			
			if (((TreeObject)selectedTreeObject).isPackage()) {
//				actionAddValueMediatorContainer.setText("Add Value Mediator Container to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				actionAddValueMediatorContainer.setText("Add Value Mediator Container");
				manager.add(actionAddValueMediatorContainer);
				
//				manager.add(new Separator());
//				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
//				manager.add(actionDeleteModelElement);
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if (((TreeObject)selectedTreeObject).isValueMediatorContainer()) {
//				actionAddValueMediator.setText("Add Value Mediator to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				actionAddValueMediator.setText("Add Value Mediator");
				manager.add(actionAddValueMediator);
				
//				manager.add(new Separator());
//				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
//				manager.add(actionDeleteModelElement);
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if (((TreeObject)selectedTreeObject).isValueMediator()) {
//				actionAssociateValueClient.setText("Add Value Client to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				actionAssociateValueClient.setText("Add Value Client");
				manager.add(actionAssociateValueClient);
				
//				actionAssociateValueProvider.setText("Add Value Provider to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				actionAssociateValueProvider.setText("Add Value Provider");
				manager.add(actionAssociateValueProvider);
				
//				manager.add(new Separator());
//				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName());
//				manager.add(actionDeleteModelElement);
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if ( ((TreeObject)selectedTreeObject).isValueClient() || ((TreeObject)selectedTreeObject).isValueProvider()) {
				actionDeleteReference.setText("Delete the reference to \"" + ((TreeObject)selectedTreeObject).getParent().getParent().getName() + "\"");
				manager.add(actionDeleteReference);
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if (((TreeObject)selectedTreeObject).isValueClientNode()) {
				actionAssociateValueClient.setText("Add Value Client");
				manager.add(actionAssociateValueClient);
			}
			else if (((TreeObject)selectedTreeObject).isValueProviderNode()) {
				actionAssociateValueProvider.setText("Add Value Provider");
				manager.add(actionAssociateValueProvider);
			}
		}

//		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
		
		if (((TreeObject)selectedTreeObject).isValueMediatorContainer() || ((TreeObject)selectedTreeObject).isValueMediator()) {
			
			manager.add(new Separator());
//			actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
			actionDeleteModelElement.setText("Delete");
			manager.add(actionDeleteModelElement);
		}
		
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
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
		
		actionLocateInPapyrusModelExplorer = new Action("actionLocateInPapyrusModelExplorer") {
			public void run() {
//				showMessage("The view will be reloaded");
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.papyrus.modelexplorer.modelexplorer");

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if (obj instanceof TreeObject ) {
						Object object = ((TreeObject)obj).getAdapter(EObject.class);
						if (object instanceof EObject) {
							CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
							List<Object> items = new ArrayList<Object>();
							items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
							modelExplorerView.setSelection(new StructuredSelection(items), true);
						}
					}
				}
			}
		};
//		actionLocateInPapyrusModelExplorer.setText("Locate in Papyrus Model Explorer");
		actionLocateInPapyrusModelExplorer.setText("Locate in Papyrus");
		actionLocateInPapyrusModelExplorer.setToolTipText("Locate in Papyrus Model Explorer");
		actionLocateInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.eclipse.papyrus.modelexplorer", "/icons/ModelExplorer.gif")));
		
		
		
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
//				showMessage("actionAssociateValueClient is not implemented yet.");
				
				ISelection selection = viewer.getSelection();
				Object selectedTreeItem = ((IStructuredSelection)selection).getFirstElement();
				
				TreeParent valueMediatorTreeItem = getValueMediator((TreeObject) selectedTreeItem);
				
				if (valueMediatorTreeItem instanceof TreeParent) {
					List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
					listOfAllowedMetaClassesNames.add("Property");
					String title = "Value Client Selection";
					String message = "Click on a model element to be associated as Value Client to the Value Mediator."; // '" + ((TreeParent)selectedTreeParent).getName() + "'.";
					String mode = "addValueClient";

					ElementSelectionDialog dialog = new ElementSelectionDialog(
							new Shell(), 
							SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectOnly.png"), 
							title, 
							message, 
							listOfAllowedMetaClassesNames,
							(TreeParent)valueMediatorTreeItem,
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
//				showMessage("actionAssociateValueProvider is not implemented yet.");
				
				ISelection selection = viewer.getSelection();
				Object selectedTreeItem = ((IStructuredSelection)selection).getFirstElement();
				
				TreeParent valueMediatorTreeItem = getValueMediator((TreeObject) selectedTreeItem);
				
				if (valueMediatorTreeItem instanceof TreeParent) {
					List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
					listOfAllowedMetaClassesNames.add("Property");
					String title = "Value Provider Selection";

					String message = "Click on a model element to be associated as Value Provider to the Value Mediator."; // '" + ((TreeParent)selectedTreeParent).getName() + "'."
					String mode = "addValueProvider";
					ElementSelectionDialog dialog = new ElementSelectionDialog(
							new Shell(), 
							SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectOnly.png"), 
							title, 
							message, 
							listOfAllowedMetaClassesNames,
							valueMediatorTreeItem,
							viewer,
							mode);
					
					dialog.open();
				}
			}
		};
		actionAssociateValueProvider.setText("Add Value Provider");
		actionAssociateValueProvider.setToolTipText("Add Value Provider");
		actionAssociateValueProvider.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/addValueProviders.png"));

		
		
		actionAddValueMediator= new Action("actionAddValueMediator") {
			public void run() {
				CreateValueMediatorHandler h = new CreateValueMediatorHandler();
				try {
					EList<Element> createdElements = h.execute(null); // execute handler

					// add tree item
					if (createdElements instanceof EList) {
						ISelection selection = viewer.getSelection();
						Object parentItem = ((IStructuredSelection)selection).getFirstElement();
						
						for (Element createdElement : createdElements) {
							if (parentItem instanceof TreeParent) {
								TreeParent item  = new TreeParent( ((NamedElement)createdElement ).getName() );
								item.setUmlElement((NamedElement)createdElement);
								((TreeParent)parentItem).addChild(item);
								viewer.add(parentItem, item);
								
								// add clients node
								TreeParent clientsNode  = new TreeParent( Constants.valueClientsTitleName );
								item.addChild(clientsNode);
								viewer.add(item, clientsNode);
								
								// add providers node
								TreeParent providersNode  = new TreeParent( Constants.valueProvidersTitleName );
								item.addChild(providersNode);
								viewer.add(item, providersNode);
								
								viewer.refresh();
							}
						}
					}
					else {
						showMessage("Could not finish the action AddValueMediator ...");
					}
					
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage("Could not finish the action AddValueMediator ...");
				}
//				showMessage("actionValueMediator is not implemented yet.");
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
						Object parentItem = ((IStructuredSelection)selection).getFirstElement();

						if (parentItem instanceof TreeParent) {
							TreeParent item  = new TreeParent( ((NamedElement)createdElement ).getName() );
							item.setUmlElement((NamedElement)createdElement);
							((TreeParent)parentItem).addChild(item);
							viewer.add(parentItem, item);
							viewer.refresh();
						}
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
//				showMessage("actionDeleteReference is not implemented yet.");
				
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				String name = "";
				
				if (obj instanceof TreeObject) {
				
					name = ((TreeObject)obj).getName();
					String title = "Delete Element";
					String message = "Are you sure you want to delete " + "'" + name + "'?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(new Shell(), title, message);

					if (go) {
						
						final TreeParent valueMediator = getValueMediator((TreeObject)obj);
						final EObject valueMediatorSteretypeApplication = valueMediator.getUmlElement().getStereotypeApplication(valueMediator.getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueMediator));
						
						TreeObject itemToBeDeleted = (TreeObject)obj;
						Stereotype itemToBeDeletedStereotype = null;
						String itemToBeDeletedStereotypePropertyName = null;

						if ( itemToBeDeleted.isValueClient() ) {
							itemToBeDeletedStereotype = itemToBeDeleted.getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueClient);
							itemToBeDeletedStereotypePropertyName = Constants.stereotypeQName_ValueClient_obtainsValueFrom;
						} else if ( itemToBeDeleted.isValueProvider() ) {
							itemToBeDeletedStereotype = itemToBeDeleted.getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueProvider);
							itemToBeDeletedStereotypePropertyName = Constants.stereotypeQName_ValueProvider_providesValueFor;
						}
						
						if ( itemToBeDeletedStereotype != null && itemToBeDeletedStereotypePropertyName != null) {
							final Object exisitngList = itemToBeDeleted.getUmlElement().getValue(itemToBeDeletedStereotype, itemToBeDeletedStereotypePropertyName);
							
							if (exisitngList instanceof EList && containsObject((EList) exisitngList, valueMediator.getUmlElement())) {
//								########## storing start
								TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
								CompoundCommand cc = new CompoundCommand("Add value mediator reference");
								Command command = new RecordingCommand(editingDomain) {
									@Override
									protected void doExecute() {
											// Important: use the getStereotypeApplication to get an EObject! 
											DynamicEObjectImpl eObject =(DynamicEObjectImpl)valueMediatorSteretypeApplication;
											
											// add value to the list
											((EList)exisitngList).remove(eObject);
									}
								};
								cc.append(command);
								editingDomain.getCommandStack().execute(cc);
							}
						}
						
						TreeParent parent = itemToBeDeleted.getParent();
						if (parent != null ) {
							parent.removeChild(itemToBeDeleted);
						}
						viewer.remove(itemToBeDeleted);
						viewer.refresh();
					}
				}

				
			}
		};
		actionDeleteReference.setText("Delete this reference");
		actionDeleteReference.setToolTipText("Delete this reference");
		actionDeleteReference.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		

		actionDeleteModelElement = new Action("actionDeleteModelElement") {
			public void run() {
//				showMessage("actionDeleteModelElement is not implemented yet.");

				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				String name = "";
				if (obj instanceof TreeObject) {
					name = ((TreeObject)obj).getName();
					String title = "Delete Element";
					String message = "Are you sure you want to delete " + "'" + name + "'?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(new Shell(), title, message);

					if (go) {
						DeleteCommandHandler h = new DeleteCommandHandler();
						try {
							// delete from the Papyrus UML model
							// Note: this will lead to the deletion in the tree because of the a listener that reacts to the changes of Papyrus Model.
							h.execute(null);
//							}
						} catch (ExecutionException e) {
							e.printStackTrace();
							MessageDialog.openError(new Shell(), "Error", "Could not delete the element '" + name + "'.");
//							showMessage("Could not finish the action DeleteModelElement ...");
						}
					}
				}
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
	
	
	
	// #############################
	private TreeParent getValueMediator(TreeObject item) {
		if (item.isValueMediator()) {
			return (TreeParent) item;
		}
		else if (item.getParent() != null) {
			return getValueMediator(item.getParent());
		}
		return null;
	}
	
	private boolean containsObject(EList list, EObject eObject){
		if (list instanceof EList) {
			for (Object object : (EList)list) {
				if (object instanceof EObject) {
//					if (((EObject)object).eCrossReferences().get(0) == eObject) {
					if (UMLUtil.getBaseElement((EObject)object) == eObject) {
						return true;
					}
				}	
			}
		}
		return false;
	}
	// #############################	
	
	
	public void removeTreeItem(EObject object) {
		TreeObject[] items = invisibleRoot.getChildren();
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() != null && ((TreeObject)items[i]).getUmlElement() == object) {
					TreeParent parent = ((TreeObject)items[i]).getParent();
					if (parent != null) {
						parent.removeChild((TreeObject) items[i]);
					}
					viewer.remove(items[i]);
//					viewer.refresh();
			}
			else {
				if (items[i] instanceof TreeParent) {
					removeTreeItem((TreeParent)items[i], object);
				}
			}
		}
	}

	
	private void removeTreeItem(TreeParent parent, EObject object) {
		TreeObject[] items = parent.getChildren();
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() != null && ((TreeObject)items[i]).getUmlElement() == object) {
				parent.removeChild((TreeObject) items[i]);
				viewer.remove(items[i]);
//				viewer.refresh();
			}
			else {
				if (items[i] instanceof TreeParent) {
					removeTreeItem((TreeParent)items[i], object);
				}
			}
		}
	}
	
	
	private HashSet<Object> findTreeItems(EObject selectedElement, TreeParent parent, HashSet<Object> list) {
		TreeObject[] items = parent.getChildren();
		
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() == selectedElement) {
				list.add(items[i]);
			}
			else {
				if (items[i] instanceof TreeParent) {
					list.addAll(findTreeItems(selectedElement, (TreeParent)items[i], list));
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
					HashSet<Object> objects = findTreeItems(selectedElement, invisibleRoot, new HashSet<Object>());
					List<Object> items = new ArrayList<Object>();
					items.addAll(objects);
					viewer.setSelection(new StructuredSelection(items), true);
				}
	        }
	    }
	};
	
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
	
	public TreeParent getTreeRoot(){
		return this.invisibleRoot;
	}

	public TreeViewer getViewer(){
		return this.viewer;
	}

	
	
}