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
package org.openmodelica.simulation.core.models.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.xml.reader.SimulationProject_XML_reader;

// TODO: Auto-generated Javadoc
/**
 * The SimulationProject contains all information about a simulation project
 * which are stored for example in the org.openmodelica.simulation.projects path of the eclipse
 * workspace
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 * 
 */
public class SimulationProject {

	/** Simulation project name. */
	private String projectName;
	
	/** Simulation project identification number. */
	private String projectId;
	
	/** Simulation project version. */
	private String projectVersion;
	
	/** Simulation project author. */
	private String projectAuthor;
	
	/** Company name, respective rights. */
	private String projectCompany;
	
	/** Simulation project designer or user comments. */
	private String projectComment;
	
	/** Simulation project root folder. */
	private String simulationProjectFolderPath;
	
	/** Modelica model which will be used to be simulated. */
	private ModelicaModel modelicaModel;
	
	/** Container with all simulation session configuration data Key=Session Configuration name, Value=Session Config object. */
	private Map<String, SimulationSessionConfiguration> simSessConfigs;

	/**
	 * Use this constructor if you want to generate a SimulationProject using
	 * the project wizard.
	 *
	 * @param projectName Simulation project name
	 * @param id Simulation project identification number
	 * @param version Simulation project version
	 * @param author Simulation project author
	 * @param company Company name, respective rights
	 * @param comment simulation project designer or user comments
	 * @param simulationProjectFolderPath Path to the root simulation project folder
	 */
	public SimulationProject(String projectName, String id, String version, String author, String company, String comment, String simulationProjectFolderPath) {
		this.projectName = projectName;
		this.projectId = id;
		this.projectVersion = version;
		this.projectAuthor = author;
		this.projectCompany = company;
		this.projectComment = comment;
		this.simulationProjectFolderPath = simulationProjectFolderPath;
		simSessConfigs = new HashMap<String, SimulationSessionConfiguration>();
	}
	
	/**
	 * Use this constructor if you want to generate a SimulationProject object
	 * from a SimProject.xml file Note: Please set the simulation project folder
	 * path afterwards
	 * 
	 * @param projectName
	 *            Simulation project name
	 * @param id
	 *            Simulation project identification number
	 * @param version
	 *            Simulation project version
	 * @param author
	 *            Simulation project author
	 * @param company
	 *            Company name, respective rights
	 * @param comment
	 *            Simulation project designer or user comments
	 */
	public SimulationProject(String projectName, String id, String version, String author, String company, String comment) {
		this.projectName = projectName;
		this.projectId = id;
		this.projectVersion = version;
		this.projectAuthor = author;
		this.projectCompany = company;
		this.projectComment = comment;
		simSessConfigs = new HashMap<String, SimulationSessionConfiguration>();
	}

	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the project name.
	 *
	 * @param projectName the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public String getProjectId() {
		return projectId;
	}

	//TODO
//	public void setModelicaProj(String initial_path) {
//		// System.out.println(initial_path + "\\" + modelicamodel +
//		// "_SimInit.xml");
//		modelicaModel = ModelicaModel_XML_reader.readFromXML(initial_path
//				+ "\\" + modelicamodel + "_SimInit.xml");
//	}

	//TODO NOT OKEY
	/**
	 * Returns the used Modelica model as initial model.
	 *
	 * @return the modelica model
	 */
	public ModelicaModel getModelicaModel() {
		return modelicaModel;
	}
	
	/**
	 * Sets the modelica model.
	 *
	 * @param modelicaModel the new modelica model
	 */
	public void setModelicaModel(ModelicaModel modelicaModel){
		this.modelicaModel = modelicaModel;
	}

	/**
	 * Gets the project version.
	 *
	 * @return the project version
	 */
	public String getProjectVersion() {
		return projectVersion;
	}

	/**
	 * Sets the project version.
	 *
	 * @param version the new project version
	 */
	public void setProjectVersion(String version) {
		this.projectVersion = version;
	}

	/**
	 * Gets the project author.
	 *
	 * @return the project author
	 */
	public String getProjectAuthor() {
		return projectAuthor;
	}

	/**
	 * Sets the project author.
	 *
	 * @param author the new project author
	 */
	public void setProjectAuthor(String author) {
		this.projectAuthor = author;
	}

	/**
	 * Gets the project company.
	 *
	 * @return the project company
	 */
	public String getProjectCompany() {
		return projectCompany;
	}

	/**
	 * Sets the project company.
	 *
	 * @param company the new project company
	 */
	public void setProjectCompany(String company) {
		this.projectCompany = company;
	}

	/**
	 * Gets the project comment.
	 *
	 * @return the project comment
	 */
	public String getProjectComment() {
		return projectComment;
	}

	/**
	 * Sets the project comment.
	 *
	 * @param comment the new project comment
	 */
	public void setProjectComment(String comment) {
		this.projectComment = comment;
	}
	
	/**
	 * Full qualified model name like TwoTanks.TanksConnectedPI
	 *
	 * @return the full qualified modelica model name
	 */
	public String getFullQualifiedModelicaModelName(){
		return modelicaModel.getMainclass().getType();
	}

	/**
	 * Adds a simulation session configuration to the simulation project
	 * Note: Name for this configuration file should be unique otherwise it will overwrite an existing session configuration file from a simulation project
	 * Please ask if name is in use before adding an configuration.
	 *
	 * @param simSessConfig a new Simulation Session configuration
	 */
	public void addSimSessConfigs(SimulationSessionConfiguration simSessConfig) {
		this.simSessConfigs.put(simSessConfig.getConfigurationName(),
				simSessConfig);
	}

	/**
	 * Returns an Simulation Session Configuration from the session config container.
	 *
	 * @param name Configuration name to find a specified configuration from the container
	 * @return a Simulation Session Configuration object
	 */
	public SimulationSessionConfiguration getSimSessConfig(String name) {
		return simSessConfigs.get(name);
	}
	
	/**
	 * Returns all available simulation session configurations for a simulation project as a set of config names.
	 *
	 * @return a set of all simulation session configuration names
	 */
	public Set<String> getSimSessionConfigs(){
		return simSessConfigs.keySet();
	}

	/**
	 * TODO the simulationProjectFolderPath has not been initialized yet!.
	 *
	 * @return the folder path to a simulation project
	 */
	public String getSimulationProjectFolderPath() {
		return simulationProjectFolderPath;
	}
	
	/**
	 * Sets the simulation project folder path.
	 *
	 * @param simulationProjectFolderPath the new simulation project folder path
	 */
	public void setSimulationProjectFolderPath(String simulationProjectFolderPath) {
		this.simulationProjectFolderPath = simulationProjectFolderPath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return projectName + " # " + projectId + " # " + projectComment + " # "
		+ "MODELICAMODEL\n"
		+ modelicaModel.toString() + simSessConfigs.toString();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String projectPath = "F:\\Eclipse_Workspaces\\workspace_EADS_OMI_02\\org.openmodelica.simulation.projects\\SimulationProject001_Interactive\\";
		
		SimulationProject sp = SimulationProject_XML_reader
		.readFromXML(projectPath
				+ "SimulationProject001_SimProject.xml");
		
		sp.addSimSessConfigs(new SimulationSessionConfiguration("SimulationSessionConfig001", "none", projectPath + "\\SimulationSessionConfig001"));
		System.out.println(sp);
	}
}
