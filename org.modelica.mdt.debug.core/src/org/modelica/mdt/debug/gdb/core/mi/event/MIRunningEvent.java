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
package org.modelica.mdt.debug.gdb.core.mi.event;

import org.modelica.mdt.debug.gdb.core.mi.MISession;

/**
 * @author Adeel Asghar
 *
 */
/**
*
*  ^running
*/
public class MIRunningEvent extends MIEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int CONTINUE = 0;
	public static final int NEXT = 1;
	public static final int NEXTI = 2;
	public static final int STEP = 3;
	public static final int STEPI = 4;
	public static final int FINISH = 5;
	public static final int UNTIL = 6;
	public static final int RETURN = 7;

	int type;

	public MIRunningEvent(MISession source, int token, int t) {
		super(source, token);
		type = t;
	}

	public int getType() {
		return type;
	}

	public String toString() {
		return "Running";
	}
}
