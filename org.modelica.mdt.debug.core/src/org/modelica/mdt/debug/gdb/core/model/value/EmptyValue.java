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
package org.modelica.mdt.debug.gdb.core.model.value;

import org.eclipse.debug.core.DebugException;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.Variable;

/**
 * @author Adeel Asghar
 *
 * Represents the empty value.
 *
 */
public class EmptyValue extends GDBValue {

	/**
	 * @param gdbVariable
	 */
	public EmptyValue(GDBVariable gdbVariable) {
		super(gdbVariable);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		// TODO Auto-generated method stub
		return "";
	}
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.debug.gdb.core.model.value.GDBValue#createVariable(org.modelica.mdt.debug.gdb.core.model.variable.Variable)
	 */
	@Override
	public void createVariable(Variable variable) {
		// TODO Auto-generated method stub
		
	}

}
