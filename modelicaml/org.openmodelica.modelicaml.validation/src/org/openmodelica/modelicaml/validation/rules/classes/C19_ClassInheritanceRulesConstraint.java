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

public class C19_ClassInheritanceRulesConstraint extends
AbstractModelConstraint {

	/**
	 * 
	 */
	public C19_ClassInheritanceRulesConstraint() {
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

		// In Both Live and Batch Mode

		if(eType != EMFEventType.NULL)
		{
			if(eObj instanceof Generalization)
			{
				eObj = ((Generalization) eObj).getSources().get(0);
			}
		}
		if( eObj instanceof Class)
		{

			if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_ModelicaPackage) != null))
			{
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {

					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !(element instanceof Class && element.getAppliedStereotype(Constants.stereotypeQName_ModelicaPackage) != null))
						{
							return ctx.createFailureStatus(new Object[] {"ModelicaPackage "+((NamedElement) eObj).getName()+ " can have only ModelicaPackages as base class"});
						}
					}
				}

			}

			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Operator) != null))
			{

				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {

					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !( element instanceof Class && element.getAppliedStereotype(Constants.stereotypeQName_Operator) != null))
						{
							return ctx.createFailureStatus(new Object[] {"Operator "+((NamedElement) eObj).getName()+ " can have only Operators as base class"});
						}
					}

				}


			}
			else if( eObj instanceof FunctionBehavior && (((FunctionBehavior) eObj).getAppliedStereotype(Constants.stereotypeQName_Function) != null))
			{
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {

					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if(!( element instanceof FunctionBehavior && element.getAppliedStereotype(Constants.stereotypeQName_Function) != null))
						{
							return ctx.createFailureStatus(new Object[] {"Function "+((NamedElement) eObj).getName()+ " can have only Functions as base class"});
						}
					}

				}


			}
			else if(eObj instanceof PrimitiveType && ((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Type) != null)
			{
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !(element instanceof PrimitiveType && element.getAppliedStereotype(Constants.stereotypeQName_Type) != null))
						{
							return ctx.createFailureStatus(new Object[]{"Type "+((NamedElement) eObj).getName()+" can have only Type as base class"});
						}
					}
				}


			}
			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Record) != null))
			{
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !( element instanceof Class && element.getAppliedStereotype(Constants.stereotypeQName_Record) != null))
						{
							return ctx.createFailureStatus(new Object[] {"Record "+((NamedElement) eObj).getName()+ " can have only Record as base class"});
						}
					}

				}


			}
			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Connector) != null))
			{
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !(( element instanceof Class && ((element.getAppliedStereotype(Constants.stereotypeQName_Connector) != null) || element.getAppliedStereotype(Constants.stereotypeQName_Record) != null )) || element instanceof PrimitiveType && element.getAppliedStereotype(Constants.stereotypeQName_Type) != null ))
						{
							return ctx.createFailureStatus(new Object[] {"Connector "+((NamedElement) eObj).getName()+ " can have only Connector or Type or Record  as base class"});
						}
					}
				}


			}
			else if( eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Block) != null))
			{
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !( element instanceof Class && (element.getAppliedStereotype(Constants.stereotypeQName_Block) != null || element.getAppliedStereotype(Constants.stereotypeQName_Record) != null )))
						{
							return ctx.createFailureStatus(new Object[] {"Block "+((NamedElement) eObj).getName()+ " can have only Block or Record as base class"});
						}
					}
				}


			}
			else if( eObj instanceof Class && ((((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Model) != null || ((Class)eObj).getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null)))
			{
				List<Generalization> generalizationList = ((Class) eObj).getGeneralizations();

				for (Generalization generalization : generalizationList) {
					List<Element> elementList = generalization.getTargets();

					for (Element element : elementList) {

						if( !( element instanceof Class && (element.getAppliedStereotype(Constants.stereotypeQName_Model) != null || element.getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null || element.getAppliedStereotype(Constants.stereotypeQName_Block) != null || element.getAppliedStereotype(Constants.stereotypeQName_Record) != null )))
						{
							return ctx.createFailureStatus(new Object[] {"Model "+((NamedElement) eObj).getName()+ " can have only Model/Class or Record or Block as base class"});
						}
					}
				}


			}
		}

		return ctx.createSuccessStatus();
	}

}
