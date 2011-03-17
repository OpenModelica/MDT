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

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import org.openmodelica.simulation.core.models.simulation.SimulationSetting;
import org.openmodelica.simulation.core.models.simulation.SimulationSettingInteractive;

// TODO: Auto-generated Javadoc
/**
 * This class is responsible to create a simulation setting XML file. 
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationSetting_XML_generator {


	/**
	 * Creates the Simulation Setting XML and stores it to a given folder path.
	 *
	 * @param projectName the project name
	 * @param projectID the project id
	 * @param simsetting full initialized session setting
	 * @param path full path to session configuration folder
	 * @throws Exception the exception
	 */
	public static void createXML(String projectName, String projectID, SimulationSetting simsetting, String path) throws Exception {

		write(appendToRoot(projectName, projectID, simsetting), path + "\\SimSetting.xml");
	}
	
	/**
	 * Append to root.
	 *
	 * @param projectName the project name
	 * @param projectID the project id
	 * @param simsetting the simsetting
	 * @return the document
	 */
	private static Document appendToRoot(String projectName, String projectID, SimulationSetting simsetting) {
		
		Element root = new Element("settings");
		{
			Element project = new Element("project");
			{
				Element name = new Element("name");
				name.appendChild(projectName);
				Element id = new Element("id");
				id.appendChild(projectID);
				
				project.appendChild(name);
				project.appendChild(id);
			}
			Element session = new Element("session");
			{
				Element name = new Element("name");
				name.appendChild(simsetting.getName());
				Element type = new Element("type");
				type.appendChild(simsetting.getType());
				Element version = new Element("version");
				version.appendChild(simsetting.getVersion());
				Element author = new Element("author");
				author.appendChild(simsetting.getAuthor());
				Element company = new Element("company");
				company.appendChild(simsetting.getCompany());
				Element comment = new Element("comment");
				comment.appendChild(simsetting.getComment());
				Element numberOfRuns = new Element("numberOfRuns");
				comment.appendChild(simsetting.getNumberOfRuns()+"");
				
				session.appendChild(name);
				session.appendChild(type);
				session.appendChild(version);
				session.appendChild(author);
				session.appendChild(company);
				session.appendChild(comment);
				session.appendChild(numberOfRuns);
			}
			
			//Will only be used if the setting is for an interactive simulation
			Element network = new Element("network");
			if(simsetting.getType().equals("i")){
				{
					Element runtimeControlServer = new Element("runtimeControlServer");
					{
						Element ip = new Element("ip");
						ip.appendChild(((SimulationSettingInteractive)simsetting).getRuntimeControlServerIp());
						Element port = new Element("port");
						port.appendChild(((SimulationSettingInteractive)simsetting).getRuntimeControlServerPort());
						
						runtimeControlServer.appendChild(ip);
						runtimeControlServer.appendChild(port);
					}
					Element simulationControlServer = new Element("simulationControlServer");
					{
						Element ip = new Element("ip");
						ip.appendChild(((SimulationSettingInteractive)simsetting).getSimulationControlServerIp());
						Element port = new Element("port");
						port.appendChild(((SimulationSettingInteractive)simsetting).getSimulationControlServerPort());
						
						simulationControlServer.appendChild(ip);
						simulationControlServer.appendChild(port);
					}
					Element simulationTransferServer = new Element("simulationTransferServer");
					{
						Element ip = new Element("ip");
						ip.appendChild(((SimulationSettingInteractive)simsetting).getSimulationTransferServerIp());
						Element port = new Element("port");
						port.appendChild(((SimulationSettingInteractive)simsetting).getSimulationTransferServerPort());
						
						simulationTransferServer.appendChild(ip);
						simulationTransferServer.appendChild(port);
					}
					network.appendChild(runtimeControlServer);
					network.appendChild(simulationControlServer);
					network.appendChild(simulationTransferServer);
				}
			}
			Element simulation = new Element("simulation");
			{
				Element start = new Element("start");
				start.appendChild(simsetting.getStart());
				Element stop = new Element("stop");
				stop.appendChild(simsetting.getStop());
				Element simStepTime = new Element("simStepTime");
				Attribute a = new Attribute("unit", "s");
				simStepTime.addAttribute(a);
				simStepTime.appendChild(simsetting.getSimStepTime());
				Element tolerance = new Element("tolerance");
				tolerance.appendChild(simsetting.getTolerance());
				Element solver = new Element("solver");
				solver.appendChild(simsetting.getSolver());
				
				simulation.appendChild(start);
				simulation.appendChild(stop);
				simulation.appendChild(simStepTime);
				simulation.appendChild(tolerance);
				simulation.appendChild(solver);
			}
			root.appendChild(project);
			root.appendChild(session);
			if(simsetting.getType().equals("i")){
				root.appendChild(network);
			}
			root.appendChild(simulation);
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
}
