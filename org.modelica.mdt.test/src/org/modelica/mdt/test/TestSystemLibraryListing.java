package org.modelica.mdt.test;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ISystemLibrary;
import org.modelica.mdt.internal.core.SystemLibrary;

import junit.framework.TestCase;

/**
 * 
 * @author Andreas Remar
 *
 */
//TODO clean me up !
// all warnings and System.out.println() must be removed 
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
			
			System.out.println(i.getElementName());
			
			List<IModelicaPackage> imps2 = i.getPackages();
			String[] names = {"UsersGuide", "Media", "Utilities", "Mechanics", 
							  "Electrical", "Math",  "Blocks", "Thermal", 
							  "Icons", "Constants", "SIunits", "StateGraph"};
			for(int j = 0;j < imps2.size();j++)
			{
				assertTrue("Name doesn't match: " + names[j] + " != " + 
						imps2.get(j).getElementName(),
						names[j].equals(imps2.get(j).getElementName()));
				System.out.println(" " + imps2.get(j).getElementName());
			}
		}
	}
	
	private void traverse(IModelicaPackage imp, int depth)
	{
		System.out.println(imp.getElementName());

		if(depth <= 0)
			return;
		
		List<IModelicaPackage> imps = imp.getPackages();
		for(IModelicaPackage i : imps)
		{
			traverse(i, depth-1);
		}
	}
}
