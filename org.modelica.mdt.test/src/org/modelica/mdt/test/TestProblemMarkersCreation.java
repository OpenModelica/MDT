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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.ModelicaCore;

/**
 * This test that problem markers are created when
 * files with syntax errors are saved to the workspace.
 *
 */
public class TestProblemMarkersCreation {

	private static final String PROJECT_NAME = TestProblemMarkersCreation.class.getName() + "1";
	private static final String UNPROBLEMATIC_FILE = "unproblematic_model.mo";
	private static final String PROBLEMATIC_FILE_1 = "problematic_model_1.mo";

	private static IProject project;

	@BeforeClass
	public static void init() throws CoreException, InterruptedException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		IProject proj = workspaceRoot.getProject(PROJECT_NAME);
		IModelicaRoot modelicaRoot = ModelicaCore.getModelicaRoot();
		IModelicaProject mproj = modelicaRoot.createProject(proj);
		project = mproj.getWrappedProject();
		createFile(UNPROBLEMATIC_FILE);
		createFile(PROBLEMATIC_FILE_1);
		// Give time for marker creation. FIXME: Find a way to simply wait for building to be finished.
		Thread.sleep(5000);
	}

	@Test
	public void testUnproblematicFile() throws CoreException {
		IFile file = project.getFile(UNPROBLEMATIC_FILE);
		IMarker[] markers = file.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
		assertTrue(markers.length == 0);
	}

	@Test
	public void testProblematicFile_1() throws CoreException {
		IFile file = project.getFile(PROBLEMATIC_FILE_1);
		IMarker[] markers = file.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
		assertTrue(markers.length == 1);
		IMarker marker = markers[0];
		assertTrue(marker.exists());
		Integer actualLineNo = (Integer)marker.getAttribute(IMarker.LINE_NUMBER);
		assertEquals(new Integer(2), actualLineNo);
		String actualMessage = (String)marker.getAttribute(IMarker.MESSAGE);
		assertEquals("aj_em_en_un_expected_tooken", actualMessage);
		Integer actualSeverity = (Integer)marker.getAttribute(IMarker.SEVERITY);
		assertEquals(new Integer(IMarker.SEVERITY_ERROR), actualSeverity);
	}

	private static void createFile(String path) throws CoreException {
		IFile file = project.getFile(path);
		InputStream is = TestProblemMarkersCreation.class.getResourceAsStream("/resources/testproblemmarkerscreation/" + path);
		assertNotNull(is);
		file.create(is,  true,  null);
	}
}
