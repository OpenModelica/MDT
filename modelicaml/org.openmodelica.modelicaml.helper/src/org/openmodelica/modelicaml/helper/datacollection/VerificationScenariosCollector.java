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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.helper.datacollection;

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
	
	// all mediators that are found
	private HashSet<Element> allMediators = new HashSet<Element>();
	
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
	private Element umlRoolModel = null;
	
	// the package containing the requirements to be used
	private Element requirementsPackage;
	// the package containing the scenarios to be used
	private Element scenariosPackage;
	// the package to containing the value bindings to be used
	private Element bindingsPackage;
	
	
	private VerificationDataCollector verificationDataCollector;
	
	// Constructors
	public VerificationScenariosCollector(){}
	
	public VerificationScenariosCollector(Element model){
		this.umlRoolModel = model;
	}
	
	public VerificationScenariosCollector(Element model,
			Element requirementsPackage,
			Element scenariosPackage,
			 Element bindingsPackage
			 ){
		
		this.umlRoolModel = model;
		
		this.requirementsPackage = requirementsPackage;
		this.scenariosPackage = scenariosPackage;
		this.bindingsPackage = bindingsPackage;
	}
	
	private void clearAll(){
		// clear the list in order to enable multiple calls of this method in the the same object
		this.allScenarios.clear();
		this.allRequirements.clear();
		this.allMediators.clear();
		this.alwaysInclude.clear();
		this.modelToItsAdditionalModels.clear();
	}
	
	public boolean collectScenariosFromModel(Boolean sortData){
		
		clearAll();
		
		if ( umlRoolModel != null ) {
			
			// if the packages to search on were specified -> use them
			if (requirementsPackage != null && scenariosPackage != null && bindingsPackage != null) {
				verificationDataCollector = new VerificationDataCollector(umlRoolModel, requirementsPackage, scenariosPackage, bindingsPackage);
			}
			else {
				verificationDataCollector = new VerificationDataCollector(umlRoolModel);
			}
			
			this.allScenarios.addAll(verificationDataCollector.getAllScenarios());
			this.alwaysInclude.addAll(verificationDataCollector.getAlwaysInclude());
			this.modelToItsAdditionalModels.putAll(verificationDataCollector.getModelToItsRequiredModels());
			
			this.allRequirements.addAll(verificationDataCollector.getAllRequirements());
			
			this.allMediators.addAll(verificationDataCollector.getAllMediators());
			
			// sort data (i.e. fill other sets and maps)
			if (sortData) { sortData();}
			
			return true; // successful execution
		}
		
		return false;
	}
	
	
	public boolean collectAll(Boolean sortData){
		return collectScenariosFromModel(sortData);
	}
	
	
	public boolean collectScenariosFromPackage(Package rootPackage, boolean sortData){

		clearAll();

		if ( rootPackage != null ) {
			verificationDataCollector = new VerificationDataCollector((EObject) rootPackage);

			this.allScenarios.addAll(verificationDataCollector.getAllScenarios());
			this.alwaysInclude.addAll(verificationDataCollector.getAlwaysInclude());
			this.modelToItsAdditionalModels.putAll(verificationDataCollector.getModelToItsRequiredModels());
			
			this.allRequirements.addAll(verificationDataCollector.getAllRequirements());
			
			this.allMediators.addAll(verificationDataCollector.getAllMediators());
			
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
			
			// collect all requirements that are referenced by <<UseToVerify>> relation
			HashSet<Element> linkedReqList = collectRequirementsForScenario(testScenario, Constants.stereotypeQName_UseToVerify);

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

	public Element getUmlRoolModel() {
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

	public HashSet<Element> getAllMediators() {
		return allMediators;
	}

	public void setAllMediators(HashSet<Element> allMediators) {
		this.allMediators = allMediators;
	}
	
	public VerificationDataCollector getVerificationDataCollector() {
		return verificationDataCollector;
	}

	public void setVerificationDataCollector(
			VerificationDataCollector verificationDataCollector) {
		this.verificationDataCollector = verificationDataCollector;
	}
}
