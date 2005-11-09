package org.modelica.mdt.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.osgi.service.datalocation.Location;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;

public class ModelicaBuildDeltaVisitor implements IResourceDeltaVisitor {

	public boolean visit(IResourceDelta delta) throws CoreException
	{
		// TODO Auto-generated method stub
		String changedFile = delta.getResource().getLocation().toString();
		String extension = delta.getResource().getFileExtension();
		
		if(extension != null && extension.equals("mo"))
		{
			IPath path = delta.getProjectRelativePath();
			IFile file = delta.getResource().getProject().getFile(path);

			file.deleteMarkers(IMarker.PROBLEM, false,
					IResource.DEPTH_INFINITE);
			try
			{
				String[] retval = OMCProxy.loadFileInteractive(changedFile);
				
				// TODO If contents are error strings, set up problem markers
				for(String s: retval)
				{
					if(s.contains("error"))
					{
						String msg;
						int lineno;
						
						String[] s2 = s.split("]");
						String[] s3 = s2[0].split(":");
						String[] s4 = s2[1].split(":");
						
						msg = s4[2].trim();
						lineno = Integer.parseInt(s3[1]);
						
						delta.getResource().getProject();
						
						ModelicaBuilder.reportProblem(msg, file, lineno);
					}
				}
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
