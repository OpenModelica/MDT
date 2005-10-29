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
public class OmcCommunicationImplementation
{
	private static ORB orb;
	private static OmcCommunication omcc;

	// TODO Do not do lazy initialization
	private static boolean hasInitialized = false;

	public static void init(String args[]) throws InitializationException
	{
		String username = System.getenv("USER");
		if(username == null)
		{
			username = "nobody";
		}
		String fileName = "/tmp/openmodelica." + username + ".objid";
		
		File f = new File(fileName);
		String stringifiedObjectReference = null;
			
		if(!f.exists())
		{
			/* start server here */
			// TODO Start server here
			System.out.println("VAFAN!!");
		}

		BufferedReader br = null;
		FileReader fr = null;
		try
		{
			fr = new FileReader(f);
		}
		catch(IOException e)
		{
			throw new InitializationException("Unable to initialize Open Modelica Compiler");
		}

		br = new BufferedReader(fr);
			
		try
		{
			stringifiedObjectReference = br.readLine();
		}
		catch(IOException e)
		{
			throw new InitializationException("Unable to read from " + fileName);
		}
		
		orb = ORB.init(args, null);
		
		// TODO Add try/catch and try to start server if this fails
		org.omg.CORBA.Object obj = orb.string_to_object(stringifiedObjectReference);
		
		omcc = OmcCommunicationHelper.narrow(obj);

		hasInitialized = true;
	}
	
	public static String sendExpression(String exp) throws InitializationException
	{
		String retval = null;
		
		if(hasInitialized == false)
		{
			// System.out.println("Initializing CORBA interface");
			init(null);
			System.out.println("Loading Modelica Standard Library");
			//sendExpression("loadModel(Modelica)");
			sendExpression("loadFile(\"/home/x05andre/ex/Modelica\")");
		}
		
		retval = omcc.sendExpression(exp);
		
		//catch(InitializationException e)
//		{
//			e.printStackTrace();
//			MdtPlugin.log(e);
//		}

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
