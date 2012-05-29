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
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Transition;

/**	
 * State Machines
 * 
 * C32:
 * 	Rule :	A UML PseudoState of kind choice or junction should have one else branch.
 * 
 * 	Severity : WARNING
 * 
 *	Mode : Batch
 */

public class C32_ChoiceOrJunctionStateShouldHaveOneElseBranchConstraint extends
		AbstractModelConstraint {

	/**
	 * 
	 */
	public C32_ChoiceOrJunctionStateShouldHaveOneElseBranchConstraint() {
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
		
		// In Batch Mode
		if(eType == EMFEventType.NULL)
		{
			
			if(eObj instanceof Pseudostate)
			{
				Pseudostate pseudoState = (Pseudostate) eObj;
				
				if(pseudoState.getKind().getValue() == PseudostateKind.CHOICE || pseudoState.getKind().getValue() == PseudostateKind.JUNCTION)
				{
					List<Transition> outgoingTransitionList = pseudoState.getOutgoings();
					
					boolean isElseExpressionExists = false;
					
					for (Transition transition : outgoingTransitionList) {
						
						Constraint constraint = transition.getGuard();
						
						if(constraint != null && constraint.getSpecification() != null && constraint.getSpecification() instanceof OpaqueExpression)
						{
							OpaqueExpression expression = (OpaqueExpression) constraint.getSpecification();
							
							List<String> expressionBody = expression.getBodies();
							
							if(expressionBody != null)
							{
								if(isElseExpressionExists == false && expressionBody.contains("else"))
								{
									isElseExpressionExists = true;
								}
							}
						}
					}
					
					if(isElseExpressionExists == false)
					{
						return ctx.createFailureStatus(new Object[]{pseudoState.getName()+" State should have one else branch"});
					}
				}
			}
		}
	
		return ctx.createSuccessStatus();

	}

}
