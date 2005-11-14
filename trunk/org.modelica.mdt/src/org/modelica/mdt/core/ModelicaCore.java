package org.modelica.mdt.core;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.internal.core.ModelicaRoot;
import org.modelica.mdt.ui.ModelicaElementAdapter;
import org.modelica.mdt.MdtPlugin;

public class ModelicaCore 
{
	private static IModelicaRoot modelicaRoot = null;
	private static IWorkbenchAdapter modelicaElementAdapter = null;
	
	public static class CreateNewProjectRunnable implements IRunnableWithProgress
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
	private static void showProjectCreationError(String message, Exception e)
	{
		showProjectCreationError(new Status(IStatus.ERROR, 
				MdtPlugin.getSymbolicName(), 0, message, e));
	}
	
	
	private static void showProjectCreationError(IStatus status)
	{
		ErrorDialog.openError(null, "Error", "Could not create project", status);	
	}

	/**
	 * creates a modelica project in the workspace
	 * 
	 * @return the handle to the created project or null if there were errors during 
	 * creation of the project
	 */
	public static IProject createProject(String name, IRunnableContext context)
	{
		IProject project = null;		
		try 
		{
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
			
			context.run(false, false, 
					new CreateNewProjectRunnable(project));

			/* this should be done after we know for certain that the project 
			 * have been created */
			//TODO implement select and reveal
			//BasicNewResourceWizard.selectAndReveal(project, 
			//workbench.getActiveWorkbenchWindow());
		} 
		catch (InvocationTargetException e)
		{
			showProjectCreationError("internal error during project creation", 
									 e);
		}
		catch (InterruptedException e)
		{
			/* ignore this */
		}
		
		return project;
		
	}
	
	public static IModelicaRoot getModelicaRoot()
	{
		if (modelicaRoot == null)
		{
			modelicaRoot = new ModelicaRoot();
		}
		return modelicaRoot;
	}


	public static Object getWorkbenchAdapter() 
	{
		if (modelicaElementAdapter == null)
		{
			modelicaElementAdapter = new ModelicaElementAdapter();
		}

		return modelicaElementAdapter;
	}
}
