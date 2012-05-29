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
 * C42:
 * 	Rule :	ConnectionPointReference referencing an entry point may not have outgoing 
 * 			transition.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C42_ConncectionPointReferenceToEntryPointHaveNoOutgoingTransitionConstraint
		extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C42_ConncectionPointReferenceToEntryPointHaveNoOutgoingTransitionConstraint() {
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
				
				if(reference.getEntries() != null && reference.getEntries().size() >= 1)
				{
					if(reference.getOutgoings() != null && reference.getOutgoings().size() > 0)
					{
						return ctx.createFailureStatus(new Object[] {"ConnectionPoint "+reference.getName()+" referencing an entry point may not have outgoing transition"});
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
