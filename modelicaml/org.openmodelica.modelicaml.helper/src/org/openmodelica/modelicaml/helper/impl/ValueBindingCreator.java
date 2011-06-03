package org.openmodelica.modelicaml.helper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeHelper;

public class ValueBindingCreator {
	
	private List<TreeObject> updatedItems = new ArrayList<TreeObject>(); 
	private HashMap<TreeObject, String> updatedItemsToNewModification = new HashMap<TreeObject, String>();
	
	private List<TreeObject> deletedItemsModification = new ArrayList<TreeObject>();
	private List<String> deletedModifications = new ArrayList<String>();
	
	private DeriveValueBindingCodeHelper deriveCodeHelper;

	public void updateBindings(TreeParent treeParent, 
			TreeParent treeRoot, 
			boolean deleteOldBindings, 
			boolean isAutomaticSelectionOfPreferredProvidersEnabled,
			boolean showProgressMonitor){

		// clear all static lists
		updatedItems.clear();
		updatedItemsToNewModification.clear();
		
		deletedItemsModification.clear();
		deletedModifications.clear();
		
		// derive code helper
		deriveCodeHelper = new DeriveValueBindingCodeHelper();
		// initialize the helper in order to collect the data from the model and the instantiated class.
		deriveCodeHelper.initialize(treeRoot, showProgressMonitor); 
		
		// update bindings
		update(treeParent, treeRoot, deleteOldBindings, isAutomaticSelectionOfPreferredProvidersEnabled);
	}
	
	private void update(TreeParent treeParent,  TreeParent treeRoot, boolean deleteOldBindings, boolean isAutomaticSelectionOfPreferredProvidersEnabled){
		
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
					// No user guidance is possible so far. 
					// TODO: Think about the option to guide, 
					// therefore the user should be able to cancel single code derivation or the overall "update all bindings" function.
					// Moreover, the code derivation dialogs should always include the information about client, mediator, provider.
					// This is not the case so far, for example, in the provider selection dialog.
					
					deriveCodeHelper.deriveBindingCodeForClient(item, false, isAutomaticSelectionOfPreferredProvidersEnabled); 
					String code = deriveCodeHelper.getCode(); 
					
		    		// delete old bindings
					// if the client element was found based on the selected tree item or the upper level scripts 
					// -> this means that in general there is a binding.
					// -> If the option is selected then delete the binding for the current tree item. 
					// -> However, it may happen that the binding code cannot be derived without user guidance so that the modification of the current item will be empty at the end. 
					
					if ( deriveCodeHelper.getClientElement() != null && modificationStoreLocation != null && deleteOldBindings ) {
		    			String result = ModificationManager.deleteComponentModificationBasedOnLeftHandValue(modificationStoreLocation, componentPath);
		    			if (result != null && !result.trim().equals("")) {
		    				
		    				deletedModifications.add(result);
							deletedItemsModification.add(item);
							
	    					item.setFinalModificationRightHand( null ); 
							item.setFinalModificationSource( null );
						}
					}
					
		    		// add modifications
		    		if (deriveCodeHelper.getClientElement() != null && code != null && modificationStoreLocation != null) {

		    			ModificationManager.addComponentModification( modificationStoreLocation, componentPath, code, true);
						
		    			item.setFinalModificationRightHand(code); 
						item.setFinalModificationSource( modificationStoreLocation );
		    			
						updatedItems.add(item);
		    			updatedItemsToNewModification.put(item, code);		    			
					}
				}
			
				if (item instanceof TreeParent) {
					update((TreeParent)item, treeRoot, deleteOldBindings, isAutomaticSelectionOfPreferredProvidersEnabled);
				}
			}
		}
	}
	
	public List<TreeObject> getDeletedItemsModification() {
		return deletedItemsModification;
	}
	
	public List<String> getDeletedModifications() {
		return deletedModifications;
	}

	public List<TreeObject> getUpdatedItems() {
		return updatedItems;
	}

	public HashMap<TreeObject, String> getUpdatedItemsToNewModification() {
		return updatedItemsToNewModification;
	}
}
