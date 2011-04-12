package org.modelica.uml.sysml.diagram2.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaPropertyCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public ModelicaPropertyCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return UMLPackage.eINSTANCE.getStructuredClassifier();
	}

	/**
	 * @generated NOT
	 */
	protected EObject doDefaultElementCreation() {
		ModelicaProperty newElement = (ModelicaProperty) super
				.doDefaultElementCreation();
		if (newElement != null) {
			if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaProperty_2001)
				SysmlElementTypes.Initializers.ModelicaProperty_2001
						.init(newElement);
			else if (((CreateElementRequest) this.getRequest())
					.getElementType() == SysmlElementTypes.ModelicaProperty_2003)
				SysmlElementTypes.Initializers.ModelicaProperty_2003
						.init(newElement);
			else if (((CreateElementRequest) this.getRequest())
					.getElementType() == SysmlElementTypes.ModelicaProperty_2004)
				SysmlElementTypes.Initializers.ModelicaProperty_2004
						.init(newElement);
		}
		return newElement;
	}
}
