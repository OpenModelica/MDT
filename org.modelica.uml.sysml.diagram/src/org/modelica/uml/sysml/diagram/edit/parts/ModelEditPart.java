package org.modelica.uml.sysml.diagram.edit.parts;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.uml2.uml.Model;

import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.diagram.edit.policies.ModelCanonicalEditPolicy;
import org.modelica.uml.sysml.diagram.edit.policies.ModelItemSemanticEditPolicy;

/**
 * @generated
 */
public class ModelEditPart extends DiagramEditPart {

	/**
	 * @generated
	 */
	public static String MODEL_ID = "Sysml"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 79;

	/**
	 * @generated
	 */
	public ModelEditPart(View view) {
		super(view);
	}

	@Override
	public void activate() {
		super.activate();

		final Model model = (Model) this.getDiagramView().getElement();
		TransactionalEditingDomain editingDomain = this.getEditingDomain();
		editingDomain.getCommandStack().execute(
				new RecordingCommand(editingDomain) {
					protected void doExecute() {

						if (model != null) {
							if (model.getOwnedType("Real") == null) {
							//	model.createOwnedType("PrimitiveReal",
							//			SysmlPackage.Literals.MODELICA_REAL);
								 model.createOwnedPrimitiveType("Real");
							}
							if (model.getOwnedType("String") == null) {
							//	model.createOwnedType("PrimitiveString",
								//		SysmlPackage.Literals.MODELICA_STRING);
								 model.createOwnedPrimitiveType("String");
							}
							if (model.getOwnedType("Integer") == null) {
							//	model.createOwnedType("PrimitiveString",
								//		SysmlPackage.Literals.MODELICA_INTEGER);
								 model.createOwnedPrimitiveType("Integer");
							}
							if (model.getOwnedType("Boolean") == null) {
							//	model.createOwnedType("PrimitiveBoolean",
								//		SysmlPackage.Literals.MODELICA_BOOLEAN);
								model.createOwnedPrimitiveType("Boolean");
							}
						}
					}
				});
		editingDomain.getCommandStack().flush();
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModelItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ModelCanonicalEditPolicy());
	}
}
