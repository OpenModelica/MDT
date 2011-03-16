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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.modelicaml.common.ast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.services.StringUtls;
//import org.openmodelica.modelicaml.view.componentstree.utls.UmlServices;
import org.openmodelica.modelicaml.common.services.UmlServices;


// TODO: Auto-generated Javadoc
/**
 * The Class ModificationsCollector.
 */
public class ModificationsCollector {
	
	
	/**
	 * Gets the merged component modifications.
	 * 
	 * @param selectedClass
	 *            the selected class
	 * @param component
	 *            the component
	 * @param dotPath
	 *            the dot path
	 * @param modificationsFromhigherComponentLevel
	 *            the modifications fromhigher component level
	 * @return the merged component modifications
	 */
	public static HashSet<String> getMergedComponentModifications(Class selectedClass, Property component, String dotPath, HashSet<String> modificationsFromhigherComponentLevel){
		HashSet<String> modifications = new HashSet<String>();
		
		if (component != null) {
			Element owner = component.getOwner();
			if (owner instanceof Class) {

				// get all inherited class modification of the class selected in View
				HashSet<String> classInheritedModList = new HashSet<String>();
				classInheritedModList.addAll(getAllInheritedClassModifications(selectedClass, modificationsFromhigherComponentLevel));	
				
				// merge inherited class modification with passed modifications (which have higher priority) 
				HashSet<String> higherLevelAndInheritedModList = merge2Lists(modificationsFromhigherComponentLevel, classInheritedModList);
				
				// get all components modification
				HashSet<String> componentModificationList = getComponentModifications(component);
				// normalize components modification (add the dotPath to each modification)
				HashSet<String> componentModificationList_normalized = new HashSet<String>();
				for (String string : componentModificationList) {
					if (!string.trim().equals("")) {
						componentModificationList_normalized.add(dotPath + "." + string);
						//System.out.println(component.getName() + " : " + string);
					}
				}
				
				// get and normalize all modifications that are inherited by the component class
				HashSet<String> componentOwnerInheritedModList = new HashSet<String>();
				HashSet<String> componentOwnerInheritedModList_normalized = new HashSet<String>();
				Type componentType = component.getType();
				if (componentType != null) {
					if (componentType instanceof Class) {
						componentOwnerInheritedModList.addAll(getAllInheritedClassModifications((Class)componentType, null));
						for (String string : componentOwnerInheritedModList) {
							if (!string.trim().equals("")) {
								componentOwnerInheritedModList_normalized.add(dotPath + "." + string);
							}
						}
					}
				}
				
				// merge component modifications and modifications inherited by the component class
				HashSet<String> componentModCompTypeInheritedModList = merge2Lists(componentModificationList_normalized, componentOwnerInheritedModList_normalized);
				// merge passed modifications (high priority, will not be overwritten) and component modifications
				
				//modifications.addAll(merge2Lists(higherLevelAndInheritedModList, componentModCompTypeInheritedModList));
				modifications.addAll(merge2Lists(higherLevelAndInheritedModList, componentModCompTypeInheritedModList));
			}
		}
		
		HashSet<String> finalList = new HashSet<String>();
		
		for (String string : modifications) {
			if (string.startsWith(dotPath)) {
				// clean up: filter the list to get only modifications for the component at hand and its sub-tree 
				finalList.add(string);
				//System.out.println(component.getName() + " : " + string);
			}
		}
		return finalList;
	}

	/**
	 * Gets the all inherited class modifications.
	 * 
	 * @param aClass
	 *            the a class
	 * @param passedModifications
	 *            the passed modifications
	 * @return the all inherited class modifications
	 */
	public static HashSet<String> getAllInheritedClassModifications(Class aClass, HashSet<String> passedModifications){
		HashSet<String> extendsRelationModifications = new HashSet<String>();
		HashSet<String> mergedModifications = new HashSet<String>();
		HashSet<String> finalSet = new HashSet<String>();
		
		EList<Generalization> extendsRelations = aClass.getGeneralizations();
		
		for (Generalization generalization : extendsRelations) {
			Stereotype stereotype = getGeneralizationStereotype(generalization);
			if ( stereotype != null ) {
				extendsRelationModifications.addAll(getGeneralizationModifications(generalization, stereotype));
				if (passedModifications != null) {
					mergedModifications = merge2Lists(passedModifications, extendsRelationModifications);
				}
				else {
					mergedModifications.addAll(extendsRelationModifications);
				}
				
				EList<Element> targets = generalization.getTargets();
				for (Element element : targets) {
					if (element instanceof Class) {
						mergedModifications.addAll(getAllInheritedClassModifications((Class)element, mergedModifications)); 
					}
				}
				
			}
		}
		
		finalSet.addAll(mergedModifications); // no duplicates
		return finalSet;
	}
	
	
	//TODO: do the same as for the Class (above) for the Primitive type with TypeRelation
	/**
	 * Gets the primitive type modifications.
	 * 
	 * @param primitiveType
	 *            the primitive type
	 * @return the primitive type modifications
	 */
	public EList<String> getPrimitiveTypeModifications(PrimitiveType primitiveType){
		EList<String> modifications = new BasicEList<String>();
		return modifications;
	}

	
	/**
	 * Merge2 lists.
	 * 
	 * @param listHighPrioriy
	 *            the list high prioriy
	 * @param listLowPriority
	 *            the list low priority
	 * @return the hash set
	 */
	private static HashSet<String> merge2Lists(HashSet<String> listHighPrioriy,
			HashSet<String> listLowPriority) {

		HashSet<String> mergedList = new HashSet<String>();

		HashSet<String> list1 = new HashSet<String>(); // has higher priority
		list1.addAll(listHighPrioriy);

		HashSet<String> list2 = new HashSet<String>();
		list2.addAll(listLowPriority);

		HashSet<String> list2_copy = new HashSet<String>(); // copy of the list
															// that has lower
															// priority
		list2_copy.addAll(list2);

		for (String string : list2) {
			String[] splittedStringList2 = string.split("=");
			String firtsPartList2 = splittedStringList2[0].trim();
			for (String string_from_list_1 : list1) {

				String[] splittedStringList1Copy = string_from_list_1
						.split("=");
				String firtsPartList1Copy = splittedStringList1Copy[0].trim();
				if (firtsPartList1Copy.equals(firtsPartList2)) {
					list2_copy.remove(string);
				}
			}
		}
		list1.addAll(list2_copy); // list1 now contains all items that are
									// missing from the list2

		mergedList.addAll(list1);

		return mergedList;

	}

	// TODO: create final strings for the stereotypes names and properties names

	/**
	 * Gets the component stereotype.
	 * 
	 * @param property
	 *            the property
	 * @return the component stereotype
	 */
	private static Stereotype getComponentStereotype(Property property) {
		Stereotype stereotype = null;
		if (UmlServices.hasStereotype((Element) property, "Component"))
			return stereotype = property
					.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Component");
		if (UmlServices.hasStereotype((Element) property, "Variable"))
			return stereotype = property
					.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
		if (UmlServices.hasStereotype((Element) property, "CalculatedProperty"))
			return stereotype = property
					.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::CalculatedProperty");
		// note: requirementInstance in is another profile than the rest!
		if (UmlServices
				.hasStereotype((Element) property, "RequirementInstance"))
			return stereotype = property
					.getAppliedStereotype("ModelicaML::ModelicaRequirementConstructs::RequirementInstance");
		return stereotype;
	}
	
	/**
	 * Gets the generalization stereotype.
	 * 
	 * @param extendsRelation
	 *            the extends relation
	 * @return the generalization stereotype
	 */
	private static Stereotype getGeneralizationStereotype(Generalization extendsRelation) {
		Stereotype stereotype = null;
		if (UmlServices.hasStereotype((Element) extendsRelation, "ExtendsRelation"))
			return stereotype = extendsRelation.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::ExtendsRelation");
		
		if (UmlServices.hasStereotype((Element) extendsRelation, "TypeRelation"))
			return stereotype = extendsRelation.getAppliedStereotype("ModelicaML::ModelicaRelationsConstructs::TypeRelation");
		
		return stereotype;
	}

	/**
	 * Gets the component modifications.
	 * 
	 * @param component
	 *            the component
	 * @return the component modifications
	 */
	public static HashSet<String> getComponentModifications(Property component) {
		HashSet<String> mList = new HashSet<String>();
		String stereotypeName = getFirstModelicaMLComponentStereotypeName(component);
		List<String> modificationList = new ArrayList<String>();
		if (stereotypeName != null) {
			Object o = UmlServices
					.getStereotypeValue((Element) component,
							getFirstModelicaMLComponentStereotypeName(component),
							"modification");
			if (o instanceof List<?>) {
				modificationList = (List<String>) o;
			}
			for (String string : modificationList) {
				mList.add(StringUtls.removeOutterBraces(string));
			}
			//mList.addAll(modificationList);
		}
		return mList;
	}
	
	/**
	 * Gets the generalization modifications.
	 * 
	 * @param extendsRelation
	 *            the extends relation
	 * @param stereotype
	 *            the stereotype
	 * @return the generalization modifications
	 */
	private static EList<String> getGeneralizationModifications(Generalization extendsRelation, Stereotype stereotype) {
		EList<String> mList = new BasicEList<String>();
		
		List<String> modificationList = new ArrayList<String>();
		if (stereotype != null) {
			Object o = UmlServices.getStereotypeValue((Element) extendsRelation, stereotype.getName(),"modification");
			if (o instanceof List<?>) {
				modificationList = (List<String>) o;
			}
			mList.addAll(modificationList);
		}
		return mList;
	}

	/**
	 * Gets the first modelica ml component stereotype name.
	 * 
	 * @param property
	 *            the property
	 * @return the first modelica ml component stereotype name
	 */
	private static String getFirstModelicaMLComponentStereotypeName(Property property) {
		String name = null;
		if (UmlServices.hasStereotype((Element) property, "Component")) return "Component";
		if (UmlServices.hasStereotype((Element) property, "Variable")) return "Variable";
		if (UmlServices.hasStereotype((Element) property, "RequirementInstance")) return "RequirementInstance";
		if (UmlServices.hasStereotype((Element) property, "CalculatedProperty")) return "CalculatedProperty";
		return name;
	}
	
//	private static String getExtendsStereotypeName(Generalization extendsRelation) {
//		String name = null;
//		if (UmlServices.hasStereotype((Element) extendsRelation, "ExtendsRelation")) return "ExtendsRelation";
//		if (UmlServices.hasStereotype((Element) extendsRelation, "TypeRelation")) return "TypeRelation";
//		return name;
//	}

}
