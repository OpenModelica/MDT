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
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.internal.omcproxy.CompilerException;
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
	/* the test subject */
	private IModelicaFolder root;
	
	/* collection of expected objects */
	private Vector<String> expectedFileNames = new Vector<String>(3);
	private Vector<String> expectedFolderNames = new Vector<String>(3);
	private Vector<String> expectedModelicaFileNames = new Vector<String>(2);
	private Vector<String> expectedPackageNames = new Vector<String>(2);
	private Vector<String> expectedRootFolderFileNames = new Vector<String>(1);
	/* expected modelica file names in package_look_alike folder */
	private Vector<String> expectedPackageLookAlikeNames 
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
		assertTrue(Collections.addAll(expectedFileNames,
				".project", "empty_file", "README.txt"));
		
		assertTrue(Collections.addAll(expectedFolderNames,
				"empty_folder", "root_folder", "package_look_alike"));

		assertTrue(Collections.addAll(expectedModelicaFileNames,
				"root_model.mo", "nested_models.mo"));
		
		assertTrue(Collections.addAll(expectedPackageNames,
				"root_package", "childless_package"));
		
		assertTrue(Collections.addAll(expectedRootFolderFileNames,
				"hej_hopp"));

		assertTrue(Collections.addAll(expectedPackageLookAlikeNames,
				"package.mo"));


	}
	
	
	/**
	 * test ModelicaFolder.getFiles()
	 */
	public void testGetFiles()
	{
		try 
		{
			for (IFile file : root.getFiles())
			{
				expectedFileNames.remove(file.getName());
			}
			assertTrue("could not find all expected files in the root folder",
					expectedFileNames.isEmpty());
			
		}
		catch (CoreException e) 
		{
			fail("exception thrown while fetching files");
		}
	}
	
	/**
	 * test ModelicaFolder.getFolders()
	 * and check how nested IModelicaFolder:s are doing
	 */
	public void testGetFolders()
	{
		IModelicaFolder empty_folder = null;
		IModelicaFolder root_folder = null;
		IModelicaFolder package_look_alike = null;
		String name;

		try 
		{
			for (IModelicaFolder folder : root.getFolders())
			{
				name = folder.getElementName();
				
				expectedFolderNames.remove(name);
				if (name.equals("empty_folder"))
				{
					empty_folder = folder;
				}
				else if (name.equals("root_folder"))
				{
					root_folder = folder;
				}
				else if (name.equals("package_look_alike"))
				{
					package_look_alike = folder;
				}
			}

			assertTrue("could not find all expected folders in the root folder",
					expectedFolderNames.isEmpty());
			assertNotNull(empty_folder);
			assertNotNull(root_folder);
			assertNotNull(package_look_alike);
			
			/*
			 * empty folder should not have any children
			 */
			assertFalse(empty_folder.hasChildren());
			assertTrue(empty_folder.getChildren().isEmpty());
			assertTrue(empty_folder.getFiles().isEmpty());
			assertTrue(empty_folder.getFolders().isEmpty());
			assertTrue(empty_folder.getPackages().isEmpty());
			assertTrue(empty_folder.getModelicaFiles().isEmpty());
			
			/* check contents of root_folder */
			for (IFile file : root_folder.getFiles())
			{
				expectedRootFolderFileNames.remove(file.getName());
			}
			assertTrue("could no find all expected files in root_folder",
					expectedRootFolderFileNames.isEmpty());
			
			/* check contents of package_look_alike */
			for (IModelicaFile file : package_look_alike.getModelicaFiles())
			{
				expectedPackageLookAlikeNames.remove(file.getElementName());
			}
			assertTrue("could no find all expected files in package_look_alike",
					expectedPackageLookAlikeNames.isEmpty());
			
			
			
		}
		catch (CoreException e)
		{
			fail("exception thrown while fetching files");
		}
		catch (CompilerException e)
		{
			fail("exception thrown while fetching files");
		}

	}
	
	/**
	 * test ModelicaFolder.getModelicaFiles()
	 */
	public void testGetModelicaFiles()
	{
		try 
		{
			for (IModelicaFile file : root.getModelicaFiles())
			{
				expectedModelicaFileNames.remove(file.getElementName());
			}
			assertTrue("could not find all expected mo files in the root folder",
					expectedModelicaFileNames.isEmpty());
			
		}
		catch (CoreException e)
		{
			fail("exception thrown while fetching files");
		}
	}

	/**
	 * test ModelicaFolder.getPackages()
	 */
	public void testPackages()
	{
		try 
		{
			for (IModelicaPackage pkg : root.getPackages())
			{
				expectedPackageNames.remove(pkg.getElementName());
			}

			assertTrue("could not find all expected mo files in the root folder",
					expectedPackageNames.isEmpty());
			
		}
		catch (CoreException e)
		{
			fail("exception thrown while fetching packages");
		}
		catch (CompilerException e) 
		{
			fail("exception thrown while fetching packages");
		}
	}
}
