package org.modelica.mdt.internal.omcproxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaClass.Type;
import org.modelica.mdt.internal.omcproxy.InitializationException;
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

	/**
	 * Reads in the OMC object reference from a file on disk. 
	 */
	private static String readObjectFromFile() throws InitializationException
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
			throw new InitializationException
				("Unable to initialize Open Modelica Compiler");
		}

		br = new BufferedReader(fr);
			
		try
		{
			stringifiedObjectReference = br.readLine();
		}
		catch(IOException e)
		{
			throw new InitializationException("Unable to read from " + 
					getPathToObject());
		}
		return stringifiedObjectReference;
	}
	
	/* Returns the path to the OMC CORBA object that is stored on disk. */
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
	 * 
	 *  @throws InitializationException
	 */
	private static void startServer() throws InitializationException
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
			throw new InitializationException(m);
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
				throw new InitializationException
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
				throw new InitializationException
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
		throws InitializationException
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
			System.out.println("Unsupported OS");
			return "Linux";
		}
	}
	
	private static void init(String args[]) throws InitializationException
	{
		/* Get type of operating system, used for finding object
		 * reference and starting OMC if the reference is faulty */
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
			throw new InitializationException("Unable to start server");
		}

		hasInitialized = true;
	}
	
	/**
	 * Send expression to OMC. If communication is not initialized, it
	 * is initialized here. After initialization it loads the Modelica
	 * Standard Library. 
	 */
	private static String sendExpression(String exp) throws InitializationException
	{
		String retval = null;
		
		if(hasInitialized == false)
		{
			init(null);
			retval = sendExpression("loadModel(Modelica)");
			if(retval.equals("false"))
			{
				System.out.println("Unable to load Modelica Standard Library");
			}
		}
		
		retval = omcc.sendExpression(exp);
		
		return retval;
	}

	/* Send expression to OMC. If communication is not initialized, it
	 * is initialized here. After initialization it loads the Modelica
	 * Standard Library. */
//	private static String sendClass(String exp) throws InitializationException
//	{
//		String retval = null;
//		
//		if(hasInitialized == false)
//		{
//			init(null);
//		}
//		
//		retval = omcc.sendClass(exp);
//		
//		return retval;
//	}
	
	public static String[] getPackages(String className)
		throws InitializationException
	{
		String retval;
		retval = sendExpression("getPackages("+className+")");
		
		String[] tokens = ProxyParser.parseList(retval);
		
		return tokens;
	}
	
	public static String[] getClassNames(String className)
		throws InitializationException
	{
		String retval;
		retval = sendExpression("getClassNames("+className+")");
		
		String[] tokens = ProxyParser.parseList(retval);

		if(tokens == null)
			return null;
		
		Vector<String> v = new Vector<String>();
		
		for(String str : tokens)
		{
			if(str.equals(""))
				continue;

			retval = sendExpression("isPackage("+className+"."+str+")");

			if(retval.contains("false"))
			{
				v.add(str);
			}
		}

		String[] t = new String[v.size()];
		t = v.toArray(t);
		
		return t;
	}
	
	public static IModelicaClass.Type getType(String className)
		throws InitializationException
	{
		IModelicaClass.Type type = null;
		
		if(OMCProxy.sendExpression("isType(" + className + ")").contains("true"))
			type = Type.TYPE;
		else if(OMCProxy.sendExpression("isConnector(" + className + ")").contains("true"))
			type = Type.CONNECTOR;
		else if(OMCProxy.sendExpression("isModel(" + className + ")").contains("true"))
			type = Type.MODEL;
		else if(OMCProxy.sendExpression("isRecord(" + className + ")").contains("true"))
			type = Type.RECORD;
		else if(OMCProxy.sendExpression("isBlock(" + className + ")").contains("true"))
			type = Type.BLOCK;
		else if(OMCProxy.sendExpression("isFunction(" + className + ")").contains("true"))
			type = Type.FUNCTION;
		
		return type;
	}
}
