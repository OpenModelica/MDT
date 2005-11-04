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

package org.modelica.mdt.test.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.core.ModelicaCore;

/**
 * This class allows to create a modelica project that is
 * populated with various elements usefull in test cases.
 * Use this project when you need to make test on a generic modelica project.
 * 
 * Method createProject() creates lazily one instance of a project and
 * returns it. Only one instance of Area51Project is created. 
 * 
 * Following project is created:
 * 
 * root
 * + .project
 * + root_package                (folder)
 *   + package.mo
 *   + plain_file
 *   + root_package_model.mo
 *   + root_package_function.mo
 *   + root_package_folder       (folder)
 *   + sub_package               (folder)
 *     + sub_package_model.mo 
 *     + package.mo 
 *     + leaf_package            (folder)
 *       + package.mo
 * + childless_package           (folder)
 *   + package.mo
 *  
 * @author Elmir Jagudin
 */

public class Area51Project 
{
	public static final String PROJECT_NAME = 
		Area51Project.class.getName();

	private static IProject project = null;
	
	/**
	 * Create and populate the project. Only one instance is created, 
	 * all calls after first one returns without doing anything. 
	 */
	public static void createProject()
	{
		if (project != null)
		{
			/* create only one instance of the project */
			return;
		}
		
		/*
		 * create THE modelica project
		 */
		project = 
			ModelicaCore.createProject(PROJECT_NAME, 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		Assert.assertNotNull("failed to create project", project);
		
		/*
		 * populate with some packages
		 */
		try 
		{
			
			IFolder folder, sub_folder;
			IFile file;
			String contents;
			
			/* root_package */
			folder = project.getFolder("root_package");
			folder.create(false, true, null);
			
			file = folder.getFile("package.mo");
			contents = 
				"package root_package\n" +
				"\n" + 
				"end root_package;\n";
			file.create(getByteStream(contents), true, null);
			
			/* plain file inside root_package */
			file = folder.getFile("plain_file");
			file.create(getByteStream(""), true, null);
			
			/* a model inside the root_package */
			file = folder.getFile("root_package_model.mo");
			contents = 
				"model root_package_model\n" +
				"\n" + 
				"end root_package_model;\n";
			file.create(getByteStream(contents), true, null);

			/* a function inside the root_package */
			file = folder.getFile("root_package_function.mo");
			contents = 
				"function root_package_function\n" +
				"    input Real x;\n" +
				"    output Real y;\n" +
				"algorithm\n" +
				"    y := 1 - x\n" +
				"end root_package_function;\n";
			file.create(getByteStream(contents), true, null);

			/* a (non-package) folder inside the root_package */
			sub_folder = folder.getFolder("root_package_folder");
			sub_folder.create(false, true, null);
			
			/* sub_package */
			folder = folder.getFolder("sub_package");
			folder.create(false, true, null);
			
			file = folder.getFile("package.mo");
			contents = 
				"package sub_package\n" +
				"\n" + 
				"end sub_package;\n";
			file.create(getByteStream(contents), true, null);
			
			/* sub_package_model inside sub_package */
			file = folder.getFile("sub_package_model.mo");
			contents = 
				"model sub_package_model\n" +
				"\n" + 
				"end sub_package_model;\n";
			file.create(getByteStream(contents), true, null);


			/* leaf_package */
			folder = folder.getFolder("leaf_package");
			folder.create(false, true, null);
			
			file = folder.getFile("package.mo");
			contents = 
				"package leaf_package\n" +
				"\n" + 
				"end leaf_package;\n";
			file.create(getByteStream(contents), true, null);
			
			/* childless_root_package */
			folder = project.getFolder("childless_package");
			folder.create(false, true, null);
			
			file = folder.getFile("package.mo");
			contents = 
				"package childless_package\n" +
				"\n" + 
				"end childless_package;\n";
			file.create(getByteStream(contents), true, null);

		} 
		catch (CoreException e) 
		{
			Assert.fail("error populating project " + e.getMessage());
		}

		
	}

	private static InputStream getByteStream(String content) 
	{
		return new ByteArrayInputStream(content.getBytes());
	}
	
	// TODO just toying around, remove me !
	public static void createProblemMarker()
	{
		// THIS is how you do a problem marker
		try
		{
			createProject(); // we need a project to add problem marker to
		
			IFile file = project.getFile("problem_model.mo");
			
			String contents = 
				"model problem_model\n" +
				"\n" + 
				"end problem_model;\n";
	
			file.create(getByteStream(contents), true, null);
			
			IMarker marker = file.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.CHAR_START, 10);
			marker.setAttribute(IMarker.CHAR_END, 15);
			marker.setAttribute(IMarker.MESSAGE, "ojoj");
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.LINE_NUMBER, 3);
			marker.setAttribute(IMarker.LOCATION, "ajsjklasdfsdf");
		}

		catch (Exception e)
		{
			Assert.fail("gick mindre bra");
		}
		
	}
}

