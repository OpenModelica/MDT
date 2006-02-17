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

import java.util.Collections;
import java.util.Vector;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.wizards.IWizardCategory;

import junit.framework.TestCase;

public class TestPerspective extends TestCase 
{
	private Vector<String> wizardShortcuts = new Vector<String>(4);
	private Vector<String> showViewShortcuts = new Vector<String>(2);
	
	@Override
	protected void setUp() throws Exception 
	{
		/*
		 * init vector with expected new wizards shortcuts in
		 * modelica prespective
		 */				
		assertTrue(Collections.addAll(wizardShortcuts,
				/* modelica wizards */
				"org.modelica.mdt.NewClassWizard",
				"org.modelica.mdt.NewPackageWizard",
				/* generic wizards */
				"org.eclipse.ui.wizards.new.folder",
				"org.eclipse.ui.wizards.new.file"));
		
		/*
		 * init vector with expected new wizards shortcuts in
		 * modelica prespective
		 */				
		assertTrue(Collections.addAll(showViewShortcuts,
				/* modelica views */
				"org.modelica.mdt.ProjectsView",
				/* generic views */
				IPageLayout.ID_PROBLEM_VIEW));
	}
	
	/**
	 * perform tests on modelica perspective
	 * @throws WorkbenchException
	 */
	public void testModelicaPerspective() throws WorkbenchException
	{
		IWorkbench workbench = PlatformUI.getWorkbench(); 
		
		/*
		 * open modelica perspective
		 */
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow(); 
		workbench.showPerspective("org.modelica.mdt.perspective", window); 
		
		IWorkbenchPage page = window.getPages()[0];

		/*
		 * check that all 'new wizards' shortcuts are installed
		 */
		Vector<String> presentWizShortcuts = new Vector<String>();
		
		for (String scut : page.getNewWizardShortcuts())
		{
			presentWizShortcuts.addElement(scut);
		}
		assertTrue(presentWizShortcuts.containsAll(wizardShortcuts));
				
		/*
		 * check that all 'show view' shortcuts are installed
		 */
		Vector<String> presentShowViewShortcuts = new Vector<String>();		
		for (String scut : page.getShowViewShortcuts())
		{
			presentShowViewShortcuts.addElement(scut);
		}
		assertTrue(presentShowViewShortcuts.containsAll(showViewShortcuts));
		
		/*
		 * check that all views are present
		 */
		assertNotNull(page.findView("org.modelica.mdt.ProjectsView"));
		assertNotNull(page.findView(IPageLayout.ID_PROBLEM_VIEW));

	}
	/**
	 * perform tests on modelica new wizards catagory
	 * @throws WorkbenchException
	 */
	
	public void testModelicaNewWizardsCatagory()
	{
		IWizardCategory category = 
			PlatformUI.getWorkbench().getNewWizardRegistry().
			  findCategory("org.modelica.mdt.ModelicaCategory");
				
		assertNotNull("Modelica New Wizards catagory not found",  category);
		assertEquals("Modelica New Wizards catagory labeled unexpectedly",
				"Modelica", category.getLabel());
		
		assertNotNull("New Project Wizard not found", 
				category.findWizard("org.modelica.mdt.NewProjectWizard"));
		
		assertNotNull("New Class Wizard not found", 
				category.findWizard("org.modelica.mdt.NewClassWizard"));

	}


}
