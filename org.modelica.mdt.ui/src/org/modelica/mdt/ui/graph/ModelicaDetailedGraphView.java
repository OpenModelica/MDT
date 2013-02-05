
package org.modelica.mdt.ui.graph;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.core.widgets.internal.GraphLabel;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * This class sets up another view presenting each equation
 * of a Modelica class which is scrollable. When selecting
 * and equation a corresponding graph will be generated to
 * the right representing an operand-tree of that specific
 * equation-line.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaDetailedGraphView extends ViewPart {

	private static Stack<String> operationStack;
	private static ArrayList<ModelicaEquation> subEquations;

	private static Composite detailedParent;
	private static Text textPanel;
	private static String equationDescriptions;
	private static ScrolledComposite sc2;
	private static Composite equationPanel;
	private static Composite graphPanel;

	private static ArrayList<Button> buttonArray;
	private static Graph[] graphArray;

	@Override
	public void createPartControl(Composite parent)
	{
		System.out.println("[Detailed Graph View] Creating the root view");
		detailedParent = parent;

		detailedParent.setLayout(new FillLayout(SWT.VERTICAL));
		buttonArray = new ArrayList<Button>();
		
		
		Composite topArea = new Composite(detailedParent, SWT.NONE);
		topArea.setLayout(new FillLayout());
		
		Composite bottomArea = new Composite(detailedParent, SWT.NONE);
		bottomArea.setLayout(new FillLayout());
	    textPanel = new Text(bottomArea, SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		//textPanel.setTextLimit(5);
		
		final ScrolledComposite sc1 = new ScrolledComposite(topArea, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		equationPanel = new Composite(sc1, SWT.NONE);
		sc1.setContent(equationPanel);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		equationPanel.setLayout(layout);
		equationPanel.setSize(equationPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		sc2 = new ScrolledComposite(topArea, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		sc2.setExpandHorizontal(true);
		sc2.setExpandVertical(true);
		
		graphPanel = new Composite(sc2, SWT.NONE);

		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginHeight = 5;
		fillLayout.marginWidth = 5;
		fillLayout.spacing = 1;
		graphPanel.setLayout(fillLayout);

		sc2.setContent(graphPanel);
	}

	private static void callMiniGraph(boolean graphVisible, Composite grid, int index)
	{
		System.out.println("Do something with the grid based on index " + index);

		// 1. Check if the graph of this index already exist
		if (!graphVisible) {
			//	2. if it doesn't, do addMiniGraph(grid, i)
			addMiniGraph(grid, index);
		} else {
			//	3. if it does, do removeMiniGraph(grid, i)
			// TODO: How do we know which one to remove? Create a new class to keep track of int?
			System.out.println("remove graph at " + index);
			removeMiniGraph(grid, index);
		}
	}

	private static void addMiniGraph(Composite grid, int index)
	{
		// TODO: Find a way for the graphs to stack up, like they do in createPartControl
		System.out.println("Now we are setting a text");
		
		Graph g = new Graph(grid, SWT.NONE);
		GraphLabel test = new GraphLabel(true);
		test.setText("Hello");
		g.setSize(300, 300);
		graphArray[index] = g;

		try
		{
			System.out.println("Index is " + index);
			analyzeDetailedClassEquation(buttonArray.get(index).getText().substring(0, buttonArray.get(index).getText().length()-1), index, g);
		} catch (ConnectException e1)
		{
			e1.printStackTrace();
		} catch (UnexpectedReplyException e1)
		{
			e1.printStackTrace();
		}

		g.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

		Device device = Display.getCurrent();
		Color black = new Color (device, 0, 0, 0);
		test.setBorderColor(black);

		g.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);

		graphPanel.setSize(graphPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		grid.layout();
	}

	private static void removeMiniGraph(Composite grid, int index)
	{
		System.out.println("Now we would like to remove the graph at index " + index);
		graphArray[index].dispose();
	}

	public static void createEquationButtons(final String className) throws ConnectException, UnexpectedReplyException {
		System.out.println("Add equation buttons based on " + className);
		int numberOfEquations = ModelicaGraphAnalyzer.getModelicaCompiler().getEquationItemsCount(className);
		refreshView();
		graphArray = new Graph[numberOfEquations];
		
		// TODO: How do we stretch the width of these buttons?
		for (int i = 0; i < numberOfEquations; i++) {
			final Button equationB = new Button(equationPanel, SWT.RADIO);
			final int buttonIndex = i;
			ICompilerResult res = ModelicaGraphAnalyzer.getModelicaCompiler().getNthEquationItem(className, buttonIndex+1);
			String equationString = res.getFirstResult();
			equationB.setText(equationString.substring(1, equationString.length()-1));
			System.out.println("Create a new button with index " + buttonIndex + " and equation " + equationB.getText());
			buttonArray.add(equationB);

			equationB.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event e) {
					System.out.println("Clicked on " + buttonArray.get(buttonIndex).getText() + " with int " + buttonIndex);
					callMiniGraph(!buttonArray.get(buttonIndex).getSelection(), graphPanel, buttonIndex);
					analyzeOptimizations(buttonArray.get(buttonIndex).getText().substring(0, buttonArray.get(buttonIndex).getText().length()-1), className);
				}
			});			
		}

		equationPanel.setSize(equationPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		equationPanel.layout();
	}

	private static void analyzeDetailedClassEquation(String trimRes, int i, Graph g) throws ConnectException, UnexpectedReplyException {
		System.out.println("[Detailed Graph View] Analyzing the loaded file's equations");

		System.out.println("Incoming equation: " + trimRes);

		// Remove all annotations from the code line
		//trimRes = trimRes.substring(0, trimRes.length()-2);
		if (trimRes.contains("annotation")) {
			trimRes.replace(trimRes.substring(trimRes.indexOf("annotation"), trimRes.length()), "");
		}
		// Remove all comments from the code line
		if (trimRes.contains("//")) {
			trimRes.replace(trimRes.substring(trimRes.indexOf("//"), trimRes.length()), "");
		}

		// Remove all loops from the code line
		// TODO: Do we need this? How is this handled?
		//if (trimRes.contains("while") || trimRes.contains("for")){
		//}

		trimRes = trimRes.replaceAll(" ", "");
		trimRes = "(" + trimRes + ")";
		equationAnalyze(trimRes, g);
	}

	private static void equationAnalyze(String operationalLine, Graph g) {
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
		deepEquationAnalyze(tokenArray, g);
		//}

		// Ordered layout according to tree-nodes
		//detailedGraph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		//detailedGraph.setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
	}

	/**
	 * Transforms a regular expression to a prefix notation expression.
	 * This makes it easier for a graph visualizer to know what the top
	 * of a tree should be relatively other two nodes. The top node is
	 * the operand operating on the two expressions, placed in the
	 * child nodes.
	 * 
	 * 
	 * @param line
	 * 				an array of all separated elements
	 * @param g
	 * 				the involved graph object
	 */

	private static void deepEquationAnalyze(ArrayList<String> line, Graph g){
		int prevPrecedence = 0;
		int currPrecedence = 0;

		for(int i = 0; i < line.size(); i++)
		{
			prevPrecedence = currPrecedence;
			String c = line.get(i);
			//System.out.println(i + ": " + c);

			if (c.equals(")")) {
				// evaluate to the top
				for(int j = 0; j <= i; j+=1) {
					//System.out.println("(backwards) " + (i-j) + ": " + line.get(i-j));
					//System.out.print("Stack is: ");
					/*
					for (String ss : operationStack)
						System.out.print(ss);
					System.out.println();
					 */
					String inner_c = line.get(i-j);

					if (inner_c.equals("(")) {
						line.remove(i-j);
						i--;
						break;
					} else if (inner_c.equals(")")) {
						continue;
					}

					String resultOfSub = createSubTree((i-j), g);
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
					operationStack.push(createSubTree(i, g));

					continue;
				}
				operationStack.push(c);

			} else if (c.equals("*") || c.equals("/")) {
				currPrecedence = 5;
				if (prevPrecedence >= currPrecedence) {
					// evaluate what we have
					operationStack.push(createSubTree(i, g));
					continue;
				}
				operationStack.push(c);

			} else if (c.equals("+") || c.equals("-")) {
				currPrecedence = 4;
				if (prevPrecedence >= currPrecedence) {
					// evaluate what we have
					operationStack.push(createSubTree(i, g));
					continue;
				}
				operationStack.push(c);
			} else if (c.equals("=") || c.equals("<=") || c.equals(">=") || c.equals("<>") || c.equals("==")) {
				currPrecedence = 3;
				if (prevPrecedence >= currPrecedence) {
					// evaluate what we have
					operationStack.push(createSubTree(i, g));
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

	private static String createSubTree(int endIndex, Graph currentGraph) {
		String newEq = "";
		String leftSide = "";
		String rightSide = "";
		String operator = "";
		GraphNode leftNode = null;
		GraphNode rightNode = null;
		GraphNode operatorNode = null;

		if (!operationStack.isEmpty()) {
			rightSide = operationStack.pop();
			rightNode = createEquationNode(currentGraph, rightSide);
		}

		if (!operationStack.isEmpty()) {
			operator = operationStack.pop();
			operatorNode = createEquationNode(currentGraph, operator);
			if (rightNode != null) {
				new GraphConnection(currentGraph, SWT.NONE, operatorNode, rightNode);
			}
		}

		if (!operationStack.isEmpty()) {
			leftSide = operationStack.pop();
			leftNode = createEquationNode(currentGraph, leftSide);
			if (operatorNode != null) {
				new GraphConnection(currentGraph, SWT.NONE, operatorNode, leftNode);
			}
		}

		if (operator != "") {
			newEq = operator + "[" + leftSide + " " + rightSide +"]";
		}

		//System.out.println("We get from createSubTree " + newEq);

		if (operatorNode != null) {
			subEquations.add(new ModelicaEquation(operatorNode, newEq));
		} else if (leftNode != null) {
			subEquations.add(new ModelicaEquation(leftNode, newEq));
		} else if (rightNode != null) {
			subEquations.add(new ModelicaEquation(rightNode, newEq));
		}

		return newEq;
	}

	private static GraphNode createEquationNode(Graph targetGraph, String side) {
		for (ModelicaEquation mEq : subEquations) {
			if (mEq.equation == side) 
				return(mEq.topNode);
		}
		GraphNode gNode = new GraphNode(targetGraph, SWT.NONE, side);
		gNode.setBackgroundColor(targetGraph.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		return(gNode);
	}

	// TODO: Make this possible, should be easy to create
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

	private static void analyzeOptimizations(String equation, String className) {
		System.out.println("performing analyzation of the optimizations");
		
		ModelicaDetailedAnalyzer.setStartingEquation(equation);
		try
		{
			consoleDump(className);
		
			XMLMarker.readTextFile();
			XMLParser.readTextFile();
			
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		// TODO: this is not working?
		
		// Print out all operations in the textfield
		textPanel.setText(ModelicaDetailedAnalyzer.getFullText());
	}
	
	/**
	 * This will send a command to the cmd so that a dump operation 
	 * is performed on a class and dumps operations into a .txt-file.
	 * This command ISN'T sent to the OMC-API.
	 * 
	 * @throws IOException 
	 */
	private static void consoleDump(String className) throws IOException {
		// TODO: This has to be fixed within MDT to handle simulation with the dump-flag better
		System.out.println("Perform dump");
		
		FileOutputStream fo = new FileOutputStream("C:/runtime-EclipseApplication/testCases/" + className + ".mos");
		String localClassPath = ModelicaGraphAnalyzer.classPath;
		
		new PrintStream(fo).println("loadModel(Modelica);");
		new PrintStream(fo).println("getErrorString();");
		new PrintStream(fo).println("loadFile(\"" + localClassPath + "\");");
		new PrintStream(fo).println("getErrorString();");
		
		//new PrintStream(fo).println("cd(\"" + "C:/runtime-EclipseApplication/testCases" + "\");");
		new PrintStream(fo).println("simulate(" + className + ");");
		new PrintStream(fo).println("getErrorString();");

		consoleDumpCommand(className, localClassPath);
		/*
		consoleDumpCommand(new String[]{"cmd", "omc", "+s", "+i=C:/runtime-EclipseApplication/testCases/" + className + ".mo", 
				"simCodeTarget=Dump", "C:\\runtime-EclipseApplication\\testCases\\" + className + ".mos",
				">", "C:\\runtime-EclipseApplication\\testCases\\a.txt"});
		*/
	}

	private static void consoleDumpCommand(String className, String classPath) throws IOException{
		// - Set the simCodeTarget option via setCommandLineOptions
		
		System.out.println("Do we get here?");
		// - Run the simulate command.
		//System.out.println(ModelicaGraphAnalyzer.getModelicaCompiler().sendExpression("simulate(C:\\runtime-EclipseApplication\\testCases\\+ " +className+ ".mos", false);
		
		//Process p = Runtime.getRuntime().exec("omc +s +i=C:/runtime-EclipseApplication/testCases/ModelicaC.mo simCodeTarget=Dump C:/runtime-EclipseApplication/testCases/ModelicaC.mos > C:/runtime-EclipseApplication/testCases/a.txt");
		ProcessBuilder pb = new ProcessBuilder("cmd", "omc", "+s", "+i="+classPath+" simCodeTarget=Dump C:/runtime-EclipseApplication/testCases/"+ className + ".mos");
		Process p = pb.start();
		
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		// read the output from the command
		String s = null;
		System.out.println("Here is the standard output of the command:\n");
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}	
	}
	
	private static void refreshView() {
		for (Button b : buttonArray)
			b.dispose();
		buttonArray = new ArrayList<Button>();
		
		if (graphArray != null)
			for (Graph g: graphArray) {
				g.dispose();
			}
	}
	
	@Override
	public void setFocus()
	{
		// TODO Auto-generated method stub
	}
}