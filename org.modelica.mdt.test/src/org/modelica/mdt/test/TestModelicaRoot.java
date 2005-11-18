/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Linköpings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.resources.IWorkspaceRoot;

import org.eclipse.ui.PlatformUI;

import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.test.util.Area51Projects;

import junit.framework.TestCase;

/**
 * @author Elmir Jagudin
 *
 */
public class TestModelicaRoot extends TestCase 
{

	private static final String PROJECT_NAME_1 = 
		TestModelicaRoot.class.getName() + "1";
	private static final String PROJECT_NAME_2 = 
		TestModelicaRoot.class.getName() + "2";
	private static final String PROJECT_NAME_3 = 
		TestModelicaRoot.class.getName() + "3";
	
	private IModelicaRoot modelicaRoot = ModelicaCore.getModelicaRoot();
	
	@Override
	protected void setUp() throws Exception 
	{
		Area51Projects.createProjects();
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		  
		/*
		 * create a modelica project
		 */
		IProject project = 
			ModelicaCore.createProject(PROJECT_NAME_1, 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		assertNotNull("failed to create project", project);
		
		/*
		 * create a regular projects
		 */
		project = workspaceRoot.getProject(PROJECT_NAME_2);
		project.create(null);
		project.open(null);
		
		/*
		 * create a regular projects which remains closed
		 */
		project = workspaceRoot.getProject(PROJECT_NAME_3);
		project.create(null);

	}
	
	public void testProjectsEnumeration()
	{
		try 
		{
			/*
			 * check that all wrapped (with IModelicaProject) projects have 
			 * modelica nature and check that non of the unwrapped projects 
			 * have modelica nature
			 */
			Object[] projs = modelicaRoot.getProjects();
			for (Object p : projs)
			{
				if (p instanceof IModelicaProject)
				{
					if (((IModelicaProject)p).getProject().isOpen())
					{
						assertTrue("project without modelica nature wrapped",
							((IModelicaProject)p).getProject().
							hasNature(MdtPlugin.MODELICA_NATURE));
					}
				}
				else if (p instanceof IProject)
				{
					if (((IProject)p).isOpen())
					{
						assertFalse("project with modelica nature not wrapped",
							((IProject)p).hasNature(MdtPlugin.MODELICA_NATURE));
					}
				}
				else
				{
					fail("unexpected type of project");
				}
			}
		}
		catch (CoreException e) 
		{
			fail("could not get projects list, exception thrown:" + e);
		}
	}
}
