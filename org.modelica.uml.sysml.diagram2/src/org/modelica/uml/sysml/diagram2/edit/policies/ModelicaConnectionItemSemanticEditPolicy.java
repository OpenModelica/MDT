package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 */
public class ModelicaConnectionItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getMSLWrapper(new DestroyElementCommand(req));
	}
}
