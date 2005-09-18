package org.modelica.mdt;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class NewProjectWizard extends BasicNewResourceWizard
{
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			monitor.done();
		}

	}
	protected WizardNewProjectCreationPage projectPage;

	public void init(IWorkbench workbench,IStructuredSelection selection)
	{
		super.init(workbench, selection);
		setNeedsProgressMonitor(true);
		setWindowTitle("New Modelica Project");
    }
	
	@Override
	public boolean performFinish()
	{
		try 
		{
			IProject project = projectPage.getProjectHandle();
			getContainer().run(false, true, 
					new CreateNewProjectRunnable(project));

			selectAndReveal(project);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void addPages()
	{
		super.addPages();
		
		projectPage = new WizardNewProjectCreationPage("");
		projectPage.setTitle("Create a Modelica project");
		projectPage.setDescription("Create a Modelica project in the workspace" +
				" or in an external location.");

		addPage(projectPage);
		
	 }

}
