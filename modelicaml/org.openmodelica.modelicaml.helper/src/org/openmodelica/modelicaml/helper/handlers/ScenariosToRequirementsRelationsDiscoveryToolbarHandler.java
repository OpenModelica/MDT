package org.openmodelica.modelicaml.helper.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.gen.modelica.popupactions.GenerateModelicaCodeFromEntireModelicaMLModelAction;
import org.openmodelica.modelicaml.helper.dialogs.SelectScenarioToReqRelationsToCreateDialog;
import org.openmodelica.modelicaml.simulation.omc.OpenModelicaCompilerCommunication;
import org.openmodelica.modelicaml.simulation.simresults.IResultsReader;
import org.openmodelica.modelicaml.simulation.simresults.ReadMatlab4;

public class ScenariosToRequirementsRelationsDiscoveryToolbarHandler extends VeMGenaratorToobarHandler{

	private String projectPath;
		
//	private HashSet<ScenariosToRequirementsRelationsDiscoveryToolbarHandler.GeneratedModelsData> genModelsData = new HashSet<ScenariosToRequirementsRelationsDiscoveryToolbarHandler.GeneratedModelsData>();
	private String simulationResultsFolderPath;
	private boolean loadMSL = true;
//	private String omcLog;
	
	private List<String> simulationFailedList;
	private List<String> simulationSuccededList;
	
	private HashMap<Element,String> simulationResultsFile = new HashMap<Element,String>();
	
	private GeneratedModelsData gmd;
	
	private Job simulationJob;
	private JobChangeAdapter simulationJobChangeAdaptor = new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
	            if (event.getResult().isOK()) {
	            		// open dialog
	            		openReport();
	            	}
	            }
	         };
	
	@Override
	protected void openDialog() {
		super.setMode(Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY);
		super.openDialog();
	}
	
	
	private void openReport(){
 		// Use this to open a Shell in the UI thread
 		Display.getDefault().asyncExec(new Runnable() {
 			public void run() {
				// open dialog for displaying discovered relations
 				SelectScenarioToReqRelationsToCreateDialog dialog = new SelectScenarioToReqRelationsToCreateDialog(getShell(), gmd);
 				dialog.open();
 			}
 		});
 	}
	
	
	
	@Override
	protected void proceed(){
		
		boolean go = true;
		
		if (isBindingErrorsDetected()) {
			NamedElement targetPackage = (NamedElement) smg.getTargetPackage();
			go = MessageDialog.openQuestion(getShell(), "Model Generation Error", 
					"For some of the generated model no bindings could be generated, see models and markers in " +
					"\n'"+targetPackage.getName()+"'" +
					
					"\n\nIt is recommended to first correct the bindings defintion before proceeding with relations discovery. " +
					"\n\nNote, that you can store manual providers selection by adding the selected provider to 'preferred providers'." +

					"\n\nDo you want to proceed with relations discovery anyway? ");
		}
		
		if (go && !smg.isTestSimulationModelGenerationCanceled()) {

			// collect data
			gmd = new GeneratedModelsData(smg.getGeneratedPackages());
			
			// set the system model that were used for discovering realations between scenarios and requirements
			gmd.setSystemModels(smg.getSystemModels());

//			genModelsData.add(gmd);
			
			// catch project data
			setUpProjectData();
			
			// generate code
			GenerateModelicaCodeFromEntireModelicaMLModelAction cgAction = new GenerateModelicaCodeFromEntireModelicaMLModelAction();
			try {
				
				// generate code
				/*
				 * TODO: set name space for code generation in order to only generate code for the new models
				 */
				cgAction.execute(null);
				/*
				 * TODO: reset name space for later code generation
				 */
				
				// when finished start the simulation job
				cgAction.getJob().addJobChangeListener(new JobChangeAdapter() {
					public void done(IJobChangeEvent event) {
			            if (event.getResult().isOK()) {
			            	// start simulation job
			            	simulationJob = new Job("Simulating") {
								
								@Override
								protected IStatus run(IProgressMonitor monitor) {
									
									if (monitor.isCanceled()){
										return Status.CANCEL_STATUS;
									}
									
									// simulate models
									simulateModels(monitor);
									
									//post process the simulation results files and fill conclusion data 
									analyzeResults(monitor);
									
									return Status.OK_STATUS;
								}
							};
							
							simulationJob.addJobChangeListener(simulationJobChangeAdaptor);
							simulationJob.setUser(true);
							simulationJob.schedule();
			            	}
			            }
			         });
				
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void analyzeResults(IProgressMonitor monitor){
		
		monitor.setTaskName("Analyzing results ...");
		
		//set the simulation failed list
		gmd.setSimulationFailedList(simulationFailedList);
		
		for (Element model : gmd.getGeneratedModels()) {
			
			HashSet<TreeObject> requirements = gmd.getRequirements(model);
			HashSet<TreeObject> scenarios = gmd.getScenarios(model);
			
			String resultsFile = simulationResultsFile.get(model);
			if (resultsFile != null) {
				try {
					IResultsReader reader = new ReadMatlab4(resultsFile);
					for (TreeObject requirement : requirements) {
						
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
									if (gmd.isNewRelation(scenario, requirement, Constants.stereotypeQName_UsedToVerify)) {

										gmd.addToPositiveRelations(scenario, requirement);
										
										if (violated) {
											// add to scenario to violated requirements map
											gmd.addToMapList(gmd.getScenarioToViolatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
										}

										// add to scenario to evaluated requirements map
										gmd.addToMapList(gmd.getScenarioToEvaluatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
									}
								}
							}
							
							// if requirement was not evaluated -> negative relation
							else { 
								// add only new dependencies
								for (TreeObject scenario : scenarios) {
									if (gmd.isNewRelation(scenario, requirement, Constants.stereotypeQName_DoNotUseToVerify)) {
										gmd.addToNegativeRelations(scenario, requirement);
										
										// add to scenario to NOT evaluated requirements map
										gmd.addToMapList(gmd.getScenarioToNotEvaluatedRequirements(), scenario.getComponentType(), requirement.getComponentType());
									}
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
	
		
//	private boolean isNewRelation(TreeObject scenarioItem, TreeObject requirementItem, String dependencyStereotypeQName){
//		
//		Element scenario = scenarioItem.getComponentType();
//		Element requirement = requirementItem.getComponentType();
//		
//		EList<Dependency> depList = ((Class)scenario).getClientDependencies();
//		for (Dependency dependency : depList) {
//			if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null )  {
//				EList<Element> targets = dependency.getTargets();
//				if (targets.contains(requirement)) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
	
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
		
	private void simulateModels(IProgressMonitor monitor){
		
		clearLists();
		
		// create compiler communication
		OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
		simulationResultsFolderPath =  omcc.getTempDirectoryPath();
		// correct the path
		while(simulationResultsFolderPath.contains("\\")){
			simulationResultsFolderPath = simulationResultsFolderPath.replace('\\', '/');
		}

		//clear compiler
		omcc.clear();
		
		// change to temp. folder
		omcc.cd(simulationResultsFolderPath);

		// if selected, load the standard library in advance
		if (loadMSL) {
			String reply = omcc.loadModel(Constants.actionLanguageName);
			System.err.println(reply);
		}
		
		// load code into compiler
		List<String> filesToLoad = new ArrayList<String>();
		filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(projectPath + "/" + Constants.folderName_code_sync));
		filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(projectPath + "/" + Constants.folderName_code_gen));
		for (String string : filesToLoad) {
			String reply = omcc.loadFile(string);
		}
		
		// simulate each model
		for (Element genModel : gmd.getGeneratedModels()) {
			
			// stop if user canceled the simulation monitor
			if (monitor.isCanceled()){
				return;
			}
			
			// determine the qualified name of the model
			String modelQName = getModelQName((NamedElement)genModel);
			
			/*
			 * First delete old files from the tmp directory.
			 * This is necessary in case the same models should be simulated and the new simulation fails.
			 * Old files needs to be deleted so that the simulation results will not get confused with older simulations.
			 */
			monitor.beginTask("Deleting old files for '" + modelQName + "'", 100);
			List<String> filesToBeDeleted = new ArrayList<String>();
			filesToBeDeleted.add(modelQName + "_res.mat");
			IStatus status = ModelicaMLServices.deleteFiles(filesToBeDeleted, monitor, "Deleting old file: ");

			// simulate
			monitor.subTask("Simulating '" + modelQName + "'");
			simulateModel((NamedElement) genModel, omcc);
			
			// Check if the simulation results are available
			simulationFailedList = new ArrayList<String>();
			simulationSuccededList = new ArrayList<String>();
				
			IFileSystem fileSystem = EFS.getLocalFileSystem();
			IFileStore newMatFile = fileSystem.getStore(java.net.URI.create("file:/" + simulationResultsFolderPath + "/" + modelQName + "_res.mat"));
			IFileInfo newMatFileInfo = newMatFile.fetchInfo();
			
			if (newMatFileInfo.exists()) {
				//Indicate that OMC generated the files, i.e. the simulation was ok
				simulationSuccededList.add(modelQName);
//				simulationResultsFile.add(simulationResultsFolderPath + "/" + newMatFileInfo.toString());
				String resultsFilePath = simulationResultsFolderPath + "/" + newMatFileInfo.toString();
				simulationResultsFile.put(genModel, resultsFilePath);
			}
			else {
				simulationFailedList.add(modelQName);
			}	
		}
	}
	
		
	private String simulateModel(NamedElement model, OpenModelicaCompilerCommunication omcc){
		String result = "";
		
		if (model.getAppliedStereotype(Constants.stereotypeQName_Simulation) != null) {
			
			String startTime="", stopTime="", numberOfIntervals="", tolerance="", solver="", outputFormat="", variableFilter = "";
			
			Stereotype sSimulation = model.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			if (sSimulation != null) {
				startTime = (String) model.getValue(sSimulation, Constants.propertyName_startTime);
				stopTime = (String) model.getValue(sSimulation, Constants.propertyName_stopTime);
				numberOfIntervals = (String) model.getValue(sSimulation, Constants.propertyName_numberOfIntervals);
				tolerance = (String) model.getValue(sSimulation, Constants.propertyName_tolerance);
			}
			
			// set default simulation settings
			if (numberOfIntervals==null|| numberOfIntervals.trim().length() == 0) {
				numberOfIntervals = "500";
			}
			if (tolerance == null || tolerance.trim().length() == 0 ) {
				tolerance = "0.000001";
			}
			if (solver == null || solver.trim().length() == 0 ) {
				solver = "dassl";
			}
			if (outputFormat == null || outputFormat.trim().length()==0 ) {
				outputFormat = "mat";
			}
			
			//Simulate model
			HashSet<TreeObject> requirementItems = gmd.getRequirements(model);
			variableFilter = getRequirementVariableFilter(requirementItems); // no filter, i.e., all variables should be recorded.
			
			String omcReturnString = omcc.simulate(
						getModelQName(model), 
						startTime, 
						stopTime, 
						numberOfIntervals, 
						tolerance, 
						solver, 
						outputFormat,
						variableFilter
						);
			
			System.err.println(omcReturnString);
			
			if(omcReturnString.contains("{\"\",\"\"}") || omcReturnString.contains("Error")){
				String errorString = omcc.getErrorString();
				result = result + "\n" + "buildModel: " + omcReturnString  + "\nErrorString: " + errorString;
			}
		}
		return result;
	}
	
	private String getModelQName(NamedElement model) {
		return StringUtls.replaceSpecCharExceptThis( model.getQualifiedName(), "::").replaceAll("::", ".");
	}
	
	
	private String getRequirementVariableFilter(HashSet<TreeObject> requirementItems){
		String requirementVariableFilter = "";
		for (TreeObject treeObject : requirementItems) {
			if (!treeObject.getDotPath().trim().equals("")) {
				requirementVariableFilter = requirementVariableFilter + "|" + treeObject.getDotPath() + "." + gmd.requirementStatusPropertyName;
			}
		}
		
		// remove the first "|"
		if (requirementVariableFilter.trim().startsWith("|")) {
			String correctedPath = requirementVariableFilter.replaceFirst("\\|", "");
			return correctedPath;
		}
		return requirementVariableFilter;
	}
	
	
//	private String getSimulationResultsPath(NamedElement model){
//		return simulationResultsFolderPath + "/" + getModelQName((NamedElement) model) + "_res.mat";
//	}

	
	private void setUpProjectData(){
		
		// get UML model data
		UmlModel umlModel = UmlUtils.getUmlModel();
//		String umlModelFileURI = umlModel.getResourceURI().toString();

		// get project data
		String projectName = umlModel.getResource().getURI().segment(1);
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject iProject = root.getProject(projectName);
		
		projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
	}
	
	
	private void clearLists(){
		if ( simulationFailedList != null) {
			simulationFailedList.clear();	
		}
		if ( simulationSuccededList != null) {
			simulationSuccededList.clear();
		}
		if ( simulationResultsFile != null) {
			simulationResultsFile.clear();
		}
	}
	
	
	
	
	public class GeneratedModelsData{
		
		private HashSet<Element> systemModels;
		private HashSet<Element> generatedPackages;
		private HashSet<Element> generatedModels;
		
		private HashMap<Element, HashSet<TreeObject>> modelToScenarios = new HashMap<Element, HashSet<TreeObject>>();
		private HashMap<Element, HashSet<TreeObject>> modelToRequirements = new HashMap<Element, HashSet<TreeObject>>();
		
		// model name + delimiter + variable dot path
		private HashSet<String> evaluatedRequirements = new HashSet<String>();
		private HashSet<String> violatedRequirements = new HashSet<String>();
		private HashSet<String> notViolatedRequirements = new HashSet<String>();

		// list of model qualified names that could not be simulated (determined by the fact if there is a simulation results file or not)
		private List<String> simulationFailedList;
		
		// maps with scenarios and their requirements
		private HashMap<Element, HashSet<Element>> scenarioToEvaluatedRequirements = new HashMap<Element, HashSet<Element>>();
		private HashMap<Element, HashSet<Element>> scenarioToViolatedRequirements = new HashMap<Element, HashSet<Element>>();
		private HashMap<Element, HashSet<Element>> scenarioToNotEvaluatedRequirements = new HashMap<Element, HashSet<Element>>();

		// positive scenario to requirements relations, i.e., scenario was used to stimulate the system mode and lead to an evaluation of the requirements
		private HashMap<TreeObject, HashSet<TreeObject>> newPositiveRelations = new HashMap<TreeObject, HashSet<TreeObject>>();

		// negative scenario to requirements relations, i.e., scenario was used to stimulate the system mode and did NOT lead to an evaluation of the requirements
		private HashMap<TreeObject, HashSet<TreeObject>> newNegativeRelations = new HashMap<TreeObject, HashSet<TreeObject>>();
		
		
		public final String requirementStatusPropertyName = "status";
		public final static String delimiter = "@-->";
		
		
		public GeneratedModelsData(HashSet<Element> generatedPackages) {
			this.generatedPackages = generatedPackages;
			this.generatedModels = getGenModels(this.generatedPackages);
			sortData();
		}
		
		private void sortData(){
			for (Element genModel : this.generatedModels) {
				// instantiate the model
				ClassInstantiation ci = new ClassInstantiation((Class) genModel, true);
				ci.createTree();
				TreeParent root = ci.getTreeRoot();
				
				// fill data maps
				findScenariosAndRequirements(root, genModel);
			}
		}
		
		protected HashSet<Element> getGenModels(HashSet<Element> generatedPackages){
			HashSet<Element> generatedModels = new HashSet<Element>();
			
			for (Element genPackage : generatedPackages) {
				EList<Element> ownedClasses = genPackage.getOwnedElements();
				
				for (Element element : ownedClasses) {
					if (element instanceof Class) {
						if (element.getAppliedStereotype(Constants.stereotypeQName_VerificationModel) != null) {
							generatedModels.add(element);
						}
					}
				}
			}
			
			return generatedModels;
		}
		
		
		private void findScenariosAndRequirements(TreeParent parent, Element selectedClass){			
			// check the given element
			Element parentUmlElementType = parent.getComponentType();
			fillMap(parentUmlElementType, parent, selectedClass);

			// go for the next level
			TreeObject[] children = parent.getChildren();
			for (int i = 0; i < children.length; i++) {
				TreeObject child = children[i];
				findScenariosAndRequirements((TreeParent) child, selectedClass);
			}
		}
		
		
		private void fillMap(Element treeItemComponentType, TreeObject treeItem, Element simModel){
			if (treeItemComponentType instanceof Element && treeItemComponentType.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null) {
				addToMapList(modelToScenarios, simModel, treeItem);
			}
			else if (treeItemComponentType instanceof Element && treeItemComponentType.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
				addToMapList(modelToRequirements, simModel, treeItem);
			}
		}
		
		
		// Utilities
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void addToMapList(HashMap map, Element key, TreeObject value){
			Object list = map.get(key);
			if (list instanceof HashSet) {
				((HashSet<TreeObject>)list).add(value);
				map.put(key, list);
			}
			else{
				HashSet<TreeObject> newList = new HashSet<TreeObject>();
				newList.add(value);
				map.put(key, newList);
			}
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void addToMapList(HashMap map, TreeObject key, TreeObject value){
			Object list = map.get(key);
			if (list instanceof HashSet) {
				((HashSet<TreeObject>)list).add(value);
				map.put(key, list);
			}
			else{
				HashSet<TreeObject> newList = new HashSet<TreeObject>();
				newList.add(value);
				map.put(key, newList);
			}
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void addToMapList(HashMap map, Element key, Element value){
			Object list = map.get(key);
			if (list instanceof HashSet) {
				((HashSet<Element>)list).add(value);
				map.put(key, list);
			}
			else{
				HashSet<Element> newList = new HashSet<Element>();
				newList.add(value);
				map.put(key, newList);
			}
		}
		
		public String getModelToTreeItemKeyString(Element model, String dotPath){
			if (model instanceof NamedElement) {
				return ((NamedElement)model).getQualifiedName() + delimiter + dotPath;
			}
			return "";
		}
		
		// Getters
		public HashSet<Element> getGeneratedModels() {
			return generatedModels;
		}
		
		public HashSet<TreeObject> getScenarios(Element simModel){
			return modelToScenarios.get(simModel);
		}
		
		public HashSet<Element> getAllScenarios(){
			HashSet<Element> scenarios = new HashSet<Element>();
			
			for (Element model : modelToScenarios.keySet()) {
				HashSet<TreeObject> modelScenarios = modelToScenarios.get(model);
				if (modelScenarios != null && modelScenarios.size() > 0) {
					for (TreeObject scenarioTreeObject : modelScenarios) {
						Element scenario  = scenarioTreeObject.getComponentType();
						if (scenario != null) {
							scenarios.add(scenario);
						}
					}
				}
			}
			return scenarios;
		}
		
		
		public HashSet<Element> getRequirements(Element scenario, boolean isPositiveRelation){
			
			if (isPositiveRelation) {
				
				/*
				 * Note that evaluated requirements lists contains both: Violated and not violated requirements.
				 */
				HashSet<Element> requirementsEvaluated = getScenarioToEvaluatedRequirements().get(scenario);
				
				HashSet<Element> impactedRequirementsList = new HashSet<Element>();
				if (requirementsEvaluated!= null) {
					impactedRequirementsList.addAll(requirementsEvaluated);
				}
				
				return impactedRequirementsList;
			}
			else {
				HashSet<Element> requirementsNotEvaluated = getScenarioToNotEvaluatedRequirements().get(scenario);
				HashSet<Element> notImpactedRequirementsList = new HashSet<Element>();
				if (requirementsNotEvaluated!= null) {
					notImpactedRequirementsList.addAll(requirementsNotEvaluated);
				}
				return notImpactedRequirementsList;
			}
		}

		
		public boolean isNewRelation(TreeObject scenarioItem, TreeObject requirementItem, String dependencyStereotypeQName){
			
			Element scenario = scenarioItem.getComponentType();
			Element requirement = requirementItem.getComponentType();
			
			EList<Dependency> depList = ((Class)scenario).getClientDependencies();
			for (Dependency dependency : depList) {
				if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null )  {
					EList<Element> targets = dependency.getTargets();
					if (targets.contains(requirement)) {
						return false;
					}
				}
			}
			return true;
		}
		
		public boolean isNewRelation(Element scenario, Element requirement, String dependencyStereotypeQName){
			
			EList<Dependency> depList = ((Class)scenario).getClientDependencies();
			for (Dependency dependency : depList) {
				if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null )  {
					EList<Element> targets = dependency.getTargets();
					if (targets.contains(requirement)) {
						return false;
					}
				}
			}
			return true;
		}
		
		public Dependency getExistingDependency(Element scenario, Element requirement, String dependencyStereotypeQName){
			
			EList<Dependency> depList = ((Class)scenario).getClientDependencies();
			for (Dependency dependency : depList) {
				if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null )  {
					EList<Element> targets = dependency.getTargets();
					if (targets.contains(requirement)) {
						return dependency;
					}
				}
			}
			return null;
		}

		public HashSet<TreeObject> getRequirements(Element simModel){
			return modelToRequirements.get(simModel);
		}

		public HashSet<String> getEvaluatedRequirements() {
			return evaluatedRequirements;
		}

		public void addToEvaluatedRequirements(String string) {
			this.evaluatedRequirements.add(string);
		}

		public HashSet<String> getViolatedRequirements() {
			return violatedRequirements;
		}

		public void addToViolatedRequirements(String string) {
			this.violatedRequirements.add(string);
		}

		public HashMap<TreeObject, HashSet<TreeObject>> getNewPositiveRelations() {
			return newPositiveRelations;
		}
		
		
		public int getNewRelationsCount(boolean isPositive) {
			int count = 0;
			for (Element scenario : getAllScenarios()) {
				HashSet<Element> requirements = getRequirements(scenario, isPositive);
				if (requirements != null) {
					count = count + requirements.size();
				}
			}
			return count;
		}

		public void addToPositiveRelations(TreeObject key, TreeObject value) {
			addToMapList(this.newPositiveRelations, key, value);
		}

		public HashMap<TreeObject, HashSet<TreeObject>> getNewNegativeRelations() {
			return newNegativeRelations;
		}

		public void addToNegativeRelations(TreeObject key, TreeObject value) {
			addToMapList(this.newNegativeRelations, key, value);
		}

		public HashSet<String> getNotViolatedRequirements() {
			return notViolatedRequirements;
		}

		public void addToNotViolatedRequirements(String string) {
			this.notViolatedRequirements.add(string);
		}

		public List<String> getSimulationFailedList() {
			return simulationFailedList;
		}

		public void setSimulationFailedList(List<String> simulationFailedList) {
			this.simulationFailedList = simulationFailedList;
		}

		public HashMap<Element, HashSet<Element>> getScenarioToViolatedRequirements() {
			return scenarioToViolatedRequirements;
		}


		public HashMap<Element, HashSet<Element>> getScenarioToEvaluatedRequirements() {
			return scenarioToEvaluatedRequirements;
		}
		
		public boolean isRequirementEvaluatedInScenario(Element requirement, Element scenario){
			// if requirement was evaluated with the given scenario 
			HashSet<Element> requirements = getScenarioToEvaluatedRequirements().get(scenario);
			if (requirements != null) {
				if (requirements.contains(requirement)) {
					return true;
				}
			}
			return false;
		}
		
		public boolean isRequirementViolatedInScenario(Element requirement, Element scenario){
			// if requirement was violated with the given scenario 
			HashSet<Element> requirements = getScenarioToViolatedRequirements().get(scenario);
			if (requirements != null) {
				if (requirements.contains(requirement)) {
					return true;
				}
			}
			return false;
		}

		public HashMap<Element, HashSet<Element>> getScenarioToNotEvaluatedRequirements() {
			return scenarioToNotEvaluatedRequirements;
		}

		public HashSet<Element> getSystemModels() {
			return systemModels;
		}

		public void setSystemModels(HashSet<Element> systemModels) {
			this.systemModels = systemModels;
		}
	}
}
