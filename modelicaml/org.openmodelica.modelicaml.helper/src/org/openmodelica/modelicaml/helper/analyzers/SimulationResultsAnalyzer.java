package org.openmodelica.modelicaml.helper.analyzers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
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
	private IProgressMonitor monitor;

	public SimulationResultsAnalyzer(GeneratedModelsData gmd, 
			HashMap<Element,String> simulationResultsFile,
			HashSet<Element> notSimulatedModels,
			IProgressMonitor monitor
			){
		
		this.gmd = gmd;
		this.gmd.setSimulationResultsFile(simulationResultsFile);
		this.simulationResultsFile = simulationResultsFile;
		this.notSimulatedModels = notSimulatedModels;
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

							boolean notViolated = checkHadAtLeastOnceValue(values, "1");
							boolean violated = checkHadAtLeastOnceValue(values, "2");
							boolean evaluated = notViolated || violated;
									
							// if requirement was evaluated -> positive relation
							if (evaluated) {
								gmd.addToEvaluatedRequirements(gmd.getModelToTreeItemKeyString(model, statusDotPath));
								
								// add only new dependencies
								for (TreeObject scenario : scenarios) {

									// new POSITIVE relation
									if (gmd.isNewRelation(scenario, requirement, Constants.stereotypeQName_UsedToVerify)) {
										gmd.addToPositiveRelations(scenario, requirement);

										// add to new relations between scenario UML element and requirement UML Element
										gmd.addToMapList(gmd.getScenarioWithNewPositiveRelationsToRequirements(), scenario.getComponentType(), requirement.getComponentType());
									}
									
									// add to scenario to requirement evaluation data
									if (violated) {
										// add to scenario to violated requirements map
										gmd.addToMapList(gmd.getScenarioToViolatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
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
}
