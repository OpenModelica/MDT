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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */

package org.openmodelica.modelicaml.gen.modelica.validation.actions;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.uml2.uml.Element;

// TODO: Auto-generated Javadoc
//import com.cea.papyrus.core.PapyrusPlugin;
//import com.cea.papyrus.core.editor.MultiDiagramEditor;
//import com.cea.papyrus.core.treeeditpart.CommonTreeEditPart;
//import com.cea.papyrus.core.treeeditpart.TreeOutlinePage;


/**
 * Implementation of the Locate contextual action PopUp action is defined in
 * plug-in extensions PopUp action accept a single IUMLObjectEditPart.
 */
public class OBSOLETE_LocateActionDelegate implements IEditorActionDelegate {

	/**
	 * Instantiates a new locate action delegate.
	 */
	public OBSOLETE_LocateActionDelegate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** The selected object. */
	protected Element selectedObject;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		selectedObject = null;
		// Retrieve selected elements
		IStructuredSelection s = (IStructuredSelection) selection;
		MarkerItem marker = (MarkerItem)s.getFirstElement();
		String qualifiedName = marker.getAttributeValue(IMarker.LOCATION, null);

//		MultiDiagramEditor editor = PapyrusPlugin.getPapyrusEditor();
//		
//		TreeOutlinePage outline = (TreeOutlinePage) editor.getAdapter(IContentOutlinePage.class);
//		if(outline != null && outline.getModel() != null && outline.getModel().getModelTreeEditPart()!=null)
//		{
//			Model model = (Model)outline.getModel().getModelTreeEditPart().getModel();
//			EList<Element> elementList = model.getOwnedElements();
//			Iterator<Element> it = elementList.iterator();
//			while (it.hasNext()) {
//				Element element = it.next();
//				if(element instanceof NamedElement)
//				{
//					NamedElement namedElement = (NamedElement)element;
//					if(qualifiedName.equals(namedElement.getQualifiedName()))
//					{
//						selectedObject = namedElement;
//						break;
//					}	
//					else if(qualifiedName.contains(namedElement.getQualifiedName()))
//						{
//							elementList = namedElement.getOwnedElements();
//							it = elementList.iterator();
//							selectedObject = namedElement;
//						}
//				}
//			}
//		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.edona.eastadl2.validation.ui.actions.Action#setActiveEditor(org.eclipse.jface.action.
	 * IAction, org.eclipse.ui.IEditorPart)
	 */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// no-op
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {

//		MultiDiagramEditor editor = PapyrusPlugin.getPapyrusEditor();
//		TreeOutlinePage outline = (TreeOutlinePage) editor.getAdapter(IContentOutlinePage.class);
//		//TODO see ExtendedMarkersView openMarkerInEditor() ..
//
//		// Find element in Outline
//		if(selectedObject!=null)
//		{
//			CommonTreeEditPart ctep = outline.expandToElement(selectedObject);
//			if (ctep != null) {
//				// Set selection on the TreeEditPart that matches the selection
//				ctep.getViewer().select(ctep);
//			}
//		}
//		else {
//			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//			MessageDialog.openInformation(shell, "Information:", "Open the project in papyrus editor");
//		}
	}
}
