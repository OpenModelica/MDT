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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.common.services;

import java.util.regex.Pattern;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

// TODO: Auto-generated Javadoc
/**
 * The Class StringUtls.
 */
public class StringUtls {

	
	/**
	 * Replace spec char.
	 *
	 * @param orgString the org string
	 * @return the string
	 */
	public static String replaceSpecChar(String orgString) {
		if (Pattern.compile("^[0-9]").matcher(orgString).find()) {
			orgString = "_" + orgString;
		}
		return orgString.replaceAll("\\W", "_");
	}

	/**
	 * Replace spec char except this.
	 *
	 * @param orgString the org string
	 * @param exceptString the except string
	 * @return the string
	 */
	public static String replaceSpecCharExceptThis(String orgString, String exceptString) {
		String newString;
		String patternString = "QQQQQQQQ1234560987QQQQQQ";
		newString = replaceSpecChar(orgString.replaceAll(exceptString,
				patternString));
		return newString.replaceAll(patternString, exceptString);
	}

	
	public static String getDotPath(Element element) {
		String dotPath = null;
		if (element instanceof NamedElement && ((NamedElement)element).getQualifiedName() != null) {
			dotPath = StringUtls.replaceSpecCharExceptThis( ((NamedElement)element).getQualifiedName().replaceAll("::", "."), "\\." );
		}
		return dotPath;
	}
	
	
	/**
	 * Removes the last char.
	 *
	 * @param sourceString the source string
	 * @param findChar the find char
	 * @return the string
	 */
	public static String removeLastChar(String sourceString, String findChar) {
		String string = sourceString.trim();
		if (string!= null && string.length() > 0 && string.substring(string.length() - 1 , string.length()).equals(findChar)) {
			return string.substring(0 , string.length() - 1);
		}
		return string;
	}
	
	/**
	 * Removes the first char.
	 *
	 * @param sourceString the source string
	 * @param findChar the find char
	 * @return the string
	 */
	public static String removeFirstChar(String sourceString, String findChar) {
		String string = sourceString.trim();
		if (string!= null && string.length() > 0 && string.substring(0, 1).equals(findChar)) {
			return string.substring(1 , string.length());
		}
		return string;
	}
	
	/**
	 * Removes the outter braces.
	 *
	 * @param sourceString the source string
	 * @return the string
	 */
	public static String removeOutterBraces(String sourceString) {
		String string = sourceString.trim();
		//System.err.println("string: " + string);
		if (string!= null && string.trim().length() > 0 && string.trim().startsWith("(") ){
			String s1 = string.substring(1 , string.length());
			//System.err.println("s1: " + s1);
			if (s1.trim().length() > 0 && s1.trim().endsWith(")") ){
				//System.err.println("s1 ...: " + s1.substring(0 , s1.length() - 1));
				return s1.substring(0 , s1.length() - 1);
			}
		}
		return string;
	}
	
}
