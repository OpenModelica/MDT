/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C3:
 * 	Rule : Any UML Class (that is not UML Behavior) must have a 
 * 		   ModelicaML stereotype applied.
 * 
 * 	Severity :	WARNING
 * 
 *	Mode : Batch
 */
public class C03_ClassWithModelicaMLStereotypeConstraint extends
		AbstractModelConstraint {

	public C03_ClassWithModelicaMLStereotypeConstraint() {
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
			
			if(eObj instanceof Class && !(eObj instanceof Behavior)){
				
				NamedElement element = (NamedElement) eObj;
				
				if(!Utility.isElementHaveModelicaMLStereotypeApplied(element)){
					return ctx.createFailureStatus(new Object[]{  Constants.validationKeyWord_MISSING  + ": ModelicaML stereotype for '"+((Class)eObj).getName()+"'."});
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
