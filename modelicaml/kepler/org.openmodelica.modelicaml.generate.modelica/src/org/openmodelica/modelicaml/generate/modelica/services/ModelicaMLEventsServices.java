/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.generate.modelica.services;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;

//import fr.obeo.acceleo.gen.template.eval.ENodeCastException;
//import fr.obeo.acceleo.gen.template.eval.ENodeIterator;
//import fr.obeo.acceleo.gen.template.eval.ENodeList;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLEventsServices.
 */
public class ModelicaMLEventsServices {

	/**
	 * Gets the events from all state machines.
	 * 
	 * @param state_machines
	 *            the state_machines
	 * @return the events from all state machines
	 */
	public HashSet<Event> getEventsFromAllStateMachines(EList<Element> state_machines) {

		HashSet<Event> events = new HashSet<Event>(); // array with no
														// duplicates!
		for (Element element : events) {
			StateMachine sm = (StateMachine)element;
			events.addAll(getEvents(sm));
		}
//		for (ENodeIterator iterator = state_machines.iterator(); iterator
//				.hasNext();) {
//			StateMachine sm;
//			try {
//				sm = (StateMachine) iterator.next().getEObject(); // .getEObject()
//																	// is a
//																	// must!
//				events.addAll(getEvents(sm));
//			} catch (ENodeCastException e) {
//				// e.printStackTrace();
//				System.out.println(e.getStackTrace());
//			}
//		}
		return events;
	}

	/**
	 * Gets the relative time events.
	 * 
	 * @param s
	 *            the s
	 * @return the relative time events
	 */
	public HashSet<Event> getRelativeTimeEvents(State s) {
		HashSet<Event> events = new HashSet<Event>(); // array with no duplicates!
		EList<Transition> otlist = s.getOutgoings();
		for (Transition transition : otlist) {
			EList<Trigger> transition_triggers = transition.getTriggers();
			for (Trigger trigger : transition_triggers) {
				if (trigger.getEvent() instanceof TimeEvent) {
					TimeEvent te = ((TimeEvent) trigger.getEvent());
					if (te.isRelative()) {
						events.add(te);
					}
				}
			}
		}
		return events;
	}

	/**
	 * Gets the events.
	 * 
	 * @param sm
	 *            the sm
	 * @return the events
	 */
	public HashSet<Event> getEvents(StateMachine sm) {
		HashSet<Trigger> triggers = new HashSet<Trigger>(); // array with no
															// duplicates!
		HashSet<Event> events = new HashSet<Event>();
		EList<Region> rlist = sm.getRegions();
		for (Region region : rlist) {
			triggers.addAll(getTriggersFromRegion(region));
		}
		for (Trigger trigger : triggers) {
			events.add(trigger.getEvent());
			// System.out.println(trigger.getEvent().getName());
		}
		return events;
	}

	/**
	 * Gets the triggers from region.
	 * 
	 * @param region
	 *            the region
	 * @return the triggers from region
	 */
	public HashSet<Trigger> getTriggersFromRegion(Region region) {
		HashSet<Trigger> triggers = new HashSet<Trigger>(); // array with no
															// duplicates!
		EList<Transition> tlist = region.getTransitions();
		for (Transition transition : tlist) {
			triggers.addAll(transition.getTriggers());
		}
		EList<Element> elist = region.getOwnedElements();
		for (Element element : elist) {
			if (element instanceof State) {
				State state = (State) element;
				if (state.isComposite()) {
					EList<Region> rlist = state.getRegions();
					for (Region region2 : rlist) {
						triggers.addAll(getTriggersFromRegion(region2));
					}
				}
				if (state.isSubmachineState()) {
					EList<Region> rlist = state.getSubmachine().getRegions();
					for (Region region2 : rlist) {
						triggers.addAll(getTriggersFromRegion(region2));
					}
				}
			}
		}
		return triggers;
	}
}
