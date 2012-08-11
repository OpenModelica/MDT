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
import java.util.HashSet;
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
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.helpers.VerificationServices;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.simulation.dialogs.DialogSimulationSettings;
import org.openmodelica.modelicaml.simulation.execution.ExecuteSimulation;
import org.openmodelica.modelicaml.simulation.testexecution.actions.PlotResultsAction;
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

	protected String projectName;
	protected String projectPath;
	protected NamedElement umlElement = null;
	private CChain cgChain = null;
	protected String umlModelFileURI = null;
	private String omcLog = "";
	
	private String resultsFileAbsolutePath = null;
	
	private List<String> simulationFailedList;
	private List<String> simulationSuccededList;
	
	private UmlModel umlModel;
	
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
		
		// instantiate in order to see of MSL is used
		boolean isMSLUsed = false;
		if (umlElement instanceof Class) {
			
			/*
			 * NOTE: this is expensive if the class is large. 
			 * TODO: think about how to avoid the instantiation ...
			 */
			ClassInstantiation ci = new ClassInstantiation((Class) umlElement, true, false, null, false);
			ci.createTree();
			// NOTE: there is no need for bindings data collection because we are only interested to see if MSL is used.
//			ci.collectBindingsDataFromUmlModel();
			
			TreeParent treeRoot = ci.getTreeRoot();
			HashSet<Element> allTypes = TreeUtls.getAllTreeItemsClasses(treeRoot);
			
			for (Element element : allTypes) {
				if (element instanceof NamedElement) {
					String qName = ((NamedElement)element).getQualifiedName();
					if (qName.startsWith("Modelica::")) {
						isMSLUsed = true;
					}
				}
			}
		}
		
		// get files to load in order to see if only files from code-sync folder are need so no code generation is required.
		List<String> filesToLoad = new ArrayList<String>();
		// First files from the code-sync, then files from code-gen
		filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(projectPath + "/" + Constants.folderName_code_sync));
		filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(projectPath + "/" + Constants.folderName_code_gen));
		
//		boolean areFilesFromCGFolderToBeLoaded = false;
//		for (String string : filesToLoad) {
//			if (string.contains(projectPath + "/" + Constants.folderName_code_gen)) {
//				areFilesFromCGFolderToBeLoaded = true;
//			}
//		}

		// open dialog with preset settings
		DialogSimulationSettings dialog = new DialogSimulationSettings(shell, 
				"Simulation Settings", 
				umlElement, 
				VerificationServices.getStartTime(umlElement), 
				VerificationServices.getStopTime(umlElement), 
				VerificationServices.getInterval(umlElement), 
				VerificationServices.getSolver(umlElement), 
				VerificationServices.getTolerance(umlElement), 
				VerificationServices.getOutputFormat(umlElement));
		
		dialog.setLoadModelicaLibChecked(isMSLUsed);
		
		/*
		 * Note, do not preset this option because it may happen 
		 * then code-gen folder was deleted by the user
		 */
//		if (!areFilesFromCGFolderToBeLoaded) {
//			dialog.setGenerateModelicaCodeChecked(false);
//		}
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
	    	
			// check if the model is dirty and, if it is, save it in order to make sure that code is generated from an up to date version
			IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			final PapyrusMultiDiagramEditor papyrusEditor = ((PapyrusMultiDiagramEditor)editorPart);
			
			// execution job
			Job job = new Job("Simulating using OMC ...") {
				protected IStatus run(IProgressMonitor monitor) {
					
					// Generate Modelica code if that was desired 
					if (isGenerateModelicaCodeChecked) {
						setUpCGChain();
						
						if (papyrusEditor.isDirty()) {
							papyrusEditor.doSave(monitor);
						}
						
						runchain(monitor);
					}
					
					String omcTempDirectory = ExecuteSimulation.getTempDirectoryPath();
					String modelQName = StringUtls.replaceSpecCharExceptThis(umlElement.getQualifiedName(), "::").replaceAll("::", ".");
					
					/*
					 * First delete old files from the tmp directory.
					 * This is necessary in case the same models should be simulated and the new simulation fails.
					 * Old files needs to be deleted so that the simulation results will not get confused with older simulations.
					 */
					monitor.beginTask("Deleting old files for '" + modelQName + "'", 100);
					List<String> filesToBeDeleted = new ArrayList<String>();
					filesToBeDeleted.add(modelQName + ".exe");
					filesToBeDeleted.add(modelQName + "_init.xml");
					filesToBeDeleted.add(modelQName + "_res.plt");
					filesToBeDeleted.add(modelQName + "_csv.plt");
					filesToBeDeleted.add(modelQName + "_res.mat");
//					filesToBeDeleted.add(modelQName + "_res.xml");
					
					IStatus status = ModelicaMLServices.deleteFiles(filesToBeDeleted, monitor, "Deleting old file: ");
//					if (status != null) {
//						System.err.println("Deleted: " + modelQName + "_init.xml, _res.plt, _res.xml, .exe");
//					}
					
					/*
					 * Simulate
					 */
					monitor.subTask("Simulating '" + modelQName + "'");
					omcLog = ExecuteSimulation.executeAllModels(monitor, new java.io.File(projectPath), omcTempDirectory, testSession, isLoadModelicaLibChecked);
					
					// Check if the simulation results are available
					simulationFailedList = new ArrayList<String>();
					simulationSuccededList = new ArrayList<String>();
					
					// rest it in order to make sure that no old results are used.
					resultsFileAbsolutePath = null;
					
					for(TestModel model : testSession.testModels){
						
						if (monitor.isCanceled()){
							return Status.CANCEL_STATUS;
						}
						
						IFileSystem fileSystem = EFS.getLocalFileSystem();
						
						IFileStore newExeFile = fileSystem.getStore(java.net.URI.create("file:/" + omcTempDirectory + "/" + model.qualifiedName + ".exe"));
						IFileInfo newExeFileInfo = newExeFile.fetchInfo();
						IFileStore newXMLInitFile = fileSystem.getStore(java.net.URI.create("file:/" + omcTempDirectory + "/" + model.qualifiedName + "_init.xml"));
						IFileInfo newXMLInitFileInfo = newXMLInitFile.fetchInfo();
//						IFileStore newPltFile = fileSystem.getStore(java.net.URI.create("file:/" + omcTempDirectory + "/" + model.qualifiedName + "_res.plt"));
//						IFileInfo newPltFileInfo = newPltFile.fetchInfo();
						IFileStore newMatFile = fileSystem.getStore(java.net.URI.create("file:/" + omcTempDirectory + "/" + model.qualifiedName + "_res.mat"));
						IFileInfo newMatFileInfo = newMatFile.fetchInfo();
						
						if (newExeFileInfo.exists() && newXMLInitFileInfo.exists() && newMatFileInfo.exists()) {
							
							//Indicate that OMC generated the files, i.e. the simulation was ok
							simulationSuccededList.add(model.qualifiedName);
							
							// If copy files option was selected -> copy files to 'sim-gen' folder (created it if it does not exist yet)
							if (isCopyFilesChecked) {
								
//								resultsFileAbsolutePath = projectPath + "/" + Constants.folderName_sim_gen + "/" + model.qualifiedName + "_res.xml";
								resultsFileAbsolutePath = projectPath + "/" + Constants.folderName_sim_gen + "/" + model.qualifiedName + "_res.mat";
								
								List<String> fileToBeDeleted = new ArrayList<String>();
								filesToBeDeleted.add(resultsFileAbsolutePath);
								ModelicaMLServices.deleteFiles(fileToBeDeleted, monitor, "Deleting old file: ");
								
								monitor.subTask("Copying files into '"+Constants.folderName_sim_gen+"' for: " + model.qualifiedName);

								// check if the sim-gen folder exists. If not create it.
								createSimGendir(fileSystem, monitor);
								
								// Copy the .exe, the _init.xml and _res.mat files into the sim-gen directory
								IFileStore newExeFileCopy = fileSystem.getStore(java.net.URI.create("file:/" + projectPath + "/" + Constants.folderName_sim_gen + "/" + model.qualifiedName + ".exe"));
								IFileStore newXMLInitFileCopy = fileSystem.getStore(java.net.URI.create("file:/" + projectPath + "/" + Constants.folderName_sim_gen + "/"+ model.qualifiedName + "_init.xml"));
								IFileStore newMatFileCopy = fileSystem.getStore(java.net.URI.create("file:/" + projectPath + "/" + Constants.folderName_sim_gen + "/" + model.qualifiedName + "_res.mat"));
								
								try {
									newExeFile.copy(newExeFileCopy, EFS.OVERWRITE, monitor);
									newXMLInitFile.copy(newXMLInitFileCopy, EFS.OVERWRITE, monitor);
									newMatFile.copy(newMatFileCopy, EFS.OVERWRITE, monitor);
									monitor.subTask("Copying files into folder '"+Constants.folderName_sim_gen+"' for: " + model.qualifiedName);

//									monitor.subTask("Creating xml results file for '"+Constants.folderName_sim_gen+"' for: " + model.qualifiedName);
//
//									//Create _res.xml based on _res.plt
////								resultsXMLAbsolutePath = projectPath + "/" + Constants.folderName_sim_gen + "/" + model.qualifiedName + "_res.xml";
//									SimulationResult_XML_generator.createXML(omcTempDirectory + "/" + model.qualifiedName + "_res.plt",  resultsXMLAbsolutePath);
									
									// Refresh the project browser
									ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

								} catch (CoreException e1) {
									e1.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else { // Results file is in the omc temp folder
								
								resultsFileAbsolutePath = omcTempDirectory + "/" + model.qualifiedName + "_res.mat";
//	
//								try {
//									 //Create the _res.xml based on the _res.plt
//									resultsXMLAbsolutePath = omcTempDirectory + "/" + model.qualifiedName + "_res.xml";
//									SimulationResult_XML_generator.createXML(omcTempDirectory + "/" + model.qualifiedName + "_res.plt", resultsXMLAbsolutePath);
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
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
//					DialogMessage logDialog = new DialogMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Models Execution Report", infoText, simulationReport, errorsExist);
					DialogMessage logDialog = new DialogMessage(new Shell(), "Models Execution Report", infoText, simulationReport, errorsExist);

					logDialog.open();
				}
				else { // open plot window
					PlotResultsAction plotAction = new PlotResultsAction();
					plotAction.setFilePath(resultsFileAbsolutePath);
					plotAction.run(null);
				}
				
 			}
 		});
 	}
	
	
	public void setUmlElement(NamedElement umlElement) {
		this.umlElement = umlElement;
	}

	
	protected void setUmlElement(){
		
		// get the selected UML element that was selected last in one of the views (e.g. Model Explorer)
		IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();		
		Object input = ((IStructuredSelection) selection).getFirstElement();
		
        EObject selectedElement = ModelicaMLServices.adaptSelectedElement(input);
        if (selectedElement instanceof NamedElement) {
        	umlElement = (NamedElement)selectedElement;
		}
	}
	

	protected void setUpProjectAndModelData(){
		
		setUmlElement();
		
		// get UML model data
		umlModel = UmlUtils.getUmlModel();
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
			if (ModelicaMLServices.regenerateCode(umlModel.getResource())) {
				
				// stamp before generating code 
				Long timeStamp = System.currentTimeMillis();
				ModelicaMLServices.codeGenerationStamp.put(umlModel.getResource(), timeStamp);
				
				// same the model in order to make sure that the code is generated from the latest version
//				ModelicaMLServices.saveModel(umlModel);

				cgChain.launch(filter, monitor, LaunchManager.create("run", true));
				
				// TODO: remove this when file encoding for generated code files is enforced to UTF-8
				ModelicaMLServices.generatePackageEncodingFile(projectName);
			}

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
