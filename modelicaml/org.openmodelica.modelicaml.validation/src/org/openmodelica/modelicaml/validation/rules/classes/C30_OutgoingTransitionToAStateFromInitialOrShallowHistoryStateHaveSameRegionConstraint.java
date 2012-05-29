/**
 * 
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

public class C30_OutgoingTransitionToAStateFromInitialOrShallowHistoryStateHaveSameRegionConstraint
		extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C30_OutgoingTransitionToAStateFromInitialOrShallowHistoryStateHaveSameRegionConstraint() {
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
					
					if(pseudoState.getKind().getValue() == PseudostateKind.INITIAL || pseudoState.getKind().getValue() == PseudostateKind.SHALLOW_HISTORY)
					{
						NamedElement targetElement = transition.getTarget();
						
						if(!(targetElement.getOwner().equals(pseudoState.getOwner())))
						{
							return ctx.createFailureStatus(new Object[] {transition.getName()+" Outgoing transition from the Inital or ShallowHistory State must point to a state in the same Region."});
						}
					}
				}
			}
		
		
		return ctx.createSuccessStatus();
	}

}
