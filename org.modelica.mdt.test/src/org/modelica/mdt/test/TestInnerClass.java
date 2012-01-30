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

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ISourceRegion;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.InnerClass;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * test org.modelica.mdt.core.compiler.InnerClass class' code
 */
public class TestInnerClass {
	/* the file on which we make the changing locations test */
	private static final String CHANGING_FILE = "changing_file.mo";

	/* the test subject */
	private InnerClass componentsBananza;
	private InnerClass importRichModel;

	private IModelicaProject proj;

	@org.junit.Before
	public void setUp()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		Area51Projects.createProjects();

		proj = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);

		IModelicaFolder rootFolder = proj.getRootFolder();

		/* navigate to the model 'component_bananza' */
		{
			IModelicaSourceFile file = Utility.findModelicaFileInFolder(rootFolder, "component_model.mo");
			Collection<? extends IModelicaElement> children = file.getChildren();
			for (IModelicaElement el : children) {
				if (el instanceof InnerClass) {
					String elementName = el.getElementName();

					if (elementName.equals("components_bananza")) {
						componentsBananza = (InnerClass)el;
						break;
					}
				}
			}

			assertNotNull("Could not find the model components_bananza", componentsBananza);
		}

		/* navigate to the model 'import_rich_model' */
		{
			IModelicaSourceFile file = Utility.findModelicaFileInFolder(rootFolder, "import_rich_model.mo");
			Collection<? extends IModelicaElement> children = file.getChildren();
			for (IModelicaElement el : children) {
				if (el instanceof InnerClass) {
					String elementName = el.getElementName();

					if (elementName.equals("import_rich_model")) {
						importRichModel = (InnerClass)el;
						break;
					}
				}
			}

			assertNotNull("could not find the model import_rich_model", importRichModel);
		}
	}

	/**
	 * do some sanity checks on InnerClass children
	 */
	@org.junit.Test
	public void testChildren()
			throws ConnectException, UnexpectedReplyException, 	InvocationError, CoreException, CompilerInstantiationException, BundleException {
		/*
		 * we want to check if we can convert modelica elements to workbench
		 * object and get correct label and some image
		 * the adapter factory that handles modelica elements object
		 * is installed by the org.modelica.mdt.ui plugin,
		 * make sure it is loaded before running tests
		 */
		Bundle bundle = Platform.getBundle("org.modelica.mdt.ui");
		bundle.start();

		IModelicaComponent a_real = null;
		IModelicaComponent an_undocumented_real = null;
		IModelicaComponent a_protected_real = null;
		IModelicaComponent a_protected_integer = null;
		IModelicaClass a_package = null;
		IModelicaClass a_class = null;
		IModelicaClass a_model = null;
		IModelicaClass a_connector = null;
		IModelicaClass a_record = null;
		IModelicaClass a_block = null;
		IModelicaClass a_type = null;
		IModelicaClass a_function = null;

		/*
		 * fetch children to local variables
		 * so we can perform checks on them
		 */
		for (IModelicaElement elm : componentsBananza.getChildren()) {
			if (elm instanceof IModelicaComponent) {
				IModelicaComponent component = (IModelicaComponent)elm;
				String elementName = component.getElementName();

				if (elementName.endsWith("a_real")) {
					a_real = component;
				}
				else if (elementName.endsWith("an_undocumented_real")) {
					an_undocumented_real = component;
				}
				else if (elementName.endsWith("a_protected_real")) {
					a_protected_real = component;
				}
				else if (elementName.endsWith("a_protected_integer")) {
					a_protected_integer = component;
				}
			}
			else if (elm instanceof IModelicaClass) {
				IModelicaClass cls = ((IModelicaClass)elm);
				IModelicaClass.Restriction restriction = cls.getRestriction();

				switch (restriction) {
				case PACKAGE:
					a_package = cls;
					break;
				case BLOCK:
					a_block = cls;
					break;
				case CLASS:
					a_class = cls;
					break;
				case CONNECTOR:
					a_connector = cls;
					break;
				case FUNCTION:
					a_function = cls;
					break;
				case MODEL:
					a_model = cls;
					break;
				case RECORD:
					a_record = cls;
					break;
				case TYPE:
					a_type = cls;
					break;
				}
			}
		}

		testChild(a_type, IModelicaClass.class, "a_type", "components_bananza.a_type", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 11, 5, 11, 27, "a_type");

		testChild(a_package, IModelicaClass.class, "a_package", "components_bananza.a_package", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 4, 5, 4, 36, "a_package");

		testChild(a_block, IModelicaClass.class, "a_block", "components_bananza.a_block", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 9, 5, 9, 30, "a_block");

		testChild(a_class, IModelicaClass.class, "a_class", "components_bananza.a_class", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 5, 5, 5, 30, "a_class");

		testChild(a_connector, IModelicaClass.class, "a_connector", "components_bananza.a_connector", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 7, 5, 7, 42, "a_connector");

		testChild(a_function, IModelicaClass.class, "a_function", "components_bananza.a_function", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 10, 5, 10, 39, "a_function");

		testChild(a_model, IModelicaClass.class, "a_model", "components_bananza.a_model", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 6, 5, 6, 30, "a_model");

		testChild(a_record, IModelicaClass.class, "a_record", "components_bananza.a_record", "component_model.mo",
				IModelicaComponent.Visibility.NONE, 8, 5, 8, 33, "a_record");

		testChild(a_real, IModelicaComponent.class, "a_real", "components_bananza.a_real", "component_model.mo",
				IModelicaComponent.Visibility.PUBLIC, 2, 5, 2, 34, "a_real");

		testChild(an_undocumented_real, IModelicaComponent.class, "an_undocumented_real", "components_bananza.an_undocumented_real",
				"component_model.mo", IModelicaComponent.Visibility.PUBLIC, 3, 5, 3, 30, "an_undocumented_real");

		testChild(a_protected_integer, IModelicaComponent.class, "a_protected_integer", "components_bananza.a_protected_integer",
				"component_model.mo", IModelicaComponent.Visibility.PROTECTED, 14, 5, 14, 49, "a_protected_integer");

		testChild(a_protected_real, IModelicaComponent.class, "a_protected_real", "components_bananza.a_protected_real",
				"component_model.mo", IModelicaComponent.Visibility.PROTECTED, 13, 5, 13, 26, "a_protected_real");
	}

	/**
	 * do some sanity checks on InnerClass' imports
	 */
	@org.junit.Test
	public void testImports()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		Collection<IModelicaImport> imports = importRichModel.getImports();

		/*
		 * we are expecting 8 import statements in following order:
		 * 1. qualified                 (import Modelica)
		 * 2. qualified                 (import Modelica.Math.sin)
		 * 3. unqualified               (import Modelica.*)
		 * 4. renaming                  (import mm = Modelica.Math)
		 * 5. local renaming            (import foo = hepp)
		 * 6. local qualified           (import hepp.hopp)
		 * 7. local unqualified         (import hepp.*)
		 * 8. local single definition   (import root_package.root_package_model)
		 */

		int actualNumImports = imports.size();
		int expectedNumImports = 8;

		assertEquals("Unexpected number of imports", actualNumImports, expectedNumImports);

		IModelicaImport[] importsArray = imports.toArray(new IModelicaImport[0]);

		testImport(importsArray[0], IModelicaImport.Type.QUALIFIED, "Modelica", null);
		testImport(importsArray[1], IModelicaImport.Type.QUALIFIED, "Modelica.Math.sin", null);
		testImport(importsArray[2], IModelicaImport.Type.UNQUALIFIED, "Modelica", null);
		testImport(importsArray[3], IModelicaImport.Type.RENAMING, "Modelica.Math", "mm");
		testImport(importsArray[4], IModelicaImport.Type.RENAMING, "hepp", "foo");
		testImport(importsArray[5], IModelicaImport.Type.QUALIFIED, "hepp.hopp", null);
		testImport(importsArray[6], IModelicaImport.Type.UNQUALIFIED, "hepp", null);
		testImport(importsArray[7], IModelicaImport.Type.QUALIFIED, "root_package.root_package_model", null);
	}

	@org.junit.Test
	public void testIsEncapsulated()
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException, InvocationError, CoreException {
		IModelicaClass clazz;
		IStandardLibrary stdLib =
				ModelicaCore.getModelicaRoot().getStandardLibrary(null);

		/*
		 * check correctness of isEncapsulated() in classes from standard library
		 */
		clazz = stdLib.getPackage("Modelica.Blocks.Math.UnitConversions");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());

		clazz = stdLib.getPackage("Modelica.Math.acos");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());

		clazz = stdLib.getPackage("Modelica.Electrical.Analog.Examples.ChuaCircuit");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());

		clazz = stdLib.getPackage("Modelica.Mechanics.Translational.Components.MassWithStopAndFriction.PartialFrictionWithStop");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());

		clazz = stdLib.getPackage("Modelica");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());

		/*
		 * check correctness of isEncapsulated() on classes from area51 project
		 */

		/* test inner packages */
		clazz = proj.getClass("root_model");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());

		clazz = proj.getClass("file_package1");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());

		clazz = proj.getClass("file_package2");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());

		clazz = proj.getClass("nested_models.foo.bar");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());

		/* test folder packages */
		clazz = proj.getClass("root_package");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());

		clazz = proj.getClass("root_package.sub_package");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());

		clazz = proj.getClass("root_package.sub_package.leaf_package");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());

		clazz = proj.getClass("childless_package");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());
	}

	/**
	 * test that the when a class definition is changed in the disk
	 * its location is updated
	 */
	@org.junit.Test
	public void testLocationUpdates()
			throws CoreException, ConnectException, CompilerInstantiationException, UnexpectedReplyException, InvocationError {
		// Create initial class definitions.
		createClassDef();

		// Check some initial locations.
		testLocation("EquationComponent", 6, 1, 13, 22);
		testLocation("EquationComponent.R", 7, 3, 9, 8);

		// Modify class definitions.
		modifyClassDef();

		// Check that locations where updated.
		testLocation("EquationComponent", 2, 1, 11, 22);
		testLocation("EquationComponent.R", 4, 3, 6, 8);
	}

	private void testChild(IModelicaElement element, Class<?> expectedClass, String elementName, String fullName, String pathEndsWith,
			IModelicaComponent.Visibility visibility, int startLine, int startCol, int endLine, int endCol, String label)
					throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		assertNotNull("The element with the expected full name \"" + fullName + "\" was not found.", element);

		Class<?> actualClass = element.getClass();
		String expectedClassName = expectedClass.getCanonicalName();
		String actualClassName = actualClass.getCanonicalName();
		String errorClass = "The actual class \"" + actualClassName + "\" is not an instance of the expected class \"" + expectedClassName + "\"."; 

		assertTrue(errorClass, expectedClass.isAssignableFrom(actualClass));

		String actualElementName = element.getElementName();
		String actualFullName = element.getFullName();

		IModelicaComponent.Visibility actualVisibility = element.getVisibility();

		String filePath = element.getFilePath();
		boolean endsWith = filePath.endsWith(pathEndsWith);
		String errorPathEndsWith = "getFilePath() returned a path \"" +
				filePath + "\" that doesn't end with the expected \"" + pathEndsWith + "\".";

		IDefinitionLocation definitionLocation = element.getLocation();
		ISourceRegion sourceRegion = definitionLocation.getSourceRegion();

		int actualStartLine = sourceRegion.getStartLine();
		int actualStartCol = sourceRegion.getStartColumn();
		int actualEndLine = sourceRegion.getEndLine();
		int actualEndCol = sourceRegion.getEndColumn();

		assertTrue("The IModelicaElement is not an instance of IAdaptable", element instanceof IAdaptable);

		IWorkbenchAdapter adapter = (IWorkbenchAdapter)element.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("getAdapter() returned null.", adapter);

		String actualLabel = adapter.getLabel(element);
		ImageDescriptor imageDescriptor = adapter.getImageDescriptor(element);

		assertEquals("getElementName() did not return the expected value.", elementName, actualElementName);
		assertEquals("getFullName() did not return the expected value.", fullName, actualFullName);
		assertTrue(errorPathEndsWith, endsWith);
		assertEquals("getVisibility() did not return the expected value.", visibility, actualVisibility);
		assertEquals("Expected start line did not match actual start line.", startLine, actualStartLine);
		assertEquals("Expected start column did not match actual start column.", startCol, actualStartCol);
		assertEquals("Expected end line did not match actual end line.", endLine, actualEndLine);
		assertEquals("Expected end column did not match actual end column.", endCol, actualEndCol);
		assertEquals("getLabel() did not return the expected value.", label, actualLabel);
		assertNotNull("getImageDescriptor() returned null.", imageDescriptor);
	}

	private void testImport(IModelicaImport modelicaImport, IModelicaImport.Type importType, String importedPackageName, String alias)
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException, InvocationError, CoreException {
		IModelicaImport.Type actualImportType = modelicaImport.getType();

		assertEquals("IModelicaImport.Type mis-match.", importType, actualImportType);

		IModelicaClass importedPackage = modelicaImport.getImportedPackage();

		String errorMsg = "importedPackageName was set to \"" + importedPackageName +
				"\" but modelicaImport.getImportedPackage() returned null.";

		assertNotNull(errorMsg, importedPackage);

		String actualImportedPackagename = importedPackage.getFullName();

		assertEquals("The expected imported package name does not match the actual one.", importedPackageName, actualImportedPackagename);

		String actualAlias = modelicaImport.getAlias();

		assertEquals("The expected alias does not match the actual one.", alias, actualAlias);
	}

	private void testLocation(String className, int startLine, int startCol, int endLine, int endCol)
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException, InvocationError, CoreException {
		IModelicaClass cls = proj.getClass(className);

		assertNotNull("IModelicaProject.getClass() returned null for \"" + className + "\".", cls);

		IDefinitionLocation definitionLocation = cls.getLocation();
		ISourceRegion sourceRegion = definitionLocation.getSourceRegion();

		int actualStartLine = sourceRegion.getStartLine();
		int actualStartCol = sourceRegion.getStartColumn();
		int actualEndLine = sourceRegion.getEndLine();
		int actualEndCol = sourceRegion.getEndColumn();

		assertEquals("For className \"" + className + "\": Expected start line did not match actual start line.", startLine, actualStartLine);
		assertEquals("For className \"" + className + "\": Expected start column did not match actual start column.", startCol, actualStartCol);
		assertEquals("For className \"" + className + "\": Expected end line did not match actual end line.", endLine, actualEndLine);
		assertEquals("For className \"" + className + "\": Expected end column did not match actual end column.", endCol, actualEndCol);
	}

	/**
	 * utility function for the testLocationUpdates test
	 *
	 * Creates the initial definitions in a source file
	 */
	private void createClassDef() throws CoreException {
		IProject project = proj.getWrappedProject();
		IFile file = project.getFile(CHANGING_FILE);

		InputStream stream = TestInnerClass.class.getResourceAsStream("/org/modelica/mdt/test/modelicafiles/TestInnerClass_changing_file_before.mo");
		file.create(stream, true, null);
	}

	/**
	 * utility function for the testLocationUpdates test
	 *
	 * overwrites the initial definitions in a source file with
	 * some modifications
	 */
	private void modifyClassDef() throws CoreException {
		IProject project = proj.getWrappedProject();
		IFile file = project.getFile(CHANGING_FILE);

		InputStream stream = TestInnerClass.class.getResourceAsStream("/org/modelica/mdt/test/modelicafiles/TestInnerClass_changing_file_after.mo");
		file.setContents(stream, IFile.FORCE, null);
	}
}
