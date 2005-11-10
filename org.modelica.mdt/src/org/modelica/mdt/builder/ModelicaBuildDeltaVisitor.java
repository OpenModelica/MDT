package org.modelica.mdt.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.internal.omcproxy.InitializationException;

public class ModelicaBuildDeltaVisitor implements IResourceDeltaVisitor {

	public boolean visit(IResourceDelta delta) throws CoreException
	{
		String extension = delta.getResource().getFileExtension();
		
		int kind = delta.getKind();
		
		if(extension != null && extension.equals("mo")
				&& (kind == IResourceDelta.ADDED
						|| kind == IResourceDelta.CHANGED))
		{
			IPath path = delta.getProjectRelativePath();
			IFile file = delta.getResource().getProject().getFile(path);

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
		
		IResourceDelta[] d = delta.getAffectedChildren();
		if(d == null)
			return false;
		
		for(IResourceDelta d1 : d)
		{
			d1.accept(new ModelicaBuildDeltaVisitor());
		}
		return false;
	}

}
