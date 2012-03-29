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

import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Result Mananger stores all calculated simulation data from a model using an interactive simulation. 
 * This is the main storing and organization unit used by Transfer to store data and all views and plots to get result values.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class OBSOLETE_SimulationResultManager_NonInteractive extends Observable{
	
	/** Contains a number of simulation results Key: fullqualifiedname <Key: Time, Value: value>. */
	private Map<String, Map<String, String>> simulationResultsAsString;
	
	/** All selected properties to plot on a view The corresponding data is stored in the SimulationResultManager_NonInteractive. */
	private Set<String> selectedProperties;
	
	private String simulationModelName;
	
	/**
	 * Instantiates a new simulation result manager_ non interactive.
	 */
	public OBSOLETE_SimulationResultManager_NonInteractive(){
		simulationResultsAsString = new TreeMap<String, Map<String, String>>();
		selectedProperties = new HashSet<String>();
	}
	
	/**
	 * A property value at a specified time can be requested by asking for its value with its full qualified name.
	 * @param time Step time which associates all results for this time step
	 * @param fullQualifiedName The unique identifier for a property
	 * @return A Number with the value corresponding to a property name
	 */
	public synchronized String getValueForFullQualifiedName(String time, String fullQualifiedName) {
		return simulationResultsAsString.get(fullQualifiedName).get(time);
	}
	
	/**
	 * This method will clear the map and set new results to the simulationResultsAsString Map.
	 *
	 * @param results the results
	 * @return 
	 */
	public synchronized void setResults(Map<String, Map<String, String>> results){
		selectedProperties.clear();
		simulationResultsAsString.clear();
		simulationResultsAsString.putAll(results);
		setChanged();
		notifyObservers("newsimulation");
	}
	
	/**
	 * Gets the results.
	 *
	 * @return a copy of the results map
	 */
	public synchronized Map<String, Map<String, String>> getResults(){
		
		Map<String, Map<String, String>> simulationResultsAsStringCopy = new TreeMap<String, Map<String, String>>();
		simulationResultsAsStringCopy.putAll(simulationResultsAsString);
		return simulationResultsAsStringCopy;
	}
	
	/**
	 * Gets the selected properties.
	 *
	 * @return a set of all selected properties as a copy
	 */
	public synchronized Set<String> getSelectedProperties(){
		Set<String> tempSelected = new HashSet<String>();
		tempSelected.addAll(selectedProperties);
		return tempSelected;
	}
	
	/**
	 * If a property has been selected, a view should display its results.
	 * This method notifies the views to display the selected property
	 *
	 * @param property the property
	 */
	public synchronized void propertySelected(String property){
		selectedProperties.add(property);
		setChanged();
		notifyObservers(property);
	}
	
	/**
	 * If a property has been unselected, a view should not display it an longer.
	 * This method notifies the views to remove the property from the plot
	 *
	 * @param property the property
	 */
	public synchronized void propertyUnselection(String property){
		selectedProperties.remove(property);
		setChanged();
		notifyObservers(property);
		
	}

	public String getSimulationModelName() {
		return simulationModelName;
	}

	public void setSimulationModelName(String simulationModelName) {
		this.simulationModelName = simulationModelName;
	}
}
