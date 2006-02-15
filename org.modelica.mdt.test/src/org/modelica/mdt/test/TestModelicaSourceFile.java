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

import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.core.compiler.ModelicaSourceFile class' code
 */
public class TestModelicaSourceFile extends TestCase 
{
	IModelicaSourceFile nestedModelsMo = null;
	
	@Override
	protected void setUp() throws Exception 
	{
		Area51Projects.createProjects();

		/*
		 * fetch a reference to nested_models.mo source file
		 */
		IModelicaProject proj = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		
		nestedModelsMo = 
			Utility.findModelicaFileInFolder(proj.getRootFolder(), 
				"nested_models.mo");
		
		assertNotNull("could not find nested_models.mo file", nestedModelsMo);
	}


	/**
	 * test ModelicaSourceFile.getClassAt()
	 */
	public void testGetClassAt() throws Exception
	{
		IModelicaClass clazz;
		
		clazz = nestedModelsMo.getClassAt(22);		
		assertNotNull(clazz);
		assertEquals("nested_models", clazz.getElementName());
		
		clazz = nestedModelsMo.getClassAt(56);		
		assertNotNull(clazz);
		assertEquals("hepp", clazz.getElementName());
		
		clazz = nestedModelsMo.getClassAt(80);		
		assertNotNull(clazz);
		assertEquals("foo", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(98);		
		assertNotNull(clazz);
		assertEquals("bar", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(164);		
		assertNotNull(clazz);
		assertEquals("muu", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(194);
		assertNull("no class definition should be found here", clazz);

		clazz = nestedModelsMo.getClassAt(195);		
		assertNull("no class definition should be found here", clazz);

		clazz = nestedModelsMo.getClassAt(213);		
		assertNotNull(clazz);
		assertEquals("foo", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(243);		
		assertNotNull(clazz);
		assertEquals("hej", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(263);		
		assertNotNull(clazz);
		assertEquals("ine_paketen", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(310);		
		assertNotNull(clazz);
		assertEquals("hejhej", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(388); 		
		assertNotNull(clazz);
		assertEquals("hepp", clazz.getElementName());
		
		/* check at the first character of the definition */
		clazz = nestedModelsMo.getClassAt(391); 		
		assertNotNull(clazz);
		assertEquals("hopp", clazz.getElementName());

		clazz = nestedModelsMo.getClassAt(400); 		
		assertNotNull(clazz);
		assertEquals("hopp", clazz.getElementName());
		
		/* check at the last character of the definition */
		clazz = nestedModelsMo.getClassAt(416);		
		assertNotNull(clazz);
		assertEquals("hopp", clazz.getElementName());
		
		clazz = nestedModelsMo.getClassAt(447);		
		assertNotNull(clazz);
		assertEquals("hehehe", clazz.getElementName());
	}
}
