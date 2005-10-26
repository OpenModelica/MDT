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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaPackage;

/**
 * @author Elmir Jagudin
 *
 */
public class ModelicaFolder extends ModelicaElement implements IModelicaFolder
{

	private IContainer cont;
	
	protected ModelicaFolder(IContainer cont)
	{
		this.cont = cont;
	}
	
	private boolean isPackage(IResource res)
	{
		if (res.getType() == IResource.FOLDER)
		{
			/* if folder contains a package.mo file, then consider it a package */
			IFolder fol = (IFolder) res;
			if (fol.getFile("package.mo").exists())
			{
				return true;
			}
		}

		return false;
	}
	
	private boolean isFolder(IResource res)
	{
		if (res.getType() == IResource.FOLDER)
		{
			IFolder fol = (IFolder) res;
			if (!fol.getFile("package.mo").exists())
			{
				return true;
			}
		}
		
		return false;
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
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaFolder#getFolders()
	 */
	public List<IModelicaFolder> getFolders() throws CoreException
	{
		IResource[] members = cont.members();
		LinkedList<IModelicaFolder> folders = new LinkedList<IModelicaFolder>();

		for (IResource res : members)
		{
			if (isFolder(res))
			{
				folders.add(new ModelicaFolder((IContainer)res));
			}
		}

		return folders;
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaFolder#getPackages()
	 */
	public List<IModelicaPackage> getPackages() throws CoreException
	{
		IResource[] members = cont.members();
		LinkedList<IModelicaPackage> pkgs = new LinkedList<IModelicaPackage>();

		for (IResource res : members)
		{
			if (isPackage(res))
			{
				pkgs.add(new ModelicaPackage((IFolder)res));
			}
		}

		return pkgs;
	}
	
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaFolder#getModelicaFiles()
	 */
	public List<IModelicaFile> getModelicaFiles() throws CoreException
	{
		IResource[] members = cont.members();
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

	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaFolder#getFiles()
	 */
	public List<IFile> getFiles() throws CoreException
	{
		IResource[] members = cont.members();
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

	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaElement#getElementName()
	 */
	public String getElementName() 
	{
		return cont.getName();
	}


	public List<?> getChildren() 
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

	public boolean hasChildren() throws CoreException 
	{
		return !getChildren().isEmpty();
	}

}
