/*
 * 
 */
package org.openmodelica.simulation.environment.filehandling;

import java.io.File;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class RunEXE.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class RunEXE {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		
		Thread t = new Thread(new Runnable() {
			 
            @Override
            public void run() 
            {
//            	try 
//            	{ 
//            	  Desktop.getDesktop().open( new File("F:/SimulationApplicationExample_TwoTanks/callRuntime.bat") ); 
//            	} 
//            	catch ( Exception /* IOException, URISyntaxException */ e ) 
//            	{ 
//            	}
            	
               String[] command = {"F:/Eclipse_Workspaces/runtime-EclipseApplication/org.openmodelica.simulation.projects/EuroFighter/TwoTanks.TanksConnectedPI.exe"};
               try 
               {
            	   ProcessBuilder builder = new ProcessBuilder(command); 
            	   builder.directory( new File("F:/Eclipse_Workspaces/runtime-EclipseApplication/org.openmodelica.simulation.projects/EuroFighter/") ); 
            	   Process p = builder.start();
            	   p.waitFor();
                   System.out.println("Process exit code is:" + p.exitValue());
            	   
               }
               catch(IOException e) 
               {
                   System.err.println("IOException starting process!");
               }
               catch(InterruptedException e) 
               {
                   System.err.println("Interrupted waiting for process!");
               }
        }});
        t.start();
        t.interrupt();
	}
}
