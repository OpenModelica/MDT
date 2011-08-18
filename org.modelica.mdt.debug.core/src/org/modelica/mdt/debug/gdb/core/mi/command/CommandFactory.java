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
		// instead via the MISession class
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

	public MIExecRun createMIExecRun(String[] args) {
		// TODO Auto-generated method stub
		return new MIExecRun(args);
	}
	
	public MIExecContinue createMIExecContinue() {
		// TODO Auto-generated method stub
		return new MIExecContinue();
	}
	
	/**
	 * @return
	 */
	public MIExecNext createMIExecNext() {
		// TODO Auto-generated method stub
		return new MIExecNext();
	}
	
	/**
	 * @return
	 */
	public MIExecStep createMIExecStep() {
		// TODO Auto-generated method stub
		return new MIExecStep();
	}
	
	/**
	 * @return
	 */
	public MIExecFinish createMIExecFinish() {
		// TODO Auto-generated method stub
		return new MIExecFinish();
	}
	
	public MIBreakInsert createMIBreakInsert(String line) {
		return new MIBreakInsert(line);
	}
	
	public MIBreakInsert createMIBreakInsert(boolean isTemporary, boolean isHardware,
			 String condition, int ignoreCount, String line, int tid) {
		return new MIBreakInsert(isTemporary, isHardware, condition, ignoreCount, line, tid);
	}
	
	/**
	 * @param breakPointNumbers
	 * @return
	 */
	public MIBreakDelete createMIBreakDelete(int[] breakPointNumbers) {
		// TODO Auto-generated method stub
		return new MIBreakDelete(breakPointNumbers);
	}
	
	public MIStackInfoFrame createMIStackInfoFrame() {
		return new MIStackInfoFrame();
	}
	
	public MIStackListFrames createMIStackListFrames() {
		return new MIStackListFrames();
	}

	public MIStackListFrames createMIStackListFrames(int lowFrame, int highFrame) {
		return new MIStackListFrames(lowFrame, highFrame);
	}
	
	/**
	 * @param showValues
	 * @return
	 */
	public MIStackListVariables createMIStackListVariables(String[] params) {
		// TODO Auto-generated method stub
		return new MIStackListVariables(params);
	}
	
	public MIDataEvaluateExpression createMIDataEvaluateExpression(String expression) {
		return new MIDataEvaluateExpression(expression);
	}

	public MIDataEvaluateExpression createMIGetTypeOfAny(String expression) {
		return new MIDataEvaluateExpression("(char*)getTypeOfAny(" + expression + ")");
	}
	
	public MIDataEvaluateExpression createMIGetListLength(String expression) {
		return new MIDataEvaluateExpression("(int)listLength(" + expression + ")");
	}
	
	public MIDataEvaluateExpression createMIGetListItem(String expression, int index) {
		return new MIDataEvaluateExpression("listGet(" + expression + ", " + index + ")");
	}
	
	/**
	 * @param variableName
	 * @return
	 */
	public MIDataEvaluateExpression createMIAnyString(String variableName) {
		// TODO Auto-generated method stub
		return new MIDataEvaluateExpression("(char*)anyString(" + variableName + ")");
	}

	/**
	 * @return MIStackInfoDepth
	 * 
	 */
	public MIStackInfoDepth createMIStackInfoDepth() {
		// TODO Auto-generated method stub
		return new MIStackInfoDepth();
	}

	/**
	 * @param fName
	 * @return
	 */
	public CLIPType createCLIPType(String variableName) {
		// TODO Auto-generated method stub
		return new CLIPType(variableName);
	}

	/**
	 * @param fName
	 * @return
	 */
	public CLIWhatis createCLIWhatis(String variableName) {
		// TODO Auto-generated method stub
		return new CLIWhatis(variableName);
	}

	/**
	 * @param strings
	 * @return
	 */
	public MIGDBSet createMIGDBSet(String[] strings) {
		// TODO Auto-generated method stub
		return new MIGDBSet(strings);
	}

	/**
	 * @param string
	 * @return
	 */
	public MIInterpreterExecConsole createMIInterpreterExecConsole(String cmd) {
		// TODO Auto-generated method stub
		return new MIInterpreterExecConsole(cmd);
	}

	/**
	 * @return
	 */
	public MIGDBShowPrompt createMIGDBShowPrompt() {
		// TODO Auto-generated method stub
		return new MIGDBShowPrompt();
	}
	
}
