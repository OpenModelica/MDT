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
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.Variable;
import org.modelica.mdt.debug.gdb.helper.GDBHelper;
import org.modelica.mdt.debug.gdb.helper.TypeHelper;
import org.modelica.mdt.debug.gdb.helper.ValueHelper;
import org.modelica.mdt.debug.gdb.helper.VariableHelper;

/**
 * @author Adeel Asghar
 *
 */
public class GDBRecordValue extends GDBValue {

	private int fRecordElements = 0;
	
	/**
	 * @param gdbVariable
	 * @throws MIException 
	 */
	public GDBRecordValue(GDBVariable gdbVariable) throws MIException {
		super(gdbVariable);
		// TODO Auto-generated constructor stub
		setRecordElements(ValueHelper.getArrayLength(getGDBVariable().getOriginalName(), getGDBVariable().getGDBStackFrame()));
		setValue(getGDBVariable().getReferenceTypeName());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public synchronized IVariable[] getVariables() throws DebugException {
		// TODO Auto-generated method stub
		if (isDisposed() || !((GDBThread)getGDBVariable().getGDBStackFrame().getThread()).getCurrentGDBStackFrame().equals(getGDBVariable().getGDBStackFrame())) {
			return new IVariable[0];
		}
		if (isRefreshChildren()) {
			if (fGDBChildVariables == null) {
				fGDBChildVariables = new ArrayList<GDBVariable>();
			}
			List<Variable> variablesList = new ArrayList<Variable>();
			try {
				for (int i = 2 ; i <= getRecordElements() ; i++) {
					// get the record element void pointer
					String voidPointer = ValueHelper.getArrayElement(getGDBVariable().getOriginalName(),
							i, getGDBVariable().getGDBStackFrame());
					// get the record element name
					String itemName = ValueHelper.getRecordElementName(getGDBVariable().getOriginalName(),
							i-2, getGDBVariable().getGDBStackFrame());
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
		if (isDisposed() || !((GDBThread)getGDBVariable().getGDBStackFrame().getThread()).getCurrentGDBStackFrame().equals(getGDBVariable().getGDBStackFrame())) {
			return false;
		}
		return getRecordElements() > 1;
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.debug.gdb.core.model.value.GDBValue#createVariable(org.modelica.mdt.debug.gdb.core.mi.output.MIArg)
	 */
	@Override
	public void createVariable(Variable variable) {
		// TODO Auto-generated method stub
		// get the record element type
		String referenceType = TypeHelper.getModelicaMetaType(variable.getVoidPointer(), getGDBVariable().getGDBStackFrame());
		// based on the modelica type create the specific variable.
		VariableHelper.createVariable(getGDBVariable().getGDBStackFrame(), variable.getName(),
				variable.getDisplayName(), GDBHelper.MODELICA_METATYPE, referenceType, getActualType(),
				variable.getVoidPointer(), fGDBChildVariables);
	}
	
	/**
	 * @param elements the fRecordElements to set
	 */
	public void setRecordElements(int elements) {
		this.fRecordElements = elements;
	}

	/**
	 * @return the fRecordElements
	 */
	public int getRecordElements() {
		return fRecordElements;
	}	
}
