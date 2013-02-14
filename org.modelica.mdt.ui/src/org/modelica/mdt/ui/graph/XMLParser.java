
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
		String firstOp, secondOp;
		currentLine = bufReader.readLine();
		
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
		} else if (tag.equals("substitution")) {
			firstOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			currentLine = bufReader.readLine();
			secondOp = currentLine.substring(currentLine.indexOf(">")+1, currentLine.lastIndexOf("<"));
			ModelicaDetailedAnalyzer.handleSubstOperation(firstOp, secondOp);
		}

		currentLine = bufReader.readLine();
		// This will remove all the things we dont need, if there is any
		while(!currentLine.contains("</"+tag+">")) {
			currentLine = bufReader.readLine();
		}
	}
}
