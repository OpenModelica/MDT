
package org.modelica.mdt.ui.graph;

import java.util.ArrayList;

public class ModelicaDetailedAnalyzer
{
	//private static String resultingEquation = "";
	private static String resultingText = "";
	private static ArrayList<String> simplifyArray = new ArrayList<String>();;

	public static void setStartingEquation(String firstEq) {
		// Removes the ending ;

		firstEq = firstEq.replaceAll("(?<=^|[ ])(\\d+)(?=$|[ ])", "$1.0");
		// TODO: Has problems with ( and ) in the equation
		//resultingEquation = firstEq.substring(0, firstEq.length());
		resultingText = firstEq.substring(0, firstEq.length()) + "\n";
		simplifyArray.clear();
	}
	
	public static void setEquationIndex(int index) {
		resultingText = resultingText + "\n\n--Equation "+ index + "--\n\n";
	}

	public static String getFullText() {
		return resultingText;
	}

	public static void handleSimpleOperation(String firstOp) {
		resultingText = resultingText + "   Simple Equation\n";

		// Add operations to the text
		resultingText = resultingText + "       " + firstOp + "\n";
		
		// Then add the resulting equation of the operation
		//resultingText = resultingText + resultingEquation + "\n";
	}

	public static void handleSolveOperation(String firstOp, String secondOp) {
		resultingText = resultingText + "   Solve\n";

		// Add operations to the text
		resultingText = resultingText + "       " + firstOp + "\n";
		resultingText = resultingText + "       " + secondOp + "\n";		
		
		// Then add the resulting equation of the operation
		//resultingText = resultingText + resultingEquation + "\n";
	}

	public static void handleDeriveOperation(String firstOp, String secondOp) {
		resultingText = resultingText + "   Derive\n";

		// Add operations to the text
		resultingText = resultingText + "       " + firstOp + "\n";
		resultingText = resultingText + "       " + secondOp + "\n";
		
		// Then add the resulting equation of the operation
		//resultingText = resultingText + resultingEquation + "\n";
	}

	// A Simplification-operation may have been performed before this
	public static void handleSubstOperation(String firstOp, String secondOp) {
		resultingText = resultingText + "   Subst\n";
		
		// Add operations to the text
		resultingText = resultingText + "       " + firstOp + "\n";
		resultingText = resultingText + "       " + secondOp + "\n";
		
		// Then add the resulting equation of the operation
		//resultingText = resultingText + resultingEquation + "\n";
	}

	public static void handleDummyOperation(String firstEq, String resultEq) {
		// TODO: Should be fixed in the future
		System.out.println("Unhandled Dummy-case");
	}
}
