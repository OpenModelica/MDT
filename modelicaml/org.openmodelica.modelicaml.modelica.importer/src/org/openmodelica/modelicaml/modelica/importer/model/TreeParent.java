package org.openmodelica.modelicaml.modelica.importer.model;

import java.util.ArrayList;

import org.openmodelica.modelicaml.common.services.ModelicaMLServices;

public class TreeParent extends TreeObject {
	private ArrayList<TreeObject> children;
	public TreeParent(String name) {
		super(name);
		children = new ArrayList<TreeObject>();
	}
	
	public void addChild(TreeObject child) {
		// avoid creating nodes that are actually error messages from OMC
		if (!ModelicaMLServices.containsOMCErrorMessage(child.getName())) {
			children.add(child);
			child.setParent(this);
		}
		else {
			System.err.println("Teh following tree item was not created beucase it is an OMC error string: " + child.getName());
		}
	}
	
	public void removeChild(TreeObject child) {
		children.remove(child);
		child.setParent(null);
	}
	public TreeObject [] getChildren() {
		return (TreeObject [])children.toArray(new TreeObject[children.size()]);
	}
	public boolean hasChildren() {
		return children.size()>0;
	}
}
