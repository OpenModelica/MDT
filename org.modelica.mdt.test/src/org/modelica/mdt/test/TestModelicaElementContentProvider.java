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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.ModelicaElementContentProvider;
import org.modelica.mdt.ui.SystemLibrary;

import junit.framework.TestCase;

/**
 * @author Elmir Jagudin
 */
public class TestModelicaElementContentProvider extends TestCase 
{

	private IModelicaProject modelicaProject;
	private IModelicaProject nonModelicaProject;
	
	/* the test subject */
	ModelicaElementContentProvider provider;

	
	@Override
	protected void setUp() 
	{
		Area51Projects.createProjects();
		
		modelicaProject = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		
		nonModelicaProject = 
			Utility.getProject(Area51Projects.SIMPLE_PROJECT_NAME);
		
		/*
		 * the content provider must be hooked to a viewer 
		 * and the input to the viewer must be set,
		 * otherwise stuff will break in bad ways
		 */
		provider = new ModelicaElementContentProvider();
		TreeViewer viewer = new TreeViewer(new Shell());
		viewer.setLabelProvider(new WorkbenchLabelProvider());
		viewer.setContentProvider(provider);
		viewer.setInput(ModelicaCore.getModelicaRoot());
	}
	
	/**
	 * this test checks that modelica elements have a system library element
	 * and non-modelica lacks it
	 */
	public void testPresensOfSystemLibrary()
	{
		
		/*
		 * check that the modelica project have a system library
		 */
		boolean systemLibraryElementFound = false;
		for (Object elm : provider.getChildren(modelicaProject))
		{
			if (elm instanceof SystemLibrary)
			{
				systemLibraryElementFound = true;
				break;
			}
		}
		assertTrue("no system library element found in modelica project",
				systemLibraryElementFound);

		/*
		 * check that the non-modelica project does _not_ have a system library
		 */
		for (Object elm : provider.getChildren(nonModelicaProject))
		{
			if (elm instanceof SystemLibrary)
			{
				fail("non modelica project contains system library");
			}
		}
	}

}
