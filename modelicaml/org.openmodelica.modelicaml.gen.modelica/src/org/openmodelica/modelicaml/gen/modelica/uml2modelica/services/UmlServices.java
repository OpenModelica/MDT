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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.gen.modelica.uml2modelica.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Stereotype;

import fr.obeo.acceleo.gen.template.eval.ENodeCastException;
import fr.obeo.acceleo.gen.template.eval.ENodeIterator;
import fr.obeo.acceleo.gen.template.eval.ENodeList;

// TODO: Auto-generated Javadoc
/**
 * The Class UmlServices.
 */
public class UmlServices {

	/**
	 * Filter the element list. if an Element have a stereotype. Use keyword and
	 * profile to find stereotype.
	 * 
	 * @param eltList
	 *            list of element.
	 * @param aStereotype
	 *            Stereotype to search.
	 * 
	 * @return the list of element has the stereotype aStereotype.
	 * 
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public ArrayList<Element> filterStereotype(ENodeList eltList,
			String aStereotype) throws ENodeCastException {
		if (eltList == null) {
			return null;
		}
		ArrayList<Element> resultList = new ArrayList<Element>();
		for (ENodeIterator iter = eltList.iterator(); iter.hasNext();) {
			Element elt = (Element) iter.next().getEObject();
			if (hasStereotype(elt, aStereotype)) {
				resultList.add(elt);
			}
		}
		return resultList;
	}

	/**
	 * Get the body of OpaqueBehavior ob with index corresponding to the
	 * language index. We replace all \n text by newline.
	 * 
	 * @param ob
	 *            an Opaque behavior.
	 * @param language
	 *            the language to find.
	 * 
	 * @return the content body.
	 * 
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public String getBody(OpaqueBehavior ob, String language)
			throws ENodeCastException {
		List<String> languages = ob.getLanguages();
		int i = 0;
		for (Iterator<String> iter2 = languages.iterator(); iter2.hasNext();) {
			String lang = iter2.next().toString();
			if (lang.equals(language)) {
				if (ob.getBodies().size() != 0) {
					Object body = ob.getBodies().get(i);
					if (body != null) {
						return body.toString();
					} else {
						return null;
					}
				}
			}
			i++;
		}
		return null;
	}

	/**
	 * Return the stereotype if is applied to element.
	 * 
	 * @param elt
	 *            Element used.
	 * @param stereotype
	 *            Stereotype to return.
	 * 
	 * @return the stereotype.
	 * 
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public Stereotype getStereotype(Element elt, String stereotype)
			throws ENodeCastException {
		Stereotype result = null;
		// search with real stereotype
		for (Iterator<Stereotype> iter = elt.getAppliedStereotypes().iterator(); iter
				.hasNext();) {
			Stereotype element = iter.next();
			if (element.getName().equals(stereotype)) {
				result = element;
			}
		}
		return result;
	}

	/**
	 * Return the stereotype value if is applied to element.
	 * 
	 * @param elt
	 *            Element used.
	 * @param stereotype
	 *            Stereotype to return.
	 * @param propertyName
	 *            the property name
	 * 
	 * @return Object contain the value.
	 * 
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public Object getStereotypeValue(Element elt, String stereotype,
			String propertyName) throws ENodeCastException {
		// search with real stereotype
		Stereotype stereotypeFound = getStereotype(elt, stereotype);
		if (stereotypeFound == null) {
			return null;
		} else {
			return elt.getValue(stereotypeFound, propertyName);
		}
	}
	
	/**
	 * Verify if an Element have a stereotype. Use keyword and profile to find
	 * stereotype. Multiple stereotype are allow.
	 * 
	 * @param elt
	 *            Element used.
	 * @param stereotype
	 *            Stereotype to search.
	 * 
	 * @return true if found. false else.
	 * 
	 * @throws ENodeCastException
	 *             the e node cast exception
	 */
	public boolean hasStereotype(Element elt, String stereotype)
			throws ENodeCastException {
		return (getStereotype(elt, stereotype) != null);
	}

	/**
	 * Test if aClass is a behavior element.
	 * 
	 * @param aClass
	 *            Class to.
	 * @return Boolean return true if aClass is a behavior.
	 */
	public Boolean isBehavior(Class aClass) {
		if (aClass instanceof Behavior) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
