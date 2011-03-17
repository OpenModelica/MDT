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

// TODO: Auto-generated Javadoc
/**
 * Simulating a model should be done with different setting. This could help to find problems or to research a model.
 * A Simulation Setting is part of a Session Configuration and helps the user to run a simulation in different settings and to archive this settings
 * 
 * A Setting is not changeable, because otherwise the used data get lost. The user can overwrite an existing setting
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationSettingNonInteractive extends SimulationSetting{
	
	/**
	 * Use this constructor as default for an non interactive simulation.
	 *
	 * @param name {@link #name}
	 * @param type the type
	 * @param version {@link #version}
	 * @param author {@link #author}
	 * @param company A company which has right on this Session configuration
	 * @param comment Developer or User comments
	 * @param numberOfRuns Number of simulations using this Session Configuration, for a new setting please enter 0
	 * @param simStepTimeInSeconds Interval between single calculation steps
	 * @param tolerance Solver calculation tolerance
	 * @param solver Solver which should be used during a simulation (DASSL, Euler, RK4)
	 * @param start Simulation start time
	 * @param stop Simulation stop time
	 */
	public SimulationSettingNonInteractive(String name, String type, String version, String author, String company, String comment, int numberOfRuns, 
			String simStepTimeInSeconds, String tolerance, String solver, String start, String stop
			) {
		super(name, type, version, author, company, comment, numberOfRuns, simStepTimeInSeconds, tolerance, solver, start, stop);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { //TODO
		return "\nSimulationSettingNonInteractive:\n simStepTime: " + getSimStepTime() + " Start: " + getStart() + " Stop: " + getStop()+ " #tolerance: " + getTolerance();
	}
}
