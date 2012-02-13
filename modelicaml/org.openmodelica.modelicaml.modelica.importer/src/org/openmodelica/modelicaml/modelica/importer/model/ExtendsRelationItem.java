package org.openmodelica.modelicaml.modelica.importer.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;


public class ExtendsRelationItem extends TreeParent {

	private EList<String> modifications = null;
	private EList<String> arraySize = null;
	private Element source = null;
	private String sourceQname;
	
	private Element target = null;
	private String targetQname;

	
	public ExtendsRelationItem(String name) {
		super(name);
	}

	// Getter and Setter
	public void setModifications(EList<String> modifications) {
		this.modifications = modifications;
	}

	public EList<String> getModifications() {
		return modifications;
	}

	public void setArraySize(EList<String> arraySize) {
		this.arraySize = arraySize;
	}

	public EList<String> getArraySize() {
		return arraySize;
	}

	public void setSource(Element source) {
		this.source = source;
	}

	public Element getSource() {
		return source;
	}

	public void setTarget(Element target) {
		this.target = target;
	}

	public Element getTarget() {
		return target;
	}

	public void setSourceQname(String sourceQname) {
		this.sourceQname = sourceQname;
	}

	public String getSourceQname() {
		return sourceQname;
	}

	public void setTargetQname(String targetQname) {
		this.targetQname = targetQname;
	}

	public String getTargetQname() {
		return targetQname;
	}	
}
