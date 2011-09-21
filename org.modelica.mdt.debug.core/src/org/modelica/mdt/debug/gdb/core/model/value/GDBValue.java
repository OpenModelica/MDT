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

import java.util.Iterator;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.command.CLIPType;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.output.CLIPTypeInfo;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugElement;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.core.model.variable.Variable;

/**
 * @author Adeel Asghar
 *
 */
/**
 * A base class for all Values.
 */
public abstract class GDBValue extends GDBDebugElement implements IValue {
	
	private String fValue = null;
	private String fActualType = null;
	private GDBVariable fGDBVariable = null;
	private Boolean fRefreshChildren = true;
	protected List<GDBVariable> fGDBChildVariables = null;
	private boolean fIsDisposed = false;
	
	/**
	 * @param gdbVariable
	 */
	public GDBValue(GDBVariable gdbVariable) {
		// TODO Auto-generated constructor stub
		super(gdbVariable.getGDBDebugTarget());
		setGDBVariable(gdbVariable);
		if (this instanceof EmptyValue) {
			return;
		}
		if (getGDBVariable().getActualType() == null) {
			retrieveActualType();
		} else {
			setActualType(getGDBVariable().getActualType());
		}
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
		return getActualType();
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
	
	public boolean hasChanged() {
		Boolean returnFlag = false;
		
		if (hasReferenceTypeChanged()) {
			returnFlag = true;
		}
		if (hasActualTypeChanged()){
			returnFlag = true;
		}
		if (hasValueChanged()) {
			returnFlag = true;
		}
		return returnFlag;
	}
	
	/**
	 * Checks if whether a variable's value has changed or not.
	 * @return true/false
	 */
	public abstract boolean hasValueChanged();
	/**
	 * Checks if whether a variable's reference type has changed or not.
	 * @return true/false
	 */
	public boolean hasReferenceTypeChanged() {
		if (isDisposed() || !getGDBVariable().getGDBStackFrame().equals(((GDBThread)getGDBVariable().getGDBStackFrame().getThread()).getCurrentGDBStackFrame())) {
			return false;
		}
		
		String oldReferenceType = getGDBVariable().getOldReferenceTypeName();
		String newReferenceType = getGDBVariable().getNewReferenceTypeName();
		
		if (newReferenceType.equals(oldReferenceType)) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Checks if whether a variable's actual type has changed or not.
	 * It is very unlikely for the Actual type to change.
	 * So no need to send an extra command on each step that is why this function always returns false.
	 * @return true/false
	 */
	public boolean hasActualTypeChanged() {
		return false;
	}

	/**
	 * @param actualType the fActualType to set
	 */
	public void setActualType(String actualType) {
		this.fActualType = actualType;
	}

	/**
	 * @return the fActualType
	 */
	public String getActualType() {
		return fActualType;
	}
	
	/**
	 * 
	 */
	private void retrieveActualType() {
		// TODO Auto-generated method stub
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		miSession.getRxThread().setEnableConsole(false);
		CLIPType cliPTypeCmd = factory.createCLIPType(getGDBVariable().getOriginalName());
		CLIPTypeInfo cliPTypeInfo;
		try {
			cliPTypeCmd.setQuiet(true);
			miSession.postCommand(cliPTypeCmd, getGDBVariable().getGDBStackFrame());
			cliPTypeInfo = cliPTypeCmd.getMIPtypeInfo();
			setActualType(cliPTypeInfo.getType());
		} catch (MIException e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		} finally {
			miSession.getRxThread().setEnableConsole(true);
		}
	}

	/**
	 * @param refreshChildren the fRefreshChildren to set
	 */
	public void setRefreshChildren(Boolean refreshChildren) {
		this.fRefreshChildren = refreshChildren;
	}

	/**
	 * @return the fRefreshChildren
	 */
	public Boolean isRefreshChildren() {
		return fRefreshChildren;
	}

	/**
	 * @param gdbChildVariables the fGDBChildVariables to set
	 */
	public void setGDBChildVariables(List<GDBVariable> gdbChildVariables) {
		fGDBChildVariables = gdbChildVariables;
	}
	
	/**
	 * @return the fGDBChildVariables
	 */
	public List<GDBVariable> getGDBChildVariables() {
		return fGDBChildVariables;
	}
	
	/**
	 * @param variablesList
	 */
	public void compareVariables(List<Variable> variablesList) {
		// TODO Auto-generated method stub
		for (Variable variable : variablesList) {
			GDBVariable gdbVariable = getGDBChildVariable(variable.getDisplayName());
			if (gdbVariable == null) {
				createVariable(variable);
			} else {
				// update the void pointer because it may be changed
				gdbVariable.setVoidPointer(variable.getVoidPointer());
				gdbVariable.setRefreshValue(true);
				if (gdbVariable.getGDBValue() != null) {
					gdbVariable.getGDBValue().setRefreshChildren(true);
				}
			}
		}
	}

	/**
	 * @param variable
	 */
	public abstract void createVariable(Variable variable);

	/**
	 * @param displayName
	 * @return
	 */
	private GDBVariable getGDBChildVariable(String displayName) {
		// TODO Auto-generated method stub
		for (GDBVariable variable : fGDBChildVariables) {
			if (variable.getDisplayName().equals(displayName)) {
				return variable;
			}
		}
		return null;
	}

	/**
	 * Clear all the child variables in the value
	 */
	public void dispose() {
		// TODO Auto-generated method stub
		setDisposed(true);
		if (fGDBChildVariables == null) {
			return;
		}
		Iterator<GDBVariable> it = fGDBChildVariables.iterator();
		while (it.hasNext()) {
			((GDBVariable)it.next()).dispose();
		}
		fGDBChildVariables.clear();
		fGDBChildVariables = null;
	}
	
	protected boolean isDisposed() {
		return fIsDisposed;
	}

	protected void setDisposed(boolean isDisposed) {
		fIsDisposed = isDisposed;
	}
	
}
