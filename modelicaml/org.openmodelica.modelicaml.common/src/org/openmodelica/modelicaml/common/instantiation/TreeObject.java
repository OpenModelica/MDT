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
package org.openmodelica.modelicaml.common.instantiation;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.UmlServices;



// TODO: Auto-generated Javadoc
/**
 * The Class TreeObject.
 */
public class TreeObject implements IAdaptable {
	private String stereotypeQName_Requirement = Constants.stereotypeQName_Requirement;
	private String stereotypeQName_Variable = Constants.stereotypeQName_Variable;
	
		/** The property. */
	private Property property = null;
	
	/** The name. */
	private String name;
	
	/** The parent. */
	private TreeParent parent;
	
	/** The dot path. */
	private String dotPath;
	
	/** The is leaf. */
	private Boolean isLeaf;
	
	private Boolean isPredefinedModelicaProperty = false;
	
	/** The is root. */
	private Boolean isRoot;
	
	/** The first level component. */
	private Property firstLevelComponent = null;
	
	/** The dot path without first level component. */
	private String dotPathWithoutFirstLevelComponent;

	/** The declaration. */
	private String declaration = null;
	
	/** The final modification right hand. */
	private String finalModificationRightHand = null; // TODO: rename to editedModification
	
	/** The final modification right hand. */
	private String modificationRightHand = null;
	
	/** The final modification source. */
	private Element finalModificationSource = null;
	private Element modificationSource = null;
	
	/** The final modification description. */
	private String finalModificationDescription = null;
	
	// TODO: implement getter and setter
	/** The element. */
	private NamedElement element = null;
	
	private Element modificationStoreLocation = null;
	
	/** The selected class. */
	private Class selectedClass;
	
	/** The component type. */
	private Type componentType = null;

	public Boolean hasInputs = null;
	public Boolean hasOutputs = null;
	public Boolean hasRequirements = null;
	public Boolean hasValueClients = null;
	public Boolean hasValueProviders = null;
	
	private boolean isValueClient = false;
	private boolean isValueProvider = false;

	public TreeObject(	String name, 
						Property property, 
						Property firstLevelComponent, 
						String dotPath, 
						Boolean isLeaf, 
						Boolean isRoot, 
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

		this.selectedClass = selectedClass;

		// declaration string
		setDeclaration();
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
	
	public Element getUmlElement(){
		if (this.property != null ) { return this.property; }
		if (this.element != null ) { return this.element; }
		return null;
	}
	
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
		if (this.declaration != null && !this.declaration.trim().equals("")) {
			return this.declaration;	
		}
		return null;
	}
	
	
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
	public String getDotPath(){ 
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
	
	
	public void setFinalModificationSource(Element finalModificationSource) {
		this.finalModificationSource = finalModificationSource;
	}
	
	public Element getFinalModificationSource() {
		if (this.finalModificationSource == null) {
			return modificationSource;
		}
		return finalModificationSource;
	}
	public void setModificationSource(Element modificationSource) {
		this.modificationSource = modificationSource;
		this.finalModificationSource = modificationSource;
	}
	public Element getModificationSource() {
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
	
	public Boolean hasRequirements() {
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
	
	public void setHasValueClients(TreeParent root) {
		this.hasValueClients = true; // this attribute is only set to true, never to false. 
//		System.err.println(this.getName() + " hasValueClients -> " + this.hasValueClients);
		if (this.getParent() != null && !this.getParent().equals(root) && this.getParent().hasValueClients == null) { // it stops when this attribute is already set.
			this.getParent().setHasValueClients(root);
		}
	}

	public Boolean hasValueClients() {
		if (hasValueClients == null) { return false; }			
		return hasValueClients;
	}
	
	public void setHasValueProviders(TreeParent root) {
		this.hasValueProviders = true; // this attribute is only set to true, never to false. 
//		System.err.println(this.getName() + " hasValueProviders");
		if (this.getParent() != null && !this.getParent().equals(root) && this.getParent().hasValueProviders == null) { // it stops when this attribute is already set.
			this.getParent().setHasValueProviders(root);
		}
	}

	public Boolean hasValueProviders() {
		if (hasValueProviders == null) { return false; }			
		return hasValueProviders;
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
	
	
	//  ####################################### for Value Bindings
	
	public void setIsValueClient(boolean isValueClient) {
		this.isValueClient = isValueClient;
	}

	public void setIsValueProvider(boolean isValueProvider) {
		this.isValueProvider = isValueProvider;
	}
	
	public Boolean isValueClient(){
//		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null) {
//			return true;
//		}
//		return false;
		return this.isValueClient;
	}
	public Boolean isValueProvider(){
//		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueProvider) != null) {
//			return true;
//		}
		return this.isValueProvider;
	}
	//  ####################################### 
	
	

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
		if (adapter == EObject.class) {
			if (isRoot) { 
				return selectedClass;
			}
			return getUmlElement();
		}
		if (adapter == TreeObject.class) {
			return this;
		}
		// TODO Auto-generated method stub
		return null;
	}


	public void setIsPredefinedModelicaProperty(Boolean isPredefinedModelicaProperty) {
		this.isPredefinedModelicaProperty = isPredefinedModelicaProperty;
	}


	public Boolean isPredefinedModelicaProperty() {
		return isPredefinedModelicaProperty;
	}



	
}
