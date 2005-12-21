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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.compiler.CompilerException;
import org.modelica.mdt.test.util.Area51Projects;
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
	
	@Override
	protected void setUp() throws Exception 
	{
		/*
		 * create the project and fetch the reference to the root folder
		 */
		Area51Projects.createProjects();
		
		project_root = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME).getRootFolder();
		
	}
	
	public void testTraverse() throws CoreException, CompilerException
	{
		IModelicaClass root_package = null;
		IModelicaClass childless_package = null;
		IModelicaFile root_package_model = null;
		IModelicaFile root_package_function = null;

		/* for a temporary reference oo package.mo */
		IModelicaFile package_mo = null;

		/* root_package/plain_file */
		IFile root_package_plain_file = null;

		/* root_package/root_package_folder */
		IModelicaFolder root_package_folder = null;
		
		IModelicaClass sub_package = null;
		IModelicaClass leaf_package = null;
		IModelicaFile sub_package_model = null;

		/*
		 * traverse children of project_root 
		 */
		String name;
		for (Object elm : project_root.getChildren())
		{
			if (elm instanceof IFile)
			{
				name = ((IFile)elm).getName();
			}
			else
			{
				name = ((IModelicaElement)elm).getElementName();
			}

			if (name.equals("root_package"))
			{
				root_package = (IModelicaClass)elm;
			}
			else if (name.equals("childless_package"))
			{
				childless_package = (IModelicaClass)elm;
			}
		}

		assertNotNull("root_package not found", root_package);
		assertEquals("Package base name", root_package.getPrefix(), "");
		assertEquals("Package name", root_package.getElementName(), 
				"root_package");
				
		assertNotNull("childless_package not found", childless_package);
		assertEquals("Package base name", childless_package.getPrefix(), "");
		assertEquals("Package name", childless_package.getElementName(), 
				"childless_package");
		
		/*
		 * traverse children of root_package
		 */
		for (Object elm : root_package.getChildren())
		{
			if (elm instanceof IFile)
			{
				name = ((IFile)elm).getName();
			}
			else
			{
				name = ((IModelicaElement)elm).getElementName();
			}

			if (name.equals("sub_package"))
			{
				sub_package = (IModelicaClass)elm;
			}
			else if (name.equals("root_package_model.mo"))
			{
				root_package_model = (IModelicaFile)elm;
			}
			else if (name.equals("root_package_function.mo"))
			{
				root_package_function = (IModelicaFile)elm;
			}
			else if (name.equals("package.mo"))
			{
				package_mo = (IModelicaFile)elm;
			}
			else if (name.equals("plain_file"))
			{
				root_package_plain_file = (IFile)elm;;
			}
			else if (name.equals("root_package_folder"))
			{
					root_package_folder = (IModelicaFolder)elm;;
			}
			
		}

		assertNotNull("sub_package not found", sub_package);
		checkFullName(sub_package, "root_package.sub_package");

		assertNotNull("root_package_model.mo not found", root_package_model);
		assertNotNull("root_package_function.mo not found", 
				root_package_function);
		assertNotNull("root_package/package.mo not found",
				package_mo);
		assertNotNull("root_package/plain_file not found",
				root_package_plain_file);
		assertNotNull("root_package/root_package_folder not found",
				root_package_folder);


		/*
		 * traverse children of sub_package
		 */

		/* traverse packages */		
		for (Object elm : sub_package.getChildren())
		{
			if (elm instanceof IFile)
			{
				name = ((IFile)elm).getName();
			}
			else
			{
				name = ((IModelicaElement)elm).getElementName();
			}
			
			if (name.equals("leaf_package"))
			{
				leaf_package = (IModelicaClass)elm;
			}
			else if (name.equals("package.mo"))
			{
				package_mo = (IModelicaFile)elm;
			}
			else if (name.equals("sub_package_model.mo"))
			{
				sub_package_model = (IModelicaFile)elm;
			}		

		}
		assertNotNull("leaf_package not found", leaf_package);
		checkFullName(leaf_package, "root_package.sub_package.leaf_package");
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
	private void checkFullName(IModelicaClass zePackage, String fullName) 
	{
		assertEquals("Package full name does not match",
					 fullName,	
					 zePackage.getPrefix() + "." + 
					         zePackage.getElementName());
	}
}
