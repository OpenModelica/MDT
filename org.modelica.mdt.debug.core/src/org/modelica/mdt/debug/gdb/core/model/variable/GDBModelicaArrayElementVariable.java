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

import java.util.ArrayList;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.value.EmptyValue;
import org.modelica.mdt.debug.gdb.core.model.value.GDBModelicaArrayElementValue;
import org.modelica.mdt.debug.gdb.helper.ValueHelper;

/**
 * @author Adeel Asghar
 *
 */
public class GDBModelicaArrayElementVariable extends GDBVariable {

	private int fNumberOfDimensions = 0;
	private int fDimensionLength = 0;
	private int fDimension = 0;
	private ArrayList<Integer> fArrayIndexes;
	
	/**
	 * @param frame
	 * @param name
	 * @param displayName
	 * @param type
	 * @param referenceType
	 * @param actualType
	 * @param voidPointer
	 * @param numberOfDimensions 
	 * @param dimension 
	 * @param arrayIndexes 
	 */
	public GDBModelicaArrayElementVariable(GDBStackFrame frame,
			String name, String displayName, String type, String referenceType,
			String actualType, String voidPointer, int dimension, int numberOfDimensions, ArrayList<Integer> arrayIndexes) {
		super(frame, name, displayName, type, referenceType, actualType, voidPointer);
		// TODO Auto-generated constructor stub
		setNumberOfDimensions(numberOfDimensions);
		setDimension(dimension);
		setDimensionLength(ValueHelper.getModelicaArrayDimensionSize(name, getDimension(), getGDBStackFrame()));
		setArrayIndexes(arrayIndexes);
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
		if (isRefreshValue()) {
			if (getGDBValue() == null) {
				setGDBValue(new GDBModelicaArrayElementValue(this));
			} else if (((GDBModelicaArrayElementValue)getGDBValue()).hasChanged()) {
				setValueChanged(true);
				getGDBValue().setRefreshChildren(true);
			} else {
				setValueChanged(false);
				getGDBValue().setRefreshChildren(true);
			}
			setRefreshValue(false);
		}
		return getGDBValue();
	}

	/**
	 * @return the fNumberOfDimensions
	 */
	public int getNumberOfDimensions() {
		return fNumberOfDimensions;
	}

	/**
	 * @param fNumberOfDimensions the fNumberOfDimensions to set
	 */
	public void setNumberOfDimensions(int fNumberOfDimensions) {
		this.fNumberOfDimensions = fNumberOfDimensions;
	}

	/**
	 * @return the fDimensionLength
	 */
	public int getDimensionLength() {
		return fDimensionLength;
	}

	/**
	 * @param fDimensionLength the fDimensionLength to set
	 */
	public void setDimensionLength(int fDimensionLength) {
		this.fDimensionLength = fDimensionLength;
	}

	/**
	 * @return the fDimension
	 */
	public int getDimension() {
		return fDimension;
	}

	/**
	 * @param fDimension the fDimension to set
	 */
	public void setDimension(int fDimension) {
		this.fDimension = fDimension;
	}

	/**
	 * @return the fArrayIndex
	 */
	public ArrayList<Integer> getArrayIndexes() {
		return fArrayIndexes;
	}

	/**
	 * @param arrayIndexes the fArrayIndex to set
	 */
	public void setArrayIndexes(ArrayList<Integer> arrayIndexes) {
		this.fArrayIndexes = arrayIndexes;
	}

}
