/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;

/**	
 * State Machines
 * 
 * C39:
 * 	Rule :	UML::Transitions should not cross the region borders inside the same 
 * 			composite state.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C39_TransitionCannotCrossRegionBordersOfCompositeStateConstraint
		extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C39_TransitionCannotCrossRegionBordersOfCompositeStateConstraint() {
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
			if(eObj instanceof Transition)
			{

				Transition transition = (Transition) eObj;
				
				if(transition.getSource() instanceof State && transition.getTarget() instanceof State)
				{
					State sourceState = (State) transition.getSource();
					State targetState = (State) transition.getTarget();
					
					if(sourceState.getOwner() instanceof Region && targetState.getOwner() instanceof Region)
					{
						Region sourceRegion = (Region) sourceState.getOwner();
						Region targetRegion = (Region) targetState.getOwner();
						
						if(sourceRegion.getOwner() instanceof State && targetRegion.getOwner() instanceof State && sourceRegion.getOwner().equals(targetRegion.getOwner()))
						{

							if(!sourceRegion.equals(targetRegion))
							{
								return ctx.createFailureStatus(new Object[]{"Transition "+transition.getName()+" should not cross region borders inside the same composite state"});
							}
						}
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
