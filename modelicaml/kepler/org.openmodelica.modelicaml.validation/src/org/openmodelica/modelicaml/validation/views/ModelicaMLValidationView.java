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
package org.openmodelica.modelicaml.validation.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.MarkerSupportView;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.modelexplorer.ModelExplorerPage;
import org.openmodelica.modelicaml.modelexplorer.ModelExplorerPageBookView;
import org.openmodelica.modelicaml.modelexplorer.ModelExplorerView;
import org.openmodelica.modelicaml.modelica.importer.views.ModelicaOMCCodeViewer;
import org.openmodelica.modelicaml.view.componentstree.views.ComponentsTree;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;
import org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView;

/**
 * The Class ModelicaMLValidationView.
 */
public class ModelicaMLValidationView extends MarkerSupportView {
    
    /**
	 * Instantiates a new modelicaml validation view.
	 */
    public ModelicaMLValidationView() {
        super("org.openmodelica.modelicaml.markerContentGenerator");
    }
    
    @SuppressWarnings("restriction")
	@Override
    public void createPartControl(Composite parent) {

    	super.createPartControl(parent);
    	
    	Action locateAction = new Action("locateAction") {
    		@Override
    		public void run() {
    			openMarkersInEditor();
			}
		}; 
		
		locateAction.setText("Open selected markers in views");
		locateAction.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png")));
		getViewSite().getActionBars().getToolBarManager().add(new Separator());
		getViewSite().getActionBars().getToolBarManager().add(locateAction);

		
		Action deleteAllAction = new Action() {
    		@Override
    		public void run() {
    			// TODO Auto-generated method stub
    			super.run();
    			try {
					ResourcesPlugin.getWorkspace().deleteMarkers(getSelectedMarkers());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
		}; 
		
		deleteAllAction.setText("Delete all selected markers");
		deleteAllAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(org.eclipse.ui.internal.SharedImages.IMG_ELCL_REMOVEALL));
		getViewSite().getActionBars().getToolBarManager().add(deleteAllAction);
    }
   
    
    public void openMarkersInEditor(){
    	
    	IMarker[] markers = getMarkersSelectedInView();
    	
		for (IMarker iMarker : markers) {
			String uriAttribute = iMarker.getAttribute(EValidator.URI_ATTRIBUTE, null);
			if(uriAttribute != null) {
				URI uri = URI.createURI(uriAttribute);
				
				IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);
				EObject eObject = null;
				if(viewPart instanceof ModelExplorerPageBookView) {
					org.eclipse.ui.part.IPage page =  ((ModelExplorerPageBookView)viewPart).getCurrentPage();
					
					if (page instanceof ModelExplorerPage) {
						ModelExplorerView modelExplorerView = (ModelExplorerView) ( (ModelExplorerPage) ((ModelExplorerPageBookView)viewPart ).getCurrentPage() ).getViewer();
						EditingDomain domain = modelExplorerView.getEditingDomain();
						eObject = domain.getResourceSet().getEObject(uri, false);
						if(eObject != null) {
							CommonViewer treeViewer = ((ModelExplorerView)modelExplorerView).getCommonViewer();
							// The common viewer is in fact a tree viewer bug enhancement: use function in ModelExplorerView instead of findElementForEObject
							List<Object> list = new ArrayList<Object>();
							list.add(eObject);
							ModelExplorerView.reveal(list, treeViewer);
						}
					}
				}
				
				// components view 
				IViewPart viewPartComponentsTree = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_COMPONENT_TREE);
				if(viewPartComponentsTree instanceof ComponentsTree) {
					EditorServices.locateInComponentsTreeView(uriAttribute);
				}

				// bindings view 
				IViewPart viewPartBindigns = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_VALUE_BINDINGS);
				if(viewPartBindigns instanceof ValueBindingsView) {
					if (eObject instanceof Element) {
						TreeUtls.locate((Element)eObject, ((ValueBindingsView)viewPartBindigns).getTreeRoot(), ((ValueBindingsView)viewPartBindigns).getViewer());
					}
				}

				// Modelica code view 
				IViewPart viewPartModelicaCodeSynchonization = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELICA_CODE_SYNCHRONIZATION);
				if(viewPartModelicaCodeSynchonization instanceof ModelicaOMCCodeViewer) {
					((ModelicaOMCCodeViewer)viewPartModelicaCodeSynchonization).locate((Element) eObject);
				}
				
			}

			// Modelica code view for markers without UML elements but having LOCATION path defined 
			String location = iMarker.getAttribute(IMarker.LOCATION, null);
			if (!location.isEmpty() && location != null) {
				IViewPart viewPartModelicaCodeSynchonization = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELICA_CODE_SYNCHRONIZATION);
				if(viewPartModelicaCodeSynchonization instanceof ModelicaOMCCodeViewer) {
					// for code importer use the marker attribute location
					((ModelicaOMCCodeViewer)viewPartModelicaCodeSynchonization).locate(location);
				}
			}
		}
    }
    
    @SuppressWarnings("restriction")
	public IMarker[] getMarkersSelectedInView(){
		IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_VALIDATION);
		if (viewPart instanceof MarkerSupportView) {
			return ((MarkerSupportView)viewPart).getSelectedMarkers();
		}
    	return super.getSelectedMarkers();
    }
   
    /**
	 * Gets the value.
	 * 
	 * @param item
	 *            the item
	 * @return the value
	 */
    public String getValue(MarkerItem item) {
        return item.getMarker().getResource().getProject().getName();
    }
}
