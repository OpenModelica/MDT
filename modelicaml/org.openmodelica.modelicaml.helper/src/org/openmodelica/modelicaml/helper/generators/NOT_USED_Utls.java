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
