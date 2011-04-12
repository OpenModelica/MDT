package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaClassCreateCommand;
import org.modelica.uml.sysml.diagram2.edit.commands.ModelicaPropertyCreateCommand;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaClassNested2ItemSemanticEditPolicy extends
		SysmlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SysmlElementTypes.ModelicaClass_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getClass_NestedClassifier());
			}
			return getGEFWrapper(new ModelicaClassCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
