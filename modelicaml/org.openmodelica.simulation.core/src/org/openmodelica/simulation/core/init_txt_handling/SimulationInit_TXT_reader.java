/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.core.init_txt_handling;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

import org.openmodelica.simulation.core.init_txt_handling.InitTXT;


//TODO: Auto-generated Javadoc
/**
 * The _init.txt file is used by OpenModelica to initialize a corresponding simulation runtime executable.
 * This file is generated by the OMC.
 * It can be used to run a simulation using different settings (start values, time interval, solver...)
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationInit_TXT_reader {

	/** The raf. */
	private static RandomAccessFile raf;
	
	/**
	 * Reads a init.txt file from a specified path and creates an InitTXT object which contains all initial values and simulation settings
	 * @param pathToInit Absoulut path to the "_init.txt" file
	 * @return An InitTXT object containing all initial values and simulation settings
	 */
	public static InitTXT readInit(String pathToInit) throws Exception{
		InitTXT initdate = new InitTXT();
		raf = new RandomAccessFile( pathToInit, "r");
		
		/*
		 * Check for the first elements to be start, stop, step, tolerance, method, outputFormat
		 */
		{
			{ //START VALUE
				String start = raf.readLine();
				Scanner sc = new Scanner(start);
				sc.useDelimiter("//");
				@SuppressWarnings("unused")
				Double startValue = new Double(sc.next().trim()); //throws an NumberFormatException
			}
			{ //STOP VALUE
				String start = raf.readLine();
				Scanner sc = new Scanner(start);
				sc.useDelimiter("//");
				@SuppressWarnings("unused")
				Double stopValue = new Double(sc.next().trim()); //throws an NumberFormatException
			}
			{ //STEP VALUE
				String step = raf.readLine();
				Scanner sc = new Scanner(step);
				sc.useDelimiter("//");
				@SuppressWarnings("unused")
				Double stepValue = new Double(sc.next().trim()); //throws an NumberFormatException
			}
			{ //TOLERANCE VALUE
				String tolerance = raf.readLine();
				Scanner sc = new Scanner(tolerance);
				sc.useDelimiter("//");
				@SuppressWarnings("unused")
				Double toleranceValue = new Double(sc.next().trim()); //throws an NumberFormatException
			}
			{
				String solver = raf.readLine();
				if(!solver.contains("euler") && !solver.contains("dassl") && !solver.contains("dassl2") && !solver.contains("rungekutta"))
					throw new Exception("Unknown Solver: " + solver);
			}
			{ //OUTPUT FORMAT
				String outputformat = raf.readLine();
				if(!outputformat.contains("outputFormat"))
					throw new Exception("Output format is not given: " + outputformat);
			}
			
			{ //VARIABLE FILTER
				String outputformat = raf.readLine();
				if(!outputformat.contains("variableFilter"))
					throw new Exception("Variable filter is not given: " + outputformat);
			}
		}
		
		File initFile = new File(pathToInit);
		
		initdate.fileName = initFile.getName();
		
		String states = raf.readLine();
		Scanner sc1 = new Scanner(states);
		sc1.useDelimiter("//");
		initdate.states = new Integer(sc1.next().trim());
		
		String alg_vars = raf.readLine();
		Scanner sc2 = new Scanner(alg_vars);
		sc2.useDelimiter("//");
		initdate.alg_vars = new Integer(sc2.next().trim());
		
		String parameters = raf.readLine();
		Scanner sc3 = new Scanner(parameters);
		sc3.useDelimiter("//");
		initdate.parameters = new Integer(sc3.next().trim());
		
		String n_int_parameters = raf.readLine();
		Scanner sc4 = new Scanner(n_int_parameters);
		sc4.useDelimiter("//");
		initdate.n_int_parameters = new Integer(sc4.next().trim());
		
		String n_int_variables = raf.readLine();
		Scanner sc5 = new Scanner(n_int_variables);
		sc5.useDelimiter("//");
		initdate.n_int_variables = new Integer(sc5.next().trim());
		
		String n_bool_parameters = raf.readLine();
		Scanner sc6 = new Scanner(n_bool_parameters);
		sc6.useDelimiter("//");
		initdate.n_bool_parameters = new Integer(sc6.next().trim());
		
		String n_bool_variables = raf.readLine();
		Scanner sc7 = new Scanner(n_bool_variables);
		sc7.useDelimiter("//");
		initdate.n_bool_variables = new Integer(sc7.next().trim());
		
		String string_parameters = raf.readLine();
		Scanner sc8 = new Scanner(string_parameters);
		sc8.useDelimiter("//");
		initdate.string_parameters = new Integer(sc8.next().trim());
		
		String string_variables = raf.readLine();
		Scanner sc9 = new Scanner(string_variables);
		sc9.useDelimiter("//");
		initdate.string_variables = new Integer(sc9.next().trim());
		
		for(int i =0; i< initdate.states; i++){
			String state = raf.readLine();
			Scanner sc = new Scanner(state);
			sc.useDelimiter("//");
			Double tempValue = new Double(sc.next().trim());
			String tempName = "";

			while (sc.hasNext()) {
				tempName = sc.next().trim();
			}
			initdate.stateProperties.put(tempName, tempValue);
			initdate.orderdStateProperties.add(tempName);
		}
		
		for(int i =0; i< initdate.states; i++){
			String derstate = raf.readLine();
			Scanner sc = new Scanner(derstate);
			sc.useDelimiter("//");
			Double tempValue = new Double(sc.next().trim());
			String tempName = "";

			while (sc.hasNext()) {
				tempName = sc.next().trim();
			}
			initdate.derStateProperties.put(tempName, tempValue);
			initdate.orderdDerStateProperties.add(tempName);
		}

			for (int i = 0; i < initdate.alg_vars; i++) {
				String alg = raf.readLine();
				Scanner sc = new Scanner(alg);
				sc.useDelimiter("//");
				Double tempValue = new Double(sc.next().trim());
				String tempName = "";

				while (sc.hasNext()) {
					tempName = sc.next().trim();
				}

				initdate.algProperties.put(tempName, tempValue);
				initdate.orderdAlgProperties.add(tempName);
			}
		
			for (int i = 0; i < initdate.parameters; i++) {
				String parameter = raf.readLine();
				Scanner sc = new Scanner(parameter);
				sc.useDelimiter("//");
				Double tempValue = new Double(sc.next().trim());
				String tempName = "";

				while (sc.hasNext()) {
					tempName = sc.next().trim();
				}
				initdate.parameterProperties.put(tempName, tempValue);
				initdate.orderdParameterProperties.add(tempName);
			}
			
			for (int i = 0; i < initdate.n_int_parameters; i++) {
				String n_int_parametersString = raf.readLine();
				Scanner sc = new Scanner(n_int_parametersString);
				sc.useDelimiter("//");
				Double tempValue = new Double(sc.next().trim());
				String tempName = "";

				while (sc.hasNext()) {
					tempName = sc.next().trim();
				}
				initdate.n_int_parametersProperties.put(tempName, tempValue);
				initdate.orderdN_int_parametersProperties.add(tempName);
			}
			
			for (int i = 0; i < initdate.n_int_variables; i++) {
				String n_int_variablesString = raf.readLine();
				Scanner sc = new Scanner(n_int_variablesString);
				sc.useDelimiter("//");
				Double tempValue = new Double(sc.next().trim());
				String tempName = "";

				while (sc.hasNext()) {
					tempName = sc.next().trim();
				}
				initdate.n_int_variablesProperties.put(tempName, tempValue);
				initdate.orderdN_int_variablesProperties.add(tempName);
			}
			
			for (int i = 0; i < initdate.n_bool_parameters; i++) {
				String n_bool_parametersString = raf.readLine();
				Scanner sc = new Scanner(n_bool_parametersString);
				sc.useDelimiter("//");
				//[20110308] New Version of OMI init file has true/false AND 1.0/0.0 as boolean values
				Double tempValue = 0.0;
				String tempBoolValue = sc.next().trim();
				if(tempBoolValue.equals("true") || tempBoolValue.equals("1.0")) tempValue = 1.0;
				String tempName = "";

				while (sc.hasNext()) {
					tempName = sc.next().trim();
				}
				initdate.n_bool_parametersProperties.put(tempName, tempValue);
				initdate.orderdN_bool_parametersProperties.add(tempName);
			}
			
			for (int i = 0; i < initdate.n_bool_variables; i++) {
				String n_bool_variablesString = raf.readLine();
				Scanner sc = new Scanner(n_bool_variablesString);
				sc.useDelimiter("//");
				//[20110308] New Version of OMI init file has true/false AND 1.0/0.0 as boolean values
				Double tempValue = 0.0;
				String tempBoolValue = sc.next().trim();
				if(tempBoolValue.equals("true") || tempBoolValue.equals("1.0")) tempValue = 1.0;
				
				String tempName = "";

				while (sc.hasNext()) {
					tempName = sc.next().trim();
				}
				initdate.n_bool_variablesProperties.put(tempName, tempValue);
				initdate.orderdN_bool_variablesProperties.add(tempName);
			}
			
			//TODO STRING will be ignored
			
		raf.close();
		
		return initdate;
		
//		return null;
	}
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		
		String pathToInit =  "D:/TwoTanksExample.Simulations.TanksSystemSimulation1_init.txt";
		try {
			System.out.println(readInit(pathToInit).start);
			System.out.println(readInit(pathToInit).stateProperties);
			System.out.println(readInit(pathToInit).algProperties);
			System.out.println(readInit(pathToInit).parameterProperties);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
}
