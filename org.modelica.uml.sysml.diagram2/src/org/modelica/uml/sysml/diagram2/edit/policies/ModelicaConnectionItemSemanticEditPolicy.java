package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.GeneralizationCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaCompositionCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RationaleRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.RequirementHierarchyRelationshipCreateCommand;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 */
public class ModelicaConnectionItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getMSLWrapper(new DestroyElementCommand(req));
	}
	
	/**
	 * @generated NOT
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
		if (SysmlElementTypes.ModelicaConnection_3009 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
				return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (SysmlElementTypes.ModelicaConnection_3009 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
				return null;
	}


}
