
package org.modelica.mdt.ui.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.ModelicaSourceFile;

public class CrossAnalyzer {
	static IModelicaCompiler currentCompiler;
	static ModelicaSourceFile currentFile;
	static int nid = 0;
	static int cid = 0;

	public static void initAnalyze(String className, ModelicaSourceFile file) {
		currentFile = file;
		try { 
			currentCompiler = CompilerProxy.getCompiler();
			System.out.println("[Analyze Operation] Found the compiler");
			currentCompiler.getStandardLibrary();

			// Standard: (Useful in thesis report - optimizing development phase) 
			// recursive = true

			// Optional: (Useful in thesis report or as an optional settings - first development phase)
			// recursive = false

			// TODO: If this is a package it should be colored Purple
			MyNode node = new MyNode(Integer.toString(nid), className, SWT.COLOR_GREEN);
			node.expandable = false;
			CrossUtil.nodes.add(node);

			analyzeClasses(0, className, false);

		} catch (Exception e) {
			currentCompiler = null;
		}
	}

	public static void analyzeClasses(int prevID, String className, boolean recursive) throws ConnectException, UnexpectedReplyException, InvocationError {
		System.out.println("[Analyze Operation] Finding classes of " + className + " (cid/nid: " + cid + "/" + nid + ")");
		String classPath;
		try {
			classPath = currentCompiler.getClassLocation(className).getPath();
		} catch (InvocationError e) {
			classPath = null;
			System.out.println("Couldn't find the path of " + className);
			return;
		}
		currentCompiler.loadFile(classPath);

		// Find the underlying classes of a package
		if(currentCompiler.isPackage(className)) {
			createPackage(className);
			List classList = currentCompiler.getClassNames(className);
			for (int j = 0; j < classList.size(); j++) {
				String nam = classList.elementAt(j).toString();
				addToPackage(className, nam);
				createBond(className, recursive, className + "." + nam, prevID, SWT.LINE_DASH, SWT.COLOR_MAGENTA);
			}
		}

		// Find inheritance-dependencies
		int num =  currentCompiler.getInheritanceCount(className);
		for (int i = 0; i < num; i++) {
			ICompilerResult res = currentCompiler.getNthInheritedClass(className, i+1);
			createBond(className, recursive, res.getFirstResult(), prevID, SWT.LINE_SOLID, SWT.COLOR_GREEN);
		}

		// Find component-dependencies
		List componentList = currentCompiler.getComponents(className);
		for (int j = 0; j < componentList.size(); j++) {
			String nam = componentList.elementAt(j).toString();
			createBond(className, recursive, nam, prevID, SWT.LINE_SOLID, SWT.COLOR_GREEN);
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

		analyzeParent(prevID, className, recursive);

		return;
	}

	public static void checkExist(String className, String trimRes, boolean recursive, int prevID) throws ConnectException, UnexpectedReplyException, InvocationError{
		// Find the function-calls from a line of code
		int lastIndex = 0;
		int count = 0;
		while (lastIndex != -1) {
			lastIndex = trimRes.indexOf("(", lastIndex);
			if (lastIndex != -1) {
				count++;
				lastIndex ++;
			}
		}

		while (count > 0){
			
			// TODO: This is not working properly
			
			//System.out.println("Analyzing code " + trimRes);
			String tempRes = trimRes.substring(0, trimRes.indexOf('('));
			trimRes = trimRes.substring(tempRes.length());
			tempRes = tempRes.substring(tempRes.lastIndexOf('"') + 1);
			tempRes.replaceAll("\\s","");
			// TODO: Should we make a list of all things that doesn't exist to avoid looking up same things over?
			//System.out.println("does " + tempRes + " exist? " + currentCompiler.classExist(tempRes));
			if (tempRes.length() != 0 && currentCompiler.classExist(tempRes))
				createBond(className, recursive, tempRes, prevID, SWT.LINE_SOLID, SWT.COLOR_GREEN);
			count -=1;
		}
	}

	public static void analyzeParent(int prevID, String fullName, boolean recursive) throws ConnectException, UnexpectedReplyException, InvocationError{
		System.out.println("[Analyze Operation] Analyzing if "+ fullName + " comes from a package");
		
		if (fullName.contains(".") && currentCompiler.isPackage(fullName.substring(0, fullName.lastIndexOf(".")))){
			String packageName = fullName.substring(0, fullName.lastIndexOf("."));
			createBond(fullName, recursive, packageName, prevID, SWT.LINE_DASH, SWT.COLOR_MAGENTA);
			//TODO: Add to package-array
			addToPackage(packageName, fullName);
		}
	}

	public static void createBond(String className, boolean rec, String elem, int prev, int style, int color) throws ConnectException, UnexpectedReplyException, InvocationError {

		// TODO: Issue with generating it for a class under package?
		// Find which line number inside a file this dependency was generated from
		Path myPath = new Path(CrossAnalyzer.currentCompiler.getClassLocation(className).getPath());
		ArrayList<Integer> lineNumbers = findLineNumber(myPath.toString(), elem);
		//System.out.println("[Analyze Operation] found " + elem + " inside " + className + " at lines: " + lineNumbers);

		// recursive
		if (rec) {
			if (!nodesContains(elem)) {
				nid += 1;
				MyNode node = new MyNode(Integer.toString(nid), elem, color);
				CrossUtil.nodes.add(node);
			}
			analyzeClasses(nid, elem, true);

			// non-recursive
		} else if (!nodesContains(elem)) {
			nid += 1;
			MyNode node = new MyNode(Integer.toString(nid), elem, color);
			CrossUtil.nodes.add(node);
		}

		if (!connectionsContains(CrossUtil.nodes.get(prev).getName(), CrossUtil.nodes.get(nid).getName()) &&
				!CrossUtil.nodes.get(prev).getName().equals(CrossUtil.nodes.get(nid).getName())) {
			MyConnection connect = new MyConnection(Integer.toString(cid), "test", CrossUtil.nodes.get(prev), CrossUtil.nodes.get(nid), style, elem, lineNumbers);

			CrossUtil.connections.add(connect);	
			cid += 1;

		}
		// DEBUG: Do we need this and is it working?
		/*
		else {
			for (int i = 0; i < CrossUtil.connections.size(); i++) {
				if (CrossUtil.connections.get(i).getSource().getName().equals(CrossUtil.nodes.get(prev).getName()) &&
						CrossUtil.connections.get(i).getDestination().getName().equals(CrossUtil.nodes.get(nid).getName())) 

					CrossUtil.connections.get(i).addLines(elem, lineNumbers);
			}
		}
		 */

	}

	public static boolean nodesContains(String comparingString) {
		for (int i = 0; i < CrossUtil.nodes.size(); i++) {
			if (CrossUtil.nodes.get(i).getName().equals(comparingString)) {
				return true;
			}
		}
		return false;
	}

	public static boolean connectionsContains(String comparingSource, String comparingDestination) {
		for (int i = 0; i < CrossUtil.connections.size(); i++) {
			//System.out.println("comparing: " + CrossUtil.connections.get(i).getSource().getName() + " and " + comparingSource);

			if (CrossUtil.connections.get(i).getSource().getName().equals(comparingSource) &&
					CrossUtil.connections.get(i).getDestination().getName().equals(comparingDestination)) 
				return true;
		}
		return false;
	}

	public static void analyzeError() throws ConnectException, UnexpectedReplyException {
		//String errorString = currentCompiler.getErrorString().getFirstResult();

		// TODO: This should be called AFTER and error is recieved

		//if (!errorString.equals("\"\"")) {
		//	System.out.println("[Analyze Operation] Found some error from compilation " + errorString);
		//}
	}

	public static GraphPackage createPackage(String packageName) {
		System.out.println("[Analyze Operation] Creating package " + packageName);
		GraphPackage createdPackage = null;
		if (!containsPackage(packageName)){
			createdPackage = new GraphPackage(packageName);
			CrossUtil.packages.add(new GraphPackage(packageName));
		}
		return createdPackage;
	}

	public static void addToPackage(String packageName, String className) {
		System.out.println("[Analyze Operation] Adding " + className + " to " + packageName);
		createPackage(packageName);

		for (int i = 0; i < CrossUtil.packages.size(); i++)
			if (CrossUtil.packages.get(i).getName().equals(packageName) && !CrossUtil.packages.get(i).containsClass(packageName)) {
				// add class to the correct package
				CrossUtil.packages.get(i).addClass(className);
				return; // there can only be one package per class
			}
	}

	public static boolean containsPackage(String packageName) {
		String currentPackage = null;
		for (int i = 0; i < CrossUtil.packages.size(); i++){
			currentPackage = CrossUtil.packages.get(i).getName();
			if (currentPackage.contains(packageName))
				return true;
		}
		return false;
	}

	private static ArrayList<Integer> findLineNumber(String file, String text) throws ConnectException, UnexpectedReplyException {
		Scanner fileScanner = null;
		try
		{
			fileScanner = new Scanner(new File(file));
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		int lineID = 0;  
		ArrayList<Integer> lineNumbers = new ArrayList<Integer>();  
		Pattern pattern =  Pattern.compile(text);
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
}
