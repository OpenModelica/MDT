/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
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
 * * Neither the name of Link�pings universitet nor the names of its
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

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.resources.IWorkspaceRoot;

import org.eclipse.ui.PlatformUI;

import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * @author Elmir Jagudin
 */
public class TestModelicaRoot extends TestCase 
{
	/* this flags are set from the modelica element change listener */
	private boolean simpleProjectAdded     = false;
	private boolean modelicaProjectAdded   = false;
	private boolean simpleProjectRemoved   = false;
	private boolean modelicaProjectRemoved = false;
	
	public class ElementListener implements IModelicaElementChangeListener 
	{

		public void elementsChanged(Collection<IModelicaElementChange> changes) 
		{
			for (IModelicaElementChange change : changes)
			{
				Object res = change.getElement();
				if (!(res instanceof IModelicaProject))
				{
					/* we are only interested in changes on projects */
					break;
				}
				IModelicaProject proj = (IModelicaProject)res;
				
				switch (change.getChangeType())
				{
				case ADDED:
					if (proj.getElementName().equals(PROJECT_NAME_SIM_EXTRA))
					{
						simpleProjectAdded = true;
					}
					else if (proj.getElementName().equals(PROJECT_NAME_MOD_EXTRA))
					{
						modelicaProjectAdded = true;
					}

					break;
				case REMOVED:
					if (proj.getElementName().equals(PROJECT_NAME_SIM_EXTRA))
					{
						simpleProjectRemoved = true;
					}
					else if (proj.getElementName().equals(PROJECT_NAME_MOD_EXTRA))
					{
						modelicaProjectRemoved = true;
					}
				}
			}
		}
	}

	/* names of modelica projects */
	private Vector<String> modelicaProjects = new Vector<String>(2);
	
	/* names of simple (no modelica) projects */
	private Vector<String> simpleProjects = new Vector<String>(2);
	
	private static final String PROJECT_NAME_1 = 
		TestModelicaRoot.class.getName() + "1";
	private static final String PROJECT_NAME_2 = 
		TestModelicaRoot.class.getName() + "2";
	private static final String PROJECT_NAME_3 = 
		TestModelicaRoot.class.getName() + "3";
	
	/* the SIMple project which is added and removed */
	private static final String PROJECT_NAME_SIM_EXTRA = 
		TestModelicaRoot.class.getName() + "_SIM";

	/* the MODelica project which is added and removed */
	private static final String PROJECT_NAME_MOD_EXTRA = 
		TestModelicaRoot.class.getName() + "_MOD";

	/* name of a non existent project */
	private static final String PROJECT_NAME_NON_EXISTENT = 
		TestModelicaRoot.class.getName() + "_NON_EXISTENT";

	
	private IWorkspaceRoot workspaceRoot = 
			ResourcesPlugin.getWorkspace().getRoot();	
	private IModelicaRoot modelicaRoot = 
			ModelicaCore.getModelicaRoot();
	
	@Override
	protected void setUp() throws CoreException 
	{
		Area51Projects.createProjects();

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
		if (!project.exists())
		{
			project.create(null);
			project.open(null);
		}
		
		/*
		 * create a regular projects which remains closed
		 */
		project = workspaceRoot.getProject(PROJECT_NAME_3);
		if (!project.exists())
		{
			project.create(null);
		}

		assertTrue(Collections.addAll(modelicaProjects,
				PROJECT_NAME_1));
		
		assertTrue(Collections.addAll(simpleProjects,
				PROJECT_NAME_2, PROJECT_NAME_3));


	}
	
	/**
	 * Test if IModelicaRoot.getProjects() (implemented by ModelicaRoot) 
	 * works as prescribed. 
	 */
	public void testGetProjects() throws CoreException
	{
 
		IModelicaProject mproj;
		for (Object p : modelicaRoot.getProjects())
		{
			mproj = (IModelicaProject)p;

			if (modelicaProjects.contains(mproj.getElementName()))
			{
				if (mproj.getProject().isOpen())
				{
					assertTrue("project without modelica nature wrapped",
						((IModelicaProject)p).getProject().
						hasNature(CorePlugin.MODELICA_NATURE));
				}
			}
			else if (simpleProjects.contains(mproj.getElementName()))
			{
				if (mproj.getProject().isOpen())
				{
					assertFalse("project with modelica nature not wrapped",
						mproj.getProject().hasNature(CorePlugin.MODELICA_NATURE));
				}
			}
		}
	}
	
	/**
	 * Test if IModelicaRoot.getProject() (implemented by ModelicaRoot) 
	 * works as prescribed. 
	 */
	public void testGetProject()
	{
		IModelicaProject proj;
		
		proj = modelicaRoot.getProject(PROJECT_NAME_1);
		assertEquals(PROJECT_NAME_1, proj.getElementName());
		
		proj = modelicaRoot.getProject(PROJECT_NAME_3);
		assertEquals(PROJECT_NAME_3, proj.getElementName());

		proj = modelicaRoot.getProject(PROJECT_NAME_2);
		assertEquals(PROJECT_NAME_2, proj.getElementName());
		
		proj = modelicaRoot.getProject(PROJECT_NAME_NON_EXISTENT);
		assertNull("hmm, an non-existent project found?", proj);
	}
	
	
	/**
	 * Test adding and removing projects to the workspace and check
	 * if ModelicaRoot picks up the changes.
	 */
	public void testChangesToWorkspace() throws CoreException
	{
		ModelicaCore.getModelicaRoot().
			addModelicaElementChangeListener(new ElementListener());
		
		/* 
		 * check if ModelicaRoot picks up additions of projects
		 */
		IProject simpleProject = 
			workspaceRoot.getProject(PROJECT_NAME_SIM_EXTRA);
		simpleProject.create(null);
		simpleProject.open(null);
		
		IProject modelicaProject = 
			ModelicaCore.createProject(PROJECT_NAME_MOD_EXTRA, 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		modelicaProject.open(null);

		
		/* wait for the changes to kick throug */
		while (!simpleProjectAdded && !modelicaProjectAdded)
		{
			Utility.sleep(this, 105);
		}
		
		boolean simpleProjFound = false;
		boolean modelicaProjFound = false;

		for (Object proj : modelicaRoot.getProjects())
		{
			if (proj instanceof IModelicaProject)
			{
				String name = ((IModelicaProject)proj).getElementName();

				if (name.equals(PROJECT_NAME_MOD_EXTRA))
				{
					modelicaProjFound = true;
				}
				else if (name.equals(PROJECT_NAME_SIM_EXTRA))
				{
					simpleProjFound = true;
				}
			}
		}		
		assertTrue("ModelicaRoot did not pick up addition of " + 
				PROJECT_NAME_SIM_EXTRA, simpleProjFound);
		
		assertTrue("ModelicaRoot did not pick up addition of " + 
				PROJECT_NAME_MOD_EXTRA, modelicaProjFound);

		/* check if ModelicaRoot picks up removal of a project */
		simpleProject.delete(true, true, null);
		modelicaProject.delete(true, true, null);
		
		/* wait for the changes to kick throug */
		while (!simpleProjectRemoved && !modelicaProjectRemoved)
		{
			Utility.sleep(this, 97);
		}

		for (Object proj : modelicaRoot.getProjects())
		{
			if (proj instanceof IModelicaProject)
			{
				String name = ((IModelicaProject)proj).getElementName();

				if (name.equals(PROJECT_NAME_MOD_EXTRA))
				{
					fail("ModelicaRoot did not pick up removal of " +
							PROJECT_NAME_MOD_EXTRA);
				}
				else if (name.equals(PROJECT_NAME_SIM_EXTRA))
				{
					fail("ModelicaRoot did not pick up removal of " +
							PROJECT_NAME_SIM_EXTRA);
				}
			}
		}
	}
}
