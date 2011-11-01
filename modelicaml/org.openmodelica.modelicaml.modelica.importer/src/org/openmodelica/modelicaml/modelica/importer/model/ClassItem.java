package org.openmodelica.modelicaml.modelica.importer.model;


public class ClassItem extends TreeParent {

	private boolean isFinal = false;
	private boolean isEncapsulated = false;
	private boolean isPartial = false;
	private boolean isReplaceable = false;
	
	// for connectors
	private boolean isExpandable = false;
	
	private String classRestriction = "";
	
	public ClassItem(String name) {
		super(name);
	}
	
	
	// Getter and Setter
	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isEncapsulated() {
		return isEncapsulated;
	}

	public void setEncapsulated(boolean isEncapsulated) {
		this.isEncapsulated = isEncapsulated;
	}

	public boolean isPartial() {
		return isPartial;
	}

	public void setPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}

	public String getClassRestriction() {
		return classRestriction.replaceAll("\"", "").trim();
	}

	public void setClassRestriction(String classRestriction) {
		this.classRestriction = classRestriction;
	}

	public void setReplaceable(boolean isReplaceable) {
		this.isReplaceable = isReplaceable;
	}

	public boolean isReplaceable() {
		return isReplaceable;
	}



	// only for connectors
	public void setExpandable(boolean isExpandable) {
		this.isExpandable = isExpandable;
	}
	public boolean isExpandable() {
		return isExpandable;
	}
	
	public boolean isEnumeration(){
		if (this.getClassRestriction().equals("enumeration")) {
			return true;
		}
		return false;
	}

	public boolean isType(){
		if (this.getClassRestriction().equals("type")) {
			return true;
		}
		return false;
	}
	
}
