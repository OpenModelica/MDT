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
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.profile.Activator;
import org.openmodelica.modelicaml.profile.dialogs.OneOrTwoColumnWithTextAndOptionalComboDialog;
import org.openmodelica.modelicaml.properties.ModelicaMLConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class CreateModelicaVariableHandler.
 */
public class CreateModelicaVariableHandler extends AbstractHandler {

//### START: adapt it for a new command handler
	/** The stereotype path. */
private String stereotypePath = Platform.getResourceString(Activator.getDefault().getBundle(), "%p_path_ModelicaCompositeConstructs");
	
	/** The stereotype name. */
	private String stereotypeName = Platform.getResourceString(Activator.getDefault().getBundle(), "%s_variable");
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
		final String commandTitle = "Create New Variables(s)";
		CompoundCommand cc = new CompoundCommand(commandTitle);
		
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
//### START: adapt it for a new command handler
				selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				Property element = null;
				Stereotype stereotype = null;
				
				if (selectedElement instanceof Class) {
					Boolean useMultipleItemsDialogForCreatingNewElements = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "useMultipleItemsDialogForCreatingNewElements", true, null);
					
					if (useMultipleItemsDialogForCreatingNewElements) {
						
						// prepare dialog settings
						Shell parentShell = new Shell();
						String dialogTitle = commandTitle;
						String dialogMessage = "Define name(s) for the variable(s) to be created.";
						Element parentElement = (Element)selectedElement;
						EClass metaClassForNewElements = UMLPackage.Literals.PROPERTY;

						int numberOfItems = ModelicaMLConstants.numberOfItemsInCreateNewElementsDiologs;
						
						String lblText = "Name: ";
						String defaultName = "variable";
						
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

						String lblCombo = "Type: ";
						String[] comboItems = {"ModelicaReal", "ModelicaInteger", "ModelicaBoolean", "ModelicaString"};
						
						OneOrTwoColumnWithTextAndOptionalComboDialog dialog = new OneOrTwoColumnWithTextAndOptionalComboDialog(parentShell, dialogTitle, dialogMessage, parentElement, metaClassForNewElements, numberOfItems, lblText, textInitialValue, lblCombo, comboItems);
						dialog.open();
						
						if (dialog.getReturnCode() == 0) {
							ArrayList<String> firstColList = dialog.getFirstColValues();
							ArrayList<String> secondColList = dialog.getSecondColValues();
							for (int i = 0; i < firstColList.size(); i++) {
								//**************************************
								element = (Property) ((org.eclipse.uml2.uml.Class)selectedElement).createOwnedAttribute(firstColList.get(i), null);
								Type type = getTypeFromDialogString(secondColList.get(i), element);
								if (type != null) { element.setType(type); }
								
								stereotype = element.getApplicableStereotype(stereotypePath+"::"+stereotypeName);
								// apply ModelicaML stereotype
								if (stereotype == null && element != null) {
									Shell shell = new Shell();
									MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype " + stereotypeName +" to " + element.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
								}
								else {
									element.applyStereotype(stereotype);	
								}
								//**************************************
							}
						}
					}
					else {
						//**************************************
						element = (Property) ((org.eclipse.uml2.uml.Class)selectedElement).createOwnedAttribute("variableName1", null);
						Type type = getTypeFromDialogString("ModelicaReal", element);
						if (type != null) { element.setType(type); }
						
						stereotype = element.getApplicableStereotype(stereotypePath+"::"+stereotypeName);
						// apply ModelicaML stereotype
						if (stereotype == null && element != null) {
							Shell shell = new Shell();
							MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype " + stereotypeName +" to " + element.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
						}
						else {
							element.applyStereotype(stereotype);	
						}
						//**************************************
					}
					
//					Shell shellDialog = new Shell();
//					shellDialog.setSize(600, 350);
//					
//					TwoColumnTextAndComboDialog dialog = new TwoColumnTextAndComboDialog(shellDialog, "Create Variables Dialog", "Define the names and the type of variables to be created:", "variable", null);
//					dialog.open();
//					
//					if (dialog.getReturnCode() == 0) {
//	
//						List<VariableData> list = dialog.getVarList();
//						
//						for (VariableData variablesData : list) {
//							String nameString = variablesData.getName();
//							String typeString = variablesData.getType();
//							//TODO: Should the name get a post-fix number (e.g. ModelicaModel1, ModelicaModel2, etc.)?
//							p = (Property) ((org.eclipse.uml2.uml.Class)selectedElement).createOwnedAttribute(nameString, null);
//							Type type = getTypeFromDialogString(typeString, p);
//							if (type != null) { p.setType(type); }
//							
//							stereotype = p.getApplicableStereotype(stereotypePath+"::"+stereotypeName);
//							// apply ModelicaML stereotype
//							if (stereotype == null && p != null) {
//								Shell shell = new Shell();
//								MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype " + stereotypeName +" to " + p.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
//							}
//							else {
//								p.applyStereotype(stereotype);	
//							}
//						}
//					}
				}
				else {
					System.err.println("Could not create a Modelica " + stereotypeName + " in " + selectedElement.getClass().getName());
				}
//### END: adapt it for a new command handler
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	/**
	 * Gets the type from dialog string.
	 * 
	 * @param typeString
	 *            the type string
	 * @param p
	 *            the p
	 * @return the type from dialog string
	 */
	private Type getTypeFromDialogString(String typeString, TypedElement p){
		Type type = p.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaReal");
		if (typeString.equals("ModelicaInteger")) {
			return type = p.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaInteger");
		}
		if (typeString.equals("ModelicaBoolean")) {
			return type = p.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaBoolean");
		}
		if (typeString.equals("ModelicaString")) {
			return type = p.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType("ModelicaString");
		}
		return type;
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
