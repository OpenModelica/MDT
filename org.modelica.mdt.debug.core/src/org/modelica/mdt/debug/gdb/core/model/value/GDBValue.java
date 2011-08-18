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
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugElement;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;

/**
 * @author Adeel Asghar
 *
 */
/**
 * A base class for all Values.
 */
public abstract class GDBValue extends GDBDebugElement implements IValue {
	
	private String fValue = null;
	private GDBVariable fGDBVariable = null;
	
	/**
	 * @param gdbVariable
	 */
	public GDBValue(GDBVariable gdbVariable) {
		// TODO Auto-generated constructor stub
		super(gdbVariable.getGDBDebugTarget());
		setGDBVariable(gdbVariable);
	}

	/**
	 * @param value the fValue to set
	 */
	public void setValue(String value) {
		this.fValue = value;
	}

	/**
	 * @return the fValue
	 */
	public String getValue() {
		return fValue;
	}

	/**
	 * @param gdbVariable the fGDBVariable to set
	 */
	public void setGDBVariable(GDBVariable gdbVariable) {
		this.fGDBVariable = gdbVariable;
	}

	/**
	 * @return the fGDBVariable
	 */
	public GDBVariable getGDBVariable() {
		return fGDBVariable;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	@Override
	public String getReferenceTypeName() throws DebugException {
		// TODO Auto-generated method stub
		try {
			Integer.parseInt(fValue);
		} catch (NumberFormatException e) {
			return "text";
		}
		return "integer";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		// TODO Auto-generated method stub
		return fValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#isAllocated()
	 */
	@Override
	public boolean isAllocated() throws DebugException {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public IVariable[] getVariables() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
