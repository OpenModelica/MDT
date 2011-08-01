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
 * This Class represents Modelica input variables.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 */
public class ModelicaInputVariable extends ModelicaProperty{

	/** The interactive. */
	private Boolean interactive;

	/**
	 * Instantiates a new modelica input variable.
	 *
	 * @param name the name
	 * @param type the type
	 * @param comment the comment
	 */
	public ModelicaInputVariable(String name, String type, String comment) {
		super(name, type, comment);
		interactive = false;
	}

	/**
	 * Gets the interactive.
	 *
	 * @return the interactive
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
	
	/* (non-Javadoc)
	 * @see org.openmodelica.simulation.core.models.modelica.ModelicaProperty#toString()
	 */
	@Override
	public String toString() {
		return " InputVariable:" + super.toString() + " Interactive: " + interactive;
	}
}
