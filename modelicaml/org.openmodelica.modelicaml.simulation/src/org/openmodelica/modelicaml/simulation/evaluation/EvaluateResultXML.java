package org.openmodelica.modelicaml.simulation.evaluation;

import java.io.File;

import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_reader;

/**
 * Class to evaluate results of a given property to a condition
 * @author parham.vasaiely
 *
 */
public class EvaluateResultXML {
	
	/**
	 * Evaluate the values of a property to be at least one time true
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static String checkAtLeastOneTimeTrue(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML(propertyName, xml_file.getAbsoluteFile());
//		System.out.println("#1-> " + sr.property);
		
		if(sr == null || sr.property.isEmpty()){
//			System.out.println("#2-> " + propertyName + " resultsNotfound");
			return "resultsNotfound";
		}
		
		if(sr.values.values().contains(1.0))
			return "true";
		else
			return "false";
	}
	
	/**
	 * Evaluate the values of a property to be at least one time false
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static String checkAtLeastOneTimeFalse(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML(propertyName, xml_file.getAbsoluteFile());

		if(sr == null || sr.property.isEmpty()){
			return "resultsNotfound";
		}
		
		if(sr.values.values().contains(0.0))
			return "true";
		else
			return "false";
	}
	
	/**
	 * Evaluate the values of a property to be always true
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static String checkAlwaysTrue(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML(propertyName, xml_file.getAbsoluteFile());

		if(sr == null || sr.property.isEmpty()){
			return "resultsNotfound";
		}
		
		if(sr.values.values().contains(0.0))
			return "false";
		else
			return "true";
	}
	
	/**
	 * Evaluate the values of a property to be always false
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static String checkAlwaysFalse(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML(propertyName, xml_file.getAbsoluteFile());

		if(sr == null || sr.property.isEmpty()){
			return "resultsNotfound";
		}
		
		if(sr.values.values().contains(1.0))
			return "false";
		else
			return "true";
	}
	
	/**
	 * Evaluate the values of a property to be changed its value
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	private static String checkChangedItsValue(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML(propertyName, xml_file.getAbsoluteFile());

		if(sr == null || sr.property.isEmpty()){
			return "resultsNotfound";
		}
		
		Double firstValue = null;
		for(Double d : sr.values.values()){
			if(firstValue != null)
				if(firstValue != d){
					return "false";
				}
			else{
				firstValue = d;
			}
		}
			return "true";
	}
	
	/**
	 * Evaluates the results of a property using a given check mode
	 * @param propertyName name of the property which is going to be evaluated
	 * @param checkMode used to evaluate the results of a property, the following check modes are allowed: atLeastOneTimeTrue, atLeastOneTimeFalse, alwaysTrue, alwaysFalse, changedItsValue
	 * @param result_xml_file absolut path to the results XML
	 * @return result of the evaluation as a String, the result can be: true, false, resultsNotfound, fileNotFound, checkModeUnknow
	 */
	public static String evaluateProperty(String propertyName, String checkMode, String result_xml_file){
		File xmlResultFile = new File(result_xml_file);
		try{
			if(!xmlResultFile.exists() || !xmlResultFile.canRead())
				return "fileNotFound";
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		if(checkMode.equals("atLeastOneTimeTrue"))
			return checkAtLeastOneTimeTrue(propertyName, xmlResultFile);		
		if(checkMode.equals("atLeastOneTimeFalse"))
			return checkAtLeastOneTimeFalse(propertyName, new File(result_xml_file));
		if(checkMode.equals("alwaysTrue"))
			return checkAlwaysTrue(propertyName, new File(result_xml_file));
		if(checkMode.equals("alwaysFalse"))
			return checkAlwaysFalse(propertyName, new File(result_xml_file));
		if(checkMode.equals("changedItsValue"))
			return checkChangedItsValue(propertyName, new File(result_xml_file));
		else
			return "checkModeUnknown";
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
//		File f = new File("");
//		String p = f.getAbsolutePath();
//		p = p.substring(0, p.lastIndexOf(System.getProperty("file.separator")));
//		File f2 = new File(p + "\\org.openmodelica.simulation.projects\\");
//		System.out.println(f2.list()[0]);
		
		String pathToResultXML = "C:/Projects/ModelicaML/runtime-New_configuration/modelicaml.example.potableWaterSystem_v30/verification-gen/verification-session_20120124110026\\ModelicaMLModel.GenVeMs_for__SPWS_Environment_1.VeM_for__s1_Fill_and_Drain_Tank_res.xml";
		
//		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", projectPath);
		String propertyName = "_reqVerificationVerdict.allRequirementsEvaluated";
		
		System.out.println(checkAtLeastOneTimeTrue(propertyName, new File(pathToResultXML)));
		System.out.println(checkAtLeastOneTimeFalse(propertyName, new File(pathToResultXML)));
		System.out.println(checkAlwaysTrue(propertyName, new File(pathToResultXML)));
		System.out.println(checkAlwaysFalse(propertyName, new File(pathToResultXML)));
		System.out.println(checkChangedItsValue(propertyName, new File(pathToResultXML)));
	}
}
