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

import junit.framework.Assert;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

/**
 * @author Elmir Jagudin
 *
 * This class contains some utility code for assisting the testcases
 */
public class Utility 
{

	/** 
	 * opens (runs dialog.open() method ) a dialog in UI thread and waits until it 
	 * is open before returning
	 * 
	 * @param dialog the dialog to open, it is assumed that dialog is fully initialized
	 * 
	 */
	public static IWorkbenchWizard openWizard(String wizardID)
	{
		
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWizardDescriptor wizDesc = 
			workbench.getNewWizardRegistry().findWizard(wizardID);	
		Assert.assertNotNull("wizard " + wizardID + " not found", wizDesc);
		
		IWorkbenchWizard wizard = null;
		try
		{
			wizard = wizDesc.createWizard();
		}
		catch (CoreException e)
		{
			Assert.fail("Could not create " + wizardID + 
					" wizard, CoreException thrown\n" + e.getMessage());
		}
		Assert.assertNotNull(wizard);
		
		wizard.init(workbench, StructuredSelection.EMPTY);
		final WizardDialog dialog = 
			new WizardDialog(workbench.getActiveWorkbenchWindow().getShell(), wizard);
		dialog.create();

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
			Assert.fail("interruped while waiting for dialog to open");
		}
		
		return wizard;
	} 	
}
