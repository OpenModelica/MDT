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
package org.openmodelica.modelicaml.common.instantiation;

import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.common.services.StringUtls;


	public class TreeUtls {
		
		// is set by the components tree plugin and each time it instantiates a class
		// it is used by other plugins, e.g., value bindings in order to get the currently instantiated class.
		// IMPORTANT: components tree plugin should not forget to reset it! 
		
		public static ClassInstantiation classInstantiation = null;
		public static TreeParent componentsTreeRoot = null;
		public static Viewer componentsTreeViewer = null;
		
		
		
		public static HashSet<Object> findTreeItems(String dotPath, TreeParent parent, HashSet<Object> list) {
			if (parent != null) {
				TreeObject[] items = parent.getChildren();
				
				for (int i = 0; i < items.length; i++) {
					if ( items[i].getDotPath().equals(dotPath) ) {
						list.add(items[i]);
					}
					else {
						// if it is a parent
						if (items[i] instanceof TreeParent) {
							list.addAll( findTreeItems(dotPath, (TreeParent)items[i], list) );
						}
					}
				}
			}
			return list;
		}
	
		
		public static HashSet<Element> getAllTreeItemsClasses(TreeParent treeParent){
			HashSet<Element> allTreeItems = new HashSet<Element>();
			
			// If it is the root then add the root class
			if (treeParent.isRoot() && treeParent.getSelectedClass() != null) {
				allTreeItems.add(treeParent.getSelectedClass());
			}
			
			// add all classes used in children
			TreeObject[] children = treeParent.getChildren();
			for (int i = 0; i < children.length; i++) {
				if (children[i].getUmlElement() instanceof TypedElement && ((TypedElement)children[i].getUmlElement()).getType() instanceof Classifier) {
					allTreeItems.add(((TypedElement)children[i].getUmlElement()).getType());
				}
				if (children[i] instanceof TreeParent) {
					allTreeItems.addAll(getAllTreeItemsClasses((TreeParent)children[i]));
				}
			}
			return allTreeItems;
		}
		
	
	/**
	 * Gets the all primitive variables dot path.
	 * 
	 * @param treeParent
	 *            the tree parent
	 * @return the all primitive variables dot path
	 */
	public static HashSet<String> getAllPrimitiveVariablesDotPath(TreeObject treeParent){
		HashSet<String> list = new HashSet<String>();
		TreeObject[] children = ((TreeParent)treeParent).getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i].isLeaf()) {
				list.add(children[i].getDotPath());
				// for Ports
				if ( !children[i].isOfPrimitiveType() ) {
					HashSet<String> nextLevelList = getAllPrimitiveVariablesDotPath(children[i]);
					list.addAll(nextLevelList);
				}
			}
			else {
				HashSet<String> nextLevelList = getAllPrimitiveVariablesDotPath(children[i]);
				list.addAll(nextLevelList);
			}
			
			// collect all stateMachine state variables and Signals for the type of treeObject property
			Property p = children[i].getProperty();
			if (p != null) {
				Type type = p.getType();
				if (type != null) {
					if (type instanceof Class) {
						list.addAll(getAllStateMachineStatesVariables((Class)type, children[i]));
						// add Signals
						list.addAll(getAllSignals((Class)type, children[i]));
					}
				}
			}
		}
		
		// for the selected Class
		if (treeParent.isRoot()) {
				// add stateMachine states variables
			list.addAll(getAllStateMachineStatesVariables(treeParent.getSelectedClass(), treeParent));
				// add Signals
			list.addAll(getAllSignals(treeParent.getSelectedClass(), treeParent));
		}
		return list;
	}
	
	
	private static EList<String> getAllSignals(Class type, TreeObject treeObject) {
		EList<String> list = new BasicEList<String>();
		EList<Element> pList = ((Class)type).getOwnedElements();
		for (Element property : pList) {
			if (property instanceof Signal) {
				String dotPath = treeObject.getDotPath();
				if (dotPath == null || dotPath.equals("")) { dotPath = StringUtls.replaceSpecChar(((Signal)property).getName()); }
				else {dotPath = dotPath + "." + StringUtls.replaceSpecChar(((Signal)property).getName()); }
				list.add(dotPath + ".counter");
			}
		}
		return list;
	}
	
	/**
	 * Gets the all state machine states variables.
	 * 
	 * @param type
	 *            the type
	 * @param treeObject
	 *            the tree object
	 * @return the all state machine states variables
	 */
	private static EList<String> getAllStateMachineStatesVariables(Class type, TreeObject treeObject) {
		EList<String> list = new BasicEList<String>();
		EList<Behavior> bList = ((Class)type).getOwnedBehaviors();
		for (Behavior behavior : bList) {
			if (behavior instanceof StateMachine) {
				if (((StateMachine)behavior).getSubmachineStates().size() < 1) {
					String dotPath = treeObject.getDotPath();
					if (dotPath == null || dotPath.equals("")) { dotPath = StringUtls.replaceSpecChar(((StateMachine)behavior).getName()); }
					else {dotPath = dotPath + "." + StringUtls.replaceSpecChar(((StateMachine)behavior).getName()); }
					list.addAll(getAllStateVariablesFromStateMachine((StateMachine) behavior, dotPath));								
				}
			}
		}
		return list;
	}
		
	
	private static EList<String> getAllStateVariablesFromStateMachine(StateMachine sm, String dotPath) {
		EList<String> list = new BasicEList<String>();
		EList<Region> regions =  sm.getRegions();
		for (Region region : regions) {
			if (dotPath != null) {
				dotPath = dotPath + "." +  StringUtls.replaceSpecChar(region.getName());
			}
			list.addAll(getAllStateVariablesFromRegion(region, dotPath));
		}
		return list;
	}
	
	/**
	 * Gets the all state variables from region.
	 * 
	 * @param r
	 *            the r
	 * @param dotPath
	 *            the dot path
	 * @return the all state variables from region
	 */
	private static EList<String> getAllStateVariablesFromRegion(Region r, String dotPath) {
		EList<String> list = new BasicEList<String>();
		EList<Vertex> states = r.getSubvertices();
		for (Vertex vertex : states) {
			if (vertex instanceof State) {
				String name = StringUtls.replaceSpecChar(((State)vertex).getName()); 
				list.add(dotPath + "." + name + ".active");
				//list.add(dotPath + "." + name + ".stime"); 
				
				if ( ((State)vertex).isComposite() ) {
					EList<Region> rList = ((State)vertex).getRegions();
					for (Region region : rList) {
						list.addAll(getAllStateVariablesFromRegion(region, dotPath + "." + name));
					}
				}
				if (((State)vertex).isSubmachineState()) {
					StateMachine subSm = ((State)vertex).getSubmachine();
					list.addAll(getAllStateVariablesFromStateMachine(subSm, dotPath + "." + name));
				}
			}
		}
		return list;
	}
	
	
}
