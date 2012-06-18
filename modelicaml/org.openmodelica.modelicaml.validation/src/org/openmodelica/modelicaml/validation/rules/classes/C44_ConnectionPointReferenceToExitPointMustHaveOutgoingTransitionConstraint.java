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
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C44:
 * 	Rule :	ConnectionPointReference referencing an exit point must have an outgoing
 * 			transition.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C44_ConnectionPointReferenceToExitPointMustHaveOutgoingTransitionConstraint extends AbstractModelConstraint {

	public C44_ConnectionPointReferenceToExitPointMustHaveOutgoingTransitionConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch Mode
		if(eType == EMFEventType.NULL){
			if(eObj instanceof ConnectionPointReference){
				ConnectionPointReference reference = (ConnectionPointReference) eObj;
				
				if(reference.getExits() != null && reference.getExits().size() >= 1){
					if(reference.getOutgoings()== null || reference.getOutgoings().size() < 1){
						return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + 
								": ConnectionPoint '"+reference.getName()+"' referencing an exit point must have an outgoing transition."});
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
