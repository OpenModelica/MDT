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

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.NewClassWizard;
import org.modelica.mdt.core.ModelicaCore;

import abbot.tester.swt.ButtonTester;
import abbot.tester.swt.ComboTester;
import abbot.tester.swt.TextTester;

import junit.framework.TestCase;

/**
 * @author Elmir Jagudin
 *
 * Test the New Class Wizard functionality
 */
public class TestNewClassWizard extends TestCase 
{
	private static final String PROJECT_NAME = "testNewCLassWizard";
	private StructuredSelection fileDestination; 
	
	private IProject project;
	private TextTester ttester;
	private ButtonTester btester;
	
	private Text sourceFolder;
	private Text className;
	private Combo classType;
	private Button initialEquation;
	private Button partialClass;
	private Button finish;
	
	

	@Override
	protected void setUp() throws Exception
	{
		/*
		 * setup project
		 */
		project = 
			ModelicaCore.createProject(PROJECT_NAME,
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		assertNotNull("failed to create project", project);
		
		/* 
		 * create the selection that points at the root of the created project 
		 */
		fileDestination = 
			new StructuredSelection(ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME));
		
		/*
		 * setup some testing support objects
		 */
		ttester = TextTester.getTextTester();
		btester = ButtonTester.getButtonTester();
				
	}
	
	private boolean compareContent(IFile file, String expectedContent)
	{
		InputStream fileContent = null;
		
		try
		{
			fileContent = file.getContents();
		}
		catch (CoreException e) 
		{
			fail("could not fetch contents of the created class");
		}

		byte[] buf = new byte[expectedContent.length()];
		
		try
		{
			fileContent.read(buf);
			int i = fileContent.read();
			
			assertEquals("file is to long", i, -1);
		}
		catch (IOException e)
		{
			fail("could not read contents of the file");
		}
		
		return expectedContent.equals(new String(buf));
	}

	
	public void openWizardAndFetchWidgets()
	{
		/*
		 * pop-up the wizard
		 */
		IWizard wizard = 
			Utility.openWizard("org.modelica.mdt.NewClassWizard", fileDestination);
		assertFalse(wizard.canFinish());

		/* fetch widgets */
		className = TextTester.getInstrumentedText(NewClassWizard.CLASS_NAME_TAG);
		sourceFolder = TextTester.getInstrumentedText(NewClassWizard.SOURCE_FOLDER_TAG);
		initialEquation = ButtonTester.getInstrumentedButton(NewClassWizard.INITIAL_EQUATION_TAG);				
		partialClass =  ButtonTester.getInstrumentedButton(NewClassWizard.PARTIAL_CLASS_TAG);
		finish = Utility.findFinishButton();
		
		/* make some checks on the state of the wizards */
		assertEquals("Wrong source folder selected", 
				sourceFolder.getText(), PROJECT_NAME);		
		assertEquals("Junk present in class name field", className.getText(), "");
		assertFalse("initial equation unexpectedly selected", initialEquation.getSelection());
		assertFalse("partial class unexpectedly selected", partialClass.getSelection());
		assertFalse("Finish button not disabled", finish.getEnabled());
		

	}
	
	public void testCreateModel()
	{
		openWizardAndFetchWidgets();		
		
		/*
		 * create model
		 */
		ttester.actionEnterText(className, "m1");
		assertTrue(finish.getEnabled());

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }		
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			compareContent(project.getFile("m1.mo"), 
				"model m1\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"end m1;");
		assertTrue("unexpected conted created in the source file", same);
	
	}
		
	public void testCreateModelWithInitEquation()
	{
		openWizardAndFetchWidgets();		
		
		/*
		 * create model
		 */
		ttester.actionEnterText(className, "m2");
		assertTrue(finish.getEnabled());
		
		btester.actionClick(initialEquation);
		
		assertTrue(initialEquation.getSelection());

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			compareContent(project.getFile("m2.mo"), 
				"model m2\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"initial equation\n"+
				"\n"+
				"end m2;");
		assertTrue("unexpected conted created in the source file", same);
	}

	public void testCreatePartialModel()
	{
		openWizardAndFetchWidgets();		
		
		String name = "m3";
		/*
		 * create model
		 */
		ttester.actionEnterText(className, name);
		assertTrue(finish.getEnabled());
		
		btester.actionClick(partialClass);
		
		assertTrue(partialClass.getSelection());

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			compareContent(project.getFile(name + ".mo"), 
				"partial model "+ name +"\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	
	}

	public void testCreatePartialModelWithInitBlock()
	{
		openWizardAndFetchWidgets();		
		
		String name = "m4";
		/*
		 * create model
		 */
		ttester.actionEnterText(className, name);
		assertTrue(finish.getEnabled());
		
		btester.actionClick(partialClass);		
		assertTrue(partialClass.getSelection());

		btester.actionClick(initialEquation);		
		assertTrue(initialEquation.getSelection());
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			compareContent(project.getFile(name + ".mo"), 
				"partial model "+ name +"\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"initial equation\n"+
				"\n"+
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	
		
	}

	public void testCreateClass()
	{
	
	}

	public void testCreateClassWithInitBlock()
	{
		
	}

	public void testCreatePartialClass()
	{
		
	}

	public void testCreatePartialClassWithInitBlock()
	{
		
	}
	
	
	public void testCreateConnector()
	{
		
	}

	public void testCreatePartialConnector()
	{
		
	}

	public void testCreateBlock()
	{
		
	}
	public void testCreateBlockWithInitBlock()
	{
		
	}

	public void testCreatePartialBlock()
	{
		
	}
	public void testCreatePartialBlockWithInitBlock()
	{
		
	}

	public void testCreateType()
	{
		
	}

	public void testCreateFunction()
	{
		
	}

	public void testCreateFunctionWithExternalBody()
	{
		
	}

	 //some ideas for more tests
		/*
		 * TODO enter some text into source folder field and
		 * check the error/warning messages 
		 */
		
		/*
		 * TODO test to create a class in a sub-directory 
		 */
}
