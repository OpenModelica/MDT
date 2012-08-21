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
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
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
			try {
				final EObject root = umlModel.lookupRoot();
				if (root instanceof Element) {
					
					ProgressMonitorDialog progressMonitor = new ProgressMonitorDialog(ModelicaMLServices.getShell());
					
					try {
						progressMonitor.run(false, true, new IRunnableWithProgress() {
							
							@Override
							public void run(IProgressMonitor monitor) throws InvocationTargetException,
									InterruptedException {

								monitor.beginTask("Searching for "+ModelicaMLServices.getName(selectedElement)+" ...", 100);
								
								// collect data
								collector = new ReferenceElementsCollector(monitor);
								collector.setElemenToSearchFor(selectedElement);
								
								collector.collectElementsFromModel(root);
								
								monitor.done();
							}
						});
					} catch (InvocationTargetException e) {
						e.printStackTrace();
						MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Reference Search Error", "Could not invoce the data collection for the references search. ");
					} catch (InterruptedException e) {
						e.printStackTrace();
						MessageDialog.openInformation(ModelicaMLServices.getShell(), "ModelicaML Collection", "Data collection was interrupted.");		}

				
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
