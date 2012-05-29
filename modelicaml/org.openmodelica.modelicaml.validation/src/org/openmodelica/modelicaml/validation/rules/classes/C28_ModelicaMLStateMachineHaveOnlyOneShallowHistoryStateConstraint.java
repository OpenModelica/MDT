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
import org.openmodelica.modelicaml.validation.util.Utility;

/**	
 * State Machines
 * 
 * C28:
 * 	Rule :	UML StateMachine A ModelicaML StateMachine can contain at most one 
 * 			UML PseudoState of kind shallowHistory. 
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C28_ModelicaMLStateMachineHaveOnlyOneShallowHistoryStateConstraint
		extends AbstractModelConstraint {

	/**
	 * 
	 */
	public C28_ModelicaMLStateMachineHaveOnlyOneShallowHistoryStateConstraint() {
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
	
		
		if(eType != EMFEventType.NULL)
		{
			if(eObj instanceof Pseudostate && ((Pseudostate)eObj).getKind().getValue() ==  PseudostateKind.SHALLOW_HISTORY)
			{
				
				
				Region region = (Region) ((Pseudostate) eObj).getOwner();
				
				eObj = region.getStateMachine();
			}
			else
			{
				return ctx.createSuccessStatus();
			}
		}
			if(eObj instanceof StateMachine && Utility.isElementHaveModelicaMLStereotypeApplied((Element)eObj))
			{
				StateMachine stateMachine = (StateMachine) eObj;
				
				List<Region> regionList = stateMachine.getRegions();
				
				int noOfShallowHistoryStates = 0;
				
				for (Region region : regionList) {
					
					List <Element> elementList = region.getOwnedElements();
					
					for (Element element : elementList) {
						
						if( element instanceof Pseudostate)
						{
							if(((Pseudostate)element).getKind().getValue() ==  PseudostateKind.SHALLOW_HISTORY)
							{
								noOfShallowHistoryStates++;
								
								if(noOfShallowHistoryStates > 1)
								{
									return ctx.createFailureStatus(new Object[]{((NamedElement)eObj).getName()+" ModelicaML StateMachine can contian at most one ShallowHistory State."});
								}
							}
						}
						
					}
				}
			}
		
		
		return ctx.createSuccessStatus();
	}

}
