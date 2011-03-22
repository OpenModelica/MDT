/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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

import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.diagram.common.editparts.IUMLEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.openmodelica.modelicaml.common.ast.ModelicaMLAST;
import org.openmodelica.modelicaml.common.ast.ModificationManager;
import org.openmodelica.modelicaml.common.ast.ModificationsCollector;
import org.openmodelica.modelicaml.common.ast.TreeObject;
import org.openmodelica.modelicaml.common.ast.TreeParent;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.impl.TestOracleElementsCreator;
import org.openmodelica.modelicaml.view.componentstree.dialogs.DialogComponentInformation;
import org.openmodelica.modelicaml.view.componentstree.dialogs.DialogComponentModification;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentsTree.
 */
public class ComponentsTree extends ViewPart {

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
	
	/** The action collaps all. */
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
	
	/** The action req test evaluation elements. */
	private Action actionReqTestEvaluationElements;
	
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
	
	/**
	 * The constructor.
	 */
	public ComponentsTree() {
		
	}
	
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
		contributeToActionBars();

		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(listener);
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
			if (item.isLeaf() && item.getProperty() != null  && !actionShowInputs.isChecked() && !actionShowOutputs.isChecked()) {
				
				if (item.getFirstLevelComponent() != item.getProperty() && !item.isOutput()) { // prevent primitive types for being first level components
					manager.add(actionEditModification);
					
					if (item.getFirstLevelComponent()!= null ) {
						if (ModificationManager.isInModModListOfComponent(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent())) {
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
				
				if (ModificationManager.isInModModListOfComponent(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent())) {
					manager.add(actionDeleteModification);
					if (item.getFirstLevelComponent()!= null) {
//						actionDeleteModification.setText("Delete modification from '" + item.getFirstLevelComponent().getName() + "'");
						actionDeleteModification.setText("Delete binding");
					}
				}

				if (item.isInput() && item.getFinalModificationLeftHand()== null ) {
					if (ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDothPath(), false)) {
						manager.add(actionDeleteFromInputs);
					}
					else {
						manager.add(actionAddToInputs);	
					}
				}
				
				if ( ModificationManager.isUsedInClassOutputs_removeOption(selectedClass, item.getProperty(), item.getDothPath(), false) ) {
					manager.add(actionDeleteFromOutputs);
				}
				else {
					if (!item.isInput()) {
						manager.add(actionAddToOutputs);
					}
				}
			}
			
			if (!item.isRoot()) { // the root nodes shall not be located
				
//				showPathAction.setText("Show information about '" + item.getName() + "'");
				showPathAction.setText("Show details");
				manager.add(showPathAction); // valid for any item
//				if (item.getProperty() != null ) {
//					if ( !(item.getProperty().getOwner() instanceof PrimitiveType)) { // Modelica predefined types shall not be located 
//						actionLocate.setText("Locate '" + item.getProperty().getName() + "' in Papyrus Outline View");
//						manager.add(actionLocate);
//						if ( item.getProperty().getType() != null ) {
//							if (!(item.getProperty().getType() instanceof PrimitiveType)) { //Primitive types should not be located.
//								actionLocateType.setText("Locate '" + item.getProperty().getType().getName() + "', the type of '" + item.getProperty().getName() + "', in Papyrus Outline View");
//								manager.add(actionLocateType);
//							}
//						}
//					}
//				}	
			}

			if (item.isRoot()) {
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

	/**
	 * Fill local tool bar.
	 * 
	 * @param manager
	 *            the manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionLinkWithEditor);
		manager.add(actionCollapsAll);
		manager.add(actionShowAboutInfo);
		//manager.add(new Separator());
		//drillDownAdapter.addNavigationActions(manager);
	}

	/**
	 * Make actions.
	 */
	private void makeActions() {
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
					showSelection(par, sel);
					//showMessage("Action 4 executed");
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
					showSelection(par, sel);
					//showMessage("Action 2 executed");
				}
			}
		};
		actionShowInputs.setText("Show only input variables");
		actionShowInputs.setToolTipText("Action 2 tooltip");
//		actionShowInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		actionShowOutputs = new Action("actionShowOutputs", 8) {
			public void run() {
				if (actionShowOutputs.isChecked()) {
					showSelection(par, sel);
					//showMessage("Action 3 executed");
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
		//actionShowStateMachines.setChecked(true);
		actionShowStateMachines.setToolTipText("Show StateMachines");
//		actionShowStateMachines.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		
		
//		actionLocate = new Action(){
//			public void run() 
//			{
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection) selection).getFirstElement();
//				if (obj instanceof TreeParent) {
//					TreeParent item = ( (TreeParent)obj);
//					Element selectedObject = item.getProperty();
//					MultiDiagramEditor editor = PapyrusPlugin.getPapyrusEditor();
//					TreeOutlinePage outline = (TreeOutlinePage) editor.getAdapter(IContentOutlinePage.class);
//					//TODO see ExtendedMarkersView openMarkerInEditor() ..
//
//					// Find element in Outline
//					if(selectedObject!=null){
//						//if ( !(selectedObject.getOwner() instanceof PrimitiveType)) { // filter out the predefined Modelica predefined type attributes that are defined using UML PrimitiveTypes Propoerties
//							CommonTreeEditPart ctep = outline.expandToElement(selectedObject);
//							if (ctep != null) {
//								// Set selection on the TreeEditPart that matches the selection
//								ctep.getViewer().select(ctep);
//							}
//						//}
//					}
//					else {
//						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//						MessageDialog.openInformation(shell, "Information:", "Open the project in papyrus editor");
//					}
//					
//				}
//			}
//		};	
//		actionLocate.setText("Locate in Papyrus Outline View");
//		actionLocate.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
//		
//		actionLocateType = new Action(){
//			public void run() 
//			{
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection) selection).getFirstElement();
//				if (obj instanceof TreeParent) {
//					TreeParent item = ( (TreeParent)obj);
//					Property p = item.getProperty();
//					if (p!=null) {
//						Type t = p.getType();
//						if (t != null) {
//							Element selectedObject = t;
//							MultiDiagramEditor editor = PapyrusPlugin.getPapyrusEditor();
//							TreeOutlinePage outline = (TreeOutlinePage) editor.getAdapter(IContentOutlinePage.class);
//
//							// Find element in Outline
//							if(selectedObject != null){
//								if ( !(t instanceof PrimitiveType)) { // filter out the predefined Modelica predefined type attributes that are defined using UML PrimitiveTypes Propoerties
//									CommonTreeEditPart ctep = outline.expandToElement(selectedObject);
//									if (ctep != null) {
//										// Set selection on the TreeEditPart that matches the selection
//										ctep.getViewer().select(ctep);
//									}
//								}
//							}
//							else {
//								Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//								MessageDialog.openError(shell, "Error:", "Cannot locate the element in Papyrus Outline.");
//							}
//
//						}
//					}					
//				}
//			}
//		};	
//		actionLocateType.setText("Locate component type in Papyrus Outline View");
//		actionLocateType.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
		
		
		actionEditModification = new Action("actionEditModification") {
			public void run() {
				ISelection selection = viewer.getSelection(); 
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					
					// get the modification for this item in its first level component
					//String modificationString = null; 
					String modificationString = "";
					HashSet<String> modList = ModificationsCollector.getComponentModifications(item.getFirstLevelComponent());
					if (modList.size() > 0 ) {
						for (String string : modList) {
							String[] splitted = string.trim().split("=");
							if (splitted.length > 1) {
								String leftHand = splitted[0].trim();
								String rightHand = splitted[1].trim();
								if (leftHand.equals(item.getDotPathWithoutFirstLevelComponent())) {
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
							ModificationManager.deleteComponentModificationBasedOnLeftHandValue(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent());
							// Delete modification in item list.
							item.deleteFromModificationListBasedOnLeftRightHandValue(item.getDothPath(), modificationString);
							viewer.update(item, null);
						}
						else {
							Boolean hasOutterBraces = string.trim().startsWith("(");
							if (hasOutterBraces && !string.trim().endsWith(")")) {
								//System.err.println("ModelicaML modifications parsing error ComponentsTree.class 02: Not well-formed modification string (missing closing brace): " + string);
								MessageDialog.openError(shell, "ModelicaML modifications parsing error (ComponentsTree.java 02)", "The modification string '"+string+"' is not well-formed (missing closing brace)");
							}
							String modStringWithoutBraces = StringUtls.removeOutterBraces(string);
							
							// Update the modification in the corresponding firstLevelComponent
							
							// Delete modification in items firstLevelComponent
							if (modStringWithoutBraces.startsWith(item.getDotPathWithoutFirstLevelComponent())) {
								String deletedModification = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent());
								// Delete modification in item list.
								item.deleteFromModificationList(item.getFirstLevelComponent().getName()+ "." + deletedModification);
								viewer.update(item, null);
							}

							
							// Add the new value to firstLevelComponent and to the component modifications list.
							//ModificationManager.addComponentModification(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent(), modificationStringDialog.getValue(), true);
							String[] parts = modStringWithoutBraces.split("=");
							if (parts.length > 0) {
								String leftHandPart = parts[0];
								//System.err.println("leftHandPart " + leftHandPart);
								if (parts.length > 1) {
									String rightHandPart = parts[1];
									//System.err.println("rightHandPart " + rightHandPart);
									ModificationManager.addComponentModification(item.getFirstLevelComponent(), leftHandPart, rightHandPart, true);
									//ModificationManager.addComponentModification(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent(), modificationStringDialog.getValue(), true); // OLD
									
									// Add to the modification in item list.
									//item.addToModificationList(item.getDothPath() + " = " + modificationStringDialog.getValue()); // OLD
									String storeString = "";
									if (hasOutterBraces) {
										storeString = "(" + StringUtls.replaceSpecChar(item.getFirstLevelComponent().getName()) + "." + modStringWithoutBraces + ")";
									}
									else {
										storeString = StringUtls.replaceSpecChar(item.getFirstLevelComponent().getName()) + "." + modStringWithoutBraces ;
									}
									item.addToModificationList(storeString); // Store with braces if there are any!
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
				
					// Delete modification in items firstLevelComponent
					String deletedModification = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent());
					
					// Delete the modification from the component modifications list.
					item.deleteFromModificationList(item.getFirstLevelComponent().getName()+ "." + deletedModification);
					viewer.update(item, null);
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
					//TODO: call method addToInpuits ...
					ModificationManager.addToClassInputs(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDothPath());
					showMessage("Confirmation", "'" + item.getName() + "' was included into the '_inputs' component of '" + selectedClass.getName() + "'");
					showSelection(par, sel); // TODO: refresh properly ...
				}
			}
		};
//		actionAddToInputs.setText("Add to class '_inputs' component");
//		actionAddToInputs.setToolTipText("Add to class '_inputs' component");
		actionAddToInputs.setText("Add to '_inputs' ");
		actionAddToInputs.setToolTipText("Add to class '_inputs' component");

		actionAddToInputs.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		actionDeleteFromInputs = new Action("actionDeleteFromInputs") {
			public void run() 
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof TreeParent) {
					TreeParent item = ( (TreeParent)obj);
					//TODO: destroy all objects and references that are related to class "_inputs"
					ModificationManager.isUsedInClassInputs_removeOption(selectedClass, item.getFirstLevelComponent(), item.getProperty(), item.getDotPathWithoutFirstLevelComponent(), item.getDothPath(), true);
					showMessage("Confirmation", "'" + item.getName() + "' was deleted from the '_inputs' component of '" + selectedClass.getName() + "'");
					showSelection(par, sel); // TODO: refresh properly ...
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
					showSelection(par, sel); // TODO: refresh properly ...
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
					showSelection(par, sel); // TODO: refresh properly ...
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
					showSelection(par, sel);
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
					showSelection(par, sel);
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
		actionLinkWithEditor.setToolTipText("Link with Papyrus Outline View");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
	
		actionCollapsAll = new Action("actionCollapsAll") { //obviously a check box style
			public void run() {
				showSelection(par, sel);
				if (actionCollapsAll.isChecked()) {
					
				}
			}
		};
		actionCollapsAll.setText("Collaps all and refresh");
		actionCollapsAll.setToolTipText("Collaps all and refresh");
		actionCollapsAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));

		
		actionShowAboutInfo = new Action("actionShowAboutInfo") { //obviously a check box style
			public void run() {
				showMessage("Notes for 'Class Components Tree' view", "This view shows the components tree of the class that is selected in model browser. " +
						"\n\nPlease note that class or instance redeclarations are not taken into account. " +
						"Furthermore, inherited duplicates of components are not merged.");
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
				showPathAction.run();
			}
		});
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

	/** The pagebook. */
	private PageBook pagebook;
	
	/** The tableviewer. */
	private TableViewer tableviewer;
	
	/** The textviewer. */
	private TextViewer textviewer;

	// the listener we register with the selection service
	/** The listener. */
	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart sourcepart,
				ISelection selection) {
			// we ignore our own selections
			if (sourcepart != ComponentsTree.this) {
				if (actionLinkWithEditor.isChecked()) {
					showSelection(sourcepart, selection);
				}
			}
		}
	};

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
	public void dispose() 
	{
		// important: We need do unregister our listener when the view is
		// disposed
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(listener);
		super.dispose();
	}
	
	/**
	 * The Class ViewContentProvider.
	 */
	class ViewContentProvider implements IStructuredContentProvider,ITreeContentProvider 
	{
		
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
				//return getChildren(visualizedTree);
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
					Object first = ((IStructuredSelection) sel).getFirstElement();
					EObject selectedElement = null;
					// TODO: Find the right meta class for ModelElementItem
					if (first instanceof ModelElementItem) {
						selectedElement = ((ModelElementItem)first).getEObject();
					}
					else if (first instanceof IUMLEditPart) {
						selectedElement = ((IUMLEditPart)first).getUMLElement();
					}
					
//					if (first instanceof IUMLElementEditPart<?>) {
//						first = ((IUMLElementEditPart<?>) first).getUmlElement();
//						
//					}
					//Class selectedClass = null;
					
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
			if (selectedClass != null && !(selectedClass instanceof Behavior) ) {
				ModelicaMLAST ast = new ModelicaMLAST(selectedClass, actionShowStateMachines.isChecked());

				if (actionShowInputs.isChecked()) {
					ast.createInputsTree();
					invisibleRoot = ast.getInvisibleRoot();
					root = ast.getTreeRoot();
				}
				else if (actionShowOutputs.isChecked()) {
					ast.createOutputsTree();
					invisibleRoot = ast.getInvisibleRoot();
					root = ast.getTreeRoot();
				}
				else {
					ast.createTree();
					invisibleRoot = ast.getInvisibleRoot();
					root = ast.getTreeRoot();
				}
			}
		}
	}
}
