
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
	}
}
