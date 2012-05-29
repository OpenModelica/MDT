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
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C4:
 * 	Rule : Any UML Property that is owned by a UML Class with a ModelicaML stereotype 
 * 		   applied must have a ModelicaML Stereotype applied as follows:
 * 				- If the type of the UML Property is of UML PrimitiveType then the stereotype must be <<Variable>>
 * 				- Else if the type of the UML Property has the stereotype <<Requirement>> then the stereotype must be <<RequirementInstance>>
 * 				- Else if the type of the UML Property has the stereotype <<CalculationModel>> then the stereotype must be <<CalculatedProperty>>
 * 				- Else if the type of the UML Property is not an instance of UML PrimitiveType then the stereotype must be <<Component>>
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C04_ClassPropertyStereotypeConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C04_ClassPropertyStereotypeConstraint() {
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
		
		// In Batch mode
		if(eType == EMFEventType.NULL)
		{
			
			if(eObj instanceof Property && ((Property) eObj).getOwner() instanceof Class)
			{
				Class clas = (Class) ((Property) eObj).getOwner();
				if(Utility.isElementHaveModelicaMLStereotypeApplied(clas))
				{
					Property property = (Property) eObj;
					Type propertyType = property.getType();
					
					
					if((propertyType != null) && (propertyType.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null))
					{
						if(property.getAppliedStereotype(Constants.stereotypeQName_RequirementInstance) == null)
						{
							return ctx.createFailureStatus(new Object[] { "Property "+((NamedElement)eObj).getName()+" of type with <<Requirement>> stereotype must have <<RequirementInstance>> stereotype applied." });
						}
					}
					else if((propertyType != null) && (propertyType.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null))
					{
						if(property.getAppliedStereotype(Constants.stereotypeQName_CalculatedProperty) == null)
						{
							return ctx.createFailureStatus(new Object[]{ "Property "+((NamedElement)eObj).getName()+" of type with <<CalculationModel>> stereotype must have <<CalculatedProperty>> stereotype applied." });
						}
					}
					else if(propertyType instanceof PrimitiveType)
					{
						if(property.getAppliedStereotype(Constants.stereotypeQName_Variable) == null)
						{
							return ctx.createFailureStatus(new Object[]{ "Property "+((NamedElement)eObj).getName()+" of type which is instance of UML PrimitiveType must have <<variable>> stereotype applied." });	
						}
					}
					else if(!(propertyType instanceof PrimitiveType) )
					{
						if(property.getAppliedStereotype(Constants.stereotypeQName_Component) == null)
						{
							return ctx.createFailureStatus(new Object[]{ "Property "+((NamedElement)eObj).getName()+" of type which is not an instance of UML PrimitiveType must have <<Component>> stereotype applied." });
						}
					}
					
				}
				
			}
			
		}
		
		return ctx.createSuccessStatus();
	}

}
