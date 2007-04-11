package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaClassConnections2ItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SysmlElementTypes.ModelicaProperty_2004 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getStructuredClassifier_OwnedAttribute());
			}
			return getMSLWrapper(new CreateModelicaProperty_2004Command(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	private static class CreateModelicaProperty_2004Command extends
			CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateModelicaProperty_2004Command(CreateElementRequest req) {
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
			ModelicaProperty newElement = (ModelicaProperty) super
					.doDefaultElementCreation();
			if (newElement != null) {
				SysmlElementTypes.Initializers.ModelicaProperty_2004
						.init(newElement);
			}
			return newElement;
		}
	}

}
