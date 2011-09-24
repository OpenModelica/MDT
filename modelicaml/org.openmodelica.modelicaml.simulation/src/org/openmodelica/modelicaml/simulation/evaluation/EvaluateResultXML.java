package org.openmodelica.modelicaml.simulation.evaluation;

import java.io.File;

import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_reader;

public class EvaluateResultXML {
	
//	- [atLeastOneTimeTrue], 
//	- [atLeastOneTimeFalse], 
//	- [alwaysTrue], 
//	- [alwaysFalse], 
//	- [changedItsValue]
	
	/**
	 * Evaluate the values of a property to be at least one time true
	 * @param propertyName name of the property to be evaluated
	 * @param result xml file
	 */
	public static String checkAtLeastOneTimeTrue(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", xml_file.getAbsoluteFile());

		if(sr == null){
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
	public static String checkAtLeastOneTimeFalse(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", xml_file.getAbsoluteFile());

		if(sr == null){
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
	public static String checkAlwaysTrue(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", xml_file.getAbsoluteFile());

		if(sr == null){
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
	public static String checkAlwaysFalse(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", xml_file.getAbsoluteFile());

		if(sr == null){
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
	public static String checkChangedItsValue(String propertyName, File xml_file){
		if(!xml_file.exists())
			return "fileNotFound";
		
		
		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", xml_file.getAbsoluteFile());

		if(sr == null){
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
		
		String projectPath = "C:\\Projects\\ModelicaML\\runtime-New_configuration\\modelicaml.example.potableWaterSystem_v26\\test-gen\\test-session_20110924175101\\tmp\\ModelicaMLModel.GenSimModels_for__SPWS_Environment_1.SiM_for__ts2__Fill_tank_res.xml";
		
//		SimulationResult sr = SimulationResult_XML_reader.readFromXML("_reqTestVerdict.allRequirementsEvaluated", projectPath);
		String propertyName = "_reqTestVerdict.allRequirementsEvaluated";
		
		System.out.println(checkAtLeastOneTimeTrue(propertyName, new File(projectPath)));
		System.out.println(checkAtLeastOneTimeFalse(propertyName, new File(projectPath)));
		System.out.println(checkAlwaysTrue(propertyName, new File(projectPath)));
		System.out.println(checkAlwaysFalse(propertyName, new File(projectPath)));
		System.out.println(checkChangedItsValue(propertyName, new File(projectPath)));
	}
}
