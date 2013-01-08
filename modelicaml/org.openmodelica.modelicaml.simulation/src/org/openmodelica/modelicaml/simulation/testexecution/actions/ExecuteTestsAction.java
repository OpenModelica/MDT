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
package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.simulation.evaluation.ParseJavaScript;
import org.openmodelica.modelicaml.simulation.execution.ExecuteSimulation;
import org.openmodelica.modelicaml.simulation.omc.OpenModelicaCompilerCommunication;
import org.openmodelica.modelicaml.simulation.xml.TestSession;
import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;
import org.openmodelica.modelicaml.simulation.xml.TestSessionXML_Reader;

public class ExecuteTestsAction implements
		IWorkbenchWindowActionDelegate {

	private String xmlFilePath = null;
	private String folderAbsolutePath = null;
	private Job jobPostProcessSimResutls;
	private List<String> simulationFailedList;
	private List<String> simulationSuccededList;
	private String omcLog = "";
	
	@Override
	public void run(IAction action) {
		if (this.xmlFilePath == null) {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
			if(selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection)selection).getFirstElement();
				if (firstElement instanceof IFile) {
					this.xmlFilePath = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				}
			}
		}
		
		if (xmlFilePath != null) {
			IFileSystem fileSystem = EFS.getLocalFileSystem();
			IFileStore xmlFile = fileSystem.getStore(URI.create(xmlFilePath));
			IFileStore parent = xmlFile.getParent();
			if (parent != null ) {
				folderAbsolutePath = parent.toURI().getRawPath().substring(1,parent.toURI().getRawPath().length()) + "/";
			}
		}
		
		if (this.xmlFilePath != null && folderAbsolutePath != null) {
			
			// wake up OMC
			final OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
			omcc.getVesion();
			
			final String pathToSession = getFolderAbsolutePath();
			
			//START of the execution as a job.
			//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()

			Job job = new Job("Simulating and Evaluating Results") {

				protected IStatus run(IProgressMonitor monitor) {

						monitor.beginTask("Executing verification models ...", 100);
						{
							TestSession testSessionObj = TestSessionXML_Reader.readFromXML(pathToSession + "verification_session.xml");
							
							// get the OMC temp folder
//							String omcTempWorkingFolder = (System.getenv().get("OPENMODELICAHOME") + "/tmp").replaceAll("\\\\", "/"); 
//							String omcTempWorkingFolder = (System.getenv().get("OPENMODELICAHOME") + "tmp").replaceAll("\\\\", "/");
//							OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
							String omcTempWorkingFolder = omcc.getTempDirectoryPath();
							
							File sessionFolder = new File(pathToSession);
							
							/*
							 * First delete old files from the tmp directory.
							 * This is necessary in case the same models should be simulated and the new simulation fails.
							 * Old files needs to be deleted so that the simulation results will not get confused with older simulations.
							 */
							IFileSystem fileSystem = EFS.getLocalFileSystem();
							
							for(TestModel model : testSessionObj.testModels){
								
								if (monitor.isCanceled()){
									return Status.CANCEL_STATUS;
								}
								
								List<String> filesToBeDeleted = new ArrayList<String>();
								filesToBeDeleted.add(model.qualifiedName + ".exe");
								filesToBeDeleted.add(model.qualifiedName + "_init.xml");
								filesToBeDeleted.add(model.qualifiedName + "_res.plt");
								filesToBeDeleted.add(model.qualifiedName + "_res.mat");
//								filesToBeDeleted.add(model.qualifiedName + "_res.xml");

//								ModelicaMLServices.deleteOldSimulationFiles(model.qualifiedName, omcTempWorkingFolder, monitor);
								ModelicaMLServices.deleteFiles(filesToBeDeleted, monitor, "Deleting old file: ");
								
//								monitor.subTask("Deleting files from OMC tmp folder for '" + model.qualifiedName + "'");
//								IFileStore oldExeFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + ".exe"));
//								IFileStore oldXMLInitFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml"));
//								IFileStore oldPltFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt"));
//								try {
//									oldExeFile.delete(EFS.NONE, monitor);
//									oldXMLInitFile.delete(EFS.NONE, monitor);
//									oldPltFile.delete(EFS.NONE, monitor);
//								} catch (CoreException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
							}
							
							/*
							 * Simulated all models one after another
							 * TODO: each model should be simulated in a separate sub job to enable the canceling of one specific model and not being forced to cancel all model executions by that!
							 * TODO: what should happen if the simulation failed? 
							 */
							omcLog = "";
							monitor.subTask("Executing models ...");
							boolean loadMSL = true; // by default load the Modelica Standard Library for the case if there are models that use it.
							String omcMessage =	ExecuteSimulation.executeAllModels(monitor, sessionFolder, omcTempWorkingFolder, testSessionObj, loadMSL);
							omcLog = omcMessage;
									
//							if(!omcMessage.isEmpty()){
//								System.out.println("OMC Message: /n" + omcMessage);
//								openErrorMsg("Verification Models Execution Error", omcMessage);
//							}
							
							simulationFailedList = new ArrayList<String>();
							simulationSuccededList = new ArrayList<String>();
							
							for(TestModel model : testSessionObj.testModels){
								
								if (monitor.isCanceled()){
									return Status.CANCEL_STATUS;
								}
									
								monitor.subTask("Processing generated files for: " + model.qualifiedName);

								IFileStore newExeFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + ".exe"));
								IFileInfo newExeFileInfo = newExeFile.fetchInfo();
								IFileStore newXMLInitFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml"));
								IFileInfo newXMLInitFileInfo = newXMLInitFile.fetchInfo();
//								IFileStore newPltFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt"));
//								IFileInfo newPltFileInfo = newPltFile.fetchInfo();
								IFileStore newMatFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + "_res.mat"));
								IFileInfo newMatFileInfo = newMatFile.fetchInfo();

								if (newExeFileInfo.exists() && newXMLInitFileInfo.exists() && newMatFileInfo.exists()) {
									
									/*
									 * Indicate that OMc generated files -> simulation was ok
									 */
									simulationSuccededList.add(model.qualifiedName);
									
									/*
									 * Copy the .exe and the _init.xml files
									 */
									IFileStore newExeFileCopy = fileSystem.getStore(URI.create("file:/" + pathToSession + "/" + model.qualifiedName + ".exe"));
									IFileStore newXMLInitFileCopy = fileSystem.getStore(URI.create("file:/" + pathToSession + "/" + model.qualifiedName + "_init.xml"));
									IFileStore newMatFileCopy = fileSystem.getStore(java.net.URI.create("file:/" + pathToSession + "/" + model.qualifiedName + "_res.mat"));

									try {
										newExeFile.copy(newExeFileCopy, EFS.OVERWRITE, monitor);
										newXMLInitFile.copy(newXMLInitFileCopy, EFS.OVERWRITE, monitor);
										newMatFile.copy(newMatFileCopy, EFS.OVERWRITE, monitor);
									} catch (CoreException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
//									cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + ".exe", sessionFolder + "/" + model.qualifiedName + ".exe");
//									cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml", sessionFolder + "/" + model.qualifiedName + "_init.xml");
//									cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.plt");
									
//									/*
//									 * Create the _res.xml based on the _res.plt
//									 */
//									try {
//										SimulationResult_XML_generator.createXML(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", sessionFolder + "/" + model.qualifiedName + "_res.xml");
//										
//										// refresh the project browser
//										ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
//										
////										[TODO 20120317] Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().setResults(Result_TXT_reader.readResult(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt"));
//
//									} catch (Exception e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
								}
								else {
									simulationFailedList.add(model.qualifiedName);
								}

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
		            	startJob(jobPostProcessSimResutls);
		            	}
		            }
		         };
			
			job.addJobChangeListener(jobChangeAdapter);
			job.setUser(true);
			job.schedule();

			// add listener to the results post processing job to open reports. 
			JobChangeAdapter jobPostProcessSimResultsChangeAdapter = new JobChangeAdapter() {
				public void done(IJobChangeEvent event) {

					if (event.getResult().isOK()) {
							
						/* 
						 * Create report data. 
						 */
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
						
						// open log and the report afterwards 
						openReport(pathToSession, infoText, simulationReport, errorsExist);
		            	}
		            }
		         };

		    jobPostProcessSimResutls = new Job("Post-processing Simulation Results") {
				protected IStatus run(IProgressMonitor monitor) {

						monitor.beginTask("Evaluating Simulation Results", 100);
						{
							try {
								ParseJavaScript.parseJSTemp_generateJSFile(pathToSession);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						monitor.done();
						return Status.OK_STATUS;
					}
			};
			jobPostProcessSimResutls.addJobChangeListener(jobPostProcessSimResultsChangeAdapter);
						
		}
		else {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Could not access the verification session files. ");
		}
	}

	
//	private void openErrorMsg(final String title, final String erroMsg){
// 		// Use this to open a Shell in the UI thread
// 		Display.getDefault().asyncExec(new Runnable() {
// 			public void run() {
// 				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, erroMsg);
// 			}
// 		});
// 	}
	
	
	private void openReport(final String pathToSession, final String infoText, final String simulationReport, final boolean errorsExist){
 		// Use this to open a Shell in the UI thread
 		Display.getDefault().asyncExec(new Runnable() {
 			public void run() {
 				
				/* open log and the report afterwards 
				 */
//				DialogMessage logDialog = new DialogMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Models Execution Report", infoText, simulationReport, errorsExist);
				DialogMessage logDialog = new DialogMessage(new Shell(), "Models Execution Report", infoText, simulationReport, errorsExist);
 				logDialog.open();
 				
 				boolean open = MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Open Report?", "The simulation is completed. Do you want to see the report?");
 				if (open) {
 					
 					// close previous dialog first
 					if (logDialog != null) {
 						logDialog.close();
					}
 					
 					// run open report action
 	            	OpenTestExecutionReportAction openReport = new OpenTestExecutionReportAction();
 	            	openReport.setReportFilePath(pathToSession + "report-gen/report.html");
 	            	openReport.run(null);
				}
 			}
 		});
 	}
	
	private void startJob(final Job job){
 		// Use this to open a Shell in the UI thread
 		Display.getDefault().asyncExec(new Runnable() {
 			public void run() {
 				job.setUser(true);
 				job.schedule();
 			}
 		});
 	}
	
	
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}


	public void setXMLFilePath(String path){
		this.xmlFilePath = path;
	}

	public String getFolderAbsolutePath() {
		return folderAbsolutePath;
	}

	public void setFolderAbsolutePath(String folderAbsolutePath) {
		this.folderAbsolutePath = folderAbsolutePath;
	}

}
