
package org.modelica.mdt.test;

import java.util.Collections;
import java.util.Vector;

import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;

import junit.framework.TestCase;

public class TestOMCProxy extends TestCase
{
	private Vector<String> modelicaLibraryPackages = new Vector<String>(12);
	
	protected void setUp()
	{
		assertTrue(Collections.addAll(modelicaLibraryPackages,
				"UsersGuide",
				"Media",
				"Utilities",
				"Mechanics",
				"Electrical",
				"Math",
				"Blocks",
				"Thermal",
				"Icons",
				"Constants",
				"SIunits",
				"StateGraph"));
	}
	public void testGetPackages()
	{
		try
		{
			String[] str = OMCProxy.getPackages("Modelica");

			assertNotNull("Could not fetch Modelica package", str);
			
			Vector<String> packages = new Vector<String>();
			for(String s : str)
			{
				packages.addElement(s);
			}

			assertTrue(packages.containsAll(modelicaLibraryPackages));
		}
		catch(InitializationException e)
		{
			fail(e.getMessage());
		}
	}
}
