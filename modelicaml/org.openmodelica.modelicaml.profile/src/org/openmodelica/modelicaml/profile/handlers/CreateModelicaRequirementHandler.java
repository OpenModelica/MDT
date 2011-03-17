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
package org.openmodelica.modelicaml.profile.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.profile.Activator;
import org.openmodelica.modelicaml.profile.dialogs.OneOrTwoColumnWithTextAndOptionalComboDialog;
import org.openmodelica.modelicaml.properties.ModelicaMLConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class CreateModelicaRequirementHandler.
 */
public class CreateModelicaRequirementHandler extends AbstractHandler {

//### START: adapt it for a new command handler
	/** The stereotype path. */
private String stereotypePath = Platform.getResourceString(Activator.getDefault().getBundle(), "%p_path_ModelicaRequirementConstructs");
	
	/** The stereotyep name. */
	private String stereotypeName = Platform.getResourceString(Activator.getDefault().getBundle(), "%s_requirement");
//### END: adapt it for a new command handler
	
	/** The selected element. */
private EObject selectedElement = null;
	
	/**
	 * Execute Command. Get the property from the selection, and apply the
	 * semantic command on each selection.
	 * 
	 * @param event
	 *            the event
	 * @return the object
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
		editingDomain.getCommandStack().execute(getCommand(editingDomain));
		return null;
	}

	/**
	 * Gets the command.
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @return the command
	 */
	protected Command getCommand(TransactionalEditingDomain editingDomain) {
		final String commandTitle = "Create New Requirement(s)";
		CompoundCommand cc = new CompoundCommand(commandTitle);
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				Class element = null;
				
//### START: adapt it for a new command handler
				Boolean useMultipleItemsDialogForCreatingNewElements = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "useMultipleItemsDialogForCreatingNewElements", true, null);
				
				if (useMultipleItemsDialogForCreatingNewElements) { // with dialog
					
					// prepare dialog settings
					Shell parentShell = new Shell();
					String dialogTitle = commandTitle;
					String dialogMessage = "Define name(s) for the requirement(s) to be created.";
					Element parentElement = (Element)selectedElement;
					EClass metaClassForNewElements = UMLPackage.Literals.CLASS;
					
					int numberOfItems = ModelicaMLConstants.numberOfItemsInCreateNewElementsDiologs;
					
					String lblText = "Name: ";
					String defaultName = "Requirement";
					
					EList<Element> existingElements = parentElement.getOwnedElements();
					Integer postfix = 1;
					for (Element existingElement : existingElements) {
						if (existingElement instanceof NamedElement) {
							if ( ((NamedElement)existingElement).getName().startsWith(defaultName) ) {
								postfix ++;
							}
						}
					}
					
					String textInitialValue = defaultName + postfix.toString();

					String lblCombo = null;
					String[] comboItems = null;
					
					OneOrTwoColumnWithTextAndOptionalComboDialog dialog = new OneOrTwoColumnWithTextAndOptionalComboDialog(parentShell, dialogTitle, dialogMessage, parentElement, metaClassForNewElements, numberOfItems, lblText, textInitialValue, lblCombo, comboItems);
					dialog.open();
					
					if (dialog.getReturnCode() == 0) {
						ArrayList<String> firstColList = dialog.getFirstColValues();
						ArrayList<String> secondColList = dialog.getSecondColValues();
						for (int i = 0; i < firstColList.size(); i++) {
							//**************************************
							element = createElement(firstColList.get(i));
						}
					}
				}
				else { // default creation without a dialog
					element = createElement(stereotypeName);
				}
//### END: adapt it for a new command handler
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	/**
	 * Creates the element.
	 *
	 * @param name the name
	 * @return the class
	 */
	private Class createElement(String name){
		
		Class element = null;
		Stereotype stereotype = null;
		
		if (selectedElement instanceof Class) {
			element = (Class) ((org.eclipse.uml2.uml.Class)selectedElement).createNestedClassifier(name, UMLPackage.Literals.CLASS);
		}
		else if (selectedElement instanceof Model) {
			element = ((Model)selectedElement).createOwnedClass(name, false);
		}
		else if (selectedElement instanceof Package) {
			element = ((Package)selectedElement).createOwnedClass(name, false);
		}
		else {
			System.err.println("Could not create a " + stereotypeName + " in " + selectedElement.getClass().getName());
		}
		
		if (element != null) {
			
			stereotype = element.getApplicableStereotype(stereotypePath+"::"+stereotypeName);
			
			Type booleanType = element.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaBoolean");
			
			Property violatedAttribute = element.getOwnedAttribute("violated", booleanType, true, UMLPackage.Literals.PROPERTY, true);
			Stereotype violatedAttributeStereotype = violatedAttribute.getApplicableStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
			if (violatedAttribute.getAppliedStereotype(violatedAttributeStereotype.getQualifiedName()) == null) {
				violatedAttribute.applyStereotype(violatedAttributeStereotype);
				violatedAttribute.setValue(violatedAttributeStereotype, "causality", "output");
			}

			Property evaluatedAttribute = element.getOwnedAttribute("evaluated", booleanType, true, UMLPackage.Literals.PROPERTY, true);
			Stereotype evaluatedAttributeStereotype = evaluatedAttribute.getApplicableStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
			if (evaluatedAttribute.getAppliedStereotype(evaluatedAttributeStereotype.getQualifiedName()) == null) {
				evaluatedAttribute.applyStereotype(evaluatedAttributeStereotype);
				evaluatedAttribute.setValue(evaluatedAttributeStereotype, "causality", "output");
			}
			
			// apply ModelicaML stereotype
			if (stereotype == null || evaluatedAttributeStereotype == null || violatedAttributeStereotype == null) {
				Shell shell = new Shell();
				MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype " + stereotypeName +" to " + element.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
			}
			else {
				element.applyStereotype(stereotype);
			}
		}
		return element;
	}
	
	/**
	 * Returns a <code>List</code> containing the currently selected objects.
	 * 
	 * @param event
	 *            the event
	 * @return A List containing the currently selected objects.
	 * @throws ExecutionException
	 *             the execution exception
	 */
	protected List<Object> getSelectedElements(ExecutionEvent event) throws ExecutionException {
		
		ISelection selection = getSelection(event);
		if (!( selection instanceof IStructuredSelection))
			return Collections.emptyList();
		return (List<Object>)((IStructuredSelection) selection).toList();
	}

	/**
	 * Gets the current selection.
	 * 
	 * @param event
	 *            the event
	 * @return The current selection.
	 * @throws ExecutionException
	 *             the execution exception
	 */
	protected ISelection getSelection(ExecutionEvent event) throws ExecutionException {
		return HandlerUtil.getCurrentSelectionChecked(event);
	}

	/**
	 * Gets the current selections.
	 * 
	 * @return the current selections
	 */
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
	
		return null;
	}

	/**
	 * Adapt the selected element to an EObject, in case there is intermediate
	 * construct (like notation.View)
	 * 
	 * @param selection
	 *            the selection
	 * @return the e object
	 */
	protected EObject adaptSelectedElement( Object selection) {

		EObject eObject = null;
	
		if(selection != null) {
	
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
	
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}

	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	public boolean isEnabled() {
		
//		for( Object selection : getCurrentSelections()) {
//			EObject converted = adaptSelectedElement(selection);
//			
//			if(converted instanceof Class)
//				return true;
//		}
//		return false;
		return true;
	}

	
	/**
	 * Checks if is visible.
	 * 
	 * @return true, if is visible
	 */
	public boolean isVisible() {
//		for( Object selection : getCurrentSelections()) {
//			EObject converted = adaptSelectedElement(selection);
//			
//			if(converted instanceof Class)
//				return true;
//		}
//		return false;
		return true;
	}

}
