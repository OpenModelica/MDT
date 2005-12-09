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

package org.modelica.mdt.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.internal.omcproxy.CompilerException;

/**
 * Visitor used to load files into OMC when a full build is happening.
 * 
 * @author Andreas Remar
 */
public class FullBuildVisitor implements IResourceVisitor
{
	/**
	 * @param resource the resource in the project that we are 'visiting'
	 */
	public boolean visit(IResource resource) throws CoreException
	{
		String extension = resource.getFileExtension();
		
		/*
		 * Only send .mo files to OMC.
		 */
		if(extension != null && extension.equals("mo"))
		{
			IPath path = resource.getProjectRelativePath();
			IFile file = resource.getProject().getFile(path);
			
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
				// TODO Proper error handling?
				MdtPlugin.log(e);
			}
		}
		
		/*
		 * Return true to indicate that the children of this resource should
		 * be visited.
		 */
		return true;
	}
}
