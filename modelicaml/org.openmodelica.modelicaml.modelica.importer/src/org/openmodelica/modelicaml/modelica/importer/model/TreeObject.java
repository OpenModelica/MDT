package org.openmodelica.modelicaml.modelica.importer.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

public class TreeObject implements IAdaptable{
	
//	private final String VISIBILITY_PUBLIC = "public";
//	private final String VISIBILITY_PROTECTED = "protected";
	
	private String name = "";
	private TreeParent parent = null;
	
	private String qName = "";
	
//	private boolean isPublic = false;

//	private boolean isFinal = false;
//	private boolean isEncapsulated = false;
//	private boolean isPartial = false;

//	private Boolean isFolder = false;
//	private Boolean isExtends = false;
//	private Boolean isImport = false;
	
//	private Boolean isClass = false;
//	private String classRestriction = "";


	private Element modelicaMLProxy = null;
	

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
//	public Boolean isPublic() {
//		return isPublic;
//	}
//	public void setIsPublic(Boolean isPublic) {
//		this.isPublic = isPublic;
//	}
//	public Boolean isEncapsulated() {
//		return isEncapsulated;
//	}
//	public void setIsEncapsulated(Boolean isEncapsulated) {
//		this.isEncapsulated = isEncapsulated;
//	}
//	public void setPartial(boolean isPartial) {
//		this.isPartial = isPartial;
//	}
//	public boolean isPartial() {
//		return isPartial;
//	}
	
	
//	public Boolean isClass() {
//		return isClass;
//	}
//	public void setIsClass(Boolean isClass) {
//		this.isClass = isClass;
//	}
//	public String getClassRestriction() {
//		return classRestriction;
//	}
//	public void setClassRestriction(String classRestriction) {
//		this.classRestriction = classRestriction;
//	}
//	public Boolean isFolder() {
//		return isFolder;
//	}
//	public void setIsFolder(Boolean isFolder) {
//		this.isFolder = isFolder;
//	}
//	public Boolean isExtends() {
//		return isExtends;
//	}
//	public void setIsExtends(Boolean isExtends) {
//		this.isExtends = isExtends;
//	}
//	public Boolean isImport() {
//		return isImport;
//	}
//	public void setIsImport(Boolean isImport) {
//		this.isImport = isImport;
//	}

	
	


	
	
	public void setModelicaMLProxy(Element modelicaMLProxy) {
		this.modelicaMLProxy = modelicaMLProxy;
	}
	public Element getModelicaMLProxy() {
		return modelicaMLProxy;
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



//	public void setFinal(boolean isFinal) {
//		this.isFinal = isFinal;
//	}
//
//	public boolean isFinal() {
//		return isFinal;
//	}
	
	
}
