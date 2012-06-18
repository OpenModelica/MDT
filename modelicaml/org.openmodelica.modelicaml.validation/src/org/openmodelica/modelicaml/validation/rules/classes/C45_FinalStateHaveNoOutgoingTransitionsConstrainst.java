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
import org.openmodelica.modelicaml.common.constants.Constants;

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

public class C45_FinalStateHaveNoOutgoingTransitionsConstrainst extends AbstractModelConstraint {

	public C45_FinalStateHaveNoOutgoingTransitionsConstrainst() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch Mode
		if(eType == EMFEventType.NULL) {
			if(eObj instanceof FinalState){
				FinalState finalState  = (FinalState) eObj;
				
				if(finalState.getOutgoings().size() > 0){
					return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID 
							+ ": Final state may not have outgoing transitions"});
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
