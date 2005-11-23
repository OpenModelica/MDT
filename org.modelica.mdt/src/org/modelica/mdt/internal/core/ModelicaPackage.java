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
import org.modelica.mdt.internal.omcproxy.CompilerException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.ParseResults;

/**
 * Represent a file based package. That is a package that is either defined
 * in a file or a separate folder inside the project root.
 * 
 * @author Elmir Jagudin
 * @author Andreas Remar
 */
public class ModelicaPackage extends ModelicaParent implements IModelicaPackage 
{
	/**
	 * the full name of the parent package
	 */
	private String prefix;
	
	/**
	 * the short name of this package
	 */
	private String name;
	
	/**
	 * the fully qualified name of this package e.g. foo.bar.hej
	 */
	private String fullName;
	
	/**
	 * folder where this package resides
	 */
	private ModelicaFolder folder = null;
	
	
	private IModelicaFile file = null;

	/**
	 * create a (root) package defined inside a folder
	 * 
	 * @param folder the top folder (the one that contains package.mo)
	 * of this project
	 */
	public ModelicaPackage(ModelicaFolder folder) 
	{
		this.folder = folder;
		prefix = "";
		name = folder.getResource().getName();
		setFullName();
	}

	/**
	 * create a subpackage residing inside a folder
	 *  
	 * @param parentPackage the parent package
	 * @param containerFolder folder where this package resides
	 */
	public ModelicaPackage(ModelicaPackage parentPackage, 
							ModelicaFolder containerFolder)
	{
		this.folder = containerFolder;
		prefix = parentPackage.getFullName();
		name = folder.getResource().getName();
		setFullName();
	}

	public ModelicaPackage(String prefix, String elementName)
	{
		this.prefix = prefix;
		this.name = elementName;
		
		setFullName();
	}		
	
	
	public ModelicaPackage(IModelicaFile container, String name)
	{
		this.prefix = "";
		this.name = name;
		
		setFullName();
		
		this.file = container;
		
	}
	
	
	/**
	 * calculate the base name of this package
	 */
	private void setFullName()
	{
		if(prefix.equals(""))
		{
			/*
			 * special case for packages that are direct children of
			 * the root package 
			 */
			fullName = name;
		}
		else /* general case */
		{
			fullName = prefix + "." + name;
		}
	}

	public List<IModelicaPackage> getPackages()
		throws CoreException, CompilerException
	{
		LinkedList<IModelicaPackage> packages = new LinkedList<IModelicaPackage>();
		
		if (folder != null) /* package is contained in a folder */
		{
			IResource[] members = folder.getResource().members();
		
			for (IResource res : members)
			{
				if (isPackage(res))
				{
					packages.add(new ModelicaPackage(this,
						                  new ModelicaFolder((IContainer)res)));
				}
			}
		}
		
		for(String s : OMCProxy.getPackages(fullName))
		{
			packages.add(new ModelicaPackage(fullName, s));
		}
			
		return packages;
	}
	
	/**
	 * Checks if the resource can represent a modelica package.
	 * @param res
	 * @return true if the res is a package, false otherwise.
	 */
	public static boolean isPackage(IResource res)
		throws CompilerException
	{
		if (res.getType() == IResource.FOLDER)
		{
			/*
			 * If a folder contains a package.mo file, and that file defines a
			 * top package with the same name as folder name,
			 * we can consider this folder a Modelica package.
			 */
			IFolder fol = (IFolder) res;
			String folderName = fol.getName();
			IFile file = fol.getFile("package.mo");
			
			if(file.exists())
			{
				/*
				 * check if package.mo defines a package (aka class) 
				 * with the same name as the parent folder
				 */
				ParseResults results = OMCProxy.loadFileInteractive(file);
				
				for (String name : results.getClasses())
				{
					if (name.equals(folderName))
					{
						return true;
					}
				}
			}
		}

		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaPackage#getClasses()
	 */
	public List<IModelicaClass> getClasses() throws CompilerException 
	{
		//TODO implement me correctly
		//
		// this method should return only classes 
		// that are not defined in a separate mo files
		// there is two way that can happen:
		// 1) if the class is defined inside the package.mo file
		// 2) if this packages is a nested package inside a class
		//   that is if this package is not defined in a separate folder
		
		LinkedList<IModelicaClass> classes = new LinkedList<IModelicaClass>();
		
		for(String str : OMCProxy.getClassNames(fullName))
		{
			classes.add(new ModelicaClass(str, fullName));
		}
		
		return classes;		
	}

	public String getPrefix()
	{
		return prefix;
	}

	public String getElementName() 
	{
		return name;
	}

	public String getFullName() 
	{
		if (prefix.equals(""))
		{
			return getElementName();
		}

		return prefix + "." + getElementName();
	}

	public List<?> getChildren() throws CompilerException
	{
		try 
		{
			List<Object> children = new LinkedList<Object>();
			
			children.addAll(getPackages());
			children.addAll(getClasses());
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



	public IResource getResource() throws CompilerException 
	{
		if (folder != null)
		{
			return folder.getResource();
		}
		
		if (file != null)
		{
			return file.getResource();
		}
		
		/* uhh, not sure which resource we are contained in */
		return null;
	}

	public List<IModelicaFolder> getFolders() 
		throws CoreException, CompilerException 
	{
		if (folder != null)
		{
			return folder.getFolders();
		}
		return new LinkedList<IModelicaFolder>();
	}

	public List<IModelicaFile> getModelicaFiles() throws CoreException 
	{
		if (folder != null)
		{
			return folder.getModelicaFiles();
		}
		return new LinkedList<IModelicaFile>();
	}

	public List<IFile> getFiles() throws CoreException 
	{
		if (folder != null)
		{
			return folder.getFiles();
		}
		return new LinkedList<IFile>();
	}
}
