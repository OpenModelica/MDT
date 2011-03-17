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

import java.io.FileOutputStream;
import java.util.Map;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import org.openmodelica.simulation.core.simulationresult_txt_handling.Result_TXT_reader;

// TODO: Auto-generated Javadoc
/**
 * Generates the SimInit.xml file from the SimProj.xml and init.txt
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 * 
 */

public class SimulationResult_XML_generator {

	/**
	 * Creates a XML file containing all results from a *.plt file from omc
	 * //	 * @param modelName Name of the Model NOTE: not used yet
	 *
	 * @param modelName the model name
	 * @param projectID unique id of the project NOTE: not used yet
	 * @param pathToResult the full qualified path to the result *.plt file
	 * @param projectFolderPath folder where the result.xml file should be stored after generating it
	 * @throws Exception the exception
	 */
	public static void createXML(String modelName, String projectID, String pathToResult, String projectFolderPath) throws Exception {
			
		write(appendToRoot(Result_TXT_reader.readResult(pathToResult)),
					projectFolderPath + "\\results.xml");
	}

	/**
	 * Append to root.
	 *
	 * @param results the results
	 * @return the document
	 */
	private static Document appendToRoot(Map<String, Map<String, String>> results) {
		Element root = new Element("results");
		// --model general
		{
			Element general = new Element("general");
			root.appendChild(general);
		}
		{
			Element datasets = new Element("datasets");
			
			for(String datasetname : results.keySet()){
				Element result = new Element("result");
				Attribute name = new Attribute("name", datasetname);
				result.addAttribute(name);				
				for(String timeValue : results.get(datasetname).keySet()){
					Element data = new Element("data");
					Attribute t = new Attribute("t", timeValue);
					data.addAttribute(t);
					data.appendChild(results.get(datasetname).get(timeValue));
					result.appendChild(data);
				}
				datasets.appendChild(result);
			}
			root.appendChild(datasets);
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

		try {
				createXML(
					"TestProj", "123", "D:\\myUMLModel.Class_0_res.plt", "D:\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
