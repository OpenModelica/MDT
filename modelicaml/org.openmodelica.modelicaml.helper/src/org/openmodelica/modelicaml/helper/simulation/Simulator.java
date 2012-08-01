package org.openmodelica.modelicaml.helper.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.openmodelica.modelicaml.simulation.omc.OpenModelicaCompilerCommunication;

public class Simulator {

	private String projectPath;
	private String simulationResultsFolderPath;
	private boolean loadMSL = true;
	private HashSet<Element> notSimulatedModels = new HashSet<Element>();
	private List<String> simulationFailedList;
	private List<String> simulationSuccededList;
	
	private HashMap<Element,String> simulationResultsFile = new HashMap<Element,String>();
	protected GeneratedModelsData gmd;
	private String log;
	private Job simulationJob;

	private boolean recordOnlyRequirementStatusAndClients;
	
	public Simulator(GeneratedModelsData gmd, String projectPath, boolean recordOnlyRequirementStatusAndClients) {
		this.gmd = gmd;
		this.projectPath = projectPath;
		this.setRecordOnlyRequirementStatusAndClients(recordOnlyRequirementStatusAndClients);
	}
	
	
	public void generateCodeAndSimulate(){
		
		simulationJob = new Job("Simulating") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				if (monitor.isCanceled() || projectPath == null){
					return Status.CANCEL_STATUS;
				}
				
				// simulate models
				simulateModels(monitor);
				
				return Status.OK_STATUS;
			}
		};
		
		simulationJob.setUser(true);
		simulationJob.schedule();
		
	}
    
    public Job getSimulationJob() {
		return simulationJob;
	}



	public void setSimulationJob(Job simulationJob) {
		this.simulationJob = simulationJob;
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
			String message = "Loading MSL: " + reply;
			System.err.println(message);
			addToLog(message);
		}
		
		// load code into compiler
		List<String> filesToLoad = new ArrayList<String>();
		filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(projectPath + "/" + Constants.folderName_code_sync));
		filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(projectPath + "/" + Constants.folderName_code_gen));
		for (String string : filesToLoad) {
			String reply = omcc.loadFile(string);
			String message = "Loading models: " + filesToLoad.toString() + " \n reply: " + reply;
			
			System.err.println(message);
			addToLog(message);
		}
		
		// simulate each model
		for (Element genModel : gmd.getGeneratedModels()) {
			
			// stop if user canceled the simulation monitor
			if (monitor.isCanceled()){
				return;
			}
			
			// determine the qualified name of the model
			String modelQName = getModelQName((NamedElement)genModel);
			String filePath = simulationResultsFolderPath + "/" + ModelicaMLServices.getSimulationResultsFileName((NamedElement) genModel);

			/*
			 * First delete old files from the tmp directory.
			 * This is necessary in case the same models should be simulated and the new simulation fails.
			 * Old files needs to be deleted so that the simulation results will not get confused with older simulations.
			 */
			String message = "Deleting old files for '" + modelQName + "'";
			monitor.beginTask(message, 100);
			addToLog(message);
			
			/*
			 * Delete the old file for the case when this model could not be simulated
			 * but an old file still exists.
			 */
			List<String> filesToBeDeleted = new ArrayList<String>();
			filesToBeDeleted.add(filePath);
			ModelicaMLServices.deleteFiles(filesToBeDeleted, monitor, "Deleting old file: ");
			
			// simulate
			message = "Simulating '" + modelQName + "'";
			monitor.subTask(message);
			addToLog(message);
			
			simulateModel((NamedElement) genModel, omcc);
			
			// Check if the simulation results are available
			simulationFailedList = new ArrayList<String>();
			simulationSuccededList = new ArrayList<String>();
				
			IFileSystem fileSystem = EFS.getLocalFileSystem();
			IFileStore newMatFile = fileSystem.getStore(URIUtil.toURI(filePath, true));
			IFileInfo newMatFileInfo = newMatFile.fetchInfo();
			
			if (newMatFileInfo.exists()) {
				//Indicate that OMC generated the files, i.e. the simulation was ok
				simulationSuccededList.add(modelQName);
				simulationResultsFile.put(genModel, filePath);
				
				addToLog("'"+modelQName+"' was simulated.");
			}
			else {
				notSimulatedModels.add(genModel);
				simulationFailedList.add(modelQName);
				
				addToLog("'"+modelQName+"' was NOT simulated.");
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
			if (startTime==null|| startTime.trim().length() == 0) {
				startTime = Constants.DEFAULT_startTime;
				startTime = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_startTime, startTime, null);
			}
			if (stopTime==null|| stopTime.trim().length() == 0) {
				stopTime = Constants.DEFAULT_stopTime;
				stopTime = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_stopTime, stopTime, null);
			}
			if (numberOfIntervals==null|| numberOfIntervals.trim().length() == 0) {
				numberOfIntervals = Constants.DEFAULT_numberOfIntervals;
				numberOfIntervals = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_numberOfIntervals, numberOfIntervals, null);
			}
			if (tolerance == null || tolerance.trim().length() == 0 ) {
				tolerance = Constants.DEFAULT_tolerance;
				tolerance = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_tolerance, tolerance, null);
			}
			if (solver == null || solver.trim().length() == 0 ) {
				solver = Constants.DEFAULT_solver;
				solver = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_solver, solver, null);
			}
			if (outputFormat == null || outputFormat.trim().length()==0 ) {
				outputFormat = Constants.DEFAULT_outputFormat;
				outputFormat = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_outputFormat, outputFormat, null);
			}
			
			if (isRecordOnlyRequirementStatusAndClients()) {
				
				HashSet<TreeObject> clientAndRequirementItems = new HashSet<TreeObject>(); 
				ClassInstantiation ci = ModelicaMLServices.getModelInstantiation(model, gmd.getPreparedInstantiations());
				
				/*
				 * In case the simulation action was started not immediately after the models generation
				 * and there are no prepared model instantiations then VeM to be simulated 
				 * will need to be instantiated. However, if the simulation is running in 
				 * a Job the Papyrus model will not be accessible. In this case we need to set 
				 * the UML model and recollect bindings data. 
				 */
				
				if (ci.getValueBindingsDataCollector() != null && ci.getValueBindingsDataCollector().getReferencedClients() != null && ci.getValueBindingsDataCollector().getReferencedClients().size() > 0) {
					// OK, skip the re-collection of binding data
				}
				else { // collect binding data
					//set the UML model because it will not be accessible while simulation is running in a job.
					ci.setUmlModel(gmd.getGeneratedModelsPackage().getModel());
					// pass the pre-collected mediators in order to avoid another search 
					ci.setAllMediators(gmd.getAllFoundMediators());
					// collect binding data in order to determine clients, providers, operations, etc.
					ci.collectValueClientsAndProvidersFromUmlModel();
				}
				
				for (TreeObject treeObject : ci.getAllTreeObjects()) {
					
					// if this is a client
					if (treeObject.isValueClient()) {
//						clientAndRequirementItems.add(treeObject);
						HashSet<TreeObject> actualClients = VerificationExecutionServices.getActualClients(ci, treeObject);
						clientAndRequirementItems.addAll(actualClients);
					}
					else {
						// look for a requirement status variable
						Element type = treeObject.getComponentType();
						if (treeObject instanceof TreeParent && type != null && type.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
							TreeObject[] children = ((TreeParent)treeObject).getChildren();
							for (TreeObject requirementPropertyTreeItem : children) {
								if ( requirementPropertyTreeItem.getName().equals(Constants.propertyName_mStatus)) {
									clientAndRequirementItems.add(requirementPropertyTreeItem);
								}
							}
						}
					}
				}
				variableFilter = getVariablesFilter(clientAndRequirementItems); 
			}
			else {
				// no filter, i.e., all variables should be recorded.
			}
			
			//Simulate model
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
	
	
	private String getVariablesFilter(HashSet<TreeObject> requirementItems){
		String requirementVariableFilter = "";
		for (TreeObject treeObject : requirementItems) {
			if (!treeObject.getDotPath().trim().equals("")) {
//				requirementVariableFilter = requirementVariableFilter + "|" + treeObject.getDotPath() + "." + gmd.requirementStatusPropertyName;
				requirementVariableFilter = requirementVariableFilter + "|" + treeObject.getDotPath();
			}
		}
		
		// remove the first "|"
		if (requirementVariableFilter.trim().startsWith("|")) {
			String correctedPath = requirementVariableFilter.replaceFirst("\\|", "");
			return correctedPath;
		}
		return requirementVariableFilter;
	}
	
//	private void setUpProjectData(){
//		
//		// get UML model data
//		UmlModel umlModel = UmlUtils.getUmlModel();
////		String umlModelFileURI = umlModel.getResourceURI().toString();
//
//		if (umlModel != null) {
//			// get project data
//			String projectName = umlModel.getResource().getURI().segment(1);
//			
//			IWorkspace workspace = ResourcesPlugin.getWorkspace();
//			IWorkspaceRoot root = workspace.getRoot();
//			IProject iProject = root.getProject(projectName);
//			
//			projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
//		}
//		else {
//			MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Model Access", "Could not access the ModelicaML model. Please open it in editor.");
//		}
//	}
	
	
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
	
	
	private void addToLog(String msg){
		this.log = this.log + msg + "\n";
	}

	public HashSet<Element> getNotSimulatedModels() {
		return notSimulatedModels;
	}

	public void setNotSimulatedModels(HashSet<Element> notSimulatedModels) {
		this.notSimulatedModels = notSimulatedModels;
	}
    

	public String getProjectPath() {
		return projectPath;
	}



	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}



	public String getSimulationResultsFolderPath() {
		return simulationResultsFolderPath;
	}



	public void setSimulationResultsFolderPath(String simulationResultsFolderPath) {
		this.simulationResultsFolderPath = simulationResultsFolderPath;
	}



	public boolean isLoadMSL() {
		return loadMSL;
	}



	public void setLoadMSL(boolean loadMSL) {
		this.loadMSL = loadMSL;
	}



	public List<String> getSimulationFailedList() {
		return simulationFailedList;
	}



	public void setSimulationFailedList(List<String> simulationFailedList) {
		this.simulationFailedList = simulationFailedList;
	}



	public List<String> getSimulationSuccededList() {
		return simulationSuccededList;
	}



	public void setSimulationSuccededList(List<String> simulationSuccededList) {
		this.simulationSuccededList = simulationSuccededList;
	}



	public HashMap<Element, String> getSimulationResultsFile() {
		return simulationResultsFile;
	}



	public void setSimulationResultsFile(
			HashMap<Element, String> simulationResultsFile) {
		this.simulationResultsFile = simulationResultsFile;
	}

	public GeneratedModelsData getGmd() {
		return gmd;
	}



	public void setGmd(GeneratedModelsData gmd) {
		this.gmd = gmd;
	}

	public String getLog() {
		return log;
	}



	public void setLog(String log) {
		this.log = log;
	}



	public boolean isRecordOnlyRequirementStatusAndClients() {
		return recordOnlyRequirementStatusAndClients;
	}



	public void setRecordOnlyRequirementStatusAndClients(boolean recordOnlyRequirementStatus) {
		this.recordOnlyRequirementStatusAndClients = recordOnlyRequirementStatus;
	}
}
