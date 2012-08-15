package org.openmodelica.modelicaml.relations.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;

public class TreeObject implements IAdaptable {
	private String name;
	private TreeParent parent;
	
	private Element umlElement;
	private Dependency dependencyElement;
	private Generalization generalzationElement;
	private boolean hasErrors = false;
	
	public TreeObject(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
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

	public Element getUmlElement() {
		if (this.umlElement == null && this.dependencyElement != null) {
			return this.dependencyElement;
		}
		else if (this.umlElement == null && this.generalzationElement != null) {
			return this.generalzationElement;
		}
		return umlElement;
	}
	public void setUmlElement(Element umlElement) {
		this.umlElement = umlElement;
	}
	public Dependency getDependencyElement() {
		return dependencyElement;
	}
	public void setDependencyElement(Dependency dependencyElement) {
		this.dependencyElement = dependencyElement;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Object getAdapter(java.lang.Class adapter) {
		if (adapter == TreeObject.class) {
			return this;
		}
		return null;
	}
	
	
	
//	@Override
//	public boolean equals(Object obj) {
//		if (obj instanceof TreeObject) {
//			TreeObject item = (TreeObject) obj;
//			if (item.getUmlElement() != null && item.getUmlElement().equals(this.getUmlElement()) ) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	
//	@Override
//	public int hashCode() {
//		
//		int result = 0;
//		
//		result = result + this.getName().hashCode();
//		
//		if (this.getParent() != null) {
//			result = result + this.getParent().hashCode();
//		}
//		
//		if (this.getUmlElement() != null) {
//			result = result + this.getUmlElement().hashCode();	
//		}
//		if (this.getDependencyElement() != null) {
//			result = result + this.getDependencyElement().hashCode();	
//		}
//		if (this.getGeneralzationElement() != null) {
//			result = result + this.getGeneralzationElement().hashCode();	
//		}
//
//		return result; 	
//	}
	
	
	
	
	public Generalization getGeneralzationElement() {
		return generalzationElement;
	}
	public void setGeneralzationElement(Generalization generalzationElement) {
		this.generalzationElement = generalzationElement;
	}
	public boolean isHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	
}
