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

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * Test various methods in org.modelica.mdt.internal.core.ModelicaFolder
 *
 * @author Elmir Jagudin
 */
public class TestModelicaFolder extends TestCase {
	/*
	 * the test subject
	 * the root folder of Area51 modelica project
	 */
	private IModelicaFolder projectRootFolder;

	/* collection of expected children */
	private Vector<String> expectedChildren = new Vector<String>();	
	private Vector<String> expectedRootFolderChildren = new Vector<String>();

	/* expected modelica file names in package_look_alike folder */
	private Vector<String> expectedPackageLookAlikeChildren = new Vector<String>();

	@Override
	protected void setUp() throws Exception {
		Area51Projects.createProjects();

		IModelicaProject project = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME); 
		projectRootFolder = project.getRootFolder();

		/*
		 * setup expected collections
		 */
		assertTrue(Collections.addAll(expectedChildren,
				".project", "empty_file", "README.txt", "empty_folder", 
				"root_folder", "package_look_alike", "root_model.mo", 
				"nested_models.mo", "root_package", "childless_package"));

		assertTrue(Collections.addAll(expectedRootFolderChildren, "hej_hopp"));

		assertTrue(Collections.addAll(expectedPackageLookAlikeChildren, "package.mo"));
	}

	/**
	 * test ModelicaFolder.hasChildren()
	 */
	public void testHasChildren() {
		try {
			boolean hasChildren = projectRootFolder.hasChildren();
			assertTrue(hasChildren);
		}
		catch (Exception e) {
			fail("exception thrown " + e.getMessage());
		}
	}

	/**
	 * test ModelicaFolder.getChildren() method
	 * @throws CompilerInstantiationException 
	 * 
	 */
	public void testGetChildren() 
			throws ConnectException, UnexpectedReplyException, InvocationError, CoreException, CompilerInstantiationException {
		IModelicaFolder root_folder = null;
		IModelicaFolder empty_folder = null;
		IModelicaFolder package_look_alike = null;

		Collection<? extends IModelicaElement> rootFolderChildren = projectRootFolder.getChildren();

		for (IModelicaElement elm : rootFolderChildren) {
			String name = null;

			if (elm instanceof IFile) {
				name = ((IFile)elm).getName();
			}
			else {
				name = elm.getElementName();
			}

			boolean wasRemoved = expectedChildren.remove(name);

			if (wasRemoved) {
				System.out.println("Removed \"" + name + "\" from expectedChildren.");
			}
			else {
				System.out.println("\"" + name + "\" was not present in expectedChildren.");
			}

			if (name.equals("root_folder")) {
				root_folder = (IModelicaFolder)elm;
			}
			else if (name.equals("empty_folder")) {
				empty_folder = (IModelicaFolder)elm;
			}
			else if (name.equals("package_look_alike")) {
				package_look_alike = (IModelicaFolder)elm;
			}
			else {
				System.out.println("\"" + name + "\" did not match \"root_folder\", \"empty_folder\", or \"package_look_alike\".");
			}
		}

		boolean expectedChildrenIsEmpty = expectedChildren.isEmpty();

		assertTrue("could not find all expected children in the root folder", expectedChildrenIsEmpty);
		assertNotNull("root_folder element not found", root_folder);
		assertNotNull("empty_folder element not found", empty_folder);
		assertNotNull("package_look_alike element not found", package_look_alike);

		rootFolderChildren = root_folder.getChildren();

		for (IModelicaElement elm : rootFolderChildren) {
			if (elm instanceof IModelicaFile) {
				/* we only expect (looking for) files inside root_folder */
				String name = elm.getElementName();
				boolean wasRemoved = expectedRootFolderChildren.remove(name);

				if (wasRemoved) {
					System.out.println("Removed \"" + name + "\" from expectedRootFolderChildren.");
				}
				else {
					System.out.println("\"" + name + "\" was not present in expectedRootFolderChildren.");
				}
			}
		}

		boolean expectedRootFolderChildrenIsEmpty = expectedRootFolderChildren.isEmpty();

		assertTrue("Could not find all expected files in root_folder.", expectedRootFolderChildrenIsEmpty);
		
		Collection<? extends IModelicaElement> packageLookAlikeChildren = package_look_alike.getChildren();

		for (IModelicaElement elm : packageLookAlikeChildren) {
			if (elm instanceof IModelicaSourceFile) {
				/* 
				 * we only expect (looking for) modelica files 
				 * inside package_look_alike 
				 */
				String name = elm.getElementName();
				boolean wasRemoved = expectedPackageLookAlikeChildren.remove(name);
				
				if (wasRemoved) {
					System.out.println("Removed \"" + name + "\" from expectedPackageLookAlikeChildren.");
				}
				else {
					System.out.println("\"" + name + "\" was not present in expectedPackageLookAlikeChildren.");
				}
			}
		}
		
		boolean expectedPackageLookAlikeChildrenIsEmpty = expectedPackageLookAlikeChildren.isEmpty();
		
		assertTrue("Could not find all expected files in package_look_alike", expectedPackageLookAlikeChildrenIsEmpty);

		/*
		 * test the empty_folder IS empty
		 */
		boolean emptyFolderHasChildren = empty_folder.hasChildren();
		assertFalse("empty_folder not empty", emptyFolderHasChildren);
		boolean emptyFolderGetChildrenIsEmpty = empty_folder.getChildren().isEmpty();
		assertTrue("empty_folder returns children", emptyFolderGetChildrenIsEmpty);
	}
}
