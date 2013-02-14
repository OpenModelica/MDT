
package org.modelica.mdt.ui.graph;

import org.eclipse.zest.core.widgets.GraphNode;

/**
 * This class holds the information of a equation.
 * It will be used as part of storage for when saving
 * data of what operations has been performed as part
 * of Modelica's compiling optimizations.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaEquation
{
	String equation;
	GraphNode topNode;
	
	public ModelicaEquation(GraphNode topNode, String equation) {
		this.topNode = topNode;
		this.equation = equation;
	}
}
