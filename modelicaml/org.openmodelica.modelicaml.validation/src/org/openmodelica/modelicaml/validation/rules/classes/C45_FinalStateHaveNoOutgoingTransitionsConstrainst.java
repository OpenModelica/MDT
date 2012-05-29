/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.FinalState;

/**	
 * State Machines
 * 
 * C45:
 * 	Rule :	Final State may not have outgoing transitions..
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C45_FinalStateHaveNoOutgoingTransitionsConstrainst extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C45_FinalStateHaveNoOutgoingTransitionsConstrainst() {
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
			if(eObj instanceof FinalState)
			{
				FinalState finalState  = (FinalState) eObj;
				
				if(finalState.getOutgoings().size() > 0)
				{
					return ctx.createFailureStatus(new Object[] {"Final State may not have outgoing transitions"});
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
