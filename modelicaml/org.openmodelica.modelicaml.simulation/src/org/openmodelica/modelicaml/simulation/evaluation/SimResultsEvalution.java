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
package org.openmodelica.modelicaml.simulation.evaluation;

import java.io.File;
import java.util.HashSet;

import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.simulation.simresults.IResultsReader;
import org.openmodelica.modelicaml.simulation.simresults.ReadMatlab4;

/**
 * Class to evaluate results of a given property to a condition
 * @author parham.vasaiely
 *
 */
public class SimResultsEvalution {
	
	/**
	 * Evaluate the values of a property to be at least one time true
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	
	private static String resultsNotfound 	= "resultsNotfound";
	private static String fileNotFound 		= "fileNotFound";
	private static String checkModeUnknown 	= "checkModeUnknown";
	
	public static IResultsReader reader;
	
	
	
	private static Boolean checkAtLeastOneTimeTrue(double[] values){
		Double valueToCheck = Double.valueOf("1");
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if( d.equals(valueToCheck) ){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Evaluate the values of a property to be at least one time false
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static Boolean checkAtLeastOneTimeFalse(double[] values){
		Double valueToCheck = Double.valueOf("0");
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if( d.equals(valueToCheck) ){
				return true;
			}
		}
		return false ;
	}
	
	/**
	 * Evaluate the values of a property to be always true
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static Boolean checkAlwaysTrue(double[] values){
		Double valueToCheck = Double.valueOf("1");
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if( !d.equals(valueToCheck) ){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Evaluate the values of a property to be always false
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static Boolean checkAlwaysFalse(double[] values){

		Double valueToCheck = Double.valueOf("0");
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if( !d.equals(valueToCheck)){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Evaluate the values of a property to be changed its value
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static Boolean checkHadNotAlwaysValue(double[] values, String argsString){
		/*
		 * We assume that it always had the value until we prove the opposite.
		 */
		Double valueToCheck = Double.valueOf(argsString);
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if( !valueToCheck.equals(d) ){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Evaluate the values of a property to be changed its value
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static Boolean checkHadAtLeastOnceValue(double[] values, String argsString){
		/*
		 * We assume that it never had the value until we prove the opposite.
		 */
		Double valueToCheck = Double.valueOf(argsString);
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if( valueToCheck.equals(d) ){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Evaluate the values of a property to be changed its value
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static Boolean checkHadNeverValue(double[] values, String argsString){
		/*
		 * We assume that it had the value until we prove the opposite.
		 */
		Double valueToCheck = Double.valueOf(argsString);
		HashSet<Double> dValues = new HashSet<Double>();
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			dValues.add(d);
		}
		if(dValues.contains(valueToCheck))
			return false;
		else
			return true;
	}
	
	
	/**
	 * Evaluate the values of a property to be changed its value
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static Boolean checkChangedItsValue(double[] values){
		Double firstValue = null;
		for (int i = 0; i < values.length; i++) {
			Double d = values[i];
			if(firstValue != null)
				if(!firstValue.equals(d)){
					return false;
				}
			else{
				firstValue = d;
			}
		}
		return true;
	}
	
	/**
	 * Evaluates the results of a property using a given check mode
	 * @param varName name of the property which is going to be evaluated
	 * @param checkMode used to evaluate the results of a property, the following check modes are allowed: atLeastOneTimeTrue, atLeastOneTimeFalse, alwaysTrue, alwaysFalse, changedItsValue
	 * @param resultFilePath absolut path to the results XML
	 * @return result of the evaluation as a String, the result can be: true, false, resultsNotfound, fileNotFound, checkModeUnknow
	 * @throws Exception 
	 */
	public static String evaluateProperty(String varName, String checkMode, String resultFilePath) throws Exception{
		
		File resultFile = new File(resultFilePath);
		
		if(!resultFile.exists() || !resultFile.canRead())
			return fileNotFound;
		
		// get the appropriate reader
		if (resultFilePath.endsWith(".mat")) {
			// if there is already a reader for this file
			if (reader instanceof ReadMatlab4 && reader.getFilePath().equals(resultFilePath)) {
				// ok, continue
			}
			else {
				// create a new reader
				reader = new ReadMatlab4(resultFilePath);
			}
		}
		
		double[] values = null;
		// get values
		try {
			values = reader.getValues(varName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// check if values is not empty
		if (values != null && values.length > 0) {
			// ok
		}
		else {
			return resultsNotfound;
		}
		
		
		if(checkMode.equals(Constants.MACRO_atLeastOneTimeTrue))
			return checkAtLeastOneTimeTrue(values).toString();		
		
		if(checkMode.equals(Constants.MACRO_atLeastOneTimeFalse))
			return checkAtLeastOneTimeFalse(values).toString();
		
		if(checkMode.equals(Constants.MACRO_alwaysTrue))
			return checkAlwaysTrue(values).toString();
		
		if(checkMode.equals(Constants.MACRO_alwaysFalse))
			return checkAlwaysFalse(values).toString();
		
		if(checkMode.equals(Constants.MACRO_changedItsValue))
			return checkChangedItsValue(values).toString();
		
		if(checkMode.contains(Constants.MACRO_hadNotAlwaysValue))
			return checkHadNotAlwaysValue(values, getPropertyValueArg(checkMode.replaceFirst(Constants.MACRO_hadNotAlwaysValue, ""))).toString();
		
		if(checkMode.contains(Constants.MACRO_hadAtLeastOnceValue))
			return checkHadAtLeastOnceValue(values, getPropertyValueArg(checkMode.replaceFirst(Constants.MACRO_hadAtLeastOnceValue, ""))).toString();

		if(checkMode.contains(Constants.MACRO_hadNeverValue))
			return checkHadNeverValue(values, getPropertyValueArg(checkMode.replaceFirst(Constants.MACRO_hadNeverValue, ""))).toString();
		
		else
			return checkModeUnknown;
	}

	
	private static String getPropertyValueArg(String string){
		if (string.matches("(\\s+)?\\([0-9]+(\\.)?([0-9]+)?\\)")) {
			String stringInBrackets = string.trim();
			String argsString = stringInBrackets.substring(1, stringInBrackets.length() - 1).trim();
			return argsString;
		}
		return string;
	}
	
	
//	/**
//	 * The main method.
//	 *
//	 * @param args the arguments
//	 */
//	public static void main(String[] args) {
////		File f = new File("");
////		String p = f.getAbsolutePath();
////		p = p.substring(0, p.lastIndexOf(System.getProperty("file.separator")));
////		File f2 = new File(p + "\\org.openmodelica.simulation.projects\\");
////		System.out.println(f2.list()[0]);
//		
//		String pathToResultXML = "C:/Projects/ModelicaML/runtime-New_configuration/modelicaml.example.potableWaterSystem_v30/verification-gen/verification-session_20120124110026\\ModelicaMLModel.GenVeMs_for__SPWS_Environment_1.VeM_for__s1_Fill_and_Drain_Tank_res.xml";
//		
////		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", projectPath);
//		String propertyName = "_reqVerificationVerdict.allRequirementsEvaluated";
//		
//		System.out.println(checkAtLeastOneTimeTrue(propertyName, new File(pathToResultXML)));
//		System.out.println(checkAtLeastOneTimeFalse(propertyName, new File(pathToResultXML)));
//		System.out.println(checkAlwaysTrue(propertyName, new File(pathToResultXML)));
//		System.out.println(checkAlwaysFalse(propertyName, new File(pathToResultXML)));
//		System.out.println(checkChangedItsValue(propertyName, new File(pathToResultXML)));
//	}
}
