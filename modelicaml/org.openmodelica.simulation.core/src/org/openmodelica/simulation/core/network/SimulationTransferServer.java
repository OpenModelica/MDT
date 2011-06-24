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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.openmodelica.simulation.core.simulationdata.SimulationResultManager_Interactive;

// TODO: Auto-generated Javadoc
/**
 * Simulation Transfer Server is a TCP/IP based communication unit which
 * receives simulation results from a simulation runtime using message parsing
 * As default the ip of a simulation runtime control server is localhost
 * (127.0.0.1) and the port is 10502. The user can change the port
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 * 
 */
public class SimulationTransferServer extends Thread {

	//set true to see output messages for debugging
	/** The debug. */
	private boolean debug = false;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The server. */
	ServerSocket server = null;
	
	/** The client. */
	Socket client = null;
	
	/** The app_transfer server port. */
	int app_transferServerPort;

	/** The in. */
	Scanner in;
	
	/** The line. */
	String line;
	
	/** The Transfer Server stores the received data to a result manager. */
	SimulationResultManager_Interactive manager;


	/**
	 * Create TransferServer using the default port number of 10502 for
	 * transferring results.
	 *
	 * @param manager the manager
	 */
	public SimulationTransferServer(SimulationResultManager_Interactive manager) { // Begin
																		// Constructor
		this.app_transferServerPort = 10502;
		this.manager = manager;
	} // End Constructor

	/**
	 * Create TransferServer with an user defined port number for transferring
	 * results.
	 *
	 * @param manager the manager
	 * @param server_port the server_port
	 */
	public SimulationTransferServer(SimulationResultManager_Interactive manager,
			int server_port) { // Begin Constructor

		this.app_transferServerPort = server_port;
		this.manager = manager;
	} // End Constructor

	
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
			try{
				line = in.next();
	//			if(debug)System.out.println("STS Received RTS Message: " + line);
					Map<String, Number> properties = new HashMap<String, Number>();
					Double time = 0.0;
					Scanner resultsScanner = new Scanner(line);
					resultsScanner.useDelimiter("#");
					if (resultsScanner.hasNext("result")) {
						resultsScanner.next(); // result
						if (resultsScanner.hasNext()) {
							time = new Double(resultsScanner.next());
							// if(debug)System.out.println("TIME: " + time);
							// The next token contains all variables
							if (resultsScanner.hasNext()) {
								properties.putAll(parseResultString(resultsScanner
										.next()));
							}
							// The next token contains all parameters
							if (resultsScanner.hasNext()) {
								properties.putAll(parseResultString(resultsScanner
										.next()));
							}
						}
					}
	//			if(debug)System.out.println("STS resived properties: " + properties);
					manager.setResultsForTime(time, properties);
			}catch (Exception e) { //Catch if the client has been closed or the simulation has been interrupted while transfer is waiting for results
//				finalize();
			}
		}
		finalize();
	}

	/**
	 * Parses the result string.
	 *
	 * @param propertiesString the properties string
	 * @return the map
	 */
	private Map<String, Number> parseResultString(String propertiesString) {
		HashMap<String, Number> properties = new HashMap<String, Number>();

		Scanner propertiesScanner = new Scanner(propertiesString);
		propertiesScanner.useDelimiter(":");
		while (propertiesScanner.hasNext()) {
			String propertyAndValue = propertiesScanner.next();
			Scanner propertyScanner = new Scanner(propertyAndValue);
			propertyScanner.useDelimiter("=");
			String fullQualifiedName = propertyScanner.next();
			String dvalue = propertyScanner.next();
			// if(fullQualifiedName.equals("tank1.h"))if(debug)System.out.println(fullQualifiedName
			// + ": " + dvalue);
			Double value = new Double(dvalue);
			// if(debug)System.out.println("name: " + fullQualifiedName + " value: " +
			// value);
			properties.put(fullQualifiedName, value);
		}
		return properties;
	}

	/**
	 * Creates the socket.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void createSocket() throws IOException {

		try {
			server = new ServerSocket(app_transferServerPort);
			if(debug)System.out.println("STS Could listen on port: "	+ app_transferServerPort);
		} catch (IOException e) {
			if(debug)System.out.println("STS Could not listen on port: "
					+ app_transferServerPort);
			System.exit(-1);
		}

		try {
			client = server.accept();
			if(debug)System.out.println("STS Accept Client: " + client.getPort());
		} catch (IOException e) {
			if(debug)System.out.println("STS Accept failed: " + app_transferServerPort);
			System.exit(-1);
		}

		try {
			in = new Scanner(client.getInputStream());
			in.useDelimiter("#end");
		} catch (IOException e) {
			if(debug)System.out.println("STS Accept failed: " + app_transferServerPort);
			System.exit(-1);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		if(debug)System.out.println("Clean up STS");
		try {
			in.close();
			server.close();
			if(debug)System.out.println("STS Could close.");
		} catch (IOException e) {
			if(debug)System.out.println("STS Could not close.");
			System.exit(-1);
		}
	}
}
