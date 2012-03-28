package org.openmodelica.modelicaml.simulation.execution;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.simulation.omc.OpenModelicaCompilerCommunication;
import org.openmodelica.modelicaml.simulation.xml.TestSession;
import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;

public class ExecuteSimulation {

	/**
	 * Compiling and simulating the model code using OMC
	 * Error Handling : Syntax, Semantic check by OMC.
	 *
	 * @return the omc return string, if the string is empty there was no error otherwise the error string is included
	 */
	
	private static OpenModelicaCompilerCommunication omcc;
	
	public static String executeAllModels(IProgressMonitor monitor, File sessionFolder, String omcTempWorkingFolder, TestSession testSessionObj, boolean loadMSL){
		if (omcc == null) {
			omcc = new OpenModelicaCompilerCommunication();
		}
		String omcReturnString = "";
		
		// use this command in order to get the OpenModelica temp folder
		while(omcTempWorkingFolder.contains("\\")){
			omcTempWorkingFolder = omcTempWorkingFolder.replace('\\', '/');
		}
		
		//Clear Workspace
		omcc.clear();
		
		if (loadMSL) {
			omcc.loadModel("Modelica");
		}
		
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
	
	
	public static String getTempDirectoryPath(){
		if (omcc == null) {
			omcc = new OpenModelicaCompilerCommunication();
		}
		return omcc.getTempDirectoryPath();
	}
	
	public static void loadMSL(){
		omcc.loadModel("Modelica");
	}
	
	private static String load(IProgressMonitor monitor, File sessionFolder, OpenModelicaCompilerCommunication omcc, TestSession testSessionObj, String omcReturnString) {
		{
			
			String folderPath = sessionFolder.toString();
			List<String> filesToLoad = new ArrayList<String>();
			
			// First files from the code-sync, then files from code-gen
			filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(folderPath + "/" + Constants.folderName_code_sync));
			filesToLoad.addAll(ModelicaMLServices.getFilesToLoad(folderPath + "/" + Constants.folderName_code_gen));
			
			// load all files 
			for (String string : filesToLoad) {
				String reply = omcc.loadFile(string);
//				omcReturnString = omcReturnString + "\n" + "Loading '" + string + "' -> " + reply;
			}
			
//			
//			/*
//			 * Load first code from code-sync folder.
//			 * Note, if there is no such folder or a package.mo OMC will return false. This does not affect the remaining procedure.
//			 */
//			
//			
//			String codeSyncPath = sessionFolder + "/" + Constants.folderName_code_sync;
//			while(codeSyncPath.contains("\\")){
//				codeSyncPath = codeSyncPath.replace('\\', '/');
//			}
//			omcReturnString = omcc.loadFile(codeSyncPath.replaceAll("%20", " ") + "/package.mo"); //If the file is a directory the whole contained model will be loaded
//			
//			/*
//			 * Load code from code-gen folder
//			 */
//			String packageMO = sessionFolder + "/" + testSessionObj.packageFileRelativePath;
////			System.out.println("packageMO: "+packageMO);
//			while(packageMO.contains("\\")){
//				packageMO = packageMO.replace('\\', '/');
//			}
//			
//			if(new File(packageMO).isDirectory()){
//				omcReturnString = omcc.loadFile(packageMO.replaceAll("%20", " ") + "/package.mo"); //If the file is a directory the whole contained model will be loaded
//				if(omcReturnString.toLowerCase().contains("false") || omcReturnString.toLowerCase().contains("error")){
//					String errorString = omcc.getErrorString();
//					omcc.quit();
//					return "loadFile: " + omcReturnString + "\nErrorString: " + errorString;
//				}
//			}
//			else if(new File(packageMO).isFile()){
//				omcReturnString = omcc.loadFile(packageMO);
//				if(omcReturnString.toLowerCase().contains("false") || omcReturnString.toLowerCase().contains("error")){
//					String errorString = omcc.getErrorString();
//					omcc.quit();
//					return "loadFile: " + omcReturnString + "\nErrorString: " + errorString;
//				}
//			}
		}
//		return omcReturnString = "";
		return omcReturnString;
	}

	
//	private static List<String> getFilesToLoad(String folderPath){
//		List<String> list = new ArrayList<String>();
//
//		File folder = new File(folderPath);
//		
//		if (folder.exists() && folder.isDirectory()) {
//			
//			// 1.Level
//			
//			// load all .mo files that are not pacakge.mo
//			File[] files1Level = folder.listFiles();
//			for (File file : files1Level) {
//				if (file.isFile() && !file.getName().equals("package.mo")) {
//					list.add(formatPath(folderPath + "/" + file.getName()));
//				}
//			}
//			
//			// load a package.mo at 1.level 
//			File packageMo = new File(folderPath + "/" + "pacakge.mo");
//			if ( packageMo.exists()) {
//				list.add(formatPath(folderPath + "/" + "pacakge.mo"));
//			}
//			// if there is no package.mo at 1.Level -> look into sub-folders and find package.mo files there
//			else {
//				// 2.Level
//				File[] files2Level = folder.listFiles();
//				for (File file : files2Level) {
//					if (file.isDirectory()) {
//						String subFolderPackageMoPath = folderPath + "/" + file.getName() + "/package.mo";
//						File subFolderPackageMo = new File(subFolderPackageMoPath);
//						if (subFolderPackageMo.exists()) {
//							list.add(formatPath(subFolderPackageMoPath));
//						}
//					}
//				}
//			}
//		}
//		return list;
//	}
	
	
//	private static String formatPath(String path){
//		while(path.contains("\\")){
//			path = path.replace('\\', '/');
//		}
//		path = path.replaceAll("%20", " ");
//		return path;
//	}

	
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
			
				// set default simulation settings
				if (model.numberOfIntervals.trim().length() == 0) {
					model.numberOfIntervals = "500";
				}
				if (model.tolerance.trim().length() == 0 ) {
					model.tolerance = "0.000001";
				}
				if (model.solver.trim().length() == 0 ) {
					model.solver = "dassl";
				}
				if (model.outputFormat.trim().length()==0 ) {
					model.outputFormat = "plt";
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
