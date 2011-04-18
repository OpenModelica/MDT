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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.modelicaml.common.instantiation;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.services.UmlServices;



// TODO: Auto-generated Javadoc
/**
 * The Class TreeObject.
 */
public class TreeObject implements IAdaptable {
	private String stereotypeQName_Requirement = "ModelicaML::ModelicaRequirementConstructs::Requirement";
	private String stereotypeQName_Variable = "ModelicaML::ModelicaCompositeConstructs::Variable";
	
		/** The property. */
	private Property property = null;
	
	/** The name. */
	private String name;
	
	/** The parent. */
	private TreeParent parent;
	
	/** The dot path. */
	private String dotPath;
	
	/** The causality. */
	private String causality; // TODO: to be used when e.g. ports has causality that affects its subcomponents
	
	/** The is leaf. */
	private Boolean isLeaf;
	
	/** The is root. */
	private Boolean isRoot;
	
//	/** The modifications. */
//	private HashSet<String> modifications = new HashSet<String>(); 
	
//	/** The modifications defined infirst level component. */
//	private HashSet<String> modificationsDefinedInfirstLevelComponent = new HashSet<String>(); 
	
	/** The first level component. */
	private Property firstLevelComponent = null;
	
	/** The dot path without first level component. */
	private String dotPathWithoutFirstLevelComponent;

	/** The declaration. */
	private String declaration = null;
	
//	/** The final modification left hand. */
//	private String finalModificationLeftHand = null;
	
	/** The final modification right hand. */
	private String finalModificationRightHand = null; // TODO: rename to editedModification
	
	/** The final modification right hand. */
	private String modificationRightHand = null;
	
		//TODO: enhance this class to store the source of modification
	/** The final modification source. */
	private NamedElement finalModificationSource = null;
	private NamedElement modificationSource = null;
	
	/** The final modification description. */
	private String finalModificationDescription = null;
	
	// TODO: implement getter and setter
	/** The element. */
	private NamedElement element = null;
	
	private Element modificationStoreLocation = null;
	
//	/** The is predefined. */
//	private Boolean isPredefined;
//	
//	/** The is primitive. */
//	private Boolean isPrimitive;
//	
//	/** The is port. */
//	private Boolean isPort;
//	
//	// TODO: add these to constructor
//	/** The is first level component. */
//	private Boolean isFirstLevelComponent;
//	
//	/** The is first level. */
//	private Boolean isFirstLevel;
	
	/** The selected class. */
	private Class selectedClass;
	
	/** The component type. */
	private Type componentType = null;

	public Boolean hasInputs = null;
	public Boolean hasOutputs = null;
	public Boolean hasRequirements = null;

	public TreeObject(	String name, 
						Property property, 
						Property firstLevelComponent, 
						String dotPath, 
						Boolean isLeaf, 
						Boolean isRoot, 
//						HashSet<String> modifications,
						org.eclipse.uml2.uml.Class selectedClass) {

		this.name = name;
		
		this.property = property;
		if (property != null) {
			this.componentType = property.getType();
		}
		
		this.firstLevelComponent = firstLevelComponent;
		this.dotPath = dotPath;
		
		// dotPathWithoutFirstLevelComponent 
		if (firstLevelComponent != null) {
			this.dotPathWithoutFirstLevelComponent = this.dotPath.replaceFirst(firstLevelComponent.getName() + ".", "");
		}
		
		this.isLeaf = isLeaf;
		this.isRoot = isRoot;
		
//		if (modifications != null ) {
//			this.modifications.addAll(modifications);
//		}
		this.selectedClass = selectedClass;
		
		// set setModificationsDefinedInFirstLevelComponents list
//		setModificationsDefinedInFirstLevelComponents();

		// declaration string
		setDeclaration();
		
		// set the final modification left/right hand values
//		setFinalModificationLeftAndRigntHand();
	}
	
	
	/**
	 * Sets the component type.
	 * 
	 * @param type
	 *            the new component type
	 */
	public void setComponentType (Type type) {
		this.componentType = type;
	}
	
	/**
	 * Gets the component type.
	 * 
	 * @return the component type
	 */
	public Type getComponentType(){
		return this.componentType;
	}

	
	/**
	 * Gets the selected class.
	 * 
	 * @return the selected class
	 */
	public Class getSelectedClass(){
		return this.selectedClass;
	}
	
	/**
	 * Sets the element.
	 * 
	 * @param element
	 *            the new element
	 */
	public void setElement(NamedElement element){
		this.element = element;
	}
	
	/**
	 * Gets the element.
	 * 
	 * @return the element
	 */
	public NamedElement getElement(){
		return this.element;
	}
	
//	/**
//	 * Sets the modifications defined in first level components.
//	 */
//	private void setModificationsDefinedInFirstLevelComponents(){
//		if (firstLevelComponent != null) {
//			HashSet<String> modList = ModificationsCollector.getComponentModifications(firstLevelComponent);
//
//			for (String string : modList) {
//				String[] splitted = string.trim().split("=");
//				if (splitted.length > 1) {
//					
//					String sLeftHand = splitted[0].trim();
//					//String sRightHand = splitted[1].trim();
//					
//					if (sLeftHand.equals(this.dotPathWithoutFirstLevelComponent)) {
//						String newModString = firstLevelComponent.getName() + "." + string;
//						modificationsDefinedInfirstLevelComponent.add(newModString);
//						
//						// Remove firstLevelComponent modification from modifications
//						HashSet<String> copyOfModifications = new HashSet<String>();
//						copyOfModifications.addAll(this.modifications);
//						for (String string2 : copyOfModifications) {
//							if (string2.equals(newModString)) {
//								this.modifications.remove(newModString);
////								System.out.println("Deleted from derived mod list: " + newModString);
////								System.out.println("getFinalModificationRightHand(): " + this.getFinalModificationRightHand());
//							}
//						}
//					}
//				}
//			}
//		}
//	}
	
//	/**
//	 * Sets the final modification left and rignt hand.
//	 */
//	private void setFinalModificationLeftAndRigntHand(){
//		
//		// reset the values
//		finalModificationLeftHand = null;
//		finalModificationRightHand = null;
//		
//		// get the final modification and split it into left/right hand
//		if (this.modificationsDefinedInfirstLevelComponent.size() > 0 ) {
//			for (String string0 : this.modificationsDefinedInfirstLevelComponent) {
//				String string = StringUtls.removeOutterBraces(string0);
//				
//				String[] splitted = string.trim().split("=");
//				if (splitted.length > 1) {
//					String sLeftHand = splitted[0].trim();
//					String sRightHand = splitted[1].trim();
//
//					if (sLeftHand.equals(dotPath)) {
//						finalModificationLeftHand = sLeftHand;
//						finalModificationRightHand = sRightHand;
//						
////						System.err.println("finalModificationLeftHand: " + this.finalModificationLeftHand);
////						System.out.println("finalModificationRightHand: " + this.finalModificationRightHand);
//					}	
//				}
//				else {
////					Shell shell = new Shell();
////					MessageDialog.openError(shell, "ModelicaML modifications parsing error (TreeObject.java 01)", "The modification string '"+string+"' does not contain an equality lign ('='). It will not be stored.");
//				}
//			}
//		}
//		else if (this.modifications.size() > 0 ) {
//			for (String string0 : this.modifications) {
//				String string = StringUtls.removeOutterBraces(string0);
//				
//				String[] splitted = string.trim().split("=");
//				if (splitted.length > 1) {
//					String sLeftHand = splitted[0].trim();
//					String sRightHand = splitted[1].trim();
//					if (sLeftHand.equals(dotPath)) {
//						finalModificationLeftHand = sLeftHand;
//						finalModificationRightHand = sRightHand;
//						
////						System.err.println("finalModificationLeftHand: " + this.finalModificationLeftHand);
////						System.out.println("finalModificationRightHand: " + this.finalModificationRightHand);
//					}	
//				}
//				else {
////					Shell shell = new Shell();
////					MessageDialog.openError(shell, "ModelicaML modifications parsing error (TreeObject.java 02)", "The modification string '"+string+"' does not contain an equality lign ('='). It will not be stored.");
//
//				}
//			}				
//		}
//	}
	
	/**
	 * Sets the declaration.
	 * 
	 * @return the string
	 */
	private String setDeclaration(){
		if (this.property != null) {
			Stereotype stereotype = property.getAppliedStereotype(stereotypeQName_Variable );
			if (stereotype != null) {
				Object declarationEquationOrAssignment = UmlServices.getStereotypeValue((Element)property, stereotype.getName(), "declarationEquationOrAssignment");
				if (declarationEquationOrAssignment instanceof String) {
					return this.declaration = " " + declarationEquationOrAssignment.toString().trim();
				}	
			}
		}
		return this.declaration;
	}
	
	/**
	 * Gets the declaration.
	 * 
	 * @return the declaration
	 */
	public String getDeclaration(){
		return this.declaration;
	}
	
//	/**
//	 * Gets the final modification left hand.
//	 * 
//	 * @return the final modification left hand
//	 */
//	public String getFinalModificationLeftHand(){
//		return this.finalModificationLeftHand;
//	}
	
	
	
	public void setModificationRightHand(String modificationRightHand) {
		this.modificationRightHand = modificationRightHand;
	}

	public String getModificationRightHand() {
		return modificationRightHand;
	}
	

	public String getFinalModificationRightHand(){ // TODO: rename to getEditedModification
		if (this.finalModificationRightHand == null) {
			return getModificationRightHand();
		}
		return this.finalModificationRightHand;
	}
	
	public void setFinalModificationRightHand( String value ){ // TODO: rename to setEditedModification
		this.finalModificationRightHand = value;
	}
	
	
//	/**
//	 * Gets the modifications.
//	 * 
//	 * @return the modifications
//	 */
//	public HashSet<String> getModifications(){
//		return this.modifications;
//	}
	
	/**
	 * Delete from modification list based on left right hand value.
	 * 
	 * @param leftHandValue
	 *            the left hand value
	 * @param rightHandValue
	 *            the right hand value
	 */
//	public void deleteFromModificationListBasedOnLeftRightHandValue(String leftHandValue, String rightHandValue){
//		
////		System.out.println("leftHandValue: " + leftHandValue);
////		System.out.println("rightHandValue: " + rightHandValue);
//		
//		HashSet<String> copyOfModificationsDefinedInfirstLevelComponent = new HashSet<String>();
//		copyOfModificationsDefinedInfirstLevelComponent.addAll(this.modificationsDefinedInfirstLevelComponent);
//		
//		String leftHand = null;
//		String rightHand = null;
//		for (String string : copyOfModificationsDefinedInfirstLevelComponent) {
//			String[] splitted = string.trim().split("=");
//			if (splitted.length > 1) {
//				leftHand = splitted[0].trim();
//				rightHand = splitted[1].trim();
//				
////				System.out.println("leftHand: " + leftHand);
////				System.out.println("rightHand: " + rightHand);
//				
//				if (leftHand.equals(leftHandValue)) {
//					if (rightHand.equals(rightHandValue)) {
//						this.modificationsDefinedInfirstLevelComponent.remove(string);
//						//System.out.println("Deleted: " + string);
//						
//						// update the left/right-hand values
//						setFinalModificationLeftAndRigntHand();
//					}
//				}
//			}
//		}
//
//	}
	
//	/**
//	 * Delete from modification list.
//	 * 
//	 * @param modificationString
//	 *            the modification string
//	 */
//	public void deleteFromModificationList(String modificationString){
//		String[] splitted = modificationString.trim().split("=");
//		if (splitted.length > 1) {
//			String leftHand = splitted[0].trim();
//			String rightHand = splitted[1].trim();
//			
//			deleteFromModificationListBasedOnLeftRightHandValue(leftHand, rightHand);
//		}
////		else {
////			System.err.println("'" + modificationString + "' is not a valid Modelica modification string!");
////		}
//	}
	
	
//	/**
//	 * Adds the to modification list.
//	 * 
//	 * @param modificationString
//	 *            the modification string
//	 */
//	public void addToModificationList(String modificationString){
//		if (modificationString != null) {
//			if (!modificationString.equals("")) {
//				//this.modifications.add(modificationString);
//				this.modificationsDefinedInfirstLevelComponent.add(modificationString);
//				//System.out.println("added : " + modificationString);
//					// update the left/right-hand values
//				setFinalModificationLeftAndRigntHand(); 
//			}
//		}
//	}
	
	/**
	 * Gets the comments.
	 * 
	 * @return the comments
	 */
	public  EList<Comment> getComments(){
		EList<Comment> comments = new BasicEList<Comment>();
		if (this.property != null) {
			comments = this.property.getOwnedComments();
		}
		return comments;
	}
	
	
	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;	
	}
	
	/**
	 * Gets the property.
	 * 
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}
	
	/**
	 * Gets the first level component.
	 * 
	 * @return the first level component
	 */
	public Property getFirstLevelComponent() {
		return firstLevelComponent;
	}
	
	/**
	 * Sets the parent.
	 * 
	 * @param parent
	 *            the new parent
	 */
	public void setParent(TreeParent parent) {
		this.parent = parent;
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public TreeParent getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Gets the doth path.
	 * 
	 * @return the doth path
	 */
	public String getDothPath(){ //TODO: refactor in order to correct the typo
		return this.dotPath;
	}
	
	/**
	 * Gets the dot path without first level component.
	 * 
	 * @return the dot path without first level component
	 */
	public String getDotPathWithoutFirstLevelComponent(){
		return this.dotPathWithoutFirstLevelComponent;
	}
	
	/**
	 * Checks if is leaf.
	 * 
	 * @return the boolean
	 */
	public Boolean isLeaf(){
		return this.isLeaf;
	}
	
	/**
	 * Checks if is root.
	 * 
	 * @return the boolean
	 */
	public Boolean isRoot(){
		return this.isRoot;
	}

	
	/**
	 * Checks if is of primitive type.
	 * 
	 * @return the boolean
	 */
	public Boolean isOfPrimitiveType(){
		if (this.property != null) {
			Type t = this.property.getType();
			if (t != null) {
				if (t instanceof PrimitiveType) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public Boolean isRequirementInstance(){
		if (this.property != null) {
			if (this.getComponentType() != null && this.getComponentType().getAppliedStereotype(stereotypeQName_Requirement) != null ) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Checks if is input.
	 * 
	 * @return the boolean
	 */
	public Boolean isInput(){
//		if (this.causality.equals("input")) {
//			return true;
//		}
		if (this.property != null) {
			if (isInputOrOutputVariable(this.property, "input")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if is output.
	 * 
	 * @return the boolean
	 */
	public Boolean isOutput(){
//		if (this.causality.equals("output")) {
//			return true;
//			}
		if (this.property != null) {
			if (isInputOrOutputVariable(this.property, "output")) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Checks if is input or output variable.
	 * 
	 * @param property
	 *            the property
	 * @param causalityString
	 *            the causality string
	 * @return the boolean
	 */
	private Boolean isInputOrOutputVariable(Property property, String causalityString){
		Boolean result = false;
		Object causality = null;
		if (property instanceof Port) {
			causality = UmlServices.getStereotypeValue(property, "ConnectionPort", "causality");				
		}
		else if (property instanceof Property) {
			causality = UmlServices.getStereotypeValue(property, "Variable", "causality");								
		}

		if (causality != null) {
			String causalityValue = ((EnumerationLiteral)causality).getName(); 
			if (causalityValue.equals(causalityString) ) {
				result = true;
			}
		}
		return result;
	}
	
	
	public void setFinalModificationSource(NamedElement finalModificationSource) {
		this.finalModificationSource = finalModificationSource;
	}
	
	public NamedElement getFinalModificationSource() {
		if (this.finalModificationSource == null) {
			return modificationSource;
		}
		return finalModificationSource;
	}
	public void setModificationSource(NamedElement modificationSource) {
		this.modificationSource = modificationSource;
		this.finalModificationSource = modificationSource;
	}
	public NamedElement getModificationSource() {
		return modificationSource;
	}
	
	
	
	public Boolean getHasInputs() {
		return hasInputs;
	}
	public void setHasInputs(TreeParent root) {
		this.hasInputs = true;
//		System.err.println(this.getName() + " has inputs");
		if (this.getParent() != null && !this.getParent().equals(root) && this.getParent().hasInputs == null) {
			this.getParent().setHasInputs(root);
		}
	}
	public Boolean getHasOutputs() {
		return hasOutputs;
	}
	public void setHasOutputs(TreeParent root) {
		this.hasOutputs = true;
//		System.err.println(this.getName() + " has inputs");
		if (this.getParent() != null && !this.getParent().equals(root) && this.getParent().hasOutputs == null) {
			this.getParent().setHasInputs(root);
		}
	}
	
	public Boolean getHasRequirements() {
		if (hasRequirements == null) {
			return false;
		}
		return hasRequirements;
	}
	public void setHasRequirements(TreeParent root) {
		this.hasRequirements = true; // this attribute is only set to true, never to false. 
//		System.err.println(this.getName() + " hasRequirements");
		if (this.getParent() != null && !this.getParent().equals(root) && this.getParent().hasRequirements == null) { // it stops when this attribute is already set.
			this.getParent().setHasRequirements(root);
		}
	}
	
	public Boolean hasRedeclaredType() {
		if (this.getProperty() != null) {
			Property property = this.getProperty(); 
			Type pType = this.getProperty().getType();
			if (property != null && pType != null && this.getComponentType() != null) {
				if (!pType.equals(this.getComponentType())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String getFinalModificationDescription() {
		if (finalModificationSource != null ) {
			if (finalModificationSource instanceof Class) {
				return "by class extension modification";
			}
			else {
				 return "by component modification";
			}
		}
		else if (this.modificationSource != null) {
			if (this.modificationSource instanceof Class) {
				return "by inheritance modification";
			}
			else {
				 return "by component modification";
			}
		}	
		return "";
	}
	
	
	
	
	
	
	
	

	public void setModificationStoreLocation(Element modificationStoreLocation) {
		this.modificationStoreLocation = modificationStoreLocation;
	}


	public Element getModificationStoreLocation() {
		return modificationStoreLocation;
	}

	public Boolean isInherited(){
		if (getModificationStoreLocation() instanceof Generalization) {
			return true;
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(java.lang.Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}


//	public void setFinalModificationDescription(
//			String finalModificationDescription) {
//		this.finalModificationDescription = finalModificationDescription;
//	}






	

	
}
