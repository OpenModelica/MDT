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
package org.openmodelica.simulation.environment.filehandling;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class RunInteractiveSimulationRuntimeEnvironment.
 */
public class RunInteractiveSimulationRuntimeEnvironment implements Runnable {
	
	/** The path to executable. */
	String pathToExecutable;
	
	/** The runtime name. */
	String runtimeName;
	
	/** The runtimecontrolserver port. */
	String runtimecontrolserverPort;
	
	/** The p. */
	Process p;

	/**
	 * A Runnable thread to execute the simulation runtime with needed
	 * parameters for an interactive simulation.
	 *
	 * @param pathToExecutable The folder path to the executable runtime NOTE: complete the
	 * string without a last slash
	 * @param runtimeName name of the runtime
	 * @param runtimecontrolserverPort port to be used from the simulation runtime control server
	 */
	public RunInteractiveSimulationRuntimeEnvironment(String pathToExecutable,
			String runtimeName, String runtimecontrolserverPort) {
		super();
		this.pathToExecutable = pathToExecutable;
		this.runtimeName = runtimeName;
		this.runtimecontrolserverPort = runtimecontrolserverPort;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
//		System.out.println(pathToExecutable + "\\" + runtimeName);
		String[] command = { pathToExecutable + "/" + runtimeName,
				"-interactive", "-port", runtimecontrolserverPort };
		try {
			ProcessBuilder builder = new ProcessBuilder(command);
			
			Map<String, String> env = builder.environment();
			String pathFromEnv = "";
			
			if(env.get("Path") != null){
				pathFromEnv = "Path";
			} else if(env.get("PATH") != null){
				pathFromEnv = "PATH";
			} else if(env.get("path") != null){
				pathFromEnv = "path";
			} else
				throw new Exception("path variable not available");
			
			env.put(pathFromEnv, env.get(pathFromEnv) + ";" + env.get("OPENMODELICAHOME") + "/bin/");
			
			builder.directory(new File(pathToExecutable + "/"));
			p = builder.start();
			Scanner s = new Scanner( p.getInputStream() ).useDelimiter( "\\n" );
			while(s.hasNext()){
				System.err.println( "RUNTIME > " + s.next() );
			}
//			p.waitFor();
		} catch (Exception e) {
//			System.err.println("IOException starting process!");
			e.getStackTrace();
		}
	}
	
	/**
	 * Stop process.
	 */
	public void stopProcess() {
		p.destroy();
	}
	
	/**
	 * Gets the exit value.
	 *
	 * @return the exit value
	 */
	public int getExitValue(){
		 return p.exitValue();
	}
}
