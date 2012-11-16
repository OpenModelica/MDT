
package org.modelica.mdt.ui.graph;

/**
 * After a file has been simulated using
 * a result-flag to print out all optimizations
 * performed, this class will start marking up
 * each line of code with XML-tags.
 *
 * @author: Magnus Sjöstrand
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// File-info:
// This class is marking up a progress-file of what optimization operations has been made

// TODO: This should be called along with simulate from MDT

public class XMLMarker
{
	private static File file;

	// TODO: Attach a className as a input parameter in order to find the correct file and display it for that node
	public static void readTextFile() {
		// Stream to read file
		FileInputStream fin;		

		try
		{
			// Open an input stream
			fin = new FileInputStream ("C:\\OpenModelica1.9.0\\bin\\a.txt");
			System.out.println("[XML-Marker] Setting input");

			BufferedReader bufReader = new BufferedReader(new InputStreamReader(fin));

			file = new File("C:\\OpenModelica1.9.0\\bin\\aM.xml");
			System.out.println("[XML-Marker] Creating file");

			marker(bufReader);

			// Close our input stream
			System.out.println("[XML-Marker] Close file");
			fin.close();		
		}
		// Catches any error conditions
		catch (IOException e)
		{
			System.err.println ("[XML-Marker] Unable to read from txt-file");
		}
	}

	private static void marker(BufferedReader bf) throws IOException {
		// Read a line of text
		System.out.println("[XML-Marker] Read a line of text");
		String currentLine = bf.readLine(); // Ignores SimCode-line at the top
		currentLine = bf.readLine();
		FileOutputStream f = openNewTextFile();

		System.out.println("[XML-Marker] Write text...");
		writeTextFile(f, "<?xml version=\"1.0\"?>");
		while (!currentLine.contains("SimulationResult") && currentLine != null){
			// Iterates over the blocks of operations
			writeTextFile(f, "<block>"+currentLine);

			// Regular structure at the start of a block
			currentLine = bf.readLine();
			writeTextFile(f, "<path>"+currentLine.substring(2)+"</path>");
			currentLine = bf.readLine();
			writeTextFile(f, "<partOfLst>"+currentLine.substring(2)+"</partOfLst>");
			currentLine = bf.readLine();
			writeTextFile(f, "<instanceOptLst>"+currentLine.substring(2)+"</instanceOptLst>");
			currentLine = bf.readLine();
			writeTextFile(f, "<connectEquationOptLst>"+currentLine.substring(2)+"</connectEquationOptLst>");
			currentLine = bf.readLine();
			writeTextFile(f, "<typeLst>"+currentLine.substring(2)+"</typeLst>");
			currentLine = bf.readLine();
			writeTextFile(f, "<operationNumber>"+currentLine.substring(2));
			
			// Finds out number of operations to iterate over
			int opNum = 0;
			Pattern p = Pattern.compile("-?\\d+");
			Matcher m = p.matcher(currentLine);
			while (m.find()) {
				opNum = Integer.parseInt(m.group());
			}

			currentLine = bf.readLine();
			
			// Inside the operations of a operation-block		
			for(int j = 0; j < opNum; j++) {
				String savedEndTag = null;

				if (currentLine.contains("solve")) {
					writeTextFile(f, "<solve>");
					savedEndTag = "</solve>";
				}
				else if (currentLine.contains("simple equation:")) {
					writeTextFile(f, "<simple>");
					savedEndTag = "</simple>";	
				}
				else if (currentLine.contains("subst:")) {
					writeTextFile(f, "<subst>");
					savedEndTag = "</subst>";	
				}
				else if (currentLine.contains("simplify:")) {
					writeTextFile(f, "<simplify>");
					savedEndTag = "</simplify>";
				}
				else if (currentLine.contains("derive:")) {
					writeTextFile(f, "<derive>");
					savedEndTag = "</derive>";
				}
				else if (currentLine.contains("dummy derivative:")) {
					writeTextFile(f, "<dummy>");
					savedEndTag = "</dummy>";
				}
				else if (currentLine.contains("added assertions:")) {
					// Skip assertion-operation because it gives no important information
					currentLine = bf.readLine();
					currentLine = bf.readLine();
					j--; // stay at current index but loop with a new read line
					continue;
				}
				
				writeTextFile(f, currentLine.substring(4));
				currentLine = bf.readLine();
				// Handles detailed information of the operation
				while (currentLine.contains("      ")) {
					writeTextFile(f, currentLine.substring(6));
					currentLine = bf.readLine();
				}
				writeTextFile(f, savedEndTag);
			}
			
			if (currentLine.contains("added assertions:"))
				currentLine = bf.readLine(); // removes potential ending added assertion
			
			writeTextFile(f, "</operationNumber>");
			writeTextFile(f, "</block>");

			if (currentLine.isEmpty())
				currentLine = bf.readLine(); // removes potential blank row
		}
		closeNewTextFile(f);
	}

	private static FileOutputStream openNewTextFile() {
		// Stream to write file
		FileOutputStream fout = null;
		try
		{
			// Open an output stream
			fout = new FileOutputStream (file);
			System.out.println("[XML-Marker] Sending to file");
		}
		// Catches any error conditions
		catch (IOException e)
		{
			System.err.println ("Unable to open file");
			System.exit(-1);
		}
		return fout;
	}

	private static void closeNewTextFile(FileOutputStream fout) throws IOException {
		// Close our output stream
		fout.close();
	}

	private static void writeTextFile(FileOutputStream fout, String message){
		// Print a line of text inside the file
		new PrintStream(fout).println(message);
	}
}
