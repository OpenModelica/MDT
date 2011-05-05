package org.openmodelica.modelicaml.common.instantiation;

import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.StringUtls;

	public class TreeUtls {
		
	
//	// TODO: remove it and use the getValueMediators() instead
//	public static HashSet<Property> getValueBindings(Property property, String valueBindingStereotypeQName, String valueBindingPropertyName){
//		HashSet<Property> list = new HashSet<Property>();
//
//		String stereotypeQName = valueBindingStereotypeQName;
//		Stereotype valueBindingStereotype = property.getAppliedStereotype(stereotypeQName);
//		
//		if (valueBindingStereotype != null) {
//			Object stereotypeValue = property.getValue(valueBindingStereotype, valueBindingPropertyName);
//			if (stereotypeValue instanceof EList) {
//				for (Object object : (EList)stereotypeValue) {
//					EList<EObject> crossreferencesList = ((EObject)object).eCrossReferences();
//					for (EObject eObject : crossreferencesList) { // to get the Property that the stereotype is applied to
//						if (eObject instanceof Property) {
//							list.add((Property)eObject); // add the proxy Property
//						}
//					}
//				}
//			}
//		}
//		return list;
//	}
	
	public static EList<Element> getValueMediators(Element element, String valueBindingStereotypeQName, String valueBindingPropertyName){
		EList<Element> list = new BasicEList<Element>();

		String stereotypeQName = valueBindingStereotypeQName;
		Stereotype valueBindingStereotype = element.getAppliedStereotype(stereotypeQName);
		
		if (valueBindingStereotype != null) {
			Object stereotypeValue = element.getValue(valueBindingStereotype, valueBindingPropertyName);
			if (stereotypeValue instanceof EList) {
				for (Object object : (EList)stereotypeValue) {
					if (object instanceof EObject) {
						Element valueMediator = UMLUtil.getBaseElement((EObject)object);
						list.add(valueMediator);
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * Gets the all linked primitive variables dot path through proxy.
	 *
	 * @param p the p
	 * @param listOfReqPropertyProxies the list of proxies
	 * @param treeParent the tree parent
	 * @return the all linked primitive variables dot path through proxy
	 */
//	public static HashSet<String> getAllLinkedPrimitiveVariablesDotPathThroughProxy (Property p, EList<Element> listOfReqPropertyProxies, TreeObject treeParent){
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
//						EList<Element> listOfChildPropertyProxies = getValueMediators(childProperty, valueBindingStereotypeQName, valueBindingPropertyName);
//						
////						System.err.println("listOfProxies: " + listOfProxies);
////						System.err.println("listOfChildPropertyProxies: " + listOfChildPropertyProxies);
//						
//						for (Element property : listOfChildPropertyProxies) {
//							if (listOfReqPropertyProxies.contains(property)) {
////								System.err.println("the list of proxies contains the proxy '"+property.getName()+"' of the childProperty '" + childProperty.getName() + "' ");
//								list.add(children[i].getDotPath());
//								// TODO: if it is a state then add ".active" to the dotPath.
//							}
//						}
//					}
//				}
//			}
//			else {
//				HashSet<String> nextLevelList = getAllLinkedPrimitiveVariablesDotPathThroughProxy(p, listOfReqPropertyProxies ,children[i]);
//				list.addAll(nextLevelList);
//			}
//		}
//		
//		// TODO: do the same for states of statemachines?
//		
//		return list;
//	}
	
	public static EList<TreeObject> getValueProviders(Element valueClient, EList<Element> listOfMediators, TreeObject treeParent){
		EList<TreeObject> list = new BasicEList<TreeObject>();
		TreeObject[] children = ((TreeParent)treeParent).getChildren();
		for (int i = 0; i < children.length; i++) {
//			if (children[i].isLeaf()) {
				Property childProperty = children[i].getProperty();
				if (childProperty != null && !childProperty.equals(valueClient)) {
					
					//System.err.println("childProperty: " + childProperty.getName());
					
					String valueBindingStereotypeQName = Constants.stereotypeQName_ValueProvider; 
					String valueBindingPropertyName = Constants.stereotypeQName_ValueProvider_providesValueFor;
					
					if (childProperty.getAppliedStereotype(valueBindingStereotypeQName) != null) {
						//System.err.println("childProperty has stereotype");
						EList<Element> listOfChildPropertyProxies = getValueMediators(childProperty, valueBindingStereotypeQName, valueBindingPropertyName);
						
//						System.err.println("listOfProxies: " + listOfProxies);
//						System.err.println("listOfChildPropertyProxies: " + listOfChildPropertyProxies);
						
						for (Element property : listOfChildPropertyProxies) {
							if (listOfMediators.contains(property)) {
//								System.err.println("the list of proxies contains the proxy '"+property.getName()+"' of the childProperty '" + childProperty.getName() + "' ");
								list.add(children[i]);
//								System.err.println(children[i].getName());
							}
						}
					}
				}
//			}
//			else {
				if ( !children[i].isLeaf() ) { // TODO: use getChlidren?
					EList<TreeObject> nextLevelList = getValueProviders(valueClient, listOfMediators, children[i]);
					list.addAll(nextLevelList);
				}
//			}
		}
		
		// TODO: do the same for states of statemachines?
		
		return list;
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
	
	/**
	 * Gets the all linked primitive variables dot path.
	 * 
	 * @param p
	 *            the p
	 * @param treeParent
	 *            the tree parent
	 * @return the all linked primitive variables dot path
	 */
//	
//	// TODO: remove it. this is now replaced by the value-proxy concept (see below). 
//	
//	public static HashSet<String> getAllLinkedPrimitiveVariablesDotPath (Property p, TreeObject treeParent){
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
