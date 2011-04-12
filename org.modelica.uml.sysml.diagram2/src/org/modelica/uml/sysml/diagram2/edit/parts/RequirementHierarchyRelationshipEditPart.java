package org.modelica.uml.sysml.diagram2.edit.parts;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IAnchorableFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ForestRouter;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ManhattanConnectionRouter;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;

import org.modelica.uml.sysml.diagram2.edit.policies.RequirementHierarchyRelationshipItemSemanticEditPolicy;

/**
 * @generated
 */
public class RequirementHierarchyRelationshipEditPart extends
		ConnectionNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3008;

	/**
	 * @generated
	 */
	public RequirementHierarchyRelationshipEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new RequirementHierarchyRelationshipItemSemanticEditPolicy());
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

	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
	}

	/**
	 * @generated
	 */
	public class RationaleRelationshipFigure extends PolylineConnectionEx {

		/**
		 * @generated NOT
		 */
		public RationaleRelationshipFigure() {
			int[] _dashes = { 5, 5 };
			this.setLineStyle(Graphics.LINE_CUSTOM);
			this.setRoutingStyles(false, false);
			this.setLineDash(_dashes);
			//this.setConnectionRouter(new ForestRouter());

		}

		/**
		 * 
		 */
		public void anchorMoved(ConnectionAnchor anchor) {
			super.anchorMoved(anchor);
		}

	}

}
