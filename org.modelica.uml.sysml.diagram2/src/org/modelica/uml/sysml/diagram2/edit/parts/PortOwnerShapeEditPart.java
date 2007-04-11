package org.modelica.uml.sysml.diagram2.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 * Abstract editpart class for shapes that contain ports
 */
public abstract class PortOwnerShapeEditPart extends
		AbstractBorderedShapeEditPart implements IPortOwnerEditPart {

	/**
	 * @param view
	 */
	public PortOwnerShapeEditPart(View view) {
		super(view);
	}

	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart#addBorderItem(org.eclipse.draw2d.IFigure,
	 *      org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart)
	 */
	protected void addBorderItem(IFigure borderItemContainer,
			IBorderItemEditPart borderItemEditPart) {
		if (borderItemEditPart instanceof ModelicaPortEditPart) {
			borderItemContainer.add(borderItemEditPart.getFigure(),
					((ModelicaPortEditPart) borderItemEditPart).getLocator());
		} else {
			super.addBorderItem(borderItemContainer, borderItemEditPart);
		}
	}

}
