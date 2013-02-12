
package org.modelica.mdt.ui.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.swt.SWT;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * This class analyze the code of a file and to extract class-/package-dependencies, 
 * and then send it onwards to org.modelica.mdt.ui.graph.GraphGenerator.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaGraphAnalyzer {

	// Keeps track of the loaded compiler
	private static IModelicaCompiler currentCompiler;

	// Keeps track of the class path
	public static String classPath;

	// Index over nodes and connections
	private static int nid = 0;
	private static int cid = 0;
	public static double elapsedTimeLines = 0.0;
	public static double elapsedTimeNodes = 0.0;
	public static double elapsedTimeConnections = 0.0;
	public static double elapsedTimeFamiliar = 0.0;

	/**
	 * This will start the analyze process by initiating the compiler
	 * and set up the local modelica environment in order to
	 * be able to perform calls of the API. When that is done it will
	 * start the analyze by calling analyzeClasses.
	 *
	 * @param fileName
	 *            the file name with file-extension
	 * @param filePath
	 *            the path to the file
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 * @exception InvocationException
	 *                if the Modelica compiler replies with an unexpected error in results
	 */
	public static void initAnalyze(String fileName, IPath filePath) 
			throws ConnectException, UnexpectedReplyException, InvocationError {
		String className;
		ModelicaNode coreNode;
		long t3 = System.currentTimeMillis();
		
		
		try
		{
			// initiate compiler
			currentCompiler = CompilerProxy.getCompiler();
			System.out.println("[Analyze Operation] Found the compiler");

		} catch (CompilerInstantiationException e)
		{
			System.out.println("[Error] Compiler not found");
			e.printStackTrace();
		}

		long tA = System.currentTimeMillis();
		double elapsedTimeSeconds = (tA - t3)/1000.0;
	    System.out.println("The first part of initialization of analyze took " + elapsedTimeSeconds + " s");
		
		// Load Modelica library only one time
		if (!ModelicaGraphView.loadedModelica){
				currentCompiler.getStandardLibrary();
				ModelicaGraphView.loadedModelica = true;
		}
		classPath = filePath.toString();
		long tB = System.currentTimeMillis();
		elapsedTimeSeconds = (tB - tA)/1000.0;
	    System.out.println("The second part of initialization of analyze took " + elapsedTimeSeconds + " s");
		
		// load modelica file
		currentCompiler.loadFile(classPath);

		long tC = System.currentTimeMillis();
		elapsedTimeSeconds = (tC - tB)/1000.0;
	    System.out.println("The third part of initialization of analyze took " + elapsedTimeSeconds + " s");
		
		// lists local classes of the file
		List classList = currentCompiler.parseFile(classPath);

		long tD = System.currentTimeMillis();
		elapsedTimeSeconds = (tD - tC)/1000.0;
	    System.out.println("The fourth part of initialization of analyze took " + elapsedTimeSeconds + " s");
				
		nid = -1;
		//System.out.println(classPath);
		
		for (int j = 0; j < classList.size(); j++) {
			className = classList.elementAt(j).toString();

			// Standard: (Useful in thesis report - optimizing development phase) 
			// recursive = true

			// Optional: (Useful in thesis report or as an optional settings - first development phase)
			// recursive = false

			// Creates root node
			if (!nodesContains(className)) {
				nid += 1;
				System.out.println(nid);
				if(currentCompiler.isPackage(className)) {
					coreNode = new ModelicaNode(nid, className, SWT.COLOR_GRAY);
				} else {
					coreNode = new ModelicaNode(nid, className, SWT.COLOR_GREEN);
				}
				setToolTipInfo(coreNode, className, classPath);

				coreNode.expandable = false;
				ModelicaGraphGenerator.nodes.add(coreNode);
			}
			
			long t4 = System.currentTimeMillis();
			elapsedTimeSeconds = (t4 - tD)/1000.0;
		    System.out.println("The fifth part took " + elapsedTimeSeconds + " s");
			
		    elapsedTimeSeconds = (t4 - t3)/1000.0;
		    System.out.println("The initialization of analyze took " + elapsedTimeSeconds + " s");
			
			// TODO: Test if this is correct when a dependency is found between class A to class B before class B has been analyzed
			//		 Where class A and class B both comes from the same start-file

			analyzeClasses(nid, className, false);
		}
	}

	/**
	 * Iterates over code by using the results from Modelica API, through
	 * calls to OMC.
	 *
	 * @param prevId
	 *            the ID of the previous node that started a dependency to this node
	 * @param className
	 *            the name of this class
	 * @param recursive
	 *            tells whether or not the analyze should be performed recursively
	 * @return the prevID that was used for the current analyze iteration
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 * @exception InvocationException
	 *                if the Modelica compiler replies with an unexpected error in results
	 */
	public static int analyzeClasses(int prevID, String className, boolean recursive) 
			throws ConnectException, UnexpectedReplyException, InvocationError {
		System.out.println("[Analyze Operation] Finding classes of " + className + " (cid/nid: " + cid + "/" + nid + ")");
		currentCompiler.loadFile(classPath);

		// Find the underlying classes of a package
		
		if(currentCompiler.isPackage(className)) {
			List classList = currentCompiler.getClassNames(className);
			for (int j = 0; j < classList.size(); j++) {
				String nam = classList.elementAt(j).toString();
				createBond(className, className + "." + nam, recursive, prevID, SWT.LINE_DASH, SWT.COLOR_GRAY);
			}
		}

		// Find inheritance-dependencies
		int num =  currentCompiler.getInheritanceCount(className);
		for (int i = 0; i < num; i++) {
			ICompilerResult res = currentCompiler.getNthInheritedClass(className, i+1);
			createBond(className, res.getFirstResult(), recursive, prevID, SWT.LINE_SOLID, SWT.COLOR_GREEN);
		}

		// Find component-dependencies
		List componentList = currentCompiler.getComponents(className);
		for (int j = 0; j < componentList.size(); j++) {
			String nam = componentList.elementAt(j).toString();
			System.out.println("[Analyze Operation] We found object " + nam + " !");
			createBond(className, nam, recursive, prevID, SWT.LINE_SOLID, SWT.COLOR_GREEN);
		}

		// Find function-call-dependencies among algorithms
		num =  currentCompiler.getAlgorithmItemsCount(className);
		for (int i = 0; i < num; i++) {
			ICompilerResult res = currentCompiler.getNthAlgorithmItem(className, i+1);
			String trimRes = res.getFirstResult();
			checkExist(className, trimRes, recursive, prevID);
		}

		// Find function-call-dependencies among equations
		num =  currentCompiler.getEquationItemsCount(className);
		for (int i = 0; i < num; i++) {
			ICompilerResult res = currentCompiler.getNthEquationItem(className, i+1);
			String trimRes = res.getFirstResult();
			checkExist(className, trimRes, recursive, prevID);
		}

		// Check if this has a dependency from a package
		analyzeParent(prevID, className, recursive);
		return prevID;
	}

	/**
	 * Provides the information of the tooltip for the given node, using results
	 * from Modelica API.
	 *
	 * @param node
	 *            the ModelicaNode that stores all information about every node
	 * @param className
	 *            the name of this class
	 * @param filePath
	 *            the path to the file
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 */
	private static void setToolTipInfo(ModelicaNode node, String className, String classPath) 
			throws ConnectException, UnexpectedReplyException {
		// loads the file in order to perform new OMC calls on this specific file
		currentCompiler.loadFile(classPath);

		String classType = currentCompiler.getClassRestriction(className).getFirstResult().replace("\"", "");
		classType = classType.trim();
		String classComment = currentCompiler.getClassComment(className).getFirstResult().replace("\"", "");
		classComment = classComment.trim();
		node.setClassName(className);

		// extract substring of full class name
		if (className.contains(".")){
			node.setClassName(className.substring(className.lastIndexOf(".")+1, className.length()));
		}

		// extract the class type if there is any
		if (!classType.isEmpty()) 
			node.setClassType(classType.substring(0, 1).toUpperCase() + classType.substring(1, classType.length()));

		node.setClassDescription(classComment);
		node.setClassPosition(className);
		node.setClassPath(classPath);
	}

	/**
	 * Parse the line of code in order to find any function calls that
	 * inflicts dependencies
	 *
	 * @param className
	 *            the name of this class
	 * @param trimRes
	 *            the trimmed string that resulted of a OMC call
	 * @param recursive
	 *            tells whether or not the analyze should be performed recursively
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 * @exception InvocationException
	 *                if the Modelica compiler replies with an unexpected error in results
	 */
	private static void checkExist(String className, String trimRes, boolean recursive, int prevID) 
			throws ConnectException, UnexpectedReplyException, InvocationError {
		int lastIndex = 0;
		int count = 0;

		while (lastIndex != -1) {
			lastIndex = trimRes.indexOf("(", lastIndex);
			if (lastIndex != -1) {
				count++;
				lastIndex++;
			}
		}

		// Removes all unnecessary stuff
		trimRes = trimRes.replaceAll("\"","");
		while (count > 0){
			String tempRes = trimRes.substring(0, trimRes.indexOf('('));
			trimRes = trimRes.substring(tempRes.length()+1, trimRes.length());

			trimRes.replaceAll("\\s\\+\\-\\*\\=\\<\\>\\:", "");
			/*
			tempRes = tempRes.replaceAll("\\s","");
			tempRes = tempRes.replaceAll("\\+"," ");
			tempRes = tempRes.replaceAll("\\-"," ");
			tempRes = tempRes.replaceAll("\\*"," ");
			tempRes = tempRes.replaceAll("\\="," ");
			tempRes = tempRes.replaceAll("\\<"," ");
			tempRes = tempRes.replaceAll("\\>"," ");
			tempRes = tempRes.replaceAll("\\:"," ");
			 */
			tempRes = tempRes.trim();
			tempRes = tempRes.substring(tempRes.lastIndexOf(" ")+1,tempRes.length());

			// TODO: Should we make a list of all things that doesn't exist to avoid looking up same things over?
			System.out.println("[Analyze Operation] Does " + tempRes + " exist? " + currentCompiler.existClass(tempRes));

			// Create a connection from this found function dependency
			if (tempRes.length() != 0 && currentCompiler.existClass(tempRes))
				createBond(className, tempRes, recursive, prevID, SWT.LINE_DOT, SWT.COLOR_GREEN);
			count -=1;
		}
	}

	/**
	 * Analyzes if something comes from a package. If it does then we need
	 * to create a new connection from this dependency
	 *
	 * @param prevId
	 *            the ID of the previous node that started a dependency to this node
	 * @param fullName
	 *            the name of this class, including all . characters
	 * @param recursive
	 *            tells whether or not the analyze should be performed recursively
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 * @exception InvocationException
	 *                if the Modelica compiler replies with an unexpected error in results
	 */
	public static void analyzeParent(int prevID, String fullName, boolean recursive) 
			throws ConnectException, UnexpectedReplyException, InvocationError {
		if (fullName.contains(".") && currentCompiler.isPackage(fullName.substring(0, fullName.lastIndexOf(".")))){
			String packageName = fullName.substring(0, fullName.lastIndexOf("."));
			createBond(fullName, packageName, recursive, prevID, SWT.LINE_DASH, SWT.COLOR_GRAY);
		}
	}

	/**
	 * Creates a connection
	 *
	 * @param sourceName
	 *            the name of the object that inflicts the dependency
	 * @param targetName
	 *            the name of the object the dependency should point to
	 * @param rec
	 *            tells whether or not the analyze should be performed recursively
	 * @param prev
	 *            the ID of the previous node that started the dependency
	 * @param style
	 *            tells what style the connection should have
	 * @param color
	 *            tells what color the target node should have
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 * @exception InvocationException
	 *                if the Modelica compiler replies with an unexpected error in results
	 */
	public static void createBond(String sourceName, String targetName, boolean recursive, int prev, int style, int color) 
			throws ConnectException, UnexpectedReplyException, InvocationError {
		
		// TODO: OpenModelica is missing a good way of checking for a Keyword
		if (targetName.equals("Real") || 
			targetName.equals("assert") || 
			targetName.equals("Integer")) {
			
			System.out.println("[Analyze Operation] " + targetName + " is a Keyword and should not be generated or analyzed");
			return;
		}
		
		System.out.println(sourceName);
		String myPath = (new Path(ModelicaGraphAnalyzer.currentCompiler.getClassLocation(sourceName).getPath()).toString());
		System.out.println("this is working: " + myPath);
		
		long t1 = System.currentTimeMillis();
		ArrayList<Integer> lineNumbers = findLineNumber(myPath, targetName);
		long t2 = System.currentTimeMillis();
		elapsedTimeLines += (t2 - t1)/1000.0;
	    boolean familiar = true;

		// recursive
		if (recursive) {
			if (!nodesContains(targetName)) {
				nid += 1;
				ModelicaNode node = new ModelicaNode(nid, targetName, color);
				ModelicaGraphGenerator.nodes.add(node);
				familiar = false;
				setToolTipInfo(node, targetName, myPath);
			}
			analyzeClasses(nid, targetName, true);

			// non-recursive
		} else if (!nodesContains(targetName)) {
			long t3 = System.currentTimeMillis();
			
			nid += 1;
			ModelicaNode node = new ModelicaNode(nid, targetName, color);
			ModelicaGraphGenerator.nodes.add(node);
			familiar = false;
			setToolTipInfo(node, targetName, myPath);
			long t4 = System.currentTimeMillis();
			elapsedTimeNodes = (t4 - t3)/1000.0;
		}

		int n = 0;
		// Fix for handling analyze of multiple trees of classes
		if(!nodesContains(targetName)) 
			n = nid;
		else {
			int j;
			for(j = 0; j < ModelicaGraphGenerator.nodes.size(); j++)
			   if (ModelicaGraphGenerator.nodes.get(j).getName().equals(targetName))
					   n = j;
		}
		System.out.println("n: " + n + " and prev: " + prev);
		
		ModelicaNode currentNode = ModelicaGraphGenerator.nodes.get(n);
		ModelicaNode prevNode = ModelicaGraphGenerator.nodes.get(prev);

		if (familiar) {
			long t3 = System.currentTimeMillis();
			
			ModelicaNode instantNode = null;
			for (int i = 0; i < ModelicaGraphGenerator.nodes.size(); i++)
				instantNode = ModelicaGraphGenerator.nodes.get(i);
			if (instantNode.getName().endsWith(targetName))
				n = instantNode.getId();

			long t4 = System.currentTimeMillis();
			elapsedTimeFamiliar = (t4 - t3)/1000.0;
		}

		System.out.println("Does a connection exist between " + prevNode.getName() + " and " + currentNode.getName() + "? " + !connectionsContains(prevNode.getName(), currentNode.getName()));
		
		// Connection exist in opposite direction
		if (connectionsContains(currentNode.getName(), prevNode.getName()) &&
				!prevNode.getName().equals(currentNode.getName())) {
			long t3 = System.currentTimeMillis();
			
			// Bend what is already there
			// TODO: This has to be regenerated somehow as well in order to be visualized

			ModelicaConnection instantConnection = null;
			for (int i = 0; i < ModelicaGraphGenerator.connections.size(); i++) {
				instantConnection = ModelicaGraphGenerator.connections.get(i);
				if (instantConnection.getSource().getName().equals(currentNode.getName()) &&
						instantConnection.getDestination().getName().equals(prevNode.getName())) 
					instantConnection.setBending();
			}

			// Bend the new connection at the same place
			ModelicaConnection connect = new ModelicaConnection(
					Integer.toString(cid), "test", prevNode, currentNode, style, targetName, lineNumbers);
			connect.setBending();
			ModelicaGraphGenerator.connections.add(connect);	
			cid += 1;
			
			long t4 = System.currentTimeMillis();
			elapsedTimeConnections = (t4 - t3)/1000.0;
		}
		// Connection doesn't exist in neither direction
		else if (!connectionsContains(prevNode.getName(), currentNode.getName()) &&
				!prevNode.getName().equals(currentNode.getName())) {
			long t3 = System.currentTimeMillis();
			
			ModelicaConnection connect = new ModelicaConnection(
					Integer.toString(cid), "test", prevNode, currentNode, style, targetName, lineNumbers);
			ModelicaGraphGenerator.connections.add(connect);	
			cid += 1;

			long t4 = System.currentTimeMillis();
			elapsedTimeConnections = (t4 - t3)/1000.0;
		}
	}

	/**
	 * This will iterate over all the stored nodes and check if a name
	 * exist among them.
	 * 	
	 * @param comparingString
	 *            the name that should be found among the stored nodes
	 * @return true if it was found, otherwise false
	 */
	public static boolean nodesContains(String comparingString) {
		for (int i = 0; i < ModelicaGraphGenerator.nodes.size(); i++) {
			ModelicaNode currentNode = ModelicaGraphGenerator.nodes.get(i);
			if (currentNode.getName().equals(comparingString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This will iterate over all the stored connections and check if a connection
	 * exist among them with a specific source and a specific target.
	 * 	
	 * @param comparingSource
	 *            the source of a connection that should be found
	 * @param comparingTarget
	 *            the target of a connection that should be found
	 * @return true if it was found, otherwise false
	 */
	public static boolean connectionsContains(String comparingSource, String comparingDestination) {
		for (int i = 0; i < ModelicaGraphGenerator.connections.size(); i++) {
			ModelicaConnection currentConnection = ModelicaGraphGenerator.connections.get(i);
			if (currentConnection.getSource().getName().equals(comparingSource) &&
					currentConnection.getDestination().getName().equals(comparingDestination)) 
				return true;
		}
		return false;
	}

	/**
	 * Parse a file to find all lines where a certain string exist
	 *
	 * @param file
	 *            the name of this file
	 * @param text
	 *            the text that should be found
	 * @return an array of line numbers where the text was found
	 * @exception ConnectException
	 *                if the Modelica compiler can't be loaded
	 * @exception UnexpectedReplyException
	 *                if the Modelica compiler returns something strange
	 */
	private static ArrayList<Integer> findLineNumber(String file, String text) throws ConnectException, UnexpectedReplyException {
		Scanner fileScanner = null;
		try
		{
			fileScanner = new Scanner(new File(file));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}  
		int lineID = 0;  
		ArrayList<Integer> lineNumbers = new ArrayList<Integer>();  
		// TODO: Is this covering all cases of occurrences?
		Pattern pattern =  Pattern.compile("\\b" + text);
		Matcher matcher = null;  

		while(fileScanner.hasNextLine()){  
			String line = fileScanner.nextLine();  
			lineID++;  
			matcher = pattern.matcher(line);  
			if(matcher.find()){  
				lineNumbers.add(lineID);
			}  	  
		}
		return lineNumbers;
	}

	/**
	 * Finds out what connections needs to be destroyed and in some
	 * cases even the destroy the node pointed at
	 *
	 * @param sourceID
	 *            the ID of the source to the dependency/pointer
	 * @param className
	 *            the name of the class
	 * @return an array of the IDs that were destroyed non-visually
	 */
	public static ArrayList<Integer> destructClasses(int sourceID, String className) {
		ArrayList<Integer> destroyedConnections = new ArrayList<Integer>();
		for(int i = 0; i < ModelicaGraphGenerator.connections.size(); i++) {
			if(ModelicaGraphGenerator.connections.get(i).getSource().getName().equals(className)) {
				System.out.println("[Analyze Operation] Destroying connection from " + ModelicaGraphGenerator.connections.get(i).getSource().getName() + " to " + ModelicaGraphGenerator.connections.get(i).getDestination().getName());
				destroyedConnections.add(i);
			}
		}

		for(int i = destroyedConnections.size()-1; i >= 0; i--) {
			if (ModelicaGraphGenerator.connections.get(destroyedConnections.get(i)).getDestination().isExpandable())
			{ 
				// We can possibly also remove the node pointed at
				ModelicaGraphGenerator.nodes.remove(ModelicaGraphGenerator.connections.get(destroyedConnections.get(i)).getDestination().getId());

				// Update all the old nodes with a id larger than the the destroyed one that they have to be reduced
				for(int j = ModelicaGraphGenerator.connections.get(destroyedConnections.get(i)).getDestination().getId(); j < ModelicaGraphGenerator.nodes.size(); j++)
					ModelicaGraphGenerator.nodes.get(j).setId(ModelicaGraphGenerator.nodes.get(j).getId()-1);
				nid -= 1;
			} 
			int resulting = destroyedConnections.get(i);
			ModelicaGraphGenerator.connections.remove(resulting);
			cid -= 1;
		}
		return destroyedConnections;
	}

	// Restricted access to some of the private variables
	public static IModelicaCompiler getModelicaCompiler() { return currentCompiler; }
	public static void setNid(int n) { nid = n; }
	public static void setCid(int c) { cid = c; }
}
