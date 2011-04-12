package org.modelica.uml.sysml.diagram2.edit.parts;

import java.util.Iterator;
import org.eclipse.draw2d.FlowLayout;

import java.awt.ScrollPane;
import java.lang.Math;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.BlockFlow;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.draw2d.ScrollBar;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.swt.graphics.Font;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.ConnectionEditPart;

import org.eclipse.gef.commands.Command;

import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;

import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IAnchorableFigure;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.dnd.TextTransfer;

import org.modelica.uml.sysml.diagram2.edit.policies.RequirementCanonicalEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.RequirementGraphicalNodeEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.RequirementItemSemanticEditPolicy;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class RequirementEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1004;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public RequirementEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new RequirementItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
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
	 * @generated
	 */
	protected IFigure createNodeShape() {
		RequirementFigure figure = new RequirementFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public RequirementFigure getPrimaryShape() {
		return (RequirementFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof RequirementNameEditPart) {
			((RequirementNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureRequirementName());
			return true;
		}
		if (childEditPart instanceof RequirementDescriptionEditPart) {
			((RequirementDescriptionEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureRequirementDescriptionValue());
			return true;
		}
		if (childEditPart instanceof RequirementIdEditPart) {
			((RequirementIdEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureRequirementIDValue());
			return true;
		}
		if (childEditPart instanceof RequirementLevelEditPart) {
			((RequirementLevelEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureRequirementLevelValue());
			return true;
		}
		if (childEditPart instanceof RequirementStatusEditPart) {
			((RequirementStatusEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureRequirementStatusValue());
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
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(200), getMapMode().DPtoLP(150));
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
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
				.getType(RequirementNameEditPart.VISUAL_ID));
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
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {

		return super.getContentPaneFor(editPart);
	}

	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
	}

	/**
	 * @generated NOT
	 */
	public class RequirementFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrapLabel fFigureRequirementName;
		/**
		 * @generated
		 */
		private WrapLabel fFigureRequirementDescriptionValue;
		/**
		 * @generated
		 */
		private WrapLabel fFigureRequirementIDValue;
		/**
		 * @generated
		 */
		private WrapLabel fFigureRequirementLevelValue;
		/**
		 * @generated
		 */
		private WrapLabel fFigureRequirementStatusValue;

		/**
		 * @generated
		 */
		public RequirementFigure() {
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(200),
					getMapMode().DPtoLP(150)));
			createContents();
		}


		/**
		 * @generated NOT
		 */
		private void createContents() {

			WrapLabel stereotypeName0 = new WrapLabel();
			stereotypeName0.setText("<<requirement>>");

			this.add(stereotypeName0);

			fFigureRequirementName = new WrapLabel();
			fFigureRequirementName.setText("");

			this.add(fFigureRequirementName);

			final RectangleFigure requirementAttributesFigure0 = new RectangleFigure();
			requirementAttributesFigure0.setPreferredSize(new Dimension(
					getMapMode().DPtoLP(200), getMapMode().DPtoLP(100)));

			this.add(requirementAttributesFigure0);

			ToolbarLayout layoutRequirementAttributesFigure0 = new ToolbarLayout();
			layoutRequirementAttributesFigure0.setStretchMinorAxis(true);
			layoutRequirementAttributesFigure0
					.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);

			layoutRequirementAttributesFigure0.setSpacing(5);
			layoutRequirementAttributesFigure0.setVertical(true);

			requirementAttributesFigure0
					.setLayoutManager(layoutRequirementAttributesFigure0);

			WrapLabel requirementDescriptionHeader1 = new WrapLabel();
			requirementDescriptionHeader1.setText("Description: ");
			requirementDescriptionHeader1.setMinimumSize(new Dimension(
					getMapMode().DPtoLP(70), getMapMode().DPtoLP(30)));

			requirementAttributesFigure0.add(requirementDescriptionHeader1,
					new Rectangle(getMapMode().DPtoLP(30), getMapMode()
							.DPtoLP(0), getMapMode().DPtoLP(70), getMapMode()
							.DPtoLP(0)));
			requirementAttributesFigure0.setBorder(new MarginBorder(1));

			fFigureRequirementDescriptionValue = new WrapLabel();
			// org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_4 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fFigureRequirementDescriptionValue
					.setText("Type new description here");

//			fFigureRequirementDescriptionValue.setSize(70, 70);
			fFigureRequirementDescriptionValue.setPreferredSize(70, 70);
			fFigureRequirementDescriptionValue.setTextWrap(true);
			fFigureRequirementDescriptionValue.setTextWrapAlignment(70);
			fFigureRequirementDescriptionValue.setTextWrapWidth(4);

			// scrolling description here

			org.eclipse.draw2d.geometry.Rectangle layData4 = new org.eclipse.draw2d.geometry.Rectangle();
			layData4.x = getMapMode().DPtoLP(0);
			layData4.y = getMapMode().DPtoLP(3);
			layData4.width = getMapMode().DPtoLP(100);
			layData4.height = getMapMode().DPtoLP(70);
						
			org.eclipse.draw2d.ScrollPane descriptionPane = new org.eclipse.draw2d.ScrollPane();
			org.eclipse.draw2d.ScrollPaneLayout layouterPane = new org.eclipse.draw2d.ScrollPaneLayout();

			descriptionPane.setScrollBarVisibility(org.eclipse.draw2d.ScrollPane.AUTOMATIC);
			descriptionPane.getViewport().setPreferredSize(
					new org.eclipse.draw2d.geometry.Dimension(getMapMode()
							.DPtoLP(100), getMapMode().DPtoLP(100)));

			descriptionPane.setContents(fFigureRequirementDescriptionValue);

//			 descriptionPane.getViewport().setContentsTracksHeight(true);
			 descriptionPane.getViewport().setContentsTracksWidth(true);
			requirementAttributesFigure0.add(descriptionPane);
			//end of scrolling description

			/*	
				fFigureRequirementDescriptionValue.setText("test");

				requirementAttributesFigure0.add(
						fFigureRequirementDescriptionValue, new Rectangle(
								getMapMode().DPtoLP(70), getMapMode().DPtoLP(0),
								getMapMode().DPtoLP(-1), getMapMode().DPtoLP(-1)));
			 */

			RectangleFigure requirementID1 = new RectangleFigure();
//			requirementID1.addFigureListener(new RowRectangleFigureListner());

			requirementID1.setOutline(false);
			requirementID1.setOutlineXOR(true);
			requirementAttributesFigure0.add(requirementID1);

			FlowLayout layoutRequirementID1 = new FlowLayout();
			layoutRequirementID1.setStretchMinorAxis(false);
			layoutRequirementID1.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

			layoutRequirementID1.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutRequirementID1.setMajorSpacing(5);
			layoutRequirementID1.setMinorSpacing(5);
			layoutRequirementID1.setHorizontal(true);

			requirementID1.setLayoutManager(layoutRequirementID1);

			WrapLabel requirementIDHeader2 = new WrapLabel();
			requirementIDHeader2.setText("ID: ");
			requirementIDHeader2.setPreferredSize(new Dimension(getMapMode()
					.DPtoLP(70), getMapMode().DPtoLP(20)));
			requirementIDHeader2.setMinimumSize(new Dimension(getMapMode()
					.DPtoLP(70), getMapMode().DPtoLP(20)));

			requirementID1.add(requirementIDHeader2);

			fFigureRequirementIDValue = new WrapLabel();
			fFigureRequirementIDValue.setText("id here");

			requirementID1.add(fFigureRequirementIDValue);

			RectangleFigure requirementLevel1 = new RectangleFigure();
			requirementLevel1.setOutline(false);

			requirementAttributesFigure0.add(requirementLevel1);

			FlowLayout layoutRequirementLevel1 = new FlowLayout();
			layoutRequirementLevel1.setStretchMinorAxis(false);
			layoutRequirementLevel1.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

			layoutRequirementLevel1.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutRequirementLevel1.setMajorSpacing(5);
			layoutRequirementLevel1.setMinorSpacing(5);
			layoutRequirementLevel1.setHorizontal(true);

			requirementLevel1.setLayoutManager(layoutRequirementLevel1);

			WrapLabel requirementLevelHeader2 = new WrapLabel();
			requirementLevelHeader2.setText("Level: ");
			requirementLevelHeader2.setPreferredSize(new Dimension(getMapMode()
					.DPtoLP(70), getMapMode().DPtoLP(20)));
			requirementLevelHeader2.setMinimumSize(new Dimension(getMapMode()
					.DPtoLP(70), getMapMode().DPtoLP(20)));

			requirementLevel1.add(requirementLevelHeader2);

			fFigureRequirementLevelValue = new WrapLabel();
			fFigureRequirementLevelValue.setText("0");

			requirementLevel1.add(fFigureRequirementLevelValue);

			RectangleFigure requirementStatus1 = new RectangleFigure();

			requirementStatus1.setOutline(false);

			requirementAttributesFigure0.add(requirementStatus1);

			FlowLayout layoutRequirementStatus1 = new FlowLayout();
			layoutRequirementStatus1.setStretchMinorAxis(false);
			layoutRequirementStatus1
					.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

			layoutRequirementStatus1
					.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutRequirementStatus1.setMajorSpacing(5);
			layoutRequirementStatus1.setMinorSpacing(5);
			layoutRequirementStatus1.setHorizontal(true);

			requirementStatus1.setLayoutManager(layoutRequirementStatus1);

			WrapLabel requirementStatusHeader2 = new WrapLabel();
			requirementStatusHeader2.setText("Status: ");
			requirementStatusHeader2.setPreferredSize(new Dimension(
					getMapMode().DPtoLP(70), getMapMode().DPtoLP(20)));
			requirementStatusHeader2.setMinimumSize(new Dimension(getMapMode()
					.DPtoLP(70), getMapMode().DPtoLP(20)));

			requirementStatus1.add(requirementStatusHeader2);

			fFigureRequirementStatusValue = new WrapLabel();
			fFigureRequirementStatusValue.setText("");

			requirementStatus1.add(fFigureRequirementStatusValue);

			this.setOpaque(true);

		}

		/**
		 * @generated
		 */
		public WrapLabel getFigureRequirementName() {
			return fFigureRequirementName;
		}

		/**
		 * @generated
		 */
		public WrapLabel getFigureRequirementDescriptionValue() {
			return fFigureRequirementDescriptionValue;
		}

		/**
		 * @generated
		 */
		public WrapLabel getFigureRequirementIDValue() {
			return fFigureRequirementIDValue;
		}

		/**
		 * @generated
		 */
		public WrapLabel getFigureRequirementLevelValue() {
			return fFigureRequirementLevelValue;
		}

		/**
		 * @generated
		 */
		public WrapLabel getFigureRequirementStatusValue() {
			return fFigureRequirementStatusValue;
		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

	}

	
	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {

		Point point = new Point(getFigure().getBounds().x
				+ getFigure().getBounds().width / 2, getFigure().getBounds().y
				+ getFigure().getBounds().height);

		ConnectionAnchor anchor = getNodeFigure().getSourceConnectionAnchorAt(
				point);
		return anchor;
	}

	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {

		Point point = new Point(getFigure().getBounds().x
				+ getFigure().getBounds().width / 2, getFigure().getBounds().y);
		PrecisionPoint pt = new PrecisionPoint(0.5, 0);
		ConnectionAnchor anchor = new SlidableAnchor(getFigure(), pt);

		return anchor;
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		ConnectionAnchor anchor = null;
		anchor = super.getTargetConnectionAnchor(connection);
		return anchor;

	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		ConnectionAnchor anchor = null;
		anchor = super.getSourceConnectionAnchor(connection);
		return anchor;

	}

}
