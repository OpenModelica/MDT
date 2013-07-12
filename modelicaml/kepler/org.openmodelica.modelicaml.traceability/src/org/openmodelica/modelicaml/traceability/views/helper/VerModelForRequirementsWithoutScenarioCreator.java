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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.traceability.views.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.datacollection.VerificationDataCollector;
import org.openmodelica.modelicaml.helper.datacollection.VerificationScenariosCollector;
import org.openmodelica.modelicaml.helper.generators.InstantiatorRequirements;
import org.openmodelica.modelicaml.helper.generators.CreatorValueBinding;
import org.openmodelica.modelicaml.helper.generators.CreatorVerificationVerdictElements;

public class VerModelForRequirementsWithoutScenarioCreator {
	
	// The selected system design model 
	private NamedElement systemModel;
	
	// Any models, e.g. requirements, that should be instantiated with the system model
	private List<Element> modelsToBeInstantiated = new ArrayList<Element>();
	
	// Only the requirements
	private List<Element> requirementsToBeInstantiated = new ArrayList<Element>();

	// Package within the models should be created
	private Package targetPackage;
	private Package valueBindingsPackage;

	private ModelComposer modelComposer;
	
//	private HashMap<Element, org.openmodelica.modelicaml.common.instantiation.TreeParent> preparedModelInstantiations = new HashMap<Element, org.openmodelica.modelicaml.common.instantiation.TreeParent>();

	
	// internal log
	private String log;
	
	public VerModelForRequirementsWithoutScenarioCreator(
			NamedElement sourceModel,
			List<Element> modelsToBeInstantiated, 
			Package targetPackage,
			Element umlRoolModel,
			HashMap<Element, ClassInstantiation> preparedModelInstantiations,
			VerificationScenariosCollector vsc){

		modelComposer = new ModelComposer(sourceModel, modelsToBeInstantiated, targetPackage, umlRoolModel, preparedModelInstantiations, vsc);

		this.systemModel = sourceModel;
		this.modelsToBeInstantiated.addAll(modelsToBeInstantiated);
		// TODO: what to do if there are not only requirements?
		for (Element element : modelsToBeInstantiated) {
			if (element instanceof Class && element.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
				this.requirementsToBeInstantiated.add(element);
			}
		}
		this.targetPackage = targetPackage;
		initializeLog();
	}

	private void addToLog(String msg){
		this.log = this.log + msg + "\n";
	}
	
	public EObject createModel(){

		VerificationDataCollector collector = new VerificationDataCollector(systemModel.getModel());
		
		String pkgName = Constants.simModelsPackageNamePrefix + ((NamedElement)systemModel).getName();
//		String postFix = ModelicaMLServices.getNamePostFix((Package)targetPackage, pkgName);
		String postFix = "_" + ModelicaMLServices.getTimeStamp();
		PackageableElement simulationModelsPackage = ((Package)targetPackage).createPackagedElement(pkgName + postFix,UMLPackage.Literals.PACKAGE);

		// create the verification (simulation) model class
		String simulationModelName = "VeM: Requirements, No Scenario";
		Class simulationModel = ((Package)simulationModelsPackage).createOwnedClass(simulationModelName, false);
		
		Stereotype s_model = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Model);
		Stereotype s_simulation = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Simulation);
		Stereotype s_test = simulationModel.getApplicableStereotype(Constants.stereotypeQName_VerificationModel);
		if (s_model != null && s_simulation != null) {
			simulationModel.applyStereotype(s_model);
			simulationModel.applyStereotype(s_simulation);
			simulationModel.applyStereotype(s_test);
		}
		else {
			String msg = "ERROR: Cannot apply the stereotype 'Model' and 'Simulation' to '" 
				+ simulationModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
			addToLog(msg);
		}


		//************************************************************************************
		// add system model property
		Element systemModel = this.systemModel;
		Property p_systemModel = simulationModel.createOwnedAttribute(
				Constants.systemModelPropertyNamePrefix 
				+ StringUtls.replaceSpecChar(((NamedElement)this.systemModel).getName().toLowerCase()), 
				(Type)systemModel);
		Stereotype s_component = p_systemModel.getApplicableStereotype(Constants.stereotypeQName_Component);
		if (s_component != null) {
			p_systemModel.applyStereotype(s_component);
		}
		else {
			String msg = "ERROR: Cannot apply the stereotype 'Component' to '" 
						+ p_systemModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
			addToLog(msg);
		}

		/*
		 * Add additional models that are required by the system model.
		 * Hereby, prune all that are not used, i.e. those that don't have at least
		 * one provider used in the combination.
		 */
		
		createAdditionalModels(simulationModel, modelComposer.getAdditionallModels(this.systemModel, true), 
				Constants.systemModelPropertyNamePrefix + Constants.additionalModelPrefix);
		
		
		//************************************************************************************
		// add requirements
		InstantiatorRequirements ri = new InstantiatorRequirements();
		HashSet<Element> reqList = new HashSet<Element>();
		reqList.addAll(this.requirementsToBeInstantiated);
		List<Element> requirements_sorted = ModelicaMLServices.getSortedByName(reqList);
		
		for (Element requirement : requirements_sorted) {
			if (requirement instanceof Classifier) {
			
				// get the number of required instantiations
				// NOTE: we have collected mediators and there is no need to try to re collect them if the list is empty.
				int requiredNumberOfInstantions = ri.getMaxNumberOfProviders(simulationModel, (Class) requirement, collector.getAllMediators(), false);
				String requiredNumberOfInstantionsString = "";
				
				for (int i = 0; i < requiredNumberOfInstantions; i++) {
				
					if (requiredNumberOfInstantions > 1) {
						requiredNumberOfInstantionsString = String.valueOf(i) + "_";
					}
					Property p_req = simulationModel.createOwnedAttribute(
							Constants.reqirementPropertyNamePrefix + 
							ModelicaMLServices.getRequirementID((NamedElement) requirement) + "_" + 
							requiredNumberOfInstantionsString +
							StringUtls.replaceSpecChar(((NamedElement)requirement).getName().toLowerCase()), 
							(Type) requirement);
					
					Stereotype s_reqInst = p_req.getApplicableStereotype(Constants.stereotypeQName_RequirementInstance);
					
					if (s_reqInst != null) {
						p_req.applyStereotype(s_reqInst);
					}
					else {
						String msg = "ERROR: Cannot apply the stereotype 'requirementInstantance' to '" + p_req.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
						addToLog(msg);
					}
				}
				
				/*
				 * Add additional models that are required by the requirement model.
				 * Hereby, prune all that are not used, i.e. those that don't have at least
				 * one provider used in the combination.
				 */
				createAdditionalModels(simulationModel, 
						modelComposer.getAdditionallModels(requirement, true), 
						Constants.reqirementPropertyNamePrefix
							+ ModelicaMLServices.getRequirementID((NamedElement) requirement) 
							+ "_" + Constants.additionalModelPrefix
						);
			}
		}
		
		/* 
		 * Instantiate the created simulation model class
		 * (it now contains all components)
		 */
		// NOTE: we have not collected mediators yet. However, we do not need to recollect them because the Value Bindings Creator will do it.
		ClassInstantiation ci = new ClassInstantiation((Class) simulationModel, true, false, null, false);
		ci.createTree();
		ci.collectBindingsDataFromUmlModel();
		
		// update all bindings in the created simulation model class
		CreatorValueBinding vbc = new CreatorValueBinding();
		/* Note, the updateAllBindings() is called with the last argument simulateOnly = false  
		 * so that modifications ARE created in components.
		 */
		vbc.updateAllBindings((Package)valueBindingsPackage, ci, ci.getTreeRoot(), ci.getTreeRoot(), false, true, true, false);
		
		/*
		 * Create test verdict code
		 */
		CreatorVerificationVerdictElements.createVerificationVerdictElements(simulationModel);
		
		return simulationModel;
	}
	
	
	
	private void createAdditionalModels(Class owningClass, HashSet<Element> additionalModels, String namePrefix){
		if (additionalModels != null && additionalModels.size() > 0) {
			List<Element> addtitionalModels_sorted = ModelicaMLServices.getSortedByName(additionalModels);
			
			for (int i = 0; i < addtitionalModels_sorted.size(); i++) {
				Element additionalModel= addtitionalModels_sorted.get(i);
				
				// create property
				Property p_additionalModel = owningClass.createOwnedAttribute(
						namePrefix + i + "_" + StringUtls.replaceSpecChar(((NamedElement)additionalModel).getName().toLowerCase()), 
						(Type)additionalModel);
				
				// apply stereotype
				Stereotype s_additionalModel= null;
				if (additionalModel.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null
						|| additionalModel.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null) {
					
					s_additionalModel = p_additionalModel.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);	
				}
				else {
					s_additionalModel = p_additionalModel.getApplicableStereotype(Constants.stereotypeQName_Component);
				}
				if (s_additionalModel != null) {
					p_additionalModel.applyStereotype(s_additionalModel);
				}
				else {
					String msg = "ERROR: Cannot apply the required stereotype to '" 
						+ p_additionalModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
					addToLog(msg);
				}
			}
		}
	}
	
	private void initializeLog(){
		this.log = "\n----------------------------------------------" +
		"---------------------------------------------------" +
		"--------------------------------------------------- \n" +
		"Log for:" +
			"\n   - System Model '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
			"\n";
	}
}
