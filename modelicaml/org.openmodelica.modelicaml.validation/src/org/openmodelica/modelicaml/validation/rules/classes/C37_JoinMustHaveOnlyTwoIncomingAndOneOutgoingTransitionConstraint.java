/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C37:
 * 	Rule :	UML::PseudoState of kind 'Join' must have atleast two incoming and exactly one 
 * 			outgoing transition.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C37_JoinMustHaveOnlyTwoIncomingAndOneOutgoingTransitionConstraint extends AbstractModelConstraint {

	public C37_JoinMustHaveOnlyTwoIncomingAndOneOutgoingTransitionConstraint() {
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
			if(eObj instanceof Pseudostate && ((Pseudostate) eObj).getKind().getValue() == PseudostateKind.JOIN) {
				Pseudostate pseudoState = (Pseudostate) eObj;
				
				if (pseudoState.getIncomings().size() < 2 || pseudoState.getOutgoings().size() != 1) {
					return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + ": " +  
							"Join PseudoState '"+pseudoState.getName()+"' must Have at least two incoming transition and exactly one outgoing transition. "});
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
