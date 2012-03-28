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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.simulation.execution.ExecuteSimulation;
import org.openmodelica.modelicaml.simulation.testexecution.actions.PlotTestResultsAction;
import org.openmodelica.modelicaml.simulation.testexecution.dialogs.DialogMessage;
import org.openmodelica.modelicaml.simulation.testexecution.dialogs.DialogSimulationSettings;
import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_generator;
import org.openmodelica.modelicaml.simulation.xml.TestSession;
import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;

import fr.obeo.acceleo.chain.File;
import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.chain.impl.spec.CFolder;
import fr.obeo.acceleo.chain.impl.spec.CLog;
import fr.obeo.acceleo.chain.impl.spec.CModel;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;

// TODO: Auto-generated Javadoc
/**
 * The Class
 * GenerateModelicaCodeFromEntireModelicaMLModelAndSimulationThisClassWithOMCAction
 * .
 * 
 * @author rmwscham
 */
public class SimulationOMCAction extends AbstractHandler {

	private String projectName;
	private String projectPath;
	private NamedElement umlElement = null;
	private CChain cgChain = null;
	private String umlModelFileURI = null;
	private String omcLog = "";
	
	private String resultsXMLAbsolutePath = null;
	
	private List<String> simulationFailedList;
	private List<String> simulationSuccededList;
	
	IGenFilter filter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile,
				EObject object) throws CoreException {
			return true;
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TransactionalEditingDomain editingDomain = PapyrusServices.getPapyrusEditingDomain();
		editingDomain.getCommandStack().execute(getCommand(editingDomain));
		return null;
	}

	/**
	 * Gets the command.
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @return the command
	 */
	protected Command getCommand(TransactionalEditingDomain editingDomain) {

		// collect model and project data
		setUpProjectAndModelData();
		
		// open dialog for simulation settings
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		DialogSimulationSettings dialog = new DialogSimulationSettings(shell, 
				"Simulation Settings", 
				umlElement, 
				VerificationExecutionServices.getStartTime(umlElement), 
				VerificationExecutionServices.getStopTime(umlElement), 
				VerificationExecutionServices.getNumberOfIntervals(umlElement), 
				VerificationExecutionServices.getSolver(umlElement), 
				VerificationExecutionServices.getTolerance(umlElement), 
				VerificationExecutionServices.getOutputFormat(umlElement));
		dialog.open();
		
		if (dialog.getReturnCode() == IDialogConstants.OK_ID) { // OK button clicked
	    	
	    	final boolean isGenerateModelicaCodeChecked = dialog.isGenerateModelicaCodeChecked();
	    	final boolean isCopyFilesChecked = dialog.isCopyFilesChecked();
	    	final boolean isLoadModelicaLibChecked = dialog.isLoadModelicaLibChecked();
			
			// test (i.e. simulation) session data
			final TestSession testSession = new TestSession("");
			TestSession.TestModel tm = new TestSession.TestModel(
					StringUtls.replaceSpecCharExceptThis(umlElement.getQualifiedName(), "::").replaceAll("::", "."), 
					dialog.getStartTime(), 
					dialog.getStopTime(), 
					dialog.getOutputInterval(), 
					dialog.getTolerance(), 
					dialog.getMethod(), 
					dialog.getFormat());
		    
			testSession.testModels.add(tm);
	    	
			// execution job
			Job job = new Job("Simulating using OMC ...") {
				protected IStatus run(IProgressMonitor monitor) {
					
					// Generate Modelica code if that was desired 
					if (isGenerateModelicaCodeChecked) {
						setUpCGChain();
						runchain(monitor);
					}
					
					String omcTempDirectory = ExecuteSimulation.getTempDirectoryPath();
					String modelQName = StringUtls.replaceSpecCharExceptThis(umlElement.getQualifiedName(), "::");
					
					/*
					 * First delete old files from the tmp directory.
					 * This is necessary in case the same models should be simulated and the new simulation fails.
					 * Old files needs to be deleted so that the simulation results will not get confused with older simulations.
					 */
					monitor.beginTask("Deleting old files for '" + modelQName + "'", 100);
					ModelicaMLServices.deleteOldSimulationFiles(modelQName, omcTempDirectory, monitor);
					
					/*
					 * Simulate
					 */
					monitor.subTask("Simulating '" + modelQName + "'");
					omcLog = ExecuteSimulation.executeAllModels(monitor, new java.io.File(projectPath), omcTempDirectory, testSession, isLoadModelicaLibChecked);
					
					// Check if the simulation results are available
					simulationFailedList = new ArrayList<String>();
					simulationSuccededList = new ArrayList<String>();
					
					// rest it in order to make sure that no old results are used.
					resultsXMLAbsolutePath = null;
					
					for(TestModel model : testSession.testModels){
						
						if (monitor.isCanceled()){
							return Status.CANCEL_STATUS;
						}
						
						IFileSystem fileSystem = EFS.getLocalFileSystem();
						
						IFileStore newExeFile = fileSystem.getStore(java.net.URI.create("file:/" + omcTempDirectory + "/" + model.qualifiedName + ".exe"));
						IFileInfo newExeFileInfo = newExeFile.fetchInfo();
						IFileStore newXMLInitFile = fileSystem.getStore(java.net.URI.create("file:/" + omcTempDirectory + "/" + model.qualifiedName + "_init.xml"));
						IFileInfo newXMLInitFileInfo = newXMLInitFile.fetchInfo();
						IFileStore newPltFile = fileSystem.getStore(java.net.URI.create("file:/" + omcTempDirectory + "/" + model.qualifiedName + "_res.plt"));
						IFileInfo newPltFileInfo = newPltFile.fetchInfo();
						
						if (newExeFileInfo.exists() && newXMLInitFileInfo.exists() && newPltFileInfo.exists()) {
							
							//Indicate that OMC generated the files, i.e. the simulation was ok
							simulationSuccededList.add(model.qualifiedName);
							
							// If copy files option was selected -> copy files to 'sim-gen' folder (created it if it does not exist yet)
							if (isCopyFilesChecked) {
								
								monitor.subTask("Copying files into '"+Constants.folderName_sim_gen+"' for: " + model.qualifiedName);

								// check if the sim-gen folder exists. If not create it.
								createSimGendir(fileSystem, monitor);
								
								// Copy the .exe and the _init.xml files into the sim-gen directory
								IFileStore newExeFileCopy = fileSystem.getStore(java.net.URI.create("file:/" + projectPath + "/" + Constants.folderName_sim_gen + "/" + model.qualifiedName + ".exe"));
								IFileStore newXMLInitFileCopy = fileSystem.getStore(java.net.URI.create("file:/" + projectPath + "/" + Constants.folderName_sim_gen + "/"+ model.qualifiedName + "_init.xml"));
								
								try {
									newExeFile.copy(newExeFileCopy, EFS.OVERWRITE, monitor);
									newXMLInitFile.copy(newXMLInitFileCopy, EFS.OVERWRITE, monitor);

									monitor.subTask("Creating xml results file for '"+Constants.folderName_sim_gen+"' for: " + model.qualifiedName);

									//Create _res.xml based on _res.plt
									resultsXMLAbsolutePath = projectPath + "/" + Constants.folderName_sim_gen + "/" + model.qualifiedName + "_res.xml";
									SimulationResult_XML_generator.createXML(omcTempDirectory + "/" + model.qualifiedName + "_res.plt",  resultsXMLAbsolutePath);
									
									// Refresh the project browser
									ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

								} catch (CoreException e1) {
									e1.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							else { // Create results xml file int he omc temp folder
								try {
									 //Create the _res.xml based on the _res.plt
									resultsXMLAbsolutePath = omcTempDirectory + "/" + model.qualifiedName + "_res.xml";
									SimulationResult_XML_generator.createXML(omcTempDirectory + "/" + model.qualifiedName + "_res.plt", resultsXMLAbsolutePath);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						else {
							simulationFailedList.add(model.qualifiedName);
						}	
						
					}
					monitor.done();
					return Status.OK_STATUS;
				}
			};
			
			// add listener to the execution job to start the next job. 
			JobChangeAdapter jobChangeAdapter = new JobChangeAdapter() {
				public void done(IJobChangeEvent event) {
		            if (event.getResult().isOK()) {
		            		openPlotDialog();
		            	}
		            }
		         };
			
		    job.addJobChangeListener(jobChangeAdapter);
			job.setUser(true);
			job.schedule();
		}

	    CompoundCommand cc = new CompoundCommand("ModelicaML Simulation");
		return (cc.unwrap());
	}

	
	private void createSimGendir(IFileSystem fileSystem, IProgressMonitor monitor){
		// check if the sim-gen dir. exists
		java.io.File simGenDir = new java.io.File(projectPath + "/" + Constants.folderName_sim_gen);
		if (simGenDir.exists() && simGenDir.isDirectory()) {
			// ok, do nothing
		}
		else { // create directory
			IFileStore simGenDirCreated = fileSystem.getStore(java.net.URI.create("file:/" + projectPath + "/" + Constants.folderName_sim_gen));
			try {
				simGenDirCreated.mkdir(EFS.OVERWRITE, monitor);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void openPlotDialog(){
 		// Use this to open a Shell in the UI thread
 		Display.getDefault().asyncExec(new Runnable() {
 			public void run() {
 				
				// Collect simulation report data 
				String msgSimulationFailed = "";
				String msgSimulationSucceded= "";
				String infoText  = "Simulation was successful.";
				boolean errorsExist = false;
				
				// if there were errors during copying files -> display on a dialog
				if (simulationFailedList != null && simulationFailedList.size() > 0) {
					
					infoText = "Errors occured during simulation.";
					errorsExist = true;
					msgSimulationFailed = "Simulation failed for:";
					
					for (String string : simulationFailedList) {
						msgSimulationFailed = msgSimulationFailed +  "\n" + "                  - " + string;
					}
					
					msgSimulationFailed = msgSimulationFailed + "\n\n";
				}
				
				if (simulationSuccededList != null && simulationSuccededList.size() > 0) {
					msgSimulationSucceded = "Simulation succeded for:";
					for (String string : simulationSuccededList) {
						msgSimulationSucceded = msgSimulationSucceded +  "\n" + "                  - " + string;
					}
					msgSimulationSucceded = msgSimulationSucceded + "\n\n";
				}
				
				String simulationReport = msgSimulationFailed + msgSimulationSucceded;

				// add log messages if there are any. 
				if (omcLog != null && !omcLog.trim().equals("")) {
					simulationReport = simulationReport + "************************** LOG ************************\n" + omcLog.trim();
				}
				
				
				
				if (errorsExist) { // open error dialog
					DialogMessage logDialog = new DialogMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
							"Models Execution Report", infoText, simulationReport, errorsExist);
					logDialog.open();
				}
				else { // open plot window
					PlotTestResultsAction plotAction = new PlotTestResultsAction();
					plotAction.setXMLFilePath(resultsXMLAbsolutePath);
					plotAction.run(null);
				}
				
 			}
 		});
 	}
	
	
	public void setUmlElement(NamedElement umlElement) {
		this.umlElement = umlElement;
	}

	private void setUpProjectAndModelData(){
		
		if (umlElement == null) { // if it was not set from outside

			// get the selected UML element that was selected last in one of the views (e.g. Model Explorer)
			IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();		
			Object input = ((IStructuredSelection) selection).getFirstElement();
			
	        EObject selectedElement = ModelicaMLServices.adaptSelectedElement(input);
	        if (selectedElement instanceof NamedElement) {
	        	umlElement = (NamedElement)selectedElement;
			}
		}
		
		// get UML model data
		UmlModel umlModel = UmlUtils.getUmlModel();
		umlModelFileURI = umlModel.getResourceURI().toString();

		// get project data
		projectName = umlModel.getResource().getURI().segment(1);
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject iProject = root.getProject(projectName);
		
		projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
	}
	
	private void setUpCGChain(){
		
		URI chainURI = URI.createPlatformPluginURI("/org.openmodelica.modelicaml.gen.modelica/bin/code_generation.chain", true);
		ResourceSet rs = new ResourceSetImpl();
		Resource r = (Resource) rs.createResource(chainURI);
		try {
			r.load(null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cgChain = (CChain) r.getContents().get(0);

		String modelFilePath = umlModelFileURI.replace("platform:/resource/", "");
		String outputFolderPath = projectName;
		String logPath = projectName + "/errors.log";

		// Don't create Parameter Files... simply set the correct path into them
		@SuppressWarnings("unchecked")
		EList<File> files = cgChain.getParametersFiles();
		for (File file : files) {
			if (file instanceof CModel) {
				file.setPath(modelFilePath);
			} else if (file instanceof CFolder) {
				file.setPath(outputFolderPath);
			} else if (file instanceof CLog) {
				file.setPath(logPath);
			}
		}
		
	}
	
	/**
	 * Runchain.
	 * 
	 * @param monitor
	 *            the monitor
	 */
	public void runchain(IProgressMonitor monitor) {
		try {
			cgChain.launch(filter, monitor, LaunchManager.create("run", true));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Checks if is visible.
	 * 
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return true;
	}

}
