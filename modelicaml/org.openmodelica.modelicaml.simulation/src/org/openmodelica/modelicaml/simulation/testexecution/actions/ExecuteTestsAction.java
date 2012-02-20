package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;
import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IFile;
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
import org.openmodelica.modelicaml.simulation.Activator;
import org.openmodelica.modelicaml.simulation.evaluation.ParseJavaScript;
import org.openmodelica.modelicaml.simulation.execution.ExecuteSimulation;
import org.openmodelica.modelicaml.simulation.filehandling.cp;
import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_generator;
import org.openmodelica.modelicaml.simulation.xml.TestSession;
import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;
import org.openmodelica.modelicaml.simulation.xml.TestSessionXML_Reader;
import org.openmodelica.simulation.core.simulationresult_txt_handling.Result_TXT_reader;

public class ExecuteTestsAction implements
		IWorkbenchWindowActionDelegate {

	private String xmlFilePath = null;
	private String folderAbsolutePath = null;
	private Job jobPostProcessSimResutls;
	
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
			
			final String pathToSession = getFolderAbsolutePath();
			
			//START of the execution as a job.
			//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()

			Job job = new Job("Simulating and Evaluating Results") {

				protected IStatus run(IProgressMonitor monitor) {

						monitor.beginTask("Executing verification models ...", 100);
						{
							TestSession testSessionObj = TestSessionXML_Reader.readFromXML(pathToSession + "verification_session.xml");
							String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; 
							File sessionFolder = new File(pathToSession);
							String omcMessage =	ExecuteSimulation.executeAllModels(monitor, sessionFolder, omcTempWorkingFolder, testSessionObj);
							if(omcMessage.isEmpty()){
								
							}
							else
								System.out.println("OMC Message: /n" + omcMessage);

							for(TestModel model : testSessionObj.testModels){
								if (monitor.isCanceled())
									return Status.CANCEL_STATUS;
								monitor.subTask("Processing: " + model.qualifiedName);
								cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + ".exe", sessionFolder + "/" + model.qualifiedName + ".exe");
								cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml", sessionFolder + "/" + model.qualifiedName + "_init.xml");
//								cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.plt");
								try {
									SimulationResult_XML_generator.createXML(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", sessionFolder + "/" + model.qualifiedName + "_res.xml");
									Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().setResults(Result_TXT_reader.readResult(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt"));
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
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

			//START of the results post processing job.
			// add listener to the results post processing job to open report. 
			JobChangeAdapter jobPostProcessSimResultsChangeAdapter = new JobChangeAdapter() {
				public void done(IJobChangeEvent event) {
		            if (event.getResult().isOK()) {
		            		openReport(pathToSession);
		            	}
		            }
		         };

		    jobPostProcessSimResutls = new Job("Post-processing Simulation Results") {
				protected IStatus run(IProgressMonitor monitor) {

						monitor.beginTask("Evaluating Simulation Results", 100);
						{
							ParseJavaScript.parseJSTemp_generateJSFile(pathToSession);
						}
						monitor.done();
						return Status.OK_STATUS;
					}
			};
			jobPostProcessSimResutls.addJobChangeListener(jobPostProcessSimResultsChangeAdapter);
						
		}
		else {
			MessageDialog.openError(new Shell(), "Error", "Could not access the verification session files. ");
		}
	}

	
	private void openReport(final String pathToSession){
 		// Use this to open a Shell in the UI thread
 		Display.getDefault().asyncExec(new Runnable() {
 			public void run() {
 				boolean open = MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Open Report?", "The simulation is completed. Do you want to see the report?");
 				if (open) {
 	            	OpenTestExecutionReportAction openReport = new OpenTestExecutionReportAction();
 	            	
 	            	openReport.setPath(pathToSession + "report-gen/report.html");
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
	
//	public static void main(String[] args) {
//		String pathToSession = "C:/Projects/ModelicaML/runtime-New_configuration/modelicaml.example.potableWaterSystem_v30/verification-gen/verification-session_20120124110026/";
//		
//		TestSession testSessionObj = TestSessionXML_Reader.readFromXML(pathToSession + "verification_session.xml");
//		String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; 
//		File sessionFolder = new File(pathToSession);
////		File tempSimulationFolder = new File(sessionFolderPath);// + "tmp");
////		tempSimulationFolder.mkdir();
////		tempSimulationFolder.canWrite();
//		String omcMessage =	ExecuteSimulation.executeAllModels(null, sessionFolder, omcTempWorkingFolder, testSessionObj);
//		if(omcMessage.isEmpty()){
//			
//		}
//		else
//			System.out.println("OMC Message: /n" + omcMessage);
//		
//		for(TestModel model : testSessionObj.testModels){
//			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + ".exe", sessionFolder + "/" + model.qualifiedName + ".exe");
//			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml", sessionFolder + "/" + model.qualifiedName + "_init.xml");
////			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.plt");
//			try {
//				SimulationResult_XML_generator.createXML(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", sessionFolder + "/" + model.qualifiedName + "_res.xml");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}//done
//		
//		
////		ProgressMonitorDialog dialog = new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
////		try {
////			dialog.run(true, true, new IRunnableWithProgress() {
////				@Override
////				public void run(IProgressMonitor monitor) {
////					monitor
////							.beginTask("Doing something timeconsuming here",
////									100);
////					for (int i = 0; i < 10; i++) {
////						if (monitor.isCanceled())
////							return;
////						monitor.subTask("I'm doing something here " + i);
////						try {
////							Thread.sleep(1000);
////						} catch (InterruptedException e) {
////							// TODO Auto-generated catch block
////							e.printStackTrace();
////						}
////						// worked increates the monitor, the values is added to the existing ones
////						monitor.worked(1);
////					}
////					monitor.done();
////				}
////			});
////		} catch (InvocationTargetException e) {
////			e.printStackTrace();
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//		
//		ParseJavaScript.parseJSTemp_generateJSFile(pathToSession);	
//	}
}
