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
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ISourceRegion;
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
		 * fetch reference to nested_models.mo file from Area51Projects modelica project
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
		 * we need to load modelica package, we can as well do some checks on the returned names of the standard packages 
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
		testGetClassLocation("nested_models", "nested_models.mo", 1, 1, 7, 18);

		testGetClassLocation("nested_models.hepp", null, 3, 5, 3, 25);

		testGetClassLocation("nested_models.foo", null, 4, 5, 6, 28);

		testGetClassLocation("nested_models.foo.bar", null, 5, 9, 6, 16);

		testGetClassLocation("muu", null, 8, 1, 12, 8);

		testGetClassLocation("foo", null, 14, 1, 16, 8);

		testGetClassLocation("hej", null, 19, 1, 26, 8);

		testGetClassLocation("hej.ine_paketen", null, 20, 5, 21, 20);

		testGetClassLocation("hej.hejhej", null, 22, 5, 25, 15);

		testGetClassLocation("hej.hejhej.foo", null, 23, 9, 24, 16);

		testGetClassLocation("hepp", null, 30, 1, 35, 9);

		testGetClassLocation("hepp.hopp", null, 31, 5, 32, 13);

		testGetClassLocation("hepp.hehehe", null, 33, 5, 34, 15);
	}

	/**
	 * Test for compile errors in a problematic file.
	 */
	public void testErrorReporting() 
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException {
		IParseResults parseResults = CompilerProxy.loadSourceFile(broken_nested_models_mo);
		String[] classes = parseResults.getClasses();

		assertTrue(classes.length == 0);

		ICompileError[] compileErrors = parseResults.getCompileErrors();
		assertTrue(compileErrors.length == 1);
		assertTrue(compileErrors[0].getStartLine() == 7);
		assertTrue(compileErrors[0].getStartColumn() == 12);
		assertTrue(compileErrors[0].getEndLine() == 8);
		assertTrue(compileErrors[0].getEndColumn() == 8);
		assertTrue(compileErrors[0].getErrorDescription().equals(";"));
	}

	/**
	 * test CompilerProxy.getClassInfo() 
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws CompilerInstantiationException 
	 */
	public void testGetClassInfo() 
			throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		testGetClassInfo("nested_models", IModelicaClass.Restriction.MODEL, false, "nested_models.mo", 1, 1, 7, 18);

		testGetClassInfo("file_package1", IModelicaClass.Restriction.PACKAGE, false, "file_package.mo", 1, 1, 3, 18);

		testGetClassInfo("file_package2", IModelicaClass.Restriction.PACKAGE, true, "file_package.mo", 5, 1, 7, 18);
	}

	private void testGetRestriction(String className, IModelicaClass.Restriction expectedRestriction)
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException {
		IModelicaClass.Restriction actualRestriction = CompilerProxy.getRestriction(className);
		String restrictionFail = "For className \"" + className + "\", the expected restriction was \"" + expectedRestriction +
				"\", but we got \"" + actualRestriction + "\".";

		assertEquals(restrictionFail, expectedRestriction, actualRestriction);
	}

	private void testGetClassLocation(String className, String pathEndsWith, int startLine, int startCol, int endLine, int endCol)
			throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		IClassInfo classInfo = CompilerProxy.getClassInfo(className);
		IDefinitionLocation definitionLoc = classInfo.getDefinitionLocation();
		ISourceRegion sourceRegion = definitionLoc.getSourceRegion();

		int actualStartLine = sourceRegion.getStartLine();
		int actualStartCol = sourceRegion.getStartColumn();
		int actualEndLine = sourceRegion.getEndLine();
		int actualEndCol = sourceRegion.getEndColumn();

		if (pathEndsWith != null) {
			String path = definitionLoc.getPath();
			assertTrue("A call to path.endsWith() did not return the expected value.", path.endsWith(pathEndsWith));
		}

		assertEquals("Expected start line did not match actual start line.", startLine, actualStartLine);
		assertEquals("Expected start column did not match actual start column.", startCol, actualStartCol);
		assertEquals("Expected end line did not match actual end line.", endLine, actualEndLine);
		assertEquals("Expected end column did not match actual end column.", endCol, actualEndCol);
	}

	private void testGetClassInfo(String className, IModelicaClass.Restriction restriction, boolean isEncapsulated,
			String pathEndsWith, int startLine, int startCol, int endLine, int endCol)
					throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		IClassInfo classInfo = CompilerProxy.getClassInfo(className);
		IDefinitionLocation definitionLoc = classInfo.getDefinitionLocation();
		ISourceRegion sourceRegion = definitionLoc.getSourceRegion();

		IModelicaClass.Restriction actualRestriction = classInfo.getRestriction();
		boolean actualIsEncapsulated = classInfo.getEncapsulated();
		int actualStartLine = sourceRegion.getStartLine();
		int actualStartCol = sourceRegion.getStartColumn();
		int actualEndLine = sourceRegion.getEndLine();
		int actualEndCol = sourceRegion.getEndColumn();

		if (pathEndsWith != null) {
			String path = definitionLoc.getPath();
			assertTrue("A call to path.endsWith() did not return the expected value.", path.endsWith(pathEndsWith));
		}

		String restrictionFail = "For className \"" + className + "\", the expected restriction was \"" + restriction
				+ "\", but we got \"" + actualRestriction + "\".";

		assertEquals(restrictionFail, restriction, actualRestriction);

		assertEquals("The expected and actual encapsulation status did not match.", isEncapsulated, actualIsEncapsulated);

		assertEquals("Expected start line did not match actual start line.", startLine, actualStartLine);
		assertEquals("Expected start column did not match actual start column.", startCol, actualStartCol);
		assertEquals("Expected end line did not match actual end line.", endLine, actualEndLine);
		assertEquals("Expected end column did not match actual end column.", endCol, actualEndCol);
	}
}
