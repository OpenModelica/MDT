package org.openmodelica.modelicaml.helper.impl;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;

public class VerificationScenariosCollector {
	
	// all scenarios that are found in the top-level model
	private HashSet<Element> allScenarios = new HashSet<Element>();

	// all requirements that are found
	private HashSet<Element> allRequirements = new HashSet<Element>();
	
	// scenarios that can be used to test selected requirements
	private HashSet<Element> matchedScenarios = new HashSet<Element>();

	// package (or any element that is the direct owner of the test scenario) and all test scenarios found in it
	private HashMap<Element,HashSet<Element>> pkgToScenarios = new HashMap<Element,HashSet<Element>>();
	
	// scenarios that reference requirements with <<UseToVerify>> relation
	private HashMap<Element,HashSet<Element>> scenarioToReq = new HashMap<Element,HashSet<Element>>();

	// scenarios that reference requirements with <<DoNotUseToVerify>> relation
	private HashMap<Element,HashSet<Element>> scenarioWithNegativeRelationsToReq = new HashMap<Element,HashSet<Element>>();
	
	// requirements mapped to scenarios that reference it with a <<UseToVerify>> relation
	private HashMap<Element,HashSet<Element>> reqToScenarios = new HashMap<Element,HashSet<Element>>();
	
	// additional models that should always be instantiated
	private HashSet<Element> alwaysInclude = new HashSet<Element>();

	// models to their additional models
	private HashMap<Element,HashSet<Element>> modelToItsAdditionalModels = new HashMap<Element,HashSet<Element>>();

	// selected requirements that were provided for finding matching test cases.
	private HashSet<Element> selectedReq = null;

	// UML root model
	private Model umlRoolModel = null;
	
	
	// Constructors
	public VerificationScenariosCollector(){}
	
	public VerificationScenariosCollector(Model model){
		this.umlRoolModel = model;
	}
	
	public boolean collectScenariosFromModel(Boolean sortData){
		// clear the list in order to enable multiple calls of this method in the the same object
		this.allScenarios.clear();
		this.allRequirements.clear();
		this.alwaysInclude.clear();
		this.modelToItsAdditionalModels.clear();
		
		if ( umlRoolModel != null ) {
			VerificationDataCollector ec = new VerificationDataCollector(umlRoolModel);
			
			this.allScenarios.addAll(ec.getAllScenarios());
			this.alwaysInclude.addAll(ec.getAlwaysInclude());
			this.modelToItsAdditionalModels.putAll(ec.getModelToItsRequiredModels());
			
			this.allRequirements.addAll(ec.getAllRequirements());
			
			// sort data (i.e. fill other sets and maps)
			if (sortData) { sortData();}
			
			return true; // successful execution
		}
		
		return false;
	}
	
	public boolean collectScenariosFromPackage(Package rootPackage, boolean sortData){
		// clear the list in order to enable multiple calls of this method in the the same object
		this.allScenarios.clear();
		this.alwaysInclude.clear();
		this.modelToItsAdditionalModels.clear();

		if ( rootPackage != null ) {
			VerificationDataCollector ec = new VerificationDataCollector((EObject) rootPackage);

			this.allScenarios.addAll(ec.getAllScenarios());
			this.alwaysInclude.addAll(ec.getAlwaysInclude());
			this.modelToItsAdditionalModels.putAll(ec.getModelToItsRequiredModels());
			
			this.allRequirements.addAll(ec.getAllRequirements());
			
			// sort data (i.e. fill other sets and maps)
			if (sortData) { sortData();}
			
			return true; // successful execution
		}
		
		return false;
	}
	
	private void sortData(){
		// clear all data
		matchedScenarios.clear();
		scenarioToReq.clear();
		scenarioWithNegativeRelationsToReq.clear();
		reqToScenarios.clear();
		
		
		for (Element testScenario : allScenarios) {
			// add the owner to map
			addToPkgToTSMap(testScenario.getOwner(), testScenario);
			
			// // collect all requirements that are referenced by <<UseToVerify>> relation
//			HashSet<Element> linkedReqList = collectRequirementsForTestCase(testScenario);
			HashSet<Element> linkedReqList = collectRequirementsForScenario(testScenario, Constants.stereotypeQName_UsedToVerify);

			// fill data
			if (linkedReqList.size() > 0) {
				
				// test scenarios to requirements mapping
				scenarioToReq.put(testScenario, linkedReqList);

				// requirement to test scenarios mapping
				for (Element req : linkedReqList) {
					addToReqToTSMap(req, testScenario);
				}

				// collect matched test scenarios
				HashSet<Element> selectedRequirementsClasses = new HashSet<Element>();
				if (selectedReq != null && selectedReq.size() > 0) {
					for (Element element : selectedReq) {
						if (element instanceof Property) {
							Type reqClass = ((Property)element).getType();
							if (reqClass.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
								selectedRequirementsClasses.add(reqClass);
							}
						}
						else if (element.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
							selectedRequirementsClasses.add(element);
						}
					}
				}
				
				// matched test scenarios -> those which can be used to test all selected requirements.
				if (selectedRequirementsClasses.size() > 0 && linkedReqList.containsAll(selectedRequirementsClasses)) {
					matchedScenarios.add(testScenario);
				}
			}
			
			
			// collect all requirements that are referenced by <<DoNotUseToVerify>> relation
			HashSet<Element> reqWithNegativeRelation = collectRequirementsForScenario(testScenario, Constants.stereotypeQName_DoNotUseToVerify);
			if (reqWithNegativeRelation != null && reqWithNegativeRelation.size() > 0) {
				scenarioWithNegativeRelationsToReq.put(testScenario, reqWithNegativeRelation);
			}
			
		}
		
		
		/*
		 * remove all requirements that are referenced by <<DoNotUseToVerify>> relation to handle the case
		 * when the same requirement is referenced with both <<UseToVerify>> and <<DoNotUseToVerify>> relations
		 */
		for (Element scenario : scenarioToReq.keySet()) {
			HashSet<Element> reqWithNegativeRelations = getScenariosWithNegativeRelationsToReq().get(scenario);
			if (reqWithNegativeRelations != null) {
				scenarioToReq.get(scenario).removeAll(reqWithNegativeRelations);
			}
		}
	}
	
	
//	public HashSet<Element> collectRequirementsForTestCase(Element testCase) {
//		
//		HashSet<Element> requirmentsFound = new HashSet<Element>();
//
//		if (testCase instanceof NamedElement) {
//			
//			// DEPRECATED
//			// collect from stereotype property
//			Stereotype s_ts = ((NamedElement)testCase).getAppliedStereotype(Constants.stereotypeQName_VerificationScenario); 
//			HashSet<Element> itemsFoundThroughStereotypeProperty = new HashSet<Element>();
//			if ( s_ts != null ) {
//				// get the list of referenced requirements
//				Object o = ((NamedElement)testCase).getValue(s_ts, Constants.propertyName_usedToVerify);
//				if (o instanceof EList) {
//					@SuppressWarnings("unchecked")
//					EList<EObject> list = (EList<EObject>) o;
//					for (EObject req : list) {
//						Element umlElement = UMLUtil.getBaseElement(req);
//						if (umlElement instanceof Class && ((Class)umlElement).getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
//							itemsFoundThroughStereotypeProperty.add(umlElement);
////							System.err.println(((NamedElement) umlElement).getQualifiedName() );
//						}
//						// collect requirements from referenced elements
//						// NOTE: no deep search is performed, only the directly linked requirements are collected.
//						else {
//							if (umlElement instanceof NamedElement) {
//								for (Dependency dependency : ((NamedElement)umlElement).getClientDependencies()) {
//									for (Element target : dependency.getTargets()) {
//										if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
//											itemsFoundThroughStereotypeProperty.add( target);
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			
//			// collect from dependencies
//			EList<Dependency> depList = ((NamedElement)testCase).getClientDependencies();
//			HashSet<Element> itemsFoundThroughDependencies = new HashSet<Element>();
//			for (Dependency dependency : depList) {
//				
//				// Check if the dependency has a stereotype <<UsedToVerify>>
//				if (dependency.getAppliedStereotype(Constants.stereotypeQName_UsedToVerify) != null) {
//				
//					for (Element target : dependency.getTargets()) {
//						if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
//							itemsFoundThroughDependencies.add( (Class) target);
//						}
//						
////						// TODO: what is that (see below) for?
////						// collect requirements from referenced elements
////						// NOTE: no deep search is performed, only the directly linked requirements are collected.
////						else {
////							if (target instanceof NamedElement) {
////								for (Dependency targetDependency : ((NamedElement)target).getClientDependencies()) {
////									for (Element targetDependencyTarget : targetDependency.getTargets()) {
////										if (targetDependencyTarget.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
////											itemsFoundThroughDependencies.add( targetDependencyTarget);
////										}
////									}
////								}
////							}
////						}
//					}
//				}
//			}
//			
//			// merge all items found
//			requirmentsFound.addAll(itemsFoundThroughStereotypeProperty);
//			requirmentsFound.addAll(itemsFoundThroughDependencies);
//		}
//		return requirmentsFound;		
//	}
	
	
	
	public HashSet<Element> collectRequirementsForScenario(Element scenario, String dependencyStereotypeQName) {
		
		HashSet<Element> requirmentsFound = new HashSet<Element>();

		if (scenario instanceof NamedElement) {
			
			// collect from dependencies
			EList<Dependency> depList = ((NamedElement)scenario).getClientDependencies();
			HashSet<Element> itemsFoundThroughDependencies = new HashSet<Element>();
			for (Dependency dependency : depList) {
				
				// Check if the dependency has the specified stereotype
				if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null) {
				
					for (Element target : dependency.getTargets()) {
						// check if it is a requirement
						if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
							itemsFoundThroughDependencies.add( (Class) target);
						}
					}
				}
			}
			requirmentsFound.addAll(itemsFoundThroughDependencies);
		}
		return requirmentsFound;		
	}
	
	
	
	private void addToPkgToTSMap(Element key, Element item){
		HashSet<Element> set = pkgToScenarios.get(key);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			pkgToScenarios.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			pkgToScenarios.put(key, updatedSet);
		}
	}
	
	private void addToReqToTSMap(Element key, Element item){
		HashSet<Element> set = reqToScenarios.get(key);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			reqToScenarios.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			reqToScenarios.put(key, updatedSet);
		}
	}
	
	// GETTER/SETTER ############################################################

	public Model getUmlRoolModel() {
		return umlRoolModel;
	}

	public void setUmlRoolModel(Model umlRoolModel) {
		this.umlRoolModel = umlRoolModel;
	}

	public HashSet<Element> getAllScenarios() {
		return allScenarios;
	}

	public HashSet<Element> getMatchedScenarios() {
		return matchedScenarios;
	}

	public HashSet<Element> getLinkedScenarios() {
		HashSet<Element> linkedTS = new HashSet<Element>();
		for (Element element : scenarioToReq.keySet()) {
			linkedTS.add(element);
		}
		return linkedTS;
	}

	public HashMap<Element,HashSet<Element>> getScenarioToReq() {
		return scenarioToReq;
	}

	public HashMap<Element,HashSet<Element>> getReqToScenarios() {
		return reqToScenarios;
	}

	public void setSelectedReq(HashSet<Element> selectedReq) {
		this.selectedReq = selectedReq;
	}

	public HashSet<Element> getSelectedReq() {
		return selectedReq;
	}

	public HashMap<Element,HashSet<Element>> getPkgToScenarios() {
		return pkgToScenarios;
	}
	
	public HashSet<Element> getAlwaysInclude() {
		return alwaysInclude;
	}
	
	public HashMap<Element, HashSet<Element>> getModelToItsAdditionalModels() {
		return modelToItsAdditionalModels;
	}

	public HashSet<Element> getAllRequirements() {
		return allRequirements;
	}

	public HashMap<Element,HashSet<Element>> getScenariosWithNegativeRelationsToReq() {
		return scenarioWithNegativeRelationsToReq;
	}
	
}
