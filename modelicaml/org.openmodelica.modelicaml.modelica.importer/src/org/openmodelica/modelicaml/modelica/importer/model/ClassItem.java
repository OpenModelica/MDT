package org.openmodelica.modelicaml.modelica.importer.model;

import java.util.List;

import org.eclipse.emf.common.util.EList;


public class ClassItem extends TreeParent {

	private boolean isFinal = false;
	private boolean isEncapsulated = false;
	private boolean isPartial = false;
	private boolean isReplaceable = false;
	private boolean isEnumeration = false;
	
	private String comment;
	private List<String> annotations;
	
	private List<String> initialAlgorithms;
	private List<String> initialEquations;
	private List<String> algorithms;
	private List<String> equations;

	
	// array size for primitive types extension
	private EList<String> arraySize;
	
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

	public void setIsReplaceable(boolean isReplaceable) {
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

	public boolean isType(){
		if (this.getClassRestriction().equals("type")) {
			return true;
		}
		return false;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getComment() {
		return comment;
	}

	
	public void setArraySize(EList<String> arraySize) {
		this.arraySize = arraySize;
	}


	public EList<String> getArraySize() {
		return arraySize;
	}

	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}

	public List<String> getAnnotations() {
		return annotations;
	}
	
	public List<String> getInitialAlgorithms() {
		return initialAlgorithms;
	}

	public void setInitialAlgorithms(List<String> initialAlgorithms) {
		this.initialAlgorithms = initialAlgorithms;
	}

	public List<String> getInitialEquations() {
		return initialEquations;
	}

	public void setInitialEquations(List<String> initialEquations) {
		this.initialEquations = initialEquations;
	}

	public List<String> getAlgorithms() {
		return algorithms;
	}

	public void setAlgorithms(List<String> algorithms) {
		this.algorithms = algorithms;
	}

	public List<String> getEquations() {
		return equations;
	}

	public void setEquations(List<String> equations) {
		this.equations = equations;
	}

	public void setIsEnumeration(boolean isEnumeration) {
		this.isEnumeration = isEnumeration;
	}

	public boolean isEnumeration() {
		return isEnumeration;
	}
	
}
