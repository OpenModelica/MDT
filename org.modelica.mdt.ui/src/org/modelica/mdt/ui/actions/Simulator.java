
package org.modelica.mdt.ui.actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.modelica.mdt.core.Element;
import org.modelica.mdt.core.ListElement;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.CompilerProxy;

public class Simulator {

	private Simulator() {
		try {
			compiler = CompilerProxy.getCompiler();
			omcCompilerPath = compiler.getOmcBinaryPaths()[0].getAbsolutePath();
		}
		catch (CompilerInstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Simulator getInstance() {
		if (inst == null)  {
			inst = new Simulator();
		}

		return inst;
	}

	public void Run(String fullPath) {
		try {
			System.out.println(fullPath);
			File testFile = File.createTempFile("tester", ".mos");
			if (testFile != null) {
				System.out.println(testFile.getAbsolutePath() + ", can write? " + testFile.canWrite());
				FileWriter fw = new FileWriter(testFile);
				// cd("C:/Users/magsj467/AppData/Local/Temp/OpenModelica");
				String topLevelModel = getFirstRootClass(fullPath);
				String testData =
					"loadModel(Modelica);\n" +
					"getErrorString();\n" +
					"loadFile(\"" + fullPath + "\");\n" +
					"getErrorString();\n" +
					"simulate(" + topLevelModel + ");\n" +
					"getErrorString();\n";
				fw.write(testData);
				fw.flush();
				fw.close();
	            List<String> params = new ArrayList<String>();
	            params.add(omcCompilerPath);
	            params.add("+simCodeTarget=Dump");
	            params.add(testFile.getAbsolutePath());
	            ProcessBuilder pb = new ProcessBuilder(params);
	            Process process = pb.start();
	            InputStream is = process.getInputStream();
	            int ch = 0;
	            String s = "";
	            while ((ch = is.read()) != -1) {
	            	s += (char)ch;
	            }
	            System.out.println(s);
			}
			else {
				System.err.println("Error creating temporary file!");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ConnectException e) {
			e.printStackTrace();
		}
		catch (UnexpectedReplyException e) {
			e.printStackTrace();
		}
	}

	private String getFirstRootClass(String fullPath) throws ConnectException, UnexpectedReplyException {
		org.modelica.mdt.core.List fileRootClasses = compiler.parseFile(fullPath);

		int numFileRootClasses = fileRootClasses.size();

		for (int i = 0; i < numFileRootClasses; i++) {
			ListElement listElement = fileRootClasses.elementAt(i);
			if (listElement instanceof Element) {
				Element element = (Element)listElement;
				String rootClass = element.toString();
				return rootClass;
			}
			else if (listElement instanceof List) {
				System.err.println("Weird, nested list found!");
			}
		}

		return null;
	}

	private static Simulator inst;

	private IModelicaCompiler compiler;
	private String omcCompilerPath;
}
