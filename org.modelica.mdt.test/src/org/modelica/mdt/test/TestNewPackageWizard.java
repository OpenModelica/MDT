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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.wizards.NewPackageWizard;
import org.modelica.mdt.ui.wizards.NewTypePage;

import abbot.tester.swt.ButtonTester;
import abbot.tester.swt.TextTester;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Elmir Jagudin
 */

public class TestNewPackageWizard extends TestCase 
{
	private static final String PROJECT_NAME_1 = 
		TestNewPackageWizard.class.getName() + "1";

	private TextTester ttester;
	private ButtonTester btester;
	
	private Text packageName;
	private Text parentPackage;
	private Text packageDesc;
	private Text sourceFolder;
	private Button isEncapsulated;
	private Button finish;
	
	private IProject project;
	private StructuredSelection fileDestination; 

	@Override
	protected void setUp() throws Exception
	{
		/*
		 * setup project
		 */
		project = 
			ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME_1);
		
		/* create project only if it does not exist yet */
		if (!project.exists())
		{
			IModelicaProject mproj = 
				ModelicaCore.getModelicaRoot().createProject(project);//PROJECT_NAME_1);
			Assert.assertNotNull("failed to create project", mproj);
			
			project = mproj.getWrappedProject();
		}

		/* 
		 * create the selection that points at the root of the created project 
		 */
		fileDestination = new StructuredSelection(project);
		
		/*
		 * setup testing support objects
		 */
		ttester = TextTester.getTextTester();
		btester = ButtonTester.getButtonTester();

	}
	
	public void openWizardAndFetchWidgets()
	{
		/*
		 * pop-up the wizard
		 */
		IWizard wizard = 
			Utility.openWizard("org.modelica.mdt.NewPackageWizard",
					fileDestination);		
		assertFalse(wizard.canFinish());

		
		
		/* fetch widgets */
		packageName = 
			TextTester.getInstrumentedText(NewPackageWizard.PACKAGE_NAME_TAG);
		parentPackage = 
			TextTester.getInstrumentedText(NewTypePage.PARENT_PACKAGE_TAG);
		packageDesc = 
			TextTester.getInstrumentedText(NewPackageWizard.PACKAGE_DESC_TAG);
		sourceFolder = 
			TextTester.getInstrumentedText(NewTypePage.SOURCE_FOLDER_TAG);
		isEncapsulated = 
			ButtonTester.getInstrumentedButton(NewPackageWizard.IS_ENCAPSULATED_TAG);
		finish = 
			Utility.findButtonByText("&Finish");

		
		/* make some checks on the state of the wizards */
		assertEquals("Wrong source folder selected", 
				sourceFolder.getText(), PROJECT_NAME_1);		
		assertEquals("Junk present in package name field", 
				packageName.getText(), "");
		assertFalse("is encapsulated unexpectedly selected",
				isEncapsulated.getSelection());
		assertFalse("Finish button not disabled", 
				finish.getEnabled());
		

	}

	/**
	 * create a plain package
	 */
	public void testCreatePackge()
	{
		openWizardAndFetchWidgets();
		
		String name = "pkg1";
		
		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, name);

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);

		/* check if the package folder was created */
		IFolder folder = project.getFolder(name);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		IFile packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		boolean same = 
			Utility.compareContent(packageMo, 
				"package " + name + "\n"+
				"\n"+
				"end " + name + ";");
		assertTrue("unexpected conted created in the package.mo", same);
		
//TODO fix the bug that causes this test below to fail and expand	
// testCreatePackageWithDesc(), testEncapsulatedCreatePackage() and 
// testEncapsulatedCreatePackageWithDesc() tests with creating a subpackage
// after the main package is created
		/*
		 * test to create a nested package inside pkg1
		 */
		
		openWizardAndFetchWidgets();
		
		String subName = "sub_pkg1";
		
		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, subName);
		ttester.actionEnterText(parentPackage, name);

		/*
		 * wait tops 5 seconds for the name change to propogate
		 * to enable the finish button 
		 */
		long waitUntil = System.currentTimeMillis() + 5000;
		while (!finish.getEnabled() && waitUntil > System.currentTimeMillis())
		{
			Utility.sleep(this, 95);
		}
		btester.actionClick(finish);

		/* check if the package folder was created */
		folder = folder.getFolder(subName);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		same = 
			Utility.compareContent(packageMo,
				"within " + name + ";\n" +
				"\n" +
				"package " + subName + "\n" +
				"\n"+
				"end " + subName + ";");
		assertTrue("unexpected conted created in the package.mo", same);

	}
	
	/**
	 * create a plain package with description
	 */
	public void testCreatePackgeWithDesc()
	{
		openWizardAndFetchWidgets();
		
		String name = "pkg2";
		String description = "jolly good package";
		
		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, name);
		ttester.actionEnterText(packageDesc, description);
	

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);

		/* check if the package folder was created */
		IFolder folder = project.getFolder(name);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		IFile packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		boolean same = 
			Utility.compareContent(packageMo, 
				"package " + name + " \"" + description + "\"" + "\n"+
				"\n"+
				"end " + name + ";");
		assertTrue("unexpected conted created in the package.mo", same);
		
		/*
		 * test to create a nested package inside pkg2
		 */
		
		openWizardAndFetchWidgets();
		
		String subName = "sub_pkg2";
		description = "a jolly good subpackage";
		
		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, subName);
		ttester.actionEnterText(parentPackage, name);
		ttester.actionEnterText(packageDesc, description);

		/*
		 * wait tops 5 seconds for the name change to propogate
		 * to enable the finish button 
		 */
		long waitUntil = System.currentTimeMillis() + 5000;
		while (!finish.getEnabled() && waitUntil > System.currentTimeMillis())
		{
			Utility.sleep(this, 95);
		}
		btester.actionClick(finish);

		/* check if the package folder was created */
		folder = folder.getFolder(subName);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		same = 
			Utility.compareContent(packageMo,
				"within " + name + ";\n" +
				"\n" +
				"package " + subName + " \"" + description + "\"" + "\n" +
				"\n"+
				"end " + subName + ";");
		assertTrue("unexpected conted created in the package.mo", same);
	}
	
	/**
	 * create a encapsulated package
	 */
	public void testEncapsulatedCreatePackge()
	{
		openWizardAndFetchWidgets();
		
		String name = "pkg3";
		
		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, name);
		btester.actionClick(isEncapsulated);

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);

		/* check if the package folder was created */
		IFolder folder = project.getFolder(name);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		IFile packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		boolean same = 
			Utility.compareContent(packageMo, 
				"encapsulated package " + name + "\n"+
				"\n"+
				"end " + name + ";");
		assertTrue("unexpected conted created in the package.mo", same);
		
		/*
		 * test to create a nested package inside pkg3
		 */
		
		openWizardAndFetchWidgets();
		
		String subName = "sub_pkg3";
		
		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, subName);
		ttester.actionEnterText(parentPackage, name);
		btester.actionClick(isEncapsulated);

		/*
		 * wait tops 5 seconds for the name change to propogate
		 * to enable the finish button 
		 */
		long waitUntil = System.currentTimeMillis() + 5000;
		while (!finish.getEnabled() && waitUntil > System.currentTimeMillis())
		{
			Utility.sleep(this, 95);
		}
		btester.actionClick(finish);

		/* check if the package folder was created */
		folder = folder.getFolder(subName);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		same = 
			Utility.compareContent(packageMo,
				"within " + name + ";\n" +
				"\n" +
				"encapsulated package " + subName + "\n" +
				"\n"+
				"end " + subName + ";");
		assertTrue("unexpected conted created in the package.mo", same);
	}
	
	/**
	 * create a encapsulated package with description
	 */
	public void testEncapsulatedCreatePackgeWithDesc()
	{
		openWizardAndFetchWidgets();
		
		String name = "pkg4";
		String description = "lebensmittelverpackung mit butter";
		
		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, name);
		btester.actionClick(isEncapsulated);
		ttester.actionEnterText(packageDesc, description);
	

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);

		/* check if the package folder was created */
		IFolder folder = project.getFolder(name);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		IFile packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		boolean same = 
			Utility.compareContent(packageMo, 
				"encapsulated package " + name + " \"" + description + "\"" + "\n"+
				"\n"+
				"end " + name + ";");
		assertTrue("unexpected conted created in the package.mo", same);
		
		/*
		 * test to create a nested package inside pkg4
		 */
		
		openWizardAndFetchWidgets();
		
		String subName = "sub_pkg4";
		description = "deutsche, jaaa";

		/*
		 * fill in the wizard fields
		 */
		ttester.actionEnterText(packageName, subName);
		ttester.actionEnterText(parentPackage, name);
		ttester.actionEnterText(packageDesc, description);
		btester.actionClick(isEncapsulated);
		

		/*
		 * wait tops 5 seconds for the name change to propogate
		 * to enable the finish button 
		 */
		long waitUntil = System.currentTimeMillis() + 5000;
		while (!finish.getEnabled() && waitUntil > System.currentTimeMillis())
		{
			Utility.sleep(this, 95);
		}
		btester.actionClick(finish);

		/* check if the package folder was created */
		folder = folder.getFolder(subName);
		assertTrue("no package folder was created", folder.exists());

		/*
		 * check that the generated package.mo exists and is sane
		 */
		packageMo = folder.getFile("package.mo");
		assertTrue("package.mo was not created", packageMo.exists());
		
		same = 
			Utility.compareContent(packageMo,
				"within " + name + ";\n" +
				"\n" +
				"encapsulated package " + subName + " \"" + description + "\"" + "\n" +
				"\n"+
				"end " + subName + ";");
		assertTrue("unexpected conted created in the package.mo", same);
		
	}
}
