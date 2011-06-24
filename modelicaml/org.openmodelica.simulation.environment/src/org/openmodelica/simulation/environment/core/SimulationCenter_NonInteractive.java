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

import java.util.Observer;

import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.core.models.simulation.SimulationSessionConfiguration;
import org.openmodelica.simulation.core.simulationdata.SimulationResultManager_NonInteractive;
import org.openmodelica.simulation.core.simulationresult_txt_handling.Result_TXT_reader;
import org.openmodelica.simulation.core.xml.generator.SimulationResult_XML_generator;
import org.openmodelica.simulation.environment.Activator;
import org.openmodelica.simulation.environment.filehandling.RunNonInteractiveSimulationRuntimeEnvironment;
// TODO: Auto-generated Javadoc
/**
 * The Simulation Center is the central management unit to run a non interactive simulation. 
 * It contains all information and data to simulate a system using
 * the OpenModelica Compiler (OMC). 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationCenter_NonInteractive{
	
	/**
	 * Represents the selected SimulationSessionConfiguration which will be used to
	 * setup the simulation environment and the runtime 
	 * with simulation data (solver, tolerance,...), network settings and model initial values
	 */
	private SimulationSessionConfiguration selectedSimulationSessionConfiguration;
	
    /** Selected Simulation Project to be observed, simulated or tested If null: No Project has been selected. */
	private SimulationProject selectedSimulationProject;
	
	/**
	 * All properties which should display on plot
	 * TODO [20101111] The properties won't be filtered using a plot check button... this will be done in a future version
	 */
	//private List<ModelicaProperty> plotProperties;  //  @jve:decl-index=0:
	
	/**
	 * Result manager for a non interactive simulation
	 */
	private SimulationResultManager_NonInteractive simulationResultManager;
	
	/**
	 * Instantiates a new simulation center_ non interactive.
	 */
	public SimulationCenter_NonInteractive(){
			simulationResultManager = new SimulationResultManager_NonInteractive();
	}
	
	/**
	 * Initializes the whole simulation environment variables.
	 *
	 * @param selectedSimulationSessionConfiguration the selected simulation session configuration
	 */
	public void initAll(SimulationSessionConfiguration selectedSimulationSessionConfiguration) {
		/*
		 * Simulation Data Initialization
		 */
		this.selectedSimulationSessionConfiguration = selectedSimulationSessionConfiguration;
		selectedSimulationProject = Activator.getSimulationProjectCenter().getSelectedSimulationProject();
	}
	

	/*
	 *************************************************************************
	 * Getter and Setter for the fields...
	 *************************************************************************
	 */
	
	/**
	 * Gets the simulation result manager.
	 *
	 * @return a SimulationResultManager
	 * @see #simulationResultManager
	 */
	public SimulationResultManager_NonInteractive getSimulationResultManager() {
		return simulationResultManager;
	}

	/**
	 * Gets the selected simulation session configuration.
	 *
	 * @return the selected simulation session configuration
	 */
	public SimulationSessionConfiguration getSelectedSimulationSessionConfiguration() {
		return selectedSimulationSessionConfiguration;
	}
	
	/*
	 *************************************************************************
	 * Thread and Resource Organization, Management: Start, Stop, ClearAll...
	 *************************************************************************
	 */
	
	/**
	 * After the executing of the simulation runtime a results file "_res.plt" will be created by a simulation runtime
	 * this file must be evaluated and a corresponding XML file will be generated. 
	 * @throws Exception while creating the XML result file
	 */
	public void evaluatingResults() throws Exception{
		
		String resultFilePath = Activator.getSimulationProjectCenter().getSimulationProjectPath(selectedSimulationProject.getProjectName());
		String resultFileName = selectedSimulationProject.getFullQualifiedModelicaModelName() + "_res.plt";
		
		simulationResultManager.setResults(Result_TXT_reader.readResult(resultFilePath + "/" + resultFileName));
		
		//TODO [20101115] results XML will not be used to check performance
//		Result_TXT_reader.readResult(resultFilePath + "/" + resultFileName);
		
		SimulationResult_XML_generator.createXML("", "", resultFilePath + "/" + resultFileName, resultFilePath + "/SimulationSessionConfigurations/" + selectedSimulationSessionConfiguration.getConfigurationName());
	}

	/**
	 * Executes the simulation runtime which creates a result file.
	 */
	public void startRuntimeEnvironment(){
		String exeFilePath = Activator.getSimulationProjectCenter().getSimulationProjectPath(selectedSimulationProject.getProjectName());
		String exeFileName = selectedSimulationProject.getFullQualifiedModelicaModelName() + ".exe";
		
		Thread t = new Thread(new RunNonInteractiveSimulationRuntimeEnvironment(exeFilePath, exeFileName));
        t.start();
        try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        t.interrupt();
	}
	
	/**
	 * General method to stop all threads and clear resources.
	 */
	public void stopAll(){
    	deleteAllObservers();
    }
	
	/*
	 *************************************************************************
	 * Management of Views using the observer pattern... addObserver, deleteObserver...
	 *************************************************************************
	 */
	
	/**
	 * Deletes all existing observers from simulationResultManager and this.
	 */
	private void deleteAllObservers(){
		simulationResultManager.deleteObservers();
	}
	
	/**
	 * Adds a new view, plot to the non interactive simulation center
	 * This plot will be notified on selected or unselected properties.
	 *
	 * @param newOberver New view/plot as an observer
	 */
	public void addPlot(Observer newOberver){
		if(newOberver != null){
			simulationResultManager.addObserver(newOberver);
		}
//		newOberver.update(simulationResultManager, "");	
	}
}
