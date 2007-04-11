package org.modelica.uml.sysml.diagram2.edit.parts;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaPortItemSemanticEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlExtNodeLabelHostLayoutEditPolicy;
import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

/**
 * A Port can sit on a border of a Modelica shape
 * 
 */
public class ModelicaPortEditPart extends AbstractBorderItemEditPart {

	public static final int VISUAL_ID = 2070;

	private BorderItemLocator locator;

	/**
	 * @param view
	 */
	public ModelicaPortEditPart(View view) {
		super(view);
	}

	/**
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		// installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new
		// PortGraphicalNodeEditPolicy());
		// installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new
		// PortCanonicalEditPolicy());

		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModelicaPortItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}

	/**
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		XYLayoutEditPolicy lep = new XYLayoutEditPolicy() {

			protected void decorateChild(EditPart child) {
				if (isExternalLabel(child)) {
					return;
				}
				super.decorateChild(child);
			}

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = super.createChildEditPolicy(child);
				if (result == null) {
					return new ResizableShapeEditPolicy();
				}
				return result;
			}
		};

		SysmlExtNodeLabelHostLayoutEditPolicy xlep = new SysmlExtNodeLabelHostLayoutEditPolicy() {

			protected boolean isExternalLabel(EditPart editPart) {
				return ModelicaPortEditPart.this.isExternalLabel(editPart);
			}
		};
		xlep.setRealLayoutEditPolicy(lep);
		return xlep;
	}

	protected NodeFigure createNodeFigure() {
		EditPart host = getParent();
		if (host instanceof IPortOwnerEditPart) {
			IFigure figure = ((IPortOwnerEditPart) host)
					.createOwnedPortFigure(this);
			figure.setLayoutManager(new XYLayout());
			return (NodeFigure) figure;
		}
		return null;
	}

	/**
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(SysmlVisualIDRegistry
				.getType(ModelicaPortNameEditPart.VISUAL_ID));
	}

	/**
	 */
	protected boolean isExternalLabel(EditPart childEditPart) {
		if (childEditPart instanceof ModelicaPortNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	 */
	protected IFigure getExternalLabelsContainer() {
		LayerManager root = (LayerManager) getRoot();
		return root.getLayer(SysmlEditPartFactory.EXTERNAL_NODE_LABELS_LAYER);
	}

	/**
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (isExternalLabel(childEditPart)) {
			IFigure labelFigure = ((GraphicalEditPart) childEditPart)
					.getFigure();
			getExternalLabelsContainer().add(labelFigure);
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 */
	protected void removeChildVisual(EditPart childEditPart) {

		if (isExternalLabel(childEditPart)) {
			IFigure labelFigure = ((GraphicalEditPart) childEditPart)
					.getFigure();
			getExternalLabelsContainer().remove(labelFigure);
			return;
		}

		super.removeChildVisual(childEditPart);
	}

	/**
	 */
	public void removeNotify() {

		for (Iterator it = getChildren().iterator(); it.hasNext();) {
			EditPart childEditPart = (EditPart) it.next();
			if (isExternalLabel(childEditPart)) {
				IFigure labelFigure = ((GraphicalEditPart) childEditPart)
						.getFigure();
				getExternalLabelsContainer().remove(labelFigure);
			}
		}

		super.removeNotify();
	}

	/*
	 * Don't allow terminal editparts to be selectable
	 */
	public boolean isSelectable() {
		return false;
	}

	public void activate() {
		super.activate();
		Insets parentInset = new Insets(0);
		IFigure fig = ((BorderItemLocator) getLocator()).getParentFigure();
		if (fig != null) {
			parentInset = fig.getInsets();
		}
		Rectangle rBounds = ((NodeFigure) getFigure()).getHandleBounds();
		((BorderItemLocator) getLocator()).setBorderItemOffset(new Dimension(
				rBounds.width / 2 + parentInset.getWidth() / 2, rBounds.height
						/ 2 + parentInset.getHeight() / 2));
	}

	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshFont();
	}

	protected void refreshFont() {
		FontStyle style = (FontStyle) getPrimaryView().getStyle(
				NotationPackage.eINSTANCE.getFontStyle());
		FontData fontData;
		fontData = new FontData(style.getFontName(), 8,
				(style.isBold() ? SWT.BOLD : SWT.NORMAL)
						| (style.isItalic() ? SWT.ITALIC : SWT.NORMAL));
		setFont(fontData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart#getLocator()
	 */
	public IBorderItemLocator getLocator() {
		return locator;
	}

	/**
	 * Sets the locator.
	 * 
	 * @param locator
	 *            The locator to set.
	 */
	public void setLocator(BorderItemLocator locator) {
		this.locator = locator;
	}
}