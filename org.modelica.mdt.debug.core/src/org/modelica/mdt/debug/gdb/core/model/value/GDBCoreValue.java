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
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.Variable;
import org.modelica.mdt.debug.gdb.helper.GDBHelper;
import org.modelica.mdt.debug.gdb.helper.ValueHelper;

/**
 * @author Adeel Asghar
 *
 * Represents the values of core data types String, Boolean, Integer, Real.
 *
 */
public class GDBCoreValue extends GDBValue {

	/**
	 * @param gdbVariable
	 * @throws MIException 
	 */
	public GDBCoreValue(GDBVariable gdbVariable) throws MIException {
		super(gdbVariable);
		// TODO Auto-generated constructor stub
		if (getGDBVariable().getVoidPointer() != null) {
			setValue(ValueHelper.getAnyString(getGDBVariable().getVoidPointer(), getGDBVariable().getNewReferenceTypeName(),
					getGDBVariable().getGDBStackFrame()));
		} else {
			if (getGDBVariable().getNewReferenceTypeName().equals(GDBHelper.STRING)) {
				setValue(ValueHelper.getAnyString(getGDBVariable().getOriginalName(), getGDBVariable().getNewReferenceTypeName(),
						getGDBVariable().getGDBStackFrame()));
			} else {
				setValue(ValueHelper.evaluateExpression(getGDBVariable().getOriginalName(), getGDBVariable().getGDBStackFrame()));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.debug.gdb.core.model.value.GDBValue#hasValueChanged()
	 */
	@Override
	public boolean hasValueChanged() {
		// TODO Auto-generated method stub
		if (isDisposed() || !getGDBVariable().getGDBStackFrame().equals(((GDBThread)getGDBVariable().getGDBStackFrame().getThread()).getCurrentGDBStackFrame())) {
			return false;
		}
		String oldValue = getValue();
		String newValue;
		if (getGDBVariable().getVoidPointer() != null) {
			newValue = ValueHelper.getAnyString(getGDBVariable().getVoidPointer(), getGDBVariable().getNewReferenceTypeName(),
					getGDBVariable().getGDBStackFrame());
		} else {
			if (getGDBVariable().getNewReferenceTypeName().equals(GDBHelper.STRING)) {
				newValue = ValueHelper.getAnyString(getGDBVariable().getOriginalName(), getGDBVariable().getNewReferenceTypeName(),
						getGDBVariable().getGDBStackFrame());
			} else {
				newValue = ValueHelper.evaluateExpression(getGDBVariable().getOriginalName(), getGDBVariable().getGDBStackFrame());
			}
		}
		
		if (oldValue.equals(newValue)) {
			return false;
		} else {
			setValue(newValue);
			return true;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		// TODO Auto-generated method stub
		if (isDisposed() || !getGDBVariable().getGDBStackFrame().equals(((GDBThread)getGDBVariable().getGDBStackFrame().getThread()).getCurrentGDBStackFrame())) {
			return null;
		}
		if (getGDBVariable().getReferenceTypeName().equals(GDBHelper.BOOLEAN)) {
			try {
				String result = getValue().substring(0, getValue().indexOf(" "));
				if (result.equals("1")) {
					return "true";
				} else if (result.equals("0")) {
					return "false";
				} else {
					return getValue();
				}
			} catch (StringIndexOutOfBoundsException e) {
				//MDTDebugCorePlugin.log(null, e);
				return getValue();
			}
		} else {
			return super.getValueString();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.debug.gdb.core.model.value.GDBValue#createVariable(org.modelica.mdt.debug.gdb.core.model.variable.Variable)
	 */
	@Override
	public void createVariable(Variable variable) {
		// TODO Auto-generated method stub
		
	}

}
