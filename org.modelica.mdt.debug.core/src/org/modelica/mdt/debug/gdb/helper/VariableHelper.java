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

import java.util.Iterator;
import java.util.List;

import org.modelica.mdt.debug.gdb.core.mi.output.MIArg;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBCoreVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBListVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBRecordVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.Variable;

/**
 * @author Adeel Asghar
 *
 */
public class VariableHelper {

	/**
	 * Creates the variable based on their type and adds it the the list passed.
	 * 
	 * @param gdbStackFrame the stack frame for the variable
	 * @param name the internal name of the variable
	 * @param displayName the external name of the variable
	 * @param type the internal type of the variable
	 * @param referenceType the external type of the variable
	 * @param voidPointer 
	 * @param depth 
	 * @param variablesList the list to be appended
	 */
	public static void createVariable(GDBStackFrame gdbStackFrame, String name,
			String displayName, String type, String referenceType, String actualType,
			String voidPointer, List<GDBVariable> variablesList) {
		// TODO Auto-generated method stub
		if (referenceType.equals(GDBHelper.STRING) || referenceType.equals(GDBHelper.REAL) ||
			referenceType.equals(GDBHelper.BOOLEAN) || referenceType.equals(GDBHelper.INTEGER)) {
			variablesList.add(new GDBCoreVariable(gdbStackFrame, name, displayName, type,
					referenceType, actualType, voidPointer));
		} else if (referenceType.startsWith("list<")) {
			variablesList.add(new GDBListVariable(gdbStackFrame, name, displayName, type,
					referenceType, actualType, voidPointer));
		} else if (referenceType.startsWith("record<")) {
			referenceType = GDBHelper.getListType(referenceType);
			variablesList.add(new GDBRecordVariable(gdbStackFrame, name, displayName, type,
					referenceType, actualType, voidPointer));
		}
	}
	
	/**
	 * Takes two lists, one is the current frame variables list
	 * the second is the already created variables list
	 * Loops onto the already created variables list and removes the variables that are not
	 * present in the current frame variables list.
	 * @param variablesList Current frame variables list.
	 * @param gdbVariables Already created variables list
	 */
	@SuppressWarnings("rawtypes")
	public static void removeVariables(List variablesList, List<GDBVariable> gdbVariables) {
		// TODO Auto-generated method stub
		Boolean isFound;
		for (Iterator<GDBVariable> i = gdbVariables.iterator(); i.hasNext();) {
			isFound = false;
			GDBVariable variable = i.next();
			for (Iterator j = variablesList.iterator(); j.hasNext();) {
				Object obj = j.next();
				if (obj instanceof MIArg) {
					if (variable.getOriginalName().equals(((MIArg)obj).getName())) {
						isFound = true;
						break;
					}
				} else if (obj instanceof Variable) {
					if (variable.getDisplayName().equals(((Variable)obj).getDisplayName())) {
						isFound = true;
						break;
					}
				}
			}
			if (!isFound) {
				i.remove();
			}
		}
	}

}
