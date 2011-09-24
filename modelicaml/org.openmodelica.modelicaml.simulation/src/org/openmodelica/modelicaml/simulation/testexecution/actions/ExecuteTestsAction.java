package org.openmodelica.modelicaml.simulation.testexecution.actions;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
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
			
			// TODO: Start the execution as a job.
			//TODO 20110924 change to dynamic path
			String sessionFolderPath = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20110924175101\\";
			
			TestSession testSessionObj = TestSessionXML_Reader.readFromXML(sessionFolderPath);
			String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; //TODO 20110924 in call method packen
			File sessionFolder = new File(sessionFolderPath);
			File tempSimulationFolder = new File(sessionFolderPath + "tmp");
			tempSimulationFolder.mkdir();
			tempSimulationFolder.canWrite();
			String omcMessage =	ExecuteSimulation.executeAllModels(sessionFolder, omcTempWorkingFolder, testSessionObj);
			if(omcMessage.isEmpty()){
				
			}
			else
				System.out.println("OMC Message: /n" + omcMessage);
		
			for(TestModel model : testSessionObj.testModels){
				cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + ".exe", tempSimulationFolder + "/" + model.qualifiedName + ".exe");
				cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml", tempSimulationFolder + "/" + model.qualifiedName + "_init.xml");
//				cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.plt");
				try {
					SimulationResult_XML_generator.createXML(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.xml");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
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
		String sessionFolderPath = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20110924175101\\";
		
		TestSession testSessionObj = TestSessionXML_Reader.readFromXML(sessionFolderPath + "test_session.xml");
		String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; 
		File sessionFolder = new File(sessionFolderPath);
		File tempSimulationFolder = new File(sessionFolderPath + "tmp");
		tempSimulationFolder.mkdir();
		tempSimulationFolder.canWrite();
//		String omcMessage =	ExecuteSimulation.executeAllModels(sessionFolder, omcTempWorkingFolder, testSessionObj);
//		if(omcMessage.isEmpty()){
//			
//		}
//		else
//			System.out.println("OMC Message: /n" + omcMessage);
		
		for(TestModel model : testSessionObj.testModels){
			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + ".exe", tempSimulationFolder + "/" + model.qualifiedName + ".exe");
			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml", tempSimulationFolder + "/" + model.qualifiedName + "_init.xml");
//			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.plt");
			try {
				SimulationResult_XML_generator.createXML(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.xml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//done
		// read xml results and update jscript file
	}
}
