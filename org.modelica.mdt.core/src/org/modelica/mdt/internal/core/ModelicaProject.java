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
package org.modelica.mdt.internal.core;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * Wrappper around IProject to provide Modelica specific view 
 * @author Elmir Jagudin
 */
public class ModelicaProject extends ModelicaElement implements IModelicaProject 
{
	private IProject project;
	private ModelicaFolder rootFolder = null;
	
	
	protected ModelicaProject(IProject project)
	{
		this.project = project; 
	}

	/**
	 * 
	 * @return the IProject on which this IModelicaProject  was created
	 */
	public IProject getProject()
	{
		return project;
	}


	public String getElementName() 
	{
		return project.getName();
	}


	public IModelicaFolder getRootFolder()
	{
		if (rootFolder == null)
		{
			rootFolder = new ModelicaFolder(project);
		}
		
		return rootFolder;
	}

	@Override
	public IResource getResource()
	{
		return getRootFolder().getResource();
	}

	public List<IModelicaElementChange> update(IResourceDelta delta)
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException
	{
		LinkedList<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();

		if ((delta.getFlags() & IResourceDelta.OPEN) != 0)
		{
			if (project.isOpen()) /* project was opened */
			{
				changes.add(new ModelicaElementChange(this, 
						ChangeType.OPENED));
			}
			else /* project was closed */
			{
				changes.add(new ModelicaElementChange(this, 
						ChangeType.CLOSED));
				rootFolder = null;
			}
		}
		else
		{
			if (rootFolder != null)
			{
				changes.addAll(rootFolder.update(this, delta));
			}
		}
		
		return changes;
	}
}
