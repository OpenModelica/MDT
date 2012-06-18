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
 * 				- A ModelicaML Stereotype must be applied to a UML Property (also true for value bindings stereotypes)
 * 				- Property must have type defined
 * 				- If the type of the UML Property is of UML PrimitiveType then the stereotype must be <<Variable>>
 * 				- Else if the type of the UML Property has the stereotype <<Requirement>> then the stereotype must be <<RequirementInstance>>
 * 				- Else if the type of the UML Property has the stereotype <<CalculationModel>> then the stereotype must be <<CalculatedProperty>>
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C04_ClassPropertyStereotypeConstraint extends AbstractModelConstraint {

	public C04_ClassPropertyStereotypeConstraint() {
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
			
			if(eObj instanceof Property && ((Property) eObj).getOwner() instanceof Class){
				Class clas = (Class) ((Property) eObj).getOwner();
				if(Utility.isElementHaveModelicaMLStereotypeApplied(clas)){
					Property property = (Property) eObj;
					Type propertyType = property.getType();
					
					/*
					 * Exclude value bindings elements
					 */
					if (property.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
						return ctx.createSuccessStatus();
					}
					
					/*
					 * Check if type is defined
					 */
					if(propertyType == null){
						return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_MISSING + ": Type for '" +((NamedElement)eObj).getName()+"'." });
					}
					
					/*
					 * Check the type stereotype compliance
					 */
					if((propertyType != null) && (propertyType.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null)){
						if(property.getAppliedStereotype(Constants.stereotypeQName_RequirementInstance) == null){
							return ctx.createFailureStatus(new Object[] { "Property '"
									+((NamedElement)eObj).getName()+"' of type with <<Requirement>> stereotype must have <<RequirementInstance>> stereotype applied." });
						}
					}
					else if((propertyType != null) && (propertyType.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null)){
						/*
						 * TODO: remove the property.getAppliedStereotype(Constants.stereotypeQName_Model) ... It should only be property.getAppliedStereotype(Constants.stereotypeQName_Model)
						 */
						if(!(property.getAppliedStereotype(Constants.stereotypeQName_CalculatedProperty) != null || property.getAppliedStereotype(Constants.stereotypeQName_Model) != null) ){
							return ctx.createFailureStatus(new Object[]{ "Property '"
									+((NamedElement)eObj).getName()+"' of type with <<CalculationModel>> or <<Model>> stereotype must have " +
											"<<CalculatedProperty>> or <<Component>> stereotype applied." });
						}
					}
					else if(propertyType instanceof PrimitiveType){
						if(property.getAppliedStereotype(Constants.stereotypeQName_Variable) == null){
							return ctx.createFailureStatus(new Object[]{ "Property '"
									+((NamedElement)eObj).getName()+"' of type which is instance of UML PrimitiveType must have <<Variable>> stereotype applied." });	
						}
					}
					else if(propertyType instanceof PrimitiveType){
						if(property.getAppliedStereotype(Constants.stereotypeQName_Variable) == null){
							return ctx.createFailureStatus(new Object[]{ "Property '"
									+((NamedElement)eObj).getName()+"' of type which is instance of UML PrimitiveType must have <<Variable>> stereotype applied." });	
						}
					}
					else if (!Utility.isElementHaveModelicaMLStereotypeApplied(property)) {
						return ctx.createFailureStatus(new Object[]{ "Property '"
								+((NamedElement)eObj).getName()+"' must have a ModelicaML stereotype applied." });	
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
