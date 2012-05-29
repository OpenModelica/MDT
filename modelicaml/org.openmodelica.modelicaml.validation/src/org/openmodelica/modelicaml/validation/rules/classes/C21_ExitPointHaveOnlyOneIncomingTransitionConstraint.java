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
 * C21:
 * 	Rule :	UML PseudoState with kind exitPoints can have at most one incoming transition.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C21_ExitPointHaveOnlyOneIncomingTransitionConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C21_ExitPointHaveOnlyOneIncomingTransitionConstraint() {
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
		
		
		
			if(eType != EMFEventType.NULL)
			{
				if(ctx.getFeatureNewValue() instanceof EObject)
				{
					eObj = (EObject) ctx.getFeatureNewValue();
				}
				else
				{
					return ctx.createSuccessStatus();
				}
			}
			
			if(eObj instanceof Pseudostate)
			{
				Pseudostate pseudoState = (Pseudostate) eObj;
				
				if(pseudoState.getKind().getValue() == PseudostateKind.EXIT_POINT)
				{
					if(pseudoState.getIncomings().size() > 1)
					{
						return ctx.createFailureStatus(new Object[]{pseudoState.getName()+" exitPoint State cannot have more than one incoming transitions"}); 
					}
				}
			}
		
		
		return ctx.createSuccessStatus();
	}

}
