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
package org.modelica.mdt.debug.gdb.helper;

import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MIDataEvaluateExpression;
import org.modelica.mdt.debug.gdb.core.mi.output.MIDataEvaluateExpressionInfo;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;

/**
 * @author Adeel Asghar
 *
 */
public class ValueHelper {
	
	/**
	 * Creates and sends the "-data-evaluate-expression (char*)anyString(void*)" command.
	 * @param variableName
	 * @param referenceType 
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static String getAnyString(String variableName,
			String referenceType, GDBDebugTarget gdbDebugTarget) throws MIException {
		// TODO Auto-generated method stub
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getTypeOfAnyCmd = factory.createMIAnyString(variableName);
		miSession.postCommand(getTypeOfAnyCmd);
		MIDataEvaluateExpressionInfo getTypeOfAnyInfo = getTypeOfAnyCmd.getMIDataEvaluateExpressionInfo();
		if (referenceType.equals(GDBHelper.STRING)) {
			return parseResultWithQuotes(getTypeOfAnyInfo.getExpression());
		} else {
			return parseResult(getTypeOfAnyInfo.getExpression());
			
		}
		
	}

	/**
	 * Returns the result string with quotes
	 * the response received from -data-evaluate-expression is like this
	 * ^done,value="0x1b488af \"value\""
	 * we need to parse it so that only data type is returned
	 * @param expression
	 * @return
	 */
	private static String parseResultWithQuotes(String expression) {
		// TODO Auto-generated method stub
		int beginIndex = expression.indexOf("\"");
		int endIndex = expression.lastIndexOf("\"");
		return expression.substring(beginIndex, endIndex + 1);	// make sure we return ""(quotes) for strings
	}

	/**
	 * Returns the result string without quotes
	 * the response received from -data-evaluate-expression is like this
	 * ^done,value="0x1b488af \"value\""
	 * we need to parse it so that only data type is returned
	 * @param expression
	 * @return
	 */
	private static String parseResult(String expression) {
		// TODO Auto-generated method stub
		int beginIndex = expression.indexOf("\"");
		int endIndex = expression.lastIndexOf("\"");
		return expression.substring(beginIndex + 1, endIndex);
	}

	/**
	 * Creates and sends the "-data-evaluate-expression expression" command.
	 * @param variableName 
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static String evaluateExpression(String variableName, GDBDebugTarget gdbDebugTarget) throws MIException {
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression dataEvaluateExpressionCmd = factory.createMIDataEvaluateExpression(variableName);
		miSession.postCommand(dataEvaluateExpressionCmd);
		MIDataEvaluateExpressionInfo dataEvaluateExpressionInfo = dataEvaluateExpressionCmd.getMIDataEvaluateExpressionInfo();
		return dataEvaluateExpressionInfo.getExpression();
	}
	
	/**
	 * Creates and sends the "-data-evaluate-expression (int)listLength(void*)" command.
	 * @param variableName 
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static int getListLength(String variableName,
			GDBDebugTarget gdbDebugTarget) throws MIException {
		// TODO Auto-generated method stub
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getListLengthCmd = factory.createMIGetListLength(variableName);
		miSession.postCommand(getListLengthCmd);
		MIDataEvaluateExpressionInfo getListLengthInfo = getListLengthCmd.getMIDataEvaluateExpressionInfo();
		return Integer.parseInt(getListLengthInfo.getExpression());
	}
	
	/**
	 * Creates and sends the "-data-evaluate-expression listGet(void*,item)" command.
	 * @param variableName
	 * @param item
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static String getListItem(String variableName, int item,
			GDBDebugTarget gdbDebugTarget) throws MIException {
		// TODO Auto-generated method stub
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getListItemCmd = factory.createMIGetListItem(variableName, item);
		miSession.postCommand(getListItemCmd);
		return getListItemCmd.getMIDataEvaluateExpressionInfo().getExpression();
	}
	
	/**
	 * Creates and sends the "-data-evaluate-expression (int)arrayLength(void*)" command.
	 * @param variableName 
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static int getRecordElements(String variableName,
			GDBDebugTarget gdbDebugTarget) throws MIException {
		// TODO Auto-generated method stub
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getRecordElementsCmd = factory.createMIGetRecordElements(variableName);
		miSession.postCommand(getRecordElementsCmd);
		MIDataEvaluateExpressionInfo getRecordElementsInfo = getRecordElementsCmd.getMIDataEvaluateExpressionInfo();
		return Integer.parseInt(getRecordElementsInfo.getExpression());
	}	

	/**
	 * Creates and sends the "-data-evaluate-expression arrayGet(void*,element)" command.
	 * @param variableName
	 * @param element
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static String getRecordElement(String variableName, int element,
			GDBDebugTarget gdbDebugTarget) throws MIException {
		// TODO Auto-generated method stub
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getRecordElementCmd = factory.createMIGetRecordElement(variableName, element);
		miSession.postCommand(getRecordElementCmd);
		return getRecordElementCmd.getMIDataEvaluateExpressionInfo().getExpression();
	}

	/**
	 * Creates and sends the "-data-evaluate-expression (char*)getRecordElementName(void*,element)" command.
	 * @param variableName
	 * @param element
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static String getRecordElementName(String variableName, int element,
			GDBDebugTarget gdbDebugTarget) throws MIException {
		// TODO Auto-generated method stub
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getRecordElementNameCmd = factory.createMIGetRecordElementName(variableName, element);
		miSession.postCommand(getRecordElementNameCmd);
		return parseResult(getRecordElementNameCmd.getMIDataEvaluateExpressionInfo().getExpression());
	}	
	
}
