/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C33:
 * 	Rule :	A UML Transition to UML PseudoState of kind fork must have UML State as source
 * 			 which must be in the same UML Region.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */
 
public class C33_TransitionToForkFromStateMustBeSameRegionConstraint extends AbstractModelConstraint {

	public C33_TransitionToForkFromStateMustBeSameRegionConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		
			if(eObj instanceof Transition) {
				Transition transition = (Transition) eObj;
				
				if(	transition.getTarget() != null 
						&& transition.getSource() != null && transition.getTarget() instanceof Pseudostate 
						&& ((Pseudostate)transition.getTarget()).getKind().getValue() == PseudostateKind.FORK ) {
					
					State source = (State) transition.getSource();
					Pseudostate target = (Pseudostate) transition.getTarget();
					
					if(source.getOwner() != null && target.getOwner() != null 
							&& source.getOwner() != target.getOwner()) {
						
						return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Transition '" 
								+ transition.getName()+"' from '"+source.getName()+"' to '"+target.getName()+"' fork state must be in same region. "});
					}
				}
			}
		
		return ctx.createSuccessStatus();
	}

}
