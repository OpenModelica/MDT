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
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C17:
 * 	Rule :	UML FunctionBehvaior with ModelicaML stereotype applied can only have one 
 * 			UML Dependency with the stereotype <<PartialDerivativeRelation>> which must 
 * 			point to a UML Function Behavior with the ModelicaML  stereotype 
 * 			Function applied
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C17_PartialDerivationRelationStereotypeForDependencyOfFunctionBehaviorConstraint
		extends AbstractModelConstraint {

	public C17_PartialDerivationRelationStereotypeForDependencyOfFunctionBehaviorConstraint() {
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
			if(eObj instanceof FunctionBehavior) {
				
				FunctionBehavior functionBehavior = (FunctionBehavior) eObj;
				
				if(Utility.isElementHaveModelicaMLStereotypeApplied(functionBehavior)) {
					
					List<Dependency> dependenciesList = functionBehavior.getClientDependencies();
					boolean havingPartialDerivativeRelationDependency = false;
					
					for (Dependency dependency : dependenciesList) {
						
						if(dependency.getAppliedStereotype(Constants.stereotypeQName_PartialDerivativeOfFunctionRelation) != null){ 
							
							if(havingPartialDerivativeRelationDependency == true){
								return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + 
										": FunctionBehavior has more than one UML::Dependency with <<PartialDerivativeRelation>> stereotype applied."});
							}
							else {
								havingPartialDerivativeRelationDependency = true;
								
								List<Element> elementList = dependency.getTargets();
								
								for (Element element : elementList) {
									
									if(element instanceof FunctionBehavior && (element.getAppliedStereotype(Constants.stereotypeQName_Function) == null)){
										return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + 
												": FunctionBehavior having UML::Dependency with <<PartialDerivativeRelation>> stereotype applied " +
												"must point to a FunctionBehavior with <<Function>> stereotype applied."});
									}
								}
							}
						}
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
