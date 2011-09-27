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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.helper.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
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
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.helper.dialogs.InstantiateRequirementsDialog;
import org.openmodelica.modelicaml.helper.impl.RequirementsInstantiator;
import org.openmodelica.modelicaml.helper.impl.VerificationVerdictElementsGenerator;
import org.openmodelica.modelicaml.helper.impl.VerificationScenariosCollector;




// TODO: Auto-generated Javadoc
/**
 * The Class InstantiateRequirementsHandler.
 */
public class InstantiateRequirementsHandler extends AbstractHandler {

	/** The selected element. */
	private EObject selectedElement = null;
	
	// list of requirements that shall be displayed (e.g. used to filter requirements that are referenced by a test scenario)
	private ArrayList<Class> preselectedList = null;
	
	public void collectLinkedItems(Class containingClass, Property selectedProperty) {
		
		// if it is a property that was selected, e.g., an instantiated test scenario 
		// for which linked requirements should be instantiated.  
		if (selectedProperty != null && containingClass != null) {
			// get the test case class
			Type testCaseClass = selectedProperty.getType();
			if ( testCaseClass != null) {
				HashSet<Class> itemsFound = new HashSet<Class>();
				
				VerificationScenariosCollector tsc = new VerificationScenariosCollector();
				for (Element element : tsc.collectRequirementsForTestCase(testCaseClass)) {
					if (element instanceof Class) {
						itemsFound.add((Class) element);
					}
				}
				
				// set the preselected list
				if (itemsFound.size() > 0 ) {
				 	preselectedList = new ArrayList<Class>();
					preselectedList.addAll(itemsFound);

					// set the containing class
					selectedElement = containingClass;
				}
				else {
					MessageDialog.openInformation(new Shell(), "Linked requirements search", 
							"No requirements, that are referenced by the selected test scenario, are found in the model.");
				}
				
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// if setData was not called or was not successful -> use structured selection. 
		if (selectedElement == null) {
			selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));	
		}
		
		if (selectedElement instanceof Class) {
			
			InstantiateRequirementsDialog dialog = new InstantiateRequirementsDialog(new Shell(), (Class) selectedElement, preselectedList);
			dialog.open();
			
			if (dialog.getReturnCode() == 0) {
				HashSet<Class> selectedReq = dialog.getSelectedList();
				HashMap<Class, Integer> selectedNumberOfInstantiations = dialog.getSelectedNumberOfInstantiations();
				
				TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
				
				// instantiate requirements
				editingDomain.getCommandStack().execute(getCommand(editingDomain, selectedReq, selectedNumberOfInstantiations, (Class) selectedElement));
				
				// Requirements test oracle code 
//				if (selectedReq.size() > 0) { // if new requirements were instantiated
//					// ask for creating requirements evaluation elements and code 
//					boolean go = MessageDialog.openQuestion(new Shell(), "Confirmation", 
//							"Should the requirements evaluation elements and respective code be created or updated " +
//							"in '" + ((Class)selectedElement).getName() + "'?");
//					if (go) {
//						editingDomain.getCommandStack().execute(getTestOracleElementCreationCommand(editingDomain, (Class) selectedElement));
//					}
//				}
			}
		}
		return null;
	}

	/**
	 * Gets the command.
	 * 
	 * @param editingDomain
	 *            the editing domain
	 * @param reqClass
	 *            the req class
	 * @param owningClass
	 *            the owning class
	 * @return the command
	 */
	protected Command getCommand(TransactionalEditingDomain editingDomain, final HashSet<Class> selectedReq, final HashMap<Class, Integer> selectedNumberOfInstantiations, final Class owningClass) {
		CompoundCommand cc = new CompoundCommand("Instantiate Requirements in " + owningClass.getName() );
		
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
//				EList<Property> pList = owningClass.getAllAttributes();
//				int numberOfReqInstancesWithSameType = 0;
//				String prefix = "req_";
//				for (Property property : pList) {
//					String pName = StringUtls.replaceSpecChar(property.getName());
//					if (pName.substring(0, pName.length() - 2).startsWith(prefix + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase()) ) {
//						numberOfReqInstancesWithSameType ++; 
//					}
//				}
//				Integer postfix = numberOfReqInstancesWithSameType + 1;
//				String postfixString = "_" + postfix.toString();
//				
//				// create Property
//				Property p = ((Class)selectedElement).createOwnedAttribute(prefix + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase() + postfixString, reqClass);
//				// apply stereotype
//				Stereotype s = p.getApplicableStereotype("ModelicaML::ModelicaRequirementConstructs::RequirementInstance");
//				if (s != null) {
//					p.applyStereotype(s);
//				}
//				else {
//					MessageDialog.openError(new Shell(), "Error:", "Cannot apply ModelicaML stereotype to " + p.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
//				}
				RequirementsInstantiator ri = new RequirementsInstantiator();
				ri.instantiateRequirements(owningClass, selectedReq, selectedNumberOfInstantiations);
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	
	/**
	 * Gets the test oracle element creation command.
	 *
	 * @param editingDomain the editing domain
	 * @param owningClass the owning class
	 * @return the test oracle element creation command
	 */
	protected Command getTestOracleElementCreationCommand(TransactionalEditingDomain editingDomain, final Class owningClass) {
		CompoundCommand cc = new CompoundCommand("Create Test Oracle Elements in " + owningClass.getName());
		
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				VerificationVerdictElementsGenerator.createTestEvaluationElements(owningClass);
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


//	@Override
//	public boolean isHandled() {
//		// TODO Auto-generated method stub
//		return false;
//	}

	
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
	 * Adapt selected element.
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
	
	
	

	
	
	
}
