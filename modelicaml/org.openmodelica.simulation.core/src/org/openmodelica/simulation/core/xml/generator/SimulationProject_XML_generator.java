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

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import org.openmodelica.simulation.core.models.simulation.SimulationProject;

// TODO: Auto-generated Javadoc
/**
 * This class is responsible to create a simulation project XML file.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationProject_XML_generator {

	/**
	 * Creates the Simulation Project XML and stores it to a given folder path from.
	 *
	 * @param simproj the simproj
	 * @param path the path
	 * @throws Exception the exception
	 */
	public static void createXML(SimulationProject simproj, String path) throws Exception {

		// System.out.println(path);
		// infos = new HashMap<String, ArrayList<String>>();

//		write(appendToRoot(simproj), simproj.getSimulationProjectFolderPath() + simproj.getProjectName() + "_SimProject.xml");
		write(appendToRoot(simproj), path + "\\SimProject.xml");
	}

	// TODO If Date will be important in the future
	// StringBuffer issudate = new StringBuffer();
	// Calendar cal = Calendar.getInstance ();
	// DateFormat dfmt = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
	// issudate.append(dfmt.format(cal.getTime()));

	/**
	 * Append to root.
	 *
	 * @param simproj the simproj
	 * @return the document
	 */
	private static Document appendToRoot(SimulationProject simproj) {
		Element root = new Element("project");
		{
			Element name = new Element("name");
			name.appendChild(simproj.getProjectName());
			Element id = new Element("id");
			id.appendChild(simproj.getProjectId());
			Element version = new Element("version");
			version.appendChild(simproj.getProjectVersion());
			Element author = new Element("author");
			author.appendChild(simproj.getProjectAuthor());
			Element company = new Element("company");
			company.appendChild(simproj.getProjectCompany());
			Element comment = new Element("comment");
			comment.appendChild(simproj.getProjectComment());
			
			root.appendChild(name);
			root.appendChild(id);
			root.appendChild(version);
			root.appendChild(author);
			root.appendChild(company);
			root.appendChild(comment);
			
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
	}
}
