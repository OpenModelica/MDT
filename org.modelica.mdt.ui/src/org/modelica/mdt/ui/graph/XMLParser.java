
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

	public static void readTextFile(String path) {
		FileInputStream fin;		

		try
		{
			// Open an input stream
			fin = new FileInputStream(path);
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
		currentLine = bufReader.readLine();
		currentLine = bufReader.readLine();
		currentLine = bufReader.readLine();

		while(currentLine != null) {
			// Iterates over each row in the xml-file

			System.out.println(currentLine);

			// Iterates over each row in the correct equation index-block

			// variables
			//   operations
			//
			// equations
			//   equation #1
			//      operations
			//   equation #2
			//      operations


			if (currentLine.contains("<equation index=")){
				ModelicaDetailedAnalyzer.setEquationIndex(Integer.parseInt(currentLine.substring(currentLine.indexOf("\"")+1, currentLine.lastIndexOf("\""))));
				while (!currentLine.contains("</equation>")) {
					if (currentLine.contains("<dummy>")) {
						iterateEquations("dummy");
					} else if (currentLine.contains("<assign>") || currentLine.contains("<solved>")){
						iterateEquations("solved");
					} else if (currentLine.contains("<substitution>")) {
						iterateEquations("substitution");
					} else if (currentLine.contains("<simplify>")) {
						iterateEquations("simplify");
					} else if (currentLine.contains("<derive>")) {
						iterateEquations("derive");
					}	
					currentLine = bufReader.readLine();
				}



				
			} else {
				// otherwise loop and read lines until AFTER </equation>
				currentLine = bufReader.readLine();
			}
		}	
	}


	private static void iterateEquations(String tag) throws IOException {
		System.out.println("---Opearation: " + tag + " ----");

		String firstOp, secondOp;

		currentLine = bufReader.readLine();
		System.out.println(currentLine);

		if (tag.equals("solved")) {
			firstOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			currentLine = bufReader.readLine();
			secondOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			ModelicaDetailedAnalyzer.handleSolveOperation(firstOp, secondOp);
		} else if (tag.equals("derive")) {
			firstOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			currentLine = bufReader.readLine();
			secondOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			ModelicaDetailedAnalyzer.handleDeriveOperation(firstOp, secondOp);
		} else if (tag.equals("simplify")) {
			firstOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			currentLine = bufReader.readLine();
			secondOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			//ModelicaDetailedAnalyzer.handlePreSimplifyOperation(firstOp, secondOp);
		} else if (tag.equals("substitution")) {
			firstOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			currentLine = bufReader.readLine();
			secondOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			ModelicaDetailedAnalyzer.handleSubstOperation(firstOp, secondOp);
		}

		currentLine = bufReader.readLine();
		// This will remove all the things we dont need, if there is any
		while(!currentLine.contains("</"+tag+">")) {
			System.out.println(currentLine);
			currentLine = bufReader.readLine();
		}
	}
}
