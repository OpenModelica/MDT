
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

public class CrossUtil {

	public static List<MyConnection> connections = new ArrayList<MyConnection>();
	public static List<MyNode> nodes = new ArrayList<MyNode>();
	public static List<GraphPackage> packages = new ArrayList<GraphPackage>();
	public static List<GraphNode> graphNodes;
	public static List<GraphConnection> graphConnections;

	public static void generateNodes(Graph graph, String fileName) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Choosing sorting");
		//System.out.println("[Graph Operation] Generating visual graph with size: " + nodes.size());
		int sorting = 1;
	
		
		switch (sorting) {
		case 1:  // Sorting #1 "Original"
			generateNodesOriginal(graph, fileName);
			graph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
			break;
		case 2:  // Sorting #2 "MathCore"
			generateNodesMathCore(graph, fileName);
			break;
		case 3:  // Sorting #3 "Error"
			//generateNodesError(graph, fileName);
			break;
		case 4:  // Sorting #4 "Packages"
			//CrossAnalyzer.analyzeAllPackages();
			generateNodesPackages(graph, fileName);
			break;
		}
	}
	
	// TODO: Some of these functions can be merged and use smaller help-functions 
	//       instead while keeping the loop among all of them
	
	private static void generateNodesOriginal(Graph graph, String fileName) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Generating graph according to Original");
		
		// Create visuals for the nodes
		graphNodes = new ArrayList<GraphNode>();
		for(int index = 0; index < nodes.size(); index++) {
			//System.out.println("node - " + index + " with name " + nodes.get(index).getName());
			GraphNode tempGraphNode = new GraphNode(graph, SWT.NONE, nodes.get(index).getName());
			tempGraphNode.setBackgroundColor(graph.getDisplay().getSystemColor(nodes.get(index).getColor()));
			//tempGraphNode.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE));
			//tempGraphNode.setVisible(false);
			//tempGraphNode.setLocation(0, 40);
			graphNodes.add(tempGraphNode);
		}
		
		
		// Create visuals for the connections (can only be made after nodes are created)
		//System.out.println("[Graph Operation] Generating visual connections with size: " + connections.size());

		graphConnections = new ArrayList<GraphConnection>();
		for(int i = 0; i < connections.size(); i++) {
			int tempSource = Integer.parseInt(connections.get(i).getSource().getId());
			int tempDestination = Integer.parseInt(connections.get(i).getDestination().getId());
			int tempStyle = connections.get(i).getStyle();
			//System.out.println("connection - source id: " + tempSource + " to destination id: " + tempDestination);
			GraphConnection tempGraphConnection = new GraphConnection(graph,  ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(tempSource), graphNodes.get(tempDestination));

			// bug#: 243452, 215040 (having different styles seems like a ongoing issue?)
			tempGraphConnection.setLineStyle(tempStyle);
			graphConnections.add(tempGraphConnection);
		}
	}

	private static void generateNodesMathCore(Graph graph, String fileName){
		System.out.println("[Graph Generation] Generating graph according to MathCore");
		// TODO: Make this recursive in the analyzing part

		// Create visuals for the nodes
		graphNodes = new ArrayList<GraphNode>();
		for(int index = 0; index < nodes.size(); index++) {
			//System.out.println("node - " + index + " with name " + nodes.get(index).getName());
			GraphNode tempGraphNode = new GraphNode(graph, SWT.NONE, nodes.get(index).getName());
			//tempGraphNode.setLocation(0, 40);
			graphNodes.add(tempGraphNode);
		}

		// Create visuals for the connections (can only be made after nodes are created)
		//System.out.println("[Graph Operation] Generating visual connections with size: " + connections.size());

		graphConnections = new ArrayList<GraphConnection>();
		for(int i = 0; i < connections.size(); i++) {
			int tempSource = Integer.parseInt(connections.get(i).getSource().getId());
			int tempDestination = Integer.parseInt(connections.get(i).getDestination().getId());
			int tempStyle = connections.get(i).getStyle();
			//System.out.println("connection - source id: " + tempSource + " to destination id: " + tempDestination);
			GraphConnection tempGraphConnection = new GraphConnection(graph, ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(tempSource), graphNodes.get(tempDestination));
			tempGraphConnection.setLineStyle(tempStyle);
			graphConnections.add(tempGraphConnection);
		}
	}

	private static void generateNodesPackages(Graph graph, String fileName){
		System.out.println("[Graph Generation] Generating graph according to Packages");
		// Create visuals for the nodes
		int tempIndex;
		graphNodes = new ArrayList<GraphNode>();
		for(int index = 0; index < nodes.size(); index++) {
			//System.out.println("node - " + index + " with name " + nodes.get(index).getName());
			GraphNode tempGraphNode = new GraphNode(graph, SWT.NONE, nodes.get(index).getName());
			for(int i = 0; i < packages.size(); i++) {
				if (packages.size() > 0)
					//System.out.println(packages.get(i).getNodes());
					if (packages.get(i).getNodes().contains(nodes.get(index).getName())){
						tempIndex = packages.get(i).getNodes().indexOf(nodes.get(index).getName());
						tempGraphNode.setLocation(graphNodes.get(tempIndex).getLocation().x + 30, graphNodes.get(tempIndex).getLocation().y + 30);
					}
			}
			//tempGraphNode.setLocation(0, 40);
			graphNodes.add(tempGraphNode);
		}

		// Create visuals for the connections (can only be made after nodes are created)
		graphConnections = new ArrayList<GraphConnection>();
		for(int i = 0; i < connections.size(); i++) {
			int tempSource = Integer.parseInt(connections.get(i).getSource().getId());
			int tempDestination = Integer.parseInt(connections.get(i).getDestination().getId());
			int tempStyle = connections.get(i).getStyle();
			//System.out.println("connection - source id: " + tempSource + " to destination id: " + tempDestination);
			GraphConnection tempGraphConnection = new GraphConnection(graph, ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(tempSource), graphNodes.get(tempDestination));
			tempGraphConnection.setLineStyle(tempStyle);
			graphConnections.add(tempGraphConnection);
		}
	}
}