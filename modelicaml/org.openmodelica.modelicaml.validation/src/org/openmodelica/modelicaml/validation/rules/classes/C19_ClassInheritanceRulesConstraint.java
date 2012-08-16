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
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * C19:
 * 	Rule :	Check for the inheritance rules of Modelica classes as described P.Fritzson book and the Modelica spec. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C19_ClassInheritanceRulesConstraint extends AbstractModelConstraint {

	public C19_ClassInheritanceRulesConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		// In Both Live and Batch Mode
		if(eType != EMFEventType.NULL) {
			if(eObj instanceof Generalization) {
				eObj = ((Generalization) eObj).getSources().get(0);
			}
		}
		
		if( eObj instanceof Class) {

			if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_ModelicaPackage) != null)) {
				
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {

					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !(element instanceof Class 
								&& ( element.getAppliedStereotype(Constants.stereotypeQName_ModelicaPackage) != null
										|| element.getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null) 
									)
							) {

							return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + ": Modelica package ('"+((NamedElement) eObj).getName()
									+ "') can only extend a Modelica packages."});
						}
					}
				}
			}

			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Operator) != null)) {

				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {

					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !( element instanceof Class && element.getAppliedStereotype(Constants.stereotypeQName_Operator) != null)){
							return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Operator '"
									+((NamedElement) eObj).getName()+ "' can only extend Modelica operators. "});
						}
					}

				}


			}
			else if( eObj instanceof FunctionBehavior && (((FunctionBehavior) eObj).getAppliedStereotype(Constants.stereotypeQName_Function) != null)){
				
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {

					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if(!( element instanceof FunctionBehavior && element.getAppliedStereotype(Constants.stereotypeQName_Function) != null)){
							return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Function '"
									+((NamedElement) eObj).getName()+ "' can only extend Modelica functions."});
						}
					}
				}
			}
			else if(eObj instanceof PrimitiveType && ((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Type) != null) {
				
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !(element instanceof PrimitiveType && element.getAppliedStereotype(Constants.stereotypeQName_Type) != null)){
							return ctx.createFailureStatus(new Object[]{ Constants.validationKeyWord_NOT_VALID + ": Type '"
									+((NamedElement) eObj).getName()+"' can only extend Modelica type."});
						}
					}
				}
			}
			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Record) != null)) {
				
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !( element instanceof Class && element.getAppliedStereotype(Constants.stereotypeQName_Record) != null)){
							return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Record '"
									+((NamedElement) eObj).getName()+ "' can only extend Modelica records."});
						}
					}
				}
			}
			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Connector) != null)){
				
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !(( element instanceof Class 
								&& (
										(element.getAppliedStereotype(Constants.stereotypeQName_Connector) != null) 
											|| element.getAppliedStereotype(Constants.stereotypeQName_Record) != null )) 
		//									|| element instanceof PrimitiveType && element.getAppliedStereotype(Constants.stereotypeQName_Type) != null 
											|| element instanceof PrimitiveType 
											|| element instanceof Enumeration 
									)){
							
							return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Connector '"
									+((NamedElement) eObj).getName()+ "' can only extend Modelica connectors."});
						}
					}
				}

			}
			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Block) != null)) {
				
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !( element instanceof Class && (element.getAppliedStereotype(Constants.stereotypeQName_Block) != null 
								|| element.getAppliedStereotype(Constants.stereotypeQName_Record) != null ))){
							
							return ctx.createFailureStatus(new Object[] { Constants.validationKeyWord_NOT_VALID + ": Block '"
									+ ((NamedElement) eObj).getName() + " can only extend Modelica blocks."});
						}
					}
				}
			}
			else if( eObj instanceof Class && ((((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Model) != null 
					|| ((Class)eObj).getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null))){
				
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {
						if( !( element instanceof Class && (element.getAppliedStereotype(Constants.stereotypeQName_Model) != null 
								|| element.getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null 
								|| element.getAppliedStereotype(Constants.stereotypeQName_Block) != null 
								|| element.getAppliedStereotype(Constants.stereotypeQName_Record) != null ))) {
							
							return ctx.createFailureStatus(new Object[] {Constants.validationKeyWord_NOT_VALID + ": Model '"
									+((NamedElement) eObj).getName()+ "' can only extend Modelica models, classes, records or blocks."});
						}
					}
				}
			}
		}

		return ctx.createSuccessStatus();
	}

}
