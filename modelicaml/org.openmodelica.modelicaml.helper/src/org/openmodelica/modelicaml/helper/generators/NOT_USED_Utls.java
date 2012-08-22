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
package org.openmodelica.modelicaml.helper.generators;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;

public class NOT_USED_Utls {

//	public static HashSet<Element> getElementsFromAllInstantiations(HashMap<Element, ClassInstantiation> modelToItsInstantiation){
//		HashSet<Element> allElements = new HashSet<Element>();
//		HashSet<ClassInstantiation> classInstantiations = new HashSet<ClassInstantiation>();
//		for (Element model: modelToItsInstantiation.keySet()) {
//			classInstantiations.add(modelToItsInstantiation.get(model));
//		}
//		
//		for (ClassInstantiation classInstantiation : classInstantiations) {
//			allElements.addAll(classInstantiation.getElementToInstantiationTreeObjects().keySet());
//		}
//		return allElements;
//	}
//	
//	public static HashMap<Element,HashSet<TreeObject>> getElementToInstantiationTreeObjects(HashMap<Element, ClassInstantiation> modelToItsInstantiation){
//		 HashMap<Element,HashSet<TreeObject>> allElements = new  HashMap<Element,HashSet<TreeObject>>();
//		HashSet<ClassInstantiation> classInstantiations = new HashSet<ClassInstantiation>();
//		for (Element model: modelToItsInstantiation.keySet()) {
//			classInstantiations.add(modelToItsInstantiation.get(model));
//		}
//		
//		for (ClassInstantiation classInstantiation : classInstantiations) {
//			allElements.putAll(classInstantiation.getElementToInstantiationTreeObjects());
//		}
//		return allElements;
//	}
//	
//	public static HashSet<TreeObject> getAllTreeObjectsFromInstantiation(ClassInstantiation classInstantiation){
//		HashSet<TreeObject> allTreeObjects = new HashSet<TreeObject>();
//		
//		for (Element element : classInstantiation.getElementToInstantiationTreeObjects().keySet()) {
//			HashSet<TreeObject> treeObjects = classInstantiation.getElementToInstantiationTreeObjects().get(element);
//			if (treeObjects != null && treeObjects.size() > 0 ) {
//				allTreeObjects.addAll(treeObjects);
//			}
//		}
//		return allTreeObjects;
//	}
}
