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
package org.openmodelica.modelicaml.view.valuebindings.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageManager;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForEObject;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.views.modelexplorer.handler.AbstractCommandHandler;

/**
 * Default handler for Delete command used in the ModelExplorer contextual menu.
 * 
 */
public class NOT_WORKING_DeleteCommandHandler extends AbstractCommandHandler implements IHandler {

	/**
	 * <pre>
	 * 
	 * Build the delete command for a set of EObject selected in the ModelExplorer.
	 * The delete command is given by the {@link IElementEditService} of selected 
	 * elements.
	 * 
	 * @return the composite deletion command for current selection
	 * 
	 * @TODO : Manage possible Diagrams listed in the selection
	 * 
	 * </pre>
	 */
	private Command buildCommand() {

		ICommand gmfCommand = null;

		// Parameters store the Request parameters of the previous request
		// if multiple elements are selected for deletion.
		Map parameters = new HashMap();

		for(EObject selectedEObject : getSelectedElements()) {

			if(selectedEObject == null) {
				continue;
			}

			IElementEditService provider = ElementEditServiceUtils.getCommandProvider(selectedEObject);
			if(provider == null) {
				continue;
			}

			// Retrieve delete command from the Element Edit service
			DestroyElementRequest request = new DestroyElementRequest(selectedEObject, false);
			// Add parameters (contains the list of previously dependents to be deleted
			// by previous commands.
			request.getParameters().putAll(parameters);

			ICommand deleteCommand = provider.getEditCommand(request);

			// Add current EObject destroy command to the global command
			gmfCommand = CompositeCommand.compose(gmfCommand, deleteCommand);

			// Store the new parameters for next delete command.
			parameters.clear();
			parameters.putAll(request.getParameters());
		}

		if(gmfCommand == null) {
			return UnexecutableCommand.INSTANCE;
		}

		Command emfCommand = new org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper(gmfCommand.reduce());
		return emfCommand;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.views.modelexplorer.handler.AbstractCommandHandler#getCommand()
	 * 
	 * @return current command (only built here when the stored command is null)
	 */
	@Override
	protected Command getCommand() {
		// Don't cache the command, as it is no more refreshed by isEnabled().
		return buildCommand();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean computeEnabled() {
		//we need to test if selected element is not a meta-class

		List<EObject> selectedElements = getSelectedElements();
		for(EObject current : selectedElements) {
			if(EMFHelper.isReadOnly(current)) {
				return false;
			}
			//the root of the model can't be deleted!
			if(current.eContainer() == null) {
				try {
					//Pages can be deleted even when they are root elements
					IPageManager pageManager = ServiceUtilsForEObject.getInstance().getIPageManager(current);
					if(pageManager.allPages().contains(current)) {
						return true;
					}
				} catch (ServiceException ex) {
					//Cannot retrieve the ServicesRegistry: ignore
				}
				return false;
			}
		}

		if(selectedElements.size() == 0) {
			return false;
		}
		// Don't compute the delete command to know if it is enabled,
		// it can be WAY too slow...
		return true;
	}

}
