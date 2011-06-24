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
package org.openmodelica.simulation.core.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * Simulation Control Server is a TCP/IP based communication unit to receive
 * operation messages from a simulation runtime As default the ip of a
 * simulation runtime control server is localhost (127.0.0.1) and the port is
 * 10500. The user can change the port
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 * 
 */
public class SimulationControlServer extends Thread {

	//set true to see output messages for debugging
	/** The debug. */
	private boolean debug = false;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The server. */
	ServerSocket server = null;
	
	/** The client. */
	Socket client = null;
	
	/** The app_control server port. */
	int app_controlServerPort;

	/** The in. */
	Scanner in;
	
	/** The line. */
	String line;

	// private StyledText statusConsole;

	/**
	 * Create ControlServer using the default port number of 10500 for
	 * transferring operations.
	 *
	 */
	public SimulationControlServer() { // Begin Constructor
		this.app_controlServerPort = 10500;
	} // End Constructor

	/**
	 * Create ControlServer using a user defined port number for transferring
	 * operations.
	 *
	 * @param server_port the server_port
	 */
	public SimulationControlServer(int server_port) { // Begin Constructor
		this.app_controlServerPort = server_port;
	}// End Constructor

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		try {
			createSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!this.isInterrupted()) {
//			if(debug)System.out.println("SCS waints for message...on: " + app_controlServerPort);
			
			try{
				line = in.next();
				if(debug)System.out.println("SCS Received RCS Message: " + line);
				// statusConsole.append(line + "\n");
				// TODO das Hauptprogramm möchte die Done Message abwarten dafür
				// muss ein flag gesetzt werden!!
			}catch (Exception e) {
				finalize();
			}
		}

		finalize();
	}

	/**
	 * Creates the socket.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void createSocket() throws IOException {

		try {
			server = new ServerSocket(app_controlServerPort);
			if(debug)System.out.println("SCS Could listen on port: " + app_controlServerPort);
		} catch (IOException e) {
			if(debug)System.out.println("SCS Could not listen on port: "
					+ app_controlServerPort);
			System.exit(-1);
		}

		try {
			client = server.accept();
			if(debug)System.out.println("SCS Accept Client: " + client.getPort());
		} catch (IOException e) {
			if(debug)System.out.println("SCS Accept failed: " + app_controlServerPort);
			System.exit(-1);
		}

		try {
			in = new Scanner(client.getInputStream());
			in.useDelimiter("#end");
		} catch (IOException e) {
			if(debug)System.out.println("SCS Accept failed: " + app_controlServerPort);
			System.exit(-1);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		if(debug)System.out.println("Clean up SCS");
		try {
			in.close();
			server.close();
			if(debug)System.out.println("SCS Could close.");
		} catch (IOException e) {
			if(debug)System.out.println("SCS Could not close.");
			System.exit(-1);
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		SimulationControlServer cs1 = new SimulationControlServer(10500);
		cs1.start();
		SimulationControlClient.setRuntime_ControlServerPort(10501);
		SimulationControlClient.send_setcontrolclienturl_Message("1", "10500");
	}
}
