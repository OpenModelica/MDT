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
package org.openmodelica.simulation.environment.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.core.models.simulation.SimulationSessionConfiguration;
import org.openmodelica.simulation.core.xml.reader.ModelicaModel_XML_reader;
import org.openmodelica.simulation.core.xml.reader.SimulationProject_XML_reader;

// TODO: Auto-generated Javadoc
/**
 * The Simulation Project Center manages all data concerning the available modelica projects
 * For example: All Projects, Session Configurations, Environment Setting and Properties...
 *  
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class SimulationProjectCenter extends Observable{
	
	/**
	 * Lists all available simulation projects from the project workspace org.openmodelica.simulation.environment.projects
	 * Key: Project Name, Value: Project root folder as file
	 */
	private Map<String, File> simulationProjects;
	
	/**
	 * Referees to the application workspace folder (org.openmodelica.simulation.projects) in Eclipse Workspace 
	 */
	private File applicationWorkspaceFolder;
	
	/** The selected simulation project representation. */
	private SimulationProjectRepresentation selectedSimulationProjectRepresentation;
	
	/**
	 * Instantiates a new simulation project center.
	 */
	public SimulationProjectCenter() {
		simulationProjects = new HashMap<String, File>();
		selectedSimulationProjectRepresentation = new SimulationProjectRepresentation(null);
		initialize();
	}
	
	/**
	 * This method will be called after creating a new or import an existing simulation project
	 * into the workspace... 
	 * 
	 * TODO [201009] Verknüpfungen müssen beachtet werden da nicht alle projecte im workspace liegen müssen
	 */
	public void changeInWorkspace() {
		initialize();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Adds the observer to sim pro center.
	 *
	 * @param newOberver the new oberver
	 */
	public void addObserverToSimProCenter(Observer newOberver){
		if(newOberver != null){
			this.addObserver(newOberver);
		}
	}
		
	/**
	 * Initialize.
	 */
	private void initialize(){
		simulationProjects.clear();
		File workspaceFolder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		applicationWorkspaceFolder = new File( workspaceFolder.getPath() + "/org.openmodelica.simulation.projects" );
		applicationWorkspaceFolder.mkdir();
		//Add all available simulation projects from the project workspace to the environment
		//TODO [20100601]As File or as String into the map? because File could restrict the usage out of eclipse
		for(File f : applicationWorkspaceFolder.listFiles()){
			if(f.isDirectory())
				simulationProjects.put(f.getName(), f);
		}
	}
	
	/**
	 * Searchs for a simulation project by name, creates a Fully initialized SimulationProject object
	 * (containing SimProject.xml, ModelInit.xml and all available SessionConfiguration files) and returns it.
	 * @param name a simulation project name
	 * @return Fully initialized SimulationProject object
	 */
	public SimulationProject getSimulationProject(String name) {
		SimulationProject tempSimulationProject;

		tempSimulationProject = SimulationProject_XML_reader
				.readFromXML(simulationProjects.get(name).getPath() + "/SimProject.xml"); // TODO für Linux geeignet?

		// Initial modelica model data has been added
		tempSimulationProject.setModelicaModel(ModelicaModel_XML_reader
				.readFromXML(simulationProjects.get(name).getPath()
						+ "/ModelInit.xml"));

		// Add all configuration files
		for (File configFolder : new File(simulationProjects.get(name)
				.getPath()
				+ "/SimulationSessionConfigurations").listFiles()) {
			if (configFolder.isDirectory()) {
				tempSimulationProject
						.addSimSessConfigs(new SimulationSessionConfiguration(
								configFolder.getName(), configFolder.getPath()));
			}
		}

		return tempSimulationProject;
	}
	
	/**
	 * Path of a simulation project folder.
	 *
	 * @param name a simulation project name
	 * @return Path of a simulation project folder
	 */
	public String getSimulationProjectPath(String name) {
		
		return simulationProjects.get(name).getPath();
	}
	
	/**
	 * Requests the actual selected SimulationProject.
	 *
	 * @return the actual selected simulation project or null if nothing has been selected
	 */
	public SimulationProject getSelectedSimulationProject(){
		return selectedSimulationProjectRepresentation.getSelectedSimulationProject();
	}
	
	/**
	 * Set the selected Simulation Project
	 * E.g. The SimulationProjectListingView can set the selected SimulationProject to be displayed on the SimulationProjectDataView
	 * @param projectName Name of the selected project or null if nothing has been selected
	 */
	public void setSelectedSimulationProject(String projectName){
//		System.out.println("selectedProjectName: " + projectName);
		if(projectName == null || projectName.equals(""))
			selectedSimulationProjectRepresentation.setSelectedSimulationProjectToNull();
		else
			selectedSimulationProjectRepresentation.initSelectedSimulationProject(simulationProjects.get(projectName));
	}
	
	/**
	 * Gets the simulation projects.
	 *
	 * @return All available simulation projects from the Eclipse simulation project workspace
	 */
	public Set<String> getSimulationProjects(){
		return simulationProjects.keySet();
	}
	
	/**
	 * Adds a new view, to view all project data.
	 *
	 * @param newOberver New view/data-view/composite as an observer
	 */
	public void addDataViewComposite(Observer newOberver){
		if(newOberver != null){
			addObserverForSelectedSimulationProject(newOberver);
		}
	}
	
	/**
	 * Adds a new Observer to the SimulationProjectRepresentation.
	 *
	 * @param newObserver - an observer to be added
	 */
	private void addObserverForSelectedSimulationProject(Observer newObserver) {
		selectedSimulationProjectRepresentation.addObserver(newObserver);
	}
	
	/**
	 * Returns the time that the file denoted by this abstract pathname was last modified.
	 *
	 * @param name A String which represents the project name
	 * @return A long value representing the time the file was last modified, measured in milliseconds since the epoch (00:00:00 GMT, January 1, 1970), or 0L if the file does not exist or if an I/O error occurs
	 */
	public Long getFileLastModifiedDate(String name){
		return simulationProjects.get(name).lastModified();
	}
	
	/**
	 * Es wäre auch möglich das SimulationProjectCenter Observable zu machen aber da nur einmal
	 * geerbt werden kann ist dies wohl die bessere alternative.
	 *
	 * @author Parham Vasaiely
	 */
	private class SimulationProjectRepresentation extends Observable{
	    
    	/** Selected Simulation Project to be observed, simulated or tested If null: No Project has been selected. */
		private SimulationProject selectedSimulationProject;
		
		/**
		 * Instantiates a new simulation project representation.
		 *
		 * @param selectedSimulationProject the selected simulation project
		 */
		private SimulationProjectRepresentation(SimulationProject selectedSimulationProject) {
			this.selectedSimulationProject = selectedSimulationProject;
		}
		
		/**
		 * Gets the selected simulation project.
		 *
		 * @return the selected simulation project
		 */
		private synchronized SimulationProject getSelectedSimulationProject(){
			return selectedSimulationProject;
		}
		
		/**
		 * this method can be used if no simulation project has been selected.
		 */
		private synchronized void setSelectedSimulationProjectToNull(){
			selectedSimulationProject = null;
			this.setChanged();
			notifyObservers();
		}
		
		/**
		 * To optimize the usage and speed of listing all projects, it is recommended to read project
		 * data from the XML Files (e.g. Information, Configs, Tests...) after selecting a project. This Method reads the data and returns a full
		 * initialized SimulationProject to be displayed
		 *
		 * @param selectedSimulationProjectFile the selected simulation project file
		 * @return Full initialized SimulationProject (e.g. Information, Configs, Tests...)
		 */
		private synchronized void initSelectedSimulationProject(
				File selectedSimulationProjectFile) {
			selectedSimulationProject = SimulationProject_XML_reader
					.readFromXML(selectedSimulationProjectFile.getPath() + "/"
							+ "SimProject.xml"); // TODO für Linux geeignet?

			//Initial modelica model data has been added
			selectedSimulationProject.setModelicaModel(ModelicaModel_XML_reader.readFromXML(selectedSimulationProjectFile.getPath() + "/ModelInit.xml"));
			
			//Add all configuration files
			for(File  configFolder : new File(selectedSimulationProjectFile.getPath() + "/SimulationSessionConfigurations").listFiles()){
				if(configFolder.isDirectory()){
					selectedSimulationProject
					.addSimSessConfigs(new SimulationSessionConfiguration(configFolder.getName(), configFolder.getPath()));
				}
			}
			this.setChanged();
			notifyObservers();
		}
	}
}
