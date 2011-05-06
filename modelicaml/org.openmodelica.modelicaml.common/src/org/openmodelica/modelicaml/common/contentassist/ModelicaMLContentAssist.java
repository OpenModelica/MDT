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
package org.openmodelica.modelicaml.common.contentassist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.display.IconProvider;
import org.openmodelica.modelicaml.common.services.StringUtls;

// TODO: REFACTOR this!

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLContentAssist.
 */
public class ModelicaMLContentAssist {

	/** The component reference list. */
	private static HashSet<String> componentReferenceList = new HashSet<String>();
	
	/** The variables list. */
	private static HashSet<String> variablesList = new HashSet<String>();
	
	/** The predefined variable properties list. */
	private static HashSet<String> predefinedVariablePropertiesList = new HashSet<String>();
	
	/** The states list. */
	private static HashSet<String> statesList = new HashSet<String>();
	
	/** The predefined state properties list. */
	private static HashSet<String> predefinedStatePropertiesList = new HashSet<String>();
	
	/** The ports list. */
	private static HashSet<String> portsList = new HashSet<String>(); // TODO
	
	/** The signals list. */
	private static HashSet<String> signalsList = new HashSet<String>();
	
	/** The functions list. */
	private static HashSet<String> functionsList = new HashSet<String>();
	
	/** The predefined functions list. */
	private static HashSet<String> predefinedFunctionsList = new HashSet<String>();
	
	
	private static HashSet<String> importedElementsList = new HashSet<String>();
	
	/** The Constant stateIconFileName. */
	private static final String stateIconFileName = "State.gif";
	
	/** The Constant variableIconFileName. */
	private static final String variableIconFileName = "variable.png";
	
	/** The Constant signalIconFileName. */
	private static final String signalIconFileName = "Signal.gif";
	
	/** The Constant portIconFileName. */
	private static final String portIconFileName = "port.gif";
	
	/** The Constant functionBehaviorIconFileName. */
	private static final String functionBehaviorIconFileName = "FunctionBehavior.gif"; 
	
	/** The class icon file name. */
	private static String classIconFileName = "Class.gif";
	
	/** The selected source element. */
	private static Element selectedSourceElement; 
	
	/** The property name. */
	private static String propertyName = null;

	/** The types list. */
	private static HashSet<String> typesList = new HashSet<String>();

	
	private static String connectorStereotypeQname = "ModelicaML::ModelicaClassConstructs::Connector";
	private static String recordStereotypeQname = "ModelicaML::ModelicaClassConstructs::Record";
	
	
	/**
	 * Sets the property name.
	 *
	 * @param name the new property name
	 */
	public static void setPropertyName(String name) {
		propertyName = name;
	}
	
	public static String getPropertyName(){
		return propertyName;
	}
	
	/**
	 * Creates the component referencelist.
	 *
	 * @param aClass the a class
	 */
	
	public static void clearAllLists(){
		// clear all lists at any time this method is called in order to avoid using wrong references.
		variablesList.clear();
		predefinedVariablePropertiesList.clear();
		
		statesList.clear();
		predefinedStatePropertiesList.clear();
		
		portsList.clear();
		
		signalsList.clear();
		
		functionsList.clear();
		predefinedFunctionsList.clear();
		
		typesList.clear();
		
		importedElementsList.clear();
		
		componentReferenceList.clear();
	}
	
	public static void createComponentReferencelist(Class aClass){
		// clear all lists at any time this method is called in order to avoid using wrong references.
		
		clearAllLists();

		// if it is a Modelica function
		if (aClass instanceof FunctionBehavior ) {
			addPredefinedVariablesToList();
			addFunctionParametersToList( (FunctionBehavior) aClass, "");
			
			addBuiltInModelicaFuntionsToList();
			
			addFunctionsToList(aClass);
			
			componentReferenceList.addAll(variablesList);
			componentReferenceList.addAll(functionsList);
		}
		
		// if it is a Modelica class 
		else if (aClass !=  null && aClass instanceof Classifier) {
			
			addPredefinedVariablesToList();
			addVariablesToList(aClass, ""); 

			// Imported elements
			addImportedElementsToList(aClass, "");
			
			addBuiltInModelicaFuntionsToList();
			addFunctionsToList(aClass);
			
			componentReferenceList.addAll(variablesList);
			componentReferenceList.addAll(portsList); // TODO
			componentReferenceList.addAll(statesList);
			componentReferenceList.addAll(signalsList);
			componentReferenceList.addAll(functionsList);
		}
		
		addTypeSpecifiersToList(aClass);
	}
	
	
	public static void replaceFirstComponentName(String replaceString){
		HashSet<String> refList = new HashSet<String>();
		refList.addAll(componentReferenceList);
		for (String path : refList) {
			if (propertyName != null && replaceString != null && path.startsWith(propertyName)) {
				componentReferenceList.remove(path);
				System.err.println("name: " + propertyName);
				System.err.println("replaced: " + path.replaceFirst(propertyName, replaceString));
				componentReferenceList.add(path.replaceFirst(propertyName, replaceString));
			}
		}
	}
	

	
//	public static void addComponentsOfThisClassToComponentReferencelist(Class aClass){
//		if (aClass !=  null && aClass instanceof Classifier) {
//			//TODO: how to deal with imported models?! 
//			
//			addPredefinedVariablesToList();
//			addVariablesToList(aClass, ""); 
//			
//			addBuiltInModelicaFuntionsToList();
//			addFunctionsToList(aClass);
//			
//			componentReferenceList.addAll(variablesList);
//			componentReferenceList.addAll(portsList); // TODO
//			componentReferenceList.addAll(statesList);
//			componentReferenceList.addAll(signalsList);
//			componentReferenceList.addAll(functionsList);
//		}
//		
//		addTypeSpecifiersToList(aClass);
//	}
	
	
	
	// to be used for validation
	/**
	 * Gets the full component reference sorted list.
	 *
	 * @return the full component reference sorted list
	 */
	public static List<String> getFullComponentReferenceSortedList(){
		List<String> sortedList = new ArrayList<String>();

		// add the list for code completion
		sortedList.addAll(componentReferenceList);
		
		// in general used for code from macros that lead to for-loop code.
		sortedList.add("i");
		
		// add the rest (predefined properties) in order to support the validation
		sortedList.addAll(predefinedVariablePropertiesList);
		sortedList.addAll(predefinedFunctionsList);
		sortedList.addAll(predefinedStatePropertiesList);
		
		// if it is a component modification then add all possible sub components of the component being modified
		if (propertyName != null) {
			sortedList.addAll(getFullModifiedComponentReferenceSortedList());
		}
		
		Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		return sortedList;
	}
	
	// to be used for code completion
	/**
	 * Gets the component reference sorted list.
	 *
	 * @return the component reference sorted list
	 */
	public static List<String> getComponentReferenceSortedList(){
		List<String> sortedList = new ArrayList<String>();
		// add only the list for code completion
		sortedList.addAll(componentReferenceList);
		
		// if it is a component modification then add all possible sub components of the component being modified
		if (propertyName != null) {
			sortedList.addAll(getModifiedComponentReferenceSortedList());
		}
		Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		return sortedList;
	}
	
	

	
	// ############## MODIFICATION
	
	// to be used for code completion
	/**
	 * Gets the modified component reference sorted list.
	 *
	 * @return the modified component reference sorted list
	 */
	public static List<String> getModifiedComponentReferenceSortedList(){
		List<String> sortedList = new ArrayList<String>();
		
		if (selectedSourceElement instanceof Generalization) {
			EList<Property> inheritedAttributes = getAllInheritedAttributes( ((Generalization)selectedSourceElement).getSpecific(), new BasicEList<Property>());
			HashSet<String> inheritedAttributesNames = new HashSet<String>();
			
			for (Property property : inheritedAttributes) {
				inheritedAttributesNames.add(StringUtls.replaceSpecChar(property.getName()));
			}
			
			for (String reference : componentReferenceList) {
				for (String string : inheritedAttributesNames) {
					if (reference.startsWith(string)) {
						if ( !functionsList.contains(reference) && !statesList.contains(reference)) {
							sortedList.add(reference);
						}
					}
				}
			}
		}
		else if (propertyName != null) {
			for (String reference : componentReferenceList) {
				if (reference.startsWith(propertyName)) {
					if (!statesList.contains(reference) && !functionsList.contains(reference)) {
						sortedList.add(reference.replaceFirst(propertyName + ".", ""));
					}
				}
			}
		}

		Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		return sortedList;
	}
	
	
	
	// to be used for validation
	/**
	 * Gets the full modified component reference sorted list.
	 *
	 * @return the full modified component reference sorted list
	 */
	public static List<String> getFullModifiedComponentReferenceSortedList(){
		List<String> sortedList = new ArrayList<String>();

		if (selectedSourceElement instanceof Generalization) {
			EList<Property> inheritedAttributes = getAllInheritedAttributes( ((Generalization)selectedSourceElement).getSpecific(), new BasicEList<Property>());
			HashSet<String> inheritedAttributesNames = new HashSet<String>();
			
			for (Property property : inheritedAttributes) {
				inheritedAttributesNames.add(StringUtls.replaceSpecChar(property.getName()));
			}
			
			for (String reference : componentReferenceList) {
				for (String string : inheritedAttributesNames) {
					if (reference.startsWith(string)) {
						if ( !functionsList.contains(reference) && !statesList.contains(reference)) {
							sortedList.add(reference);
						}
					}
				}
			}
		}
		else if (propertyName != null) {

			// collect references 
			for (String reference : componentReferenceList) {
				if (reference.startsWith(propertyName)) {
					if (!statesList.contains(reference)) {
						sortedList.add(reference.replaceFirst(propertyName + ".", ""));
					}
				}
			}
			// collect the predefined properties
			for (String reference : predefinedVariablePropertiesList) {
				if (reference.startsWith(propertyName)) {
					//sortedList.add(reference); // IMPORTANT: this is used for component tree in order to enable modifications that are stored in first level components.
					sortedList.add(reference.replaceFirst(propertyName + ".", ""));
				}
			}
			
			Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		}
		
		//System.err.println(sortedList);
		return sortedList;
	}
	
	// ############## MODIFICATION END
	
	
	
	
	
	
	
	/**
	 * Gets the type specifier sorted list.
	 *
	 * @return the type specifier sorted list
	 */
	public static List<String> getTypeSpecifierSortedList(){
		List<String> sortedList = new ArrayList<String>();
		sortedList.addAll(typesList);
		Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		return sortedList;
	}
	
	
	
	
	
	
	
	
	/**
	 * Adds the predefined variables to list.
	 */
	private static void addPredefinedVariablesToList(){
		variablesList.add("time"); 
//		variablesList.add("i"); // used for iterations in e.g. marco expansions.
	}
	
	/**
	 * Adds the built in Modelica functions to list.
	 */
	private static void addBuiltInModelicaFuntionsToList(){
		predefinedFunctionsList.add("abs");
		predefinedFunctionsList.add("sign");
		
		predefinedFunctionsList.add("sqrt");
		predefinedFunctionsList.add("Integer");
		predefinedFunctionsList.add("String");
		predefinedFunctionsList.add("div");
		predefinedFunctionsList.add("mod");
		predefinedFunctionsList.add("rem");
		predefinedFunctionsList.add("ceil");
		predefinedFunctionsList.add("floor");
		predefinedFunctionsList.add("integer");
		
		predefinedFunctionsList.add("sin");
		predefinedFunctionsList.add("cos");
		predefinedFunctionsList.add("tan");
		predefinedFunctionsList.add("asin");
		predefinedFunctionsList.add("acos");
		predefinedFunctionsList.add("atan");
		predefinedFunctionsList.add("atan2");
		predefinedFunctionsList.add("sinh");
		predefinedFunctionsList.add("cosh");
		predefinedFunctionsList.add("tanh");
		predefinedFunctionsList.add("exp");
		predefinedFunctionsList.add("log");
		predefinedFunctionsList.add("log10");
		
		predefinedFunctionsList.add("delay");
		predefinedFunctionsList.add("cardinality");
		predefinedFunctionsList.add("homotopy");
		predefinedFunctionsList.add("semiLinear");
		predefinedFunctionsList.add("Subtask.decouple");
		
		predefinedFunctionsList.add("initial");
		predefinedFunctionsList.add("terminal");
		predefinedFunctionsList.add("noEvent");
		predefinedFunctionsList.add("smooth");
		predefinedFunctionsList.add("sample");
		predefinedFunctionsList.add("pre");
		predefinedFunctionsList.add("edge");
		predefinedFunctionsList.add("change");
		predefinedFunctionsList.add("reinit");
		
		predefinedFunctionsList.add("terminate");
		predefinedFunctionsList.add("assert");
		
		//Reduction Functions and Operators (see Modelica spec. 3.2)
		predefinedFunctionsList.add("min");
		predefinedFunctionsList.add("max");
		predefinedFunctionsList.add("sum");
		predefinedFunctionsList.add("product");

	}
	

	/**
	 * Gets the component reference list.
	 *
	 * @return the component reference list
	 */
	@Deprecated
	public static HashSet<String> getComponentReferenceList(){
		return componentReferenceList;
	}

	
	/**
	 * Gets the icon.
	 *
	 * @param dothPath the doth path
	 * @return the icon
	 */
	public static Image getIcon(String dothPath){
		Image image = null;
		
		// see if this dothPath belongs to a port.
		for (String string : portsList) {
			if (dothPath.startsWith(string)) {
				image = IconProvider.getIcon(portIconFileName);
				return image;
			}
		}
		
		if (variablesList.contains(dothPath)) {
			image = IconProvider.getIcon(variableIconFileName);
			return image;
		}
		if (propertyName!= null) {
			if (getModifiedComponentReferenceSortedList().contains(dothPath)) {
				image = IconProvider.getIcon(variableIconFileName);
				return image;
			}
		}
		if (statesList.contains(dothPath)) {
			image = IconProvider.getIcon(stateIconFileName);
			return image;
		}
		if (signalsList.contains(dothPath)) {
			image = IconProvider.getIcon(signalIconFileName);
			return image;
		}
		if (functionsList.contains(dothPath)) {
			image = IconProvider.getIcon(functionBehaviorIconFileName);
			return image;
		}
		if (typesList.contains(dothPath)) {
			image = IconProvider.getIcon(classIconFileName);
			return image;			
		}
		
		return null;
	}
	
	
	// ################################# Functions and function parameters 
	/**
	 * Adds the functions to list.
	 *
	 * @param aClass the a class
	 */
	private static void addFunctionsToList(Class aClass){
		Model model = aClass.getModel();
		String dotPath = null;
		Iterator<EObject> i = model.eAllContents();
		List<EObject> functions = new ArrayList<EObject>() ;
		while (i.hasNext()) {
			EObject object = i.next() ;
			if (object instanceof FunctionBehavior)
				functions.add(object) ;
		}
		for (EObject eObject : functions) {
			dotPath = StringUtls.replaceSpecCharExceptThis(((FunctionBehavior)eObject).getQualifiedName().replaceFirst(aClass.getQualifiedName() + "::", "").replaceAll("::", "."), "\\.");
			functionsList.add(dotPath);
			
		}
	}
	
	/**
	 * Adds the type specifiers to list.
	 *
	 * @param aClass the a class
	 */
	private static void addTypeSpecifiersToList(Class aClass){
		Model model = aClass.getModel();
		String dotPath = null;
		Iterator<EObject> i = model.eAllContents();
		List<EObject> classifiers = new ArrayList<EObject>() ;
		while (i.hasNext()) {
			EObject object = i.next() ;
			if (object instanceof Element && isModelicaClass((Element)object))
				classifiers.add(object) ;
		}
		for (EObject eObject : classifiers) {
			dotPath = StringUtls.replaceSpecCharExceptThis(((Classifier)eObject).getQualifiedName().replaceAll("::", "."), "\\.");
			typesList.add(dotPath);
		}
	}
	
	
	/**
	 * Checks if is Modelica class.
	 *
	 * @param element the element
	 * @return the boolean
	 */
	private static Boolean isModelicaClass(Element element){
		if (element.getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_ModelicaPackage) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Operator) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Model) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Block) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Connector) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Record) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Type) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_ModelicaEnumeration) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Function) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_ModelicaModelReference) != null) { return true; }
		if (element.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) { return true; }
		return false;
	}
	
	/**
	 * Adds the function parameters to list.
	 *
	 * @param aClass the a class
	 * @param dotPath the dot path
	 */
	private static void addFunctionParametersToList(FunctionBehavior aClass, String dotPath){
		EList<Parameter> parameterList = aClass.getOwnedParameters();
		for (Parameter parameter : parameterList) {
			String newDotPath = getNewDotPath(parameter, dotPath);
			
			Type pType = parameter.getType();
			if (pType != null) {
				if (pType instanceof PrimitiveType || pType instanceof Enumeration) {
					//Add to the componentReferenceList
					variablesList.add(newDotPath);
					
					// create predefined properties for Modelica real, string, integer and boolean.
					addPredefinedTypePropertiesToList(parameter, newDotPath);			
				}
				else if (pType instanceof Class) {
					if (((Class)pType)!= aClass) { // TODO: prevent endless looping, implement it correctly!
						addVariablesToList((Class) pType, newDotPath);
					}
				}
			}
		}
		
		//add the properties 
		EList<Property> pList = aClass.getAttributes();
		
		for (Property property : pList) {
			String newDotPath = getNewDotPath(property, dotPath); 
			
			Type pType = property.getType();
			if (pType != null) {
				if (pType instanceof PrimitiveType || pType instanceof Enumeration) {
					//Add to the componentReferenceList
					variablesList.add(newDotPath);
					
					// create predefined properties for Modelica real, string, integer and boolean.
					addPredefinedTypePropertiesToList(property, newDotPath);			
				}
				else if (pType instanceof Class) {
					if (((Class)pType)!= aClass) { // TODO: prevent endless looping, implement it correctly!
						addVariablesToList((Class) pType, newDotPath);
					}
				}
			}
		}
		
	}
	
	// ################################# Add imported elements
	private static void addImportedElementsToList(Class aClass, String dotPath){
		EList<Dependency> clientList = aClass.getClientDependencies();
		for (Dependency dependency : clientList) {
			EList<NamedElement> suppliersList = dependency.getSuppliers();
			for (NamedElement namedElement : suppliersList) {
				if (namedElement instanceof Enumeration) {
					for (NamedElement literal : ((Enumeration)namedElement).getOwnedLiterals()) {
						importedElementsList.add(StringUtls.replaceSpecChar(namedElement.getName()) + "." + StringUtls.replaceSpecChar(literal.getName()) );
					}
				}
				else if (namedElement instanceof FunctionBehavior) {
					// TODO: test it!
					importedElementsList.add(StringUtls.replaceSpecChar(namedElement.getName()));
				}
				else if (namedElement instanceof Class) {
					// TODO: test it!
					addVariablesToList((Class)namedElement, "");
				}
			}
		}
		variablesList.addAll(importedElementsList);
	}
	
	
	// ################################# Add variables to list
	
	/**
	 * Adds the variables to list.
	 *
	 * @param aClass the a class
	 * @param dotPath the dot path
	 */
	private static void addVariablesToList(Class aClass, String dotPath){
		//TODO: refactor this! Use the Class instantiation for collecting the variables.
		
		EList<Property> pList = getClassComponents(aClass);
		
		for (Property property : pList) {
			String newDotPath = getNewDotPath(property, dotPath); 
			
			if (property instanceof Port) {
				portsList.add(newDotPath);
			}
			
			Type pType = property.getType();
			if (pType != null) {
				
				// Add instantces of connectors or records to the list
				Stereotype c_stereotype = pType.getAppliedStereotype(connectorStereotypeQname);
				Stereotype r_stereotype = pType.getAppliedStereotype(recordStereotypeQname);
				
				if (c_stereotype != null || r_stereotype != null) {
					variablesList.add(newDotPath);
				}
				
				if (pType instanceof PrimitiveType || pType instanceof Enumeration) {
					
					//Add primitive variable or enumeration to the componentReferenceList
					variablesList.add(newDotPath);
					
					// create predefined properties for Modelica real, string, integer and boolean.
					addPredefinedTypePropertiesToList(property, newDotPath);			
				}
				else if (pType instanceof Class) {
					if (((Class)pType)!= aClass) { // TODO: prevent endless looping, implement it correctly!
						addVariablesToList((Class) pType, newDotPath);
					}
				}
			}
		}
		
		// add signals and state machines
		EList<Element> eList = aClass.getOwnedElements();
		for (Element element : eList) {
			if (element instanceof Signal) {
				String newDotPath = getNewDotPath((Signal)element, dotPath); 
				signalsList.add(newDotPath);
				signalsList.add(newDotPath + ".counter");
			}
			if (element instanceof StateMachine) {
				if ( ((StateMachine)element).getSubmachineStates().size() < 1 ) {
					String newDotPath = getNewDotPath((StateMachine)element, dotPath); 
					traverseStateMachine((StateMachine)element, newDotPath);
				}
			}
		}
	}
	
	/**
	 * Gets the class components.
	 *
	 * @param aClass the a class
	 * @return the class components
	 */
	private static EList<Property> getClassComponents(Classifier aClass){
		//TODO: implement here all Modelica specific filtering of components, such as redeclaration and merging of inherited duplicates.
		EList<Property> pFinalList = getAllInheritedAttributes(aClass, aClass.getAttributes());
		return pFinalList;
	}
	
	/**
	 * Gets the all inherited attributes.
	 *
	 * @param aClass the a class
	 * @param passedAList the passed a list
	 * @return the all inherited attributes
	 */
	private static EList<Property> getAllInheritedAttributes(Classifier aClass, EList<Property> passedAList){
		//TODO: verify if this works correctly for Modelica inheritance concept.
		HashSet<Property> mergedSet = new HashSet<Property>();
		mergedSet.addAll(passedAList);
		
		HashSet<Property> finalSet = new HashSet<Property>();
		EList<Property> finalList = new BasicEList<Property>();
		
		EList<Generalization> extendsRelations = aClass.getGeneralizations(); // TODO: filter on ModelicaML stereotypes
		
		if (extendsRelations.size() < 1) { // if there there is no inheritance then just return the passedAList
			finalList.addAll(passedAList);
		}
		else {// if there are inheritance then merge
			for (Generalization generalization : extendsRelations) {
				EList<Element> targets = generalization.getTargets();
				for (Element element : targets) {
					if (element instanceof Classifier) {
						EList<Property> inheritedProperties = ((Classifier)element).getAttributes();
						mergedSet.addAll(inheritedProperties);
						for (Property propertyInherited : inheritedProperties) {
							String nameInheritedProperty = propertyInherited.getName();
							for (Property propertyPassed : passedAList) {
								String namePropertyPassed = propertyPassed.getName();
								if (nameInheritedProperty.equals(namePropertyPassed)) {
									mergedSet.remove(propertyInherited);
									//System.out.println("removed: " + propertyInherited.getName());
								}
							}
						}
						EList<Property> list = new BasicEList<Property>();
						list.addAll(mergedSet);
						finalSet.addAll(getAllInheritedAttributes((Classifier)element, list));
					}
				}		
			}
		}
		finalList.addAll(finalSet);
		return finalList;
	}
	
	/**
	 * Adds the predefined type properties to list.
	 *
	 * @param property the property
	 * @param dotPath the dot path
	 */
	private static void addPredefinedTypePropertiesToList(TypedElement property, String dotPath){
		Type type = property.getType();
		
		if (type != null) {
			if (type instanceof Classifier) {
				EList<Property> ModelicaPredefinedTypeProperties = ((Classifier)type).getAllAttributes();
				for (Property p : ModelicaPredefinedTypeProperties) {
				
					String newDotPath = getNewDotPath(p, dotPath);
					//Add to variables list 
					//variablesList.add(newDotPath);
					
					//Add to the full list of variables that is used for validation but not for code completion
					predefinedVariablePropertiesList.add(newDotPath);
				}
			}
		}
	}
	
	
//	private static void addPredefinedTypePropertiesToList(Property property, String dotPath){
//		Type type = property.getType();
//		
//		if (type != null) {
//			if (type instanceof Classifier) {
//				EList<Property> ModelicaPredefinedTypeProperties = ((Classifier)type).getAllAttributes();
//				for (Property p : ModelicaPredefinedTypeProperties) {
//				
//					String newDotPath = getNewDotPath(p, dotPath);
//					//Add to the componentReferenceList
//					variablesList.add(newDotPath);
//				}
//			}
//		}
//	}
	
//	private static void addFunctionParameterPredefinedTypePropertiesToList(Parameter parameter, String dotPath){
//		Type type = parameter.getType();
//		
//		if (type != null) {
//			if (type instanceof Classifier) {
//				EList<Property> ModelicaPredefinedTypeProperties = ((Classifier)type).getAllAttributes();
//				for (Property p : ModelicaPredefinedTypeProperties) {
//				
//					String newDotPath = getNewDotPath(p, dotPath);
//					//Add to the componentReferenceList
//					variablesList.add(newDotPath);
//				}
//			}
//		}
//	}
	
	
	// ################################# Add ModelicaML states representation variables to list
	
//	private static void addStateMachinesToList(Class aClass, String dotPath){
//		EList<Behavior> sList = aClass.getOwnedBehaviors();
//		for (Element element : sList) {
//			if (element instanceof StateMachine) {
//				if ( ((StateMachine)element).getSubmachineStates().size() < 1 ) {
//					String newDotPath = getNewDotPath((StateMachine)element, ""); 
//					traverseStateMachine((StateMachine)element, newDotPath);
//				}
//			}
//		}
//	}

	/**
 * Traverse state machine.
 *
 * @param sm the sm
 * @param dotPath the dot path
 */
private static void traverseStateMachine(StateMachine sm, String dotPath){
		EList<Region> rList = sm.getRegions();
		for (Region region : rList) {
//			String newDotPath = getNewDotPath(region, dotPath); 
//			addStatesFromRegion(region, newDotPath);
			addStatesFromRegion(region, dotPath);
		}
	}
	
	/**
	 * Adds the states from region.
	 *
	 * @param r the r
	 * @param dotPath the dot path
	 */
	private static void addStatesFromRegion(Region r, String dotPath) {
		
		String regionDotPath = getNewDotPath(r, dotPath); 
		
		EList<Vertex> states = r.getSubvertices();
		for (Vertex vertex : states) {
			if (vertex instanceof State) {

				String newDotPath = getNewDotPath(vertex, regionDotPath); 
				
				// add the predefined properties of the ModelicaML state representation 
				statesList.add(newDotPath + ".active");
//				statesList.add(newDotPath + ".stime");
//				statesList.add(newDotPath + ".timeAtActivation");
//				statesList.add(newDotPath + ".selfTransitionActivated");
				
				//add to the predefined list
				predefinedStatePropertiesList.add(newDotPath + ".stime");
				predefinedStatePropertiesList.add(newDotPath + ".timeAtActivation");
				predefinedStatePropertiesList.add(newDotPath + ".selfTransitionActivated");
				
				// add composite state states
				if ( ((State)vertex).isComposite() ) {
					EList<Region> rList = ((State)vertex).getRegions();
					for (Region region : rList) {
						addStatesFromRegion(region, newDotPath);
					}
				}
				// add sub stateMachine state states
				if (((State)vertex).isSubmachineState()) {
					StateMachine subSm = ((State)vertex).getSubmachine();
					EList<Region> rList = subSm.getRegions();
					for (Region region : rList) {
						addStatesFromRegion(region, newDotPath);
					}
				}
			}
		}
	}
	
	
//	private Type getModelicaPremitiveType (Class aClass, String type) {
//		if (type.equals("realType")) {
//			Type realType = aClass.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaReal");
//			return realType;
//		}
//		if (type.equals("integerType")) {
//			Type integerType = aClass.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaInteger");
//			return integerType;
//		}
//		if (type.equals("booleanType")) {
//			Type booleanType = aClass.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaBoolean");
//			return booleanType;
//		}
//		return null;
//	}
	

	
	// ################################# Add signals
	
//	private static void addSignalsToList(Class aClass, String dotPath){
//		
//		// get the signals inside the class
//		EList<Element> sList = aClass.getOwnedElements();
//		for (Element element : sList) {
//			if (element instanceof Signal) {
//				String newDotPath = getNewDotPath((Signal)element, dotPath); 
//				signalsList.add(newDotPath + ".counter");
//			}
//		}		
//	}
	
	
	
	// ################################# Common
	
	/**
 * Gets the new dot path.
 *
 * @param element the element
 * @param dotPath the dot path
 * @return the new dot path
 */
private static String getNewDotPath(NamedElement element, String dotPath){
		String newDotPath = ""; 
		if ( !dotPath.equals("")) {
			newDotPath = dotPath + "." + StringUtls.replaceSpecChar(element.getName());
		}
		else {
			newDotPath = StringUtls.replaceSpecChar(element.getName());
		}
		return newDotPath;
	}


	/**
	 * Sets the selected source element.
	 *
	 * @param selectedSourceElement the new selected source element
	 */
	public static void setSelectedSourceElement(Element selectedSourceElement) {
		ModelicaMLContentAssist.selectedSourceElement = selectedSourceElement;
	}


	/**
	 * Gets the selected source element.
	 *
	 * @return the selected source element
	 */
	public static Element getSelectedSourceElement() {
		return selectedSourceElement;
	}

	
	// display string is the text shown in the completion list. 
	/**
	 * Gets the display completion string.
	 *
	 * @param prefix the prefix
	 * @param completionString the completion string
	 * @return the display completion string
	 */
	public static String getDisplayCompletionString(String prefix, String completionString) {
		if (prefix.endsWith(".")) {
			return completionString.replaceFirst(prefix, "");
		}
		return completionString;
	}
	
}
