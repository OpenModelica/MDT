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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.validation.util.Utility;

/**	
 * State Machines
 * 
 * C29:
 * 	Rule :	UML StateMachine A ModelicaML StateMachine must have either one  UML PseudoState 
 * 			of kind initial or one UML PseudoState of kind shallowHistory.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C29_ModelicaMLStateMachineHaveOnlyOneInitialOrShallowHistoryStateConstraint extends AbstractModelConstraint {


	public C29_ModelicaMLStateMachineHaveOnlyOneInitialOrShallowHistoryStateConstraint() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		if(eType != EMFEventType.NULL) {
			if(eObj instanceof Pseudostate 
					&& (((Pseudostate)eObj).getKind().getValue() ==  PseudostateKind.SHALLOW_HISTORY 
					|| ((Pseudostate)eObj).getKind().getValue() ==  PseudostateKind.INITIAL)) {
				
				Region region = (Region) ((Pseudostate) eObj).getOwner();
				eObj = region.getStateMachine();
			}
			else {
				return ctx.createSuccessStatus();
			}
		}
		
		if(eObj instanceof StateMachine && Utility.isElementHaveModelicaMLStereotypeApplied((Element)eObj)) {
			StateMachine stateMachine = (StateMachine) eObj;
			
			List<Region> regionList = stateMachine.getRegions();
			
			int noOfInitialOrShallowHistoryStates = 0;
			
			for (Region region : regionList) {
				
				List <Element> elementList = region.getOwnedElements();
				
				for (Element element : elementList) {
					
					if( element instanceof Pseudostate) {
						if(((Pseudostate)element).getKind().getValue() ==  PseudostateKind.SHALLOW_HISTORY 
								|| ((Pseudostate)element).getKind().getValue() ==  PseudostateKind.INITIAL) {
							noOfInitialOrShallowHistoryStates++;
							
							if(noOfInitialOrShallowHistoryStates > 1) {
								return ctx.createFailureStatus(new Object[]{Constants.validationKeyWord_NOT_VALID + ": '" 
										+ ((NamedElement)eObj).getName()+"' state machine can contian at most one initial state or shallow history state."});
							}
						}
					}
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
