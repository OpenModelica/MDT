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
package org.openmodelica.modelicaml.common.valuebindings.helpers;

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;

public class MediatorsCollector {
	
	// all imported members
	private HashSet<Element> importedMembers = new HashSet<Element>(); 
	
	// only the imported mediator containers
	private HashSet<Element> importedMediatorContainers = new HashSet<Element>();
	// only the imported mediators
	private HashSet<Element> importedMediators = new HashSet<Element>(); 
	
	// collected mediator containers
	private HashSet<Element> valueMediatorContainers = new HashSet<Element>();
	// collected mediators
	private HashSet<Element> valueMediators = new HashSet<Element>();

	
	public void collectElementsFromModel(EObject umlRootElement){

		// clear lists in order to enable the call of the this method multiple times using the same object.
		importedMembers.clear();
		importedMediatorContainers.clear();
		importedMediators.clear();
		valueMediatorContainers.clear();
		valueMediators.clear();
		
		if (umlRootElement != null) {
			
			// collect mediators that are imported by the selected root element
			if (umlRootElement instanceof Namespace) {
				
				Namespace element = (Namespace)umlRootElement;
				EList<PackageableElement> importedElements = getImportedMembers(element);
				if (importedElements != null && importedElements.size() > 0) {
					for (PackageableElement packageableElement : importedElements) {
						
						// check if the imported memeber is a mediator containers or mediator
						if (packageableElement instanceof Element) {
							collectMediators(packageableElement, true);
						}
						
						// go to the contents of the imported memeber
						collectFromImportedMember(packageableElement);
					}
				}
			}
			
			// get all direct contents of the selected root element 
			Iterator<EObject> i = umlRootElement.eAllContents();

			while (i.hasNext()) {
				EObject object = i.next() ;
				
				// collect all imported elements 
				if (object instanceof Namespace) {
					
					EList<PackageableElement> importedElements = getImportedMembers((Namespace)object);
					if (importedElements != null && importedElements.size() > 0) {
						for (PackageableElement packageableElement2 : importedElements) {
							
							// check if the imported memeber is a mediator containers or mediator
							if (packageableElement2 instanceof Element) {
								collectMediators(packageableElement2, true);
							}
							
							// go to the contents of the imported memeber
							collectFromImportedMember(packageableElement2);
						}
					}
				}
				
				// collect mediator containers and mediators.
				if (object instanceof Element) {
					collectMediators((Element)object, false);
				}
			}
		}
		else {
//			System.err.println("Cannot access the root ModelicaML model for searching for value binding containers.");
		}
	}
	
	
	private void collectFromImportedMember(EObject importedMember) {
		
		// get imported members from itself
 		if (importedMember instanceof Namespace) {
			Namespace element = (Namespace) importedMember;
			EList<PackageableElement> importedElements = getImportedMembers(element);
			
			if (importedElements != null && importedElements.size() > 0) {
				
				// add to imported members in order enable recognizing cyclic runs.  
				importedMembers.addAll(importedElements);
				for (PackageableElement packageableElement : importedElements) {
					// collect mediator containers and mediators.
					if (packageableElement instanceof Element) {
						collectMediators((Element)packageableElement, true);
					}
					// not yet considered -> recursively call
					if (!importedMembers.contains(packageableElement)) {
						if (packageableElement instanceof Namespace) {
							collectFromImportedMember(packageableElement);
						}
					}
				}
			}
		}

		// iterate over all contained elements, collect mediators, and recursively collect mediators from all imported members
		Iterator<EObject> i = importedMember.eAllContents();
		while (i.hasNext()) {
			EObject object = i.next() ;
			
			// collect mediator containers and mediators.
			if (object instanceof Element) {
				collectMediators((Element)object, true);
			}
			// not yet considered -> recursively call
			if (!importedMembers.contains(object)) {
				if (object instanceof Namespace) {
					collectFromImportedMember(object);
				}
			}
		}
	}
	
	private void collectMediators(Element element, boolean isImported){
		// collect mediator containers and mediators
		// avoid duplicates that can occure due to the multiple imports of the same mediators
		
		if (element instanceof Class 
				&& ((Element)element).getAppliedStereotype(Constants.stereotypeQName_ValueMediatorsContainer) != null
				&& !valueMediatorContainers.contains(element)) {
			
			valueMediatorContainers.add(element) ;
			
			if (isImported) {
				importedMediatorContainers.add(element);
			}
			
		}
		else if (element instanceof Property 
				&& ((Element)element).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null
				&& !valueMediators.contains(element)) {
			
			valueMediators.add((Property) element);

			if (isImported) {
				importedMediators.add(element);
			}

		}
	}
	
	private EList<PackageableElement> getImportedMembers(Namespace element){
		if (element != null) {
			return element.getImportedMembers();
		}
		return null;
	}
	
	
	
	
	
	// ########## GETTER
	public HashSet<Element> getValueMediatorContainers() {
		return valueMediatorContainers;
	}

	public HashSet<Element> getValueMediators() {
		return valueMediators;
	}

	public HashSet<Element> getImportedMediatorContainers() {
		return importedMediatorContainers;
	}

	public HashSet<Element> getImportedMediators() {
		return importedMediators;
	}

}
