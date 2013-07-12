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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.datacollection.ElementsCollector;

public class ElementFinder extends ElementsCollector {

	String qName = "";
	public ElementFinder(EObject umlRootElement, String qName){
		
		this.qName = qName;
		
		if (umlRootElement != null) {
			// collect elements
//			collectElementsFromModel(umlRootElement, null);
			collectElementsFromModel(umlRootElement);
		}
	}
	
	@Override
	protected void collect(Element element, boolean isImported) {
		
		// collect elements
		if (element instanceof NamedElement 
				&& ((NamedElement)element).getQualifiedName() != null // important when the name is not set
				&& ((NamedElement)element).getQualifiedName().equals(this.qName)
				&& !elements.contains(element)) {
			
			elements.add(element) ;
			
			if (isImported) {
				importedPackages.add(element);
			}
		}
	}
}
