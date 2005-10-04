package org.modelica.mdt;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
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
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class NewProjectWizard extends Wizard implements INewWizard
{
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
			
			// TODO set image descriptor			
			//setImageDescriptor(...);
			
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
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
	        projectName.setLayoutData(gd);
	        
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
	public class CreateNewProjectRunnable implements IRunnableWithProgress
	{
		IProject newProject;
		
		public CreateNewProjectRunnable(IProject newProject)
		{
			this.newProject = newProject;
		}

		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException
		{
			monitor.beginTask("creating project " + newProject.getName(), 3);
			try
			{
				/* create the project */
				newProject.create(monitor);
				monitor.worked(1);

				/* open project */
				newProject.open(monitor);
				monitor.worked(2);

				/* add modelica nature to the project */
				MdtPlugin.addModelicaNature(newProject);
				
			}
			catch (CoreException e)
			{
				showProjectCreationError(e.getStatus());
			}			
			monitor.done();
		}

	}

	private NewProjectPage projectPage = new NewProjectPage();
	private IWorkbench workbench;
	
	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		this.workbench = workbench;
		setWindowTitle("New Modelica Project");
	}	
	

	private void showProjectCreationError(String message, Exception e)
	{
		showProjectCreationError(new Status(IStatus.ERROR, 
				MdtPlugin.getSymbolicName(), 0, message, e));
	}
	
	
	private void showProjectCreationError(IStatus status)
	{
		ErrorDialog.openError(null, "Error", "Could not create project", status);	
	}

	@Override
	public boolean performFinish()
	{
		try 
		{
			IProject project = 
				ResourcesPlugin.getWorkspace().getRoot().getProject(projectPage.projectName.getText());
			
			getContainer().run(false, true, 
					new CreateNewProjectRunnable(project));

			BasicNewResourceWizard.selectAndReveal(project, workbench.getActiveWorkbenchWindow());
		} 
		catch (InvocationTargetException e)
		{
			showProjectCreationError("internal error during project creation", e);
		}
		catch (InterruptedException e)
		{
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
