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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.core.xml.generator;

import java.io.FileOutputStream;
import java.util.Scanner;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import org.openmodelica.simulation.core.init_handling.InitData;
import org.openmodelica.simulation.core.init_handling.SimulationInit_XML_reader;

/**
 * Generates the SimInit.xml file from the SimProj.xml and init.xml
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 * 
 */

public class SimulationInitial_XML_generator {

	/**
	 * Use this method to create a ModelInit.xml with a path to an init.xml file but not available InitData class.
	 *
	 * @param modelName Modelica Model Name, usually this is the name of the Modelica mainclass
	 * @param projectID Unique identifier for the simulation project
	 * @param pathToInit Path where the init.xml file can be found
	 * @param path Path where the ModelInit.xml has to be stored
	 * @param modelversion Version of the Modelica model
	 * @param modelauthor Author of this model
	 * @param modelcompany Company which has rights on this model
	 * @param modelcomment Model developer comment
	 * @throws Exception the exception
	 */
	public static void createXML(String modelName, String projectID, String pathToInit,
			String path, String modelversion, String modelauthor,
			String modelcompany, String modelcomment) throws Exception {

		write(appendToRoot(modelName, projectID, modelversion, modelauthor, modelcompany,
				modelcomment, SimulationInit_XML_reader.readFromXML(pathToInit)),
				path);
	}
	
	/**
	 * Use this method to create a ModelInit.xml with a given initTXTFile object.
	 *
	 * @param modelName Modelica Model Name, usually this is the name of the Modelica mainclass
	 * @param projectID Unique identifier for the simulation project
	 * @param initTXTFile Initial File representation
	 * @param projectFolderPath Folder where the project has been stored
	 * @param modelversion Version of the Modelica model
	 * @param modelauthor Author of this model
	 * @param modelcompany Company which has rights on this model
	 * @param modelcomment Model developer comment
	 * @throws Exception the exception
	 */
	public static void createXML(String modelName, String projectID,
			InitData initTXTFile, String projectFolderPath, String modelversion,
			String modelauthor, String modelcompany, String modelcomment) throws Exception {
		System.out.println("ModelInit createXML: " + modelName);
			write(appendToRoot(modelName, projectID, modelversion, modelauthor, modelcompany,
					modelcomment, initTXTFile),
					projectFolderPath + "\\ModelInit.xml");
	}

	// TODO If Date will be important in the future
	// StringBuffer issudate = new StringBuffer();
	// Calendar cal = Calendar.getInstance ();
	// DateFormat dfmt = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
	// issudate.append(dfmt.format(cal.getTime()));

	/**
	 * Append to root.
	 *
	 * @param modelName the model name
	 * @param simprojID the simproj id
	 * @param modelversion the modelversion
	 * @param modelauthor the modelauthor
	 * @param modelcompany the modelcompany
	 * @param modelcomment the modelcomment
	 * @param initdata the initdata
	 * @return the document
	 */
	private static Document appendToRoot(String modelName, String simprojID,
			String modelversion, String modelauthor, String modelcompany,
			String modelcomment, InitData initdata) {
		
		Element root = new Element("model");
		// --model general
		{
			Element general = new Element("general");

			Element name = new Element("name");
			name.appendChild(modelName);
			Element version = new Element("version");
			version.appendChild(modelversion);
			Element id = new Element("id");
			id.appendChild(simprojID);
			Element n_states = new Element("n_states");
			n_states.appendChild(initdata.states.toString());
			Element n_algebraics = new Element("n_algebraics");
			n_algebraics.appendChild(initdata.alg_vars.toString());
			Element n_parameters = new Element("n_parameters");
			n_parameters.appendChild(initdata.parameters.toString());
			
			Element n_int_parameters = new Element("n_int_parameters");
			n_int_parameters.appendChild(initdata.n_int_parameters.toString());
			Element n_int_variables = new Element("n_int_variables");
			n_int_variables.appendChild(initdata.n_int_variables.toString());
			Element n_bool_parameters = new Element("n_bool_parameters");
			n_bool_parameters.appendChild(initdata.n_bool_parameters.toString());
			Element n_bool_variables = new Element("n_bool_variables");
			n_bool_variables.appendChild(initdata.n_bool_variables.toString());
			
			Element n_string_parameters = new Element("n_string_parameters");
			n_string_parameters.appendChild(initdata.string_parameters
					.toString());
			Element n_string_variables = new Element("n_string_variables");
			n_string_variables
					.appendChild(initdata.string_variables.toString());
			Element comment = new Element("comment");
			comment.appendChild(modelcomment);
			Element author = new Element("author");
			author.appendChild(modelauthor);
			Element company = new Element("company");
			company.appendChild(modelcompany);

			general.appendChild(name);
			general.appendChild(id);
			general.appendChild(version);
			general.appendChild(author);
			general.appendChild(company);
			general.appendChild(comment);

			general.appendChild(n_states);
			general.appendChild(n_algebraics);
			general.appendChild(n_parameters);
			general.appendChild(n_int_parameters);
			general.appendChild(n_int_variables);
			general.appendChild(n_bool_parameters);
			general.appendChild(n_bool_variables);
			general.appendChild(n_string_parameters);
			general.appendChild(n_string_variables);

			root.appendChild(general);
		}

		// Mainclass
		{
			Element mainclass = new Element("mainclass");
			{
				Attribute mainclass_name = new Attribute("name", "main");
				mainclass.addAttribute(mainclass_name);
			}

			// Mainclass general
			{
				Element general = new Element("general");

				Element type = new Element("type");

				Scanner sc = new Scanner(initdata.fileName);
				sc.useDelimiter("_init");
				type.appendChild(sc.next());

				Element comment = new Element("comment");
				comment.appendChild("no comment yet");
				general.appendChild(type);
				general.appendChild(comment);

				mainclass.appendChild(general);
			}

			// Mainclass variables
			{
				Element variables = new Element("variables");
				{
					/*
					 * ADD STATES
					 */
					for (String fullQualifiedName_state : initdata.orderdStateProperties) {
						Element var = new Element("var");
						{
							Attribute var_name = new Attribute("name",
									fullQualifiedName_state);
							var.addAttribute(var_name);
						}
						// Var general
						{
							Element general = new Element("general");

							Element type = new Element("type");
							type.appendChild("Real");
							Element comment = new Element("comment");
							comment.appendChild("no comment yet");
							Element plot = new Element("plot");
							plot.appendChild("false");

							general.appendChild(type);
							general.appendChild(comment);
							general.appendChild(plot);

							var.appendChild(general);
						}

						// Var value
						{
							Element value = new Element("value");
							value.appendChild(initdata.stateProperties.get(
									fullQualifiedName_state).toString());
							var.appendChild(value);
						}
						variables.appendChild(var);
					}// end add states in for loop

					/*
					 * ADD ALG VARS
					 */
					for (String fullQualifiedName_alg : initdata.orderdAlgProperties) {
						Element var = new Element("var");
						{
							Attribute var_name = new Attribute("name",
									fullQualifiedName_alg);
							var.addAttribute(var_name);
						}
						// Var general
						{
							Element general = new Element("general");

							Element type = new Element("type");
							type.appendChild("Real");
							Element comment = new Element("comment");
							comment.appendChild("no comment yet");
							Element plot = new Element("plot");
							plot.appendChild("false");

							general.appendChild(type);
							general.appendChild(comment);
							general.appendChild(plot);

							var.appendChild(general);
						}

						// Var value
						{
							Element value = new Element("value");
							value.appendChild(initdata.algProperties.get(
									fullQualifiedName_alg).toString());

							var.appendChild(value);
						}
						variables.appendChild(var);
					}// end add states in for loop

					/*
					 * ADD INT VARS
					 */
					for (String fullQualifiedName_int_var : initdata.orderdN_int_variablesProperties) {
						Element var = new Element("var");
						{
							Attribute var_name = new Attribute("name",
									fullQualifiedName_int_var);
							var.addAttribute(var_name);
						}
						// Var general
						{
							Element general = new Element("general");

							Element type = new Element("type");
							type.appendChild("Integer");
							Element comment = new Element("comment");
							comment.appendChild("no comment yet");
							Element plot = new Element("plot");
							plot.appendChild("false");

							general.appendChild(type);
							general.appendChild(comment);
							general.appendChild(plot);

							var.appendChild(general);
						}

						// Var value
						{
							Element value = new Element("value");
							value
									.appendChild(initdata.n_int_variablesProperties
											.get(fullQualifiedName_int_var)
											.toString());

							var.appendChild(value);
						}
						variables.appendChild(var);
					}// end add states in for loop

					/*
					 * ADD BOOL VARS
					 */
					for (String fullQualifiedName_alg : initdata.orderdN_bool_variablesProperties) {
						Element var = new Element("var");
						{
							Attribute var_name = new Attribute("name",
									fullQualifiedName_alg);
							var.addAttribute(var_name);
						}
						// Var general
						{
							Element general = new Element("general");

							Element type = new Element("type");
							type.appendChild("Boolean");
							Element comment = new Element("comment");
							comment.appendChild("no comment yet");
							Element plot = new Element("plot");
							plot.appendChild("false");

							general.appendChild(type);
							general.appendChild(comment);
							general.appendChild(plot);

							var.appendChild(general);
						}

						// Var value
						{
							Element value = new Element("value");
							value
									.appendChild(initdata.n_bool_variablesProperties
											.get(fullQualifiedName_alg)
											.toString());

							var.appendChild(value);
						}
						variables.appendChild(var);
					}// end add states in for loop
				}
				mainclass.appendChild(variables);
			}

				// Mainclass parameters

				Element parameters = new Element("parameters");
				{
					for (String fullQualifiedName_parameter : initdata.orderdParameterProperties) {
						Element par = new Element("par");
						{
							Attribute par_name = new Attribute("name",
									fullQualifiedName_parameter);
							par.addAttribute(par_name);
						}
						// par general
						{
							Element general = new Element("general");

							Element type = new Element("type");
							type.appendChild("Real");
							Element comment = new Element("comment");
							comment.appendChild("no comment yet");
							Element plot = new Element("plot");
							plot.appendChild("false");
							Element interactive = new Element("interactive");
							interactive.appendChild("false");

							general.appendChild(type);
							general.appendChild(comment);
							general.appendChild(plot);
							general.appendChild(interactive);

							par.appendChild(general);
						}

						// par value
						{
							Element value = new Element("value");
							value.appendChild(initdata.parameterProperties.get(
									fullQualifiedName_parameter).toString());
							par.appendChild(value);

							Element minValue = new Element("minValue");
							par.appendChild(minValue);
							Element maxValue = new Element("maxValue");
							par.appendChild(maxValue);
						}
						parameters.appendChild(par);
					}// end add parameters in for loop
					
					/*
					 * INT PARAMETER
					 */
					for (String fullQualifiedName_parameter : initdata.orderdN_int_parametersProperties) {
						Element par = new Element("par");
						{
							Attribute par_name = new Attribute("name",
									fullQualifiedName_parameter);
							par.addAttribute(par_name);
						}
						// par general
						{
							Element general = new Element("general");

							Element type = new Element("type");
							type.appendChild("Integer");
							Element comment = new Element("comment");
							comment.appendChild("no comment yet");
							Element plot = new Element("plot");
							plot.appendChild("false");
							Element interactive = new Element("interactive");
							interactive.appendChild("false");

							general.appendChild(type);
							general.appendChild(comment);
							general.appendChild(plot);
							general.appendChild(interactive);

							par.appendChild(general);
						}

						// par value
						{
							Element value = new Element("value");
							value.appendChild(initdata.n_int_parametersProperties.get(
									fullQualifiedName_parameter).toString());
							par.appendChild(value);

							Element minValue = new Element("minValue");
							par.appendChild(minValue);
							Element maxValue = new Element("maxValue");
							par.appendChild(maxValue);
						}
						parameters.appendChild(par);
					}// end add parameters in for loop
					
					
					/*
					 * BOOL PARAMETER
					 */
					for (String fullQualifiedName_parameter : initdata.orderdN_bool_parametersProperties) {
						Element par = new Element("par");
						{
							Attribute par_name = new Attribute("name",
									fullQualifiedName_parameter);
							par.addAttribute(par_name);
						}
						// par general
						{
							Element general = new Element("general");

							Element type = new Element("type");
							type.appendChild("Boolean");
							Element comment = new Element("comment");
							comment.appendChild("no comment yet");
							Element plot = new Element("plot");
							plot.appendChild("false");
							Element interactive = new Element("interactive");
							interactive.appendChild("false");

							general.appendChild(type);
							general.appendChild(comment);
							general.appendChild(plot);
							general.appendChild(interactive);

							par.appendChild(general);
						}

						// par value
						{
							Element value = new Element("value");
							value.appendChild(initdata.n_bool_parametersProperties.get(
									fullQualifiedName_parameter).toString());
							par.appendChild(value);

							Element minValue = new Element("minValue");
							par.appendChild(minValue);
							Element maxValue = new Element("maxValue");
							par.appendChild(maxValue);
						}
						parameters.appendChild(par);
					}// end add parameters in for loop
					
				}

				mainclass.appendChild(parameters);

				Element inputVars = new Element("inputVars");
				mainclass.appendChild(inputVars);
				Element outputVars = new Element("outputVars");
				mainclass.appendChild(outputVars);
				Element string_parameters = new Element("string_parameters");
				mainclass.appendChild(string_parameters);
				Element string_variables = new Element("string_variables");
				mainclass.appendChild(string_variables);

			// end Mainclass
			root.appendChild(mainclass);
		}

		Document doc = new Document(root);
		return doc;
	}

	/**
	 * Write.
	 *
	 * @param doc the doc
	 * @param filename the filename
	 * @throws Exception the exception
	 */
	private static void write(Document doc, String filename) throws Exception {

		FileOutputStream f = new FileOutputStream(filename);
		Serializer serializer = new Serializer(f, "ISO-8859-1");
		serializer.setIndent(4);
		serializer.write(doc);
		f.close();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		String pathToInit = "C:\\AircraftWaterTankSystem.TestEnvironment_init.xml";

		try {
				createXML(
					"TestProj", "123",
					pathToInit,
					"C:\\AircraftWaterTankSystem.TestEnvironment_SimInit.xml",
					"1.3", "Parham Vasaiely", "EADS IW", "No comment yet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
