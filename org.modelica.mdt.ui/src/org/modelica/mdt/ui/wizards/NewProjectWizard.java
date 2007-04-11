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

package org.modelica.mdt.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.ui.ModelicaImages;
import org.modelica.mdt.ui.UIPlugin;

public class NewProjectWizard extends Wizard implements INewWizard
{
	public static final String PROJECT_NAME_TAG = "projectNameTag";
	
	public class NewProjectPage extends WizardPage 
	{
		private Text projectName;		

		public NewProjectPage()
		{
			super("");
		}
		
		public void createControl(Composite parent)
		{
			setPageComplete(false);
			/*
			 * configure description of this page
			 */
			setTitle("Create a Modelica project");
			setDescription("Create a Modelica project in the workspace.");
			
			setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_WIZBAN_PROJECT));
			
			/*
			 * setup widgets for this page
			 */
			Composite composite= new Composite(parent, SWT.NONE);
			setControl(composite);
			composite.setFont(parent.getFont());
			
	        GridLayout layout = new GridLayout();
	        layout.numColumns = 2;
	        composite.setLayout(layout);
	        
	        GridData gd;

	        /* project name field */
	        Label l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Project name:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        projectName = new Text(composite, SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        				  GridData.GRAB_HORIZONTAL);
	        projectName.setLayoutData(gd);
	        UIPlugin.tag(projectName, PROJECT_NAME_TAG);
	        
	        projectName.addModifyListener(new ModifyListener()
	    	{
	        	/* check if entered classname is valid */
	        	public void modifyText(ModifyEvent e)
	        	{
	        		validateProjectName(projectName.getText());
	        	}
	    	});

		}

		public void validateProjectName(String name)
		{
			IStatus res = ResourcesPlugin.getWorkspace().validateName(name, IResource.PROJECT);
			if (res.isOK())
			{
				projectPage.setPageComplete(true);
				setMessage(null);
			}
			else
			{
				projectPage.setPageComplete(false);
				setMessage(res.getMessage(), DialogPage.ERROR);
			}			
		}
	}

	private NewProjectPage projectPage = new NewProjectPage();
	
	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		setWindowTitle("New Modelica Project");
	}	
	
	private static void showProjectCreationError(IStatus status)
	{
		ErrorDialog.openError(null, "Error", "Could not create project", status);	
	}

	@Override
	public boolean performFinish()
	{
		/*
		 * run creation of the project operation inside
		 * an instance of IWorkspaceRunnable to batch the
		 * changes to the workspace
		 */
		try
		{
			final IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectPage.projectName.getText());
			if (ModelicaCore.getModelicaRoot().createProject(newProject) == null)
				return false;
			/* TODO! FIXME! adrpo - 2007-03-11 if i run it on a runable i get the event twice.
			IWorkspaceRunnable wr = new IWorkspaceRunnable()
			{
				public void run(IProgressMonitor monitor) throws CoreException
				{
					ModelicaCore.getModelicaRoot().createProject(newProject);
				}
			};

			ISchedulingRule rule = null;
	        if (newProject != null) {
	            IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
	            rule = ruleFactory.markerRule(newProject);
	        }			
			ResourcesPlugin.getWorkspace().run(wr, rule, IWorkspace.AVOID_UPDATE, null);
			 */
		}
		catch (CoreException e)
		{
			/* 
			 * on error show the error dialog and don't
			 * close the new project wizard to give user a
			 * chance to correct the error and try again
			 */
			showProjectCreationError(e.getStatus());
			return false;
		}
		
		return true;
	}
	
	@Override
	public void addPages()
	{
		addPage(projectPage);
	}
}
