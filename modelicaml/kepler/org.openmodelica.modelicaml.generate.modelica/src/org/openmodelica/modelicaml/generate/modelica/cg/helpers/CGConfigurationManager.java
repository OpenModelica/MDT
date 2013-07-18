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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.generate.modelica.cg.helpers;

import java.util.List;

import org.eclipse.uml2.uml.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class CGConfigurationManager.
 */
public class CGConfigurationManager {
	
	/** The q name. */
	public static String qName = null; // Qualified name of the element (e.g. Class) that the code generator will take as the starting point.
	
	/** The q name list. */
	public static List<String> qNameList = null; // List of qualified names that code shall be regenerated for.
	
	/**
	 * Sets the cG name space.
	 * 
	 * @param newQName
	 *            the new cG name space
	 */
	public static void setCGNameSpace(String newQName) {
		qName = newQName;
	}

	/**
	 * Sets the cG name space list.
	 * 
	 * @param newQNameList
	 *            the new cG name space list
	 */
	public static void setCGNameSpaceList(List<String> newQNameList) {
		qNameList = newQNameList;
	}
	
	/**
	 * Gets the cG name space.
	 * 
	 * @param el
	 *            the el
	 * @return the cG name space
	 */
	public static String getCGNameSpace(Element el) {
		return qName;
	}
	
	/**
	 * Gets the cG name space list.
	 * 
	 * @param el
	 *            the el
	 * @return the cG name space list
	 */
	public static List<String> getCGNameSpaceList(Element el) {
		return qNameList;
	}

}
