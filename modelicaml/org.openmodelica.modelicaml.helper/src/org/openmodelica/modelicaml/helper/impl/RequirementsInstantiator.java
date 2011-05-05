package org.openmodelica.modelicaml.helper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.ModificationsCollector;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveCodeHelper;

public class RequirementsInstantiator {
	
	final static String reqPropertyPrefix = "req_";

	List<TreeObject> valueProviders = new ArrayList<TreeObject>();
	HashSet<String> valueProvidersUsed = new HashSet<String>();
	HashSet<String> reservedBindings = new HashSet<String>(); // used and reseted for each instantiated requirement

	class ValueBinding{
		public Property instantiatedRequirement = null;
		public Property  requirementInputProperty = null;
		public TreeObject valueProvider = null;
		public String valueProviderDotPath = null;
	}
	class PotentialValueProvider extends ValueBinding {	}
	
	List<PotentialValueProvider> valueProvidersPotentialCandidates = new ArrayList<PotentialValueProvider>();
	//HashSet<String> valueProvidersPotentialCandidatesDotPath = new HashSet<String>();
	
	HashSet<String> reqPropertiesBound = new HashSet<String>();
	List<ValueBinding> finalBindings = new ArrayList<ValueBinding>();
	
	ClassInstantiation ast; // the ast object
	TreeParent astRoot; // the root node of the ast 
	
//	private String entryPoint = null; // used for marking the parent-dotPath of the first value provider found 
	
	
	public void instantiateRequirements(Class containingClass, HashSet<Class> reqClasses, HashMap<Class, Integer> selectedNumberOfInstantiations){
    	ast = new ClassInstantiation(containingClass, true);
		ast.createTree();
		astRoot = ast.getTreeRoot();
		
		EList<Property> instantiatedRequirements = new BasicEList<Property>();
		
		for (Class reqClass : reqClasses) {
			Integer numberOfInstantiations = 0; 
			if (selectedNumberOfInstantiations != null) {
				Integer n = selectedNumberOfInstantiations.get(reqClass);
				if (n != null) {
					numberOfInstantiations = n;
				}
				else {
					numberOfInstantiations = getFinalNumberOfRequiredInstantiations(containingClass, reqClass);
				}
			}
			else {
				numberOfInstantiations = getFinalNumberOfRequiredInstantiations(containingClass, reqClass);
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

		bindReqInputProperties(instantiatedRequirements, containingClass, true);
		
		
//		// Initial matching of bindings
//		for (Property property : instantiatedRequirements) {
//			System.err.println("\nInitial matching of bindings ... ");
//			refillReservedBindingsList(containingClass, property);
//			entryPoint = null; // it is important to reset the entry point for each instantiated requirement
//			bindRequirementProperties(property, astRoot); // does the initial binding matching
//		}
//		
//		System.err.println("\n#######################################################################\n");
//		System.err.println("\n Value providers list");
//		for (PotentialValueProvider o : valueProvidersPotentialCandidates) {
//			System.err.println( "         " + o.valueProviderDotPath);
//		}
//		System.err.println("\n#######################################################################\n");
//		
//		//Matching of candidates that are left from the initial matching of bindings ...
//		int lastValueProvidersPotentialCandidatesSize;
//		while (valueProvidersPotentialCandidates.size() > 0) {
//			entryPoint = null;
//			lastValueProvidersPotentialCandidatesSize = valueProvidersPotentialCandidates.size();
//			
//			System.err.println("\nMatching of candidates that are left from the initial matching of bindings ... ");
//			for (Property property : instantiatedRequirements) {
//				refillReservedBindingsList(containingClass, property);
//				entryPoint = null; // it is important to reset the entry point for each instantiated requirement
//				//bindRequirementPropertiesWithPotentialCandidates(property);
//				bindRequirementProperties(property, astRoot);
//			}
//			
//			if (valueProvidersPotentialCandidates.size() >= lastValueProvidersPotentialCandidatesSize) {
//				System.err.println("\n valueProvidersPotentialCandidates list is same or increased -> Break the matching ...");
//				if (valueProvidersPotentialCandidates.size() > 0 ) {
//					for (PotentialValueProvider o : valueProvidersPotentialCandidates) {
////						System.err.println("Value client: " + o.instantiatedRequirement.getName() + "." + o.requirementInputProperty.getName() );
//						System.err.println("Not possible to bind the valueProvider " + o.valueProviderDotPath);
//					}
//				}
//				break; // stop if it was not possible to reduce the list of candidates
//			}
//		}

//		bindRequirementProperties_roughGuess(instantiatedRequirements, astRoot); // WORKS but not correct.

	}
	
	public void bindReqInputProperties(EList<Property> instantiatedRequirementList, Class containingClass, Boolean tryToMatch) {

		for (Property instantiatedRequirement : instantiatedRequirementList) {
			
			refillReservedBindingsList(containingClass, instantiatedRequirement); 
			
			Type pType =  instantiatedRequirement.getType();
			
			if (pType != null && pType instanceof Class) {
				EList<Property> reqProperties = getRequirementInputPropertiesList(((Class)pType) );
				for (Property reqProperty : reqProperties) {
	    			String valueBindingStereotypeQNameForReqProperty =  Constants.stereotypeQName_ValueClient;
	    			String valueBindingPropertyNameForReqProperty = Constants.stereotypeQName_ValueClient_obtainsValueFrom;
	    			Stereotype reqPropertyStereotype = reqProperty.getAppliedStereotype(valueBindingStereotypeQNameForReqProperty);
					
					if (reqPropertyStereotype != null) {
						EList<Element> listOfReqPropertyProxies = TreeUtls.getValueMediators(reqProperty, valueBindingStereotypeQNameForReqProperty, valueBindingPropertyNameForReqProperty);
				    	EList<TreeObject> listOfDotPathThroughProxiesForReqProperty = TreeUtls.getValueProviders(reqProperty, listOfReqPropertyProxies, astRoot);
				    	
				    	if (listOfDotPathThroughProxiesForReqProperty.size() > 1) { // multiple choices
							if (tryToMatch) {
								// TODO: implement a "find the best match" ... 
							}
						}
				    	else { // only one choice 
//				    		List<String> list = new ArrayList<String>();
//				    		list.addAll(listOfDotPathThroughProxiesForReqProperty);
//				    		System.err.println("Add modification: " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName()) + " = " + list.get(0));

				    		// get the first item path
//				    		TreeObject provider = listOfDotPathThroughProxiesForReqProperty.get(0);
				    		
				    		DeriveCodeHelper helper = new DeriveCodeHelper(reqProperty, astRoot);
				    		String modification = helper.getCode();
				    		
				    		if (modification != null) {
				    			ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), modification, true);	
							}
						}
					}
				}
			}
		}
		
//		List<String> sortedList = new ArrayList<String>();
//		sortedList.addAll(multipleChoiceValueProviderslist);
//		Collections.sort(sortedList);
//		
//		System.err.println("Single choice ValueProviders :");
//		for (String string : singleChoiceValueProviderslist ) {
//			System.err.println("    " + string);
//		}
//
//		System.err.println("\nMultiple candidates ValueProviders :");
//		for (String string : sortedList ) {
//			System.err.println("    " + string);
//		}
//
//		System.err.println("\nValueProviders already in use :");
//		for (String string : valueProvidersUsed ) {
//			System.err.println("    " + string);
//		}
		
	}
	
	
	private void refillReservedBindingsList(Class containingClass, Property property){
		reservedBindings.clear(); // clear this list from the last iteration
		
		Type pType = property.getType();
		if (pType != null) {
			EList<Property> existingClassInstances = getExisitngClassInstances(containingClass, (Class) pType);
//			System.err.println(property.getName() + ": ");
			for (Property instantiatedReq : existingClassInstances) {
				HashSet<String> existingModifications = ModificationsCollector.getComponentModifications(instantiatedReq);
				for (String string : existingModifications) {
					String[] splitted = string.split("=");
					if (splitted.length > 1) {
						reservedBindings.add(splitted[1].trim());
//						System.err.println("  " + splitted[1].trim() + " was added to used value providers.");
					}
				}
			}
		}
		
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
		String prefix = reqPropertyPrefix;
		for (Property property : pList) {
			String pName = StringUtls.replaceSpecChar(property.getName());
			if (pName.substring(0, pName.length() - 2).startsWith(prefix + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase()) ) {
				numberOfReqInstancesWithSameType ++; 
			}
		}
		Integer postfix = numberOfReqInstancesWithSameType + 1;
		String postfixString = "_" + postfix.toString();
		
		// create Property
		Property p = containingClass.createOwnedAttribute(prefix + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase() + postfixString, reqClass);
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
	
	
	public int getFinalNumberOfRequiredInstantiations(Class containingClass, Class reqClass){
		int numberOfExistingInstantations = getNumberOfExisitngClassInstances(containingClass, reqClass);
		int numberOfRequiredInstantations = getNumberOfRequiredInstantiations(containingClass, reqClass);
		int finalNumber = numberOfRequiredInstantations - numberOfExistingInstantations; 
		if (finalNumber > 0 ) {
			return finalNumber;
		}
		return 0;
	}
	
	
	
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
	public int getNumberOfRequiredInstantiations(Class containingClass, Class reqClass){

		ClassInstantiation ast = new ClassInstantiation(containingClass, true);
		ast.createTree();
		TreeParent root = ast.getTreeRoot();
		
    	EList<Property> inputsList = getRequirementInputPropertiesList(reqClass);
    	Integer numberOrRequiredInstantiations = 1;
    	for (Property property : inputsList) {
    		if (root != null) {
    			String valueBindingStereotypeQName =  Constants.stereotypeQName_ValueClient;
    			String valueBindingPropertyName = Constants.stereotypeQName_ValueClient_obtainsValueFrom;
    	    	
    			EList<Element> listOfProxies = TreeUtls.getValueMediators(property, valueBindingStereotypeQName, valueBindingPropertyName);
//    			HashSet<String> listOfDotPathThroughProxies = TreeUtls.getAllLinkedPrimitiveVariablesDotPathThroughProxy(property, listOfProxies, root);
    			EList<TreeObject> listOfDotPathThroughProxies = TreeUtls.getValueProviders(property, listOfProxies, root);
    			if (listOfDotPathThroughProxies.size() > numberOrRequiredInstantiations) {
    	    		numberOrRequiredInstantiations = listOfDotPathThroughProxies.size(); 
				}
//    			System.err.println(listOfDotPathThroughProxies);
//    			System.err.println("listOfDotPathThroughProxies.size(): " + listOfDotPathThroughProxies.size());
//    			System.err.println("numberOrRequiredInstantiations: " + numberOrRequiredInstantiations);
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
