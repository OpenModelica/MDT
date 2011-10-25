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

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.wizards.IWizardCategory;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

import junit.framework.TestCase;

public class TestPerspective extends TestCase { 
	private Vector<String> wizardShortcuts = new Vector<String>();
	private Vector<String> showViewShortcuts = new Vector<String>();
	
	@Override
	protected void setUp() throws Exception {
		/*
		 * Initialize the vector with expected new wizard shortcuts in the Modelica perspective.
		 */				
		assertTrue(Collections.addAll(wizardShortcuts,
				/* Modelica wizards. */
				org.modelica.mdt.ui.constants.Constants.MDT_UI_WIZARD_NEW_CLASS,
				org.modelica.mdt.ui.constants.Constants.MDT_UI_WIZARD_NEW_PACKAGE,
				org.modelica.mdt.ui.constants.Constants.MDT_UI_WIZARD_NEW_PROJECT,
				/* Generic wizards. */
				"org.eclipse.ui.wizards.new.folder",
				"org.eclipse.ui.wizards.new.file"));

		/*
		 * Initialize the vector with expected new view shortcuts in the Modelica perspective.
		 */				
		assertTrue(Collections.addAll(showViewShortcuts,
				/* Modelica views. */
				org.modelica.mdt.ui.constants.Constants.MDT_UI_VIEW_PROJECTS,
				org.modelica.mdt.ui.constants.Constants.MDT_UI_VIEW_CONSOLE,
				/* Generic views. */
				IPageLayout.ID_PROBLEM_VIEW));
	}


	/**
	 * perform tests on Modelica perspective
	 * @throws WorkbenchException
	 */
	public void testModelicaPerspective() throws WorkbenchException {
		IWorkbench workbench = PlatformUI.getWorkbench(); 
		
		/*
		 * Open Modelica perspective.
		 */
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow(); 
		workbench.showPerspective(org.modelica.mdt.ui.constants.Constants.MDT_UI_PERSPECTIVE_MODELICA, window); 
		
		IWorkbenchPage workbenchPage = window.getPages()[0];

		/*
		 * Check that all 'new wizards' shortcuts are installed.
		 */
		String[] actualWizShortcuts = workbenchPage.getNewWizardShortcuts();

		assertTrue(Arrays.asList(actualWizShortcuts).containsAll(wizardShortcuts));		

		/*
		 * Check that all 'show view' shortcuts are installed.
		 */
		String[] actualShowViewShortcuts = workbenchPage.getShowViewShortcuts();

		assertTrue(Arrays.asList(actualShowViewShortcuts).containsAll(showViewShortcuts));

		/*
		 * Check that all views are present.
		 */
		IViewPart projectsView = workbenchPage.findView(org.modelica.mdt.ui.constants.Constants.MDT_UI_VIEW_PROJECTS);
		IViewPart problemView = workbenchPage.findView(IPageLayout.ID_PROBLEM_VIEW);
		
		assertNotNull(projectsView);
		assertNotNull(problemView);
	}


	/**
	 * Perform tests on Modelica new wizards category.
	 * @throws WorkbenchException
	 */
	public void testModelicaNewWizardsCatagory() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWizardRegistry wizardRegistry = workbench.getNewWizardRegistry();
		
		IWizardCategory category = wizardRegistry.findCategory(org.modelica.mdt.ui.constants.Constants.MDT_UI_MODELICA_CATEGORY);
				
		assertNotNull("Modelica New Wizards category not found", category);
		
		String actualCategoryLabel = category.getLabel();
		
		assertEquals("Modelica New Wizards category labeled unexpectedly", "Modelica", actualCategoryLabel);
		
		IWizardDescriptor wizDescNewProj = category.findWizard(org.modelica.mdt.ui.constants.Constants.MDT_UI_WIZARD_NEW_PROJECT);
		
		assertNotNull("New Project Wizard not found", wizDescNewProj);

		IWizardDescriptor wizDescNewClass = category.findWizard(org.modelica.mdt.ui.constants.Constants.MDT_UI_WIZARD_NEW_CLASS);

		assertNotNull("New Class Wizard not found", wizDescNewClass);
	}
}
