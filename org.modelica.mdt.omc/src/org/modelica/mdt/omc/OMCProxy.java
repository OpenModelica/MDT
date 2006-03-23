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
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IllegalRestrictionException;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ListElement;
import org.modelica.mdt.core.ModelicaParserException;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementInfo;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.core.preferences.PreferenceManager;
import org.modelica.mdt.internal.core.DefinitionLocation;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.omc.internal.ClassInfo;
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
	
	
	enum osType { WINDOWS, UNIX };
	
	/* what Operating System we're running on */
	private static osType os;
	
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

		/* This mirrors the way OMC creates the object file. */		
		switch (os)
		{
		case UNIX:
			String username = System.getenv("USER");
			if(username == null)
			{
				username = "nobody";
			}
			fileName = "/tmp/openmodelica." + username + ".objid";
			break;
		case WINDOWS:
			String temp = System.getenv("TMP");			
			fileName = temp + "\\openmodelica.objid";
			break;
		default:
			ErrorManager.logBug("org.modelica.mdt.omc",
					"os variable set to unexpected os-type");
		}
		
		logOMCStatus("Will look for OMC object reference in '" 
				+ fileName + "'.");
		
		return fileName;
	}
	
	/**
	 * With the help of voodoo magic determines the path to the
	 * omc binary that user (probably) wants to use and the working
	 * direcoty of where that binary (most likely) should be started in
	 * 
	 * This will returns for example 'c:\openmodelica132\omc.exe'
	 * or '/usr/local/share/openmodelica/omc' depending on
	 * such factors as: OS type, environment variables settings,
	 * plugin user preferences, where the first matching
	 * binary found and the weather outside. 
	 * 
	 * @return full path to the omc binary  
	 * @throws ConnectException if the path could not be determined
	 */
	private static File[] getOmcBinaryPaths() throws ConnectException
	{
		String binaryName = "omc";
		
		if (os == osType.WINDOWS)
		{
			binaryName += ".exe";
		}
		
		File omcBinary = null;
		File omcWorkingDirectory = null;
		if (PreferenceManager.getUseStandardOmcPath())
		{
			/*
			 * user specified that standard path to omc should be used,
			 * try to determine the omc path via the OPENMODELICAHOME and
			 * by checking in it's varius subdirectory for the omc binary file
			 */
			logOMCStatus("Using OPENMODELICAHOME environment variable to find omc-binary");
			
			/* 
			 * Standard path to omc (or omc.exe) binary is encoded in OPENMODELICAHOME
			 * variable. 
			 */
			String openModelicaHome = System.getenv("OPENMODELICAHOME");
			if(openModelicaHome == null)
			{
				final String m = "Environment variable OPENMODELICAHOME not set";
				logOMCStatus("Environment variable OPENMODELICAHOME not set,"+
						" don't know how to start OMC from standard path.");
				throw new ConnectException(m);
			}
			
			omcWorkingDirectory = new File(openModelicaHome);
			
			/* the subdirectories where omc binary may be located, hurray for standards! */
			String[] subdirs = { "", "bin", "Compiler" };
			
			for (String subdir : subdirs)
			{
			
				String path = omcWorkingDirectory.getAbsolutePath() + File.separator;
				path += subdir.equals("") ? binaryName :  subdir + File.separator + binaryName;

				File file = new File(path); 

				if (file.exists())
				{
					omcBinary = file;
					logOMCStatus("Using omc-binary at '" + omcBinary.getAbsolutePath() + "'");
					break;
				}
				else
				{
					logOMCStatus("No omc binary at: [" + path + "]");
				}
			}
			
			if (omcBinary == null)
			{
				logOMCStatus("Could not fine omc-binary on the OPENMODELICAHOME path");
				throw new ConnectException("Unable to start the OpenModelica Compiler, binary not found");
			}
		}
		else
		{
			omcBinary = new File(PreferenceManager.getCustomOmcPath());
			
			logOMCStatus("Using userspecified omc-binary at '" +
					omcBinary.getAbsolutePath() + "'");
			
			if (!omcBinary.exists())
			{
				logOMCStatus("file '" + omcBinary.getAbsolutePath() + "' does not exist");
				throw new ConnectException("Specified omc-binary '" + omcBinary.getAbsolutePath() +
				 	"' does not exist");
			}
			
			/*
			 * take an educated guess at where the user wants the binary to be
			 * started. The guessing heuristics are as follows:
			 * 
			 * If binary is inside the 'bin' or 'compiler' directory, use the
			 * above directory as working directory.
			 * 
			 * Otherwise use the directory where binary is located as working 
			 * directory.
			 * 
			 * e.g. binary path /foo/bar/bin/omc      => working directory /foo/bar
			 *      binary path /foo/bar/compiler/omc => working directory /foo/bar
			 *      binary path /foo/bar/omc          => working directory /foo/bar
			 */
			File parent = omcBinary.getParentFile();
			
			if (parent.getName().equalsIgnoreCase("bin") || 
				parent.getName().equalsIgnoreCase("compiler"))
			{
				omcWorkingDirectory = parent.getParentFile();
			}
			else
			{
				omcWorkingDirectory = parent;
			}
			
		}

		return new File[] {omcBinary, omcWorkingDirectory};
	}
	
	/**
	 * Start a new OMC server.
	 */
	private static void startServer() throws ConnectException
	{
		File tmp[] = getOmcBinaryPaths();

		File omcBinary = tmp[0];
		File workingDirectory = tmp[1];

		
		/* 
		 * Delete old object reference file. We need to do this because we're
		 * checking if the file exists to determine if the server has started
		 * or not (further down). 
		 */
		File f = new File(getPathToObject());
		if(f.exists())
		{
			logOMCStatus("Removing old OMC object reference file.");
			f.delete();
		}
		
		String command[] = { omcBinary.getAbsolutePath(), "+d=interactiveCorba" };
		try
		{
			logOMCStatus("Running command " + command[0] + " " + command[1]);
			logOMCStatus("Setting working directory to " + workingDirectory.getAbsolutePath());
			Runtime.getRuntime().exec(command, null, workingDirectory);
			logOMCStatus("Command run successfully.");
		}
		catch(IOException e)
		{
			logOMCStatus("Error running command " + e.getMessage());
			logOMCStatus("Unable to start OMC, giving up."); 
			throw new ConnectException
				("Unable to start the OpenModelica Compiler. ");
		}			

		logOMCStatus("Waiting for OMC CORBA object reference to appear on disk.");
		
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
				/* ignore */
			}
			ticks++;
			
			/* If we've waited for around 5 seconds, abort the wait for OMC */
			if(ticks > 50)
			{
				logOMCStatus("No OMC object reference file created after " + 
						"approximately 5 seconds.");
				logOMCStatus("It seems OMC does not want to come up, giving " +
						"up.");
				throw new ConnectException
					("Unable to start the Open Modelica Compiler. Waited for 5"
							+" seconds, but it didn't respond.");
			}
		}
		logOMCStatus("OMC object reference found.");
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
	private static osType getOs()
	{
		String osName = System.getProperty("os.name");
		if(osName.contains("Linux"))
		{
			return osType.UNIX;
		}
		else if(osName.contains("Windows"))
		{
			return osType.WINDOWS;
		}
		else
		{
			ErrorManager.logWarning("'" + osName + "' not officialy supported OS");
			/* If the OS is not GNU/Linux or Windows, default to Unix */
			return osType.UNIX;
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
			logOMCStatus("No OMC object reference found, starting server.");
			startServer();
		}
		else
		{
			logOMCStatus("Old OMC CORBA object reference present," +
					" assuming OMC is running.");
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
			logOMCStatus("Trying to send expression to OMC.");
			omcc.sendExpression("1+1");
			logOMCStatus("Expression sent successfully.");
		}
		catch(org.omg.CORBA.COMM_FAILURE e)
		{
			/* Start server and set up omcc */
			logOMCStatus("Failed sending expression, will try to start OMC.");
			startServer();
			stringifiedObjectReference = readObjectFromFile();
			setupOmcc(stringifiedObjectReference);

			try
			{
				/* Once again try to send an expression to OMC. If it fails this
				 * time it's time to send back an exception to the caller of
				 * this function. */
				logOMCStatus("Trying to send expression to OMC.");
				omcc.sendExpression("1+1");
				logOMCStatus("Expression sent successfully.");
			}
			catch(org.omg.CORBA.COMM_FAILURE x)
			{
				logOMCStatus("Failed sending expression, giving up.");
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
			logOMCCallError("Error while sending expression " + exp + " ["+x+"]");
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
	 * outputs the message about a call error that occured
	 * when communicating with omc
	 * @param message the message to log
	 */
	private static void logOMCCallError(String message)
	{
		if(!traceOMCCalls)
		{
			return;
		}
		System.out.println(message);
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
		System.out.println("OMCSTATUS: " + message);
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
	 * @return a <code>List</code> of subclasses defined (and loaded into OMC)
	 * inside the class named className.
	 * 
	 * @throws ConnectException 
	 * @throws UnexpectedReplyException 
	 * @throws InitializationException
	 */	
	public List getClassNames(String className)
		throws ConnectException, UnexpectedReplyException
	{
		String retval = sendExpression("getClassNames("+className+")");
		
		/* fetch error string but ignore it */
		getErrorString();
		
		List list = null;
		try
		{
			list = ModelicaParser.parseList(retval);
		}
		catch(ModelicaParserException e)
		{
			throw new UnexpectedReplyException("Unable to parse list: " 
					+ e.getMessage());
		}
		
		return list;
	}

	/**
	 * Gets the type of restriction of a class.
	 * 
	 * @param className fully qualified class name
	 * @return the type of restriction of the class or Type.CLASS if 
	 *         type can't be determined
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 */
	public IModelicaClass.Restriction getRestriction(String className)
		throws ConnectException, UnexpectedReplyException
	{
		String reply = 
			sendExpression("getClassRestriction(" + className + ")");

		/* remove " around the reply */
		reply = reply.trim();
		
		if(reply.equals(""))
		{
			throw new UnexpectedReplyException("getClassRestriction("+className
					+") returned an empty result");
		}
		
		reply = reply.substring(1, reply.length()-1);
		
		/* fetch error string but ignore it */
		getErrorString();
		
		IModelicaClass.Restriction type = null;
		try
		{
			type = IModelicaClass.Restriction.parse(reply);
		}
		catch(IllegalRestrictionException e)
		{
			throw new UnexpectedReplyException("Illegal type: "
					+ e.getMessage());
		}
		
		return type;
	}
	
	/**
	 * Fetches the error string from OMC. This should be called after an "Error"
	 * is received. (Or whenever the queue of errors should be emptied.)
	 * @return the <code>String</code> of errors
	 * @throws ConnectException
	 */
	private String getErrorString()
		throws ConnectException
	{
		String res = sendExpression("getErrorString()");
		
		/* Make sure the error string isn't empty */
		if(res != null && res.length() > 2)
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
	 * @return a <code>ParseResult</code> containing the classes found in the
	 * file and the error messages from OMC 
	 * @throws ConnectException 
	 * @throws UnexpectedReplyException 
	 * @throws InitializationException
	 */
	public ParseResults loadSourceFile(IFile file)
		throws ConnectException, UnexpectedReplyException
	{
		ParseResults res = new ParseResults();

		String fullName = file.getLocation().toString();
		String retval = 
			sendExpression("loadFileInteractiveQualified(\"" + fullName + "\")");
		
		/* Always keep your stuff nice and tidy! */
		retval = retval.trim();
		
		String errorString = getErrorString();

		/*
		 * See if there were parse errors, an empty list {} also denotes error
		 */
		if(retval.toLowerCase().contains("error") || retval.equals("{}"))
		{			
			res.setClassNames(new List());
			if(errorString.equals("") == false)
			{
				res.setCompileErrors(OMCParser.parseErrorString(errorString));
			}
		}
		/*
		 * File loaded and parsed successfully
		 */
		else
		{
			try
			{
				res.setClassNames(ModelicaParser.parseList(retval));
			} 
			catch (ModelicaParserException e)
			{
				throw new UnexpectedReplyException("Unable to parse list: " 
						+ e.getMessage());
			}

			/*
			 * If there were errors, but the compilation went through,
			 * collect the error messages. (Test if errorString != "") 
			 */
			if(errorString.equals("") == false)
			{
				res.setCompileErrors(OMCParser.parseErrorString(errorString));				
			}
		}

		return res;
	}

	/**
	 * Gets the location (file, starting and ending line number and column
	 * number) of a Modelica element. 
	 * @param className the element we want to get location of
	 * @return an <code>ElementLocation</code> containing the file, starting and
	 * ending line number and column number of the given class 
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 * @throws InvocationError
	 */
	public DefinitionLocation getClassLocation(String className)
		throws ConnectException, UnexpectedReplyException, InvocationError 
	{
		String retval = sendExpression("getCrefInfo(" + className + ")");
		
		/* fetch error string but ignore it */
		getErrorString();
		
		if(retval.contains("Error") || retval.contains("error"))
		{
			throw new 
				InvocationError("Fetching file position of " + className,
						"getCrefInfo(" + className + ")");
		}
		
		
		/*
		 * The getCrefInfo reply has the following format:
		 * 
		 * <file path>,<something>,<start line>,<start column>,<end line>,<end column>
		 * 
		 * for example:
		 * /foo/Modelica/package.mo,writable,1,1,1029,13
		 */

		/* For some reason, the list returned doesn't contain curly braces. */
		retval = retval.trim();
		retval = "{" + retval + "}"; 

		List tokens = null;
		try
		{
			tokens = ModelicaParser.parseList(retval);
		} 
		catch (ModelicaParserException e1)
		{
			throw new UnexpectedReplyException("Unable to parse list: " 
					+ e1.getMessage());
		}
		
		String filePath = tokens.elementAt(0).toString();
		int startLine;
		int startColumn;
		int endLine;
		int endColumn;

		try
		{
			startLine = Integer.parseInt(tokens.elementAt(2).toString());
			startColumn = Integer.parseInt(tokens.elementAt(3).toString());
			endLine = Integer.parseInt(tokens.elementAt(4).toString());
			endColumn = Integer.parseInt(tokens.elementAt(5).toString());
		}
		catch (NumberFormatException e)
		{
			throw new 
				UnexpectedReplyException("Can't parse getCrefInfo() reply, "+
										 "unexpected format");
		}
		
		return new DefinitionLocation(filePath, 
					startLine, startColumn, endLine, endColumn);
	}
	
	/**
	 * Queries the compiler if a particular modelica class/package is a package.
	 * 
	 * @param className fully qualified name of the class/package
	 * @return true if className is a package, false otherwise
	 * @throws ConnectException 
	 */
	public boolean isPackage(String className)
		throws ConnectException 
	{
		String retval = sendExpression("isPackage(" + className + ")");

		/* fetch error string but ignore it */
		getErrorString();
		
		return retval.contains("true");
	}
	
	/**
	 * Uses the OMC API call getElementsInfo to fetch lots of information
	 * about a class definition. See interactive_api.txt in the OMC
	 * source tree.
	 * @param className the fully qualified name of a class
	 * @return a <code>Collection</code> (of <code>ElementsInfo</code>)
	 * containing the information about className
	 */
	public Collection<ElementInfo> getElements(String className)
		throws ConnectException, InvocationError, UnexpectedReplyException
	{
		String retval = sendExpression("getElementsInfo("+ className +")");
		
		/* fetch error string but ignore it */
		getErrorString();
		
		/*
		 * we need a efficient way to check if the result is
		 * humongosly huge list or 'Error' or maybe 'error' 
		 */
		for (int i = 0; i < retval.length(); i++)
		{
			if (retval.charAt(i) == '{')
			{
				/* 
				 * we found the begining of the list, send it to parser and 
				 * hope for the best 
				 */
				List parsedList = null;
				try
				{
					parsedList = ModelicaParser.parseList(retval);
				} 
				catch (ModelicaParserException e)
				{
					throw new UnexpectedReplyException("Unable to parse list: " 
							+ e.getMessage());
				}
				
				/* convert the parsedList to a collection of ElementsInfo:s */
				LinkedList<ElementInfo> elementsInfo = 
					new LinkedList<ElementInfo>();

				for (ListElement element : parsedList)
				{
					elementsInfo.add(new ElementInfo((List)element));
				}
				
				return elementsInfo;
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

	public IClassInfo getClassInfo(String className)
		throws ConnectException, UnexpectedReplyException
	{
		String retval = sendExpression("getClassInformation("+ className +")");
		
		/* fetch error string but ignore it */
		getErrorString();
		
		ClassInfo ci = null;
		
		try
		{
			ci = new ClassInfo(retval);
		} 
		catch (ModelicaParserException e)
		{
			throw new UnexpectedReplyException(retval);
		}
		catch (IllegalRestrictionException e)
		{
			throw new UnexpectedReplyException(retval);
		}
		
		return  ci;
	}

	
	/**
	 * @return the name of the compiler that this plugin tries to communicate
	 * with (at least it tries...)
	 */
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
			
			/* fetch error string but ignore it */
			getErrorString();
			
			systemLibraryLoaded = true;
		}

		return standardLibraryPackages;
	}
}
