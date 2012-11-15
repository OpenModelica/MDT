
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
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
	private static ArrayList<MyEquation> subEquations;

	@Override
	public void createPartControl(Composite parent)
	{
		System.out.println("[DetailedGraphView initial] Creating a new view");
		detailedGraph = new Graph(parent, SWT.NONE);
	}

	public static void createDetailedGraph(String className) throws ConnectException, UnexpectedReplyException, InvocationError{
		System.out.println("[Detailed Graph View] Loading class into analyze for the detailed view");
		IPath classPath = new Path(CrossAnalyzer.currentCompiler.getClassLocation(className).getPath());
		CrossAnalyzer.currentCompiler.loadFile(classPath.toString());
		ArrayList<Graph> graphSteps = new ArrayList<Graph>();
		analyzeDetailedClass(className);
		graphSteps.add(detailedGraph);
		currentStep = 0;
	}

	public static Graph getDetailedGraph(int step) {
		// TODO: Maybe we can keep all of them, but in order to avoid expensive regenerations 
		// we can instead turn unused graphs invisible?
		currentStep = step;
		return(graphSteps.get(step));
	}

	private static void analyzeDetailedClass(String className) {
		System.out.println("[Detailed Graph View] Analyzing the loaded file");

		try
		{
			analyzeDetailedClassEquations(className);
		} catch (ConnectException e)
		{
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		} catch (UnexpectedReplyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		analyzeDetailedClassVariables(className);
	}

	private static void analyzeDetailedClassEquations(String className) throws ConnectException, UnexpectedReplyException {
		System.out.println("[Detailed Graph View] Analyzing the loaded file's equations");
		// Repeat:

		// 2. Read line
		int numberOfEquations =  CrossAnalyzer.currentCompiler.getEquationItemsCount(className);
		for (int i = 0; i < numberOfEquations; i++) {
			ICompilerResult res = CrossAnalyzer.currentCompiler.getNthEquationItem(className, i+1);
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
		subEquations = new ArrayList<MyEquation>();
		
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
	
	private static String functionalAnalyze(String funCall) {
		
		return funCall;
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
			subEquations.add(new MyEquation(operatorNode, newEq));
		} else if (leftNode != null) {
			subEquations.add(new MyEquation(leftNode, newEq));
		} else if (rightNode != null) {
			subEquations.add(new MyEquation(rightNode, newEq));
		}

		return newEq;
	}

	private static GraphNode createEquationNode(String side) {
		for (MyEquation mEq : subEquations) {
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