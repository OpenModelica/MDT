package org.openmodelica.modelicaml.view.componentstree.listeners;

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
