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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.gen.modelica.uml2modelica.services;

import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class UML2ModelicaServices.
 */
public class UML2ModelicaServices {

	/**
	 * Replaces all non-word characters with a underline and the first character
	 * if its a digit.
	 * 
	 * @param orgString
	 *            the org string
	 * @return modified String with any non-word character
	 */

	public String replaceSpecChar(String orgString) {

		if (Pattern.compile("^[0-9]").matcher(orgString).find()) {

			orgString = "_" + orgString;

		}

		return orgString.replaceAll("\\W", "_");

	}

	/**
	 * Replace spec char except this.
	 * 
	 * @param orgString
	 *            the org string
	 * @param exceptString
	 *            the except string
	 * @return the string
	 */
	public String replaceSpecCharExceptThis(String orgString,
			String exceptString) {
		String newString;
		String patternString = "QQQQQQQQ1234560987QQQQQQ";

		newString = replaceSpecChar(orgString.replaceAll(exceptString,
				patternString));

		return newString.replaceAll(patternString, exceptString);

	}

}
