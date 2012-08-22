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
