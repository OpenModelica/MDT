package org.openmodelica.simulation.core.init_handling;

import java.io.File;
import java.io.IOException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 * The Class SimulationInit_XML_reader.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class SimulationInit_XML_reader {


	/** The simpro. */
	private static InitData initdata;
	//public static ArrayList<String> invalidDataLength = new ArrayList<String>();

	/**
	 * Reads a XML file, creates a InitData object and initializes it.
	 * The XML file can be a "Model_init.xml" created by omc since version 1.7.0
	 *
	 * @param xml_file the xml_file
	 * @return complete initialized ModelicaModel object
	 */
	public static InitData readFromXML(File xml_file) {
		initdata = new InitData();
		initdata.fileName = xml_file.getName();
		createInitData(xml_file);
		return initdata;
	}
	
	
	/**
	 * Reads a XML file, creates a ModelicaModel object and initializes it.
	 * The XML file can be a "ModelInit.xml" or a "ModelConfig.xml"
	 *
	 * @param xml_file_path the xml_file_path
	 * @return complete initialized ModelicaModel object
	 */
	public static InitData readFromXML(String xml_file_path) {
		initdata = new InitData();
		initdata.fileName = (new File(xml_file_path).getName());
		
		createInitData(new File(xml_file_path));
		return initdata;
	}
	
	/**
	 * Creates the project.
	 *
	 * @param xmlFile the xml file
	 */
	private static void createInitData(File xmlFile) {
		Document project_xml = parseXML_File(xmlFile);
		Element root = project_xml.getRootElement();	

//		initdata.start = eDefaultExperiment.get(0).getAttribute("startTime").getValue();
		
		initdata.states = new Integer(root.getAttribute("numberOfContinuousStates").getValue());
		initdata.alg_vars = new Integer(root.getAttribute("numberOfRealAlgebraicVariables").getValue());
		initdata.parameters = new Integer(root.getAttribute("numberOfRealParameters").getValue());
		initdata.n_bool_parameters = new Integer(root.getAttribute("numberOfBooleanParameters").getValue());
		initdata.n_bool_variables = new Integer(root.getAttribute("numberOfBooleanAlgebraicVariables").getValue());
		initdata.n_int_parameters = new Integer(root.getAttribute("numberOfIntegerParameters").getValue());
		initdata.n_int_variables = new Integer(root.getAttribute("numberOfIntegerAlgebraicVariables").getValue());
		initdata.string_parameters = new Integer(root.getAttribute("numberOfStringParameters").getValue());
		initdata.string_variables = new Integer(root.getAttribute("numberOfStringAlgebraicVariables").getValue());
		
		Elements eModelVariables = root.getChildElements("ModelVariables");
		
		addModelVariables(eModelVariables);
	}
	
	private static void addModelVariables(Elements eModelVariables) {
		Elements eScalarVariables = eModelVariables.get(0).getChildElements("ScalarVariable");
		int i = 0;
		while (i < eScalarVariables.size()){
			String classType = eScalarVariables.get(i).getAttribute("classType").getValue();
			if(classType.equals("rSta")){
				addState(eScalarVariables.get(i));
			} else if(classType.equals("rDer")){
				addDerState(eScalarVariables.get(i));
			} else if(classType.equals("rAlg")){
				addAlgebraic(eScalarVariables.get(i));
			} else if(classType.equals("rPar")){
				addParameter(eScalarVariables.get(i));
			} 
			
			i++;
		}		
	}
	
	private static void addState(Element property){

		String tempName = property.getAttributeValue("name").trim();
		Double tempValue = new Double(property.getChildElements().get(0).getAttributeValue("start").trim());
		
		initdata.stateProperties.put(tempName, tempValue);
		initdata.orderdStateProperties.add(tempName);
	}
	
	private static void addDerState(Element property){

		String tempName = property.getAttributeValue("name").trim();
		Double tempValue = new Double(property.getChildElements().get(0).getAttributeValue("start").trim());
		
		initdata.derStateProperties.put(tempName, tempValue);
		initdata.orderdDerStateProperties.add(tempName);
	}
	
	private static void addAlgebraic(Element property){

		String tempName = property.getAttributeValue("name").trim();
		Double tempValue = new Double(property.getChildElements().get(0).getAttributeValue("start").trim());
		
		initdata.algProperties.put(tempName, tempValue);
		initdata.orderdAlgProperties.add(tempName);
	}
	
	private static void addParameter(Element property){

		String tempName = property.getAttributeValue("name").trim();
		Double tempValue = new Double(property.getChildElements().get(0).getAttributeValue("start").trim());
		
		initdata.parameterProperties.put(tempName, tempValue);
		initdata.orderdParameterProperties.add(tempName);
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
		
		String projectPath = "C:\\AircraftWaterTankSystem.TestEnvironment_init.xml";
		InitData sp = readFromXML(projectPath);
		System.out.println(sp.stateProperties);
		System.out.println(sp.parameterProperties);
		System.out.println(sp.toString());
	}
}
