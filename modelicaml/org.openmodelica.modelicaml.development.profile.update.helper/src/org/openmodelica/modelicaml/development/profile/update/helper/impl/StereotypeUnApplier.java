package org.openmodelica.modelicaml.development.profile.update.helper.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;

public class StereotypeUnApplier {
	
	private HashSet<Element> elements = new HashSet<Element>();
	private int numberOfHandledElements = 0;
	private String mMessage = "Un-Applying all ModelicaML stereotypes ...";
	
	private ServicesRegistry serviceRegistry = null;
	private TransactionalEditingDomain editingDomain = null;
	private ProgressMonitorDialog progressMonitor;

	private void clearLists(){
		elements.clear();
	}
	
	public void unApplyStereotypes(final EObject root){
		
		if (root instanceof Element) {
			
			//clear all lists before the new run
			clearLists();
			
			// open dialog
			progressMonitor = new ProgressMonitorDialog(ModelicaMLServices.getShell());
			
			try {
				progressMonitor.run(false, true, new IRunnableWithProgress() {
					
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
						
						monitor.setTaskName("Collecting data ...");
						
						if (root instanceof Element) {
							
							// collect
							collectElements((Element) root);
							
							monitor.beginTask(mMessage, elements.size());
							
							// apply stereotypes
							try {
								unApplyStereotypesCommand(monitor);
								
							} catch (ServiceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else {
							MessageDialog.openError(new Shell(), "Selection Error", "The selected element is not a UML element.");
						}
						monitor.done();

						// report
						MessageDialog.openInformation(ModelicaMLServices.getShell(), 
								"Result for Removing ModelicaML Stereotypes", 
								"For " + numberOfHandledElements + " of " + elements.size() + " elements found stereotypes were removed.");
					}
				});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Stereotypes Update", "Could not invoke data collection.");
			} catch (InterruptedException e) {
				e.printStackTrace();
				MessageDialog.openInformation(ModelicaMLServices.getShell(), "ModelicaML Stereotypes Update", "Service was interrupted.");		
			}
		}
	}
	
	private void collectElements(Element root){
		
		elements.add(root);
		
		// collect
		Iterator<EObject> i = root.eAllContents();
		
		progressMonitor.getProgressMonitor().setTaskName("Collecting elements ...");
		// collect before modifying
		while (i.hasNext()) {
			EObject object = i.next() ;
			if (object instanceof Element && ModelicaMLServices.isModelicaMLStereotypeApplied((Element) object)) {
				setMonitorText(object, "Found ");
				elements.add((Element) object);
				
			}
		}
	}
	
	private void setMonitorText(EObject eObject, String string){
		if (eObject instanceof NamedElement) {
			progressMonitor.getProgressMonitor().setTaskName(string + eObject.eClass().getName() + " '" + ((NamedElement)eObject).getQualifiedName() + "'");
		}
	}
	
	
	
	private void unApplyStereotypesCommand(final IProgressMonitor monitor) throws ServiceException {
		
		serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
		editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
		
		CompoundCommand cc = new CompoundCommand("Unapplying ModelicaML Stereotypes");
		Command command = new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				int i = 0;
				int size = elements.size();
				
				for (Element element : elements) {
					if (!monitor.isCanceled()) {
						
						List<Stereotype> sl = element.getAppliedStereotypes();
						for (Stereotype stereotype : sl) {
							if (stereotype.getQualifiedName().startsWith("ModelicaML::")) {
								element.unapplyStereotype(stereotype);
								if (element instanceof NamedElement) {
									monitor.setTaskName(i + " of " + size + ": Removing stereotypes from " + ((NamedElement)element).getQualifiedName());
								}
							}
						}
						
						i ++;
						monitor.worked(i);
						numberOfHandledElements ++;
					}
				}
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);
	}
	
}
