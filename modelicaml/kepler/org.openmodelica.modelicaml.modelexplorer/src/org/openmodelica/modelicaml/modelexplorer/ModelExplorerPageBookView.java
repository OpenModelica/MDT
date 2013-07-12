/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
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
