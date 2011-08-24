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
public class GDBListValue extends GDBValue {

	private int fListLength = 0;
	
	/**
	 * @param gdbVariable
	 * @throws MIException 
	 */
	public GDBListValue(GDBVariable gdbVariable) throws MIException {
		super(gdbVariable);
		// TODO Auto-generated constructor stub
		setListLength(ValueHelper.getListLength(getGDBVariable().getOriginalName(), getGDBDebugTarget()));
		if (getListLength() > 1) {
			setValue("<" + getListLength() + " items>");
		} else {
			setValue("<" + getListLength() + " item>");
		}
	}
	
	/**
	 * @return
	 * @throws MIException 
	 */
	public boolean hasValueChanged() throws MIException {
		// TODO Auto-generated method stub
		String oldValue = getValue();
		setListLength(ValueHelper.getListLength(getGDBVariable().getOriginalName(), getGDBDebugTarget()));
		String newValue;
		if (getListLength() > 1) {
			newValue = "<" + getListLength() + " items>";
		} else {
			newValue = "<" + getListLength() + " item>";
		}
		if (oldValue.equals(newValue)) {
			return false;
		} else {
			setValue(newValue);
			return true;
		}
	}

	/**
	 * @param listLength the fListLength to set
	 */
	public void setListLength(int listLength) {
		this.fListLength = listLength;
	}

	/**
	 * @return the fListLength
	 */
	public int getListLength() {
		return fListLength;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public synchronized IVariable[] getVariables() throws DebugException {
		// TODO Auto-generated method stub
		if (isRefreshChildren()) {
			List<Variable> variablesList = new ArrayList<Variable>();
			try {
				for (int i = 1 ; i <= getListLength() ; i++) {
					String voidPointer = ValueHelper.getListItem(getGDBVariable().getOriginalName(), i, 
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
				e.printStackTrace();
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
		return getListLength() > 0;
	}
		
	@Override
	public void createVariable(Variable variable) {
		// TODO Auto-generated method stub
		String referenceType = GDBHelper.getListType(getGDBVariable().getReferenceTypeName());
		// based on the modelica type create the specific variable.
		VariableHelper.createVariable(getGDBVariable().getStackFrame(), variable.getName(),
				variable.getDisplayName(), "modelica_metatype", referenceType, getActualType(),
				variable.getVoidPointer(), fGDBChildVariables);
	}
	
}
