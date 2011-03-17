/*
 * 
 */
package org.openmodelica.simulation.environment.filehandling;

import java.io.File;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class RunNonInteractiveSimulationRuntimeEnvironment.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class RunNonInteractiveSimulationRuntimeEnvironment implements Runnable{

	/** The path to executable. */
	String pathToExecutable;
	
	/** The runtime name. */
	String runtimeName;
	
	/**
	 * Instantiates a new run non interactive simulation runtime environment.
	 */
	public RunNonInteractiveSimulationRuntimeEnvironment(){
		super();
	}
	
	/**
	 * A Runnable thread to execute the simulation runtime.
	 *
	 * @param pathToExecutable The folder path to the executable runtime NOTE: complete the
	 * string without a last slash
	 * @param runtimeName name of the runtime
	 */
	public RunNonInteractiveSimulationRuntimeEnvironment(String pathToExecutable,
			String runtimeName){
		super();
		this.pathToExecutable = pathToExecutable;
		this.runtimeName = runtimeName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

        String[] command = {pathToExecutable + "/" + runtimeName};
        try 
        {
     	   ProcessBuilder builder = new ProcessBuilder(command); 
     	   builder.directory(new File(pathToExecutable + "/")); 
     	   Process p = builder.start();
     	   p.waitFor();
        }
        catch(IOException e) 
        {
            System.err.println("IOException starting process!");
        }
        catch(InterruptedException e) 
        {
            System.err.println("Interrupted waiting for process!");
        }
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Thread t = new Thread(new RunNonInteractiveSimulationRuntimeEnvironment("F:/Eclipse_Workspaces/runtime-EclipseApplication/org.openmodelica.simulation.projects/EuroFighter/", "TwoTanks.TanksConnectedPI.exe"));
        t.start();
        t.interrupt();
	}
}
