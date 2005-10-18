package org.modelica.mdt.corba;

import org.modelica.mdt.MdtPlugin;
import org.omg.CORBA.ORB;

public class ModeqCommunicationImplementation
{
	static ORB orb;
	static ModeqCommunication omcc;
	static boolean hasInitialized = false;
	
	public static void init(String args[])
	{
		try
		{
			orb = ORB.init(args, null);
			org.omg.CORBA.Object obj = orb.string_to_object("IOR:010000001b00000049444c3a4d6f646571436f6d6d756e69636174696f6e3a312e30000002000000000000002b000000010100000a0000003132372e302e302e31000f05130000002f323135362f313132393633363632392f5f30000100000024000000010000000100000001000000140000000100000001000100000000000901010000000000");
			omcc = ModeqCommunicationHelper.narrow(obj);
			sendExpression("loadModel(Modelica");
		} 
		catch(Exception e)
		{
			MdtPlugin.log(e);
		}
		hasInitialized = true;
	}

	public static String sendExpression(String exp)
	{
		String retval = null;
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

	public static String sendClass(String exp)
	{
		String retval = null;
		try {
			retval = omcc.sendClass(exp);
		} catch(Exception e) {
			MdtPlugin.log(e);
		}
		return retval;
	}
}
