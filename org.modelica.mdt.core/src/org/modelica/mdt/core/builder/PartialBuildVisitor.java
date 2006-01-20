/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.core.builder;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Visitor used to load files into OMC when a partial build is happening. 
 * 
 * @author Andreas Remar
 */

public class PartialBuildVisitor implements IResourceDeltaVisitor 
{

	/**
	 * @param delta the delta that contains the differences between this
	 * resource tree and the last resource tree that was built
	 */
	public boolean visit(IResourceDelta delta) throws CoreException
	{
		String extension = delta.getResource().getFileExtension();
		
		int kind = delta.getKind();
		
		/*
		 * Perform the build on .mo files that have either been added or changed
		 */
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
				/*
				 * Try loading the file into OMC, and report errors if any
				 * are found.
				 */
				SyntaxChecker.loadFileAndReportErrors(file);
			}
			catch(CompilerException e)
			{
				ErrorManager.logError(e);

				/* Don't visit the children, OMC is fubarred. */
				return false;
			}
		}
		
		/*
		 * Get the children of this resource that have been changed since
		 * last rebuild.
		 */
		IResourceDelta[] d = delta.getAffectedChildren();
		if(d == null)
			return false;
		
		/*
		 * Rebuild all children of this resource that have changed.
		 * (Maybe this shouldn't be done explicitly but instead return
		 *  true from this function?)
		 */
		for(IResourceDelta d1 : d)
		{
			d1.accept(new PartialBuildVisitor());
		}
		
		/*
		 * Return false to indicate that children should not be visited.
		 */
		return false;
	}

}
