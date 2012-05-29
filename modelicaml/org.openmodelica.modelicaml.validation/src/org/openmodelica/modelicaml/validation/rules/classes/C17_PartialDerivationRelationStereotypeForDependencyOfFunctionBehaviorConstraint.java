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

	/**
	 * 
	 */
	public C17_PartialDerivationRelationStereotypeForDependencyOfFunctionBehaviorConstraint() {
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
			if(eObj instanceof FunctionBehavior)
			{
				
				FunctionBehavior functionBehavior = (FunctionBehavior) eObj;
				
				if(Utility.isElementHaveModelicaMLStereotypeApplied(functionBehavior))
				{
					List<Dependency> dependenciesList = functionBehavior.getClientDependencies();
					boolean havingPartialDerivativeRelationDependency = false;

					
					for (Dependency dependency : dependenciesList) {
						
						if(dependency.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::PartialDerivativeOfFunctionRelation") != null)
						{
							
							
							if(havingPartialDerivativeRelationDependency == true)
							{
								return ctx.createFailureStatus(new Object[] {"FunctionBehavior have more than one UML dependency with PartialDerivativeRelation Stereotype"});
							}
							else
							{
								havingPartialDerivativeRelationDependency = true;
								
								List<Element> elementList = dependency.getTargets();
								
								for (Element element : elementList) {
									
									if(element instanceof FunctionBehavior && (element.getAppliedStereotype(Constants.stereotypeQName_Function) == null))
									{
										return ctx.createFailureStatus(new Object[] {"FunctionBehavior having UML dependency with PartialDerivativeRelation Stereotype must point to FunctionBehavior with Function Stereotype"});
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
