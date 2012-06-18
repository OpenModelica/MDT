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
 * C38:
 * 	Rule :	UML::Transition to a UML::PseudoState of kind 'join' must be from states that 
 * 			are all in different region of a composite state.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Batch
 */

public class C38_TransitionToJoinFromSourcetMustBeStatesOfDifferentRegionInCompositeStateConstraint extends AbstractModelConstraint {

	public C38_TransitionToJoinFromSourcetMustBeStatesOfDifferentRegionInCompositeStateConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In Batch Mode
		if(eType == EMFEventType.NULL) {
			if(eObj instanceof Pseudostate) {
				Pseudostate pseudostate = (Pseudostate) eObj;
				
				if(pseudostate.getKind().getValue() == PseudostateKind.JOIN) {
					
					List<Transition> incomingTransitionList = pseudostate.getIncomings();
					State compositeState = null;
					List<Region> foundRegionsList = new ArrayList<Region>();
					
					for (Transition transition : incomingTransitionList) {
						
						// Check each and every transition sources 
						if(transition.getSource() != null && transition.getSource() instanceof State) {
							State source_state = (State) transition.getSource();
							
							if(source_state.getOwner() != null && source_state.getOwner() instanceof Region) {
								Region region = (Region) source_state.getOwner();
								
								if(region.getOwner() != null && region.getOwner() instanceof State) {
									if(compositeState == null) {
										compositeState = (State) region.getOwner();
										foundRegionsList.add(region);
									}
									else if(compositeState.equals((State)region.getOwner()) && !foundRegionsList.contains(region)) {
										foundRegionsList.add(region);
									}
									else {
										return ctx.createFailureStatus(new Object[]{ Constants.validationKeyWord_NOT_VALID + ": Transition '" 
												+ transition.getName()+"' to a join must have sources as states that are all in different regions of a composite state."});
									}
								}
								else {
									return ctx.createFailureStatus(new Object[]{ Constants.validationKeyWord_NOT_VALID + ": Transition '" 
											+ transition.getName() + " to join must have sources as states that are all in different regions of a composite state."});
								}
							}
						}
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
