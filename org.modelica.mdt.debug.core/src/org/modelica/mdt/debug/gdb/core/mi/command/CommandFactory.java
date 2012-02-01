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
	 * Creates the -gdb-exit command
	 * @return MIGDBExit
	 */
	public MIGDBExit createMIGDBExit() {
		// TODO Auto-generated method stub
		return new MIGDBExit();
	}

	/**
	 * Creates the -data-evaluate-expression $_exitcode command
	 * @return MIGDBShowExitCode
	 */
	public MIGDBShowExitCode createMIGDBShowExitCode() {
		// TODO Auto-generated method stub
		return new MIGDBShowExitCode();
	}

	/**
	 * Creates the kill command
	 * @return CLIExecAbort
	 */
	public CLIExecAbort createCLIExecAbort() {
		// TODO Auto-generated method stub
		return new CLIExecAbort();
	}

	/**
	 * @return MIExecInterrupt
	 */
	public MIExecInterrupt createMIExecInterrupt() {
		// TODO Auto-generated method stub
		// return null here to signal that we do not support
		// -exec-interrupt and to use to drop a signal to gdb
		// instead via the MISession class
		return null;
	}

	/**
	 * Creates the info proc command
	 * @return CLIInfoProc
	 */
	public CLIInfoProc createCLIInfoProc() {
		// TODO Auto-generated method stub
		return new CLIInfoProc();
	}

	/**
	 * Creates the info program command
	 * @return CLIInfoProgram
	 */
	public CLIInfoProgram createCLIInfoProgram() {
		// TODO Auto-generated method stub
		return new CLIInfoProgram();
	}
	
	/* Program Execution Commands */
	
	/**
	 * Creates the -exec-run command
	 * @param args
	 * @return MIExecRun
	 */
	public MIExecRun createMIExecRun(String[] args) {
		// TODO Auto-generated method stub
		return new MIExecRun(args);
	}
	
	/**
	 * Creates the -exec-continue command
	 * @return MIExecContinue
	 */
	public MIExecContinue createMIExecContinue() {
		// TODO Auto-generated method stub
		return new MIExecContinue();
	}
	
	/**
	 * Creates the -exec-next command
	 * @return MIExecNext
	 */
	public MIExecNext createMIExecNext() {
		// TODO Auto-generated method stub
		return new MIExecNext();
	}
	
	/**
	 * Creates the -exec-step command
	 * @return MIExecStep
	 */
	public MIExecStep createMIExecStep() {
		// TODO Auto-generated method stub
		return new MIExecStep();
	}
	
	/**
	 * Creates the -exec-finish command
	 * @return MIExecFinish
	 */
	public MIExecFinish createMIExecFinish() {
		// TODO Auto-generated method stub
		return new MIExecFinish();
	}
	
	/* Breakpoint Commands */
	
	/**
	 * Creates the -break-insert command
	 * @param line
	 * @return MIBreakInsert
	 */
	public MIBreakInsert createMIBreakInsert(String line) {
		return new MIBreakInsert(line);
	}
	
	/**
	 * Creates the -break-insert command with params
	 * @param isTemporary
	 * @param isHardware
	 * @param condition
	 * @param ignoreCount
	 * @param line
	 * @param tid
	 * @return MIBreakInsert
	 */
	public MIBreakInsert createMIBreakInsert(boolean isTemporary, boolean isHardware,
			 String condition, int ignoreCount, String line, int tid) {
		return new MIBreakInsert(isTemporary, isHardware, condition, ignoreCount, line, tid);
	}
	
	/**
	 * Creates the -break-delete command
	 * @param breakPointNumbers
	 * @return MIBreakDelete
	 */
	public MIBreakDelete createMIBreakDelete(int[] breakPointNumbers) {
		// TODO Auto-generated method stub
		return new MIBreakDelete(breakPointNumbers);
	}
	
	/**
	 * Creates the -break-enable command
	 * @param breakPointNumbers
	 * @return MIBreakEnable
	 */
	public MIBreakEnable createMIBreakEnable(int[] breakPointNumbers) {
		// TODO Auto-generated method stub
		return new MIBreakEnable(breakPointNumbers);
	}
	
	/**
	 * Creates the -break-disable command
	 * @param breakPointNumbers
	 * @return MIBreakDisable
	 */
	public MIBreakDisable createMIBreakDisable(int[] breakPointNumbers) {
		// TODO Auto-generated method stub
		return new MIBreakDisable(breakPointNumbers);
	}
	
	/* Stack Manipulation Commands */
	
	/**
	 * Creates the -stack-info-frame command
	 * @return MIStackInfoFrame
	 */
	public MIStackInfoFrame createMIStackInfoFrame() {
		return new MIStackInfoFrame();
	}
	
	/**
	 * Creates the -stack-list-frame command
	 * @return MIStackListFrames
	 */
	public MIStackListFrames createMIStackListFrames() {
		return new MIStackListFrames();
	}

	/**
	 * Creates the -stack-list-frames command with params
	 * @param lowFrame
	 * @param highFrame
	 * @return MIStackListFrames
	 */
	public MIStackListFrames createMIStackListFrames(int lowFrame, int highFrame) {
		return new MIStackListFrames(lowFrame, highFrame);
	}
	
	/**
	 * Creates the -stack-list-variables command
	 * @param params
	 * @return MIStackListVariables
	 */
	public MIStackListVariables createMIStackListVariables(String[] params) {
		// TODO Auto-generated method stub
		return new MIStackListVariables(params);
	}
	
	/**
	 * Creates the -stack-info-depth [ MAX-DEPTH ] command
	 * @return MIStackInfoDepth
	 * 
	 */
	public MIStackInfoDepth createMIStackInfoDepth() {
		// TODO Auto-generated method stub
		return new MIStackInfoDepth();
	}
	
	/**
	 * Creates the -stack-select-frame FRAMENUM command
	 * @return MIStackSelectFrame
	 * 
	 */
	public MIStackSelectFrame createMIStackSelectFrame(int frameNum) {
		return new MIStackSelectFrame(frameNum);
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIDataEvaluateExpression(String expression) {
		return new MIDataEvaluateExpression(expression);
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function changeStdoutBuffer
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIChangeStdStreamBuffer() {
		return new MIDataEvaluateExpression("changeStdStreamBuffer()");
	}

	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function anyString
	 * @param variableName
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIAnyString(String variableName) {
		// TODO Auto-generated method stub
		return new MIDataEvaluateExpression("(char*)anyString(" + variableName + ")");
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function getTypeOfAny
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIGetTypeOfAny(String expression) {
		return new MIDataEvaluateExpression("(char*)getTypeOfAny(" + expression + ")");
	}
	
	/* List Manipulation Commands */
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function listLength
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIGetListLength(String expression) {
		return new MIDataEvaluateExpression("(int)listLength(" + expression + ")");
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function listGet
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIGetListItem(String expression, int index) {
		return new MIDataEvaluateExpression("((long (*) (long)) listGet)(" + expression + ", " + index + ")");
	}
	
	/* Record Manipulation Commands */
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function arrayLength
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIGetArrayLength(String expression) {
		return new MIDataEvaluateExpression("(int)arrayLength(" + expression + ")");
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function arrayGet
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIGetArrayElement(String expression, int index) {
		return new MIDataEvaluateExpression("((long (*) (long)) arrayGet)(" + expression + ", " + index + ")");
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function getRecordElementName
	 * @param expression
	 * @return MIDataEvaluateExpression
	 */
	public MIDataEvaluateExpression createMIGetRecordElementName(String expression, int index) {
		return new MIDataEvaluateExpression("(char*)getRecordElementName(" + expression + ", " + index + ")");
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to call the executable function isOptionValue
	 * @param variableName
	 * @return
	 */
	public MIDataEvaluateExpression createMIIsOptionNone(String expression) {
		// TODO Auto-generated method stub
		return new MIDataEvaluateExpression("(int)isOptionNone(" + expression + ")");
	}

	/**
	 * Creates the ptype command
	 * @param variableName
	 * @return CLIPType
	 */
	public CLIPType createCLIPType(String variableName) {
		// TODO Auto-generated method stub
		return new CLIPType(variableName);
	}

	/**
	 * Creates the whatis command
	 * @param variableName
	 * @return CLIWhatis
	 */
	public CLIWhatis createCLIWhatis(String variableName) {
		// TODO Auto-generated method stub
		return new CLIWhatis(variableName);
	}

	/**
	 * Creates the -gdb-set command with params
	 * @param strings
	 * @return MIGDBSet
	 */
	public MIGDBSet createMIGDBSet(String[] strings) {
		// TODO Auto-generated method stub
		return new MIGDBSet(strings);
	}

	/**
	 * Creates the -interpreter-exec command 
	 * @param cmd
	 * @return MIInterpreterExecConsole
	 */
	public MIInterpreterExecConsole createMIInterpreterExecConsole(String cmd) {
		// TODO Auto-generated method stub
		return new MIInterpreterExecConsole(cmd);
	}

	/** 
	 * Creates the -gdb-show prompt command
	 * @return MIGDBShowPrompt
	 */
	public MIGDBShowPrompt createMIGDBShowPrompt() {
		// TODO Auto-generated method stub
		return new MIGDBShowPrompt();
	}	
	
}
