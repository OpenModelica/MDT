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
package org.openmodelica.modelicaml.helper.generators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.valuebindings.helpers.ValueBindingsDataCollector;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeHelper;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;

public class InstantiatorRequirements {
	
	final static String reqPropertyPrefix = Constants.reqirementPropertyNamePrefix;
	
	public void instantiateRequirements(Class containingClass, HashSet<Class> reqClasses, HashMap<Class, Integer> selectedNumberOfInstantiations, HashSet<Element> preCollectedMediators){
	
		EList<Property> instantiatedRequirements = new BasicEList<Property>();
		
		for (Class reqClass : reqClasses) {
			Integer numberOfInstantiations = 0; 
			if (selectedNumberOfInstantiations != null) {
				Integer n = selectedNumberOfInstantiations.get(reqClass);
//				System.err.println(reqClass.getName() + " number of instantiations: " + n);
				if (n != null) {
					numberOfInstantiations = n;
				}
				else {
//					numberOfInstantiations = getFinalNumberOfRequiredInstantiations(containingClass, reqClass);
					// if it is not clear then always instantiated only one.
					numberOfInstantiations = 1; 
				}
			}
			else {
//				numberOfInstantiations = getFinalNumberOfRequiredInstantiations(containingClass, reqClass);
				// if it is not clear then always instantiated only one.
				numberOfInstantiations = 1;
			}
			
			if (numberOfInstantiations > 0 ) {
				for (int i = 0; i < numberOfInstantiations; i++) {
					Property pAdded = instantiateRequirement(containingClass, reqClass);
					if (pAdded != null) {
						instantiatedRequirements.add(pAdded);
					}
				}
			}
		}

		bindReqInputProperties(instantiatedRequirements, containingClass, preCollectedMediators);
	}
	
	public void bindReqInputProperties(EList<Property> instantiatedRequirementList, Class containingClass, HashSet<Element> preCollectedMediators) {
		
		// class instantiation after the requirements were added
		ClassInstantiation ast = new ClassInstantiation(containingClass, true, false);
		ast.setAllMediators(preCollectedMediators);
		ast.createTree();
		TreeParent treeRoot = ast.getTreeRoot();
		
		// find the instantiation class tree items based on the created UML properties
		HashSet<TreeObject> instantiationClassTreeItems = new HashSet<TreeObject>();
		for (Property property : instantiatedRequirementList) {
			HashSet<TreeObject> treeItems = findTreeItems(property, treeRoot, new HashSet<TreeObject>());
			instantiationClassTreeItems.addAll(treeItems);
		}
		
		// update bindings
		CreatorValueBinding vc = new CreatorValueBinding();

		for (TreeObject treeObject : instantiationClassTreeItems) {
			if (treeObject instanceof TreeParent) {
				vc.updateAllBindings( ((TreeParent)treeObject).getSelectedClass().getModel(), ast, (TreeParent)treeObject, treeRoot, false, true, false, false);
			}
		}
	}

	
	public static HashSet<TreeObject> findTreeItems(EObject selectedElement, TreeParent parent, HashSet<TreeObject> list) {
		if (parent != null) {
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
		}
		return list;
	}
	
	
	public EList<Property> getExisitngClassInstances(Class containingClass, Class reqClass){
		EList<Property> list = new BasicEList<Property>();
		 
		if (containingClass != null) {
			EList<Property> listOfProperties = containingClass.getAllAttributes();
			for (Property property : listOfProperties) {
				Type pType = property.getType();
				if (pType != null && pType.equals(reqClass)) {
					list.add(property);
				}
			}
		}
		return list;	
	}
	
	
	public Property instantiateRequirement(Class containingClass, Class reqClass){
		EList<Property> pList = containingClass.getAllAttributes();
		int numberOfReqInstancesWithSameType = 0;
//		String prefix = reqPropertyPrefix;
		for (Property property : pList) {
			String pName = StringUtls.replaceSpecChar(property.getName());
			if (pName.substring(0, pName.length() - 2).startsWith(reqPropertyPrefix + getRequirementId(reqClass) + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase()) ) {
				numberOfReqInstancesWithSameType ++; 
			}
		}
		Integer postfix = numberOfReqInstancesWithSameType + 1;
		String postfixString = "_" + postfix.toString();
		
		// create Property
		Property p = containingClass.createOwnedAttribute(reqPropertyPrefix + getRequirementId(reqClass) + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase() + postfixString, reqClass);
		// apply stereotype
		Stereotype s = p.getApplicableStereotype(Constants.stereotypeQName_RequirementInstance);
		if (s != null) {
			p.applyStereotype(s);
			return p; 
		}
		else {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error:", "Cannot apply ModelicaML stereotype to " + p.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
		}
		return null;
	}
	
	
	private String getRequirementId(Class requirement){
		String id = "";
		Stereotype s = requirement.getAppliedStereotype(Constants.stereotypeQName_Requirement);
		if (s != null) {
			Object o = requirement.getValue(s, Constants.propertyName_id);
			if (o instanceof String) {
				id = "_" + (String) o + "_";
			}
		}
		
		return id;
	}
	
	
//	public int getFinalNumberOfRequiredInstantiations(Class containingClass, Class reqClass){
//		int numberOfExistingInstantations = getNumberOfExisitngClassInstances(containingClass, reqClass);
//		int numberOfRequiredInstantations = getMaxNumberOfProviders(containingClass, reqClass);
//		int finalNumber = numberOfRequiredInstantations - numberOfExistingInstantations; 
//		if (finalNumber > 0 ) {
//			return finalNumber;
//		}
//		return 0;
//	}
	
	
	
	public int getNumberOfExisitngClassInstances(Class containingClass, Class reqClass){
		int numberOfExistingInstance = 0;

		if (containingClass != null) {
			EList<Property> listOfProperties = containingClass.getAllAttributes();
			for (Property property : listOfProperties) {
				Type pType = property.getType();
				if (pType != null && pType.equals(reqClass)) {
					numberOfExistingInstance ++;
				}
			}
		}
		return numberOfExistingInstance;	
	}
	
	
    /**
     * Gets the number of required instantiations.
     *
     * @param req the req
     * @return the number of required instantiations
     */
	public int getMaxNumberOfProviders(Class containingClass, Class reqClass, HashSet<Element> preCollectedMediators){
    	EList<Property> inputsList = getRequirementInputPropertiesList(reqClass);
    	
    	// default number if required instantiations
    	int numberOrRequiredInstantiations = 1;  

		ClassInstantiation ast = new ClassInstantiation(containingClass, true, false);
		ast.setAllMediators(preCollectedMediators);
		ast.createTree();
		TreeParent astRoot = ast.getTreeRoot();
		
		ValueBindingsDataCollector dc = new ValueBindingsDataCollector();
		dc.collectElementsFromInstantiationTree(ast);
		
		Set<Element> umlElementsInTreeInstantiation = dc.getUmlElementsInInstantiationTree();
		// add the property in order to simulate that it is a class instantiation tree item 
		umlElementsInTreeInstantiation.addAll(inputsList);
		// set pre-collected mediators in order to avoid iterating over all elements if this was done before
		dc.setAllMediators(preCollectedMediators);
		// collect used mediators (i.e. mediators that have references to tree items)
		dc.collectMeditorsDataFromUmlModel(containingClass.getModel(), umlElementsInTreeInstantiation);
		
		DeriveValueBindingCodeHelper deriveCodeHelper = new DeriveValueBindingCodeHelper();
		// set the data collection manually in order to ensure that the properties are simulated as tree items in the class instantiation tree.
		deriveCodeHelper.setDataCollection(dc);
		
    	for (Property property : inputsList) {
    		if (astRoot != null) {
    			
    			// set the client in order to simulate that this is a tree item client
    			deriveCodeHelper.setClient(null, property, false);
    			
    			// find the mediator(s)
    			deriveCodeHelper.findMediator(null, property, false);
    			
    			// get the mediator found. If there is no mediator then either there are multiple mediators for selection or none at all found.
    			Element selectedMediator = deriveCodeHelper.getMediatorElement();
    			
    			if (selectedMediator != null) {
    				// call it in order to set the providers
    				deriveCodeHelper.findProviders(selectedMediator, false, false);
    				
    				// get mediator script in order to see if multiple providers will be reduced to one.
    				String mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(selectedMediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
    				
    				// if it does not have operation to reduce multiple providers to one
    				// -> get the max. number of providers
    				if ( !DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorOperation) ) {
    					HashSet<TreeObject> providers = deriveCodeHelper.getProviders();
            			HashSet<TreeObject> multipleProvidersForSelection = deriveCodeHelper.getProvidersForSelection();
            			
//            			System.err.println("providers: " + providers);
//            			System.err.println("multipleProvidersForSelection: " + multipleProvidersForSelection);
        				
        				if (providers.size() > 0 ) {
                			if (providers.size() > numberOrRequiredInstantiations) {
                	    		numberOrRequiredInstantiations = providers.size(); 
            				}
    					}
            			else if (multipleProvidersForSelection.size() > 0 ) {
                			if (multipleProvidersForSelection.size() > numberOrRequiredInstantiations) {
                	    		numberOrRequiredInstantiations = multipleProvidersForSelection.size(); 
            				}
    					}
					} else {
						// there operation of the mediator reduces the multiple providers to one. 
					}
				}
    			else {
    				//No mediator could be selected for the requirement property ...
//    				System.err.println("No mediator could be selected for " + property.getQualifiedName());
    			}
			}
    		else {
    			System.err.println("Cannot access the instantiator tree root");
    		}
		}
    	
//    	System.err.println("containingClass: " + containingClass.getName());
//    	System.err.println("reqClass: " + reqClass.getName());
//    	System.err.println("numberOrRequiredInstantiations: " + numberOrRequiredInstantiations);
    	
    	return numberOrRequiredInstantiations;
    }
	
    /**
     * Gets the requirement input properties list.
     *
     * @param req the req
     * @return the requirement input properties list
     */
    private EList<Property> getRequirementInputPropertiesList(Class req){
    	EList<Property> inputsList = new BasicEList<Property>();
    	EList<Property> list = req.getAllAttributes();
    	for (Property property : list) {
    		Stereotype stereotype = property.getAppliedStereotype(Constants.stereotypeQName_Variable);
			if (stereotype != null) {
				Object causality = property.getValue(stereotype, Constants.propertyName_causality);
				if (causality != null && causality instanceof EnumerationLiteral) {
					if ( ((EnumerationLiteral)causality).getName().equals( Constants.propertyName_input )) {
						inputsList.add(property);
					}
				}
			}
		}
    	return inputsList;
    }
    
}
