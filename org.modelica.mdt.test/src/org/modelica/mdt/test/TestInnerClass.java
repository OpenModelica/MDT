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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.InnerClass;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.core.compiler.InnerClass class' code 
 */
public class TestInnerClass extends TestCase 
{

	/* the test subject */
	private InnerClass componentsBananza;
	private InnerClass importRichModel;
	
	/* teh Modelica package (from the standard library) */
	private InnerClass modelica = null;
	
	@Override
	protected void setUp() throws Exception 
	{
		Area51Projects.createProjects();
		
		/* navigate to the model 'component_bananza' */
		IModelicaProject proj = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		
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
		
		/* fetch teh Modelica package from the standard library */
		for (IModelicaClass clazz : CompilerProxy.getStandardLibrary())
		{
			if (clazz.getElementName().equals("Modelica"))
			{
				modelica = (InnerClass)clazz;
				break;
			}
		}
		assertNotNull("could not found standard library package 'Modelica'",
				modelica);
		

	}
	
	/**
	 * do some sanity checks on InnerClass children
	 */
	public void testChildren()
	throws ConnectException, UnexpectedReplyException,
		InvocationError, CoreException, CompilerInstantiationException
	{ 
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
		
		/* sanity checks on components_bananza.a_type */
		assertNotNull("components_bananza.a_type not found", a_type);
		assertEquals("wrong element name", "a_type", a_type.getElementName());
		assertEquals("wrong element full name", 
				"components_bananza.a_type", a_type.getFullName());		
		assertTrue("fishy file path", 
				a_type.getFilePath().endsWith("component_model.mo"));
		IRegion reg = a_type.getLocation();
		assertEquals("wrong start offset", 342, reg.getOffset());
		assertEquals("wrong length", 364-342+1, reg.getLength());

		/* sanity checks on components_bananza.a_package */
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

		/* sanity checks on components_bananza.a_block */
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

		/* sanity checks on components_bananza.a_class */
		assertNotNull("components_bananza.a_class not found", a_class);
		assertEquals("wrong element name", "a_class", a_class.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_class",
				a_class.getFullName());
		assertTrue("fishy file path", 
				a_class.getFilePath().endsWith("component_model.mo"));
		reg = a_class.getLocation();
		assertEquals("wrong start offset", 132, reg.getOffset());
		assertEquals("wrong length", 157-132+1, reg.getLength());

		/* sanity checks on components_bananza.a_connector */
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

		/* sanity checks on components_bananza.a_function */
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

		/* sanity checks on components_bananza.a_model */
		assertNotNull("components_bananza.a_model not found", a_model);
		assertEquals("wrong element name", "a_model", a_model.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_model", 
				a_model.getFullName());
		assertTrue("fishy file path", 
				a_model.getFilePath().endsWith("component_model.mo"));
		reg = a_model.getLocation();
		assertEquals("wrong start offset", 163, reg.getOffset());
		assertEquals("wrong length", 188-163+1, reg.getLength());

		/* sanity checks on components_bananza.a_record */
		assertNotNull("components_bananza.a_record not found", a_record);
		assertEquals("wrong element name", "a_record", a_record.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_record",
				a_record.getFullName());
		assertTrue("fishy file path", 
				a_record.getFilePath().endsWith("component_model.mo"));
		reg = a_record.getLocation();
		assertEquals("wrong start offset", 237, reg.getOffset());
		assertEquals("wrong length", 265-237+1, reg.getLength());

		/* sanity checks on components_bananza.a_real */
		assertNotNull("components_bananza.a_real not found", a_real);
		assertEquals("wrong element name", "a_real", a_real.getElementName());
		assertEquals("wrong element full name", "components_bananza.a_real", 
				a_real.getFullName());
		assertEquals("wrong visibility", a_real.getVisbility(),
				IModelicaComponent.Visibility.PUBLIC);

		reg = a_real.getLocation();
		assertEquals("wrong start offset", 29, reg.getOffset());
		assertEquals("wrong length", 58-29+1, reg.getLength());
		
		/* sanity checks on components_bananza.a_undocumented_real */
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
		
		/* sanity checks on components_bananza.a_protected_integer */
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

		/* sanity checks on components_bananza.a_protected_real */
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
	}
	
	/**
	 * do some sanity checks on InnerClass' imports
	 */
	public void testImports() 
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException
	{
		int importCounter = 0;
		IModelicaClass importedPackage;
		for (IModelicaImport imp : importRichModel.getImports())
		{
			importCounter++;
			
			/*
			 * we are expecting 8 import statments in following order:
			 * 1. qualified         (import Modelica)
			 * 2. single definition (import Modelica.Math.sin)
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
				// this is basicaly not implemented and maybe will go away...argh
//				assertEquals(IModelicaImport.Type.SINGLE_DEFINITION, 
//						imp.getType());
				break;
			case 3: // import Modelica.*
				assertEquals(IModelicaImport.Type.UNQUALIFIED, imp.getType());
				break;
			case 4: // import mm = Modelica.Math
				assertEquals(IModelicaImport.Type.RENAMING, imp.getType());
				break;
			case 5: // import foo = hepp
				assertEquals(IModelicaImport.Type.RENAMING, imp.getType());
				break;
			case 6: // import hepp.hopp
				assertEquals(IModelicaImport.Type.QUALIFIED, imp.getType());
				break;
			case 7: // import hepp.*
				assertEquals(IModelicaImport.Type.UNQUALIFIED, imp.getType());
				break;
			case 8: // import root_package.root_package_model
				// this is basicaly not implemented and maybe will go away...argh
//				assertEquals(IModelicaImport.Type.SINGLE_DEFINITION, imp.getType());
				break;
			default:
				fail("unexpectedly many imports found"); 
			}
		}
		
		assertFalse("did not find all import statments", 
				importCounter < 4);

	}
	
	/**
	 * Do some integrity tests on classes/packages from the standard library.
	 */
	public void testStandardLibraryElements()
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CompilerInstantiationException, CoreException
	{
		/*
		 * do checks on Modelica package
		 */
		assertNull("standard library should be defined outside of workspace",
				modelica.getResource());
		assertFalse("empty path to the source file", 
				modelica.getFilePath().equals(""));
		IRegion reg = modelica.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);


		/*
		 * do checks on Modelica.Blocks and Modelica.Constants packages
		 */

		InnerClass blocks = null;
		InnerClass constants = null;
 
		for (IModelicaElement el : modelica.getChildren())
		{
			String name = el.getElementName();
			
			if (name.equals("Blocks"))
			{
				blocks = (InnerClass)el;
			}
			else if (name.equals("Constants"))
			{
				constants = (InnerClass)el;
			}
		} 
		
		/* check Modelica.Blocks */
		assertNotNull("could not find package Modelica.Blocks in standard" +
				"library ", blocks);
		assertNull("standard library should be defined outside of workspace",
				blocks.getResource());
		//TODO this is broken in omc (rev 2113), uncomment blew then omc is fixed
//		assertFalse("empty path to the source file", 
//				blocks.getFilePath().equals(""));
//		reg = blocks.getLocation();
//		assertTrue("negative element region can't be", reg.getOffset() >= 0);
//		System.out.println(reg.getLength());
//		assertTrue("elements length must be positive", reg.getLength() > 0);
		
		/* check Modelica.Blocks imports */
		boolean foundSIimport = false;

		for (IModelicaImport imp : blocks.getImports())
		{
			switch (imp.getType())
			{
			case RENAMING:
				if (imp.getAlias().equals("SI"))
				{
					foundSIimport = true;
				}
				break;
				/* ignore all other types of imports */
			}
		}
		
		assertTrue("could not find the representation of" +
				" 'import SI = Modelica.SIunits;' statment", foundSIimport); 
		
		/* check Modelica.Constants */
		assertNotNull("could not find package Modelica.Constants in standard" +
				"library ", constants);
		assertNull("standard library should be defined outside of workspace",
				constants.getResource());
		//TODO this is broken in omc (rev 2113), uncomment blew then omc is fixed
//		assertFalse("empty path to the source file", 
//				constants.getFilePath().equals(""));
//		reg = constants.getLocation();
//		assertTrue("negative element region can't be", reg.getOffset() >= 0);
//		assertTrue("elements length must be positive", reg.getLength() > 0);


		IModelicaComponent pi = null;
		IModelicaComponent D2R = null;
		
		/*
		 * do checks on Modelica.Constants components
		 */
		for (IModelicaElement el : constants.getChildren())
		{
			String name = el.getElementName();
			
			if (name.equals("pi"))
			{
				pi = (IModelicaComponent)el;
			}
			else if (name.equals("D2R"))
			{
				D2R = (IModelicaComponent)el;
			}
		}

		/* check Modelica.Constants.pi */
		assertNotNull("could not find package Modelica.Constants.pi in standard" 
				+ "library ", pi);
		assertEquals("pi must have public visibility", 
				IModelicaComponent.Visibility.PUBLIC,
				pi.getVisbility());
		assertNull("standard library should be defined outside of workspace",
				pi.getResource());
		assertFalse("empty path to the source file", 
				pi.getFilePath().equals(""));
		reg = pi.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);

		/* check Modelica.Constants.D2R */
		assertNotNull("could not find package Modelica.Constants.pi in standard" 
				+ "library ", D2R);
		assertEquals("pi must have public visibility", 
				IModelicaComponent.Visibility.PUBLIC,
				D2R.getVisbility());
		assertNull("standard library should be defined outside of workspace",
				D2R.getResource());
		assertFalse("empty path to the source file", 
				D2R.getFilePath().equals(""));
		reg = pi.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);
		
		/* check Modelica.Constants imports */
		foundSIimport = false;
		boolean foundNonSIimport = false;

		for (IModelicaImport imp : constants.getImports())
		{
			switch (imp.getType())
			{
			case RENAMING:
				if (imp.getAlias().equals("SI"))
				{
					foundSIimport = true;
				}
				else if (imp.getAlias().equals("NonSI"))
				{
					foundNonSIimport = true;
				}
				break;
				/* ignore all other types of imports */
			}
		}
		
		assertTrue("could not find the representation of" +
				" 'import SI = Modelica.SIunits;' statment", foundSIimport); 
		assertTrue("could not find the representation of" +
				" import NonSI = Modelica.SIunits.Conversions.NonSIunits;' " +
				"statment", foundNonSIimport); 
	}
}
