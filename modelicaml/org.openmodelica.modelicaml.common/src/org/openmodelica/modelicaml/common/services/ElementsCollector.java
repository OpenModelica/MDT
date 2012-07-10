/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.common.services;

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

public class ElementsCollector {
	
	protected String stereotypeQName;
	
	// all imported members
	private HashSet<Element> importedMembers = new HashSet<Element>(); 
	
	// only the imported elements
	protected HashSet<Element> importedElements = new HashSet<Element>();
	
	// all collected elements
	protected EList<Element> elements = new BasicEList<Element>();

	
	public void collectElementsFromModel(EObject umlRootElement, String stereotypeQName){
		
		//set the stereotype
		this.stereotypeQName = stereotypeQName;
		
		// clear lists in order to enable the call of the this method multiple times using the same object.
		importedMembers.clear();
		importedElements.clear();
		elements.clear();
		
		// collect elements that are imported by the selected root element
		if (umlRootElement instanceof Namespace) {
			
			Namespace element = (Namespace)umlRootElement;
			
			// collect imported packages
			for (Package importedPackage : element.getImportedPackages()) {
				collectElement(importedPackage, true);
				
				// go to the contents of the imported package
				collectFromImportedMember(importedPackage);
			}
			
//			EList<PackageableElement> importedElements = getImportedMembers(element);
//			if (importedElements != null && importedElements.size() > 0) {
//				for (PackageableElement packageableElement : importedElements) {
//					
//					// check if the imported member is an element
//					if (packageableElement instanceof Element) {
//						collectElements(packageableElement, true);
//					}
//					
//					// go to the contents of the imported member
//					collectFromImportedMember(packageableElement);
//				}
//			}
			
		}
		else {
//				System.err.println("Cannot access the root ModelicaML model for searching for value binding containers.");
		}
		
		// get all direct contents of the selected root element 
		Iterator<EObject> i = umlRootElement.eAllContents(); // only elements that are in the name space of this element 
//		Iterator<EObject> i = umlRootElement.eResource().getAllContents(); // do not use this because this return all element in a .uml file

		while (i.hasNext()) {
			EObject object = i.next() ;
			
			// collect all imported elements 
			if (object instanceof Namespace) {
				
				Namespace element = (Namespace)object;
				
				// collect imported packages
				for (Package importedPackage : element.getImportedPackages()) {
					collectElement(importedPackage, true);
					
					// go to the contents of the imported package
					collectFromImportedMember(importedPackage);
				}
				
				EList<PackageableElement> importedElements = getImportedMembers(element);
				if (importedElements != null && importedElements.size() > 0) {
					for (PackageableElement packageableElement2 : importedElements) {
						
						// check if the imported member is an element
						if (packageableElement2 instanceof Element) {
							collectElement(packageableElement2, true);
						}
						
						// go to the contents of the imported member
						collectFromImportedMember(packageableElement2);
					}
				}
			}
			
			// collect elements.
			if (object instanceof Element) {
				collectElement((Element)object, false);
			}
		}
	}
	
	
	private void collectFromImportedMember(EObject importedMember) {
		
		// get imported members from itself
 		if (importedMember instanceof Namespace) {
 			
			Namespace element = (Namespace) importedMember;
			
			// collect imported packages
			for (Package importedPackage : element.getImportedPackages()) {
				collectElement(importedPackage, true);
			}
			
			EList<PackageableElement> importedElements = getImportedMembers(element);
			
			if (importedElements != null && importedElements.size() > 0) {
				
				// add to imported members in order enable recognizing cyclic runs.  
				importedMembers.addAll(importedElements);
				for (PackageableElement packageableElement : importedElements) {
					
					// collect elements
					if (packageableElement instanceof Element) {
						collectElement((Element)packageableElement, true);
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

		// iterate over all contained elements
		Iterator<EObject> i = importedMember.eAllContents();
		while (i.hasNext()) {
			EObject object = i.next() ;
			
			// collect elements
			if (object instanceof Element) {
				collectElement((Element)object, true);
			}
			
			// not yet considered -> recursively call
			if (!importedMembers.contains(object)) {
				if (object instanceof Namespace) {
					collectFromImportedMember(object);
				}
			}
		}
	}
	
	
	/*
	 *  This method can be overridden for specific collections
	 */
	protected void collectElement(Element element, boolean isImported){
		// collect elements
		// avoid duplicates that can occur due to the multiple imports of the same elements
		
		if (element instanceof Element 
				&& ((Element)element).getAppliedStereotype(this.stereotypeQName) != null
				&& !elements.contains(element)) {
			
			elements.add(element) ;
			
			if (isImported) {
				importedElements.add(element);
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
	public EList<Element> getElements() {
		return elements;
	}

	public HashSet<Element> getOnlyImportedElements() {
		return importedElements;
	}

}
