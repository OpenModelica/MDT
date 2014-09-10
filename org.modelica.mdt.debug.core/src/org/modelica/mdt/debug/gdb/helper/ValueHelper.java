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

import java.util.ArrayList;

import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MIDataEvaluateExpression;
import org.modelica.mdt.debug.gdb.core.mi.output.MIDataEvaluateExpressionInfo;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;

/**
 * @author Adeel Asghar
 *
 */
public class ValueHelper {
	
	/**
	 * Creates and sends the "-data-evaluate-expression (char*)anyString(void*)" command.
	 * @param variableName
	 * @param referenceType 
	 * @param gdbStackFrame
	 * @return 
	 */
	public static String getAnyString(String variableName,
			String referenceType, GDBStackFrame gdbStackFrame) {
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression getAnyStringCmd = factory.createMIAnyString(variableName);
			getAnyStringCmd.setQuiet(true);
			miSession.postCommand(getAnyStringCmd, gdbStackFrame);
			MIDataEvaluateExpressionInfo getTypeOfAnyInfo = getAnyStringCmd.getMIDataEvaluateExpressionInfo();
			if (referenceType.equals(GDBHelper.STRING)) {
				return parseResultWithQuotes(getTypeOfAnyInfo.getExpression());
			} else {
				return parseResult(getTypeOfAnyInfo.getExpression());
			}
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
			return "$$error$$";
		} catch (Exception e) {
			// TODO: handle exception
			return "$$error$$";
		}
		//return "";
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
	 * @param gdbStackFrame
	 * @return 
	 */
	public static String evaluateExpression(String variableName, GDBStackFrame gdbStackFrame) {
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression dataEvaluateExpressionCmd = factory.createMIDataEvaluateExpression(variableName);
			dataEvaluateExpressionCmd.setQuiet(true);
			miSession.postCommand(dataEvaluateExpressionCmd, gdbStackFrame);
			MIDataEvaluateExpressionInfo dataEvaluateExpressionInfo = dataEvaluateExpressionCmd.getMIDataEvaluateExpressionInfo();
			return dataEvaluateExpressionInfo.getExpression();
		} catch (MIException e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	/**
	 * Creates and sends the "-data-evaluate-expression (int)listLength(void*)" command.
	 * @param variableName 
	 * @param gdbStackFrame
	 * @return 
	 */
	public static int getListLength(String variableName,
			GDBStackFrame gdbStackFrame) {
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression getListLengthCmd = factory.createMIGetListLength(variableName);
			getListLengthCmd.setQuiet(true);
			miSession.postCommand(getListLengthCmd, gdbStackFrame);
			MIDataEvaluateExpressionInfo getListLengthInfo = getListLengthCmd.getMIDataEvaluateExpressionInfo();
			return Integer.parseInt(getListLengthInfo.getExpression());
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	/**
	 * Creates and sends the "-data-evaluate-expression listGet(void*,item)" command.
	 * @param variableName
	 * @param item
	 * @param gdbStackFrame
	 * @return
	 * @throws MIException 
	 */
	public static String getListItem(String variableName, int item,
			GDBStackFrame gdbStackFrame) {
		String result = "";
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression getListItemCmd = factory.createMIGetListItem(variableName, item);
			getListItemCmd.setQuiet(true);
			miSession.postCommand(getListItemCmd, gdbStackFrame);
			result = getListItemCmd.getMIDataEvaluateExpressionInfo().getExpression();
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
			result = "GDB ERROR:" + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			result = "GDB ERROR:" + e.getMessage();
		}
		return result;
	}
	
	/**
	 * Creates and sends the "-data-evaluate-expression (int)mmc_gdb_arrayLength(void*)" command.
	 * @param variableName 
	 * @param gdbStackFrame
	 * @return
	 * @throws MIException 
	 */
	public static int getArrayLength(String variableName,
			GDBStackFrame gdbStackFrame) {
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression getArrayLengthCmd = factory.createMIGetArrayLength(variableName);
			getArrayLengthCmd.setQuiet(true);
			miSession.postCommand(getArrayLengthCmd, gdbStackFrame);
			MIDataEvaluateExpressionInfo getArrayLengthInfo = getArrayLengthCmd.getMIDataEvaluateExpressionInfo();
			return Integer.parseInt(getArrayLengthInfo.getExpression());
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	/**
	 * Creates and sends the "-data-evaluate-expression arrayGet(void*,element)" command.
	 * @param variableName
	 * @param element
	 * @param gdbStackFrame
	 * @return
	 * @throws MIException 
	 */
	public static String getArrayElement(String variableName, int element,
			GDBStackFrame gdbStackFrame) throws MIException {
		// TODO Auto-generated method stub
		String result;
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression getArrayElementCmd = factory.createMIGetArrayElement(variableName, element);
			getArrayElementCmd.setQuiet(true);
			miSession.postCommand(getArrayElementCmd, gdbStackFrame);
			result = getArrayElementCmd.getMIDataEvaluateExpressionInfo().getExpression();
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
			result = "GDB ERROR:" + e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			result = "GDB ERROR:" + e.getMessage();
		}
		return result;
	}

	/**
	 * Creates and sends the "-data-evaluate-expression (char*)getRecordElementName(void*,element)" command.
	 * @param variableName
	 * @param element
	 * @param gdbStackFrame
	 * @return
	 * @throws MIException 
	 */
	public static String getRecordElementName(String variableName, int element,
			GDBStackFrame gdbStackFrame) throws MIException {
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression getRecordElementNameCmd = factory.createMIGetRecordElementName(variableName, element);
			getRecordElementNameCmd.setQuiet(true);
			miSession.postCommand(getRecordElementNameCmd, gdbStackFrame);
			return parseResult(getRecordElementNameCmd.getMIDataEvaluateExpressionInfo().getExpression());
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * Creates and sends the "-data-evaluate-expression (int)isOptionNone(void*)" command.
	 * @param variableName
	 * @param gdbStackFrame
	 * @return
	 * @throws MIException 
	 */
	public static String isOptionNone(String variableName,
			GDBStackFrame gdbStackFrame) {
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIDataEvaluateExpression getOptionValueCmd = factory.createMIIsOptionNone(variableName);
			getOptionValueCmd.setQuiet(true);
			miSession.postCommand(getOptionValueCmd, gdbStackFrame);
			return getOptionValueCmd.getMIDataEvaluateExpressionInfo().getExpression();
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to get the array dimensions
	 * by sending command array.ndims
	 * @param variableName 
	 * @param gdbStackFrame
	 * @return
	 * @throws MIException 
	 */
	public static int getModelicaArrayDimensions(String variableName, GDBStackFrame gdbStackFrame) {
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			variableName += ".ndims";
			MIDataEvaluateExpression getArrayDimensionsCmd = factory.createMIGetModelicaArrayDimensions(variableName);
			getArrayDimensionsCmd.setQuiet(true);
			miSession.postCommand(getArrayDimensionsCmd, gdbStackFrame);
			MIDataEvaluateExpressionInfo getArrayDimensionsInfo = getArrayDimensionsCmd.getMIDataEvaluateExpressionInfo();
			return Integer.parseInt(getArrayDimensionsInfo.getExpression());
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	/**
	 * Creates the -data-evaluate-expression EXPR command to get the array dimension size
	 * by sending command array.dim_size[0/1/2...]
	 * @param variableName
	 * @param dimension  
	 * @param gdbStackFrame
	 * @return
	 * @throws MIException 
	 */
	public static int getModelicaArrayDimensionSize(String variableName, int dimension, GDBStackFrame gdbStackFrame) {
		// TODO Auto-generated method stub
		try {
			GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
			MISession miSession = gdbDebugTarget.getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			variableName += ".dim_size[" + (dimension - 1)  + "]";
			MIDataEvaluateExpression getArrayDimensionSizeCmd = factory.createMIGetModelicaArrayDimensionSize(variableName);
			getArrayDimensionSizeCmd.setQuiet(true);
			miSession.postCommand(getArrayDimensionSizeCmd, gdbStackFrame);
			MIDataEvaluateExpressionInfo getArrayDimensionSizeInfo = getArrayDimensionSizeCmd.getMIDataEvaluateExpressionInfo();
			return Integer.parseInt(getArrayDimensionSizeInfo.getExpression());
		} catch (MIException e) {
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	/**
	 * Calculates the correct index number for the Modelica Array.
	 * @param itemName 
	 * @param numberOfDimensions
	 * @param dimension
	 * @param gdbStackFrame 
	 * @return
	 */
	public static Integer getArrayIndex(String itemName, ArrayList<Integer> arrayIndexes, GDBStackFrame gdbStackFrame) {
		// TODO Auto-generated method stub
		if (arrayIndexes.size() == 1)
			arrayIndexes.get(0);
		
		int index = 0;
		for(int i = 0 ; i < arrayIndexes.size() - 1 ; i++)
		{
			index += getArrayIndexHelper(itemName, i, arrayIndexes, gdbStackFrame);
		}
		index += arrayIndexes.get(arrayIndexes.size() - 1);
		return index;
	}
	
	protected static Integer getArrayIndexHelper(String itemName, int startIndex, ArrayList<Integer> arrayIndexes, GDBStackFrame gdbStackFrame) {
		int index = arrayIndexes.get(startIndex);
		for (int i = startIndex + 1 ; i < arrayIndexes.size() ; i++) {
			index *= getModelicaArrayDimensionSize(itemName, i + 1, gdbStackFrame);
		}
		return index;
	}
	
}
