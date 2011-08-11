//package org.openmodelica.modelicaml.view.valuebindings.sections;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.core.runtime.jobs.Job;
//import org.eclipse.emf.common.notify.Notification;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
//import org.eclipse.jface.viewers.StructuredSelection;
//import org.eclipse.papyrus.core.listenerservice.IPapyrusListener;
//import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
//import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
//import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
//import org.eclipse.papyrus.resource.uml.UmlUtils;
//import org.eclipse.ui.IViewPart;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.navigator.CommonViewer;
//import org.eclipse.uml2.uml.resource.UMLResource;
//import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
//import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;
//import org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView;
//
//public class PapyrusStoreListener implements IPapyrusListener {
//	private static Job job;
//	/** The uml model resource. */
//	private static UMLResource umlModelResource = null;
//	/** The uml model. */
//	private ExtendedUmlModel umlModel = null;
//
//	private IViewPart valueBindingsView;
//	private Object viewerSelection;
//	
//	public PapyrusStoreListener(){
//		job = new Job("Update value binding view.") {
//			
//			@Override
//			protected IStatus run(IProgressMonitor monitor) {
//				if (valueBindingsView != null) {
//					updateView((ValueBindingsView) valueBindingsView);
//				}
//				return Status.OK_STATUS;
//			}
//		};
//	}
//	
//	@Override
//	public void notifyChanged(Notification notification) {
//		if (notification.getNotifier() instanceof UMLResource) { // TODO: obviously an indicator for a save action
//			
//			umlModelResource = (UMLResource) notification.getNotifier();
//			umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
//			
//			if (UmlUtils.getUmlModel().getResourceURI().equals( ((UMLResource) notification.getNotifier()).getURI() ) ) {
//				final IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView");
//				if (view instanceof ValueBindingsView) {
//					valueBindingsView = view;
//					
//					ISelection selection = ((ValueBindingsView)valueBindingsView).getViewer().getSelection();
//					viewerSelection = ((IStructuredSelection)selection).getFirstElement();
//					
//					if (job.getState() != Job.NONE ) {
//						job.cancel();
//					}
//					job.setPriority(Job.BUILD);
//					//job.setUser(true);
//					job.schedule(1000); // wait a second in order to let Papyrus time to save the model.
//					
//				}
//			}
//		}
//	}
//	
//	private void updateView( ValueBindingsView valueBindingsView){
////		valueBindingsView.actionReload.run();
//		
////		ISelection selection = valueBindingsView.getViewer().getSelection();
////		Object obj = ((IStructuredSelection)selection).getFirstElement();
//		
////		TreeObject[] children = valueBindingsView.getTreeRoot().getChildren();
////		for (int i = 0; i < children.length; i++) {
////			valueBindingsView.getTreeRoot().removeChild(children[i]);
////		}
////		
////		if (valueBindingsView.actionInstantiatedClassMode.isChecked()) {
////			valueBindingsView.getTreeBuilder().buildTreeFromInstantiatedClass(valueBindingsView.getTreeRoot(), org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot);
////		}
////		else {
////			valueBindingsView.getTreeBuilder().buildTreeFromUmlModel(valueBindingsView.getTreeRoot());	
////		}
//
////		valueBindingsView.getViewer().setInput(valueBindingsView.getViewSite());
//
//		// select in view
////		TreeUtls.selectInView(viewerSelection, valueBindingsView.getTreeRoot(), valueBindingsView.getViewer());
////		valueBindingsView.getViewer().expandToLevel(ValueBindingsView.DEFAULT_EXPAND_LEVEL);
//	}
//
//}
