package org.openmodelica.modelicaml.helper.impl;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;

public class Utls {

//	public static HashMap<Element,TreeParent> getModelInstantiations(HashSet<Element> models, HashMap<Element,TreeParent> preparedInstantiations){
//		
//		HashMap<Element,TreeParent> modelToInstantiations = new HashMap<Element, TreeParent>();
//		
//		for (Element model : models) {
//			TreeParent newChild = getModelInstantiation(model, preparedInstantiations);
//			if (newChild != null) {
//				modelToInstantiations.put(model, newChild);
//			}
//		}
//		
//		return modelToInstantiations;
//	}
//	
//	public static TreeParent getModelInstantiation(Element model, HashMap<Element,TreeParent> preparedInstantiations){
//		TreeParent newChild = null;
//
//		// create only if it does not exists already
//		if (preparedInstantiations.get(model) == null) {
//			
//			ClassInstantiation ci_model = new ClassInstantiation((Class) model, true);
//			ci_model.createTree();
//			newChild = ci_model.getTreeRoot();
//		}
//		
//		return newChild;
//	}
}
