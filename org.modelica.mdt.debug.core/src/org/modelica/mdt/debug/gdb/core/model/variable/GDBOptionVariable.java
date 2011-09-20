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
package org.modelica.mdt.debug.gdb.core.model.variable;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.value.EmptyValue;
import org.modelica.mdt.debug.gdb.core.model.value.GDBOptionValue;

/**
 * @author Adeel Asghar
 *
 */
public class GDBOptionVariable extends GDBVariable {

	/**
	 * @param frame
	 * @param name
	 * @param displayName
	 * @param type
	 * @param referenceType
	 * @param actualType
	 * @param voidPointer
	 */
	public GDBOptionVariable(GDBStackFrame frame, String name,
			String displayName, String type, String referenceType,
			String actualType, String voidPointer) {
		super(frame, name, displayName, type, referenceType, actualType, voidPointer);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public synchronized IValue getValue() throws DebugException {
		// TODO Auto-generated method stub
		if (isDisposed() || !getGDBStackFrame().equals(((GDBThread)getGDBStackFrame().getThread()).getCurrentGDBStackFrame())) {
			return new EmptyValue(this);
		}
		try {
			if (isRefreshValue()) {
				if (getGDBValue() == null) {
					setGDBValue(new GDBOptionValue(this));
				} else if (((GDBOptionValue)getGDBValue()).hasValueChanged()) {
					setValueChanged(true);
					getGDBValue().setRefreshChildren(true);
				} else {
					setValueChanged(false);
					getGDBValue().setRefreshChildren(true);
				}
				setRefreshValue(false);
			}
		} catch (MIException e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		}
		return getGDBValue();
	}
	
}
