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

import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.ISourceRegion;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.DefinitionSourceRegion;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.core.compiler.ModelicaSourceFile class' code
 */
public class TestModelicaSourceFile extends TestCase {
	IModelicaSourceFile nestedModelsMo = null;

	@Override
	protected void setUp() throws Exception {
		Area51Projects.createProjects();

		/*
		 * fetch a reference to nested_models.mo source file
		 */
		IModelicaProject proj = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		IModelicaFolder folder = proj.getRootFolder();
		nestedModelsMo = Utility.findModelicaFileInFolder(folder, "nested_models.mo");

		assertNotNull("could not find nested_models.mo file", nestedModelsMo);
	}

	/**
	 * test ModelicaSourceFile.getClassAt()
	 */
	public void testGetClassAt() throws Exception {
		testGetClass(nestedModelsMo, "nested_models", "nested_models", 1, 1, 7, 18);

		testGetClass(nestedModelsMo, "nested_models.hepp", "hepp", 3, 5, 3, 25);

		testGetClass(nestedModelsMo, "nested_models.foo", "foo", 4, 5, 6, 28);

		testGetClass(nestedModelsMo, "nested_models.foo.bar", "bar", 5, 9, 6, 16);

		testGetClass(nestedModelsMo, "muu", "muu", 8, 1, 12, 8);

		testGetClass(nestedModelsMo, "foo", "foo", 14, 1, 16, 8);

		testGetClass(nestedModelsMo, "hej", "hej", 19, 1, 26, 8);

		testGetClass(nestedModelsMo, "hej.ine_paketen", "ine_paketen", 20, 5, 21, 20);

		testGetClass(nestedModelsMo, "hej.hejhej", "hejhej", 22, 5, 25, 15);

		testGetClass(nestedModelsMo, "hepp", "hepp", 30, 1, 35, 9);

		testGetClass(nestedModelsMo, "hepp.hopp", "hopp", 31, 5, 32, 13);

		testGetClass(nestedModelsMo, "hepp.hehehe", "hehehe", 33, 5, 34, 15);

		testGetClassNonExisting(nestedModelsMo, 12, 9, 13, 1);

		testGetClassNonExisting(nestedModelsMo, 13, 1, 14, 1);
	}

	private void testGetClass(IModelicaSourceFile modelicaSourceFile, String fullName, String className,
			int startLine, int startCol, int endLine, int endCol)
					throws ConnectException, UnexpectedReplyException, CompilerInstantiationException, InvocationError, CoreException {
		/*
		 * The way IModelicaSourceFile.getClassAt() is implemented right now we can get a hit for source regions that do not
		 * exactly match where the item we are looking for is located. Example:
		 * testGetClass(nestedModelsMo, "nested_models.foo", "foo", 4, 5, 6, 2);
		 * The call above would get a hit with the expected full name and element name. However, the source region returned by the
		 * the found class would not match with the source region constructed here, because the end column is wrong (the correct
		 * value is 28, not 2).
		 * So why is it found by getClassAt()? Well, here's why:
		 * getClassAt() drills down until it finds the innermost class that contains the given region. And the region only has to be
		 * contained, a perfect match is not required. Also, DefinitionSourceRegion.contains() is forgiving when it comes to column
		 * mismatch: If the following condition is true the given region is said to be contained without any column check at all:
		 * if ((foundStartLine <= givenStartLine and foundEndLine > givenEndLine) or (foundStartLine < givenStartLine and foundEndLine
		 * >= givenEndLine). This condition would miss given regions that have a lower starting column or a higher ending column while
		 * starting or ending line is the same as the found region. This is a possible FIXME .
		 * I suppose it may also possible to find a class with the same element name and inside the given region but actually deeper
		 * inside than the one we were looking for, like a foo nested somewhere within a foo, but I wasn't able to create a working
		 * test case exhibiting this behavior after a quick try. 
		 * Anyway, we solve all these problem by always checking the full name of the found class and checking that the source
		 * region it returns exactly matches the supplied one. That way we force the user of this method to exactly specify the full
		 * name and location of the class he or she is looking for in order for this method to pass the test.
		 */
		ISourceRegion sourceRegion = new DefinitionSourceRegion(startLine, startCol, endLine, endCol);
		IModelicaClass modelicaClass = modelicaSourceFile.getClassAt(sourceRegion);

		assertNotNull("modelicaSourceFile.getClassAt() returned null for \"" + className + "\".", modelicaClass);

		String actualFullName = modelicaClass.getFullName();
		String actualClassName = modelicaClass.getElementName();

		assertEquals("Expected full name differs from actual full name for the given region.", fullName, actualFullName);
		assertEquals("Expected class name differs from actual class name for the given region.", className, actualClassName);

		IDefinitionLocation location = modelicaClass.getLocation();
		ISourceRegion actualSourceRegion = location.getSourceRegion();

		int actualStartLine = actualSourceRegion.getStartLine();
		int actualStartCol = actualSourceRegion.getStartColumn();
		int actualEndLine = actualSourceRegion.getEndLine();
		int actualEndCol = actualSourceRegion.getEndColumn();

		assertEquals("Expected start line did not match actual start line.", startLine, actualStartLine);
		assertEquals("Expected start column did not match actual start column.", startCol, actualStartCol);
		assertEquals("Expected end line did not match actual end line.", endLine, actualEndLine);
		assertEquals("Expected end column did not match actual end column.", endCol, actualEndCol);
	}

	private void testGetClassNonExisting(IModelicaSourceFile modelicaSourceFile, int startLine, int startCol, int endLine, int endCol)
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException, InvocationError, CoreException {
		ISourceRegion sourceRegion = new DefinitionSourceRegion(startLine, startCol, endLine, endCol);
		IModelicaClass modelicaClass = modelicaSourceFile.getClassAt(sourceRegion);

		assertNull("No class should be found for that source region.", modelicaClass);
	}
}
