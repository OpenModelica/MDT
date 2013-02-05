
package org.modelica.mdt.ui.graph;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.zest.core.widgets.Graph;

public class MarkedGraph extends Graph
{

	public MarkedGraph(Composite parent, int style, String text) {
		super(parent, style);
		
		//Label title = new Label(parent, SWT.CENTER);
	    //title.setText(text);
	    //title.setLocation(parent.getLocation().x, parent.getLocation().y);
	}
}
