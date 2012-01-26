package org.openmodelica.modelicaml.traceability.views.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.uml2.uml.Element;

public class TreeObject implements IAdaptable{
	
//	private final String VISIBILITY_PUBLIC = "public";
//	private final String VISIBILITY_PROTECTED = "protected";
	
	private String name = "";
	private TreeParent parent = null;
	
	private String qName = "";

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
	
	
	// Getters
	public String getQName() {
		return qName;
	}
	public void setQName(String qName) {
		this.qName = qName;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TreeObject) {
			TreeObject item = (TreeObject) obj;
			if (item.getQName().equals(this.getQName())) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public int hashCode() {
		int result = 0;
		
		// get the name 
		result = result + this.getName().hashCode() + this.getQName().hashCode();
		return result;
	}

	public void setUmlElement(Element umlElement) {
		this.umlElement = umlElement;
	}

	public Element getUmlElement() {
		return umlElement;
	}

}
