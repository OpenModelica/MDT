package org.modelica.uml.sysml.diagram2.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import org.eclipse.gef.commands.Command;

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

import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

import org.modelica.uml.sysml.ModelicaBlock;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.ModelicaFunction;
import org.modelica.uml.sysml.ModelicaModel;
import org.modelica.uml.sysml.ModelicaRecord;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart.ClassFigure;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaClass2CanonicalEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaClass2GraphicalNodeEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaClass2ItemSemanticEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlTextSelectionEditPolicy;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaClass2EditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2005;

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
	public ModelicaClass2EditPart(View view) {
		super(view);
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
											.getType(ModelicaClassParameters2EditPart.VISUAL_ID));
									return compartmentEditPart == null ? null
											: compartmentEditPart
													.getCommand(request);
								}
								if (type == SysmlElementTypes.ModelicaProperty_2002) {
									EditPart compartmentEditPart = getChildBySemanticHint(SysmlVisualIDRegistry
											.getType(ModelicaClassParts2EditPart.VISUAL_ID));
									return compartmentEditPart == null ? null
											: compartmentEditPart
													.getCommand(request);
								}
								if (type == SysmlElementTypes.ModelicaProperty_2003) {
									EditPart compartmentEditPart = getChildBySemanticHint(SysmlVisualIDRegistry
											.getType(ModelicaClassVariables2EditPart.VISUAL_ID));
									return compartmentEditPart == null ? null
											: compartmentEditPart
													.getCommand(request);
								}
								if (type == SysmlElementTypes.ModelicaEquationProperty_2006) {
									EditPart compartmentEditPart = getChildBySemanticHint(SysmlVisualIDRegistry
											.getType(ModelicaClassEquations2EditPart.VISUAL_ID));
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
				new ModelicaClass2ItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ModelicaClass2GraphicalNodeEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ModelicaClass2CanonicalEditPolicy());
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
	 * @generated NOT
	 */
	protected IFigure createNodeShape() {
		ClassFigure figure = new ClassFigure();

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

		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public ClassFigure getPrimaryShape() {
		return (ClassFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ModelicaClassNameEditPart) {
			((ModelicaClassNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureClassName());
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

	protected void refreshBackgroundColor() {

		if (((View) this.getModel()).getElement() instanceof ModelicaConnector) {
			this.setBackgroundColor(YELLOW_BACKGROUND_COLOR);
		} else {
			this.setBackgroundColor(YELLOW_BACKGROUND_COLOR);
		}
	}

	protected void refreshFont() {
		FontStyle style = (FontStyle) this.getPrimaryView().getStyle(
				NotationPackage.eINSTANCE.getFontStyle());
		if (style != null) {
			FontData fontData = new FontData(style.getFontName(), 8,
					(style.isBold() ? SWT.BOLD : SWT.NORMAL)
							| (style.isItalic() ? SWT.ITALIC : SWT.NORMAL));
			setFont(fontData);
		}
	}

	/**
	 * @generated
	 */
	public class ClassFigure extends org.eclipse.draw2d.RectangleFigure {

		/**
		 * @generated NOT
		 */
		public ClassFigure() {

			this.setPreferredSize(getMapMode().DPtoLP(166), getMapMode()
					.DPtoLP(180));
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_0 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fig_0.setText("<<stereotype>>");

			setFigureStereotypeName(fig_0);

			Object layData0 = null;

			this.add(fig_0, layData0);
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_1 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fig_1.setText("<..>");

			setFigureClassName(fig_1);

			Object layData1 = null;

			this.add(fig_1, layData1);
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
		private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fClassName;

		/**
		 * @generated
		 */
		public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigureClassName() {
			return fClassName;
		}

		/**
		 * @generated
		 */
		private void setFigureClassName(
				org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
			fClassName = fig;
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

	public static final org.eclipse.swt.graphics.Color YELLOW_BACKGROUND_COLOR = new org.eclipse.swt.graphics.Color(
			null, 255, 255, 225);

	public static final org.eclipse.swt.graphics.Color LIGHTGRAY_BACKGROUND_COLOR = new org.eclipse.swt.graphics.Color(
			null, 247, 247, 247);

}
