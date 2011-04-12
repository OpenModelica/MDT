package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IEditHelperContext;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaClassifier;
import org.modelica.uml.sysml.ModelicaRationale;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.SatisfiedBy;
import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.edit.helpers.SysmlBaseEditHelper;

import org.modelica.uml.sysml.diagram2.expressions.SysmlAbstractExpression;
import org.modelica.uml.sysml.diagram2.expressions.SysmlOCLFactory;

import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorPlugin;
import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class SysmlBaseItemSemanticEditPolicy extends SemanticEditPolicy {

	/**
	 * Extended request data key to hold editpart visual id.
	 * 
	 * @generated
	 */
	public static final String VISUAL_ID_KEY = "visual_id"; //$NON-NLS-1$

	/**
	 * Extended request data key to hold editpart visual id.
	 * Add visual id of edited editpart to extended data of the request
	 * so command switch can decide what kind of diagram element is being edited.
	 * It is done in those cases when it's not possible to deduce diagram
	 * element kind from domain element.
	 * 
	 * @generated
	 */
	public Command getCommand(Request request) {
		if (request instanceof ReconnectRequest) {
			Object view = ((ReconnectRequest) request).getConnectionEditPart()
					.getModel();
			if (view instanceof View) {
				Integer id = new Integer(SysmlVisualIDRegistry
						.getVisualID((View) view));
				request.getExtendedData().put(VISUAL_ID_KEY, id);
			}
		}
		return super.getCommand(request);
	}

	/**
	 * Returns visual id from request parameters.
	 * 
	 * @generated
	 */
	protected int getVisualID(IEditCommandRequest request) {
		Object id = request.getParameter(VISUAL_ID_KEY);
		return id instanceof Integer ? ((Integer) id).intValue() : -1;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommand(IEditCommandRequest request) {
		IEditCommandRequest completedRequest = completeRequest(request);
		Object editHelperContext = completedRequest.getEditHelperContext();
		if (editHelperContext instanceof View
				|| (editHelperContext instanceof IEditHelperContext && ((IEditHelperContext) editHelperContext)
						.getEObject() instanceof View)) {
			// no semantic commands are provided for pure design elements
			return null;
		}
		if (editHelperContext == null) {
			editHelperContext = ViewUtil
					.resolveSemanticElement((View) getHost().getModel());
		}
		IElementType elementType = ElementTypeRegistry.getInstance()
				.getElementType(editHelperContext);
		if (elementType == ElementTypeRegistry.getInstance().getType(
				"org.eclipse.gmf.runtime.emf.type.core.default")) { //$NON-NLS-1$ 
			elementType = null;
		}
		Command semanticCommand = getSemanticCommandSwitch(completedRequest);
		if (semanticCommand != null) {
			ICommand command = semanticCommand instanceof ICommandProxy ? ((ICommandProxy) semanticCommand)
					.getICommand()
					: new CommandProxy(semanticCommand);
			completedRequest.setParameter(
					SysmlBaseEditHelper.EDIT_POLICY_COMMAND, command);
		}
		if (elementType != null) {
			ICommand command = elementType.getEditCommand(completedRequest);
			if (command != null) {
				if (!(command instanceof CompositeTransactionalCommand)) {
					TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
							.getEditingDomain();
					command = new CompositeTransactionalCommand(editingDomain,
							null).compose(command);
				}
				semanticCommand = new ICommandProxy(command);
			}
		}
		boolean shouldProceed = true;
		if (completedRequest instanceof DestroyRequest) {
			shouldProceed = shouldProceed((DestroyRequest) completedRequest);
		}
		if (shouldProceed) {
			if (completedRequest instanceof DestroyRequest) {
				TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
						.getEditingDomain();
				Command deleteViewCommand = new ICommandProxy(
						new DeleteCommand(editingDomain, (View) getHost()
								.getModel()));
				semanticCommand = semanticCommand == null ? deleteViewCommand
						: semanticCommand.chain(deleteViewCommand);
			}
			return semanticCommand;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommandSwitch(IEditCommandRequest req) {
		if (req instanceof CreateRelationshipRequest) {
			return getCreateRelationshipCommand((CreateRelationshipRequest) req);
		} else if (req instanceof CreateElementRequest) {
			return getCreateCommand((CreateElementRequest) req);
		} else if (req instanceof ConfigureRequest) {
			return getConfigureCommand((ConfigureRequest) req);
		} else if (req instanceof DestroyElementRequest) {
			return getDestroyElementCommand((DestroyElementRequest) req);
		} else if (req instanceof DestroyReferenceRequest) {
			return getDestroyReferenceCommand((DestroyReferenceRequest) req);
		} else if (req instanceof DuplicateElementsRequest) {
			return getDuplicateCommand((DuplicateElementsRequest) req);
		} else if (req instanceof GetEditContextRequest) {
			return getEditContextCommand((GetEditContextRequest) req);
		} else if (req instanceof MoveRequest) {
			return getMoveCommand((MoveRequest) req);
		} else if (req instanceof ReorientReferenceRelationshipRequest) {
			return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
		} else if (req instanceof ReorientRelationshipRequest) {
			return getReorientRelationshipCommand((ReorientRelationshipRequest) req);
		} else if (req instanceof SetRequest) {
			return getSetCommand((SetRequest) req);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getConfigureCommand(ConfigureRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSetCommand(SetRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getEditContextCommand(GetEditContextRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getMoveCommand(MoveRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected final Command getGEFWrapper(ICommand cmd) {
		return new ICommandProxy(cmd);
	}

	/**
	 * @generated
	 */
	protected final Command getMSLWrapper(ICommand cmd) {
		// XXX deprecated: use getGEFWrapper() instead
		return getGEFWrapper(cmd);
	}

	/**
	 * @generated
	 */
	protected EObject getSemanticElement() {
		return ViewUtil.resolveSemanticElement((View) getHost().getModel());
	}

	/**
	 * Returns editing domain from the host edit part.
	 * 
	 * @generated
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	/**
	 * Creates command to destroy the link.
	 * 
	 * @generated
	 */
	protected Command getDestroyElementCommand(View view) {
		EditPart editPart = (EditPart) getHost().getViewer()
				.getEditPartRegistry().get(view);
		DestroyElementRequest request = new DestroyElementRequest(
				getEditingDomain(), false);
		return editPart.getCommand(new EditCommandRequestWrapper(request,
				Collections.EMPTY_MAP));
	}

	/**
	 * Creates commands to destroy all host incoming and outgoing links.
	 * 
	 * @generated
	 */
	protected CompoundCommand getDestroyEdgesCommand() {
		CompoundCommand cmd = new CompoundCommand();
		View view = (View) getHost().getModel();
		for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
			cmd.add(getDestroyElementCommand((Edge) it.next()));
		}
		for (Iterator it = view.getTargetEdges().iterator(); it.hasNext();) {
			cmd.add(getDestroyElementCommand((Edge) it.next()));
		}
		return cmd;
	}

	/**
	 * @generated
	 */
	protected void addDestroyShortcutsCommand(CompoundCommand command) {
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			return;
		}
		for (Iterator it = view.getDiagram().getChildren().iterator(); it
				.hasNext();) {
			View nextView = (View) it.next();
			if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
				continue;
			}
			command.add(getDestroyElementCommand(nextView));
		}
	}

	/**
	 * @generated 
	 */
	public static class LinkConstraints {
		/**
		 * @generated 
		 */
		private static final String OPPOSITE_END_VAR = "oppositeEnd"; //$NON-NLS-1$

		/**
		 * @generated
		 */
		private static final SysmlAbstractExpression Generalization_3001_SourceExpression;

		/**
		 * @generated
		 */
		static {
			Map env = new HashMap(3);
			env.put(OPPOSITE_END_VAR, UMLPackage.eINSTANCE.getClassifier());
			Generalization_3001_SourceExpression = SysmlOCLFactory
					.getExpression(
							"self<>oppositeEnd", UMLPackage.eINSTANCE.getClassifier(), env); //$NON-NLS-1$
		}

		/**
		 * @generated
		 */
		private static final SysmlAbstractExpression DeriveReqRelationship_3003_SourceExpression;

		/**
		 * @generated
		 */
		static {
			Map env = new HashMap(3);
			env.put(OPPOSITE_END_VAR, SysmlPackage.eINSTANCE.getRequirement());
			DeriveReqRelationship_3003_SourceExpression = SysmlOCLFactory
					.getExpression(
							"self<>oppositeEnd\r\n\r\n", SysmlPackage.eINSTANCE.getRequirement(), env); //$NON-NLS-1$
		}

		/**
		 * @generated
		 */
		private static final SysmlAbstractExpression RequirementHierarchyRelationship_3008_SourceExpression;

		/**
		 * @generated
		 */
		static {
			Map env = new HashMap(3);
			env.put(OPPOSITE_END_VAR, SysmlPackage.eINSTANCE.getRequirement());
			RequirementHierarchyRelationship_3008_SourceExpression = SysmlOCLFactory
					.getExpression(
							"self<>oppositeEnd\r\n\r\n", UMLPackage.eINSTANCE.getElement(), env); //$NON-NLS-1$
		}

		/**
		 * @generated
		 */
		public static boolean canCreateGeneralization_3001(Classifier source,
				Classifier target) {
			return canExistGeneralization_3001(source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateSatisfyRelationship_3002(
				Package container, Requirement source, ModelicaClass target) {
			return canExistSatisfyRelationship_3002(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateDeriveReqRelationship_3003(
				Package container, Requirement source, Requirement target) {
			return canExistDeriveReqRelationship_3003(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateModelicaComposition_3004(
				Package container, Element source, Element target) {
			return canExistModelicaComposition_3004(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateRationaleRelationship_3005(
				Package container, ModelicaClassifier source,
				ModelicaRationale target) {
			return canExistRationaleRelationship_3005(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateSatisfiedByRelationship_3006(
				Package container, Requirement source, SatisfiedBy target) {
			return canExistSatisfiedByRelationship_3006(container, source,
					target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateGeneralRelationship_3007(
				Package container, Element source, Element target) {
			return canExistGeneralRelationship_3007(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateRequirementHierarchyRelationship_3008(
				Package container, Element source, Requirement target) {
			return canExistRequirementHierarchyRelationship_3008(container,
					source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canExistGeneralization_3001(Classifier source,
				Classifier target) {
			if (!evaluate(Generalization_3001_SourceExpression, source, target,
					false)) {
				return false;
			}
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistSatisfyRelationship_3002(
				Package container, Requirement source, ModelicaClass target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistDeriveReqRelationship_3003(
				Package container, Requirement source, Requirement target) {
			if (!evaluate(DeriveReqRelationship_3003_SourceExpression, source,
					target, false)) {
				return false;
			}
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistModelicaComposition_3004(
				Package container, Element source, Element target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistRationaleRelationship_3005(
				Package container, ModelicaClassifier source,
				ModelicaRationale target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistSatisfiedByRelationship_3006(
				Package container, Requirement source, SatisfiedBy target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistGeneralRelationship_3007(
				Package container, Element source, Element target) {
			return true;
		}

		/**
		 * @generated
		 */
		public static boolean canExistRequirementHierarchyRelationship_3008(
				Package container, Element source, Requirement target) {
			if (!evaluate(
					RequirementHierarchyRelationship_3008_SourceExpression,
					source, target, false)) {
				return false;
			}
			return true;
		}

		/**
		 * @generated 
		 */
		private static boolean evaluate(SysmlAbstractExpression constraint,
				Object sourceEnd, Object oppositeEnd, boolean clearEnv) {
			if (sourceEnd == null) {
				return true;
			}
			Map evalEnv = Collections.singletonMap(OPPOSITE_END_VAR,
					oppositeEnd);
			try {
				Object val = constraint.evaluate(sourceEnd, evalEnv);
				return (val instanceof Boolean) ? ((Boolean) val)
						.booleanValue() : false;
			} catch (Exception e) {
				SysmlDiagramEditorPlugin.getInstance().logError(
						"Link constraint evaluation error", e); //$NON-NLS-1$
				return false;
			}
		}
	}
}
