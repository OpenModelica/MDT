package org.openmodelica.modelicaml.view.componentstree.display;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.view.componentstree.views.ComponentsTree;

public class TreeUtls {

	// #############################	TreeViewer management

//	public static HashSet<Object> findTreeItems(EObject selectedElement, TreeParent parent, HashSet<Object> list) {
//		if (parent != null) {
//			TreeObject[] items = parent.getChildren();
//			
//			for (int i = 0; i < items.length; i++) {
//				if ( ((TreeObject)items[i]).getUmlElement() == selectedElement) {
//					list.add(items[i]);
//				}
//				else {
//					if (items[i] instanceof TreeParent) {
//						list.addAll(findTreeItems(selectedElement, (TreeParent)items[i], list));
//					}
//				}
//			}
//		}
//		return list;
//	}
	
	public static HashSet<Object> findTreeItems(TreeObject selectedItem, TreeParent parent, HashSet<Object> list) {

		if (parent != null) {
			TreeObject[] items = parent.getChildren();
			
			for (int i = 0; i < items.length; i++) {
//				if ( items[i] == selectedItem) { // does not work
//					list.add(items[i]);
//				}
//				if ( items[i].equals(selectedItem) ) { // does not work
//					list.add(items[i]);
//				}
				if ( items[i].getDotPath().equals(selectedItem.getDotPath()) ) {
					list.add(items[i]);
				}

				else {
					// if it is a parent
					if (items[i] instanceof TreeParent) {
						list.addAll( findTreeItems(selectedItem, (TreeParent)items[i], list) );
					}
				}
			}
		}
		return list;
	}
	

	public static void selectInView(Object obj, TreeParent treeRoot, TreeViewer viewer){

		if ( obj instanceof TreeObject ) {
			TreeObject item = (TreeObject)obj;

			HashSet<Object> objects = findTreeItems( item , treeRoot, new HashSet<Object>());
			List<Object> items = new ArrayList<Object>();
			items.addAll(objects);

			if (items.size() > 0) {
				viewer.setSelection(new StructuredSelection(items), true);
			}
			else {
				viewer.expandToLevel(ComponentsTree.DEFAULT_EXPAND_LEVEL);
			}
		}
	}
	
	
	
	
	
	
	public static void removeTreeItem(EObject object, TreeParent treeRoot, TreeViewer viewer) {
		TreeObject[] items = treeRoot.getChildren();
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() != null && ((TreeObject)items[i]).getUmlElement() == object) {
					TreeParent parent = ((TreeObject)items[i]).getParent();
					if (parent != null) {
						parent.removeChild((TreeObject) items[i]);
					}
					viewer.remove(items[i]);
//					viewer.refresh();
			}
			else {
				if (items[i] instanceof TreeParent) {
					removeTreeItem((TreeParent)items[i], object, viewer);
				}
			}
		}
	}

	
	public static void removeTreeItem(TreeParent parent, EObject object, TreeViewer viewer) {
		TreeObject[] items = parent.getChildren();
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() != null && ((TreeObject)items[i]).getUmlElement() == object) {
				parent.removeChild((TreeObject) items[i]);
				viewer.remove(items[i]);
//				viewer.refresh();
			}
			else {
				if (items[i] instanceof TreeParent) {
					removeTreeItem((TreeParent)items[i], object, viewer);
				}
			}
		}
	}

}
