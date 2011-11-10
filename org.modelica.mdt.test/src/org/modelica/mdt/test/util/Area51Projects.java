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

package org.modelica.mdt.test.util;

import java.io.InputStream;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.ModelicaCore;

/**
 * This class allows to create a sample projects that are
 * populated with various elements useful in test cases.
 * Use this projects when you need to make test on a generic modelica project 
 * or a simple project.
 * 
 * Method createProject() creates lazily one instance of a project and
 * returns it. Only one instance of Area51Project is created. 
 * 
 * Following modelica project is created:
 * 
 *'MODELICA_PROJECT_NAME'
 * + .project
 * + empty_file
 * + README.txt
 * + root_model.mo
 * + nested_models.mo
 * + component_model.mo
 * + import_rich_model.mo
 * + root_folder                 (folder)
 *   + hej_hopp
 * + package_look_alike          (folder)
 *   + package.mo                (an empty file)
 * + empty_folder                (folder)  
 * + packages_folder             (folder)
 *   + folder_package            (folder)
 *     + package.mo
 *   + file_package.mo 
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
 *'SIMPLE_PROJECT_NAME'
 * + .project
 * + plain_file
 * + root_folder                (folder)
 *   + sub_folder               (folder)
 *   + folder_file
 *  
 * @author Elmir Jagudin
 */

public class Area51Projects {
	public static final String MODELICA_PROJECT_NAME = 
			Area51Projects.class.getName() + "_modelica";

	public static final String SIMPLE_PROJECT_NAME = 
			Area51Projects.class.getName() + "_simple";

	private static IProject modelica_project = null;
	private static IProject simple_project = null;

	/**
	 * Creates and populates the projects if they don't exist already.
	 */
	public static void createProjects() {
		if (modelica_project == null) {
			try {
				createModelicaProject();
			}
			catch (CoreException e) {
				Assert.fail("error while creating modelica project '" +
						MODELICA_PROJECT_NAME + "'\n" +						
						e.getMessage());
			}
		}

		if (simple_project == null) {
			try {
				createSimpleProject();
			}
			catch (CoreException e) {
				Assert.fail("error while creating simple project '" +
						SIMPLE_PROJECT_NAME + "'\n" +
						e.getMessage());
			}
		}
	}

	/**
	 * create modelica project 
	 * @throws CoreException if any error occurs while creating the project
	 */
	private static void createSimpleProject() throws CoreException {
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
		file.create(Utility.getByteStream(""), true, null);

		/* root_folder */
		folder = simple_project.getFolder("root_folder");
		folder.create(false, true, null);		

		/* folder_file */
		file = folder.getFile("folder_file");
		file.create(Utility.getByteStream(""), true, null);

		/* sub_folder */
		folder = folder.getFolder("sub_folder");
		folder.create(false, true, null);
	}

	/**
	 * create modelica project 
	 * @throws CoreException if any error occurs while creating the project
	 */
	private static void createModelicaProject() throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		/*
		 * Turn off automatic builds if it's on.
		 */
		//Utility.setAutobuilding(false);

		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(MODELICA_PROJECT_NAME);
		IModelicaRoot modelicaRoot = ModelicaCore.getModelicaRoot();
		IModelicaProject mproj = modelicaRoot.createProject(project);
		Assert.assertNotNull("failed to create project", mproj);

		modelica_project = mproj.getWrappedProject();

		/*
		 * populate with some packages
		 */
		IFolder folder, sub_folder;
		IFile file;
		String contents;

		/* root_folder */
		folder = modelica_project.getFolder("root_folder");
		folder.create(false, true, null);

		/* root_folder/hej_hopp */
		file = folder.getFile("hej_hopp");
		file.create(Utility.getByteStream("go morron!\n"), true, null);

		/* packages_folder */
		folder = modelica_project.getFolder("packages_folder");
		folder.create(false, true, null);

		/* packages_folder/file_package.mo */
		file = folder.getFile("file_package.mo");
		contents = 
				"package file_package1\n" +
						"    import Modelica;\n" +
						"end file_package1;\n" +
						"\n" +
						"encapsulated package file_package2\n" +
						"    import Modelica;\n" +
						"end file_package2;";

		file.create(Utility.getByteStream(contents), true, null);

		/* packages_folder/folder_package */
		folder = folder.getFolder("folder_package");
		folder.create(false, true, null);

		/* packages_folder/folder_package/package.mo */
		file = folder.getFile("package.mo");
		contents = 
				"package folder_package\n" +
						"    import Modelica;\n" +
						"end folder_package;";
		file.create(Utility.getByteStream(contents), true, null);

		/* package_look_alike */
		folder = modelica_project.getFolder("package_look_alike");
		folder.create(false, true, null);

		/* package_look_alike/package.mo */
		file = folder.getFile("package.mo");
		file.create(Utility.getByteStream(""), true, null);

		/* empty_folder */
		folder = modelica_project.getFolder("empty_folder");
		folder.create(false, true, null);		

		/* empty_file */
		file = modelica_project.getFile("empty_file");
		file.create(Utility.getByteStream(""), true, null);

		/* root_model.mo */
		file = modelica_project.getFile("root_model.mo");
		InputStream is = Area51Projects.class.getResourceAsStream("/org/modelica/mdt/test/modelicafiles/area51/root_model.mo");
		file.create(is, true, null);

		/* nested_models.mo */
		file = modelica_project.getFile("nested_models.mo");
		is = Area51Projects.class.getResourceAsStream("/org/modelica/mdt/test/modelicafiles/area51/nested_models.mo");
		file.create(is, true, null);

		/* broken_nested_models.mo */
		file = modelica_project.getFile("broken_nested_models.mo");
		contents = "model broken_nested_models\n" +
				"    import Modelica;\n" +
				"    record hepp end hepp;\n" +
				"    class foo\n" +
				"        class bar\n"+
				"          Real a;\n"+
				"          b;\n"+
				"        equation\n" +
				"          a = ;\n"+
				"          b = c;\n" +
				"          c := 0;\n" +
				"        end bar;\n" +
				"    algorithm\n"+
				"      a = 0;\n" +
				"      b := 3\n" +
				"      c = 2;\n" +
				"    end foo;\n" +
				"end nested_models;\n" +
				"\n"+
				"model bruuken_muu\n" +
				"Real a;\n" +
				"equation\n"+
				"    x = 10;\n"+
				"end bruuken_muu;\n"+
				"\n"+
				"model foobared\n"+
				"    import Modelica;\n"+
				"end foobared;\n" +
				"\n\n" +
				"record broken_hej\n" +
				"    package ine_paketen\n" +
				"    end ine_paketen;\n" +
				"    class hejhej\n" +
				"        class foo\n" +
				"        end foo;\n" +
				"    end hejhej;\n" +
				"end broken_hej;\n" +
				"\n\n\n" +
				"package broken_hepp\n" +
				"    package hopp\n" +
				"    end hopp;\n" +
				"    class hehehe\n" +
				"    end hehehe;\n" +
				"end broken_hepp;\n";

		/* Version of broken_nested_models.mo without syntax errors: */
        /*
		contents = "model broken_nested_models\n" +
				"    import Modelica;\n" +
				"    record hepp end hepp;\n" +
				"    class foo\n" +
				"        class bar\n" +
				"          Real a;\n" +
				"          Real b;\n" +
				"          Real c;\n" +
				"        equation\n" +
				"          a = 0;\n" +
				"          b = c;\n" +
				"          c = 0;\n" +
				"        end bar;\n" +
				"    algorithm\n" +
				"      a := 0;\n" +
				"      b := 3;\n" +
				"      c := 2;\n" +
				"    end foo;\n" +
				"end broken_nested_models;\n" +
				"\n" +
				"model bruuken_muu\n" +
				"Real a;\n" +
				"equation\n" +
				"    x = 10;\n" +
				"end bruuken_muu;\n" +
				"\n" +
				"model foobared\n" +
				"    import Modelica;\n" +
				"end foobared;\n" +
				"\n\n" +
				"record broken_hej\n" +
				"    package ine_paketen\n" +
				"    end ine_paketen;\n" +
				"    class hejhej\n" +
				"        class foo\n" +
				"        end foo;\n" +
				"    end hejhej;\n" +
				"end broken_hej;\n" +
				"\n\n\n" +
				"package broken_hepp\n" +
				"    package hopp\n" +
				"    end hopp;\n" +
				"    class hehehe\n" +
				"    end hehehe;\n" +
				"end broken_hepp;\n";
        */

		file.create(Utility.getByteStream(contents), true, null);

		/* component_model.mo */
		file = modelica_project.getFile("component_model.mo");
		is = Area51Projects.class.getResourceAsStream("/org/modelica/mdt/test/modelicafiles/area51/component_model.mo");
		file.create(is, true, null);

		/* import_rich_model.mo */
		file = modelica_project.getFile("import_rich_model.mo");
		is = Area51Projects.class.getResourceAsStream("/org/modelica/mdt/test/modelicafiles/area51/import_rich_model.mo");
		file.create(is, true, null);

		/* README.txt */
		file = modelica_project.getFile("README.txt");
		contents = 
				"This is a dummy modelica project to run regression test on.\n" +
						" tr�s bien !";
		file.create(Utility.getByteStream(contents), true, null);

		/* root_package */
		folder = modelica_project.getFolder("root_package");
		folder.create(false, true, null);

		file = folder.getFile("package.mo");
		contents = 
				"package root_package\n" +
						"\n" + 
						"end root_package;\n";
		file.create(Utility.getByteStream(contents), true, null);

		/* plain file inside root_package */
		file = folder.getFile("plain_file");
		file.create(Utility.getByteStream(""), true, null);

		/* a model inside the root_package */
		file = folder.getFile("root_package_model.mo");
		contents = 
				"within root_package;\n" +
						"\n" +
						"model root_package_model\n" +
						"\n" + 
						"end root_package_model;\n";
		file.create(Utility.getByteStream(contents), true, null);

		/* a function inside the root_package */
		file = folder.getFile("root_package_function.mo");
		contents = 
				"function root_package_function\n" +
						"    input Real x;\n" +
						"    output Real y;\n" +
						"algorithm\n" +
						"    y := 1 - x\n" +
						"end root_package_function;\n";
		file.create(Utility.getByteStream(contents), true, null);

		/* a (non-package) folder inside the root_package */
		sub_folder = folder.getFolder("root_package_folder");
		sub_folder.create(false, true, null);

		/* root_package/sub_package */
		folder = folder.getFolder("sub_package");
		folder.create(false, true, null);

		/* root_package/sub_package/package.mo */
		file = folder.getFile("package.mo");
		contents = 
				"within root_package;\n" +
						"\n" +
						"package sub_package\n" +
						"\n" + 
						"end sub_package;\n";
		file.create(Utility.getByteStream(contents), true, null);

		/* root_package/sub_package/sub_package_model.mo */
		file = folder.getFile("sub_package_model.mo");
		contents =
				"within root_package.sub_package;\n" +
						"\n" +
						"model sub_package_model\n" +
						"\n" + 
						"end sub_package_model;\n";
		file.create(Utility.getByteStream(contents), true, null);

		/* root_package/sub_package/leaf_package */
		folder = folder.getFolder("leaf_package");
		folder.create(false, true, null);

		/* root_package/sub_package/leaf_package/package.mo */
		file = folder.getFile("package.mo");
		contents =
				"within root_package.sub_package;\n" +
						"\n" +
						"encapsulated package leaf_package\n" +
						"\n" + 
						"end leaf_package;\n";
		file.create(Utility.getByteStream(contents), true, null);

		/* childless_package */
		folder = modelica_project.getFolder("childless_package");
		folder.create(false, true, null);

		file = folder.getFile("package.mo");
		contents = 
				"encapsulated package childless_package\n" +
						"\n" + 
						"end childless_package;\n";
		file.create(Utility.getByteStream(contents), true, null);
	}
}
