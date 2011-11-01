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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ICompileError;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

import static org.modelica.mdt.core.IModelicaClass.Restriction.MODEL;
import static org.modelica.mdt.core.IModelicaClass.Restriction.PACKAGE;

/**
 * test org.modelica.mdt.core.CompilerProxy class' code 
 */
public class TestCompilerProxy extends TestCase {
	/* a source code file use in some tests */
	private IFile nested_models_mo;
	private IFile broken_nested_models_mo;
	private Vector<String> expectedClasses = new Vector<String>();

	protected void setUp()
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException {
		Area51Projects.createProjects();

		/*
		 * fetch reference to nested_models.mo file 
		 * from Area51Projects modelica project 
		 */
		IModelicaProject mproj = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		IProject proj = mproj.getWrappedProject();

		nested_models_mo = proj.getFile("nested_models.mo");
		broken_nested_models_mo = proj.getFile("broken_nested_models.mo");

		CompilerProxy.loadSourceFile(nested_models_mo);
		CompilerProxy.loadSourceFile
		(proj.getFolder("packages_folder").getFile("file_package.mo"));

		/*
		 * setup expected collection
		 */
		assertTrue(Collections.addAll(expectedClasses, "broken_nested_models",
				"bruuken_muu", "foobared", "broken_hej", "broken_hepp"));
	}

	/**
	 * test CompilerProxy.getRestriction() 
	 * @throws CompilerInstantiationException 
	 */
	public void testGetRestriction() 
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException {
		/*
		 * we need to load modelica package,
		 * we can as well do some checks		
		 * on the returned names of the standard packages 
		 */
		String[] stdlib = CompilerProxy.getStandardLibrary();
		List<String> stdlibList = Arrays.asList(stdlib);
		
		assertTrue(stdlib.length == stdlibList.size() && stdlib.length >= 1);
		assertTrue(stdlibList.contains("Modelica"));

		testGetRestriction("Modelica", IModelicaClass.Restriction.PACKAGE);

		testGetRestriction("Modelica.Blocks.Examples.BusUsage", IModelicaClass.Restriction.MODEL);

		testGetRestriction("Modelica.Math.log", IModelicaClass.Restriction.FUNCTION);

		testGetRestriction("Modelica.Icons.Record", IModelicaClass.Restriction.RECORD);

		testGetRestriction("Modelica.Electrical.Analog.Interfaces.Pin", IModelicaClass.Restriction.CONNECTOR);

		testGetRestriction("Modelica.Blocks.Continuous.Der", IModelicaClass.Restriction.BLOCK);

		testGetRestriction("Modelica.SIunits.Lethargy", IModelicaClass.Restriction.TYPE);

		testGetRestriction("hepp.hehehe", IModelicaClass.Restriction.CLASS);
	}

	/**
	 * test CompilerProxy.getClassLocation()
	 */
	public void testGetClassLocation()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException {
		/*
		 * we are basically only interested in getting the right definition region
		 */
		IDefinitionLocation loc = CompilerProxy.getClassLocation("nested_models");		
		assertTrue(loc.getPath().endsWith("nested_models.mo"));
		IRegion reg = loc.getRegion();
		assertEquals(0, reg.getOffset());
		assertEquals(146, reg.getLength());

		loc = CompilerProxy.getClassLocation("nested_models.hepp");
		reg = loc.getRegion();
		assertEquals(45, reg.getOffset());
		assertEquals(21, reg.getLength());

		loc = CompilerProxy.getClassLocation("nested_models.foo");		
		reg = loc.getRegion();
		assertEquals(71, reg.getOffset());
		assertEquals(126-71+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("nested_models.foo.bar");		
		reg = loc.getRegion();
		assertEquals(89, reg.getOffset());
		assertEquals(114-89+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("muu");		
		reg = loc.getRegion();
		assertEquals(147, reg.getOffset());
		assertEquals(193-147+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("foo");		
		reg = loc.getRegion();
		assertEquals(196, reg.getOffset());
		assertEquals(234-196+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("hej");		
		reg = loc.getRegion();
		assertEquals(238, reg.getOffset());
		assertEquals(369-238+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("hej.ine_paketen");		
		reg = loc.getRegion();
		assertEquals(253, reg.getOffset());
		assertEquals(292-253+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("hej.hejhej");		
		reg = loc.getRegion();
		assertEquals(298, reg.getOffset());
		assertEquals(360-298+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("hej.hejhej.foo");		
		reg = loc.getRegion();
		assertEquals(319, reg.getOffset());
		assertEquals(344-319+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("hepp");		
		reg = loc.getRegion();
		assertEquals(374, reg.getOffset());
		assertEquals(459-374+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("hepp.hopp");		
		reg = loc.getRegion();
		assertEquals(391, reg.getOffset());
		assertEquals(416-391+1, reg.getLength());

		loc = CompilerProxy.getClassLocation("hepp.hehehe");		
		reg = loc.getRegion();
		assertEquals(422, reg.getOffset());
		assertEquals(449-422+1, reg.getLength());
	}

	/**
	 * Test that both compile errors and contents are found in
	 * a problematic file.
	 *
	 */
	public void testErrorReporting() 
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException {
		IParseResults res = CompilerProxy.loadSourceFile(broken_nested_models_mo);

		for(String s : res.getClasses()) {
			expectedClasses.remove(s);
		}

		assertTrue("Could not find all expected classes in file",
				expectedClasses.isEmpty());

		ICompileError[] errs = res.getCompileErrors();
		assertEquals(7, errs[0].getStartLine()); 
		assertEquals(9, errs[1].getStartLine()); 
		assertEquals(11, errs[2].getStartLine()); 
		assertEquals(14, errs[3].getStartLine()); 
		assertEquals(16, errs[4].getStartLine()); 
	}

	/**
	 * test CompilerProxy.getClassInfo() 
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws CompilerInstantiationException 
	 */
	public void testGetClassInfo() 
			throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		/*
		 * run getClassInfo() on contents of nested_models.mo
		 * and make sure the returned info checks out
		 */
		IClassInfo ci = CompilerProxy.getClassInfo("nested_models");
		assertEquals("wrong type of restriction", MODEL, ci.getRestriction());
		assertFalse("wrong encapsulated status", ci.getEncapsulated());
		IDefinitionLocation loc = ci.getDefinitionLocation();
		assertTrue("fishy path", loc.getPath().endsWith("nested_models.mo"));
		assertEquals("wring start offset", 0, loc.getRegion().getOffset());
		assertEquals("wring start offset", 146, loc.getRegion().getLength());

		/*
		 * run getClassInfo() on contents of packages_folder/file_package.mo
		 * and make sure the returned info checks out
		 */
		ci = CompilerProxy.getClassInfo("file_package1");
		assertEquals("wrong type of restriction", PACKAGE, ci.getRestriction());
		assertFalse("wrong encapsulated status", ci.getEncapsulated());
		loc = ci.getDefinitionLocation();
		assertTrue("fishy path", loc.getPath().endsWith("file_package.mo"));
		assertEquals("wring start offset", 0, loc.getRegion().getOffset());
		assertEquals("wring start offset", 61, loc.getRegion().getLength());

		ci = CompilerProxy.getClassInfo("file_package2");
		assertEquals("wrong type of restriction", PACKAGE, ci.getRestriction());
		assertTrue("wrong encapsulated status", ci.getEncapsulated());
		loc = ci.getDefinitionLocation();
		assertTrue("fishy path", loc.getPath().endsWith("file_package.mo"));
		assertEquals("wring start offset", 63, loc.getRegion().getOffset());
		assertEquals("wring start offset", 137-63, loc.getRegion().getLength());
	}

	private void testGetRestriction(String className, IModelicaClass.Restriction expectedRestriction)
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException {
		IModelicaClass.Restriction actualRestriction = CompilerProxy.getRestriction(className);
		String restrictionFail = "For className \"" + className + "\", the expected restriction was \"" + expectedRestriction +
				"\", but we got \"" + actualRestriction + "\".";

		assertEquals(restrictionFail, expectedRestriction, actualRestriction);
	}
}
