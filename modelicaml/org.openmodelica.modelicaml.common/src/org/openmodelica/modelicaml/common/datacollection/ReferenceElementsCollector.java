package org.openmodelica.modelicaml.common.datacollection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.uml.tools.utils.OpaqueBehaviorUtil;
import org.eclipse.papyrus.uml.tools.utils.OpaqueExpressionUtil;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;

public class ReferenceElementsCollector extends ElementsCollector {

	private Element elemenToSearchFor;
	private IProgressMonitor monitor;
	
	public ReferenceElementsCollector(IProgressMonitor monitor){
		this.monitor = monitor;
	}

	
	@Override
	protected void collectElements(Element rootElement, boolean isImported) {
		monitor.subTask("Searching in " + ModelicaMLServices.getQualifiedName(rootElement));
		super.collectElements(rootElement, isImported);
	}
	
	@Override
	protected void collect(Element element, boolean isImported) {
		
		if (element instanceof Property) {
			searchInPropertyType((Property) element);
		}
		else if (element instanceof Generalization) {
			searchInGeneralization((Generalization) element);
		}
		else if (element instanceof Dependency) {
			searchInDependecy((Dependency) element);
		}
		else if (element instanceof OpaqueBehavior) {
			searchInOpaqueBehaviorBody((OpaqueBehavior) element);
		}
		else if (element instanceof OpaqueAction) {
			searchInOpaqueActionBody((OpaqueAction) element);
		}
		else if (element instanceof OpaqueExpression) {
			searchInOpaqueExpressionBody((OpaqueExpression) element);
		}
	}
	
	
	private void searchInPropertyType(Property property) {
		Type type = property.getType();
		if (type != null && type.equals(getElemenToSearchFor())) {
			elements.add(property);
		}
	}
	
	private void searchInGeneralization(Generalization generalization ) {
		if (generalization.getGeneral().equals(getElemenToSearchFor())) {
			elements.add(generalization);
		}
		else if (generalization.getSpecific().equals(getElemenToSearchFor())) {
			elements.add(generalization);
		}
		else if (generalization.getSources().contains(getElemenToSearchFor())) {
			elements.add(generalization);
		}
		else if (generalization.getTargets().contains(getElemenToSearchFor())) {
			elements.add(generalization);
		}
	}
	
	
	private void searchInDependecy(Dependency dependency) {
		if (dependency.getClients().contains(getElemenToSearchFor())) {
			elements.add(dependency);
		}
		else if (dependency.getSuppliers().contains(getElemenToSearchFor())) {
			elements.add(dependency);
		}
		else if (dependency.getSources().contains(getElemenToSearchFor())) {
			elements.add(dependency);
		}
		else if (dependency.getTargets().contains(getElemenToSearchFor())) {
			elements.add(dependency);
		}
	}
	
	
	private void searchInOpaqueBehaviorBody(OpaqueBehavior opaqueBehavior) {
		String dotPath = StringUtls.getDotPath(getElemenToSearchFor());
		if (dotPath != null) {
			String code = (String) OpaqueBehaviorUtil.getBody(opaqueBehavior,Constants.actionLanguageName);
			if (code.contains(dotPath)) {
				elements.add(opaqueBehavior);
			}
			else if (code.contains(ModelicaMLServices.getName(getElemenToSearchFor()))) {
				elements.add(opaqueBehavior);
			}
		}
	}
	
	private void searchInOpaqueActionBody(OpaqueAction opaqueAction) {
		String dotPath = StringUtls.getDotPath(getElemenToSearchFor());
		if (dotPath != null) {
			int index = ModelicaMLServices.getBodyIndex(opaqueAction, Constants.actionLanguageName);
			
			if ((index > -1) && (index < (opaqueAction).getBodies().size())) {
				String code =(String) (opaqueAction).getBodies().get(index);
				if (code.contains(dotPath)) {
					elements.add(opaqueAction);
				}
				else if (code.contains(ModelicaMLServices.getName(getElemenToSearchFor()))) {
					elements.add(opaqueAction);
				}
			}
		}
	}
	
	
	private void searchInOpaqueExpressionBody(OpaqueExpression opaqueExpression ) {
		String dotPath = StringUtls.getDotPath(getElemenToSearchFor());
		if (dotPath != null) {
			String code = (String) "" + OpaqueExpressionUtil.getBodyForLanguage(opaqueExpression, Constants.actionLanguageName);
			if (code.contains(dotPath)) {
				elements.add(opaqueExpression);
			}
			else if (code.contains(ModelicaMLServices.getName(getElemenToSearchFor()))) {
				elements.add(opaqueExpression);
			}
		}
	}
	
	
	public Element getElemenToSearchFor() {
		return elemenToSearchFor;
	}

	public void setElemenToSearchFor(Element elemenToSearchFor) {
		this.elemenToSearchFor = elemenToSearchFor;
	}


	public IProgressMonitor getMonitor() {
		return monitor;
	}


	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}
	
}
