package org.openmodelica.modelicaml.common.instantiation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
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
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.services.UmlServices;

public class ClassInstantiation {

	private Class selectedClass;
	
	private TreeParent invisibleRoot;
	private TreeParent treeRoot;
	
	private HashSet<Element> referencedClients = new HashSet<Element>();
	private HashSet<Element> referencedProviders = new HashSet<Element>();
	
	/** The action show state machines. */
	private Boolean includeStateMachines;

	
	public HashMap<String,Type> redeclaredComponentTypes = new HashMap<String,Type>();
	public HashMap<String,String> firstLevelComponentsModifications = new HashMap<String,String>();
	public HashMap<String,NamedElement> modificationSource = new HashMap<String,NamedElement>();
	
	public HashMap<String,String> modifications = new HashMap<String,String>();
	
//	public HashMap<Property,Class> componentIsInheritedFrom = new HashMap<Property,Class>(); // TODO do we need it? 
	
	public HashSet<Property> selectedClassOwnedComponents = new HashSet<Property>();
	public HashMap<Generalization,EList<Property>> selectedClassExtensionsReferenceForInheritedComponents = new HashMap<Generalization,EList<Property>>(); // TODO do we need it?
	
	
	public ClassInstantiation(Class selectedClass, Boolean includeStateMachines){
		this.selectedClass = selectedClass;
		this.includeStateMachines = includeStateMachines;
	}
	
	
	public void createTree(){
		if (selectedClass != null) {
			invisibleRoot = new TreeParent("", null, null, "", false, false, new HashSet<String>(), selectedClass, false);
			
//			String name = "'" + selectedClass.getName() +"' components";
//			String name = "'" + selectedClass.getName() +"' instantiated";
			String name = "instantiated '" + selectedClass.getName() +"' ";
			treeRoot = new TreeParent(name, null, null, "", false, true, new HashSet<String>(), selectedClass, includeStateMachines);
			invisibleRoot.addChild(treeRoot);
			
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
			
			
			// Get value bindings data 
			collectValueClientsAndProvidersFromUmlModel(this.selectedClass.getModel());
			
			// build tree starting from the selected class node.
			buildNextTreeLevel(this.selectedClass, null, treeRoot , "");
			
//			for (Object object : modifications.keySet() ) {
//				System.err.println( object.toString() + " = " + modifications.get(object).toString() );
//			}
//			for (Object object : redeclaredComponentTypes.keySet() ) {
//				System.err.println( object.toString() + " = " + ((Type)redeclaredComponentTypes.get(object)).getQualifiedName() );
//			}

//			for (Object object : modificationSource.keySet() ) {
//				System.err.println( object.toString() + " = " + ((NamedElement)modificationSource.get(object)).getQualifiedName() );
//			}
		}
	}
	
	
	private void addInheritedClassModifications(Class aClass, String dotPath){
		EList<Generalization> extendsRelations = aClass.getGeneralizations();
		
		for (Generalization generalization : extendsRelations) {
			Stereotype stereotype = getGeneralizationStereotype(generalization);
			if ( stereotype != null ) {
				EList<String> modList = getGeneralizationModifications(generalization, stereotype);
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
	}
	
	private void addClassComponentsModifications(Property property, String dotPath){
		String prefix = "";
		if (!dotPath.trim().equals("")) {
			prefix = dotPath + ".";
		}
//		EList<Property> components = getClassComponents(aClass);
//		for (Property property : components) {
			HashSet<String> modList = getComponentModifications(property);
			String propertyName = prefix + StringUtls.replaceSpecChar(property.getName());
			for (String string : modList) {
				addToModificationList(string, propertyName, property);
			}
//		}
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
				String rightHand = splitted[1].trim();
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
							String nameInheritedProperty = propertyInherited.getName();
							for (Property propertyPassed : passedAList) { // iterate over the passed list
								String namePropertyPassed = propertyPassed.getName();
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
			if (!dotPath.equals("")) {
				newDotPath = dotPath + "." + property.getName();
			} else {
				newDotPath = property.getName();
			}

			if (isPrimitiveType(property)) { // if it is a primitive which value can be modified

				addClassComponentsModifications(property, dotPath);
				HashSet<String> modifications = null;
				
				TreeParent child = new TreeParent(property.getName(), property, firstLevelComponent, newDotPath, true, false, modifications, selectedClass, includeStateMachines);
				parent.addChild(child);
				
				// set the final modification right hand
//				child.setFinalModificationRightHand(this.modifications.get(newDotPath));
				child.setModificationRightHand(this.modifications.get(newDotPath));
				child.setModificationSource(this.modificationSource.get(newDotPath));

				// set the store location for the tree object.
				setModificationStoreLocation(child);

				// set parent "has inputs, outputs" indicators to all ancestors
				if (child.isInput()) { parent.setHasInputs(treeRoot); }
				if (child.isOutput()) { parent.setHasOutputs(treeRoot); }

				// set parent "has value clients or providers" indicators
//				if (child.isValueClient()) { parent.setHasValueClients(treeRoot); }
//				if (child.isValueProvider()) { parent.setHasValueProviders(treeRoot); }
				if (this.referencedClients.contains(property)) {
					child.setIsValueClient(true);
					parent.setHasValueClients(treeRoot);
				}
				if (this.referencedProviders.contains(property)) {
					child.setIsValueProvider(true);
					parent.setHasValueProviders(treeRoot);
				}

				// create predefined properties for Modelica real, string, integer and boolean.
				createPredefinedTypeProperties(property, firstLevelComponent, child, newDotPath);
				
			} else { // non-primitive item

				addClassComponentsModifications(property, dotPath);

				Type pType = null;
				pType = this.redeclaredComponentTypes.get(newDotPath);
				if (pType == null) {
					pType = property.getType();
				}
				
				HashSet<String> modifications = null;
				TreeParent newParent = new TreeParent(property.getName(), property, firstLevelComponent, newDotPath, false, false, modifications, selectedClass, includeStateMachines);
				parent.addChild(newParent);
				
				// set the final modification right hand
//				newParent.setFinalModificationRightHand(this.modifications.get(newDotPath));
				newParent.setModificationRightHand(this.modifications.get(newDotPath));
				newParent.setModificationSource(this.modificationSource.get(newDotPath));
				
				// set the store location for the tree object.
				setModificationStoreLocation(newParent);
				
				// set has requirements indicator to all ancestors
				if (newParent.isRequirementInstance()) { newParent.setHasRequirements(treeRoot); }
				
				// set parent "has value clients or providers" indicators
//				if (newParent.isValueClient()) { newParent.setHasValueClients(treeRoot); }
//				if (newParent.isValueProvider()) { newParent.setHasValueProviders(treeRoot); }
				if (this.referencedClients.contains(property)) {
					newParent.setIsValueClient(true);
					newParent.setHasValueClients(treeRoot);
				}
				if (this.referencedProviders.contains(property)) {
					newParent.setIsValueProvider(true);
					newParent.setHasValueProviders(treeRoot);
				}
				
				// make sure that the tree object gets its redeclared type.
				newParent.setComponentType(pType); 
				
				if (pType instanceof Class && (Class) pType != aClass && !(pType instanceof Stereotype)) { // TODO: // prevent endless looping, implement it correctly!
					buildNextTreeLevel((Class) pType, firstLevelComponent, newParent, newDotPath);
				}
			}
			
			// reset the firstLevelComponent. This ensures that only the very first level components are passed.
			if (firstLevelComponent == property) {
				firstLevelComponent = null;
			}
		}
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
	

	private void createPredefinedTypeProperties(Property property, Property firstLevelComponent, TreeParent parent, String dotPath) {
		Type pType = null;
		pType = this.redeclaredComponentTypes.get(dotPath);
		if (pType == null) {
			pType = property.getType();
		}

		if (pType != null) {
			if (pType instanceof Classifier) {
				EList<Property> ModelicaPredefinedTypeProperties = ((Classifier) pType).getAllAttributes();
				for (Property p : ModelicaPredefinedTypeProperties) {

					String newDotPath = "";
					if (!dotPath.equals("")) {
						newDotPath = dotPath + "." + p.getName();
					} else {
						newDotPath = p.getName();
					}

					HashSet<String> modifications = null;
					TreeParent child = new TreeParent(p.getName(), p, firstLevelComponent, newDotPath, true, false, modifications, selectedClass, false);
					parent.addChild(child);
					
					// set the final modification right hand
//					child.setFinalModificationRightHand(this.modifications.get(newDotPath));
					child.setModificationRightHand(this.modifications.get(newDotPath));
					child.setModificationSource(this.modificationSource.get(newDotPath));

					child.setIsPredefinedModelicaProperty(true);
					
					// set the store location for the tree object.
					setModificationStoreLocation(child);
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	private Type findType(Element sourceOfRedeclaration, String dotPath){
		Model model = sourceOfRedeclaration.getModel();
		Iterator<EObject> i = model.eAllContents();
//		List<EObject> redeclaredTypes = new ArrayList<EObject>() ;
//		Type type = null;
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
	
	

	/**
	 * Gets the generalization stereotype.
	 * 
	 * @param extendsRelation
	 *            the extends relation
	 * @return the generalization stereotype
	 */
	private static Stereotype getGeneralizationStereotype(Generalization extendsRelation) {
		Stereotype stereotype = null;
		if (UmlServices.hasStereotype((Element) extendsRelation, "ExtendsRelation"))
			return stereotype = extendsRelation.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::ExtendsRelation");
		
		if (UmlServices.hasStereotype((Element) extendsRelation, "TypeRelation"))
			return stereotype = extendsRelation.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::TypeRelation");
		
		return stereotype;
	}

	/**
	 * Gets the component modifications.
	 * 
	 * @param component
	 *            the component
	 * @return the component modifications
	 */
	public HashSet<String> getComponentModifications(Property component) {
		HashSet<String> mList = new HashSet<String>();
		String stereotypeName = getFirstModelicaMLComponentStereotypeName(component);
		List<String> modificationList = new ArrayList<String>();
		if (stereotypeName != null) {
			Object o = UmlServices.getStereotypeValue((Element) component, getFirstModelicaMLComponentStereotypeName(component), "modification");
			if (o instanceof List<?>) {
				modificationList = (List<String>) o;
			}
			for (String string : modificationList) {
				mList.add(StringUtls.removeOutterBraces(string));
			}
			//mList.addAll(modificationList);
		}
		return mList;
	}
	
	/**
	 * Gets the generalization modifications.
	 * 
	 * @param extendsRelation
	 *            the extends relation
	 * @param stereotype
	 *            the stereotype
	 * @return the generalization modifications
	 */
	private EList<String> getGeneralizationModifications(Generalization extendsRelation, Stereotype stereotype) {
		EList<String> mList = new BasicEList<String>();
		
		List<String> modificationList = new ArrayList<String>();
		if (stereotype != null) {
			Object o = UmlServices.getStereotypeValue((Element) extendsRelation, stereotype.getName(),"modification");
			if (o instanceof List<?>) {
				modificationList = (List<String>) o;
			}
			mList.addAll(modificationList);
		}
		return mList;
	}

	/**
	 * Gets the first modelica ml component stereotype name.
	 * 
	 * @param property
	 *            the property
	 * @return the first modelica ml component stereotype name
	 */
	private String getFirstModelicaMLComponentStereotypeName(Property property) {
		String name = null;
		if (UmlServices.hasStereotype((Element) property, "Component")) return "Component";
		if (UmlServices.hasStereotype((Element) property, "Variable")) return "Variable";
		if (UmlServices.hasStereotype((Element) property, "RequirementInstance")) return "RequirementInstance";
		if (UmlServices.hasStereotype((Element) property, "CalculatedProperty")) return "CalculatedProperty";
		return name;
	}
	
	
	
	private void collectValueClientsAndProvidersFromUmlModel(EObject umlRootElement){
		Iterator<EObject> i = umlRootElement.eAllContents();
		while (i.hasNext()) {
			EObject object = i.next();
			if (object instanceof NamedElement 
					&& ((NamedElement)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
				
				NamedElement mediator = (NamedElement)object;
				EList<Dependency> depList = mediator.getClientDependencies();
				for (Dependency dependency : depList) {
					
					// reference to a clients
					if (dependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null ) { 
						EList<Element> targets = dependency.getTargets();
						for (Element element : targets) {
							if (element instanceof NamedElement) {
								// add only mediators and referenced elements if they are used in the instantiation tree
								referencedClients.add(element);	
							}
						}
					}
					// reference to providers or other mediators
					else if (dependency.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null ) {
						EList<Element> targets = dependency.getTargets();
						for (Element element : targets) {
							if (element instanceof NamedElement) {
								referencedProviders.add(element);
							}
						}
					}
				}
			}
		}
	}

}
