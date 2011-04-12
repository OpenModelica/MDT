package org.modelica.uml.sysml.diagram2.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.GeneralRelationship;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralizationCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralizationReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaCompositionCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaCompositionReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RationaleRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RationaleRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RequirementHierarchyRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RequirementHierarchyRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.SatisfyRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.SatisfyRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralizationEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClass2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnections2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquations2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNested2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParameters2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParts2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariables2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty3EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty4EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RationaleRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementHierarchyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;
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
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyChildNodesCommand(cc);
		addDestroyShortcutsCommand(cc);
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(CompoundCommand cmd) {
		View view = (View) getHost().getModel();
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation != null) {
			return;
		}
		for (Iterator it = view.getChildren().iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			switch (SysmlVisualIDRegistry.getVisualID(node)) {
			case ModelicaClassParameters2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (SysmlVisualIDRegistry.getVisualID(cnode)) {
					case ModelicaPropertyEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case ModelicaClassParts2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (SysmlVisualIDRegistry.getVisualID(cnode)) {
					case ModelicaProperty2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case ModelicaClassVariables2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (SysmlVisualIDRegistry.getVisualID(cnode)) {
					case ModelicaProperty3EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case ModelicaClassConnections2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (SysmlVisualIDRegistry.getVisualID(cnode)) {
					case ModelicaProperty4EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case ModelicaClassNested2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (SysmlVisualIDRegistry.getVisualID(cnode)) {
					case ModelicaClass2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case ModelicaClassEquations2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (SysmlVisualIDRegistry.getVisualID(cnode)) {
					case ModelicaEquationPropertyEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (SysmlElementTypes.Generalization_3001 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.SatisfyRelationship_3002 == req.getElementType()) {
			return null;
		}
		if (SysmlElementTypes.ModelicaComposition_3004 == req.getElementType()) {
			return getGEFWrapper(new ModelicaCompositionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.RationaleRelationship_3005 == req
				.getElementType()) {
			return getGEFWrapper(new RationaleRelationshipCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.GeneralRelationship_3007 == req.getElementType()) {
			return getGEFWrapper(new GeneralRelationshipCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.RequirementHierarchyRelationship_3008 == req
				.getElementType()) {
			return getGEFWrapper(new RequirementHierarchyRelationshipCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (SysmlElementTypes.Generalization_3001 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.SatisfyRelationship_3002 == req.getElementType()) {
			return getGEFWrapper(new SatisfyRelationshipCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.ModelicaComposition_3004 == req.getElementType()) {
			return getGEFWrapper(new ModelicaCompositionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.RationaleRelationship_3005 == req
				.getElementType()) {
			return null;
		}
		if (SysmlElementTypes.GeneralRelationship_3007 == req.getElementType()) {
			return getGEFWrapper(new GeneralRelationshipCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.RequirementHierarchyRelationship_3008 == req
				.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case GeneralizationEditPart.VISUAL_ID:
			return getGEFWrapper(new GeneralizationReorientCommand(req));
		case SatisfyRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new SatisfyRelationshipReorientCommand(req));
		case ModelicaCompositionEditPart.VISUAL_ID:
			return getGEFWrapper(new ModelicaCompositionReorientCommand(req));
		case RationaleRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new RationaleRelationshipReorientCommand(req));
		case GeneralRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new GeneralRelationshipReorientCommand(req));
		case RequirementHierarchyRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new RequirementHierarchyRelationshipReorientCommand(
					req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
