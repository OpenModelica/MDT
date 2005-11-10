package org.modelica.mdt.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.internal.omcproxy.InitializationException;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaBuildVisitor implements IResourceVisitor
{
	public boolean visit(IResource resource) throws CoreException
	{
		String extension = resource.getFileExtension();
		
		if(extension != null && extension.equals("mo"))
		{
			IPath path = resource.getProjectRelativePath();
			IFile file = resource.getProject().getFile(path);
			
			file.deleteMarkers(IMarker.PROBLEM, false,
					IResource.DEPTH_INFINITE);
			try
			{
				ModelicaBuilder.loadFileAndReportErrors(file);
			}
			catch(InitializationException e)
			{
				MdtPlugin.log(e);
			}
		}
		return true;
	}
}
