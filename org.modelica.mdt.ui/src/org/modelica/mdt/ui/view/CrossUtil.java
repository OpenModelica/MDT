
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.Animation;
import org.eclipse.swt.SWT;
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
	
	// TODO: Create a graphContainer for each package, where each package has nodes connected to outside nodes (THIS IS POSSIBLE IN ZEST!)
	// http://git.eclipse.org/c/gef/org.eclipse.zest.git/tree/org.eclipse.zest.examples/src/org/eclipse/zest/examples/swt/NestedGraphSnippet.java	
	// (Internal sorting withing graphContainer is also possible)

	// When we see that a node is a package we should make all things included in that package to be created INSIDE the graphcontainer
	// The cid and nid are the same for all, but we have to remember that the package should keep a nid aswell 
	// (thats why we need myPackage or GraphPackage, which could extend MyNode)

	public static void generateNodes(Graph graph, String fileName, boolean expand, int startingInt) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Choosing sorting method");

		// TODO: This should be possible to be set by user
		int sorting = 1;

		switch (sorting) {
		case 1:  // Sorting #1 "Original"
			generateNodesOriginal(graph, fileName, expand, startingInt);

			// LayoutStyles.NO_LAYOUT_NODE_RESIZING
			// ZestStyles.NODES_NO_LAYOUT_ANIMATION
			graph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
			graph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
			//graph.applyLayout();
			break;
		case 2:  // Sorting #2 "MathCore"
			//generateNodesMathCore(graph, fileName);
			break;
		case 3:  // Sorting #3 "Error"
			//generateNodesError(graph, fileName);
			break;
		case 4:  // Sorting #4 "Packages"
			generateNodesPackages(graph, fileName, expand, startingInt);
			break;
		}
	}	


	// TODO: Some of these functions can be merged and use smaller help-functions 
	//       instead while keeping the loop among all of them

	private static void generateNodesOriginal(Graph graph, String fileName, boolean expand, int startInt) throws ConnectException, UnexpectedReplyException{
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

				tempGraphConnection.setLineStyle(tempStyle);
				graphConnections.add(tempGraphConnection);
		}
	}

	private static void generateNodesMathCore(Graph graph, String fileName, boolean expand, int startInt){
		// generate everything with recursive analyze (exhaustive but shows everything that we may need to know)
		// place at random locations
		
		// TODO: How do we set the analyze to be recursive before we get here?
	}

	private static void generateNodesPackages(Graph graph, String fileName, boolean expand, int startInt){
		// generate everything with support of graphContainers
	}

	private static boolean nodeExist(Graph g, String name){
		// iterate over graphNodes
		GraphNode tempGraphNode = new GraphNode(g, SWT.NONE, name);
		if (graphNodes.contains(tempGraphNode))
			return true;
		return false;
	}

	private static boolean connectionExist(Graph g, GraphConnection c){
		// iterate over graphConnections
		if (graphConnections.contains(c))
			return true;
		return false;

	}
}