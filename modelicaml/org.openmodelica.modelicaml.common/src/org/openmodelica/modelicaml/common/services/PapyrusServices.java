package org.openmodelica.modelicaml.common.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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


public class PapyrusServices {

	public static TransactionalEditingDomain getPapyrusEditingDomain(){
		ServicesRegistry serviceRegistry;
		TransactionalEditingDomain editingDomain = null;
		try {
			serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
			editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return editingDomain;
	}
	
	
	public static CommonViewer getModelExplorerView(){
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);

		ModelExplorerPageBookView modelExplorerPageBookView = null;
		if (view instanceof ModelExplorerPageBookView) {
			modelExplorerPageBookView = (ModelExplorerPageBookView)view;
		}
		CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();

		return modelExplorerView;
	}
	
	
	public static boolean isVisiblePapyrusModelExplorerView(){
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);

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
	
//	@Deprecated
//	/*
//	 * use locateInModelExplorer(Object object, boolean reselectFirst) instead
//	 */
//	public static void locate(Object object){
//		if (object instanceof EObject) {
//			
//			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);
//
//			ModelExplorerPageBookView modelExplorerPageBookView = null;
//			if (view instanceof ModelExplorerPageBookView) {
//				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
//			}
//			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
//			List<Object> items = new ArrayList<Object>();
//			items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
//			
//			// set focus
//			modelExplorerView.getControl().setFocus();
//
//			// set new selection
//			modelExplorerView.setSelection(new StructuredSelection(items), true);
//			
//     	}
//	}
	
	public static void locateInModelExplorer(Object object, boolean reselectFirst){
		if (object instanceof EObject) {
			
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);

			ModelExplorerPageBookView modelExplorerPageBookView = null;
			if (view instanceof ModelExplorerPageBookView) {
				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
			}
			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
			List<EObject> items = new ArrayList<EObject>();
//			items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
			items.add((EObject) object);
			
			ModelExplorerView.reveal(items, modelExplorerView);

			// set focus
			modelExplorerView.getControl().setFocus();
			
//			if (reselectFirst) {
//				// reset the selection so that the components tree can instantiate the selected class again
//				modelExplorerView.setSelection(new StructuredSelection(), true);
//			}
//			
//			// set new selection
//			modelExplorerView.setSelection(new StructuredSelection(items), true);
      	}
	}
	
	
//	@Deprecated
//	/*
//	 * use locateInModelExplorer(Object object, boolean reselectFirst) instead
//	 */
//	public static void locateWithReselection(Object object){
//		if (object instanceof EObject) {
//			
//			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);
//
//			ModelExplorerPageBookView modelExplorerPageBookView = null;
//			if (view instanceof ModelExplorerPageBookView) {
//				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
//			}
//			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
//			List<Object> items = new ArrayList<Object>();
//			items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
//
//			// set focus
//			modelExplorerView.getControl().setFocus();
//			
//			// reset the selection so that the components tree can instantiate the selected class again
//			modelExplorerView.setSelection(new StructuredSelection(), true);
//			
//			// set new selection
//			modelExplorerView.setSelection(new StructuredSelection(items), true);
//      	}
//	}
	
	
	public static void locateInComponentsTreeView(Object object){
		if (object instanceof String) {
			
			List<Object> elements = new ArrayList<Object>();
			// find treeObject with the dotPath
			HashSet<Object> treeObjects =  TreeUtls.findTreeItems((String) object, TreeUtls.componentsTreeRoot, new HashSet<Object>());
			elements.addAll(treeObjects);
			
			StructuredSelection selection = new StructuredSelection(elements);

			if (TreeUtls.componentsTreeViewer != null) {
				TreeUtls.componentsTreeViewer.setSelection(selection);	
			}
      	}
	}
}
