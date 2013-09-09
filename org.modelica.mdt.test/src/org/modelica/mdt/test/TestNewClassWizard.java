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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.junit.Before;
import org.junit.Test;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.wizards.NewClassWizard;
import org.modelica.mdt.ui.wizards.NewTypePage;

import abbot.tester.swt.ButtonTester;
import abbot.tester.swt.ComboTester;
import abbot.tester.swt.TextTester;

/**
 * @author Elmir Jagudin
 *
 * Test the New Class Wizard functionality
 */
public class TestNewClassWizard {
	private static final String PROJECT_NAME_1 = TestNewClassWizard.class.getName() + "1";

	// A project name that is guaranteed to be nonexistent.
	private static final String NON_EXSITING_PROJECT_NAME = TestNewClassWizard.class.getName() + "non_existing_project";

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
	private Button cancel;

	@Before
	public void setUp() throws Exception {
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME_1);

		// Create project only if it does not exist yet.
		if (!project.exists()) {
			IModelicaProject mproj = ModelicaCore.getModelicaRoot().createProject(project);
			assertNotNull("failed to create project", mproj);

			project = mproj.getWrappedProject();
		}

		// Create the selection that points at the root of the created project.
		fileDestination = new StructuredSelection(project);

		// Setup testing support objects.
		ttester = TextTester.getTextTester();
		btester = ButtonTester.getButtonTester();
		ctester = new ComboTester();
	}

	// Don't make this into a test-case of its own since almost every other test case will call this method.
	private void openWizardAndFetchWidgets() {
		// Pop-up the wizard.
		IWizard wizard = Utility.openWizard(org.modelica.mdt.ui.constants.Constants.MDT_UI_WIZARD_NEW_CLASS, fileDestination);
		assertFalse(wizard.canFinish());

		// Fetch widgets.
		className = TextTester.getInstrumentedText(NewClassWizard.CLASS_NAME_TAG);
		sourceFolder = TextTester.getInstrumentedText(NewTypePage.SOURCE_FOLDER_TAG);
		initialEquation = ButtonTester.getInstrumentedButton(NewClassWizard.INITIAL_EQUATION_TAG);
		externalBody = ButtonTester.getInstrumentedButton(NewClassWizard.EXTERNAL_BODY_TAG);
		partialClass = ButtonTester.getInstrumentedButton(NewClassWizard.PARTIAL_CLASS_TAG);
		finish = Utility.findButtonByText("&Finish");
		assertNotNull("Problems finding finish button", finish);
		cancel = Utility.findButtonByText("Cancel");
		assertNotNull("Problems finding cancel button", cancel);

		// Find classType combo by tag.
		classType = (Combo)Utility.getInstrumentedWidget(NewClassWizard.CLASS_TYPE_TAG);
		assertNotNull("Problems finding classType widget", classType);

		// Make some checks on the state of the wizards.
		assertEquals("Wrong source folder selected", PROJECT_NAME_1, sourceFolder.getText());
		assertEquals("Junk present in class name field", "", className.getText());
		assertFalse("initial equation unexpectedly selected", initialEquation.getSelection());
		assertFalse("partial class unexpectedly selected", partialClass.getSelection());
		assertFalse("Finish button not disabled", finish.getEnabled());
	}

	/**
	 * This test makes wizard to display a warning and error messages
	 * by entering invalid data into the fields.
	 *
	 * The test checks that the state of finish button is right,
	 * it should be enabled for warnings and disabled for errors.
	 */
	@Test
	public void testFinishButtonState() {
		// open dialog and set a legal source folder and an illegal class name,
		// which should display a warning
		openWizardAndFetchWidgets();

		ttester.actionEnterText(sourceFolder, PROJECT_NAME_1);
		ttester.actionEnterText(className, "#�%&");
		assertFalse("Finish button should be disabled when an illegal class name is entered", finish.getEnabled());

		// set legal class name and non-existing source folder,
		// which should display an error
		ttester.actionEnterText(className, "legal_class_name");
		ttester.actionEnterText(sourceFolder, NON_EXSITING_PROJECT_NAME);
		assertFalse("Finish button should not be enabled for an error", finish.getEnabled());

		// close the dialog
		btester.actionClick(cancel);
	}

	/**
	 * Check if the wizard 'remembers' its class type selection between different invocations.
	 */
	@Test
	public void testSelectionRetention() {
		// Pop-up wizard, change class restriction to 'type',
		// check that check boxes were updated and close wizard.
		openWizardAndFetchWidgets();
		ctester.actionSelectItem(classType, "type");
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertFalse("partial class check box should be disabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());
		btester.actionClick(cancel);

		// Pop-up wizard again and check that the selection is 'type'
		// and that the status of other widgets is correctly set
		openWizardAndFetchWidgets();
		assertEquals("wrong class type selection", "type", classType.getText());
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertFalse("partial class check box should be disabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());

		// Change class restriction to 'model' and check that check boxes were
		// updated and close dialog.
		ctester.actionSelectItem(classType, "model");
		assertTrue("initial equation check box should be enabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());
		btester.actionClick(cancel);

		// Pop-up wizard again and check that the selection is 'model'
		// and that the status of other widgets is correctly set.
		openWizardAndFetchWidgets();
		assertEquals("wrong class type selection", "model", classType.getText());
		assertTrue("initial equation check box should be enabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());

		// Change class restriction to 'connector', and check that check boxes
		// were updated and close dialog.
		ctester.actionSelectItem(classType, "connector");
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());
		btester.actionClick(cancel);

		// Pop-up wizard again and check that the selection is 'connector'
		// and that the status of other widgets is correctly set.
		openWizardAndFetchWidgets();
		assertEquals("wrong class type selection", "connector", classType.getText());
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());

		// Change class restriction to 'block', and check that check boxes
		// were updated and close dialog.
		ctester.actionSelectItem(classType, "block");
		assertTrue("initial equation check box should be enabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());
		btester.actionClick(cancel);

		// Pop-up wizard again and check that the selection is 'block'
		// and that the status of other widgets is correctly set.
		openWizardAndFetchWidgets();
		assertEquals("wrong class type selection", "block", classType.getText());
		assertTrue("initial equation check box should be enabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());

		// Change class restriction to 'record',
		// check that check boxes were updated and close dialog.
		ctester.actionSelectItem(classType, "record");
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());
		btester.actionClick(cancel);

		// Pop-up wizard again and check that the selection is 'record'
		// and that the status of other widgets is correctly set.
		openWizardAndFetchWidgets();
		assertEquals("wrong class type selection", "record", classType.getText());
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());

		// Change class restriction to 'class',
		// check that check boxes were updated and close dialog.
		ctester.actionSelectItem(classType, "class");
		assertTrue("initial equation check box should be enabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());
		btester.actionClick(cancel);

		// Pop-up wizard again and check that the selection is 'class',
		// check that check boxes were updated and that the status of
		// other widgets is correctly set.
		openWizardAndFetchWidgets();
		assertEquals("wrong class type selection", "class", classType.getText());
		assertTrue("initial equation check box should be enabled", initialEquation.getEnabled());
		assertTrue("partial class check box should be enabled", partialClass.getEnabled());
		assertFalse("external body check box should be disabled", externalBody.getEnabled());

		// Change class restriction to 'function', check that
		// check boxes were updated and close dialog
		ctester.actionSelectItem(classType, "function");
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertFalse("partial class check box should be disabled", partialClass.getEnabled());
		assertTrue("external body check box should be enabled", externalBody.getEnabled());
		btester.actionClick(cancel);

		// Pop-up wizard again and check that the selection is 'function'
		// and that the status of other widgets is correctly set.
		openWizardAndFetchWidgets();
		assertEquals("wrong class type selection", "function", classType.getText());
		assertFalse("initial equation check box should be disabled", initialEquation.getEnabled());
		assertFalse("partial class check box should be disabled", partialClass.getEnabled());
		assertTrue("external body check box should be enabled", externalBody.getEnabled());

		// Close dialog
		btester.actionClick(cancel);
	}

	@Test
	public void testCreateModel() {
		openWizardAndFetchWidgets();

		// Create model
		String name = "m1";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "model");
		assertTrue(finish.getEnabled());

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"model " + name + "\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"end " + name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateModelWithInitEquation() {
		openWizardAndFetchWidgets();

		// Create model
		String name = "m2";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "model");
		assertTrue(finish.getEnabled());

		btester.actionClick(initialEquation);

		assertTrue(initialEquation.getSelection());

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"model "+ name +"\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"initial equation\n"+
								"\n"+
								"end " + name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialModel() {
		openWizardAndFetchWidgets();

		// Create model
		String name = "m3";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "model");
		assertTrue(finish.getEnabled());

		btester.actionClick(partialClass);

		assertTrue(partialClass.getSelection());

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial model "+ name +"\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialModelWithInitBlock() {
		openWizardAndFetchWidgets();

		// Create model
		String name = "m4";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "model");
		assertTrue(finish.getEnabled());

		btester.actionClick(partialClass);
		assertTrue(partialClass.getSelection());

		btester.actionClick(initialEquation);
		assertTrue(initialEquation.getSelection());

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial model "+ name +"\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"initial equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateClass() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "c1";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"class "+ name +"\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateClassWithInitBlock() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "c2";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");
		btester.actionClick(initialEquation);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"class "+ name +"\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"initial equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialClass() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "c3";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");
		btester.actionClick(partialClass);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial class "+ name +"\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialClassWithInitBlock() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "c4";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "class");
		btester.actionClick(partialClass);
		btester.actionClick(initialEquation);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial class "+ name +"\n"+
								"\n"+
								"equation\n"+
								"\n"+
								"initial equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateConnector() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "con1";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "connector");

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"connector "+ name +"\n" +
								"\n" +
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialConnector() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "con2";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "connector");
		btester.actionClick(partialClass);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial connector "+ name +"\n" +
								"\n" +
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateRecord() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "rec1";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "record");

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"record "+ name +"\n" +
								"\n" +
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialRecord() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "rec2";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "record");
		btester.actionClick(partialClass);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial record "+ name +"\n" +
								"\n" +
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateBlock() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "b1";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"block "+ name +"\n" +
								"\n" +
								"equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateBlockWithInitBlock() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "b2";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");
		btester.actionClick(initialEquation);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"block "+ name +"\n" +
								"\n" +
								"equation\n"+
								"\n"+
								"initial equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialBlock() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "b3";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");
		btester.actionClick(partialClass);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial block "+ name +"\n" +
								"\n" +
								"equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreatePartialBlockWithInitBlock() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "b4";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "block");
		btester.actionClick(partialClass);
		btester.actionClick(initialEquation);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"partial block "+ name +"\n" +
								"\n" +
								"equation\n"+
								"\n"+
								"initial equation\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateType() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "t1";
		ttester.actionEnterText(className, name);
		ctester.actionSelectItem(classType, "type");

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"type "+ name +"\n" +
						";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateFunction() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "f1";
		ctester.actionSelectItem(classType, "function");
		ttester.actionEnterText(className, name);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"function "+ name +"\n" +
								"\n" +
								"algorithm\n"+
								"\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	@Test
	public void testCreateFunctionWithExternalBody() {
		openWizardAndFetchWidgets();

		// Create class
		String name = "f2";
		ctester.actionSelectItem(classType, "function");
		ttester.actionEnterText(className, name);

		// Wait for the class type change to propagate to the
		// external body checkbox.
		while (!externalBody.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(externalBody);

		// Wait for the name change to propagate to enable the finish button.
		while (!finish.getEnabled()) {
			Utility.sleep(this, 100);
		}

		btester.actionClick(finish);

		while (!project.isOpen()) {
			Utility.sleep(this, 100);
		}

		// Check that the generated source code is sane.
		boolean same =
				Utility.compareContent(project.getFile(name + ".mo"),
						"function "+ name +"\n" +
								"\n" +
								"external\n"+
								"end "+ name + ";");
		assertTrue("unexpected content created in the source file", same);
	}

	/*
	 * Some ideas for more tests:
	 * TODO enter some text into source folder field and
	 * check the error/warning messages
	 *
	 * TODO test to create a class in a sub-directory
	 */
}
