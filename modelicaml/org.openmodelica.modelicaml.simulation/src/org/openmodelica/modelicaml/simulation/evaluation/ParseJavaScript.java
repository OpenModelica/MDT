package org.openmodelica.modelicaml.simulation.evaluation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.Scanner;

public class ParseJavaScript {
	/** The raf. */
	private static RandomAccessFile raf;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pathToJSFiles = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20110924175101\\report-gen\\includes\\";
		String result_xml_file = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20110924175101\\tmp\\ModelicaMLModel.GenSimModels_for__SPWS_Environment_1.SiM_for__ts2__Fill_tank_res.xml";
		
		parseJSTemp_generateJSFile(result_xml_file, pathToJSFiles);		
	}
	
	/**
	 * Parses the JavaScript template file to generate a new JavaScript file including the evaluated results
	 * @param result_xml_file result file including properties and results to be evaluated
	 * @param pathToJSFiles path to the report-gen\includes folder
	 */
	public static void parseJSTemp_generateJSFile(String result_xml_file, String pathToJSFiles){
		File initFile = new File(pathToJSFiles + "/report_data.js");
		
		Writer fw = null; 
		
		File f = new File(pathToJSFiles + "/report_data.js");
		try {
			fw = new FileWriter(initFile); 
			f.createNewFile();
			
			
			raf = new RandomAccessFile( pathToJSFiles + "/report_data.js_template", "r");
			fw.write(raf.readLine());
			fw.append( System.getProperty("line.separator") );
			boolean hasDataSet = true;
			while(hasDataSet){
				String line = raf.readLine();
//				System.out.println(line);
				if(line != null){
					if(line.contains("###")){
						line = startEvaluation(line, result_xml_file);
					}
					fw.append(line);
					fw.append( System.getProperty("line.separator") );
//					System.out.println("Line: " + line);
				} else
					hasDataSet= false;
					
			}
			raf.close();
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

	private static String startEvaluation(String line, String result_xml_file) {
		String evaluatedLine = "";
		Scanner sc = new Scanner(line);
		sc.useDelimiter("###");
		evaluatedLine = sc.next();
		Scanner sc2 = new Scanner(sc.next());
		sc2.useDelimiter(":");
		//_reqTestVerdict.testPassed:atLeastOneTimeTrue
		String evaluateionResult = EvaluateResultXML.evaluateProperty(sc2.next(), sc2.next(), result_xml_file);
		evaluatedLine = evaluatedLine + evaluateionResult + sc.next();
		return evaluatedLine;
	}

}
