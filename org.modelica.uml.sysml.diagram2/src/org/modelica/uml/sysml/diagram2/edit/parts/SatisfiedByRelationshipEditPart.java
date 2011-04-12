package org.modelica.uml.sysml.diagram2.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.draw2d.Connection;

import org.eclipse.draw2d.Graphics;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;

import org.modelica.uml.sysml.diagram2.edit.policies.SatisfiedByRelationshipItemSemanticEditPolicy;

/**
 * @generated
 */
public class SatisfiedByRelationshipEditPart extends ConnectionNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3006;

	/**
	 * @generated
	 */
	public SatisfiedByRelationshipEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new SatisfiedByRelationshipItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected Connection createConnectionFigure() {
		return new RationaleRelationshipFigure();
	}

	/**
	 * @generated
	 */
	public RationaleRelationshipFigure getPrimaryShape() {
		return (RationaleRelationshipFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class RationaleRelationshipFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public RationaleRelationshipFigure() {
			this.setLineStyle(Graphics.LINE_CUSTOM);

		}

	}

}
