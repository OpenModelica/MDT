package org.modelica.uml.sysml.diagram2.edit.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;

/**
 */
public class FixedConnectionAnchor extends AbstractConnectionAnchor {

	public boolean leftToRight = true;

	public int offsetH = 1;

	public int offsetV = 1;

	public boolean topDown = true;

	public FixedConnectionAnchor(IFigure owner) {
		super(owner);
	}

	/**
	 * @see org.eclipse.draw2d.AbstractConnectionAnchor#ancestorMoved(IFigure)
	 */
	public void ancestorMoved(IFigure figure) {
		if (figure instanceof ScalableFigure)
			return;
		super.ancestorMoved(figure);
	}

	public Point getLocation(Point reference) {
		
		IMapMode mm = MapModeUtil.getMapMode(getOwner());
		PointList copy = InOutModelicaPortFigure.portPoints.getCopy();
		mm.DPtoLP(copy);
		copy.performTranslate(mm.DPtoLP(60),mm.DPtoLP(7));
		copy.translate(copy.getBounds().width / 2, copy.getBounds().height / 2);
		
		Rectangle portBounds = copy.getBounds();		
		Rectangle r = getOwner().getBounds();
		 
		int x, y;
		if (topDown)
			y = r.y + portBounds.y;
		else
			y = r.bottom() - 1 - offsetV;

		if (leftToRight)
			x = r.x + portBounds.x;
		else
			x = r.right() - 1 - offsetH;

		Point p = new PrecisionPoint(x, y);
		getOwner().translateToAbsolute(p);
		return p;
	}

	public Point getReferencePoint() {
		return getLocation(null);
	}

	/**
	 * @param offsetH
	 *            The offsetH to set.
	 */
	public void setOffsetH(int offsetH) {
		this.offsetH = offsetH;
		fireAnchorMoved();
	}

	/**
	 * @param offsetV
	 *            The offsetV to set.
	 */
	public void setOffsetV(int offsetV) {
		this.offsetV = offsetV;
		fireAnchorMoved();
	}

}