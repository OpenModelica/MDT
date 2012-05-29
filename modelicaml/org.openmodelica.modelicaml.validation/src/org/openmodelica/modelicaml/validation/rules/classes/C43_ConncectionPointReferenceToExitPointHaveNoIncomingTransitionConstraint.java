/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.ConnectionPointReference;

/**	
 * State Machines
 * 
 * C43:
 * 	Rule :	ConnectionPointReference referencing an exit point may not have incoming 
 * 			transition.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C43_ConncectionPointReferenceToExitPointHaveNoIncomingTransitionConstraint
		extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C43_ConncectionPointReferenceToExitPointHaveNoIncomingTransitionConstraint() {
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
			if(eObj instanceof ConnectionPointReference)
			{
				ConnectionPointReference reference = (ConnectionPointReference) eObj;
				
				if(reference.getExits() != null && reference.getExits().size() <= 1)
				{
					if(reference.getIncomings() != null && reference.getIncomings().size() > 0)
					{
						return ctx.createFailureStatus(new Object[] {"ConnectionPoint "+reference.getName()+" referencing an exit point may not have incoming transition"});
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
