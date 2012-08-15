package org.openmodelica.modelicaml.modelexplorer;

import org.eclipse.papyrus.views.modelexplorer.core.ui.pagebookview.ViewPartPage;
import org.eclipse.ui.IWorkbenchPart;
import org.openmodelica.modelicaml.common.constants.Constants;

public class ModelExplorerPageBookView extends org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView {

	/** ID of the view, as given in the plugin.xml file */
	public static final String VIEW_ID = Constants.VIEW_MODELEXPLORER; //$NON-NLS-1$

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PageRec doCreatePage(IWorkbenchPart part) {

		// part is of type IMultiDiagramEditor (because of isImportant() )

		ViewPartPage page = new ModelExplorerPage();

		// Init the page, and so the View
		initPage(page, part);
		page.createControl(getPageBook());
		return new PageRec(part, page);
	}
	
	
	
	/*
	 * TODO: goto marker does not work. The operation
	 * below is never called because the IDE will find the Papyrus Editor and 
	 * call its goto marker operation, which in turn will call it 
	 * on the Papyrus Model Explorer.
	 */
	
	
//	/**
//	 * {@inheritDoc}
//	 */
//	public void gotoMarker(IMarker marker) {
//		String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
//		if(uriAttribute != null) {
//			URI uri = URI.createURI(uriAttribute);
//			IViewPart viewPart = getActiveView();
//			if(viewPart instanceof ModelExplorerView) {
//				ModelExplorerView modelExplorerView = (ModelExplorerView)viewPart;
//				EditingDomain domain = modelExplorerView.getEditingDomain();
//				EObject eObject = domain.getResourceSet().getEObject(uri, false);
//				if(eObject != null) {
//					CommonViewer treeViewer = ((ModelExplorerView)viewPart).getCommonViewer();
//					// The common viewer is in fact a tree viewer
//					// bug enhancement: use function in ModelExplorerView instead of findElementForEObject
//					ModelExplorerView.reveal(Lists.newArrayList(eObject), treeViewer);
//				}
//			}
//		}
//	}
}
