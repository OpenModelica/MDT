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
 * C26:
 * 	Rule :	A UML PseudoState of kind initial can have at most one outgoing transition.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C26_InitialStateHaveOnlyOneOutgoingTransitionConstraint extends AbstractModelConstraint {

	public C26_InitialStateHaveOnlyOneOutgoingTransitionConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		if(eType != EMFEventType.NULL) {
			if(ctx.getFeatureNewValue() instanceof EObject) {
				eObj = (EObject) ctx.getFeatureNewValue();
			}
			else {
				return ctx.createSuccessStatus();
			}
		}
		
		if(eObj instanceof Pseudostate) {
			Pseudostate pseudoState = (Pseudostate) eObj;
			
			if(pseudoState.getKind().getValue() == PseudostateKind.INITIAL) {
				if(pseudoState.getOutgoings().size() > 1) {
					return ctx.createFailureStatus(new Object[]{Constants.validationKeyWord_NOT_VALID + ": '" 
							+ pseudoState.getName()+"' initial state can have atmost one outgoing transition."});
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
