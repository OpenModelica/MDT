package org.modelica.mdt;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class ModelicaNature implements IProjectNature
{
	private IProject project;
	
	public void configure() throws CoreException 
	{
	}

	public void deconfigure() throws CoreException 
	{
	}

	public IProject getProject()
	{
		return project;
	}

	public void setProject(IProject project)
	{
		this.project = project;
	}
}
