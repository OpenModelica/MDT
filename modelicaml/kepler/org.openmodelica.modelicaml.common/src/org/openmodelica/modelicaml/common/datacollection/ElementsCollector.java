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
package org.openmodelica.modelicaml.common.datacollection;

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.constants.Constants;

public class ElementsCollector {
	
	protected String stereotypeQName;
	
	// all imported elements
	protected HashSet<Element> importedElements = new HashSet<Element>(); 
	
	// only the imported packages
	protected HashSet<Element> importedPackages = new HashSet<Element>();
	
	// all collected elements
	protected HashSet<Element> elements = new HashSet<Element>();

	// indicates of elements from imported libraries should also be collected or not
	private boolean collectElementsFromLibraries = true;
	
	protected IProgressMonitor monitor;
	
	// TODO: like for libraries, same could be done for imported packages.
	
	public void collectElementsFromModel(EObject umlRootElement){
		
		/*
		 * Clear lists in order to enable the call of the this method 
		 * multiple times using the same object.
		 */
		importedPackages.clear();
		importedElements.clear();
		elements.clear();
		
		// DFS traversal
		collectElements((Element) umlRootElement, false);
	}
	
	public void collectElementsFromModel(EObject umlRootElement, IProgressMonitor monitor){
		this.monitor = monitor;
		collectElementsFromModel(umlRootElement);
	}
	
	
	protected void collectElements(Element rootElement, boolean isImported){
		
		if (monitor != null && monitor.isCanceled()) { // stop if the operation was canceled by the user.
			return;
		}
		
		// check if the element is from a library
		if (rootElement.getAppliedStereotype(Constants.stereotypeQName_InstalledLibrary) != null) { // if we are about to iterate within the lib.
			if (!isCollectElementsFromLibraries()) {
				return; // discard this element and all its children
			}
		}
		
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
				
				if (monitor != null && monitor.isCanceled()) { // stop if the operation was canceled by the user.
					return;
				}
				
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


	public boolean isCollectElementsFromLibraries() {
		return collectElementsFromLibraries;
	}


	public void setCollectElementsFromLibraries(boolean collectElementsFromLibraries) {
		this.collectElementsFromLibraries = collectElementsFromLibraries;
	}

}
