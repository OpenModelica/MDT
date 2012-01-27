package org.openmodelica.modelicaml.traceability.views.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.uml2.uml.Element;

public class TreeObject implements IAdaptable{
	
//	private final String VISIBILITY_PUBLIC = "public";
//	private final String VISIBILITY_PROTECTED = "protected";
	
	private String name = "";
	private TreeParent parent = null;

	private Element umlElement = null;
	

	public TreeObject(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
	}
	public TreeParent getParent() {
		return parent;
	}
	public String toString() {
		return getName();
	}

	public Object getAdapter(java.lang.Class adapter) {
		if (adapter == TreeObject.class) {
			return this;
		}
		return null;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TreeObject) {
			TreeObject item = (TreeObject) obj;
			if (item.getUmlElement().equals(this.getUmlElement())) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public int hashCode() {
		if (this.getUmlElement() != null) {
			return this.getName().hashCode() + this.getUmlElement().hashCode();	
		}
		else return this.getName().hashCode();
		
	}

	public void setUmlElement(Element umlElement) {
		this.umlElement = umlElement;
	}

	public Element getUmlElement() {
		return umlElement;
	}

	
	
	
}
