
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
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

	public static List<GraphNode> graphNodes;
	public static List<GraphConnection> graphConnections;

	// http://git.eclipse.org/c/gef/org.eclipse.zest.git/tree/org.eclipse.zest.examples/src/org/eclipse/zest/examples/swt/NestedGraphSnippet.java	
	// (Internal sorting withing graphContainer is also possible)

	public static void generateNodes(final Graph graph, final String fileName) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Choosing sorting method");

		// TODO: This should be possible to be set by user
		int sorting = 1;

		switch (sorting) {
		case 1:  // Sorting #1 "Original"
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					try
					{
						generateNodesOriginal(graph, fileName);
					} catch (ConnectException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnexpectedReplyException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					graph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
					graph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
				}
			});
			break;
		case 2:  // Sorting #2 "TreeMap"
			break;
		case 3:  // Sorting #3 "Packages"
			break;
		}
	}	


	// TODO: Some of these functions can be merged and use smaller help-functions 
	//       instead while keeping the loop among all of them

	private static void generateNodesOriginal(Graph graph, String fileName) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Generating graph according to Original");

		// Create visuals for the nodes
		graphNodes = new ArrayList<GraphNode>();
		graphConnections = new ArrayList<GraphConnection>();

		System.out.println("[Graph Generation] Nodes to be generated = " + nodes.size());

		for(int index = 0; index < nodes.size(); index++) {
			//System.out.println("Create node " + nodes.get(index).getName() + " with int " + index );
			MyNode tempMyNode = nodes.get(index);
			GraphNode tempGraphNode = new GraphNode(graph, SWT.NONE, tempMyNode.getName());
			tempGraphNode.setBackgroundColor(graph.getDisplay().getSystemColor(nodes.get(index).getColor()));
			tempGraphNode.setBorderColor(org.eclipse.draw2d.ColorConstants.black);
			if (!tempMyNode.isExpandable())
				tempGraphNode.setBorderWidth(3);
			ArrayList<String> toolTipList = tempMyNode.getToolTipInfo();
			tempGraphNode.setTooltip(new Label(
					"Type: " + toolTipList.get(0) + "\n" +
							"Name: " + toolTipList.get(1) + "\n" +
							"Descripton: " + toolTipList.get(2) + "\n" +
							"Position: "   + toolTipList.get(3) + "\n" +
							"Path: "       + toolTipList.get(4) + "\n"
					));
			graphNodes.add(tempGraphNode);
		}

		// Create visuals for the connections (can only be made after nodes are created)

		for(int i = 0; i < connections.size(); i++) {
			int tempSource = connections.get(i).getSource().getId();
			int tempDestination = connections.get(i).getDestination().getId();
			int tempStyle = connections.get(i).getStyle();
			GraphConnection tempGraphConnection = new GraphConnection(graph,  ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(tempSource), graphNodes.get(tempDestination));
			tempGraphConnection.setLineColor(org.eclipse.draw2d.ColorConstants.black);
			tempGraphConnection.setLineWidth(2);
			if (tempStyle == SWT.LINE_DOT){
				tempGraphConnection.setLineWidth(3);
			}
			tempGraphConnection.setLineStyle(tempStyle);


			if (connections.get(i).bending)
				tempGraphConnection.setCurveDepth(10);


			graphConnections.add(tempGraphConnection);
		}  
	}

	static void generateExpanding(Graph graph, String... selectedNode) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Generating graph from expanding node");
		System.out.println("[Graph Generation] Nodes to be generated = " + (nodes.size()-graphNodes.size()));

		if ((nodes.size()-graphNodes.size()) == 0 && selectedNode != null){
			System.out.println("[Graph Generation] We got to the end of a branch for " + selectedNode[0]);
			for(int index = 0; index < graphNodes.size(); index++) {
				if(graphNodes.get(index).getText().equals(selectedNode[0])){
					graphNodes.get(index).setBorderWidth(3);
					return;
				}
			}
		}

		for(int index = graphNodes.size(); index < nodes.size(); index++) {
			System.out.println("Create node " + nodes.get(index).getName() + " with int " + index );

			MyNode tempMyNode = nodes.get(index);
			GraphNode tempGraphNode = new GraphNode(graph, SWT.NONE, tempMyNode.getName());
			tempGraphNode.setBackgroundColor(graph.getDisplay().getSystemColor(nodes.get(index).getColor()));
			tempGraphNode.setBorderColor(org.eclipse.draw2d.ColorConstants.black);
			System.out.println("Is this node expandable? " + tempMyNode.isExpandable());
			if (!tempMyNode.isExpandable())
				tempGraphNode.setBorderWidth(3);
			ArrayList<String> toolTipList = tempMyNode.getToolTipInfo();
			tempGraphNode.setTooltip(new Label(
					"Type: " + toolTipList.get(0) + "\n" +
							"Name: " + toolTipList.get(1) + "\n" +
							"Descripton: " + toolTipList.get(2) + "\n" +
							"Position: "   + toolTipList.get(3) + "\n" +
							"Path: "       + toolTipList.get(4) + "\n"
					));
			graphNodes.add(tempGraphNode);
		}


		//System.out.println("Now the number of ungenerated connections are " + CrossUtil.connections.size());
		//System.out.println("And the size of generated connections are " + CrossUtil.graphConnections.size());
		//System.out.println("[Graph Generation] Connections to be generated = " + (connections.size()-graphConnections.size()));

		for(int i = graphConnections.size(); i < connections.size(); i++) {
			int tempSource = connections.get(i).getSource().getId();
			int tempDestination = connections.get(i).getDestination().getId();
			int tempStyle = connections.get(i).getStyle();
			//System.out.println("Created a GRAPH connector between " + connections.get(i).getSource().getName() + " and " + connections.get(i).getDestination().getName());
			GraphConnection tempGraphConnection = new GraphConnection(graph,  ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(tempSource), graphNodes.get(tempDestination));
			graphNodes.get(tempSource).setBorderWidth(3);
			tempGraphConnection.setLineColor(org.eclipse.draw2d.ColorConstants.black);
			tempGraphConnection.setLineWidth(2);
			if (tempStyle == SWT.LINE_DOT){
				tempGraphConnection.setLineWidth(3);
			}
			tempGraphConnection.setLineStyle(tempStyle);


			if (connections.get(i).bending)
				tempGraphConnection.setCurveDepth(10);


			graphConnections.add(tempGraphConnection);
		}  

		System.out.println("5");

		final Graph UIgraph = graph;

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// TODO: This requires a new expanded layout-algorithm which only change the location of new entities
				UIgraph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
				UIgraph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
			}
		});

		System.out.println("6");
	}


	public static void removeDependencies(Graph graph, ArrayList<Integer> destructList, int index) throws ConnectException, UnexpectedReplyException{
		System.out.println("[Graph Generation] Generating shrinked graph");

		GraphNode pushedNode = (GraphNode) graph.getNodes().get(index);
		pushedNode.setBorderWidth(1);

		for(int i = destructList.size()-1; i >= 0 ; i--) {

			int destructIndex = destructList.get(i);
			Object[] connectionObjects = graph.getConnections().toArray() ;
			GraphConnection iterCon;
			GraphNode iterNode;


			for (int x = connectionObjects.length-1 ; x >= 0  ; x-- ) {
				iterCon = ((GraphConnection) connectionObjects[x]);
				if((iterCon.getSource() == graphConnections.get(destructIndex).getSource())
						&&
						(iterCon.getDestination() == graphConnections.get(destructIndex).getDestination())){

					iterNode = iterCon.getDestination();

					if (iterNode.getSourceConnections().isEmpty()){
						// Slow performance lookup of ID
						for (int y = 0 ; y < graphNodes.size() ; y++) {
							String test = graphNodes.get(y).getText();
							if (test.equals(iterNode.getText())){
								graphNodes.remove(y);
								iterNode.dispose();
								break;
							}
						}
					}

					iterCon.dispose();
					graphConnections.remove(destructIndex);

					break;
				}
			}
		}  
		//System.out.println("NEW SIZE of generated connections are " + graphConnections.size());
	}

}