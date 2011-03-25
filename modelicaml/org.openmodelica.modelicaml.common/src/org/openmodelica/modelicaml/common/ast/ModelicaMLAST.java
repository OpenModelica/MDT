package org.openmodelica.modelicaml.common.ast;

import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.services.UmlServices;

public class ModelicaMLAST {

	private Class selectedClass;
	
	private TreeParent invisibleRoot;
	private TreeParent treeRoot;
	
	/** The action show state machines. */
	private Boolean includeStateMachines;


	
	public ModelicaMLAST(Class selectedClass, Boolean includeStateMachines){
		this.selectedClass = selectedClass;
		this.includeStateMachines = includeStateMachines;
	}
	
	
	public void createTree(){
		HashSet<String> emptyModificationsList = new HashSet<String>();
		invisibleRoot = new TreeParent("", null, null, "", false, false, emptyModificationsList, selectedClass, false);
		
		treeRoot = new TreeParent("'" + selectedClass.getName() +"' components", null, null, "", false, true, emptyModificationsList, selectedClass, includeStateMachines);
		invisibleRoot.addChild(treeRoot);
		
		buildNextTreeLevel(this.selectedClass, null, treeRoot , "");
	}
	
//	public void createInputsTree(){
//		HashSet<String> emptyModificationsList = new HashSet<String>();
//		invisibleRoot = new TreeParent("", null, null, "", "", false, false, emptyModificationsList, selectedClass, false);
//		
//		treeRoot = new TreeParent("Inputs" + " of " + "'"+selectedClass.getName() +"' components", null, null, "", "input", false, true, emptyModificationsList, selectedClass, false);
//		invisibleRoot.addChild(treeRoot);
//		
//		buildNextTreeLevel(this.selectedClass, null, treeRoot , "input", "");
//	}
//	
//	public void createOutputsTree(){
//		HashSet<String> emptyModificationsList = new HashSet<String>();
//		invisibleRoot = new TreeParent("", null, null, "", "", false, false, emptyModificationsList, selectedClass, false);
//		
//		treeRoot = new TreeParent("Outputs" + " of " + "'"+selectedClass.getName() +"' components", null, null, "", "output", false, true, emptyModificationsList, selectedClass, false);
//		invisibleRoot.addChild(treeRoot);
//		
//		buildNextTreeLevel(this.selectedClass, null, treeRoot , "output", "");
//	}
//	
	
//	public void setTreeRoot(TreeParent treeRoot) {
//		this.treeRoot = treeRoot;
//	}

	public TreeParent getTreeRoot() {
		return treeRoot;
	}
	
	
	public TreeParent getInvisibleRoot() {
		return invisibleRoot;
	}


//	public void setInvisibleRoot(TreeParent invisibleRoot) {
//		this.invisibleRoot = invisibleRoot;
//	}

	
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

//	/**
//	 * Checks if is input or output variable.
//	 * 
//	 * @param property
//	 *            the property
//	 * @param causalityString
//	 *            the causality string
//	 * @return the boolean
//	 */
//	private Boolean isInputOrOutputVariable(Property property,
//			String causalityString) {
//		Boolean result = false;
//		Object causality = null;
//		if (property instanceof Port) {
//			causality = UmlServices.getStereotypeValue(property,
//					"ConnectionPort", "causality");
//		} else if (property instanceof Property) {
//			causality = UmlServices.getStereotypeValue(property, "Variable",
//					"causality");
//		}
//
//		if (causality != null) {
//			String causalityValue = ((EnumerationLiteral) causality).getName();
//			if (causalityValue.equals(causalityString)) {
//				result = true;
//			}
//		}
//		return result;
//	}

//	/**
//	 * Checks for input or output variables.
//	 * 
//	 * @param p
//	 *            the p
//	 * @param causalityString
//	 *            the causality string
//	 * @return the boolean
//	 */
//	private Boolean hasInputOrOutputVariables(Property p, String causalityString) {
//		Boolean result = false;
//		HashSet<Property> listOfInputVariables = findInputOrOutputVariables(p,
//				causalityString);
//
//		if (listOfInputVariables.size() > 0) {
//			result = true;
//		}
//		return result;
//	}
//
//	/**
//	 * Find input or output variables.
//	 * 
//	 * @param p
//	 *            the p
//	 * @param causalityString
//	 *            the causality string
//	 * @return the hash set
//	 */
//	private HashSet<Property> findInputOrOutputVariables(Property p,
//			String causalityString) {
//		Type pType = p.getType();
//		HashSet<Property> listOfOutputVariables = new HashSet<Property>();
//
//		if (pType != null) {
//			if (isInputOrOutputVariable(p, causalityString)) {
//				listOfOutputVariables.add(p);
//			} else { // is a component -> go for sub components
//				if (listOfOutputVariables.size() < 1) { // optimization: it
//														// shall stop as soon as
//														// at least one is
//														// found!
//					// EList<Property> subComponentslist = ((Classifier)
//					// pType).getAllAttributes(); // includes also all inherited
//					// attributes // OLD
//					EList<Property> subComponentslist = getClassComponents((Classifier) pType);
//					for (Property subProperty : subComponentslist) {
//						listOfOutputVariables
//								.addAll(findInputOrOutputVariables(subProperty,
//										causalityString));
//					}
//				} else
//					return listOfOutputVariables; // optimization: it shall stop
//													// as soon as at least one
//													// is found!
//			}
//		}
//		return listOfOutputVariables;
//	}

	/**
	 * Gets the class components.
	 * 
	 * @param aClass
	 *            the a class
	 * @return the class components
	 */
	private EList<Property> getClassComponents(Classifier aClass) {
		// EList<Property> pList = aClass.getAllAttributes(); // OLD
		// TODO: implement here all Modelica specific filtering of components, such as redeclaration and merging of duplicates if they are identical.
		EList<Property> pFinalList = getAllInheritedAttributes(aClass, aClass.getAttributes());
		return pFinalList;
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
	public EList<Property> getAllInheritedAttributes(Classifier aClass,
			EList<Property> passedAList) {
		// TODO: verify if this works correctly for Modelica inheritance concept.
		HashSet<Property> mergedSet = new HashSet<Property>();
		mergedSet.addAll(passedAList);

		HashSet<Property> finalSet = new HashSet<Property>();
		EList<Property> finalList = new BasicEList<Property>();

		EList<Generalization> extendsRelations = aClass.getGeneralizations(); // TODO: filter on ModelicaML stereotypes

		if (extendsRelations.size() < 1) { // if there there is no inheritance then just return the passedAList
			finalList.addAll(passedAList);
		} else {// if there are inheritance then merge
			for (Generalization generalization : extendsRelations) {
				EList<Element> targets = generalization.getTargets();
				for (Element element : targets) {
					if (element instanceof Classifier) {
						EList<Property> inheritedProperties = ((Classifier) element)
								.getAttributes();
						mergedSet.addAll(inheritedProperties);
						for (Property propertyInherited : inheritedProperties) {
							String nameInheritedProperty = propertyInherited
									.getName();
							for (Property propertyPassed : passedAList) {
								String namePropertyPassed = propertyPassed
										.getName();
								if (nameInheritedProperty
										.equals(namePropertyPassed)) {
									mergedSet.remove(propertyInherited);
									// System.out.println("removed: " + propertyInherited.getName());
								}
							}
						}
						EList<Property> list = new BasicEList<Property>();
						list.addAll(mergedSet);
						finalSet.addAll(getAllInheritedAttributes(
								(Classifier) element, list));
					}
				}
			}
		}
		finalList.addAll(finalSet);
		return finalList;
	}

	/**
	 * Builds the next level of input or output tree.
	 * 
	 * @param aClass
	 *            the a class
	 * @param firstLevelComponent
	 *            the first level component
	 * @param parent
	 *            the parent
	 * @param causalityString
	 *            the causality string
	 * @param dotPath
	 *            the dot path
	 */
	public void buildNextTreeLevel(Class aClass, Property firstLevelComponent, TreeParent parent, String dotPath) {
		// EList<Property> pList = aClass.getAllAttributes(); // OLD
		EList<Property> pList = getClassComponents(aClass);

		for (Property property : pList) {

			if (firstLevelComponent == null) { // this is a very first component
				firstLevelComponent = property;
			}

			String newDotPath = "";
			if (!dotPath.equals("")) {
				newDotPath = dotPath + "." + property.getName();
			} else {
				newDotPath = property.getName();
			}

			if (isPrimitiveType(property)) {
				HashSet<String> modifications = ModificationsCollector.getMergedComponentModifications(selectedClass, property, newDotPath, parent.getModifications());
				TreeParent child = new TreeParent(property.getName(), property, firstLevelComponent, newDotPath, true, false, modifications, selectedClass, includeStateMachines);
				parent.addChild(child);
				
				// set parent "has inputs/outputs" indicators 
				if (child.isInput()) {
					parent.setHasInputs(treeRoot);
				}
				if (child.isOutput()) {
					parent.setHasOutputs(treeRoot);
				}

				// create predefined properties for Modelica real, string, integer and boolean.
				createPredefinedTypeProperties(property, firstLevelComponent, child, newDotPath);
				
			} else {
				HashSet<String> modifications = ModificationsCollector.getMergedComponentModifications(selectedClass, property, newDotPath, parent.getModifications());
				TreeParent newParent = new TreeParent(property.getName(), property, firstLevelComponent, newDotPath, false, false, modifications, selectedClass, includeStateMachines);
				parent.addChild(newParent);
				Type pType = property.getType();
				if (pType instanceof Class && (Class) pType != aClass && !(pType instanceof Stereotype)) { // TODO: // prevent endless looping, implement it correctly!
					buildNextTreeLevel((Class) pType, firstLevelComponent, newParent, newDotPath);
				}
			}
			
//			
//			if (!causalityString.equals("")) { // input/output component
//				if (isInputOrOutputVariable(property, causalityString)) {
//					TreeParent child = null;
//					if (property instanceof Port) { // instance of Port
//						HashSet<String> modifications = ModificationsCollector
//								.getMergedComponentModifications(selectedClass,
//										property, newDotPath,
//										parent.getModifications());
//						child = new TreeParent(property.getName(), property,
//								firstLevelComponent, newDotPath,
//								causalityString, true, false, modifications,
//								selectedClass, false);
//
//						// getAllPortComponents(property, firstLevelComponent,
//						// child, causalityString, newDotPath);
//						// TODO: test if the code below is equivalent.
//						Type pType = property.getType();
//						if (pType instanceof Class && (Class) pType != aClass) { // TODO:
//																					// prevent
//																					// endless
//																					// looping,
//																					// implement
//																					// it
//																					// correctly!
//							buildNextTreeLevel((Class) pType,
//									firstLevelComponent, child,
//									causalityString, newDotPath);
//						}
//					} else { // instance of Property
//						HashSet<String> modifications = ModificationsCollector
//								.getMergedComponentModifications(selectedClass,
//										property, newDotPath,
//										parent.getModifications());
//						child = new TreeParent(property.getName(), property,
//								firstLevelComponent, newDotPath,
//								causalityString, true, false, modifications,
//								selectedClass, false);
//					}
//					parent.addChild(child);
//
//					// create predefined properties for Modelica real, string,
//					// integer and boolean.
//					createPredefinedTypeProperties(property,
//							firstLevelComponent, child, newDotPath,
//							causalityString);
//
//				} else if (hasInputOrOutputVariables(property, causalityString)) {
//					HashSet<String> modifications = ModificationsCollector
//							.getMergedComponentModifications(selectedClass,
//									property, newDotPath,
//									parent.getModifications());
//					TreeParent newParent = new TreeParent(property.getName(),
//							property, firstLevelComponent, newDotPath,
//							causalityString, false, false, modifications,
//							selectedClass, false);
//					parent.addChild(newParent);
//					Type pType = property.getType();
//					if (pType instanceof Class && (Class) pType != aClass) { // TODO:
//																				// prevent
//																				// endless
//																				// looping,
//																				// implement
//																				// it
//																				// correctly!
//						buildNextTreeLevel((Class) pType,
//								firstLevelComponent, newParent,
//								causalityString, newDotPath);
//					}
//				}
//			} else {// any component
//				if (isPrimitiveType(property)) {
//					HashSet<String> modifications = ModificationsCollector.getMergedComponentModifications(selectedClass, property, newDotPath, parent.getModifications());
//					TreeParent child = new TreeParent(property.getName(), property, firstLevelComponent, newDotPath, causalityString, true, false, modifications, selectedClass, includeStateMachines);
//					parent.addChild(child);
//					
//					// set parent "has inputs/outputs" indicators 
//					if (child.isInput()) {
//						parent.setHasInputs(treeRoot);
//					}
//					if (child.isOutput()) {
//						parent.setHasOutputs(treeRoot);
//					}
//					
//
//					// create predefined properties for Modelica real, string,
//					// integer and boolean.
//					createPredefinedTypeProperties(property, firstLevelComponent, child, newDotPath, causalityString);
//				} else {
//					HashSet<String> modifications = ModificationsCollector.getMergedComponentModifications(selectedClass, property, newDotPath, parent.getModifications());
//					TreeParent newParent = new TreeParent(property.getName(), property, firstLevelComponent, newDotPath, causalityString, false, false, modifications, selectedClass, includeStateMachines);
//					parent.addChild(newParent);
//					Type pType = property.getType();
//					if (pType instanceof Class && (Class) pType != aClass && !(pType instanceof Stereotype)) { // TODO: // prevent endless looping, implement it correctly!
//						buildNextTreeLevel((Class) pType,
//								firstLevelComponent, newParent,
//								causalityString, newDotPath);
//					}
//				}
//			}
			
			
			
			// reset the firstLevelComponent. This ensures that only the very first level components are passed.
			if (firstLevelComponent == property) {
				firstLevelComponent = null;
			}
		}
	}

	/**
	 * Creates the predefined type properties.
	 * 
	 * @param property
	 *            the property
	 * @param firstLevelComponent
	 *            the first level component
	 * @param parent
	 *            the parent
	 * @param dotPath
	 *            the dot path
	 * @param causalityString
	 *            the causality string
	 */
	private void createPredefinedTypeProperties(Property property, Property firstLevelComponent, TreeParent parent, String dotPath) {
		Type type = property.getType();

		if (type != null) {
			if (type instanceof Classifier) {
				EList<Property> ModelicaPredefinedTypeProperties = ((Classifier) type)
						.getAllAttributes();
				for (Property p : ModelicaPredefinedTypeProperties) {

					String newDotPath = "";
					if (!dotPath.equals("")) {
						newDotPath = dotPath + "." + p.getName();
					} else {
						newDotPath = p.getName();
					}
					HashSet<String> modifications = ModificationsCollector.getMergedComponentModifications(selectedClass, property, newDotPath, parent.getModifications());
					// EList<String> modifications = new BasicEList<String>();
					// TODO: here get the final modifications for this component.
					TreeParent child = new TreeParent(p.getName(), p, firstLevelComponent, newDotPath, true, false, modifications, selectedClass, false);
					parent.addChild(child);
				}
			}
		}
	}



}
