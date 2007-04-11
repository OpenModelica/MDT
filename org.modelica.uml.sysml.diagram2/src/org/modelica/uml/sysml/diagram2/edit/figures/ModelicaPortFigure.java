package org.modelica.uml.sysml.diagram2.edit.figures;

import java.util.List;
import java.util.ListIterator;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

/**
 * To manage fixed connection anchors
 */
public class ModelicaPortFigure extends NodeFigure {

	protected FixedConnectionAnchor fixedAnchor;

	protected Dimension prefSize;

	protected int portSide;

	/**
	 * @author sshaw
	 * 
	 * Override for BorderItemLocator that will fix the location for the
	 * connection point based on an initial position. This locator will also
	 * scale the location of the connection if the parent figure changes.
	 */
	static public class TerminalLocator extends BorderItemLocator {

		protected Point getPreferredLocation(int side, IFigure borderItem) {

			Rectangle bounds = getParentBorder();
			int parentFigureWidth = bounds.width;
			int parentFigureHeight = bounds.height;
			int parentFigureX = bounds.x;
			int parentFigureY = bounds.y;
			int x = parentFigureX;
			int y = parentFigureY;

			Rectangle borderItemBounds = borderItem.getBounds();

			if (side == PositionConstants.WEST) {
				x = parentFigureX - borderItemBounds.width
						+ getBorderItemOffset().width;
				y += 1; //parentFigureHeight / 7;
			} else if (side == PositionConstants.EAST) {
				x = parentFigureX + parentFigureWidth
						- getBorderItemOffset().width;
				y += parentFigureHeight - (parentFigureHeight/3) ;
			} else if (side == PositionConstants.NORTH) {
				y = parentFigureY - borderItemBounds.height
						+ getBorderItemOffset().height;
				x += parentFigureWidth - (parentFigureWidth/7);
			} else if (side == PositionConstants.SOUTH) {
				x += parentFigureWidth / 7;
				y = parentFigureY + parentFigureHeight
						- getBorderItemOffset().height;
			}
			return new Point(x, y);
		}

		public void relocate(IFigure borderItem) {

			Rectangle rectSuggested = new Rectangle(
					getPreferredLocation(borderItem), borderItem.getBounds().getSize());
				int closestSide = findClosestSideOfParent(rectSuggested,
					getParentBorder());
				setPreferredSideOfParent(closestSide);

				Point ptNewLocation = locateOnBorder(getPreferredLocation(borderItem),
					getPreferredSideOfParent(), 0, borderItem);
				borderItem.setLocation(ptNewLocation);

				setCurrentSideOfParent(findClosestSideOfParent(new Rectangle(ptNewLocation, borderItem
					.getBounds().getSize()), getParentBorder()));

			
			super.relocate(borderItem);
			((ModelicaPortFigure) borderItem).setPortSide(this
					.getCurrentSideOfParent());
		}

		/**
		 * @param gate
		 * @param parentFigure
		 */
		public TerminalLocator(IFigure parentFigure, int side) {
			super(parentFigure, side);
		}
		
		private Point locateOnBorder(Point suggestedLocation,
				int suggestedSide, int circuitCount, IFigure borderItem) {
			Point recommendedLocation = locateOnParent(suggestedLocation,
				suggestedSide, borderItem);
			
			int vertical_gap = MapModeUtil.getMapMode(getParentFigure()).DPtoLP(0);
			int horizontal_gap = MapModeUtil.getMapMode(getParentFigure())
				.DPtoLP(3);
			Rectangle borderItemBounds = borderItem.getBounds();

			if (circuitCount < 4 && conflicts(recommendedLocation, borderItem)) {
				if (suggestedSide == PositionConstants.WEST) {
					do {
						recommendedLocation.y += borderItemBounds.height
							+ vertical_gap;
					} while (conflicts(recommendedLocation, borderItem));
					if (recommendedLocation.y > getParentBorder().getBottomLeft().y
						- borderItemBounds.height) { // off the bottom,
						// wrap south
						return locateOnBorder(recommendedLocation,
							PositionConstants.SOUTH, circuitCount + 1, borderItem);
					}
				} else if (suggestedSide == PositionConstants.SOUTH) {
					do {
						recommendedLocation.x += borderItemBounds.width
							+ horizontal_gap;
					} while (conflicts(recommendedLocation, borderItem));
					if (recommendedLocation.x > getParentBorder().getBottomRight().x
						- borderItemBounds.width) {
						return locateOnBorder(recommendedLocation,
							PositionConstants.EAST, circuitCount + 1, borderItem);
					}
				} else if (suggestedSide == PositionConstants.EAST) {
					// move up the east side
					do {
						recommendedLocation.y -= borderItemBounds.height
							- vertical_gap;
					} while (conflicts(recommendedLocation, borderItem));
					if (recommendedLocation.y < getParentBorder().getTopRight().y) {
						// east is full, try north.
						return locateOnBorder(recommendedLocation,
							PositionConstants.NORTH, circuitCount + 1, borderItem);
					}
				} else { // NORTH
					do {
						recommendedLocation.x -= borderItemBounds.width
							- horizontal_gap;
					} while (conflicts(recommendedLocation, borderItem));
					if (recommendedLocation.x < getParentBorder().getTopLeft().x) {
						return locateOnBorder(recommendedLocation,
							PositionConstants.WEST, circuitCount + 1, borderItem);
					}
				}
			}
			return recommendedLocation;
		}

		private Point locateOnParent(Point suggestedLocation,
				int suggestedSide, IFigure borderItem) {
			Rectangle bounds = getParentBorder();
			int parentFigureWidth = bounds.width;
			int parentFigureHeight = bounds.height;
			int parentFigureX = bounds.x;
			int parentFigureY = bounds.y;
			Rectangle borderItemBounds = borderItem.getBounds();
			int newX = suggestedLocation.x;
			int newY = suggestedLocation.y;
			int westX = parentFigureX - borderItemBounds.width
				+ getBorderItemOffset().width;
			int eastX = parentFigureX + parentFigureWidth
				- getBorderItemOffset().width;
			int southY = parentFigureY + parentFigureHeight
				- getBorderItemOffset().height;
			int northY = parentFigureY - borderItemBounds.height
				+ getBorderItemOffset().height;
			if (suggestedSide == PositionConstants.WEST) {
				if (suggestedLocation.x != westX) {
					newX = westX;
				}
				if (suggestedLocation.y < bounds.getTopLeft().y) {
					newY = northY + borderItemBounds.height;
				} else if (suggestedLocation.y > bounds.getBottomLeft().y
					- borderItemBounds.height) {
					newY = southY - borderItemBounds.height;
				}
			} else if (suggestedSide == PositionConstants.EAST) {
				if (suggestedLocation.x != eastX) {
					newX = eastX;
				}
				if (suggestedLocation.y < bounds.getTopLeft().y) {
					newY = northY + borderItemBounds.height;
				} else if (suggestedLocation.y > bounds.getBottomLeft().y
					- borderItemBounds.height) {
					newY = southY - borderItemBounds.height;
				}
			} else if (suggestedSide == PositionConstants.SOUTH) {
				if (suggestedLocation.y != southY) {
					newY = southY;
				}
				if (suggestedLocation.x < bounds.getBottomLeft().x) {
					newX = westX + borderItemBounds.width;
				} else if (suggestedLocation.x > bounds.getBottomRight().x
					- borderItemBounds.width) {
					newX = eastX - borderItemBounds.width;
				}
			} else { // NORTH
				if (suggestedLocation.y != northY) {
					newY = northY;
				}
				if (suggestedLocation.x < bounds.getBottomLeft().x) {
					newX = westX + borderItemBounds.width;
				} else if (suggestedLocation.x > bounds.getBottomRight().x
					- borderItemBounds.width) {
					newX = eastX - borderItemBounds.width;
				}
			}
			return new Point(newX, newY);
		}

		private boolean conflicts(Point recommendedLocation,
				IFigure targetBorderItem) {
			Rectangle recommendedRect = new Rectangle(recommendedLocation,
				targetBorderItem.getSize());
			List borderItems = targetBorderItem.getParent().getChildren();
			ListIterator iterator = borderItems.listIterator();
			while (iterator.hasNext()) {
				IFigure borderItem = (IFigure) iterator.next();
				if (borderItem.isVisible()) {
					Rectangle rect = borderItem.getBounds().getCopy();
					if (borderItem != targetBorderItem
						&& rect.intersects(recommendedRect)) {
						return true;
					}
				}
			}
			return false;
		}

	}

	/**
	 * @param preferredSide
	 */
	public ModelicaPortFigure(Dimension prefSize) {
		super();
		this.prefSize = new Dimension(prefSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#getSourceConnectionAnchorAt(org.eclipse.draw2d.geometry.Point)
	 */
	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		if (p == null) {
			return getConnectionAnchor(szAnchor);
		}
		return fixedAnchor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#getTargetConnectionAnchorAt(org.eclipse.draw2d.geometry.Point)
	 */
	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		if (p == null) {
			return getConnectionAnchor(szAnchor);
		}
		return fixedAnchor;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(prefSize);
	}

	public int getPortSide() {
		return portSide;
	}

	public void setPortSide(int portSide) {
		this.portSide = portSide;
	}

}
