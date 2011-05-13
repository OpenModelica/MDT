package org.openmodelica.modelicaml.helper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveCodeHelper;

public class ValueBindingCreator {
	
	private static List<TreeObject> updatedItems = new ArrayList<TreeObject>(); 
	private static HashMap<TreeObject, String> updatedItemsToNewModification = new HashMap<TreeObject, String>();
	
	private static List<TreeObject> deletedItemsModification = new ArrayList<TreeObject>();
	private static List<String> deletedModifications = new ArrayList<String>();
	

	public static void updateBindings(TreeParent treeParent, TreeParent treeRoot, boolean deleteOldBindings){

		// clear all static lists
		updatedItems.clear();
		updatedItemsToNewModification.clear();
		
		deletedItemsModification.clear();
		deletedModifications.clear();
		
		// update bindings
		update(treeParent, treeRoot, deleteOldBindings);
	}
	
	private static void update(TreeParent treeParent,  TreeParent treeRoot, boolean deleteOldBindings){
		
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
		    		
		    		EList<Element> mediatorsList = TreeUtls.getValueMediators(element, Constants.stereotypeQName_ValueClient, Constants.stereotypeQName_ValueClient_obtainsValueFrom);

//		    		if (mediatorsList.size() > 0 ) {
//		    			for (Element element2 : mediatorsList) {
//		    				if (element2 instanceof NamedElement) {
//		    					System.err.println( item.getDotPath() + " mediator : " + ((NamedElement)element2).getName());
//							}
//						}
//					}
		    		
		    		// delete old bindings
//		    		if ( mediatorsList.size() > 0  && modificationStoreLocation != null && deleteOldBindings ) {
		    		// if there are value providers for this client and the modification store location is clear ...
		    		if ( helper.getValueProviders().size() > 0  && modificationStoreLocation != null && deleteOldBindings ) {
	    				
		    			String result = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);
	    				
		    			if (result != null && !result.trim().equals("")) {
		    				
		    				deletedModifications.add(result);
							deletedItemsModification.add(item);
							
	    					item.setFinalModificationRightHand( null ); 
							item.setFinalModificationSource( null );
						}
					}
		    		
		    		// add modifications
//		    		if (mediatorsList.size() > 0 && modification != null && modificationStoreLocation != null) {
		    		if (modification != null && modificationStoreLocation != null) {
		    			
		    			ModificationManager.addComponentModification( modificationStoreLocation, componentPath, modification, true);
						
		    			item.setFinalModificationRightHand(modification); 
						item.setFinalModificationSource( modificationStoreLocation );
		    			
						updatedItems.add(item);
		    			updatedItemsToNewModification.put(item, modification);		    			
					}
				}
			
				if (item instanceof TreeParent) {
					update((TreeParent)item, treeRoot, deleteOldBindings);
				}
			}
		}
	}
	
	public static List<TreeObject> getDeletedItemsModification() {
		return deletedItemsModification;
	}
	
	public static List<String> getDeletedModifications() {
		return deletedModifications;
	}

	public static List<TreeObject> getUpdatedItems() {
		return updatedItems;
	}

	public static HashMap<TreeObject, String> getUpdatedItemsToNewModification() {
		return updatedItemsToNewModification;
	}
}
