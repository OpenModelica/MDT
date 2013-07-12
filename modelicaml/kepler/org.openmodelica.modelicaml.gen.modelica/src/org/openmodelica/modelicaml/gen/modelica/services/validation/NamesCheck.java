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
package org.openmodelica.modelicaml.gen.modelica.services.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Vertex;
import org.openmodelica.modelicaml.gen.modelica.services.MoldelicaMLValidator;

import fr.obeo.acceleo.gen.template.eval.ENodeCastException;

// TODO: Auto-generated Javadoc
/**
 * The Class NamesCheck.
 */
public class NamesCheck {

	/**
	 * Check for name duplicates of owned attributes.
	 * 
	 * @param classifier
	 *            the classifier
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public String checkForNameDuplicatesOfOwnedAttributes(Classifier classifier)
			throws ENodeCastException {
		String msg, result = null;
		Collection<String> names = new ArrayList<String>(); // a list that can
															// contain
															// duplicates.
		EList<Property> elements = classifier.getAllAttributes();

		for (Property property : elements) {
			String name = property.getName();
			names.add(name);
			isModelicaKeyword(property, name);
		}

		List duplicates = getDuplicate(names);

		if (duplicates.size() > 0) {
			// This creates one message per duplicate
			result = "Multiple class properties with the same name found.";
			for (Object object : duplicates) {
				msg = "NOT VALID(n01): There are multiple properties with the name "
						+ "'" + object.toString() + "' ";
				MoldelicaMLValidator.modelicaMLValidationAlert(classifier,
						"error", msg);
			}
		}

		return result;
	}

	/**
	 * Check for name duplicates of nested classes.
	 * 
	 * @param classifier
	 *            the classifier
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public String checkForNameDuplicatesOfNestedClasses(Classifier classifier)
			throws ENodeCastException {
		String msg, result = null;
		Collection<String> names = new ArrayList<String>(); // a list that can
															// contain
															// duplicates.
		EList<Element> elements = classifier.getOwnedElements();

		for (Element element : elements) {
			if (element instanceof Classifier) {
				String name = ((Classifier) element).getName();
				names.add(name);
				isModelicaKeyword(element, name);
			}
		}

		List duplicates = getDuplicate(names);

		if (duplicates.size() > 0) {
			// This creates one message per duplicate
			result = "Multiple nested elements with the same name found.";
			for (Object object : duplicates) {
				msg = "NOT VALID(n02): There are multiple elements with the name "
						+ "'" + object.toString() + "' ";
				MoldelicaMLValidator.modelicaMLValidationAlert(classifier,
						"error", msg);
			}
		}

		return result;
	}

	/**
	 * Check region for name duplicates of states.
	 * 
	 * @param region
	 *            the region
	 * @return the string
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public String checkRegionForNameDuplicatesOfStates(Region region)
			throws ENodeCastException {
		String msg, result = null;
		Collection<String> names = new ArrayList<String>(); // a list that can
															// contain
															// duplicates.
		EList<Vertex> elements = region.getSubvertices();

		for (Element element : elements) {
			if (element instanceof State) {
				String name = ((State) element).getName();
				names.add(name);
				isModelicaKeyword(element, name);
			}
		}

		List duplicates = getDuplicate(names);

		if (duplicates.size() > 0) {
			// This creates one message per duplicate
			result = "Multiple states with the same name found.";
			for (Object object : duplicates) {
				msg = "NOT VALID(n03): There are multiple states with the name "
						+ "'" + object.toString() + "' ";
				MoldelicaMLValidator.modelicaMLValidationAlert(region, "error",
						msg);
			}
		}
		return result;
	}

	/**
	 * Checks if is modelica keyword.
	 * 
	 * @param elt
	 *            the elt
	 * @param name
	 *            the name
	 */
	public void isModelicaKeyword(Element elt, String name) {
		String msg = null;
		if (ModelicaKeywords().contains(name)) {
			msg = "NOT VALID(n04): '"
					+ name
					+ "' is a Modelica keyword. It cannot be used as name for this "
					+ elt.eClass().getName() + ".";
			MoldelicaMLValidator.modelicaMLValidationAlert(elt, "error", msg);
		}

	}

	/**
	 * Modelica keywords.
	 * 
	 * @return the hash set
	 */
	public static HashSet<String> ModelicaKeywords() {
		HashSet<String> keywords = new HashSet<String>();

		keywords.add("algorithm");
		keywords.add("and");
		keywords.add("annotation");
		keywords.add("assert");
		keywords.add("block");
		keywords.add("break");
		keywords.add("class");
		keywords.add("connect");
		keywords.add("connector");
		keywords.add("constant");
		keywords.add("constrainedby");
		keywords.add("der");
		keywords.add("discrete");
		keywords.add("each");
		keywords.add("else");
		keywords.add("elseif");
		keywords.add("elsewhen");
		keywords.add("encapsulated");
		keywords.add("end");
		keywords.add("enumeration");
		keywords.add("equation");
		keywords.add("expandable");
		keywords.add("extends");
		keywords.add("external");
		keywords.add("false");
		keywords.add("final");
		keywords.add("flow");
		keywords.add("for");
		keywords.add("function");
		keywords.add("if");
		keywords.add("import");
		keywords.add("in");
		keywords.add("initial");
		keywords.add("inner");
		keywords.add("input");
		keywords.add("loop");
		keywords.add("model");
		keywords.add("not");
		keywords.add("operator");
		keywords.add("or");
		keywords.add("outer");
		keywords.add("output");
		keywords.add("package");
		keywords.add("parameter");
		keywords.add("partial");
		keywords.add("protected");
		keywords.add("public");
		keywords.add("record");
		keywords.add("redeclare");
		keywords.add("replaceable");
		keywords.add("return");
		keywords.add("stream");
		keywords.add("then");
		keywords.add("true");
		keywords.add("type");
		keywords.add("when");
		keywords.add("while");
		keywords.add("within");

		return keywords;
	}

	/**
	 * Gets the duplicate.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return the duplicate
	 */
	public static <T> List getDuplicate(Collection<T> list) {

		final List<T> duplicatedObjects = new ArrayList<T>();
		Set<T> set = new HashSet<T>() {
			@Override
			public boolean add(T e) {
				if (contains(e)) {
					duplicatedObjects.add(e);
				}
				return super.add(e);
			}
		};
		for (T t : list) {
			set.add(t);
		}
		return duplicatedObjects;
	}

	/**
	 * Checks for duplicate.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return true, if successful
	 */
	public static <T> boolean hasDuplicate(Collection<T> list) {
		if (getDuplicate(list).isEmpty())
			return false;
		return true;
	}

}
