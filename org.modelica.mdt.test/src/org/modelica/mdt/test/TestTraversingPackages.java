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
import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.test.util.Area51Project;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * Traverses a heirarchy of packages and makes consistensy checks.
 * 
 * @author Elmir Jagudin
 */
public class TestTraversingPackages extends TestCase 
{
	private IModelicaFolder project_root = null;
	private Vector<String> root_package_names = new Vector<String>(2);
	
	@Override
	protected void setUp() throws Exception 
	{
		/*
		 * create the project and fetch the reference to the root folder
		 */
		Area51Project.createProject();
		
		project_root = 
			Utility.getProject(Area51Project.PROJECT_NAME).getRootFolder();
		
		/*
		 * init vector with expected root packages
		 * modelica prespective
		 */				
		assertTrue(Collections.addAll(root_package_names,
				"root_package",
				"childless_root_package"));
	
	}
	
	public void testTraverse() throws CoreException, InitializationException
	{
		IModelicaPackage root_package = null;
		IModelicaPackage childless_package = null;
		IModelicaFile root_package_model = null;
		IModelicaFile root_package_function = null;
		/* for a temporal reference too package.mo */
		IModelicaFile package_mo = null;
		/* root_package/plain_file */
		IFile root_package_plain_file = null;
		/* root_package/root_package_folder */
		IModelicaFolder root_package_folder = null;
		
		IModelicaPackage sub_package = null;
		IModelicaPackage leaf_package = null;
		IModelicaFile sub_package_model = null;


		/*
		 * traverse children of project_root 
		 */
		
		/* traverese packages */
		for (IModelicaPackage pkg : project_root.getPackages())
		{
			
			String name = pkg.getElementName();
			if (name.equals("root_package"))
			{
				root_package = pkg;
			}
			else if (name.equals("childless_package"))
			{
				childless_package = pkg;
			}
		}
		
		assertNotNull("root_package not found", root_package);
		assertEquals("Package base name", root_package.getBaseName(), "");
		assertEquals("Package name", root_package.getElementName(), 
				"root_package");
				
		assertNotNull("childless_package not found", childless_package);
		assertEquals("Package base name", childless_package.getBaseName(), "");
		assertEquals("Package name", childless_package.getElementName(), 
				"childless_package");
		
		/*
		 * traverse children of root_package
		 */
		
		/* traverse packages */		
		for (IModelicaPackage pkg : root_package.getPackages())
		{
			String name = pkg.getElementName();
			if (name.equals("sub_package"))
			{
				sub_package = pkg;
			}
			
		}
		assertNotNull("sub_package not found", sub_package);
		checkFullName(sub_package, "root_package.sub_package");

		
		/* traverse modelica files */
		for (IModelicaFile file : root_package.getModelicaFiles())
		{	
			String name = file.getElementName();
			
			if (name.equals("root_package_model.mo"))
			{
				root_package_model = file;
			}
			else if (name.equals("root_package_function.mo"))
			{
				root_package_function = file;
			}
			else if (name.equals("package.mo"))
			{
				package_mo = file;
			}
		}
		assertNotNull("root_package_model.mo not found", root_package_model);
		assertNotNull("root_package_function.mo not found", 
				root_package_function);
		assertNotNull("root_package/package.mo not found",
				package_mo);

		/* traverse regular files */
		for (IFile file : root_package.getFiles())
		{
			String name = file.getName();

			if (name.equals("plain_file"))
			{
				root_package_plain_file = file;
			}
		}
		assertNotNull("root_package/plain_file not found",
				root_package_plain_file);
		
		/* traverse folders */
		for (IModelicaFolder folder : root_package.getFolders())
		{
			String name = folder.getElementName();
			
			if (name.equals("root_package_folder"))
			{
				root_package_folder = folder;
			}
		}
		assertNotNull("root_package/root_package_folder not found",
				root_package_folder);

		/*
		 * traverse children of sub_package
		 */

		/* traverse packages */		
		for (IModelicaPackage pkg : sub_package.getPackages())
		{
			String name = pkg.getElementName();
			if (name.equals("leaf_package"))
			{
				leaf_package = pkg;
			}
			
		}
		assertNotNull("leaf_package not found", leaf_package);
		checkFullName(leaf_package, "root_package.sub_package.leaf_package");
		
		/* traverse modelica files */		
		package_mo = null;

		for (IModelicaFile file : sub_package.getModelicaFiles())
		{
			String name = file.getElementName();
			if (name.equals("package.mo"))
			{
				package_mo = file;
			}
			else if (name.equals("sub_package_model.mo"))
			{
				sub_package_model = file;
			}		
		}
		assertNotNull("root_package/sub_package/package.mo not found",
				package_mo);
		assertNotNull("root_package/sub_package/sub_package_model.mo not found",
				sub_package_model);


		
	}

	/**
	 * Check that ze package's full name matches the fullName, otherwize
	 * fails.
	 * @param zePackage
	 * @param fullName
	 */
	private void checkFullName(IModelicaPackage zePackage, String fullName) 
	{
		assertEquals("Package full name does not match",
					 fullName,	
					 zePackage.getBaseName() + "." + 
					         zePackage.getElementName());
	}
}
