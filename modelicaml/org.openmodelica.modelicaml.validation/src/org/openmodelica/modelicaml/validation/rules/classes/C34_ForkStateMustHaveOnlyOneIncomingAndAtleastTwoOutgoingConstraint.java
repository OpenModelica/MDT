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
 * C34:
 * 	Rule :	 A UML Transition to UML PseudoState of kind fork must have at least 
 * 			two outgoing UML Transition and at most one incoming UML Transition.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C34_ForkStateMustHaveOnlyOneIncomingAndAtleastTwoOutgoingConstraint extends AbstractModelConstraint {

	public C34_ForkStateMustHaveOnlyOneIncomingAndAtleastTwoOutgoingConstraint() {
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
				
				if(pseudoState.getKind().getValue() == PseudostateKind.FORK) {
					if(pseudoState.getOutgoings().size() < 2 || pseudoState.getIncomings().size() > 1) {
						return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Fork '" 
								+ pseudoState.getName()+"' must have at least two outgoing and at most one incoming transitions."});
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
