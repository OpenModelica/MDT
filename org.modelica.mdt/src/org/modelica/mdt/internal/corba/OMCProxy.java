package org.modelica.mdt.internal.corba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.internal.corba.InitializationException;
import org.omg.CORBA.ORB;

/**
 * 
 * @author Andreas Remar
 *
 */
public class OMCProxy
{
	private static OmcCommunication omcc;
	private static String os;
	@SuppressWarnings("unused")
	private static Process OmcProcess;

	// TODO Do not do lazy initialization
	private static boolean hasInitialized = false;

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
	
	private static String getPathToObject()
	{
		String fileName = null;
		if(os.equals("Linux"))
		{
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
	
	private static void startServer()
	{
		String pathToOmc = null;
		String argToOmc = null;
		String modelicaPath = null;
		String user = null;

		System.out.println("Starting Open Modelica Compiler");
		
		if(os.equals("Linux"))
		{
			pathToOmc = "/home/x05andre/ex/omc-build/OpenModelica/Compiler/omc";
			modelicaPath = "MODELICAPATH=/home/x05andre/ex/Modelica Library";
		}
		else if(os.equals("Windows"))
		{
			pathToOmc = "c:\\OpenModelica13\\omc.exe";
			modelicaPath = "MODELICAPATH=c:\\OpenModelica13\\ModelicaLibrary";
		}

		argToOmc = "+d=interactiveCorba";
		String userName = System.getenv("USER");
		if(userName == null)
			userName = "nobody";
		user = "USER=" + userName;
		
		File f = new File(getPathToObject());

		if(f.exists())
		{
			System.out.println("Delete " + f);
			if(f.delete())
			{
				System.out.println(f + " deleted");
			}
			else
			{
				System.out.println("Couldn't delete " + f);
			}
		}

		System.out.flush();
		String command[] = {pathToOmc, argToOmc};
		String env[] = {modelicaPath, user};
		try
		{
			OmcProcess = Runtime.getRuntime().exec(command);
			//OmcProcess = Runtime.getRuntime().exec(command, env);
			System.out.println(command[0] + command[1] + " mod path " + modelicaPath);
		}
		catch(IOException e)
		{
			// TODO Handle this exception
			System.out.println("Couldn't start Open Modelica Compiler");
			System.out.println(e);
		}
		
		try
		{
			System.out.println("Sleep 1 second");
			Thread.sleep(1000);
			System.out.println("done Sleeping 1 second");
		}
		catch(InterruptedException e)
		{
			// Ignore
			System.out.println("mrrp" + e);
		}
		
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
		}
	}
	
	private static void setupOmcc(String stringifiedObjectReference)
	{
		String args[] = {null};
		
		ORB orb;
		orb = ORB.init(args, null);
		
		org.omg.CORBA.Object obj = null;
		try
		{
			obj = orb.string_to_object(stringifiedObjectReference);
		}
		catch(Exception e)
		{
			System.out.println("Va <cencur>");
			System.out.println(e);
		}
		
		try
		{
			omcc = OmcCommunicationHelper.narrow(obj);
		}
		catch(Exception e)
		{
			System.out.println("<cencur> Va?");
			System.out.flush();
			System.out.println(e);
		}

	}
	
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
	
	public static void init(String args[]) throws InitializationException
	{
		os = getOs();
		
		File f = new File(getPathToObject());
		String stringifiedObjectReference = null;
		if(!f.exists())
		{
			startServer();
		}
		stringifiedObjectReference = readObjectFromFile();

		setupOmcc(stringifiedObjectReference);

		try
		{
			
			System.out.println("omcc is " + omcc._non_existent());
			omcc.sendExpression("1+1");
		}
		catch(Exception e)
		{
			System.out.println("exception couth");
			startServer();
			stringifiedObjectReference = readObjectFromFile();
			setupOmcc(stringifiedObjectReference);
		}

		try
		{
			omcc.sendExpression("1+1");
		}
		catch(Exception e)
		{
			throw new InitializationException("Unable to start server");
		}

		hasInitialized = true;
	}
	
	public static String sendExpression(String exp) throws InitializationException
	{
		String retval = null;
		
		if(hasInitialized == false)
		{
			init(null);
			System.out.println("Loading Modelica Standard Library");
			retval = sendExpression("loadModel(Modelica)");
			if(retval.equals("false"))
			{
				System.out.println("Unable to load Modelica Standard Library");
			}
		}
		
		retval = omcc.sendExpression(exp);
		
		return retval;
	}

	public static String sendClass(String exp) throws Exception
	{
		String retval = null;

		if(hasInitialized == false)
		{
			try
			{
				init(null);
			}
			catch(Exception e)
			{
				throw new Exception("Unable to initialize communication with OMC");
			}
			sendExpression("loadModel(Modelica)");
		}
		
		try
		{
			retval = omcc.sendClass(exp);
		}
		catch(Exception e)
		{
			MdtPlugin.log(e);
		}
		return retval;
	}
}
