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
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.Variable;
import org.modelica.mdt.debug.gdb.helper.GDBHelper;
import org.modelica.mdt.debug.gdb.helper.ValueHelper;
import org.modelica.mdt.debug.gdb.helper.VariableHelper;

/**
 * @author Adeel Asghar
 *
 */
public class GDBArrayValue extends GDBValue {

	private int fArrayLength = 0;
	
	/**
	 * @param gdbVariable
	 * @throws MIException 
	 */
	public GDBArrayValue(GDBVariable gdbVariable) throws MIException {
		super(gdbVariable);
		// TODO Auto-generated constructor stub
		setArrayLength(ValueHelper.getArrayLength(getGDBVariable().getOriginalName(), getGDBDebugTarget()));
		if (getArrayLength() > 1) {
			setValue("<" + getArrayLength() + " items>");
		} else {
			setValue("<" + getArrayLength() + " item>");
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public synchronized IVariable[] getVariables() throws DebugException {
		// TODO Auto-generated method stub
		if (isDisposed()) {
			return new IVariable[0];
		}
		if (isRefreshChildren()) {
			if (fGDBChildVariables == null) {
				fGDBChildVariables = new ArrayList<GDBVariable>();
			}
			List<Variable> variablesList = new ArrayList<Variable>();
			try {
				for (int i = 1 ; i <= getArrayLength() ; i++) {
					String voidPointer = ValueHelper.getArrayElement(getGDBVariable().getOriginalName(), i, 
							getGDBDebugTarget());
					String itemName = "[" + i + "]";
					String displayName = itemName;
					variablesList.add(new Variable(itemName, displayName, voidPointer));
				}
				// first remove the variables that are removed from the List
				VariableHelper.removeVariables(variablesList, fGDBChildVariables);
				// compare and create IVariable
				compareVariables(variablesList);
			} catch (MIException e) {
				// TODO: handle exception
				MDTDebugCorePlugin.log(null, e);
			}
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
		if (isDisposed()) {
			return false;
		}
		return getArrayLength() > 0;
	}
		
	@Override
	public void createVariable(Variable variable) {
		// TODO Auto-generated method stub
		String referenceType = GDBHelper.getListType(getGDBVariable().getReferenceTypeName());
		// based on the modelica type create the specific variable.
		VariableHelper.createVariable(getGDBVariable().getGDBStackFrame(), variable.getName(),
				variable.getDisplayName(), GDBHelper.MODELICA_METATYPE, referenceType, getActualType(),
				variable.getVoidPointer(), fGDBChildVariables);
	}
	
	/**
	 * @return
	 * @throws MIException 
	 */
	public boolean hasValueChanged() throws MIException {
		// TODO Auto-generated method stub
		if (isDisposed()) {
			return false;
		}
		String oldValue = getValue();
		setArrayLength(ValueHelper.getArrayLength(getGDBVariable().getOriginalName(), getGDBDebugTarget()));
		String newValue;
		if (getArrayLength() > 1) {
			newValue = "<" + getArrayLength() + " items>";
		} else {
			newValue = "<" + getArrayLength() + " item>";
		}
		if (oldValue.equals(newValue)) {
			return false;
		} else {
			setValue(newValue);
			return true;
		}
	}

	/**
	 * @param arrayLength the fArrayLength to set
	 */
	public void setArrayLength(int arrayLength) {
		this.fArrayLength = arrayLength;
	}

	/**
	 * @return the fListLength
	 */
	public int getArrayLength() {
		return fArrayLength;
	}

}
