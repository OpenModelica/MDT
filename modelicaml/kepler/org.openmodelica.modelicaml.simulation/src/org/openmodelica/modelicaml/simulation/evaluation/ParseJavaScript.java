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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.simulation.evaluation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.Scanner;

import org.openmodelica.modelicaml.simulation.simresults.IResultsReader;

public class ParseJavaScript {
	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		String pathToSession = "C:/Projects/ModelicaML/runtime-New_configuration/modelicaml.example.potableWaterSystem_v30/verification-gen/verification-session_20120124110026\\";
//		//String result_xml_file = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20110927133331\\tmp\\ModelicaMLModel.GenSimModels_for__SPWS_Environment_6.SiM_for__ts2__Fill_tank_res.xml";
//		
//		parseJSTemp_generateJSFile(pathToSession);
//	}
	
/**
 * Parses the JavaScript template file to generate a new JavaScript file including the evaluated results
 * @param pathToSession, path to the verification session folder
 * @throws Exception 
 */
	public static void parseJSTemp_generateJSFile(String pathToSession) throws Exception{
		/** The raf. */
		RandomAccessFile raf;
		File initFile = new File(pathToSession + "/report-gen/includes/report_data.js");
		
		Writer fw = null; 
		
		File f = new File(pathToSession + "/report-gen/includes/report_data.js");
		try {
			fw = new FileWriter(initFile); 
			f.createNewFile();			
			
			raf = new RandomAccessFile( pathToSession + "/report-gen/includes/report_data.js_template", "r");
			fw.write(raf.readLine());
			fw.append( System.getProperty("line.separator") );
			boolean hasDataSet = true;
			while(hasDataSet){
				String line = raf.readLine();
//				System.out.println(line);
				if(line != null){
				if(line.contains("{$START}")){ ///*START*/
//					System.out.println("START");
					fw.append(line); // add /*START*/
					fw.append( System.getProperty("line.separator") );
					line = raf.readLine();
					if(line != null){
						fw.append(line); // add /*RESULTS_FILE_RELATIVE_PATH=...
						fw.append( System.getProperty("line.separator") );
						String resultFilePath;
						Scanner sc = new Scanner(line);
						sc.useDelimiter("###");
						sc.next(); //first part which is "RESULTS_FILE_RELATIVE_PATH="
						resultFilePath = sc.next(); // Second part which is the XML result file
						line = startIteration(fw, raf, pathToSession + "/" + resultFilePath);
					}
				}
					fw.append(line);
					fw.append( System.getProperty("line.separator") );
//					System.out.println("Line: " + line);
				} else{
					hasDataSet= false;
				}
					
			}
			raf.close();
			
			// release the simulation results file after parsing
			IResultsReader reader = SimResultsEvalution.reader;
			if (reader != null) {
				reader.releaseFile();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally { 
			  if ( fw != null ) 
			    try { fw.close(); } catch ( IOException e ) { } 
		}
	}

	/**
	 * One iteration block covers the evaluation of one single simulation model and it's result file. In the JavaScript file it starts with {$START} and ends with {$END}
	 * @param fw is the report_data.js
	 * @param raf is the "report_data.js_template"
	 * @param resultFilePath Result XML file which needs to be evaluated
	 * @return a String containing the last line of an iteration "{$END}" or ERROR if the iteration block is not marked by "{$END}"
	 * @throws Exception 
	 */
	private static String startIteration(Writer fw, RandomAccessFile raf, String resultFilePath) throws Exception{
		boolean hasDataSet = true;
		while(hasDataSet){
			String line = raf.readLine();
//			System.out.println(line);
			if(line != null){
				if(line.contains("{$END}")){
//					fw.append(line);
//					fw.append( System.getProperty("line.separator") );
					return line;
				}
				if(line.contains("###")){
					line = startEvaluation(line, resultFilePath);
//					System.err.println("New Line: " + line);
				}
				fw.append(line);
//				System.out.println("Line: " + line);
				fw.append( System.getProperty("line.separator") );
			} else{
				hasDataSet= false;
			}
		}
		return "ERROR"; // this line should never been reached since the end of an iteration (start-end) should marked by {$END}
	}

	private static String startEvaluation(String line, String resultFilePath) throws Exception {
		
		String evaluatedLine;
		
		Scanner sc = new Scanner(line);
		sc.useDelimiter("###");
		String firstPart = sc.next(); //first part starting with data[...
		String evaluationLine = "";
		evaluationLine = sc.next(); //attribute + check mode
		
		Scanner sc2 = new Scanner(evaluationLine);
		sc2.useDelimiter(":");
		String property = sc2.next();
		String checkMode = sc2.next();
		
		String endPart = sc.next(); //first part starting with data[...

//		String evaluatedResult = EvaluateResultXML.evaluateProperty(property, checkMode, resultFilePath);
		String evaluatedResult = SimResultsEvalution.evaluateProperty(property, checkMode, resultFilePath);
		evaluatedLine = firstPart + evaluatedResult + endPart;
//		System.out.println(property + ", checkMode: " + checkMode + ", Result: " + evaluatedResult);
//		System.err.println("evaluatedLine: " + evaluatedLine);
		
		return evaluatedLine;
	}
}
