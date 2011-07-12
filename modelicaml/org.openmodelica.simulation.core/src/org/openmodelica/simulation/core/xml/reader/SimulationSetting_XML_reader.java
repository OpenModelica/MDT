package org.openmodelica.simulation.core.xml.reader;

import java.io.File;
import java.io.IOException;

import org.openmodelica.simulation.core.models.simulation.SimulationSetting;
import org.openmodelica.simulation.core.models.simulation.SimulationSettingInteractive;
import org.openmodelica.simulation.core.models.simulation.SimulationSettingNonInteractive;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 * The Class SimulationSetting_XML_reader.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class SimulationSetting_XML_reader {
	


	/** The simsett. */
	private static SimulationSetting simsett = null;
	//public static ArrayList<String> invalidDataLength = new ArrayList<String>();

	/**
	 * Reads a XML file, creates a SimulationSetting object and initializes it.
	 * The XML file have to be a "SimSetting.xml"
	 *
	 * @param xml_file the xml_file
	 * @return complete initialized SimulationSetting object
	 */
	public static SimulationSetting readFromXML(File xml_file) {
		simsett = null;
		createSetting(xml_file);
		return simsett;
	}
	
	
	/**
	 * Reads a XML file, creates a SimulationSetting object and initializes it.
	 * The XML file have to be a "SimSetting.xml"
	 * @param xml_file_path full path to xml file
	 * @return complete initialized SimulationSetting object
	 */
	public static SimulationSetting readFromXML(String xml_file_path) {
		simsett = null;
		createSetting(new File(xml_file_path));
		return simsett;
	}
	
	
	/**
	 * Creates the setting.
	 *
	 * @param xmlFile the xml file
	 */
	private static void createSetting(File xmlFile) {
		Document project_xml = parseXML_File(xmlFile);
		Element root = project_xml.getRootElement();

		// Elements eModel = root.getChildElements("model");
		// String name =
		// eModel.get(0).getChildElements("name").get(0).getValue();
		// String id = eModel.get(0).getChildElements("id").get(0).getValue();

		Elements eSession = root.getChildElements("session");
		String name = eSession.get(0).getChildElements("name").get(0)
				.getValue();
		String type = eSession.get(0).getChildElements("type").get(0).getValue();
		String version = (eSession.get(0).getChildElements("version").get(0)
				.getValue());
		String author = (eSession.get(0).getChildElements("author").get(0)
				.getValue());
		String company = (eSession.get(0).getChildElements("company").get(0)
				.getValue());
		String comment = (eSession.get(0).getChildElements("comment").get(0)
				.getValue());
		Integer numberOfRuns = 0;
		{
			String numberOfRunsString =eSession.get(0).getChildElements("numberOfRuns").get(0).getValue();
			if(!numberOfRunsString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
				try {
					numberOfRuns = Integer.valueOf(numberOfRunsString); //exception it the value is not a valid integer value
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		if (type.equals("i")) {
			createInteractiveSetting(xmlFile, name, type, version, author,
					company, comment, numberOfRuns);
		} else if (type.equals("ni")) {
			createNonInteractiveSetting(xmlFile, name, type, version, author,
					company, comment, numberOfRuns);
		}
	}
	
	/**
	 * Creates the interactive setting.
	 *
	 * @param xmlFile the xml file
	 * @param name the name
	 * @param type the type
	 * @param version the version
	 * @param author the author
	 * @param company the company
	 * @param comment the comment
	 * @param numberOfRuns the number of runs
	 */
	private static void createInteractiveSetting(File xmlFile, String name,
			String type, String version, String author, String company,
			String comment, Integer numberOfRuns) {

		Document project_xml = parseXML_File(xmlFile);
		Element root = project_xml.getRootElement();

		Elements eNetwork = root.getChildElements("network");
		Element eRuntimeControlServer = eNetwork.get(0)
				.getChildElements("runtimeControlServer").get(0);
		Element eSimulationControlServer = eNetwork.get(0)
				.getChildElements("simulationControlServer").get(0);
		Element eSimulationTransferServer = eNetwork.get(0)
				.getChildElements("simulationTransferServer").get(0);

		String runtimeControlServerIp = eRuntimeControlServer
				.getChildElements("ip").get(0).getValue();
		String runtimeControlServerPort = eRuntimeControlServer
				.getChildElements("port").get(0).getValue();
		String simulationControlServerIp = eSimulationControlServer
				.getChildElements("ip").get(0).getValue();
		String simulationControlServerPort = eSimulationControlServer
				.getChildElements("port").get(0).getValue();
		String simulationTransferServerIp = eSimulationTransferServer
				.getChildElements("ip").get(0).getValue();
		String simulationTransferServerPort = eSimulationTransferServer
				.getChildElements("port").get(0).getValue();

		Elements eSimulation = root.getChildElements("simulation");

		// SimStepTime
		String tempSimStepTime = eSimulation.get(0)
				.getChildElements("simStepTime").get(0).getValue();
		Double tempSimStepTimeAsDouble = 0.0;
		Double simStepTimeInSec = 0.0;
		if (!tempSimStepTime.isEmpty()) { // If the string is empty ("") it
											// can't converts into an integer
			try {
				tempSimStepTimeAsDouble = Double.valueOf(tempSimStepTime); // exception it the value is not a valid integer value
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		String stepTimeUnit = eSimulation.get(0)
				.getChildElements("simStepTime").get(0).getAttribute("unit")
				.getValue();
		// OpenModelica uses second as default time unit for calculation steps,
		// but a user can enter any time unit.
		// The smallest unit is ms because 0.0001 is the smallest time step
		// available in OM (e.g. y, ... , d, h, s, ms)
		// TODO [20100909] Negative Werte abfangen!

		if (stepTimeUnit.equals("s"))
			simStepTimeInSec = tempSimStepTimeAsDouble;
		else if (stepTimeUnit.equals("ms"))
			simStepTimeInSec = tempSimStepTimeAsDouble / 1000;
		else if (stepTimeUnit.equals("min"))
			simStepTimeInSec = tempSimStepTimeAsDouble * 60;
		else if (stepTimeUnit.equals("h"))
			simStepTimeInSec = tempSimStepTimeAsDouble * 3600;

		String tolerance = eSimulation.get(0).getChildElements("tolerance")
				.get(0).getValue();
		String solver = eSimulation.get(0).getChildElements("solver").get(0)
				.getValue();

		simsett = new SimulationSettingInteractive(name, type, version, author,
				company, comment, numberOfRuns, runtimeControlServerIp,
				runtimeControlServerPort, simulationControlServerIp,
				simulationControlServerPort, simulationTransferServerIp,
				simulationTransferServerPort, simStepTimeInSec.doubleValue()
						+ "", tolerance, solver);
	}
	
	/**
	 * Creates the non interactive setting.
	 *
	 * @param xmlFile the xml file
	 * @param name the name
	 * @param type the type
	 * @param version the version
	 * @param author the author
	 * @param company the company
	 * @param comment the comment
	 * @param numberOfRuns the number of runs
	 */
	private static void createNonInteractiveSetting(File xmlFile, String name, String type, String version, String author, String company, String comment, Integer numberOfRuns){
		
		Document project_xml = parseXML_File(xmlFile);
		Element root = project_xml.getRootElement();		
		
		Elements eSimulation = root.getChildElements("simulation");
		
		//Start
		String start = eSimulation.get(0).getChildElements("start").get(0).getValue();
			
		//Stop
		String stop = eSimulation.get(0).getChildElements("stop").get(0).getValue();
		//SimStepTime
		
		String tempSimStepTime = eSimulation.get(0)
				.getChildElements("simStepTime").get(0).getValue();
		Double tempSimStepTimeAsDouble = 0.0;
		Double simStepTimeInSec = 0.0;
		if (!tempSimStepTime.isEmpty()) { // If the string is empty ("") it
											// can't converts into an integer
			try {
				tempSimStepTimeAsDouble = Double.valueOf(tempSimStepTime); //exception it the value is not a valid integer value
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		String stepTimeUnit = eSimulation.get(0).getChildElements("simStepTime").get(0).getAttribute("unit").getValue();
		//OpenModelica uses second as default time unit for calculation steps, but a user can enter any time unit. 
		//The smallest unit is ms because 0.0001 is the smallest time step available in OM (e.g. y, ... , d, h, s, ms)  
		//TODO [20100909] Negative Werte abfangen!

		if (stepTimeUnit.equals("s"))
			simStepTimeInSec = tempSimStepTimeAsDouble;
		else if (stepTimeUnit.equals("ms"))
			simStepTimeInSec = tempSimStepTimeAsDouble / 1000;
		else if (stepTimeUnit.equals("min"))
			simStepTimeInSec = tempSimStepTimeAsDouble * 60;
		else if (stepTimeUnit.equals("h"))
			simStepTimeInSec = tempSimStepTimeAsDouble * 3600;

		String tolerance = eSimulation.get(0).getChildElements("tolerance")
				.get(0).getValue();
		String solver = eSimulation.get(0).getChildElements("solver").get(0)
				.getValue();
		simsett = new SimulationSettingNonInteractive(name, type, version, author,
				company, comment, numberOfRuns, simStepTimeInSec.doubleValue()
						+ "", tolerance, solver, start, stop);
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
	public static void main(String[] args){
		SimulationSetting ss = readFromXML("F:\\SimSetting.xml");
		System.out.println(ss.getName());
	}
}