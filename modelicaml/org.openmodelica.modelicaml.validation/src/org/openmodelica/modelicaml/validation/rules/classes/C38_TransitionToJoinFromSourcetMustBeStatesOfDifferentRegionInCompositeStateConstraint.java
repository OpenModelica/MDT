/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
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
