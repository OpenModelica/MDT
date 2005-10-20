
package org.modelica.mdt.test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;

import junit.framework.TestCase;

/**
 * 
 * @author Andreas Remar
 *
 */
public class TestModelicaProject extends TestCase {
	
	private static final String PROJECT_NAME = "testModelicaProject";
	//private static final String PROJECT_NAME2 = "testModelicaProject2";
	IProject project;
	IModelicaProject modelicaProject;
	
	protected void setUp() throws Exception
	{
		project = ModelicaCore.createProject(PROJECT_NAME,
						PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		assertNotNull("Failed to create project", project);

//		project = ModelicaCore.createProject(PROJECT_NAME2,
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//		assertNotNull("Failed to create project", project);
	}
	public void testStuff()
	{
		try
		{
			Object[] objs = ModelicaCore.getModelicaRoot().getProjects();
			assertTrue("The number of projects != 1", objs.length == 1);
			for(Object p : objs)
			{
				if(p instanceof IModelicaProject)
				{
					IModelicaPackage[] packages = ((IModelicaProject)p).getPackages();
					for(IModelicaPackage imp : packages)
					{
						//System.out.println(imp.getElementName());
						traverse(imp, 0);
					}
				}
				else
				{
					fail("Unexpected type of project");
				}
			}
		}
		catch(CoreException e)
		{
			fail(e.getMessage());
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	public void traverse(IModelicaPackage pkg, int level)
	{
		IModelicaPackage[] packages = pkg.getPackages();
		packages = pkg.getPackages();
		IModelicaClass[] classes = pkg.getClasses();
		
		for(int i = 0;i < level;i++)
			System.out.print("--");
		System.out.println(pkg.getElementName() + "  P");		

		if(packages != null)
		{
			for(IModelicaPackage imp : packages)
			{
				traverse(imp, level+1);
			}
		}
		
		if(classes != null)
		{
			for(IModelicaClass imc : classes)
			{
				for(int i = 0; i < level;i++)
					System.out.print("  ");
				System.out.println(imc.getName() + "  C ("+pkg.getElementName()+")");
			}
		}
	}
}
