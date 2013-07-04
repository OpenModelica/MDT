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
package org.openmodelica.simulation.core.simulationdata;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Result Mananger stores all calculated simulation data from a model using an interactive simulation. 
 * This is the main storing and organization unit used by Transfer to store data and all views and plots to get result values.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class SimulationResultManager_Interactive extends Observable{
	
	/** Actual simulation time to synchronize results and views. */
	private Double actSimulationTime;
	
	/** Contains a number of simulation results. */
	private Map<Double, HashMap<String, Number>> simulationResults;
	
	/**
	 * Instantiates a new simulation result manager.
	 */
	public SimulationResultManager_Interactive(){
		actSimulationTime = 0.0;
		simulationResults = new HashMap<Double, HashMap<String, Number>>();
	}
	
	/**
	 * The Actual simulation time is to synchronize the views and with each other, with the result manager and the Transfer Threads.
	 *
	 * @return the actSimulationTime value as a double value
	 */
	public synchronized Double getActSimulationTime(){
		return actSimulationTime;
	}
	
	/**
	 * The Actual simulation time is to synchronize the views and with each other, with the result manager and the Transfer Threads.
	 *
	 * @param actSimulationTime the new act simulation time
	 */
	public synchronized void setActSimulationTime(Double actSimulationTime){
		this.actSimulationTime =  actSimulationTime;
	}
	
	/**
	 * A property value at a specified time can be requested by asking for its value with its full qualified name.
	 * @param time Step time which associates all results for this time step
	 * @param fullQualifiedName The unique identifier for a property
	 * @return A Number with the value corresponding to a property name
	 */
	public synchronized Number getValueForFullQualifiedName(Double time, String fullQualifiedName) {
		return (Number)simulationResults.get(time).get(fullQualifiedName);
	}
	
	/**
	 * Stores results for a specific time step and notifies all observers.
	 *
	 * @param time Step time which associates all results for this time step
	 * @param properties A Modelica Property for a specific time step
	 */
	public synchronized void setResultsForTime(Double time, Map<String, Number> properties) {
//		System.out.println(time + ":" + properties);
		simulationResults.put(time, new HashMap<String, Number>());
		setActSimulationTime(time);
		simulationResults.get(time).putAll(properties);
		if(time >= 200)simulationResults.remove(time-199); //TODO Only for the prototype...! BufferOverFlow!!
	    setChanged();
	    notifyObservers( time ); //is the arg1 of the method update
	}
	
	/**
	 * Start new simulation.
	 */
	public synchronized void startNewSimulation(){
		actSimulationTime = 0.0;
		simulationResults = new HashMap<Double, HashMap<String, Number>>();
		setChanged();
		notifyObservers( "newsimulation" ); //is the arg1 of the method update
	}
}
