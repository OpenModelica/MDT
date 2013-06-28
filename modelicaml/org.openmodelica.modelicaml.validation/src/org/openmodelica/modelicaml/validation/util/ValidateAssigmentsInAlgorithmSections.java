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
package org.openmodelica.modelicaml.validation.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;

public class ValidateAssigmentsInAlgorithmSections {

	/**
	 * Gets the error message for single assignment rule in algorithm sections.
	 * 
	 * @param classifier
	 *            the classifier
	 * @return the error message for single assignment rule in algorithm sections
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public List<String> getVariableWithMultipleAssignementInAlgorithmSections(Classifier classifier)  {
	
		Collection<String> left_side_variables = new ArrayList<String>(); // a list that can contain duplicates.
	
		HashSet<Element> blist = getBehavior(classifier);
		for (Element element : blist) {
			if (element instanceof StateMachine) {
				left_side_variables.addAll(getLeftSideVariablesFromStateMachine((StateMachine)element));
			}
			else if ((element instanceof OpaqueBehavior)) {
				left_side_variables.addAll(getLeftSideVariablesFromOpaqueBehavior((OpaqueBehavior)element));
			}
			else if ((element instanceof Activity)) {
				left_side_variables.addAll(getLeftSideVariablesFromActivity((Behavior)element));
			}		
		}
		
		if (left_side_variables.size() > 0 ) {
			
			List<String> duplicates = getDuplicate(left_side_variables);
			if (duplicates.size() > 0 ) {
				return duplicates;
			}
		}
	
		return null;
	}
	
	
	public List<String> getConinuesVariablesAssignedInStateMachines(Class clazz){
		
		List<String> conVarsAssingedInAlgSections = new ArrayList<String>(); 
		
		List<String> vars = getVariableNamesAssignedInStateMachines(clazz);
		if (vars != null && !vars.isEmpty()) {
			ClassInstantiation ci = new ClassInstantiation((Class) clazz, null, false);
			ci.createTree();

			for (String assignedVarName : vars) {
				for (TreeObject treeObject : ci.getAllTreeObjects()) {

					// find the treeObject
					if (treeObject.getDotPath().equals(assignedVarName.trim())) {
						// check if it is of type Real
						
						Type type = treeObject.getComponentType();
						// TODO: The type can be a sup-type of Real ... implement this
						if (type.getName().contains("Real")) {
							
							// check if the variability is set to discrete
							Element element = treeObject.getUmlElement();
							if (element instanceof Property) {
								Stereotype sVariable = element.getAppliedStereotype(Constants.stereotypeQName_Variable);
								
								if (sVariable != null) {
									Object pVariability = element.getValue(sVariable, Constants.propertyName_variability);
									if (pVariability instanceof EnumerationLiteral) {
										if ( !((EnumerationLiteral)pVariability).getName().equals("discrete") )  {
											conVarsAssingedInAlgSections.add(treeObject.getDotPath());
										}
									}
								}
							}
						}
					}
				}	
			}
		}
		
		if (conVarsAssingedInAlgSections.isEmpty()) {
			return null;
		}
		
		return conVarsAssingedInAlgSections;
	}
	
	
	
	private List<String> getVariableNamesAssignedInStateMachines(Classifier classifier)  {
		
		List<String> left_side_variables = new ArrayList<String>(); // a list that can contain duplicates.
	
		HashSet<Element> blist = getBehavior(classifier);
		
		for (Element element : blist) {
			// consider only state machines
			if (element instanceof StateMachine) {
				left_side_variables.addAll(getLeftSideVariablesFromStateMachine((StateMachine)element));
			}
		}
		
		return left_side_variables;
	}
	
	
	
	/**
	 * Gets the behavior.
	 * 
	 * @param classifier
	 *            the classifier
	 * @return the behavior
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	private HashSet<Element> getBehavior(Classifier classifier) {
		HashSet<Element> behavior = new HashSet<Element>(); // array with no duplicates!
		EList<Element> blist = (EList<Element>) classifier.getOwnedElements();
		
		for (Element element : blist) {

			// get state machines
			if (element instanceof StateMachine && element.getAppliedStereotype(Constants.stereotypeQName_ModelicaMLStateMachine) != null && ((StateMachine)element).getSubmachineStates().size() < 1) {
					behavior.add(element);
			}
			
			// get algorithm sections
			else if ((element instanceof OpaqueBehavior) && element.getAppliedStereotype(Constants.stereotypeQName_Algorithm) != null) {
					behavior.add(element);
			}
			
			// get activities
			else if ((element instanceof Activity)) {
				Stereotype s = element.getAppliedStereotype(Constants.stereotypeQName_ConditionalAlgorithm);
				Object p = element.getValue(s, Constants.propertyName_isSubActivity);
				if (s != null && p instanceof Boolean && !((Boolean)p)) {
					behavior.add(element);
				}
			}
		}
		
		//TODO TEST it! 
		//TODO Iterate over generalization and recursively call this method to get all behavior from the super classes.
		EList<Generalization> superclasses = classifier.getGeneralizations();
		for (Generalization generalization : superclasses) {
			behavior.addAll(getBehavior(generalization.getGeneral()));
		}
		
		return behavior;
	}

	/**
	 * Gets the left side variables from state machine.
	 * 
	 * @param sm
	 *            the sm
	 * @return the left side variables from state machine
	 */
	private HashSet<String> getLeftSideVariablesFromStateMachine(StateMachine sm) {
		Collection<String> sm_qualified_names = new ArrayList<String>(); // a list that can contain duplicates.
		HashSet<String> left_side_variables = new HashSet<String>(); // array with no duplicates!
		
		sm_qualified_names.add(sm.getQualifiedName());
		EList<Region> rlist = sm.getRegions();
		for (Region region : rlist) {
			left_side_variables.addAll(getLeftSideVariablesFromRegion(region, sm_qualified_names));
		}
		return left_side_variables;
	}
	
	/**
	 * Gets the left side variables from region.
	 * 
	 * @param region
	 *            the region
	 * @param sm_qualified_names
	 *            the sm_qualified_names
	 * @return the left side variables from region
	 */
	private HashSet<String> getLeftSideVariablesFromRegion(Region region, Collection<String> sm_qualified_names) {
		HashSet<String> left_side_variables = new HashSet<String>(); // array with no duplicates!
		HashSet<State> slist = getAllStates(region);
		
		for (State state : slist) {
			if (state.getEntry() != null) {
				left_side_variables.addAll(getLeftSideVariablesFromBehavior(state.getEntry()));
			}
			if (state.getExit() != null) {
				left_side_variables.addAll(getLeftSideVariablesFromBehavior(state.getExit()));
			}
			if (state.getDoActivity() != null) {
				left_side_variables.addAll(getLeftSideVariablesFromBehavior(state.getDoActivity()));
			}
			if (state.isComposite()) {
				EList<Region> rlist = state.getRegions();
				for (Region region2 : rlist) {
					left_side_variables.addAll(getLeftSideVariablesFromRegion(region2, sm_qualified_names));
				}
			}
			if (state.isSubmachineState()) {
				StateMachine submachine = state.getSubmachine();
				
				if (sm_qualified_names.contains(submachine.getQualifiedName().toString())) {
					//do nothing
				}
				else {
					sm_qualified_names.add(submachine.getQualifiedName().toString());
					EList<Region> rlist = state.getSubmachine().getRegions();
					for (Region region2 : rlist) {
						left_side_variables.addAll(getLeftSideVariablesFromRegion(region2, sm_qualified_names));
					}
				}
			}
		}
		EList<Transition> tlist = region.getTransitions();
		for (Transition transition : tlist) {
			if (transition.getEffect() != null) {
				left_side_variables.addAll(getLeftSideVariablesFromBehavior(transition.getEffect()));
			}
		}
		return left_side_variables;
	}
	

	/**
	 * Gets the left side variables from behavior.
	 * 
	 * @param behavior
	 *            the behavior
	 * @return the left side variables from behavior
	 */
	private HashSet<String> getLeftSideVariablesFromBehavior(Behavior behavior) {
		HashSet<String> left_side_variables = new HashSet<String>();
		if (behavior instanceof OpaqueBehavior) {
			left_side_variables.addAll(getLeftSideVariablesFromOpaqueBehavior((OpaqueBehavior)behavior));	
		}
		if (behavior instanceof Activity) {
			left_side_variables.addAll(getLeftSideVariablesFromActivity(behavior));	
		}
		
		return left_side_variables;
	}
	
	/**
	 * Gets the left side variables from opaque behavior.
	 * 
	 * @param b
	 *            the b
	 * @return the left side variables from opaque behavior
	 */
	private HashSet<String> getLeftSideVariablesFromOpaqueBehavior(OpaqueBehavior b) {
		
		HashSet<String> left_side_variables = new HashSet<String>();
		
		EList<String> OpaqueBehaviorBodies = b.getBodies();
		if (OpaqueBehaviorBodies != null) {
			for (String string : OpaqueBehaviorBodies) {
				left_side_variables.addAll(getLeftSideVariablesFromString(string));
			}
		}
		return left_side_variables;
	}
	
/**
 * Gets the left side variables from activity.
 * 
 * @param activity
 *            the activity
 * @return the left side variables from activity
 */
	private HashSet<String> getLeftSideVariablesFromActivity(Behavior activity) {
		
	HashSet<Action> actions = getAllActions(activity);
	HashSet<String> left_side_variables = new HashSet<String>();
	
	for (Action action : actions) {
		if (action instanceof OpaqueAction) {
			left_side_variables.addAll(getLeftSideVariablesFromOpaqueAction( (OpaqueAction)action));
		}
	}
		return left_side_variables;
	}
	
	/**
	 * Gets the left side variables from opaque action.
	 * 
	 * @param b
	 *            the b
	 * @return the left side variables from opaque action
	 */
	private HashSet<String> getLeftSideVariablesFromOpaqueAction(OpaqueAction b) {
		
		HashSet<String> left_side_variables = new HashSet<String>();
		
		EList<String> Bodies = b.getBodies();
		if (Bodies != null) {
			for (String string : Bodies) {
				left_side_variables.addAll(getLeftSideVariablesFromString(string));
			}
		}
		return left_side_variables;
	}
	
	
	/**
	 * Gets the left side variables from string.
	 * 
	 * @param string
	 *            the string
	 * @return the left side variables from string
	 */
	private HashSet<String> getLeftSideVariablesFromString(String string) {
		//TODO This method uses regex. It should be improved by using a parser.
			
		HashSet<String> left_side_variables = new HashSet<String>();
		String pure_statements_without_cond = removeConditionals(string); // remove if/when/for/while ... end if/when etc.
		String pure_statements_without_spaces = removeSpaces(pure_statements_without_cond); // remove spaces
		
		String[] lines = pure_statements_without_spaces.trim().split(";"); // split into lines
		for (String line : lines) {
			if (line.trim().startsWith("GEN_")) {
				String var_from_macro = getVariableFromMacro(line);
				//left_side_variables.add(removeSpaces(var_from_macro).trim());
				left_side_variables.add(var_from_macro.trim());
			}
			else {
				String[] var = line.trim().split(":="); // split into left/right of the ":=" sign
				//left_side_variables.add(removeSpaces(var[0]).trim());
				left_side_variables.add(var[0].trim());
			}
		}
		return left_side_variables;
	}
	
	
	
	/**
	 * Gets the variable from macro.
	 * 
	 * @param code
	 *            the code
	 * @return the variable from macro
	 */
	private String getVariableFromMacro(String code) {
		Pattern p = Pattern.compile("\\(+.+\\)");
		Matcher variable_in_macro = p.matcher(code);
		String var = code;
		if (variable_in_macro.find()) {
			var = variable_in_macro.group(0).replace("(", "").replace(")", "");
		}
		return var;
	}
	
	/**
	 * Removes the spaces.
	 * 
	 * @param code
	 *            the code
	 * @return the string
	 */
	private String removeSpaces(String code) {
		String code_new = code;
		code_new = code_new.replaceAll("\n", "");
		code_new = code_new.replaceAll("\b", "");
		code_new = code_new.replaceAll("\r", "");
		code_new = code_new.replaceAll("\t", "");

		return code_new;
	}
	
	/**
	 * Removes the conditionals.
	 * 
	 * @param code
	 *            the code
	 * @return the string
	 */
	private String removeConditionals(String code) {
		Pattern p_if_when = Pattern.compile("(if|when|elseif|elsewhen)+.+then");
		Matcher p_if_when_matcher = p_if_when.matcher(code);
		
		Pattern p_end_if_when = Pattern.compile("(else|end if;|end when;)");
		Matcher p_end_if_when_matcher = p_end_if_when.matcher(code);
		
		Pattern p_for_while = Pattern.compile("(for|while)+.+loop");
		Matcher p_for_while_matcher = p_for_while.matcher(code);
		
		Pattern p_end_for_while = Pattern.compile("(end for;|end while;)");
		Matcher p_end_for_while_matcher = p_end_for_while.matcher(code);
		
		String var = code;
		
		while(p_if_when_matcher.find()) {
			var = var.replace(p_if_when_matcher.group(0), "");
		}
		
		while(p_end_if_when_matcher.find()) {
			var = var.replace(p_end_if_when_matcher.group(0), "");
		}

		while(p_for_while_matcher.find()) {
			var = var.replace(p_for_while_matcher.group(0), "");
		}
		
		while(p_end_for_while_matcher.find()) {
			var = var.replace(p_end_for_while_matcher.group(0), "");
		}
		
		return var;
	}
	
	/**
	 * Gets the all states.
	 * 
	 * @param region
	 *            the region
	 * @return the all states
	 */
	private HashSet<State> getAllStates(Region region) {
		HashSet<State> states = new HashSet<State>();
		
		EList<Vertex> slist = region.getSubvertices();
		for (Vertex vertex : slist) {
			if (vertex instanceof State) {
				states.add((State) vertex);
			}
		}
		return states;
	}
	
	/**
	 * Gets the all actions.
	 * 
	 * @param activity
	 *            the activity
	 * @return the all actions
	 */
	private HashSet<Action> getAllActions(Behavior activity) {
		HashSet<Action> actions = new HashSet<Action>();
		
		EList<Element> alist = activity.getOwnedElements();		
		
		for (Element action: alist) {
			if (action instanceof OpaqueAction) {
				actions.add((OpaqueAction) action);
				//System.out.println( ((OpaqueAction)action).getName());
			}
			if (action instanceof CallBehaviorAction) {
				if ( ((CallBehaviorAction)action).getBehavior() != null) {
					actions.addAll(getAllActions( ((CallBehaviorAction)action).getBehavior() ));
				}
			}
		}
		return actions;
	}
	
	
	/**
	 * Gets the duplicate.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return the duplicate
	 */
	private static <T> List getDuplicate(Collection<T> list) {
		 
	    final List<T> duplicatedObjects = new ArrayList<T>();
	    Set<T> set = new HashSet<T>() {
	    @Override
	    public boolean add(T e) {
	        if (contains(e)) {
	            duplicatedObjects.add(e);
	        }
	        return super.add(e);
	    }
	    };
	   for (T t : list) {
	        set.add(t);
	    }
	    return duplicatedObjects;
	}

	/**
	 * Checks for duplicate.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return true, if successful
	 */
	private static <T> boolean hasDuplicate(Collection<T> list) {
	    if (getDuplicate(list).isEmpty())
	        return false;
	    return true;
	}
	
	
}
