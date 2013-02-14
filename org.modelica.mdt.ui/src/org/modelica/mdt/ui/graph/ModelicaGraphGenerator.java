
package org.modelica.mdt.ui.graph;

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

/**
 * This class analyze the stored nodes that comes from the analyze in
 * GraphAnalyzer. Then visualize these by making a graph, and then creates 
 * nodes and connections inside of this. This is made possible using the
 * open-source framework Zest.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaGraphGenerator {

	/* The stored connections and nodes from the analyze */
	public static List<ModelicaConnection> connections = new ArrayList<ModelicaConnection>();
	public static List<ModelicaNode> nodes = new ArrayList<ModelicaNode>();

	/* The stored connections and nodes from the graph visualization */
	public static List<GraphNode> graphNodes;
	public static List<GraphConnection> graphConnections;

	/**
	 * This will choose an sorting algorithm (2012-11-16: currently only 1 
	 * sorting algorithm has been implemented) and generate nodes and connections 
	 * according to those instructions. After the generation has been performed a
	 * layout is performed using a layout algorithm provided by the Zest-framework.
	 *
	 * @param graph
	 *            the graph of the view
	 * @param fileName
	 *            the file name with file-extension
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 */
	public static void generateNodes(final Graph graph, final String fileName) 
			throws ConnectException, UnexpectedReplyException {
		// TODO: The sorting method should be possible to be set by a user
		int sorting = 1;

		switch (sorting) {
		case 1:  // Sorting #1 "Original"
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					try
					{
						// TODO: Show the name of the genereated class inside of the graph
						//GraphLabel test = new GraphLabel(true);
						//test.setText("Hello");
						
						generateNodesOriginal(graph, fileName);
					} catch (ConnectException e)
					{
						e.printStackTrace();
					} catch (UnexpectedReplyException e)
					{
						e.printStackTrace();
					}
					graph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
					graph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
				}
			});
			break;
		}
	}	

	/**
	 * This will instantiate arrays for graphical nodes and graphical
	 * connections each time something new has to be generated visually.
	 * Then it calls for the generations of nodes and connections.
	 *
	 * @param graph
	 *            the graph of the view
	 * @param fileName
	 *            the file name with file-extension
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 */
	private static void generateNodesOriginal(Graph graph, String fileName) 
			throws ConnectException, UnexpectedReplyException {
		// Create visuals for the nodes
		graphNodes = new ArrayList<GraphNode>();		
		generateModelicaNodes(graph, 0);

		// Create visuals for the connections (can only be made after nodes are created)
		graphConnections = new ArrayList<GraphConnection>();
		generateModelicaConnections(graph, 0);
	}

	/**
	 * This will expand upon a previous graph by adding more
	 * nodes and connections by using the same array.
	 *
	 * @param graph
	 *            the graph of the view
	 * @param selectedNode
	 *            the selected nodes (but only expands one of them)
	 */
	static void generateExpanding(Graph graph, String... selectedNode) 
			throws ConnectException, UnexpectedReplyException {
		if ((nodes.size()-graphNodes.size()) == 0 && selectedNode != null) {
			for(int index = 0; index < graphNodes.size(); index++) {
				GraphNode currentNode = graphNodes.get(index);
				if(currentNode.getText().equals(selectedNode[0])) {
					currentNode.setBorderWidth(3);
					return;
				}
			}
		}
		generateModelicaNodes(graph, graphNodes.size());
		generateModelicaConnections(graph, graphConnections.size());

		final Graph UIgraph = graph;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// TODO: This requires a new expanded layout-algorithm which only change the location of new entities
				UIgraph.setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
				UIgraph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
			}
		});
	}

	/**
	 * This will remove nodes and connections from n a previous 
	 * graph by using the same array and an incoming array that
	 * keeps track of what should be removed.
	 *
	 * @param graph
	 *            the graph of the view
	 * @param destructList
	 *            the list of node IDs that should be destroyed
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 */
	public static void removeDependencies(Graph graph, ArrayList<Integer> destructList, int index) 
			throws ConnectException, UnexpectedReplyException {
		GraphNode pushedNode = (GraphNode) graph.getNodes().get(index);
		pushedNode.setBorderWidth(1);
		for(int i = destructList.size()-1; i >= 0 ; i--) {
			int destructIndex = destructList.get(i);
			Object[] connectionObjects = graph.getConnections().toArray() ;
			GraphConnection iterCon;
			GraphNode iterNode;

			for (int x = connectionObjects.length-1 ; x >= 0  ; x-- ) {
				iterCon = ((GraphConnection) connectionObjects[x]);
				GraphConnection currentConnection = graphConnections.get(destructIndex);

				if((iterCon.getSource() == currentConnection.getSource()) &&
						(iterCon.getDestination() == currentConnection.getDestination())){
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
	}

	/**
	 * This will add nodes to the graph.
	 *
	 * @param graph
	 *            the graph of the view
	 * @param startInt
	 *            the index from where it should add things to the node array
	 */
	
	private static void generateModelicaNodes(Graph graph, int startInt) {
		for(int index = startInt; index < nodes.size(); index++) {
			ModelicaNode tempMyNode = nodes.get(index);
			GraphNode tempGraphNode = createModelicaNode(graph, tempMyNode, index);
			tempGraphNode.setBorderColor(org.eclipse.draw2d.ColorConstants.black);

			if (!tempMyNode.isExpandable())
				tempGraphNode.setBorderWidth(3);
			createInfoTip(tempMyNode, tempGraphNode);
			graphNodes.add(tempGraphNode);
		}
	}

	/**
	 * This will add connections to the graph.
	 *
	 * @param graph
	 *            the graph of the view
	 * @param startInt
	 *            the index from where it should add things to the connection array
	 */
	private static void generateModelicaConnections(Graph graph, int startInt) {
		for(int i = startInt; i < connections.size(); i++) {
			ModelicaConnection currentConnection =  connections.get(i);
			int tempSource = currentConnection.getSource().getId();
			int tempDestination = currentConnection.getDestination().getId();
			int tempStyle = currentConnection.getStyle();
			GraphConnection tempGraphConnection = new GraphConnection(graph, ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(tempSource), graphNodes.get(tempDestination));
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

	/**
	 * This will add connections to the graph.
	 *
	 * @param graph
	 *            the graph of the view
	 * @param tempMyNode
	 *            the non-visually node
	 * @param index
	 *            the index where it should receive information from
	 */
	private static GraphNode createModelicaNode(Graph graph, ModelicaNode tempMyNode, int index) {
		GraphNode tempGraphNode = new GraphNode(graph, SWT.NONE, tempMyNode.getName());
		tempGraphNode.setBackgroundColor(graph.getDisplay().getSystemColor(nodes.get(index).getColor()));
		return tempGraphNode;
	}

	/**
	 * This will create a tooltip based on stored information.
	 *
	 * @param tempMyNode
	 *            the non-visually stored node
	 * @param tempGraphNode
	 *            the visually stored node
	 */
	private static void createInfoTip(ModelicaNode tempMyNode, GraphNode tempGraphNode) {
		ArrayList<String> toolTipList = tempMyNode.getToolTipInfo();
		tempGraphNode.setTooltip(new Label(
				"Type: " + toolTipList.get(0) + "\n" +
						"Name: " + toolTipList.get(1) + "\n" +
						"Descripton: " + toolTipList.get(2) + "\n" +
						"Position: "   + toolTipList.get(3) + "\n" +
						"Path: "       + toolTipList.get(4) + "\n"
				));
	}
}