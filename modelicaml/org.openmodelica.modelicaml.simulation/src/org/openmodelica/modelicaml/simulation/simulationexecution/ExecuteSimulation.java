package org.openmodelica.modelicaml.simulation.simulationexecution;
import java.io.File;

import org.openmodelica.modelicaml.simulation.omc.OpenModelicaCompilerCommunication;
import org.openmodelica.modelicaml.simulation.xml.TestSession;
import org.openmodelica.modelicaml.simulation.xml.TestSession.TestModel;
import org.openmodelica.modelicaml.simulation.xml.TestSessionXML_Reader;

public class ExecuteSimulation {

	/**
	 * Compiling the model code using OMC
	 * Error Handling : Syntax, Semantic check by OMC.
	 *
	 * @return the string
	 */
	private static String compileModel(File sessionFolder, File tempSimulationFolder, TestSession testSessionObj){
		OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
		String omcReturnString = "";
		String pathForOMC = tempSimulationFolder.getAbsolutePath();
		
		while(pathForOMC.contains("\\")){
			pathForOMC = pathForOMC.replace('\\', '/');
		}
		
		//Clear Workspace
		omcc.clear();
		
		//Change directory
		omcReturnString = omcc.cd();
		if(omcReturnString.contains("Error")){
			String errorString = omcc.getErrorString();
			omcc.quit();
			return "cd: " + omcReturnString + "\nErrorString: " + errorString;
		}
		else{
			File omcWorkingDirectory = new File(omcReturnString);
//			omcReturnString = ""; //TODO cd muss hier raus und nach oben gepackt werden!
		}
		
		//Load package.mo
		{
		String packageMO = sessionFolder + "/" + testSessionObj.packageFileRelativePath;
			System.out.println("packageMO: "+packageMO);
			while(packageMO.contains("\\")){
				packageMO = packageMO.replace('\\', '/');
			}
			//TODO [20110224] Check for true or false
			if(new File(packageMO).isDirectory()){
				omcReturnString = omcc.loadFile(packageMO.replaceAll("%20", " ") + "/package.mo"); //If the file is a directory the whole contained model will be loaded
				if(omcReturnString.toLowerCase().contains("false") || omcReturnString.toLowerCase().contains("error")){
					String errorString = omcc.getErrorString();
					omcc.quit();
					return "loadFile: " + omcReturnString + "\nErrorString: " + errorString;
				}
				else
					omcReturnString = "";
			}
			else if(new File(packageMO).isFile()){
				omcReturnString = omcc.loadFile(packageMO);
				if(omcReturnString.toLowerCase().contains("false") || omcReturnString.toLowerCase().contains("error")){
					String errorString = omcc.getErrorString();
					omcc.quit();
					return "loadFile: " + omcReturnString + "\nErrorString: " + errorString;
				}
				else
					omcReturnString = "";
			}
		}
		
		
		//Check Model
		for(TestModel model : testSessionObj.testModels){
			omcReturnString =  omcc.checkModel(model.qualifiedName);
			
			if(!omcReturnString.toLowerCase().contains("successfully") || omcReturnString.toLowerCase().contains("failed")){
				String errorString = omcc.getErrorString();
				omcc.quit();
				return "checkModel: " + omcReturnString  + "\nErrorString: " + errorString;
			}
			else
				omcReturnString = "";
		}		
		
		//Simulate model
		for(TestModel model : testSessionObj.testModels){
			omcReturnString = omcc.buildModel(model.qualifiedName, model.start, model.stop, model.numberOfIntervals, model.tolerance, model.solver, model.outputFormat);
			
			if(omcReturnString.contains("{\"\",\"\"}") || omcReturnString.contains("Error")){
//			System.err.println("buildModel ERROR");
				String errorString = omcc.getErrorString();
				omcc.quit();
				return "buildModel: " + omcReturnString  + "\nErrorString: " + errorString;
			}
			else
				omcReturnString = "";
		}
		
		omcc.quit();
		
//		System.err.println("NO ERROR");
		return omcReturnString;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sessionFolderPath = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v25\\test-gen\\test-session_20110922235427\\";
		
		TestSession testSessionObj = TestSessionXML_Reader.readFromXML(sessionFolderPath + "test_session.xml");
		
		File sessionFolder = new File(sessionFolderPath);
		File tempSimulationFolder = new File(sessionFolderPath + "tmp");
			tempSimulationFolder.mkdir();
			tempSimulationFolder.canWrite();
		String omcMessage =	compileModel(sessionFolder, tempSimulationFolder, testSessionObj);
		if(omcMessage.isEmpty()){
			
		}
		else
			System.out.println("OMC Message: /n" + omcMessage);
	}

}
