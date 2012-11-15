
package org.modelica.mdt.ui.view;

import java.util.ArrayList;

import org.eclipse.zest.core.widgets.GraphNode;

public class MyEquation
{
	String equation;
	GraphNode topNode;
	
	public MyEquation(GraphNode topNode, String equation) {
		this.topNode = topNode;
		this.equation = equation;
	}
}
