package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaClassEquationsItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SysmlElementTypes.ModelicaEquationProperty_2006 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getStructuredClassifier_OwnedAttribute());
			}
			return getMSLWrapper(new CreateModelicaEquationProperty_2006Command(
					req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	private static class CreateModelicaEquationProperty_2006Command extends
			CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateModelicaEquationProperty_2006Command(
				CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return SysmlPackage.eINSTANCE.getModelicaClass();
		};

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
		 */
		protected EObject doDefaultElementCreation() {
			ModelicaEquationProperty newElement = (ModelicaEquationProperty) super
					.doDefaultElementCreation();
			if (newElement != null) {
				SysmlElementTypes.Initializers.ModelicaEquationProperty_2006
						.init(newElement);
			}
			return newElement;
		}
	}

}
