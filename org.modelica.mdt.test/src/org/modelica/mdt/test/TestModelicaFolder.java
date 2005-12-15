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

import java.util.Collections;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.internal.omcproxy.ConnectException;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * Test various methods in org.modelica.mdt.internal.core.ModelicaFolder
 * 
 * @author Elmir Jagudin
 */
public class TestModelicaFolder extends TestCase 
{
	/*
	 * the test subject
	 * the root folder of Area51 modelica project
	 */
	private IModelicaFolder root;
	
	/* collection of expected children */
	private Vector<String> expectedChildren = new Vector<String>(10);	
	private Vector<String> expectedRootFolderChildren = new Vector<String>(1);
	
	/* expected modelica file names in package_look_alike folder */
	private Vector<String> expectedPackageLookAlikeChildren 
		= new Vector<String>(1);
	
	@Override
	protected void setUp() throws Exception
	{
		Area51Projects.createProjects();
		
		root = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME).
				getRootFolder();
		
		/*
		 * setup expected collections
		 */
		assertTrue(Collections.addAll(expectedChildren,
				".project", "empty_file", "README.txt", "empty_folder", 
				"root_folder", "package_look_alike", "root_model.mo", 
				"nested_models.mo", "root_package", "childless_package"));
				
		assertTrue(Collections.addAll(expectedRootFolderChildren,
				"hej_hopp"));

		assertTrue(Collections.addAll(expectedPackageLookAlikeChildren,
				"package.mo"));
	}

	/**
	 * test ModelicaFolder.hasChildren()
	 */
	public void testHasChildren()
	{
		try 
		{
			assertTrue(root.hasChildren());
		}
		catch (Exception e)
		{
			fail("exception thrown " + e.getMessage());
		}
	}

	/**
	 * test ModelciaFoldet.getChildren()
	 * 
	 */
	public void testGetChildren() 
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CoreException
	{
		IModelicaFolder root_folder = null;
		IModelicaFolder empty_folder = null;
		IModelicaFolder package_look_alike = null;
		
		
		String name = "";
		for (Object elm : root.getChildren())
		{
			
			if (elm instanceof IFile)
			{
				name = ((IFile)elm).getName();
			}
			else
			{
				name = ((IModelicaElement)elm).getElementName();
			}
			expectedChildren.remove(name);
			
			if (name.equals("root_folder"))
			{
				root_folder = (IModelicaFolder) elm;
			}
			else if (name.equals("empty_folder"))
			{
				empty_folder = (IModelicaFolder) elm;
			}
			else if (name.equals("package_look_alike"))
			{
				package_look_alike = (IModelicaFolder) elm;
			}

		}
		assertTrue("could not find all expected children in the root folder",
				expectedChildren.isEmpty());
		
		assertNotNull("root_folder element not found", root_folder);
		assertNotNull("empty_folder element not found", empty_folder);
		assertNotNull("package_look_alike element not found",
				package_look_alike);
		
		for (Object elm : root_folder.getChildren())
		{
			if (elm instanceof IFile)
			{
				/* we only expect (looking for) files inside root_folder */
				name = ((IFile)elm).getName();
				expectedRootFolderChildren.remove(name);
			}
		}
		assertTrue("could no find all expected files in root_folder",
				expectedRootFolderChildren.isEmpty());
		
		for (Object elm : package_look_alike.getChildren())
		{
			if (elm instanceof IModelicaFile)
			{
				/* 
				 * we only expect (looking for) modelica files 
				 * inside package_look_alike 
				 */
				name = ((IModelicaFile)elm).getElementName();
				expectedPackageLookAlikeChildren.remove(name);
			}
		}
		assertTrue("could no find all expected files in package_look_alike",
				expectedPackageLookAlikeChildren.isEmpty());
		
		/*
		 * test the empty_folder IS empty
		 */
		assertFalse("empty_folder not empty", empty_folder.hasChildren());
		assertTrue("empty_folder returns children", 
				empty_folder.getChildren().isEmpty());
	}
}
