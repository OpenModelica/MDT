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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;

/**
 * C9:
 * 	Rule : There must be no loops in UML StateMachine sub-statemachine references.
 * 
 * 	Severity : ERROR
 * 
 *	Mode : Live
 */

public class C09_NonCircularStatemachineReferencesConstraint extends
AbstractModelConstraint {

	/**
	 * 
	 */
	public C09_NonCircularStatemachineReferencesConstraint() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		// In Batch mode
		if(eType == EMFEventType.NULL)
		{
			if(eObj instanceof StateMachine)
			{

				List<StateMachine> statemachineList = null;
				
				if(ctx.getCurrentConstraintData() == null)
				{
					statemachineList = new ArrayList<StateMachine>();
				}
				else if(ctx.getCurrentConstraintData() instanceof List)
				{
					statemachineList = ((List<StateMachine>)ctx.getCurrentConstraintData());
				}


				List<Region> regionList = ((StateMachine) eObj).getRegions();

				for (Region region : regionList) {
					
					List<Element> regionElementList = region.getOwnedElements();
					
					for (Element element : regionElementList) {

						if(element instanceof State)
						{
							StateMachine referencedStateMachine = ((State) element).getSubmachine();
							
							if(statemachineList.contains(referencedStateMachine))
							{
								return ctx.createFailureStatus(new Object[]{"StateMachine "+((StateMachine) eObj).getName()+" and sub-StateMachine Reference to "+referencedStateMachine.getName()+" forming a loop"});
							}
							else
							{
								statemachineList.add(((StateMachine) eObj));

								ctx.putCurrentConstraintData(statemachineList);
							}
						}
					}


				}


			}
		}
		else // In Live Mode
		{
			if(ctx.getFeatureNewValue() instanceof StateMachine)
			{
				List<StateMachine> stateMachineList = new ArrayList<StateMachine>();
				if(checkStateMachineLoop(stateMachineList,(StateMachine)ctx.getFeatureNewValue()))
				{
					return ctx.createFailureStatus(new Object[]{"StateMachine "+((StateMachine) ctx.getFeatureNewValue()).getName()+" and sub-StateMachine Reference to  forming a loop"});

				}
			}	
		}
		return ctx.createSuccessStatus();
	}

	private boolean checkStateMachineLoop(List<StateMachine> stateMachineList,
			StateMachine eObj) {
		// TODO Auto-generated method stub
		stateMachineList.add(eObj);
		List<Region> regionList = eObj.getRegions();
		for (Region region : regionList) {
			
			List<Element> elementList = region.getOwnedElements();
			
			for (Element element : elementList) {
				if(element instanceof State)
				{
					StateMachine subStateMachine = ((State) element).getSubmachine();
					
					if(subStateMachine == null)
					{
						//return false;
					}
					else
					{
						if(stateMachineList.contains(subStateMachine))
						{
							return true;
						}
						else
						{
							return checkStateMachineLoop(stateMachineList, subStateMachine);
						}
					}
				}
			}
			
		}
		return false;
	}

}
