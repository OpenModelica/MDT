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
 * -break-condition NUMBER EXPR
 *
 * Breakpoint NUMBER will stop the program only if the condition in
 * EXPR is true. The condition becomes part of the `-break-list' output
 * Result:
 *  ^done
 */
public class MIBreakCondition extends MICommand {
	
	public MIBreakCondition(int breakPointNumber, String condition) {
		super("-break-condition", new String[] { Integer.toString(breakPointNumber), condition });
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do not do any manipulation on the string i.e. quoting spaces
	 * etc .. doing this will break the command -break-condition.
	 */
	protected String parametersToString() {
		String[] parameters = getParameters();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < parameters.length; i++) {
			buffer.append(' ').append(parameters[i]);
		}
		return buffer.toString().trim();
	}
}
