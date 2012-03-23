package org.openmodelica.modelicaml.simulation.execution;
import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.openmodelica.modelicaml.common.constants.Constants;
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
	
//		omccRef = omcc; // used to quit simulations
		String omcReturnString = "";
		
		// use this command in order to get the OpenModelica temp folder
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
		else {
			omcReturnString = "";
		}
		
		//Load package.mo
		String loadResult = load(monitor, sessionFolder, omcc, testSessionObj, omcReturnString);
		
		String simulateResult = check_and_simulate(monitor, omcc, testSessionObj, omcReturnString);
		
		omcc.quit();
		
//		System.err.println("NO ERROR");

		omcReturnString = "\n\n" + loadResult + "\n" + simulateResult + "\n"; 
		return omcReturnString;
	}
	
	public static String getOMCWorkingDir(){
		OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
		return omcc.cd();
	}
	
//	private static OpenModelicaCompilerCommunication omccRef;
//	
//	public static void quitSimulations(){
//		if (omccRef != null) {
//			omccRef.quit();	
//		}
//	}
	
	private static String load(IProgressMonitor monitor, File sessionFolder, OpenModelicaCompilerCommunication omcc, TestSession testSessionObj, String omcReturnString) {
		{
			/*
			 * Load first code from code-sync folder.
			 * Note, if there is no such folder or a package.mo OMC will return false. This does not affect the remaining procedure.
			 */
			String codeSyncPath = sessionFolder + "/" + Constants.folderName_code_sync;
			while(codeSyncPath.contains("\\")){
				codeSyncPath = codeSyncPath.replace('\\', '/');
			}
			omcReturnString = omcc.loadFile(codeSyncPath.replaceAll("%20", " ") + "/package.mo"); //If the file is a directory the whole contained model will be loaded
			
			/*
			 * Load code from code-gen folder
			 */
			String packageMO = sessionFolder + "/" + testSessionObj.packageFileRelativePath;
//			System.out.println("packageMO: "+packageMO);
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

	private static String check_and_simulate(IProgressMonitor monitor, OpenModelicaCompilerCommunication omcc, TestSession testSessionObj, String omcReturnString) {
		String result = "";
		//Check Model
		for(TestModel model : testSessionObj.testModels){
			
			if (monitor.isCanceled()){
				omcc.quit();
			}
			else {
				monitor.subTask("Simulating: " + model.qualifiedName); 
				omcReturnString =  omcc.checkModel(model.qualifiedName);
				
				if(!omcReturnString.toLowerCase().contains("successfully") || omcReturnString.toLowerCase().contains("failed")){
					String errorString = omcc.getErrorString();
					//omcc.quit();
					//return "checkModel: " + omcReturnString  + "\nErrorString: " + errorString;
					result = result + "\n" + "checkModel: " + omcReturnString  + "\nErrorString: " + errorString;
				}
			
				//Simulate model
				omcReturnString = omcc.simulate(model.qualifiedName, model.start, model.stop, model.numberOfIntervals, model.tolerance, model.solver, model.outputFormat);
				
				if(omcReturnString.contains("{\"\",\"\"}") || omcReturnString.contains("Error")){
//					System.err.println("buildModel ERROR");
					String errorString = omcc.getErrorString();
					//omcc.quit();
					//return "buildModel: " + omcReturnString  + "\nErrorString: " + errorString;
					result = result + "\n" + "buildModel: " + omcReturnString  + "\nErrorString: " + errorString;
				}
			}
		}
		return result;
	}

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		String pathToSession = "C:/Projects/ModelicaML/runtime-New_configuration/modelicaml.example.potableWaterSystem_v30/verification-gen/verification-session_20120124110026\\";
//		
//		TestSession testSessionObj = TestSessionXML_Reader.readFromXML(pathToSession + "verification_session.xml");
//		String omcTempWorkingFolder = System.getenv().get("OPENMODELICAHOME") + "/tmp"; 
//		File sessionFolder = new File(pathToSession);
////		File tempSimulationFolder = new File(pathToSession + "tmp");
////		tempSimulationFolder.mkdir();
////		tempSimulationFolder.canWrite();
//		String omcMessage =	executeAllModels(null, sessionFolder, omcTempWorkingFolder, testSessionObj);
//		if(omcMessage.isEmpty()){
//			
//		}
//		else
//			System.out.println("OMC Message: /n" + omcMessage);
//		
//		for(TestModel model : testSessionObj.testModels){
//			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + ".exe", pathToSession + "/" + model.qualifiedName + ".exe");
//			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_init.xml", pathToSession + "/" + model.qualifiedName + "_init.xml");
//			cp.copyFile(omcTempWorkingFolder + "/" + model.qualifiedName + "_res.plt", pathToSession + "/" + model.qualifiedName + "_res.plt");
//		}
//	}

}
