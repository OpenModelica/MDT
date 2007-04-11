package org.modelica.uml.sysml.diagram2.edit.parts;

import java.util.Map;

import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;


/**
 *
 * The interface for Modelica shapes that own connectors(ports).
 */
public interface IPortOwnerEditPart {
	
	/**
	 * Create connection points position map on the given editpart.
	 * @return connection points position map
	 */
	public Map createBoundsMap();
	
	/**
	 * Method for returning the owned port figure based on an ID value.
	 * 
	 * @param terminalEP <code>Terminal</code> to create a figure of
	 * @return <code>NodeFigure</code> that is the figure for the terminal.
	 */
	public NodeFigure createOwnedPortFigure(ModelicaPortEditPart portPart);

}