/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.view.componentstree.views;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.AbstractTreeViewer;
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
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.gen.modelica.cg.helpers.OMCClassValidator;
import org.openmodelica.modelicaml.helper.handlers.InstantiateRequirementsHandler;
import org.openmodelica.modelicaml.helper.handlers.InstantiateTestScenarioHandler;
import org.openmodelica.modelicaml.helper.impl.ValueBindingCreator;
import org.openmodelica.modelicaml.helper.impl.VerificationVerdictElementsGenerator;
import org.openmodelica.modelicaml.view.componentstree.Activator;
import org.openmodelica.modelicaml.view.componentstree.dialogs.DialogComponentModification;
import org.openmodelica.modelicaml.view.componentstree.dialogs.UpdateBindingsConfirmationDialog;
import org.openmodelica.modelicaml.view.componentstree.display.TreeUtls;
import org.openmodelica.modelicaml.view.componentstree.display.ViewLabelProvider;
import org.openmodelica.modelicaml.view.componentstree.listeners.DragListener;
import org.openmodelica.modelicaml.view.componentstree.validation.ComponentModificationValidator;
/**
 * The Class ComponentsTree.
 */
public class ComponentsTree extends ViewPart implements ITabbedPropertySheetPageContributor  {

	/** The viewer. */
	private TreeViewer viewer;
	
	/** The drill down adapter. */
	private DrillDownAdapter drillDownAdapter;

	/** The sel. */
	private ISelection sel;
	
	/** The par. */
	private IWorkbenchPart par;
	
	//Dialog box
	/** The i dialog. */
//	private OBSOLETE_DialogComponentInformation iDialog = null;
	
	/** The modification string dialog. */
	private DialogComponentModification modificationStringDialog = null;
	
	/** The shell. */
	private Shell shell = null;

	/** The visualized tree. */
	TreeParent visualizedTree = null;
	
	/** The root. */
	TreeParent root = null;
	
	/** The tree root. */
	public static TreeParent treeRoot;
	
	// Actions
	/** The show path action. */
	private Action showPathAction;
	
	/** The action show all. */
	private Action actionShowAll;
	
	/** The action show inputs. */
	private Action actionShowInputs;
	
	/** The action show outputs. */
	private Action actionShowOutputs;
	
	/** The action edit modification. */
	private Action actionEditModification;
	
	/** The action locate. */
	private Action actionLocate;
	
	/** The action link with editor. */
	private Action actionLinkWithEditor;
	
	/** The action collapse all. */
	private Action actionCollapseAll;
	
	/** The action add to inputs. */
	private Action actionAddToInputs;
	
	/** The action add to outputs. */
	private Action actionAddToOutputs;
	
	/** The action delete from inputs. */
	private Action actionDeleteFromInputs;
	
	/** The action delete from outputs. */
	private Action actionDeleteFromOutputs;

	
	/** The selected class. */
	private Class selectedClass = null;
	
	/** The action add req test evaluation elements. */
	private Action actionAddReqTestEvaluationElements;
	
	/** The action delete req test evaluation elements. */
	private Action actionDeleteReqTestEvaluationElements;
	
	/** The action delete modification. */
	private Action actionDeleteModification;
	
	/** The action show about info. */
	private Action actionShowAboutInfo;
	
	/** The action locate type. */
	private Action actionLocateType;
	
	/** The action show state machines. */
	private IAction actionShowStateMachines;

	private Action actionReload;

	private IAction actionExpandArrays;

	private IAction actionUpdateBindings;

	private Action actionShowValueClients;

	private Action actionShowValueProviders;

	private Action actionShowRequirements;

	public Action actionAddValueProvider;

	public Action actionAddValueClient;

	private Action actionInstantiateRequirements;

	private Action actionAllDeleteModifications;

	private Action actionInstantiateTestScenarios;

	private Action actionValidateComponentModifications;

	private Action actionShowRequiredValueClients;

	private Action actionValidate;

	private Action actionSimulate;
	
	public final static int DEFAULT_EXPAND_LEVEL = 2;

	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 * 
	 * @param parent
	 *            the parent
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
//		viewer.setSorter(new NameSorter()); // sorts the tree names alphabetically
		viewer.setInput(getViewSite());
		
		ViewerFilter[] filters = {showAllFilter};
		viewer.setFilters(filters);

		shell = parent.getShell();

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.view.componentstree");
		makeActions();
		hookContextMenu();
//		hookDoubleClickAction();
		hookSelectionChangedAction();
		contributeToActionBars();

//		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(listener);
		
		// add a selection provider.
		getSite().setSelectionProvider(viewer); 
		
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
		
		// add drag support
		int operations = DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[]{ LocalSelectionTransfer.getTransfer()};
		viewer.addDragSupport(operations, transferTypes, new DragListener(viewer));

	}

	/**
	 * Hook context menu.
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ComponentsTree.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
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
	 * @param manager
	 *            the manager
	 */
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(actionShowAll);
		manager.add(actionShowInputs);	
		manager.add(actionShowOutputs);
		manager.add(actionShowValueClients);
		manager.add(actionShowRequiredValueClients);
		manager.add(actionShowValueProviders);
		manager.add(actionShowRequirements);
		
		manager.add(new Separator());
		manager.add(actionShowStateMachines);
		
		manager.add(new Separator());
		manager.add(actionExpandArrays);

	}
	

	/**
	 * Fill context menu.
	 * 
	 * @param manager
	 *            the manager
	 */
	private void fillContextMenu(IMenuManager manager) {
		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		
		if (obj instanceof TreeObject) {
			TreeObject item = (TreeObject)obj ;
			
			if (item.isLeaf() && item.getProperty() != null ) {
				
				Element modificationStoreLocation = item.getModificationStoreLocation();
				String componentPath = "";
				if (modificationStoreLocation instanceof Property) {
					componentPath = item.getDotPathWithoutFirstLevelComponent();
				}
				else if (modificationStoreLocation instanceof Generalization) {
					componentPath = item.getDotPath();
				}
				
				// ************* Add or edit modification
				if (item.getFirstLevelComponent() != item.getProperty() && !item.isOutput()) { // prevent primitive types for being first level components
					manager.add(actionEditModification);
					
					if (item.getFirstLevelComponent()!= null ) {
//						Element modificationStoreLocation = item.getModificationStoreLocation();
//						String componentPath = "";
//						if (modificationStoreLocation instanceof Property) {
//							componentPath = item.getDotPathWithoutFirstLevelComponent();
//						}
//						else if (modificationStoreLocation instanceof Generalization) {
//							componentPath = item.getDotPath();
//						}
						if (ModificationManager.isInModModListOfComponent(modificationStoreLocation, componentPath)) {
//							String title = "Edit modification in '" + item.getFirstLevelComponent().getName() + "'";
							String title = "Edit binding";
							actionEditModification.setText(title);
							actionEditModification.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FILE));
						}
						else {
//							String title = "Add modification to '" + item.getFirstLevelComponent().getName() + "'";
							String title = "Bind to ...";
							actionEditModification.setText(title);
							actionEditModification.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
						}
					}
				}
				
				// ************* Delete modification
				// if there is a modification defined in the corresponding first level component and the item is not used in inputs
				if (ModificationManager.isInModModListOfComponent(modificationStoreLocation, componentPath) 
						&& !ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDotPath(), false)) {
					manager.add(actionDeleteModification);
					if (item.getFirstLevelComponent()!= null) {
//						actionDeleteModification.setText("Delete modification from '" + item.getFirstLevelComponent().getName() + "'");
						actionDeleteModification.setText("Delete binding");
					}
				}

//				// ************* Add or delete to/from INPUTS
//				// if it is an input and there is no modification defined in its first level components
//				if ( isSimulation(selectedClass) && item.isInput() 
//						&& !ModificationManager.isInModModListOfComponent(modificationStoreLocation, componentPath) ) { 
//					if (ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDotPath(), false)) {
//						manager.add(new Separator());
//						manager.add(actionDeleteFromInputs);
//					}
//					else {
//						manager.add(new Separator());
//						manager.add(actionAddToInputs);	
//					}
//				}
//				else if (isSimulation(selectedClass) && ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDotPath(), false)) {
//					manager.add(new Separator());
//					manager.add(actionDeleteFromInputs);
//				}
//				
//				// ************* Add or delete to/from OUTPUTS
//				if (isSimulation(selectedClass) &&  ModificationManager.isUsedInClassOutputs_removeOption(selectedClass, item.getProperty(), item.getDotPath(), false) ) {
//					manager.add(new Separator());
//					manager.add(actionDeleteFromOutputs);
//				}
//				else {
//					if (isSimulation(selectedClass) && !item.isInput()) {
//						manager.add(new Separator());
//						manager.add(actionAddToOutputs);
//					}
//				}
			}
			
//			// show the "Add a Value Provider" action for a Value Client
//			if ( item.isValueClient() ) {
//				manager.add(new Separator());
//				manager.add(actionAddValueProvider);
//			}
//			// show the "Add a Value Client" action for a Value Provider
//			if ( item.isValueProvider() ) {
//				manager.add(new Separator());
//				manager.add(actionAddValueClient);
//			}

			
			// locate actions
			if (!item.isRoot()) { // the root nodes shall not be located
				
//				showPathAction.setText("Show information about '" + item.getName() + "'");
//				showPathAction.setText("Show details");
//				manager.add(showPathAction); // valid for any item
				
				if (item.getUmlElement() instanceof Property) {
					if ( !(item.getProperty().getOwner() instanceof PrimitiveType)) { // Modelica predefined types shall not be located
						
//						actionLocate.setText("Locate '" + item.getProperty().getName() + "' in Papyrus");
						manager.add(new Separator());
						manager.add(actionLocate);
						
//						if ( item.getProperty().getType() != null ) {
//							if (!(item.getProperty().getType() instanceof PrimitiveType)) { //Primitive types should not be located.
////								actionLocateType.setText("Locate '" + item.getProperty().getType().getName() + "', the type of '" + item.getProperty().getName() + "', in Papyrus Outline View");
//								manager.add(new Separator());
//								manager.add(actionLocateType);
//							}
//						}
					}
				}
				else if (item.getUmlElement() instanceof NamedElement) {
					manager.add(new Separator());
					manager.add(actionLocate);
				}
				
				// delete all modifications from first level components.
				if (item.getUmlElement() instanceof Property && item.getFirstLevelComponent() instanceof Property 
						&& item.getUmlElement() == item.getFirstLevelComponent()) {
					manager.add(new Separator());
					manager.add(actionAllDeleteModifications);					
				}
			}

			
			// root actions
			if (item.isRoot() && isSimulation(selectedClass)) {
				
				// instantiate requirements handler can only be used for the root that returns the selected uml class
//				manager.add(new Separator());
				manager.add(actionInstantiateRequirements);
				actionInstantiateRequirements.setText("Instantiate requirements");
				
				manager.add(actionInstantiateTestScenarios);
				actionInstantiateTestScenarios.setText("Instantiate test scenarios");
				
				
				if (VerificationVerdictElementsGenerator.removeRegTestEvalElemenents_deleteOption(selectedClass, false)) {
					manager.add(new Separator());
					manager.add(actionDeleteReqTestEvaluationElements);
				}
				else {
					manager.add(new Separator());
					manager.add(actionAddReqTestEvaluationElements);					
				}
				// delete all modifications from first level components.
				if (item instanceof TreeParent && ((TreeParent)item).getChildren().length > 0) {
					manager.add(new Separator());
					manager.add(actionAllDeleteModifications);
				}
			}
			
			// used for instantiating requirements from an instantiated test scenario.
			if (item.getProperty() != null && typeIsATestScenarioSimulation(item.getProperty())) {
				manager.add(new Separator());
				actionInstantiateRequirements.setText("Find linked requirements");
				manager.add(actionInstantiateRequirements);
				manager.add(new Separator());
			}
			
			// instantiate test scenarios based on selected requirements
			if (areRequirementsSelected(viewer.getSelection()) != null) {
				manager.add(new Separator());
				actionInstantiateTestScenarios.setText("Find test scenarios");
				manager.add(actionInstantiateTestScenarios);
				manager.add(new Separator());
			}
			
			if (!item.isPredefinedModelicaProperty()) {
				// updated bindings action
				manager.add(new Separator());
				manager.add(actionUpdateBindings);
			}
		}
		
//		manager.add(new Separator());
		//drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		
		//manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	
	private boolean typeIsATestScenarioSimulation(Property property) {
		Type type = property.getType();
		
		if (type != null && type.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null) {
			return true;
		}
		return false;
	}
	
	private boolean isSimulation(Class aClass) {
		if (aClass.getAppliedStereotype(Constants.stereotypeQName_Simulation) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Fill local tool bar.
	 * 
	 * @param manager
	 *            the manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionReload);
		manager.add(new Separator());
//		manager.add(actionValidate);
//		manager.add(actionSimulate);
		manager.add(new Separator());
		manager.add(actionCollapseAll);
		manager.add(actionLinkWithEditor);
		
		manager.add(new Separator());
		manager.add(actionShowAboutInfo);
		
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	/**
	 * Make actions.
	 */
	private void makeActions() {
		
//		actionAddValueProvider = new Action("actionAddValueProvider") {
//			public void run() {
//				
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection) selection).getFirstElement();
//				if (obj instanceof TreeObject) {
//					TreeObject item = (TreeObject)obj;
//					if (item.isValueClient()) {
//						List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
//						listOfAllowedMetaClassesNames.add("Property");
//						String title = "Value Provider Selection";
//						String message = "Click on a model element to be associated as Value Provider."; 
//
//						NOT_USED_SelectValueClientOrProviderDialog dialog = new NOT_USED_SelectValueClientOrProviderDialog(
//								new Shell(), 
//								SWTResourceManager.getImage(NOT_USED_SelectValueClientOrProviderDialog.class,"/icons/selectOnly.png"), 
//								title, 
//								message, 
//								listOfAllowedMetaClassesNames,
//								item,
//								viewer,
//								NOT_USED_SelectValueClientOrProviderDialog.MODE_ADD_PROVIDER,
//								actionEditModification,
//								actionLinkWithEditor);
//						dialog.open();
//					}
//				}
//			}
//		};
//		actionAddValueProvider.setText("Add a Value Provider");
//		actionAddValueProvider.setToolTipText("Add a Value Provider");
//		actionAddValueProvider.setImageDescriptor(ImageDescriptor.createFromFile(NOT_USED_SelectValueClientOrProviderDialog.class, "/icons/addValueProviders.png"));

		
		
//		actionAddValueClient = new Action("actionAddValueClient") {
//			public void run() {
//				
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection) selection).getFirstElement();
//				if (obj instanceof TreeObject) {
//					TreeObject item = (TreeObject)obj;
//					if (item.isValueProvider()) {
//						List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
//						listOfAllowedMetaClassesNames.add("Property");
//						String title = "Value Client Selection";
//						String message = "Click on a model element to be associated as Value Client."; 
//
//						NOT_USED_SelectValueClientOrProviderDialog dialog = new NOT_USED_SelectValueClientOrProviderDialog(
//								new Shell(), 
//								SWTResourceManager.getImage(NOT_USED_SelectValueClientOrProviderDialog.class,"/icons/selectOnly.png"), 
//								title, 
//								message, 
//								listOfAllowedMetaClassesNames,
//								item,
//								viewer,
//								NOT_USED_SelectValueClientOrProviderDialog.MODE_ADD_CLIENT,
//								actionEditModification,
//								actionLinkWithEditor);
//						dialog.open();
//					}
//				}
//			}
//		};
//		actionAddValueClient.setText("Add a Value Client");
//		actionAddValueClient.setToolTipText("Add a Value actionAddValueClient");
//		actionAddValueClient.setImageDescriptor(ImageDescriptor.createFromFile(NOT_USED_SelectValueClientOrProviderDialog.class, "/icons/addValueClient.png"));

		
		actionAllDeleteModifications = new Action("actionAllDeleteModifications") {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = (TreeParent)obj;
					if (item.isRoot() && item.getChildren().length > 0 ) {
						TreeObject[] chldren = item.getChildren();

						String childrenNameList = "";
						for (int i = 0; i < chldren.length; i++) {
							TreeObject treeObject = chldren[i];
							childrenNameList = childrenNameList + "    - " + treeObject.getDotPath() + "\n";
						}
						
						String title = "Delete all modifications";
						String message = "Are you sure you want to delete all modifications from the following first-level components of " + "'" + item.getName() + "'?" 
								+ "\n\n" + childrenNameList
								+ "\nThis action cannot be undone.";
						Boolean go = MessageDialog.openQuestion(new Shell(), title, message);
						
						if (go) {
							for (int i = 0; i < chldren.length; i++) {
								TreeObject treeObject = chldren[i];
								if (treeObject.getUmlElement() != null && treeObject.getUmlElement()==treeObject.getFirstLevelComponent()) {
									ModificationManager.deleteAllComponentModifications(treeObject.getUmlElement());
									viewer.refresh();
									viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
								}
							}
						}
					}
					else if (item.getUmlElement() != null && item.getUmlElement()==item.getFirstLevelComponent()) {
						
						HashSet<String> modList = ModificationManager.getModifications(item.getUmlElement());
						String modListString = "";
						for (String string : modList) {
							modListString = modListString + "    - " + string + "\n";
						}
						
						String title = "Delete all modifications";
						String message = "Are you sure you want to delete the following modifications from " + "'" + item.getDotPath() + "'?"
								+ "\n\n"
								+ modListString 
								+ "\nThis action cannot be undone.";
						Boolean go = MessageDialog.openQuestion(new Shell(), title, message);
						if (go) {
							ModificationManager.deleteAllComponentModifications(item.getUmlElement());
							viewer.refresh();
							viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
						}
					}
				}
			}
		};
		actionAllDeleteModifications.setText("Delete all modifications");
		actionAllDeleteModifications.setToolTipText("Delete all modifications");
		actionAllDeleteModifications.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		actionReload = new Action("actionReload") {
			public void run() {
				showSelection(par, sel);
				
				// validate the component modifications
				actionValidateComponentModifications.run();
			}
		};
		actionReload.setText("(Re)load and validate");
		actionReload.setToolTipText("(Re)load and validate");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/reload.png"));
		actionReload.setEnabled(false);
		
		actionValidateComponentModifications = new Action("actionValidateComponentModifications") { //obviously a check box style
			public void run() {
				
				if (root != null) {
					
					// UIJob is needed because composites are used for xtext editors. 
					// TODO: refactor the editors glue code in order to don't use the any UI objects for the validation of action code. 
					UIJob UIjob = new UIJob("Component Modifications Validation") {
						public IStatus runInUIThread(IProgressMonitor monitor) {
							
							ComponentModificationValidator validator = new ComponentModificationValidator(root);
							validator.validate();
							
							viewer.refresh();
							
							return Status.OK_STATUS;
						}
					};
					UIjob.setUser(true);
					UIjob.schedule();
				}
				else {
					MessageDialog.openError(new Shell(), "Component Validation", "Please select a class in Papyrus Model Explorer.");
				}
			}
		};
		actionValidateComponentModifications.setText("Validate Component Modifications");
		actionValidateComponentModifications.setToolTipText("Validate Component Modifications");
		actionValidateComponentModifications.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/validate.gif"));
		actionValidateComponentModifications.setEnabled(false);
		
		actionValidate = new Action("actionValidate") { //obviously a check box style
			public void run() {
				if (root != null) {
					actionValidateComponentModifications.run();
					// Check with OMC
					if (root.getSelectedClass() != null) {
						OMCClassValidator omcValidator = new OMCClassValidator(root.getSelectedClass());
						omcValidator.validate();
					}
				}
				else {
					MessageDialog.openError(new Shell(), "Component Validation", "Please select a class in Papyrus Model Explorer.");
				}
			}
		};
		actionValidate.setText("Validate");
		actionValidate.setToolTipText("Validate");
		actionValidate.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/validate.gif"));
		actionValidate.setEnabled(false);
		
		
		actionSimulate = new Action("actionSimulate") {
			public void run() {
//				viewer.setSelection(new StructuredSelection(root));
//				CGFromEntireModelAndSimThisClassWithOMCSimCenterAction c = new CGFromEntireModelAndSimThisClassWithOMCSimCenterAction();
//				try {
//					c.execute(null);
//				} catch (ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		};
		actionSimulate.setText("Simulate with OMC");
		actionSimulate.setToolTipText("Simulate with OMC");
		actionSimulate.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/simulate.png"));
		actionSimulate.setEnabled(false);
		
		
		actionUpdateBindings = new Action("actionUpdateBindings") {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = (TreeParent)obj;
					
					// open the confirmation dialog
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					UpdateBindingsConfirmationDialog confirmationDialog = new UpdateBindingsConfirmationDialog(shell, item);
					confirmationDialog.open();
				
					int result = confirmationDialog.getReturnCode();
					
					if (result == IDialogConstants.OK_ID) {
						boolean deleteOldBindings = confirmationDialog.deleteAllBindings();
						
						// update bindings
//						ValueBindingCreator vc = new ValueBindingCreator();
//						vc.updateAllBindings(root.getSelectedClass().getModel(), item, root, deleteOldBindings, confirmationDialog.isAutomaticSelectionOfProviders(), true, false);

						try {
							// update bindings
							ValueBindingCreator vc = new ValueBindingCreator();
							vc.updateAllBindings(root.getSelectedClass().getModel(), item, root, deleteOldBindings, confirmationDialog.isAutomaticSelectionOfProviders(), true, false);
							
							// get the updated items
							List<TreeObject> updatedItems = vc.getUpdatedItems();
							HashMap<TreeObject, String> updatedModifications = vc.getUpdatedItemsToNewModification();
							
							// get the deleted modifications items
							List<TreeObject> deletedItemsModifications = vc.getDeletedItemsModification();
							List<String> deletedModifications = vc.getDeletedModifications();

							String infoText = "The following updates were performed: \n";
							String message = "";
							String separator = "\n\n---------------------------------------------" + 
												"------------------------------------------------" +
												"--------------------- \n\n";
							
							// update the deleted modification items in viewer 
							for (TreeObject deletedItemModificationItem : deletedItemsModifications) {
								// update in viewer
								updateItem(deletedItemModificationItem);
							}
							
							viewer.refresh();
							
							// select the item again in view
							TreeUtls.selectInView(obj, root, viewer);
							viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
							
							// create message for deleted modifications
							for (String string : deletedModifications) {
								message = message + "DELETED: " + string + separator ;
							}
							
							if (updatedItems.size() > 0) {
								for (TreeObject updatedTreeObject : updatedItems) {
									
									// update in viewer
									updateItem(updatedTreeObject);
									
									// create message for updated modifications
									message = message + "UPDATED: " +  updatedTreeObject.getDotPath()+ " = " + updatedModifications.get(updatedTreeObject)
											+ separator ;
								}
								
								// show results.
								DialogMessage dialog = new DialogMessage(new Shell(), "Result", infoText, message);
								dialog.open();
								
							} else {
								showMessage("Result", "No updates were performed.");
							}
							
							new ProgressMonitorDialog(new Shell()).run(true, true, vc);
						} catch (InvocationTargetException e) {
							e.printStackTrace();
							MessageDialog.openError(new Shell(), "Value Bindigns Geneartion Process Error", 
									"It was not possible to invoce the value bindings generation.");
						} catch (InterruptedException e) {
							e.printStackTrace();
							MessageDialog.openError(new Shell(), "Value Bindigns Geneartion Process Abort", 
									"The generation of value bindings was canceled.");
						}
					}
				}
			}
		};
//		actionUpdateBindings.setText("Update bindings in all sub-components");
//		actionUpdateBindings.setToolTipText("Update bindings in all sub-components");
		actionUpdateBindings.setText("Update bindings");
		actionUpdateBindings.setToolTipText("Update bindings in all sub-components");
//		actionUpdateBindings.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/updateBindings.png"));
		actionUpdateBindings.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png")));
		
		
//		showPathAction = new Action() {
//			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection) selection).getFirstElement();
//				//LABEL = ((TreeParent)obj).getFirstLevelComponent().getName();
//				String LABEL = null;
//				if (obj instanceof TreeParent) {
//					if ( !((TreeParent) obj).isRoot()) { 
//						TreeParent item = ( (TreeParent)obj);
//						iDialog = new OBSOLETE_DialogComponentInformation(shell, "Component Information", LABEL, item.getDotPath(), item, null);
//						iDialog.open();
//					}
//				}
//			}
//		};
//		showPathAction.setText("Show information");
//		showPathAction.setToolTipText("Show information");
//		showPathAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		
		actionShowAll = new Action("actionShowAll", 8) {
			public void run() {
				if (actionShowAll.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {showAllFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, root, viewer);
					viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowAll.setText("Show All");
		actionShowAll.setChecked(true);
		actionShowAll.setToolTipText("Show All");
//		actionShowAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		actionShowInputs = new Action("actionShowInputs", 8) {
			public void run() {
				if (actionShowInputs.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {inputFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, root, viewer);					
					viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowInputs.setText("Show only Inputs");
		actionShowInputs.setToolTipText("Show only Inputs");
//		actionShowInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		actionShowOutputs = new Action("actionShowOutputs", 8) {
			public void run() {
				if (actionShowOutputs.isChecked()) {
					
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {outputFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, root, viewer);					
					viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowOutputs.setText("Show only Outputs");
		actionShowOutputs.setToolTipText("Show only Outputs");
//		actionShowOutputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		
		actionShowValueClients = new Action("actionShowValueClients", 8) {
			public void run() {
				if (actionShowValueClients.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {valueClientFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, root, viewer);					
					viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowValueClients.setText("Show only Value Clients");
		actionShowValueClients.setToolTipText("Show only Value Clients");
//		actionShowValueClients.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		
		actionShowRequiredValueClients = new Action("actionShowRequiredValueClients", 8) {
			public void run() {
				if (actionShowRequiredValueClients.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {requiredValueClientFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, root, viewer);					
					viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowRequiredValueClients.setText("Show only Mandatory Value Clients");
		actionShowRequiredValueClients.setToolTipText("Show only Mandatory Value Clients");
		
		
		actionShowValueProviders = new Action("actionShowValueProviders", 8) {
			public void run() {
				if (actionShowValueProviders.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {valueProviderFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, root, viewer);					
					viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowValueProviders.setText("Show only Value Providers");
		actionShowValueProviders.setToolTipText("Show only Value Providers");
//		actionShowValueProviders.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		actionShowRequirements = new Action("actionShowRequirements", 8) {
			public void run() {
				if (actionShowRequirements.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {requirementInstanceFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, root, viewer);					
					viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowRequirements.setText("Show only Requirements");
		actionShowRequirements.setToolTipText("Show only Requirements");
//		actionShowRequirements.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		
		
		actionShowStateMachines = new Action("actionShowStateMachines", 2) {
			public void run() {
				if (actionShowStateMachines.isChecked()) {
//					TreePath[] path = viewer.getExpandedTreePaths();
//					ViewerFilter[] filters = viewer.getFilters();
//					List<ViewerFilter> newFilters = new ArrayList<ViewerFilter>();
//					for (int i = 0; i < filters.length; i++) {
//						ViewerFilter viewerFilter = filters[i];
//						newFilters.add(viewerFilter);
//					}
//					newFilters.add(hideStateMachnineElementFilter);
//					ViewerFilter[] newF = (ViewerFilter[]) newFilters.toArray();
//					viewer.setFilters(newF);
					viewer.removeFilter(hideStateMachnineElementFilter);
					viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
//					viewer.setExpandedTreePaths(path);
				}
				else {
//					TreePath[] path = viewer.getExpandedTreePaths();
					viewer.addFilter(hideStateMachnineElementFilter);
					viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
//					viewer.setExpandedTreePaths(path);
				}
//				showSelection(par, sel);
				//if (actionShowStateMachines.isChecked() || !actionShowStateMachines.isChecked()) {
					//showMessage("actionShowStateMachines executed");
				//}
			}
		};
		actionShowStateMachines.setText("Show StateMachines");
		actionShowStateMachines.setChecked(true);
		actionShowStateMachines.setToolTipText("Show StateMachines");
//		actionShowStateMachines.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		

		
		actionExpandArrays = new Action("actionExpandArrays", 2) {
			public void run() {
				if ( actionExpandArrays.isChecked() ) {
					showMessage("Note", "This feature is not implemented yet. The purpose of " +
							"this feature will be to include into the components tree " +
							"all combination of components based on their arrays size definition.");
					actionExpandArrays.setChecked(false);
				}
			}
		};
		actionExpandArrays.setText("Expand Arrays");
		actionExpandArrays.setChecked(false);
		actionExpandArrays.setToolTipText("Expand Arrays");
//		actionExpandArrays(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		
		
		actionLocate = new Action(){
			public void run() {
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
		actionLocate.setText("Locate component");
		actionLocate.setToolTipText("Locate component in Papyrus Model Explorer");
		actionLocate.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.eclipse.papyrus.modelexplorer", "/icons/ModelExplorer.gif")));
//		actionLocate.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
//		
		actionLocateType = new Action(){
			public void run() {
				
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.papyrus.modelexplorer.modelexplorer");

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if (obj instanceof TreeObject ) {
						TreeParent item = ( (TreeParent)obj);
						Property property = item.getProperty();
						if (property != null) {
							Type type = property.getType();
							if (type != null && !(type instanceof PrimitiveType)) {
								Object object = type;
								if (object instanceof EObject) {
									CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
									List<Object> items = new ArrayList<Object>();
									items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
									modelExplorerView.setSelection(new StructuredSelection(items), true);
								}
							}
							else {
								Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
								if (type == null) {
									MessageDialog.openError(shell, "Error:", "No type is defined for " +  item.getName() + ".");	
								}
								else {
									MessageDialog.openError(shell, "Error:", "Primitive Types cannot be located.");
								}
							}
						}
					}
				}
			}
		};	
		actionLocateType.setText("Locate the component type");
		actionLocateType.setToolTipText("Locate in Papyrus Model Explorer");
		actionLocateType.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.eclipse.papyrus.modelexplorer", "/icons/ModelExplorer.gif")));
//		actionLocateType.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
		
		
		actionEditModification = new Action("actionEditModification") {
			public void run() {
				ISelection selection = viewer.getSelection(); 
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					
					// get the modification for this item in its first level component
					String modificationString = "";
					HashSet<String> modList = ModificationManager.getModifications(item.getModificationStoreLocation());
					if (modList.size() > 0 ) {
						for (String string : modList) {
							String[] splitted = string.trim().split("=");
							if (splitted.length > 1) {
								String leftHand = splitted[0].trim();
								String rightHand = splitted[1].trim();
								if (leftHand.equals(item.getDotPathWithoutFirstLevelComponent())) {
									modificationString = rightHand;
								}
								else if (leftHand.equals(item.getDotPath()) ) {
									modificationString = rightHand;
								}
							}
							else {
//								System.err.println("ModelicaML modification parsing error (ComponentsTree.java 01) for the string '" + string + "'");
								//MessageDialog.openError(shell, "ModelicaML modifications parsing error (01)", "'"+string+"' ");
							}
						}				
					}
					
					String fullModificationString = item.getDotPathWithoutFirstLevelComponent() + " = " + modificationString;
					
					//modificationStringDialog = new DialogModificationStringInput(shell, "Edit Component Modification", item.getDothPath() + " = ", modificationString, item, null, root);
					modificationStringDialog = new DialogComponentModification(shell, "Edit Component Modification", item.getDotPath(), fullModificationString, item, null, root);
					
					modificationStringDialog.open();
					
					if (modificationStringDialog.getReturnCode() == 0) { // OK button pressed
						String string = modificationStringDialog.getValue();
						Shell shell = new Shell();
						
						if ( string.trim().equals("")) {
							// Delete modification in items firstLevelComponent
							// Delete modification
							Element modificationStoreLocation = item.getModificationStoreLocation();
							String componentPath = "";
							if (modificationStoreLocation instanceof Property) {
								componentPath = item.getDotPathWithoutFirstLevelComponent();
							}
							else if (modificationStoreLocation instanceof Generalization) {
								componentPath = item.getDotPath();
							}
							ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);

							// Delete modification in item list.
							//item.deleteFromModificationListBasedOnLeftRightHandValue(item.getDothPath(), modificationString);

							item.setFinalModificationRightHand(null); // TODO: verify against the line above
							item.setFinalModificationSource(null);
//							viewer.update(item, null);
//							updateItem(item);
						}
						else {
							Boolean hasOutterBraces = string.trim().startsWith("(");
							if (hasOutterBraces && !string.trim().endsWith(")")) {
								//System.err.println("ModelicaML modifications parsing error ComponentsTree.class 02: Not well-formed modification string (missing closing brace): " + string);
								MessageDialog.openError(shell, "ModelicaML modifications parsing error (ComponentsTree.java 02)", "The modification string '"+string+"' is not well-formed (missing closing brace)");
							}
							String modStringWithoutBraces = StringUtls.removeOutterBraces(string);
							
							// Delete modification 
							if (modStringWithoutBraces.startsWith(item.getDotPathWithoutFirstLevelComponent())) {
								Element modificationStoreLocation = item.getModificationStoreLocation();
								String componentPath = "";
								if (modificationStoreLocation instanceof Property) {
									componentPath = item.getDotPathWithoutFirstLevelComponent();
								}
								else if (modificationStoreLocation instanceof Generalization) {
									componentPath = item.getDotPath();
								}
								String deletedModification = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);
								
								// Delete modification in item list.
								//item.deleteFromModificationList(item.getFirstLevelComponent().getName()+ "." + deletedModification);
								item.setFinalModificationRightHand(null); // verify against the line above
								item.setFinalModificationSource(null);
//								viewer.update(item, null);
//								updateItem(item);

							}

							
							// Add the new value to firstLevelComponent and to the component modifications list.
							//ModificationManager.addComponentModification(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent(), modificationStringDialog.getValue(), true);
							String[] parts = modStringWithoutBraces.split("=");
							if (parts.length > 0) {
//								String leftHandPart = parts[0];
								
								// TODO: test it!
								String leftHandPart = "";
								if (item.getFirstLevelComponent() != null && item.getModificationStoreLocation() instanceof Generalization) {
									leftHandPart = StringUtls.replaceSpecChar(item.getFirstLevelComponent().getName()) + "." + parts[0].trim();
								}
								else if (item.getFirstLevelComponent() != null && item.getModificationStoreLocation() instanceof Property) {
									leftHandPart = parts[0].trim();
								}
								//System.err.println("leftHandPart " + leftHandPart);
								if (parts.length > 1) {
									String rightHandPart = parts[1];
									//System.err.println("rightHandPart " + rightHandPart);
									ModificationManager.addComponentModification(item.getModificationStoreLocation(), leftHandPart, rightHandPart, true);
									//ModificationManager.addComponentModification(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent(), modificationStringDialog.getValue(), true); // OLD
									
//									// Add to the modification in item list.
//									//item.addToModificationList(item.getDothPath() + " = " + modificationStringDialog.getValue()); // OLD
//									String storeString = "";
//									if (hasOutterBraces) {
//										storeString = "(" + StringUtls.replaceSpecChar(item.getFirstLevelComponent().getName()) + "." + modStringWithoutBraces + ")";
//									}
//									else {
//										storeString = StringUtls.replaceSpecChar(item.getFirstLevelComponent().getName()) + "." + modStringWithoutBraces ;
//									}
									//item.addToModificationList(storeString); // Store with braces if there are any!
									item.setFinalModificationRightHand(rightHandPart); // verify this against the line above!
									item.setFinalModificationSource(item.getFirstLevelComponent()); // TODO: this is wrong if the modification source is Generalization! 
//									viewer.update(item, null);
//									updateItem(item);
								}
								else {
									//System.err.println("ModelicaML modifications parsing error ComponentsTree.class 03, no right hand part found: " + string);
									MessageDialog.openError(shell, "ModelicaML modifications parsing error (ComponentsTree.java 03)", "No right hand part found in '" + string + "'. This string will not be stored.");		
								}
							}
							else {
								//System.err.println("ModelicaML modifications parsing error ComponentsTree.class 04: Not well-formed modification string (missing closing brace): " + string);
								MessageDialog.openError(shell, "ModelicaML modifications parsing error (ComponentsTree.java 04)", "The modification string '"+string+"' does not contain an equality lign ('='). It will not be stored.");
							}
							//viewer.update(item, null);
						}
						//viewer.update(item, null);
					}
//					viewer.update(item, null);
					updateItem(item);
				}
			}
		};
		actionEditModification.setText("Edit modification");
		actionEditModification.setToolTipText("Edit modification");
		actionEditModification.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FILE));
		
		
		actionDeleteModification = new Action("actionDeleteModification") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					
					String title = "Delete Reference";
					String message = "Are you sure you want to delete the binding for " + "'" + item.getName() + "'?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(new Shell(), title, message);

					if (go) {
						// Delete modification
						Element modificationStoreLocation = item.getModificationStoreLocation();
						String componentPath = "";
						if (modificationStoreLocation instanceof Property) { // if is the owned first level component
							componentPath = item.getDotPathWithoutFirstLevelComponent();
							item.setModificationRightHand(null); // reset the modification because it was created from the same first level component
						}
						else if (modificationStoreLocation instanceof Generalization) { // if it is an inherited first level component
							componentPath = item.getDotPath();
						}
						
						String deletedModification = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);
						
						// Delete the modification from the component modifications list.
//						item.deleteFromModificationList(item.getFirstLevelComponent().getName()+ "." + deletedModification);
						item.setFinalModificationRightHand(null); // verify against the line above
						item.setFinalModificationSource(null);
						//viewer.update(item, null);
						updateItem(item);
					}
				}
			}
		};
		actionDeleteModification.setText("Delete modification");
		actionDeleteModification.setToolTipText("Delete modification");
		actionDeleteModification.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		


		actionAddToInputs = new Action("AddToInputs") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					String rightHandModificationValue = ModificationManager.addToClassInputs(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDotPath());
					showMessage("Confirmation", "'" + item.getName() + "' was included into the '_inputs' component of '" + selectedClass.getName() + "'");
//					System.err.println("item.getFinalModificationRightHand(): " + item.getFinalModificationRightHand());
//					System.err.println("item.getFinalModificationLeftHand(): " + item.getFinalModificationLeftHand());
					item.setFinalModificationRightHand(rightHandModificationValue);
					item.setFinalModificationSource(item.getFirstLevelComponent());
					updateItem(item);
				}
			}
		};
//		actionAddToInputs.setText("Add to class '_inputs' component");
//		actionAddToInputs.setToolTipText("Add to class '_inputs' component");
//		actionAddToInputs.setText("Add to '_inputs' ");
		actionAddToInputs.setText("Bind to '_inputs' ");
		actionAddToInputs.setToolTipText("Bind to class '_inputs' component");
		actionAddToInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));

		
		
		
		actionDeleteFromInputs = new Action("actionDeleteFromInputs") {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					
					String title = "Delete '" + item.getName() + "' from class '_inputs'";
					String message = "Are you sure you want to delete " + "'" + item.getName() + "' from class '_inputs'?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(new Shell(), title, message);

					if (go) {
						ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDotPath(), true);
						showMessage("Confirmation", "'" + item.getName() + "' was deleted from the '_inputs' component of '" + selectedClass.getName() + "'");
						item.setFinalModificationRightHand(null);
						item.setFinalModificationSource(null);
						updateItem(item);
					}
				}
			}
		};
//		actionDeleteFromInputs.setText("Delete from class '_inputs'");
		actionDeleteFromInputs.setText("Delete from '_inputs'");
		actionDeleteFromInputs.setToolTipText("Delete from class '_inputs'");
		actionDeleteFromInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
				
		actionAddToOutputs = new Action("AddToOuputs") {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					ModificationManager.addToClassOutputs(selectedClass, item.getProperty(), item.getDotPath());
					showMessage("Confirmation", "'" + item.getName() + "' was included into the '_outputs' component of '" + selectedClass.getName() + "'");
					updateItem(item);
				}
			}
		};
//		actionAddToOutputs.setText("Add to class '_outputs' component");
		actionAddToOutputs.setText("Add to '_outputs'");
		actionAddToOutputs.setToolTipText("Add to class '_outputs' component");
		actionAddToOutputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		
		actionDeleteFromOutputs = new Action("actionDeleteFromOutputs") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					String title = "Delete '" + item.getName() + "' from class '_outputs'";
					String message = "Are you sure you want to delete " + "'" + item.getName() + "' from class '_outputs'?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(new Shell(), title, message);

					if (go) {
						ModificationManager.isUsedInClassOutputs_removeOption(selectedClass, item.getProperty(), item.getDotPath(), true);
						showMessage("Confirmation", "'" + item.getName() + " was deleted from the '_outputs' component of '" + selectedClass.getName() + "'");
						//showSelection(par, sel); // TODO: refresh properly ...
						updateItem(item);
					}
				}
			}
		};
//		actionDeleteFromOutputs.setText("Delete from class '_outputs'");
		actionDeleteFromOutputs.setText("Delete from '_outputs'");
		actionDeleteFromOutputs.setToolTipText("Delete to class '_outputs'");
		actionDeleteFromOutputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		
		actionInstantiateRequirements = new Action("actionReqTestEvaluationElements") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					
					InstantiateRequirementsHandler ri = new InstantiateRequirementsHandler();
					
					// if it is an instantiated test scenario -> collect linked requirements. 
					if ( ((TreeObject)obj).getProperty() instanceof Property && typeIsATestScenarioSimulation(((TreeObject)obj).getProperty())) {
						ri.collectLinkedItems(selectedClass, ((TreeObject)obj).getProperty());
					}
					
					try {
						ri.execute(null);
						viewer.refresh();
						viewer.expandToLevel(2);
						
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		actionInstantiateRequirements.setText("Instantiate Requirements");
		actionInstantiateRequirements.setToolTipText("Instantiate Requirements");
		actionInstantiateRequirements.setImageDescriptor(ImageDescriptor.createFromFile(InstantiateRequirementsHandler.class, "/icons/list-accept.png"));
		
		
		// instantiate test scenarios
		actionInstantiateTestScenarios = new Action("actionInstantiateTestScenarios") {
			public void run() 
			{
				HashSet<Element> selectedRequirementsObjects = new HashSet<Element>();
				HashSet<Element> selectedRequirements = null;
				Class containigClass = null;

				ISelection selection = viewer.getSelection();
				Boolean go = false;
				
				if (selection instanceof IStructuredSelection) {

					Object obj = ((IStructuredSelection) selection).getFirstElement();
					if (obj instanceof TreeParent && ((TreeParent)obj).isRoot() ) {
						// class was selected -> instantiate test scenarios 
						containigClass = ((TreeParent)obj).getSelectedClass();
						go = true;
					}
					
					// requirements were selected -> instantiate test cases based on selected requirements
					else {
						containigClass = selectedClass;
						
						for (Object object : ((IStructuredSelection)selection).toList()) {
							if (object instanceof TreeParent) {

								Type type = ((TreeParent)object).getComponentType();
								if (type != null && type.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
									selectedRequirementsObjects.add(((TreeParent)object).getProperty());
									go = true;
								}
							}
						}
					}
				}
				
				if (selectedRequirementsObjects.size() > 0 ) {
					selectedRequirements = new HashSet<Element>();
					selectedRequirements.addAll(selectedRequirementsObjects);
				}
				
				if (go) {
					InstantiateTestScenarioHandler tsi = new InstantiateTestScenarioHandler();
					tsi.setData(containigClass, selectedRequirements);
					
					try {
						tsi.execute(null);
						viewer.refresh();
						viewer.expandToLevel(2);
						
						// if selected test scenarios were instantiated
						if (tsi.instantiatedElements != null && tsi.instantiatedElements.size() > 0) {
							
							Boolean updateBindings = MessageDialog.openQuestion(new Shell(), 
									"Update bindings", "Do you want to update bindings in '" + root.getName() + "'?");
							
							if (updateBindings) {
								List<TreeParent> newSelection = new ArrayList<TreeParent>();
								newSelection.add(root);
								viewer.setSelection(new StructuredSelection(newSelection));
								actionUpdateBindings.run();						
							}
						}
						
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		actionInstantiateTestScenarios.setText("Instantiate Test Scenarios");
		actionInstantiateTestScenarios.setToolTipText("Instantiate Test Scenarios");
		actionInstantiateTestScenarios.setImageDescriptor(ImageDescriptor.createFromFile(InstantiateRequirementsHandler.class, "/icons/listOfActions.png"));

		
		actionAddReqTestEvaluationElements = new Action("actionReqTestEvaluationElements") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					//TestPassFailCodeGenerator t = new TestPassFailCodeGenerator(selectedClass);
					VerificationVerdictElementsGenerator t = new VerificationVerdictElementsGenerator(selectedClass);
					updateItem(obj);
					showSelection(par, sel);
				}
			}
		};
		actionAddReqTestEvaluationElements.setText("Add requirements test verdict");
		actionAddReqTestEvaluationElements.setToolTipText("Add requirements test verdict");
		actionAddReqTestEvaluationElements.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		actionDeleteReqTestEvaluationElements = new Action("actionReqTestEvaluationElements") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
//					TreeParent item = ( (TreeParent)obj);
					String title = "Delete requirements test verdict";
					String message = "Are you sure you want to delete the requirements test verdict elements?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(new Shell(), title, message);

					if (go) {
						VerificationVerdictElementsGenerator.removeRegTestEvalElemenents_deleteOption(selectedClass, true);
						updateItem(obj);
						showSelection(par, sel);
					}
				}
			}
		};
		actionDeleteReqTestEvaluationElements.setText("Delete requirements test verdict");
		actionDeleteReqTestEvaluationElements.setToolTipText("Delete requirements test verdict");
		actionDeleteReqTestEvaluationElements.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		
		actionLinkWithEditor = new Action("Action6", 2) { //obviously a check box style
			public void run() {
				if (actionShowOutputs.isChecked()) {
					org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot = null;
					showSelection(par, sel);
				}
			}
		};
		Boolean linkClassComponentsTreeWithModelExplorer = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "linkClassComponentsTreeWithModelExplorer", true, null);
		if (linkClassComponentsTreeWithModelExplorer) {
			actionLinkWithEditor.setChecked(true); 
		}

		actionLinkWithEditor.setText("Link with Papyrus Model Explorer");
		actionLinkWithEditor.setToolTipText("Link with Papyrus Model Explorer");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
	
		actionCollapseAll = new Action("actionCollapsAll") { //obviously a check box style
			public void run() {
				viewer.collapseToLevel(root, 2); // TODO: does not, it always collapse to level 1
//				showSelection(par, sel);
//				if (actionCollapsAll.isChecked()) {
//					
//				}
			}
		};
		actionCollapseAll.setText("Collapse all ");
		actionCollapseAll.setToolTipText("Collapse all");
		actionCollapseAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
		actionCollapseAll.setEnabled(false);
		
		actionShowAboutInfo = new Action("actionShowAboutInfo") { //obviously a check box style
			public void run() {
				showMessage("Notes for 'Class Components Tree' view", "This view shows the components tree of the class that is selected in Papyrus model explorer.");
//							"\n\nNote that arrays are not expanded.");
			}
		};
		actionShowAboutInfo.setText("About");
		actionShowAboutInfo.setToolTipText("About");
		actionShowAboutInfo.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_LCL_LINKTO_HELP));
		
	}

//	/**
//	 * Hook double click action.
//	 */
//	private void hookDoubleClickAction() {
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//			public void doubleClick(DoubleClickEvent event) {
////				showPathAction.run();
//			}
//		});
//	}

	
	private HashSet<Element> areRequirementsSelected(ISelection selection){
		HashSet<Element> selectedRequirementsObjects = new HashSet<Element>();
		
		if (selection instanceof IStructuredSelection) {

				for (Object object : ((IStructuredSelection)selection).toList()) {
					if (object instanceof TreeParent) {
						Type type = ((TreeParent)object).getComponentType();
						if (type != null && type.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
							selectedRequirementsObjects.add(((TreeParent)object).getProperty());
						}
					}
				}
			}
		if (selectedRequirementsObjects.size() > 0) {
			return selectedRequirementsObjects;
		}
		return null;
	}
	
	
	// ############################### FILTERS 
	
	class ShowAllFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			return true;
		}
	}
	ShowAllFilter showAllFilter = new ShowAllFilter();
	
	
	class InputFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

//				if (item.isRoot()) { // always show the root
//					return true;
//				}
//				if (item.getHasInputs() == null && !item.isLeaf()){ // not a leaf and indicator is NOT set
//					return false;
//				}
//				if (item.getHasInputs() != null && !item.getHasInputs() ) { // not a leaf but indicator is set
//					return false;
//				}
//				if ( item.isLeaf() && !item.isInput()) { // is leaf 
//					return false;
//				}
				return hasInputs(item);
			}
			return true;
		}
	}
	InputFilter inputFilter = new InputFilter();
	
	
	private boolean hasInputs(TreeObject item){
		if (item.isInput()) {
			return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list= findNextInput((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextInput(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();

		TreeObject[] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject child = children[i];
			if (child.isInput()) {
				list.add(child);
				return list;
			}
			else if (child instanceof TreeParent) {
				list.addAll(findNextInput( (TreeParent)child ));	
			}
		}
		 return list;
	}
	
	// Outputs ******************************************************************

	class OutputFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

//				if (item.isRoot()) { // always show the root
//					return true;
//				}
//				if (item.getHasOutputs() == null && !item.isLeaf()){ // not a leaf and indicator is NOT set
//					return false;
//				}
//				if (item.getHasOutputs() != null && !item.getHasOutputs() ) { // not a leaf but indicator is set
//					return false;
//				}
//				if ( item.isLeaf() && !item.isOutput()) { // is leaf 
//					return false;
//				}
				return hasOutputs(item);
			}
			return true;
		}
	}
	OutputFilter outputFilter = new OutputFilter();
	
	private boolean hasOutputs(TreeObject item){
		if (item.isOutput()) {
			return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list= findNextOutput((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextOutput(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();

		TreeObject[] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject child = children[i];
			if (child.isOutput()) {
				list.add(child);
				return list;
			}
			else if (child instanceof TreeParent) {
				list.addAll(findNextOutput( (TreeParent)child ));	
			}
		}
		 return list;
	}
	
	
	// Clients *****************************************************************
	class ValueClientFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

//				if (item.isRoot()) { // always show the root
//					return true;
//				}
////				System.err.println("item.hasValueClients(): " + item + " -> " + item.hasValueClients());
//				if (item.hasValueClients()) {
//					return true;
//				}
//				if (item.isValueClient()) { 
//					return true;
//				}
				
				return hasClients(item);
			}
			return false;
		}
	}
	ValueClientFilter valueClientFilter = new ValueClientFilter();
	
	private boolean hasClients(TreeObject item){
		if (item.isValueClient()) {
			return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list= findNextClient((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextClient(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();

		TreeObject[] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject child = children[i];
			if (child.isValueClient()) {
				list.add(child);
				return list;
			}
			else if (child instanceof TreeParent) {
				list.addAll(findNextClient( (TreeParent)child ));	
			}
		}
		 return list;
	}
	
	
	//Required Clients *****************************************************
	
	class RequiredValueClientFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 
				return hasRequiredClients(item);
			}
			return false;
		}
	}
	RequiredValueClientFilter requiredValueClientFilter = new RequiredValueClientFilter();
	
	private boolean hasRequiredClients(TreeObject item){
		if (item.isValueClient_required()) {
			return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list= findNextRequiredClient((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextRequiredClient(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();

		TreeObject[] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject child = children[i];
			if (child.isValueClient_required()) {
				list.add(child);
				return list;
			}
			else if (child instanceof TreeParent) {
				list.addAll(findNextRequiredClient( (TreeParent)child ));	
			}
		}
		 return list;
	}
	
	// Providers *****************************************************
	
	class ValueProviderFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

				return hasProviders(item);

//				if (item.isRoot()) { // always show the root
//					return true;
//				}
//				if (item.hasValueProviders()) {
//					return true;
//				}
//				if (item.isValueProvider()) { 
//					return true;
//				}
			}
			return false;
//			return true;
		}
	}
	ValueProviderFilter valueProviderFilter = new ValueProviderFilter();
	
	private boolean hasProviders(TreeObject item){
		if (item.isValueProvider()) {
			return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list= findNextProvider((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextProvider(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();

		TreeObject[] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject child = children[i];
			if (child.isValueProvider()) {
				list.add(child);
				return list;
			}
			else if (child instanceof TreeParent) {
				list.addAll(findNextProvider( (TreeParent)child ));	
			}
		}
		 return list;
	}
	
	
	// Requirements ***********************************************************************
	
	class RequirementInstanceFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

//				if (item.isRoot()) { // always show the root
//					return true;
//				}
//				if (item.hasRequirements()) { // Intermediate node with an indicator
//					return true;
//				}
//				if (item.isRequirementInstance()) { 
//					return true;
//				}
				
				return hasRequirements(item);
			}
			return false;
		}
	}
	RequirementInstanceFilter requirementInstanceFilter = new RequirementInstanceFilter();

	private boolean hasRequirements(TreeObject item){
		if (item.isRequirementInstance()) {
			return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list= findNextRequirement((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextRequirement(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();

		TreeObject[] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject child = children[i];
			if (child.isRequirementInstance()) {
				list.add(child);
				return list;
			}
			else if (child instanceof TreeParent) {
				list.addAll(findNextRequirement( (TreeParent)child ));	
			}
		}
		 return list;
	}
	
	// State machines *****************************************************************
	class HideStateMachnineElementFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 
				Element umlElement = item.getUmlElement();
				if (umlElement instanceof StateMachine) {
					return false;
				}
				else if (umlElement instanceof Region) {
					return false;
				}
				else if (umlElement instanceof State) {
					return false;
				}
			}
			return true;
		}
	}
	HideStateMachnineElementFilter hideStateMachnineElementFilter = new HideStateMachnineElementFilter();

	
	// ############################### FILTERS
	
	
	private void updateItem(Object item) {
		if (item != null) {
			viewer.update(item, null);
			if (item instanceof TreeObject) {
				updateAllParents((TreeObject)item );
			}
			// does not work. see https://bugs.eclipse.org/bugs/show_bug.cgi?id=113675 
//			TreePath[] expanded = viewer.getExpandedTreePaths();
//			viewer.refresh();
//			viewer.setExpandedTreePaths(expanded);
		}
	}
	
	private void updateAllParents(TreeObject item){
		if (item.getParent() != null && !item.isRoot()) {
			viewer.update(item.getParent(), null);
			updateAllParents(item.getParent());
		}
	}
	

	private void showMessage(String title, String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(), title, message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

//	/** The pagebook. */
//	private PageBook pagebook;
//	
//	/** The tableviewer. */
//	private TableViewer tableviewer;
//	
//	/** The textviewer. */
//	private TextViewer textviewer;

	// the listener we register with the selection service
	/** The listener. */
	
	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			// we ignore our own selections
			if (sourcepart != ComponentsTree.this) {
				EObject selectedElement = null;
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
					selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				}
				if (selectedElement instanceof Element) {
					if (actionLinkWithEditor.isChecked()) {
						showSelection(sourcepart, selection);
						viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
					}
				}
			}
		}
	};

	
	
//	private TreeObject lastSelectedItem = null;
//	private String lastSelectedDotPath = null;
	
	private void hookSelectionChangedAction() {
//		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
//			
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
////				System.err.println("Previous item was " + lastSelectedItem );
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection) selection).getFirstElement();
//				if (obj instanceof TreeParent) {
//					lastSelectedItem = (TreeObject)obj;
////					lastSelectedDotPath = ((TreeObject)obj).getDothPath();
//				}
//			}
//		});
	}
	
	
	/**
	 * Shows the given selection in this view.
	 * 
	 * @param sourcepart
	 *            the sourcepart
	 * @param selection
	 *            the selection
	 */
	public void showSelection(IWorkbenchPart sourcepart, ISelection selection) {
		sel = selection;
		par = sourcepart;
		
		Object[] expandedElements = viewer.getExpandedElements();
		TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
		
//		// remember the last selected item
//		IStructuredSelection viewerSelection = (IStructuredSelection) viewer.getSelection();
//		TreeObject obj = (TreeObject) viewerSelection.getFirstElement();
		
		// Only listen to the Papyrus Model Explorer selection
		if (sourcepart != null && sourcepart.getSite() != null 
				&& sourcepart.getSite().getId()!= null 
				&& sourcepart.getSite().getId().equals("org.eclipse.papyrus.modelexplorer.modelexplorer")) {
			
			// set new input from the selection.
			viewer.setInput(getViewSite());
			
		}

		viewer.setExpandedElements(expandedElements);
		viewer.setExpandedTreePaths(expandedTreePaths);
		
		// select the item again in view
//		TreeUtls.selectInView(obj, root, viewer);			
////		viewer.setSelection(new StructuredSelection(viewerSelection)); // does not work
//		viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
	}
	
//	private void showItems(Object[] items) {
//		tableviewer.setInput(items);
//		pagebook.showPage(tableviewer.getControl());
//	}
//
//	private void showText(String text) {
//		textviewer.setDocument(new Document(text));
//		pagebook.showPage(textviewer.getControl());
//	}

	/*
	 * public void setFocus() { pagebook.setFocus(); }
	 */

	/* (non-Javadoc)
 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
 */
	public void dispose() {
		// important: We need do unregister our listener when the view is
		// disposed
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(listener);
		super.dispose();
	}
	
	/**
	 * The Class ViewContentProvider.
	 */
	class ViewContentProvider implements IStructuredContentProvider,ITreeContentProvider {
		
		/** The invisible root. */
		private TreeParent invisibleRoot;

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput){
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose(){
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object parent){
			if (parent.equals(getViewSite())) {
				invisibleRoot = null;
				initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}

		/**
		 * Initialize.
		 */
		private void initialize() {
			// add the selection listener
			getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(listener);
			
			if (sel != null) {
				// disable action by default until the selection is a class.
				
				if (sel instanceof IStructuredSelection) {
//					Object first = ((IStructuredSelection) sel).getFirstElement();
					EObject selectedElement = null;
		        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
						selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
					}
					
//					if (first instanceof Property) {
//						Type type = ((Property)first).getType();
//						if ( type != null) {
//							if (type instanceof Class) {
//								selectedClass = (Class) type;
//							}
//						}
//					}
					
//					if (selectedElement instanceof StateMachine){
//						BehavioredClassifier owner = ((StateMachine)selectedElement).getContext();
//						if (owner != null) {
//							if (owner instanceof Class) {
//								selectedClass = (Class)owner;
//							}								
//						}
//					}
//					else if (selectedElement instanceof State) {
//						Element region = ((State)selectedElement).getOwner();
//						if (region != null) {
//							if (region instanceof Region) {
//								StateMachine sm = ((Region)region).getStateMachine();
//								if (sm != null) {
//									BehavioredClassifier owner = sm.getContext();
//									if (owner != null) {
//										if (owner instanceof Class) {
//											selectedClass = (Class)owner;
//										}								
//									}
//								}
//							}
//						}
//						
//					}
					//else if (selectedElement instanceof Class ) {
					if (selectedElement instanceof Class ) {
						selectedClass = (Class) selectedElement;
						
						if (selectedClass != null && !(selectedClass instanceof Behavior) && isValid(selectedClass)) {
							actionSimulate.setEnabled(true);
							actionValidate.setEnabled(true);
							actionReload.setEnabled(true);
							actionCollapseAll.setEnabled(true);
							
//							createTree(selectedClass); // build the entire tree
						}
						else {
							actionSimulate.setEnabled(false);
							actionValidate.setEnabled(false);
							actionReload.setEnabled(false);
							actionCollapseAll.setEnabled(false);
						}
					}

					createTree(selectedClass); // build the entire tree
				}
			}
		}
	
		
		/**
		 * Creates the tree.
		 * 
		 * @param selectedClass
		 *            the selected class
		 */
		public void createTree(Class selectedClass){
			// reset
//			org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot = null;
			
			if (selectedClass != null && !(selectedClass instanceof Behavior) && isValid(selectedClass)) {
				
				ClassInstantiation ast = new ClassInstantiation(selectedClass, actionShowStateMachines.isChecked());

				ast.createTree();
				invisibleRoot = ast.getInvisibleRoot();
//				invisibleRoot = new TreeParent("");
				root = ast.getTreeRoot();
				
				// set the static variable to be used by other plugins. This is done in order to avoid cyclic plugin dependecies
				org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot = root;
				org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeViewer = viewer;
			}
		}
	}

	private boolean isValid(Class aClass){
		if (aClass.getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null) { return true; }
		if (aClass.getAppliedStereotype(Constants.stereotypeQName_Model) != null) { return true; }
		if (aClass.getAppliedStereotype(Constants.stereotypeQName_Block) != null) { return true; }
		
//		if (aClass.getAppliedStereotype(stereotypeQName_Connector) != null) { return true; }
//		if (aClass.getAppliedStereotype(stereotypeQName_Record) != null) { return true; }
//		if (aClass.getAppliedStereotype(stereotypeQName_Function) != null) { return true; }

		if (aClass.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null) { return true; }
		if (aClass.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) { return true; }
		
		return false;
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
	
	
    @SuppressWarnings("rawtypes")
	public Object getAdapter(java.lang.Class adapter) {
        if (adapter == IPropertySheetPage.class){
            return new TabbedPropertySheetPage(this);        	
        }
        return super.getAdapter(adapter);
    }

	@Override
	public String getContributorId() {
		return getSite().getId();
	}
}
