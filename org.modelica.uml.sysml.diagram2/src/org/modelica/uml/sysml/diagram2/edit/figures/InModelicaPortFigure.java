package org.modelica.uml.sysml.diagram2.edit.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;

public class InModelicaPortFigure extends InOutModelicaPortFigure {

	public InModelicaPortFigure(String portName, String portType){
		super(portName, portType);
	}

	protected void paintArrows(Graphics graphics) {

		IMapMode mm = MapModeUtil.getMapMode(this);
		PointList copy = portPoints.getCopy();
		mm.DPtoLP(copy);

		PointList ps = null;
		if (getPortSide() == PositionConstants.NORTH) {
			ps = downArrow.getCopy();
		} else if (getPortSide() == PositionConstants.WEST) {
			ps = rightArrow.getCopy();
		} else if (getPortSide() == PositionConstants.SOUTH) {
			ps = upArrow.getCopy();
		} else if (getPortSide() == PositionConstants.EAST) {
			ps = leftArrow.getCopy();
		}

		mm.DPtoLP(ps);
		ps.performTranslate(mm.DPtoLP(50), 0);
		ps.translate(copy.getBounds().width / 2, copy.getBounds().height / 2);
		graphics.drawPolygon(ps);
		graphics.fillPolygon(ps);
	}

}
