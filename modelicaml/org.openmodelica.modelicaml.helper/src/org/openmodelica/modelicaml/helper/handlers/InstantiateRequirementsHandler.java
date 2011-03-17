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
package org.openmodelica.modelicaml.helper.handlers;

import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.dialogs.InstantiateRequirementsDialog;
import org.openmodelica.modelicaml.helper.impl.TestOracleElementsCreator;




// TODO: Auto-generated Javadoc
/**
 * The Class InstantiateRequirementsHandler.
 */
public class InstantiateRequirementsHandler extends AbstractHandler {

	/** The selected element. */
	private EObject selectedElement = null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
		
		if (selectedElement instanceof Class) {
			
			InstantiateRequirementsDialog dialog = new InstantiateRequirementsDialog(new Shell(), (Class) selectedElement);
			dialog.open();
			
			if (dialog.getReturnCode() == 0) {
				HashSet<Class> selectedReq = dialog.getSelectedList();
				
				TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
				for (Class reqClass : selectedReq) {
					editingDomain.getCommandStack().execute(getCommand(editingDomain, reqClass, (Class) selectedElement));
				}
				editingDomain.getCommandStack().execute(getTestOracleElementCreationCommand(editingDomain, (Class) selectedElement));
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
	protected Command getCommand(TransactionalEditingDomain editingDomain, final Class reqClass, final Class owningClass) {
		CompoundCommand cc = new CompoundCommand("Instantiate Requirement " + reqClass.getName());
		
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				EList<Property> pList = owningClass.getAllAttributes();
				int numberOfReqInstancesWithSameType = 0;
				String prefix = "req_";
				for (Property property : pList) {
					String pName = StringUtls.replaceSpecChar(property.getName());
					if (pName.substring(0, pName.length() - 2).startsWith(prefix + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase()) ) {
						numberOfReqInstancesWithSameType ++; 
					}
				}
				Integer postfix = numberOfReqInstancesWithSameType + 1;
				String postfixString = "_" + postfix.toString();
				
				// create Property
				Property p = ((Class)selectedElement).createOwnedAttribute(prefix + StringUtls.replaceSpecChar(reqClass.getName()).toLowerCase() + postfixString, reqClass);
				// apply stereotype
				Stereotype s = p.getApplicableStereotype("ModelicaML::ModelicaRequirementConstructs::RequirementInstance");
				if (s != null) {
					p.applyStereotype(s);
				}
				else {
					MessageDialog.openError(new Shell(), "Error:", "Cannot apply ModelicaML stereotype to " + p.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
				}
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
				TestOracleElementsCreator.createTestEvaluationElements(owningClass);
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
