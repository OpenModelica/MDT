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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.helper.dialogs.VeMGenerationOptionsDialog;
import org.openmodelica.modelicaml.helper.generators.GeneratorVeMScenariosBased;

public class VeMGenaratorPopupHandler extends AbstractHandler {

	protected Element rootPackage; 
	
	protected EObject selectedElement = null;

	private Element targetPackage;
	private Element requirementsPackage; 
	private Element testScenariosPackage; 
	private Element valueMediatorsPackage;
	
	// default requirements selection options
	boolean includeRequirementsWithPositiveRelations = true;
	boolean includeRequirementsWithNegativeRelations = false;
	boolean includeRequirementsWitnUnknownRelations = false;
	
	protected int mode = Constants.MODE_VEM_GENERATION;
	
	// indicates if there was at at least one model for which bindings could not be generated 
//	private boolean bindingErrorsDetected = false;
	private HashSet<Element> modelsWithBindingErrors;
	
	// models generator
	protected GeneratorVeMScenariosBased smg;
	
	
	protected void setSelectedSystemModel(){
		selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
	}
	
	public EObject getSelectedSystemModel() {
		return selectedElement;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		/*
		 * If action was performed on an element from model explorer 
		 * then get the selection otherwise set it to null.
		 */
		setSelectedSystemModel();
		
		// get the uml model that is open in Papyrus.
		UmlModel papyrusModel = UmlUtils.getUmlModel();
		if (papyrusModel != null ) {
			try {

				// get the top levelPapyrus UML::Model
				rootPackage = (Element) papyrusModel.lookupRoot();
				
				// preset the packages to search in
				targetPackage = rootPackage;
				requirementsPackage = rootPackage;
				testScenariosPackage = rootPackage;
				valueMediatorsPackage = rootPackage;
		
			} catch (NotFoundException e) {
				e.printStackTrace();
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Packages Selection", "Cannot access the root model in editor. " +
						"Please open the models in editor, " +
						"click on a diagram and then try again to select the element in the Model Explorer.");
			}
	
			boolean allPackagesAreSet = targetPackage != null && requirementsPackage != null && testScenariosPackage != null && valueMediatorsPackage != null;
			
			if (allPackagesAreSet) {
				
				// Set requirements selection options via user dialog
				openDialog();
				
			}
			else {
				MessageDialog.openError(getShell(), "Packages Selection for Models Generation",
						"Please open the models in editor, " +
						"click on a diagram and then try again to select the element in the Model Explorer.");

			}
		}
		
		return null;
	}

	
	
	/*
	 * For further post-processing
	 */
	protected void proceed(){
	}
	
	
	/*
	 * Options dialog and tracer
	 */
	
	private VeMGenerationOptionsDialog dialog;
	private DisposeListener listener = new DisposeListener() {
		
		@Override
		public void widgetDisposed(DisposeEvent e) {
			// set the indication if there was an error during bindings generation
			smg = dialog.getSmg();
			setModelsWithBindingErrors(smg.getModelsWithBindingErrors());
			
			// proceed after asking user
			proceed();
		}
	};
	
	protected void openDialog(){
		
		// Set requirements selection options via user dialog
		dialog = new VeMGenerationOptionsDialog(
						new Shell(), 
						rootPackage,
						(Element) selectedElement, 
						targetPackage, 
						requirementsPackage, 
						testScenariosPackage, 
						valueMediatorsPackage,
						getMode());
		

		// add a listener to catch the result of bindings generation
		dialog.getDialogShell().addDisposeListener(listener);
		dialog.open();
	}
	
	
	@Override
	public void dispose() {
		dialog.getShell().removeDisposeListener(listener);
		super.dispose();
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
	@SuppressWarnings("unchecked")
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
	
	
	
	protected Shell getShell(){
		Shell shell = null;
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			if (win != null) {
				shell = win.getShell();
			}
		}
		if (shell == null) {
			shell = new Shell();
		}
		return shell;
	}
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public HashSet<Element> getModelsWithBindingErrors() {
		return modelsWithBindingErrors;
	}

	public void setModelsWithBindingErrors(HashSet<Element> modelsWithBindingErrors) {
		this.modelsWithBindingErrors = modelsWithBindingErrors;
	}

//	public boolean isBindingErrorsDetected() {
//		return bindingErrorsDetected;
//	}
//
//	public void setBindingErrorsDetected(boolean bindingErrorsDetected) {
//		this.bindingErrorsDetected = bindingErrorsDetected;
//	}


}
