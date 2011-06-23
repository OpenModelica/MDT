package org.openmodelica.simulation.environment.filehandling;

import java.io.File;
import java.util.Map;

/**
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class RunNonInteractiveSimulationRuntimeEnvironment implements Runnable{

	String pathToExecutable;
	String runtimeName;
	
	public RunNonInteractiveSimulationRuntimeEnvironment(){
		super();
	}
	
	/**
	 * A Runnable thread to execute the simulation runtime
	 * 
	 * @param pathToExecutable
	 *            The folder path to the executable runtime NOTE: complete the
	 *            string without a last slash
	 * @param runtimeName
	 *            name of the runtime

	 */
	public RunNonInteractiveSimulationRuntimeEnvironment(String pathToExecutable,
			String runtimeName){
		super();
		this.pathToExecutable = pathToExecutable;
		this.runtimeName = runtimeName;
	}
	
	public void run() {

        String[] command = {pathToExecutable + "/" + runtimeName};
        try 
        {
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
     	   Process p = builder.start();
     	   p.waitFor();
        }
        catch (Exception e) {
//			System.err.println("IOException starting process!");
			e.getStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread t = new Thread(new RunNonInteractiveSimulationRuntimeEnvironment("F:/Eclipse_Workspaces/runtime-EclipseApplication/org.openmodelica.simulation.projects/EuroFighter/", "TwoTanks.TanksConnectedPI.exe"));
        t.start();
        t.interrupt();
	}
}
