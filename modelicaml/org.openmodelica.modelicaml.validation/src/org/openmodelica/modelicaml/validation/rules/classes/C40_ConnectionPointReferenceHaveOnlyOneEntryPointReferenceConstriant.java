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
 * C40:
 * 	Rule :	ConnectionPointReference may only refer one entry point.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C40_ConnectionPointReferenceHaveOnlyOneEntryPointReferenceConstriant extends AbstractModelConstraint {

	public C40_ConnectionPointReferenceHaveOnlyOneEntryPointReferenceConstriant() {
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
			if(eObj instanceof ConnectionPointReference){
				ConnectionPointReference reference  = (ConnectionPointReference) eObj;
				
				if(reference.getEntries().size() > 1){
					return ctx.createFailureStatus(new Object[]{ Constants.validationKeyWord_NOT_VALID + 
							": ConnectionPointReference '"+reference.getName()+"' may only reference one entryPoint. "});
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
