package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.simulation.evaluation.ParseJavaScript;
import org.openmodelica.modelicaml.simulation.execution.ExecuteSimulation;
import org.openmodelica.modelicaml.simulation.filehandling.cp;
import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_generator;
import org.openmodelica.modelicaml.simulation.xml.TestSession;
import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;
import org.openmodelica.modelicaml.simulation.xml.TestSessionXML_Reader;

public class ExecuteTestsAction implements
		IWorkbenchWindowActionDelegate {

	private String xmlFilePath = null;
	
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
		
		if (this.xmlFilePath != null) {
			
			//START of the execution as a job. 
			//TODO 20110924 change to dynamic path
			
			//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()

			Job job = new Job("Simulating and Evaluating Test Results") {

				protected IStatus run(IProgressMonitor monitor) {

						monitor.beginTask("Doing something timeconsuming here", 100);
						{
							String sessionFolderPath = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20111021101526\\";
							
							TestSession testSessionObj = TestSessionXML_Reader.readFromXML(sessionFolderPath + "test_session.xml");
							String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; 
							File sessionFolder = new File(sessionFolderPath);
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
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							ParseJavaScript.parseJSTemp_generateJSFile(sessionFolderPath);	
						}
						monitor.done();
						return Status.OK_STATUS;
					}
			};
			job.setUser(true);
			job.schedule();


			

			
			//END of the execution as a job.
			
			// TODO: When finished -> run the update action 
			// find the js-files path 
//			UpdateTestExecutionReportDataAction updateAction = new UpdateTestExecutionReportDataAction();
			//updateAction.setPath();
//			// ...
//			updateAction.run(null);
			
			// TODO: When finished -> open report
			// find the html file path 
//			OpenTestExecutionReportAction openReportAction = new OpenTestExecutionReportAction();
//			openReportAction.setPath(html file path);
//			openReportAction.run(null);
		}
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
	
	public static void main(String[] args) {
		String sessionFolderPath = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20111021101526";
		
//		TestSession testSessionObj = TestSessionXML_Reader.readFromXML(sessionFolderPath + "test_session.xml");
//		String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; 
//		File sessionFolder = new File(sessionFolderPath);
////		File tempSimulationFolder = new File(sessionFolderPath);// + "tmp");
////		tempSimulationFolder.mkdir();
////		tempSimulationFolder.canWrite();
//		String omcMessage =	ExecuteSimulation.executeAllModels(sessionFolder, omcTempWorkingFolder, testSessionObj);
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
		
		
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		try {
			dialog.run(true, true, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) {
					monitor
							.beginTask("Doing something timeconsuming here",
									100);
					for (int i = 0; i < 10; i++) {
						if (monitor.isCanceled())
							return;
						monitor.subTask("I'm doing something here " + i);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// worked increates the monitor, the values is added to the existing ones
						monitor.worked(1);
					}
					monitor.done();
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		ParseJavaScript.parseJSTemp_generateJSFile(sessionFolderPath);	
	}
}
