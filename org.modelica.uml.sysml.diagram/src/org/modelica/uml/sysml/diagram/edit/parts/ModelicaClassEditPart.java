package org.modelica.uml.sysml.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

import org.eclipse.gef.commands.Command;

import org.eclipse.gef.editparts.LayerManager;

import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;

import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;

import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

import org.modelica.uml.sysml.diagram.edit.policies.ModelicaClassCanonicalEditPolicy;
import org.modelica.uml.sysml.diagram.edit.policies.ModelicaClassGraphicalNodeEditPolicy;
import org.modelica.uml.sysml.diagram.edit.policies.ModelicaClassItemSemanticEditPolicy;
import org.modelica.uml.sysml.diagram.edit.policies.SysmlTextSelectionEditPolicy;

import org.modelica.uml.sysml.diagram.part.SysmlVisualIDRegistry;
import org.modelica.uml.sysml.diagram.providers.SysmlElementTypes;

import org.modelica.uml.sysml.ModelicaBlock;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.ModelicaFunction;
import org.modelica.uml.sysml.ModelicaModel;
import org.modelica.uml.sysml.ModelicaRecord;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * @generated
 */
public class ModelicaClassEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1001;

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
	public ModelicaClassEditPart(View view) {
		super(view);
	}

	/**
	 */
	protected void addNotationalListeners() {
		super.addNotationalListeners();
		addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
	}

	/**
	 */
	protected void removeNotationalListeners() {
		super.removeNotationalListeners();
		removeListenerFilter("PrimaryView"); //$NON-NLS-1$
	}

	protected void handleNotificationEvent(Notification event) {

		if (event.getFeature() instanceof EAttribute) {
			EAttribute umlProperty = (EAttribute) event.getFeature();
			if (umlProperty.getName().equals("isAbstract")) {

				if (event.getNewBooleanValue()) {
					getPrimaryShape().getFigureSpecifiaction().setVisible(true);
					getPrimaryShape().getFigureSpecifiaction().setText(
							"{partial}");
				} else {
					getPrimaryShape().getFigureSpecifiaction()
							.setVisible(false);
					getPrimaryShape().getPreferredSize(1, 1);
				}
			}
		}
		super.handleNotificationEvent(event);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy() {

					public Command getCommand(Request request) {
						if (understandsRequest(request)) {
							if (request instanceof CreateViewAndElementRequest) {
								CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request)
										.getViewAndElementDescriptor()
										.getCreateElementRequestAdapter();
								IElementType type = (IElementType) adapter
										.getAdapter(IElementType.class);
								if (type == SysmlElementTypes.ModelicaProperty_2001) {
									EditPart compartmentEditPart = getChildBySemanticHint(SysmlVisualIDRegistry
											.getType(ModelicaClassParametersEditPart.VISUAL_ID));
									return compartmentEditPart == null ? null
											: compartmentEditPart
													.getCommand(request);
								}
								if (type == SysmlElementTypes.ModelicaProperty_2002) {
									EditPart compartmentEditPart = getChildBySemanticHint(SysmlVisualIDRegistry
											.getType(ModelicaClassPartsEditPart.VISUAL_ID));
									return compartmentEditPart == null ? null
											: compartmentEditPart
													.getCommand(request);
								}
								if (type == SysmlElementTypes.ModelicaProperty_2003) {
									EditPart compartmentEditPart = getChildBySemanticHint(SysmlVisualIDRegistry
											.getType(ModelicaClassValuesEditPart.VISUAL_ID));
									return compartmentEditPart == null ? null
											: compartmentEditPart
													.getCommand(request);
								}
							}
							return super.getCommand(request);
						}
						return null;
					}
				});
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModelicaClassItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ModelicaClassGraphicalNodeEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ModelicaClassCanonicalEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		ConstrainedToolbarLayoutEditPolicy lep = new ConstrainedToolbarLayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				if (child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE) == null) {
					if (child instanceof ITextAwareEditPart) {
						return new SysmlTextSelectionEditPolicy();
					}
				}
				return super.createChildEditPolicy(child);
			}
		};
		return lep;
	}

	/**
	 * @generated NOT
	 */
	protected IFigure createNodeShape() {
		LabeledStereotypedFigure figure = new LabeledStereotypedFigure();

		View model = (View) this.getModel();
		if (model.getElement() instanceof ModelicaModel) {
			figure.getFigureStereotypeName().setText("<<model>>");
		} else if (model.getElement() instanceof ModelicaBlock) {
			figure.getFigureStereotypeName().setText("<<block>>");
		} else if (model.getElement() instanceof ModelicaRecord) {
			figure.getFigureStereotypeName().setText("<<record>>");
		} else if (model.getElement() instanceof ModelicaConnector) {
			figure.getFigureStereotypeName().setText("<<connector>>");
		} else if (model.getElement() instanceof ModelicaFunction) {
			figure.getFigureStereotypeName().setText("<<function>>");
		} else if (model.getElement() instanceof ModelicaClass) {
			figure.getFigureStereotypeName().setText("<<class>>");
		}

		figure.getFigureSpecifiaction().setVisible(false);
		figure.getFigureSpecifiaction().setSize(0, 0);

		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public LabeledStereotypedFigure getPrimaryShape() {
		return (LabeledStereotypedFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ModelicaClassNameEditPart) {
			((ModelicaClassNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureStereotypedNodeName());
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
		return new DefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode()
				.DPtoLP(40));
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so
	 * you may safely remove <i>generated</i> tag and modify it.
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
				.getType(ModelicaClassNameEditPart.VISUAL_ID));
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
	public class LabeledStereotypedFigure extends
			org.eclipse.draw2d.RectangleFigure {

		/**
		 * @generated
		 */
		public LabeledStereotypedFigure() {

			org.eclipse.draw2d.ToolbarLayout myGenLayoutManager = new org.eclipse.draw2d.ToolbarLayout();
			myGenLayoutManager.setStretchMinorAxis(true);
			myGenLayoutManager
					.setMinorAlignment(org.eclipse.draw2d.ToolbarLayout.ALIGN_CENTER);
			myGenLayoutManager.setSpacing(0);
			myGenLayoutManager.setVertical(true);

			this.setLayoutManager(myGenLayoutManager);

			this.setBackgroundColor(LABELEDSTEREOTYPEDFIGURE_BACK

			);
			this.setFont(LABELEDSTEREOTYPEDFIGURE_FONT);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_0 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fig_0.setText("<<stereotype>>");
			fig_0.setFont(STEREOTYPENAME_FONT);

			setFigureStereotypeName(fig_0);

			Object layData0 = null;

			this.add(fig_0, layData0);
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_1 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();

			fig_1.setFont(STEREOTYPEDNODENAME_FONT);

			setFigureStereotypedNodeName(fig_1);

			Object layData1 = null;

			this.add(fig_1, layData1);
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_2 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fig_2.setText("{}");
			fig_2.setForegroundColor(org.eclipse.draw2d.ColorConstants.gray

			);
			fig_2.setFont(SPECIFIACTION_FONT);

			setFigurespecifiaction(fig_2);

			Object layData2 = null;

			this.add(fig_2, layData2);
		}

		/**
		 * @generated
		 */
		private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fStereotypeName;

		/**
		 * @generated
		 */
		public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigureStereotypeName() {
			return fStereotypeName;
		}

		/**
		 * @generated
		 */
		private void setFigureStereotypeName(
				org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
			fStereotypeName = fig;
		}

		/**
		 * @generated
		 */
		private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fStereotypedNodeName;

		/**
		 * @generated
		 */
		public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigureStereotypedNodeName() {
			return fStereotypedNodeName;
		}

		/**
		 * @generated
		 */
		private void setFigureStereotypedNodeName(
				org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
			fStereotypedNodeName = fig;
		}

		/**
		 * @generated
		 */
		private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fSpecifiaction;

		/**
		 * @generated
		 */
		public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigureSpecifiaction() {
			return fSpecifiaction;
		}

		/**
		 * @generated
		 */
		private void setFigurespecifiaction(
				org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
			fSpecifiaction = fig;
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
	 * @generated
	 */
	public static final org.eclipse.swt.graphics.Font LABELEDSTEREOTYPEDFIGURE_FONT = new org.eclipse.swt.graphics.Font(
			org.eclipse.swt.widgets.Display.getCurrent(), "Tahoma", 8,
			org.eclipse.swt.SWT.NORMAL);

	/**
	 * @generated
	 */
	public static final org.eclipse.swt.graphics.Font STEREOTYPEDNODENAME_FONT = new org.eclipse.swt.graphics.Font(
			org.eclipse.swt.widgets.Display.getCurrent(), "Tahoma", 8,
			org.eclipse.swt.SWT.NORMAL);

	/**
	 * @generated NOT
	 */
	public static final org.eclipse.swt.graphics.Color LABELEDSTEREOTYPEDFIGURE_BACK = new org.eclipse.swt.graphics.Color(
			null, 255, 255, 225);

	/**
	 * @generated
	 */
	public static final org.eclipse.swt.graphics.Font STEREOTYPENAME_FONT = new org.eclipse.swt.graphics.Font(
			org.eclipse.swt.widgets.Display.getCurrent(), "Tahoma", 8,
			org.eclipse.swt.SWT.NORMAL);

	/**
	 * @generated
	 */
	public static final org.eclipse.swt.graphics.Font SPECIFIACTION_FONT = new org.eclipse.swt.graphics.Font(
			org.eclipse.swt.widgets.Display.getCurrent(), "Tahoma", 8,
			org.eclipse.swt.SWT.NORMAL);

}
