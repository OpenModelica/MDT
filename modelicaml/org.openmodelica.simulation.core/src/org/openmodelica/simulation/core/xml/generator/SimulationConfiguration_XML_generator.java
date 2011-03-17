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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.simulation.core.xml.generator;

	import java.io.File;
import java.io.FileOutputStream;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.models.modelica.ModelicaParameter;
import org.openmodelica.simulation.core.models.modelica.ModelicaProperty;
import org.openmodelica.simulation.core.xml.reader.ModelicaModel_XML_reader;
// TODO: Auto-generated Javadoc
/**
 * Generates the *_SimConfig.xml
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
	public class SimulationConfiguration_XML_generator {

//		static HashMap<String, ArrayList<String>> infos;
		
		/**
 * Creates the xml.
 *
 * @param moproj the moproj
 * @param projectConfigFolderPath the project config folder path
 * @throws Exception the exception
 */
public static void createXML(ModelicaModel moproj, String projectConfigFolderPath) throws Exception{
			
//			System.out.println(path);
//			infos = new HashMap<String, ArrayList<String>>();
			
			write(appendToRoot(moproj), projectConfigFolderPath + "\\SimConfig.xml");
		}
		
//TODO If Date will be important in the future
//			StringBuffer issudate = new StringBuffer();			
//			Calendar cal = Calendar.getInstance ();
//			DateFormat dfmt = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
//			issudate.append(dfmt.format(cal.getTime()));
		
		
		
		/**
 * Append to root.
 *
 * @param modelicaModel the modelica model
 * @return the document
 */
private static Document appendToRoot(ModelicaModel modelicaModel){
			Element root = new Element("model");
			//--model general
			{
				Element general = new Element("general");
				
				Element name = new Element("name");
				name.appendChild(modelicaModel.getName());
				Element version = new Element("version");
				version.appendChild(modelicaModel.getVersion());
				Element id = new Element("id");
				id.appendChild(modelicaModel.getId());
				Element n_states = new Element("n_states");
				n_states.appendChild(modelicaModel.getN_states().toString());
				Element n_algebraics = new Element("n_algebraics");
				n_algebraics.appendChild(modelicaModel.getN_algebraics().toString());
				Element n_parameters = new Element("n_parameters");
				n_parameters.appendChild(modelicaModel.getN_parameters().toString());
				
				Element n_int_parameters = new Element("n_int_parameters");
				n_int_parameters.appendChild(modelicaModel.getN_int_parameters().toString());
				Element n_int_variables = new Element("n_int_variables");
				n_int_variables.appendChild(modelicaModel.getN_int_variables().toString());
				Element n_bool_parameters = new Element("n_bool_parameters");
				n_bool_parameters.appendChild(modelicaModel.getN_bool_parameters().toString());
				Element n_bool_variables = new Element("n_bool_variables");
				n_bool_variables.appendChild(modelicaModel.getN_bool_variables().toString());
				
				Element n_string_parameters = new Element("n_string_parameters");
				n_string_parameters.appendChild(modelicaModel.getN_string_parameters().toString());
				Element n_string_variables = new Element("n_string_variables");
				n_string_variables.appendChild(modelicaModel.getN_string_variables().toString());
				Element comment = new Element("comment");
				comment.appendChild(modelicaModel.getComment());
				Element author = new Element("author");
				author.appendChild(modelicaModel.getAuthor());
				Element company = new Element("company");
				company.appendChild(modelicaModel.getCompany());
				
				
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
			
			//Mainclass
			{
				Element mainclass = new Element("mainclass");
				{
					Attribute mainclass_name = new Attribute("name", modelicaModel.getMainclass().getName());
					mainclass.addAttribute(mainclass_name);
				}
				
				//Mainclass general
				{
					Element general = new Element("general");
					
					Element type = new Element("type");
					type.appendChild(modelicaModel.getMainclass().getType());
					Element comment = new Element("comment");
					comment.appendChild(modelicaModel.getMainclass().getComment());
					general.appendChild(type);
					general.appendChild(comment);
					
					mainclass.appendChild(general);
				}
				
				//Mainclass variables
				{
					Element variables = new Element("variables");
					{
						for(String fullQualifiedName : modelicaModel.getMainclass().getAllFullQualifiedVariableNames()){
							ModelicaProperty mpTemp = modelicaModel.getMainclass().getVariableByName(fullQualifiedName);
							Element var = new Element("var");
							{
								Attribute var_name = new Attribute("name", mpTemp.getName());
								var.addAttribute(var_name);
							}
							//Var general
							{
								Element general = new Element("general");
								
								Element type = new Element("type");
								type.appendChild(mpTemp.getType());
								Element comment = new Element("comment");
								comment.appendChild(mpTemp.getComment());
								Element plot = new Element("plot");
								plot.appendChild(mpTemp.getDisplay().toString());
								
								general.appendChild(type);
								general.appendChild(comment);
								general.appendChild(plot);
								
								var.appendChild(general);
							}
							
							//Var value
							{
								Element value = new Element("value");
								value.appendChild(mpTemp.getPrimitiveValue().toString());
								
								var.appendChild(value);
							}
							variables.appendChild(var);							
						}//end add variables in for loop
					}
					mainclass.appendChild(variables);
				}
				
				
				//Mainclass parameters
				{
					Element parameters = new Element("parameters");
					{
						for(String fullQualifiedName : modelicaModel.getMainclass().getAllFullQualifiedParameterNames()){
							ModelicaParameter mpTemp = (ModelicaParameter)modelicaModel.getMainclass().getParameterByName(fullQualifiedName);
							Element par = new Element("par");
							{
								Attribute par_name = new Attribute("name", mpTemp.getName());
								par.addAttribute(par_name);
							}
							//par general
							{
								Element general = new Element("general");
								
								Element type = new Element("type");
								type.appendChild(mpTemp.getType());
								Element comment = new Element("comment");
								comment.appendChild(mpTemp.getComment());
								Element plot = new Element("plot");
								plot.appendChild(mpTemp.getDisplay().toString());
								Element interactive = new Element("interactive");
								interactive.appendChild(mpTemp.getInteractive().toString());
								
								general.appendChild(type);
								general.appendChild(comment);
								general.appendChild(plot);
								general.appendChild(interactive);
								
								par.appendChild(general);
							}
							
							//par value
							{
								Element value = new Element("value");
								value.appendChild(mpTemp.getPrimitiveValue().toString());
								par.appendChild(value);
								
								if(mpTemp.getType().equals("Real") || mpTemp.getType().equals("Integer")){
									Element minValue = new Element("minValue");
									if(mpTemp.getMinValue() != null)minValue.appendChild(mpTemp.getMinValue().toString());
									Element maxValue = new Element("maxValue");
									if(mpTemp.getMaxValue() != null)maxValue.appendChild(mpTemp.getMaxValue().toString());
									
									par.appendChild(minValue);
									par.appendChild(maxValue);
								}
							}
							parameters.appendChild(par);							
						}//end add parameters in for loop
					}
					mainclass.appendChild(parameters);
				}
				
				Element inputVars = new Element("inputVars");
				mainclass.appendChild(inputVars);
				Element outputVars = new Element("outputVars");
				mainclass.appendChild(outputVars);
				Element string_parameters = new Element("string_parameters");
				mainclass.appendChild(string_parameters);
				Element string_variables = new Element("string_variables");
				mainclass.appendChild(string_variables);
				
				
			//end Mainclass
				root.appendChild(mainclass);
			}
			
			Document doc = new Document(root);
			return doc;
		}
		
		/**
		 * Write.
		 *
		 * @param doc the doc
		 * @param configFile the config file
		 * @throws Exception the exception
		 */
		private static void write(Document doc, String configFile) throws Exception {
			
			FileOutputStream f = new FileOutputStream(configFile);
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

			try {
				ModelicaModel modelicaModel = ModelicaModel_XML_reader.readFromXML(new File ("F:\\Eclipse_Workspaces\\runtime-EclipseApplication\\org.openmodelica.simulation.projects\\Test2\\Test2_SimInit.xml"));
				createXML(modelicaModel, "E:\\");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}