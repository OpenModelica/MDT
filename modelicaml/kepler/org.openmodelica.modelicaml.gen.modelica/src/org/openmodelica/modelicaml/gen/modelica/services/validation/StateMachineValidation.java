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
package org.openmodelica.modelicaml.gen.modelica.services.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ConnectionPointReference;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.gen.modelica.services.MoldelicaMLValidator;


// TODO: Auto-generated Javadoc
/**
 * The Class StateMachineValidation.
 */
public class StateMachineValidation {

	/**
	 * Transitions from entry point lead to targets in same region.
	 * 
	 * @param v
	 *            the v
	 * @return the boolean
	 */
	public static Boolean transitionsFromEntryPointLeadToTargetsInSameRegion(
			Vertex v) {
		HashSet<String> target_qualified_names = new HashSet<String>(); // array
																		// with
																		// no
																		// duplicates!

		if (v instanceof ConnectionPointReference) {
			EList<Pseudostate> entryPoints = ((ConnectionPointReference) v)
					.getEntries();
			for (Pseudostate entryPoint : entryPoints) {
				target_qualified_names
						.addAll(getTargetsContainerQualifiedNames(entryPoint));
			}
		} else if (v instanceof Pseudostate) {
			if (((Pseudostate) v).getKind().toString().equals("entryPoint")) {
				target_qualified_names
						.addAll(getTargetsContainerQualifiedNames(v));
			}

		}
		if (target_qualified_names.size() > 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets the targets container qualified names.
	 * 
	 * @param v
	 *            the v
	 * @return the targets container qualified names
	 */
	public static HashSet<String> getTargetsContainerQualifiedNames(Vertex v) {
		HashSet<String> target_qualified_names = new HashSet<String>(); // array
																		// with
																		// no
																		// duplicates!
		EList<Transition> tlist = v.getOutgoings();
		for (Transition transition : tlist) {
			Vertex target = transition.getTarget();
			target_qualified_names
					.add(target.getContainer().getQualifiedName());
			// System.out.println(target.getContainer().getQualifiedName());
		}
		return target_qualified_names;

	}
	
	
	/**
	 * Transitions from fork lead to targets in same composite state.
	 * 
	 * @param fork
	 *            the fork
	 * @return the boolean
	 */
	public static Boolean transitionsFromForkLeadToTargetsInSameCompositeState(Pseudostate fork) {
		// array with no duplicates!
		HashSet<String> containing_state_qualified_name_list = new HashSet<String>(); 
		String containing_state_qualified_name = null;
		
		EList<Transition> tlist = fork.getOutgoings(); 
		for (Transition transition : tlist) {
			containing_state_qualified_name = getContainingStateQualifiedName(transition.getTarget());
			containing_state_qualified_name_list.add(containing_state_qualified_name);
		}
		if (containing_state_qualified_name_list.size() > 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Transitions to join originate from same composite state.
	 * 
	 * @param join
	 *            the join
	 * @return the boolean
	 */
	public static Boolean transitionsToJoinOriginateFromSameCompositeState(Pseudostate join) {
		// array with no duplicates!
		HashSet<String> containing_state_qualified_name_list = new HashSet<String>(); 
		String containing_state_qualified_name = null;
		
		EList<Transition> tlist = join.getIncomings(); 
		for (Transition transition : tlist) {
			containing_state_qualified_name = getContainingStateQualifiedName(transition.getSource());
			containing_state_qualified_name_list.add(containing_state_qualified_name);
		}
		if (containing_state_qualified_name_list.size() > 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Transitions from fork lead to targets in orthogonal regions.
	 * 
	 * @param fork
	 *            the fork
	 * @return the boolean
	 */
	public static Boolean transitionsFromForkLeadToTargetsInOrthogonalRegions(Pseudostate fork) {
		// array with no duplicates!
		HashSet<String> containing_region_qualified_name_list = new HashSet<String>(); 
		String containing_region_qualified_name = null;
		Boolean result = false;
		
		EList<Transition> tlist = fork.getOutgoings(); 
		for (Transition transition : tlist) {
			containing_region_qualified_name = transition.getTarget().getContainer().getQualifiedName().toString();
			if (containing_region_qualified_name_list.contains(containing_region_qualified_name)) {
				result=false;
			}
			else {
				result=true;
				containing_region_qualified_name_list.add(containing_region_qualified_name);	
			}
		}
		return result;
	}
	
	/**
	 * Transitions to join originate from targets in orthogonal regions.
	 * 
	 * @param join
	 *            the join
	 * @return the boolean
	 */
	public static Boolean transitionsToJoinOriginateFromTargetsInOrthogonalRegions(Pseudostate join) {
		// array with no duplicates!
		HashSet<String> containing_region_qualified_name_list = new HashSet<String>(); 
		String containing_region_qualified_name = null;
		Boolean result = false;
		
		EList<Transition> tlist = join.getIncomings(); 
		for (Transition transition : tlist) {
			containing_region_qualified_name = transition.getSource().getContainer().getQualifiedName().toString();
			if (containing_region_qualified_name_list.contains(containing_region_qualified_name)) {
				result=false;
			}
			else {
				result=true;
				containing_region_qualified_name_list.add(containing_region_qualified_name);	
			}
		}
		return result;
	}
	
	
	/**
	 * Gets the containing state qualified name.
	 * 
	 * @param v
	 *            the v
	 * @return the containing state qualified name
	 */
	public static String getContainingStateQualifiedName(Vertex v) {
		String result = null;
		if (v.getContainer().getState() != null) {
			result = v.getContainer().getState().getQualifiedName();
		}
		else {
			result = v.getContainer().getStateMachine().getQualifiedName();
		}
		return result;
	}
	
	
	
	/**
	 * State machine reference loop detection.
	 * 
	 * @param sm
	 *            the sm
	 * @return the string
	 */
	public static String StateMachineReferenceLoopDetection(StateMachine sm) {
		Collection<String> sm_qualified_names = new ArrayList<String>(); // a list that can contain duplicates.
		EList<Region> rlist = sm.getRegions();
		Boolean result = false;
		String msg = "";
		
		sm_qualified_names.add(sm.getQualifiedName().toString());
					
		for (Region region : rlist) {
			result = isLoopDetected(region, sm_qualified_names);
		}
		if (result.equals(true)) {
			msg = "NOT VALID(sar02): A SubStateMachines reference loop was detected in " + sm.getName();
		}
		return msg;
	}
	
	/**
	 * Checks if is loop detected.
	 * 
	 * @param region
	 *            the region
	 * @param sm_qualified_names
	 *            the sm_qualified_names
	 * @return the boolean
	 */
	private static Boolean isLoopDetected(Region region, Collection<String> sm_qualified_names) {
		Boolean result = false;
		
		EList<Vertex> slist = region.getSubvertices();
		for (Vertex vertex : slist) {
			if (vertex instanceof State) {
				State state = (State)vertex;
				if (state.isSubmachineState()) {
					StateMachine submachine = state.getSubmachine();
					
					if (sm_qualified_names.contains(submachine.getQualifiedName().toString())) {
						result = true;
						String msg = "NOT VALID(sar02): A SubStateMachines reference loop was detected. StateMachine: '"+submachine.getQualifiedName()+"' is referenced multiple times.";
						MoldelicaMLValidator.modelicaMLValidationAlert(submachine, "error", msg);
					}
					else {
						sm_qualified_names.add(submachine.getQualifiedName().toString());
						EList<Region> rlist = state.getSubmachine().getRegions();
						for (Region region2 : rlist) {
							result = isLoopDetected(region2, sm_qualified_names);
						}
					}
				}
			}
		}
		return result;
	}
	
}
