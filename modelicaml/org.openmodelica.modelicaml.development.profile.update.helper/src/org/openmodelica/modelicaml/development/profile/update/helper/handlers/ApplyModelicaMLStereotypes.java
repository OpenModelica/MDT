package org.openmodelica.modelicaml.development.profile.update.helper.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.development.profile.update.helper.dialogs.ElementSelectionDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ApplyModelicaMLStereotypes extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ApplyModelicaMLStereotypes() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		EObject selectedElement = null;
		
		if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
			selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
		}
		
		if (selectedElement instanceof Element) {
			if (ModelicaMLServices.isModelicaMLProfileApplied(selectedElement)) {
			
				ElementSelectionDialog dialog = new ElementSelectionDialog(new Shell(),
						SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectItem.gif"), 
						"Element Selection", 
						"Select an element in Model Explorer",
						(Element) selectedElement);
				dialog.open();
			}
			else {
				MessageDialog.openError(ModelicaMLServices.getShell(), "Profile Application Error", "The root model of the selected has no ModelicaML profile applied. Please apply the profile and try again.");
			}
		}
		return null;
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
	
	
	protected EObject adaptSelectedElement( Object selection ) {
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
