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
package org.openmodelica.modelicaml.validation.rules.classes;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.validation.util.Utility;
import org.openmodelica.modelicaml.validation.util.ValidateAssigmentsInAlgorithmSections;

	/**
	 * C48:
	 * 	Rule : A variable can be assigned only in one algorithm section.
	 * 	Severity : ERROR
	 * 
	 *	Mode : Batch
	 */
	public class C48_SingleAssignmentRule extends AbstractModelConstraint {

		
	public C48_SingleAssignmentRule() {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();

		// Live and Batch Mode
		if(eObj instanceof Element && ((Element)eObj).getOwner() instanceof NamedElement){
			
			Element element = (Element) eObj;

			if(element instanceof Class && Utility.isElementHaveModelicaMLStereotypeApplied(element)) {
				
				Class clazz = (Class)element;
				ValidateAssigmentsInAlgorithmSections v = new ValidateAssigmentsInAlgorithmSections();
				List<String> duplicates = v.getVariableWithMultipleAssignementInAlgorithmSections(clazz);
				
				if (duplicates == null) {
					return ctx.createSuccessStatus();
				}
				else {
					String msg = "";
					String vars = "";
					// Creates message 
					for (Object object : duplicates) {
						vars += object.toString() + ",";
					}
					
					msg = "Variable(s) "+ "'" + vars.trim().substring(0, vars.trim().length() - 1)+ "' " +
							"are set in multiple algorithm sections, state machine(s) or activities." +
							"'"+element.eClass().getName()+"' '" + ((NamedElement)element).getName()+"' violates the Modelica single assigment rule." +
							"Note that redeclarations are not taken into account in this analysis.";

					return ctx.createFailureStatus(new Object[] {msg} );
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}