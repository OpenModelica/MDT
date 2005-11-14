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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.core.ModelicaCore;

/**
 * This class allows to create a sample projects that are
 * populated with various elements usefull in test cases.
 * Use this projects when you need to make test on a generic modelica project 
 * or a simple project.
 * 
 * Method createProject() creates lazily one instance of a project and
 * returns it. Only one instance of Area51Project is created. 
 * 
 * Following modelica project is created:
 * 
 * 'MODELICA_PROJECT_NAME'
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
 *  Following simple project (without any natures) is created:
 *  
 *  'SIMPLE_PROJECT_NAME'
 *  + .project
 *  + plain_file
 *  + root_folder                (folder)
 *    + sub_folder               (folder)
 *    + folder_file
 *  
 *  
 * @author Elmir Jagudin
 */

public class Area51Projects 
{
	public static final String MODELICA_PROJECT_NAME = 
		Area51Projects.class.getName() + "_modelica";

	public static final String SIMPLE_PROJECT_NAME = 
		Area51Projects.class.getName() + "_simple";

	private static IProject modelica_project = null;
	private static IProject simple_project = null;
	
	/**
	 * Create and populate the project. Only one instance is created, 
	 * all calls after first one returns without doing anything. 
	 */
	public static void createProjects()
	{
		if (modelica_project == null)
		{
			try 
			{
				createModelicaProject();
			}
			catch (CoreException e) 
			{
				Assert.fail("error while creating modelica project '" +
						MODELICA_PROJECT_NAME + "'\n" +						
						e.getMessage());
			}
		}
		
		if (simple_project == null)
		{
			try 
			{
				createSimpleProject();
			}
			catch (CoreException e) 
			{
				Assert.fail("error while creating simple project '" +
						SIMPLE_PROJECT_NAME + "'\n" +
						e.getMessage());
			}
		}
		

		
	}
	/**
	 * create modelica project 
	 * @throws CoreException if any error occures while creating the project
	 */
	private static void createSimpleProject() throws CoreException
	{
		/*
		 * create the simple project
		 */
		simple_project = 
			ResourcesPlugin.getWorkspace().getRoot()
				.getProject(SIMPLE_PROJECT_NAME);

		simple_project.create(null);
		simple_project.open(null);
		
		/*
		 * create the file tree in the project
		 */
		IFolder folder;
		IFile file;
		
		/* plain file */
		file = simple_project.getFile("plain_file");
		file.create(getByteStream(""), true, null);
		
		/* root_folder */
		folder = simple_project.getFolder("root_folder");
		folder.create(false, true, null);

		/* folder_file */
		file = folder.getFile("folder_file");
		file.create(getByteStream(""), true, null);
		
		/* sub_folder */
		folder = folder.getFolder("sub_folder");
		folder.create(false, true, null);
		

		
	}

	/**
	 * create modelica project 
	 * @throws CoreException if any error occures while creating the project
	 */
	private static void createModelicaProject() throws CoreException
	{
		/*
		 * create the project
		 */
		modelica_project = 
			ModelicaCore.createProject(MODELICA_PROJECT_NAME, 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		Assert.assertNotNull("failed to create project", modelica_project);
		
		/*
		 * populate with some packages
		 */
		IFolder folder, sub_folder;
		IFile file;
		String contents;
			
		/* root_package */
		folder = modelica_project.getFolder("root_package");
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
		folder = modelica_project.getFolder("childless_package");
		folder.create(false, true, null);
			
		file = folder.getFile("package.mo");
		contents = 
			"package childless_package\n" +
			"\n" + 
			"end childless_package;\n";
		file.create(getByteStream(contents), true, null);
	}

	private static InputStream getByteStream(String content) 
	{
		return new ByteArrayInputStream(content.getBytes());
	}	
}

