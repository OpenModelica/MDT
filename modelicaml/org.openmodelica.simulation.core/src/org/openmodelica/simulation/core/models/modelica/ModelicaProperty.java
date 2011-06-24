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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.core.models.modelica;

// TODO: Auto-generated Javadoc
/**
 * Represents a Modelica property this could be variable, parameter, input or output variable
 * A Property has a name and a comment. A Property can be plotted on a view during an simulation or non.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public abstract class ModelicaProperty {

	/** Name of the modelica property. */
	private String name;
	
	/** Specific comment at the instance declaration. */
	private String comment;
	
	/** Option if this property should be displayed on a plot/view. */
	private Boolean display;
	
	/** The primitive data type. */
	private ModelicaPrimitiveDataType primitive;

	/**
	 * Use this constructor as default.
	 * @param name Name of the modelica variable
	 * @param modelicaType Type of the Modelica primitive data type (Real, Integer, String, Boolean) 
	 * @param comment Specific comment at the instance declaration
	 */
	public ModelicaProperty(String name, String modelicaType, String comment) {
		super();
		this.comment = comment;
		this.name = name;
		display = false;

		if (modelicaType.equals("Real")) {
			primitive = new ModelicaReal();
		} else if (modelicaType.equals("Integer")) {
			primitive = new ModelicaInteger();
		} else if (modelicaType.equals("String")) {
			primitive = new ModelicaString();
		} else if (modelicaType.equals("Boolean")) {
			primitive = new ModelicaBoolean();
		}
	}

	/**
	 * Returns the full qualified name of the Modelica property.
	 *
	 * @return full qualified name as String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the comment of the Modelica property.
	 *
	 * @return comment as String
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Returns the type of the Modelica property
	 * 
	 * Modelica Types: Real, Integer, String, Boolean.
	 *
	 * @return type as String
	 */
	public String getType() {
		return primitive.getType();
	}

	/**
	 * Returns the flag if a property should being displayed in a plot or not.
	 *
	 * @return true if it should be displayed false if not
	 */
	public Boolean getDisplay() {
		return display;
	}

	/**
	 * Set the flag if a property should being displayed or not.
	 *
	 * @param display the new display
	 */
	public void setDisplay(Boolean display) {
		this.display = display;
	}

	/**
	 * returns the value of the primitive type Double = ModelicaReal Integer =
	 * ModelicaInteger String = ModelicaString Boolean = ModelicaBoolean.
	 *
	 * @return The value of the Modelica primitive value will be returned. Its type could be: Double, Integer, String or Boolean
	 */
	public Object getPrimitiveValue() {
		if (primitive instanceof ModelicaReal) {
			return ((ModelicaReal) primitive).getValue();
		} else if (primitive instanceof ModelicaInteger) {
			return ((ModelicaInteger) primitive).getValue();
		} else if (primitive instanceof ModelicaString) {
			return ((ModelicaString) primitive).getValue();
		} else if (primitive instanceof ModelicaBoolean) {
			return ((ModelicaBoolean) primitive).getValue();
		} else
			return null;
	}

	/**
	 * Sets the value of the primitive type Double = ModelicaReal Integer =
	 * ModelicaInteger String = ModelicaString Boolean = ModelicaBoolean.
	 *
	 * @param value have to be from the types: Double, Integer, String or Boolean
	 * @throws Exception the exception
	 */
	public void setPrimitiveValue(Object value) throws Exception {
		if (value instanceof Double && primitive instanceof ModelicaReal) {
			((ModelicaReal) primitive).setValue((Double) value);
		} else if (value instanceof Integer && primitive instanceof ModelicaInteger) {
			((ModelicaInteger) primitive).setValue((Integer) value);
		} else if (value instanceof String && primitive instanceof ModelicaString) {
			((ModelicaString) primitive).setValue((String) value);
		} else if (value instanceof Boolean && primitive instanceof ModelicaBoolean) {
			((ModelicaBoolean) primitive).setValue((Boolean) value);
		} else
			throw new Exception("setPrimitiveValue: Type does not match with Modelica types");
	}

	/**
	 * Gets the min value if the Modelica Property is from the type ModelicaReal
	 * or ModelicaInteger. On default the value is "null".
	 * 
	 * @return Value from type Number and null if the primitive type of the
	 *         called object is not Number or if it is from the type Number but
	 *         the max value is "null"
	 */
	public Number getMinValue() {
		if (primitive instanceof ModelicaReal) {
			return ((ModelicaReal) primitive).getMinValue();
		} else if (primitive instanceof ModelicaInteger) {
			return ((ModelicaInteger) primitive).getMinValue();
		} else
			return null;
	}

	/**
	 * Sets the min value if the Modelica Property is from the type ModelicaReal
	 * or ModelicaInteger. On default the value is "null".
	 * 
	 * @param minValue
	 *            from the type Double or Integer, if the type does not match
	 *            with the type of the PrimitiveDataType the method will do
	 *            nothing
	 */
	public void setMinValue(Number minValue) {
		if(minValue == null){
			if (primitive instanceof ModelicaReal)
				((ModelicaReal) primitive).setMinValue(null);
			else if (primitive instanceof ModelicaInteger)
				((ModelicaInteger) primitive).setMinValue(null);
		} else if (primitive instanceof ModelicaReal && minValue instanceof Double) {
			((ModelicaReal) primitive).setMinValue((Double) minValue);
		} else if (primitive instanceof ModelicaInteger
				&& minValue instanceof Integer) {
			((ModelicaInteger) primitive).setMinValue((Integer) minValue);
		}
	}

	/**
	 * Gets the max value if the Modelica Property is from the type ModelicaReal
	 * or ModelicaInteger. On default the value is "null".
	 * 
	 * @return Value from type Number and null if the primitive type of the
	 *         called object is not Number or if it is from the type Number but
	 *         the max value is "null"
	 */
	public Number getMaxValue() {
		if (primitive instanceof ModelicaReal) {
			return ((ModelicaReal) primitive).getMaxValue();
		} else if (primitive instanceof ModelicaInteger) {
			return ((ModelicaInteger) primitive).getMaxValue();
		} else
			return null;
	}

	/**
	 * Sets the max value if the Modelica Property is from the type ModelicaReal
	 * or ModelicaInteger. On default the value is "null".
	 * 
	 * @param maxValue
	 *            from the type Double or Integer, if the type does not match
	 *            with the type of the PrimitiveDataType the method will do
	 *            nothing
	 */
	public void setMaxValue(Number maxValue) {
		if(maxValue == null){
			if (primitive instanceof ModelicaReal)
				((ModelicaReal) primitive).setMaxValue(null);
			else if (primitive instanceof ModelicaInteger)
				((ModelicaInteger) primitive).setMaxValue(null);
		} else if (primitive instanceof ModelicaReal && maxValue instanceof Double) {
			((ModelicaReal) primitive).setMaxValue((Double) maxValue);
		} else if (primitive instanceof ModelicaInteger
				&& maxValue instanceof Integer) {
			((ModelicaInteger) primitive).setMaxValue((Integer) maxValue);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " Name: " + name + " Comment: " + comment + " Display: " + display + primitive.toString();
	}
}
