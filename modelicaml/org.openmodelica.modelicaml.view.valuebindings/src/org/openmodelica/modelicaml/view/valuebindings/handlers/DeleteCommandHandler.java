package org.openmodelica.modelicaml.view.valuebindings.handlers;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.service.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.service.edit.service.IElementEditService;
import org.eclipse.ui.PlatformUI;

/**
 * Default handler for Delete command used in the ModelExplorer contextual menu.
 * 
 */
public class DeleteCommandHandler extends AbstractHandler implements IHandler {

	/** Current deleteCommand for selection (updated in {@link DeleteCommandHandler#isEnabled()}) */
	private Command deleteCommand;

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
			ICommand deleteCommand = provider.getEditCommand(request);

			// Add current EObject destroy command to the global command
			gmfCommand = CompositeCommand.compose(gmfCommand, deleteCommand);
		}

		if(gmfCommand == null) {
			return UnexecutableCommand.INSTANCE;
		}

		Command emfCommand = new GMFtoEMFCommandWrapper(gmfCommand.reduce());
		return emfCommand;
	}

	/**
	 * 
//	 * @see org.eclipse.papyrus.modelexplorer.handler.AbstractCommandHandler#getCommand()
	 * 
	 * @return current command (only built here when the stored command is null)
	 */
	protected Command getCommand() {

		// Build the delete command in case it is not initialized.
		if(deleteCommand == null) {
			deleteCommand = buildCommand();
		}

		return deleteCommand;
	}

	/**
	 * <pre>
	 * 
	 * Calculates whether the handler is enabled or not. The command is re-built on the
	 * basis of current selection before any attempt to test if current command can be executed.
	 * 
//	 * @see org.eclipse.papyrus.modelexplorer.handler.AbstractCommandHandler#isEnabled()
	 * 
	 * @return true if the command can be executed
	 * 
	 * </pre>
	 */
	public boolean isEnabled() {

		// Prepare and store the delete command only when trying to find out 
		// whether the command is enabled or not.
		// This assumes the isEnabled() method is called each time the contextual menu
		// opens. 
		deleteCommand = buildCommand();

		return super.isEnabled();
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			ServiceUtilsForActionHandlers util = new ServiceUtilsForActionHandlers();
			util.getTransactionalEditingDomain().getCommandStack().execute(getCommand());
		} catch (ServiceException e) {
//			Activator.log.error("Unexpected error while executing command.", e); //$NON-NLS-1$
		}

		return null;
	}
	
	
	protected List<EObject> getSelectedElements() {

		List<EObject> selectedEObjects = new ArrayList<EObject>();

		// Parse current selection
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			for(Object current : structuredSelection.toArray()) {
				// Adapt current selection to EObject
				if(current instanceof IAdaptable) {
					selectedEObjects.add((EObject)((IAdaptable)current).getAdapter(EObject.class));
				}
			}
		} else { // Not a IStructuredSelection
			if(selection != null) {
				// Adapt current selection to EObject
				if(selection instanceof IAdaptable) {
					selectedEObjects.add((EObject)((IAdaptable)selection).getAdapter(EObject.class));
				}
			}
		}

		return selectedEObjects;
	}
	
	protected EObject getSelectedElement() {
		EObject eObject = null;
		Object selection = null;

		// Get current selection
		selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();

		// Get first element if the selection is an IStructuredSelection
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			selection = structuredSelection.getFirstElement();
		}

		// Treat non-null selected object (try to adapt and return EObject)
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