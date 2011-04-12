package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.edit.commands.SatisfiedByPropertyCreateCommand;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class SatisfiedBySatisfiedByCompartmentItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SysmlElementTypes.SatisfiedByProperty_2007 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(SysmlPackage.eINSTANCE
						.getSatisfiedBy_SatisfiedBy());
			}
			return getGEFWrapper(new SatisfiedByPropertyCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
