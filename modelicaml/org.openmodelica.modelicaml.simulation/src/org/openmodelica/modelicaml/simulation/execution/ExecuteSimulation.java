package org.openmodelica.modelicaml.simulation.execution;
import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.openmodelica.modelicaml.simulation.filehandling.cp;
import org.openmodelica.modelicaml.simulation.omc.OpenModelicaCompilerCommunication;
import org.openmodelica.modelicaml.simulation.xml.TestSession;
import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;
import org.openmodelica.modelicaml.simulation.xml.TestSessionXML_Reader;

public class ExecuteSimulation {

	/**
	 * Compiling and simulating the model code using OMC
	 * Error Handling : Syntax, Semantic check by OMC.
	 *
	 * @return the omc return string, if the string is empty there was no error otherwise the error string is included
	 */
	public static String executeAllModels(IProgressMonitor monitor, File sessionFolder, String omcTempWorkingFolder, TestSession testSessionObj){
		OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
		String omcReturnString = "";
		
		while(omcTempWorkingFolder.contains("\\")){
			omcTempWorkingFolder = omcTempWorkingFolder.replace('\\', '/');
		}
		
		//Clear Workspace
		omcc.clear();
		
		//OMC is working in the eclipse project folder, therefore the folder must been changed to the OM installation folder where a tmp folder can be created.
		omcReturnString = omcc.cd(omcTempWorkingFolder);		
		if(omcReturnString.contains("Error")){
			String errorString = omcc.getErrorString();
			omcc.quit();
			return "cd: " + omcReturnString + "\nErrorString: " + errorString;
		}
		else
			omcReturnString = "";
		
		//Load package.mo
		load(monitor, sessionFolder, omcc, testSessionObj, omcReturnString);
		
		
		check_simulate(monitor, omcc, testSessionObj, omcReturnString);
		
		omcc.quit();
		
//		System.err.println("NO ERROR");
		return omcReturnString;
	}
	
	private static String load(IProgressMonitor monitor, File sessionFolder, OpenModelicaCompilerCommunication omcc, TestSession testSessionObj, String omcReturnString) {
		{
			String packageMO = sessionFolder + "/" + testSessionObj.packageFileRelativePath;
				System.out.println("packageMO: "+packageMO);
				while(packageMO.contains("\\")){
					packageMO = packageMO.replace('\\', '/');
				}
				if(new File(packageMO).isDirectory()){
					omcReturnString = omcc.loadFile(packageMO.replaceAll("%20", " ") + "/package.mo"); //If the file is a directory the whole contained model will be loaded
					if(omcReturnString.toLowerCase().contains("false") || omcReturnString.toLowerCase().contains("error")){
						String errorString = omcc.getErrorString();
						omcc.quit();
						return "loadFile: " + omcReturnString + "\nErrorString: " + errorString;
					}
				}
				else if(new File(packageMO).isFile()){
					omcReturnString = omcc.loadFile(packageMO);
					if(omcReturnString.toLowerCase().contains("false") || omcReturnString.toLowerCase().contains("error")){
						String errorString = omcc.getErrorString();
						omcc.quit();
						return "loadFile: " + omcReturnString + "\nErrorString: " + errorString;
					}
				}
			}
		return omcReturnString = "";
		
	}

	private static String check_simulate(IProgressMonitor monitor, OpenModelicaCompilerCommunication omcc, TestSession testSessionObj, String omcReturnString) {

		//Check Model
		for(TestModel model : testSessionObj.testModels){
			monitor.subTask("Simulating: " + model.qualifiedName);
			omcReturnString =  omcc.checkModel(model.qualifiedName);
			
			if(!omcReturnString.toLowerCase().contains("successfully") || omcReturnString.toLowerCase().contains("failed")){
				String errorString = omcc.getErrorString();
				omcc.quit();
				return "checkModel: " + omcReturnString  + "\nErrorString: " + errorString;
			}
		
		//Simulate model
			omcReturnString = omcc.simulate(model.qualifiedName, model.start, model.stop, model.numberOfIntervals, model.tolerance, model.solver, model.outputFormat);
			
			if(omcReturnString.contains("{\"\",\"\"}") || omcReturnString.contains("Error")){
//			System.err.println("buildModel ERROR");
				String errorString = omcc.getErrorString();
				omcc.quit();
				return "buildModel: " + omcReturnString  + "\nErrorString: " + errorString;
			}
		}
		return "";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sessionFolderPath = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20110927133331\\";
		
		TestSession testSessionObj = TestSessionXML_Reader.readFromXML(sessionFolderPath + "test_session.xml");
		String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; 
		File sessionFolder = new File(sessionFolderPath);
		File tempSimulationFolder = new File(sessionFolderPath + "tmp");
		tempSimulationFolder.mkdir();
		tempSimulationFolder.canWrite();
		String omcMessage =	executeAllModels(null, sessionFolder, omcTempWorkingFolder, testSessionObj);
		if(omcMessage.isEmpty()){
			
		}
		else
			System.out.println("OMC Message: /n" + omcMessage);
		
		for(TestModel model : testSessionObj.testModels){
			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + ".exe", tempSimulationFolder + "/" + model.qualifiedName + ".exe");
			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml", tempSimulationFolder + "/" + model.qualifiedName + "_init.xml");
			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", tempSimulationFolder + "/" + model.qualifiedName + "_res.plt");
		}
	}

}
