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
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaPackage;

/**
 * Represent a file based package. That is a package that is either defined
 * in a file or a separate folder inside the project root.
 * 
 * @author Elmir Jagudin
 */
public class ModelicaPackage extends ModelicaParent implements IModelicaPackage 
{

	private String baseName;
	private ModelicaFolder folder = null;

	/**
	 * Create a (root) package defined inside the folder.
	 * 
	 * @param folder the top folder (the one that contains package.mo)
	 * of this project
	 */
	public ModelicaPackage(ModelicaFolder folder) 
	{
		this.folder = folder;
		baseName = "";
	}

	public ModelicaPackage(ModelicaPackage parentPackage, 
							ModelicaFolder containerFolder)
	{
		String parentBaseName = parentPackage.getBaseName();
		
		/*
		 * calculate the base name of this package
		 */
		if (parentBaseName.equals(""))
		{
			/*
			 * special case for packages that are direct children of
			 * the root package 
			 */
			baseName = parentPackage.getElementName();
		}
		else /* general case */
		{
			baseName = 
				parentPackage.getBaseName() + "." + 
				parentPackage.getElementName();
		}
		this.folder = containerFolder;
	}

	public List<IModelicaPackage> getPackages() throws CoreException 
	{
		IResource[] members = folder.getResource().members();
		LinkedList<IModelicaPackage> pkgs = new LinkedList<IModelicaPackage>();

		for (IResource res : members)
		{
			if (isPackage(res))
			{
				pkgs.add(new ModelicaPackage(this,
						                  new ModelicaFolder((IContainer)res)));
			}
		}

		return pkgs;
	}
	
	/**
	 * Checks if the resource can represent a modelica package.
	 * @param res
	 * @return true if the res is a package false otherwise.
	 */
	public static boolean isPackage(IResource res)
	{
		if (res.getType() == IResource.FOLDER)
		{
			/* 
			 * if folder contains a package.mo file, 
			 * then consider it a package 
			 */

			// TODO load the package.mo into the OMC and check
			// that it defines a package and not something else

			IFolder fol = (IFolder) res;
			if (fol.getFile("package.mo").exists())
			{
				return true;
			}
		}

		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaPackage#getClasses()
	 */
	public List<IModelicaClass> getClasses() 
	{
		//TODO implement me
		//
		// this method should return only classes 
		// that are not defined in a separate mo files
		// there is two way that can happen:
		// 1) if the class is defined inside the package.mo file
		// 2) if this packages is a nested package inside a class
		//   that is if this package is not defined in a separate folder
		
		return new LinkedList<IModelicaClass>(); // return an empty list for now
	}

	public String getBaseName()
	{
		return baseName;
	}

	public String getElementName() 
	{
		return folder.getResource().getName();
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



	public IResource getResource() 
	{
		return folder.getResource();
	}

	public List<IModelicaFolder> getFolders() throws CoreException 
	{
		return folder.getFolders();
	}

	public List<IModelicaFile> getModelicaFiles() throws CoreException 
	{
		return folder.getModelicaFiles();
	}

	public List<IFile> getFiles() throws CoreException 
	{
		return folder.getFiles();
	}
}
