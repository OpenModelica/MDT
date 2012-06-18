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
import org.eclipse.uml2.uml.Dependency;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * C16:
 * 	Rule :	UML Class with ModelicaML stereotype applied can only have one UML Dependency 
 * 			with the stereotype <<ConstrainedByRElation>>. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C16_ConstrainedByRelationStereotypeForDependencyOfClassConstraint
		extends AbstractModelConstraint {

	public C16_ConstrainedByRelationStereotypeForDependencyOfClassConstraint() {
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
			
			C03_ClassWithModelicaMLStereotypeConstraint classWithModelicaMLStereotypeConstraint = new C03_ClassWithModelicaMLStereotypeConstraint();
			
			if((eObj instanceof Class) && (classWithModelicaMLStereotypeConstraint.validate(ctx).isOK())) {
			
				Class clas = (Class) eObj;
				List<Dependency> classDependencies = clas.getClientDependencies();
				boolean havingOnlyOneConstrainedByRelationStereotype = false;
				
				for (Dependency dependency : classDependencies) {
					
					if(dependency.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::ConstrainedByRelation") != null) { 
						
						if(havingOnlyOneConstrainedByRelationStereotype == true) {
							havingOnlyOneConstrainedByRelationStereotype = false;
							return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + ":  '"
									+clas.getName()+"' can have only one UML::Dependency with <<ConstrainedByRelation>> stereotype applied."} );
						}
						else{
							havingOnlyOneConstrainedByRelationStereotype = true;
						}
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
