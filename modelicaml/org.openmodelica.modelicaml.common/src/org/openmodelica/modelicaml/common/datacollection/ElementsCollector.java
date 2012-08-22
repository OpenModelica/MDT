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
package org.openmodelica.modelicaml.common.datacollection;

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;

public class ElementsCollector {
	
	protected String stereotypeQName;
	
	// all imported elements
	private HashSet<Element> importedElements = new HashSet<Element>(); 
	
	// only the imported packages
	protected HashSet<Element> importedPackages = new HashSet<Element>();
	
	// all collected elements
	protected HashSet<Element> elements = new HashSet<Element>();

	
	
	public void collectElementsFromModel(EObject umlRootElement){
		
		/*
		 * Clear lists in order to enable the call of the this method 
		 * multiple times using the same object.
		 */
		importedPackages.clear();
		importedElements.clear();
		elements.clear();
		
//		System.err.println("Start collecting of elements ...");
		
		// DFS traversal
		collectElements((Element) umlRootElement, false);
		
//		System.err.println("Completed collecting of elements");
	}
	
	
	protected void collectElements(Element rootElement, boolean isImported){
		
		// avoid cycles -> check if this element was already collected
		if (!elements.contains(rootElement) && !importedPackages.contains(rootElement)) {
			
			// collect the passed element
			collect(rootElement, isImported);
			
			// get imported packages
			if (rootElement instanceof Namespace) {
				for (Package importedPackage : ((Namespace)rootElement).getImportedPackages()) {
					
					// recursively collect from imported packages
					collectElements(importedPackage, true);
					
					/*
					 * Remember the imported package in order to avoid multiple 
					 * in case the same package is imported in multiple models.
					 */
					importedPackages.add(importedPackage);
				}
			}
			
			/*
			 * Iterate over all contents of the passed element and
			 * collect recursively.
			 */
			Iterator<EObject> i = rootElement.eAllContents(); // only elements that are in the name-space of this element
			
			while (i.hasNext()) {
				EObject object = i.next();
				if (object instanceof Element) {
					
					// collect the actual element
					collect((Element) object, isImported);
					
					// recursive call
					collectElements((Element) object, isImported);
				}
			}
		}
	}


	
	/*
	 *  This method can be overridden for specific collections
	 */
	protected void collect(Element element, boolean isImported){
		
		// check if the element to collect fulfills the selection criteria
		if (element instanceof Element && ((Element)element).getAppliedStereotype(this.stereotypeQName) != null) {
			elements.add(element) ;
		}
		
		// add to indicate that this element is imported.
		if (isImported) {
			importedElements.add(element);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
//	public void collectElementsFromModel(EObject umlRootElement, String stereotypeQName){
//		
//		//set the stereotype
//		this.stereotypeQName = stereotypeQName;
//		
//		// clear lists in order to enable the call of the this method multiple times using the same object.
//		importedMembers.clear();
//		importedElements.clear();
//		elements.clear();
//		
//		// check the element itself
//		if (umlRootElement instanceof Element) {
//			collectElement((Element)umlRootElement, false);
//		}
//		
//		// collect elements that are imported by the selected root element
//		if (umlRootElement instanceof Namespace) {
//			
//			Namespace element = (Namespace)umlRootElement;
//			
//			// collect imported packages
//			for (Package importedPackage : element.getImportedPackages()) {
//				collectElement(importedPackage, true);
//				
//				// go to the contents of the imported package
//				collectFromImportedMember(importedPackage);
//			}
//			
//		}
//		else {
////				System.err.println("Cannot access the root ModelicaML model for searching for value binding containers.");
//		}
//		
//		// get all direct contents of the selected root element 
//		Iterator<EObject> i = umlRootElement.eAllContents(); // only elements that are in the name space of this element 
////		Iterator<EObject> i = umlRootElement.eResource().getAllContents(); // DO NOT use this because this returns all elements in a .uml file
//
//		while (i.hasNext()) {
//			EObject object = i.next() ;
//			
//			// collect all imported elements 
//			if (object instanceof Namespace) {
//				
//				Namespace element = (Namespace)object;
//				
//				// collect imported packages
//				for (Package importedPackage : element.getImportedPackages()) {
//					collectElement(importedPackage, true);
//					
//					// go to the contents of the imported package
//					collectFromImportedMember(importedPackage);
//				}
//				
//				EList<PackageableElement> importedElements = getImportedMembers(element);
//				if (importedElements != null && importedElements.size() > 0) {
//					for (PackageableElement packageableElement2 : importedElements) {
//						
//						// check if the imported member is an element
//						if (packageableElement2 instanceof Element) {
//							collectElement(packageableElement2, true);
//						}
//						
//						// go to the contents of the imported member
//						collectFromImportedMember(packageableElement2);
//					}
//				}
//			}
//			
//			// collect elements.
//			if (object instanceof Element) {
//				collectElement((Element)object, false);
//			}
//		}
//	}
//	
//	
//	private void collectFromImportedMember(EObject importedMember) {
//		
//		// get imported members from itself
// 		if (importedMember instanceof Namespace) {
// 			
//			Namespace element = (Namespace) importedMember;
//			
//			// collect imported packages
//			for (Package importedPackage : element.getImportedPackages()) {
//				collectElement(importedPackage, true);
//			}
//			
//			EList<PackageableElement> importedElements = getImportedMembers(element);
//			
//			if (importedElements != null && importedElements.size() > 0) {
//				
//				// add to imported members in order enable recognizing cyclic runs.  
//				importedMembers.addAll(importedElements);
//				for (PackageableElement packageableElement : importedElements) {
//					
//					// collect elements
//					if (packageableElement instanceof Element) {
//						collectElement((Element)packageableElement, true);
//					}
//					// not yet considered -> recursively call
//					if (!importedMembers.contains(packageableElement)) {
//						if (packageableElement instanceof Namespace) {
//							collectFromImportedMember(packageableElement);
//						}
//					}
//				}
//			}
//		}
//
//		// iterate over all contained elements
//		Iterator<EObject> i = importedMember.eAllContents();
//		while (i.hasNext()) {
//			EObject object = i.next() ;
//			
//			// collect elements
//			if (object instanceof Element) {
//				collectElement((Element)object, true);
//			}
//			
//			// not yet considered -> recursively call
//			if (!importedMembers.contains(object)) {
//				if (object instanceof Namespace) {
//					collectFromImportedMember(object);
//				}
//			}
//		}
//	}
//	
//	
//	private EList<PackageableElement> getImportedMembers(Namespace element){
//		if (element != null) {
//			return element.getImportedMembers();
//		}
//		return null;
//	}

	

	
	// ########## GETTER
	public HashSet<Element> getElements() {
		return elements;
	}

	public HashSet<Element> getOnlyImportedPackages() {
		return importedPackages;
	}
	
	public String getStereotypeQName() {
		return stereotypeQName;
	}


	public void setStereotypeQName(String stereotypeQName) {
		this.stereotypeQName = stereotypeQName;
	}


	public HashSet<Element> getImportedElements() {
		return importedElements;
	}


	public void setImportedElements(HashSet<Element> importedElements) {
		this.importedElements = importedElements;
	}

}
