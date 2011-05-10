/*
 * 
 */
package org.openmodelica.simulation.environment.filehandling;

import java.io.File;
import java.io.IOException;
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
			builder.directory(new File(pathToExecutable + "/"));
			p = builder.start();
			Scanner s = new Scanner( p.getInputStream() ).useDelimiter( "\\n" );
			while(s.hasNext()){
				System.err.println( "RUNTIME > " + s.next() );
			}
//			p.waitFor();
		} catch (IOException e) {
			System.err.println("IOException starting process!");
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
