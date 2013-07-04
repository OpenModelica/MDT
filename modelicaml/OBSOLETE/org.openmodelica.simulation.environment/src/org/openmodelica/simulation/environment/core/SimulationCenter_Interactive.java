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

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.models.modelica.ModelicaParameter;
import org.openmodelica.simulation.core.models.modelica.ModelicaProperty;
import org.openmodelica.simulation.core.models.modelica.ModelicaVariable;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.core.models.simulation.SimulationSessionConfiguration;
import org.openmodelica.simulation.core.network.SimulationControlClient;
import org.openmodelica.simulation.core.network.SimulationControlServer;
import org.openmodelica.simulation.core.network.SimulationTransferServer;
import org.openmodelica.simulation.core.simulationdata.SimulationResultManager_Interactive;
import org.openmodelica.simulation.environment.Activator;
import org.openmodelica.simulation.environment.filehandling.RunInteractiveSimulationRuntimeEnvironment;

// TODO: Auto-generated Javadoc
/**
 * The Simulation Center is the central management unit to run an interactive simulation. 
 * It contains all information and data to simulate a system using
 * the OpenModelica Compiler (OMC). 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class SimulationCenter_Interactive {
	
	/**
	 * Represents the selected SimulationSessionConfiguration which will be used to
	 * setup the simulation environment and the runtime 
	 * with simulation data (solver, tolerance,...), network settings and model initial values
	 */
	private SimulationSessionConfiguration selectedSimulationSessionConfiguration;
	
	/** Status of the actual selected simulation (start = Simulation is running, pause = Simulation is paused at it's last state, stop = Simulation is interrupted and the first state is rebuild). */
	private String simulationStatus = "";
    
	/** Stores and organizes all simulation results. @see #SimulationResultManager */
    private SimulationResultManager_Interactive simulationResultManager_Interactive;
	
    /** Selected Simulation Project to be observed, simulated or tested If null: No Project has been selected. */
	private SimulationProject selectedSimulationProject;
	
	/** All parameters which should change interactively. */
	private List<ModelicaParameter> interactiveProperties;  //  @jve:decl-index=0:
	
	/** All properties which should display on plot. */
	private List<ModelicaProperty> plotProperties;  //  @jve:decl-index=0:
	
	/** List from the type Observer which contains all views, which will be registered as observers. */
	private List<Observer> observers;
	
	/** Network Object for the Simulation Control Server. */
	private SimulationControlServer controlServer;
	
	/** Network Object for the Simulation Transfer Server. */
	private SimulationTransferServer transferServer;
	
	/**
	 * The simulation runtime executable file, is an child process while simulation is running. The runnable is implemented in the class RunInteractiveSimulationRuntimeEnvironment
	 */
	private Thread simulationruntime;
	
	/** The runnable for simulationruntime. */
	private RunInteractiveSimulationRuntimeEnvironment runnableForSimulationruntime;
	
	/**
	 * A sequence number to identify an operation reply from the runtime and to check if an operation has been received correctly from the runtime
	 * The runtime replies with a done message containing the sequence number. e.g. done#seq#end
	 */
	private Integer sequenceNumber;
	
	/**
	 * Instantiates a new simulation center_ interactive.
	 */
	public SimulationCenter_Interactive(){
		simulationResultManager_Interactive = new SimulationResultManager_Interactive();
		interactiveProperties = new ArrayList<ModelicaParameter>();
		plotProperties = new ArrayList<ModelicaProperty>();
		observers = new ArrayList<Observer>();
	}
	
//	/**
//	 * Constructor to be used from the simulation project view, e.g. after choosing a simulationsessionconfiguration and the run simulation operation
//	 * @param selectedSimulationSessionConfiguration
//	 */
//	public SimulationCenter_Interactive(SimulationSessionConfiguration selectedSimulationSessionConfiguration) {
//		simulationResultManager = new SimulationResultManager();
//		interactiveProperties = new ArrayList<ModelicaParameter>();
//		plotProperties = new ArrayList<ModelicaProperty>();
//		
//		initAll(selectedSimulationSessionConfiguration);
//	}
	
	/**
 * Initializes the whole simulation environment variables.
 *
 * @param selectedSimulationSessionConfiguration the selected simulation session configuration
 */
	public void initAll(SimulationSessionConfiguration selectedSimulationSessionConfiguration) {
		this.selectedSimulationSessionConfiguration = selectedSimulationSessionConfiguration;
		sequenceNumber = 1;
		simulationStatus = "stop";
		
		/*
		 * Simulation Data Initialization
		 */
		selectedSimulationProject = Activator.getSimulationProjectCenter().getSelectedSimulationProject();
		
//		simulationResultManager = new SimulationResultManager();
//		interactiveProperties = new ArrayList<ModelicaParameter>();
//		plotProperties = new ArrayList<ModelicaProperty>();

		/*
		 * RUNTIME THREAD CONFIGURATION, NETWORK CONFIGURATION AND SETUP
		 */

//		int scsPort = 0;
//		int rcsPort = 0;
//		int stsPort = 0;
//		try {
//			rcsPort = Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getRuntimeControlServerPort().trim());
//			scsPort = Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getSimulationControlServerPort().trim());
//			stsPort = Integer.valueOf(((SimulationSettingInteractive)selectedSimulationSessionConfiguration.getSimSetting()).getSimulationTransferServerPort().trim());
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//---
		
		//Create thread which called the runtime executable file TODO [20100912] only for the interactive simulation
//		prepareExecutableRuntimeForInteractiveSimulation(rcsPort);
		
		//---
				
		//prepareNetworkCommunicationUnits(scsPort, rcsPort, stsPort);
		
		
		//---
		//ModelicaModel configuredMoProj = selectedSimulationSessionConfiguration.getModelicaModel();
		//createFilterProperties(configuredMoProj);
		//---
		//startAll();
	}
	
	/**
	 * Prepare network communication units.
	 *
	 * @param scsPort the scs port
	 * @param rcsPort the rcs port
	 * @param stsPort the sts port
	 */
	public void prepareNetworkCommunicationUnits(int scsPort, int rcsPort, int stsPort){
		//Simulation environment client for communication with runtime control server
		SimulationControlClient.setRuntime_ControlServerPort(rcsPort);
		//Simulation environment servers to communicate with runtime clients
		controlServer = new SimulationControlServer(scsPort);
		transferServer = new SimulationTransferServer(simulationResultManager_Interactive, stsPort);
	}
	
	/**
	 * Create and prepare thread which called the runtime executable file.
	 *
	 * @param rcsPort the rcs port
	 */
	public void prepareExecutableRuntimeForInteractiveSimulation(int rcsPort){
		/**
		 * Path to model.exe excluding the last slash and file name! Because the RunInteractiveSimulationRuntimeEnvironment needs both e.g. c:/path for c:/path/model.exe
		 */
		String exeFilePath = Activator.getSimulationProjectCenter().getSimulationProjectPath(selectedSimulationProject.getProjectName());
		String exeFileName = selectedSimulationProject.getFullQualifiedModelicaModelName() + ".exe";
		runnableForSimulationruntime = new RunInteractiveSimulationRuntimeEnvironment(exeFilePath, exeFileName, rcsPort+"");
		simulationruntime = new Thread(runnableForSimulationruntime);
	}
	
	/**
	 * Filter all properties to be displayed and store to plotProperties
	 * Filter all Parameter to be changed interactively at simulation time and store to interactiveProperties, Filter all Parameter to be displayed and store to plotProperties.
	 *
	 * @param configuredMoProj the configured mo proj
	 */
	public void createFilterProperties(ModelicaModel configuredMoProj){
		//Filter all properties to be displayed and store to plotProperties
		for (String fullQualifiedName : configuredMoProj.getMainclass()
				.getAllFullQualifiedVariableNames()) {
			ModelicaVariable mv = (ModelicaVariable)configuredMoProj.getMainclass()
					.getVariableByName(fullQualifiedName);
			if (mv.getDisplay())
				plotProperties.add(mv);
		}

		//Filter all Parameter to be changed interactively at simulation time and store to interactiveProperties, Filter all Parameter to be displayed and store to plotProperties
		for (String fullQualifiedName : configuredMoProj.getMainclass()
				.getAllFullQualifiedParameterNames()) {
			ModelicaParameter mp = (ModelicaParameter)configuredMoProj
					.getMainclass().getParameterByName(fullQualifiedName);
			if (mp.getDisplay())
				plotProperties.add(mp);
			if (mp.getInteractive()) {
				interactiveProperties.add(mp);
			}
		}
		
//		System.out.println("1. plotProperties: " + plotProperties);
	}
	
	/*
	 *************************************************************************
	 * Getter and Setter for the fields...
	 *************************************************************************
	 */

	/**
	 * Gets the simulation status.
	 *
	 * @return the simulation status
	 * @see #simulationStatus
	 */
	public String getSimulationStatus() {
		return simulationStatus;
	}
	
	/**
	 * Changes the actual simulation status and sends the status also to the simulation runtime.
	 *
	 * @param status Status of the simulation (start = Simulation is running, pause = Simulation is paused at it's last state, stop = Simulation is interrupted and the first state is rebuild)
	 * @see #simulationStatus
	 */
	public void setSimulationStatus(String status) {
		if(status.equals("start")){
			if(simulationStatus.equals("stop")){
				//TODO [20100912] AllePlots sollen zurück gesetzt werden um neu zu starten
			}
			simulationStatus = status;
			SimulationControlClient.send_start_Message(getSequenceNumber() + "");
		}
		else if(status.equals("pause")){
			simulationStatus = status;
			SimulationControlClient.send_pause_Message(getSequenceNumber() + "");
		}
		else if(status.equals("stop")){
			simulationStatus = status;
			SimulationControlClient.send_stop_Message(getSequenceNumber() + "");
		}
	}

	/**
	 * Gets the simulation result manager.
	 *
	 * @return a SimulationResultManager
	 * @see #simulationResultManager_Interactive
	 */
	public SimulationResultManager_Interactive getSimulationResultManager() {
		return simulationResultManager_Interactive;
	}

	/**
	 * Gets the interactive properties.
	 *
	 * @return the interactive properties
	 */
	public List<ModelicaParameter> getInteractiveProperties() {
		return interactiveProperties;
	}

	/**
	 * Gets the plot properties.
	 *
	 * @return the plot properties
	 */
	public List<ModelicaProperty> getPlotProperties() {
		return plotProperties;
	}

	/**
	 * Gets the sequence number.
	 *
	 * @return the actual sequence number to used for communication with runtime control server
	 * @see #sequenceNumber
	 */
	synchronized public Integer getSequenceNumber() {
		//TODO [20100912] ControlServer must wait for reply done#seq#end
		return sequenceNumber++;
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
	 * General method to start all server and client threads and to send the filter mask.
	 */
	public void startAll() {
		runSimulationRuntime();
		runServers();
	}

	/**
	 * Starts the Simulation Runtime executable file.
	 */
//	@SuppressWarnings("unused") //TODO [20110221] Implement starting runtime environment
	private void runSimulationRuntime(){
		simulationruntime.start();
	}
	
	/**
	 * Starts the Simulation Control and Transfer Server.
	 */
	private void runServers(){
		controlServer.start();
		transferServer.start();	
	}
	
	/**
	 * Sending SimulationControlServer Port to RuntimeControlClient
	 * Sending SimulationTransferServer Port to RuntimeTransferClient
	 * Sending FilterMask to Runtime.
	 *
	 * @param scsPort the scs port
	 * @param stsPort the sts port
	 */
	public void prepairRuntimeEnvironment(int scsPort, int stsPort) {
		try {
			/*
			 * This Operation order is important to avoid problems while
			 * simulating
			 */
			SimulationControlClient.send_setcontrolclienturl_Message(Activator
					.getSimulationCenter_Interactive().getSequenceNumber()
					+ "", scsPort + "");
			Thread.sleep(1500);

			SimulationControlClient.send_settransferclienturl_Message(Activator
					.getSimulationCenter_Interactive().getSequenceNumber()
					+ "", stsPort + "");

			Thread.sleep(1500);
			// Generate Filter and send it to the simulation controller
			SimulationControlClient.send_setfilter_Message(Activator
					.getSimulationCenter_Interactive().getSequenceNumber()
					+ "", (ArrayList<ModelicaProperty>) plotProperties);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * General method to stop all threads and clear resources.
	 */
	public void stopAll(){
		interruptServers();
		interruptSimulationRuntime();
//    	deleteAllObservers();
    	setSimulationStatus("");
    	
		interactiveProperties.clear();
		plotProperties.clear();
//		observers = new ArrayList<Observer>();
    }
	
	/**
	 * Sends a shutdown message to a running simulation runtime and destroys the simulation runtime process.
	 */
	private void interruptSimulationRuntime(){
		SimulationControlClient.clearAll(getSequenceNumber()+"");
		runnableForSimulationruntime.stopProcess();
		simulationruntime.interrupt();
	}	
	
	/**
	 * Interrupts the Simulation Control and Transfer Server.
	 */
	private void interruptServers(){
		try{
			if(transferServer != null)
				transferServer.interrupt();
			if(controlServer != null)
				controlServer.interrupt();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 *************************************************************************
	 * Management of Views using the observer pattern... addObserver, deleteObserver...
	 *************************************************************************
	 */
	
	/**
	 * Adds a new Observer to the simulationResultManager model observers.
	 *
	 * @param newObserver - an observer to be added
	 */
	private void addObserver(Observer newObserver) {
		observers.add(newObserver);
		simulationResultManager_Interactive.addObserver(newObserver);
	}
	
	/**
	 * Deletes a Observer from the simulationResultManager model observer.
	 *
	 * @param observer - an observer to be deleted
	 */
	@SuppressWarnings("unused")
	private void deleteObserver(Observer observer){
		simulationResultManager_Interactive.deleteObserver(observer);
	}
	
	/**
	 * Deletes all existing observers.
	 */
	@SuppressWarnings("unused")
	private void deleteAllObservers(){
		simulationResultManager_Interactive.deleteObservers();
		observers.clear();
	}
	
	/**
	 * Adds a new view, plot to the simulation environment.
	 *
	 * @param newOberver New view/plot as an observer
	 */
	public void addPlot(Observer newOberver){
		if(newOberver != null){
			addObserver(newOberver);
		}
	}
	
	/**
	 * Adds a time plot view to the simulation control view, this time plot displays the actual simulation time.
	 *
	 * @param newOberver New Time Plot object as an observer
	 */
	public void addSimulationTimePlot(Observer newOberver){
		if(newOberver != null){
			addObserver(newOberver);
		}
	}
}
