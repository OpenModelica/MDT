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
package org.openmodelica.modelicaml.gen.modelica.validation.view;

import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.MarkerSupportView;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLValidationView.
 */
public class ModelicaMLValidationView extends MarkerSupportView {
    
    /**
	 * Instantiates a new modelica ml validation view.
	 */
    public ModelicaMLValidationView() {
        super("org.openmodelica.modelicaml.markerContentGenerator");
        
    }

//	public static void openMarkerInEditor(IMarker marker, IWorkbenchPage page) {
//		// optimization: if the active editor has the same input as
//		// the
//		// selected marker then
//		// RevealMarkerAction would have been run and we only need
//		// to
//		// activate the editor
//		IEditorPart editor = page.getActiveEditor();
//		if (editor != null) {
//			IEditorInput input = editor.getEditorInput();
//			IFile file = ResourceUtil.getFile(input);
//			if (file != null) {
//				if (marker.getResource().equals(file)
//						&& OpenStrategy.activateOnOpen()) {
//					page.activate(editor);
//				}
//			}
//		}
//
//		if (marker != null && marker.getResource() instanceof IFile) {
//			try {
//				IDE.openEditor(page, marker, OpenStrategy.activateOnOpen());
//			} catch (PartInitException e) {
//
//				// Check for a nested CoreException
//				IStatus status = e.getStatus();
//				if (status != null
//						&& status.getException() instanceof CoreException) {
//					status = ((CoreException) status.getException())
//							.getStatus();
//				}
//
//				if (status == null)
//					StatusManager.getManager().handle(
//							StatusUtil.newStatus(IStatus.ERROR, e.getMessage(),
//									e), StatusManager.SHOW);
//
//				else
//					StatusManager.getManager().handle(status,
//							StatusManager.SHOW);
//
//			}
//		}
//		else if (marker != null ) {
//			try {
//				String markerType = marker.getType();
//				if (markerType.equals(Constants.MARKERTYPE_MODELICA_MODELS_LOADING) || markerType.equals(Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES) ) {
//					System.err.println("HOIER");
//				}
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//	}
    
    
    
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
