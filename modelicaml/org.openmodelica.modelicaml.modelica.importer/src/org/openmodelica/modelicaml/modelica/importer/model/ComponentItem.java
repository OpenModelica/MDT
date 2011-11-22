package org.openmodelica.modelicaml.modelica.importer.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;


public class ComponentItem extends TreeParent {

	private Element componentTypeProxy = null;
	private String componentTypeQName = "";
	private TreeObject componentTypeTreeItem = null;
	
	private String comment; 
	private String visibility;
	private boolean isFinal;
	private boolean isFlow;
	private boolean isStream;
	private boolean isReplaceable;
	private String variability;
	private String innerouter;
	private String causality;
	private EList<String> arraySize;
	
	private EList<String> modifications = null;
	private String declaration = "";
	private String conditionalExpression = "";
	
	private String annotation;
	
	public ComponentItem(String name) {
		super(name);
	}
	
	
	// Getter and Setter
	public Boolean isPort() {
		if (!this.isFunctionArgument() && !this.isEnumarationLiteral() ) {
			if (this.componentTypeProxy instanceof Class) {
				Stereotype s = this.componentTypeProxy.getAppliedStereotype(Constants.stereotypeQName_Connector);
				if (s != null) {
					return true;
				}
			}
			TreeObject componentTypeTreeItem = this.getComponentTypeTreeItem(); 
			if (componentTypeTreeItem instanceof ClassItem && ((ClassItem)componentTypeTreeItem).getClassRestriction().contains("connector")) { // this means that this is not a primitive type
				return true; 
			}
		}
		return false;
	}
	
	public Boolean isVariable() {
		if (!this.isPort() 
				&& !this.isFunctionArgument() 
				&& !this.isEnumarationLiteral() 
				&& this.isPrimitive()) {
			return true;
		}
		return false;
	}

	public Boolean isPrimitive() {
		if (componentTypeProxy instanceof PrimitiveType) {
			return true;
		}
		TreeObject componentTypeTreeItem = getComponentTypeTreeItem(); 
		if ( componentTypeTreeItem != null && isPredefinedTypeName(componentTypeTreeItem.getQName())) {
			return true;
		}
		return false;
	}
	
	public Boolean isComponent() {
		if (!this.isPort() 
				&& !this.isFunctionArgument() 
				&& !this.isEnumarationLiteral() 
				&& !this.isPrimitive()) {
			if (this.componentTypeProxy instanceof Class) {
				return true;
			}
			TreeObject componentTypeTreeItem = this.getComponentTypeTreeItem(); 
			if (componentTypeTreeItem != null && !isPredefinedTypeName(componentTypeTreeItem.getQName())) { // this means that this is not a primitive type
				return true; 
			}
		}
		return false;
	}
	
	
	private boolean isPredefinedTypeName(String name){
		if (name.equals("Real")) { return true; }
		if (name.equals("Integer")) { return true; }
		if (name.equals("String")) { return true; }
		if (name.equals("Boolean")) { return true; }
		return false;
	}
	
	public Element getComponentTypeProxy() {
		return componentTypeProxy;
	}
	
	public void setComponentTypeProxy(Element componentType) {
		this.componentTypeProxy = componentType;
	}
	public void setComponentTypeQName(String componentTypeQame) {
		this.componentTypeQName = componentTypeQame;
	}
	public String getComponentTypeQame() {
		return componentTypeQName;
	}
	
	public void setComponentTypeTreeItem(TreeObject componentTypeTreeItem) {
		this.componentTypeTreeItem = componentTypeTreeItem;
	}


	public TreeObject getComponentTypeTreeItem() {
		return componentTypeTreeItem;
	}
	
	
	
	
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}


	public String getVisibility() {
		return visibility;
	}


	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}


	public boolean isFinal() {
		return isFinal;
	}


	public void setFlow(boolean isFlow) {
		this.isFlow = isFlow;
	}


	public boolean isFlow() {
		return isFlow;
	}


	public void setStream(boolean isStream) {
		this.isStream = isStream;
	}


	public boolean isStream() {
		return isStream;
	}


	public void setReplaceable(boolean isReplaceable) {
		this.isReplaceable = isReplaceable;
	}


	public boolean isReplaceable() {
		return isReplaceable;
	}


	public void setVariability(String variability) {
		this.variability = variability;
	}


	public String getVariability() {
		return variability;
	}


	public void setInnerouter(String innerouter) {
		this.innerouter = innerouter;
	}


	public String getInnerouter() {
		return innerouter;
	}


	public void setCausality(String causality) {
		this.causality = causality;
	}


	public String getCausality() {
		return causality.replaceAll("\"", "").trim();
	}
	
	
	public boolean isInput() {
		if (this.getCausality().equals("input")) {
			return true;
		}
		return false;
	}

	public boolean isOutput() {
		if (this.getCausality().equals("output")) {
			return true;
		}
		return false;
	}

	
	
	public void setModifications(EList<String> modifications) {
		this.modifications = modifications;
	}


	public EList<String> getModifications() {
		return modifications;
	}


	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}


	public String getDeclaration() {
		return declaration;
	}


	public void setConditionalExpression(String conditionalExpression) {
		this.conditionalExpression = conditionalExpression;
	}


	public String getConditionalExpression() {
		return conditionalExpression;
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
	
	public boolean isFunctionArgument(){
		TreeObject parent = this.getParent();
		if (parent instanceof ClassItem) {
			if (((ClassItem) parent).getClassRestriction().equals("function")) {
				if (this.getCausality().equals("input") || this.getCausality().equals("output")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isEnumarationLiteral(){
		TreeObject parent = this.getParent();
		if (parent instanceof ClassItem) {
			if (((ClassItem) parent).getClassRestriction().equals("enumeration")) {
				return true;
			}
		}
		return false;
	}


	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public String getAnnotation() {
		return annotation;
	}

}
