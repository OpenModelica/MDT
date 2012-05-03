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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBModelicaArrayElementVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.Variable;
import org.modelica.mdt.debug.gdb.helper.ValueHelper;
import org.modelica.mdt.debug.gdb.helper.VariableHelper;

/**
 * @author Adeel Asghar
 *
 */
public class GDBModelicaArrayValue extends GDBValue {

	private int fNumberOfDimensions = 0;
	private int fDimensionLength = 0;
	private int fDimension = 0;
	/**
	 * @param gdbVariable
	 */
	public GDBModelicaArrayValue(GDBVariable gdbVariable) {
		super(gdbVariable);
		// TODO Auto-generated constructor stub
		setDimension(1);
		setNumberOfDimensions(ValueHelper.getModelicaArrayDimensions(getGDBVariable().getOriginalName(), getGDBVariable().getGDBStackFrame()));
		setDimensionLength(ValueHelper.getModelicaArrayDimensionSize(getGDBVariable().getOriginalName(), getDimension(), getGDBVariable().getGDBStackFrame()));
		if (getDimensionLength() > 1) {
			setValue("<" + getDimensionLength() + " items>");
		} else {
			setValue("<" + getDimensionLength() + " item>");
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public synchronized IVariable[] getVariables() {
		// TODO Auto-generated method stub
		if (isDisposed() || !getGDBVariable().getGDBStackFrame().equals(((GDBThread)getGDBVariable().getGDBStackFrame().getThread()).getCurrentGDBStackFrame())) {
			return new IVariable[0];
		}
		if (isRefreshChildren()) {
			if (fGDBChildVariables == null) {
				fGDBChildVariables = new ArrayList<GDBVariable>();
			}
			List<Variable> variablesList = new ArrayList<Variable>();
			for (int i = 0 ; i < getDimensionLength() ; i++) {
				String itemName = getGDBVariable().getOriginalName();
				String displayName = "[" + i + "]";
				ArrayList<Integer> arrayIndexes = new ArrayList<Integer>();
				arrayIndexes.add(i);
				variablesList.add(new Variable(itemName, displayName, null, arrayIndexes));
			}
			// first remove the variables that are removed from the List
			VariableHelper.removeVariables(variablesList, fGDBChildVariables);
			// compare and create IVariable
			compareVariables(variablesList);
			setRefreshChildren(false);
		}
		return (IVariable[])getGDBChildVariables().toArray(new IVariable[getGDBChildVariables().size()]);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		// TODO Auto-generated method stub
		if (isDisposed() || !getGDBVariable().getGDBStackFrame().equals(((GDBThread)getGDBVariable().getGDBStackFrame().getThread()).getCurrentGDBStackFrame())) {
			return false;
		}
		return getDimensionLength() > 0;
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
		setNumberOfDimensions(ValueHelper.getModelicaArrayDimensions(getGDBVariable().getOriginalName(), getGDBVariable().getGDBStackFrame()));
		setDimensionLength(ValueHelper.getModelicaArrayDimensionSize(getGDBVariable().getOriginalName(), getDimension(), getGDBVariable().getGDBStackFrame()));
		String newValue;
		if (getDimensionLength() > 1) {
			newValue = "<" + getDimensionLength() + " items>";
		} else {
			newValue = "<" + getDimensionLength() + " item>";
		}
		if (oldValue.equals(newValue)) {
			return false;
		} else {
			setValue(newValue);
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.debug.gdb.core.model.value.GDBValue#createVariable(org.modelica.mdt.debug.gdb.core.model.variable.Variable)
	 */
	@Override
	public void createVariable(Variable variable) {
		// TODO Auto-generated method stub
		try {
			String referenceType = getGDBVariable().getReferenceTypeName();
			fGDBChildVariables.add(new GDBModelicaArrayElementVariable(getGDBVariable().getGDBStackFrame(), variable.getName(), variable.getDisplayName(),
					referenceType, referenceType, null, null, getDimension() + 1, getNumberOfDimensions(), variable.getArrayIndexes()));
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		}
	}

	/**
	 * @param fNumberOfDimensions the fNumberOfDimensions to set
	 */
	public void setNumberOfDimensions(int fNumberOfDimensions) {
		this.fNumberOfDimensions = fNumberOfDimensions;
	}

	/**
	 * @return the fNumberOfDimensions
	 */
	public int getNumberOfDimensions() {
		return fNumberOfDimensions;
	}

	/**
	 * @param fDimensionLength the fDimensionLength to set
	 */
	public void setDimensionLength(int fDimensionLength) {
		this.fDimensionLength = fDimensionLength;
	}

	/**
	 * @return the fDimensionLength
	 */
	public int getDimensionLength() {
		return fDimensionLength;
	}

	/**
	 * @param fDimension the fDimension to set
	 */
	public void setDimension(int fDimension) {
		this.fDimension = fDimension;
	}

	/**
	 * @return the fDimension
	 */
	public int getDimension() {
		return fDimension;
	}

}
