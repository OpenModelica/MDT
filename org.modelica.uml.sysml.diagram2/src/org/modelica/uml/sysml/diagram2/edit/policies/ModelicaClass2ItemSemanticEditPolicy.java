package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.gef.commands.UnexecutableCommand;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaClass2ItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getMSLWrapper(new DestroyElementCommand(req) {

			protected EObject getElementToDestroy() {
				View view = (View) getHost().getModel();
				EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
				if (annotation != null) {
					return view;
				}
				return super.getElementToDestroy();
			}

		});
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (SysmlElementTypes.Generalization_3001 == req.getElementType()) {
			return req.getTarget() == null ? getCreateStartOutgoingGeneralization3001Command(req)
					: getCreateCompleteIncomingGeneralization3001Command(req);
		}
		if (SysmlElementTypes.ModelicaComposition_3002 == req.getElementType()) {
			return req.getTarget() == null ? getCreateStartOutgoingModelicaComposition3002Command(req)
					: getCreateCompleteIncomingModelicaComposition3002Command(req);
		}
		return super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartOutgoingGeneralization3001Command(
			CreateRelationshipRequest req) {
		return new Command() {
		};
	}

	/**
	 * @generated
	 */
	protected Command getCreateCompleteIncomingGeneralization3001Command(
			CreateRelationshipRequest req) {
		if (!(req.getSource() instanceof Classifier)) {
			return UnexecutableCommand.INSTANCE;
		}
		final Classifier element = (Classifier) req.getSource();
		if (req.getContainmentFeature() == null) {
			req.setContainmentFeature(UMLPackage.eINSTANCE
					.getClassifier_Generalization());
		}
		return getMSLWrapper(new CreateIncomingGeneralization3001Command(req) {

			/**
			 * @generated
			 */
			protected EObject getElementToEdit() {
				return element;
			}
		});
	}

	/**
	 * @generated
	 */
	private static class CreateIncomingGeneralization3001Command extends
			CreateRelationshipCommand {

		/**
		 * @generated
		 */
		public CreateIncomingGeneralization3001Command(
				CreateRelationshipRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return UMLPackage.eINSTANCE.getClassifier();
		};

		/**
		 * @generated
		 */
		protected void setElementToEdit(EObject element) {
			throw new UnsupportedOperationException();
		}

		/**
		 * @generated
		 */
		protected EObject doDefaultElementCreation() {
			Generalization newElement = (Generalization) super
					.doDefaultElementCreation();
			if (newElement != null) {
				newElement.setGeneral((Classifier) getTarget());
			}
			return newElement;
		}
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartOutgoingModelicaComposition3002Command(
			CreateRelationshipRequest req) {
		return new Command() {
		};
	}

	/**
	 * @generated
	 */
	protected Command getCreateCompleteIncomingModelicaComposition3002Command(
			CreateRelationshipRequest req) {
		if (!(req.getSource() instanceof Type)) {
			return UnexecutableCommand.INSTANCE;
		}
		final org.eclipse.uml2.uml.Package element = (org.eclipse.uml2.uml.Package) getRelationshipContainer(
				req.getSource(), UMLPackage.eINSTANCE.getPackage(), req
						.getElementType());
		if (element == null) {
			return UnexecutableCommand.INSTANCE;
		}
		if (req.getContainmentFeature() == null) {
			req.setContainmentFeature(UMLPackage.eINSTANCE
					.getPackage_PackagedElement());
		}
		return getMSLWrapper(new CreateIncomingModelicaComposition3002Command(
				req) {

			/**
			 * @generated
			 */
			protected EObject getElementToEdit() {
				return element;
			}
		});
	}

	/**
	 * @generated
	 */
	private static class CreateIncomingModelicaComposition3002Command extends
			CreateRelationshipCommand {

		/**
		 * @generated
		 */
		public CreateIncomingModelicaComposition3002Command(
				CreateRelationshipRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return UMLPackage.eINSTANCE.getPackage();
		};

		/**
		 * @generated
		 */
		protected void setElementToEdit(EObject element) {
			throw new UnsupportedOperationException();
		}

		/**
		 * @generated NOT
		 */
		protected EObject doDefaultElementCreation() {
			ModelicaClass sourceClass = (ModelicaClass) getSource();
			ModelicaClass targetClass = (ModelicaClass) getTarget();

			ModelicaComposition newElement = sourceClass
					.createModelicaComposition("end1", targetClass, "end2");
			return newElement;
		}
	}
}
