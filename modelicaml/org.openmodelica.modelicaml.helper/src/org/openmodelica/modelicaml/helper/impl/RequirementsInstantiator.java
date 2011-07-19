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
package org.openmodelica.modelicaml.helper.impl;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
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
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeHelper;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;
import org.openmodelica.modelicaml.view.valuebindings.helpers.ValueBindingsDataCollector;

public class RequirementsInstantiator {
	
	final static String reqPropertyPrefix = Constants.reqirementPropertyNamePrefix;
	
	public void instantiateRequirements(Class containingClass, HashSet<Class> reqClasses, HashMap<Class, Integer> selectedNumberOfInstantiations){
		
//		ClassInstantiation ast = new ClassInstantiation(containingClass, true);
//		ast.createTree();
//		TreeParent astRoot = ast.getTreeRoot();
		
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

		bindReqInputProperties(instantiatedRequirements, containingClass);
	}
	
	public void bindReqInputProperties(EList<Property> instantiatedRequirementList, Class containingClass) {
		
		// class instantiation after the requirements were added
		ClassInstantiation ast = new ClassInstantiation(containingClass, true);
		ast.createTree();
		TreeParent treeRoot = ast.getTreeRoot();
		
		// find the instantiation class tree items based on the created UML properties
		HashSet<TreeObject> instantiationClassTreeItems = new HashSet<TreeObject>();
		for (Property property : instantiatedRequirementList) {
			HashSet<TreeObject> treeItems = findTreeItems(property, treeRoot, new HashSet<TreeObject>());
			instantiationClassTreeItems.addAll(treeItems);
		}
		
		// update bindings
		ValueBindingCreator vc = new ValueBindingCreator();

		for (TreeObject treeObject : instantiationClassTreeItems) {
			if (treeObject instanceof TreeParent) {
				vc.updateAllBindings( ((TreeParent)treeObject).getSelectedClass().getModel(), (TreeParent)treeObject, treeRoot, false, true, false, false);
			}
		}
		
		
//		for (Property instantiatedRequirement : instantiatedRequirementList) {
//			Type pType =  instantiatedRequirement.getType();
//			
//			if (pType != null && pType instanceof Class) {
//				EList<Property> reqProperties = getRequirementInputPropertiesList(((Class)pType) );
//				for (Property reqProperty : reqProperties) {
//
//					// collect all binding data from the model 
//					// simulate as the reqProperty is already in the tree by passing the tempListOfReqElement
//					ValueBindingsDataCollector dc = new ValueBindingsDataCollector();
//					dc.collectElementsFromInstantiationTree(astRoot);
//					
//					HashSet<Element> umlElementsInTreeInstantiation = dc.getUmlElementsInInstantiationTree();
//					// add the property in order to simulate that it is a class instantiation tree item 
//					umlElementsInTreeInstantiation.add(reqProperty);
//					// collect used mediators (i.e. mediators that have references to tree items)
//					dc.collectMeditorsDataFromUmlModel(containingClass.getModel(), umlElementsInTreeInstantiation);
//					
//					DeriveValueBindingCodeHelper deriveCodeHelper = new DeriveValueBindingCodeHelper();
//					// set the data collection manually in order to ensure that the properties are simulated as tree items in the class instantiation tree.
//					deriveCodeHelper.setDataCollection(dc);
//					
//					// set the client in order to simulate that this is a tree item client
//	    			deriveCodeHelper.setClient(null, reqProperty, false);
//	    			
//	    			// find the mediator(s)
//	    			deriveCodeHelper.findMediator(null, reqProperty, false);
//	    			
//					// if this property is a client at all
//					if ( deriveCodeHelper.getDataCollection().getReferencedClients().contains(reqProperty) ) {
//						
//			    		// Find the tree item for property in the components tree
//						HashSet<TreeObject> list = findTreeItems(reqProperty, astRoot, new HashSet<TreeObject>());
//
//						// get the mediator found. If there is no mediator then either there are multiple mediators for selection or none at all found.
//		    			Element selectedMediator = deriveCodeHelper.getMediatorElement();
//		    			
//		    			if (selectedMediator != null) {
//		    				
//		    				// call it in order to set the providers
//		    				deriveCodeHelper.findProviders(selectedMediator, false, false);
//	    					HashSet<TreeObject> providers = deriveCodeHelper.getProviders();
//		    				
//		    				// get mediator script in order to see if multiple providers will be reduced to one.
//		    				String mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(selectedMediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
//
//		    				String bindingCode = deriveCodeHelper.deriveCode(null, null, selectedMediator, mediatorOperation, providers);
//
//				    		if (bindingCode != null && !bindingCode.equals("")) {
//				    			ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), bindingCode, true);	
//							}
//		    			}
//					}
//				}
//			}
//		}
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
			MessageDialog.openError(new Shell(), "Error:", "Cannot apply ModelicaML stereotype to " + p.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
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
//	public int getNumberOfRequiredInstantiations(Class containingClass, Class reqClass){
	public int getMaxNumberOfProviders(Class containingClass, Class reqClass){
    	EList<Property> inputsList = getRequirementInputPropertiesList(reqClass);
    	
    	// default number if required instantiations
    	int numberOrRequiredInstantiations = 1;  
    	
		ClassInstantiation ast = new ClassInstantiation(containingClass, true);
		ast.createTree();
		TreeParent astRoot = ast.getTreeRoot();
		
		ValueBindingsDataCollector dc = new ValueBindingsDataCollector();
		dc.collectElementsFromInstantiationTree(astRoot);
		
		HashSet<Element> umlElementsInTreeInstantiation = dc.getUmlElementsInInstantiationTree();
		// add the property in order to simulate that it is a class instantiation tree item 
		umlElementsInTreeInstantiation.addAll(inputsList);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
//	private void refillReservedBindingsList(Class containingClass, Property property){
//		reservedBindings.clear(); // clear this list from the last iteration
//		
//		Type pType = property.getType();
//		if (pType != null) {
//			EList<Property> existingClassInstances = getExisitngClassInstances(containingClass, (Class) pType);
////			System.err.println(property.getName() + ": ");
//			for (Property instantiatedReq : existingClassInstances) {
//				HashSet<String> existingModifications = ModificationsCollector.getComponentModifications(instantiatedReq);
//				for (String string : existingModifications) {
//					String[] splitted = string.split("=");
//					if (splitted.length > 1) {
//						reservedBindings.add(splitted[1].trim());
////						System.err.println("  " + splitted[1].trim() + " was added to used value providers.");
//					}
//				}
//			}
//		}
//		
//	}
    
    
    
    
	
//	public void bindRequirementProperties(Property instantiatedRequirement, TreeParent treeObject){
//		TreeObject[] children = treeObject.getChildren();
//		
//		for (int i = 0; i < children.length; i++) {
//			if (children[i].isLeaf()) {
//				Property childProperty = children[i].getProperty();
//				
//				if (childProperty != null) {
//					
//					//System.err.println("childProperty: " + childProperty.getName());
//					
//					String valueBindingStereotypeQName = "ModelicaMLTesting::ValueBinding::ValueProvider"; 
//					String valueBindingPropertyName = "providesValueFor";
//					
//					if (childProperty.getAppliedStereotype(valueBindingStereotypeQName) != null) {
//						//System.err.println("childProperty has stereotype");
//						EList<Element> listOfChildPropertyProxies = TreeUtls.getValueMediators(childProperty, valueBindingStereotypeQName, valueBindingPropertyName);
//						for (Element proxy : listOfChildPropertyProxies) {
//							//System.err.println("Proxy found: " + proxy.getName());
//							
//								// get requirement type class
//								Type pType =  instantiatedRequirement.getType();
//								
//								if (pType != null && pType instanceof Class) {
//									// get all requirements properties
//									//EList<Property> reqProperties = ((Class)pType).getAllAttributes(); // TODO: get only inputs, not all properties!
//									EList<Property> reqProperties = getRequirementInputPropertiesList(((Class)pType) );
//									for (Property reqProperty : reqProperties) {
//										
//						    			String valueBindingStereotypeQNameForReqProperty =  "ModelicaMLTesting::ValueBinding::ValueClient";
//						    			String valueBindingPropertyNameForReqProperty = "obtainsValueFrom";
//						    			Stereotype reqPropertyStereotype = reqProperty.getAppliedStereotype(valueBindingStereotypeQNameForReqProperty);
//										
//						    			//System.err.println("reqProperty: " + reqProperty.getName());
//										
//										if (reqPropertyStereotype != null) {
//											EList<Element> listOfReqPropertyProxies = TreeUtls.getValueMediators(reqProperty, valueBindingStereotypeQNameForReqProperty, valueBindingPropertyNameForReqProperty);
//									    	EList<TreeObject> listOfDotPathThroughProxiesForReqProperty = TreeUtls.getValueProviders(reqProperty, listOfReqPropertyProxies, astRoot);
//											
//											for (Element reqPropertyProxy : listOfReqPropertyProxies) {
//												//System.err.println("reqPropertyProxy: " + reqPropertyProxy.getName());
//												if (reqPropertyProxy.equals(proxy)) { // if there is a binding defined
//													
//													// create an identifier for this combination.
//													String ReqPropertyKey = StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." 
//													+ StringUtls.replaceSpecChar(reqProperty.getName());
//
//													String valueProviderKey = children[i].getDotPath();
//													
//													if ( !reqPropertiesBound.contains(ReqPropertyKey) 
//															&& !valueProvidersUsed.contains(valueProviderKey) 
//															&& !reservedBindings.contains(valueProviderKey)) {
//														
//														if (listOfDotPathThroughProxiesForReqProperty.size() > 1) { // if there are multiple candidates then take only those from the current tree branch
//															
//															if (entryPoint == null) {
//																entryPoint = children[i].getDotPath().substring(0, children[i].getDotPath().length() - StringUtls.replaceSpecChar(children[i].getName()).length());
//																System.err.println("entryPoint: " + entryPoint);
//															}
//															
//															if (entryPoint != null && children[i].getDotPath().startsWith(entryPoint)) {
//																//Add modification to the instantiatedRequirement
////																System.err.println("Modification to add to " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + ":");
////																System.err.println( StringUtls.replaceSpecChar(reqProperty.getName()) + " = "  + children[i].getDothPath() );
//																System.err.println( StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName() ) + " = "  + children[i].getDotPath() );
//																ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), children[i].getDotPath(), true);
//																
//																// mark this combination to be already used.
//																reqPropertiesBound.add(ReqPropertyKey); 
//			//													System.err.println("ReqPropertyKey: " + ReqPropertyKey);
//
//																// mark this value provides as being already used only if there are multiple candidates.
//																if (listOfDotPathThroughProxiesForReqProperty.size() > 1) {
//																	valueProvidersUsed.add(valueProviderKey);
////																	System.err.println("valueProviderKey: " + valueProviderKey);
//																	
//																	// Remove from potential candidates
//																	removePotentialValueProvidersBasedOnDotPath(valueProviderKey);
//																}
//															}
//															else {
//																// add the dotPath, the instantiated requirement and the requirement property to a map.
//																PotentialValueProvider pc = new PotentialValueProvider();
//																pc.instantiatedRequirement = instantiatedRequirement;
//																pc.requirementInputProperty = reqProperty;
//																pc.valueProviderDotPath = valueProviderKey;
//																pc.valueProvider = children[i];
//																
////																if ( valueProvidersPotentialCandidatesDotPath.add(valueProviderKey) ) { //avoid duplicates
//																	valueProvidersPotentialCandidates.add(pc);
//																	System.err.println("Potential candidate for " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName() ) + " = "  + children[i].getDotPath());
////																}
//															}
//														}
//														else { // there is only one provider, always take it
////															System.err.println( StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName() ) + " = "  + children[i].getDothPath() );
//															ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), children[i].getDotPath(), true);
//															
//															// mark this combination to be already used.
//															reqPropertiesBound.add(ReqPropertyKey); 
//		//													System.err.println("ReqPropertyKey: " + ReqPropertyKey);
//
//														}
//														
//														
//														
////														if (entryPoint != null && children[i].getDothPath().startsWith(entryPoint)) {
//////															System.err.println( StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." 
//////															+ StringUtls.replaceSpecChar(reqProperty.getName())
//////															+ " = "  + children[i].getDothPath() );
////
////															//Add modification to the instantiatedRequirement
//////															System.err.println("Modification to add to " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + ":");
//////															System.err.println( StringUtls.replaceSpecChar(reqProperty.getName()) + " = "  + children[i].getDothPath() );
////															System.err.println( StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName() ) + " = "  + children[i].getDothPath() );
////															ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), children[i].getDothPath(), true);
////															
////															// mark this combination to be already used.
////															reqPropertiesBound.add(ReqPropertyKey); 
////		//													System.err.println("ReqPropertyKey: " + ReqPropertyKey);
////
////															// mark this value provides as being already used only if there are multiple candidates.
////															if (listOfDotPathThroughProxiesForReqProperty.size() > 1) {
////																valueProvidersUsed.add(valueProviderKey);
//////																System.err.println("valueProviderKey: " + valueProviderKey);
////															}
////														}
//														
//														
//													}
//												}
//											}
//										}
//									}
//								}
//						}
//					}
//				}
//			}
//			else {
//				bindRequirementProperties(instantiatedRequirement, (TreeParent) children[i]);
//			}
//		}
//	}
	
	
	
//	private void bindRequirementPropertiesWithPotentialCandidates(Property instantiatedRequirement){
//		// get requirement type class
//		Type pType =  instantiatedRequirement.getType();
//		
//		if (pType != null && pType instanceof Class) {
//			// get all requirements properties
//			EList<Property> reqProperties = getRequirementInputPropertiesList(((Class)pType) );
//			for (Property reqProperty : reqProperties) {
//				
//				List<PotentialValueProvider> valueProviders = new ArrayList<PotentialValueProvider>();
//				valueProviders.addAll(valueProvidersPotentialCandidates);
//				
//				for (PotentialValueProvider pvp : valueProviders) {
//					
//					if (pvp.instantiatedRequirement == instantiatedRequirement && pvp.requirementInputProperty == reqProperty) { 
//						
//						// create an identifier for this combination.
//						String ReqPropertyKey = StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName());
//						String valueProviderKey = pvp.valueProviderDotPath;
//						
//						if ( !reqPropertiesBound.contains(ReqPropertyKey) && !valueProvidersUsed.contains(valueProviderKey) && !reservedBindings.contains(valueProviderKey) ) {
//							
////							if (listOfDotPathThroughProxiesForReqProperty.size() > 1) { // if there are multiple candidates then take only those from the current tree branch
////								if (entryPoint == null) {
////									entryPoint = pvp.valueProviderDotPath.substring(0, pvp.valueProviderDotPath.length() - StringUtls.replaceSpecChar(pvp.valueProvider.getName()).length());
//////									System.err.println("entryPoint: " + entryPoint);
////								}
//								
////								if (entryPoint != null && pvp.valueProviderDotPath.startsWith(entryPoint)) {
//									//Add modification to the instantiatedRequirement
////									System.err.println("Modification to add to " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + ":");
////									System.err.println( StringUtls.replaceSpecChar(reqProperty.getName()) + " = "  + children[i].getDothPath() );
//									System.err.println( StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName() ) + " = "  + pvp.valueProviderDotPath );
//									ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), pvp.valueProviderDotPath, true);
//									
//									// mark this combination to be already used.
//									reqPropertiesBound.add(ReqPropertyKey); 
////												System.err.println("ReqPropertyKey: " + ReqPropertyKey);
//
//									// mark this value provides as being already used only if there are multiple candidates.
////									if (listOfDotPathThroughProxiesForReqProperty.size() > 1) {
//										valueProvidersUsed.add(valueProviderKey);
////										System.err.println("valueProviderKey: " + valueProviderKey);
//										
//										// Remove from potential candidates
//										removePotentialValueProvidersBasedOnDotPath(valueProviderKey);
////									}
////								}
////								else {
////									// add the dotPath, the instantiated requirement and the requirement property to a map.
////									PotentialValueProvider pc = new PotentialValueProvider();
////									pc.instantiatedRequirement = instantiatedRequirement;
////									pc.requirementInputProperty = reqProperty;
////									pc.valueProviderDotPath = valueProviderKey;
////									pc.valueProvider = pvp.valueProvider;
////									valueProvidersPotentialCandidates.add(pc);
////									
////									System.err.println("Potential candidate for " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName() ) + " = "  + pvp.valueProviderDotPath);
////								}
////							}
////							else { // there is only one provider, always take it
////								System.err.println( StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName() ) + " = "  + children[i].getDothPath() );
////								ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), children[i].getDothPath(), true);
////								
////								// mark this combination to be already used.
////								reqPropertiesBound.add(ReqPropertyKey); 
//////												System.err.println("ReqPropertyKey: " + ReqPropertyKey);
//	//
////							}
//						}
//					}
//				}
//			}
//		}
//	}
	
	
	
    
    
//	private void removePotentialValueProvidersBasedOnDotPath(String dotPath) {
//		HashSet<PotentialValueProvider> objectsToRemove = new HashSet<PotentialValueProvider>();
//		for (PotentialValueProvider potentialValueProvider : valueProvidersPotentialCandidates) {
//			if (potentialValueProvider.valueProviderDotPath.equals(dotPath) ) {
//				objectsToRemove.add(potentialValueProvider);
//			}
//		}
//		for (PotentialValueProvider potentialValueProvider : objectsToRemove) {
//			valueProvidersPotentialCandidates.remove(potentialValueProvider);
//		}
//	}
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    
//
//	/**
//	 * Gets the value bindings.
//	 *
//	 * @param property the property
//	 * @param valueBindingStereotypeQName the value binding stereotype q name
//	 * @param valueBindingPropertyName the value binding property name
//	 * @return the value bindings
//	 */
//	public HashSet<Property> getValueBindings(Property property, String valueBindingStereotypeQName, String valueBindingPropertyName){
//		HashSet<Property> list = new HashSet<Property>();
//		//final String stereotypeQName = "ModelicaMLTesting::ValueBinding::ValueClient";
//		String stereotypeQName = valueBindingStereotypeQName;
//		Stereotype valueBindingStereotype = property.getAppliedStereotype(stereotypeQName);
//		if (valueBindingStereotype != null) {
//			//Object stereotypeValue = property.getValue(valueBindingStereotype, "obtainsValueFrom");
//			Object stereotypeValue = property.getValue(valueBindingStereotype, valueBindingPropertyName);
//			if (stereotypeValue instanceof EList) {
//				for (Object object : (EList)stereotypeValue) {
//					EList<EObject> crossreferencesList = ((EObject)object).eCrossReferences();
//					for (EObject eObject : crossreferencesList) { // to get the Property that the stereotype is applied to
//						if (eObject instanceof Property) {
//							//System.err.println(property.getName() + " has the proxy: " + ((Property)eObject).getName());
//							list.add((Property)eObject); // add the proxy Property
//						}
//					}
//				}
//			}
//		}
//		return list;
//	}
//	
//	/**
//	 * Gets the all linked primitive variables dot path through proxy.
//	 *
//	 * @param p the p
//	 * @param listOfProxies the list of proxies
//	 * @param treeParent the tree parent
//	 * @return the all linked primitive variables dot path through proxy
//	 */
//	public HashSet<String> getAllLinkedPrimitiveVariablesDotPathThroughProxy (Property p, HashSet<Property> listOfProxies, TreeObject treeParent){
//		HashSet<String> list = new HashSet<String>();
//		TreeObject[] children = ((TreeParent)treeParent).getChildren();
//		for (int i = 0; i < children.length; i++) {
//			if (children[i].isLeaf()) {
//				Property childProperty = children[i].getProperty();
//				if (childProperty != null && !childProperty.equals(p)) {
//					
//					//System.err.println("childProperty: " + childProperty.getName());
//					
//					String valueBindingStereotypeQName = Constants.stereotypeQName_ValueProvider; 
//					String valueBindingPropertyName = Constants.stereotypeQName_ValueProvider_providesValueFor;
//					
//					if (childProperty.getAppliedStereotype(valueBindingStereotypeQName) != null) {
//						//System.err.println("childProperty has stereotype");
//						HashSet<Property> listOfChildPropertyProxies = getValueBindings(childProperty, valueBindingStereotypeQName, valueBindingPropertyName);
//						
////						System.err.println("listOfProxies: " + listOfProxies);
////						System.err.println("listOfChildPropertyProxies: " + listOfChildPropertyProxies);
//						
//						for (Property property : listOfChildPropertyProxies) {
//							if (listOfProxies.contains(property)) {
////								System.err.println("the list of proxies contains the proxy '"+property.getName()+"' of the childProperty '" + childProperty.getName() + "' ");
//								list.add(children[i].getDotPath());
//								// TODO: if it is a state then add ".active" to the dotPath.
//							}
//						}
//					}
//				}
//			}
//			else {
//				HashSet<String> nextLevelList = getAllLinkedPrimitiveVariablesDotPathThroughProxy(p, listOfProxies ,children[i]);
//				list.addAll(nextLevelList);
//			}
//		}
//		
//		// TODO: do the same for states of statemachines.
//		
//		return list;
//	}
//    


	
	
	
	
	
	
    
    
    
    
    
    
    
    
    
    
//	private PotentialValueProvider getPotentialValueProviderObjectBasedOnItsDotPath(HashSet<PotentialValueProvider> list, String dotPath) {
//	for (PotentialValueProvider potentialValueProvider : list) {
//		if (potentialValueProvider.valueProviderDotPath.equals(dotPath) ) {
//			return potentialValueProvider;
//		}
//	}
//	return null;
//}
    
    
    
    
//	public void bindRequirementProperties_roughGuess(EList<Property> instantiatedRequirements, TreeParent treeObject){
//	//EList<String> modificationsList = new BasicEList<String>();
//	
//	TreeObject[] children = treeObject.getChildren();
//	
//	for (int i = 0; i < children.length; i++) {
//		if (children[i].isLeaf()) {
//			Property childProperty = children[i].getProperty();
//			
//			if (childProperty != null) {
//				
//				//System.err.println("childProperty: " + childProperty.getName());
//				
//				String valueBindingStereotypeQName = "ModelicaMLTesting::ValueBinding::ValueProvider"; 
//				String valueBindingPropertyName = "providesValueFor";
//				
//				if (childProperty.getAppliedStereotype(valueBindingStereotypeQName) != null) {
//					//System.err.println("childProperty has stereotype");
//					HashSet<Property> listOfChildPropertyProxies = treeObject.getValueBindings(childProperty, valueBindingStereotypeQName, valueBindingPropertyName);
//					for (Property proxy : listOfChildPropertyProxies) {
//						//System.err.println("Proxy found: " + proxy.getName());
//						
//						for (Property instantiatedRequirement : instantiatedRequirements) {
//							// get requirement type class
//							Type pType =  instantiatedRequirement.getType();
//							
//							if (pType != null && pType instanceof Class) {
//								// get all requirements properties
//								//EList<Property> reqProperties = ((Class)pType).getAllAttributes(); // TODO: get only inputs, not all properties!
//								EList<Property> reqProperties = getRequirementInputPropertiesList(((Class)pType) );
//								for (Property reqProperty : reqProperties) {
//									
//					    			String valueBindingStereotypeQNameForReqProperty =  "ModelicaMLTesting::ValueBinding::ValueClient";
//					    			String valueBindingPropertyNameForReqProperty = "obtainsValueFrom";
//					    			Stereotype reqPropertyStereotype = reqProperty.getAppliedStereotype(valueBindingStereotypeQNameForReqProperty);
//									
//					    			//System.err.println("reqProperty: " + reqProperty.getName());
//									
//									if (reqPropertyStereotype != null) {
//										HashSet<Property> listOfReqPropertyProxies = treeObject.getValueBindings(reqProperty, valueBindingStereotypeQNameForReqProperty, valueBindingPropertyNameForReqProperty);
//								    	HashSet<String> listOfDotPathThroughProxiesForReqProperty = ((TreeParent)treeObject).getAllLinkedPrimitiveVariablesDotPathThroughProxy(reqProperty, listOfReqPropertyProxies, astRoot);
//										
//										for (Property reqPropertyProxy : listOfReqPropertyProxies) {
//											//System.err.println("reqPropertyProxy: " + reqPropertyProxy.getName());
//											if (reqPropertyProxy.equals(proxy)) { // if there is a binding defined
//												
//												// create an identifier for this combination.
//												String ReqPropertyKey = StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." 
//												+ StringUtls.replaceSpecChar(reqProperty.getName());
//
//												String valueProviderKey = children[i].getDothPath();
//												
//												if ( !reqPropertiesBound.contains(ReqPropertyKey) && !valueProvidersUsed.contains(valueProviderKey)) {
//													
////													System.err.println( StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." 
////															+ StringUtls.replaceSpecChar(reqProperty.getName())
////															+ " = "  + children[i].getDothPath() );
//
//													//Add modification to the instantiatedRequirement
////													System.err.println("Modification to add to " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + ":");
////													System.err.println( StringUtls.replaceSpecChar(reqProperty.getName()) + " = "  + children[i].getDothPath() );
//													ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), children[i].getDothPath(), true);
//													
//													// mark this combination to be already used.
//													reqPropertiesBound.add(ReqPropertyKey); 
////													System.err.println("ReqPropertyKey: " + ReqPropertyKey);
//
//													// mark this value provides as being already used only if there are multiple candidates.
//													if (listOfDotPathThroughProxiesForReqProperty.size() > 1) {
//														valueProvidersUsed.add(valueProviderKey);
////														System.err.println("valueProviderKey: " + valueProviderKey);
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		else {
//			bindRequirementProperties_roughGuess(instantiatedRequirements, (TreeParent) children[i]);
//		}
//	}
//}
}
