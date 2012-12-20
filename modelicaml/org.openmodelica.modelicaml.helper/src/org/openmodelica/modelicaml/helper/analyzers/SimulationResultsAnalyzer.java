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
package org.openmodelica.modelicaml.helper.analyzers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.openmodelica.modelicaml.simulation.simresults.IResultsReader;
import org.openmodelica.modelicaml.simulation.simresults.ReadMatlab4;

public class SimulationResultsAnalyzer {

	private GeneratedModelsData gmd;
	private HashMap<Element,String> simulationResultsFile;

	private HashSet<Element> notSimulatedModels;
	private HashSet<Element> simulatedModels;
	private IProgressMonitor monitor;

	public SimulationResultsAnalyzer(GeneratedModelsData gmd, IProgressMonitor monitor){
		
		this.gmd = gmd;
		this.simulationResultsFile = gmd.getSimulationResultsFile();
		this.notSimulatedModels = gmd.getNotSimulatedModels();
		this.simulatedModels = gmd.getSimulatedModels();
		
		this.monitor = monitor;
		
	}

	private void setMonitorTaskName(String taskName){
		if (monitor != null) {
			monitor.setTaskName("Analyzing results ...");
		}
	}
	
	public void analyze(){
		
		setMonitorTaskName("Analyzing results ...");
		
		//set the simulation failed list
		gmd.setNotSimulatedModels(getNotSimulatedModels());
		
		for (Element model : gmd.getGeneratedModels()) {
			
			HashSet<TreeObject> requirements = gmd.getRequirements(model);
			HashSet<TreeObject> scenarios = gmd.getScenarios(model);
			
			String resultsFile = simulationResultsFile.get(model);
			if (resultsFile != null) {
				try {
					
					setMonitorTaskName("Reading file: " + resultsFile);
					
					IResultsReader reader = new ReadMatlab4(resultsFile);
					for (TreeObject requirement : requirements) {

						setMonitorTaskName("Analyzing results for: " + ModelicaMLServices.getName(model) + " -> " + requirement.getDotPath());

						String statusDotPath = requirement.getDotPath().trim() + "." + gmd.requirementStatusPropertyName;
						List<String> vars = reader.getNames();
						
						if (vars.contains(statusDotPath)) {
							
							double[] values = reader.getValues(statusDotPath);
							
							String valueForViolated = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", "valueForViolated", "2", null);
							String valueForNotViolated = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", "valueForNotViolated", "1", null);
							String valueForNotEvaluated = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", "valueForNotEvaluated", "0", null);
							
							boolean violated = checkHadAtLeastOnceValue(values, valueForViolated);
							boolean notViolated = checkHadAtLeastOnceValue(values, valueForNotViolated);
							boolean evaluated = notViolated || violated;
									
							// if requirement was evaluated -> positive relation
							if (evaluated) {
								gmd.addToEvaluatedRequirements(gmd.getModelToTreeItemKeyString(model, statusDotPath));
								
								// add only new dependencies
								for (TreeObject scenario : scenarios) {

									// new POSITIVE relation
									if (gmd.isNewRelation(scenario, requirement, Constants.stereotypeQName_UseToVerify)) {
										gmd.addToPositiveRelations(scenario, requirement);

										// add to new relations between scenario UML element and requirement UML Element
										gmd.addToMapList(gmd.getScenarioWithNewPositiveRelationsToRequirements(), scenario.getComponentType(), requirement.getComponentType());
									}
									
									// add to scenario to requirement evaluation data
									if (violated) {
										// add to scenario to violated requirements map
										gmd.addToMapList(gmd.getScenarioToViolatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
									}
									else if (notViolated) {
										// add to scenario to NOT violated requirements map
										gmd.addToMapList(gmd.getScenarioToNotViolatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
										
									}

									// add to scenario to evaluated requirements map
									gmd.addToMapList(gmd.getScenarioToEvaluatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
								}
							}
							
							// if requirement was not evaluated -> negative relation
							else { 
								
								gmd.addToNotEvaluatedRequirements(gmd.getModelToTreeItemKeyString(model, statusDotPath));
								
								// add only new dependencies
								for (TreeObject scenario : scenarios) {
									
									// new NEGATIVE relation
									if (gmd.isNewRelation(scenario, requirement, Constants.stereotypeQName_DoNotUseToVerify)) {
										gmd.addToNegativeRelations(scenario, requirement);
										
										// add to new relations between scenario UML element and requirement UML Element
										gmd.addToMapList(gmd.getScenarioWithNewNegativeRelationsToRequirements(), scenario.getComponentType(), requirement.getComponentType());
									}
									
									// add to scenario to NOT evaluated requirements map
									gmd.addToMapList(gmd.getScenarioToNotEvaluatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
								}
							}
							
							// add to violated simulation model to requirement list
							if (violated) {
								gmd.addToViolatedRequirements(gmd.getModelToTreeItemKeyString(model, statusDotPath));
							}
							else if (notViolated) {
								gmd.addToNotViolatedRequirements(gmd.getModelToTreeItemKeyString(model, statusDotPath));
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	private static Boolean checkHadAtLeastOnceValue(double[] values, String value){
		/*
		 * We assume that it never had the value until we prove the opposite.
		 */
		Double valueToCheck = Double.valueOf(value);
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if( valueToCheck.equals(d) ){
				return true;
			}
		}
		return false;
	}
	
	// getter and setter 
	public GeneratedModelsData getGmd() {
		return gmd;
	}

	public void setGmd(GeneratedModelsData gmd) {
		this.gmd = gmd;
	}

	public HashSet<Element> getNotSimulatedModels() {
		return notSimulatedModels;
	}

	public void setNotSimulatedModels(HashSet<Element> notSimulatedModels) {
		this.notSimulatedModels = notSimulatedModels;
	}

	public HashSet<Element> getSimulatedModels() {
		return simulatedModels;
	}

	public void setSimulatedModels(HashSet<Element> simulatedModels) {
		this.simulatedModels = simulatedModels;
	}
}
