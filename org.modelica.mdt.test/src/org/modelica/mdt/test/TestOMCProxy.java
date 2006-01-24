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

import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass.Type;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IElementLocation;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

public class TestOMCProxy extends TestCase
{
	/* a source code file use in some tests */
	private IFile nested_models_mo;
	
	protected void setUp() 
		throws ConnectException, CompilerInstantiationException
	{
		Area51Projects.createProjects();

		/* 
		 * fetch reference to nested_models.mo file 
		 * from Area51Projects modelica project 
		 */
		IProject proj = Utility.getProject(
				Area51Projects.MODELICA_PROJECT_NAME).getProject();
		
		nested_models_mo = proj.getFile("nested_models.mo");

	}
	
	/**
	 * test OMCProxy.getRestrictionType() 
	 * @throws CompilerInstantiationException 
	 */
	public void testGetRestrictionType() 
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		/* we need to load modelica package */		
		String[] stdPackages = CompilerProxy.getStandardLibrary();
		
		 /* make some checks on the returned names of the standard packages */
		Vector<String> v = new Vector<String>();
		for (String name : stdPackages)
		{
			v.add(name);
		}
		assertTrue(v.size() >= 1);
		assertTrue(v.contains("Modelica"));
		
		if (CompilerProxy.getRestrictionType("Modelica") != Type.PACKAGE)
		{
			fail("Modelica class' restriction type is wrong");
		}
		if (CompilerProxy.getRestrictionType("Modelica.Blocks.Examples.BusUsage") 
				!= Type.MODEL)
		{
			fail("Modelica.Blocks.Examples.BusUsage class' " + 
					"restriction type is wrong");
		}
		if (CompilerProxy.getRestrictionType("Modelica.Math.log") 
				!= Type.FUNCTION)
		{
			fail("Modelica.Math.log class' restriction type is wrong");
		}
		if (CompilerProxy.getRestrictionType("Modelica.Icons.Record") 
				!= Type.RECORD)
		{
			fail("Modelica.Icons.Record class' restriction type is wrong");
		}
		if (CompilerProxy.getRestrictionType("Modelica.Electrical.Analog.Interfaces.Pin") 
				!= Type.CONNECTOR)
		{
			fail("Modelica.Blocks.Interfaces.BooleanPort class' " + 
					"restriction type is wrong");
		}
		if (CompilerProxy.getRestrictionType("Modelica.Blocks.Continuous.Der") 
				!= Type.BLOCK)
		{
			fail("Modelica.Blocks.Continuous.Der class' " + 
					"restriction type is wrong");
		}
		if (CompilerProxy.getRestrictionType("Modelica.SIunits.Lethargy") 
				!= Type.TYPE)
		{
			fail("Modelica.SIunits.Lethargy class' restriction type is wrong");
		}
		
		CompilerProxy.loadFileInteractive(nested_models_mo);
		if (CompilerProxy.getRestrictionType("hepp.hehehe") 
				!= Type.CLASS)
		{
			fail("hepp.hehehe class' restriction type is wrong");
		}
	}
	
	/**
	 * test OMCProxy.getElementLocation()
	 * @throws CompilerInstantiationException 
	 */
	public void testGetElementLocation()
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException
	{
		CompilerProxy.loadFileInteractive(nested_models_mo);

		/*
		 * we are basicaly only interested in getting the right line number
		 */
		IElementLocation loc = CompilerProxy.getClassLocation("nested_models");		
		assertTrue(loc.getPath().endsWith("nested_models.mo"));
		assertEquals(loc.getLine(), 1);
		
		loc = CompilerProxy.getClassLocation("nested_models.hepp");		
		assertEquals(loc.getLine(), 3);

		loc = CompilerProxy.getClassLocation("nested_models.foo");		
		assertEquals(loc.getLine(), 4);
		
		loc = CompilerProxy.getClassLocation("nested_models.foo.bar");		
		assertEquals(loc.getLine(), 5);
		
		loc = CompilerProxy.getClassLocation("muu");		
		assertEquals(loc.getLine(), 8);
		
		loc = CompilerProxy.getClassLocation("foo");		
		assertEquals(loc.getLine(), 14);

		loc = CompilerProxy.getClassLocation("hej");		
		assertEquals(loc.getLine(), 19);

		loc = CompilerProxy.getClassLocation("hej.ine_paketen");		
		assertEquals(loc.getLine(), 20);

		loc = CompilerProxy.getClassLocation("hej.hejhej");		
		assertEquals(loc.getLine(), 22);

		loc = CompilerProxy.getClassLocation("hej.hejhej.foo");		
		assertEquals(loc.getLine(), 23);

		loc = CompilerProxy.getClassLocation("hepp");		
		assertEquals(loc.getLine(), 30);

		loc = CompilerProxy.getClassLocation("hepp.hopp");		
		assertEquals(loc.getLine(), 31);

		loc = CompilerProxy.getClassLocation("hepp.hehehe");		
		assertEquals(loc.getLine(), 33);

	}
}
