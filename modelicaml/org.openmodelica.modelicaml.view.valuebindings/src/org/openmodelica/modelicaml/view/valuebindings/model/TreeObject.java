package org.openmodelica.modelicaml.view.valuebindings.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.constants.Constants;

public class TreeObject implements IAdaptable {
	private String name;
	private TreeParent parent;
	private Element umlElement;
	
	// only used for instantiated class mode
	private org.openmodelica.modelicaml.common.instantiation.TreeObject instantiationTreeObject = null;
	
	// only for the root of the instantiated class tree.
	private boolean isInstantiatedClass = false; 
	
	private boolean isValueClient = false;
	private boolean isValueProvider = false;
	
	// is used for different perspective items, that are not underneath the "normal" root, 
	// in order to disable actions on items
	private boolean isReadOnly = false;
	
	
	public TreeObject(String name) {
		this.name = name;
	}
	
	public String getName() {
//		if (getUmlElement() instanceof NamedElement) {
//			return ((NamedElement)getUmlElement()).getName();
//		}
//		return name;
		
		if ( !this.name.equals("") ) {
			return this.name;	
		}
		else if (getUmlElement() instanceof NamedElement) {
			return ((NamedElement)getUmlElement()).getName();
		}
		return this.name;
	}
	
	public void setName(String name){
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
	
	public void setIsValueClient() {
		this.isValueClient = true;
	}



	
	public boolean isValueClient() {
		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null) {
			return true;
		}
//		return false;
		return this.isValueClient;
	}

	public boolean isValueClientsNode() {
		if (getName().equals(Constants.valueClientsNodeName) || getName().equals(Constants.potentialCalueClientsNodeName)) {
			return true;
		}
		return false;
	}
	
	public boolean isValueMediatorsNode() {
		if (getName().equals(Constants.valueMediatorsNodeName)) {
			return true;
		}
		return false;
	}
	
	
	public void setIsValueProvider() {
		this.isValueProvider = true;
	}
	
	public boolean isValueProvider() {
		if (getUmlElement() != null && getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueProvider) != null) {
			return true;
		}
//		return false;
		return this.isValueProvider;
	}
	
	public boolean isValueProvidersNode() {
		if (getName().equals(Constants.valueProvidersNodeName)) {
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
	
	public boolean isModel() {
		if (getUmlElement() != null && getUmlElement() instanceof Model ) {
			return true;
		}
		return false;
	}
	
	
	public Object getAdapter(java.lang.Class adapter) {
		
		if (adapter == EObject.class) {
			return getUmlElement();
		}
		if (adapter == TreeObject.class) {
			return this;
		}
		return null;
	}
	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	public boolean isReadOnly() {
		return isReadOnly;
	}
	public void setInstantiatedClass(boolean isInstantiatedClass) {
		this.isInstantiatedClass = isInstantiatedClass;
	}
	public boolean isInstantiatedClass() {
		return isInstantiatedClass;
	}
	public void setInstantiationTreeObject(org.openmodelica.modelicaml.common.instantiation.TreeObject instantiationTreeObject) {
		this.instantiationTreeObject = instantiationTreeObject;
	}
	public org.openmodelica.modelicaml.common.instantiation.TreeObject getInstantiationTreeObject() {
		return instantiationTreeObject;
	}
}