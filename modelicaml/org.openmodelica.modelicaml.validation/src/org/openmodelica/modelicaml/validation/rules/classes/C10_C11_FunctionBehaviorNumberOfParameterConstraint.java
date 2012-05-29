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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * C10:
 * 	Rule :  A UML Parameter that is owned by a UML FunctionBehavior with the stereotype 
 * 			ModelicaML <<Function>>  applied must have its causality set to 
 * 			input or output. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 *
 * C11:
 * 	Rule :	A UML FunctionBehavior  with the ModelicaML stereotype <<Function>>  applied 
 * 		   	must have at least one UML Parameter with causality input and exactly 
 * 			one UML Parameter with the causality output. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C10_C11_FunctionBehaviorNumberOfParameterConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C10_C11_FunctionBehaviorNumberOfParameterConstraint() {
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
		
		
		
		
			if(eType != EMFEventType.NULL) // In Live Mode
			{
				if(eObj instanceof Parameter)
				{
					eObj = ((Parameter) eObj).getOwner();
				}
				else
				{
					return ctx.createSuccessStatus();
				}
			}
			
			if((eObj instanceof FunctionBehavior) && ((((FunctionBehavior)eObj).getAppliedStereotype(Constants.stereotypeQName_Function)) != null))
			{
				FunctionBehavior functionBehavior = (FunctionBehavior) eObj;
				List<Element> functionBehaviorElements = functionBehavior.allOwnedElements();
				boolean atleastOneInput = false;
				boolean atMostOneOutput = false;
				for (Element element : functionBehaviorElements) {
					
					if(element instanceof Parameter && (element.getAppliedStereotype(Constants.stereotypeQName_FunctionArgument) != null))
					{
						Stereotype parameterStereotype = element.getAppliedStereotype(Constants.stereotypeQName_FunctionArgument);
						String causalityValue = ((EnumerationLiteral)element.getValue(parameterStereotype, Constants.propertyName_causality)).getName();
						
						if(causalityValue.equalsIgnoreCase("input"))
						{
							atleastOneInput = true;
						}
						else if(causalityValue.equalsIgnoreCase("output"))
						{
							if(atMostOneOutput == true)
							{
								return ctx.createFailureStatus(new Object[]{ "Function "+ ((FunctionBehavior)eObj).getName() +" have more than one Parameter's causality set to 'output'"});
							}
							else if(atMostOneOutput == false)
							{
								atMostOneOutput = true;
							}
						}
						else
						{
							return ctx.createFailureStatus(new Object[]{ "Parameter "+ ((Parameter)element).getName() +" must have its causality set to 'input' or 'output'"});
						}
						
					}
				}
				if(atleastOneInput == false)
				{
					return ctx.createFailureStatus(new Object[]{  "Function "+ ((FunctionBehavior)eObj).getName() +" should have atleast one Parameter's causality set to 'input'"});
				}
			}
	
		return ctx.createSuccessStatus();
	}

}
