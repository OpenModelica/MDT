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
package org.openmodelica.modelicaml.profile.handlers;

import java.util.Collections;
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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.openmodelica.modelicaml.common.services.EditorServices;


public class CreateCommentHandler extends AbstractHandler {

	private EObject selectedElement = null;
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain();
		editingDomain.getCommandStack().execute(getCommand(editingDomain));
		return null;
	}

	protected Command getCommand(TransactionalEditingDomain editingDomain) {
		
		CompoundCommand cc = new CompoundCommand("Create commment");
		
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				Comment ct =null;
				
//### START: adapt it for a new command handler
				if (selectedElement instanceof Classifier) {
					//TODO: Should the name get a post-fix number (e.g. ModelicaModel1, ModelicaModel2, etc.)?
					ct = (Comment) ((org.eclipse.uml2.uml.Class)selectedElement).createOwnedComment();
				}
				else if (selectedElement instanceof Package) {
					//TODO: Should the name get a post-fix number (e.g. ModelicaModel1, ModelicaModel2, etc.)?
					ct = (Comment) ((org.eclipse.uml2.uml.Package)selectedElement).createOwnedComment();
				}
				
				// locate in Papyrus
				if ( ct != null) {
					EditorServices.locateInModelExplorer(ct, true);
				}
				
//### END: adapt it for a new command handler
				
			}
		};
		cc.append(command);
		return (cc.unwrap());
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

	public boolean isEnabled() {
		return true;
	}

	public boolean isVisible() {
		return true;
	}

}
