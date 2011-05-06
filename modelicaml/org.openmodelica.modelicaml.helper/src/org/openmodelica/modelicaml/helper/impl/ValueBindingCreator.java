package org.openmodelica.modelicaml.helper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveCodeHelper;

public class ValueBindingCreator {
	private static List<TreeObject> updatedItems = new ArrayList<TreeObject>(); 

	public static List<TreeObject> getUpdatedItems() {
		return updatedItems;
	}

	private static HashMap<TreeObject, String> updatedItemsToNewModification = new HashMap<TreeObject, String>();
	
	public static HashMap<TreeObject, String> getUpdatedItemsToNewModification() {
		return updatedItemsToNewModification;
	}

	public static void updateBindings(TreeParent treeParent, TreeParent treeRoot){

		// clear all static lists
		updatedItems.clear();
		updatedItemsToNewModification.clear();
		
		// update bindings
		update(treeParent,treeRoot);
	}
	
	private static void update(TreeParent treeParent,  TreeParent treeRoot){
		if (treeParent != null && treeRoot != null) {
			
			TreeObject[] children = treeParent.getChildren();
			
			for (int i = 0; i < children.length; i++) {
				
				TreeObject item = children[i];
				Element element = item.getUmlElement();
				
				Element modificationStoreLocation = item.getModificationStoreLocation();
				String componentPath = "";
				if (modificationStoreLocation instanceof Property) {
					componentPath = item.getDotPathWithoutFirstLevelComponent();
				}
				else if (modificationStoreLocation instanceof Generalization) {
					componentPath = item.getDotPath();
				}
				
				if (element instanceof Property) {
//		    		DeriveCodeHelper helper = new DeriveCodeHelper(element, treeRoot);
					DeriveCodeHelper helper = new DeriveCodeHelper(item, treeRoot);
		    		String modification = helper.getCode();
		    		
		    		if (modification != null && modificationStoreLocation != null) {
		    			
		    			ModificationManager.addComponentModification( modificationStoreLocation, componentPath, modification, true);
						
		    			item.setFinalModificationRightHand(modification); 
						if (modificationStoreLocation instanceof NamedElement) {  
							item.setFinalModificationSource( modificationStoreLocation );
						}
		    			
						updatedItems.add(item);
		    			updatedItemsToNewModification.put(item, modification);		    			
		    			
//		    			viewer.update(item, null);
					}
				}
			
				if (item instanceof TreeParent) {
					update((TreeParent)item, treeRoot);
				}
			}
		}
	}
	
//	public void bindReqInputProperties(EList<Property> instantiatedRequirementList, Class containingClass, Boolean tryToMatch) {
//
//		for (Property instantiatedRequirement : instantiatedRequirementList) {
//			
//			Type pType =  instantiatedRequirement.getType();
//			
//			if (pType != null && pType instanceof Class) {
//				EList<Property> reqProperties = getRequirementInputPropertiesList(((Class)pType) );
//				for (Property reqProperty : reqProperties) {
//	    			String valueBindingStereotypeQNameForReqProperty =  Constants.stereotypeQName_ValueClient;
//	    			String valueBindingPropertyNameForReqProperty = Constants.stereotypeQName_ValueClient_obtainsValueFrom;
//	    			Stereotype reqPropertyStereotype = reqProperty.getAppliedStereotype(valueBindingStereotypeQNameForReqProperty);
//					
//					if (reqPropertyStereotype != null) {
//						EList<Element> listOfReqPropertyProxies = TreeUtls.getValueMediators(reqProperty, valueBindingStereotypeQNameForReqProperty, valueBindingPropertyNameForReqProperty);
//				    	EList<TreeObject> listOfDotPathThroughProxiesForReqProperty = TreeUtls.getValueProviders(reqProperty, listOfReqPropertyProxies, astRoot);
//				    	
//				    	if (listOfDotPathThroughProxiesForReqProperty.size() > 1) { // multiple choices
//							if (tryToMatch) {
//								// TODO: implement a "find the best match" ... 
//							}
//						}
//				    	else { // only one choice 
////				    		List<String> list = new ArrayList<String>();
////				    		list.addAll(listOfDotPathThroughProxiesForReqProperty);
////				    		System.err.println("Add modification: " + StringUtls.replaceSpecChar(instantiatedRequirement.getName()) + "." + StringUtls.replaceSpecChar(reqProperty.getName()) + " = " + list.get(0));
//
//				    		// get the first item path
////				    		TreeObject provider = listOfDotPathThroughProxiesForReqProperty.get(0);
//				    		
//				    		DeriveCodeHelper helper = new DeriveCodeHelper(reqProperty, astRoot);
//				    		String modification = helper.getCode();
//				    		
//				    		if (modification != null) {
//				    			ModificationManager.addComponentModification(instantiatedRequirement, StringUtls.replaceSpecChar(reqProperty.getName()), modification, true);	
//							}
//						}
//					}
//				}
//			}
//		}
//		
//	}
	
}
