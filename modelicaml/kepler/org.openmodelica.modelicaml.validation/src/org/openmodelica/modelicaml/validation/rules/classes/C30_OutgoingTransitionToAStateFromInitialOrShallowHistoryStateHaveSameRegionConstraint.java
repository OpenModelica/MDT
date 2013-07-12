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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Transition;
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C30:
 * 	Rule :	The outgoing transition from the UML PsuedoState of kind initial or 
 * 			shallowHistory node must point to a state in that is in the same UML Region.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C30_OutgoingTransitionToAStateFromInitialOrShallowHistoryStateHaveSameRegionConstraint extends AbstractModelConstraint {


	public C30_OutgoingTransitionToAStateFromInitialOrShallowHistoryStateHaveSameRegionConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		
		EObject eObj = ctx.getTarget();
		
		if(eObj instanceof Transition){
			
			Transition transition = (Transition) eObj;
			
			if(transition.getSource() instanceof Pseudostate) {
				Pseudostate pseudoState = (Pseudostate) transition.getSource();
				
				if(pseudoState.getKind().getValue() == PseudostateKind.INITIAL || pseudoState.getKind().getValue() == PseudostateKind.SHALLOW_HISTORY) {
					NamedElement targetElement = transition.getTarget();
					
					if(!(targetElement.getOwner().equals(pseudoState.getOwner()))){
						return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID +": Outgoing transition '" 
								+ transition.getName()+"' from the inital or shallow history state must point to a state in the same region."});
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
