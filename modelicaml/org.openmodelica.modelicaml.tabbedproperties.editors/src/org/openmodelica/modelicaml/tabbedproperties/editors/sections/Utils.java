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

package org.openmodelica.modelicaml.tabbedproperties.editors.sections;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Transition;


// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {
	
	/**
	 * Gets the context class.
	 *
	 * @param element the element
	 * @return the context class
	 */
	public static Class getContextClass(Element element){

		if (element instanceof Class && !(element instanceof Behavior)) {
			return (Class)element;
		}

		//------------------- OpaqueBehavior
		if (element instanceof OpaqueBehavior) {
			return getContextClass(element.getOwner());
		}
		
		//------------------- State Machine
		if (element instanceof State) {
			return getContextClass(element.getOwner());
		}
		if (element instanceof Region) {
			return getContextClass(element.getOwner());
		}
		if (element instanceof StateMachine) {
			return getContextClass(element.getOwner());
		}
		if (element instanceof Transition) {
			return getContextClass(element.getOwner());
		}

		//------------------- Activity
		if (element instanceof ActivityEdge) {
			return getContextClass(element.getOwner());
		}
		if (element instanceof OpaqueAction) {
			return getContextClass(element.getOwner());
		}
		if (element instanceof Activity) {
			return getContextClass(element.getOwner());
		}

		if (element instanceof NamedElement) {
			return getContextClass(element.getOwner());
		}
		
		return null;
	}
	
//	public static  Boolean isModelicaMLClassInstance(Stereotype stereotype){
//		if(stereotype.getName().equals(ModelicaMLProperties.s_calculatedProperty) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_functionArgument) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_requirementInstance) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_component) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_variable) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_port) 
//
//		)
//			return true;
//		else
//			return false;
//	}
//	
//	public static  Boolean isModelicaMLComponent(Stereotype stereotype){
//		if(
//		   stereotype.getName().equals(ModelicaMLProperties.s_component) 
//		)
//			return true;
//		else
//			return false;
//	}
//	
//	public static  Boolean isModelicaMLFunction(Stereotype stereotype){
//		if(stereotype.getName().equals(ModelicaMLProperties.s_function))
//			return true;
//		else
//			return false;
//	}
//	
//	public static Boolean isModelicaMLRestrictedClass(Stereotype stereotype){
//		if(stereotype.getName().equals(ModelicaMLProperties.s_block) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_model) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_calculationModel) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_connector) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_function) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_enumeration) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_package) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_record) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_type) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_requirement) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_operator) ||
//		   stereotype.getName().equals(ModelicaMLProperties.s_modelicaClass) 
//		)
//			return true;
//		else
//			return false;
//	}
//	
//	public static Boolean isModelicaMLVariable(Stereotype stereotype){
//		if(stereotype.getName().equals(ModelicaMLProperties.s_variable))
//			return true;
//		else
//			return false;
//	}
//	public static Boolean isModelicaMLFunctionArgument(Stereotype stereotype){
//		if(stereotype.getName().equals(ModelicaMLProperties.s_function_argument))
//			return true;
//		else
//			return false;
//	}
//	

	/**
 * Checks for attribute.
 * 
 * @param stereotype
 *            the stereotype
 * @param attribute
 *            the attribute
 * @return the boolean
 */
public static Boolean hasAttribute(Stereotype stereotype, String attribute){
		Boolean tmpbool = false;
		EList<Property> allAttributes = stereotype.getAllAttributes();
		Iterator<Property> iter = allAttributes.iterator();
		while (iter.hasNext()) {
			Property property = (Property) iter.next();
			if(property.getName().equals(attribute))
				tmpbool = true;
		}
		return tmpbool;
	}
	
	/**
	 * Checks for stereotype.
	 * 
	 * @param element
	 *            the element
	 * @param stereotypeName
	 *            the stereotype name
	 * @return the boolean
	 */
	public static Boolean hasStereotype(Element element, String stereotypeName){
		Boolean tmpbool = false;
		EList<Stereotype> allStereotypes = element.getAppliedStereotypes();
		Iterator<Stereotype> iter = allStereotypes.iterator();
		while (iter.hasNext()) {
			Stereotype stereo = (Stereotype) iter.next();
			if(stereo.getQualifiedName().equals(stereotypeName))
				tmpbool = true;
		}
		return tmpbool;
	}
	
	/**
	 * Gets the attribute.
	 * 
	 * @param stereotype
	 *            the stereotype
	 * @param attribute
	 *            the attribute
	 * @return the attribute
	 */
	public static Property getAttribute(Stereotype stereotype, String attribute){
		EList<Property> allAttributes = stereotype.getAllAttributes();
		Iterator<Property> iter = allAttributes.iterator();
		while (iter.hasNext()) {
			Property property = (Property) iter.next();
			if(property.getName().equals(attribute))
				return property;
		}
		return null;
	}
//	public static Boolean isAlgorithm(Stereotype stereotype){
//		if(stereotype.getName().equals(ModelicaMLProperties.s_algorithm) ||		 
//				stereotype.getName().equals(ModelicaMLProperties.s_algorithmstatements) ||		 
//				stereotype.getName().equals(ModelicaMLProperties.s_conditionalalgorithm) ||		 
//				stereotype.getName().equals(ModelicaMLProperties.s_initialalgorithm) ||		 
//				stereotype.getName().equals(ModelicaMLProperties.s_initialconditionalalgorithm))
//			return true;
//		else
//			return false;
//	}
	
	
	/**
 * Convert the list of element in a list of string after some basic checks.
 * 
 * @param behavior
 *            the behavior
 * @param language
 *            the language
 * @return the string array from list
 */
//	public static String[] getStringArrayFromList(List<?> elements) {
//
//		ArrayList<String> tmp = new ArrayList<String>();
//
//		// if no possible selection : abort
//		if (elements.size() == 0) {
//			return null;
//		}
//
//		// First create tmp list
//		Iterator<?> it = elements.iterator();
//		while (it.hasNext()) {
//			Object current = it.next();
//
//			String label = Util.getLabel(current, false);
//			int tmpint = label.lastIndexOf(':');
//			tmp.add(label.substring(tmpint+1));
//		}
//
//		// if no possible selection : abort
//		if (tmp.size() == 0) {
//			return null;
//		}
//
//		// Put this in array
//		String[] elementsNames = new String[tmp.size()];
//		for (int i = 0; i < tmp.size(); i++) {
//			elementsNames[i] = tmp.get(i);
//		}
//
//		// PostCondition
//		if (elementsNames.length != elements.size()) {
//			Message.error("Number of elements in name list does not match number or elements.");
//			elementsNames = null;
//		}
//		return elementsNames;
//	}
	
	/**
	 * Returns the index of the body for a given langauge.
	 * 
	 * @param language
	 *            the language of the body to find
	 * @return the index of the body in the list or -1 if not found
	 */
	public static int getBodyIndex(OpaqueAction behavior, String language) {
		int index = 0;
		boolean isFound = false;

		// test if the language exists
		Iterator<String> it = behavior.getLanguages().iterator();
		while (it.hasNext() && !isFound) {
			String lang = it.next();
			if (lang.equalsIgnoreCase(language)) {
				isFound = true;
			} else {
				index++;
			}
		}
		// returns -1 if not found
		if (!isFound) {
			index = -1;
		}
		return index;
	}
	
	
	

}
