package org.modelica.mdt;


import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class NewProjectWizard extends BasicNewResourceWizard
{
	protected WizardNewProjectCreationPage projectPage;

	public void init(IWorkbench workbench,IStructuredSelection selection)
	{
		setWindowTitle("New Modelica Project");
    }
	
	@Override
	public boolean performFinish()
	{
		System.out.println("finish!");
		// TODO Auto-generated method stub		
		return false;
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
