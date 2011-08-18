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
	 * @param variableName
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static String getModelicaAnyString(String variableName,
			GDBDebugTarget gdbDebugTarget) throws MIException {
		// TODO Auto-generated method stub
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getTypeOfAnyCmd = factory.createMIAnyString(variableName);
		miSession.postCommand(getTypeOfAnyCmd);
		MIDataEvaluateExpressionInfo getTypeOfAnyInfo = getTypeOfAnyCmd.getMIDataEvaluateExpressionInfo();
		/* the response received from -data-evaluate-expression is like this
		 * ^done,value="0x1b488af \"value\""
		 * we need to parse it so that only data type is returned 
		*/
		int beginIndex = getTypeOfAnyInfo.getExpression().indexOf("\"");
		int endIndex = getTypeOfAnyInfo.getExpression().lastIndexOf("\"");
		return getTypeOfAnyInfo.getExpression().substring(beginIndex + 1, endIndex);
	}

	/**
	 * @param variableName 
	 * @param gdbDebugTarget
	 * @return
	 * @throws MIException 
	 */
	public static int getModelicaListLength(String variableName,
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
	
}
