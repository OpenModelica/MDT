package org.openmodelica.modelicaml.helper.impl;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ElementsCollector;

public class VerificationScenariosDataCollector extends ElementsCollector {

	// stereotypes qualified names
	private static final String scenarioStereotypeQName = Constants.stereotypeQName_VerificationScenario;
	private static final String requiresStereotypeQName = Constants.stereotypeQName_Requires;
	private static final String requiredForStereotypeQName = Constants.stereotypeQName_RequiredFor;
	
	// all scenarios found.
	private HashSet<Element> allScenarios = new HashSet<Element>();
	
	// all models that should always be instantiated in addition
	private HashSet<Element> alwaysInclude = new HashSet<Element>();
	
	// model to their additional models
	private HashMap<Element,HashSet<Element>> modelToItsRequiredModels = new HashMap<Element,HashSet<Element>>();


	public VerificationScenariosDataCollector(EObject umlRootElement){
		// collect elements
		collectElementsFromModel(umlRootElement, null);
		
		// add scenarios
		allScenarios.addAll(getElements());
	}
	
	@Override
	protected void collectElement(Element element, boolean isImported) {
		
		// collect elements
		// avoid duplicates that can occur due to the multiple imports of the same elements
		if (element instanceof Class 
				&& ((Element)element).getAppliedStereotype(scenarioStereotypeQName) != null
				&& !elements.contains(element)) {
			
			elements.add(element) ;
			
			if (isImported) {
				importedElements.add(element);
			}
		}
		
		/*
		 * Collect all models that have client or source dependencies 
		 * to indicate that they are required in addition
		 */
		
		else if (element instanceof Dependency) {
			
			Dependency dep = (Dependency)element;
			
			// collect from the requires stereotype -> from the model to its additionally required model
			if ( dep.getAppliedStereotype(requiresStereotypeQName) != null )  {
				Stereotype s = dep.getAppliedStereotype(requiresStereotypeQName);
				if (s != null) {
					Object isAlways = dep.getValue(s, Constants.propertyName_always);
					for (Element target : dep.getTargets()) {
						// add to map
						for (Element client : dep.getClients()) {
							addToModelToItsRequiredModelsMap(client, target);
							
							// add to always include set
							if (isAlways instanceof Boolean && (Boolean)isAlways) {
								alwaysInclude.add(target);
							}
						}
					}
				}
			}
			
			// collect from the requiredFor stereotype -> from the additionally required model to the model it is required for
			else if (((Dependency)element).getAppliedStereotype(requiredForStereotypeQName) != null ) {
				Stereotype s = dep.getAppliedStereotype(requiredForStereotypeQName);
				if (s != null) {
					Object isAlways = dep.getValue(s, Constants.propertyName_always);
					for (Element client : dep.getClients()) {
						// add to map
						for (Element target : dep.getTargets()) {
							addToModelToItsRequiredModelsMap(target, client);
							
							// add to always include set
							if (isAlways instanceof Boolean && (Boolean)isAlways) {
								alwaysInclude.add(client);
							}
						}
					}
				}
			}
		}
	}
	
	
	// Utls ************************************************************************
	
	private void addToModelToItsRequiredModelsMap(Element key, Element item){
		HashSet<Element> set = modelToItsRequiredModels.get(key);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			modelToItsRequiredModels.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			modelToItsRequiredModels.put(key, updatedSet);
		}
	}
	
	// GETTER *********************************************************************
	public HashSet<Element> getAllScenarios() {
		return allScenarios;
	}
	
	public HashSet<Element> getAlwaysInclude() {
		return alwaysInclude;
	}
	public HashMap<Element, HashSet<Element>> getModelToItsRequiredModels() {
		return modelToItsRequiredModels;
	}
	
}
