/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
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
 * * Neither the name of Linköpings universitet nor the names of its
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
package org.modelica.mdt.internal.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.IModelicaRoot;

/**
 * @author Elmir Jagudin
 *
 */
public class ModelicaRoot implements IModelicaRoot 
{
	private IWorkspaceRoot workspaceRoot = null;

	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaRoot#getProjects()
	 */
	public Object[] getProjects() throws CoreException 
	{
		if (workspaceRoot == null)
		{
			workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		}
		
		IProject[] projects = workspaceRoot.getProjects();
		Object[] resProjs = new Object[projects.length];

		/*
		 * wrap all projects with modelica nature into a ModelicProject object
		 */
		for (int i = 0; i < projects.length; i++)
		{
			if ((projects[i]).hasNature(MdtPlugin.MODELICA_NATURE))
			{
				resProjs[i] = new ModelicaProject((IProject)projects[i]);
			}
			else
			{
				resProjs[i] = projects[i];
			}
		}

		return resProjs;
	}

}
