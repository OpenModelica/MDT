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

// TODO: Auto-generated Javadoc
/**
 * Simulating a model should be done with different setting. This could help to find problems or to research a model.
 * A Simulation Setting is part of a Session Configuration and helps the user to run a simulation in different settings and to archive this settings
 * 
 * A Setting is not changeable, because otherwise the used data get lost. The user can overwrite an existing setting
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationSettingInteractive extends SimulationSetting{
	
	//** network
	/** The runtime control server ip. */
	private String runtimeControlServerIp = "";
	
	/** The runtime control server port. */
	private String runtimeControlServerPort = "";
	
	/** The simulation control server ip. */
	private String simulationControlServerIp = "";
	
	/** The simulation control server port. */
	private String simulationControlServerPort = "";
	
	/** The simulation transfer server ip. */
	private String simulationTransferServerIp = "";
	
	/** The simulation transfer server port. */
	private String simulationTransferServerPort = "";
	//** simulation
		
	/**
	 * Use this constructor as default for an interactive simulation, start = 0.0, stop = 1.0
	 *
	 * @param name {@link #name}
	 * @param type the type
	 * @param version {@link #version}
	 * @param author {@link #author}
	 * @param company A company which has right on this Session configuration
	 * @param comment Developer or User comments
	 * @param numberOfRuns Number of simulations using this Session Configuration, for a new setting please enter 0
	 * @param runtimeControlServerIp IP of the simulation runtime Control server
	 * @param runtimeControlServerPort Port of the simulation runtime Control server
	 * @param simulationControlServerIp IP of the simulation environment Control server
	 * @param simulationControlServerPort Port of the simulation environment Control server
	 * @param simulationTransferServerIp IP of the simulation environment Transfer server
	 * @param simulationTransferServerPort Port of the simulation environment Transfer server
	 * @param simStepTimeInSeconds Interval between single calculation steps
	 * @param tolerance Solver calculation tolerance
	 * @param solver Solver which should be used during a simulation (DASSL, Euler, RK4)
	 */
	public SimulationSettingInteractive(String name, String type, String version, String author, String company, String comment, int numberOfRuns, 
			String runtimeControlServerIp, String runtimeControlServerPort,
			String simulationControlServerIp, String simulationControlServerPort,
			String simulationTransferServerIp, String simulationTransferServerPort,
			String simStepTimeInSeconds, String tolerance, String solver
			) {
		super(name, type, version, author, company, comment, numberOfRuns, simStepTimeInSeconds, tolerance, solver, "0.0", "1.0");
		
		this.runtimeControlServerIp = runtimeControlServerIp;
		this.runtimeControlServerPort = runtimeControlServerPort;
		this.simulationControlServerIp = simulationControlServerIp;
		this.simulationControlServerPort = simulationControlServerPort;
		this.simulationTransferServerIp = simulationTransferServerIp;
		this.simulationTransferServerPort = simulationTransferServerPort;
	}


	/**
	 * Gets the runtime control server port.
	 *
	 * @return the runtime control server port
	 */
	public String getRuntimeControlServerPort() {
		return runtimeControlServerPort;
	}

	/**
	 * Gets the simulation control server port.
	 *
	 * @return the simulation control server port
	 */
	public String getSimulationControlServerPort() {
		return simulationControlServerPort;
	}

	/**
	 * Gets the simulation transfer server port.
	 *
	 * @return the simulation transfer server port
	 */
	public String getSimulationTransferServerPort() {
		return simulationTransferServerPort;
	}

	/**
	 * Gets the runtime control server ip.
	 *
	 * @return the runtime control server ip
	 */
	public String getRuntimeControlServerIp() {
		return runtimeControlServerIp;
	}

	/**
	 * Gets the simulation control server ip.
	 *
	 * @return the simulation control server ip
	 */
	public String getSimulationControlServerIp() {
		return simulationControlServerIp;
	}

	/**
	 * Gets the simulation transfer server ip.
	 *
	 * @return the simulation transfer server ip
	 */
	public String getSimulationTransferServerIp() {
		return simulationTransferServerIp;
	}

	/**
	 * The simulation step time is an interval of output results.
	 *
	 * @return simulation step time in seconds
	 */

	public String toString() { //TODO
		return "\nSimulationSetting:\nruntimeControlServerIp: " + runtimeControlServerIp
				+ " #simulationControlServerIp: " + simulationControlServerIp
				+ " #simulationTransferServerIp: " + simulationTransferServerIp
				+ "runtimeControlServerPort: " + runtimeControlServerPort
					+ " #simulationControlServerPort: " + simulationControlServerPort
					+ " #simulationTransferServerPort: " + simulationTransferServerPort
				+ " #simStepTime: " + getSimStepTime() + " #tolerance: " + getTolerance();
	}
}
