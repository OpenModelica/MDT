package org.modelica.mdt.internal.omcproxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaClass.Type;
import org.omg.CORBA.ORB;

/**
 * 
 * @author Andreas Remar
 *
 */
public class OMCProxy
{
	private static OmcCommunication omcc;
	private static String os; /* what Operating System we're running on */
	private static boolean hasInitialized = false;
	private static boolean systemLibraryLoaded = false;
	
	private static boolean traceOMCCalls = false;
	static
	{
		/* load debug options and set debug flag variables accordingly */ 
		String value = Platform.getDebugOption
			("org.modelica.mdt/trace/omcCalls");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			traceOMCCalls = true;
		}
		
	}

	/**
	 * Reads in the OMC CORBA object reference from a file on disk. 
	 */
	private static String readObjectFromFile() throws ConnectionException
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
			throw new ConnectionException
				("Unable to initialize Open Modelica Compiler");
		}

		br = new BufferedReader(fr);
			
		try
		{
			stringifiedObjectReference = br.readLine();
		}
		catch(IOException e)
		{
			throw new ConnectionException("Unable to read from " + 
					getPathToObject());
		}
		return stringifiedObjectReference;
	}
	
	/**
	 * @return Returns the path to the OMC CORBA object that is stored on disk.
	 */
	private static String getPathToObject()
	{
		String fileName = null;
		if(os.equals("Linux"))
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
			String temp = System.getenv("TEMP");
			fileName = temp + "\\openmodelica.objid";
		}
		
		return fileName;
	}
	
	/**
	 * Start a new OMC server.
	 */
	private static void startServer() throws ConnectionException
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
			throw new ConnectionException(m);
		}
		
		if(os.equals("Linux"))
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
			f.delete();
		}
		
		String command[] = {pathToOmc, "+d=interactiveCorba"};
		try
		{
			Runtime.getRuntime().exec(command);
		}
		catch(IOException e)
		{
			String secondaryPathToOmc = null;
			try
			{
				if(os.equals("Linux"))
				{
					secondaryPathToOmc = omHome + "/Compiler/omc";
				}
				else if(os.equals("Windows"))
				{
					secondaryPathToOmc = omHome + "\\Compiler\\omc.exe";
				}
				command = new String[]{secondaryPathToOmc, "+d=interactiveCorba"};
				Runtime.getRuntime().exec(command);
			}
			catch(IOException ex)
			{
				throw new ConnectionException
					("Unable to start Open Modelica Compiler\n"
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
			
			/* If we've waited for 5 seconds, abort wait for OMC */
			if(ticks > 50)
			{
				throw new ConnectionException
					("Unable to start Open Modelica Compiler");
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
	 * the default is Linux. 
	 */
	private static String getOs()
	{
		String osName = System.getProperty("os.name");
		if(osName.contains("Linux"))
		{
			return "Linux";
		}
		else if(osName.contains("Windows"))
		{
			return "Windows";
		}
		else
		{
			MdtPlugin.logWarning("Unsupported OS");
			return "Linux";
		}
	}
	
	private static void init(String args[]) throws ConnectionException
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
			startServer();
		}
		
		/* Read in the CORBA OMC object from a file on disk */
		stringifiedObjectReference = readObjectFromFile();

		/* Setup up OMC object reference by initializing ORB and then
		 * converting the string object to a real CORBA object. */
		setupOmcc(stringifiedObjectReference);

		try
		{
			/* Test the server by trying to send an expression to it. 
			 * This might fail if the object reference found on disk didn't
			 * have a corresponding server running. If a server is missing,
			 * catch an exception and try starting a server. */
			omcc.sendExpression("1+1");
		}
		catch(org.omg.CORBA.COMM_FAILURE e)
		{
			/* Start server and set up omcc */
			startServer();
			stringifiedObjectReference = readObjectFromFile();
			setupOmcc(stringifiedObjectReference);
		}

		try
		{
			/* Once again try to send an expression to OMC. If it fails this
			 * time it's time to send back an exception to the caller of this
			 * function. */
			omcc.sendExpression("1+1");
		}
		catch(org.omg.CORBA.COMM_FAILURE e)
		{
			throw new ConnectionException("Unable to start server");
		}

		hasInitialized = true;
	}
	
	/**
	 * Send expression to OMC. If communication is not initialized, it
	 * is initialized here. After initialization it loads the Modelica
	 * Standard Library. 
	 * @throws ConnectionException 
	 */
	private static String sendExpression(String exp) throws ConnectionException
	{
		String retval = null;
		
		if(hasInitialized == false)
		{
			init(null);
		}
		
		logOMCCall(exp);		
		retval = omcc.sendExpression(exp);
		logOMCReply(retval);
		
		return retval;
	}
	
	/**
	 * loggs the expression send to OMC if the
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
	 * loggs the reply resived from OMC if
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

	
	public static void loadSystemLibrary()
		throws ConnectionException
	{
		if (!systemLibraryLoaded)
		{
			sendExpression("loadModel(Modelica)");
			systemLibraryLoaded = true;
		}
	}
	
	/**
	 * @param className full class name where to look for packages
	 * @return an array of subpackages defined (and loaded into OMC)
	 *  inside the class named className
	 * @throws ConnectionException 
	 * @throws InvocationError 
	 * @throws UnexpectedReplyException 
	 */
	public static String[] getPackages(String className)
		throws ConnectionException, InvocationError, UnexpectedReplyException
	{
		String retval;
		
		retval = sendExpression("getPackages("+className+")");

		if (retval.trim().toLowerCase().equals("error"))
		{
			throw new InvocationError("getPackages("+className+")" +  
					" replies 'error'");
		}

		return ProxyParser.parseSimpleList(retval);
	}
	
	/**
	 * @param className full class name where to look for packages
	 * @return an array of subclasses defined (and loaded into OMC)
	 *  inside the class named className
	 * @throws ConnectionException 
	 * @throws UnexpectedReplyException 
	 * @throws InitializationException
	 */	
	public static String[] getClassNames(String className)
		throws ConnectionException, UnexpectedReplyException
	{
		String retval;
		retval = sendExpression("getClassNames("+className+")");
		
		String[] tokens = ProxyParser.parseSimpleList(retval);

		if(tokens == null)
			return null;
		
		Vector<String> v = new Vector<String>();
		
		for(String str : tokens)
		{
			if(!isPackage(className+"."+str))
			{
				v.add(str);
			}
		}

		String[] t = new String[v.size()];
		t = v.toArray(t);
		
		return t;
	}

	/**
	 * 
	 * @param className fully qualified class name
	 * @return the restriction type of the class or null if 
	 *         type can't be determined
	 * @throws ConnectionException 
	 */
	public static IModelicaClass.Type getType(String className)
		throws ConnectionException
	{
		IModelicaClass.Type type = Type.CLASS;
		
		String retval = sendExpression("getClassRestriction(" + className + ")");
		
		if(retval.contains("model"))
		{
			type = Type.MODEL;
		}
		else if(retval.contains("connector"))
		{
			type = Type.CONNECTOR;
		}
		else if(retval.contains("record"))
		{
			type = Type.RECORD;
		}
		else if(retval.contains("block"))
		{
			type = Type.BLOCK;
		}
		else if(retval.contains("function"))
		{
			type = Type.FUNCTION;
		}
		else if(retval.contains("type"))
		{
			type = Type.TYPE;
		}
		
		return type;
	}
	
	public static String getErrorString()
		throws ConnectionException
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
	 * @param file
	 * @return either returns the classes (and packages) found in the file or the error
	 * messages from OMC
	 * @throws ConnectionException 
	 * @throws UnexpectedReplyException 
	 * @throws InitializationException
	 */
	public static ParseResults loadFileInteractive(IFile file)
		throws ConnectionException, UnexpectedReplyException
	{
		ParseResults res = new ParseResults();
		
		String fullName = file.getLocation().toString();
		String retval = sendExpression("loadFileInteractive(\"" + fullName 
				+ "\")");
		
		/*
		 * at this point OMC (ver 1.3.1) 
		 * does not support returning partial parsing
		 * results if there was parsing errors in the file. nor does OMC
		 * provide an interface to both query for file contents and
		 * parsing errors (loadFileInteractive() either returns a class name
		 * list or "error").
		 * 
		 *  so for now we either return parsing errors or class names defined
		 *  in the file. Go PELAB ! 
		 */
		
		/*
		 * there were parse errors
		 */
		if(retval.toLowerCase().contains("error"))
		{
			res.setCompileErrors
				(ProxyParser.parseErrorString(getErrorString()));
		}
		/*
		 * file loaded and parse successsfully
		 */
		else
		{
			res.setClassNames(ProxyParser.parseSimpleList(retval));
		}
		
		return res;
	}

	
	public static ElementLocation getElementLocation(String className)
		throws ConnectionException, UnexpectedReplyException, InvocationError 
	{
		String retval = sendExpression("getCrefInfo(" + className + ")");
		
		if(retval.contains("Error") || retval.contains("error"))
		{
			throw new InvocationError("getCrefInfo(" + className + 
					") replys error");
		}
		
		
		/*
		 * getCrefInfo reply have following format:
		 * 
		 * <file path>,<line number>,<column number>
		 * 
		 */
		
		/* For some reason, the list returned doesn't contain curly braces. */
		retval = "{" + retval + "}"; 

		String[] tokens = ProxyParser.parseSimpleList(retval);
		int line, column;

		try
		{
			line = Integer.parseInt(tokens[1]);
			column = Integer.parseInt(tokens[2]);
		}
		catch (NumberFormatException e)
		{
			throw new InvocationError("can't parse getCrefInfo() reply, "+
					"unexpected format");
		}
		
		return new ElementLocation(tokens[0], line, column);
	}
	
	/**
	 * Queries the compiler if a particular modelica class/package is a package.
	 * 
	 * @param className fully qualified name of the class/package
	 * @return true if className is a package false otherwise
	 * @throws ConnectionException 
	 */
	public static boolean isPackage(String className)
		throws ConnectionException 
	{
		String retval = sendExpression("isPackage(" + className + ")");
		return retval.contains("true");
	}
	
	public static ElementsInfo getElementsInfo(String className)
		throws ConnectionException
	{
		// TODO actually return real elements info..
		String retval = sendExpression("getElementsInfo("+ className +")");
		Vector<Object> v = ProxyParser.parseList(retval);
		System.out.println(v);
		
		return new ElementsInfo();
	}
}
