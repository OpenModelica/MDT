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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.debug.gdb.core.mi.output.MIFrame;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBCoreVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBListVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;

/**
 * @author Adeel Asghar
 *
 */
public class GDBHelper {
	
	public static String MODELICA_METATYPE = "modelica_metatype";
	public static String MODELICA_BOOLEAN = "modelica_boolean";
	public static String MODELICA_INETGER = "modelica_integer";
	public static String MODELICA_REAL = "modelica_real";
	public static String STRING = "String";
	public static String BOOLEAN = "Boolean";
	public static String INTEGER = "Integer";
	public static String REAL = "Real";
	public static String REPLACEABLE_TYPE_ANY = "replaceable type Any";
	
	public static Boolean filterCFiles(GDBDebugTarget gdbDebugTarget, MIFrame miFrame) throws CoreException {
		if (miFrame.getFile().isEmpty()) {
			return true;
		}
		String debugCSource = gdbDebugTarget.getLaunch().getLaunchConfiguration().getAttribute(IMDTConstants.ATTR_MDT_DEBUG_C_SOURCE, "false");
		if (debugCSource.equals("true")) {
			// if debugcsource is true we return false but we have to make sure that
			// user don't get any bootstrapped c file
			Path filePath = new Path(miFrame.getFile());
			if (filePath.segment(filePath.segmentCount() - 2).equals("bootstrapping")) {
				return true;
			}
			return false;
		} else {
			return isCFile(miFrame.getFile());
		}
	}
	
	public static Boolean isCFile(String file) {
		if (new Path(file).getFileExtension().equals("c")) {
			return true;
		} else if (new Path(file).getFileExtension().equals("cpp")) {
			return true;
		} else if (new Path(file).getFileExtension().equals("cc")) {
			return true;
		} else if (new Path(file).getFileExtension().equals("h")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates the variables based on their type and adds it the the list passed.
	 * 
	 * @param gdbStackFrame the stack frame for the variable
	 * @param name the internal name of the variable
	 * @param displayName the external name of the variable
	 * @param type the internal type of the variable
	 * @param referenceType the external type of the variable
	 * @param variablesList the list to be appended
	 */
	public static List<GDBVariable> createVariables(GDBStackFrame gdbStackFrame, String name, String displayName,
			String type, String referenceType, List<GDBVariable> variablesList) {
		// TODO Auto-generated method stub
		if (referenceType.equals(GDBHelper.STRING) || referenceType.equals(GDBHelper.REAL) ||
			referenceType.equals(GDBHelper.BOOLEAN) || referenceType.equals(GDBHelper.INTEGER)) {
			variablesList.add(new GDBCoreVariable(gdbStackFrame, name, displayName, type,
					referenceType));
		} else if (referenceType.startsWith("list<")) {
			variablesList.add(new GDBListVariable(gdbStackFrame, name, displayName, type,
					referenceType));
		}
		return variablesList;
	}

}
