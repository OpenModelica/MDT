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

import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.impl.ModelicaConnectionImpl;
import org.modelica.uml.sysml.impl.SysmlFactoryImpl;
import org.modelica.uml.sysml.ModelicaConnection;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaConnectionCreateCommand;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 */
public class ModelicaPortItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
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
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {

		if (SysmlElementTypes.ModelicaConnection_3009 == req.getElementType()) {

			if (req.getSource() instanceof ModelicaProperty) {
				ModelicaProperty portSource = (ModelicaProperty) req
						.getSource();
				if (portSource.getType() instanceof ModelicaClass) {

					if (req.getTarget() == null) {
						return getCreateStartOutgoingModelicaConnection3009Command(req);
					}

					ModelicaProperty portTarget = (ModelicaProperty) req
							.getTarget();
					if (portTarget.getType() instanceof ModelicaClass) {
						if (!portSource.equals(portTarget)){
							int i = 5; 
						}
						return getCreateCompleteIncomingModelicaConnection3009Command(req);
					}
				}
			}
		}
		return super.getCreateRelationshipCommand(req);
	}

	/**
	 */
	protected Command getCreateStartOutgoingModelicaConnection3009Command(
			CreateRelationshipRequest req) {
		
		final ModelicaClass connectorContainer = (ModelicaClass) ((ModelicaProperty) ((View) this
				.getHost().getParent().getModel()).getElement()).getOwner();

		if (connectorContainer == null) {
			return UnexecutableCommand.INSTANCE;
		}
		
		req.setContainer(connectorContainer);
		
		if (SysmlElementTypes.ModelicaConnection_3009 == req.getElementType()) {
			return getGEFWrapper(new ModelicaConnectionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 */
	protected Command getCreateCompleteIncomingModelicaConnection3009Command(
			CreateRelationshipRequest req) {

		
		
//		
//		if (!(req.getSource() instanceof ModelicaProperty)) {
//			return UnexecutableCommand.INSTANCE;
//		}
//
//		// get top most class that will own connector
		final ModelicaClass connectorContainer = (ModelicaClass) ((ModelicaProperty) ((View) this
				.getHost().getParent().getModel()).getElement()).getOwner();

		if (connectorContainer == null) {
			return UnexecutableCommand.INSTANCE;
		}
		
		req.setContainer(connectorContainer);
		
		if (SysmlElementTypes.ModelicaConnection_3009 == req.getElementType()) {
			return getGEFWrapper(new ModelicaConnectionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
		
//	
//
//		if (req.getContainmentFeature() == null) {
//			req.setContainmentFeature(UMLPackage.eINSTANCE
//					.getStructuredClassifier_OwnedConnector());
//		}
//
//		return getMSLWrapper(new CreateIncomingModelicaConnection3009Command(
//				req) {
//
//			protected EObject getElementToEdit() {
//				return connectorContainer;
//			}
//		});
	}

	/**
	 */
	private static class CreateIncomingModelicaConnection3009Command extends
			CreateRelationshipCommand {

		/**
		 */
		public CreateIncomingModelicaConnection3009Command(
				CreateRelationshipRequest req) {
			super(req);
		}

		/**
		 */
		protected EClass getEClassToEdit() {
			return SysmlPackage.eINSTANCE.getModelicaClass();
		};

		/**
		 */
		protected void setElementToEdit(EObject element) {
			throw new UnsupportedOperationException();
		}

		/**
		 */
		protected EObject doDefaultElementCreation() {

			ModelicaConnection modelicaConnector = SysmlFactoryImpl.eINSTANCE.createModelicaConnection();	
			ConnectorEnd sourceEnd = modelicaConnector.createEnd();
			sourceEnd.setRole((ModelicaProperty)getSource());
			
			ConnectorEnd targetEnd = modelicaConnector.createEnd();
			targetEnd.setRole((ModelicaProperty)getTarget());
			modelicaConnector.setName("connector");
			
			((ModelicaClass) getElementToEdit()).
				getOwnedConnectors().add(modelicaConnector);
			return modelicaConnector;
		}
	}
}
