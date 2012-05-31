/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C7:
 * 	Rule : A UML Class with a ModelicaML stereotype applied can only contain elements 
 * 			that are instances of: 
 * 				- UML Class
 * 				- UML Enumeration
 * 				- UML PrimitiveType 
 * 				- UML Property
 * 				- UML Port
 * 				- UML Parameter
 * 				- UML  StateMachine 
 * 				- UML Activity
 * 				- UML OpaqueBehavior
 *				- UML FunctionBehavior
 *				- UML Comment
 *				- UML Connector
 *				- UML Constraint
 *				- UML Generalization 
 *				- UML Dependency
 * 				- UML Region
 * 
 * 	Severity : WARNING
 * 
 *	Mode : Live
 */
public class C07_ClassElementsInstanceConstraint extends
AbstractModelConstraint {

	/**
	 * 
	 */
	public C07_ClassElementsInstanceConstraint() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		EObject eObj = ctx.getTarget();


		// Live and Batch Mode
		
		if(eObj instanceof Element && ((Element)eObj).getOwner() instanceof NamedElement)
		{
			Element element = (Element) eObj;
			NamedElement elementOwner = (NamedElement) element.getOwner();

			if(elementOwner != null && elementOwner instanceof Class && Utility.isElementHaveModelicaMLStereotypeApplied(elementOwner))
			{
				if((element instanceof Dependency) || (element instanceof Generalization)) 
				{
					// NOP
				}
				else if((element instanceof Constraint) || (element instanceof Connector))
				{
					// NOP
				}
				else if((element instanceof Comment))
				{
					// NOP
				}
				else if((element instanceof OpaqueBehavior) || (element instanceof FunctionBehavior))
				{
					// NOP
				}
				else if((element instanceof Activity) || (element instanceof StateMachine))
				{
					// NOP
				}
				else if((element instanceof Parameter) || (element instanceof Port))
				{
					// NOP
				}
				else if((element instanceof Property) || (element instanceof PrimitiveType))
				{
					// NOP
				}
				else if((element instanceof Enumeration) || (element instanceof Class))
				{
					// NOP
				}
				else if((element instanceof Region))
				{
					// NOP
				}
				else
				{
					return ctx.createFailureStatus(new Object[] {"Element "+((NamedElement)element).getName()+" cannot be a part of UML Class with any ModelicaML Stereotype."  } );
				}
			}
		}


		return ctx.createSuccessStatus();
	}

}
