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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.relations.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.datacollection.ReferenceElementsCollector;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.relations.dialogs.FindReferencesDialog;


public class FindReferencesPopupHandler extends AbstractHandler{
	
	private ReferenceElementsCollector collector;	
	private NamedElement selectedElement;
	 
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// reset 
		collector = null;
		selectedElement = null;
		
		EObject eObject = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
		
		if (eObject != null) {
			if (eObject instanceof NamedElement) {
				selectedElement = (NamedElement) eObject;
			}
		}
		
		if (selectedElement == null) {
			MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Reference Search", "It is not possible to search for this element.");
			return null;
		}
		
		// get UML model data
		UmlModel umlModel = UmlUtils.getUmlModel();

		if (umlModel != null && selectedElement != null) {
			final boolean searchInLibs = MessageDialog.openQuestion(ModelicaMLServices.getShell(), "Find references", "Search also in included libraries?");
			
			try {
				final EObject root = umlModel.lookupRoot();
				if (root instanceof Element) {
					
					ProgressMonitorDialog progressMonitor = new ProgressMonitorDialog(ModelicaMLServices.getShell());
					
					try {
						progressMonitor.run(false, true, new IRunnableWithProgress() {
							
							@Override
							public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
								
								monitor.beginTask("Searching for "+ModelicaMLServices.getName(selectedElement)+" ...", 100);
								
								// collect data
								collector = new ReferenceElementsCollector(monitor);
								collector.setElemenToSearchFor(selectedElement);
								collector.setCollectElementsFromLibraries(searchInLibs);
								
								collector.collectElementsFromModel(root);
								
								monitor.done();
							}
						});
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML References Search Error", "Could not invoke the data collection for the references search.");
					} catch (InterruptedException e) {
						e.printStackTrace();
						MessageDialog.openInformation(ModelicaMLServices.getShell(), "ModelicaML References Search", "References search was interrupted.");		
					}

				
					if (collector != null) {
						
						if (collector.getElements() != null && collector.getElements().size() > 0) {
							// open dialog
							FindReferencesDialog dialog = new FindReferencesDialog(ModelicaMLServices.getShell(), collector.getElements());
							dialog.setElementToSearchFor((NamedElement) collector.getElemenToSearchFor());
							dialog.open();
						}
						else {
							MessageDialog.openInformation(ModelicaMLServices.getShell(), "References Search", "No references were found for "+ ModelicaMLServices.getQualifiedName(selectedElement));
						}
					}
				}
			} catch (NotFoundException e) {
				MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Model Access", "Could not access the ModelicaML model. Please open it in editor.");
			}
		}
		else {
			MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Model Access", "Could not access the ModelicaML model. Please open it in editor.");
		}
		
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	protected List<Object> getSelectedElements(ExecutionEvent event) throws ExecutionException {
		
		ISelection selection = getSelection(event);
		if (!( selection instanceof IStructuredSelection))
			return Collections.emptyList();
		return (List<Object>)((IStructuredSelection) selection).toList();
	}
	
	protected ISelection getSelection(ExecutionEvent event) throws ExecutionException {
		return HandlerUtil.getCurrentSelectionChecked(event);
	}

	@SuppressWarnings("unchecked")
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
	
		return null;
	}

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
