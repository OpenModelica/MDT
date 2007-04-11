
package org.modelica.mdt.ui.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.compiler.CompilerException;

public class OpenModelicaCommandHandler extends Thread
{
	private IOConsoleInputStream  input  = null;
	private IOConsoleOutputStream output = null;
	private BufferedReader        reader = null;
	private BufferedWriter        writer = null;
	
	public OpenModelicaCommandHandler(IOConsoleInputStream input, IOConsoleOutputStream output)
	{
		super();
		this.input  = input;
		this.output = output;
		reader = new BufferedReader(new InputStreamReader(this.input));
		writer = new BufferedWriter(new OutputStreamWriter(this.output));		
	}
	
	public void run()
	{
		try
		{
			while (true)
			{
				String line = reader.readLine();
				String result = null;
				try
				{
					if (line != null && line.length() > 0)
					{
						ICompilerResult res = CompilerProxy.sendExpression(line, false);
						result = res.getFirstResult().trim();
						writer.write(result + "\n");
					}
				}
				catch(CompilerException e)
				{
					// ignore
					writer.write("reply omc> error!\n");
					//writer.write(e.getMessage());
					e.printStackTrace(new PrintStream(output));					
				}
				writer.write("omc> ");
				writer.flush();
			}
		}
		catch(IOException e)
		{
			//e.printStackTrace();
			// if the stream is closed then interrupt the thread.
			this.interrupt();
		}
	}
	
}
