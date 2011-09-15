package org.openmodelica.modelicaml.common.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;

public class PapyrusServices {

	public static void locate(Object object){
		if (object instanceof EObject) {
			
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.papyrus.modelexplorer.modelexplorer");

			ModelExplorerPageBookView modelExplorerPageBookView = null;
			if (view instanceof ModelExplorerPageBookView) {
				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
			}
			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
			List<Object> items = new ArrayList<Object>();
			items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
			modelExplorerView.setSelection(new StructuredSelection(items), true);
      	}
	}
	
	
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
