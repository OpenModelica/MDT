package org.openmodelica.modelicaml.view.valuebindings.utls;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;

public class TreeObject implements IAdaptable {
	private String name;
	private TreeParent parent;
	private Element umlElement;
	
	
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
	
	

	public void setUmlElement(Element umlElement) {
		this.umlElement = umlElement;
	}
	public Element getUmlElement() {
		return umlElement;
	}
	
	
	

	public boolean isValueMediatorContainer() {
		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueMediatorsContainer) != null) {
			return true;
		}
		return false;
	}
	
	public boolean isValueMediator() {
		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
			return true;
		}
		return false;
	}
	public boolean isValueClient() {
		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null) {
			return true;
		}
		return false;
	}

	public boolean isValueProvider() {
		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueProvider) != null) {
			return true;
		}
		return false;
	}
	
	public boolean isPackage() {
		if (getUmlElement() != null && (getUmlElement() instanceof Package || getUmlElement() instanceof Model) ) {
			return true;
		}
		return false;
	}
	
	public Object getAdapter(java.lang.Class adapter) {
		if (adapter == EObject.class) {
			return getUmlElement();
		}
		return null;
	}
}