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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.wizards.NewClassWizard;

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
	private static final String PROJECT_NAME_1 = 
			TestNewClassWizard.class.getName() + "1";
	private StructuredSelection fileDestination; 
	
	private IProject project;
	
	private TextTester ttester;
	private ButtonTester btester;
	private ComboTester ctester;

	private Text sourceFolder;
	private Text className;
	private Combo classType;
	private Button initialEquation;
	private Button partialClass;
	private Button externalBody;
	
	private Button finish;
	

	@Override
	//protected void setUp() throws Exception
	public void setUp() throws Exception
	{
		/*
		 * setup project
		 */
		project = 
			ModelicaCore.createProject(PROJECT_NAME_1,
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		assertNotNull("failed to create project", project);
		
		/* 
		 * create the selection that points at the root of the created project 
		 */
		fileDestination = 
			new StructuredSelection(ResourcesPlugin.getWorkspace().getRoot().
					getProject(PROJECT_NAME_1));
		
		/*
		 * setup testing support objects
		 */
		ttester = TextTester.getTextTester();
		btester = ButtonTester.getButtonTester();
		ctester = new ComboTester();
				
	}
	

	
	public void openWizardAndFetchWidgets()
	{
		/*
		 * pop-up the wizard
		 */
		IWizard wizard = 
			Utility.openWizard("org.modelica.mdt.NewClassWizard",
					fileDestination);		
		assertFalse(wizard.canFinish());

		
		
		/* fetch widgets */
		className = 
			TextTester.getInstrumentedText(NewClassWizard.CLASS_NAME_TAG);
		sourceFolder = 
			TextTester.getInstrumentedText(NewClassWizard.SOURCE_FOLDER_TAG);
		initialEquation = 
			ButtonTester.getInstrumentedButton(NewClassWizard.INITIAL_EQUATION_TAG);
		externalBody =
			ButtonTester.getInstrumentedButton(NewClassWizard.EXTERNAL_BODY_TAG);
		partialClass =  
			ButtonTester.getInstrumentedButton(NewClassWizard.PARTIAL_CLASS_TAG);
		finish = 
			Utility.findFinishButton();

		/* find classType combo by tag */
		classType = 
			(Combo)
				Utility.getInstrumentedWidget(NewClassWizard.CLASS_TYPE_TAG);
		assertNotNull("Problems finding classType widget", classType);
		
		
		/* make some checks on the state of the wizards */
		assertEquals("Wrong source folder selected", 
				sourceFolder.getText(), PROJECT_NAME_1);		
		assertEquals("Junk present in class name field", 
				className.getText(), "");
		assertFalse("initial equation unexpectedly selected",
				initialEquation.getSelection());
		assertFalse("partial class unexpectedly selected",
				partialClass.getSelection());
		assertFalse("Finish button not disabled", 
				finish.getEnabled());
		

	}
	
	public void testCreateModel()
	{
		openWizardAndFetchWidgets();		
		
		String name = "m1";
		/*
		 * create model
		 */
		ttester.actionEnterText(className, name);
		assertTrue(finish.getEnabled());

		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }		
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"model " + name + "\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"end " + name + ";");
		assertTrue("unexpected conted created in the source file", same);
	
	}
		
	public void testCreateModelWithInitEquation()
	{
		openWizardAndFetchWidgets();
		
		String name = "m2";	
		
		/*
		 * create model
		 */
		ttester.actionEnterText(className, name);
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
			Utility.compareContent(project.getFile(name + ".mo"), 
				"model "+ name +"\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"initial equation\n"+
				"\n"+
				"end " + name + ";");
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
			Utility.compareContent(project.getFile(name + ".mo"), 
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
			Utility.compareContent(project.getFile(name + ".mo"), 
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
		openWizardAndFetchWidgets();		
		
		String name = "c1";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"class "+ name +"\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	}

	public void testCreateClassWithInitBlock()
	{
		openWizardAndFetchWidgets();
		
		String name = "c2";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");
		btester.actionClick(initialEquation);
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"class "+ name +"\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"initial equation\n"+
				"\n"+
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
		
	}

	public void testCreatePartialClass()
	{
		openWizardAndFetchWidgets();
		
		String name = "c3";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");
		btester.actionClick(partialClass);
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"partial class "+ name +"\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	}

	public void testCreatePartialClassWithInitBlock()
	{
		openWizardAndFetchWidgets();
		
		String name = "c4";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");
		btester.actionClick(partialClass);
		btester.actionClick(initialEquation);
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"partial class "+ name +"\n"+
				"\n"+
				"equation\n"+
				"\n"+
				"initial equation\n"+
				"\n"+				
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
		
	}
	
	
	public void testCreateConnector()
	{
		openWizardAndFetchWidgets();
		
		String name = "con1";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "connector");
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"connector "+ name +"\n" +
				"\n" +
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	}

	public void testCreatePartialConnector()
	{
		openWizardAndFetchWidgets();
		
		String name = "con2";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "connector");
		btester.actionClick(partialClass);		
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"partial connector "+ name +"\n" +
				"\n" +
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
		
	}
	
	public void testCreateRecord()
	{
		openWizardAndFetchWidgets();
		
		String name = "rec1";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "record");

		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"record "+ name +"\n" +
				"\n" +
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
		
	}
	
	public void testCreatePartialRecord()
	{
		openWizardAndFetchWidgets();
		
		String name = "rec2";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "record");
		btester.actionClick(partialClass);
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"partial record "+ name +"\n" +
				"\n" +
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	}

	public void testCreateBlock()
	{
		openWizardAndFetchWidgets();
		
		String name = "b1";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");

		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"block "+ name +"\n" +
				"\n" +
				"equation\n"+
				"\n"+
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
		
	}
	public void testCreateBlockWithInitBlock()
	{
		openWizardAndFetchWidgets();
		
		String name = "b2";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");
		btester.actionClick(initialEquation);		

		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"block "+ name +"\n" +
				"\n" +
				"equation\n"+
				"\n"+
				"initial equation\n"+
				"\n"+								
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
		
	}

	public void testCreatePartialBlock()
	{
		openWizardAndFetchWidgets();
		
		String name = "b3";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");
		btester.actionClick(partialClass);		

		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"partial block "+ name +"\n" +
				"\n" +
				"equation\n"+
				"\n"+								
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	
	}
	public void testCreatePartialBlockWithInitBlock()
	{
		openWizardAndFetchWidgets();
		
		String name = "b4";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");
		btester.actionClick(partialClass);
		btester.actionClick(initialEquation);		

		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"partial block "+ name +"\n" +
				"\n" +
				"equation\n"+
				"\n"+
				"initial equation\n"+
				"\n"+				
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	}

	public void testCreateType()
	{
		openWizardAndFetchWidgets();
		
		String name = "t1";
		
		/*
		 * create class
		 */
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "type");

		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"type "+ name +"\n" +
				";");
		assertTrue("unexpected conted created in the source file", same);
		
	}

	public void testCreateFunction()
	{
		openWizardAndFetchWidgets();
		
		String name = "f1";
		
		/*
		 * create class
		 */
		ctester.actionSelectItem(classType, "function");
		ttester.actionEnterText(className, name);
		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"function "+ name +"\n" +
				"\n" +
				"algorithm\n"+
				"\n"+				
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
	}

	public void testCreateFunctionWithExternalBody()
	{
		openWizardAndFetchWidgets();
		
		String name = "f2";
		
		/*
		 * create class
		 */
		ctester.actionSelectItem(classType, "function");
		ttester.actionEnterText(className, name);

		/*
		 * wait for the class type change to propogate to the
		 * external body  checkboc
		 */
		while (!externalBody.getEnabled()) { Utility.sleep(this, 100); }

		btester.actionClick(externalBody);		
		
		/* wait for the name change to propogate to enable the finish button */
		while (!finish.getEnabled()) { Utility.sleep(this, 100); }
		btester.actionClick(finish);
		
		
		while(!project.isOpen()){ Utility.sleep(this, 100); }
		
		/*
		 * check that the generated source code is sane
		 */
		boolean same = 
			Utility.compareContent(project.getFile(name + ".mo"), 
				"function "+ name +"\n" +
				"\n" +
				"external\n"+
				"end "+ name + ";");
		assertTrue("unexpected conted created in the source file", same);
		
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
