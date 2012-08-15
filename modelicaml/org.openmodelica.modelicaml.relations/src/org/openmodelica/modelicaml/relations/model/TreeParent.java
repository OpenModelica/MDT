package org.openmodelica.modelicaml.relations.model;

import java.util.ArrayList;

public class TreeParent extends TreeObject {
	private ArrayList<TreeObject> children;
	
	private boolean isRoot = false;
	private boolean isDependeciesNode = false;
	private boolean isInheritanceNode = false;
	
	public TreeParent(String name) {
		super(name);
		children = new ArrayList<TreeObject>();
	}
	public void addChild(TreeObject child) {
		children.add(child);
		child.setParent(this);
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
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public boolean isDependeciesNode() {
		return isDependeciesNode;
	}
	public void setDependeciesNode(boolean isDependeciesNode) {
		this.isDependeciesNode = isDependeciesNode;
	}
	public boolean isInheritanceNode() {
		return isInheritanceNode;
	}
	public void setInheritanceNode(boolean isInheritanceNode) {
		this.isInheritanceNode = isInheritanceNode;
	}
}