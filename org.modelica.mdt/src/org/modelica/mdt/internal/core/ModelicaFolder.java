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

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.ErrorManager;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.internal.omcproxy.CompilerException;
import org.modelica.mdt.internal.omcproxy.ConnectException;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * @author Elmir Jagudin
 */
public class ModelicaFolder extends ModelicaParent implements IModelicaFolder
{
	private IContainer container;
	
	private boolean childrenLoaded = false;
	
	private Hashtable<IResource, Object> children = 
			new Hashtable<IResource, Object>();
	
	protected ModelicaFolder(IContainer cont)
	{
		this.container = cont;
	}
	

	/**
	 * @see org.modelica.mdt.core.IModelicaElement#getElementName()
	 */
	public String getElementName() 
	{
		return container.getName();
	}


	public Collection<Object> getChildren() 
		throws CoreException, ConnectException, UnexpectedReplyException
	{
		if (!childrenLoaded)
		{
			loadChildren();
			childrenLoaded = true;
		}
		
		return children.values();
	}

	private void loadChildren() 
		throws CoreException, ConnectException, UnexpectedReplyException
	{
		for (IResource member :  container.members())
		{
			children.put(member, wrap(member));
		}
	}

	@Override
	public IContainer getResource()
	{
		return container;
	}

	public List<IModelicaElementChange> update(IResourceDelta delta) 
		throws ConnectException, UnexpectedReplyException, InvocationError
	{
		return update(null, delta);
	}

	/**
	 * A special update method where alternative root of the changed elements 
	 * can be specified. This is a little hack to enable ModelicaProject object
	 * to specifie itself as the parent, otherwise updating the tree in the
	 * projects view does not work. That is due to the fact that root folder
	 * of any project is now explisitly shown in the tree. This kinda sucks,
	 * pehaps there are better design alternatives.
	 * 
	 * @param root
	 * @param delta
	 * @return
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws InvocationError 
	 */
	public List<IModelicaElementChange> update(Object root, 
			IResourceDelta delta) 
			throws ConnectException, UnexpectedReplyException, InvocationError
	{
		LinkedList<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();
		Object parent = (root != null) ? root : this;

		for (IResourceDelta d : delta.getAffectedChildren())
		{
			IResource res = d.getResource();
			Object element = children.get(res);

			switch (d.getKind())
			{
			case IResourceDelta.ADDED:
				// TODO when a modelica file is added
				// there is possability that this folder is turned into 
				// a package, right now that is just ignored 
				try
				{
					element = wrap(res);
					children.put(res, element);
					changes.add(new ModelicaElementChange(parent, element));
				} 
				catch (CompilerException e)
				{
					/* report error and pretend nothing happend */
					ErrorManager.showCompilerError(e);
				} 
				break;
			case IResourceDelta.REMOVED:
				children.remove(res);
				changes.add(new ModelicaElementChange(element,
						ChangeType.REMOVED));
				break;
			case IResourceDelta.CHANGED:
				if (element instanceof ModelicaFolder)
				{
					changes.addAll(((ModelicaFolder)element).update(d));
				}
				else if (element instanceof ModelicaElement)
				{
					changes.addAll(((ModelicaElement)element).update(d));
				}
				changes.add(new ModelicaElementChange(element, 
						ChangeType.CHANGED));
				break;
			}
		}
		
		return changes;
	}
	
	/**
	 * map a IResource to the type of modelica element it represents
	 */
	private Object wrap(IResource res)
		throws ConnectException, UnexpectedReplyException
	{
		switch (res.getType())
		{
		case IResource.FOLDER:
			/* a folder is either a package or a just a folder */
			if (FolderPackage.isFolderPackage(res))
			{
				/* we have ourself a package */
				return new FolderPackage((IFolder)res);
			}
			else
			{
				/* just a folder */
				return new ModelicaFolder((IContainer)res);
			}
		case IResource.FILE:
			String extension = res.getFileExtension(); 
			if (extension != null && extension.equals("mo"))
			{
				return new ModelicaFile((IFile)res);
			}
		}
		/* only one option left, a regular file */
		return res;

	}

}
