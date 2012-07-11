/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C5:
 * 	Rule : Any UML OpaqueBehavior that is owned by a UML Class with a ModelicaML stereotype 
 * 		   applied must have either the stereotype <<Equation>> or <<Algorithm>> applied.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C05_OpaqueBehaviorStereotypeConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C05_OpaqueBehaviorStereotypeConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch mode
		if(eType == EMFEventType.NULL){
			
			if( eObj instanceof OpaqueBehavior && !(eObj instanceof FunctionBehavior)){

				Element element = ((OpaqueBehavior) eObj).getOwner();
				if( element instanceof Class ) {
					if(Utility.isElementHaveModelicaMLStereotypeApplied(element)){
						if( (((OpaqueBehavior) eObj).getAppliedStereotype(Constants.stereotypeQName_Equations) == null) 
								&& (((OpaqueBehavior)eObj).getAppliedStereotype(Constants.stereotypeQName_Algorithm) == null) ) {
							
							return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_MISSING + ": Stereotype (<<Equation>> or <<Algorithm>>) for OpaqueBehavior '"
								+((OpaqueBehavior) eObj).getName()+"' owned by '"+((Class) element).getName()+"'." });
						}
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
