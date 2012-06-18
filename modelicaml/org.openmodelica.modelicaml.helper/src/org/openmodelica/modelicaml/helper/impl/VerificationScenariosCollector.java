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
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.openmodelica.modelicaml.common.constants.Constants;

public class VerificationScenariosCollector {
	
	// all scenarios that are found in the top-level model
	private HashSet<Element> allTS = new HashSet<Element>();

	// all requirements that are found
	private HashSet<Element> allRequirements = new HashSet<Element>();
	
	// test scenarios that can be used to test selected requirements
	private HashSet<Element> matchedTS = new HashSet<Element>();

	// package (or any element that is the direct owner of the test scenario) and all test scenarios found in it
	private HashMap<Element,HashSet<Element>> pkgToTS = new HashMap<Element,HashSet<Element>>();
	
	// test scenarios mapped to requirements
	private HashMap<Element,HashSet<Element>> tsToReq = new HashMap<Element,HashSet<Element>>();

	// requirements mapped to test scenarios 
	private HashMap<Element,HashSet<Element>> reqToTS = new HashMap<Element,HashSet<Element>>();
	
	// additional models that should always be instantiated
	private HashSet<Element> alwaysInclude = new HashSet<Element>();

	// models to their additional models
	private HashMap<Element,HashSet<Element>> modelToItsRequiredModels = new HashMap<Element,HashSet<Element>>();

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
		this.allTS.clear();
		this.allRequirements.clear();
		this.alwaysInclude.clear();
		this.modelToItsRequiredModels.clear();
		
		if ( umlRoolModel != null ) {
			VerificationScenariosDataCollector ec = new VerificationScenariosDataCollector(umlRoolModel);
			
			this.allTS.addAll(ec.getAllScenarios());
			this.alwaysInclude.addAll(ec.getAlwaysInclude());
			this.modelToItsRequiredModels.putAll(ec.getModelToItsRequiredModels());
			
			this.allRequirements.addAll(ec.getAllRequirements());
			
			// sort data (i.e. fill other sets and maps)
			if (sortData) { sortData();}
			
			return true; // successful execution
		}
		
		return false;
	}
	
	public boolean collectTestCasesFromPackage(Package rootPackage, boolean sortData){
		// clear the list in order to enable multiple calls of this method in the the same object
		this.allTS.clear();
		this.alwaysInclude.clear();
		this.modelToItsRequiredModels.clear();

		if ( rootPackage != null ) {
//			ElementsCollector ec = new ElementsCollector();
//			ec.collectElementsFromModel((EObject) rootPackage, Constants.stereotypeQName_TestScenario);
			VerificationScenariosDataCollector ec = new VerificationScenariosDataCollector((EObject) rootPackage);

//			this.allTS.addAll(ec.getElements());
			this.allTS.addAll(ec.getAllScenarios());
			this.alwaysInclude.addAll(ec.getAlwaysInclude());
			this.modelToItsRequiredModels.putAll(ec.getModelToItsRequiredModels());
			
			// sort data (i.e. fill other sets and maps)
			if (sortData) { sortData();}
			
			return true; // successful execution
		}
		
		return false;
	}
	
	private void sortData(){
		// clear all data
		matchedTS.clear();
		tsToReq.clear();
		reqToTS.clear();
		
		for (Element testScenario : allTS) {
			// add the owner to map
			addToPkgToTSMap(testScenario.getOwner(), testScenario);
			
			// get referenced requirements
			HashSet<Element> linkedReqList = collectRequirementsForTestCase(testScenario);
			
			// fill data
			if (collectRequirementsForTestCase(testScenario).size() > 0) {
				
				// test scenarios to requirements mapping
				tsToReq.put(testScenario, linkedReqList);

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
					matchedTS.add(testScenario);
				}
			}
		}
	}
	
	
	public HashSet<Element> collectRequirementsForTestCase(Element testCase) {
		
		HashSet<Element> requirmentsFound = new HashSet<Element>();

		if (testCase instanceof NamedElement) {
			
			// DEPRECATED
			// collect from stereotype property
			Stereotype s_ts = ((NamedElement)testCase).getAppliedStereotype(Constants.stereotypeQName_VerificationScenario); 
			HashSet<Element> itemsFoundThroughStereotypeProperty = new HashSet<Element>();
			if ( s_ts != null ) {
				// get the list of referenced requirements
				Object o = ((NamedElement)testCase).getValue(s_ts, Constants.propertyName_usedToVerify);
				if (o instanceof EList) {
					@SuppressWarnings("unchecked")
					EList<EObject> list = (EList<EObject>) o;
					for (EObject req : list) {
						Element umlElement = UMLUtil.getBaseElement(req);
						if (umlElement instanceof Class && ((Class)umlElement).getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
							itemsFoundThroughStereotypeProperty.add(umlElement);
//							System.err.println(((NamedElement) umlElement).getQualifiedName() );
						}
						// collect requirements from referenced elements
						// NOTE: no deep search is performed, only the directly linked requirements are collected.
						else {
							if (umlElement instanceof NamedElement) {
								for (Dependency dependency : ((NamedElement)umlElement).getClientDependencies()) {
									for (Element target : dependency.getTargets()) {
										if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
											itemsFoundThroughStereotypeProperty.add( target);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// collect from dependencies
			EList<Dependency> depList = ((NamedElement)testCase).getClientDependencies();
			HashSet<Element> itemsFoundThroughDependencies = new HashSet<Element>();
			for (Dependency dependency : depList) {
				
				// Check if the dependency has a stereotype <<UsedToVerify>>
				if (dependency.getAppliedStereotype(Constants.stereotypeQName_UsedToVerify) != null) {
				
					for (Element target : dependency.getTargets()) {
						if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
							itemsFoundThroughDependencies.add( (Class) target);
						}
						
//						// TODO: what is that (see below) for?
//						// collect requirements from referenced elements
//						// NOTE: no deep search is performed, only the directly linked requirements are collected.
//						else {
//							if (target instanceof NamedElement) {
//								for (Dependency targetDependency : ((NamedElement)target).getClientDependencies()) {
//									for (Element targetDependencyTarget : targetDependency.getTargets()) {
//										if (targetDependencyTarget.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
//											itemsFoundThroughDependencies.add( targetDependencyTarget);
//										}
//									}
//								}
//							}
//						}
					}
				}
			}
			
			// merge all items found
			requirmentsFound.addAll(itemsFoundThroughStereotypeProperty);
			requirmentsFound.addAll(itemsFoundThroughDependencies);
		}
		return requirmentsFound;		
	}
	
	
	
	private void addToPkgToTSMap(Element key, Element item){
		HashSet<Element> set = pkgToTS.get(key);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			pkgToTS.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			pkgToTS.put(key, updatedSet);
		}
	}
	
	private void addToReqToTSMap(Element key, Element item){
		HashSet<Element> set = reqToTS.get(key);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			reqToTS.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			reqToTS.put(key, updatedSet);
		}
	}
	
	// GETTER/SETTER ############################################################

	public Model getUmlRoolModel() {
		return umlRoolModel;
	}
	public void setUmlRoolModel(Model umlRoolModel) {
		this.umlRoolModel = umlRoolModel;
	}

	
	public HashSet<Element> getAllTS() {
		return allTS;
	}

	public void setAllTS(HashSet<Element> allTS) {
		this.allTS = allTS;
	}
	
	public void setMatchedTS(HashSet<Element> matchedTS) {
		this.matchedTS = matchedTS;
	}

	public HashSet<Element> getMatchedTS() {
		return matchedTS;
	}

	public HashSet<Element> getLinkedTS() {
		HashSet<Element> linkedTS = new HashSet<Element>();
		for (Element element : tsToReq.keySet()) {
			linkedTS.add(element);
		}
		return linkedTS;
	}

	public HashMap<Element,HashSet<Element>> getTsToReq() {
		return tsToReq;
	}


	public HashMap<Element,HashSet<Element>> getReqToTS() {
		return reqToTS;
	}

	public void setSelectedReq(HashSet<Element> selectedReq) {
		this.selectedReq = selectedReq;
	}

	public HashSet<Element> getSelectedReq() {
		return selectedReq;
	}

	public void setPkgToTS(HashMap<Element,HashSet<Element>> pkgToTS) {
		this.pkgToTS = pkgToTS;
	}

	public HashMap<Element,HashSet<Element>> getPkgToTS() {
		return pkgToTS;
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
	
}
