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
package org.openmodelica.simulation.environment;

// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
public class Test {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws InterruptedException{
		
		System.out.println("dassl2asd".contains("dassl2"));
		
		//		String errorString = "{\"\",\"\"}";
//		System.out.println(errorString);
//		System.out.println(errorString.equals("{\"\",\"\"}"));
		
//		Image i = new Image(Display.getCurrent(), "src/org/openmodelica/simulation/environment/icons/add.gif");
//		System.out.println((new File("src/org/openmodelica/simulation/environment/icons/add.gif")).exists());
		//		Date dt = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMDD");
//		
//		System.out.println(df.format(dt));
		/**
		 * Network Object for the Simulation Control Server
		 */
//		SimulationControlServer controlServer;
		/**
		 * Network Object for the Simulation Transfer Server
		 */
//		SimulationTransferServer transferServer;
		
		/**
		 * The simulation runtime executable file, is an child process while simulation is running. The runnable is implemented in the class RunInteractiveSimulationRuntimeEnvironment
		 */
//		Thread simulationruntime;
//		RunInteractiveSimulationRuntimeEnvironment runnableForSimulationruntime;
//		runnableForSimulationruntime = new RunInteractiveSimulationRuntimeEnvironment("F:/Eclipse_Workspaces/runtime-EclipseApplication/org.openmodelica.simulation.projects/EuroFighter", "TwoTanks.TanksConnectedPI.exe", 10501+"");
//		simulationruntime = new Thread(runnableForSimulationruntime);
//		
//		controlServer = new SimulationControlServer(10500);
//		
//		simulationruntime.start();
//		controlServer.start();
//		transferServer = new SimulationTransferServer(new SimulationResultManager(), 10500);
//		transferServer.start();
			/*
			 * This Operation order is important to avoid problems while simulating
			 */
//			SimulationControlClient.send_setcontrolclienturl_Message("1", 10500 +"");
//			Thread.sleep(1000);
//			SimulationControlClient.send_Error("2");
//			SimulationControlClient.send_settransferclienturl_Message(Activator.getSimulationCenter().getSequenceNumber()+"", stsPort +"");
//			
//			Thread.sleep(1800);
//			Generate Filter and send it to the simulation controller
//			SimulationControlClient.send_setfilter_Message(Activator.getSimulationCenter().getSequenceNumber()+"", (ArrayList<ModelicaProperty>)plotProperties);
	}

		
	/**
	 * Formatter.
	 *
	 * @param timeInSec the time in sec
	 * @return the string
	 */
	static public String formatter(Double timeInSec){
		
		double y = 31556926;
		double m = 2629743.83;
		double d = 86400;
		double h = 3600;
		double min = 60;
		
		Double year = 0.0;
		Double month = 0.0;
		Double day = 0.0;
		Double hour = 0.0;
		Double minute = 0.0;
		Double seconds = 0.0;
		Integer milliseconds = 0;
		
		milliseconds = (int)(remainder(timeInSec, 1.0) * 1000);
		
		double timeInSec2 = timeInSec.intValue(); //Time without millis
		
		year = ((timeInSec2 / y));
		timeInSec2 = remainder(timeInSec2, y);
		
		month = ((timeInSec2 / m));
		timeInSec2 = remainder(timeInSec2, m);
		
		day = ((timeInSec2 / d));
		timeInSec2 = remainder(timeInSec2, d);
		
		hour = ((timeInSec2 / h));
		timeInSec2 = remainder(timeInSec2, h);
		
		minute = ((timeInSec2 / min));
		timeInSec2 = remainder(timeInSec2, min);
		
		seconds = (timeInSec2);
		
		String formattedTime = "";
		
		if(year.intValue() > 0)
			formattedTime = formattedTime + year.intValue() + " y ";
		if(month.intValue() > 0)
			formattedTime = formattedTime + month.intValue() + " m ";
		if(day.intValue() > 0)
			formattedTime = formattedTime + day.intValue() + " d ";
		if(hour.intValue() > 0)
			formattedTime = formattedTime + hour.intValue() + ":";
		else
			formattedTime = formattedTime + "00" + ":";
		if(minute.intValue() > 0)
			formattedTime = formattedTime + minute.intValue() + ":";
		else
			formattedTime = formattedTime + "00" + ":";
		if(seconds.intValue() > 0)
			formattedTime = formattedTime + seconds.intValue() + ".";
		else
			formattedTime = formattedTime + "00" + ".";
		if(milliseconds > 99)
			formattedTime = formattedTime + milliseconds;
		else if(milliseconds > 9)
			formattedTime = formattedTime + "0" + milliseconds;
		else if(milliseconds > 0)
			formattedTime = formattedTime + "00" + milliseconds;
		else
			formattedTime = formattedTime + "000";
			
		return formattedTime;
	}
	
	/**
	 * Remainder.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the double
	 */
	static private Double remainder( Double a, Double b ) 
	{ 
		return Math.signum(a) * 
		(Math.abs(a) - Math.abs(b) * Math.floor(Math.abs(a)/Math.abs(b))); 
	}
}
