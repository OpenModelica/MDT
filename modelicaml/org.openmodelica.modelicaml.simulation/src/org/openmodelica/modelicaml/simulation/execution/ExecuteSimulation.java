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
package org.openmodelica.modelicaml.simulation.execution;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
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
	
	private static String omcTempDirectoryPath;
	
	public static String executeAllModels(IProgressMonitor monitor, File sessionFolder, String omcTempWorkingFolder, TestSession testSessionObj, boolean loadMSL){
		OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
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
//			omcc.quit();
			return "cd: " + omcReturnString + "\nErrorString: " + errorString;
		}
		else {
			omcReturnString = "";
		}
		
		//Load package.mo
		String loadResult = load(monitor, sessionFolder, omcc, testSessionObj, omcReturnString);
		
		String simulateResult = check_and_simulate(monitor, omcc, testSessionObj, omcReturnString);
		
		// DO NOT quit! 
//		omcc.quit();

		omcReturnString = "\n\n" + loadResult + "\n" + simulateResult + "\n"; 
		return omcReturnString;
	}
	
	
	public static String getTempDirectoryPath(){
		if (omcTempDirectoryPath == null) {
			OpenModelicaCompilerCommunication omcc = new OpenModelicaCompilerCommunication();
			omcTempDirectoryPath = omcc.getTempDirectoryPath();
		}
		return omcTempDirectoryPath;
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
			}
		}

		return omcReturnString;
	}

	
	private static String check_and_simulate(IProgressMonitor monitor, OpenModelicaCompilerCommunication omcc, TestSession testSessionObj, String omcReturnString) {
		String result = "";
		//Check Model
		for(TestModel model : testSessionObj.testModels){
			
			if (monitor.isCanceled()){
//				omcc.quit();
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
				if (model.start==null|| model.start.trim().length() == 0) {
					model.start = Constants.DEFAULT_startTime;
					model.start = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_startTime, model.start, null);
				}
				if (model.stop==null || model.stop.trim().length() == 0) {
					model.stop = Constants.DEFAULT_stopTime;
					model.stop = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_stopTime, model.stop, null);
				}
				if (model.numberOfIntervals==null|| model.numberOfIntervals.trim().length() == 0) {
					model.numberOfIntervals =  Constants.DEFAULT_numberOfIntervals;
					model.numberOfIntervals = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_numberOfIntervals, model.numberOfIntervals, null);
				}
				if (model.tolerance == null || model.tolerance.trim().length() == 0 ) {
					model.tolerance = Constants.DEFAULT_tolerance;
					model.tolerance = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_tolerance, model.tolerance, null);
				}
				if (model.solver == null || model.solver.trim().length() == 0 ) {
					model.solver = Constants.DEFAULT_solver;
					model.solver = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_solver, model.solver, null);
				}
				if (model.outputFormat == null || model.outputFormat.trim().length()==0 ) {
					model.outputFormat = Constants.DEFAULT_outputFormat;
					model.outputFormat = Platform.getPreferencesService().getString("org.openmodelica.modelicaml.preferences", Constants.propertyName_outputFormat, model.outputFormat, null);
				}
				
				//Simulate model
				String variableFilter = null; // no filter, i.e., all variables should be recorded.
				omcReturnString = omcc.simulate(
							model.qualifiedName, 
							model.start, 
							model.stop, 
							model.numberOfIntervals, 
							model.tolerance, 
							model.solver, 
							model.outputFormat,
							variableFilter
							);
				
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
