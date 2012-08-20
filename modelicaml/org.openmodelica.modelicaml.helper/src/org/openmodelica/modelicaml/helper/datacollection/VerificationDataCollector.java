package org.openmodelica.modelicaml.helper.datacollection;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.datacollection.ElementsCollector;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;

public class VerificationDataCollector extends ElementsCollector {

	// stereotypes qualified names
	private static final String scenarioStereotypeQName = Constants.stereotypeQName_VerificationScenario;
	private static final String requirementStereotypeQName = Constants.stereotypeQName_Requirement;

	private static final String requiresStereotypeQName = Constants.stereotypeQName_Requires;
	private static final String requiredForStereotypeQName = Constants.stereotypeQName_RequiredFor;

	/*
	 * Note, for this approach it is required to iterate over all elements.
	 * That is not only in specified packages, but starting with the root element 
	 * and considering all imported elements (infinite depth)! 
	 */
	
	// the top level element that contains all elements and imports 
	private Element rootElement;
	
	// the package containing the requirements to be used
	private Element requirementsPackage;
	// the package containing the scenarios to be used
	private Element scenariosPackage;
	// the package to containing the value bindings to be used
	private Element bindingsPackage;
	
	// name space of specified packages
	private String requirementsPackageQName;
	private String scenariosPackageQName;
	private String bindingsPackageQName;
	
	// all requirements found
	private HashSet<Element> allRequirements = new HashSet<Element>();
	
	// all scenarios found.
	private HashSet<Element> allScenarios = new HashSet<Element>();
	
	// all models that should always be instantiated in addition
	private HashSet<Element> alwaysInclude = new HashSet<Element>();
	
	// all additional models found 
	private HashSet<Element> allAdditionalModels = new HashSet<Element>();

	// all mediators found
	private HashSet<Element> allMediators = new HashSet<Element>();
	
	// model to their additional models
	private HashMap<Element,HashSet<Element>> modelToItsRequiredModels = new HashMap<Element,HashSet<Element>>();


	public VerificationDataCollector( 
				EObject umlRootElement, 
				Element requirementsPackage,
				Element scenariosPackage,
				Element bindingsPackage){
		
		if (umlRootElement instanceof NamedElement) {
			this.rootElement = (Element) umlRootElement;
			
			this.requirementsPackage = requirementsPackage;
			this.scenariosPackage = scenariosPackage;
			this.bindingsPackage = bindingsPackage;
			
			requirementsPackageQName = ModelicaMLServices.getQualifiedName(this.requirementsPackage);
			scenariosPackageQName = ModelicaMLServices.getQualifiedName(this.scenariosPackage);
			bindingsPackageQName = ModelicaMLServices.getQualifiedName(this.bindingsPackage);
			
			// collect elements
			collectElementsFromModel(this.rootElement);
		}
	}
	
	
	/*
	 * This constructor is used if no packages are specified so that
	 * the root element should be used 
	 */
	
	public VerificationDataCollector(EObject umlRootElement){
	
		if (umlRootElement instanceof NamedElement) {
			this.rootElement = (Element) umlRootElement;
			
			this.requirementsPackage = this.rootElement;
			this.scenariosPackage = this.rootElement;
			this.bindingsPackage = this.rootElement;
			
			requirementsPackageQName = ModelicaMLServices.getQualifiedName(requirementsPackage);
			scenariosPackageQName = ModelicaMLServices.getQualifiedName(scenariosPackage);
			bindingsPackageQName = ModelicaMLServices.getQualifiedName(bindingsPackage);
			
			// collect elements
//			collectElementsFromModel(umlRootElement, null);
			collectElementsFromModel(umlRootElement);
		}
	}	
	
	
	@Override
	protected void collect(Element element, boolean isImported) {
		
		// collect elements, avoid duplicates that can occur due to the multiple imports of the same elements
		if ( element instanceof Classifier ) {

			// Add to the overall elements list in order to enable loop detection for imported elements
			getElements().add(element);
			
			if ( ((Element)element).getAppliedStereotype(scenarioStereotypeQName) != null 
					&& ModelicaMLServices.getQualifiedName(element).contains(scenariosPackageQName)
				) {
				
				allScenarios.add(element);
				
			}
			else if (((Element)element).getAppliedStereotype(requirementStereotypeQName) != null
					&& ModelicaMLServices.getQualifiedName(element).contains(requirementsPackageQName)) {
				
				allRequirements.add(element);
			}
		}
		
		/*
		 * Find mediators for bindings
		 */
		else if (element instanceof Property 
				&& ((Element)element).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null
				&& ModelicaMLServices.getQualifiedName(element).contains(bindingsPackageQName)
				) {
			
			// Add to the overall elements list in order to enable loop detection for imported elements
			getElements().add(element);
			
			allMediators.add(element);
		}
		
		/*
		 * Collect all models that have client or source dependencies 
		 * to indicate that they are required in addition
		 */
		
		else if (element instanceof Dependency) {

			// Add to the overall elements list in order to enable loop detection for imported elements
			getElements().add(element);
			
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
							allAdditionalModels.add(target);
							
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
							allAdditionalModels.add(client);
							
							// add to always include set
							if (isAlways instanceof Boolean && (Boolean)isAlways) {
								alwaysInclude.add(client);
							}
						}
					}
				}
			}
		}
		
		if (isImported) {
			importedPackages.add(element);
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
	
	public HashSet<Element> getAllRequirements() {
		return allRequirements;
	}

	public HashSet<Element> getAllMediators() {
		return allMediators;
	}

	public void setAllMediators(HashSet<Element> allMediators) {
		this.allMediators = allMediators;
	}
	
	
	public HashSet<Element> getAllAdditionalModels() {
		return allAdditionalModels;
	}

	public void setAllAdditionalModels(HashSet<Element> allAdditionalModels) {
		this.allAdditionalModels = allAdditionalModels;
	}

}
