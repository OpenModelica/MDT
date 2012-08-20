package org.openmodelica.modelicaml.relations.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.openmodelica.modelicaml.common.datacollection.RelationsCollector;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.relations.dialogs.SelectInvalidRelationsToDeleteDialog;


public class FindInvalidRelationsToolbarHandler extends AbstractHandler{
	
	private RelationsCollector collector;	
	 
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get UML model data
		UmlModel umlModel = UmlUtils.getUmlModel();

		if (umlModel != null) {
			try {
				final EObject root = umlModel.lookupRoot();
				if (root instanceof Element) {
					
					ProgressMonitorDialog progressMonitor = new ProgressMonitorDialog(ModelicaMLServices.getShell());
					
					try {
						progressMonitor.run(false, true, new IRunnableWithProgress() {
							
							@Override
							public void run(IProgressMonitor monitor) throws InvocationTargetException,
									InterruptedException {

								monitor.beginTask("Collecting ModelicaML Relations Data ...", 100);
								
								// collect data
								collector = new RelationsCollector();
								collector.collectElementsFromModel(root);
							}
						});
					} catch (InvocationTargetException e) {
						MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Relations Data Collection Error", "Could not invoce the data collection for ModelicaML Relations view. ");
					} catch (InterruptedException e) {
						MessageDialog.openInformation(ModelicaMLServices.getShell(), "ModelicaML Relations Data Collection", "Data collection was interrupted.");		}

				
					if (collector != null) {
						HashSet<Element> invalidRelations = analyze(collector);
						if (invalidRelations.size() > 0) {
							
							// open dialog
							SelectInvalidRelationsToDeleteDialog dialog = new SelectInvalidRelationsToDeleteDialog(ModelicaMLServices.getShell(), invalidRelations);
							dialog.open();
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

	
	private HashSet<Element> analyze(RelationsCollector collector){
		
		HashSet<Element> invalidRelations = new HashSet<Element>();
		for (Element relation : collector.getElements()) {
			Element source = getSource(relation);
			Element target = getTarget(relation);
			
			if (source == null || target == null) {
				invalidRelations.add(relation);
			}
		}
		
		return invalidRelations;
		
	}
	
	
	private Element getSource(Element relation){
		if (relation instanceof Dependency) {
			if (((Dependency)relation).getClients().size() > 0 ) {
				// NOTE: we always take the first because we do not use Dependency Sets in ModelicaML
				return ((Dependency)relation).getClients().get(0);
			}
		}
		else if (relation instanceof Generalization) {
			if (((Generalization)relation).getSources().size() > 0 ) {
				return ((Generalization)relation).getSources().get(0);
			}
		}
		return null;
	}
	
	private Element getTarget(Element relation){
		if (relation instanceof Dependency) {
			if (((Dependency)relation).getTargets().size() > 0 ) {
				// NOTE: we take the first
				return ((Dependency)relation).getTargets().get(0);
			}
		}
		else if (relation instanceof Generalization) {
			if (((Generalization)relation).getTargets().size() > 0 ) {
				// NOTE: we take the first
				return ((Generalization)relation).getTargets().get(0);
			}
		}
		return null;
	}
	
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//	
//	@Override
//	public boolean isHandled() {
//		return true;
//	}
	
}
