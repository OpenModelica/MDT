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
package org.openmodelica.simulation.core.models.simulation;

import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.xml.reader.ModelicaModel_XML_reader;
import org.openmodelica.simulation.core.xml.reader.SimulationSetting_XML_reader;

// TODO: Auto-generated Javadoc
/**
 * A Simulation Session Configuration will be used to specify needed modifications on model values or simulation settings for a single simulation session.
 * The use has the possibility to create different session configurations to test a model and to archive the modifications.
 * The modified model data will be stored in a SimConfig.xml
 * The modified simulation settings will be stored in a SimSetting.xml
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationSessionConfiguration {
	
	/** The configuration name. */
	private String configurationName;
	
	/** The comment. */
	private String comment;
	//*** Data for Simulation Settings ***
	/** The sim setting. */
	private SimulationSetting simSetting;
	
	//*** Model Data for Simulation Configuration generated from SimConfig.xml
	/** The configured_modelica model. */
	private ModelicaModel configured_modelicaModel;
	
	/**
	 * Use this constructor if you want to create a new session config file from a wizard.
	 * NOTE: To avoid Object reference problems: A Wizard should first create a SimConfig.xml and a SimSettig.xml. Afterwards this constructor can create a new object based on the XMLs
	 * The same can be done with a copy method but this is much more comfortable 
	 * @param configurationName Name for this configuration file should be unique otherwise it will overwrite an existing session configuration file from a simulation project
	 * @param comment Specified comments for this simulation configuration
	 * @param initial_path Path to the folder containing the model configuration file "_SimConfig.xml" and the simulation setting file "_SimSetting.xml"
	 */
	public SimulationSessionConfiguration(String configurationName, String comment, String initial_path){
		this.configurationName = configurationName;
		this.comment = comment;
		readModelicaModelConfigFile(initial_path);
		readSimSetting(initial_path);
	}
	
	/**
	 * Use this constructor if you want to create a new config file reading the configs folder.
	 *
	 * @param configurationName Name for this configuration file should be unique otherwise it will overwrite an existing session configuration file from a simulation project
	 * @param initial_path Path to the folder containing the model configuration file "_SimConfig.xml" and the simulation setting file "_SimSetting.xml"
	 */
	public SimulationSessionConfiguration(String configurationName, String initial_path){
		this.configurationName = configurationName;
		readModelicaModelConfigFile(initial_path);
		readSimSetting(initial_path);
	}

	/**
	 * Gets the configuration name.
	 *
	 * @return the configuration name
	 */
	public String getConfigurationName() {
		return configurationName;
	}
	
//	public void setConfigurationName(String configurationName) {
//		this.configurationName = configurationName;
//	}
	
	/**
 * Gets the comment.
 *
 * @return the comment
 */
public String getComment() {
		return comment;
	}
	
	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Reads a model configuration file which is store in the initial_path folder.
	 *
	 * @param initial_path folder which contains the simulation configuration file (_SimConfig.xml)
	 */
	public void readModelicaModelConfigFile(String initial_path){
//		System.out.println(initial_path + "\\" + configurationName + "_SimConfig.xml");
		configured_modelicaModel = ModelicaModel_XML_reader.readFromXML(initial_path + "\\SimConfig.xml");
	}
	
	/**
	 * Returns the configured, modified modelica model used to simulate a model with diffrent data and settings.
	 *
	 * @return Configured Modelica model
	 */
	public ModelicaModel getModelicaModel() {
		return configured_modelicaModel;
	}

	/**
	 * Reads a simulation setting file which is store in the initial_path folder.
	 *
	 * @param initial_path folder which contains the simulation configuration file (_SimSetting.xml)
	 */
	public void readSimSetting(String initial_path) {
//		System.out.println(initial_path + "\\" + configurationName + "_SimSetting.xml");
		simSetting = SimulationSetting_XML_reader.readFromXML(initial_path + "\\SimSetting.xml");
	}

	/**
	 * Gets the sim setting.
	 *
	 * @return the sim setting
	 */
	public SimulationSetting getSimSetting() {
		return simSetting;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "configurationName: " + configurationName + "comment: " + comment + configured_modelicaModel.toString() + simSetting.toString();
	}
}
