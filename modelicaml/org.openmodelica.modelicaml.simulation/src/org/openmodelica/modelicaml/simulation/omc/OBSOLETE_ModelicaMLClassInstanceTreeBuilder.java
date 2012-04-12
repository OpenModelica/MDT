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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation.omc;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.common.services.StringUtls;



// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLClassInstanceTreeBuilder.
 */
public class OBSOLETE_ModelicaMLClassInstanceTreeBuilder {
	
	/** The uml model element. */
	private Element umlModelElement;
	
	/** The instance path list. */
	private HashSet<String> instancePathList = new HashSet<String>(); // Array without duplicates
	
	/** The instance tree. */
	public Tree instanceTree;
	
	/**
	 * Instantiates a new modelica ml class instance tree builder.
	 * 
	 * @param umlElement
	 *            the uml element
	 * @param tree
	 *            the tree
	 */
	public OBSOLETE_ModelicaMLClassInstanceTreeBuilder(Element umlElement, Tree tree) {
		this.umlModelElement = umlElement;
		this.instanceTree = tree;
		
		if (umlModelElement instanceof Class) {
			TreeItem newTreeItem = new TreeItem(instanceTree, SWT.NONE);
			newTreeItem.setText( "Select the variables of class '" + ((Class)umlElement).getName() + "' that should be displayed on plot ..."); 
			newTreeItem.setGrayed(true);
			getComponents((Classifier)umlModelElement, null, newTreeItem);
		}
	}

	/**
	 * Gets the state machines.
	 * 
	 * @param behavior
	 *            the behavior
	 * @param path
	 *            the path
	 * @param parent
	 *            the parent
	 * @return the state machines
	 */
	private void  getStateMachines(StateMachine behavior, String path, TreeItem parent){
		String new_path = null;
		if (path != null) {
			new_path = path + "." + StringUtls.replaceSpecChar(behavior.getName()); 
		}
		else {
			new_path = StringUtls.replaceSpecChar(behavior.getName());
		}
		if (((StateMachine) behavior).getSubmachineStates()
				.isEmpty()) {
			TreeItem newTreeItem = addTreeItem(parent, ((StateMachine) behavior).getName(), path, false);
			getRegionsOfStateMachine((StateMachine) behavior, new_path, newTreeItem);
		}
	}
	
	/**
	 * Gets the regions of state machine.
	 * 
	 * @param stateMachine
	 *            the state machine
	 * @param path
	 *            the path
	 * @param parent
	 *            the parent
	 * @return the regions of state machine
	 */
	private void getRegionsOfStateMachine(StateMachine stateMachine, String path, TreeItem parent) {
		EList<Region> rList = stateMachine.getRegions();
		for (Region region : rList) {
			TreeItem newTreeItem = addTreeItem(parent, region.getName(), path, false);
			getStates(region, path + "." + StringUtls.replaceSpecChar(region.getName()), newTreeItem);
		}
	}

	/**
	 * Gets the regions of state.
	 * 
	 * @param state
	 *            the state
	 * @param path
	 *            the path
	 * @param parent
	 *            the parent
	 * @return the regions of state
	 */
	private void getRegionsOfState(State state, String path, TreeItem parent) {
		EList<Region> rList = state.getRegions();
		for (Region region : rList) {
			TreeItem newTreeItem = addTreeItem(parent, region.getName(), path, false);
			getStates(region, path + "." + StringUtls.replaceSpecChar(region.getName()), newTreeItem);
		}
	}

	/**
	 * Gets the states.
	 * 
	 * @param region
	 *            the region
	 * @param path
	 *            the path
	 * @param parent
	 *            the parent
	 * @return the states
	 */
	private void getStates(Region region, String path, TreeItem parent) {
		EList<Vertex> sList = region.getSubvertices();
		for (Vertex vertex : sList) {
			if (vertex instanceof State) {
				if (((State) vertex).isSimple()) {
					instancePathList.add(path + "." + StringUtls.replaceSpecChar(vertex.getName()) + ".active");
					// instancePathList.add(path + "." + Utls.replaceSpecChar(vertex.getName()) + ".timeAtActivation");
					// instancePathList.add(path + "." + Utls.replaceSpecChar(vertex.getName()) + ".stime");
					// instancePathList.add(path + "." + Utls.replaceSpecChar(vertex.getName()) + ".selfTransitionActivated");
					
					TreeItem newTreeItem = addTreeItem(parent, vertex.getName(), path + "." + StringUtls.replaceSpecChar(vertex.getName()), false);
					addTreeItem(newTreeItem, "active", path + "." + StringUtls.replaceSpecChar(vertex.getName()) + ".active", true);
//					addTreeItem(newTreeItem, "timeAtActivation", path + "." + Utls.replaceSpecChar(vertex.getName()) + ".timeAtActivation", true);
//					addTreeItem(newTreeItem, "stime", path + "." + Utls.replaceSpecChar(vertex.getName()) + ".stime", true);
//					addTreeItem(newTreeItem, "selfTransitionActivated", path + "." + Utls.replaceSpecChar(vertex.getName()) + ".selfTransitionActivated", true);
					
				} else if (((State) vertex).isComposite()) {
					TreeItem newTreeItem = addTreeItem(parent, ((State) vertex).getName(), path, false);
					getRegionsOfState((State) vertex, path + "."
							+ StringUtls.replaceSpecChar(((State) vertex).getName()), newTreeItem);
				} else if (((State) vertex).isSubmachineState()) {
					StateMachine subStateMachine = ((State) vertex)
							.getSubmachine();
					TreeItem newTreeItem = addTreeItem(parent, ((State) vertex).getName(), path, false);
					getRegionsOfStateMachine(subStateMachine, path + "."
							+ StringUtls.replaceSpecChar(((State) vertex).getName()), newTreeItem);
				}
			}
		}
	}

	/**
	 * Gets the components.
	 * 
	 * @param type
	 *            the type
	 * @param path
	 *            the path
	 * @param parentTreeItem
	 *            the parent tree item
	 * @return the components
	 */
	private void getComponents(Classifier type, String path, TreeItem parentTreeItem) {
		
		if (type instanceof Class) {

			EList<Property> list = ((Classifier) type).getAllAttributes();
			
			String newPath = null;
			String pName = null;
			Type pType = null;
	
			// owned and inherited Properties
			for (Property property : list) {
				pType = property.getType();
				pName = StringUtls.replaceSpecChar(property.getName());
				
				if (path != null) {
					newPath = path + "." + pName;
				}
				else {
					newPath = pName;
				}
				
				if (pType != null) {
					if (( pType instanceof PrimitiveType) || (pType instanceof Enumeration)) {
						instancePathList.add(newPath);
						addTreeItem(parentTreeItem, pName, newPath, true);
						
					} else {
						if (pType instanceof Class) {
							TreeItem newTreeItem = addTreeItem(parentTreeItem, pName, newPath, false);
							getComponents((Classifier)pType, newPath, newTreeItem);
						}
					}
				}
			}
			
			// owned StateMachines
			EList<Behavior> bList = ((Class) type).getOwnedBehaviors();
			for (Behavior behavior : bList) {
				if (behavior instanceof StateMachine) {
					getStateMachines((StateMachine)behavior, path, parentTreeItem);
				}
			}
			
			// inherited StateMachines
			EList<NamedElement> inheritedMmebersList = ((Class) type).getInheritedMembers();
			for (NamedElement namedElement : inheritedMmebersList) {
				if (namedElement instanceof StateMachine) {
					getStateMachines((StateMachine)namedElement, path, parentTreeItem);
				}
			}
		}
	}
	
	
	
	/**
	 * Gets the instace path list.
	 * 
	 * @return the instace path list
	 */
	public HashSet<String> getInstacePathList() {
		return this.instancePathList;
	}
	
	/**
	 * Gets the instance tree.
	 * 
	 * @return the instance tree
	 */
	public Tree getInstanceTree(){
		return this.instanceTree;
	}
	
	/**
	 * Adds the tree item.
	 * 
	 * @param parent
	 *            the parent
	 * @param text
	 *            the text
	 * @param data
	 *            the data
	 * @param isLeaf
	 *            the is leaf
	 * @return the tree item
	 */
	private TreeItem addTreeItem(TreeItem parent, String text, Object data, Boolean isLeaf){
		TreeItem newItem = new TreeItem(parent, SWT.BOLD); //SWT.NONE 
		newItem.setText(text);
		if (isLeaf) {
			newItem.setData(data);
			//newItem.getParentItem().setExpanded(true);
		}
		if (!isLeaf) {
			newItem.setGrayed(true);
		}
		return newItem;
	}

}
