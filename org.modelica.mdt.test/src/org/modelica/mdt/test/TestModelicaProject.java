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

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

/**
 * Test various methods in org.modelica.mdt.internal.core.ModelicaProject
 */
public class TestModelicaProject {
	/* the test subject, Area51 modelica project */
	private IModelicaProject project;

	/* the names of the expected root packages in the area51 modelica project */
	private Vector<String> expectedRootPackages = new Vector<String>();

	@Before
	public void setUp() throws Exception {
		Area51Projects.createProjects();

		project = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);

		assertTrue(Collections.addAll(expectedRootPackages,
				"file_package1",
				"foo",
				"file_package2",
				"root_package",
				"bruuken_muu",
				"foobared",
				"components_bananza",
				"nested_models",
				"childless_package",				
				"import_rich_model",
				"folder_package",				
				"hepp",
				"muu",
				"root_model"));
	}

	/**
	 * test (I)ModelciaProject.getClass() method
	 */
	@Test
	public void testGetClass() 
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException, InvocationError, CoreException, Exception {
		/*
		 * check that find existing packages works
		 */
		testGetClass("folder_package", "folder_package", IModelicaClass.Restriction.PACKAGE);

		testGetClass("file_package1", "file_package1", IModelicaClass.Restriction.PACKAGE);

		testGetClass("file_package2", "file_package2", IModelicaClass.Restriction.PACKAGE);

		testGetClass("childless_package", "childless_package", IModelicaClass.Restriction.PACKAGE);

		testGetClass("root_package", "root_package", IModelicaClass.Restriction.PACKAGE);

		testGetClass("root_package.sub_package", "root_package.sub_package", IModelicaClass.Restriction.PACKAGE);

		testGetClass("root_package.sub_package.leaf_package", "root_package.sub_package.leaf_package", IModelicaClass.Restriction.PACKAGE);

		testGetClass("root_package.root_package_model", "root_package.root_package_model", IModelicaClass.Restriction.MODEL);

		testGetClass("components_bananza", "components_bananza", IModelicaClass.Restriction.MODEL);

		testGetClass("components_bananza.a_package", "components_bananza.a_package", IModelicaClass.Restriction.PACKAGE);

		testGetClass("components_bananza.a_block", "components_bananza.a_block", IModelicaClass.Restriction.BLOCK);

		testGetClass("components_bananza.a_class", "components_bananza.a_class", IModelicaClass.Restriction.CLASS);

		testGetClass("components_bananza.a_type", "components_bananza.a_type", IModelicaClass.Restriction.TYPE);	

		testGetClass("components_bananza.a_connector", "components_bananza.a_connector", IModelicaClass.Restriction.CONNECTOR);

		testGetClass("components_bananza.a_function", "components_bananza.a_function", IModelicaClass.Restriction.FUNCTION);

		testGetClass("components_bananza.a_model", "components_bananza.a_model", IModelicaClass.Restriction.MODEL);

		testGetClass("components_bananza.a_record", "components_bananza.a_record", IModelicaClass.Restriction.RECORD);

		testGetClass("import_rich_model", "import_rich_model", IModelicaClass.Restriction.MODEL);

		testGetClass("import_rich_model.bar", "import_rich_model.bar", IModelicaClass.Restriction.CLASS);

		testGetClass("hepp", "hepp", IModelicaClass.Restriction.PACKAGE);

		testGetClass("hepp.hopp", "hepp.hopp", IModelicaClass.Restriction.PACKAGE);

		testGetClass("hepp.hehehe", "hepp.hehehe", IModelicaClass.Restriction.CLASS);

		testGetClass("nested_models.hepp", "nested_models.hepp", IModelicaClass.Restriction.RECORD);

		testGetClass("broken_nested_models.foo", "broken_nested_models.foo", IModelicaClass.Restriction.CLASS);

		testGetClass("foo", "foo", IModelicaClass.Restriction.MODEL);

		testGetClass("hej.ine_paketen", "hej.ine_paketen", IModelicaClass.Restriction.PACKAGE);

		testGetClass("hej.hejhej", "hej.hejhej", IModelicaClass.Restriction.CLASS);

		testGetClass("muu", "muu", IModelicaClass.Restriction.MODEL);

		testGetClass("nested_models", "nested_models", IModelicaClass.Restriction.MODEL);

		testGetClass("nested_models.foo", "nested_models.foo", IModelicaClass.Restriction.CLASS);

		testGetClass("hej.hejhej.foo", "hej.hejhej.foo", IModelicaClass.Restriction.CLASS);

		testGetClass("nested_models.foo.bar", "nested_models.foo.bar", IModelicaClass.Restriction.CLASS);

		testGetClass("root_model", "root_model", IModelicaClass.Restriction.MODEL);

		testGetClass("broken_nested_models", "broken_nested_models", IModelicaClass.Restriction.MODEL);

		testGetClass("broken_nested_models.hepp", "broken_nested_models.hepp", IModelicaClass.Restriction.RECORD);

		testGetClass("broken_nested_models.foo.bar", "broken_nested_models.foo.bar", IModelicaClass.Restriction.CLASS);

		testGetClass("bruuken_muu", "bruuken_muu", IModelicaClass.Restriction.MODEL);

		testGetClass("foobared", "foobared", IModelicaClass.Restriction.MODEL);

		/*
		 * check that non-existent packages are not found
		 * there is a slight possibility that below tests break
		 * if the packages it tries to not found are added to
		 * the Area51 modelica project. even if the changes is one in
		 * a million, still, beware !
		 */
		testGetClassNonExisting("non_existing_package");

		testGetClassNonExisting("non_existing_package.hej");

		testGetClassNonExisting("non_existing_package.hej.hopp");

		testGetClassNonExisting("childless_package.a_child");

		testGetClassNonExisting("");

		testGetClassNonExisting("root_package.sub_package.leaf_package.meep");
	}

	/**
	 * test (I)ModelciaProject.getRootClasses() method
	 */
	@Test
	public void testGetRootClasses()
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException, CoreException {
		Collection<? extends IModelicaClass> rootClasses = project.getRootClasses();

		for (IModelicaClass cls : rootClasses) {
			String classPrefix = cls.getPrefix();

			assertEquals("non root class found", "", classPrefix);
			String elementName = cls.getElementName();
			boolean wasRemoved = expectedRootPackages.remove(elementName);

			if (wasRemoved) {
				System.out.println("Removed \"" + elementName + "\" from expectedRootPackages.");
			}
			else {
				System.out.println("\"" + elementName + "\" was not present in expectedRootPackages.");
			}
		}

		String errorMsg = "could not find following expeced root classes:";
		for (String cls : expectedRootPackages) {
			errorMsg += " " + cls;
		}

		boolean expectedRootPackagesIsEmpty = expectedRootPackages.isEmpty();

		assertTrue(errorMsg, expectedRootPackagesIsEmpty);
	}

	/**
	 * test (I)ModelciaProject.findElement() method
	 */
	@Test
	public void testFindElement()
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException, InvocationError, CoreException {
		testFindElement("empty_folder", IModelicaFolder.class, null);

		testFindElement("package_look_alike", IModelicaFolder.class, null);

		/* 
		 * FIXME: We are currently not creating the empty file package.mo inside package_look_alike
		 * because it makes OMC to freeze. When this problem has been fixed, we can run this test
		 * again.
		 * However, even if OMC could cope with an empty file, would it be considered an IModeliaSourceFile, as
		 * stated in the original version of this test, or simply an IModelicaFile?
		 */
		//testFindPath("package_look_alike/package.mo", IModelicaSourceFile.class, null);

		testFindElement("packages_folder", IModelicaFolder.class, null);

		testFindElement("packages_folder/folder_package", IModelicaClass.class, IModelicaClass.Restriction.PACKAGE);

		testFindElement("packages_folder/folder_package/package.mo", IModelicaSourceFile.class, null);

		testFindElement("packages_folder/file_package.mo", IModelicaSourceFile.class, null);

		testFindElement("root_folder", IModelicaFolder.class, null);

		testFindElement("root_folder/hej_hopp", IModelicaFile.class, null);

		testFindElement("childless_package", IModelicaClass.class, IModelicaClass.Restriction.PACKAGE);

		testFindElement("childless_package/package.mo", IModelicaSourceFile.class, null);

		testFindElement("root_package/root_package_folder", IModelicaFolder.class, null);

		testFindElement("root_package/sub_package", IModelicaClass.class, IModelicaClass.Restriction.PACKAGE);

		testFindElement("root_package/sub_package/leaf_package", IModelicaClass.class, IModelicaClass.Restriction.PACKAGE);

		testFindElement("root_package/sub_package/leaf_package/package.mo", IModelicaSourceFile.class, null);

		testFindElement("root_package/package.mo", IModelicaSourceFile.class, null);

		testFindElement("root_package/root_package_function.mo", IModelicaSourceFile.class, null);

		testFindElement("component_model.mo", IModelicaSourceFile.class, null);

		testFindElement("nested_models.mo", IModelicaSourceFile.class, null);

		testFindElement("root_model.mo", IModelicaSourceFile.class, null);

		testFindElement("empty_file", IModelicaFile.class, null);	

		testFindElement("README.txt", IModelicaFile.class, null);

		/*
		 * check what happens when trying to find non-existing elements
		 * 
		 * BEWARE, the test below can break if elements search for are added
		 * to the area51 modelica project
		 */
		testFindElementNonExisting("packages_folder/file_package.mo/file_package1");

		testFindElementNonExisting("packages_folder/file_package.mo/file_package2");
		
		testFindElementNonExisting("non_existing_file");

		testFindElementNonExisting("non_existing_folder/package.mo");

		testFindElementNonExisting("non_existing_folder/non_existing_subfolder");

		testFindElementNonExisting("non_existing_folder/non_existing_subfolder/package.mo");

		testFindElementNonExisting("a/lot/of/slashes/in/a/path/");

		/* empty string path */
		testFindElementNonExisting("");

		/* project name */
		testFindElementNonExisting(Area51Projects.MODELICA_PROJECT_NAME);
	}

	private void testGetClass(String className, String expectedFullName, IModelicaClass.Restriction expectedRestriction) throws Exception {
		IModelicaClass pkg = project.getClass(className);

		if (pkg == null) {
			printAll(project.getRootClasses(), 0);
		}

		assertNotNull("project.getClass() returned null for \"" + className + "\"", pkg);

		String actualFullName = pkg.getFullName();

		assertEquals("Expected pkg.getFullName() to return \"" + expectedFullName + "\" for className \"" + className + "\", but got \"" + actualFullName + "\".", expectedFullName, actualFullName);

		IModelicaClass.Restriction actualRestriction = pkg.getRestriction();

		assertEquals("Expected restriction " + expectedRestriction + ", but got " + actualRestriction + ".", expectedRestriction, actualRestriction);
	}

	private void testGetClassNonExisting(String className) throws Exception {
		IModelicaClass pkg = project.getClass(className);

		assertNull("project.getClass() was called with \"" + className + "\", and was expected to return NULL, but didn't.", pkg);
	}
	
	private void testFindElement(String pathName, Class<?> expectedClass, IModelicaClass.Restriction expectedRestriction)
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException, InvocationError, CoreException {
		Path path = new Path(pathName);
		IModelicaElement element = project.findElement(path);
		
		assertNotNull(element);
		
		Class<?> actualClass = element.getClass();
		String expectedClassName = expectedClass.getCanonicalName();
		String actualClassName = actualClass.getCanonicalName();
		String errorMsgClass = "Was expecting " + expectedClassName + ", but got " + actualClassName;
		
		assertTrue(errorMsgClass, expectedClass.isAssignableFrom(actualClass));
		
		if (expectedRestriction != null) {
			assertTrue(element instanceof IModelicaClass);
			IModelicaClass.Restriction actualRestriction = ((IModelicaClass)element).getRestriction();
			
			assertEquals("Expected restriction " + expectedRestriction + ", but got " + actualRestriction + ".", expectedRestriction, actualRestriction);
		}
	}
	
	private void testFindElementNonExisting(String pathName) 
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException, InvocationError, CoreException {
		Path path = new Path(pathName);
		IModelicaElement element = project.findElement(path);
		assertNull(element);
	}

	private void printAll(Collection<? extends IModelicaElement> children, int indent) throws Exception {		
		for (IModelicaElement elem : children) {
			for (int i = 0; i < indent; i++) System.out.print("  ");
			String elementName = elem.getElementName();
			String type = elem.getClass().getSimpleName();

			System.out.println("\"" + elementName + "\" of type \"" + type + "\"");

			if (elem instanceof IModelicaClass) {
				printAll(((IModelicaClass)elem).getChildren(), ++indent);
			}
		}
	}
}
