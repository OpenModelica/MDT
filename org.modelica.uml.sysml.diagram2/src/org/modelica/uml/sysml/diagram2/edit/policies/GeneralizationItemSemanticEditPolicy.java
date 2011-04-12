package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaCompositionCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaCompositionReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RequirementHierarchyRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RequirementHierarchyRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementHierarchyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class GeneralizationItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
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
		if (SysmlElementTypes.ModelicaComposition_3004 == req.getElementType()) {
			return getGEFWrapper(new ModelicaCompositionCreateCommand(req, req
					.getSource(), req.getTarget()));
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
		if (SysmlElementTypes.ModelicaComposition_3004 == req.getElementType()) {
			return getGEFWrapper(new ModelicaCompositionCreateCommand(req, req
					.getSource(), req.getTarget()));
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
		case ModelicaCompositionEditPart.VISUAL_ID:
			return getGEFWrapper(new ModelicaCompositionReorientCommand(req));
		case GeneralRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new GeneralRelationshipReorientCommand(req));
		case RequirementHierarchyRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new RequirementHierarchyRelationshipReorientCommand(
					req));
		}
		return super.getReorientRelationshipCommand(req);
	}
}
