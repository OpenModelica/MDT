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
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.helper.datacollection.VerificationScenariosCollector;
import org.openmodelica.modelicaml.helper.dialogs.InstantiateVerificationScenarioDialog;
import org.openmodelica.modelicaml.helper.generators.InstantiatorScenarios;

// TODO: Auto-generated Javadoc
/**
 * The Class InstantiateRequirementsHandler.
 */
public class InstantiateTestScenarioHandler extends AbstractHandler {

	/** The selected element. */
	private Class containingClass = null;
	HashSet<Element> selectedRequirements = null;
//	HashSet<Element> instantiatedTestScenarios = null;
	public EList<Property> instantiatedElements = null;
	
	public void setData(Class containingClass, HashSet<Element> selectedRequirements){
		this.containingClass = containingClass;
		this.selectedRequirements = selectedRequirements;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (containingClass == null) {
		
			EObject selectedObject = adaptSelectedElement(getCurrentSelections().get(0));
			if (selectedObject instanceof Class) {
				
				containingClass = (Class) selectedObject;
				
				// this is the case where test cases shall be instantiated not based on the selected requirements.
				this.selectedRequirements = null; 
			}
		}
		
		if ( containingClass != null ) {
			
			// collect test cases
			VerificationScenariosCollector tsc = new VerificationScenariosCollector(containingClass.getModel());
			tsc.setSelectedReq(selectedRequirements);
			tsc.collectScenariosFromModel(true);
			
			// if there are test scenarios at all -> open dialog
			if (tsc.getAllScenarios().size() > 0 ) {
				
				Boolean calceled = false;
				
				// if there are selected requirements and there are no matched test scenarios for them -> ask user
				if ( tsc.getSelectedReq()!= null &&  tsc.getSelectedReq().size() > 0 && tsc.getMatchedScenarios().size() == 0 ) {
					// stop or continue based on user decision.
					calceled = !MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Test scenarios search", "No test scenarios were found " +
							"in '" + containingClass.getModel().getName()+ "' that can be used to test all the selected requirements." +
									"\n\nDo you want to see other test scenarios that exist in the model?" );
				}
				
				if ( !calceled ) { // if user want to see it event if there are no matched test scenarios. 

					// collect not covered requirements
					HashSet<Element> allRequirements = tsc.getAllRequirements();
					// remove covered requirements
					for (Element req: tsc.getReqToScenarios().keySet()) {
						allRequirements.remove(req);
					}
					HashSet<Element> notCoveredRequirements = new HashSet<Element>();
					notCoveredRequirements.addAll(allRequirements);
					
					InstantiateVerificationScenarioDialog dialog = new InstantiateVerificationScenarioDialog(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
							containingClass, 
							tsc.getAllScenarios(), 
							tsc.getMatchedScenarios(),
							tsc.getLinkedScenarios(),
							tsc.getScenarioToReq(),
							tsc.getReqToScenarios(),
							notCoveredRequirements,
							selectedRequirements);
					
					dialog.open();
					
					if (dialog.getReturnCode() == 0) { // if OK button was pressed
						HashSet<Element> selectedTS = dialog.getTsToBeInstantiated();
						TransactionalEditingDomain editingDomain = PapyrusServices.getPapyrusEditingDomain();
						
						// instantiate test scenarios
						editingDomain.getCommandStack().execute(getCommand(editingDomain, selectedTS, containingClass));
					}
				}
				
			}
			else {
				MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Test scenarios search", "No test scenarios were found in '" + containingClass.getModel().getName()+ "'" );
			}
		}
		return null;
	}

	protected Command getCommand(TransactionalEditingDomain editingDomain, final HashSet<Element> selectedTS, final Class containingClass) {
		CompoundCommand cc = new CompoundCommand("Instantiate Test Scenarios in " + containingClass.getName() );
		
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				InstantiatorScenarios ri = new InstantiatorScenarios ();
				instantiatedElements = ri.instantiateTestScenarios(containingClass, selectedTS);
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
