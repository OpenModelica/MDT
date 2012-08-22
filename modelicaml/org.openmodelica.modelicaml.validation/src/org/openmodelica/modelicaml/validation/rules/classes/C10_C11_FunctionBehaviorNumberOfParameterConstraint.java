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
package org.openmodelica.modelicaml.validation.rules.classes;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * C10:
 * 	Rule :  A UML Parameter that is owned by a UML FunctionBehavior with the stereotype 
 * 			ModelicaML <<Function>>  applied must have its causality set to 
 * 			input or output. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 *
 * C11:
 * 	Rule :	A UML FunctionBehavior  with the ModelicaML stereotype <<Function>>  applied 
 * 		   	must have at least one UML Parameter with causality input and at least one 
 * 			UML::Parameter with the causality output. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C10_C11_FunctionBehaviorNumberOfParameterConstraint extends
		AbstractModelConstraint {

	public C10_C11_FunctionBehaviorNumberOfParameterConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Live Mode
		if(eType != EMFEventType.NULL) {
			if(eObj instanceof Parameter) {
				eObj = ((Parameter) eObj).getOwner();
			}
			else {
				return ctx.createSuccessStatus();
			}
		}
		
		if((eObj instanceof FunctionBehavior) && ((((FunctionBehavior)eObj).getAppliedStereotype(Constants.stereotypeQName_Function)) != null)){
			
			FunctionBehavior functionBehavior = (FunctionBehavior) eObj;
			Stereotype functionStereotype = functionBehavior.getAppliedStereotype(Constants.stereotypeQName_FunctionArgument);
			
			if (functionStereotype != null) {
				boolean isPartial =  (Boolean) functionBehavior.getValue(functionStereotype, Constants.propertyName_partial);
				if (isPartial) {
					/*
					 * a partial function can be "empty"
					 */
					return ctx.createSuccessStatus();
				}
			}

			
			List<Element> functionBehaviorElements = functionBehavior.allOwnedElements();
			
			/*
			 * TODO: Get all inherited attributes. For that we need to search through all inheritance relations ... 
			 * 
			 * This is a workaround. The indicator is used to assume that 
			 * the function inherits from another function which in turn will 
			 * have inputs and outouts. 
			 */
			boolean hasExtendsRelation = functionBehavior.getGeneralizations().size() >  0;
			
			
			boolean atleastOneInput = false;
			boolean atleastOneOutput= false;
//			boolean atMostOneOutput = false;
			for (Element element : functionBehaviorElements) {
				
				if(element instanceof Parameter && (element.getAppliedStereotype(Constants.stereotypeQName_FunctionArgument) != null)){
					
					Stereotype parameterStereotype = element.getAppliedStereotype(Constants.stereotypeQName_FunctionArgument);
					String causalityValue = ((EnumerationLiteral)element.getValue(parameterStereotype, Constants.propertyName_causality)).getName();
					
					
					
					if(causalityValue.equalsIgnoreCase("input")){
						atleastOneInput = true;
					}
					else if(causalityValue.equalsIgnoreCase("output")){
						atleastOneOutput = true;
						
//						if(atMostOneOutput == true && !hasExtendsRelation){
//							return ctx.createFailureStatus(new Object[]{ Constants.validationKeyWord_NOT_VALID + ": Function '"
//									+ ((FunctionBehavior)eObj).getName() +"' has more than one variables with causality set to 'output'."});
//						}
//						else if(atMostOneOutput == false){
//							atMostOneOutput = true;
//						}
					}
					else {
						if (!hasExtendsRelation) {
							return ctx.createFailureStatus(new Object[]{ "Parameter "+ ((Parameter)element).getName() +" must have its causality set to 'input' or 'output'"});
						}
					}
				}
			}
			
			if(atleastOneInput == false && !hasExtendsRelation){
				return ctx.createFailureStatus(new Object[]{  "Function "+ ((FunctionBehavior)eObj).getName() +" should have atleast one parameter's causality set to 'input'"});
			}
			
			if (atleastOneOutput == false && !hasExtendsRelation){
				return ctx.createFailureStatus(new Object[]{  "Function "+ ((FunctionBehavior)eObj).getName() +" should have atleast one parameter's causality set to 'output'"});
			}
		}
	
		return ctx.createSuccessStatus();
	}

}
