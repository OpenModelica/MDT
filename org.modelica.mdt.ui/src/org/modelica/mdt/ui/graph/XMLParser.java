
package org.modelica.mdt.ui.graph;

/**
 * After a file has been marked up with XML-tags, 
 * this class will read it and parse through it 
 * in order to absorb the core information
 * about what operations has been performed to 
 * the equations.
 *
 * @author: Magnus Sjöstrand
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// File-info:
// This class will take the previous XML-file, resulting by XMLMarker.java, and parse to see what operations has been made

// This should only be possible to call if MyNode.simulated = true, meaning that the specific class has been simulated

public class XMLParser
{
	private static String currentLine;
	private static BufferedReader bufReader;

	// TODO: Attach a className or fileName as a input parameter in order to find the correct file and display it for that node
	public static void readTextFile() {
		FileInputStream fin;		

		try
		{
			// Open an input stream
			fin = new FileInputStream ("C:\\OpenModelica1.9.0\\bin\\aM.xml");
			System.out.println("[XML-Parser] Setting input");

			bufReader = new BufferedReader(new InputStreamReader(fin));
			reader();

			// Close our input stream
			System.out.println("[XML-Parser] Close file");
			fin.close();		
		}
		// Catches any error conditions
		catch (IOException e)
		{
			System.err.println ("[XML-Parser] Unable to read from xml-file");
		}
	}

	private static void reader() throws IOException {
		currentLine = bufReader.readLine();
		int blockIndex = 0;

		while(currentLine != null) {
			// Iterates over each row in the xml-file

			while(!currentLine.contains("</block>")) {
				// Iterates over each row in a single block

				// block
				// path
				// partoflst
				// instanceoptlst
				// connectequationoptlst
				// typelst
				// operationnumber

				// each of the following operations does a modification to the graph
				if (currentLine.contains("<operationNumber>")) {
					currentLine = bufReader.readLine(); // remove starting tag
					while (!currentLine.contains("</operationNumber>")) {
						if (currentLine.contains("<dummy>")) {
							iterateEquations("dummy");
						} else if (currentLine.contains("<simple>")) {
							iterateEquations("simple");
						} else if (currentLine.contains("<solve>")) {
							iterateEquations("solve");
						} else if (currentLine.contains("<subst>")) {
							iterateEquations("subst");
						} else if (currentLine.contains("<simplify>")) {
							iterateEquations("simplify");
						} else if (currentLine.contains("<derive>")) {
							iterateEquations("derive");
						}		
						currentLine = bufReader.readLine();
					}
				}
				currentLine = bufReader.readLine();
			}

			currentLine = bufReader.readLine();
			blockIndex++;
		}
	}

	private static void iterateEquations(String tag) throws IOException {
		// removes unnecessary tags and text before sending it forward
		System.out.println("---Opearation: " + tag + " ----");
		
		currentLine = bufReader.readLine();
		if (currentLine.endsWith(":"))
			currentLine = bufReader.readLine(); // removes a line with just the name of the operation
		else if(tag.equals("dummy") || tag.equals("simple"))
			currentLine = currentLine.substring(currentLine.indexOf(":")+2, currentLine.length());

		if (tag.equals("simple")) {
			String firstOp = currentLine;
			ModelicaDetailedAnalyzer.handleSimpleOperation(firstOp);
		} else if (tag.equals("solve")) {
			String firstOp = currentLine;
			currentLine = bufReader.readLine();
			String secondOp = currentLine;
			currentLine = bufReader.readLine();
			String thirdOp = currentLine;
			ModelicaDetailedAnalyzer.handleSolveOperation(firstOp, secondOp, thirdOp);
		} else if (tag.equals("derive")) {
			String firstOp = currentLine;
			currentLine = bufReader.readLine();
			String secondOp = currentLine;
			currentLine = bufReader.readLine();
			String thirdOp = currentLine;
			ModelicaDetailedAnalyzer.handleDeriveOperation(firstOp, secondOp, thirdOp);
		} else if (tag.equals("simplify")) {
			String firstOp = currentLine;
			currentLine = bufReader.readLine();
			String secondOp = currentLine;
			currentLine = bufReader.readLine();
			String thirdOp = currentLine;
			ModelicaDetailedAnalyzer.handlePreSimplifyOperation(firstOp, secondOp, thirdOp);
		} else if (tag.equals("subst")) {
			String firstOp = currentLine;
			currentLine = bufReader.readLine();
			String secondOp = currentLine;
			currentLine = bufReader.readLine();
			String thirdOp = currentLine;
			ModelicaDetailedAnalyzer.handleSubstOperation(firstOp, secondOp, thirdOp);
		}
		
		// This will remove all the things we dont need, if there is any
		while(!currentLine.contains("</"+tag+">")) {
			currentLine = bufReader.readLine();
		}
		System.out.println();
	}
}
