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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StateMachine;
import org.openmodelica.modelicaml.validation.util.Utility;

/**
 * C6:
 * 	Rule :  Any UML StateMachine that is owned by a UML Class with a ModelicaML stereotype 
 * 		    applied must have either the stereotype <<ModelicaMLStateMachine>> applied.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C06_StateMachineStereotypeConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C06_StateMachineStereotypeConstraint() {
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
			
			if( eObj instanceof StateMachine )
			{

				Element element = ((StateMachine) eObj).getOwner();
				if( element instanceof Class ) 
				{
					if(Utility.isElementHaveModelicaMLStereotypeApplied(element))
					{
						if( ((StateMachine) eObj).getAppliedStereotype("ModelicaML::ModelicaBehaviorConstructs::ModelicaMLStateMachine") == null )
						{
							return ctx.createFailureStatus(new Object[] { "StateMachine "+((StateMachine) eObj).getName()+" Owned by Class "+((Class) element).getName()+" must have 'ModelicaMLStateMachine' Stereotype" });
						}
					}
					
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
