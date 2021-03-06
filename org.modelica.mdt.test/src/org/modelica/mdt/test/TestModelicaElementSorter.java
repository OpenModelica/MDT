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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.junit.Before;
import org.junit.Test;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.ModelicaElementComparator;

/**
 * Test the ModelicaElementSorter
 */
public class TestModelicaElementSorter {

	@Before
	public void setUp() {
		Area51Projects.createProjects();
	}

	@Test
	public void testSorter()
		throws ConnectException, UnexpectedReplyException, InvocationError, CoreException, CompilerInstantiationException {
		/*
		 * let the sorter sort children elements of Area51 modelica project
		 * and check the order
		 */
		IModelicaProject proj = Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);

		Object[] children = proj.getRootFolder().getChildren().toArray();

		/*
		 * we need to create a viewer and set a label provider,
		 * for the sorter. sorter uses label provider for sorting elements
		 * within a category
		 */
		TreeViewer viewer = new TreeViewer(new Shell());
		viewer.setLabelProvider(new WorkbenchLabelProvider());

		/*
		 * let the sorter do the sorting
		 */
		ModelicaElementComparator sorter = new ModelicaElementComparator(ModelicaElementComparator.MODELICA);
		sorter.sort(viewer, children);

		int i = 0;
		int order;
		String previous = null;
		String current;

		/* check folders category */
		for (; i < children.length; i++) {
			Object elm = children[i];
			if (elm instanceof IModelicaFolder) {
				IModelicaFolder modelicaFolder = (IModelicaFolder)elm;
				current = modelicaFolder.getElementName();
				if (previous != null) {
					order = String.CASE_INSENSITIVE_ORDER.compare(previous, current);

					assertTrue("folder elements are not in assending order", order <= 0);
				}
				previous = current;
			}
			else {
				/* next category begun */
				previous = null;
				break;
			}
		}

		/* check packages category */
		for (; i < children.length; i++) {
			Object elm = children[i];
			if (elm instanceof IModelicaClass) {
				IModelicaClass modelicaClass = (IModelicaClass)elm;
				current = modelicaClass.getElementName();
				if (previous != null) {
					order = String.CASE_INSENSITIVE_ORDER.compare(previous, current);

					assertTrue("class elements are not in assending order", order <= 0);
				}
				previous = current;
			}
			else if (elm instanceof IModelicaFolder) {
				fail("modelica folder in the wrong place");
			}
			else {
				/* next category begun */
				previous = null;
				break;
			}
		}

		/*
		 * we don't have a class category in this test case
		 */

		/* check modelica files category */
		for (; i < children.length; i++) {
			Object elm = children[i];
			if (elm instanceof IModelicaSourceFile) {
				IModelicaSourceFile modelicaSourceFile = (IModelicaSourceFile)elm;
				current = modelicaSourceFile.getElementName();
				if (previous != null) {
					order = String.CASE_INSENSITIVE_ORDER.compare(previous, current);

					assertTrue("sourcefile elements are not in assending order", order <= 0);
				}
				previous = current;
			}
			else if (elm instanceof IModelicaFolder) {
				fail("modelica folder/package in the wrong place");
			}
			else if (elm instanceof IModelicaClass) {
				fail("modelica class in the wrong place");
			}
			else {
				/* next category begun */
				previous = null;
				break;
			}
		}

		/* check plain files category */
		for (; i < children.length; i++) {
			Object elm = children[i];
			if (elm instanceof IFile) {
				IFile file = (IFile)elm;
				current = file.getName();
				if (previous != null) {
					order = String.CASE_INSENSITIVE_ORDER.compare(previous, current);

					assertTrue("file elements are not in assending order", order <= 0);
				}
				previous = current;
			}
			else if (elm instanceof IModelicaFolder) {
				fail("modelica folder/package in the wrong place");
			}
			else if (elm instanceof IModelicaClass) {
				fail("modelica class in the wrong place");
			}
			else if (elm instanceof IModelicaSourceFile) {
				fail("modelica class in the wrong place");
			}
			else {
				/* next category begun */
				previous = null;
				break;
			}
		}

		/*
		 * we don't have a system library category in this test case
		 */
	}
}
