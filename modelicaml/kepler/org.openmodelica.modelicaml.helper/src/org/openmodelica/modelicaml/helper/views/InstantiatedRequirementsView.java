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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.helper.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.helper.dialogs.InstantiateRequirementsDialog;
//import org.eclipse.gmt.modisco.infra.browser.uicore.internal.model.ModelElementItem;


public class InstantiatedRequirementsView extends ViewPart implements ISelectionListener {
	
	public InstantiatedRequirementsView() {
		super();
	}
	private final String reqStereotypeQname = "ModelicaML::ModelicaRequirementConstructs::Requirement";
	private ExpandBar expandBar;
	private Composite shell;
	private Class selectedClass;
	
	private List<ExpandItem> reqGraphicalItemsList = new ArrayList<ExpandItem>();
	private List<Group> reqGraphicalGroupItemsList = new ArrayList<Group>();
	
	
	private HashMap<Element, ArrayList<String>> reqClassInstantiations = new HashMap<Element,ArrayList<String>>();	
	private HashMap<String, Element> reqClassNameToElement = new HashMap<String, Element>();
	private List<String> reqClassesNames = new ArrayList<String>();
	private EList<Property> reqInstancesList = new BasicEList<Property>(); 
	
	
	
	
	@Override
	public void createPartControl(Composite parent) {
		shell = parent;
		
		expandBar = new ExpandBar(shell, SWT.V_SCROLL);
		expandBar.setSpacing(2);
		GridData gd_expandBar = new GridData(SWT.LEFT, SWT.TOP, true, true);
//		gd_expandBar.heightHint = 626;
//		gd_expandBar.widthHint = 1400;
		expandBar.setLayoutData(gd_expandBar);
		getViewSite().getPage().addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		shell.setFocus();
	}


	/**
	 * @see ISelectionListener#selectionChanged(IWorkbenchPart, ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if (!shell.isDisposed() && selection != null && selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			
//			EObject selectedElement = null;

			// Get the selected element
	        EObject selectedElement = ModelicaMLServices.adaptSelectedElement(first);
	        if (selectedElement instanceof Element) {
	        	selectedElement = (Element)selectedElement;
			}
			
			if (selectedElement instanceof Class ) {
				selectedClass = (Class) selectedElement;
				//reqList = getInstatiatedRequirementsList(selectedClass);
				
				// clear all lists
				reqClassInstantiations.clear();
				reqClassNameToElement.clear();
				reqClassesNames.clear();
				reqInstancesList.clear(); 
				
				// instantiate class 
				// NOTE: here we are not interested in bindings data, but only in number of requirement instantiated in the selected class
				ClassInstantiation ci = new ClassInstantiation(selectedClass, false, false, null, false);
				ci.createTree();
				ci.collectBindingsDataFromUmlModel();
				
				TreeParent treeRoot = ci.getTreeRoot();
				
				// collect all requirements instances and create maps
				collectRequirements(treeRoot);
				
				// sort requirements classes by name
				reqClassesNames.addAll(reqClassNameToElement.keySet());
				Collections.sort(reqClassesNames);
				
//				for (String string : list) {
//					Element aClass = reqClassNameToElement.get(string);
//					System.err.println(string + ":  " + reqClassInstantiations.get(aClass).toString());
//				}
				
				drawContents();
			}
		}
	}
	
	private void drawContents(){

		clearItemsList();// clear the graphical view.

		for (String reqClassName : reqClassesNames) {
				
				Class req = (Class) reqClassNameToElement.get(reqClassName);
				int numberOfInstances = reqClassInstantiations.get(req).size();
				
				Stereotype stereotype = req.getAppliedStereotype(reqStereotypeQname);
				if (stereotype != null) {
//					String title = "" + property.getName() + " (" + req.getName() + ")";
//					String title = "" + property.getName();
					Object id = "" + req.getValue(stereotype, "id");
					String title = "" + "(x" + numberOfInstances + ") " + id + ": " + reqClassName;
					Object text = "" + req.getValue(stereotype, "text");
					String qName = "" + req.getQualifiedName();

					ExpandItem reqExpandItem = new ExpandItem(expandBar, SWT.NONE);
					reqGraphicalItemsList.add(reqExpandItem);
					reqExpandItem.setExpanded(false);
					
					// mark requirement that is already instantiated 
					reqExpandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/exists_already.png"));

					// Set Req. Title
//					reqExpandItem.setText(id + ": " + title);
					reqExpandItem.setText(title);
					
					Group grpId = new Group(expandBar, SWT.NONE);
					reqGraphicalGroupItemsList.add(grpId);
					grpId.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					grpId.setToolTipText(qName);
					
					// Set Req. ID
					//grpId.setText("id: " + id.toString() + "   " + qName);
					grpId.setText(id.toString() + " : " + qName);
					reqExpandItem.setControl(grpId);

					// Combo for requirement instance names:
					CCombo reqInstancesNames = new CCombo(grpId, SWT.BORDER);
					List<String> list = reqClassInstantiations.get(req);
					Collections.sort(list);
					reqInstancesNames.setItems(list.toArray(new String[list.size()]));
					reqInstancesNames.setText(list.get(0).toString());
					reqInstancesNames.setBounds(5, 20, 600, 18);
					reqInstancesNames.setEditable(false);
					
					//StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP | SWT.V_SCROLL);
					StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP);
					reqText.setIndent(1);
					reqText.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
					reqText.setEditable(false);
					
					// Set Req. Text
					reqText.setText(text.toString());
//					reqText.setBounds(5, 20, expandBar.getShell().getBounds().width - 70, SWT.VERTICAL - 229);
					reqText.setBounds(5, 50, 600, 80);
					reqText.setToolTipText(qName);
					
					grpId.setTabList(new Control[]{reqText});
					reqExpandItem.setHeight(reqExpandItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);	
				}
			
		}
	}
	
	private void collectRequirements(TreeParent treeRoot){
		TreeObject[] children = treeRoot.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i].isRequirementInstance()) {
				
				// add to requirements instances list
				reqInstancesList.add(children[i].getProperty());
				
				// add to map
				ArrayList<String> list = reqClassInstantiations.get(children[i].getComponentType());
				if (list == null) {
					list = new ArrayList<String>();
					list.add(children[i].getDotPath());
				}
				else {
					list.add(children[i].getDotPath());
				}
				reqClassNameToElement.put(children[i].getComponentType().getName(), children[i].getComponentType());
				reqClassInstantiations.put(children[i].getComponentType(), list);
				
			}
//			else if (children[i].hasRequirements()) {
			else if (children[i] instanceof TreeParent) {
				collectRequirements((TreeParent)children[i]); // recursive call				
			} 
		}
	}
	
	private void clearItemsList(){
		for (ExpandItem item : reqGraphicalItemsList) {
			item.dispose();
		}
		for (Group group : reqGraphicalGroupItemsList) {
			group.dispose();
		}
	}

	
//	private void drawContents(){
//
//		clearItemsList();// clear the graphical view.
//
////		Label lblSelectRequirementsTo = new Label(messageComposite, SWT.NONE);
////		lblSelectRequirementsTo.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
////		lblSelectRequirementsTo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
////		lblSelectRequirementsTo.setBounds(0, 0, 238, 18);
////
////		if (reqList != null && reqList.size() > 0) {
////			// NEW Label for the owning class name
////			lblSelectRequirementsTo.setText("The following requirements are instantiated in:");
////			CLabel label_1 = new CLabel(messageComposite, SWT.NONE);
////			label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
////			label_1.setTopMargin(0);
////			label_1.setBottomMargin(0);
////			
////			// Set the name of containing class 
////			label_1.setText(selectedClass.getName());	
////			label_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
////			label_1.setBounds(244, 0, 450, 18);
////		}
////		else {
////			lblSelectRequirementsTo.setText("No requirements are instantiated in " + selectedClass.getName() +  ".");
////		}
//		
//		
//		for (Property property : reqList) {
//				
//				Class req = (Class) property.getType();
//				
//				Stereotype stereotype = req.getAppliedStereotype(reqStereotypeQname);
//				if (stereotype != null) {
////					String title = "" + property.getName() + " (" + req.getName() + ")";
//					String title = "" + property.getName();
//					Object id = "" + req.getValue(stereotype, "id");
//					Object text = "" + req.getValue(stereotype, "text");;
//					String qName = "" + req.getQualifiedName();
//
//					ExpandItem reqExpandItem = new ExpandItem(expandBar, SWT.NONE);
//					reqGraphicalItemsList.add(reqExpandItem);
//					reqExpandItem.setExpanded(false);
//					
//					// mark requirement that is already instantiated 
//					reqExpandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/exists_already.png"));
//
//					// Set Req. Title
////					reqExpandItem.setText(id + ": " + title);
//					reqExpandItem.setText(title);
//					
//					Group grpId = new Group(expandBar, SWT.NONE);
//					reqGraphicalGroupItemsList.add(grpId);
//					grpId.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//					grpId.setToolTipText(qName);
//					
//					// Set Req. ID
//					//grpId.setText("id: " + id.toString() + "   " + qName);
//					grpId.setText(id.toString() + " : " + qName);
//					reqExpandItem.setControl(grpId);
//					
//					//StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP | SWT.V_SCROLL);
//					StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP);
//					reqText.setIndent(1);
//					reqText.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
//					reqText.setEditable(false);
//					
//					// Set Req. Text
//					reqText.setText(text.toString());
//					//reqText.setBounds(29, 36, 656, 98);
////					reqText.setBounds(5, 20, expandBar.getShell().getBounds().width - 70, SWT.VERTICAL - 229);
//					reqText.setBounds(5, 20, 600, 80);
//					reqText.setToolTipText(qName);
//					
//					grpId.setTabList(new Control[]{reqText});
//					reqExpandItem.setHeight(reqExpandItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);	
//				}
//			
//		}
//	}
	
//	private List<Property> getInstatiatedRequirementsList(Class owningClass){
//		if (owningClass != null) {
//			List<Property> requirements = new ArrayList<Property>();
//			for (Property property : owningClass.getAllAttributes()) { // TODO: here use the ModelicaMLAST instead to find requirements
//				Type pType = property.getType();
//				
//				if ( pType != null && (pType.getAppliedStereotype(reqStereotypeQname) != null)) {
//					requirements.add(property);
//				}
//			}
//			
//			return requirements;
//		}
//		return null;
//	}
	



}

