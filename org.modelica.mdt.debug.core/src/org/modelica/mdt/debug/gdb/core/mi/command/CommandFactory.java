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
package org.modelica.mdt.debug.gdb.core.mi.command;

/**
 * @author Adeel Asghar
 *
 */
/**
 * Factory to create GDB commands.
 */
public class CommandFactory {

	/**
	 * @return
	 */
	public MIGDBExit createMIGDBExit() {
		// TODO Auto-generated method stub
		return new MIGDBExit();
	}

	/**
	 * @return
	 */
	public MIGDBShowExitCode createMIGDBShowExitCode() {
		// TODO Auto-generated method stub
		return new MIGDBShowExitCode();
	}

	/**
	 * @return
	 */
	public CLIExecAbort createCLIExecAbort() {
		// TODO Auto-generated method stub
		return new CLIExecAbort();
	}

	/**
	 * @return
	 */
	public MIExecInterrupt createMIExecInterrupt() {
		// TODO Auto-generated method stub
		// return null here to signal that we do not support
		// -exec-interrupt and to use to drop a signal to gdb
		// instead via the MIProcess class
		return null;
	}

	/**
	 * @return
	 */
	public CLIInfoProc createCLIInfoProc() {
		// TODO Auto-generated method stub
		return new CLIInfoProc();
	}

	/**
	 * @return
	 */
	public CLIInfoProgram createCLIInfoProgram() {
		// TODO Auto-generated method stub
		return new CLIInfoProgram();
	}

	/**
	 * @param strings
	 * @return
	 */
	public MIExecRun createMIExecRun(String[] args) {
		// TODO Auto-generated method stub
		return new MIExecRun(args);
	}
	
	public MIBreakInsert createMIBreakInsert(String line) {
		return new MIBreakInsert(line);
	}
	
	public MIBreakInsert createMIBreakInsert(boolean isTemporary, boolean isHardware,
			 String condition, int ignoreCount, String line, int tid) {
		return new MIBreakInsert(isTemporary, isHardware, condition, ignoreCount, line, tid);
	}
}
