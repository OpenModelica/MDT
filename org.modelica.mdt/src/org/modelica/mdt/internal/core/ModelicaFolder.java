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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * @author Elmir Jagudin
 */
public class ModelicaFolder extends ModelicaParent implements IModelicaFolder
{

	private IContainer container;
	
	protected ModelicaFolder(IContainer cont)
	{
		this.container = cont;
	}
	
	
	private boolean isModelicaFile(IResource res)
	{
		if (res.getType() == IResource.FILE)
		{
			String extension = res.getFileExtension(); 
			if (extension != null && extension.equals("mo"))
			{
				return true;
			}
		}

		return false;
	}
	
	private boolean isFile(IResource res)
	{
		if (res.getType() == IResource.FILE)
		{
			String extension = res.getFileExtension(); 
			if (extension == null || (!extension.equals("mo")))
			{
				return true;
			}
		}

		return false;
	}
	
	/**
	 * @throws UnexpectedReplyException 
	 * @throws ConnectionException 
	 * @throws CoreException
	 * @see org.modelica.mdt.core.IModelicaFolder#getFolders()
	 */
	public List<IModelicaFolder> getFolders() 
		throws CoreException, ConnectionException, UnexpectedReplyException
	{
		IResource[] members = container.members();
		LinkedList<IModelicaFolder> folders = new LinkedList<IModelicaFolder>();

		for (IResource res : members)
		{
			if (res.getType() == IResource.FOLDER && 
					!ModelicaPackage.isPackage(res))
			{
				folders.add(new ModelicaFolder((IContainer)res));
			}
		}

		return folders;
	}

	/**
	 * @throws UnexpectedReplyException 
	 * @throws ConnectionException 
	 * @throws CoreException
	 * @see org.modelica.mdt.core.IModelicaFolder#getPackages()
	 */
	public List<IModelicaPackage> getPackages()
		throws CoreException, ConnectionException, UnexpectedReplyException
	{
		IResource[] members = container.members();
		LinkedList<IModelicaPackage> pkgs = new LinkedList<IModelicaPackage>();

		for (IResource res : members)
		{
			if (ModelicaPackage.isPackage(res))
			{
				pkgs.add(new ModelicaPackage(new ModelicaFolder((IContainer)res)));
			}
		}

		return pkgs;
	}
	
	
	/**
	 * @see org.modelica.mdt.core.IModelicaFolder#getModelicaFiles()
	 */
	public List<IModelicaFile> getModelicaFiles() throws CoreException
	{
		IResource[] members = container.members();
		LinkedList<IModelicaFile> mofiles = new LinkedList<IModelicaFile>();
		
		for (IResource res : members)
		{
			if (isModelicaFile(res))
			{
				mofiles.add(new ModelicaFile((IFile)res));
			}
		}

		return mofiles;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaFolder#getFiles()
	 */
	public List<IFile> getFiles() throws CoreException
	{
		IResource[] members = container.members();
		LinkedList<IFile> files = new LinkedList<IFile>();
		
		for (IResource res : members)
		{
			if (isFile(res))
			{
				files.add((IFile)res);
			}
		}
		return files;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaElement#getElementName()
	 */
	public String getElementName() 
	{
		return container.getName();
	}


	public List<?> getChildren()
		throws ConnectionException, UnexpectedReplyException 
	{
		try 
		{
			List<Object> children = new LinkedList<Object>();
			
			children.addAll(getPackages());
			children.addAll(getFolders());
			children.addAll(getModelicaFiles());
			children.addAll(getFiles());
			
			return children;
		}
		catch (CoreException e) 
		{
			// TODO do proper error reporting/error logging
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IContainer getResource()
	{
		return container;
	}

}
