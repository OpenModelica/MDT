package org.openmodelica.modelicaml.simulation.xml;

import java.io.File;
import java.io.IOException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

import org.openmodelica.modelicaml.simulation.evaluation.SimulationResult;

/**
 * The Class SimulationInit_XML_reader.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 */
public class SimulationResult_XML_reader {

	/**
	 * Reads a XML file, creates a ModelicaModel object and initializes it.
	 * The XML file can be a "ModelInit.xml" or a "ModelConfig.xml"
	 *
	 * @param xml_file the xml_file
	 * @return complete initialized ModelicaModel object
	 */
	public static SimulationResult readFromXML(String propertyName, File xml_file) {
		SimulationResult results = new SimulationResult();
		readResult(propertyName, results, xml_file);
		return results;
	}
	
	
	/**
	 * Reads a XML file, creates a ModelicaModel object and initializes it.
	 * The XML file can be a "ModelInit.xml" or a "ModelConfig.xml"
	 *
	 * @param xml_file_path the xml_file_path
	 * @return complete initialized ModelicaModel object
	 */
	public static SimulationResult readFromXML(String propertyName, String xml_file_path) {
		SimulationResult results = new SimulationResult();
		readResult(propertyName, results, new File(xml_file_path));
		return results;
	}
	
	/**
	 * Creates the project.
	 *
	 * @param xmlFile the xml file
	 */
	private static void readResult(String propertyName, SimulationResult results, File xmlFile) {
		
		Document resultsXML = parseXML_File(xmlFile);
		Element root = resultsXML.getRootElement();	
		Element eDataset = root.getChildElements("datasets").get(0);
		getResult(results, propertyName, eDataset);
	}

	private static void getResult(SimulationResult results, String propertyName, Element eDataset) {
		int i = 0;
		Elements eResults = eDataset.getChildElements();
		while (i < eResults.size()) {
			Element eResult = eResults.get(i);
			
			String name = eResult.getAttributeValue("name");
			if(name.equals(propertyName)){
//				System.err.println("FOUND: " + name);
				results.property = name;
				int t = 0;
				while (t < eResult.getChildElements("data").size()) {
					Element eData = eResult.getChildElements("data").get(t);
					double time = new Double(eData.getAttributeValue("t")).doubleValue();
					double value = new Double(eData.getValue()).doubleValue();
					results.values.put(time, value);
					++t;
				}
				break;
			}
//			String qualifiedName = eTestModel.getChildElements("qualifiedName").get(0).getValue();
//			
//			Elements eSimulationData = eTestModel.getChildElements("simulationData");
//				String start = eSimulationData.get(0).getChildElements("start").get(0).getValue();
			++i;
		}
	}
	
	
	/**
	 * parse a XML file.
	 *
	 * @param file the file
	 * @return the doc which generated from the file or null if there is any
	 * error
	 */
	private static Document parseXML_File(File file) {

		try {
			Builder parser = new Builder();
			Document doc = parser.build(file);
			return doc;
		} catch (ParsingException ex) {
			System.err.println("The XML file is malformed. Pleas check it");
		} catch (IOException ex) {
			System.err
					.println("Could not read the file. The file may not exists.");
		}
		return null;
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
		
		SimulationResult sr = readFromXML("_reqTestVerdict.allRequirementsEvaluated", projectPath);
		System.out.println(sr.property);
		System.out.println(sr.values.values());
	}

}
