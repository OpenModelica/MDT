/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Represents the primitive data type "String" from the Modelica language.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class ModelicaString extends ModelicaPrimitiveDataType{

	/** The value. */
	private String value;
	
	/**
	 * Instantiates a new modelica string.
	 */
	public ModelicaString() {
		super("String");
		value = "";
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * After Reading the XML file with the standard initial values the user has the possibility to change the default value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value){
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see org.openmodelica.simulation.core.models.modelica.ModelicaPrimitiveDataType#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " Value: " + value;
	}
}
