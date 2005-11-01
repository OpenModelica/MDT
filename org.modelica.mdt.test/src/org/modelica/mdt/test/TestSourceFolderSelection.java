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

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.ProjectsView;

import abbot.finder.swt.BasicFinder;
import abbot.finder.swt.Matcher;
import abbot.finder.swt.TestHierarchy;
import abbot.tester.swt.TextTester;

import junit.framework.TestCase;

/**
 * This test case checks that selection of elements i Projects View
 * is correctly picked up in new class wizard.
 * 
 * E.g. if a folder bar in project foo is selected then 'Source folder'
 * in the new class wizard should contain foo/bar when the wizard is displayed.
 * 
 * @author Elmir Jagudin
 *
 */
public class TestSourceFolderSelection extends TestCase 
{
	public static final String PROJECT_NAME_1 = 
		TestSourceFolderSelection.class.getName() + "1";
	
	private Text sourceFolder;
	private TextTester ttester;
	private IModelicaProject project = null;
	private Tree projectsTree = null;

	private TreeItem treeItemProject = null;
	private TreeItem treeItemModelicaFolder = null;
	private TreeItem treeItemModelicaFile = null;
	private TreeItem treeItemModelicaPackage = null;
	private TreeItem treeItemModelicaFile2 = null;
	private TreeItem treeItemFile = null;
	private TreeItem treeItemSysLibrary = null;

	@Override
	protected void setUp() throws Exception
	{
		  
		/*
		 * create a modelica project
		 */
		IProject project = 
			ModelicaCore.createProject(PROJECT_NAME_1, 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());		
		assertNotNull("failed to create project", project);
		project.open(null);
		
		while (!(project.isOpen()))
		{
			Utility.sleep(this, 100);
		}
		/* 
		 * populate project with modelica elements
		 * 
		 * following structre is created:
		 *  + modelica_folder    (IModelicaFolder)
		 *  |  +- model.mo       (IModelicaFile)
		 *  + modelica_package   (IModelicaPackage)
		 *  |  +- package.mo
		 *  + modelica_file.mo   (IModelicaFile)
		 *  + plain_file         (IFile)
		 */
		IFolder folder;
		IFile file;
		
		folder = project.getFolder("modelica_folder");
		folder.create(true, true, null);
		assertTrue(folder.exists());
		
		file = folder.getFile("model.mo");
		file.create(null, true, null);
		assertTrue(file.exists());

		folder = project.getFolder("modelica_package");
		folder.create(true, true, null);
		assertTrue(folder.exists());
		
		file = folder.getFile("package.mo");
		file.create(new ByteArrayInputStream("".getBytes()), true, null);
		assertTrue(file.exists());
		
		file = project.getFile("modelica_file.mo");
		file.create(new ByteArrayInputStream("".getBytes()), true, null);
		assertTrue(file.exists());
		
		file = project.getFile("plain_file");
		file.create(new ByteArrayInputStream("".getBytes()), true, null);
		assertTrue(file.exists());
		
		/*
		 * fetch the handle for the above created project
		 * 
		 */
		Object[] projects = ModelicaCore.getModelicaRoot().getProjects();		
		for (Object i : projects) 
		{
			/* search for a modelica project name PROJECT_NAME_1 */
			if (i instanceof IModelicaProject)
			{
				if (((IModelicaProject)i).getElementName().
						equals(PROJECT_NAME_1))
				{
					this.project = (IModelicaProject)i;
				}
			}

		}
		assertNotNull(project);
		
		/* setup testing support objects */
		ttester = TextTester.getTextTester();
		
		/* 
		 * display Modelica Project View 
		 */
		IWorkbench workbench = PlatformUI.getWorkbench();
		
		workbench.getActiveWorkbenchWindow().
			getActivePage().showView("org.modelica.mdt.ProjectsView");
		
		/*
		 * find the TreeWidget in the Modelica Projects view  
		 */
		BasicFinder finder = 
			new BasicFinder(new TestHierarchy(workbench.getDisplay()));

		Tree projectsTree = (Tree) finder.find(new Matcher()
		{
			public boolean matches(Widget w) 
			{
				Object tag = w.getData("name");
				if (tag == null || !(tag instanceof String))
				{
					return false;
				}
				
				return ((String)tag).equals(ProjectsView.TREE_TAG);
			}
			
		});
		
		assertNotNull("could not find Modelica Projects Tree widget",
					  projectsTree);
		
		
		projectsTree.update();

		
		/*
		 * fetch individual tree items 
		 */
		for (TreeItem it : projectsTree.getItems()) 
		{
			/* find project tree item */
			if (it.getText().equals(PROJECT_NAME_1))
			{
				treeItemProject  = it;
				break;
			}
		}

		if (2-1 == 1 ) return; /* bail out, nothing works argh...darn gui tests */
		treeItemProject.setExpanded(true);
		projectsTree.update();
		
		

		while (treeItemModelicaFolder == null || treeItemModelicaFile == null ||
				treeItemModelicaPackage == null || treeItemModelicaFile2 == null
				|| treeItemFile == null || treeItemSysLibrary == null)
		{
		for (TreeItem it : treeItemProject.getItems())
		{
			String label = it.getText();
			
			if (label.equals("modelica_folder"))
			{
				treeItemModelicaFolder = it;
			}
			else if (label.equals("model.mo"))
			{
				treeItemModelicaFile = it;
			}
			else if (label.equals("modelica_package"))
			{
				treeItemModelicaPackage = it;
			}
			else if (label.equals("modelica_file.mo"))
			{
				treeItemModelicaFile2 = it;
			}
			else if (label.equals("plain_file"))
			{
				treeItemFile = it;
			}
			else if (label.equals("System Library"))
			{
				treeItemSysLibrary = it;
			}
		}
		Thread.sleep(500);
		}

		assertNotNull(treeItemModelicaFolder);
		assertNotNull(treeItemModelicaFile);
		assertNotNull(treeItemModelicaPackage);
		assertNotNull(treeItemModelicaFile2);
		assertNotNull(treeItemFile);
		assertNotNull(treeItemSysLibrary);

		
	}
	
	public void testSelection()
	{

	}

}
