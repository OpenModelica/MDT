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
 * Represents a Modelica Variable.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class ModelicaVariable extends ModelicaProperty{

	/**
	 * To be used as default constructor. The interactive variable is set to "false" as default
	 * @param name Variable name
	 * @param type Variable data type
	 * @param comment Designer Comment
	 */
	public ModelicaVariable(String name, String type, String comment) {
		super(name, type, comment);
	}
	
	/**
	 * Creates a copy of an existing Modelica Property to avoid reference problems.
	 *
	 * @param srcModelicaVariable a Modelica Property object to be copied using its primitive values only
	 * @return a copy of the source Modelica Property
	 */
	public static ModelicaProperty createCopyOf(ModelicaProperty srcModelicaVariable){
		ModelicaVariable destModelicaVariable;
		
		destModelicaVariable = new ModelicaVariable(srcModelicaVariable.getName(), srcModelicaVariable.getType(), srcModelicaVariable.getComment());
		destModelicaVariable.setDisplay(srcModelicaVariable.getDisplay());
		
		if ((srcModelicaVariable.getPrimitiveValue() instanceof ModelicaInteger) || (srcModelicaVariable.getPrimitiveValue() instanceof ModelicaReal)){
			destModelicaVariable.setMaxValue(srcModelicaVariable.getMaxValue());
			destModelicaVariable.setMinValue(srcModelicaVariable.getMinValue());
		}
		
		try {
			destModelicaVariable.setPrimitiveValue(srcModelicaVariable.getPrimitiveValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destModelicaVariable;
	}
	
	/* (non-Javadoc)
	 * @see org.openmodelica.simulation.core.models.modelica.ModelicaProperty#toString()
	 */
	public String toString(){
		return " Variable:" + super.toString();
	}
}
