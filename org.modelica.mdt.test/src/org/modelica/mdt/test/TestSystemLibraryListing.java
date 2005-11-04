package org.modelica.mdt.test;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ISystemLibrary;
import org.modelica.mdt.internal.core.SystemLibrary;
import org.modelica.mdt.internal.omcproxy.InitializationException;

import junit.framework.TestCase;

/**
 * 
 * @author Andreas Remar
 *
 */
public class TestSystemLibraryListing extends TestCase 
{
	
	IProject project;
	IModelicaProject modelicaProject;
	
	public void testStuff()
	{
		ISystemLibrary isl = new SystemLibrary();
		List<IModelicaPackage> imps = isl.getPackages();
		for(IModelicaPackage i : imps)
		{
			assertTrue("System library doesn't contain Modelica", 
						i.getElementName().equals("Modelica"));
			
			List<IModelicaPackage> imps2 = null;
			try
			{
				imps2 = i.getPackages();
			}
			catch (InitializationException e)
			{
				// TODO Auto-generated catch block
				fail("Exception when getting packages" + e.getMessage());
			}
			catch (CoreException e)
			{
				// TODO Auto-generated catch block
				fail("Exception when getting packages" + e.getMessage());
			}
			String[] names = {"UsersGuide", "Media", "Utilities", "Mechanics", 
							  "Electrical", "Math",  "Blocks", "Thermal", 
							  "Icons", "Constants", "SIunits", "StateGraph"};
			for(int j = 0;j < imps2.size();j++)
			{
				assertTrue("Name doesn't match: " + names[j] + " != " + 
						imps2.get(j).getElementName(),
						names[j].equals(imps2.get(j).getElementName()));
			}
		}
	}
}
