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
package org.openmodelica.simulation.core.network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.openmodelica.simulation.core.models.modelica.ModelicaParameter;
import org.openmodelica.simulation.core.models.modelica.ModelicaProperty;
import org.openmodelica.simulation.core.models.modelica.ModelicaVariable;

// TODO: Auto-generated Javadoc
/**
 * Simulation Control Client is a TCP/IP based communication unit to control a simulation runtime using message parsing
 * As default the ip of a simulation runtime control server is localhost (127.0.0.1) and the port is 10501. The user can change the port
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationControlClient {

	/** The debug. */
	private static boolean debug = false;
	
	/** The runtime_control server port. */
	private static int runtime_controlServerPort = 10501;
	
	/** The client socket. */
	private static Socket clientSocket = null;
	
	/** The out to server. */
	private static PrintStream outToServer = null;
	
	/**
	 * Changes the default port if a simulation runtime control server is also using a non default port.
	 *
	 * @param newPort New simulation runtime control server port
	 */
	public static void setRuntime_ControlServerPort(int newPort){
		runtime_controlServerPort = newPort;
	}

	/**
	 * Sends a message to the simulation runtime control server.
	 *
	 * @param message the message
	 * @return true if the message was send correctly to the simulation runtime control server. A correctly send is only based on the TCP/IP feedback and not on a done#end message of the control server
	 */
	private static boolean sendMessage(String message) {
		if(debug)System.out.println("Client send: " + message);
		try{
			checkConnection();
		} catch (UnknownHostException e) {
		if(debug)System.out.println("Unknown host: localhost.eng");
		System.exit(1);
		} catch (IOException e) {
			if(debug)System.out.println("No I/O");
			System.exit(1);
		}
		
		try {
			outToServer.print( message );
			return true;
		} catch (Exception e) {
			if(debug)System.out.println("TCP Client stopped!");
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Changes the value of the appended parameters and stets the simulation time back to the point where the user clicked in the UI.
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @param time the time
	 * @param fullQualifiedName the full qualified name
	 * @param value the value
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_changevalue_Message(String seq, String time, String fullQualifiedName, String value){
		String changevalue = "changevalue#"  + seq + "#" + time + "#" + fullQualifiedName + "=" + value + "#end"; 
		return sendMessage(changevalue);
	}
	
	/**
	 * Changes the simulation time and goes back to a specific time step.
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @param time the time
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_changetime_Message(String seq, String time){
		String changetime = "changetime#"  + seq + "#" + time + "#end"; 
		return sendMessage(changetime);
	}
	
	/**
	 * Changes the IP and port of the Control Server. Otherwise the default configuration will be used.
	 * <b>NOTE 1:</b> in this version the ip will be localhost (127.0.0.1)
	 * <b>Note 2</b>: Please maintain the following operation order to avoid problems while simulating: 1. setcontrolclienturl, 2. settransferclienturl, 3. setfilter
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @param ccPort the cc port
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_setcontrolclienturl_Message(String seq, String ccPort){
		String setcontrolclienturl = "setcontrolclienturl#" + seq + "#127.0.0.1#" + ccPort + "#end";
		return sendMessage(setcontrolclienturl);
		
	}
	
	/**
	 * Changes the IP and port of the Control Server. Otherwise the default configuration will be used.
	 * <b>NOTE 1:</b> in this version the ip will be localhost (127.0.0.1)
	 * <b>Note 2</b>: Please maintain the following operation order to avoid problems while simulating: 1. setcontrolclienturl, 2. settransferclienturl, 3. setfilter
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @param tcPort the tc port
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_settransferclienturl_Message(String seq, String tcPort){
		String settransferclienturl = "settransferclienturl#" + seq + "#127.0.0.1#" + tcPort + "#end";
		return sendMessage(settransferclienturl);
	}
	
	/**
	 * Sets the filter for variables and parameters which should send from OMI to the client UI
	 * <b>Note</b>: Please maintain the following operation order to avoid problems while simulating: 1. setcontrolclienturl, 2. settransferclienturl, 3. setfilter
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @param plotProperties the plot properties
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_setfilter_Message(String seq, ArrayList<ModelicaProperty> plotProperties){
//		if(debug)System.out.println(plotProperties);
		String variables = "";
		String parameters = "";
				
		for(ModelicaProperty mp : plotProperties){
			if(mp instanceof ModelicaVariable){
				if(!variables.equals(""))
					variables += ":";
				variables += mp.getName();
			} else if(mp instanceof ModelicaParameter){
				if(!parameters.equals(""))
					parameters += ":";
				parameters += mp.getName();
			}
		}
		
		if(variables != null && !variables.equals("") && variables.charAt(variables.length()-1) == ':'){
			variables.substring(0, variables.length());
		}
		
		if(parameters!=null && !parameters.equals("") && parameters.charAt(parameters.length()-1) == ':'){
			parameters.substring(0, parameters.length());
		}
		
		String setfilter = "setfilter#" + seq + "#" + variables + "#" + parameters + "#end";
//		if(debug)System.out.println("setfilter: " + setfilter);
		return sendMessage(setfilter);
	}
	
	/**
	 * TODO.
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_useindex_Message(String seq){
		return false;
	}
	
	/**
	 * Starts or continues the simulation.
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_start_Message(String seq){
		return sendMessage("start#" + seq + "#end");
	}
	
	/**
	 * Pauses the running simulation.
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_pause_Message(String seq){
		return sendMessage("pause#" + seq + "#end");
	}
	
	/**
	 * Stops the running simulation and resets all values to the beginning.
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_stop_Message(String seq){
		return sendMessage("stop#" + seq + "#end");
	}

	/**
	 * Shuts the simulation down.
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_shutdown_Message(String seq){
		return sendMessage("shutdown#" + seq + "#end");
	}
	
	/**
	 * Sends a fail message without the suffix "end".
	 *
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 * @return true if message was send correctly (This is only the TCP communication reply not the runtime reply! A correct operation reply from the runtime is done#seq#end)
	 */
	public static boolean send_Error(String seq){
		return sendMessage("error#" + seq + "#");
	}
	
	/**
	 * Checks if a connection to a simulation runtime control server is established otherwise it will connect to a runtime.
	 *
	 * @throws UnknownHostException the unknown host exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void checkConnection() throws UnknownHostException, IOException{
		
		if(clientSocket == null || outToServer == null || clientSocket.isClosed()){
				clientSocket = new Socket("127.0.0.1", runtime_controlServerPort);
				clientSocket.setKeepAlive(true);
				outToServer = new PrintStream(clientSocket.getOutputStream(), true);
				if(debug)System.out.println("SCC connected on: "+ runtime_controlServerPort);
		}
	}
	
	/**
	 * Sends a shutdown message to the runtime and closes all open sockets and servers.
	 * @param seq A sequence number is helpful to manage operation request and reply, a UI has to send a sequence number combined with an operation.
	 */
	public static void clearAll(String seq){
		try {
			send_shutdown_Message(seq);
			clientSocket.close();
			outToServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}