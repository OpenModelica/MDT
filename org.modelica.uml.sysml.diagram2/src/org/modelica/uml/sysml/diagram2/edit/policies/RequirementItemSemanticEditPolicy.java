package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.gef.commands.UnexecutableCommand;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.DeriveReqRelationship;
import org.modelica.uml.sysml.GeneralRelationship;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.RequirementHierarchyRelationship;
import org.modelica.uml.sysml.SatisfyRelationship;

import org.modelica.uml.sysml.diagram2.edit.commands.DeriveReqRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.DeriveReqRelationshipReorientCommand;
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
import org.modelica.uml.sysml.diagram2.edit.commands.SatisfiedByRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.SatisfiedByRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.SatisfyRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.SatisfyRelationshipReorientCommand;
import org.modelica.uml.sysml.diagram2.edit.parts.DeriveReqRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralizationEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RationaleRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementHierarchyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiedByRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class RequirementItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyShortcutsCommand(cc);
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
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
			return getGEFWrapper(new SatisfyRelationshipCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.DeriveReqRelationship_3003 == req
				.getElementType()) {
			return getGEFWrapper(new DeriveReqRelationshipCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.ModelicaComposition_3004 == req.getElementType()) {
			return getGEFWrapper(new ModelicaCompositionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.SatisfiedByRelationship_3006 == req
				.getElementType()) {
			return getGEFWrapper(new SatisfiedByRelationshipCreateCommand(req,
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
			return null;
		}
		if (SysmlElementTypes.DeriveReqRelationship_3003 == req
				.getElementType()) {
			return getGEFWrapper(new DeriveReqRelationshipCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.ModelicaComposition_3004 == req.getElementType()) {
			return getGEFWrapper(new ModelicaCompositionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (SysmlElementTypes.SatisfiedByRelationship_3006 == req
				.getElementType()) {
			return null;
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
		case DeriveReqRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new DeriveReqRelationshipReorientCommand(req));
		case ModelicaCompositionEditPart.VISUAL_ID:
			return getGEFWrapper(new ModelicaCompositionReorientCommand(req));
		case SatisfiedByRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new SatisfiedByRelationshipReorientCommand(req));
		case GeneralRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new GeneralRelationshipReorientCommand(req));
		case RequirementHierarchyRelationshipEditPart.VISUAL_ID:
			return getGEFWrapper(new RequirementHierarchyRelationshipReorientCommand(
					req));
		}
		return super.getReorientRelationshipCommand(req);
	}
}
