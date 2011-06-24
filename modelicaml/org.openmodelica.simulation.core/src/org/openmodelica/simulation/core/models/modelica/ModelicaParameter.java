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
 * Represents a Modelica parameter.
 * Unlike a variable type, the parameter can be modified interactively during a simulation  
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class ModelicaParameter extends ModelicaProperty{

	/** The interactive. */
	private Boolean interactive;

	/**
	 * To be used as default constructor. The interactive variable is set to "false" as default
	 * @param name Parameter name
	 * @param type Parameter data type
	 * @param comment Designer Comment
	 */
	public ModelicaParameter(String name, String type, String comment) {
		super(name, type, comment);
		interactive = false;
	}

	/**
	 * A parameter can be modified interactively during a simulation. 
	 * @return true if the parameter can be modified interactively during a simulation, otherwise none
	 */
	public Boolean getInteractive() {
		return interactive;
	}

	/**
	 * Sets the interactive.
	 *
	 * @param interactive the new interactive
	 */
	public void setInteractive(Boolean interactive) {
		this.interactive = interactive;
	}
	
	/**
	 * Creates a copy of an existing Modelica Property to avoid reference problems.
	 *
	 * @param srcModelicaParameter the src modelica parameter
	 * @return a copy of the source Modelica Property
	 */
	public static ModelicaProperty createCopyOf(ModelicaProperty srcModelicaParameter){
		ModelicaParameter destModelicaParameter;
		
		destModelicaParameter = new ModelicaParameter(srcModelicaParameter.getName(), srcModelicaParameter.getType(), srcModelicaParameter.getComment());
		destModelicaParameter.setDisplay(srcModelicaParameter.getDisplay());
		destModelicaParameter.setInteractive(((ModelicaParameter)srcModelicaParameter).getInteractive());

		if ((srcModelicaParameter.getPrimitiveValue() instanceof ModelicaInteger) || (srcModelicaParameter.getPrimitiveValue() instanceof ModelicaReal)){
			destModelicaParameter.setMaxValue(srcModelicaParameter.getMaxValue());
			destModelicaParameter.setMinValue(srcModelicaParameter.getMinValue());
		}
	
		try {
			destModelicaParameter.setPrimitiveValue(srcModelicaParameter.getPrimitiveValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destModelicaParameter;
	}
	
	/* (non-Javadoc)
	 * @see org.openmodelica.simulation.core.models.modelica.ModelicaProperty#toString()
	 */
	@Override
	public String toString() {
		return " Parameter:" + super.toString() + " Interactive: " + interactive;
	}
}
