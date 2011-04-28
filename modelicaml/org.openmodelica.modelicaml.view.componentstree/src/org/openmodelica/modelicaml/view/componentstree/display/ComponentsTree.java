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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.modelicaml.view.componentstree.display;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
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
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.impl.TestOracleElementsCreator;
import org.openmodelica.modelicaml.view.componentstree.Activator;
import org.openmodelica.modelicaml.view.componentstree.dialogs.DialogComponentInformation;
import org.openmodelica.modelicaml.view.componentstree.dialogs.DialogComponentModification;
import org.openmodelica.modelicaml.view.componentstree.utls.ResourceManager;

// TODO: Auto-generated Javadoc
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
	private DialogComponentInformation iDialog = null;
	
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
	private Action actionCollapsAll;
	
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
	
	public static final String stereotypeQName_ModelicaClass = "ModelicaML::ModelicaClassConstructs::ModelicaClass";
	public static final String stereotypeQName_Model = "ModelicaML::ModelicaClassConstructs::Model";
	public static final String stereotypeQName_Block = "ModelicaML::ModelicaClassConstructs::Block";
	public static final String stereotypeQName_Connector = "ModelicaML::ModelicaClassConstructs::Connector";
	public static final String stereotypeQName_Record = "ModelicaML::ModelicaClassConstructs::Record";
	public static final String stereotypeQName_Function = "ModelicaML::ModelicaClassConstructs::Function";
	
	public static final String stereotypeQName_CalculationModel = "ModelicaML::ModelicaClassConstructs::CalculationModel";
	public static final String stereotypeQName_Requirement = "ModelicaML::ModelicaRequirementConstructs::Requirement";	
	
	public static final String stereotypeQName_Simulation = "ModelicaML::ModelicaSimulationConstructs::Simulation";
	
	
//	/**
//	 * The constructor.
//	 */
//	public ComponentsTree() {
//		
//	}
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 * 
	 * @param parent
	 *            the parent
	 */
	public void createPartControl(Composite parent)
	{
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		
		//viewer.setSorter(new NameSorter()); // sorts the tree names alphabetically
				
		shell = parent.getShell();

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.view.componentstree");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		hookSelectionChangedAction();
		contributeToActionBars();

		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(listener);
		
		getSite().setSelectionProvider(viewer); // add a selection provider.
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
		manager.add(new Separator());
		manager.add(actionShowStateMachines);
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
		
		if (obj instanceof TreeParent) {
			TreeParent item = ( (TreeParent)obj);
			//if (item.isLeaf() && item.getProperty() != null  && !actionShowInputs.isChecked() && !actionShowOutputs.isChecked()) {
			if (item.isLeaf() && item.getProperty() != null ) {
				
				if (item.getFirstLevelComponent() != item.getProperty() && !item.isOutput()) { // prevent primitive types for being first level components
					manager.add(actionEditModification);
					
					if (item.getFirstLevelComponent()!= null ) {
						Element modificationStoreLocation = item.getModificationStoreLocation();
						String componentPath = "";
						if (modificationStoreLocation instanceof Property) {
							componentPath = item.getDotPathWithoutFirstLevelComponent();
						}
						else if (modificationStoreLocation instanceof Generalization) {
							componentPath = item.getDothPath();
						}
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
				
				Element modificationStoreLocation = item.getModificationStoreLocation();
				String componentPath = "";
				if (modificationStoreLocation instanceof Property) {
					componentPath = item.getDotPathWithoutFirstLevelComponent();
				}
				else if (modificationStoreLocation instanceof Generalization) {
					componentPath = item.getDothPath();
				}
				if (ModificationManager.isInModModListOfComponent(modificationStoreLocation, componentPath)) {
					manager.add(actionDeleteModification);
					if (item.getFirstLevelComponent()!= null) {
//						actionDeleteModification.setText("Delete modification from '" + item.getFirstLevelComponent().getName() + "'");
						actionDeleteModification.setText("Delete binding");
					}
				}

				if (isSimulation(selectedClass) && item.isInput() && item.getFinalModificationRightHand() == null ) { // if it is an input and has no modification.
					if (ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDothPath(), false)) {
						manager.add(actionDeleteFromInputs);
					}
					else {
						manager.add(actionAddToInputs);	
					}
				}
				else if (isSimulation(selectedClass) && ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDothPath(), false)) {
					manager.add(actionDeleteFromInputs);
				}
				
				if (isSimulation(selectedClass) &&  ModificationManager.isUsedInClassOutputs_removeOption(selectedClass, item.getProperty(), item.getDothPath(), false) ) {
					manager.add(actionDeleteFromOutputs);
				}
				else {
					if (isSimulation(selectedClass) && !item.isInput()) {
						manager.add(actionAddToOutputs);
					}
				}
			}
			
			if (!item.isRoot()) { // the root nodes shall not be located
				
//				showPathAction.setText("Show information about '" + item.getName() + "'");
//				showPathAction.setText("Show details");
//				manager.add(showPathAction); // valid for any item
				
				if (item.getUmlElement() instanceof Property) {
					if ( !(item.getProperty().getOwner() instanceof PrimitiveType)) { // Modelica predefined types shall not be located
						
//						actionLocate.setText("Locate '" + item.getProperty().getName() + "' in Papyrus");
						manager.add(new Separator());
						manager.add(actionLocate);
						
						if ( item.getProperty().getType() != null ) {
							if (!(item.getProperty().getType() instanceof PrimitiveType)) { //Primitive types should not be located.
//								actionLocateType.setText("Locate '" + item.getProperty().getType().getName() + "', the type of '" + item.getProperty().getName() + "', in Papyrus Outline View");
								manager.add(new Separator());
								manager.add(actionLocateType);
							}
						}
					}
				}
				else if (item.getUmlElement() instanceof NamedElement) {
					manager.add(new Separator());
					manager.add(actionLocate);
				}
			}

			
			if (item.isRoot() && isSimulation(selectedClass)) {
				if (TestOracleElementsCreator.removeRegTestEvalElemenents_deleteOption(selectedClass, false)) {
					manager.add(actionDeleteReqTestEvaluationElements);
				}
				else {
					manager.add(actionAddReqTestEvaluationElements);					
				}
			}
		}
		
//		manager.add(new Separator());
		//drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		
		//manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	
	private boolean isSimulation(Class aClass) {
		if (aClass.getAppliedStereotype(stereotypeQName_Simulation) != null) {
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
		manager.add(actionCollapsAll);
		manager.add(actionLinkWithEditor);
		
		manager.add(new Separator());
		manager.add(actionShowAboutInfo);
		//manager.add(new Separator());
		//drillDownAdapter.addNavigationActions(manager);
	}

	/**
	 * Make actions.
	 */
	private void makeActions() {
		
		actionReload = new Action("actionReload") {
			public void run() {
				showSelection(par, sel);
			}
		};
		actionReload.setText("(Re)load");
		actionReload.setToolTipText("(Re)load");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/reload.png"));
		
		
		showPathAction = new Action() {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				//LABEL = ((TreeParent)obj).getFirstLevelComponent().getName();
				String LABEL = null;
				if (obj instanceof TreeParent) {
					if ( !((TreeParent) obj).isRoot()) { 
						TreeParent item = ( (TreeParent)obj);
						iDialog = new DialogComponentInformation(shell, "Component Information", LABEL, item.getDothPath(), item, null);
						iDialog.open();
					}
				}
			}
		};
		showPathAction.setText("Show information");
		showPathAction.setToolTipText("Show information");
		showPathAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		
		actionShowAll = new Action("actionShowAll", 8) {
			public void run() {
				if (actionShowAll.isChecked()) {
//					selectLastItem(); // TODO: does not work ...
//					showSelection(par, sel);
				}
			}
		};
		actionShowAll.setText("Show all components");
		actionShowAll.setChecked(true);
		actionShowAll.setToolTipText("Action 3 tooltip");
//		actionShowAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		actionShowInputs = new Action("actionShowInputs", 8) {
			public void run() {
				if (actionShowInputs.isChecked()) {
//					Object[] list = viewer.getExpandedElements();
					viewer.addFilter(inputFilter);
//					viewer.setSelection(new StructuredSelection(list), true);
//					selectLastItem(); // TODO: does not work
					viewer.expandToLevel(2);
//					
//					for (int i = 0; i < list.length; i++) {
////						System.err.println(list[i]);
//						viewer.expandToLevel(list[i], 0); // TODO: does not work. no idea why ...
//					}
////					viewer.setExpandedElements(list);
////					viewer.refresh(list);
					
//					showSelection(par, sel);
				}
				else {
//					Object[] list = viewer.getExpandedElements();
					viewer.removeFilter(inputFilter);
//					viewer.setSelection(new StructuredSelection(list), true);
					viewer.expandToLevel(2);
					
//					System.err.println(lastSelectedItem);
//					viewer.expandToLevel(lastSelectedItem, 1);
					
//					showSelection(par, sel);
//					selectLastItem(); // TODO: does not work ...
				}
			}
		};
		actionShowInputs.setText("Show only input variables");
		actionShowInputs.setToolTipText("Action 2 tooltip");
//		actionShowInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		actionShowOutputs = new Action("actionShowOutputs", 8) {
			public void run() {
				if (actionShowOutputs.isChecked()) {
					viewer.addFilter(outputFilter);
					viewer.expandToLevel(2);
					//showSelection(par, sel);
				}
				else {
					viewer.removeFilter(outputFilter);
					viewer.expandToLevel(2);
				}
			}
		};
		actionShowOutputs.setText("Show only output variables");
		actionShowOutputs.setToolTipText("Action 3 tooltip");
//		actionShowOutputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		actionShowStateMachines = new Action("actionShowStateMachines", 2) {
			public void run() {
				showSelection(par, sel);
				//if (actionShowStateMachines.isChecked() || !actionShowStateMachines.isChecked()) {
					//showMessage("actionShowStateMachines executed");
				//}
			}
		};
		actionShowStateMachines.setText("Show StateMachines");
		actionShowStateMachines.setChecked(true);
		actionShowStateMachines.setToolTipText("Show StateMachines");
//		actionShowStateMachines.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
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
								else if (leftHand.equals(item.getDothPath()) ) {
									modificationString = rightHand;
								}
							}
							else {
								System.err.println("ModelicaML modification parsing error (ComponentsTree.java 01) for the string '" + string + "'");
								//MessageDialog.openError(shell, "ModelicaML modifications parsing error (01)", "'"+string+"' ");
							}
						}				
					}
					
					String fullModificationString = item.getDotPathWithoutFirstLevelComponent() + " = " + modificationString;
					
					//modificationStringDialog = new DialogModificationStringInput(shell, "Edit Component Modification", item.getDothPath() + " = ", modificationString, item, null, root);
					modificationStringDialog = new DialogComponentModification(shell, "Edit Component Modification", item.getDothPath(), fullModificationString, item, null, root);
					
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
								componentPath = item.getDothPath();
							}
							ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);

							// Delete modification in item list.
							//item.deleteFromModificationListBasedOnLeftRightHandValue(item.getDothPath(), modificationString);

							item.setFinalModificationRightHand(null); // TODO: verify against the line above
							item.setFinalModificationSource(null);
							viewer.update(item, null);
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
									componentPath = item.getDothPath();
								}
								String deletedModification = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);
								
								// Delete modification in item list.
								//item.deleteFromModificationList(item.getFirstLevelComponent().getName()+ "." + deletedModification);
								item.setFinalModificationRightHand(null); // verify against the line above
								item.setFinalModificationSource(null);
								viewer.update(item, null);
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
									item.setFinalModificationSource(item.getFirstLevelComponent());
									viewer.update(item, null);
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
					viewer.update(item, null);
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
				
					// Delete modification
					Element modificationStoreLocation = item.getModificationStoreLocation();
					String componentPath = "";
					if (modificationStoreLocation instanceof Property) { // if is the owned first level component
						componentPath = item.getDotPathWithoutFirstLevelComponent();
						item.setModificationRightHand(null); // reset the modification because it was created from the same first level component
					}
					else if (modificationStoreLocation instanceof Generalization) { // if it is an inherited first level component
						componentPath = item.getDothPath();
					}
					
					String deletedModification = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);
					
					// Delete the modification from the component modifications list.
//					item.deleteFromModificationList(item.getFirstLevelComponent().getName()+ "." + deletedModification);
					item.setFinalModificationRightHand(null); // verify against the line above
					item.setFinalModificationSource(null);
					//viewer.update(item, null);
					updateItem(item);
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
					String rightHandModificationValue = ModificationManager.addToClassInputs(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDothPath());
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
		actionAddToInputs.setText("Add to '_inputs' ");
		actionAddToInputs.setToolTipText("Add to class '_inputs' component");

		actionAddToInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		actionDeleteFromInputs = new Action("actionDeleteFromInputs") {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDothPath(), true);
					showMessage("Confirmation", "'" + item.getName() + "' was deleted from the '_inputs' component of '" + selectedClass.getName() + "'");
					item.setFinalModificationRightHand(null);
					item.setFinalModificationSource(null);
					updateItem(item);
				}
			}
		};
//		actionDeleteFromInputs.setText("Delete from class '_inputs'");
		actionDeleteFromInputs.setText("Delete from '_inputs'");
		actionDeleteFromInputs.setToolTipText("Delete from class '_inputs'");
		actionDeleteFromInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
	
		
				
		actionAddToOutputs = new Action("AddToOuputs") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					ModificationManager.addToClassOutputs(selectedClass, item.getProperty(), item.getDothPath());
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
					ModificationManager.isUsedInClassOutputs_removeOption(selectedClass, item.getProperty(), item.getDothPath(), true);
					showMessage("Confirmation", "'" + item.getName() + " was deleted from the '_outputs' component of '" + selectedClass.getName() + "'");
					//showSelection(par, sel); // TODO: refresh properly ...
					updateItem(item);

				}
			}
		};
//		actionDeleteFromOutputs.setText("Delete from class '_outputs'");
		actionDeleteFromOutputs.setText("Delete from '_outputs'");
		actionDeleteFromOutputs.setToolTipText("Delete to class '_outputs'");
		actionDeleteFromOutputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		
		actionAddReqTestEvaluationElements = new Action("actionReqTestEvaluationElements") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					//TestPassFailCodeGenerator t = new TestPassFailCodeGenerator(selectedClass);
					TestOracleElementsCreator t = new TestOracleElementsCreator(selectedClass);
					updateItem(obj);
				}
			}
		};
		actionAddReqTestEvaluationElements.setText("Add requirements test evaluation elements");
		actionAddReqTestEvaluationElements.setToolTipText("Add requirements test evaluation elements");
		actionAddReqTestEvaluationElements.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		actionDeleteReqTestEvaluationElements = new Action("actionReqTestEvaluationElements") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					//TestPassFailCodeGenerator.removeRegTestEvalElemenents_deleteOption(selectedClass, true);
					TestOracleElementsCreator.removeRegTestEvalElemenents_deleteOption(selectedClass, true);
					updateItem(obj);
				}
			}
		};
		actionDeleteReqTestEvaluationElements.setText("Delete requirements test evaluation elements");
		actionDeleteReqTestEvaluationElements.setToolTipText("Delete requirements test evaluation elements");
		actionDeleteReqTestEvaluationElements.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		
		actionLinkWithEditor = new Action("Action6", 2) { //obviously a check box style
			public void run() {
				if (actionShowOutputs.isChecked()) {
					showSelection(par, sel);
				}
			}
		};
		actionLinkWithEditor.setChecked(true);
		actionLinkWithEditor.setText("Link with Papyrus Model Explorer");
		actionLinkWithEditor.setToolTipText("Link with Papyrus Model Explorer");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
	
		actionCollapsAll = new Action("actionCollapsAll") { //obviously a check box style
			public void run() {
				showSelection(par, sel);
//				if (actionCollapsAll.isChecked()) {
//					
//				}
			}
		};
		actionCollapsAll.setText("Collaps all and refresh");
		actionCollapsAll.setToolTipText("Collaps all and refresh");
		actionCollapsAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));

		
		actionShowAboutInfo = new Action("actionShowAboutInfo") { //obviously a check box style
			public void run() {
				showMessage("Notes for 'Class Components Tree' view", "This view shows the components tree of the class that is selected in model browser. " +
						"\n\nNote, arrays are not expanded");
			}
		};
		actionShowAboutInfo.setText("About");
		actionShowAboutInfo.setToolTipText("About");
		actionShowAboutInfo.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_LCL_LINKTO_HELP));
		
	}

	/**
	 * Hook double click action.
	 */
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
//				showPathAction.run();
			}
		});
	}

	
	
	
	// ############################### FILTERS 
	
	class InputFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

				if (item.isRoot()) { // always show the root
					return true;
				}
				if (item.getHasInputs() == null && !item.isLeaf()){ // not a leaf and indicator is NOT set
					return false;
				}
				if (item.getHasInputs() != null && !item.getHasInputs() ) { // not a leaf but indicator is set
					return false;
				}
				if ( item.isLeaf() && !item.isInput()) { // is leaf 
					return false;
				}
			}
			return true;
		}
	}
	InputFilter inputFilter = new InputFilter();
	

	class OutputFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

				if (item.isRoot()) { // always show the root
					return true;
				}
				if (item.getHasOutputs() == null && !item.isLeaf()){ // not a leaf and indicator is NOT set
					return false;
				}
				if (item.getHasOutputs() != null && !item.getHasOutputs() ) { // not a leaf but indicator is set
					return false;
				}
				if ( item.isLeaf() && !item.isOutput()) { // is leaf 
					return false;
				}
			}
			return true;
		}
	}
	OutputFilter outputFilter = new OutputFilter();
	// ############################### FILTERS
	
	
	
	
	private void updateItem(Object item) {
		if (item != null) {
			viewer.update(item, null);
		}
	}
	
	/**
	 * Show message.
	 * 
	 * @param title
	 *            the title
	 * @param message
	 *            the message
	 */
	private void showMessage(String title, String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				title, message);
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
						viewer.expandToLevel(2);
					}
				}
			}
		}
	};

	
	
	private TreeObject lastSelectedItem = null;
//	private String lastSelectedDotPath = null;
	
	private void hookSelectionChangedAction() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
//				System.err.println("Previous item was " + lastSelectedItem );
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					lastSelectedItem = (TreeObject)obj;
//					lastSelectedDotPath = ((TreeObject)obj).getDothPath();
				}
			}
		});
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
		viewer.setInput(getViewSite());
		viewer.setAutoExpandLevel(2);
	}
	
	private void selectLastItem(){ // Does not work because we ignore our own selection ...
		List<Object> items = new ArrayList<Object>();
		items.add(lastSelectedItem);
		viewer.setSelection(new StructuredSelection(items), true);
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

			if (sel != null) {
				if (sel instanceof IStructuredSelection) {
//					Object first = ((IStructuredSelection) sel).getFirstElement();
					EObject selectedElement = null;
		        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
						selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
					}
					
//					// TODO: Find the right meta class for ModelElementItem
//					if (first instanceof ModelElementItem) {
//						selectedElement = ((ModelElementItem)first).getEObject();
//					}
//					else if (first instanceof IUMLEditPart) {
//						selectedElement = ((IUMLEditPart)first).getUMLElement();
//					}
					
//					if (first instanceof IUMLElementEditPart<?>) {
//						first = ((IUMLElementEditPart<?>) first).getUmlElement();
//						
//					}
					
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
			if (selectedClass != null && !(selectedClass instanceof Behavior) && isValid(selectedClass)) {
				
				ClassInstantiation ast = new ClassInstantiation(selectedClass, actionShowStateMachines.isChecked());

				ast.createTree();
				invisibleRoot = ast.getInvisibleRoot();
				root = ast.getTreeRoot();
			}
		}
	}

	private boolean isValid(Class aClass){
		if (aClass.getAppliedStereotype(stereotypeQName_ModelicaClass) != null) { return true; }
		if (aClass.getAppliedStereotype(stereotypeQName_Model) != null) { return true; }
		if (aClass.getAppliedStereotype(stereotypeQName_Block) != null) { return true; }
		
//		if (aClass.getAppliedStereotype(stereotypeQName_Connector) != null) { return true; }
//		if (aClass.getAppliedStereotype(stereotypeQName_Record) != null) { return true; }
//		if (aClass.getAppliedStereotype(stereotypeQName_Function) != null) { return true; }

		if (aClass.getAppliedStereotype(stereotypeQName_CalculationModel) != null) { return true; }
		if (aClass.getAppliedStereotype(stereotypeQName_Requirement) != null) { return true; }
		
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
