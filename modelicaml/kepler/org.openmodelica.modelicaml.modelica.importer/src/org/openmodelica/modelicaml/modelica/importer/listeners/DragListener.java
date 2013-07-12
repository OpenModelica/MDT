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
package org.openmodelica.modelicaml.modelica.importer.listeners;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

public class DragListener extends DragSourceAdapter implements DragSourceListener {

	private final TreeViewer viewer;

	public DragListener(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		LocalSelectionTransfer.getTransfer().setSelection(null);
//		System.out.println("Finshed Drag");
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		if (LocalSelectionTransfer.getTransfer().isSupportedType(event.dataType)) {
			event.data = selection;
		} else {
			event.doit = false;
		}
	}

		
	@Override
	public void dragStart(DragSourceEvent event) {
		ISelection selection = (IStructuredSelection) viewer.getSelection();
		if (!selection.isEmpty()) {
			LocalSelectionTransfer.getTransfer().setSelection(selection);
			event.doit = true;
			event.data = (IStructuredSelection) viewer.getSelection();
		} else {
			event.doit = false;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	 public void dragStart(DragSourceEvent event) {
//		 ISelection selection = viewer.getSelection();
//		 System.err.println(selection);
//      };
	
//
//	public void dragSetData(DragSourceEvent event) { 
//		IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
////		TreeObject[] items = (TreeObject[])selection.toList().toArray(new TreeObject[selection.size()]);
//		
//		System.err.println("event: " + event);
//		
//		if (PluginTransfer.getInstance().isSupportedType(event.dataType)) { 
//			byte[] dataToSend = PluginTransfer.getInstance(). 
//				((TreeObject)selection.getFirstElement()).toString().getBytes();
//            event.data = new PluginTransferData( 
//                "org.openmodelica.modelicaml.view.valuebindings.instantiatedClassTreeItemDropAction", dataToSend); 
//        } 
//    } 
//	
	
//	@Override
//	public void dragSetData(DragSourceEvent event) {
//		
//		System.err.println("dragSetData");
//		
//		if (PluginTransfer.getInstance().isSupportedType(event.dataType)) {
//			byte[] segmentData = getSegmentText().getBytes();
//			event.data = new PluginTransferData("org.openmodelica.modelicaml.view.valuebindings.instantiatedClassTreeItemDropAction", segmentData);
//			return;
//		}
////		if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
////			event.data = getSegmentText();
////			return;
////		}
//	}

//	private String getSegmentText() {
//		StringBuffer result = new StringBuffer();
//		ISelection selection = viewer.getSelection();
//		if (selection instanceof org.eclipse.jface.viewers.IStructuredSelection) {
//			Object[] selected = ((IStructuredSelection) selection).toArray();
//			result.append("\n"); //$NON-NLS-1$
//			for (int i = 0; i < selected.length; i++) {
//				if (selected[i] instanceof TreeObject) {
//					result.append(((TreeObject) selected[i]));
//					result.append("\n"); //$NON-NLS-1$
//				}
//			}
//		}
//		return result.toString();
//	}



}
