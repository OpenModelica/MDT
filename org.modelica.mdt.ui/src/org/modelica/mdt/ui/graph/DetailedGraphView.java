
package org.modelica.mdt.ui.graph;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

public class DetailedGraphView extends ViewPart {

	private static Graph detailedGraph;
	private static ArrayList<Graph> graphSteps;
	private static int currentStep;
	private static Stack<String> operationStack;
	private static ArrayList<ModelicaEquation> subEquations;
	
	private Composite detailedParent;
	private Composite equationPanel;
	public static int numberOfEquations;
	
	@Override
	public void createPartControl(Composite parent)
	{
		System.out.println("[Detailed Graph View] Creating the root view");
		detailedParent = parent;
		
		// 2. create a panel to the left
		// and a view to the right
		//Display display = new Display ();
	    //Shell shell = new Shell (display);
	    detailedParent.setLayout(new FillLayout());
	        
	      // set the size of the scrolled content - method 1
	    detailedParent.setLayout(new FillLayout());
	        
	      // set the size of the scrolled content - method 1
	      final ScrolledComposite sc1 = new ScrolledComposite(detailedParent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
	      Composite equationPanel = new Composite(sc1, SWT.NONE);
	      sc1.setContent(equationPanel);
	      GridLayout layout = new GridLayout();
	      layout.numColumns = 1;
	      equationPanel.setLayout(layout);
	      
	      for (int i = 0; i > numberOfEquations; i++) {
	    	  Button eqB = new Button (equationPanel, SWT.PUSH);
	    	  eqB.setText("temp");
	     }
		 this.equationPanel.setSize(equationPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
         equationPanel.layout();
	      
	      // Replace add with a new type of a window that we can place views in 
	      Button add = new Button (detailedParent, SWT.PUSH);
	      add.setText("add children");
	      
	      /*
	      final int[] index = new int[]{0};
	      add.addListener(SWT.Selection, new Listener() {
	          public void handleEvent(Event e) {
	              index[0]++;
	              Button button = new Button(equationPanel, SWT.PUSH);
	              button.setText("button "+index[0]);
	              // reset size of content so children can be seen - method 1
	              equationPanel.setSize(equationPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	              equationPanel.layout();
	          }
	      });
	      */
	}

	private static void analyzeDetailedClassEquations(String className) throws ConnectException, UnexpectedReplyException {
		System.out.println("[Detailed Graph View] Analyzing the loaded file's equations");
		
		int n =  ModelicaGraphAnalyzer.getModelicaCompiler().getEquationItemsCount(className);
		for (int i = 0; i < n; i++) {
			// create a new graph here
			//graphSteps.add(detailedGraph);
			
			ICompilerResult res = ModelicaGraphAnalyzer.getModelicaCompiler().getNthEquationItem(className, i+1);
			String trimRes = res.getFirstResult();
			System.out.println("Incoming equation: " + trimRes);

			// Remove all annotations from the code line
			trimRes = trimRes.substring(1, trimRes.length()-2);
			if (trimRes.contains("annotation")) {
				trimRes.replace(trimRes.substring(trimRes.indexOf("annotation"), trimRes.length()), "");
			}
			// Remove all comments from the code line
			if (trimRes.contains("//")) {
				trimRes.replace(trimRes.substring(trimRes.indexOf("//"), trimRes.length()), "");
			}

			// Remove all loops from the code line
			// TODO: Do we need this?
			if (trimRes.contains("while") || trimRes.contains("for")){
				continue;
			}

			trimRes = trimRes.replaceAll(" ", "");
			trimRes = "(" + trimRes + ")";

			equationAnalyze(trimRes, true);
		}
	}

	private static void equationAnalyze(String operationalLine, boolean root) {
		//operationalLine = "(a.x(y)=c.x)"; // test-data
		System.out.print("So far we got: " + operationalLine + " ==> ");

		Pattern p = Pattern.compile("(\\w+((\\.\\w+)?)*)|([<|>|=][=|>]|[+|-|*|/|(|)|,|=|^|<|>]|[:][=])");
		Matcher m = p.matcher(operationalLine);
		ArrayList<String> tokenArray = new ArrayList<String>();

		while (m.find()) {
			String token = m.group();
			System.out.print("[" + token + "]");
			tokenArray.add(token);
		}
		System.out.print("\n");

		operationStack = new Stack<String>();
		subEquations = new ArrayList<ModelicaEquation>();

		// TODO: Temporary quick-fix for = in equations
		/*if (tokenArray.contains("=")){
			ArrayList<String> leftSplit = new ArrayList<String>();
			ArrayList<String> rightSplit = new ArrayList<String>();
			deepEquationAnalyze(leftSplit);
			deepEquationAnalyze(rightSplit);

		} else {
		 */
		deepEquationAnalyze(tokenArray);
		//}

		// Ordered layout according to tree-nodes
		detailedGraph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		detailedGraph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
	}

	private static void deepEquationAnalyze(ArrayList<String> line){
		int prevPrecedence = 0;
		int currPrecedence = 0;

		for(int i = 0; i < line.size(); i++)
		{
			prevPrecedence = currPrecedence;
			String c = line.get(i);
			System.out.println(i + ": " + c);

			if (c.equals(")")) {
				// evaluate to the top
				for(int j = 0; j <= i; j+=1) {
					System.out.println("(backwards) " + (i-j) + ": " + line.get(i-j));
					System.out.print("Stack is: ");
					for (String ss : operationStack)
						System.out.print(ss);
					System.out.println();
					String inner_c = line.get(i-j);

					if (inner_c.equals("(")) {
						line.remove(i-j);
						i--;
						break;
					} else if (inner_c.equals(")")) {
						continue;
					}

					String resultOfSub = createSubTree(i-j);
					if (resultOfSub.length() == 0)
						break;
					operationStack.push(resultOfSub);

					Pattern p = Pattern.compile("(\\w+((\\.\\w+)?)*)|([<|>|=][=|>]|[+|-|*|/|(|)|,|=|^|<|>])");
					Matcher m = p.matcher(resultOfSub);
					int inner_push_backwards = 0;
					while(m.find()) {
						inner_push_backwards++;
					}

					j = inner_push_backwards;
				}
			} else if (c.equals("^")) {
				currPrecedence = 6;
				if (prevPrecedence >= currPrecedence) {
					// evaluate what we have
					operationStack.push(createSubTree(i));

					continue;
				}
				operationStack.push(c);

			} else if (c.equals("*") || c.equals("/")) {
				currPrecedence = 5;
				if (prevPrecedence >= currPrecedence) {
					// evaluate what we have
					operationStack.push(createSubTree(i));
					continue;
				}
				operationStack.push(c);

			} else if (c.equals("+") || c.equals("-")) {
				currPrecedence = 4;
				if (prevPrecedence >= currPrecedence) {
					// evaluate what we have
					operationStack.push(createSubTree(i));
					continue;
				}
				operationStack.push(c);
			} else if (c.equals("=") || c.equals("<=") || c.equals(">=") || c.equals("<>") || c.equals("==")) {
				currPrecedence = 3;
				if (prevPrecedence >= currPrecedence) {
					// evaluate what we have
					operationStack.push(createSubTree(i));
					continue;
				}
				operationStack.push(c);
			} else if (c.equals("(")) {
				continue;
			} else {
				// variables, "," and integers
				currPrecedence = 1;
				operationStack.push(c);
			}
		}
	}

	private static String createSubTree(int endIndex) {
		String newEq = "";
		String leftSide = "";
		String rightSide = "";
		String operator = "";
		GraphNode leftNode = null;
		GraphNode rightNode = null;
		GraphNode operatorNode = null;

		if (!operationStack.isEmpty()) {
			rightSide = operationStack.pop();
			rightNode = createEquationNode(rightSide);
		}

		if (!operationStack.isEmpty()) {
			operator = operationStack.pop();
			operatorNode = createEquationNode(operator);
			if (rightNode != null) {
				new GraphConnection(detailedGraph, SWT.NONE, operatorNode, rightNode);
			}
		}

		if (!operationStack.isEmpty()) {
			leftSide = operationStack.pop();
			leftNode = createEquationNode(leftSide);
			if (operatorNode != null) {
				new GraphConnection(detailedGraph, SWT.NONE, operatorNode, leftNode);
			}
		}

		if (operator != "") {
			newEq = operator + "[" + leftSide + " " + rightSide +"]";
		}

		System.out.println("We get from createSubTree " + newEq);

		if (operatorNode != null) {
			subEquations.add(new ModelicaEquation(operatorNode, newEq));
		} else if (leftNode != null) {
			subEquations.add(new ModelicaEquation(leftNode, newEq));
		} else if (rightNode != null) {
			subEquations.add(new ModelicaEquation(rightNode, newEq));
		}

		return newEq;
	}

	private static GraphNode createEquationNode(String side) {
		for (ModelicaEquation mEq : subEquations) {
			if (mEq.equation == side) 
				return(mEq.topNode);
		}
		GraphNode gNode = new GraphNode(detailedGraph, SWT.NONE, side);
		gNode.setBackgroundColor(detailedGraph.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		return(gNode);
	}

	private static void analyzeDetailedClassVariables(String className) {
		System.out.println("[Detailed Graph View] Analyzing the loaded file's variables");

		// 8. Extract the variables that has pre-instantiated values

		// 9. Check if there is another node containing one of those variables, in that case
		// we should add a ":= <value>" to the text in the node
		// for (x...)
		//		for (y...)
		// 			if (x.getText().equals(y)
		//				x.setText(<old value> + ":= <value>");
	}

	// TODO: Create a call-function that can analyze the modifications discovered in XMLParser.java
	// This function should do changes to the nodes already existing, remove some of them or add more nodes (i.e. dummies)
	// This should also somehow store the new unique graph after modifications within an array with a operation-index, so that
	// we can step over several graphs


	@Override
	public void setFocus()
	{
		// TODO Auto-generated method stub
	}
}