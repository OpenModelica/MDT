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
package org.openmodelica.modelicaml.gen.modelica.services;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ConnectionPointReference;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UML2ModelicaServices;
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices;

import fr.obeo.acceleo.gen.template.eval.ENode;
import fr.obeo.acceleo.gen.template.eval.ENodeCastException;
import fr.obeo.acceleo.gen.template.eval.ENodeIterator;
import fr.obeo.acceleo.gen.template.eval.ENodeList;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLServices.
 */
public class ModelicaMLServices {

	// public String expandGuardMacros(String guard, String replaceString) {
	// String new_guard = guard;
	// new_guard = expandAfterMacro(guard, replaceString);
	// new_guard = expandAbsMacro(guard);
	// return new_guard;
	// }
	/**
	 * The Class TransitionComparator.
	 */
	public class TransitionComparator implements Comparator<Integer> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Integer o1, Integer o2) {
			return o1.intValue() - o2.intValue();
		}

	}
	
	/**
	 * Test if all transitions targets have the same container.owner.
	 * 
	 * @param list
	 *            the list
	 * @return the boolean
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public static Boolean isForkEntry(ENodeList list) throws ENodeCastException
	{
		ENodeIterator iterator = list.iterator();
		Transition trans = null;
		Element owner = null;
//		System.out.print("test");
//		System.out.println(list);
		if(iterator.hasNext())
		{
			trans = (Transition) iterator.next().getEObject();
//			System.out.println(trans);
			if(trans.getTarget() instanceof State)
			{
				owner = trans.getTarget().getContainer().getOwner();
			}
			else {
				return false;
			}
//			System.out.println(owner);
		}
		if(!iterator.hasNext())
		{
			return false;
		}
		while (iterator.hasNext()) {
			trans = (Transition) iterator.next().getEObject();
			if(trans.getTarget() instanceof State)
			{
				if(trans.getTarget().getContainer().getOwner() != owner)
				{
//					System.out.println("false");
					return false;
				}
			}
			else {
				return false;
			}
			
			
		}
//		System.out.println("true");
		return true;
	}

	/**
	 * Sort transitions.
	 * 
	 * @param list
	 *            the list
	 * @return the linked list
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public LinkedList<Transition> sortTransitions(ENodeList list)
			throws ENodeCastException, ClassCastException {
		Set<Transition> unpriorizedSet = new LinkedHashSet<Transition>();
		TreeMap<Integer, Transition> tm = new TreeMap<Integer, Transition>(
				new TransitionComparator());

		UmlServices umlservice = new UmlServices();
		LinkedList<Transition> sortedList = new LinkedList<Transition>();
		Set<Entry<Integer, Transition>> set = null;
		// if list is empty return empty list
		if (list == null || list.size() == 0) {
			return sortedList;
		}
		for (ENodeIterator iterator = list.iterator(); iterator.hasNext();) {
			Transition transition = (Transition) iterator.next().getEObject();
			Stereotype stereotype = umlservice.getStereotype(transition,
					"ExecutionOrderPriority");

			if (stereotype == null) {
				unpriorizedSet.add(transition);
			} else {
				Integer transitionPriority = (Integer) transition.getValue(
						stereotype, "priority");
				if (transitionPriority != null) {
					if (!tm.containsKey(transitionPriority)) {
						tm.put(transitionPriority, transition);
					} else {
						unpriorizedSet.add(transition);
					}
				} else {
					unpriorizedSet.add(transition);
				}
			}

			// }
		}

		set = tm.entrySet();
		for (Entry<Integer, Transition> entry : set) {
			sortedList.add(entry.getValue());
		}
		for (Transition entry : unpriorizedSet) {
			sortedList.add(entry);
		}

		return sortedList;
	}

	/**
	 * Sort regions.
	 * 
	 * @param eNode
	 *            the e node
	 * @return the linked list
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public LinkedList<Region> sortRegions(ENode eNode)
			throws ENodeCastException, ClassCastException {

		Set<Region> unpriorizedSet = new LinkedHashSet<Region>();
		TreeMap<Integer, Region> tm = new TreeMap<Integer, Region>(
				new TransitionComparator());
		ENodeList list = null;
		UmlServices umlservice = new UmlServices();
		LinkedList<Region> sortedList = new LinkedList<Region>();
		Set<Entry<Integer, Region>> set = null;

		if (eNode.getType().equals("EObject")) {
			throw new IllegalArgumentException(
					"Argument must be of type ENodeList, not EObject. Owner.filter(\"Region\").sortRegions() or container.filter(\"Region\").sortRegions() is not allowed.");
			// sortedList.add((Region)eNode.getEObject());
			// return sortedList;
		} else if (eNode.getType().equals("ENodeList")) {
			list = eNode.getList();
			if (list == null) {
				throw new NullPointerException(
						"Argument (list) must be not null.");
			}

		} else {
			throw new IllegalArgumentException(
					"Argument must be of type ENodeList.");
		}

		for (ENodeIterator iterator = list.iterator(); iterator.hasNext();) {
			Region region = (Region) iterator.next().getEObject();
			Stereotype stereotype = umlservice.getStereotype(region,
					"ExecutionOrderPriority");
			if (stereotype == null) {
				unpriorizedSet.add(region);
			} else {
				Integer transitionPriority = (Integer) region.getValue(
						stereotype, "priority");
				if (transitionPriority != null) {
					if (!tm.containsKey(transitionPriority)) {
						tm.put(transitionPriority, region);
					} else {
						unpriorizedSet.add(region);
					}
				} else {
					unpriorizedSet.add(region);
				}
			}

		}

		set = tm.entrySet();
		for (Entry<Integer, Region> entry : set) {
			sortedList.add(entry.getValue());
		}
		for (Region entry : unpriorizedSet) {
			sortedList.add(entry);
		}
		return sortedList;
	}

	/**
	 * Sort region.
	 * 
	 * @param list
	 *            the list
	 * @return the linked list
	 */
	public LinkedList<Region> sortRegion(List<Region> list) {

		Set<Region> unpriorizedSet = new LinkedHashSet<Region>();
		TreeMap<Integer, Region> tm = new TreeMap<Integer, Region>(
				new TransitionComparator());
		UmlServices umlservice = new UmlServices();
		LinkedList<Region> sortedList = new LinkedList<Region>();
		Set<Entry<Integer, Region>> set = null;

		for (Iterator<Region> iterator = list.iterator(); iterator.hasNext();) {
			Region region = (Region) iterator.next();
			Stereotype stereotype;
			try {
				stereotype = umlservice.getStereotype(region,
						"ExecutionOrderPriority");

				if (stereotype == null) {
					unpriorizedSet.add(region);
				}

				else {
					Integer transitionPriority = (Integer) region.getValue(
							stereotype, "priority");
					if (transitionPriority != null) {
						if (!tm.containsKey(transitionPriority)) {
							tm.put(transitionPriority, region);
						} else {
							unpriorizedSet.add(region);
						}
					} else {
						unpriorizedSet.add(region);
					}
				}
			} catch (ENodeCastException e) {
				e.printStackTrace();
			}

		}

		set = tm.entrySet();
		for (Entry<Integer, Region> entry : set) {
			sortedList.add(entry.getValue());
		}
		for (Region entry : unpriorizedSet) {
			sortedList.add(entry);
		}

		return sortedList;
	}

	/**
	 * Sort control flows.
	 * 
	 * @param list
	 *            the list
	 * @return the linked list
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public static LinkedList<ActivityEdge> sortControlFlows(ENodeList list)
			throws ENodeCastException {
		TreeMap<Integer, ActivityEdge> tm = new TreeMap<Integer, ActivityEdge>();
		Set<ActivityEdge> unpriorizedSet = new LinkedHashSet<ActivityEdge>();
		UmlServices umlservice = new UmlServices();
		LinkedList<ActivityEdge> sortedList = new LinkedList<ActivityEdge>();
		Set<Entry<Integer, ActivityEdge>> set = null;
		// if list is empty return empty list
		if (list == null || list.size() == 0) {
			return sortedList;
		}
		for (ENodeIterator iterator = list.iterator(); iterator.hasNext();) {
			ActivityEdge transition = (ActivityEdge) iterator.next()
					.getEObject();
			Stereotype stereotype = umlservice.getStereotype(transition,
					"ExecutionOrderPriority");
			if (stereotype == null) {
				unpriorizedSet.add(transition);
			} else {
				Integer transitionPriority = (Integer) transition.getValue(
						stereotype, "priority");
				if (transitionPriority != null) {
					if (!tm.containsKey(transitionPriority)) {
						tm.put(transitionPriority, transition);
					} else {
						unpriorizedSet.add(transition);
					}
				} else {
					unpriorizedSet.add(transition);
				}
			}

		}

		set = tm.entrySet();
		for (Entry<Integer, ActivityEdge> entry : set) {
			sortedList.add(entry.getValue());
		}
		for (ActivityEdge entry : unpriorizedSet) {
			sortedList.add(entry);
		}

		return sortedList;
	}

	/**
	 * Filter join targets out.
	 * 
	 * @param transitionList
	 *            the transition list
	 * @return the linked list
	 */
	public static LinkedList<Transition> filterJoinTargetsOut(ENodeList transitionList) {
		LinkedList<Transition> sortedList = new LinkedList<Transition>();
		for (ENodeIterator iterator = transitionList.iterator(); iterator
				.hasNext();) {
			try {
				Transition transition = (Transition) iterator.next()
						.getEObject();
				if (transition.getTarget() instanceof Pseudostate) {
					Pseudostate pseudostate = (Pseudostate) transition
							.getTarget();
					if (!pseudostate.getKind().equals(
							PseudostateKind.JOIN_LITERAL)) {
						sortedList.add(transition);
					}

				} else {
					sortedList.add(transition);
				}
			} catch (ENodeCastException e) {
				System.out.println(e.getStackTrace());
			}

		}
		return sortedList;
	}

	/**
	 * Lowest common ancestor name.
	 * 
	 * @param transition
	 *            the transition
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static String lowestCommonAncestorName(Transition transition)
			throws ENodeCastException, ClassCastException {

		return lCAName(transition, null);
	}

	/**
	 * L ca name.
	 * 
	 * @param transition
	 *            the transition
	 * @param source
	 *            the source
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static String lCAName(Transition transition, State source)
			throws ENodeCastException, ClassCastException {
		String lowestCommonAncestor = "";
		String stateMachineName = "";
		NamedElement element;
		String transitionSourceContainerName;
		if (source != null) {
			transitionSourceContainerName = source.getContainer()
					.getQualifiedName();
		} else {
			transitionSourceContainerName = transition.getSource()
					.getContainer().getQualifiedName();
		}

		String transitionTargetContainerName = transition.getTarget()
				.getContainer().getQualifiedName();
		element = transition.getSource();

		// set stateMachineName
		while (stateMachineName.equals("")) {
			if (element instanceof StateMachine) {
				if (((StateMachine) element).getSubmachineStates().isEmpty()) {
					stateMachineName = element.getName();
				} else {
					element = (NamedElement) element.getOwner();
				}

			} else {
				element = (NamedElement) element.getOwner();
			}
		}

		int length = 0;
		if (transitionSourceContainerName.length() <= transitionTargetContainerName
				.length()) {
			length = transitionSourceContainerName.length();
		} else {
			length = transitionTargetContainerName.length();
		}
		for (int i = 0; i < length; i++) {
			if (!(transitionSourceContainerName.charAt(i)
					- transitionTargetContainerName.charAt(i) == 0)) {

				lowestCommonAncestor = transitionSourceContainerName
						.substring(transitionSourceContainerName
								.indexOf(stateMachineName), i + 1);
				lowestCommonAncestor = lowestCommonAncestor.substring(0,
						lowestCommonAncestor.lastIndexOf("::"));
				return (lowestCommonAncestor);
			}
			if (i + 1 == length) {
				if (transitionSourceContainerName.length() < transitionTargetContainerName
						.length()) {
					lowestCommonAncestor = transitionSourceContainerName;
				} else if (transitionSourceContainerName.length() > transitionTargetContainerName
						.length()) {
					lowestCommonAncestor = transitionTargetContainerName;
				}
				lowestCommonAncestor = lowestCommonAncestor.substring(
						lowestCommonAncestor.indexOf(stateMachineName), i + 1);
				return (lowestCommonAncestor);
			}
		}
		return null;
	}

	/**
	 * Lowest common ancestor namefor pseudostates.
	 * 
	 * @param transition
	 *            the transition
	 * @param source
	 *            the source
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static String lowestCommonAncestorNameforPseudostates(
			Transition transition, State source) throws ENodeCastException,
			ClassCastException {
		return lCAName(transition, source);
	}

	/**
	 * L ca qualified name.
	 * 
	 * @param transition
	 *            the transition
	 * @param source
	 *            the source
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static String lCAQualifiedName(Transition transition, State source)
			throws ENodeCastException, ClassCastException {
		String lowestCommonAncestor = "";
		String transitionSourceContainerName;
		String transitionTargetContainerName = "";
		if (source != null) {
			transitionSourceContainerName = source.getContainer()
					.getQualifiedName();
		} else {
			transitionSourceContainerName = transition.getSource()
					.getContainer().getQualifiedName();
		}
		if (transition.getTarget() instanceof Pseudostate) {
			Pseudostate pseudo = (Pseudostate) transition.getTarget();

			if (pseudo.getKind().equals(PseudostateKind.EXIT_POINT_LITERAL)) {
			} else {
				transitionTargetContainerName = transition.getTarget()
						.getContainer().getQualifiedName();
			}
		} else if (transition.getTarget() instanceof ConnectionPointReference) {
			ConnectionPointReference cPR = (ConnectionPointReference) transition
					.getTarget();
			transitionTargetContainerName = cPR.getState().getContainer()
					.getQualifiedName();
		} else {
			transitionTargetContainerName = transition.getTarget()
					.getContainer().getQualifiedName();
		}
		int length = 0;
		if (transitionSourceContainerName.length() <= transitionTargetContainerName
				.length()) {
			length = transitionSourceContainerName.length();
		} else {
			length = transitionTargetContainerName.length();
		}
		if (transitionSourceContainerName.equals(transitionTargetContainerName)) {
			return transitionSourceContainerName;
		}
		for (int i = 0; i < length; i++) {
			if (!(transitionSourceContainerName.charAt(i)
					- transitionTargetContainerName.charAt(i) == 0)) {
				lowestCommonAncestor = transitionSourceContainerName.substring(
						0, i + 1);
				lowestCommonAncestor = lowestCommonAncestor.substring(0,
						lowestCommonAncestor.lastIndexOf("::"));
				return (lowestCommonAncestor);
			}
			if (i + 1 == length) {
				if (transitionSourceContainerName.length() < transitionTargetContainerName
						.length()) {
					lowestCommonAncestor = transitionSourceContainerName;
				} else if (transitionSourceContainerName.length() > transitionTargetContainerName
						.length()) {
					lowestCommonAncestor = transitionTargetContainerName;
				}
				return (lowestCommonAncestor);
			}
		}
		if (lowestCommonAncestor.equals("")) {
//			throw new NullPointerException();
		}
		return lowestCommonAncestor;

	}

	/**
	 * Lowest common ancestor qualified name.
	 * 
	 * @param transition
	 *            the transition
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static String lowestCommonAncestorQualifiedName(Transition transition)
			throws ENodeCastException, ClassCastException {
		return lCAQualifiedName(transition, null);
	}

	/**
	 * Lowest common ancestor qualified namefor pseudostates.
	 * 
	 * @param transition
	 *            the transition
	 * @param source
	 *            the source
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static String lowestCommonAncestorQualifiedNameforPseudostates(
			Transition transition, State source) throws ENodeCastException,
			ClassCastException {
		return lCAQualifiedName(transition, source);
	}

	/**
	 * Gets the qualified dot name.
	 * 
	 * @param namedElement
	 *            the named element
	 * @param dotName
	 *            the dot name
	 * @return the qualified dot name
	 */
	public static String getQualifiedDotName(Vertex namedElement, String dotName) {
		UML2ModelicaServices uml2modelicaservice = new UML2ModelicaServices();

		if (dotName == null)
		{
			throw new NullPointerException("No String Argument.");
		}
		String containerName = uml2modelicaservice.replaceSpecChar(namedElement
				.getContainer().getName());
		String name = uml2modelicaservice.replaceSpecChar(namedElement
				.getName());
		String qualifiedDotName = dotName + "." + containerName + "." + name;
		return qualifiedDotName;
	}

	/**
	 * Gets the full qualified dot name.
	 * 
	 * @param namedElement
	 *            the named element
	 * @param dotName
	 *            the dot name
	 * @return the full qualified dot name
	 */
	public static String getFullQualifiedDotName(Vertex namedElement, String dotName) {
		if (dotName == null)
		{
			throw new NullPointerException("No String Argument.");
		}
		String qualifiedDotPart = getQualifiedDotPart(namedElement);

		String qualifiedDotName = dotName + qualifiedDotPart;
		return qualifiedDotName;
	}

	/**
	 * Gets the qualified dot name2.
	 * 
	 * @param namedElement
	 *            the named element
	 * @return the qualified dot name2
	 * @deprecated
	 */
	public String getQualifiedDotName2(NamedElement namedElement) {
		String qualifiedName = "";
		String qualifiedDotName = "";
		String stateMachineName = "";
		String substateMachineName = "";
		String subQualifiedName = "";
		String subqualifiedDotName = "";
		UML2ModelicaServices uml2modelicaservice = new UML2ModelicaServices();
		NamedElement element = namedElement;
		while (stateMachineName.equals("")) {
			if (element instanceof StateMachine) {
				if (((StateMachine) element).getSubmachineStates().isEmpty()) {
					stateMachineName = element.getName();
				} else {
					substateMachineName = element.getName();
					subQualifiedName = namedElement.getQualifiedName();
					subQualifiedName = subQualifiedName
							.substring(subQualifiedName
									.indexOf(substateMachineName)
									+ substateMachineName.length());
					subQualifiedName = uml2modelicaservice
							.replaceSpecCharExceptThis(subQualifiedName, "::");
					if (!subqualifiedDotName.equals("")) {
						subqualifiedDotName = subQualifiedName.replaceAll("::",
								".")
								+ subqualifiedDotName;
					} else {
						subqualifiedDotName = subQualifiedName.replaceAll("::",
								".");
					}

					element = ((StateMachine) element).getSubmachineStates()
							.get(0);
					namedElement = element;
				}

			} else {
				element = (NamedElement) element.getOwner();
				if (element == null) {
//					System.out.print("break");
					break;
				}
			}
		}

		qualifiedName = namedElement.getQualifiedName();

		qualifiedName = qualifiedName.substring(qualifiedName
				.indexOf(stateMachineName));
		qualifiedName = uml2modelicaservice.replaceSpecCharExceptThis(
				qualifiedName, "::");
		qualifiedDotName = qualifiedName.replaceAll("::", ".");
		if (!subqualifiedDotName.equals("")) {
			qualifiedDotName = qualifiedDotName + subqualifiedDotName;
		}
		// System.out.println("TTTTTT  "+qualifiedDotName+"   TTTTTT");
		return qualifiedDotName;
	}

	/**
	 * Gets the qualified dot part.
	 * 
	 * @param namedElement
	 *            the named element
	 * @return the qualified dot part
	 */
	public static String getQualifiedDotPart(NamedElement namedElement) {
		String qualifiedName = "";
		String qualifiedDotName = "";
		String stateMachineName = "";
		UML2ModelicaServices uml2modelicaservice = new UML2ModelicaServices();
		NamedElement element = namedElement;
		while (stateMachineName.equals("")) {
			if (element instanceof StateMachine) {
				stateMachineName = element.getName();
			} else {
				element = (NamedElement) element.getOwner();
				if (element == null) {
//					System.out.print("break");
					break;
				}
			}
		}

		qualifiedName = namedElement.getQualifiedName();

		qualifiedName = qualifiedName.substring(qualifiedName
				.indexOf(stateMachineName)
				+ stateMachineName.length());
		qualifiedName = uml2modelicaservice.replaceSpecCharExceptThis(
				qualifiedName, "::");
		qualifiedDotName = qualifiedName.replaceAll("::", ".");
		return qualifiedDotName;
	}

	/**
	 * Gets the interlevel entry list.
	 * 
	 * @param target
	 *            the target
	 * @param lca
	 *            the lca
	 * @return the interlevel entry list
	 */
	public static LinkedList<State> getInterlevelEntryList(State target, String lca) {
		LinkedList<State> linkedList = new LinkedList<State>();
		int i = 0;
		while (!target.getContainer().getQualifiedName().equals(lca)) {
			i++;
			if (!linkedList.contains(target)) {
				linkedList.addFirst(target);
			}
			if (target.getContainer().getOwner() instanceof State) {
				target = (State) target.getContainer().getOwner();
			} else {
				break;
			}
		}
		if (!linkedList.contains(target)) {
			linkedList.addFirst(target);
		}
		return linkedList;
	}

	/**
	 * Gets the missing fork region.
	 * 
	 * @param forkNode
	 *            the fork node
	 * @param targetOwner
	 *            the target owner
	 * @return the missing fork region
	 */
	public LinkedList<Region> getMissingForkRegion(Pseudostate forkNode,
			State targetOwner) {
		LinkedList<Region> linkedList = new LinkedList<Region>();

		linkedList.addAll(targetOwner.getRegions());
		EList<Transition> outgoings = forkNode.getOutgoings();
		for (Transition transition : outgoings) {
			linkedList.remove(transition.getTarget().getContainer());
		}
		return sortRegion(linkedList);
	}

	/**
	 * Adds the sub machien stateto list.
	 * 
	 * @param subMachineState
	 *            the sub machine state
	 * @param submachineStateList
	 *            the submachine state list
	 * @return the linked list
	 */
	public static LinkedList<State> addSubMachienStatetoList(State subMachineState,
			ENodeList submachineStateList) {
		if (submachineStateList == null) {
		}
		LinkedList<State> submachineStateList3 = new LinkedList<State>();
		if (submachineStateList != null) {
			List<?> submachineStateList2 = submachineStateList.asList();
			for (Object object : submachineStateList2) {
				if (object instanceof State) {
					submachineStateList3.add((State) object);
				}
			}
		}

		submachineStateList3.addFirst(subMachineState);
		return submachineStateList3;
	}

	/**
	 * Gets the missing regions.
	 * 
	 * @param list
	 *            the list
	 * @return the missing regions
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public LinkedList<Region> getMissingRegions(ENodeList list)
			throws ENodeCastException {

		LinkedList<Region> linkedList = new LinkedList<Region>();

		ENodeIterator iterator = list.iterator();

		State state = (State) iterator.next().getEObject();
		State nextState = null;
		while (iterator.hasNext()) {
			if (nextState != null) {
				state = nextState;
			}
			if (iterator.hasNext()) {
				nextState = (State) iterator.next().getEObject();
				EList<Region> regions = state.getRegions();

				for (Region region : regions) {
					if (!region.getQualifiedName().equals(
							nextState.getContainer().getQualifiedName())) {
						if (!linkedList.contains(region)) {
							linkedList.addFirst(region);
						}
					}
				}
			}
		}
		return sortRegion(linkedList);
	}

	/**
	 * Gets the other regions.
	 * 
	 * @param eNode
	 *            the e node
	 * @param region
	 *            the region
	 * @return the other regions
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public static LinkedList<Region> getOtherRegions(ENode eNode, Region region)
			throws ENodeCastException {

		LinkedList<Region> linkedList = new LinkedList<Region>();
		List<Region> list = null;
		if (eNode.getType().equals("ENodeList")) {
			list = eNode.getList().asList();
			if (list == null) {
				throw new NullPointerException(
						"Argument (list) must be not null.");
			}

		}
		Iterator<Region> iterator = list.iterator();
		while (iterator.hasNext()) {

			Region region2 = iterator.next();
			if (!region2.getQualifiedName().equals(region.getQualifiedName())) {
				if (!linkedList.contains(region2)) {
					linkedList.addFirst(region2);
				}
			}
		}
		return linkedList;
	}

	/**
	 * Gets the highest regionfor entry.
	 * 
	 * @param region
	 *            the region
	 * @return the highest regionfor entry
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public static Region getHighestRegionforEntry(Region region)
	throws ENodeCastException {
		Region region1 = region;
		while(! (region1.getOwner() instanceof StateMachine) )
		{
			Element element = region1.getOwner();
			if(element instanceof Vertex)
			{
				Vertex vertex = (Vertex) element;
				region1 =vertex.getContainer();
			}
		}
		return region1;
}

	

	/**
	 * Gets the other join regions.
	 * 
	 * @param eNode
	 *            the e node
	 * @param join
	 *            the join
	 * @return the other join regions
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public static LinkedList<Region> getOtherJoinRegions(ENode eNode, Pseudostate join)
			throws ENodeCastException {

		LinkedList<Region> linkedList = new LinkedList<Region>();
		LinkedList<Region> joinsourceRegions = new LinkedList<Region>();
		List<Region> list = null;

		EList<Transition> incomings = join.getIncomings();
		for (Transition transition : incomings) {
			joinsourceRegions.add(transition.getSource().getContainer());
		}
		if (eNode.getType().equals("ENodeList")) {
			list = eNode.getList().asList();
			if (list == null) {
				throw new NullPointerException(
						"Argument (list) must be not null.");
			}

		}
		Iterator<Region> iterator = list.iterator();
		while (iterator.hasNext()) {

			Region region2 = iterator.next();

			if (!joinsourceRegions.contains(region2)) {
				if (!linkedList.contains(region2)) {
					linkedList.addFirst(region2);
				}
			}
		}
		return linkedList;
	}

	/**
	 * Gets the container name.
	 * 
	 * @param umlElement
	 *            the uml element
	 * @param containerName
	 *            the container name
	 * @param qualifiedDotName
	 *            the qualified dot name
	 * @return the container name
	 */
	public static String getContainerName(Region umlElement, String containerName,
			String qualifiedDotName) {
		while (!qualifiedDotName.equals(umlElement.getQualifiedName())) {
//			System.out.println("uml" + umlElement.getQualifiedName());
			if (umlElement.getOwner() instanceof State) {
				String substring = "."
						+ ((State) umlElement.getOwner()).getContainer()
								.getName() + "."
						+ ((State) umlElement.getOwner()).getName();
				containerName = containerName.substring(0, containerName
						.lastIndexOf(substring));
				umlElement = ((State) umlElement.getOwner()).getContainer();
			} else if (umlElement.getOwner() instanceof StateMachine) {
//				System.out.println("test5:"
//						+ ((StateMachine) umlElement.getOwner()).getName());
				return ((StateMachine) umlElement.getOwner()).getName();
			}

		}
		return containerName;

	}

	/**
	 * Gets the connection point reference.
	 * 
	 * @param exitPoint
	 *            the exit point
	 * @param subMachineState
	 *            the sub machine state
	 * @return the connection point reference
	 */
	public static ConnectionPointReference getConnectionPointReference(
			Pseudostate exitPoint, State subMachineState) {
		EList<ConnectionPointReference> connectionPointReferences = subMachineState
				.getConnections();
		for (ConnectionPointReference connectionPointReference : connectionPointReferences) {
			EList<Pseudostate> exits = connectionPointReference.getExits();
			for (Pseudostate pseudostate : exits) {
				if (exitPoint == pseudostate) {
					return connectionPointReference;
				}
			}
		}
		new ExecutionException(
				"The subMachineState must have a connection point reference with the exitPoint '"
						+ exitPoint.getName() + "' as exit.");
		return null;
	}

	/**
	 * Gets the modelica type default value.
	 * 
	 * @param name
	 *            the name
	 * @return the modelica type default value
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public String getModelicaTypeDefaultValue(String name)
			throws ENodeCastException {
		if (name == "ModelicaReal") {
			return "0.0";
		} else if (name == "ModelicaInteger") {
			return "0";
		} else if (name == "ModelicaString") {
			return "";
		} else if (name == "ModelicaBoolean") {
			return "false";
		} else {
			return "";
		}
	}

	/**
	 * Gets the xMIID.
	 * 
	 * @param element
	 *            the element
	 * @return the xMIID
	 */
	public String getXMIID(EObject element) {
		if (element.eResource() instanceof XMIResource)
			return ((XMIResource) element.eResource()).getID(element);
		return "";
	}

}
