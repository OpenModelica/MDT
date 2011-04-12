/**
 * This file is part of Modelica Development Tooling (MDT).
 * The Modelica Development Tooling (MDT) software is 
 * distributed under the conditions specified below.
 *
 * Copyright (c) 2005-2006,
 * The MDT Team: 
 * @author Adrian Pop [adrpo@ida.liu.se], 
 * @author Elmir Jagudin, 
 * @author Andreas Remar, 
 * Programming Environments Laboratory (PELAB),
 * Department of Computer and Information Science (IDA),
 * Linköping University (LiU).
 * 
 * All rights reserved.
 *
 * (The new BSD license, see also 
 *  http://www.opensource.org/licenses/bsd-license.php)
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
 * * Neither the name of Authors nor the name of Linköpings University nor 
 *   the names of its contributors may be used to endorse or promote products 
 *   derived from this software without specific prior written permission.
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.core.ICompilerResult;
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
import org.modelica.mdt.internal.core.CompilerResult;
import org.modelica.mdt.internal.core.DefinitionLocation;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.omc.internal.ClassInfo;
import org.modelica.mdt.omc.internal.ParseResults;
import org.modelica.mdt.omc.internal.OMCParser;
import org.modelica.mdt.omc.internal.corba.OmcCommunication;
import org.modelica.mdt.omc.internal.corba.OmcCommunicationHelper;
import org.omg.CORBA.ORB;
//import org.eclipse.core.runtime.jobs.ILock;

/**
 * The OMCProxy is the glue between the OpenModelica Compiler and MDT.
 * It uses the interactive API of OMC to get information about classes
 * and to load classes into OMC.
 * 
 * @author Adrian Pop
 * @author Andreas Remar
 */
public class OMCProxy implements IModelicaCompiler
{
	
	/* the CORBA object */
	private  OmcCommunication omcc;

	enum osType { WINDOWS, UNIX };
	
	private String corbaSession = "mdt";

	/* what Operating System we're running on */
	private  osType os;

	/* indicates if we've setup the communication with OMC */
	private boolean hasInitialized = false;

	/* indicates if we've give up to run OMC as it didn't wanted to start! */
	private boolean couldNotStartOMC = false;
	/* number of compiler errors to show */
	private int showMaxErrors = 10;	
	/* number of compiler errors to show */
	private int numberOfErrors = 0;	
	

	/* indicates if the Modelica System Library has been loaded */
	private boolean systemLibraryLoaded = false;

	private ArrayList<String> standardLibraryPackages = null;
	
	/* should we trace the calls to sendExpression? */
	private  boolean traceOMCCalls  = false;
	private  boolean traceOMCStatus = true;
	
	private  boolean traceStatusPreferences = false;
	private  boolean traceCommandsPreferences = false;
	private  boolean traceErrorPreferences = false;	
	private  boolean traceReplyPrefereces = false;	
	
	{
		/* load debug options and set debug flag variables accordingly */
		/*load trace/omcCalls flag */
		String value = Platform.getDebugOption("org.modelica.mdt.omc/trace/omcCalls");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			traceOMCCalls = true;
		}		

		value = Platform.getDebugOption("org.modelica.mdt.omc/trace/omcStatus");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			traceOMCStatus = true;
		}
	}
	
	private  OutputStream consoleOutput = null;
	
	private OMCThread fOMCThread = null;
	private boolean fOMCThreadHasBeenScheduled = false;
	
	//private ILock fOMCLock = null;

	public OMCProxy()
	{
		/* create the OMC thread */
		if (fOMCThread == null) 
		{
			fOMCThread = new OMCThread();
			// fOMCLock = Platform.getJobManager().newLock();	
		}
	}

	/**
	 * Reads in the OMC CORBA object reference from a file on disk.
	 * @return the object reference as a <code>String</code>
	 */
	private  String readObjectFromFile() throws ConnectException
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
			throw new ConnectException("Unable to read OpenModelica Compiler CORBA object from "+ f.toString());
		}

		br = new BufferedReader(fr);

		try
		{
			stringifiedObjectReference = br.readLine();
		}
		catch(IOException e)
		{
			throw new ConnectException("Unable to read OpenModelica Compiler CORBA object from " + getPathToObject());
		}
		return stringifiedObjectReference;
	}

	/**
	 * @return Returns the path to the OMC CORBA object that is stored on disk.
	 */
	private  String getPathToObject()
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
			if (corbaSession == null || corbaSession.equalsIgnoreCase(""))
				fileName = "/tmp/openmodelica." + username + ".objid";
			else
				fileName = "/tmp/openmodelica." + username + ".objid" + "." +  corbaSession;
			break;
		case WINDOWS:
			String temp = System.getenv("TMP");			
			if (corbaSession == null || corbaSession.equalsIgnoreCase(""))
				fileName = temp + "\\openmodelica.objid";
			else
				fileName = temp + "\\openmodelica.objid"+ "." + corbaSession;
			break;
		default:
			ErrorManager.logBug("org.modelica.mdt.omc",	"os variable set to unexpected os-type");
		}

		logOMCStatus("Will look for OMC object reference in '" + fileName + "'.", true);

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
	private  File[] getOmcBinaryPaths() throws ConnectException
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
			logOMCStatus("Using OPENMODELICAHOME environment variable to find omc-binary", true);

			/* 
			 * Standard path to omc (or omc.exe) binary is encoded in OPENMODELICAHOME
			 * variable. 
			 */
			String openModelicaHome = System.getenv("OPENMODELICAHOME");
			if(openModelicaHome == null)
			{
				final String m = "Environment variable OPENMODELICAHOME not set";
				logOMCStatus("Environment variable OPENMODELICAHOME not set,"+
				" don't know how to start OMC from standard path.", true);
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
					logOMCStatus("Using omc-binary at '" + omcBinary.getAbsolutePath() + "'", true);
					break;
				}
				else
				{
					logOMCStatus("No omc binary at: [" + path + "]", true);
				}
			}

			if (omcBinary == null)
			{
				logOMCStatus("Could not find omc-binary on the OPENMODELICAHOME path", true);
				throw new ConnectException("Unable to start the OpenModelica Compiler, binary not found");
			}
		}
		else
		{
			omcBinary = new File(PreferenceManager.getCustomOmcPath());

			logOMCStatus("Using userspecified omc-binary at '" + omcBinary.getAbsolutePath() + "'", true);

			if (!omcBinary.exists())
			{
				logOMCStatus("file '" + omcBinary.getAbsolutePath() + "' does not exist", true);
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
			 * e.g. binary path /foo/bar/bin/omc      => working directory /foo/bar/work
			 *      binary path /foo/bar/compiler/omc => working directory /foo/bar/work
			 *      binary path /foo/bar/omc          => working directory /foo/bar
			 */
			File parent = omcBinary.getParentFile();

			if (parent.getName().equalsIgnoreCase("bin") || 
				parent.getName().equalsIgnoreCase("compiler"))
			{
				omcWorkingDirectory = parent.getParentFile();
				File work = new File(omcWorkingDirectory.getAbsolutePath()+File.pathSeparator+"tmp");
				if (work.exists()) 
				{
					omcWorkingDirectory = work;
				}
				else
				{
					work = new File(omcWorkingDirectory.getAbsolutePath()+File.pathSeparator+"work");
					if (work.exists()) 
					{
						omcWorkingDirectory = work;
					}
					else
					{
						// start in tmp.
					}
				}				
			}
			else
			{
				// start in tmp
				omcWorkingDirectory = parent;
			}

		}

		return new File[] {omcBinary, omcWorkingDirectory};
	}

	/**
	 * Start a new OMC server.
	 */
	private synchronized void startServer() throws ConnectException
	{
		if (!fOMCThreadHasBeenScheduled)
		{
			fOMCThread.start();
			fOMCThreadHasBeenScheduled = true;
			/* wait for 10 seconds */
			try { Thread.sleep(1000); } catch(InterruptedException e) {};
		}
	}

	/**
	 * Initializes an ORB, converts the stringified OMC object to a real
	 * CORBA object, and then narrows that object to an OmcCommunication
	 * object. 
	 */
	private synchronized void setupOmcc(String stringifiedObjectReference)
	{
		/* Can't remember why this is needed. But it is. */
		String args[] = {null};

		/* set the CORBA read timeout to a larger value as we send huge ammounts of data
		 * from OMC to MDT
		 */
		System.setProperty("com.sun.CORBA.transport.ORBTCPReadTimeouts", "1:60000:300:1"); 
		
		ORB orb;
		orb = ORB.init(args, null);		

		/* Convert string to object. */
		org.omg.CORBA.Object obj = orb.string_to_object(stringifiedObjectReference);		

		/* Convert object to OmcCommunication object. */
		omcc = OmcCommunicationHelper.narrow(obj);
	}

	/**
	 * @return the name of the operating system. If an unknown os is found,
	 * the default is Unix. 
	 */
	private  osType getOs()
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
	private synchronized void init() throws ConnectException
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
			logOMCStatus("No OMC object reference found, starting server.", true);
			startServer();
		}
		else
		{
			logOMCStatus("Old OMC CORBA object reference present, assuming OMC is running.", true);
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
			logOMCStatus("Trying to send 'getVersion()' expression to OMC.", true);
			omcc.sendExpression("getVersion()");
			logOMCStatus("Expression sent successfully.", true);
		}
		catch(Exception e)
		{
			/* Start server and set up omcc */
			logOMCStatus("Failed sending expression, will try to start OMC.", true);
			startServer();

			stringifiedObjectReference = readObjectFromFile();
			setupOmcc(stringifiedObjectReference);

			try
			{
				/* Once again try to send an expression to OMC. If it fails this
				 * time it's time to send back an exception to the caller of
				 * this function. */
				logOMCStatus("Trying to send 'getVersion()' expression to OMC.", true);
				omcc.sendExpression("getVersion()");
				logOMCStatus("Expression sent successfully.", true);
			}
			catch(org.omg.CORBA.COMM_FAILURE x)
			{
				logOMCStatus("Failed sending expression, giving up.", true);
				throw new ConnectException("Unable to start the OpenModelica Compiler.");
			}
		}

		hasInitialized = true;
	}

	/**
	 * Send expression to OMC. If communication is not initialized, it is initialized here.
	 * @param command the expression to send to OMC 
	 * @param showInConsole true or false
	 * @throws ConnectException if we're unable to start communicating with
	 * @return a String[] {result, answerToGetErrorString};
	 * the server
	 */
	// TODO add synchronization so that two threads don't fudge up each others
	// communication with OMC
	// old synchronization aka 'private synchronized String sendExpression(String command)'
	// doesnt work when there is possibility of multiple instances of OMCProxy objects
	public ICompilerResult sendExpression(String command, boolean showInConsole) throws ConnectException
	{	
		String error = null;		
		String[] retval = { "" };
		// if we couldn't start OMC, don't even bother anymore!
		if (couldNotStartOMC)
		{
			return CompilerResult.makeResult(retval, error);
		}
		
		if (numberOfErrors > showMaxErrors) return CompilerResult.makeResult(retval, error);
		
		// trim the start and end spaces
		command = command.trim();
		// first try to evaluate commands locally if they start with '!'
		// commands starting with ! are used by MDT Console.
		if (command.startsWith("!")) 
		{
			retval[0] = evaluateExpressionLocally(command.substring(1));
			return CompilerResult.makeResult(retval, error);
		}
		
		if(hasInitialized == false)
		{
			init();
		}
		
		if (command.equalsIgnoreCase("quit()")) 
			hasInitialized = false;

		try
		{
			logOMCCall(command, showInConsole);
			/**
			 * Fetches the error string from OMC. This should be called after an "Error"
			 * is received. (Or whenever the queue of errors should be emptied.)
			 */
			//synchronized (omcc)
			{
				retval[0] = omcc.sendExpression(command); 
				error = omcc.sendExpression("getErrorString()");
				/* Make sure the error string isn't empty */
				if(error != null && error.length() > 2)
				{
					error = error.trim();
					error = error.substring(1, error.length() - 1);
				}
				else
				{
					error = null;
				}			
				logOMCReply(retval[0], showInConsole);
				logOMCCall("getErrorString()", showInConsole);
				logOMCReply(error, showInConsole);
				/*
				if (error != null && !error.equalsIgnoreCase("")) 
					System.out.println(" -=> getErrorString() = '" + error + "'");
				else
					System.out.println("");
				*/
				return CompilerResult.makeResult(retval, error);				
			}			
		}
		catch(org.omg.CORBA.COMM_FAILURE x)
		{
			numberOfErrors++;
			logOMCCallError("Error while sending command " + command + " ["+x+"]", showInConsole);
			/* lost connection to OMC or something */
			throw new ConnectException("Couldn't send command to the OpenModelica Compiler. Tried sending: " + command);
		}

		//return CompilerResult.makeResult(retval, error);
	}

	/**
	 * Logs the expression sent to OMC if the
	 * tracing flag (traceOMCCalls) is set
	 * 
	 * @param expression the expression that is about to be sent to OMC
	 */
	private  void logOMCCall(String expression, boolean showInConsole)
	{
		if (showInConsole == true && traceCommandsPreferences == true)
		{
			if (consoleOutput != null)
			{
				ConsoleWriter cWriterCommand = new ConsoleWriter(consoleOutput, "command: ");				
				cWriterCommand.setMessage(expression);
				cWriterCommand.start();
			}
		}
			
		if (!traceOMCCalls)
		{
			return;
		}
		System.out.println(">> " + expression);
	}
	
	public void setConsoleOutputStream(OutputStream outputStream)
	{
		consoleOutput = outputStream;
	}
	

	/**
	 * outputs the message about a call error that occured
	 * when communicating with omc
	 * @param message the message to log
	 */
	private  void logOMCCallError(String message, boolean showInConsole)
	{
		if (showInConsole == true && traceErrorPreferences == true)
		{
			if (consoleOutput != null)
			{
				ConsoleWriter cWriterError = new ConsoleWriter(consoleOutput, "error: ");
				cWriterError.setMessage(message);
				cWriterError.start();
			}
		}
		
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
	private  void logOMCStatus(String message, boolean showInConsole)
	{
		if (showInConsole == true && traceStatusPreferences == true)
		{
			if (consoleOutput != null)
			{
				ConsoleWriter cWriterStatus = new ConsoleWriter(consoleOutput, "status: ");				
				cWriterStatus.setMessage(message);
				cWriterStatus.start();
			}
		}
		
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
	private  void logOMCReply(String reply, boolean showInConsole)
	{
		if (showInConsole == true && traceReplyPrefereces == true)
		{
			if (consoleOutput != null)
			{
				ConsoleWriter cWriterReply = new ConsoleWriter(consoleOutput, "reply :");				
				cWriterReply.setMessage(reply);
				cWriterReply.start();
			}
		}
		
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
	public List getClassNames(String className) throws ConnectException, UnexpectedReplyException
	{
		ICompilerResult retval = sendExpression("getClassNames("+className+")", true);

		List list = null;
		try
		{
			list = ModelicaParser.parseList(retval.getFirstResult());
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
		ICompilerResult result = sendExpression("getClassRestriction(" + className + ")", true);

		String reply = result.getFirstResult(); 
		/* remove " around the reply */
		reply = reply.trim();

		if(reply.equals(""))
		{
			throw new UnexpectedReplyException("getClassRestriction("+className+") returned an empty result");
		}

		reply = reply.substring(1, reply.length()-1);

		IModelicaClass.Restriction type = null;
		try
		{
			type = IModelicaClass.Restriction.parse(reply);
		}
		catch(IllegalRestrictionException e)
		{
			throw new UnexpectedReplyException("Illegal type: " + e.getMessage());
		}

		return type;
	}

	/**
	 * @author Adrian Pop [adrpo@ida.liu.se]
	 * @param retval, the string returned by the OMC compiler
	 * @return checks if the string is actually an error
	 */	
	public boolean isError(String retval)
	{
		if (retval == null) return false;
		/*
		 * See if there were parse errors, an empty list {} also denotes error
		 */		
		return 
		(retval.toLowerCase().contains("error") /* && 
				!retval.startsWith("{")) || 
				retval.equals("{}"*/);
	}

	protected class LazyLoadResult
	{
		ParseResults results;
		long lastModification;
		
		public LazyLoadResult(ParseResults results, long lastModification)
		{
			this.results = results;
			this.lastModification = lastModification;
		}
	}
	private static Map<IPath, LazyLoadResult> lazyLoadList = new HashMap<IPath, LazyLoadResult>(); 
	
	public static Map<IPath, LazyLoadResult> getLazyLoadList()
	{
		return lazyLoadList;
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
	public ParseResults loadSourceFile(IFile file) throws ConnectException, UnexpectedReplyException
	{
		synchronized (getLazyLoadList())
		{						
			// activate lazy load
			if (getLazyLoadList().containsKey(file.getFullPath()))
			{
				long lastModification = -1;
				if (file.exists())
				{
					lastModification = file.getModificationStamp();
					if (lastModification != IFile.NULL_STAMP)
					{
						LazyLoadResult llr = (LazyLoadResult)getLazyLoadList().get(file.getFullPath());
						if (llr.lastModification >= lastModification)
							return llr.results;
					}
				}
			}
		}

		ParseResults res = new ParseResults();
		String fullName = file.getLocation().toString();
		ICompilerResult ret = sendExpression("loadFileInteractiveQualified(\"" + fullName + "\")", true);

		String retval = ret.getFirstResult();
		/* Always keep your stuff nice and tidy! */
		retval = retval.trim();

		String errorString = ret.getError();

		if(isError(errorString))
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
				System.out.println("Unable to parse list: " + e.getMessage());
				System.out.flush();
				throw new UnexpectedReplyException("Unable to parse list: " + e.getMessage());
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

		synchronized (getLazyLoadList())
		{
			if (file.exists())
			{
				long lastModification = file.getModificationStamp();
				if (lastModification != IFile.NULL_STAMP)				
				getLazyLoadList().put(file.getFullPath(), new LazyLoadResult(res, lastModification));
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
		ICompilerResult res = sendExpression("getCrefInfo(" + className + ")", true);

		String retval = res.getFirstResult();
		retval.trim();

		if(isError(retval))
		{
			throw new InvocationError("Fetching file position of " + className, "getCrefInfo(" + className + ")");
		}


		/*
		 * The getCrefInfo reply has the following format:
		 * 
		 * {<file path>,<something>,<start line>,<start column>,<end line>,<end column>}
		 * 
		 * for example:
		 * {/foo/Modelica/package.mo,writable,1,1,1029,13}
		 */
		retval = retval.trim();

		List tokens = null;
		try
		{
			tokens = ModelicaParser.parseList(retval);
		} 
		catch (ModelicaParserException e1)
		{
			throw new UnexpectedReplyException("Unable to parse list: " + e1.getMessage());
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

		return new DefinitionLocation(filePath, startLine, startColumn, endLine, endColumn);
	}

	/**
	 * Queries the compiler if a particular modelica class/package is a package.
	 * 
	 * @param className fully qualified name of the class/package
	 * @return true if className is a package, false otherwise
	 * @throws ConnectException 
	 */
	public boolean isPackage(String className) throws ConnectException 
	{
		ICompilerResult res = sendExpression("isPackage(" + className + ")", true);

		String retval = res.getFirstResult();
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
		ICompilerResult res = sendExpression("getElementsInfo("+ className +")", true);
		String retval = res.getFirstResult();
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
					throw new UnexpectedReplyException("Unable to parse list: " + e.getMessage());
				}

				/* convert the parsedList to a collection of ElementsInfo:s */
				LinkedList<ElementInfo> elementsInfo = new LinkedList<ElementInfo>();

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
					throw new InvocationError("fetching contents of " + className, "getElementsInfo("+ className +")");
				}
				else
				{
					/* OMC returned someting wierd, panic mode ! */
					break;
				}
			}
		}
		/* we have no idea what OMC returned */
		throw new UnexpectedReplyException("getElementsInfo("+ className +")" +	"replies:'" + retval + "'");
	}

	public IClassInfo getClassInfo(String className)
	throws ConnectException, UnexpectedReplyException
	{
		ICompilerResult res = sendExpression("getClassInformation("+ className +")", true);
		String retval = res.getFirstResult();

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


	public String getCompilerVersion() throws ConnectException
	{
		ICompilerResult res = sendExpression("getVersion()", true);	
		String retval = res.getFirstResult();
		if (retval.length() == 0) return null;
		if (retval.charAt(0) == '"')
			retval = retval.substring(1);
		int lio = -1;
		if ((lio=retval.lastIndexOf('"')) > 0)
			retval = retval.substring(0, lio);		
		return retval.trim();
	}

	/**
	 * @return the name of the compiler that this plugin tries to communicate
	 * with (at least it tries...)
	 */
	public String getCompilerName()
	{
		String version = "";
		try
		{
			version = getCompilerVersion();
		}
		catch(ConnectException e)
		{
			ErrorManager.logError(e);
		}
		return "OpenModelica Compiler " + version; 
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
		String openModelicaLibrary = System.getenv("OPENMODELICALIBRARY");
		if (openModelicaLibrary != null)
		{
			File path = new File(openModelicaLibrary);
			ArrayList<String> libs = new ArrayList<String>();
			for (File f: path.listFiles()) if (f.isDirectory()) libs.add(f.getName());
			standardLibraryPackages = libs; 
		}

		if (!systemLibraryLoaded)
		{
			if (standardLibraryPackages == null) 
			{
				ArrayList<String> libs = new ArrayList<String>();
				libs.add("Modelica");
				standardLibraryPackages = libs; 
			}
			for (Object lib : standardLibraryPackages)
			{
				sendExpression("loadModel("+(String)lib+")", true);
				
			}

			systemLibraryLoaded = true;
		}

		return (String[]) standardLibraryPackages.toArray(new String[standardLibraryPackages.size()]);
	}

	/**
	 * lists the name of a class
	 *  
	 * @throws ConnectException if we're unable to start communicating with
	 * the server
	 */	
	public ICompilerResult getClassString(String className)
	throws ConnectException, UnexpectedReplyException
	{
		ICompilerResult res = sendExpression("list("+ className +")", true);

		res.trimFirstResult();

		return res;
	}
	
	/**
	 * 
	 * @author Adrian Pop
	 *
	 */
	public class ConsoleWriter extends Thread
	{
		private PrintWriter consoleWriter = null;
		private String message = null;
		private String prefix = null;
		
		public ConsoleWriter(OutputStream output, String prefix)
		{
			super();
			if (output != null)
			{
				consoleWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)));
			}
			this.prefix = prefix;
		}
		
		public synchronized void setMessage(String what)
		{
			message = what;
		}
		
		public synchronized void run()
		{
			if (message != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(message, "\n");
				while (tokenizer.hasMoreTokens())
				{
					consoleWriter.write("\nmdt> " + prefix + tokenizer.nextToken());
				}
				consoleWriter.write("\nomc> ");
				consoleWriter.flush();
			}
		}
	}
	
	/**
	 * Evaluate command locally, don't sent it to OpenModelica Compiler
	 * @author Adrian Pop
	 * @param String command
	 * @return String result
	 */
	String evaluateExpressionLocally(String command)
	{
		String retval = "Unknown command!";
		if (command.equals("help"))
		{
			retval = 
				"All the MDT Console local commands start with '!'.\n" +
				"All commands not starting with '!' will be sent to the OpenModelica compiler.\n" +
				"For a list of OpenModelica commands available type help().\n" +
				"NOTE: The !trace* commands display traces of all OpenModelica<->MDT\n" +
				"      communication except the commands send from the console!\n" +
				"Available local commands are:\n" +
				"!help         - toggle display help on local commands.\n" +
				"!traceStatus  - toggle display the status of the OpenModelica compiler.\n" +
				"!traceError   - toggle display errors talking with the OpenModelica compiler.\n" +
				"!traceReply   - toggle display the reply of the OpenModelica compiler.\n" +
				"!traceCommand - toggle display the commands send to the OpenModelica compiler.\n" +
				"!traceAll     - toggle on the display of all the traces available.\n" +
				"!traceNone    - toggle off the display of all the traces available.\n";
		}
		
		if (command.equals("traceStatus"))
		{
			traceStatusPreferences = traceStatusPreferences ? false : true;
			retval = "Tracing of OpenModelica status is set to: " + traceStatusPreferences;
		}
		
		if (command.equals("traceError"))
		{
			traceErrorPreferences = traceErrorPreferences ? false : true;
			retval = 
				"Tracing of errors while talking with OpenModelica is set to: " + traceErrorPreferences;
		}		
		
		if (command.equals("traceReply"))
		{
			traceReplyPrefereces = traceReplyPrefereces ? false : true;
			retval = "Tracing of OpenModelica reply to commands is set to: " + traceReplyPrefereces;
		}		

		if (command.equals("traceCommand"))
		{
			traceCommandsPreferences = traceCommandsPreferences ? false : true;
			retval = "Tracing of commands sent to OpenModelica is set to: " + traceCommandsPreferences;
		}
		
		if (command.equals("traceAll"))
		{
			traceCommandsPreferences = true;			
			traceReplyPrefereces     = true;	
			traceErrorPreferences    = true;	
			traceStatusPreferences   = true;
			retval = "All tracing is now enabled!";			
		}
		
		if (command.equals("traceNone"))
		{
			traceCommandsPreferences = false;			
			traceReplyPrefereces     = false;	
			traceErrorPreferences    = false;	
			traceStatusPreferences   = false;
			retval = "All tracing is now disabled!";			
		}		
		
		return "\n" + retval;
	}
	
	class OMCThread extends Thread
	{
		
		public OMCThread()
		{
			super("OpenModelica Interactive Compiler Thread");
		}		

		public void run() 
		{
			File tmp[] = null;
			try{
				tmp = getOmcBinaryPaths();
			}
			catch (ConnectException e) 
			{
				ErrorManager.logError(e);
				couldNotStartOMC = true; 
				hasInitialized = false;
				return;
			}

			File omcBinary = tmp[0];
			final File workingDirectory = tmp[1];

			File f = new File(getPathToObject());
			/* 
			 * Delete old object reference file. We need to do this because we're
			 * checking if the file exists to determine if the server has started
			 * or not (further down). 
			 */
			long lastModified = 0;
    		if(f.exists())
    		{
    			logOMCStatus("Remember the creation time for old OMC object reference file.", true);
    			lastModified = f.lastModified();
    		}
    		
			Process proc = null;
			StreamReaderThread outThread = null;
			StreamReaderThread errThread = null;
			/* TODO! FIXME! add corba session to the preferences! */
			String command[] = { omcBinary.getAbsolutePath(), "+c="+corbaSession, "+d=interactiveCorba"};
			String extraCmds[] = PreferenceManager.getOMCCommandLineParametersArray();
			ArrayList both = new ArrayList(command.length + extraCmds.length);
		    Collections.addAll(both, command);
		    Collections.addAll(both, extraCmds);
		    String cmd[] = new String[both.size()];
		    int nonNull = 0;
		    for (int i=0; i < both.size(); i++)
		    {
		    	String str = (String) both.get(i);
		    	if (str != null)
		    	{
		    		cmd[nonNull] = str;
		    		nonNull++;
		    	}
		    }
		    String fullCMD = "";
		    for (int i=0; i < nonNull; i++) 
		    	fullCMD += cmd[i] + " ";
			logOMCStatus("Running command: " + fullCMD, true);
			logOMCStatus("Setting working directory to: " + workingDirectory.getAbsolutePath(), true);				
			try
			{
				// prepare buffers for process output and error streams
				proc=Runtime.getRuntime().exec(cmd, null, workingDirectory);
				//create thread for reading inputStream (process' stdout)
				outThread= new StreamReaderThread(proc.getInputStream(),System.out);
				//create thread for reading errorStream (process' stderr)
				errThread= new StreamReaderThread(proc.getErrorStream(),System.err);
				//start both threads
				outThread.start();
				errThread.start();
			}
			catch(IOException e)
			{
				logOMCStatus("Failed to run command: " + fullCMD, true);
				ErrorManager.logError(e);
				couldNotStartOMC = true; hasInitialized = false;
				return;
			}
			logOMCStatus("Command run successfully.", true);				
			logOMCStatus("Waiting for OMC CORBA object reference to appear on disk.", true);


			/*
			 * Wait until the object exists on disk, but if it takes longer than
			 * 10 seconds, abort. (Very arbitrary 10 seconds..)
			 */
			int ticks = 0;
			while(true)
			{
				if (f.exists())
					if (lastModified == 0 || (lastModified != 0 && f.lastModified() != lastModified)) 
						break;
				
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
				if(ticks > 100)
				{
					logOMCStatus("The OMC Corba object reference file has not been modified in 100 seconds; we give up starting OMC.", true);
					couldNotStartOMC = true; hasInitialized = false;
					return;
				}
			}
			logOMCStatus("OMC object reference found.", true);
			int omcExitCode = 0;
			try
			{
				//wait for process to end
				omcExitCode = proc.waitFor();
				if (omcExitCode != 0)
				{
					ErrorManager.logWarning("OpenModelica compiler exited with code:" + omcExitCode);
				}
				//finish reading whatever's left in the buffers
				outThread.join();
				errThread.join();								
			}
			catch(InterruptedException e)
			{
				logOMCStatus("OpenModelica compiler interrupted:" + e.getMessage() + " with code " + proc.exitValue(), true);
				couldNotStartOMC = true; hasInitialized = false;
				return;
			}
			logOMCStatus("OpenModelica compiler exited with code: " + proc.exitValue(), true);
			couldNotStartOMC = true; hasInitialized = false;
		}
	}
	
	public boolean isRunning()
	{
		if (fOMCThread.isAlive()) return true;
		if (hasInitialized) return true;
		return false;
	}

}
