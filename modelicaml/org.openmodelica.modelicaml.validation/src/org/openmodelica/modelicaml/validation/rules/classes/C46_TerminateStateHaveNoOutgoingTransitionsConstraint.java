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
 * C46:
 * 	Rule :	Terminate State may not have outgoing transitions..
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C46_TerminateStateHaveNoOutgoingTransitionsConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C46_TerminateStateHaveNoOutgoingTransitionsConstraint() {
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
			if(eObj instanceof Pseudostate && ((Pseudostate) eObj).getKind().getValue() == PseudostateKind.TERMINATE)
			{
				Pseudostate terminateState = (Pseudostate) eObj;
				
				if(terminateState.getOutgoings().size() > 0)
				{
					return ctx.createFailureStatus(new Object[] {"Terminate State may not have outgoing transitions"});
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
