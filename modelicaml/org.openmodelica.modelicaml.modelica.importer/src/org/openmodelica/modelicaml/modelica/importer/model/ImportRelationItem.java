package org.openmodelica.modelicaml.modelica.importer.model;

import org.eclipse.uml2.uml.Element;


public class ImportRelationItem extends TreeParent {

	private Element source = null;
	private String sourceQname;
	
	private Element target = null;
	private String targetQname;
	
	private String alias;
	private String kind;
	
	public ImportRelationItem(String name) {
		super(name);
	}

	// Getter and Setter
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

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getKind() {
		return kind;
	}	
}
