
package org.modelica.mdt.ui.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

public class CrossAnalyzer {
	static IModelicaCompiler currentCompiler;
	static int nid = 0;
	static int cid = 0;

	public static void initAnalyze(String fileName, final IPath filePath) {
		String className;
		MyNode coreNode;

		try { 
			currentCompiler = CompilerProxy.getCompiler();
			System.out.println("[Analyze Operation] Found the compiler");
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					try
					{
						currentCompiler.getStandardLibrary();

						System.out.println(filePath.toString());
						currentCompiler.loadFile(filePath.toString());

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

			List classList = currentCompiler.parseFile(filePath.toString());
			int startID = 0;
			nid = -1;
			for (int j = 0; j < classList.size(); j++) {
				className = classList.elementAt(j).toString();

				// Standard: (Useful in thesis report - optimizing development phase) 
				// recursive = true

				// Optional: (Useful in thesis report or as an optional settings - first development phase)
				// recursive = false


				if (!nodesContains(className)) {
					nid += 1;
					System.out.println("[Analyze Operation] Creating node " + className + " with nid:" + nid);
					if(currentCompiler.isPackage(className)) {
						createPackage(className);
						coreNode = new MyNode(nid, className, SWT.COLOR_GRAY);
					} else {
						coreNode = new MyNode(nid, className, SWT.COLOR_GREEN);
					}

					coreNode.expandable = false;
					CrossUtil.nodes.add(coreNode);
				}

				// TODO: Test if this is correct when a dependency is found between class A to class B before class B has been analyzed
				//		 Where class A and class B both comes from the same start-file
				analyzeClasses(nid, className, false);
				//nid += 1;
			}


		} catch (Exception e) {
			currentCompiler = null;
		}
	}

	public static int analyzeClasses(int prevID, String className, boolean recursive) throws ConnectException, UnexpectedReplyException, InvocationError {
		System.out.println("[Analyze Operation] Finding classes of " + className + " (cid/nid: " + cid + "/" + nid + ")");

		// Find the underlying classes of a package
		if(currentCompiler.isPackage(className)) {
			createPackage(className);
			List classList = currentCompiler.getClassNames(className);
			for (int j = 0; j < classList.size(); j++) {
				String nam = classList.elementAt(j).toString();
				addToPackage(className, nam);
				createBond(className, recursive, className + "." + nam, prevID, SWT.LINE_DASH, SWT.COLOR_GRAY);
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
			System.out.println("[Analyze Operation] We found object " + nam + " !");
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

		// TODO: We need to check the annotations as well

		analyzeParent(prevID, className, recursive);

		return prevID;
	}

	public static void checkExist(String className, String trimRes, boolean recursive, int prevID) throws ConnectException, UnexpectedReplyException, InvocationError{
		// TODO: This is only checked against equations, may also exist in the variables

		// Extract the function-calls from a line of code
		int lastIndex = 0;
		int count = 0;
		while (lastIndex != -1) {
			lastIndex = trimRes.indexOf("(", lastIndex);
			if (lastIndex != -1) {
				count++;
				lastIndex ++;
			}
		}

		trimRes = trimRes.replaceAll("\"","");

		while (count > 0){

			String tempRes = trimRes.substring(0, trimRes.indexOf('('));
			trimRes = trimRes.substring(tempRes.length()+1, trimRes.length());

			tempRes = tempRes.replaceAll("\\s","");

			tempRes = tempRes.replaceAll("\\+"," ");
			tempRes = tempRes.replaceAll("\\-"," ");
			tempRes = tempRes.replaceAll("\\*"," ");
			tempRes = tempRes.replaceAll("\\="," ");
			tempRes = tempRes.replaceAll("\\<"," ");
			tempRes = tempRes.replaceAll("\\>"," ");
			tempRes = tempRes.replaceAll("\\:"," ");

			tempRes = tempRes.trim();

			tempRes = tempRes.substring(tempRes.lastIndexOf(" ")+1,tempRes.length());

			// TODO: Should we make a list of all things that doesn't exist to avoid looking up same things over?
			System.out.println("[Analyze Operation] Does " + tempRes + " exist? " + currentCompiler.existClass(tempRes));

			if (tempRes.length() != 0 && currentCompiler.existClass(tempRes))
				createBond(className, recursive, tempRes, prevID, SWT.LINE_DOT, SWT.COLOR_GREEN);
			count -=1;
		}
	}

	public static void analyzeParent(int prevID, String fullName, boolean recursive) throws ConnectException, UnexpectedReplyException, InvocationError{
		System.out.println("[Analyze Operation] Analyzing if "+ fullName + " comes from a package");

		if (fullName.contains(".") && currentCompiler.isPackage(fullName.substring(0, fullName.lastIndexOf(".")))){
			String packageName = fullName.substring(0, fullName.lastIndexOf("."));
			createBond(fullName, recursive, packageName, prevID, SWT.LINE_DASH, SWT.COLOR_GRAY);

			//TODO: Add to package-array
			addToPackage(packageName, fullName);
		}
	}

	public static void createBond(String className, boolean rec, String elem, int prev, int style, int color) throws ConnectException, UnexpectedReplyException, InvocationError {
		Path myPath = new Path(CrossAnalyzer.currentCompiler.getClassLocation(className).getPath());
		ArrayList<Integer> lineNumbers = findLineNumber(myPath.toString(), elem);

		boolean familiar = true;

		// recursive
		if (rec) {
			if (!nodesContains(elem)) {
				nid += 1;
				System.out.println("[Analyze Operation] Creating node " + elem + " with nid:" + nid);
				MyNode node = new MyNode(nid, elem, color);
				CrossUtil.nodes.add(node);
				familiar = false;
			}
			analyzeClasses(nid, elem, true);


			// non-recursive
		} else if (!nodesContains(elem)) {
			nid += 1;
			MyNode node = new MyNode(nid, elem, color);
			System.out.println("[Analyze Operation] Creating node " + elem + " with nid:" + nid);
			CrossUtil.nodes.add(node);
			familiar = false;
		}

		// Fix for handling analyze of multiple trees of classes
		int n = nid;

		if (familiar) {
			for (int i = 0; i < CrossUtil.nodes.size(); i++)
				if (CrossUtil.nodes.get(i).getName().endsWith(elem))
					n = CrossUtil.nodes.get(i).getId();
		}

		// < -  Change these so that we use n instead of nid - >
		System.out.println("[Analyze Operation] Checking if connection exist between " + CrossUtil.nodes.get(prev).getName() + "(" + prev + ") and " +  CrossUtil.nodes.get(n).getName() + "(" + n + ")");
		if (connectionsContains(CrossUtil.nodes.get(n).getName(), CrossUtil.nodes.get(prev).getName()) &&
				!CrossUtil.nodes.get(prev).getName().equals(CrossUtil.nodes.get(n).getName())) {
			// Connection exist in opposite direction
			System.out.println("[Analyze Operation] Creating connection between " +  CrossUtil.nodes.get(prev).getName() + "(" + prev + ") and " + CrossUtil.nodes.get(n).getName() + "(" + n + ")");

			// Bend what is already there
			// TODO: This has to be regenerated somehow as well in order to be visualized
			for (int i = 0; i < CrossUtil.connections.size(); i++) {
				if (CrossUtil.connections.get(i).getSource().getName().equals(CrossUtil.nodes.get(n).getName()) &&
						CrossUtil.connections.get(i).getDestination().getName().equals(CrossUtil.nodes.get(prev).getName())) 
					CrossUtil.connections.get(i).setBending();
			}

			// Bend the new connection at the same place
			MyConnection connect = new MyConnection(Integer.toString(cid), "test", CrossUtil.nodes.get(prev), CrossUtil.nodes.get(n), style, elem, lineNumbers);
			connect.setBending();
			CrossUtil.connections.add(connect);	
			cid += 1;
		}
		else if (!connectionsContains(CrossUtil.nodes.get(prev).getName(), CrossUtil.nodes.get(n).getName()) &&
				!CrossUtil.nodes.get(prev).getName().equals(CrossUtil.nodes.get(n).getName())) {
			// Connection doesn't exist in neither direction
			System.out.println("[Analyze Operation] Creating connection between " +  CrossUtil.nodes.get(prev).getName() + "(" + prev + ") and " + CrossUtil.nodes.get(n).getName() + "(" + n + ")");

			MyConnection connect = new MyConnection(Integer.toString(cid), "test", CrossUtil.nodes.get(prev), CrossUtil.nodes.get(n), style, elem, lineNumbers);
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
		System.out.println("[Analyze Operation] Adding " + className + " to package " + packageName);
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
		// Finds all line numbers and stores them in a array that is returned, given a file name and a text

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
