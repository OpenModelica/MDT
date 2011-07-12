package org.openmodelica.simulation.core.xml.reader;

import java.io.File;
import java.io.IOException;

import org.openmodelica.simulation.core.models.simulation.SimulationProject;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

/**
 * The Class SimulationInit_XML_reader.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class SimulationProject_XML_reader {


	/** The simpro. */
	private static SimulationProject simpro;
	//public static ArrayList<String> invalidDataLength = new ArrayList<String>();

	/**
	 * Reads a XML file, creates a ModelicaModel object and initializes it.
	 * The XML file can be a "ModelInit.xml" or a "ModelConfig.xml"
	 *
	 * @param xml_file the xml_file
	 * @return complete initialized ModelicaModel object
	 */
	public static SimulationProject readFromXML(File xml_file) {
		simpro = null;
		createProject(xml_file);
		return simpro;
	}
	
	
	/**
	 * Reads a XML file, creates a ModelicaModel object and initializes it.
	 * The XML file can be a "ModelInit.xml" or a "ModelConfig.xml"
	 *
	 * @param xml_file_path the xml_file_path
	 * @return complete initialized ModelicaModel object
	 */
	public static SimulationProject readFromXML(String xml_file_path) {
		simpro = null;
		createProject(new File(xml_file_path));
		return simpro;
	}
	
	/**
	 * Creates the project.
	 *
	 * @param xmlFile the xml file
	 */
	private static void createProject(File xmlFile) {
		
		Document project_xml = parseXML_File(xmlFile);
		Element root = project_xml.getRootElement();	
		
//		System.out.println(root.getChildElements("name").get(0).getValue());
		String projectName = (root.getChildElements("name").get(0).getValue());
		String id = (root.getChildElements("id").get(0).getValue());
		String version = (root.getChildElements("version").get(0).getValue());
		String author = (root.getChildElements("author").get(0).getValue());
		String company = (root.getChildElements("company").get(0).getValue());
		String comment = (root.getChildElements("comment").get(0).getValue());
		simpro = new SimulationProject(projectName, id, version, author, company, comment);
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
		
		String projectPath = "F:\\Eclipse_Workspaces\\workspace_EADS_OMI_02\\org.openmodelica.simulation.projects\\SimulationProject003\\SimulationProject003_SimProject.xml";
		
		SimulationProject sp = readFromXML(projectPath);
		System.out.println(sp.toString());
	}

}
