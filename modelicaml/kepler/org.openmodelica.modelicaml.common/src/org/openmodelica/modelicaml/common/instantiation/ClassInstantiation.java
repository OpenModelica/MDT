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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.valuebindings.helpers.BindingsDataCollector;

public class ClassInstantiation {

	private Class selectedClass;
	
	private TreeParent invisibleRoot;
	private TreeParent treeRoot;
	
	private HashSet<TreeObject> allTreeObjects = new HashSet<TreeObject>();

	private HashMap<Element,HashSet<TreeObject>> elementToInstantiationTreeObjects = new HashMap<Element,HashSet<TreeObject>>();
	
	// all mediators that were pre-collected
	private HashSet<Element> allMediators;
	
	/** The action show state machines. */
	private boolean includeStateMachines;
	
	/** The action show state machines. */
	private boolean buildPredefinedTypesProperties;

	public HashMap<String,Type> redeclaredComponentTypes = new HashMap<String,Type>();
	public HashMap<String,String> firstLevelComponentsModifications = new HashMap<String,String>();
	public HashMap<String,NamedElement> modificationSource = new HashMap<String,NamedElement>();
	
	public HashMap<String,String> modifications = new HashMap<String,String>();
	
	public HashSet<Property> selectedClassOwnedComponents = new HashSet<Property>();
	public HashMap<Generalization,EList<Property>> selectedClassExtensionsReferenceForInheritedComponents = new HashMap<Generalization,EList<Property>>();
	
	private Element umlModel= null;
	
	//  value bindings data collection
	private BindingsDataCollector valueBindingsDataCollector; 

	private boolean recollectMediatorsIfEmpty = false;


	
	public ClassInstantiation(Class selectedClass, 
			boolean includeStateMachines, 
			boolean buildPredefinedTypesProperties, 
			HashSet<Element> preCollectedMediators, 
			boolean reCollectMediatorsIfEmpty
			){
		
		this.selectedClass = selectedClass;
		
		this.setAllMediators(preCollectedMediators);
		this.setRecollectMediatorsIfEmpty(reCollectMediatorsIfEmpty);
		
		this.setIncludeStateMachines(includeStateMachines);
		this.setBuildPredefinedTypesProperties(buildPredefinedTypesProperties);
	}
	

	/*
	 * This constructor is used for instantiations that 
	 * get added sub graphs and will not create their own graph
	 */
	public ClassInstantiation(Class selectedClass, HashSet<Element> preCollectedMediators, boolean reCollectMediatorsIfEmpty){
		this.selectedClass = selectedClass;
		
		this.setAllMediators(preCollectedMediators);
		this.setRecollectMediatorsIfEmpty(reCollectMediatorsIfEmpty);

		this.setIncludeStateMachines(includeStateMachines);
		this.setBuildPredefinedTypesProperties(buildPredefinedTypesProperties);
		
		invisibleRoot = new TreeParent("", null, null, "", false, false, new HashSet<String>(), selectedClass, false);
		
		String name = "Instantiated '" + selectedClass.getName() +"' ";
		treeRoot = new TreeParent(name, null, null, "", false, true, new HashSet<String>(), selectedClass, includeStateMachines);
		invisibleRoot.addChild(treeRoot);
		allTreeObjects.add(treeRoot);
		addToElementToInstantiationTreeObjectMap(selectedClass, treeRoot);
	}

	
	
	public void createTree(){
		if (selectedClass != null) {
			invisibleRoot = new TreeParent("", null, null, "", false, false, new HashSet<String>(), selectedClass, false);
			
			String name = "Instantiated '" + selectedClass.getName() +"' ";
			treeRoot = new TreeParent(name, null, null, "", false, true, new HashSet<String>(), selectedClass, includeStateMachines);
			invisibleRoot.addChild(treeRoot);
			allTreeObjects.add(treeRoot);
			addToElementToInstantiationTreeObjectMap(selectedClass, treeRoot);
			
			// get class components in order to know which other attributes are inherited.
			selectedClassOwnedComponents.addAll(this.selectedClass.getOwnedAttributes());

			// associate all inherited components to the selected class extension (there may be multiple) in order to be able to determine the location for storing modifications
			EList<Generalization> extendsRelations = this.selectedClass.getGeneralizations(); // TODO: filter on ModelicaML stereotypes
			for (Generalization generalization : extendsRelations) {
				EList<Element> targets = generalization.getTargets(); // get the extended classes
				for (Element element : targets) {
					if (element instanceof Class) { // if it is a class
						EList<Property> inheritedAtrtibutesList = getAllInheritedAttributes((Class)element, ((Class)element).getOwnedAttributes());
						selectedClassExtensionsReferenceForInheritedComponents.put(generalization, inheritedAtrtibutesList);
					}
				}
			}
			
			// build tree starting from the selected class node.
			buildNextTreeLevel(this.selectedClass, null, treeRoot , "");

			/*
			 * Note, clients should call this method explicitly in order to 
			 * make sure that this data is only collected when required.
			 */
			// Get value bindings data and create tree nodes for predefined properties if they are used as clients or providers
//			collectValueClientsAndProvidersFromUmlModel();

//			// indicate the size of the tree
//			int numberOfTreeNodes = getAllTreeObjects().size();
//			treeRoot.setName(treeRoot.getName() + " (" + numberOfTreeNodes + " nodes)");
		}
	}
	
	
	/**
	 * Gets the all inherited attributes.
	 * 
	 * @param aClass
	 *            the a class
	 * @param passedAList
	 *            the passed a list
	 * @return the all inherited attributes
	 */
	public EList<Property> getAllInheritedAttributes(Classifier aClass, EList<Property> passedAList) {
		
		// TODO: verify if this works correctly for Modelica inheritance concept.
		HashSet<Property> mergedSet = new HashSet<Property>();
		mergedSet.addAll(passedAList);
	
		HashSet<Property> finalSet = new HashSet<Property>();
		EList<Property> finalList = new BasicEList<Property>();
	
		EList<Generalization> extendsRelations = aClass.getGeneralizations(); // TODO: filter on ModelicaML stereotypes
	
		if (extendsRelations.size() < 1) { // if there there is no inheritance then just return the passedAList
			finalList.addAll(passedAList);
		} 
		else {	// if there are inheritance then merge
			for (Generalization generalization : extendsRelations) {
				EList<Element> targets = generalization.getTargets();
				for (Element element : targets) {
					if (element instanceof Classifier) { // if the target is a class TODO: filter for applied ModelicaML stereotypes
						EList<Property> inheritedProperties = ((Classifier) element).getAttributes(); // get the attributes of the class
						
						mergedSet.addAll(inheritedProperties); // add all class attribute to list
						
						for (Property propertyInherited : inheritedProperties) { // iterate over the class attributes
							String nameInheritedProperty = StringUtls.replaceSpecChar(propertyInherited.getName());
							for (Property propertyPassed : passedAList) { // iterate over the passed list
								String namePropertyPassed = StringUtls.replaceSpecChar(propertyPassed.getName());
								if (nameInheritedProperty.equals(namePropertyPassed)) { // if a duplicate is found ...
									mergedSet.remove(propertyInherited); // remove duplicate from the final list.
									// System.out.println("removed: " + propertyInherited.getName());
								}
							}
						}
						EList<Property> list = new BasicEList<Property>();
						list.addAll(mergedSet);
						finalSet.addAll(getAllInheritedAttributes((Classifier) element, list)); // recursive call
					}
				}
			}
		}
		finalList.addAll(finalSet);
		return finalList;
	}


	public void buildNextTreeLevel(Class aClass, Property firstLevelComponent, TreeParent parent, String dotPath) {
		
		// collect selected class inherited modifications. add to modifications list. If there are redeclare modifications add them to redeclaredTypes
		addInheritedClassModifications(aClass, dotPath);
		
		EList<Property> pList = getClassComponents(aClass); // get all (i.e. also inherited) class components
	
		for (Property property : pList) {
	
			if (firstLevelComponent == null) { // this is a very first component
				firstLevelComponent = property;
			}
	
			String newDotPath = "";
			String name = StringUtls.replaceSpecChar(property.getName());
			
			if (!dotPath.equals("")) {
				newDotPath = dotPath + "." + name;
			} else {
				newDotPath = name;
			}
	
			if (isPrimitiveType(property)) { // if it is a primitive which value can be modified
	
				addClassComponentsModifications(property, dotPath);
				HashSet<String> modifications = null;
				
				TreeParent child = new TreeParent(name, property, firstLevelComponent, newDotPath, true, false, modifications, selectedClass, includeStateMachines);
				parent.addChild(child);
				allTreeObjects.add(child);
				addToElementToInstantiationTreeObjectMap(property, child);
				
				// set the final modification right hand
				child.setModificationRightHand(this.modifications.get(newDotPath));
				child.setModificationSource(this.modificationSource.get(newDotPath));
	
				// set the store location for the tree object.
				setModificationStoreLocation(child);
	
				if (isBuildPredefinedTypesProperties()) {
					/*
					 * Note, the building of predefined properties of primitive variables 
					 * is typically disabled by default because it blows up the tree by factor 5 or more ... 
					 */
					
					// create predefined properties for Modelica real, string, integer and boolean.
					createPredefinedTypeProperties(property, firstLevelComponent, child, newDotPath, null); // predefinedPropertyName = null means create all predefined properties
				}
				
			} else { // non-primitive item
	
				addClassComponentsModifications(property, dotPath);
	
				Type pType = null;
				pType = this.redeclaredComponentTypes.get(newDotPath);
				if (pType == null) {
					pType = property.getType();
				}
				
				HashSet<String> modifications = null;
				TreeParent newParent = new TreeParent(name, property, firstLevelComponent, newDotPath, false, false, modifications, selectedClass, includeStateMachines);
				parent.addChild(newParent);
				allTreeObjects.add(newParent);
				addToElementToInstantiationTreeObjectMap(property, newParent);
				
				// set the final modification right hand
				newParent.setModificationRightHand(this.modifications.get(newDotPath));
				newParent.setModificationSource(this.modificationSource.get(newDotPath));
				
				// set the store location for the tree object.
				setModificationStoreLocation(newParent);
	
				// make sure that the tree object gets its redeclared type.
				newParent.setComponentType(pType); 
				
				// TODO: // prevent endless looping, implement it correctly!
				if (pType instanceof Class && (Class) pType != aClass && !(pType instanceof Stereotype)) { 
					buildNextTreeLevel((Class) pType, firstLevelComponent, newParent, newDotPath);
				}
			}
			
			// reset the firstLevelComponent. This ensures that only the very first level components are passed.
			if (firstLevelComponent == property) {
				firstLevelComponent = null;
			}
		}
	}


	/**
	 * Gets the component modifications.
	 * 
	 * @param component
	 *            the component
	 * @return the component modifications
	 */
//	@SuppressWarnings("unchecked")
//	public HashSet<String> getComponentModifications(Property component) {
//		HashSet<String> mList = new HashSet<String>();
//		String stereotypeName = getFirstModelicaMLComponentStereotypeName(component);
//		List<String> modificationList = new ArrayList<String>();
//		if (stereotypeName != null) {
//			Object o = UmlServices.getStereotypeValue((Element) component, getFirstModelicaMLComponentStereotypeName(component), "modification");
//			if (o instanceof List<?>) {
//				modificationList = (List<String>) o;
//			}
//			for (String string : modificationList) {
//				mList.add(StringUtls.removeOutterBraces(string));
//			}
//		}
//		return mList;
//	}
	
	@SuppressWarnings("unchecked")
	public HashSet<String> getComponentModifications(Property component) {
		HashSet<String> mList = new HashSet<String>();
		Stereotype stereotype = ModelicaMLServices.getFirstModelicaMLComponentStereotype(component);
		List<String> modificationList = new ArrayList<String>();
		if (stereotype != null) {
			Object o = component.getValue(stereotype, Constants.propertyName_modification);
			if (o instanceof List<?>) {
				modificationList = (List<String>) o;
			}
			for (String string : modificationList) {
				mList.add(StringUtls.removeOutterBraces(string));
			}
		}
		return mList;
	}


	private void addInheritedClassModifications(Class aClass, String dotPath){
		EList<Generalization> extendsRelations = aClass.getGeneralizations();
		
		for (Generalization generalization : extendsRelations) {
			EList<String> modList = getGeneralizationModifications(generalization);
			for (String string : modList) {
				addToModificationList(string, dotPath, aClass);
			}
			
			// recursive call to the next inheritance level
			EList<Element> targets = generalization.getTargets();
			for (Element element : targets) {
				if (element instanceof Class) {
					addInheritedClassModifications((Class)element, dotPath); 
				}
			}
		}
	}
	
	private void addClassComponentsModifications(Property property, String dotPath){
		String prefix = "";
		if (!dotPath.trim().equals("")) {
			prefix = dotPath + ".";
		}
		HashSet<String> modList = getComponentModifications(property);
		String propertyName = prefix + StringUtls.replaceSpecChar(property.getName());
		for (String string : modList) {
			addToModificationList(string, propertyName, property);
		}
	}
	
	private void addToModificationList(String string, String dotPath, NamedElement sourceOfModification) {
		String prefix = "";
		if ( !dotPath.trim().equals("")) {
			prefix = dotPath + ".";
		}
		
		if ( isSimpleComponentValueBinding(string) ) { // if it is a simple component binding, e.g. comp1 = comp2.value ... 
			String[] splitted = string.trim().split("=");
			if (splitted.length > 1) {
				String leftHand = prefix + splitted[0].trim();
				//String rightHand = splitted[1].trim();
				String rightHand = getRightHandAfterFirstEqSign(splitted[0], string.trim());
				if ( !this.firstLevelComponentsModifications.containsKey(leftHand) ) { // TODO: do we need it?!
					this.firstLevelComponentsModifications.put(leftHand, rightHand); // contains modifications that are defined first level components
					// Add to modification source in order to always know where this modification came from
					this.modificationSource.put(leftHand, sourceOfModification);
				}
				if ( !this.modifications.containsKey(leftHand) ) {
					this.modifications.put(leftHand, rightHand); // contains all final modifications.
					// Add to modification source in order to always know where this modification came from
					this.modificationSource.put(leftHand, sourceOfModification);
				}
			}
		}
		else { // match others, e.g. redeclaration of the component type
			addRedeclaredComponentTypeEntry(string, dotPath, sourceOfModification);
		}
	}
	
	private String getRightHandAfterFirstEqSign(String lefthand, String entireString){
		String righthandString = entireString;
		return righthandString.replaceFirst(lefthand, "").trim().replaceFirst("=", "").trim();
	}
	
	private void addRedeclaredComponentTypeEntry(String string, String dotPath, NamedElement sourceOfModification){
		String prefix = "";
		if ( !dotPath.trim().equals("")) {
			prefix = dotPath + ".";
		}
		//match a component type redeclaration
		if (string.contains("redeclare ")) {
			String s = StringUtls.removeOutterBraces(string).replaceFirst("redeclare ", "").replaceFirst(" each ", "").replaceFirst(" final ", "").trim();
			String[] splitted = s.split(" ");
			
			if (splitted.length == 2) {
				String typeDotPath = splitted[0].trim();
				String componentDotPath = prefix + splitted[1].trim();
				
				Type type = findType(sourceOfModification, typeDotPath); // sourceOfModification is used to get the top level model in order to search fo the type
				if (type != null) {
					if ( !redeclaredComponentTypes.containsKey(componentDotPath) ) {
						redeclaredComponentTypes.put(componentDotPath, type);
						// Add to modification source in order to always know where this modification came from
						this.modificationSource.put(componentDotPath, sourceOfModification);
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Checks if is primitive type.
	 * 
	 * @param property
	 *            the property
	 * @return the boolean
	 */
	private Boolean isPrimitiveType(Property property) {
		Boolean result = false;
		Type pType = property.getType();
		if (pType != null) {
			if (pType instanceof PrimitiveType || pType instanceof Enumeration) {
				return result = true;
			}
		}
		return result;
	}

	/**
	 * Gets the class components.
	 * 
	 * @param aClass
	 *            the a class
	 * @return the class components
	 */
	private EList<Property> getClassComponents(Class aClass) {
		// EList<Property> pList = aClass.getAllAttributes(); // OLD
		EList<Property> pFinalList = getAllInheritedAttributes(aClass, aClass.getOwnedAttributes());
		// TODO: implement here all Modelica specific filtering of components, such as redeclaration and merging of duplicates if they are identical.
		return pFinalList;
	}

	private void setModificationStoreLocation(TreeParent treeItem){
		if (treeItem.getFirstLevelComponent() != null) { // if first level component is defined
			if (selectedClassOwnedComponents.contains(treeItem.getFirstLevelComponent()) ) { //check if this is an owned attribute of the selected class
//				System.err.println("Store location for '" + treeItem.getName() + "' is the selected-class owned component: " + treeItem.getFirstLevelComponent().getName());
				treeItem.setModificationStoreLocation(treeItem.getFirstLevelComponent());
			}
			else {
				Set<Generalization> keys = selectedClassExtensionsReferenceForInheritedComponents.keySet();
				for (Generalization generalization : keys) {
					//get the respective generalization
					if (selectedClassExtensionsReferenceForInheritedComponents.get(generalization).contains(treeItem.getFirstLevelComponent())) {
//						System.err.println("Store for " + treeItem.getName() + " location is the selected-class generalization to " + ((NamedElement)generalization.getTargets().get(0)).getQualifiedName());
						treeItem.setModificationStoreLocation(generalization);
					}
				}
			}
		}
	}
	

	private TreeObject createPredefinedTypeProperties(Property property, Property firstLevelComponent, TreeParent parent, String dotPath, String predefinedPropertyName) {
		TreeObject createdTreeObject = null;
		
		Type pType = null;
		pType = this.redeclaredComponentTypes.get(dotPath);
		
		if (pType == null) {
			pType = property.getType();
		}

		if (pType != null) {
			if (pType instanceof Classifier) {
				EList<Property> ModelicaPredefinedTypeProperties = ((Classifier) pType).getAllAttributes();
				for (Property p : ModelicaPredefinedTypeProperties) {

					boolean skip = false;
					if (predefinedPropertyName != null && !StringUtls.replaceSpecChar(p.getName()).equals(predefinedPropertyName)) {
						skip = true;
					}
					
					if (!skip) {
						String newDotPath = "";
						String name = StringUtls.replaceSpecChar(p.getName());
						if (!dotPath.equals("")) {
							newDotPath = dotPath + "." + name;
						} else {
							newDotPath = name;
						}

						HashSet<String> modifications = null;
						TreeParent child = new TreeParent(name, p, firstLevelComponent, newDotPath, true, false, modifications, selectedClass, false);
						parent.addChild(child);
						allTreeObjects.add(child);
						addToElementToInstantiationTreeObjectMap(p, child);
						
						// set the final modification right hand
//						child.setFinalModificationRightHand(this.modifications.get(newDotPath));
						child.setModificationRightHand(this.modifications.get(newDotPath));
						child.setModificationSource(this.modificationSource.get(newDotPath));

						child.setIsPredefinedModelicaProperty(true);
						
						// set the store location for the tree object.
						setModificationStoreLocation(child);
						
						createdTreeObject = child;
					}
				}
			}
		}
		return createdTreeObject;
	}

	
	
	private Type findType(Element sourceOfRedeclaration, String dotPath){
		Model model = sourceOfRedeclaration.getModel();
		Iterator<EObject> i = model.eAllContents();
		String typeDotPath = "";
		while (i.hasNext()) {
			EObject object = i.next() ;
			if (object instanceof Class )
				typeDotPath = StringUtls.replaceSpecCharExceptThis(((Class)object).getQualifiedName(), "::").replaceAll("::", ".");
				if (typeDotPath.equals(dotPath)) {
					return (Type) object;
				}
		}
		return null;
	}
	
	private Boolean isSimpleComponentValueBinding(String modification){
		if (modification.contains("redeclare ")) {
			return false;
		}
		return true;
	}
	
	

//	/**
//	 * Gets the generalization stereotype.
//	 * 
//	 * @param extendsRelation
//	 *            the extends relation
//	 * @return the generalization stereotype
//	 */
//	private static Stereotype getGeneralizationStereotype(Generalization extendsRelation) {
//		Stereotype stereotype = null;
//		if (UmlServices.hasStereotype((Element) extendsRelation, "ExtendsRelation"))
//			return stereotype = extendsRelation.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::ExtendsRelation");
//		
//		if (UmlServices.hasStereotype((Element) extendsRelation, "TypeRelation"))
//			return stereotype = extendsRelation.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::TypeRelation");
//		
//		return stereotype;
//	}

	/**
	 * Gets the generalization modifications.
	 * 
	 * @param extendsRelation
	 *            the extends relation
	 * @param stereotype
	 *            the stereotype
	 * @return the generalization modifications
	 */
//	@SuppressWarnings("unchecked")
//	private EList<String> getGeneralizationModifications(Generalization extendsRelation, Stereotype stereotype) {
//		EList<String> mList = new BasicEList<String>();
//		
//		List<String> modificationList = new ArrayList<String>();
//		if (stereotype != null) {
//			Object o = UmlServices.getStereotypeValue((Element) extendsRelation, stereotype.getName(), "modification");
//			if (o instanceof List<?>) {
//				modificationList = (List<String>) o;
//			}
//			mList.addAll(modificationList);
//		}
//		return mList;
//	}
	
	@SuppressWarnings("unchecked")
	private EList<String> getGeneralizationModifications(Generalization extendsRelation) {
		EList<String> mList = new BasicEList<String>();
		
		List<String> modificationList = new ArrayList<String>();
		
		Stereotype stereotype = extendsRelation.getAppliedStereotype(Constants.stereotypeQName_ExtendsRelation);
		if (stereotype == null) {
			stereotype = extendsRelation.getAppliedStereotype(Constants.stereotypeQName_TypeRelation);
		}
		
		if (stereotype != null) {
			Object o = extendsRelation.getValue(stereotype, Constants.propertyName_modification);
			if (o instanceof List<?>) {
				modificationList = (List<String>) o;
			}
			mList.addAll(modificationList);
		}
		return mList;
	}

//	/**
//	 * Gets the first modelica ml component stereotype name.
//	 * 
//	 * @param property
//	 *            the property
//	 * @return the first modelica ml component stereotype name
//	 */
//	private String getFirstModelicaMLComponentStereotypeName(Property property) {
//		String name = null;
//		if (UmlServices.hasStereotype((Element) property, "Component")) return "Component";
//		if (UmlServices.hasStereotype((Element) property, "Variable")) return "Variable";
//		if (UmlServices.hasStereotype((Element) property, "RequirementInstance")) return "RequirementInstance";
//		if (UmlServices.hasStereotype((Element) property, "CalculatedProperty")) return "CalculatedProperty";
//		return name;
//	}
	
//	private Stereotype getFirstModelicaMLComponentStereotype(Property property) {
//		Stereotype stereotype = null;
//		
//		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_Component);
//		if (stereotype != null) { return stereotype; }
//		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_Variable);
//		if (stereotype != null) { return stereotype; }
//		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_RequirementInstance);
//		if (stereotype != null) { return stereotype; }
//		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_CalculatedProperty);
//		if (stereotype != null) { return stereotype; }
//		return stereotype ;
//	}
	
	
	// Value Clients or Providers handling ************************************************************************
	
	private void setValueClientOrProviderIndicator(TreeParent item){
		TreeObject[] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject treeObject = children[i];
			
			boolean isClient = false;
			boolean isProvider = false;
			
			// client
			if (valueBindingsDataCollector.getReferencedClients().contains(treeObject.getUmlElement())) {
				treeObject.setIsValueClient(true);
				isClient = true;
			}
			if (valueBindingsDataCollector.getReferencedRequiredClients().contains(treeObject.getUmlElement())) {
				treeObject.setValueClient_required(true);
				isClient = true;
			}

			if (isClient) {
				/*
				 * If there are predefined properties used in the client operation
				 * then we need to add the predefined properties to the tree in order to make sure that the 
				 * binding code will be created for them.
				 */
				addClientPredefinedProperties(treeObject);	
			}
			
			// provider
			if (valueBindingsDataCollector.getReferencedProviders().contains(treeObject.getUmlElement())) {
				treeObject.setIsValueProvider(true);
				isProvider = true;
			}

			if (isProvider) {
				/*
				 * It is actually not necessary to create predefined properties for providers. 
				 * Because the binding code is derived based on
				 * the found provider (which cannot be a predefined property) and its 
				 * provider operation.
				 */
			}
			
			// recursive call
			if (treeObject instanceof TreeParent) {
				setValueClientOrProviderIndicator((TreeParent) treeObject);
			}
		}

	}
	
	private void addClientPredefinedProperties(TreeObject upperClientTeeItem){

		Element element = upperClientTeeItem.getProperty();
		HashSet<String> operations = new HashSet<String>();

		if (element != null) {
			// add all client operations
			if (valueBindingsDataCollector.getReferencedClients().contains(element)) {
				HashSet<String> clientOperations = valueBindingsDataCollector.getClientOperations().get(element);
				if (clientOperations != null) {
					operations.addAll(clientOperations);
				}
			}
		}
		
		for (String operation : operations) {
			String[] scriptCodeSplitted = operation.split(";");
			if ( scriptCodeSplitted.length > 0) { // if there is at least one line that ends with ";"
				for (int i = 0; i < scriptCodeSplitted.length; i++) { // the next will always overwrite the previous, i.e. the last one is always taken. 
					
					if ( !scriptCodeSplitted[i].trim().equals("") ) { // if it is not an empty line 
						String[] bindingEqationParts = scriptCodeSplitted[i].split("=");
						if (bindingEqationParts.length == 2 ) {// it is a binding equation with left and right part 
							
							// get the left and right parts and trim them
							String leftHand = bindingEqationParts[0].trim();
//							String rightHand = bindingEqationParts[1].trim();
							
							String expandedLeftHand = leftHand.replaceFirst(Constants.MACRO_clientPath, upperClientTeeItem.getDotPath()).trim();
							
							List<String> clientData = getDotPathWithoutPredefinedPropertyName(expandedLeftHand);
							// if a predefined property is referenced in the left hand 
							if (clientData != null && clientData.size() == 2) {
								
								String actualClientDotPath = clientData.get(0);
								String predefinedPropertyName = clientData.get(1);
								
								// get the actual client from the tree
								TreeObject actualClient = getTreeObject(actualClientDotPath);
								
								if (actualClient != null) {
									/*
									 * If the client is a primitive variable 
									 * AND if it does not have such a child yet.
									 * This is important because collectBindingData operation may be called several time 
									 * and will lead to the creation of multiple predefined property tree objects for the same
									 * property.  
									 */
									if (isPrimitiveType(actualClient.getProperty()) && actualClient instanceof TreeParent && !hasChild((TreeParent) actualClient, predefinedPropertyName)) {
										
										// create only a tree node for the referenced predefined properties
										TreeObject createdTreeObject = createPredefinedTypeProperties(actualClient.getProperty(), upperClientTeeItem.getFirstLevelComponent(), (TreeParent) actualClient, actualClientDotPath, predefinedPropertyName);
										if (createdTreeObject != null) {
											
											// indicate that this is a client
											createdTreeObject.setIsValueClient(true);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean hasChild(TreeParent parent, String name){
		TreeObject[] children = parent.getChildren();
		for (TreeObject child : children) {
			if (child.getName().trim().equals(name.trim())) {
				return true;
			}
		}
		return false;
	}
	
	
	private List<String> getDotPathWithoutPredefinedPropertyName(String dotPath){
		List<String> reply = new ArrayList<String>();

		// TODO: check if this list is complete AND move the list to ModelicaML Constants! 
		HashSet<String> predefinedPropertyNames = new HashSet<String>();
		predefinedPropertyNames.add("min");
		predefinedPropertyNames.add("max");
		
		predefinedPropertyNames.add("start");
		predefinedPropertyNames.add("fixed");
		
		predefinedPropertyNames.add("nominal");
		predefinedPropertyNames.add("stateSelect");
		
		predefinedPropertyNames.add("unit");
		predefinedPropertyNames.add("displayUnit");
		
		predefinedPropertyNames.add("quantity");
		
		String dotPathWithoutPredefinedPropertyName = null;
		
		for (String predefinedPropertyName : predefinedPropertyNames) {
			// if the upper client path ends with "." predefined property name
			if (dotPath.endsWith( "." + predefinedPropertyName)) {
				
				// get the path and the predefined property name
				dotPathWithoutPredefinedPropertyName = dotPath.substring(0, dotPath.length() - ("." + predefinedPropertyName).length());
				reply.add(dotPathWithoutPredefinedPropertyName);
				reply.add(predefinedPropertyName);
				
				return reply; 
			}
		}
		
		return reply;
	}
	
	private TreeObject getTreeObject(String dotPath){
		for (TreeObject treeObject : allTreeObjects) {
			if (treeObject.getDotPath().equals(dotPath)) {
				return treeObject;
			}
		}
		return null;
	}
	
	
	private void addToElementToInstantiationTreeObjectMap(Element key, TreeObject item){
		HashSet<TreeObject> set = elementToInstantiationTreeObjects.get(key);
		HashSet<TreeObject> updatedSet = new HashSet<TreeObject>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			elementToInstantiationTreeObjects.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			elementToInstantiationTreeObjects.put(key, updatedSet);
		}
	}

	
	public void collectBindingsDataFromUmlModel(){
		
		UmlModel papyrusModel = null;
		Element valueMediatorsPackage = null;
		
		if (this.umlModel != null ) {
			valueMediatorsPackage = this.umlModel;
		}
		else {
			papyrusModel = UmlUtils.getUmlModel();
			if (papyrusModel != null) {
				try {
					valueMediatorsPackage = (Element) papyrusModel.lookupRoot();
				} catch (NotFoundException e) {
					e.printStackTrace();
//					System.err.println("Collecting Value Binding Elements: Could not access the Papyrus model.");
				}
			}
			else {
//				System.err.println("Collecting Value Binding Elements: Could not access the Papyrus model.");
			}
		}

		if (valueMediatorsPackage != null ) {
			valueBindingsDataCollector = new BindingsDataCollector(isRecollectMediatorsIfEmpty());
			
			// pass all pre-collected mediators in order to avoid another search
			valueBindingsDataCollector.setAllMediators(getAllMediators());
			// collect, in case no mediators were passed
			valueBindingsDataCollector.collectAll(valueMediatorsPackage, this, treeRoot);

			setValueClientOrProviderIndicator(treeRoot);
			
			// update the size of the tree
			int numberOfTreeNodes = getAllTreeObjects().size();
			treeRoot.setName(treeRoot.getName() + " (" + numberOfTreeNodes + " nodes)");
		}
		else {
//			System.err.println("Component Tree View: Cannot access the root model in Papyrus in order to collect value bindings data.");
		}
	}
	
	
	public TreeParent getTreeRoot() {
		return treeRoot;
	}
	
	
	public TreeParent getInvisibleRoot() {
		return invisibleRoot;
	}
	
	public void setIncludeStateMachines(Boolean showStateMachines) {
		this.includeStateMachines = showStateMachines;
	}

	public Boolean getIncludeStateMachines() {
		return includeStateMachines;
	}

	public void setSelectedClass(Class aClass) {
		this.selectedClass = aClass;
	}

	public Class getSelectedClass() {
		return selectedClass;
	}


	public void setUmlModel(Element model) {
		this.umlModel = model;
	}
	
	public HashMap<Element, HashSet<TreeObject>> getElementToInstantiationTreeObjects() {
		return elementToInstantiationTreeObjects;
	}


	public HashSet<Element> getAllMediators() {
		if (getValueBindingsDataCollector().getAllMediators() != null ) {

			if (allMediators == null) {
				/*
				 * TODO: not clear when this happens
				 */
				return getValueBindingsDataCollector().getAllMediators();
			}
			else {
				this.allMediators.addAll(getValueBindingsDataCollector().getAllMediators());
			}
		}
		return allMediators;
	}


	public void setAllMediators(HashSet<Element> allMediators) {
		this.allMediators = allMediators;
	}


	public boolean isBuildPredefinedTypesProperties() {
		return buildPredefinedTypesProperties;
	}


	public void setBuildPredefinedTypesProperties(
			boolean buildPredefinedTypesProperties) {
		this.buildPredefinedTypesProperties = buildPredefinedTypesProperties;
	}
	
	public HashSet<TreeObject> getAllTreeObjects() {
		return allTreeObjects;
	}

	/*
	 * Note, this method is used by clients that construct the tree by pieces
	 * instead of using the createTree() method of this class.
	 * Here we need to collect UML elements from the tree objects set.
	 */
	public void setAllTreeObjects(HashSet<TreeObject> allTreeObjects) {
		
		this.allTreeObjects = allTreeObjects;
		// collect UML elements 
		for (TreeObject treeObject : allTreeObjects) {
			addToElementToInstantiationTreeObjectMap(treeObject.getUmlElement(), treeObject);
		}
	}
	
	public BindingsDataCollector getValueBindingsDataCollector() {
		return valueBindingsDataCollector;
	}


	public void setValueBindingsDataCollector(
			BindingsDataCollector valueBindingsDataCollector) {
		this.valueBindingsDataCollector = valueBindingsDataCollector;
	}
	
	
	public boolean isRecollectMediatorsIfEmpty() {
		return recollectMediatorsIfEmpty;
	}

	public void setRecollectMediatorsIfEmpty(boolean recollectMediatorsIfEmpty) {
		this.recollectMediatorsIfEmpty = recollectMediatorsIfEmpty;
	}
}
