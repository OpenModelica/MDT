package org.openmodelica.modelicaml.validation.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.marker.IMarkerConfigurator;
import org.eclipse.emf.validation.marker.MarkerUtil;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.validation.Activator;

public class ModelicaMLValidationAction implements IHandler {
	IStatus status;
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// first check the user selection 
		IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();		
		Object selectedElement = null;
		if (selection != null) {
			selectedElement = ((IStructuredSelection) selection).getFirstElement();
		}
		
		// if user selection is empty then get the model from Papyrus
		EObject topLevelElement = null;
		if (! (ModelicaMLServices.adaptSelectedElement(selectedElement) instanceof Element) ) {
			UmlModel umlModel = UmlUtils.getUmlModel();
			try {
				topLevelElement = umlModel.lookupRoot();
			} catch (NotFoundException e) {
//				e.printStackTrace();
				MessageDialog.openError(new Shell(), "ModelicaML Validation Error", "Could not access the top-level element in Papyrus.");
			}
		}
		
		// select the object
		Object finalElementSelection = null;
		if (selectedElement != null) {
			finalElementSelection = selectedElement;
		}
		else {
			finalElementSelection = topLevelElement;
		}
		
		// object to be validated
		final Object objToBeValidated = finalElementSelection;
		
		Job job = new Job("ModelicaML Model Validation") {
			protected IStatus run(IProgressMonitor monitor) {
				IBatchValidator validator = (IBatchValidator)ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
				
				// include live constraints, also, in batch validation
				validator.setIncludeLiveConstraints(true);
				
				// track the validated resources for accurate problem-marker updates
				validator.setOption(IBatchValidator.OPTION_TRACK_RESOURCES, true);
				
				EObject adoptedElement = ModelicaMLServices.adaptSelectedElement(objToBeValidated);
				List<EObject> list = new ArrayList<EObject>();
				list.add(adoptedElement);
				
				// Initiate Batch Validation
				status = validator.validate(list);
				
				return Status.OK_STATUS;
			}

		};
		job.setUser(true);
		job.addJobChangeListener(new JobChangeAdapter() {
				public void done(IJobChangeEvent event) {
		            if (event.getResult().isOK()) {
			        		try {
			        			MarkerUtil.updateMarkers(status, "org.openmodelica.modelicaml.validation.problem", new IMarkerConfigurator() {
									
									@Override
									public void appendMarkerConfiguration(IMarker marker,
											IConstraintStatus status) throws CoreException {
										
										if(status.getTarget() instanceof NamedElement)
										{
											marker.setAttribute(IMarker.LOCATION, ((NamedElement) status.getTarget()).getName());
										}
										else
										{
											marker.setAttribute(IMarker.LOCATION, status.getTarget().toString());
										}

									}
								});
			        		} catch (CoreException e) {
			        			Activator.getDefault().getLog().log(e.getStatus());
			        		}
		            	}
		            }
		         });
		
		if (objToBeValidated != null) {
			job.schedule();
		}
		else {
			MessageDialog.openError(new Shell(), "ModelicaML Validation Error", "No elements could be selected for validation.");
		}
		
	return null;
	}


		
	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
