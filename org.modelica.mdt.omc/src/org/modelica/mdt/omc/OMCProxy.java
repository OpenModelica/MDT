/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.omc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.omc.internal.ElementLocation;
import org.modelica.mdt.omc.internal.ParseResults;
import org.modelica.mdt.omc.internal.OMCParser;
import org.modelica.mdt.omc.internal.corba.OmcCommunication;
import org.modelica.mdt.omc.internal.corba.OmcCommunicationHelper;
import org.omg.CORBA.ORB;

/**
 * The OMCProxy is the glue between the OpenModelica Compiler and MDT.
 * It uses the interactive API of OMC to get information about classes
 * and to load classes into OMC.
 * 
 * @author Andreas Remar
 */
public class OMCProxy implements IModelicaCompiler
{
	/* the CORBA object */
	private static OmcCommunication omcc;
	
	/* what Operating System we're running on */
	private static String os;
	
	/* indicates if we've setup the communication with OMC */
	private boolean hasInitialized = false;
	
	/* indicates if the Modelica System Library has been loaded */
	private boolean systemLibraryLoaded = false;

	private String[] standardLibraryPackages = { "Modelica" };
	
	/* should we trace the calls to sendExpression? */
	private static boolean traceOMCCalls = false;
	private static boolean traceOMCStatus = false;
	static
	{
		/* load debug options and set debug flag variables accordingly */
		
		
		String value = Platform.getDebugOption  /*load trace/omcCalls flag */
			("org.modelica.mdt.omc/trace/omcCalls");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			traceOMCCalls = true;
		}
		
		value = Platform.getDebugOption
		("org.modelica.mdt.omc/trace/omcStatus");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			traceOMCStatus = true;
		}
	}
	
	public OMCProxy()
	{
		
	}

	/**
	 * Reads in the OMC CORBA object reference from a file on disk.
	 * @return the object reference as a <code>String</code>
	 */
	private static String readObjectFromFile() throws ConnectException
	{
		File f = new File(getPathToObject());
		String stringifiedObjectReference = null;

		BufferedReader br = null;
		FileReader fr = null;
		try
		{
			fr = new FileReader(f);
		}
		catch(IOException e)
		{
			throw new ConnectException
				("Unable to read OpenModelica Compiler CORBA object from "
						+ f.toString());
		}

		br = new BufferedReader(fr);
			
		try
		{
			stringifiedObjectReference = br.readLine();
		}
		catch(IOException e)
		{
			throw new ConnectException("Unable to read OpenModelica Compiler"
					+ " CORBA object from " + getPathToObject());
		}
		return stringifiedObjectReference;
	}
	
	/**
	 * @return Returns the path to the OMC CORBA object that is stored on disk.
	 */
	private static String getPathToObject()
	{
		String fileName = null;
		if(os.equals("Unix"))
		{
			/* This mirrors the way OMC creates the object file. */
			String username = System.getenv("USER");
			if(username == null)
			{
				username = "nobody";
			}
			fileName = "/tmp/openmodelica." + username + ".objid";
		}
		else if(os.equals("Windows"))
		{
			String temp = System.getenv("TMP");			
			fileName = temp + "\\openmodelica.objid";
		}
		
		logOMCStatus("will look for OMC object reference in '" 
				+ fileName + "'");
		
		return fileName;
	}
	
	/**
	 * Start a new OMC server.
	 */
	private static void startServer() throws ConnectException
	{
		String pathToOmc = null;

		/* 
		 * Path to omc (or omc.exe) can be found in the OPENMODELICAHOME
		 * variable. 
		 */
		String omHome = System.getenv("OPENMODELICAHOME");
		if(omHome == null)
		{
			final String m = "Environment variable OPENMODELICAPATH not set";
			logOMCStatus("Environment variable OPENMODELICAPATH not set,"+
					" don't know how to start OMC");
			throw new ConnectException(m);
		}
		
		if(os.equals("Unix"))
		{
			pathToOmc = omHome + "/omc";
		}
		else if(os.equals("Windows"))
		{
			pathToOmc = omHome + "\\omc.exe";
		}

		/* 
		 * Delete old object reference file. We need to do this because we're
		 * checking if the file exists to determine if the server has started
		 * or not (further down). 
		 */
		File f = new File(getPathToObject());
		if(f.exists())
		{
			logOMCStatus("removing old OMC object reference file");
			f.delete();
		}
		
		String command[] = { pathToOmc, "+d=interactiveCorba" };
		try
		{
			logOMCStatus("running command " + command[0] + " " + command[1]);
			Runtime.getRuntime().exec(command);
			logOMCStatus("command run successfully");
		}
		catch(IOException e)
		{
			/*
			 * If we fail to start the compiler, maybe the executable is in
			 * the Compiler directory (if we've compiled the compiler from
			 * source). Try starting OMC from this secondary location.
			 */
			logOMCStatus("error running command " + e.getMessage()
					+ "\ntrying allternative path to the binary");
			String secondaryPathToOmc = null;
			try
			{
				if(os.equals("Unix"))
				{
					secondaryPathToOmc = omHome + "/Compiler/omc";
				}
				else if(os.equals("Windows"))
				{
					secondaryPathToOmc = omHome + "\\Compiler\\omc.exe";
				}

				command = 
					new String[]{secondaryPathToOmc, "+d=interactiveCorba"};
				logOMCStatus("running command " 
						+ command[0] + " " + command[1]);
				Runtime.getRuntime().exec(command);
				logOMCStatus("command run successfully");
			}
			catch(IOException ex)
			{
				logOMCStatus("error running command, giving up"); 
				throw new ConnectException
					("Unable to start Open Modelica Compiler. "
					 + "Tried starting " + pathToOmc
					 + " and " + secondaryPathToOmc);
			}
		}

		/*
		 * Wait until the object exists on disk, but if it takes longer than
		 * 5 seconds, abort. (Very arbitrary 5 seconds..)
		 */
		int ticks = 0;
		while(!f.exists())
		{
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				// Ignore
			}
			ticks++;
			
			/* If we've waited for 5 seconds, abort the wait for OMC */
			if(ticks > 50)
			{
				logOMCStatus("no OMC object reference file created after " + 
						"approximatly 5 seconds\n" +
						"it seems OMC does not want to come up, giving up");
				throw new ConnectException
					("Unable to start the Open Modelica Compiler. Waited for 5"
							+" seconds, but it didn't respond.");
			}
		}
	}
	
	/**
	 * Initializes an ORB, converts the stringified OMC object to a real
	 * CORBA object, and then narrows that object to an OmcCommunication
	 * object. 
	 */
	private static void setupOmcc(String stringifiedObjectReference)
	{
		/* Can't remember why this is needed. But it is. */
		String args[] = {null};
		
		ORB orb;
		orb = ORB.init(args, null);
		
		/* Convert string to object. */
		org.omg.CORBA.Object obj
			= orb.string_to_object(stringifiedObjectReference);
		
		/* Convert object to OmcCommunication object. */
		omcc = OmcCommunicationHelper.narrow(obj);
	}
	
	/**
	 * @return the name of the operating system. If an unknown os is found,
	 * the default is Unix. 
	 */
	private static String getOs()
	{
		String osName = System.getProperty("os.name");
		if(osName.contains("Linux"))
		{
			return "Unix";
		}
		else if(osName.contains("Windows"))
		{
			return "Windows";
		}
		else
		{
			ErrorManager.logWarning("'" + osName + "' is unsupported OS");
			/* If the OS is not GNU/Linux or Windows, default to Unix */
			return "Unix";
		}
	}

	/**
	 * Initialize the communication with OMC
	 * @throws ConnectException if we're unable to start communicating with
	 * the server
	 */
	private void init() throws ConnectException
	{
		/* 
		 * Get type of operating system, used for finding object
		 * reference and starting OMC if the reference is faulty 
		 */
		os = getOs();
		
		/* See if an OMC server is already running */
		File f = new File(getPathToObject());
		String stringifiedObjectReference = null;
		if(!f.exists())
		{
			/* If a server isn't running, start it */
			logOMCStatus("no OMC object reference found");
			startServer();
		}
		else
		{
			logOMCStatus("OMC object reference present," +
					" assuming OMC is running");
		}
		
		/* Read in the CORBA OMC object from a file on disk */
		stringifiedObjectReference = readObjectFromFile();

		/*
		 * Setup up OMC object reference by initializing ORB and then
		 * converting the string object to a real CORBA object.
		 */
		setupOmcc(stringifiedObjectReference);

		try
		{
			/*
			 * Test the server by trying to send an expression to it. 
			 * This might fail if the object reference found on disk didn't
			 * have a corresponding server running. If a server is missing,
			 * catch an exception and try starting a server.
			 */
			logOMCStatus("trying to send expression to OMC");
			omcc.sendExpression("1+1");
			logOMCStatus("expression send successfully");
		}
		catch(org.omg.CORBA.COMM_FAILURE e)
		{
			/* Start server and set up omcc */
			logOMCStatus("failed sending expression");
			startServer();
			stringifiedObjectReference = readObjectFromFile();
			setupOmcc(stringifiedObjectReference);

			try
			{
				/* Once again try to send an expression to OMC. If it fails this
				 * time it's time to send back an exception to the caller of
				 * this function. */
				logOMCStatus("trying to send expression to OMC");
				omcc.sendExpression("1+1");
				logOMCStatus("expression send successfully");
			}
			catch(org.omg.CORBA.COMM_FAILURE x)
			{
				logOMCStatus("failed sending expression, giving up");
				throw new ConnectException("Unable to start the OpenModelica"
						+" Compiler.");
			}
		}

		hasInitialized = true;
	}
	
	/**
	 * Send expression to OMC. If communication is not initialized, it
	 * is initialized here.
	 * @param exp the expression to send to OMC 
	 * @throws ConnectException if we're unable to start communicating with
	 * the server
	 */
	// TODO add synchronization so that two threads don't fudge up each others
	// communication with OMC
	// old synchronization aka 'private synchronized String sendExpression(String exp)'
	// doesnt work when there is possibility of multiple instances of OMCProxy objects
	private String sendExpression(String exp)
		throws ConnectException
	{
		String retval = null;
		
		if(hasInitialized == false)
		{
			init();
		}
		
		try
		{
			logOMCCall(exp);
			retval = omcc.sendExpression(exp);
			logOMCReply(retval);
		}
		catch(org.omg.CORBA.COMM_FAILURE x)
		{
			/* lost connection to OMC or something */
			throw new ConnectException("Couldn't send expression to the "+
					"OpenModelica Compiler. Tried sending: " + exp);
		}
		
		return retval;
	}
	
	/**
	 * Logs the expression sent to OMC if the
	 * tracing flag (traceOMCCalls) is set
	 * 
	 * @param expression the expression that is about to be sent to OMC
	 */
	private static void logOMCCall(String expression)
	{
		if (!traceOMCCalls)
		{
			return;
		}
		System.out.println(">> " + expression);
	}
	
	/**
	 * loggs the message conserning OMC status if the
	 * tracing flag traceOMCStatus is set
	 * @param message the message to log
	 */
	private static void logOMCStatus(String message)
	{
		if (!traceOMCStatus)
		{
			return;
		}
		System.out.println(message);
	}

	/**
	 * Logs the reply received from OMC if
	 * the tracing flag (traceOMCCalls) is set
	 * 
	 * @param reply the reply recieved from the OMC
	 */
	private static void logOMCReply(String reply)
	{
		if (!traceOMCCalls)
		{
			return;
		}

		StringTokenizer tokenizer = new StringTokenizer(reply, "\n");
		
		while (tokenizer.hasMoreTokens())
		{
			System.out.println("<< " + tokenizer.nextToken());
		}
	}
	
	/**
	 * Get the classes contained in a class (a package is a class..)
	 *
	 * 
	 * @param className full class name where to look for packages
	 * @return an array of subclasses defined (and loaded into OMC)
	 *  inside the class named className, but don't return packages in this
	 *  class. The results is returned as Vector of objects but objects
	 *  are actually String's.
	 *  
	 * @throws ConnectException 
	 * @throws UnexpectedReplyException 
	 * @throws InitializationException
	 */	
	public List getClassNames(String className)
		throws ConnectException, UnexpectedReplyException
	{
		String retval = sendExpression("getClassNames("+className+")");
		
		return ModelicaParser.parseList(retval);
	}

	/**
	 * Gets the restriction type of a class.
	 * 
	 * @param className fully qualified class name
	 * @return the restriction type of the class or Type.CLASS if 
	 *         type can't be determined
	 * @throws ConnectException 
	 */
	public IModelicaClass.Type getRestrictionType(String className)
		throws ConnectException
	{
		String reply = 
			sendExpression("getClassRestriction(" + className + ")");
		
		/* remove " around the reply */
		reply = reply.trim();
		reply = reply.substring(1, reply.length()-1);
		
		return IModelicaClass.Type.parse(reply);
	}
	
	/**
	 * Fetches the error string from OMC. This should be called after an "Error"
	 * is received.
	 * @return
	 * @throws ConnectException
	 */
	private String getErrorString()
		throws ConnectException
	{
		String res = sendExpression("getErrorString()");
		if(res != null)
		{
			res = res.trim();
			return res.substring(1, res.length() - 1);
		}
		else
			return "";
	}
	

	/**
	 * Tries to load file into OMC which causes it to be parsed and the syntax
	 * checked.
	 * @param file the file we want to load
	 * @return either returns the classes (and packages) found in the file or
	 * the error messages from OMC
	 * @throws ConnectException 
	 * @throws UnexpectedReplyException 
	 * @throws InitializationException
	 */
	public ParseResults loadSourceFile(IFile file)
		throws ConnectException, UnexpectedReplyException
	{
		ParseResults res = new ParseResults();

		// We need to call this to empty the error queue in OMC
		sendExpression("getMessagesString()");
		
		String fullName = file.getLocation().toString();
		String retval = 
			sendExpression("loadFileInteractiveQualified(\"" + fullName + "\")");
		
		String errorString = sendExpression("getMessagesString()");
		
		/*
		 * See if there were parse errors
		 */
		if(retval.toLowerCase().contains("error"))
		{			
			res.setCompileErrors
				(OMCParser.parseErrorString(getErrorString()));
			res.setClassNames(new List());
		}
		/*
		 * file loaded and parse successsfully
		 */
		else
		{
			res.setClassNames(ModelicaParser.parseList(retval));
		}

		/*
		 * If there were errors, but the compilation went through,
		 * collect the error messages. (Test if errorString == "") 
		 */
		errorString = errorString.trim();
		if(errorString.equals("\"\"") == false)
		{
			errorString = errorString.substring(1, errorString.length() - 1);
			res.setCompileErrors(OMCParser.parseErrorString(errorString));
		}
		
		return res;
	}

	/**
	 * Gets the location (file, line number and column number) of a Modelica
	 * element. 
	 * @param className the element we want to get location of
	 * @return an IElementLocation containing the file, line number and column
	 * number of the given class 
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 * @throws InvocationError
	 */
	public ElementLocation getClassLocation(String className)
		throws ConnectException, UnexpectedReplyException, InvocationError 
	{
		String retval = sendExpression("getCrefInfo(" + className + ")");
		
		if(retval.contains("Error") || retval.contains("error"))
		{
			throw new 
				InvocationError("fetching file position of " + className,
						"getCrefInfo(" + className + ")");
		}
		
		
		/*
		 * The getCrefInfo reply have the following format:
		 * 
		 * <file path>,<line number>,<column number>
		 * 
		 */

		/* For some reason, the list returned doesn't contain curly braces. */
		retval = "{" + retval + "}"; 

		List tokens = ModelicaParser.parseList(retval);
		int line;

		try
		{
			line = Integer.parseInt(tokens.elementAt(1).toString());
		}
		catch (NumberFormatException e)
		{
			throw new 
			UnexpectedReplyException("can't parse getCrefInfo() reply, "+
					"unexpected format");
		}
		
		return new ElementLocation(tokens.elementAt(0).toString(), line);
	}
	
	/**
	 * Queries the compiler if a particular modelica class/package is a package.
	 * 
	 * @param className fully qualified name of the class/package
	 * @return true if className is a package false otherwise
	 * @throws ConnectException 
	 */
	public boolean isPackage(String className)
		throws ConnectException 
	{
		String retval = sendExpression("isPackage(" + className + ")");
		return retval.contains("true");
	}
	
	/**
	 * @param className
	 * @return
	 * @throws ConnectException
	 * @throws InvocationError 
	 * @throws UnexpectedReplyException 
	 */
	public List getElementsInfo(String className)
		throws ConnectException, InvocationError, UnexpectedReplyException
	{
		String retval = sendExpression("getElementsInfo("+ className +")");
		
		/*
		 * we need a efficient way to check if the result is
		 * humongosly huge list or 'Error' or maybe 'error' 
		 */
		for (int i = 0; i < retval.length(); i++)
		{
			if (retval.charAt(i) == '{')
			{
				/* we found the begining of the list, let's hope for the best */
				return ModelicaParser.parseList(retval);		
			}
			else if (retval.charAt(i) == 'E' || retval.charAt(i) == 'e')
			{
				/*
				 * this is the unreadable way to check if the retval 
				 * equals 'Error' or 'error'
				 */
				if (retval.substring(i+1,i+5).equals("rror"))
				{
					throw new 
						InvocationError("fetching contents of " + className,
								"getElementsInfo("+ className +")");
				}
				else
				{
					/* OMC returned someting wierd, panic mode ! */
					break;
				}
			}
		}
		/* we have no idea what OMC returned */
		throw new UnexpectedReplyException("getElementsInfo("+ className +")" + 
						"replies:'" + retval + "'");
	}

	
	public String getCompilerName()
	{
		return "OpenModelica Compiler";
	}

	/**
	 * Loads in the Modelica System Library and returns names of the top-level
	 * packages.
	 *  
	 * @throws ConnectException if we're unable to start communicating with
	 * the server
	 */	
	public String[] getStandardLibrary() throws ConnectException
	{
		if (!systemLibraryLoaded)
		{
			sendExpression("loadModel(Modelica)");
			systemLibraryLoaded = true;
		}

		return standardLibraryPackages;
	}
}
