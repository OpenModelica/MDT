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
 
public class C33_TransitionToForkFromStateMustBeSameRegionConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C33_TransitionToForkFromStateMustBeSameRegionConstraint() {
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
				
				if(transition.getTarget() != null && transition.getSource() != null && transition.getTarget() instanceof Pseudostate && ((Pseudostate)transition.getTarget()).getKind().getValue() == PseudostateKind.FORK)
				{
					State source = (State) transition.getSource();
					Pseudostate target = (Pseudostate) transition.getTarget();
					
					if(source.getOwner() != null && target.getOwner() != null && source.getOwner() != target.getOwner())
					{
						return ctx.createFailureStatus(new Object[] { transition.getName()+" transition from "+source.getName()+" state to "+target.getName()+" fork state must be in same region"});
					}
				}
			}
		
		
		return ctx.createSuccessStatus();
	}

}
