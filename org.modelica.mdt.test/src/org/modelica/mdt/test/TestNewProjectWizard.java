package org.modelica.mdt.test;

import java.util.concurrent.Semaphore;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
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
