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

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.common.services.StringUtls;



// TODO: Auto-generated Javadoc
/**
 * The Class TreeParent.
 */
public class TreeParent extends TreeObject {
	
	/** The children. */
	private ArrayList<TreeObject> children;

	/**
	 * Instantiates a new tree parent.
	 * 
	 * @param name
	 *            the name
	 * @param property
	 *            the property
	 * @param firstLevelComponent
	 *            the first level component
	 * @param dotPath
	 *            the dot path
	 * @param causality
	 *            the causality
	 * @param isLeaf
	 *            the is leaf
	 * @param isRoot
	 *            the is root
	 * @param modifications
	 *            the modifications
	 * @param selectedClass
	 *            the selected class
	 * @param showStateMachines
	 *            the show state machines
	 */
	public TreeParent(	String name, 
						Property property, 
						Property firstLevelComponent,
						String dotPath, 
						Boolean isLeaf, 
						Boolean isRoot, 
						HashSet<String> modifications, // TODO: remove it!
						Class selectedClass,
						Boolean showStateMachines) {
		super(	name, 
				property,
				firstLevelComponent, 
				dotPath, 
				isLeaf, 
				isRoot, 
//				modifications,
				selectedClass);

		children = new ArrayList<TreeObject>();

		if (showStateMachines) {
			addStateMachines();			
		}
		addSignals();

	}
	
	
	
	/**
	 * Adds the child.
	 * 
	 * @param child
	 *            the child
	 */
	public void addChild(TreeObject child) {
		children.add(child);
		child.setParent(this);
	}



	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public TreeObject[] getChildren() {
		return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
	}



	/**
	 * Removes the child.
	 * 
	 * @param child
	 *            the child
	 */
	public void removeChild(TreeObject child) {
		children.remove(child);
		child.setParent(null);
	}



	/**
	 * Checks for children.
	 * 
	 * @return true, if successful
	 */
	public boolean hasChildren() {
		return children.size() > 0;
	}



	/**
	 * Adds the state machines.
	 */
	private void addStateMachines(){
		if (isRoot()) { // if it is the selected class
			if (super.getSelectedClass()!= null) {
				// add all owned state machines
				EList<Behavior> sList = super.getSelectedClass().getOwnedBehaviors();
				for (Element element : sList) {
					if (element instanceof StateMachine) {
						if ( ((StateMachine)element).getSubmachineStates().size() < 1 ) {
							addStateMachine(this, (StateMachine)element);
						}
					}
				}
				// add all inherited state machines
				// TODO: elaborate this in order to support Modelica constructs like redeclaration
				EList<NamedElement> inheritedElements = super.getSelectedClass().getInheritedMembers();
				for (NamedElement namedElement : inheritedElements) {
					if (namedElement instanceof StateMachine) {
						if ( ((StateMachine)namedElement).getSubmachineStates().size() < 1 ) {
							addStateMachine(this, (StateMachine)namedElement);
						}
					}
				}
			}
		}
		else if (super.getProperty() != null) { // if it is a component that has a type
			Type t = super.getComponentType();
			if (t != null) {
				if (t instanceof Class) {
					EList<Behavior> sList = ((Class)t).getOwnedBehaviors();
					for (Element element : sList) {
						if (element instanceof StateMachine) {
							if ( ((StateMachine)element).getSubmachineStates().size() < 1 ) {
								addStateMachine(this, (StateMachine)element);
							}
						}
					}
					// add all inherited state machines
					// TODO: elaborate this in order to support Modelica constructs like redeclaration
					EList<NamedElement> inheritedElements = ((Classifier) t).getInheritedMembers();
					for (NamedElement namedElement : inheritedElements) {
						if (namedElement instanceof StateMachine) {
							if ( ((StateMachine)namedElement).getSubmachineStates().size() < 1 ) {
								addStateMachine(this, (StateMachine)namedElement);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Adds the state machine.
	 * 
	 * @param treeParent
	 *            the tree parent
	 * @param sm
	 *            the sm
	 * @return the tree parent
	 */
	private TreeParent addStateMachine(TreeParent treeParent, StateMachine sm){
		TreeParent smTreeParent = this.addElement(sm);
		EList<Region> rList = sm.getRegions();
		for (Region region : rList) {
			TreeParent rTreeParent = smTreeParent.addElement(region);
			addStatesFromRegion(rTreeParent, region);
		}
		return treeParent;
	}
	
	
	/**
	 * Adds the element.
	 * 
	 * @param element
	 *            the element
	 * @return the tree parent
	 */
	private TreeParent addElement(NamedElement element){
		String qName;
		if (super.getDotPath() == null || super.getDotPath().equals("")) {
			qName = StringUtls.replaceSpecChar(element.getName());
		}
		else {
			qName = super.getDotPath() + "." + StringUtls.replaceSpecChar(element.getName());			
		}
//		if (element instanceof State) {
//			if (((State)element).isSimple()) {
//				qName = qName + ".active";
//			}
//		}
		TreeParent treeParent = new TreeParent(StringUtls.replaceSpecChar(element.getName()), null, null, qName, false, false, null, super.getSelectedClass(), true);
		treeParent.setElement(element);
		this.addChild(treeParent);
		return treeParent;
	}
	
	/**
	 * Gets the modelica premitive type.
	 * 
	 * @param treeParent
	 *            the tree parent
	 * @param type
	 *            the type
	 * @return the modelica premitive type
	 */
	private Type getModelicaPremitiveType (TreeParent treeParent, String type) {
		Type realType = treeParent.getSelectedClass().getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaReal");
		Type integerType = treeParent.getSelectedClass().getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaInteger");
		Type booleanType = treeParent.getSelectedClass().getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaBoolean");
		
		if (type.equals("realType")) {return realType;}
		if (type.equals("integerType")) {return integerType;}
		if (type.equals("booleanType")) {return booleanType;}
		
		return null;
	}
	
	/**
	 * Adds the states from region.
	 * 
	 * @param treeParent
	 *            the tree parent
	 * @param r
	 *            the r
	 */
	private void addStatesFromRegion(TreeParent treeParent, Region r) {
		EList<Vertex> states = r.getSubvertices();
		for (Vertex vertex : states) {
			if (vertex instanceof State) {
					// add the state
				TreeParent sTreeParent = treeParent.addElement(vertex);
				
				// add state attributes, such as active, stime, etc.
				TreeParent stateAttr_active = new TreeParent("active", null, null, sTreeParent.getDotPath() + ".active", true, false, null, super.getSelectedClass(), true);
				stateAttr_active.setComponentType(getModelicaPremitiveType(treeParent, "booleanType"));
				
				TreeParent stateAttr_stime = new TreeParent("stime", null, null, sTreeParent.getDotPath() + ".stime", true, false, null, super.getSelectedClass(), true);
				stateAttr_stime.setComponentType(getModelicaPremitiveType(treeParent, "realType"));
				
				TreeParent stateAttr_timeAtActivation = new TreeParent("timeAtActivation", null, null, sTreeParent.getDotPath() + ".timeAtActivation", true, false, null, super.getSelectedClass(), true);
				stateAttr_timeAtActivation.setComponentType(getModelicaPremitiveType(treeParent, "realType"));
				
				TreeParent stateAttr_selfTransitionActivated = new TreeParent("selfTransitionActivated", null, null, sTreeParent.getDotPath() + ".selfTransitionActivated", true, false, null, super.getSelectedClass(), true);
				stateAttr_selfTransitionActivated.setComponentType(getModelicaPremitiveType(treeParent, "booleanType"));
				
				sTreeParent.addChild(stateAttr_active);
				sTreeParent.addChild(stateAttr_stime);
				sTreeParent.addChild(stateAttr_timeAtActivation);
				sTreeParent.addChild(stateAttr_selfTransitionActivated);
				
				
				// add composite state states
				if ( ((State)vertex).isComposite() ) {
					EList<Region> rList = ((State)vertex).getRegions();
					for (Region region : rList) {
						TreeParent rTreeParent = sTreeParent.addElement(region);
						treeParent.addStatesFromRegion(rTreeParent, region);
					}
				}
				// add sub stateMachine state states
				if (((State)vertex).isSubmachineState()) {
					StateMachine subSm = ((State)vertex).getSubmachine();
					EList<Region> rList = subSm.getRegions();
					for (Region region : rList) {
						TreeParent rTreeParent = sTreeParent.addElement(region);
						treeParent.addStatesFromRegion(rTreeParent, region);
					}
				}
			}
		}
	}
	
	
	
	
	
	
//	/**
//	 * Gets the all signals.
//	 * 
//	 * @param type
//	 *            the type
//	 * @param treeObject
//	 *            the tree object
//	 * @return the all signals
//	 */
//	private EList<String> getAllSignals(Class type, TreeObject treeObject) {
//		EList<String> list = new BasicEList<String>();
//		EList<Element> pList = ((Class)type).getOwnedElements();
//		for (Element property : pList) {
//			if (property instanceof Signal) {
//				String dotPath = treeObject.getDotPath();
//				if (dotPath == null || dotPath.equals("")) { dotPath = StringUtls.replaceSpecChar(((Signal)property).getName()); }
//				else {dotPath = dotPath + "." + StringUtls.replaceSpecChar(((Signal)property).getName()); }
//				list.add(dotPath + ".counter");
//			}
//		}
//		return list;
//	}
	
//	/**
//	 * Gets the all state machine states variables.
//	 * 
//	 * @param type
//	 *            the type
//	 * @param treeObject
//	 *            the tree object
//	 * @return the all state machine states variables
//	 */
//	private EList<String> getAllStateMachineStatesVariables(Class type, TreeObject treeObject) {
//		EList<String> list = new BasicEList<String>();
//		EList<Behavior> bList = ((Class)type).getOwnedBehaviors();
//		for (Behavior behavior : bList) {
//			if (behavior instanceof StateMachine) {
//				if (((StateMachine)behavior).getSubmachineStates().size() < 1) {
//					String dotPath = treeObject.getDotPath();
//					if (dotPath == null || dotPath.equals("")) { dotPath = StringUtls.replaceSpecChar(((StateMachine)behavior).getName()); }
//					else {dotPath = dotPath + "." + StringUtls.replaceSpecChar(((StateMachine)behavior).getName()); }
//					list.addAll(getAllStateVariablesFromStateMachine((StateMachine) behavior, dotPath));								
//				}
//			}
//		}
//		return list;
//	}
//		
	
	/**
	 * Gets the all state variables from state machine.
	 * 
	 * @param sm
	 *            the sm
	 * @param dotPath
	 *            the dot path
	 * @return the all state variables from state machine
	 */
//	private EList<String> getAllStateVariablesFromStateMachine(StateMachine sm, String dotPath) {
//		EList<String> list = new BasicEList<String>();
//		EList<Region> regions =  sm.getRegions();
//		for (Region region : regions) {
//			if (dotPath != null) {
//				dotPath = dotPath + "." +  StringUtls.replaceSpecChar(region.getName());
//			}
//			list.addAll(getAllStateVariablesFromRegion(region, dotPath));
//		}
//		return list;
//	}
//	
//	/**
//	 * Gets the all state variables from region.
//	 * 
//	 * @param r
//	 *            the r
//	 * @param dotPath
//	 *            the dot path
//	 * @return the all state variables from region
//	 */
//	private EList<String> getAllStateVariablesFromRegion(Region r, String dotPath) {
//		EList<String> list = new BasicEList<String>();
//		EList<Vertex> states = r.getSubvertices();
//		for (Vertex vertex : states) {
//			if (vertex instanceof State) {
//				String name = StringUtls.replaceSpecChar(((State)vertex).getName()); 
//				list.add(dotPath + "." + name + ".active");
//				//list.add(dotPath + "." + name + ".stime"); 
//				
//				if ( ((State)vertex).isComposite() ) {
//					EList<Region> rList = ((State)vertex).getRegions();
//					for (Region region : rList) {
//						list.addAll(getAllStateVariablesFromRegion(region, dotPath + "." + name));
//					}
//				}
//				if (((State)vertex).isSubmachineState()) {
//					StateMachine subSm = ((State)vertex).getSubmachine();
//					list.addAll(getAllStateVariablesFromStateMachine(subSm, dotPath + "." + name));
//				}
//			}
//		}
//		return list;
//	}
	
	
	
	/**
	 * Adds the signals.
	 */
	private void addSignals(){
		if (this.isRoot()) {
			if (super.getSelectedClass()!= null) {
				EList<Element> sList = super.getSelectedClass().getOwnedElements();
				for (Element element : sList) {
					if (element instanceof Signal) {
						this.addSignal((Signal)element);
					}
				}
			}
		}
		else if (super.getProperty() != null) {
			Type t = super.getProperty().getType();
			if (t != null) {
				if (t instanceof Class) {
					EList<Element> sList = t.getOwnedElements();
					for (Element element : sList) {
						if (element instanceof Signal) {
							this.addSignal((Signal)element);
						}
					}
				}
			}
		}
	}



	/**
	 * Adds the signal.
	 * 
	 * @param signal
	 *            the signal
	 */
	private void addSignal(Signal signal){		
		TreeParent signalItem = addElement(signal);
		TreeParent signalAttr_counter = new TreeParent("counter", null, null, signalItem.getDotPath() + ".counter", true, false, null, super.getSelectedClass(), true);
		signalAttr_counter.setComponentType(getModelicaPremitiveType(signalItem, "integerType"));
		signalItem.addChild(signalAttr_counter);
	}


	
	
	
	
	
//	
//
//	/**
//	 * Gets the all primitive variables dot path.
//	 * 
//	 * @param treeParent
//	 *            the tree parent
//	 * @return the all primitive variables dot path
//	 */
//	public HashSet<String> getAllPrimitiveVariablesDotPath(TreeObject treeParent){
//		HashSet<String> list = new HashSet<String>();
//		TreeObject[] children = ((TreeParent)treeParent).getChildren();
//		for (int i = 0; i < children.length; i++) {
//			if (children[i].isLeaf()) {
//				list.add(children[i].getDotPath());
//				// for Ports
//				if ( !children[i].isOfPrimitiveType() ) {
//					HashSet<String> nextLevelList = getAllPrimitiveVariablesDotPath(children[i]);
//					list.addAll(nextLevelList);
//				}
//			}
//			else {
//				HashSet<String> nextLevelList = getAllPrimitiveVariablesDotPath(children[i]);
//				list.addAll(nextLevelList);
//			}
//			
//			// collect all stateMachine state variables and Signals for the type of treeObject property
//			Property p = children[i].getProperty();
//			if (p != null) {
//				Type type = p.getType();
//				if (type != null) {
//					if (type instanceof Class) {
//						list.addAll(getAllStateMachineStatesVariables((Class)type, children[i]));
//						// add Signals
//						list.addAll(getAllSignals((Class)type, children[i]));
//					}
//				}
//			}
//		}
//		
//		// for the selected Class
//		if (treeParent.isRoot()) {
//				// add stateMachine states variables
//			list.addAll(getAllStateMachineStatesVariables(super.getSelectedClass(), treeParent));
//				// add Signals
//			list.addAll(getAllSignals(super.getSelectedClass(), treeParent));
//		}
//		return list;
//	}
//	
//	
//	/**
//	 * Gets the all linked primitive variables dot path.
//	 * 
//	 * @param p
//	 *            the p
//	 * @param treeParent
//	 *            the tree parent
//	 * @return the all linked primitive variables dot path
//	 */
//	
//	// TODO: remove it. this is now replaced by the value-proxy concept (see below). 
//	
//	public HashSet<String> getAllLinkedPrimitiveVariablesDotPath (Property p, TreeObject treeParent){
//		HashSet<String> list = new HashSet<String>();
//		TreeObject[] children = ((TreeParent)treeParent).getChildren();
//		for (int i = 0; i < children.length; i++) {
//			if (children[i].isLeaf()) {
//				Property childProperty = children[i].getProperty();
//				if (childProperty != null) {
//					// get Dependencies from the passed property
//					EList<Dependency> dep = p.getClientDependencies();
//					for (Dependency dependency : dep) {
//						EList<Element> targets = dependency.getTargets();
//						for (Element element : targets) {
//							if (element instanceof Property) {
////								System.out.println("element " + ((Property) element).getName());
////								System.out.println("children[i].getProperty():  " + children[i].getProperty());
//								if ( ((Property)element) == children[i].getProperty()) {
//									list.add(children[i].getDotPath());
//									
//									// for Ports
//									if ( !children[i].isOfPrimitiveType() ) {
//										HashSet<String> nextLevelList = getAllPrimitiveVariablesDotPath(children[i]);
//										list.addAll(nextLevelList);
//									}
//								}
//							}
//						}
//					}
//					
//					// get Dependencies from the iterated property
//					EList<Dependency> childDep = childProperty.getClientDependencies();
//					for (Dependency dependency : childDep) {
//						EList<Element> targets = dependency.getTargets();
//						for (Element element : targets) {
//							if (element instanceof Property) {
////								System.out.println("element " + ((Property) element).getName());
////								System.out.println("children[i].getProperty():  " + children[i].getProperty());
//								if ( ((Property)element) == p ) {
//									list.add(children[i].getDotPath());
//									
//									// for Ports
//									if ( !children[i].isOfPrimitiveType() ) {
//										HashSet<String> nextLevelList = getAllPrimitiveVariablesDotPath(children[i]);
//										list.addAll(nextLevelList);
//									}
//								}
//							}
//						}
//					}
//
//				}
//			}
//			else {
//				HashSet<String> nextLevelList = getAllLinkedPrimitiveVariablesDotPath(p,children[i]);
//				list.addAll(nextLevelList);
//			}
//		}
//		
//		// TODO: do the same for states of statemachines.
//		
//		return list;
//	}
//	
//	
//	
//	
//	
//	/**
//	 * Gets the value bindings.
//	 *
//	 * @param property the property
//	 * @param valueBindingStereotypeQName the value binding stereotype q name
//	 * @param valueBindingPropertyName the value binding property name
//	 * @return the value bindings
//	 */
//	public HashSet<Property> getValueBindings(Property property, String valueBindingStereotypeQName, String valueBindingPropertyName){
//		HashSet<Property> list = new HashSet<Property>();
//		//final String stereotypeQName = "ModelicaMLTesting::ValueBinding::ValueClient";
//		String stereotypeQName = valueBindingStereotypeQName;
//		Stereotype valueBindingStereotype = property.getAppliedStereotype(stereotypeQName);
//		if (valueBindingStereotype != null) {
//			//Object stereotypeValue = property.getValue(valueBindingStereotype, "obtainsValueFrom");
//			Object stereotypeValue = property.getValue(valueBindingStereotype, valueBindingPropertyName);
//			if (stereotypeValue instanceof EList) {
//				for (Object object : (EList)stereotypeValue) {
//					EList<EObject> crossreferencesList = ((EObject)object).eCrossReferences();
//					for (EObject eObject : crossreferencesList) { // to get the Property that the stereotype is applied to
//						if (eObject instanceof Property) {
//							//System.err.println(property.getName() + " has the proxy: " + ((Property)eObject).getName());
//							list.add((Property)eObject); // add the proxy Property
//						}
//					}
//				}
//			}
//		}
//		return list;
//	}
//	
//	/**
//	 * Gets the all linked primitive variables dot path through proxy.
//	 *
//	 * @param p the p
//	 * @param listOfProxies the list of proxies
//	 * @param treeParent the tree parent
//	 * @return the all linked primitive variables dot path through proxy
//	 */
//	public HashSet<String> getAllLinkedPrimitiveVariablesDotPathThroughProxy (Property p, HashSet<Property> listOfProxies, TreeObject treeParent){
//		HashSet<String> list = new HashSet<String>();
//		TreeObject[] children = ((TreeParent)treeParent).getChildren();
//		for (int i = 0; i < children.length; i++) {
//			if (children[i].isLeaf()) {
//				Property childProperty = children[i].getProperty();
//				if (childProperty != null && !childProperty.equals(p)) {
//					
//					//System.err.println("childProperty: " + childProperty.getName());
//					
//					String valueBindingStereotypeQName = "ModelicaMLTesting::ValueBinding::ValueProvider"; 
//					String valueBindingPropertyName = "providesValueFor";
//					
//					if (childProperty.getAppliedStereotype(valueBindingStereotypeQName) != null) {
//						//System.err.println("childProperty has stereotype");
//						HashSet<Property> listOfChildPropertyProxies = getValueBindings(childProperty, valueBindingStereotypeQName, valueBindingPropertyName);
//						
////						System.err.println("listOfProxies: " + listOfProxies);
////						System.err.println("listOfChildPropertyProxies: " + listOfChildPropertyProxies);
//						
//						for (Property property : listOfChildPropertyProxies) {
//							if (listOfProxies.contains(property)) {
////								System.err.println("the list of proxies contains the proxy '"+property.getName()+"' of the childProperty '" + childProperty.getName() + "' ");
//								list.add(children[i].getDotPath());
//								// TODO: if it is a state then add ".active" to the dotPath.
//							}
//						}
//					}
//				}
//			}
//			else {
//				HashSet<String> nextLevelList = getAllLinkedPrimitiveVariablesDotPathThroughProxy(p, listOfProxies ,children[i]);
//				list.addAll(nextLevelList);
//			}
//		}
//		
//		// TODO: do the same for states of statemachines?
//		
//		return list;
//	}
	
}
