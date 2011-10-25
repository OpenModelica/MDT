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
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.viewers.Viewer;

import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;

import junit.framework.TestCase;

/**
 * @author Elmir Jagudin
 */
public class TestModelicaRoot extends TestCase {

	/* this flags are set from the workspace change listener */
	private boolean simpleProjectAdded = false;
	private boolean modelicaProjectAdded = false;
	private boolean simpleProjectRemoved = false;
	private boolean modelicaProjectRemoved = false;


	public class WorkspaceChangesListener implements IModelicaElementChangeListener {

		public void setViewer(Viewer viewer) {
			// Empty.
		}


		public void elementsChanged(Collection<IModelicaElementChange> changes) {
			for (IModelicaElementChange change : changes) {
				IModelicaElement res = change.getElement();

				if (!(res instanceof IModelicaProject)) {
					/* we are only interested in changes on projects */
					break;
				}

				IModelicaProject proj = (IModelicaProject)res;
				String projectElementName = proj.getElementName();

				ChangeType changeType = change.getChangeType();

				switch (changeType) {
				case ADDED: 
					if (projectElementName.equals(PROJECT_NAME_SIM_EXTRA)) {
						simpleProjectAdded = true;
					}
					else if (projectElementName.equals(PROJECT_NAME_MOD_EXTRA)) {
						modelicaProjectAdded = true;
					}

					break;
				case REMOVED:
					if (projectElementName.equals(PROJECT_NAME_SIM_EXTRA)) {
						simpleProjectRemoved = true;
					}
					else if (projectElementName.equals(PROJECT_NAME_MOD_EXTRA)) {
						modelicaProjectRemoved = true;
					}
				}
			}
		}
	}

	/* this flag is set from folder to package morph detector */
	private boolean morphedToPackage = false;

	/**
	 * This class detect when the MORPH_FOLDER folder have morped into
	 * a package by listening to modelica change events.
	 * 
	 * To engage this class register it as a listener with modelica root. When
	 * the morph is detected the morphedToPackage flag is set.
	 */
	public class FolderToPackageMorphDetector implements IModelicaElementChangeListener {

		private boolean folderRemoved = false;
		private boolean packageAdded = false;

		public void setViewer(Viewer viewer) {
			// Empty.
		}


		public void elementsChanged(Collection<IModelicaElementChange> changes) {
			for (IModelicaElementChange ch : changes) {
				IModelicaElement element = ch.getElement();
				String elementName = element.getElementName();

				if (elementName.equals(MORPH_FOLDER)) {
					ChangeType changeType = ch.getChangeType();

					/* 
					 * we are waiting for a removed event for the
					 * folder event and added event for the package object
					 */
					switch(changeType) {
					case REMOVED:
						folderRemoved = true;
						break;
					case ADDED:
						assertTrue("element morphed into something wierd",
								element instanceof IModelicaClass);
						packageAdded = true;
						break;
					}

					/* 
					 * when both removed and added events recieved we know
					 * that the folder have morphed
					 */
					morphedToPackage = (folderRemoved && packageAdded);
				}
			}
		}
	}

	/* this flag is set from folder to package morph detector */
	private boolean morphedToFolder = false;

	/**
	 * This class detect when the MORPH_FOLDER package have morped back into
	 * a folder by listening to modelica change events.
	 * 
	 * To engage this class register it as a listener with modelica root. When
	 * the morph is detected the morphedToFolder flag is set.
	 */
	public class PackageToFolderMorphDetector implements IModelicaElementChangeListener {

		private boolean packageRemoved = false;
		private boolean folderAdded = false;

		public void setViewer(Viewer viewer) {
			// Empty.
		}


		public void elementsChanged(Collection<IModelicaElementChange> changes) {
			for (IModelicaElementChange ch : changes) {
				IModelicaElement element = ch.getElement();
				String elementName = element.getElementName();

				if (elementName.equals(MORPH_FOLDER)) {
					ChangeType changeType = ch.getChangeType();

					/* 
					 * we are waiting for a removed event for the
					 * folder event and added event for the package object
					 */
					switch(changeType)
					{
					case REMOVED:
						packageRemoved = true;
						break;
					case ADDED:
						assertTrue("element morphed into something wierd",
								element instanceof IModelicaFolder);
						folderAdded = true;
						break;
					}

					/* 
					 * when both removed and added events recieved we know
					 * that the folder have morphed
					 */
					morphedToFolder = (packageRemoved && folderAdded);
				}
			}
		}
	}

	/* names of modelica projects */
	private Vector<String> modelicaProjects = new Vector<String>();

	/* names of simple (no modelica) projects */
	private Vector<String> simpleProjects = new Vector<String>();

	private static final String PROJECT_NAME_1 = TestModelicaRoot.class.getName() + "1";
	private static final String PROJECT_NAME_2 = TestModelicaRoot.class.getName() + "2";
	private static final String PROJECT_NAME_3 = TestModelicaRoot.class.getName() + "3";

	private static final String MORPH_FOLDER = "morph_folder";

	/* the SIMple project which is added and removed */
	private static final String PROJECT_NAME_SIM_EXTRA = TestModelicaRoot.class.getName() + "_SIM";

	/* the MODelica project which is added and removed */
	private static final String PROJECT_NAME_MOD_EXTRA = TestModelicaRoot.class.getName() + "_MOD";

	/* name of a non existent project */
	private static final String PROJECT_NAME_NON_EXISTENT = TestModelicaRoot.class.getName() + "_NON_EXISTENT";


	private IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();	
	private IModelicaRoot modelicaRoot = ModelicaCore.getModelicaRoot();

	private IFolder morphFolder = null;


	@Override
	protected void setUp() throws CoreException {
		Area51Projects.createProjects();

		/*
		 * create a modelica project
		 */
		IProject project = workspaceRoot.getProject(PROJECT_NAME_1);

		if (!project.exists()) {
			IModelicaProject moProj = modelicaRoot.createProject(project); 
			assertNotNull("failed to create project", moProj);
			project = moProj.getWrappedProject();
			/*
			 * create the folder that will be morphed to a package and then
			 * back to folder (see testMorphing())
			 */
			IFolder folder = project.getFolder(new Path(MORPH_FOLDER));
			folder.create(false, true, null);

		}

		morphFolder = project.getFolder(new Path(MORPH_FOLDER));

		/*
		 * create a regular projects
		 */
		project = workspaceRoot.getProject(PROJECT_NAME_2);

		if (!project.exists()) {
			project.create(null);
			project.open(null);
		}

		project = workspaceRoot.getProject(PROJECT_NAME_2);

		/*
		 * create a regular projects which remains closed
		 */
		project = workspaceRoot.getProject(PROJECT_NAME_3);

		if (!project.exists()) {
			project.create(null);
		}

		assertTrue(Collections.addAll(modelicaProjects, PROJECT_NAME_1));
		assertTrue(Collections.addAll(simpleProjects, PROJECT_NAME_2, PROJECT_NAME_3));
	}


	/**
	 * Test if IModelicaRoot.getProjects() (implemented by ModelicaRoot) 
	 * works as prescribed. 
	 */
	public void testGetProjects() throws CoreException {
		IModelicaProject[] projects = modelicaRoot.getProjects();

		for (IModelicaProject mproj : projects) {
			String projectElementName = mproj.getElementName();
			IProject wrappedProject = mproj.getWrappedProject();
			boolean projectIsOpen = wrappedProject.isOpen();

			if (modelicaProjects.contains(projectElementName) && projectIsOpen) {
				boolean hasNature = wrappedProject.hasNature(CorePlugin.MODELICA_NATURE);
				assertTrue("project without modelica nature wrapped", hasNature);
			}
			else if (simpleProjects.contains(projectElementName) && projectIsOpen) {
				boolean hasNature = wrappedProject.hasNature(CorePlugin.MODELICA_NATURE);
				assertFalse("project with modelica nature not wrapped", hasNature);
			}
		}
	}


	/**
	 * Test if IModelicaRoot.getProject() (implemented by ModelicaRoot) 
	 * works as prescribed. 
	 */
	public void testGetProject() {
		Utility.listAllProjects();
		
		{
			IModelicaProject proj1 = modelicaRoot.getProject(PROJECT_NAME_1);
			String proj1ElementName = proj1.getElementName();
			assertEquals(PROJECT_NAME_1, proj1ElementName);
		}

		{
			IModelicaProject proj3 = modelicaRoot.getProject(PROJECT_NAME_3);
			String proj3ElementName = proj3.getElementName();
			assertEquals(PROJECT_NAME_3, proj3ElementName);
		}

		{
			IModelicaProject proj2 = modelicaRoot.getProject(PROJECT_NAME_2);
			String proj2ElementName = proj2.getElementName();
			assertEquals(PROJECT_NAME_2, proj2ElementName);
		}

		{
			IModelicaProject projNoneExistent = modelicaRoot.getProject(PROJECT_NAME_NON_EXISTENT);
			assertNull("hmm, an non-existent project found?", projNoneExistent);
		}
	}


	/**
	 * Test adding and removing projects to the workspace and check
	 * if ModelicaRoot picks up the changes.
	 */
	public void testChangesToWorkspace() throws CoreException {
		modelicaRoot.addModelicaElementChangeListener(new WorkspaceChangesListener());

		/* 
		 * check if ModelicaRoot picks up additions of projects
		 */
		IProject simpleProject = workspaceRoot.getProject(PROJECT_NAME_SIM_EXTRA);
		simpleProject.create(null);
		simpleProject.open(null);

		IProject modelicaProject = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME_MOD_EXTRA);
		modelicaProject = modelicaRoot.createProject(modelicaProject).getWrappedProject();//modelicaRoot.createProject(simpleProject).getWrappedProject();
		//modelicaProject.open(null);

		/* wait for the changes to kick through */
		while (!simpleProjectAdded && !modelicaProjectAdded) {
			Utility.sleep(this, 105);
		}

		boolean simpleProjFound = false;
		boolean modelicaProjFound = false;
		IModelicaProject[] allProjectsAfterAdding = modelicaRoot.getProjects();

		for (IModelicaProject proj : allProjectsAfterAdding) {
			String name = proj.getElementName();

			if (name.equals(PROJECT_NAME_MOD_EXTRA)) {
				modelicaProjFound = true;
			}
			else if (name.equals(PROJECT_NAME_SIM_EXTRA)) {
				simpleProjFound = true;
			}
		}

		assertTrue("ModelicaRoot did not pick up addition of " + PROJECT_NAME_SIM_EXTRA, simpleProjFound);
		assertTrue("ModelicaRoot did not pick up addition of " + PROJECT_NAME_MOD_EXTRA, modelicaProjFound);

		/* check if ModelicaRoot picks up removal of a project */
		simpleProject.delete(true, true, null);
		modelicaProject.delete(true, true, null);

		/* wait for the changes to kick through */
		while (!simpleProjectRemoved && !modelicaProjectRemoved) {
			Utility.sleep(this, 97);
		}

		IModelicaProject[] allProjectsAfterRemoval = modelicaRoot.getProjects();

		for (IModelicaProject proj : allProjectsAfterRemoval) {
			String name = proj.getElementName();

			if (name.equals(PROJECT_NAME_MOD_EXTRA)) {
				fail("ModelicaRoot did not pick up removal of " + PROJECT_NAME_MOD_EXTRA);
			}
			else if (name.equals(PROJECT_NAME_SIM_EXTRA)) {
				fail("ModelicaRoot did not pick up removal of " + PROJECT_NAME_SIM_EXTRA);
			}
		}
	}


	/**
	 * test that a IModelicaFolder changes to an IModelicaPackage
	 * when a package.mo with correct contents is added to it and
	 * that IModelicaPackage turns back to IModelicaFolder when
	 * the package.mo is removed
	 */
	public void testMorphing() 
			throws CoreException, ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException {
		/* we need to make sure that the folder we are going to morph is loaded */
		Utility.getProject(PROJECT_NAME_1).getRootFolder().getChildren();

		/*
		 * check if folder will morph into a package when the package.mo
		 * file is added
		 */
		IModelicaElementChangeListener listener = new FolderToPackageMorphDetector();

		modelicaRoot.addModelicaElementChangeListener(listener);

		IFile file = morphFolder.getFile("package.mo");
		String contents = 
				"package " + MORPH_FOLDER + "\n" +
						"\n" +
						"end " + MORPH_FOLDER + ";";

		file.create(Utility.getByteStream(contents), true, null);


		/* wait tops 7 seconds for the morph to kick in */
		long waitUntil = System.currentTimeMillis() + 7000;
		while (!morphedToPackage && waitUntil > System.currentTimeMillis()) {
			Utility.sleep(this, 100);
		}

		modelicaRoot.removeModelicaElementChangeListener(listener);
		assertTrue("the folder didn't morph into package, waited for the change around 7 seconds", morphedToPackage); 

		/*
		 * check if package will morph into a folder when the package.mo
		 * file is removed
		 */

		listener = new PackageToFolderMorphDetector();

		modelicaRoot.addModelicaElementChangeListener(listener);
		file.delete(true, false, null);

		/* wait tops 7 seconds for the morph to kick in */
		waitUntil = System.currentTimeMillis() + 7000;
		while (!morphedToFolder && waitUntil > System.currentTimeMillis()) {
			Utility.sleep(this, 100);
		}

		modelicaRoot.removeModelicaElementChangeListener(listener);
		assertTrue("the package didn't morph into folder, waited for the change around 7 seconds", morphedToFolder); 
	}
}
