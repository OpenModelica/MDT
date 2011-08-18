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
import org.eclipse.debug.core.model.IVariable;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugElement;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;
import org.modelica.mdt.debug.gdb.core.model.value.GDBValue;

/**
 * @author Adeel Asghar
 *
 */
/**
 * A base class for all variables. 
 */
public abstract class GDBVariable extends GDBDebugElement implements IVariable {

	private String fName;
	private String fDisplayName;
	private String fType;
	private String fReferenceTypeName;
	private GDBStackFrame fGDBStackFrame;
	private boolean fValueChanged = false;
	private boolean fReferenceTypeChanged = false;
	private boolean fDisplayNameChanged = false;
	private Boolean fRefreshValue = true;
	private GDBValue fGDBValue = null;
	
	/**
	 * Constructs a variable contained in the given stack frame
	 * with the given name.
	 * 
	 * @param frame owning the variable
	 * @param name variable name
	 * @param type 
	 */
	public GDBVariable(GDBStackFrame frame, String name, String displayName, String type,
			String referenceType) {
		super(frame.getGDBDebugTarget());
		fGDBStackFrame = frame;
		fName = name;
		setDisplayName(displayName);
		setType(type);
		setReferenceTypeName(referenceType);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getName(java.lang.String)
	 */
	@Override
	public String getName() throws DebugException {
		// TODO Auto-generated method stub
		return fDisplayName;
	}
	
	/**
	 * @return the fName
	 */
	public String getOriginalName() {
		return fName;
	}
	
	/**
	 * @param displayName the fDisplayName to set
	 */
	public void setDisplayName(String displayName) {
		this.fDisplayName = displayName;
	}	

	/**
	 * @param type the fType to set
	 */
	public void setType(String type) {
		this.fType = type;
	}

	/**
	 * @return the fType
	 */
	public String getType() {
		return fType;
	}
	
	/**
	 * @param referenceTypeName the fReferenceTypeName to set
	 */
	public void setReferenceTypeName(String referenceTypeName) {
		this.fReferenceTypeName = referenceTypeName;
	}

	/**
	 * @return the fReferenceTypeName
	 */
	public String getReferenceTypeName() {
		return fReferenceTypeName;
	}
	
	/**
	 * Returns the stack frame owning this variable.
	 * 
	 * @return the stack frame owning this variable
	 */
	public GDBStackFrame getStackFrame() {
		return fGDBStackFrame;
	}
	
    /**
	 * @param change the fValueChanged to set
	 */
	public void setValueChanged(boolean change) {
		this.fValueChanged = change;
	}
	
	/**
	 * @return the fValueChanged
	 */
	public boolean isValueChanged() {
		return fValueChanged;
	}
	
	/**
	 * @param change the fReferenceTypeChanged to set
	 */
	public void setReferenceTypeChanged(boolean change) {
		this.fReferenceTypeChanged = change;
	}
	
	/**
	 * @return the fReferenceTypeChanged
	 */
	public boolean isReferenceTypeChanged() {
		return fReferenceTypeChanged;
	}
	
	/**
	 * @param change the fDisplayNameChanged to set
	 */
	public void setDisplayNameChanged(boolean change) {
		this.fDisplayNameChanged = change;
	}
	
	/**
	 * @return the fDisplayNameChanged
	 */
	public boolean isDisplayNameChanged() {
		return fDisplayNameChanged;
	}
	
	/**
	 * @param refreshValue the fRefreshValue to set
	 */
	public void setRefreshValue(Boolean refreshValue) {
		this.fRefreshValue = refreshValue;
	}
	
	/**
	 * @return the fRefreshValue
	 */
	public Boolean isRefreshValue() {
		return fRefreshValue;
	}	

	/**
	 * @param gdbValue the fGDBValue to set
	 */
	public void setGDBValue(GDBValue gdbValue) {
		this.fGDBValue = gdbValue;
	}

	/**
	 * @return the fGDBValue
	 */
	public GDBValue getGDBValue() {
		return fGDBValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String expression) throws DebugException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#setValue(org.eclipse.debug.core.model.IValue)
	 */
	@Override
	public void setValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#supportsValueModification()
	 */
	@Override
	public boolean supportsValueModification() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#verifyValue(java.lang.String)
	 */
	@Override
	public boolean verifyValue(String expression) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#verifyValue(org.eclipse.debug.core.model.IValue)
	 */
	@Override
	public boolean verifyValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public synchronized IValue getValue() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#hasValueChanged()
	 */
	@Override
	public synchronized boolean hasValueChanged() throws DebugException {
		// TODO Auto-generated method stub
		if (isValueChanged()) {
			setValueChanged(false);
			return true;
		} else if (isReferenceTypeChanged()) {
			setReferenceTypeChanged(false);
			return true;
		} else if (isDisplayNameChanged()) {
			setDisplayNameChanged(false);
			return true;
		} else {
			return false;
		}
	}
	
}
