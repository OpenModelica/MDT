/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;

/**
 * Starts the GDB with the given parameters.
 * Creates the MISession object.
 * 
 * @author Adeel Asghar
 * 
 */
public class GDBDebugger {

	// GDB Process
	private Process fGDBProcess;
	// MI Session
	private MISession fMISession;
	
	/**
	 * @param debugTargetProgram
	 * @param debugTargetCommandLineArguments
	 * @param debugTargetWorkDirectory
	 * @param debugTargetGDBPath
	 * @param environmentVars 
	 * @throws MIException 
	 * @throws IOException 
	 */
	public GDBDebugger(String debugTargetProgram,
			String debugTargetCommandLineArguments,
			String debugTargetWorkDirectory, String debugTargetGDBPath, String[] environmentVars) throws CoreException, MIException, IOException {
		// TODO Auto-generated constructor stub
		// start the GDB Process
		startGDBProcess(debugTargetProgram, debugTargetCommandLineArguments, debugTargetWorkDirectory,
				debugTargetGDBPath, environmentVars);
		// create the MI Session for communication with GDB
		createMISession();
	}

	/**
	 * Creates a new MISession object.
	 * @throws MIException 
	 * @throws IOException 
	 * 
	 */
	private void createMISession() throws MIException, IOException {
		// TODO Auto-generated method stub
		fMISession = new MISession(fGDBProcess);
		if (MDTDebugCorePlugin.DEBUG) fMISession.writeLog("Created MI Session");
	}

	/**
	 * Start the GDB process.
	 * @param debugTargetProgram
	 * @param debugTargetCommandLineArguments
	 * @param debugTargetWorkDirectory
	 * @param debugTargetGDBPath
	 * @param environmentVars 
	 * @throws CoreException 
	 */
	private void startGDBProcess(String debugTargetProgram, String debugTargetCommandLineArguments,
			String debugTargetWorkDirectory, String debugTargetGDBPath, String[] environmentVars) throws CoreException {
		// TODO Auto-generated method stub
		String gdb = getGDBPath(debugTargetGDBPath);
		if (gdb == null || gdb.length() == 0) {
			gdb =  "gdb";
		}
		
		ArrayList<String> argList = new ArrayList<String>();
		argList.add(gdb);
		argList.add("-q");		// don't print welcome messages
		argList.add("-nw");		// don't use window interface
		argList.add("-i");		// tells gdb to run in MI protocol mode
		argList.add("mi");		// tells gdb to run in MI protocol mode
		
		if (debugTargetProgram != null) {
			argList.add("--args");
			argList.add(new Path(debugTargetProgram).toOSString());
			String[] extraArgs = DebugPlugin.parseArguments(debugTargetCommandLineArguments);
			for (String x : extraArgs) argList.add(x);
		}
		String[] args = (String[])argList.toArray(new String[argList.size()]);
		fGDBProcess = DebugPlugin.exec(args, new File(debugTargetWorkDirectory), environmentVars);
	}

	/**
	 * Get the GDB path.
	 * If user has used environment variables while giving the path.
	 * It resolves the variables and return the full path.
	 * @param debugTargetGDBPath
	 * @return String GDB Path
	 * @throws CoreException 
	 */
	private String getGDBPath(String debugTargetGDBPath) throws CoreException {
		// TODO Auto-generated method stub
		String command = null;
		command = VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(debugTargetGDBPath, false);
		return new Path(command).toOSString();
	}

	/**
	 * Returns the MISession object
	 * @return fMISession
	 */
	public MISession getMISession() {
		// TODO Auto-generated method stub
		return fMISession;
	}

}
