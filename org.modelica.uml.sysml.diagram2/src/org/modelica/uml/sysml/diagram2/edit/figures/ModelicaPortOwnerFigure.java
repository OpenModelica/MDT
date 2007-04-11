package org.modelica.uml.sysml.diagram2.edit.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

/**
 */
public class ModelicaPortOwnerFigure extends NodeFigure implements HandleBounds {

	private Dimension prefSize;

	public ModelicaPortOwnerFigure(Dimension prefSize) {
		setBorder(new ModelicaPortOwnerBorder());
		setOpaque(true);
		this.prefSize = prefSize;

		this.setBackgroundColor(CLASSPARTFIGURE_BACK);
		createContents();
	}

	public Dimension getPreferredSize(int w, int h) {
		Dimension newPrefSize = super.getPreferredSize(w, h);
		Dimension defaultSize = prefSize;
		newPrefSize.union(defaultSize);
		return newPrefSize;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#paintFigure(Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();
		graphics.fillRectangle(rect);
	}

	/**
	 */
	private void createContents() {
		org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_0 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();

		setFigurePartProperyName(fig_0);

		Object layData0 = null;
		this.add(fig_0, layData0);
	}

	/**
	 */
	private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fPartProperyName;

	/**
	 */
	public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigurePartProperyName() {
		return fPartProperyName;
	}

	/**
	 */
	private void setFigurePartProperyName(
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
		fPartProperyName = fig;
	}

	/**
	 */
	private boolean myUseLocalCoordinates = false;

	/**
	 */
	protected boolean useLocalCoordinates() {
		return myUseLocalCoordinates;
	}

	/**
	 */
	protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
		myUseLocalCoordinates = useLocalCoordinates;
	}

	public static final org.eclipse.swt.graphics.Color CLASSPARTFIGURE_BACK = new org.eclipse.swt.graphics.Color(
			null, 238, 247, 255);

}