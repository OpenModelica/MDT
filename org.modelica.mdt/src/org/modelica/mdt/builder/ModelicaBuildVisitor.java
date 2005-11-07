package org.modelica.mdt.builder;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaBuildVisitor implements IResourceVisitor
{
	public boolean visit(IResource resource) throws CoreException
	{
		System.out.println(resource.getFullPath());
		// TODO Auto-generated method stub
		return true;
	}
}
