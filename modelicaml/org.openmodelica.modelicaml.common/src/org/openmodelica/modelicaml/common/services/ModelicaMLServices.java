package org.openmodelica.modelicaml.common.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;

public class ModelicaMLServices {

	public static String getRequirementID(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_id);
				return o.toString();
			}
		}
		return "";
	}
	
	public static String getRequirementText(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_text);
				return o.toString();
			}
		}
		return "";
	}

	
	public static List<TreeObject> getSortedByDotPath(HashSet<TreeObject> set){
		if (set == null) { return null; }

		List<TreeObject> listSorted = new ArrayList<TreeObject>(set);
		Comparator<TreeObject> c = new Comparator<TreeObject>() {
			@Override
			public int compare(TreeObject arg0, TreeObject arg1) {
				return arg0.getDotPath().compareToIgnoreCase(arg1.getDotPath());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	public static List<Element> getSortedByName(HashSet<Element> set){
		if (set == null) { return null; }

		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				if (arg0 instanceof NamedElement) {
					return ((NamedElement)arg0).getName().compareToIgnoreCase( ((NamedElement)arg1).getName());
				}
				return arg0.toString().compareToIgnoreCase(arg1.toString());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	public static  List<Element> getSortedByQName(HashSet<Element> set){
		if (set == null) { return null; }

		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				if (arg0 instanceof NamedElement) {
					return ((NamedElement)arg0).getQualifiedName().compareToIgnoreCase( ((NamedElement)arg1).getQualifiedName());
				}
				return arg0.toString().compareToIgnoreCase(arg1.toString());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	public static List<Element> getSortedByRequirementId(HashSet<Element> set){
		if (set == null) { return null; }
		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				return ModelicaMLServices.getRequirementID(arg0).trim().compareToIgnoreCase( ModelicaMLServices.getRequirementID(arg1).trim());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	
	public static String getNamePostFix(NamedElement owner, String name){
		String postfix = "_";
		int highestPostFixNumber = 0;
	
		EList<Element> ownedElements = owner.getOwnedElements();
		for (Element element : ownedElements) {
			if (element instanceof NamedElement && ((NamedElement)element).getName().startsWith(name)) {
				
				String namePart = ((NamedElement)element).getName();
				String postFix = namePart.replaceFirst(name, "").replaceAll("_", "").trim();
				
				if (!postFix.equals("")) {
					try {
						int postFixNumber = Integer.valueOf(postFix);
						if (postFixNumber > highestPostFixNumber) {
							highestPostFixNumber = postFixNumber;
						}
					} catch (Exception e) {
						// ignore, do nothing.
					}
				}
			}
		}
		return postfix + (highestPostFixNumber + 1);	
	}
	
}
