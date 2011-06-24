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
import org.openmodelica.modelicaml.helper.impl.TestOracleElementsCreator;




// TODO: Auto-generated Javadoc
/**
 * The Class InstantiateRequirementsHandler.
 */
public class GenerateTestOracleElementsHandler extends AbstractHandler {

	/** The selected element. */
	private EObject selectedElement = null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
		
		if (selectedElement instanceof Class) {
			
			Shell shell = new Shell();
			Boolean go = MessageDialog.openQuestion(shell, "Please confirm ...", "This helper will create or update the following elements in the class '"+ ((Class) selectedElement).getName()+"': " +
						"\n     - a nested class '"+TestOracleElementsCreator.resultsClassName+"' containing additional variables and behavior"+ 
						"\n     - a component '"+TestOracleElementsCreator.resultsPropertyName+"' of type '"+TestOracleElementsCreator.resultsClassName+"'");
			if (go) {
				TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
				editingDomain.getCommandStack().execute(getTestOracleElementCreationCommand(editingDomain, (Class) selectedElement));
			}
		}
		return null;
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
