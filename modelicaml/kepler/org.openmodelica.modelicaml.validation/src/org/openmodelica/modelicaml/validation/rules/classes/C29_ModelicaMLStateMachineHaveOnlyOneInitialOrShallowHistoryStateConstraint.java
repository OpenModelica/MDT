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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
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
 * 	Rule :	Any region in a ModelicaML StateMachine must have either one UML PseudoState 
 * 			of kind 'initial' or one UML PseudoState of kind 'shallowHistory'.
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
			
			for (Region region : regionList) {
				
				int noOfInitialOrShallowHistoryStates = 0;
				
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
