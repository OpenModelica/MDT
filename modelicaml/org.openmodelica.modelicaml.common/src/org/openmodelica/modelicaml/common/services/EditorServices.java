package org.openmodelica.modelicaml.common.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;


public class EditorServices {

	public static TransactionalEditingDomain getPapyrusEditingDomain(){
		ServicesRegistry serviceRegistry;
		TransactionalEditingDomain editingDomain = null;
		try {
			serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
			editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

		} catch (ServiceException e) {
			e.printStackTrace();
			
			ModelicaMLServices.notify("ModelicaML: Papyrus Editing Domain Access", 
					"Could not access the Papyrus Editing Domain." +
					"\nPlease make sure that your model is open in editor.", 
					2,
					2);
		}
		
		return editingDomain;
	}
	
	
	public static CommonViewer getModelExplorerView(){
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

		ModelExplorerPageBookView modelExplorerPageBookView = null;
		if (view instanceof ModelExplorerPageBookView) {
			modelExplorerPageBookView = (ModelExplorerPageBookView)view;
		}
		CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();

		return modelExplorerView;
	}
	
	
	public static boolean isVisiblePapyrusModelExplorerView(){
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

		ModelExplorerPageBookView modelExplorerPageBookView = null;
		if (view instanceof ModelExplorerPageBookView) {
			modelExplorerPageBookView = (ModelExplorerPageBookView)view;
			
			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
			if (modelExplorerView != null) {
				return modelExplorerView.getControl().isVisible();
			}
		}

		
		return false;
	}
	
	public static void locateInModelExplorer(Object object, boolean reselectFirst){
		if (object instanceof EObject) {
			
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

			ModelExplorerPageBookView modelExplorerPageBookView = null;
			if (view instanceof ModelExplorerPageBookView) {
				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
			}
			if (modelExplorerPageBookView.getAdapter(ModelExplorerView.class) != null) {
				CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
				List<EObject> items = new ArrayList<EObject>();
				items.add((EObject) object);
				
//				ModelExplorerView.reveal(items, modelExplorerView);

				// set focus
				modelExplorerView.getControl().setFocus();
				
				if (reselectFirst) {
					// reset the selection so that the components tree can instantiate the selected class again
					ModelExplorerView.selectReveal(new StructuredSelection(items), modelExplorerView);
				}
				
				// set new selection
				ModelExplorerView.reveal(items, modelExplorerView);
			}
			else {
				MessageDialog.openError(ModelicaMLServices.getShell(), "Locate in Model Explorer Error", "Could not access the Model Explorer. Please click on a diagram in order to active the Model Explorer and try again.");
			}
      	}
	}
	
	
	public static void locateInComponentsTreeView(Object dotPath){
		if (dotPath instanceof String) {
			
			List<Object> elements = new ArrayList<Object>();
			// find treeObject with the dotPath
			HashSet<Object> treeObjects =  TreeUtls.findTreeItems((String) dotPath, TreeUtls.componentsTreeRoot, new HashSet<Object>());
			elements.addAll(treeObjects);
			
			StructuredSelection selection = new StructuredSelection(elements);

			if (TreeUtls.componentsTreeViewer != null) {
				TreeUtls.componentsTreeViewer.setSelection(selection);	
			}
      	}
	}
}
