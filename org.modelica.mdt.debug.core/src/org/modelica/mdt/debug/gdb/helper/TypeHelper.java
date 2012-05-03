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
public class TypeHelper {
	
	public static String getModelicaType(String variableName, String variableType, GDBStackFrame gdbStackFrame) {
		if (variableType.equals(GDBHelper.MODELICA_METATYPE)) {
			return getModelicaMetaType(variableName, gdbStackFrame);
		} else if (variableType.equals(GDBHelper.MODELICA_BOOLEAN)) {
			return GDBHelper.BOOLEAN;
		} else if (variableType.equals(GDBHelper.MODELICA_INETGER)) {
			return GDBHelper.INTEGER;
		} else if (variableType.equals(GDBHelper.MODELICA_REAL)) {
			return GDBHelper.REAL;
		} else if (variableType.equals(GDBHelper.MODELICA_INTEGER_ARRAY)) {
			return GDBHelper.MODELICA_INTEGER_ARRAY;
		}
		return variableType;
	}
	
	public static String getModelicaMetaType(String variableName, GDBStackFrame gdbStackFrame) {
		GDBDebugTarget gdbDebugTarget = gdbStackFrame.getGDBDebugTarget();
		MISession miSession = gdbDebugTarget.getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIDataEvaluateExpression getTypeOfAnyCmd = factory.createMIGetTypeOfAny(variableName);
		getTypeOfAnyCmd.setQuiet(true);
		MIDataEvaluateExpressionInfo getTypeOfAnyInfo;
		try {
			miSession.postCommand(getTypeOfAnyCmd, gdbStackFrame);
			getTypeOfAnyInfo = getTypeOfAnyCmd.getMIDataEvaluateExpressionInfo();
			/* the response received from -data-evaluate-expression is like this
			 * ^done,value="0x1b488af \"replaceable type Any\""
			 * we need to parse it so that only data type is returned 
			*/
			int beginIndex = getTypeOfAnyInfo.getExpression().indexOf("\"");
			int endIndex = getTypeOfAnyInfo.getExpression().lastIndexOf("\"");
			return getTypeOfAnyInfo.getExpression().substring(beginIndex + 1, endIndex);
		} catch (MIException e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return GDBHelper.REPLACEABLE_TYPE_ANY;
	}

}
