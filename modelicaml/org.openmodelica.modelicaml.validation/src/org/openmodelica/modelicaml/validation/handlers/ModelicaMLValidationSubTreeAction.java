package org.openmodelica.modelicaml.validation.handlers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;

public class ModelicaMLValidationSubTreeAction extends
		ModelicaMLValidationAction {
	
	@Override
	protected EObject getElementToValidate() {

		Object selectedElement = null;
		// get the user selection 
		IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();		
		if (selection != null) {
			selectedElement = ((IStructuredSelection) selection).getFirstElement();
		}
		
		EObject castedElement = ModelicaMLServices.adaptSelectedElement(selectedElement);
		if ( castedElement instanceof Element ) {
			return castedElement;
		}
		return null;
	}
}
