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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * Test various methods in org.modelica.mdt.internal.core.ModelicaProject
 */
public class TestModelicaProject extends TestCase 
{
	 /* the test subject, Area51 modelica project */
	private IModelicaProject project;

	/* the names of the expected root packages in the area51 modelica project */
	private Vector<String> expectedRootPackages = new Vector<String>(6);

	@Override
	protected void setUp() throws Exception
	{
		Area51Projects.createProjects();
		
		project = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		
		assertTrue(Collections.addAll(expectedRootPackages,
				"childless_package", "root_package", "hepp", "folder_package",
				"file_package1", "file_package2"));

	}
	
	/**
	 * test (I)ModelciaProject.getPackage() method
	 */
	public void testGetPackage() 
		throws ConnectException, CompilerInstantiationException, 
			UnexpectedReplyException, InvocationError, CoreException
	{
		/*
		 * check that find existing packages works
		 */
		IModelicaClass pkg = project.getPackage("root_package");
		assertEquals("root_package", pkg.getFullName());
		
		pkg = project.getPackage("childless_package");
		assertEquals("childless_package", pkg.getFullName());
		
		pkg = project.getPackage("folder_package");
		assertEquals("folder_package", pkg.getFullName());
		
		pkg = project.getPackage("root_package.sub_package");
		assertEquals("root_package.sub_package", pkg.getFullName());

		pkg = project.getPackage("root_package.sub_package.leaf_package");
		assertEquals("root_package.sub_package.leaf_package",
				pkg.getFullName());

		pkg = project.getPackage("hepp");
		assertEquals("hepp", pkg.getFullName());

		pkg = project.getPackage("hepp.hopp");
		assertEquals("hepp.hopp", pkg.getFullName());
		
		/*
		 * check that non-existent packages are not found
		 * there is a slight posability that below tests break
		 * if the packages it tries to not found are added to
		 * the Area51 modelica project. even if the changes is one in
		 * a million, still, beware !
		 */
		
		pkg = project.getPackage("non_existing_package");
		assertNull("unexpectedly found a supposedly non-existent package", pkg);

		pkg = project.getPackage("non_existing_package.hej");
		assertNull("unexpectedly found a supposedly non-existent package", pkg);
		
		pkg = project.getPackage("non_existing_package.hej.hopp");
		assertNull("unexpectedly found a supposedly non-existent package", pkg);

		pkg = project.getPackage("childless_package.a_child");
		assertNull("unexpectedly found a supposedly non-existent package", pkg);

		pkg = project.getPackage("");
		assertNull("unexpectedly found a supposedly non-existent package", pkg);
		
		pkg = project.getPackage("root_package.sub_package.leaf_package.meep");
		assertNull("unexpectedly found a supposedly non-existent package", pkg);

	}
	
	
	/**
	 * test (I)ModelciaProject.getRootPackages() method
	 */
	public void testGetRootPackages() 
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException, CoreException
	{

		for (IModelicaClass pkg : project.getRootPackages())
		{
			assertEquals("non root package found", "", pkg.getPrefix());
			assertEquals("non package returned", IModelicaClass.Type.PACKAGE,
					pkg.getRestrictionType());
			expectedRootPackages.remove(pkg.getElementName());
		}
		
		assertTrue("could not find all expected root packages in the project",
				expectedRootPackages.isEmpty());

	}
	
	/**
	 * test (I)ModelciaProject.findElement() method
	 */
	public void testFindElement()
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException, InvocationError, CoreException
	{
		IModelicaElement element =
			project.findElement(new Path("empty_folder"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFolder);
		
		element = project.findElement(new Path("package_look_alike"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFolder);
		
		element = 
			project.findElement(new Path("package_look_alike/package.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);
		
		element = project.findElement(new Path("packages_folder"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFolder);

		element = 
			project.findElement(new Path("packages_folder/folder_package"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaClass);
		assertEquals(IModelicaClass.Type.PACKAGE, 
				((IModelicaClass)element).getRestrictionType());

		element = 
			project.findElement(new Path("packages_folder/folder_package/" +
					"package.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);
		
		element = 
			project.findElement(new Path("packages_folder/file_package.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		/* file_package1 should not be found by this path */
		element = 
			project.findElement(new Path("packages_folder/file_package.mo/" +
					"file_package1"));
		assertNull(element);
		
		/* file_package2 should not be found by this path */
		element = 
			project.findElement(new Path("packages_folder/file_package.mo/" +
					"file_package2"));
		assertNull(element);
		

		element = project.findElement(new Path("root_folder"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFolder);
		
		element = project.findElement(new Path("root_folder/hej_hopp"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFile);
		
		element = project.findElement(new Path("childless_package"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaClass);
		assertEquals(IModelicaClass.Type.PACKAGE, 
				((IModelicaClass)element).getRestrictionType());
		
		element = project.findElement(new Path("childless_package/package.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		element = project.findElement(new Path("root_package/"+ 
				"root_package_folder"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFolder);
		
		element = project.findElement(new Path("root_package/sub_package"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaClass);
		assertEquals(IModelicaClass.Type.PACKAGE, 
				((IModelicaClass)element).getRestrictionType());

		element = project.findElement(new Path("root_package/sub_package/" +
				"leaf_package"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaClass);
		assertEquals(IModelicaClass.Type.PACKAGE, 
				((IModelicaClass)element).getRestrictionType());
		
		element = project.findElement(new Path("root_package/sub_package/" +
				"leaf_package/package.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		element = project.findElement(new Path("root_package/package.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		element = project.findElement(new Path("root_package/" +
				"root_package_function.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		element = project.findElement(new Path("component_model.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		element = project.findElement(new Path("nested_models.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		element = project.findElement(new Path("root_model.mo"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaSourceFile);

		element = project.findElement(new Path("empty_file"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFile);		
		
		element = project.findElement(new Path("README.txt"));
		assertNotNull(element);
		assertTrue(element instanceof IModelicaFile);
		
		/*
		 * check what happens when trying to find non-existing elements
		 * 
		 * BEWARE, the test below can break if elements search for are added
		 * to the area51 modelica project
		 */
		
		element = project.findElement(new Path("non_existing_file"));
		assertNull(element);

		element = project.findElement(new Path("non_existing_folder/" +
				"package.mo"));		
		assertNull(element);
		
		element = project.findElement(new Path("non_existing_folder/" +
			"non_existing_subfolder"));
		assertNull(element);
		
		element = project.findElement(new Path("non_existing_folder/" +
			"non_existing_subfolder/package.mo"));
		assertNull(element);

		element = project.findElement(new Path("a/lot/of/slashes/in/a/path/"));
		assertNull(element);
		
		/* empty string path */
		element = project.findElement(new Path(""));
		assertNull(element);
		
		/* project name */
		element = 
			project.findElement(new Path(Area51Projects.MODELICA_PROJECT_NAME));
		assertNull(element);
	}
}
