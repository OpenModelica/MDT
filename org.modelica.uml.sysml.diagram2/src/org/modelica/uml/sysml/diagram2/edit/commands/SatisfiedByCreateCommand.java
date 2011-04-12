package org.modelica.uml.sysml.diagram2.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.SatisfiedBy;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class SatisfiedByCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public SatisfiedByCreateCommand(CreateElementRequest req) {
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
		return UMLPackage.eINSTANCE.getPackage();
	}

	/**
	 * @generated
	 */
	protected EObject doDefaultElementCreation() {
		SatisfiedBy newElement = (SatisfiedBy) super.doDefaultElementCreation();
		if (newElement != null) {
			SysmlElementTypes.Initializers.SatisfiedBy_1005.init(newElement);
		}
		return newElement;
	}
}
