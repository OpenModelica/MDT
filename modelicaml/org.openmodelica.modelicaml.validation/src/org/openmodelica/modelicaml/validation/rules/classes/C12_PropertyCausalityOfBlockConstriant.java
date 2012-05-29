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
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * C12:
 * 	Rule :	Any Property of a UML Class with the ModelicaML stereotype <<Block>> applied 
 * 			must have causality input or output. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C12_PropertyCausalityOfBlockConstriant extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C12_PropertyCausalityOfBlockConstriant() {
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
		
	
			if(eObj instanceof Class && (((Class) eObj).getAppliedStereotype(Constants.stereotypeQName_Block) != null))
			{
				Class blockClass = (Class) eObj;
				List<Element> class_elements = blockClass.allOwnedElements();
				
				for (Element element : class_elements) {
					
					if((element instanceof Property) && (((Property)element).getAppliedStereotype(Constants.stereotypeQName_Variable) != null))
					{
						Property class_property = (Property) element;
						Element propertyValueElement = (EnumerationLiteral)class_property.getValue(class_property.getAppliedStereotype(Constants.stereotypeQName_Variable), Constants.propertyName_causality);
						if(propertyValueElement == null)
						{
							if(eType != EMFEventType.NULL)
							{
								System.out.println("C12 : NULL");
							}
							return ctx.createFailureStatus(new Object[] { class_property.getName()+" Block Property must have causality set to either 'input' or 'output'" });
						}
					}
				}
			}

		return ctx.createSuccessStatus();
	}

}
