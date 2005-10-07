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

import java.util.concurrent.Semaphore;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.modelica.mdt.NewProjectWizard;

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
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWizardDescriptor wizDesc = 
			workbench.getNewWizardRegistry().findWizard("org.modelica.mdt.NewProjectWizard");		
		assertNotNull("new project wizard not found", wizDesc);
		
		IWorkbenchWizard wizard = null;
		try
		{
			wizard = wizDesc.createWizard();
		}
		catch (CoreException e)
		{
			fail("Could not create new project wizard, CoreException thrown\n" 
					+ e.getMessage());
		}
		assertNotNull(wizard);
		
		wizard.init(workbench, StructuredSelection.EMPTY);
		final WizardDialog dialog = 
			new WizardDialog(workbench.getActiveWorkbenchWindow().getShell(), wizard);
		dialog.create();

		openDialog(dialog);
		
		assertFalse(wizard.canFinish());

		/* fetch project name text field */
		Text name = TextTester.getInstrumentedText(NewProjectWizard.PROJECT_NAME_TAG);		
		/* enter project name */
		TextTester.getTextTester().actionEnterText(name, PROJECT_NAME);
		
		assertTrue(wizard.canFinish());

		/* create project */
		boolean finishRes = wizard.performFinish();
		assertTrue("New wizard dialog failed to finish", finishRes);
		
		/* check that project was created */
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME);
				
		assertTrue("new wizard fail to create project", project.exists());
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
		
		
		/* clean up */
		dialog.close();
	}

	/** 
	 * opens (runs dialog.open() method ) a dialog in UI thread and waits until it 
	 * is open before returning
	 * 
	 * @param dialog the dialog to open, it is assumed that dialog is fully initialized
	 * 
	 */
	private void openDialog(final WizardDialog dialog)
	{
		final Semaphore sem = new Semaphore(0);
		
		dialog.getShell().getDisplay().syncExec(new Runnable()
		{
			public void run()
			{
				dialog.setBlockOnOpen(false);
				dialog.open();
				sem.release();
			}
		});
		
		try
		{
			sem.acquire();
		} 
		catch (InterruptedException e)
		{
			fail("interruped while waiting for dialog to open");
		}
	} 

}
