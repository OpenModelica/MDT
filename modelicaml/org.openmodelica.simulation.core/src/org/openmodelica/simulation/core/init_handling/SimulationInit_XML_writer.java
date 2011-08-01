package org.openmodelica.simulation.core.init_handling;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;

// TODO: Auto-generated Javadoc
/**
 * Generates an text file including all data from the model_init.xml file in the same structure
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class SimulationInit_XML_writer {

	/**
	 * Reads a init.xml file from a specified path and creates an InitData object
	 * which contains all initial values and simulation settings
	 *
	 * @param pathToInit Absoulut path to the "_init.xml" file
	 * @param modifiedInitData the modified init data
	 * @return An InitData object containing all initial values and simulation
	 * settings
	 */
	public static boolean writeInit(String pathToInit, InitData modifiedInitData) {
		
		Document project_xml = parseXML_File(new File(pathToInit));
		Element root = project_xml.getRootElement();	

		Elements eModelVariables = root.getChildElements("ModelVariables");
		Element eDefaultExperiment = root.getChildElements("DefaultExperiment").get(0);
		
		addDefaultExperiment(eDefaultExperiment, modifiedInitData);
		addModelVariables(eModelVariables, modifiedInitData);
		
		FileOutputStream f;
		try {
			f = new FileOutputStream(pathToInit);
			Serializer serializer = new Serializer(f, "UTF-8");
			serializer.setIndent(4);
			serializer.write(project_xml);
			f.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private static void addDefaultExperiment(Element eDefaultExperiment, InitData modifiedInitData) {
		Attribute aStartTime = new Attribute("startTime", modifiedInitData.start + "");
		Attribute aStopTime = new Attribute("stopTime", modifiedInitData.stop + "");
		Attribute aStepSize = new Attribute("stepSize", modifiedInitData.step + "");
		Attribute aTolerance = new Attribute("tolerance", modifiedInitData.tolerance + "");
		Attribute aSolver = new Attribute("solver", modifiedInitData.method);
		
		eDefaultExperiment.addAttribute(aStartTime);
		eDefaultExperiment.addAttribute(aStopTime);
		eDefaultExperiment.addAttribute(aStepSize);
		eDefaultExperiment.addAttribute(aTolerance);
		eDefaultExperiment.addAttribute(aSolver);
	}

	private static void addModelVariables(Elements eModelVariables,
			InitData modifiedInitData) {
		Elements eScalarVariables = eModelVariables.get(0).getChildElements("ScalarVariable");
		int i = 0;
		while (i < eScalarVariables.size()){
			String classType = eScalarVariables.get(i).getAttribute("classType").getValue();
			if(classType.equals("rSta")){
				addState(eScalarVariables.get(i), modifiedInitData);
			} else if(classType.equals("rDer")){
				addDerState(eScalarVariables.get(i), modifiedInitData);
			} else if(classType.equals("rAlg")){
				addAlgebraic(eScalarVariables.get(i), modifiedInitData);
			} else if(classType.equals("rPar")){
				addParameter(eScalarVariables.get(i), modifiedInitData);
			} 
			
			i++;
		}		
	}

	private static void addState(Element element, InitData modifiedInitData) {
		Double startValue = modifiedInitData.stateProperties.get(element.getAttribute("name").getValue());
		System.err.println(element.getAttribute("name") + " = " + startValue);
		Attribute aStartValue = new Attribute("start", startValue + "");
		element.getChildElements().get(0).addAttribute(aStartValue); // getChildElements().get(0) -> <Real start="0.5" fixed="false" unit="m2" displayUnit="m2" />
	}

	private static void addDerState(Element element, InitData modifiedInitData) {
		Double startValue = modifiedInitData.derStateProperties.get(element.getAttribute("name").getValue());
		
		Attribute aStartValue = new Attribute("start", startValue + "");
		element.getChildElements().get(0).addAttribute(aStartValue); // getChildElements().get(0) -> <Real start="0.5" fixed="false" unit="m2" displayUnit="m2" />
	}

	private static void addAlgebraic(Element element, InitData modifiedInitData) {
		Double startValue = modifiedInitData.algProperties.get(element.getAttribute("name").getValue());
		
		Attribute aStartValue = new Attribute("start", startValue + "");
		element.getChildElements().get(0).addAttribute(aStartValue); // getChildElements().get(0) -> <Real start="0.5" fixed="false" unit="m2" displayUnit="m2" />
	}


	private static void addParameter(Element element, InitData modifiedInitData) {
		Double startValue = modifiedInitData.parameterProperties.get(element.getAttribute("name").getValue());
		
		Attribute aStartValue = new Attribute("start", startValue + "");
		element.getChildElements().get(0).addAttribute(aStartValue); // getChildElements().get(0) -> <Real start="0.5" fixed="false" unit="m2" displayUnit="m2" />
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
		InitData modifiedInitData;
		
		String projectPath = "C:\\AircraftWaterTankSystem.TestEnvironment_init.xml";
		
		try {
			modifiedInitData = SimulationInit_XML_reader.readFromXML(projectPath);
			modifiedInitData.step = 1.8;
			
			modifiedInitData.stateProperties.put("SUT.tank2.h", 22.0);
			System.out.println(modifiedInitData.stateProperties);
			System.out.println(modifiedInitData.derStateProperties);
			System.out.println(modifiedInitData.algProperties);
			System.out.println(modifiedInitData.parameterProperties);
			
			writeInit("C:\\AircraftWaterTankSystem.TestEnvironment_init.xml", modifiedInitData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
