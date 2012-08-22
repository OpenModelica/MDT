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
package org.openmodelica.modelicaml.view.valuebindings.helpers;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

public class DeriveValueBindingCodeUtls {

	public static boolean isValidClientScript(String scriptCode){
		if (scriptCode != null) {
//			if ( scriptCode.matches("(.+)getBinding\\((\\s+)?\\)(.+)?") && !hasMediatorBindingScriptFunctions(scriptCode)) {
//				return true;
//			}
			if ( scriptCode.contains(Constants.MACRO_getBinding + "(")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidMediatorMultipleItemsScript(String scriptCode){
		if (scriptCode != null) {
			if ( scriptCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?") && !scriptCode.contains(Constants.MACRO_getSingleProvider) ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidMediatorSingleItemsScript(String scriptCode){
		if (scriptCode != null) {
			if ( !scriptCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?") && scriptCode.contains(Constants.MACRO_getSingleProvider) ) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean hasMediatorBindingScriptFunctions(String scriptCode){
		if (scriptCode != null && !scriptCode.trim().equals("")) {
			// any array reduction function
			if ( scriptCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?")) {
				return true;
			}
			if (scriptCode.contains(Constants.MACRO_getSingleProvider+ "(") ) {
				return true;
			}
		}
		return false;
	}
	
	
	public static String getOperationSpecification(Element element, String stereotypeQName, String propertyName){
		if (element != null) {
			Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
			if (stereotype != null) {
				Object o = element.getValue(stereotype, propertyName);
				if (o instanceof String) {
					if ( !((String)o).trim().equals("") ) {
						return (String)o;
					}
				}
			}
		}			
		return null;
	}
}
