package org.modelica.mdt.corba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.modelica.mdt.MdtPlugin;
import org.omg.CORBA.ORB;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModeqCommunicationImplementation
{
	static ORB orb;
	static ModeqCommunication omcc;
	static boolean hasInitialized = false;
	
	static String pathToModeq = "c:\\OpenModelica122\\modeq.exe";
	
	public static void init(String args[]) throws Exception
	{
		try
		{
			String fileName = System.getenv("TEMP");
			fileName += "\\openmodelica.objid";
			File f = new File(fileName);
			String stringifiedObjectReference = null;

			if(!f.exists())
			{
				System.out.println("Starting modeq server.");
				String[] command = {pathToModeq, "+d=interactiveCorba"};
				Runtime.getRuntime().exec(command);
				while(!f.exists())
				{
					Thread.sleep(100);
				}
			}
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
				
			stringifiedObjectReference = br.readLine();
			
			orb = ORB.init(args, null);
			org.omg.CORBA.Object obj = orb.string_to_object(stringifiedObjectReference);
			omcc = ModeqCommunicationHelper.narrow(obj);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			MdtPlugin.log(e);
			throw new Exception("Unable to initialize communication with OMC");
		}
		hasInitialized = true;
	}

	public static String sendExpression(String exp) throws Exception
	{
		String retval = null;

		if(hasInitialized == false)
		{
			// System.out.println("Initializing CORBA interface");
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
			retval = omcc.sendExpression(exp);
		}
		catch(Exception e)
		{
			MdtPlugin.log(e);
		}

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
		
		try {
			retval = omcc.sendClass(exp);
		} catch(Exception e) {
			MdtPlugin.log(e);
		}
		return retval;
	}
}
