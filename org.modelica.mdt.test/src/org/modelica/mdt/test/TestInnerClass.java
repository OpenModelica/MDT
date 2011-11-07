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

import java.io.InputStream;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
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

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.core.compiler.InnerClass class' code
 */
public class TestInnerClass extends TestCase {
	/* the file on which we make the changing locations test */
	private static final String CHANGING_FILE = "changing_file.mo";

	/* the test subject */
	private InnerClass componentsBananza;
	private InnerClass importRichModel;

	private IModelicaProject proj;

	@Override
	protected void setUp() throws Exception {
		Area51Projects.createProjects();

		/* navigate to the model 'component_bananza' */
		proj = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);

		IModelicaSourceFile file =
				Utility.findModelicaFileInFolder(proj.getRootFolder(),
						"component_model.mo");

		componentsBananza = null;
		for (IModelicaElement el : file.getChildren()) {
			if (el instanceof InnerClass) {
				String elementName = el.getElementName();

				if (elementName.equals("components_bananza")) {
					componentsBananza = (InnerClass)el;
					break;
				}
			}
		}

		/* navigate to the model 'import_rich_model' */
		file = Utility.findModelicaFileInFolder(proj.getRootFolder(),
				"import_rich_model.mo");

		importRichModel = null;
		for (IModelicaElement el : file.getChildren()) {
			if (el instanceof InnerClass) {
				String elementName = el.getElementName();

				if (elementName.equals("import_rich_model")) {
					importRichModel = (InnerClass)el;
					break;
				}
			}
		}

		assertNotNull("could not find the model import_rich_model",
				importRichModel);
	}

	/**
	 * do some sanity checks on InnerClass children
	 */
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

		/*
		 * sanity checks on components_bananza.a_type
		 */
		assertNotNull("components_bananza.a_type not found", a_type);
		assertEquals("wrong element name", "a_type", a_type.getElementName());
		assertEquals("wrong element full name",
				"components_bananza.a_type", a_type.getFullName());
		assertTrue("fishy file path",
				a_type.getFilePath().endsWith("component_model.mo"));
		IRegion reg = a_type.getLocation().getRegion();
		assertEquals("wrong start offset", 342, reg.getOffset());
		assertEquals("wrong length", 364-342+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_type instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		IWorkbenchAdapter wbAdapter =
				(IWorkbenchAdapter)a_type.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_type",
				wbAdapter.getLabel(a_type));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_type));

		/*
		 * sanity checks on components_bananza.a_package
		 */
		assertNotNull("components_bananza.a_package not found", a_package);
		assertEquals("wrong element name", "a_package",
				a_package.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_package",
				a_package.getFullName());
		assertTrue("fishy file path",
				a_package.getFilePath().endsWith("component_model.mo"));
		reg = a_package.getLocation().getRegion();
		assertEquals("wrong start offset", 95, reg.getOffset());
		assertEquals("wrong length", 126-95+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_package instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_package.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		wbAdapter.getLabel(a_package);
		assertEquals("wrong label", "a_package",
				wbAdapter.getLabel(a_package));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_package));

		/*
		 * sanity checks on components_bananza.a_block
		 */
		assertNotNull("components_bananza.a_block not found", a_block);
		assertEquals("wrong element name", "a_block",
				a_block.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_block",
				a_block.getFullName());
		assertTrue("fishy file path",
				a_block.getFilePath().endsWith("component_model.mo"));
		reg = a_block.getLocation().getRegion();
		assertEquals("wrong start offset", 271, reg.getOffset());
		assertEquals("wrong length", 296-271+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_block instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_block.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_block",
				wbAdapter.getLabel(a_block));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_block));

		/*
		 * sanity checks on components_bananza.a_class
		 */
		assertNotNull("components_bananza.a_class not found", a_class);
		assertEquals("wrong element name", "a_class", a_class.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_class",
				a_class.getFullName());
		assertTrue("fishy file path",
				a_class.getFilePath().endsWith("component_model.mo"));
		reg = a_class.getLocation().getRegion();
		assertEquals("wrong start offset", 132, reg.getOffset());
		assertEquals("wrong length", 157-132+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_class instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_class.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_class",
				wbAdapter.getLabel(a_class));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_class));

		/*
		 * sanity checks on components_bananza.a_connector
		 */
		assertNotNull("components_bananza.a_connector not found", a_connector);
		assertEquals("wrong element name", "a_connector",
				a_connector.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_connector",
				a_connector.getFullName());
		assertTrue("fishy file path",
				a_connector.getFilePath().endsWith("component_model.mo"));
		reg = a_connector.getLocation().getRegion();
		assertEquals("wrong start offset", 194, reg.getOffset());
		assertEquals("wrong length", 231-194+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_connector instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_connector.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_connector",
				wbAdapter.getLabel(a_connector));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_connector));

		/*
		 * sanity checks on components_bananza.a_function
		 */
		assertNotNull("components_bananza.a_function not found", a_function);
		assertEquals("wrong element name", "a_function",
				a_function.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_function",
				a_function.getFullName());
		assertTrue("fishy file path",
				a_function.getFilePath().endsWith("component_model.mo"));
		reg = a_function.getLocation().getRegion();
		assertEquals("wrong start offset", 302, reg.getOffset());
		assertEquals("wrong length", 336-302+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_function instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_function.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_function",
				wbAdapter.getLabel(a_function));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_function));

		/*
		 * sanity checks on components_bananza.a_model
		 */
		assertNotNull("components_bananza.a_model not found", a_model);
		assertEquals("wrong element name", "a_model", a_model.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_model",
				a_model.getFullName());
		assertTrue("fishy file path",
				a_model.getFilePath().endsWith("component_model.mo"));
		reg = a_model.getLocation().getRegion();
		assertEquals("wrong start offset", 163, reg.getOffset());
		assertEquals("wrong length", 188-163+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_model instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_model.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_model",
				wbAdapter.getLabel(a_model));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_model));

		/*
		 * sanity checks on components_bananza.a_record
		 */
		assertNotNull("components_bananza.a_record not found", a_record);
		assertEquals("wrong element name", "a_record", a_record.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_record",
				a_record.getFullName());
		assertTrue("fishy file path",
				a_record.getFilePath().endsWith("component_model.mo"));
		reg = a_record.getLocation().getRegion();
		assertEquals("wrong start offset", 237, reg.getOffset());
		assertEquals("wrong length", 265-237+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_record instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_record.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_record",
				wbAdapter.getLabel(a_record));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_record));

		/*
		 * sanity checks on components_bananza.a_real
		 */
		assertNotNull("components_bananza.a_real not found", a_real);
		assertEquals("wrong element name", "a_real", a_real.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_real",
				a_real.getFullName());
		assertEquals("wrong visibility", a_real.getVisibility(),
				IModelicaComponent.Visibility.PUBLIC);

		reg = a_real.getLocation().getRegion();
		assertEquals("wrong start offset", 29, reg.getOffset());
		assertEquals("wrong length", 58-29+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_real instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_real.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_real",
				wbAdapter.getLabel(a_real));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_real));

		/*
		 * sanity checks on components_bananza.a_undocumented_real
		 */
		assertNotNull("components_bananza.an_undocumented_real not found",
				an_undocumented_real);
		assertEquals("wrong element name", "an_undocumented_real",
				an_undocumented_real.getElementName());
		assertEquals("wrong element full name",
				"components_bananza.an_undocumented_real",
				an_undocumented_real.getFullName());
		assertEquals("wrong visibility", an_undocumented_real.getVisibility(),
				IModelicaComponent.Visibility.PUBLIC);
		reg = an_undocumented_real.getLocation().getRegion();
		assertEquals("wrong start offset", 64, reg.getOffset());
		assertEquals("wrong length", 89-64+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(an_undocumented_real instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =
				(IWorkbenchAdapter)an_undocumented_real.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "an_undocumented_real",
				wbAdapter.getLabel(an_undocumented_real));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(an_undocumented_real));

		/*
		 * sanity checks on components_bananza.a_protected_integer
		 */
		assertNotNull("components_bananza.a_protected_integer not found",
				a_protected_integer);
		assertEquals("wrong element name", "a_protected_integer",
				a_protected_integer.getElementName());
		assertEquals("wrong element full name",
				"components_bananza.a_protected_integer",
				a_protected_integer.getFullName());
		assertEquals("wrong visibility", a_protected_integer.getVisibility(),
				IModelicaComponent.Visibility.PROTECTED);
		reg = a_protected_integer.getLocation().getRegion();
		assertEquals("wrong start offset", 407, reg.getOffset());
		assertEquals("wrong length", 451-407+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_protected_integer instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =	(IWorkbenchAdapter)a_protected_integer.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_protected_integer",
				wbAdapter.getLabel(a_protected_integer));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_protected_integer));

		/*
		 * sanity checks on components_bananza.a_protected_real
		 */
		assertNotNull("components_bananza.a_protected_real not found",
				a_protected_real);
		assertEquals("wrong element name", "a_protected_real",
				a_protected_real.getElementName());
		assertEquals("wrong element full name",
				"components_bananza.a_protected_real",
				a_protected_real.getFullName());
		assertEquals("wrong visibility", a_protected_real.getVisibility(),
				IModelicaComponent.Visibility.PROTECTED);
		reg = a_protected_real.getLocation().getRegion();
		assertEquals("wrong start offset", 380, reg.getOffset());
		assertEquals("wrong length", 401-380+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_protected_real instanceof IAdaptable));

		/* check if we can convert element to workbench adapter */
		wbAdapter =
				(IWorkbenchAdapter)a_protected_real.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "a_protected_real",
				wbAdapter.getLabel(a_protected_real));
		assertNotNull("no image provided",
				wbAdapter.getImageDescriptor(a_protected_real));
	}

	/**
	 * do some sanity checks on InnerClass' imports
	 */
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

		testImport(imports.toArray(new IModelicaImport[0])[0], IModelicaImport.Type.QUALIFIED, "Modelica", null);
		testImport(imports.toArray(new IModelicaImport[0])[1], IModelicaImport.Type.QUALIFIED, "Modelica.Math.sin", null);
		testImport(imports.toArray(new IModelicaImport[0])[2], IModelicaImport.Type.UNQUALIFIED, "Modelica", null);
		testImport(imports.toArray(new IModelicaImport[0])[3], IModelicaImport.Type.RENAMING, "Modelica.Math", "mm");
		testImport(imports.toArray(new IModelicaImport[0])[4], IModelicaImport.Type.RENAMING, "hepp", "foo");
		testImport(imports.toArray(new IModelicaImport[0])[5], IModelicaImport.Type.QUALIFIED, "hepp.hopp", null);
		testImport(imports.toArray(new IModelicaImport[0])[6], IModelicaImport.Type.UNQUALIFIED, "hepp", null);
		testImport(imports.toArray(new IModelicaImport[0])[7], IModelicaImport.Type.QUALIFIED, "root_package.root_package_model", null);
	}

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
	public void testLocationUpdates()
			throws CoreException, ConnectException, CompilerInstantiationException, UnexpectedReplyException, InvocationError {
		/* Create initial class definitions. */
		createClassDef();

		/* Check some initial locations. */
		testLocation("EquationComponent", 6, 1, 13, 22);
		testLocation("EquationComponent.R", 7, 3, 9, 8);

		/* Modify class definitions. */
		modifyClassDef();

		/* Check that locations where updated. */
		testLocation("EquationComponent", 2, 1, 11, 22);
		Utility.printFile(proj, TestInnerClass.CHANGING_FILE);
		testLocation("EquationComponent.R", 4, 3, 6, 8);
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

		assertEquals("Expected start line did not match actual start line.", startLine, actualStartLine);
		assertEquals("Expected start column did not match actual start column.", startCol, actualStartCol);
		assertEquals("Expected end line did not match actual end line.", endLine, actualEndLine);
		assertEquals("Expected end column did not match actual end column.", endCol, actualEndCol);
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
