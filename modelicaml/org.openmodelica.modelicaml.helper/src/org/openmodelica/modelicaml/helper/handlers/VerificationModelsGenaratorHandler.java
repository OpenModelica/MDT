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

import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.services.ServicesRegistry;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.ServiceUtils;
import org.eclipse.papyrus.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.helper.impl.VerificationModelsGenerator;

public class VerificationModelsGenaratorHandler extends AbstractHandler {

	private EObject selectedElement = null;
	private HashSet<Element> sourceModels; 
	private Element targetPackage;
	private Element requirementsPackage; 
	private Element testScenariosPackage; 
	private Element valueMediatorsPackage;
	private VerificationModelsGenerator smg;
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
		
		if (selectedElement instanceof Class) {
			
			/* TODO: let the user select the target package, requirements package, test scenarios package and the value mediators package.
			 * preselect all to the root UML Model from which the user selected the models to generate the simulation models for.
			 */
			
			sourceModels = new HashSet<Element>();
			sourceModels.add((Class)selectedElement);
			
			// get the uml model that is open in Papyrus.
			UmlModel papyrusModel = UmlUtils.getUmlModel();
			if (papyrusModel != null ) {
				try {
					targetPackage = (Element) papyrusModel.lookupRoot();
					requirementsPackage = (Element) papyrusModel.lookupRoot();
					testScenariosPackage = (Element) papyrusModel.lookupRoot();
					valueMediatorsPackage = (Element) papyrusModel.lookupRoot();

//					targetPackage = ((Class)selectedElement).getModel();
//					requirementsPackage = ((Class)selectedElement).getModel(); 
//					testScenariosPackage = ((Class)selectedElement).getModel(); 
//					valueMediatorsPackage = ((Class)selectedElement).getModel();
					
				} catch (NotFoundException e) {
					e.printStackTrace();
					MessageDialog.openError(new Shell(), "Packages Selection", "Cannot access the root model in Papyrus. Please try it again.");
				}
			}
			
			boolean allPackagesAreSet = targetPackage != null && requirementsPackage != null && testScenariosPackage != null && valueMediatorsPackage != null;
			
			if (allPackagesAreSet) {
				boolean go = MessageDialog.openQuestion(new Shell(), "Please confirm ...", "This helper will create a package " +
						"that will contain miltiple verification models each including one scenario and all requirements, "+ 
						"that can be tested using this scenario, and the selected system model." +
						"\n\nNote, only verificationi models that have scenarios and requirements with appropriate value bindings will be collected." +
						"\n\n" +
						
						"   - Selected system model: '" + ((Class)selectedElement).getName() + "'\n" + 
						"   - Package to store generated Ver. Models in: '" + ((NamedElement)targetPackage).getName() + "'\n" + 
						"   - Package for Requirements search: '" + ((NamedElement)requirementsPackage).getName() + "'\n" +
						"   - Package for Scenarios search: '" + ((NamedElement)testScenariosPackage).getName() + "'\n" +
						"   - Package for Value Mediators search: '" + ((NamedElement)valueMediatorsPackage).getName() + "'\n"
						);
				
				if (go && allPackagesAreSet) {
					try {
						ServicesRegistry  serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
						TransactionalEditingDomain  editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
						
						smg = new VerificationModelsGenerator(
								sourceModels, 
								targetPackage,  
								requirementsPackage, 
								testScenariosPackage, 
								valueMediatorsPackage);
						
						if (!smg.isTestSimulationModelGenerationCanceled()) {
							// execute 
							editingDomain.getCommandStack().execute(getCommand(editingDomain));
						}
						if (!smg.isTestSimulationModelGenerationCanceled()) {
							// show log
							String msg = "Generation of Verification Models for '" + ((NamedElement)selectedElement).getName() + "'\n" +
										 "Number of created models: " + smg.getUserSelectedTestScenarios().size() + "\n\n";

							DialogMessage dialog = new DialogMessage(new Shell(), "Verification Models Generation Log", 
									"Data collecation and models generation log entries:", msg + smg.getLog().trim());
							dialog.open();
						}

					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				MessageDialog.openError(new Shell(), "Packages Selection for Verification Models Generation",
						"Cannot access the root model in Papyrus. Please try it again.");
			}
		}
		return null;
	}

	protected Command getCommand(TransactionalEditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand("Generating simulation models for '" + ((Class)selectedElement).getQualifiedName() + "'");
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				try {
					smg.generate();
					new ProgressMonitorDialog(new Shell()).run(true, true, smg);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					MessageDialog.openError(new Shell(), "Simulation Models Generation Process Error", "It was not possible to invoce the generation of simulation models operation.");
				} catch (InterruptedException e) {
					e.printStackTrace();
					MessageDialog.openError(new Shell(), "Simulation Models Generation Process Abort", "The generation of simulation models was canceled.");
				}
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
