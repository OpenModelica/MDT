
package org.modelica.mdt.ui.graph;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelicaDetailedAnalyzer
{
	private static String resultingEquation = "";
	private static String resultingText = "";
	private static ArrayList<String> simplifyArray = new ArrayList<String>();;

	public static void setStartingEquation(String firstEq) {
		// Removes the ending ;

		firstEq = firstEq.replaceAll("(?<=^|[ ])(\\d+)(?=$|[ ])", "$1.0");
		// TODO: Has problems with ( and ) in the equation
		resultingEquation = firstEq.substring(0, firstEq.length());
		resultingText = firstEq.substring(0, firstEq.length()) + "\n";
		simplifyArray.clear();
	}

	public static String getFullText() {
		return resultingText;
	}

	public static void handleSimpleOperation(String resultEq) {
		if (!resultingEquation.contains(resultEq.substring(0, resultEq.indexOf(" "))))
			return;
		resultingText = resultingText + "   Simple Equation\n";

		// Add the operation in its simplest form
		resultingText = resultingText + "       " + resultEq + "\n";

		// TODO: This doesn't change the resulting equation?

		// Then add the resulting equation of the operation
		resultingText = resultingText + resultingEquation + "\n";
	}

	public static void handleSolveOperation(String firstOp, String secondOp, String thirdOp) {
		if (!resultingEquation.contains(thirdOp.substring(0, thirdOp.indexOf(" "))))
			return;
		resultingText = resultingText + "   Solve\n";
		// Add the operations in its simplest form
		addThreeOperationStructure(resultingText, firstOp, secondOp, thirdOp);

		// Modify the resulting equation
		String toReplace = thirdOp.substring(0, thirdOp.indexOf(" "));
		String toReplaceWith = thirdOp.substring(firstOp.indexOf("=")+2, thirdOp.length());
		resultingEquation = resultingEquation.replace(toReplace, toReplaceWith);

		// Then add the resulting equation of the operation
		resultingText = resultingText + resultingEquation + "\n";
	}

	public static void handleDeriveOperation(String firstOp, String secondOp, String thirdOp) {
		resultingText = resultingText + "   Derive\n";
		// Add the operations in its simplest form
		addThreeOperationStructure(resultingText, firstOp, secondOp, thirdOp);

		// TODO: This doesn't change the resulting equation?

		// Then add the resulting equation of the operation
		resultingText = resultingText + resultingEquation + "\n";
	}

	// A Simplification-operation may have been performed before this
	public static void handleSubstOperation(String firstOp, String secondOp, String thirdOp) {
		System.out.println(firstOp + "    " + resultingEquation);
		
		if (!resultingEquation.contains(firstOp))
			return;

		resultingText = resultingText + "   Substitution\n";
		// Add the operations in its simplest form
		addThreeOperationStructure(resultingText, firstOp, secondOp, thirdOp);

		String beforeMod = resultingEquation;
		resultingEquation.replace(firstOp, thirdOp);
		// TODO: This is just a case-based solution for parenthesis
		if (resultingEquation.equals(beforeMod))
			resultingEquation.replace("("+firstOp+")", thirdOp);

		// Then add the resulting equation of the operation
		resultingText = resultingText + resultingEquation + "\n";

		// Check if there is a simplicification for this equation
		for (int i = 0; i < simplifyArray.size(); i++) {
			System.out.println("simplifyArray.get(i)");
			if (resultingEquation.contains("(" + simplifyArray.get(i) + ")")) {
				System.out.println("Now we want to perform a simplicification");
				handleProSimplifyOperation(simplifyArray.get(i), simplifyArray.get(i+1));
				simplifyArray.remove(i);
				simplifyArray.remove(i+1);
			}
		}
	}

	// A Simplification-operation will always take place after a substitution (?)
	public static void handlePreSimplifyOperation(String firstOp, String secondOp, String thirdOp) {
		// TODO: Can be replaced with a x3-matrix
		System.out.println("Add " + firstOp + " and " + thirdOp);
		simplifyArray.add(firstOp);
		simplifyArray.add(thirdOp);

		// Check if there is a simplicification for this equation
		for (int i = 0; i < simplifyArray.size(); i++) {
			if (resultingEquation.contains("(" + simplifyArray.get(i) + ")")) {
				System.out.println("Now we want to perform a simplicification");
				handleProSimplifyOperation(simplifyArray.get(i), simplifyArray.get(i+1));
				simplifyArray.remove(i);
				simplifyArray.remove(i+1);
			}
		}
	}

	private static void handleProSimplifyOperation(String firstOp, String thirdOp) {
		resultingText = resultingText + "   Simplifying\n";
		// Add the operations in its simplest form
		addThreeOperationStructure(resultingText, firstOp, "==>\n==>", thirdOp);

		String beforeMod = resultingEquation;
		resultingEquation.replace(firstOp, thirdOp);
		// TODO: This is just a case-based solution for parenthesis
		if (resultingEquation.equals(beforeMod))
			resultingEquation.replace("("+firstOp+")", thirdOp);


		// Then add the resulting equation of the operation
		resultingText = resultingText + resultingEquation + "\n";
	}

	public static void handleDummyOperation(String firstEq, String resultEq) {
		// TODO: Should be fixed in the future
		System.out.println("Unhandled Dummy-case");
	}

	private static void addThreeOperationStructure(String text, String a, String b, String c) {
		resultingText = resultingText + "       " + a + "\n";
		resultingText = resultingText + "       " + b + "\n";
		resultingText = resultingText + "       " + c + "\n";
	}
}
