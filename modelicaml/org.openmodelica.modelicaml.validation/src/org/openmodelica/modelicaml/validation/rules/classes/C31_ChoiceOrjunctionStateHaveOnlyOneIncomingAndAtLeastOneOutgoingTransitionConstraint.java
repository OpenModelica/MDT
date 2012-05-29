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

/**	
 * State Machines
 * 
 * C31:
 * 	Rule :	A UML PseudoState of kind choice or junction must have at most one 
 * 			incoming and at least one outgoing transitions.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */
 
public class C31_ChoiceOrjunctionStateHaveOnlyOneIncomingAndAtLeastOneOutgoingTransitionConstraint
		extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C31_ChoiceOrjunctionStateHaveOnlyOneIncomingAndAtLeastOneOutgoingTransitionConstraint() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch Mode
		if(eType == EMFEventType.NULL)
		{
			
			if(eObj instanceof Pseudostate)
			{
				Pseudostate pseudoState = (Pseudostate) eObj;
				
				if(pseudoState.getKind().getValue() == PseudostateKind.CHOICE || pseudoState.getKind().getValue() == PseudostateKind.JUNCTION)
				{
					if(pseudoState.getIncomings().size() > 1 || pseudoState.getOutgoings().size() < 1)
					{
						return ctx.createFailureStatus(new Object[]{ pseudoState.getName()+" state should have atmost one incoming and atleast one outgoing transition."});
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
