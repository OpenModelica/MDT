package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaClassNestedItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated NOT
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SysmlElementTypes.ModelicaClass_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getClass_NestedClassifier());
			}
			return getMSLWrapper(new CreateModelicaClass_2005Command(req));
		}

		if (SysmlElementTypes.ModelicaModel_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getClass_NestedClassifier());
			}
			return getMSLWrapper(new CreateModelicaClass_2005Command(req));
		}

		if (SysmlElementTypes.ModelicaBlock_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getClass_NestedClassifier());
			}
			return getMSLWrapper(new CreateModelicaClass_2005Command(req));
		}

		if (SysmlElementTypes.ModelicaRecord_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getClass_NestedClassifier());
			}
			return getMSLWrapper(new CreateModelicaClass_2005Command(req));
		}

		if (SysmlElementTypes.ModelicaConnector_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getClass_NestedClassifier());
			}
			return getMSLWrapper(new CreateModelicaClass_2005Command(req));
		}

		if (SysmlElementTypes.ModelicaFunction_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getClass_NestedClassifier());
			}
			return getMSLWrapper(new CreateModelicaClass_2005Command(req));
		}

		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	private static class CreateModelicaClass_2005Command extends
			CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateModelicaClass_2005Command(CreateElementRequest req) {
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
	}

}
