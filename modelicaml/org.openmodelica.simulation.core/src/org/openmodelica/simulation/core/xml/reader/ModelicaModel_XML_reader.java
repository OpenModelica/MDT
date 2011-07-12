package org.openmodelica.simulation.core.xml.reader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

import org.openmodelica.simulation.core.models.modelica.ModelicaMainclass;
import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.models.modelica.ModelicaParameter;
import org.openmodelica.simulation.core.models.modelica.ModelicaProperty;
import org.openmodelica.simulation.core.models.modelica.ModelicaVariable;

/**
 * Reads the *_SimInitial.xml and the *_SimConfig.xml, parses it and creates the ModelicaModel 
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class ModelicaModel_XML_reader {

	/** The modelica model. */
	private static ModelicaModel modelicaModel = null;
	//public static ArrayList<String> invalidDataLength = new ArrayList<String>();

	/**
	 * Reads a XML file, creates a ModelicaModel object and initializes it.
	 * The XML file can be a "ModelInit.xml" or a "ModelConfig.xml"
	 *
	 * @param xml_file the xml_file
	 * @return complete initialized ModelicaModel object
	 */
	public static ModelicaModel readFromXML(File xml_file) {
		modelicaModel = null;
		createProject(xml_file);
		return modelicaModel;
	}
	
	/**
	 * Reads a XML file, creates a ModelicaModel object and initializes it.
	 * The XML file can be a "ModelInit.xml" or a "ModelConfig.xml"
	 *
	 * @param xml_file_path the xml_file_path
	 * @return complete initialized ModelicaModel object
	 */
	public static ModelicaModel readFromXML(String xml_file_path) {
		modelicaModel = null;
		createProject(new File(xml_file_path));
		return modelicaModel;
	}
	
	
	/**
	 * Creates the project.
	 *
	 * @param xmlFile the xml file
	 */
	private static void createProject(File xmlFile) {
//		System.out.println(xmlFile.getAbsolutePath());
		
		Document project_xml = parseXML_File(xmlFile);
		Element root = project_xml.getRootElement();		
		
		Elements eProjectGeneral = root.getChildElements("general");
		modelicaModel = createModelicaProject(eProjectGeneral);
		
		Elements eMainclass = root.getChildElements("mainclass");
		modelicaModel.setMainclass(createModelicaMainclass(eMainclass));
		
//		System.out.println("Name: " + modelicaModel.getMainclass().getName());
//		System.out.println("Value: " + modelicaModel.getMainclass().getType());
		
		Element eVariables = eMainclass.get(0).getChildElements("variables").get(0);
		Elements eVariablesList = eVariables.getChildElements();			
		addAllVariablesToMainclass(eVariablesList);
		
		Element eParameters = eMainclass.get(0).getChildElements("parameters").get(0);
		Elements eParametersList = eParameters.getChildElements();			
		addAllParametersToMainclass(eParametersList);
	}
	
	/**
	 * CHECKED OK!
	 * creates and initializes the ModelicaModel object modelicaModel with the general project information
	 * from the XML file.
	 * @param eProjectGeneral is the general project information from the project XML
	 * @return a ModelicaModel initialized with the general project information from the project XML
	 */
	private static ModelicaModel createModelicaProject(Elements eProjectGeneral){
		if(eProjectGeneral.size() != 0){
			/*
			 * General Data to create a ModelicaModel
			 * this is important for the ModelicaModel constructor
			 */
			String name = (eProjectGeneral.get(0).getChildElements("name").get(0).getValue());
			String id = (eProjectGeneral.get(0).getChildElements("id").get(0).getValue());
			String version = (eProjectGeneral.get(0).getChildElements("version").get(0).getValue());
			String author = (eProjectGeneral.get(0).getChildElements("author").get(0).getValue());
			String company = (eProjectGeneral.get(0).getChildElements("company").get(0).getValue());
			String comment = (eProjectGeneral.get(0).getChildElements("comment").get(0).getValue());
			
			
			Integer n_states = 0;			
			{
				String n_stateString =eProjectGeneral.get(0).getChildElements("n_states").get(0).getValue();
				if(!n_stateString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_states = Integer.valueOf(n_stateString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
				
			Integer n_algebraics = 0;
			{
				String n_algebraicsString =eProjectGeneral.get(0).getChildElements("n_algebraics").get(0).getValue();
				if(!n_algebraicsString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_algebraics = Integer.valueOf(n_algebraicsString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			Integer n_parameters = 0;
			{
				String n_parametersString =eProjectGeneral.get(0).getChildElements("n_parameters").get(0).getValue();
				if(!n_parametersString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_parameters = Integer.valueOf(n_parametersString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			
			Integer n_inputVars = 0;
//			{
//				String n_inputVarsString =eProjectGeneral.get(0).getChildElements("n_inputVars").get(0).getValue();
//				if(!n_inputVarsString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
//					try {
//						n_inputVars = Integer.valueOf(n_inputVarsString); //exception it the value is not a valid integer value
//					} catch (Exception e) {
//						System.err.println(e.getMessage());
//					}
//				}
//			}
//			
			Integer n_outputVars = 0;
//			{
//				String n_outputVarsString =eProjectGeneral.get(0).getChildElements("n_outputVars").get(0).getValue();
//				if(!n_outputVarsString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
//					try {
//						n_outputVars = Integer.valueOf(n_outputVarsString); //exception it the value is not a valid integer value
//					} catch (Exception e) {
//						System.err.println(e.getMessage());
//					}
//				}
//			}
			
			Integer n_int_parameters = 0;
			{
				String n_int_parametersString =eProjectGeneral.get(0).getChildElements("n_int_parameters").get(0).getValue();
				if(!n_int_parametersString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_int_parameters = Integer.valueOf(n_int_parametersString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			
			Integer n_int_variables = 0;
			{
				String n_int_variablesString =eProjectGeneral.get(0).getChildElements("n_int_variables").get(0).getValue();
				if(!n_int_variablesString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_int_variables = Integer.valueOf(n_int_variablesString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			
			Integer n_bool_parameters = 0;
			{
				String n_bool_parametersString =eProjectGeneral.get(0).getChildElements("n_bool_parameters").get(0).getValue();
				if(!n_bool_parametersString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_bool_parameters = Integer.valueOf(n_bool_parametersString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			
			Integer n_bool_variables = 0;
			{
				String n_bool_variablesString =eProjectGeneral.get(0).getChildElements("n_bool_variables").get(0).getValue();
				if(!n_bool_variablesString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_bool_variables = Integer.valueOf(n_bool_variablesString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			
			Integer n_string_parameters = 0;
			{
				String n_string_parametersString =eProjectGeneral.get(0).getChildElements("n_string_parameters").get(0).getValue();
				if(!n_string_parametersString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_string_parameters = Integer.valueOf(n_string_parametersString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			
			Integer n_string_variables = 0;
			{
				String n_string_variablesString =eProjectGeneral.get(0).getChildElements("n_string_variables").get(0).getValue();
				if(!n_string_variablesString.isEmpty()){ //If the string is empty ("") it can't converts into an integer
					try {
						n_string_variables = Integer.valueOf(n_string_variablesString); //exception it the value is not a valid integer value
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			
			/*
			 * If the id is not available the constructor for the "ModelInit.xml" will be used
			 * otherwise the constructor for "ModelConfig.xml" will be used 
			 */
				return new ModelicaModel(name, id, version, author, company, comment, n_states, n_algebraics, n_parameters, n_inputVars, n_outputVars,  n_int_parameters, n_int_variables, n_bool_parameters, n_bool_variables, n_string_parameters, n_string_variables);
		}
		return null;
	}

	/**
	 * creates and initializes a ModelicaMainclass for a ModelicaModel. 
	 * @param eMainclass is the project mainclass from the project XML, it contains all other classes as variables
	 * @return a ModelicaMainclass object initialied with the general mainclass information and all variables from the project XML
	 */
	private static ModelicaMainclass createModelicaMainclass(Elements eMainclass){
		if(eMainclass.size() != 0){
			
			Element eMainclassGeneral = eMainclass.get(0).getChildElements("general").get(0);
		
			String mainClassName = eMainclass.get(0).getAttributeValue("name");
			String mainClassType = getElementGeneralType(eMainclassGeneral);
			String mainClassComment = getElementGeneralComment(eMainclassGeneral);

			ModelicaMainclass momainclass = new ModelicaMainclass(mainClassName, mainClassType, mainClassComment);
			return momainclass;
		}
		return null;
	}
	
	/**
	 * Adds the all variables to mainclass.
	 *
	 * @param eVariablesList the e variables list
	 */
	private static void addAllVariablesToMainclass(Elements eVariablesList){
		
		int i = 0;
		while (i < eVariablesList.size()) {
		
			Element eVariable = eVariablesList.get(i);

			String variableName = eVariable.getAttributeValue("name");
			
			String variableType = getElementGeneralType(eVariable.getChildElements("general").get(0));
			String variableComment = getElementGeneralComment(eVariable.getChildElements("general").get(0));
			String variablePlot = getElementGeneralPlot(eVariable.getChildElements("general").get(0));
			
			ModelicaVariable variable = new ModelicaVariable(variableName, variableType, variableComment);
			try {
				variable.setPrimitiveValue(getPropertyDataType(eVariable));
				
				//Set plot boolean
				if(variablePlot != null && (variablePlot.equals("true") || variablePlot.equals("false")))
					variable.setDisplay(new Boolean(variablePlot));
				else
					variable.setDisplay(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//System.out.println(variable.toString());
			
			modelicaModel.getMainclass().addVariable(variable);
			++i;
		}
		
//		System.out.println("Name: " + modelicaModel.getMainclass().getVariableByName("tank1.h").getName());
//		System.out.println("Value: " + modelicaModel.getMainclass().getVariableByName("tank1.h").getPrimitiveValue());
	
	}

	/**
	 * Adds the all parameters to mainclass.
	 *
	 * @param eParametersList the e parameters list
	 */
	private static void addAllParametersToMainclass(Elements eParametersList){
		
		int i = 0;
		while (i < eParametersList.size()) {
		
			Element eParameter = eParametersList.get(i);
						
			String parameterName = eParameter.getAttributeValue("name");
			
			String parameterType = getElementGeneralType(eParameter.getChildElements("general").get(0));
			String parameterComment = getElementGeneralComment(eParameter.getChildElements("general").get(0));
			
			String parameterPlot = getElementGeneralPlot(eParameter.getChildElements("general").get(0));
			String parameterInteractive = getElementGeneralInteractive(eParameter.getChildElements("general").get(0));

			
			ModelicaProperty parameter = new ModelicaParameter(parameterName, parameterType, parameterComment);
			try {
				parameter.setPrimitiveValue(getPropertyDataType(eParameter));
				
				//Set plot boolean
				if(parameterPlot != null && (parameterPlot.equals("true") || parameterPlot.equals("false")))
					parameter.setDisplay(new Boolean(parameterPlot));
				else
					parameter.setDisplay(false);
				
				//Set interactive boolean
				if(parameterPlot != null && (parameterPlot.equals("true") || parameterPlot.equals("false")))
					((ModelicaParameter)parameter).setInteractive(new Boolean(parameterInteractive));
				else
					((ModelicaParameter)parameter).setInteractive(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (parameter.getPrimitiveValue() instanceof Number){
				try {
					parameter.setMinValue((Number)getParameterMinValue(eParameter));
					parameter.setMaxValue((Number)getParameterMaxValue(eParameter));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			modelicaModel.getMainclass().addParameter(parameter);
			++i;
		}
		
//		System.out.println("Name: " + modelicaModel.getMainclass().getVariableByName("tank1.h").getName());
//		System.out.println("Value: " + modelicaModel.getMainclass().getVariableByName("tank1.h").getPrimitiveValue());
	}
	
	/**
	 * Reads the Property value and creates a corresponding object from the property type.
	 *
	 * @param eVariable the e variable
	 * @return value object from type Double (If property type is Real), Integer (If property type is Integer),
	 * String (If property type is String), Boolean (If property type is Boolean)
	 */
	private static Object getPropertyDataType(Element eVariable){
		String variableType = getElementGeneralType(eVariable.getChildElements("general").get(0));
		Object variableValue = null;
		
		if(variableType.equals("Real")){
			variableValue = new Double(eVariable.getChildElements("value").get(0).getValue());
		} else if(variableType.equals("Integer")){
			String value = eVariable.getChildElements("value").get(0).getValue();
			Scanner sc = new Scanner(value);
			sc.useDelimiter("\\."); //OM uses float values also for integer and boolean, it is a need to separate the integer from the rest 
			variableValue = new Integer(sc.next());
		} else if(variableType.equals("String")){
			variableValue = new String(eVariable.getChildElements("value").get(0).getValue());
		} else if(variableType.equals("Boolean")){
			String value = eVariable.getChildElements("value").get(0).getValue();
			Scanner sc = new Scanner(value);
			sc.useDelimiter("\\."); //OM uses float values also for integer and boolean, it is a need to separate the integer from the rest 
			variableValue = new Boolean(sc.next());		
		}		
		return variableValue;
	}
	
	/**
	 * This method is only for the Real and Integer types of a Modelica property.
	 *
	 * @param eVariable the e variable
	 * @return the minimum value for a parameter or null if the value is not available
	 */
	private static Object getParameterMinValue(Element eVariable){
		String variableType = getElementGeneralType(eVariable.getChildElements("general").get(0));
		Object variableValue = null;
		String minValue = eVariable.getChildElements("minValue").get(0).getValue();
		
		if(!minValue.equals("")){
			if(variableType.equals("Real")){
				variableValue = new Double(minValue);
			} else if(variableType.equals("Integer")){
				variableValue = new Integer(minValue);
			}	
			return variableValue;
		} 
		return null;
	}
	
	/**
	 * This method is only for the Real and Integer types of a Modelica property.
	 *
	 * @param eVariable the e variable
	 * @return the maximum value for a parameter or null if the value is not available
	 */
	private static Object getParameterMaxValue(Element eVariable){
		String variableType = getElementGeneralType(eVariable.getChildElements("general").get(0));
		Object variableValue = null;
		String maxValue = eVariable.getChildElements("maxValue").get(0).getValue();
		
		if(!maxValue.equals("")){
			if(variableType.equals("Real")){
				variableValue = new Double(maxValue);
			} else if(variableType.equals("Integer")){
				variableValue = new Integer(maxValue);
			}	
			return variableValue;
		} 
		return null;
	}
	
	/**
	 * Gets the element general type.
	 *
	 * @param eGeneral the e general
	 * @return the element general type
	 */
	private static String getElementGeneralType(Element eGeneral){
		return eGeneral.getChildElements("type").get(0).getValue();
	}
	
	/**
	 * Gets the element general comment.
	 *
	 * @param eGeneral the e general
	 * @return the element general comment
	 */
	private static String getElementGeneralComment(Element eGeneral){
		return eGeneral.getChildElements("comment").get(0).getValue();
	}
	
	/**
	 * Gets the element general plot.
	 *
	 * @param eGeneral the e general
	 * @return the element general plot
	 */
	private static String getElementGeneralPlot(Element eGeneral){
		return eGeneral.getChildElements("plot").get(0).getValue();
	}
	
	/**
	 * Gets the element general interactive.
	 *
	 * @param eGeneral the e general
	 * @return the element general interactive
	 */
	private static String getElementGeneralInteractive(Element eGeneral){
		return eGeneral.getChildElements("interactive").get(0).getValue();
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
//		System.out.println(p);
//		File f = new File("E:\\workspace_EADS_OMI_02\\org.openmodelica.simulation.projects\\SimulationProject002_NonInteractive\\TwoTanks_SimInit.xml");
		String path = "C:\\AircraftWaterTankSystem.TestEnvironment_init.xml";
		ModelicaModel mopro = ModelicaModel_XML_reader.readFromXML(path);
		System.out.println(mopro.toString());
	}
}
