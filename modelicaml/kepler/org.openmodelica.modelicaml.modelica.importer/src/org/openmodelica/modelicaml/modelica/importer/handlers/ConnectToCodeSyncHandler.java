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
package org.openmodelica.modelicaml.modelica.importer.handlers;

import java.util.Iterator;
import java.util.List;

import javax.lang.model.type.PrimitiveType;

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
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.EditorServices;

public class ConnectToCodeSyncHandler extends AbstractHandler {
	private EObject selectedElement = null;



	public Object execute(ExecutionEvent event) throws ExecutionException {
		String msg = "This helper will now apply the <<ModelicaModelProxy>> stereotype to the selected elements and all its nested elements. " +
				"This stereotype is used to identify elements that should be synchronized with the code in '"+Constants.folderName_code_sync+"' folder." +
				"\n\nYou can disconnect this sub-tree from code synchronization again at any time." +
				"\n\nDo you want to proceed?";
		
		boolean go = MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Connect To Code Synchronization Inoformation", msg);
		if (go) {
			selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
			if (	selectedElement instanceof Package || selectedElement instanceof Model
					|| ((selectedElement instanceof Class && !(selectedElement instanceof Behavior)))
					|| selectedElement instanceof FunctionBehavior
					|| selectedElement instanceof Enumeration
					|| selectedElement instanceof PrimitiveType) {
				
				applyProxyStereotype((NamedElement) selectedElement);
			}
			else {
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Connect to Code Synchronization Error", "Not a valid selection.");
			}
		}
		return null;
	}

	private void applyProxyStereotype(NamedElement proxy){
//		ServicesRegistry serviceRegistry;
		TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain();
		// execute 
		editingDomain.getCommandStack().execute(getCommand(editingDomain, proxy));
	}
	
	
	protected Command getCommand(TransactionalEditingDomain editingDomain, final NamedElement proxy) {
		CompoundCommand cc = new CompoundCommand("Applying the proxy stereotype");
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// apply the stereotype for the selected element
				Stereotype sProxy = proxy.getApplicableStereotype(Constants.stereotypeQName_ModelicaModelProxy);
				if (sProxy != null) {
					proxy.applyStereotype(sProxy);
				}
				
				// get all contents, iterate and apply the proxy stereotype.
				Iterator<EObject> i = proxy.eAllContents();

				while (i.hasNext()) {
					EObject object = i.next() ;
					if (	object instanceof Package || object instanceof Model
							|| ((object instanceof Class && !(object instanceof Behavior)))
							|| object instanceof FunctionBehavior
							|| object instanceof Enumeration
							|| object instanceof PrimitiveType) {
						
						Element nestedProxy = (Element)object;
						Stereotype s = nestedProxy.getApplicableStereotype(Constants.stereotypeQName_ModelicaModelProxy);

						if (s != null) {
							nestedProxy.applyStereotype(s);
						}
					}
				}
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	
	
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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
	
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
		return null;
	}
	

}
