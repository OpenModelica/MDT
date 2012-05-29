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
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.PrimitiveType;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C18:
 * 	Rule :	Any UML Generalization that is owned by a UML Class with a ModelicaML stereotype 
 * 			applied must have the stereotype <<TypeExtendsRelation>> if it points to a 
 * 			UML Primitive Type instance or  <<ExtendsRelation>> otherwise.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C18_GeneralizationStereotypeConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C18_GeneralizationStereotypeConstraint() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		 
		EObject eObj  = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch mode
		if(eType == EMFEventType.NULL)
		{
			if( eObj instanceof Generalization)
			{
				Element ownerClass = ((Generalization) eObj).getOwner();
				
				if(ownerClass instanceof Class) // Check whether Owner element is a UML Class
				{
					if(Utility.isElementHaveModelicaMLStereotypeApplied(ownerClass))  // Check the Owner Class with ModelicaML stereotype
					{
						List<Element> targetList = ((Generalization) eObj).getTargets();
						
						for (Element element : targetList) {
							
							if(element instanceof PrimitiveType)
							{
								if((((Generalization) eObj).getAppliedStereotype(Constants.stereotypeQName_TypeRelation) == null))
								{
									return ctx.createFailureStatus(new Object[] {"UML Generalization of a Class pointing to PrimitiveType must extend Stereotype TypeRelation"});
								}
							}
							else if(((Generalization) eObj).getAppliedStereotype(Constants.stereotypeQName_ExtendsRelation) == null)
							{
								return ctx.createFailureStatus(new Object[] {"UML Generalization of a Class must extend Stereotype ExtendsRelation"});
							}
						}
					}
					
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}
