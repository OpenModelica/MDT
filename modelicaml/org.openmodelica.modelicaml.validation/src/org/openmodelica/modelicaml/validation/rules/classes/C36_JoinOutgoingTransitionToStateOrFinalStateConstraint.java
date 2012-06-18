/**
 * 
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
