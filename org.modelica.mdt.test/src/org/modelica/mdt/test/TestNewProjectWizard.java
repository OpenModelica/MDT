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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.NewProjectWizard;

import abbot.finder.matchers.swt.TextMatcher;
import abbot.finder.swt.BasicFinder;
import abbot.finder.swt.MultipleWidgetsFoundException;
import abbot.finder.swt.TestHierarchy;
import abbot.finder.swt.WidgetNotFoundException;
import abbot.tester.swt.ButtonTester;
import abbot.tester.swt.TextTester;

import junit.framework.TestCase;

public class TestNewProjectWizard extends TestCase 
{
	/* name of the project that test creates */
	public static final String PROJECT_NAME = "test";
	
	public void testWizard()
	{
		/*
		 * init and display wizard
		 */
		IWorkbenchWizard wizard = Utility.openWizard("org.modelica.mdt.NewProjectWizard");
		assertFalse(wizard.canFinish());

		/* fetch project name text field */
		Text name = TextTester.getInstrumentedText(NewProjectWizard.PROJECT_NAME_TAG);		
		/* enter project name */
		TextTester.getTextTester().actionEnterText(name, PROJECT_NAME);
		
		assertTrue(wizard.canFinish());

		/* 
		 * create project 
		 */
		BasicFinder finder =  /* find finish button */
			new BasicFinder(new TestHierarchy(PlatformUI.getWorkbench().getDisplay()));

		Button finish = null;
		try
		{
			finish = (Button) finder.find(new TextMatcher("&Finish"));
		}
		catch (WidgetNotFoundException e)
		{
			fail("Finish button not found.");
		} 
		catch (MultipleWidgetsFoundException e) 
		{
			fail("Multiple finish buttons found.");
		}
		
		/* create project by clicking finish button */
		while (!finish.getEnabled())
		{
			/* wait for the name changed to propogate to enable the finish button */
		}		
		ButtonTester.getButtonTester().actionClick(finish);
		
		
		/* check that project was created */
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME);
				
		assertTrue("new wizard failed to create project", project.exists());
		assertTrue("new wizard didnt open the created project", project.isOpen());
		
		/* check that it have modelica nature */
		try 		
		{
			assertTrue("modelica nature not added to the project", 
					project.hasNature("org.modelica.mdt.ModelicaNature"));
			assertTrue("modelica nature was not enabled on the project", 
					project.isNatureEnabled("org.modelica.mdt.ModelicaNature"));

		}
		catch (CoreException e)
		{
			fail("CoreException thrown while probing for modelica nature " + e.getMessage());
		}

	}

}
