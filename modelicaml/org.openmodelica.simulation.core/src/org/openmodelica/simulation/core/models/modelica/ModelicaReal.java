/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.simulation.core.models.modelica;

// TODO: Auto-generated Javadoc
/**
 * Represents the primitive data type "Real" from the Modelica language
 * A Real can has a min and max value.
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class ModelicaReal extends ModelicaPrimitiveDataType{

	/** The value. */
	private Double value;
	
	/*
	 * the Range of the value is important if the user wants to use a slider to change the value
	 */
	/** The min value. */
	private Double minValue;
	
	/** The max value. */
	private Double maxValue;
	
	/**
	 * Use this Constructor as default, set the primitive data type to "Real" and its value to 0.0 as default.
	 * As default the values of minValue and maxValue are "null". If both of the values are not "null" a slider can be used to change the value while simulation is running
	 */
	public ModelicaReal() {
		super("Real");
		value = 0D;
		
		minValue = null;
		maxValue = null;
		
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}
	
	/**
	 * After Reading the XML file with the standard initial values the user has the possibility to change the default value.
	 *
	 * @param value the new value
	 */
	public void setValue(Double value){
		this.value = value;
	}

	/**
	 * Gets the min value.
	 *
	 * @return the min value
	 */
	public Double getMinValue() {
		return minValue;
	}

	/**
	 * Sets the min value.
	 *
	 * @param minValue the new min value
	 */
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	/**
	 * Gets the max value.
	 *
	 * @return the max value
	 */
	public Double getMaxValue() {
		return maxValue;
	}

	/**
	 * Sets the max value.
	 *
	 * @param maxValue the new max value
	 */
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	/* (non-Javadoc)
	 * @see org.openmodelica.simulation.core.models.modelica.ModelicaPrimitiveDataType#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " Value: " + value + " MinValue: " + minValue + " MaxValue: " + maxValue;
	}
}