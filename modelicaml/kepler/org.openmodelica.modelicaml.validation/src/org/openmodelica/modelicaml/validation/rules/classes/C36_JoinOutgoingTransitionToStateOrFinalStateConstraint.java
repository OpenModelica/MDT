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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C36:
 * 	Rule :	UML::Transition from a UML::PseudoState of kind 'fork' must
 *			have target as state or final state.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C36_JoinOutgoingTransitionToStateOrFinalStateConstraint extends AbstractModelConstraint {

	public C36_JoinOutgoingTransitionToStateOrFinalStateConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();
		EMFEventType eType =  ctx.getEventType();
		
		// In Batch Mode
		if(eType == EMFEventType.NULL) {

			if(eObj instanceof Transition) {
				if(((Transition) eObj).getSource() instanceof Pseudostate 
						&& ((Pseudostate) ((Transition) eObj).getSource()).getKind().getValue() == PseudostateKind.JOIN) {
					
					Transition transition = (Transition) eObj;
					
					if((transition.getTarget() instanceof State) || (transition.getTarget() instanceof FinalState)){
						// NOP
					}
					else{
						return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Transition " + 
								transition.getName()+" from join must lead to a state or a final state."});
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
