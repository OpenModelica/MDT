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
import org.openmodelica.modelicaml.common.services.ElementsCollector;

public class TestScenariosCollector {
	
	// all test scenarios that are found in the top-level model
	private HashSet<Element> allTS = new HashSet<Element>();

	// test scenarios that can be used to test selected requirements
	private HashSet<Element> matchedTS = new HashSet<Element>();

	// package (or any element that is the direct owner of the test scenario) and all test scenarios found in it
	private HashMap<Element,HashSet<Element>> pkgToTS = new HashMap<Element,HashSet<Element>>();
	
	// test scenarios mapped to requirements
	private HashMap<Element,HashSet<Element>> tsToReq = new HashMap<Element,HashSet<Element>>();

	// requirements mapped to test scenarios 
	private HashMap<Element,HashSet<Element>> reqToTS = new HashMap<Element,HashSet<Element>>();
	
	
	// selected requirements that were provided in order for finding matching test cases.
	private HashSet<Element> selectedReq = null;

	// UML root model
	private Model umlRoolModel = null;
	
	
	// Constructors
	public TestScenariosCollector(){}
	
	public TestScenariosCollector(Model model){
		this.umlRoolModel = model;
	}
	
	public boolean collectTestCasesFromModel(Boolean sortData){
		// clear the list in order to enable multiple calls of this method in the the same object
		this.allTS.clear();
		
		if ( umlRoolModel != null ) {
			ElementsCollector ec = new ElementsCollector();
			ec.collectElementsFromModel(umlRoolModel, Constants.stereotypeQName_TestScenario);
			
			this.allTS.addAll(ec.getElements());
			
			// sort data (i.e. fill other sets and maps)
			if (sortData) { sortData();}
			
			return true; // successful execution
		}
		
		return false;
	}
	
	public boolean collectTestCasesFromPackage(Package rootPackage, boolean sortData){
		// clear the list in order to enable multiple calls of this method in the the same object
		this.allTS.clear();
		
		if ( rootPackage != null ) {
			ElementsCollector ec = new ElementsCollector();
			ec.collectElementsFromModel((EObject) rootPackage, Constants.stereotypeQName_TestScenario);
			
			this.allTS.addAll(ec.getElements());
			
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
			
			// collect from stereotype
			Stereotype s_ts = ((NamedElement)testCase).getAppliedStereotype(Constants.stereotypeQName_TestScenario); 
			HashSet<Element> itemsFoundTroughStereotypeProperty = new HashSet<Element>();
			if ( s_ts != null ) {
				// get the list of referenced requirements
				Object o = ((NamedElement)testCase).getValue(s_ts, Constants.propertyName_usedToVerify);
				if (o instanceof EList) {
					@SuppressWarnings("unchecked")
					EList<EObject> list = (EList<EObject>) o;
					for (EObject req : list) {
						Element umlElement = UMLUtil.getBaseElement(req);
						if (umlElement instanceof Class && ((Class)umlElement).getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
							itemsFoundTroughStereotypeProperty.add(umlElement);
//							System.err.println(((NamedElement) umlElement).getQualifiedName() );
						}
						// collect requirements from referenced elements
						// NOTE: no deep search is performed, only the directly linked requirements are collected.
						else {
							if (umlElement instanceof NamedElement) {
								for (Dependency dependency : ((NamedElement)umlElement).getClientDependencies()) {
									for (Element target : dependency.getTargets()) {
										if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
											itemsFoundTroughStereotypeProperty.add( target);
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
			HashSet<Element> itemsFoundTroughDependencies = new HashSet<Element>();
			for (Dependency dependency : depList) {
				// TODO: should we check if the dependency has a stereotype <<UsedToVerify>>?
				for (Element target : dependency.getTargets()) {
					if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
						itemsFoundTroughDependencies.add( (Class) target);
					}
					// collect requirements from referenced elements
					// NOTE: no deep search is performed, only the directly linked requirements are collected.
					else {
						if (target instanceof NamedElement) {
							for (Dependency targetDependency : ((NamedElement)target).getClientDependencies()) {
								for (Element targetDependencyTarget : targetDependency.getTargets()) {
									if (targetDependencyTarget.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
										itemsFoundTroughDependencies.add( targetDependencyTarget);
									}
								}
							}
						}
					}
				}
			}
			
			// merge all items found
			requirmentsFound.addAll(itemsFoundTroughStereotypeProperty);
			requirmentsFound.addAll(itemsFoundTroughDependencies);
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

//	public void setTsToReq(HashMap<Element,HashSet<Element>> tsToReq) {
//		this.tsToReq = tsToReq;
//	}

	public HashMap<Element,HashSet<Element>> getTsToReq() {
		return tsToReq;
	}

//	public void setReqToTS(HashMap<Element,HashSet<Element>> reqToTS) {
//		this.reqToTS = reqToTS;
//	}

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
}
