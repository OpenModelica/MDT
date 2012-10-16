
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.Animation;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.Filter;
import org.eclipse.zest.layouts.InvalidLayoutConfiguration;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.LayoutRelationship;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.CompositeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalShift;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

public class CrossUtil {

	public static List<MyConnection> connections = new ArrayList<MyConnection>();
	public static List<MyNode> nodes = new ArrayList<MyNode>();

	public static List<GraphPackage> packages = new ArrayList<GraphPackage>();
	public static List<GraphNode> graphNodes;
	public static List<GraphConnection> graphConnections;

	private static int startInt;

	// TODO: Create a graphContainer for each package, where each package has nodes connected to outside nodes (THIS IS POSSIBLE IN ZEST!)
	// http://git.eclipse.org/c/gef/org.eclipse.zest.git/tree/org.eclipse.zest.examples/src/org/eclipse/zest/examples/swt/NestedGraphSnippet.java	
	// (Internal sorting withing graphContainer is also possible)

	public static void generateNodes(final Graph graph, final String fileName, final boolean expand, int startingInt) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Choosing sorting method");
		startInt = startingInt;

		// TODO: This should be possible to be set by user
		int sorting = 1;


		switch (sorting) {
		case 1:  // Sorting #1 "Original"

			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					try
					{
						generateNodesOriginal(graph, fileName, expand);
					} catch (ConnectException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnexpectedReplyException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			graph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
			graph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
			//graph.applyLayout();
			break;
		case 2:  // Sorting #2 "MathCore"
			break;
		case 3:  // Sorting #3 "Error"
			break;
		case 4:  // Sorting #4 "Packages"
			break;
		}
	}	


	// TODO: Some of these functions can be merged and use smaller help-functions 
	//       instead while keeping the loop among all of them

	private static void generateNodesOriginal(Graph graph, String fileName, boolean expand) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Generating graph according to Original");

		// Create visuals for the nodes
		if (!expand) {
			graphNodes = new ArrayList<GraphNode>();
			graphConnections = new ArrayList<GraphConnection>();
		}
		
		for(int index = startInt; index < nodes.size(); index++) {
			//System.out.println("Create node " + nodes.get(index).getName() + " with int " + index );
			GraphNode tempGraphNode = new GraphNode(graph, SWT.NONE, nodes.get(index).getName());
			tempGraphNode.setBackgroundColor(graph.getDisplay().getSystemColor(nodes.get(index).getColor()));
			tempGraphNode.setTooltip(new Label("Hello"));
			graphNodes.add(tempGraphNode);
		}
		
		// Create visuals for the connections (can only be made after nodes are created)
		if (expand)
			startInt -= 1;
		for(int i = startInt; i < connections.size(); i++) {
			int tempSource = connections.get(i).getSource().getId();
			int tempDestination = connections.get(i).getDestination().getId();
			int tempStyle = connections.get(i).getStyle();
			//System.out.println("Created a GRAPH connector between " + tempSource + " and " + tempDestination);
			GraphConnection tempGraphConnection = new GraphConnection(graph,  ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(tempSource), graphNodes.get(tempDestination));

			tempGraphConnection.setLineWidth(2);
			if (tempStyle == SWT.LINE_DOT){
				tempGraphConnection.setLineWidth(3);
			}
			tempGraphConnection.setLineStyle(tempStyle);


			if (connections.get(i).bending)
				tempGraphConnection.setCurveDepth(10);


			graphConnections.add(tempGraphConnection);
		}  
		//syncWithUi(graph, expand);
	}

}