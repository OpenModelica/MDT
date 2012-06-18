/**
 * 
 */
package org.openmodelica.modelicaml.validation.rules.classes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.openmodelica.modelicaml.common.constants.Constants;

/**	
 * State Machines
 * 
 * C35:
 * 	Rule :	UML::Transition from a UML::PseudoState of kind 'fork' must
 *			lead to states that are all in different (orthogonal) regions of a composite state.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C35_TransitionFromForkToTargetMustBeStatesOfDifferentRegionInCompositeStateConstraint extends AbstractModelConstraint {

	public C35_TransitionFromForkToTargetMustBeStatesOfDifferentRegionInCompositeStateConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) { // Blaming Fork instead of transition
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		
		if(eType != EMFEventType.NULL)
		{
			if(eObj instanceof Transition && ((Transition) eObj).getSource() instanceof Pseudostate)
			{
				eObj = ((Transition) eObj).getSource();	
			}
			else
			{
				return ctx.createSuccessStatus();
			}
		}
		if(eObj instanceof Pseudostate) {
			Pseudostate pseudoState = (Pseudostate) eObj;
			
			if(pseudoState.getKind().getValue() == PseudostateKind.FORK) {
				
				List<Transition> outgoingTransitionList = pseudoState.getOutgoings();
				State compositeState = null;
				List<Region> foundRegionsList = new ArrayList<Region>();
				
				for (Transition transition : outgoingTransitionList) {
					
					if(transition.getTarget() != null && transition.getTarget() instanceof State) {
						
						State target_state = (State) transition.getTarget();
						
						if(target_state.getOwner() != null && target_state.getOwner() instanceof Region) {
							
							Region region = (Region) target_state.getOwner();
							
							if(region.getOwner() != null && region.getOwner() instanceof State) {
								
								if(compositeState == null) {
									
									compositeState = (State) region.getOwner();
									foundRegionsList.add(region);
								}
								else if(compositeState.equals((State)region.getOwner()) && !foundRegionsList.contains(region)){
									foundRegionsList.add(region);
								}
								else {
									return ctx.createFailureStatus(new Object[]{ Constants.validationKeyWord_NOT_VALID + ": Transition '" 
											+ transition.getName()+"' from fork must lead to states that are all in different regions of a composite state."});
								}
							}
							else
							{
								return ctx.createFailureStatus(new Object[]{ transition.getName()+" Transition from Fork State must lead to States that are all in different regions of a composite state."});
							}
						}
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
