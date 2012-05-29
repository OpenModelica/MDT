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
import org.eclipse.uml2.uml.Transition;

/**	
 * State Machines
 * 
 * C27:
 * 	Rule :	Outgoing transition of A UML PseudoState of kind initial must not have a 
 * 			guard.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C27_NoGuardForOutgoingTransitionOfInitialStateConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C27_NoGuardForOutgoingTransitionOfInitialStateConstraint() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		
 		EObject eObj = ctx.getTarget();
		
	
		
		
			
			if(eObj instanceof Transition)
			{
				Transition transition = (Transition) eObj;
				
				if(transition.getSource() instanceof Pseudostate)
				{
					Pseudostate pseudoState = (Pseudostate) transition.getSource();
					
					if(pseudoState.getKind().getValue() == PseudostateKind.INITIAL)
					{
						if(transition.getGuard() != null)
						{
							return ctx.createFailureStatus(new Object[]{transition.getName()+" is an Outgoing transition of Initial State must not have a guard."});
						}
					}
				}
			}
		
		
		return ctx.createSuccessStatus();
	}

}
