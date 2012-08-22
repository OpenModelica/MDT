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
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Transition;
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C32:
 * 	Rule :	A UML PseudoState of kind choice or junction should have one else branch.
 * 
 * 	Severity : WARNING
 * 
 *	Mode : Batch
 */

public class C32_ChoiceOrJunctionStateShouldHaveOneElseBranchConstraint extends AbstractModelConstraint {

	public C32_ChoiceOrJunctionStateShouldHaveOneElseBranchConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		 
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch Mode
		if(eType == EMFEventType.NULL) {
			
			if(eObj instanceof Pseudostate) {
				Pseudostate pseudoState = (Pseudostate) eObj;
				
				if(pseudoState.getKind().getValue() == PseudostateKind.CHOICE || pseudoState.getKind().getValue() == PseudostateKind.JUNCTION) {
					List<Transition> outgoingTransitionList = pseudoState.getOutgoings();
					
					boolean isElseExpressionExists = false;
					
					for (Transition transition : outgoingTransitionList) {
						
						Constraint constraint = transition.getGuard();
						
						if(constraint != null && constraint.getSpecification() != null && constraint.getSpecification() instanceof OpaqueExpression) {
							OpaqueExpression expression = (OpaqueExpression) constraint.getSpecification();
							
							List<String> expressionBody = expression.getBodies();
							
							if(expressionBody != null) {
								if(isElseExpressionExists == false && expressionBody.contains("else")) {
									isElseExpressionExists = true;
								}
							}
						}
					}
					
					if(isElseExpressionExists == false) {
						return ctx.createFailureStatus(new Object[]{ Constants.validationKeyWord_NOT_VALID 
								+ ": State '" + pseudoState.getName()+"' should have one else branch."});
					}
				}
			}
		}
	
		return ctx.createSuccessStatus();

	}

}
