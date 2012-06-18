/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.PrimitiveType;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C18:
 * 	Rule :	Any UML Generalization that is owned by a UML Class with a ModelicaML stereotype 
 * 			applied must have the stereotype <<TypeExtendsRelation>> if it points to a 
 * 			UML Primitive Type instance or  <<ExtendsRelation>> otherwise.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C18_GeneralizationStereotypeConstraint extends
		AbstractModelConstraint {

	public C18_GeneralizationStereotypeConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		 
		EObject eObj  = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch mode
		if(eType == EMFEventType.NULL){
			if( eObj instanceof Generalization){
				Element ownerClass = ((Generalization) eObj).getOwner();
				
				// Check whether Owner element is a UML Class
				if(ownerClass instanceof Class) {
					// Check the Owner Class with ModelicaML stereotype
					if(Utility.isElementHaveModelicaMLStereotypeApplied(ownerClass)) {
						List<Element> targetList = ((Generalization) eObj).getTargets();
						
						for (Element element : targetList) {
							
							if(element instanceof PrimitiveType){
								if((((Generalization) eObj).getAppliedStereotype(Constants.stereotypeQName_TypeRelation) == null)){
									return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + 
											": UML::Generalization of a class pointing to a primitive type must have stereotype <<TypeRelation>> applied."});
								}
							}
							else if(((Generalization) eObj).getAppliedStereotype(Constants.stereotypeQName_ExtendsRelation) == null){
								return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + ": UML::Generalization of a class must have <<ExtendsRelation>> stereotype applied."});
							}
						}
					}
					
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}
