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
abstract public class SimulationSetting {
	//** session
	/** Session Configuration name where this setting belongs to. */
	private String name = "";
	
	/** Session Configuration type "i" for interactive "ni" for non interactive. */
	private String type = "";
	
	/** Session configuration version. */
	private String version = "";
	
	/** Session configuration author. */
	private String author = "";
	
	/** A company which has right on this Session configuration. */
	private String company = "";
	
	/** Developer or User comments. */
	private String comment = "";
	
	/** Number of simulations using this Session Configuration. */
	private int numberOfRuns = 0;

	//** simulation	
	/** Simulation start time only necessary for non-interactive simulation. */
	private String start;
	
	/** Simulation stop time only necessary for non-interactive simulation. */
	private String stop;
	
	/** Interval between single calculation steps. */
	private String simStepTime = "";
	
	/** Solver calculation tolerance. */
	private String tolerance = "";
	
	/** Solver which should be used during a simulation (DASSL, Euler, RK4). */
	private String solver = "";
	
	/**
	 * Use this constructor as default, start = 0.0, stop = 1.0
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
	 * @param start the start
	 * @param stop the stop
	 */
	public SimulationSetting(String name, String type, String version, String author, String company, String comment, int numberOfRuns, 
			String simStepTimeInSeconds, String tolerance, String solver,
			String start, String stop
			) {
		super();
		this.name = name;
		this.type = type;
		this.version = version;
		this.author = author;
		this.company = company;
		this.comment = comment;
		this.numberOfRuns = numberOfRuns;
		this.simStepTime = simStepTimeInSeconds;
		this.tolerance = tolerance;
		this.solver = solver;
		this.start = start; this.stop = stop;
	}

	/**
	 * Gets the name.
	 *
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return {@link #type}
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the version.
	 *
	 * @return {@link #version}
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Gets the author.
	 *
	 * @return {@link #author}
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Gets the company.
	 *
	 * @return {@link #company}
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Gets the comment.
	 *
	 * @return {@link #numberOfRuns}
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Gets the number of runs.
	 *
	 * @return {@link #numberOfRuns}
	 */
	public int getNumberOfRuns() {
		return numberOfRuns;
	}

	/**
	 * Gets the solver.
	 *
	 * @return {@link #solver}
	 */
	public String getSolver() {
		return solver;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * Gets the stop.
	 *
	 * @return the stop
	 */
	public String getStop() {
		return stop;
	}

	/**
	 * The simulation step time is an interval of output results.
	 *
	 * @return simulation step time in seconds
	 */
	public String getSimStepTime() {
		return simStepTime;
	}

	/**
	 * Gets the tolerance.
	 *
	 * @return the tolerance
	 */
	public String getTolerance() {
		return tolerance;
	}
}
