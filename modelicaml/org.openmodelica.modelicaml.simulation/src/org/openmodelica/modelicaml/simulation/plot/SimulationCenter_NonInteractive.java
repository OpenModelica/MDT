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
package org.openmodelica.modelicaml.simulation.plot;

import java.util.Observer;

import org.openmodelica.simulation.core.simulationdata.SimulationResultManager_NonInteractive;
// TODO: Auto-generated Javadoc
/**
 * The Simulation Center is the central management unit to run a non interactive simulation. 
 * It contains all information and data to simulate a system using
 * the OpenModelica Compiler (OMC). 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class SimulationCenter_NonInteractive{
	
//    /** Path to the _res.xml file which is the result of a test simulation */
//	private String xmlResultFile;
	
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
	 * Instantiates a new simulation center_ non interactive.
	 */
//	public SimulationCenter_NonInteractive(String xmlResultFile){
//		this.xmlResultFile = xmlResultFile;
//		this.simulationResultManager = new SimulationResultManager_NonInteractive();
//	}
	
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
//	public void evaluatingResults() throws Exception{
//		
//		simulationResultManager.setResults(Result_TXT_reader.readResult(resultFilePath + "/" + resultFileName));
//		
//		//TODO [20101115] results XML will not be used to check performance
////		Result_TXT_reader.readResult(resultFilePath + "/" + resultFileName);
//		
//		SimulationResult_XML_generator.createXML("", "", resultFilePath + "/" + resultFileName, resultFilePath + "/SimulationSessionConfigurations/" + selectedSimulationSessionConfiguration.getConfigurationName());
//	}

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
