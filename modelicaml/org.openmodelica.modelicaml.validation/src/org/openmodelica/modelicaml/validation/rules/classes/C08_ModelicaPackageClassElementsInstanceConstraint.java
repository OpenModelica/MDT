/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C8:
 * 	Rule : A UML Class with a ModelicaML stereotype <<ModelicaPackage>> applied can 
 * 		   only contain elements that are instances of: 
 * 				- UML Class with a ModelicaML stereotype applied
 * 				- UML Enumeration
 * 				- UML PrimitiveType 
 * 				- UML Property with a ModelicaML stereotype <<Variable>> and variability 
 * 					set to constant
 * 				- UML FunctionBehavior with ModelicaML stereotype applied
 * 				- UML Comment
 * 				- UML Generalization with ModelicaML stereotype applied		
 * 				- UML Dependency 
 * 
 * 	Severity : WARNING
 * 
 *	Mode : Live
 */
public class C08_ModelicaPackageClassElementsInstanceConstraint extends
AbstractModelConstraint {

	/**
	 * 
	 */
	public C08_ModelicaPackageClassElementsInstanceConstraint() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse
	 * .emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		EObject eObj = ctx.getTarget();


		// Both In Live and Batch mode
		
		if(eObj instanceof Element)
		{
			Element elementOwner = ((Element) eObj).getOwner();

			if(elementOwner instanceof Class && elementOwner.getAppliedStereotype(Constants.stereotypeQName_ModelicaPackage) != null)
			{
				Element element = (Element) eObj;

				if((element instanceof Dependency) || (element instanceof Comment) || (element instanceof Enumeration) || (element instanceof PrimitiveType))
				{

				}
				else if(element instanceof Generalization || element instanceof FunctionBehavior || element instanceof Class)
				{
					if(Utility.isElementHaveModelicaMLStereotypeApplied(element))
					{
						return ctx.createSuccessStatus();
					}

					return ctx.createFailureStatus(new Object[] { "Element "+((NamedElement)element).getName()+" cannot be a part of ModelicaPackage." });
				}
				else if((element instanceof Property) && (element.getAppliedStereotype(Constants.stereotypeQName_Variable) != null))
				{
					Stereotype property_variable_stereotype = ((Property)element).getAppliedStereotype(Constants.stereotypeQName_Variable);

					Object constant_variable = element.getValue(property_variable_stereotype, Constants.propertyName_variability);
					if(!(((EnumerationLiteral)constant_variable).getName().equals("constant")))
					{
						return ctx.createFailureStatus(new Object[] { "Variable Property "+((NamedElement)element).getName()+" must have variability set to 'constant'" });
					}
				}
				else if(element instanceof Property)
				{
					return ctx.createFailureStatus(new Object[]{" Property in ModelicaPackage must have <<Variable>> Stereotype applied"});
				}
				else {
					return ctx.createFailureStatus(new Object[] {"Element "+((NamedElement)element).getName()+" cannot be a part of ModelicaPackage."  });
				}
			}
		}



		return ctx.createSuccessStatus();
	}

}
