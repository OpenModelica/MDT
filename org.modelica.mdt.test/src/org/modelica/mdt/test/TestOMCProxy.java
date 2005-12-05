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

import java.util.Collections;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.modelica.mdt.internal.omcproxy.CompilerException;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.ElementLocation;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

public class TestOMCProxy extends TestCase
{
	private Vector<String> modelicaLibraryPackages = new Vector<String>(12);
	
	protected void setUp()
	{
		Area51Projects.createProjects();

		assertTrue(Collections.addAll(modelicaLibraryPackages,
				"Mechanics",
				"Electrical",
				"Math",
				"Blocks",
				"Thermal",
				"Icons",
				"Constants",
				"SIunits"));
	}
	
	/**
	 * test OMCProxy.getPackages()
	 */
	public void testGetPackages()
	{
		try
		{
			OMCProxy.loadSystemLibrary();
			String[] str = OMCProxy.getPackages("Modelica");

			assertNotNull("Could not fetch Modelica package", str);
			
			Vector<String> packages = new Vector<String>();
			for(String s : str)
			{
				packages.addElement(s);
			}

			assertTrue(packages.containsAll(modelicaLibraryPackages));
		}
		catch(CompilerException e)
		{
			fail(e.getMessage());
		}
	}
	
	/**
	 * test OMCProxy.getElementLocation()
	 */
	public void testGetElementLocation()
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		/* load file nested_models.mo from Area51Projects modelica project */
		IProject proj = Utility.getProject(
				Area51Projects.MODELICA_PROJECT_NAME).getProject();
		
		OMCProxy.loadFileInteractive(proj.getFile("nested_models.mo"));
		
		/*
		 * we are basicaly only interested in getting the right line number
		 */
		ElementLocation loc = OMCProxy.getElementLocation("nested_models");		
		assertTrue(loc.getPath().endsWith("nested_models.mo"));
		assertEquals(loc.getLine(), 1);
		
		loc = OMCProxy.getElementLocation("nested_models.hepp");		
		assertEquals(loc.getLine(), 3);

		loc = OMCProxy.getElementLocation("nested_models.foo");		
		assertEquals(loc.getLine(), 4);
		
		loc = OMCProxy.getElementLocation("nested_models.foo.bar");		
		assertEquals(loc.getLine(), 5);
		
		loc = OMCProxy.getElementLocation("muu");		
		assertEquals(loc.getLine(), 8);
		
		loc = OMCProxy.getElementLocation("foo");		
		assertEquals(loc.getLine(), 14);

		loc = OMCProxy.getElementLocation("hej");		
		assertEquals(loc.getLine(), 19);

		loc = OMCProxy.getElementLocation("hej.ine_paketen");		
		assertEquals(loc.getLine(), 20);

		loc = OMCProxy.getElementLocation("hej.hejhej");		
		assertEquals(loc.getLine(), 22);

		loc = OMCProxy.getElementLocation("hej.hejhej.foo");		
		assertEquals(loc.getLine(), 23);

		loc = OMCProxy.getElementLocation("hepp");		
		assertEquals(loc.getLine(), 30);

		loc = OMCProxy.getElementLocation("hepp.hopp");		
		assertEquals(loc.getLine(), 31);

		loc = OMCProxy.getElementLocation("hepp.hehehe");		
		assertEquals(loc.getLine(), 33);

	}
}
