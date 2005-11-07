package org.modelica.mdt.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaBuilder extends IncrementalProjectBuilder
{

	@Override
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException
	{
		// TODO Auto-generated method stub
		switch(kind)
		{
		case IncrementalProjectBuilder.FULL_BUILD:
			fullBuild(monitor);
			break;
		
		default:
			IResourceDelta delta = getDelta(getProject());
			if(delta == null)
			{
				fullBuild(monitor);
			}
			else
			{
				incrementalBuild(delta, monitor);
			}
			break;
		}
		return null;
	}
	
	protected void fullBuild(final IProgressMonitor monitor)
	{
		try
		{
			getProject().accept(new ModelicaBuildVisitor());
		}
		catch(CoreException e)
		{
			// TODO Some other error handling?
			e.printStackTrace();
		}
	}
	
	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor)
	{
		try
		{
			delta.accept(new ModelicaBuildDeltaVisitor());
		}
		catch(CoreException e)
		{
			// TODO Proper error handling, someone?
			e.printStackTrace();
		}
	}

	protected void startupOnInitializa()
	{
		// Add builder init here
	}
	
	protected void clean(IProgressMonitor monitor)
	{
		// Add builder clean logic here
	}
}
