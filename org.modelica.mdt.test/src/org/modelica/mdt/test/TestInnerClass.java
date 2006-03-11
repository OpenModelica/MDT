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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaProject;
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
public class TestInnerClass extends TestCase 
{

	/* the file on which we make the changing locations test */
	private static final String CHANGING_FILE = "changing_file.mo";
	
	/* the test subject */
	private InnerClass componentsBananza;
	private InnerClass importRichModel;
	
	private IModelicaProject proj;
	
	
	@Override
	protected void setUp() throws Exception 
	{
		Area51Projects.createProjects();
		
		/* navigate to the model 'component_bananza' */
		proj = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		
		IModelicaSourceFile file = 
			Utility.findModelicaFileInFolder(proj.getRootFolder(), 
				"component_model.mo");
		
		componentsBananza = null;
		for (IModelicaElement el : file.getChildren())
		{
			if (el instanceof InnerClass)
			{
				if (el.getElementName().equals("components_bananza"))
				{
					componentsBananza = (InnerClass)el;
					break;
				}
			}
		}


		/* navigate to the model 'import_rich_model' */
		file = Utility.findModelicaFileInFolder(proj.getRootFolder(), 
				"import_rich_model.mo");
		
		importRichModel = null;
		for (IModelicaElement el : file.getChildren())
		{
			if (el instanceof InnerClass)
			{
				if (el.getElementName().equals("import_rich_model"))
				{
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
		throws ConnectException, UnexpectedReplyException,
			InvocationError, CoreException, CompilerInstantiationException,
			BundleException
	{ 
		/*
		 * we want to check if we can convert modelica elemnts to workbench
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
		 * so we can perfort check on 'em
		 */
		for (IModelicaElement elm : componentsBananza.getChildren())
		{
			if (elm instanceof IModelicaComponent)
			{
				IModelicaComponent comp = (IModelicaComponent) elm;
				
				if (comp.getElementName().endsWith("a_real"))
				{
					a_real = comp;
				}
				else if (comp.getElementName().endsWith("an_undocumented_real"))
				{
					an_undocumented_real = comp;
				}
				else if (comp.getElementName().endsWith("a_protected_real"))
				{
					a_protected_real = comp;
				}
				else if (comp.getElementName().endsWith("a_protected_integer"))
				{
					a_protected_integer = comp;
				}
			}
			else if (elm instanceof IModelicaClass)
			{
				switch (((IModelicaClass)elm).getRestrictionType())
				{
				case PACKAGE:
					a_package = (IModelicaClass)elm;
					break;
				case BLOCK:
					a_block = (IModelicaClass)elm;
					break;
				case CLASS:
					a_class = (IModelicaClass)elm;
					break;
				case CONNECTOR:
					a_connector = (IModelicaClass)elm;
					break;
				case FUNCTION:
					a_function = (IModelicaClass)elm;
					break;
				case MODEL:
					a_model = (IModelicaClass)elm;
					break;
				case RECORD:
					a_record = (IModelicaClass)elm;
					break;
				case TYPE:
					a_type = (IModelicaClass)elm;
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
		IRegion reg = a_type.getLocation();
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
		reg = a_package.getLocation();
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
		reg = a_block.getLocation();
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
		reg = a_class.getLocation();
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
		reg = a_connector.getLocation();
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
		reg = a_function.getLocation();
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
		reg = a_model.getLocation();
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
		reg = a_record.getLocation();
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
		assertEquals("wrong visibility", a_real.getVisbility(),
				IModelicaComponent.Visibility.PUBLIC);

		reg = a_real.getLocation();
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
		assertEquals("wrong visibility", an_undocumented_real.getVisbility(),
				IModelicaComponent.Visibility.PUBLIC);
		reg = an_undocumented_real.getLocation();
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
		assertEquals("wrong visibility", a_protected_integer.getVisbility(),
				IModelicaComponent.Visibility.PROTECTED);
		reg = a_protected_integer.getLocation();
		assertEquals("wrong start offset", 407, reg.getOffset());
		assertEquals("wrong length", 451-407+1, reg.getLength());

		/* check if element is adaptable */
		assertTrue("element object must be adaptable",
				(a_protected_integer instanceof IAdaptable));
		
		/* check if we can convert element to workbench adapter */
		wbAdapter =	
			(IWorkbenchAdapter)a_protected_integer.getAdapter(IWorkbenchAdapter.class);

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
		assertEquals("wrong visibility", a_protected_real.getVisbility(),
				IModelicaComponent.Visibility.PROTECTED);
		reg = a_protected_real.getLocation();
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
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{
		int importCounter = 0;
		IModelicaClass importedPackage;
		for (IModelicaImport imp : importRichModel.getImports())
		{
			importCounter++;
			
			/*
			 * we are expecting 8 import statments in following order:
			 * 1. qualified         (import Modelica)
			 * 2. qualified         (import Modelica.Math.sin)
			 * 3. unqualified       (import Modelica.*)
			 * 4. renaming          (import mm = Modelica.Math)
			 * 5. local renaming    (import foo = hepp)
			 * 6. local qualified   (import hepp.hopp)
			 * 7. local unqualified (import hepp.*)
			 * 8. local single definition 
			 *                      (import root_package.root_package_model)
			 */

			switch (importCounter)
			{
			case 1: // import Modelica
				assertEquals(IModelicaImport.Type.QUALIFIED, imp.getType());
				importedPackage = imp.getImportedPackage();
				assertEquals("wrong imported package returned",
						"Modelica",
						importedPackage.getFullName());
				break;
			case 2: // import Modelica.Math.sin
				assertEquals(IModelicaImport.Type.QUALIFIED, imp.getType());
				importedPackage = imp.getImportedPackage();
				assertEquals("wrong imported package returned",
						"Modelica.Math.sin",
						importedPackage.getFullName());
				break;
			case 3: // import Modelica.*
				assertEquals(IModelicaImport.Type.UNQUALIFIED, imp.getType());
				importedPackage = imp.getImportedPackage();
				assertEquals("wrong imported package returned",
						"Modelica",
						importedPackage.getFullName());
				break;
			case 4: // import mm = Modelica.Math
				assertEquals(IModelicaImport.Type.RENAMING, imp.getType());
				assertEquals("mm", imp.getAlias());
				break;
			case 5: // import foo = hepp
				assertEquals(IModelicaImport.Type.RENAMING, imp.getType());
				assertEquals("foo", imp.getAlias());
				break;
			case 6: // import hepp.hopp
				assertEquals(IModelicaImport.Type.QUALIFIED, imp.getType());
				break;
			case 7: // import hepp.*
				assertEquals(IModelicaImport.Type.UNQUALIFIED, imp.getType());
				break;
			case 8: // import root_package.root_package_model
				// TODO this is broken, fix this
//				assertEquals(IModelicaImport.Type.QUALIFIED, imp.getType());
				break;
			default:
				fail("unexpectedly many imports found"); 
			}
		}
		
		assertFalse("did not find all import statments", 
				importCounter < 4);

	}

	public void testIsEncapsulated()
		throws ConnectException, CompilerInstantiationException, UnexpectedReplyException, 
			InvocationError, CoreException
	{
		IModelicaClass clazz;
		IStandardLibrary stdLib = 
				ModelicaCore.getModelicaRoot().getStandardLibrary();
		
		/*
		 * check correctness of isEncapsulated() in classes from standard library
		 */
		clazz = stdLib.getPackage("Modelica.Blocks.Examples.BusUsage");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());
		
		clazz = stdLib.getPackage("Modelica.Math.acos");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());

		clazz = stdLib.getPackage("Modelica.Mechanics.Rotational.Examples.Friction");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());

		clazz = stdLib.getPackage("Modelica.Mechanics.Rotational.Examples.Friction");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());
		
		clazz = stdLib.getPackage("Modelica");
		assertNotNull(clazz);
		assertFalse("wrong encapsulated status", clazz.isEncapsulated());
		
		/*
		 * check correctness of isEncapsulated() on classes from area51 project
		 */
		
		/* test inner packager */
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
//TODO this is broken, fix it		
//		clazz = proj.getClass("root_package.sub_package.leaf_package");
//		assertNotNull(clazz);
//		assertTrue("wrong encapsulated status", clazz.isEncapsulated());
		
		clazz = proj.getClass("childless_package");
		assertNotNull(clazz);
		assertTrue("wrong encapsulated status", clazz.isEncapsulated());
	}

	/** 
	 * test that the when a class definiton is changed in the disk
	 * it's location is updated
	 */
	public void testLocationUpdates() throws CoreException, 
		ConnectException, CompilerInstantiationException, 
			UnexpectedReplyException, InvocationError
	{		
//		
//		/* create initial class definitons */
//		createClassDef();		
//		/* 
//		 * check the initial locations 
//		 */
//		IModelicaClass clazz;
//		IRegion defReg;
//		
//		/* checks on EquationComponent */
//		clazz = proj.getClass("EquationComponent"); 
//		assertNotNull(clazz);
//		
//		defReg = clazz.getLocation();
//		assertEquals("wrong start offset", 111, defReg.getOffset());
//		assertEquals("wrong length", 239-111+1, defReg.getLength());
//
//		/* checks on EquationComponent.R */
//		clazz = proj.getClass("EquationComponent.R"); 
//		assertNotNull(clazz);
//		
//		defReg = clazz.getLocation();
//		assertEquals("wrong start offset", 137, defReg.getOffset());
//		assertEquals("wrong length", 167-137+1, defReg.getLength());
//		
//		
//		/* modify class defintions */
//		modifyClassDef();
//		
//		/*
//		 * check that locations where updated 
//		 */
//		/* checks on EquationComponent */
//		clazz = proj.getClass("EquationComponent"); 
//		assertNotNull(clazz);
//		
//		defReg = clazz.getLocation();
//		assertEquals("wrong start offset", 25, defReg.getOffset());
//		assertEquals("wrong length", 179-25+1, defReg.getLength());
//
//		/* checks on EquationComponent.R */
//		clazz = proj.getClass("EquationComponent.R"); 
//		assertNotNull(clazz);
//		
//		defReg = clazz.getLocation();
//		assertEquals("wrong start offset", 76, defReg.getOffset());
//		assertEquals("wrong length", 106-76+1, defReg.getLength());
//
	}
	
	/**
	 * utility function for the testLocationUpdates test
	 * 
	 * overwrites the initial definitions in a source file with
	 * some modifications
	 */
	private void modifyClassDef() throws CoreException 
	{
		String contents =
			"// funny comment removed\n" +
			"class EquationComponent\n" +
			"  /* records are nice */\n" +
			"  record R\n" +
			"    Real x,y;\n" +
			"  end R;\n" +
			"\n" +
			"  R a,b,c;\n" +
			"equation\n" +
			"  a = if true then b else c;\n" +
			"end EquationComponent;";

		IProject project = proj.getWrappedProject();
		IFile file = project.getFile(CHANGING_FILE);
		
		file.setContents(Utility.getByteStream(contents), true, true, null);
	}

	/**
	 * utility function for the testLocationUpdates test
	 * 
	 * Creates the initial definitions in a source file
	 *
	 */
	private void createClassDef() throws CoreException 
	{
		String contents =
			"/*\n" +
			" * However, don't ever expect you can parse\n" +
			" * the error message from the compiler.\n" +
			" *    -- Adrian Pop\n" +
			" */\n" +
			"class EquationComponent\n" +
			"  record R\n" +
			"    Real x,y;\n" +
			"  end R;\n" + 
			"  R a,b,c;\n" +
			"equation\n" +
			"  a = if true then b else c;\n" +
			"end EquationComponent;";

		IProject project = proj.getWrappedProject();
		IFile file = project.getFile(CHANGING_FILE);
		
		file.create(Utility.getByteStream(contents), true, null);
	}
}
