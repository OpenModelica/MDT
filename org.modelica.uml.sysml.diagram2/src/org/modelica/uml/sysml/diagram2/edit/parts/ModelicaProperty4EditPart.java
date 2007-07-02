package org.modelica.uml.sysml.diagram2.edit.parts;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;

import org.eclipse.gef.Request;

import org.eclipse.gef.commands.Command;

import org.eclipse.gef.editpolicies.LayoutEditPolicy;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;

import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;

import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;

import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.diagram2.edit.figures.InModelicaPortFigure;
import org.modelica.uml.sysml.diagram2.edit.figures.InOutModelicaPortFigure;
import org.modelica.uml.sysml.diagram2.edit.figures.ModelicaPortFigure;
import org.modelica.uml.sysml.diagram2.edit.figures.ModelicaPortOwnerFigure;
import org.modelica.uml.sysml.diagram2.edit.figures.OutModelicaPortFigure;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaProperty4CanonicalEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaProperty4GraphicalNodeEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaProperty4ItemSemanticEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlTextSelectionEditPolicy;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated NOT
 */
public class ModelicaProperty4EditPart extends PortOwnerShapeEditPart {

	public static int portWidth = 120;

	public static int portHeight = 30;

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2004;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	protected List inPorts = new ArrayList();

	protected List outPorts = new ArrayList();

	protected List inoutPorts = new ArrayList();

	/**
	 * @generated
	 */
	public ModelicaProperty4EditPart(View view) {
		super(view);
	}

	public NodeFigure createOwnedPortFigure(ModelicaPortEditPart portPart) {
		ModelicaProperty connector = (ModelicaProperty) ViewUtil
				.resolveSemanticElement((View) portPart.getModel());

		if (connector == null) {
			return null;
		}

		NodeFigure theFigure = null;
		String portName = null;
		FlowDirection direction = null;

		if (connector.getOwner() instanceof ModelicaComposition) {
			portName = ((ModelicaComposition) connector.getOwner()).getName();
			direction = ((ModelicaComposition) connector.getOwner())
					.getDirection();
		} else {
			portName = connector.getName();
			direction = connector.getDirection();
		}

		String portType = connector.getType() != null ? connector.getType()
				.getName() : "";

		int side = PositionConstants.WEST;
		if (direction.equals(FlowDirection.INOUT_LITERAL)) {
			side = PositionConstants.WEST;
			theFigure = new InOutModelicaPortFigure(portName, portType);
		} else if (direction.equals(FlowDirection.IN_LITERAL)) {
			side = PositionConstants.WEST;
			theFigure = new InModelicaPortFigure(portName, portType);
		} else if (direction.equals(FlowDirection.OUT_LITERAL)) {
			side = PositionConstants.EAST;
			theFigure = new OutModelicaPortFigure(portName, portType);
		}

		portPart.setLocator(new ModelicaPortFigure.TerminalLocator(getFigure(),
				side));

		return theFigure;
	}

	/**
	 */
	public Map createBoundsMap() {
		return null;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModelicaProperty4ItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ModelicaProperty4GraphicalNodeEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ModelicaProperty4CanonicalEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * Creates a new Modelica Property Figure and returns it.
	 * 
	 * @return Figure representing the property;
	 */
	protected NodeFigure createMainFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * @generated NOT
	 */
	protected IFigure createNodeShape() {
		NodeFigure figure = new ModelicaPortOwnerFigure(new Dimension(
				getMapMode().DPtoLP(100), getMapMode().DPtoLP(100)));
		return primaryShape = figure;
	}

	/**
	 * @generated NOT
	 */
	public ModelicaPortOwnerFigure getPrimaryShape() {
		return (ModelicaPortOwnerFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ModelicaPropertyNameEditPart) {
			((ModelicaPropertyNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigurePartProperyName());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		return false;
	}

	/**
	 * @generated NOT
	 */
	protected NodeFigure createNodePlate() {
		return new DefaultSizeNodeFigure(getMapMode().DPtoLP(100), getMapMode()
				.DPtoLP(100));
	}

	/**
	 * Default implementation treats passed figure as content pane. Respects
	 * layout one may have set for generated figure.
	 * 
	 * @param nodeShape
	 *            instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(getMapMode().DPtoLP(5));
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(SysmlVisualIDRegistry
				.getType(ModelicaPropertyNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated NOT
	 */
	protected void handleNotificationEvent(Notification event) {
		Object feature = event.getFeature();

		if (feature instanceof EAttribute || feature instanceof EReference) {
			// refreshLabel();
		}

		/*
		 
		 if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
		 Integer c = (Integer) event.getNewValue();
		 setFontColor(DiagramColorRegistry.getInstance().getColor(c));
		 } else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(
		 feature)) {
		 refreshUnderline();
		 } else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough()
		 .equals(feature)) {
		 refreshStrikeThrough();
		 } else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(
		 feature)
		 || NotationPackage.eINSTANCE.getFontStyle_FontName().equals(
		 feature)
		 || NotationPackage.eINSTANCE.getFontStyle_Bold()
		 .equals(feature)
		 || NotationPackage.eINSTANCE.getFontStyle_Italic().equals(
		 feature)) {
		 refreshFont();
		 } else {
		 if (getParser() != null
		 && getParser().isAffectingEvent(event,
		 getParserOptions().intValue())) {
		 refreshLabel();
		 }
		 if (getParser() instanceof ISemanticParser) {
		 ISemanticParser modelParser = (ISemanticParser) getParser();
		 if (modelParser.areSemanticElementsAffected(null, event)) {
		 removeSemanticListeners();
		 if (resolveSemanticElement() != null) {
		 addSemanticListeners();
		 }
		 refreshLabel();
		 }
		 }		
		 }
		 */

		super.handleNotificationEvent(event);
	}

}
