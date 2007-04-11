package org.modelica.uml.sysml.diagram2.edit.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;

/**
 * Node figure for Modelica Port. Ports are small square items that can sit on
 * the border.
 * 
 */
public class InOutModelicaPortFigure extends ModelicaPortFigure {

	protected String portName;

	protected String portType;

	protected static Dimension preferedSize = new Dimension(120, 27);

	protected Rectangle portSquareBounds;
	
	/**
	 * Constructor
	 * 
	 * @param edge
	 */
	public InOutModelicaPortFigure(String portName, String portType) {
		super(preferedSize);
		setOpaque(true);
		setSize(preferedSize);

		fixedAnchor = new FixedConnectionAnchor(this);
		getConnectionAnchors().put(portName, fixedAnchor);
		this.portName = portName;
		this.portType = portType;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#paintFigure(Graphics)
	 * 
	 * @param graphics
	 */
	protected void paintFigure(Graphics graphics) {

		Rectangle r = getBounds().getCopy();
		IMapMode mm = MapModeUtil.getMapMode(this);
		r.translate(mm.DPtoLP(4), mm.DPtoLP(0));

		int leftPadding =  (r.width / 2 - mm.DPtoLP(15))
			- FigureUtilities.getTextExtents(portName, getFont()).width;

		graphics.translate(r.getLocation());

		graphics.setForegroundColor(ColorConstants.gray);
		graphics.setBackgroundColor(ColorConstants.white);

		if (getPortSide() == PositionConstants.WEST) {
			graphics.drawString(portName, mm.DPtoLP(leftPadding), mm.DPtoLP(-3));
		} else if (getPortSide() == PositionConstants.SOUTH) {
			graphics
					.drawString(portName, mm.DPtoLP(leftPadding), mm.DPtoLP(12));
		} else if (getPortSide() == PositionConstants.EAST) {
			graphics.drawString(portName, r.width / 2 + mm.DPtoLP(5), mm
					.DPtoLP(12));
		} else if (getPortSide() == PositionConstants.NORTH) {
			graphics
					.drawString(portName, mm.DPtoLP(leftPadding), mm.DPtoLP(-2));
		}

		PointList copy = portPoints.getCopy();
		mm.DPtoLP(copy);
		copy.performTranslate(mm.DPtoLP(50), 0);
		copy.translate(copy.getBounds().width / 2, copy.getBounds().height / 2);
		graphics.fillPolygon(copy);
		graphics.drawPolygon(copy);

		portSquareBounds = copy.getBounds();
		
		graphics.setForegroundColor(ColorConstants.gray);
		graphics.setBackgroundColor(ColorConstants.gray);

		paintArrows(graphics);

	}

	public Rectangle getPortSquareBounds() {
		return portSquareBounds;
	}
	
	
	protected void paintArrows(Graphics graphics) {
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPortType() {
		return portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public static PointList portPoints = new PointList();
	static {
		portPoints.addPoint(-5, 0);
		portPoints.addPoint(-5, 12);
		portPoints.addPoint(5, 12);
		portPoints.addPoint(5, 0);
	}

	protected static PointList downArrow = new PointList();
	{
		downArrow.addPoint(0, 3);
		downArrow.addPoint(0, 7);
		downArrow.addPoint(-2, 7);
		downArrow.addPoint(0, 9);
		downArrow.addPoint(2, 7);
		downArrow.addPoint(0, 7);
	}

	protected static PointList upArrow = new PointList();
	{
		upArrow.addPoint(0, 9);
		upArrow.addPoint(0, 5);
		upArrow.addPoint(2, 5);
		upArrow.addPoint(0, 2);
		upArrow.addPoint(-2, 5);
		upArrow.addPoint(0, 5);
	}

	protected static PointList rightArrow = new PointList();
	{
		rightArrow.addPoint(-3, 6);
		rightArrow.addPoint(0, 6);
		rightArrow.addPoint(0, 8);
		rightArrow.addPoint(3, 6);
		rightArrow.addPoint(0, 4);
		rightArrow.addPoint(0, 6);
	}

	protected static PointList leftArrow = new PointList();
	{
		leftArrow.addPoint(3, 6);
		leftArrow.addPoint(0, 6);
		leftArrow.addPoint(0, 8);
		leftArrow.addPoint(-3, 6);
		leftArrow.addPoint(0, 4);
		leftArrow.addPoint(0, 6);
	}

}