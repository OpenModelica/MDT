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

import org.eclipse.core.runtime.Path;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementsInfo;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.core.compiler.ElementsInfo class' code 
 */
public class TestElementsInfo extends TestCase 
{
	@Override
	protected void setUp() throws Exception 
	{
		/*
		 * make sure nested_models.mo is created
		 */
		Area51Projects.createProjects();
		IModelicaProject proj = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		
		/*
		 * make sure nested_models.mo are loaded into compiler
		 */
		IModelicaSourceFile file = 
			(IModelicaSourceFile)proj.findElement(new Path("nested_models.mo"));
		assertNotNull("could not find nested_models.mo", file);
		file.getChildren();
		
		/*
		 * make sure nested_models.mo are loaded into compiler
		 */
		file =
			(IModelicaSourceFile)proj.findElement(new Path("import_rich_model.mo"));
		assertNotNull("could not find import_rich_model.mo", file);
		file.getChildren();

	}

	/**
	 * get a list of elements infos on classes defined in nested_models.mo 
	 * file in Area51 modelica project and check that fetching fields values 
	 * works as expected
	 */
	public void testFetchingFields()
		throws ConnectException, InvocationError, 
			UnexpectedReplyException, CompilerInstantiationException
	{
		String elementType;
		String className;
		
		/* do some tests on nested_models class from nested_models.mo */
		
		boolean heppFound = false;
		boolean fooFound = false;
		for (ElementsInfo ei : CompilerProxy.getElementsInfo("nested_models"))
		{
			elementType = ei.getElementType();
			
			if (elementType.equals("classdef"))
			{
				className = ei.getClassName();
				
				if (className.equals("hepp"))
				{
					heppFound = true;
					assertEquals(3, ei.getElementStartLine());
					assertEquals(5, ei.getElementStartColumn());
					// this is broken in omc at the moment (rev 2104)
					//assertEquals(3, ei.getElementEndLine());
					//assertEquals(25, ei.getElementEndColumn());
					
					assertEquals("RECORD", ei.getClassRestriction());
					assertTrue(ei.getClassFile().
							endsWith("nested_models.mo"));
							
				}
				else if (className.equals("foo"))
				{
					fooFound = true;
					assertEquals(4, ei.getElementStartLine());
					assertEquals(5, ei.getElementStartColumn());
					// this is broken in omc at the moment (rev 2104)
					//assertEquals(6, ei.getElementEndLine());
					//assertEquals(28, ei.getElementEndColumn());

					assertEquals("CLASS", ei.getClassRestriction());
					assertTrue(ei.getClassFile().
							endsWith("nested_models.mo"));					
				}
			}
		}
		
		assertTrue("class nested_models.foo not found", fooFound);
		assertTrue("class nested_models.hepp not found", heppFound);
		
		boolean component_a_found = false;
		/* do some tests on muu class from nested_models.mo */
		for (ElementsInfo ei : CompilerProxy.getElementsInfo("muu"))
		{
			if(ei.getElementType().equals("component"))
			{				
				List comp = ModelicaParser.parseList(ei.getNames());
				
				/* check component name */
				assertEquals("a", comp.elementAt(0).toString());
				
				component_a_found = true;
				/* check component comment */
				assertEquals("\"\"", comp.elementAt(1).toString()); 
				
				assertEquals("public", ei.getElementVisibility());
				assertTrue(ei.getElementFile().
						endsWith("nested_models.mo"));					

			}
		}
		assertTrue("could not find component muu.a", component_a_found);
		
		/* do some test on import_rich_model class from import_rich_model.mo */
		int importStatmentsFound = 0;
		
		for (ElementsInfo ei : CompilerProxy.getElementsInfo("import_rich_model"))
		{
			/*
			 * we are expecting 4 import statments in following order:
			 * 1. qualified         (import Modelica)
			 * 2. single definition (import Modelica.Math.sin)
			 * 3. unqualified       (import Modelica.*)
			 * 4. renaming          (import mm = Modelica.Math)
			 */
			elementType = ei.getElementType();
			
			if (elementType.equals("import"))
			{
				importStatmentsFound++;
				switch (importStatmentsFound) 
				{
				case 1: // import Modelica
					assertEquals("qualified", ei.getKind());
					assertEquals("Modelica", ei.getPath());
					break;
				case 2: // import Modelica.Math.sin
					assertEquals("qualified", ei.getKind());
					assertEquals("Modelica.Math.sin", ei.getPath());
					break;
				case 3: // import Modelica.*
					assertEquals("unqualified", ei.getKind());
					assertEquals("Modelica", ei.getPath());
					break;
				case 4: // import mm = Modelica.Math
					assertEquals("named", ei.getKind());
					assertEquals("Modelica.Math", ei.getPath());
					assertEquals("mm", ei.getId());
					break;
				default:
					fail("unexpectedly many imports found"); 
				}
			}
		}
		
		assertFalse("did not find all import statments", 
				importStatmentsFound < 4);
	}
}
