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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.services.UmlServices;
//import org.openmodelica.modelicaml.view.componentstree.utls.UmlServices;
//import org.openmodelica.modelicaml.view.componentstree.utls.Utls;

// TODO: Auto-generated Javadoc
//import com.cea.papyrus.core.listener.PapyrusNotification;

/**
 * The Class ModificationManager.
 */
public class ModificationManager {
	
	/** The Constant StereotypePath_Model. */
	private static final String StereotypePath_Model = "ModelicaML::ModelicaClassConstructs::Model";
	
	/** The Constant StereotypePath_record. */
	private static final String StereotypePath_record = "ModelicaML::ModelicaClassConstructs::Record";
	
	/** The Constant StereotypePath_variable. */
	private static final String StereotypePath_variable = "ModelicaML::ModelicaCompositeConstructs::Variable";
	
	/** The Constant StereotypePath_CalculatedProperty. */
	private static final String StereotypePath_CalculatedProperty  = "ModelicaML::ModelicaCompositeConstructs::CalculatedProperty";
	
	/** The Constant StereotypePropertyName_modification. */
	private static final String StereotypePropertyName_modification = "modification";
	
	/** The Constant StereotypePropertyName_causality. */
	private static final String StereotypePropertyName_causality = "causality";
	
	/** The Constant StereotypePropertyName_causality_output. */
	private static final String StereotypePropertyName_causality_output = "output";
	
	/** The Constant StereotypePropertyName_causality_input. */
	private static final String StereotypePropertyName_causality_input = "input";
	
	/** The Constant StereotypeName_variable. */
	private static final String StereotypeName_variable = "Variable";
	
	/** The Constant inputsComponentName. */
	private static final String inputsComponentName = "_inputs";
	
	/** The Constant inputsClassName. */
	private static final String inputsClassName = "_Inputs";
	
	/** The Constant outputsComponentName. */
	private static final String outputsComponentName = "_outputs";
	
	/** The Constant outputsClassName. */
	private static final String outputsClassName = "_Outputs";
	
	
	/**
	 * Adds the component modification.
	 * 
	 * @param component
	 *            the component
	 * @param modificationLeftHandPart
	 *            the modification left hand part
	 * @param modificationRightHandPart
	 *            the modification right hand part
	 * @param overwriteExistingModification
	 *            the overwrite existing modification
	 */
	public static void addComponentModification(
			final Property component, 
			String modificationLeftHandPart,
			String modificationRightHandPart,
			Boolean overwriteExistingModification){
		
		HashSet<String> bindingEquations = new HashSet<String>(); // new modifications to be created (no duplicates)
		
		HashSet<String> newModificationList = new HashSet<String>(); // new value to be set (no duplicates)
		final List<String> newValue = new ArrayList<String>(); // new modification value

		HashSet<String> existingModificationList = getModifications(component); // existing modifications
		HashSet<String> filteredModificationsList = new HashSet<String>(); // filtered modifications 
		filteredModificationsList.addAll(existingModificationList);
				
		String bindingEquation = "";
		bindingEquation = modificationLeftHandPart + " = " + modificationRightHandPart;	
		bindingEquations.add(bindingEquation);				
	
	
		if (overwriteExistingModification) {
			for (String string : existingModificationList) {
				String[] sPart = string.split("=");
				String modFirstPart = sPart[0].trim();
				if ( modFirstPart.equals(modificationLeftHandPart.trim())) {
					filteredModificationsList.remove(string); // remove existing modification so that the new one will replace it.
				}
			}
		}

		final Stereotype stereotype = getComponentStereotype(component);
		
		if (stereotype != null) {
			newModificationList.addAll(bindingEquations); // add first the new modifications
			newModificationList.addAll(filteredModificationsList); // then add the existing modification and reject duplicates
			newValue.addAll(newModificationList); // add all now to the new list to be sorted and set to the property
			Collections.sort(newValue); // sort the new list
			
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					component.setValue(stereotype, StereotypePropertyName_modification, newValue); // set the value
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
			
			//component.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null)); // notify Papyrus
		}
		
	}
	
	
	/**
	 * Delete component modification.
	 * 
	 * @param component
	 *            the component
	 * @param modificationString
	 *            the modification string
	 */
	public static void deleteComponentModification(
			final Property component, 
			String modificationString){
		
		HashSet<String> newModificationList = new HashSet<String>(); // new value to be set (no duplicates)
		final List<String> newValue = new ArrayList<String>(); // new modification value

		HashSet<String> existingModificationList = getModifications(component); // existing modifications
		HashSet<String> filteredModificationsList = new HashSet<String>(); // filtered modifications 
		filteredModificationsList.addAll(existingModificationList);

		for (String string : existingModificationList) {
//			System.out.println("string: " + string);
//			System.out.println("modificationString: " + modificationString);
			if (string.equals(modificationString)) {
				filteredModificationsList.remove(string); // remove existing modification so that the new one will replace it.
			}
		}

		final Stereotype stereotype = getComponentStereotype(component);
		
		if (stereotype != null) {
			newModificationList.addAll(filteredModificationsList); // then add the existing modification and reject duplicates
			newValue.addAll(newModificationList); // add all now to the new list to be sorted and set to the property
			Collections.sort(newValue); // sort the new list
			
			
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					component.setValue(stereotype, StereotypePropertyName_modification, newValue); // set the value
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end

			//component.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null)); // notify Papyrus
		}
		
	}
	
	
	/**
	 * Checks if is in mod mod list of component.
	 * 
	 * @param component
	 *            the component
	 * @param lefthandValue
	 *            the lefthand value
	 * @return the boolean
	 */
	public static Boolean isInModModListOfComponent(
			Property component, 
			String lefthandValue){
		
		HashSet<String> existingModificationList = getModifications(component); // existing modifications

		for (String string : existingModificationList) {
			String[] splitted = string.trim().split("=");
			if (splitted.length > 1) {
				String leftHand = splitted[0].trim();
				//String rightHand = splitted[1].trim();
				if (leftHand.equals(lefthandValue)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Delete component modification based on left hand value.
	 * 
	 * @param component
	 *            the component
	 * @param lefthandValue
	 *            the lefthand value
	 * @return the string
	 */
	public static String deleteComponentModificationBasedOnLeftHandValue(
			final Property component, 
			String lefthandValue){
		
		String deletedString = "";
		
		HashSet<String> newModificationList = new HashSet<String>(); // new value to be set (no duplicates)
		final List<String> newValue = new ArrayList<String>(); // new modification value

		HashSet<String> existingModificationList = getModifications(component); // existing modifications
		HashSet<String> filteredModificationsList = new HashSet<String>(); // filtered modifications 
		filteredModificationsList.addAll(existingModificationList);

		for (String string : existingModificationList) {
			String[] splitted = string.trim().split("=");
			if (splitted.length > 1) {
				String leftHand = splitted[0].trim();
				//String rightHand = splitted[1].trim();
				if (leftHand.equals(lefthandValue)) {
					filteredModificationsList.remove(string); // remove existing modification so that the new one will replace it.
					deletedString = string;
				}
			}
		}

		final Stereotype stereotype = getComponentStereotype(component);
		
		if (stereotype != null) {
			newModificationList.addAll(filteredModificationsList); // then add the existing modification and reject duplicates
			newValue.addAll(newModificationList); // add all now to the new list to be sorted and set to the property
			Collections.sort(newValue); // sort the new list
			
			
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					component.setValue(stereotype, StereotypePropertyName_modification, newValue); // set the value
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
			
//			component.setValue(stereotype, StereotypePropertyName_modification, newValue); // set the value
			//component.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null)); // notify Papyrus
		}
		return deletedString;
	}
	
	/**
	 * Delete component modification from list.
	 * 
	 * @param list
	 *            the list
	 * @param lefthandValue
	 *            the lefthand value
	 * @return the hash set
	 */
	public static HashSet<String> deleteComponentModificationFromList(
			HashSet<String> list, 
			String lefthandValue){
		
		HashSet<String> newModificationList = new HashSet<String>(); // new value to be set (no duplicates)
		HashSet<String> newValue = new HashSet<String>(); // new modification value

		HashSet<String> existingModificationList = list; // existing modifications
		HashSet<String> filteredModificationsList = new HashSet<String>(); // filtered modifications 
		filteredModificationsList.addAll(existingModificationList);

		
		for (String string : existingModificationList) {
						String[] splitted = string.trim().split("=");
			if (splitted.length > 1) {
				String leftHand = splitted[0].trim();
				//String rightHand = splitted[1].trim();
				if (leftHand.equals(lefthandValue)) {
					filteredModificationsList.remove(string); // remove existing modification so that the new one will replace it.
					//System.out.println("removed: " + string);
				}
			}
		}
		
		newModificationList.addAll(filteredModificationsList); // then add the existing modification and reject duplicates
		newValue.addAll(newModificationList); // add all now to the new list to be sorted and set to the property
		
		return newValue;
	}
	
	

	/**
	 * Adds the to class inputs.
	 * 
	 * @param selectedClass
	 *            the selected class
	 * @param firstLevelComponent
	 *            the first level component
	 * @param component
	 *            the component
	 * @param componentDotPathWithoutFirstLevelComponentName
	 *            the component dot path without first level component name
	 * @param componentDotPath
	 *            the component dot path
	 */
	public static void addToClassInputs(final Class selectedClass, Property firstLevelComponent, final Property component, String componentDotPathWithoutFirstLevelComponentName, final String componentDotPath){
		
//		Class inputsClass = (Class)selectedClass.getNestedClassifier(inputsClassName, true, UMLPackage.Literals.CLASS, false);
		
		final Class inputsClassCheck = (Class)selectedClass.getNestedClassifier(inputsClassName, true, UMLPackage.Literals.CLASS, false);
		final Class inputsClass;

		
		if (inputsClassCheck == null) {
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					selectedClass.createNestedClassifier(inputsClassName, UMLPackage.Literals.CLASS);
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
		}
		
		inputsClass = (Class)selectedClass.getNestedClassifier(inputsClassName, true, UMLPackage.Literals.CLASS, false);
				
//		// apply ModelicaML stereotype
//		if (inputsClass.getAppliedStereotype(StereotypePath_Model) == null) {
//			Stereotype resultsClassStereotype = inputsClass.getApplicableStereotype(StereotypePath_Model);
//			inputsClass.applyStereotype(resultsClassStereotype);
//		}

		// apply ModelicaML stereotype
		if (inputsClass.getAppliedStereotype(StereotypePath_Model) == null) {
			final Stereotype resultsClassStereotype = inputsClass.getApplicableStereotype(StereotypePath_Model);
			
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					inputsClass.applyStereotype(resultsClassStereotype);
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
			
		}
		
		
		// add property to the "Inputs" class
		//Property p = inputsClass.getOwnedAttribute(Utls.replaceSpecChar(componentDotPath), component.getType(), true, UMLPackage.Literals.PROPERTY, true);
		Property pCheck = inputsClass.getOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType(), true, UMLPackage.Literals.PROPERTY, false);


		if (pCheck == null) {
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					inputsClass.createOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType());
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
		}

		final Property p = inputsClass.getOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType(), true, UMLPackage.Literals.PROPERTY, false);

		// apply ModelicaML stereotype to the properties
		if ( !UmlServices.hasStereotype(p, StereotypeName_variable) ) {
			final Stereotype pStereotype = p.getApplicableStereotype(StereotypePath_variable);
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					p.applyStereotype(pStereotype);
					//set causality
					//p.setValue(pStereotype, StereotypePropertyName_causality, StereotypePropertyName_causality_input);
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end	
			
		}

		// add modification to the "_inputs" property
		Property inputsPropertyCheck = selectedClass.getOwnedAttribute(inputsComponentName, inputsClass, true, UMLPackage.Literals.PROPERTY, false);

		//########## storing start
		if (inputsPropertyCheck == null) {
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					selectedClass.createOwnedAttribute(inputsComponentName, inputsClass);
					//Property outputsProperty = selectedClass.getOwnedAttribute(outputsComponentName, outputsClass, true, UMLPackage.Literals.PROPERTY, false);
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
		}
		//########## storing end
		
		final Property inputsProperty = selectedClass.getOwnedAttribute(inputsComponentName, inputsClass, true, UMLPackage.Literals.PROPERTY, false);
		final Stereotype inputsPropertyStereotype = inputsProperty.getApplicableStereotype(StereotypePath_CalculatedProperty);
		if (inputsProperty.getAppliedStereotype(StereotypePath_CalculatedProperty) == null) {
			//########## storing start
			if (inputsClassCheck == null) {
				TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
				CompoundCommand cc = new CompoundCommand();
				Command command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						inputsProperty.applyStereotype(inputsPropertyStereotype);
					}
				};
				cc.append(command);
				editingDomain.getCommandStack().execute(cc);
			}
			//########## storing end
		}
		addComponentModification(firstLevelComponent, componentDotPathWithoutFirstLevelComponentName, inputsComponentName + "." + p.getName(), true);
		//inputsClass.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null)); // notify Papyrus
	}
	
	
	/**
	 * Adds the to class outputs.
	 * 
	 * @param selectedClass
	 *            the selected class
	 * @param component
	 *            the component
	 * @param componentDotPath
	 *            the component dot path
	 */
	public static void addToClassOutputs(final Class selectedClass, final Property component, final String componentDotPath){
		
		final Class outputsClassCheck = (Class)selectedClass.getNestedClassifier(outputsClassName, true, UMLPackage.Literals.CLASS, false);
		final Class outputsClass;
		
		if (outputsClassCheck == null) {
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					selectedClass.createNestedClassifier(outputsClassName, UMLPackage.Literals.CLASS);
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
		}
		
		outputsClass = (Class)selectedClass.getNestedClassifier(outputsClassName, true, UMLPackage.Literals.CLASS, false);
		
		// apply ModelicaML stereotype
		if (outputsClass.getAppliedStereotype(StereotypePath_record) == null) {
			final Stereotype resultsClassStereotype = outputsClass.getApplicableStereotype(StereotypePath_record);
			
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					outputsClass.applyStereotype(resultsClassStereotype);
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
			
		}

		// add property to the "Outputs" class
		Property pCheck = outputsClass.getOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType(), true, UMLPackage.Literals.PROPERTY, false);
		
		if (pCheck == null) {
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					outputsClass.createOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType());
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
		}
		
		final Property p = outputsClass.getOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType(), true, UMLPackage.Literals.PROPERTY, false);
		
		// apply ModelicaML stereotype to the properties
		if ( !UmlServices.hasStereotype(p, StereotypeName_variable) ) {
			final Stereotype pStereotype = p.getApplicableStereotype(StereotypePath_variable);
			
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					p.applyStereotype(pStereotype);
					//set causality
					p.setValue(pStereotype, StereotypePropertyName_causality, StereotypePropertyName_causality_output);				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end	
		}

		// add modification to the "_outputs" property
		Property outputsPropertyCheck = selectedClass.getOwnedAttribute(outputsComponentName, outputsClass, true, UMLPackage.Literals.PROPERTY, false);
		
		//########## storing start
		if (outputsPropertyCheck == null) {
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand();
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					selectedClass.createOwnedAttribute(outputsComponentName, outputsClass);
					//Property outputsProperty = selectedClass.getOwnedAttribute(outputsComponentName, outputsClass, true, UMLPackage.Literals.PROPERTY, false);
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
		}
		//########## storing end
		
		final Property outputsProperty = selectedClass.getOwnedAttribute(outputsComponentName, outputsClass, true, UMLPackage.Literals.PROPERTY, false);
		final Stereotype outputsPropertyStereotype = outputsProperty.getApplicableStereotype(StereotypePath_CalculatedProperty);
		if (outputsProperty.getAppliedStereotype(StereotypePath_CalculatedProperty) == null) {
			
			//########## storing start
			if (outputsClassCheck == null) {
				TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
				CompoundCommand cc = new CompoundCommand();
				Command command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						outputsProperty.applyStereotype(outputsPropertyStereotype);					}
				};
				cc.append(command);
				editingDomain.getCommandStack().execute(cc);
			}
			//########## storing end
		}
		
		addComponentModification(outputsProperty, StringUtls.replaceSpecChar(componentDotPath), componentDotPath, true);
		//outputsClass.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null)); // notify Papyrus
	}
	
	/**
	 * Checks if is used in class inputs_remove option.
	 * 
	 * @param selectedClass
	 *            the selected class
	 * @param firstLevelComponent
	 *            the first level component
	 * @param component
	 *            the component
	 * @param componentDotPathWithoutFirstLevelComponentName
	 *            the component dot path without first level component name
	 * @param componentDotPath
	 *            the component dot path
	 * @param deleteIt
	 *            the delete it
	 * @return the boolean
	 */
	public static Boolean isUsedInClassInputs_removeOption(Class selectedClass, Property firstLevelComponent, Property component, String componentDotPathWithoutFirstLevelComponentName, String componentDotPath, Boolean deleteIt){
		final Class inputsClass = (Class)selectedClass.getNestedClassifier(inputsClassName, true, UMLPackage.Literals.CLASS, false);
		if (inputsClass != null) {
			final Property p = inputsClass.getOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType(), true, UMLPackage.Literals.PROPERTY, false);
			if (p != null) {
				final Property inputsProperty = selectedClass.getOwnedAttribute(inputsComponentName, inputsClass, true, UMLPackage.Literals.PROPERTY, true);
				Stereotype inputsPropertyStereotype = inputsProperty.getApplicableStereotype(StereotypePath_CalculatedProperty);
				if (inputsProperty != null ) {
					if (inputsPropertyStereotype != null) {
						HashSet<String> existingModificationList = getModifications(firstLevelComponent); // existing modifications
						for (String string : existingModificationList) {
							String[] sPart = string.split("=");
							if (sPart.length > 0) {
								String modLeftHand = sPart[0].trim();
								String modRightHand = null;
								if (sPart.length > 1 ) {
									modRightHand = sPart[1].trim();
								}
								
								if ( modLeftHand.equals(componentDotPathWithoutFirstLevelComponentName.trim()) 
									&& modRightHand.equals(inputsComponentName + "." + StringUtls.replaceSpecChar(componentDotPath))) {
									
									if (deleteIt) {
										TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
										//########## storing start
										CompoundCommand cc = new CompoundCommand();
										Command command = new RecordingCommand(editingDomain) {
											@Override
											protected void doExecute() {
												p.destroy();
											}
										};
										cc.append(command);
										editingDomain.getCommandStack().execute(cc);
										//########## storing end
										
										deleteComponentModification(firstLevelComponent, string);
										
										if (inputsClass.getAllAttributes().size() < 1 ) {
											//########## storing start
											CompoundCommand cc2 = new CompoundCommand();
											Command command2 = new RecordingCommand(editingDomain) {
												@Override
												protected void doExecute() {
													inputsClass.destroy();
													inputsProperty.destroy();
												}
											};
											cc2.append(command2);
											editingDomain.getCommandStack().execute(cc2);
											//########## storing end
										}
									}
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if is used in class outputs_remove option.
	 * 
	 * @param selectedClass
	 *            the selected class
	 * @param component
	 *            the component
	 * @param componentDotPath
	 *            the component dot path
	 * @param deleteIt
	 *            the delete it
	 * @return the boolean
	 */
	public static Boolean isUsedInClassOutputs_removeOption(final Class selectedClass, Property component, String componentDotPath, Boolean deleteIt){
		// get the Outputs class
		final Class outputsClass = (Class)selectedClass.getNestedClassifier(outputsClassName, true, UMLPackage.Literals.CLASS, false);
		
		if (outputsClass != null) {
			// get the corresponding property in the Outputs class
			final Property p = outputsClass.getOwnedAttribute(StringUtls.replaceSpecChar(componentDotPath), component.getType(), true, UMLPackage.Literals.PROPERTY, false);
			if (p != null) {
					// get the instance of the Outputs class
				
				Property outputsPropertyCheck = selectedClass.getOwnedAttribute(outputsComponentName, outputsClass, true, UMLPackage.Literals.PROPERTY, false);
				//########## storing start
				if (outputsPropertyCheck == null) {
					TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
					CompoundCommand cc = new CompoundCommand();
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							selectedClass.createOwnedAttribute(outputsComponentName, outputsClass);
						}
					};
					cc.append(command);
					editingDomain.getCommandStack().execute(cc);
				}
				//########## storing end
				
				final Property outputsProperty = selectedClass.getOwnedAttribute(outputsComponentName, outputsClass, true, UMLPackage.Literals.PROPERTY, false);
				Stereotype outputsPropertyStereotype = outputsProperty.getApplicableStereotype(StereotypePath_CalculatedProperty);
				
				if (outputsProperty != null ) {
					if (outputsPropertyStereotype != null) {
							// get the modification from the instance of the Output class
						HashSet<String> existingModificationList = getModifications(outputsProperty); // existing modifications
						for (String string : existingModificationList) {
							String[] sPart = string.split("=");
							if (sPart.length > 0) {
								String modLeftHand = sPart[0].trim();
								String modRightHand = sPart[1].trim();
							
								// compare if the instance of the Outputs class has a modification that consists of the corresponding property and the dotPath
								if ( modLeftHand.equals(p.getName()) 
									&& modRightHand.equals(componentDotPath)) {
									
									if (deleteIt) {

										TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
										
										//########## storing start
										CompoundCommand cc = new CompoundCommand();
										Command command = new RecordingCommand(editingDomain) {
											@Override
											protected void doExecute() {
												p.destroy();
											}
										};
										cc.append(command);
										editingDomain.getCommandStack().execute(cc);
										//########## storing end
										
										deleteComponentModification(outputsProperty, string);
										
										if (outputsClass.getAllAttributes().size() < 1 ) {
											//########## storing start
											CompoundCommand cc2 = new CompoundCommand();
											Command command2 = new RecordingCommand(editingDomain) {
												@Override
												protected void doExecute() {
													outputsClass.destroy();
													outputsProperty.destroy();
												}
											};
											cc2.append(command2);
											editingDomain.getCommandStack().execute(cc2);
											//########## storing end
										}
									}
									else {
										return true; 
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
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
		if (UmlServices.hasStereotype((Element) property, "Variable")) return stereotype = property.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
		if (UmlServices.hasStereotype((Element) property, "Component")) return stereotype = property.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Component");
		if (UmlServices.hasStereotype((Element) property, "ConnectionPort")) return stereotype = property.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::ConnectionPort");
		if (UmlServices.hasStereotype((Element) property, "CalculatedProperty")) return stereotype = property.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::CalculatedProperty");
			// note: requirementInstance in is another profile than the rest! 
		if (UmlServices.hasStereotype((Element) property, "RequirementInstance")) return stereotype = property.getAppliedStereotype("ModelicaML::ModelicaRequirementConstructs::RequirementInstance");
		return stereotype;
	}
	
	/**
	 * Gets the modifications.
	 * 
	 * @param component
	 *            the component
	 * @return the modifications
	 */
	private static HashSet<String> getModifications(Property component)  {
		HashSet<String> mList = new HashSet<String>();
		String stereotypeName = getFirstModelicaMLStereotypeName(component);
		List<String> modificationList = new ArrayList<String>();
		if (stereotypeName != null && !stereotypeName.equals("")) {
			Object o = UmlServices.getStereotypeValue((Element)component, getFirstModelicaMLStereotypeName(component),"modification");	
			if (o instanceof List<?>) {
				modificationList = (List<String>)o;
			}
			mList.addAll(modificationList);
		}		
		return mList;
	}
	
	/**
	 * Gets the first modelica ml stereotype name.
	 * 
	 * @param property
	 *            the property
	 * @return the first modelica ml stereotype name
	 */
	private static String getFirstModelicaMLStereotypeName(Property property) {
		String name = null;
		if (property != null) {
			if (UmlServices.hasStereotype((Element) property, "Variable")) return "Variable";
			if (UmlServices.hasStereotype((Element) property, "Component")) return "Component";
			if (UmlServices.hasStereotype((Element) property, "ConnectionPort")) return "ConnectionPort";
			if (UmlServices.hasStereotype((Element) property, "CalculatedProperty")) return "CalculatedProperty";
			if (UmlServices.hasStereotype((Element) property, "RequirementInstance")) return "RequirementInstance";			
		}
		return name;
	}
	
	
}
