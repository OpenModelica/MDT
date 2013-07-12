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
package org.openmodelica.modelicaml.helper.generators;

import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.StringUtls;

public class InstantiatorScenarios {
	
	final static String propertyPrefix = Constants.verificationScenarioPropertyNamePrefix;
//	private HashSet<Element> instantiatedElements = new HashSet<Element>();
	
	public EList<Property> instantiateTestScenarios(Class containingClass, HashSet<Element> testScenarioClasses){
		
		EList<Property> instantiatedTestScenarios = new BasicEList<Property>();
		
		for (Element tsClass : testScenarioClasses) {
			if (tsClass instanceof Class) {
				Property pAdded = instantiateTestScenario(containingClass, (Class) tsClass);
				if (pAdded != null) {
					instantiatedTestScenarios.add(pAdded);
				}
			}
		}
		
		return instantiatedTestScenarios;
	}

	public Property instantiateTestScenario(Class containingClass, Class tsClass){
		EList<Property> pList = containingClass.getAllAttributes();
		int numberOfReqInstancesWithSameType = 0;
		String prefix = propertyPrefix;
		for (Property property : pList) {
			String pName = StringUtls.replaceSpecChar(property.getName());
			if (pName.substring(0, pName.length() - 2).startsWith(prefix + StringUtls.replaceSpecChar(tsClass.getName()).toLowerCase()) ) {
				numberOfReqInstancesWithSameType ++; 
			}
		}
		Integer postfix = numberOfReqInstancesWithSameType + 1;
		String postfixString = "_" + postfix.toString();
		
		// create Property
		Property p = containingClass.createOwnedAttribute(prefix + StringUtls.replaceSpecChar(tsClass.getName()).toLowerCase() + postfixString, tsClass);
		// apply stereotype
		Stereotype s = p.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);
//		Stereotype s_testScenario = p.getApplicableStereotype(Constants.stereotypeQName_TestScenario);
		
//		if (s != null && s_testScenario!= null) {
		if (s != null) {
			p.applyStereotype(s);
//			p.applyStereotype(s_testScenario);
			return p; 
		}
		else {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error:", "Cannot apply ModelicaML stereotype to " + p.getName() + ". Please make sure that ModelicaML profile is applied to the top-level model/package.");
		}
		return null;
	}
   
}
