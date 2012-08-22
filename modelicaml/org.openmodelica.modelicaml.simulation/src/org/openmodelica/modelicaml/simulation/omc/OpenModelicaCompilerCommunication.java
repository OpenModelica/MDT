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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation.omc;

import java.util.ArrayList;
import java.util.Collections;

import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.omc.OMCProxy;
import org.openmodelica.modelicaml.simulation.Activator;

public class OpenModelicaCompilerCommunication {

	/** The omc. */
	private OMCProxy omc;
	
	/** The history. */
	private ArrayList<String> history;
	
	
	/**
	 * Instantiates a new open modelica compiler communication.
	 */
	public OpenModelicaCompilerCommunication(){
		super();
		
		this.omc = new OMCProxy();
		
		// set the Activator proxy as reference in order to enable the shut down of OMC when Eclipse is closed 
		Activator.setOmcProxy(omc);
		
		history = new ArrayList<String>();
	}
	
	/*
	 * EXAMPLES:
	 * 
	 * history.add(0, "getVersion()"); history.add(0, "loadModel(Modelica)");
	 * history.add(0, "getClassNames()"); history.add(0,
	 * "getClassNames(...class name...)"); history.add(0,
	 * "loadFile(\"...file...\")"); history.add(0, "list()"); history.add(0,
	 * "list(...class name...)"); history.add(0, "simulate(...class name...)");
	 * history.add(0, "plot(...variable name...)"); history.add(0,
	 * "plot({...var1, var2,...})"); history.add(0,
	 * "instantiateModel(...class name...");
	 * 
	 * executeCommand("\n" + "model BouncingBall\n"+
	 * "  parameter Real e=0.7 \"coefficient of restitution\";\n" +
	 * "  parameter Real g=9.81 \"gravity acceleration\";\n" +
	 * "  Real h(start=1) \"height of ball\";\n" +
	 * "  Real v \"velocity of ball\";\n" +
	 * "  Boolean flying(start=true)  \"true, if ball is flying\";\n" +
	 * "  Boolean impact;\n" + "  Real v_new;\n" + "  Integer foo;\n" +
	 * "equation\n" + "  impact = h <= 0.0;\n" +
	 * "  foo = if impact then 1 else 2;\n" +
	 * "  der(v) = if flying then -g else 0;\n" + "  der(h) = v;\n" +
	 * "  when {h <= 0.0 and v <= 0.0,impact} then\n" +
	 * "    v_new = if edge(impact) then -e*pre(v) else 0;\n" +
	 * "    flying = v_new > 0;\n" + "    reinit(v, v_new);\n" + "  end when;\n"
	 * + "end BouncingBall;\n"); executeCommand("getErrorString()");
	 * executeCommand("instantiateModel(BouncingBall)");
	 * executeCommand("getErrorString()");
	 * executeCommand("instantiateModel(BouncingBall)");
	 * executeCommand("getErrorString()");
	 * executeCommand("simulate(BouncingBall,stopTime=3.5)");
	 * executeCommand("getErrorString()");
	 * executeCommand("plot({h, impact, v})");
	 * executeCommand("getErrorString()");
	 * executeCommand("readFile(\"sim_dcmotor.mos\")");
	 * executeCommand("getErrorString()");
	 * executeCommand("runScript(\"sim_dcmotor.mos\")");
	 * executeCommand("getErrorString()");
	 */
	
	/**
	 * Execute command.
	 *
	 * @param command A command to OMC e.g. loadModel(Modelica), simulate(...), ...
	 * @return the string
	 */
	private String executeCommand(String command) {
//		System.err.println("Expression:" + command);

		/**
		 * Reply from OMC
		 */
		ICompilerResult reply;
		String replyString = "Error: No reply from OMC ...";

		if (command != null && command.length() > 0) {
			history.add(command);
			try {
				reply = omc.sendExpression(command, true);
				replyString = reply.getFirstResult();
				
			} catch (Exception ex) {
				replyString = "\nError while sending expression: " + command + "\n"
						+ ex.getMessage();
			}
		} else {
			replyString = ("\nNo expression sent because it is empty");
		}

		return replyString;
	}
	
	/**
	 * Change directory to the directory given as a string.
	 * 
	 * @param dir
	 *            a directory given as a string. NOTE: USE slash "/" instead of "\\"
	 * @return Reply from OMC
	 */
	public String cd(String dir) {
		return executeCommand("cd(\"" + dir + "\")");
	}
	
	/**
	 * Request actual OMC working directory.
	 * 
	 * @return Reply from OMC, path to omc working directory
	 */
	public String cd() {
		return executeCommand("cd()");
	}
	
	
	public String getTempDirectoryPath() {
		String path = executeCommand("getTempDirectoryPath()").replaceAll("\\\\\\\\\\\\\\\\",  "/").trim();		// replace \\\\ by / and trim
		// remove the first and the last double quote 
		String pathCorrected = path.substring(1, path.length() - 1);
		
		if (pathCorrected.endsWith("/")) {
			return pathCorrected + "OpenModelica";	
		}
		return pathCorrected + "/" + "OpenModelica";
	}
	
	/**
	 * Clear everything.
	 * @return Reply from OMC
	 */
	public String clear(){
		return executeCommand("clear()");
	}
	
	/**
	 * Instantiates a model/class and returns a string containing the flat class definition.
	 * Ex: instantiateModel(dcmotor)
	 *
	 * @param modelname the modelname
	 * @return Reply from OMC
	 */
	public String instantiateModel(String modelname){
		return executeCommand("instantiateModel("+ modelname + ")");
	}
	
	
	/**
	 * Clear the variables.
	 * @return Reply from OMC
	 */
	public String clearVariables(){
		return executeCommand("clearVariables()");
	}
	
	/**
	 * Return a string containing all class definitions.
	 * 
	 * @return Reply from OMC
	 */
	public String list() {
		return executeCommand("list()");
	}

	/**
	 * Return a string containing the class definition of the named class. Ex:
	 * list(dcmotor)
	 *
	 * @param modelname the modelname
	 * @return Reply from OMC
	 */
	public String list(String modelname) {
		return executeCommand("list(" + modelname + ")");
	}

	/**
	 * 
	 Return a vector of the currently defined variable names.
	 * 
	 * @return Reply from OMC
	 */
	public String listVariables() {
		return executeCommand(listVariables());
	}

	/**
	 * Load modelica file given as string argument. Ex:
	 * loadFile("../myLibrary/myModels.mo")
	 *
	 * @param strFile the str file
	 * @return Reply from OMC
	 */
	public String loadFile(String strFile) {
		return executeCommand("loadFile(\"" + strFile + "\")");
		// TODO: this is a workaround. remove the encoding after Acceleo will encode the files with UTF-8
//		return executeCommand("loadFile(\"" + strFile + "\", encoding=\""+Constants.fileEncoding+"\")"); 
	}

	/**
	 * Load model, function, or package relative to $OPENMODELICALIBRARY.
	 * Ex: loadModel(Modelica.Electrical) Note: if e.g. loadModel(Modelica) fails,
	 * you may have OPENMODELICALIBRARY pointing at the wrong location.
	 *
	 * @param name the name
	 * @return Reply from OMC
	 */
	public String loadModel(String name) {
		return executeCommand("loadModel(" + name + ")");
	}
	
	/**
	 * Define a model from "model Name" ... to ... "end Name"
	 * @param modelCode Full Modelica model code
	 * @return Reply from OMC
	 */
	public String defineModel(String modelCode) {
		return executeCommand(modelCode);
	}
	
	/**
	 * Translates a model but does not simulate it automatically.
	 *
	 * @param MainClass name of the main class
	 * @param outputFormat for the results. For now just the "plt" format will be supported.
	 * @return Reply from OMC
	 */
	public String buildModel(String MainClass, String outputFormat){
		return executeCommand("buildModel("+MainClass+ ", outputFormat=\""+ outputFormat +"\")");
	}
	
	/**
	 * Translates a model but does not simulate it automatically.
	 *
	 * @param MainClass name of the main class
	 * @param outputFormat for the results. For now just the "plt" format will be supported.
	 * @return Reply from OMC
	 */
	public String buildModel(String MainClass, String startTime, String stopTime, String numberOfIntervals, String tolerance, String method, String outputFormat){
		return executeCommand("buildModel("+MainClass+ ", startTime=" +startTime+ ", stopTime=" +stopTime+ ", numberOfIntervals="+numberOfIntervals+ ", tolerance="+tolerance+ ", method=\""+method + "\", outputFormat=\"" + outputFormat +"\")");
	}
	
	/**
	 * Translates a model and simulates it automatically.
	 *
	 * @param MainClass name of the main class
	 * @param outputFormat for the results.
	 * @return Reply from OMC
	 * example for variables filter: simulate(... , variableFilter="x|y|z")
	 */
	public String simulate(	String MainClass, 
							String startTime, 
							String stopTime, 
							String numberOfIntervals, 
							String tolerance, 
							String method, 
							String outputFormat,
							String variableFilter){
		
		
		String _mainClass = "";
		String _startTime = ""; 
		String _stopTime = ""; 
		String _numberOfIntervals = "";  
		String _tolerance = ""; 
		String _method = ""; 
		String _outputFormat = ""; 
		String _variableFilter  = ""; 

		if (MainClass != null && !MainClass.trim().equals("")) {
			_mainClass = MainClass.trim() + ",";
		}
		if (startTime != null && !startTime.trim().equals("")) {
			_startTime = "startTime=" + startTime.trim() + ",";
		}
		if (stopTime != null && !stopTime.trim().equals("")) {
			_stopTime = "stopTime=" + stopTime.trim() + ",";
		}
		if (numberOfIntervals != null && !numberOfIntervals.trim().equals("")) {
			_numberOfIntervals = "numberOfIntervals=" + numberOfIntervals.trim() + ",";
		}
		if (_tolerance != null && !_tolerance.trim().equals("")) {
			_tolerance = "tolerance=" + tolerance.trim() + ",";
		}
		if (method != null && !method.trim().equals("")) {
			_method = "method=\"" + method.trim() + "\",";
		}
		if (outputFormat != null && !outputFormat.trim().equals("")) {
			_outputFormat = "outputFormat=\"" + outputFormat.trim() +"\",";
		}
		if (variableFilter != null && !variableFilter.trim().equals("")) {
			_variableFilter = "variableFilter=\""+variableFilter.trim() + "\",";
		}
		
		String params = _mainClass + _startTime + _stopTime + _numberOfIntervals + _tolerance + _method + _outputFormat + _variableFilter;
		if (params.trim().endsWith(",")) {
			params = params.trim().substring(0, params.length() - 1);
		}
		
		return executeCommand("simulate(" + params + ")");
		
//		return executeCommand("simulate("+MainClass+ "," +
//				" startTime=" +startTime+ "," +
//				" stopTime=" +stopTime+ "," +
//				" numberOfIntervals="+numberOfIntervals+ "," +
//				" tolerance="+tolerance+ ", " +
//				"method=\""+method + "\", " +
//				"outputFormat=\"" + outputFormat +"\"," +
//				_variableFilter +
//				")");
	}
	
	/**
	 * Check a model.
	 *
	 * @param MainClass name of the main class
	 * @return Reply from OMC
	 */
	public String checkModel(String MainClass){
		return executeCommand("checkModel("+MainClass+")");
	}
	
	
	/**
	 * Leave OpenModelica and kill the process.
	 * 
	 * @return Reply from OMC
	 */
	public String quit() {
		return executeCommand("quit()");
	}
	
	/**
	 * Get a possible error String from omc after executing a command.
	 *
	 * @return error string as reply from omc
	 */
	public String getErrorString(){
		return executeCommand("getErrorString()");
	}
	
	/**
	 * Gets the command history.
	 *
	 * @return the command history
	 */
	public ArrayList<String> getCommandHistory() {
		ArrayList<String> tempHistory = new ArrayList<String>();
		Collections.copy(tempHistory, history);

		return tempHistory;
	}

//	/**
//	 * The main method.
//	 *
//	 * @param args the arguments
//	 */
//	public static void main(String[] args){
//		OpenModelicaCompilerCommunication i = new OpenModelicaCompilerCommunication();
//		i.cd("D:/OpenModelica1.5.0/tmp/");
//		i.loadModel("TwoTanks");
//		i.buildModel("TwoTanks.TanksConnectedPI", "plt");
//		i.quit();
//	}
	
}
