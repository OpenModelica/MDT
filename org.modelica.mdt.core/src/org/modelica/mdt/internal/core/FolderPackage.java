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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * A package that is stored as a folder.
 * 
 * @author Elmir Jagudin
 */
public class FolderPackage extends ModelicaClass
{
	private IFolder container;
	private boolean childrenLoaded = false;
	
	private Hashtable<IResource, IModelicaElement> children = 
			new Hashtable<IResource, IModelicaElement>();

	/**
	 * Create a root package, a package that is defined in the unnamed root
	 * namespace.
	 * 
	 * @param container the folder where this package resides
	 */
	public FolderPackage(ModelicaFolder parent, IFolder container)
	{
		super(parent);
		this.container = container;
		parentNamespace = null;
		name = container.getName();
		setFullName();
	}

	/**
	 * Create a package that is a subpackage to some other folder package.
	 * @param parent
	 * @param container
	 */
	public FolderPackage(FolderPackage parent, IFolder container)
	{
		super(parent);
		this.container = container;
		parentNamespace = parent;
		name = container.getName();
		setFullName();
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaElement#getResource()
	 */
	public IResource getResource() 
	{
		return container;
	}

	/**
	 * @see org.modelica.mdt.core.IParent#hasChildren()
	 */
	public boolean hasChildren() throws CoreException, ConnectException,
			UnexpectedReplyException, InvocationError
	{
		/* a folder package have allways at least package.mo as a child */
		return true;
	}

	public Collection<IModelicaElement> getChildren() 
		throws CoreException, ConnectException, UnexpectedReplyException, 
			CompilerInstantiationException
	{
		if (!childrenLoaded)
		{
			loadChildren();
			childrenLoaded = true;
		}
		
		return children.values();
	}

	private void loadChildren() 
		throws CoreException, ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		for (IResource member : container.members())
		{
			children.put(member, wrap(member));
		}
	}

	@Override
	public Collection<IModelicaElementChange> update(IResourceDelta delta) 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		LinkedList<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();

		if (!childrenLoaded)
		{
			/* 
			 * we don't want to process changes until 
			 * children elements are loaded 
			 */
			return changes; /* just return an empty list */
		}
				
		for (IResourceDelta d : delta.getAffectedChildren())
		{
			IResource res = d.getResource();
			IModelicaElement element = children.get(res);
			
			switch (d.getKind())
			{
			case IResourceDelta.ADDED:
				try
				{
					element = wrap(res);
				} 
				catch (CompilerException e)
				{
					ErrorManager.showCompilerError(e);
				}
				children.put(res, element);
				changes.add(new ModelicaElementChange(this, element));
				break;
			case IResourceDelta.REMOVED:
				children.remove(res);
				changes.add(new ModelicaElementChange(element,
						ChangeType.REMOVED));
				break;
			case IResourceDelta.CHANGED:
				if (element instanceof ModelicaFolder)
				{
					if (ModelicaFolder.checkIfMorphedIntoPackage(d))
					{
						/* 
						 * replace the folder object with
						 * package object
						 */
						
						/* remove folder object */
						children.remove(res);
						changes.add(new ModelicaElementChange(element,
								ChangeType.REMOVED));

						/* add package object */
						element = wrap(res);
						children.put(res, element);
						changes.add(new ModelicaElementChange(this, element));
					}
					else
					{
						changes.addAll(((ModelicaFolder)element).update(d));
					}
				}
				else if (element instanceof FolderPackage)
				{
					if (ModelicaFolder.checkIfMorphedIntoFolder(d))
					{
						/* 
						 * replace the folder object with
						 * package object
						 */
						
						/* remove folder object */
						children.remove(res);
						changes.add(new ModelicaElementChange(element,
								ChangeType.REMOVED));

						/* add package object */
						element = wrap(res);
						children.put(res, element);
						changes.add(new ModelicaElementChange(this, element));
					}
					else
					{
						changes.addAll(((FolderPackage)element).update(d));
					}
				}
				else if (element instanceof ModelicaElement)
				{
					changes.addAll(((ModelicaElement)element).update(d));
				}
				break;
			}
		}
		
		return changes;
	}
	/**
	 * Checks if the resource can represent a modelica package.
	 * @param res
	 * @return true if the res is a package, false otherwise.
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws CompilerInstantiationException 
	 */
	public static boolean isFolderPackage(IResource res)
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
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
				IParseResults results = CompilerProxy.loadSourceFile(file);
				
				for (String name : results.getClasses())
				{
					if (name.endsWith(folderName))
					{
						return true;
					}
				}
			}
		}

		return false;
	}


	/**
	 * map a IResource to the type of modelica element it represents
	 * @throws CompilerInstantiationException 
	 */
	protected IModelicaElement wrap(IResource res)
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		switch (res.getType())
		{
		case IResource.FOLDER:
			/* a folder is either a package or a just a folder */
			if (isFolderPackage(res))
			{
				/* we have ourself a package */
				return new FolderPackage(this, (IFolder)res);
			}
			else
			{
				/* just a folder */
				return new ModelicaFolder(this, (IContainer)res);
			}
		case IResource.FILE:
			String extension = res.getFileExtension(); 
			if (extension != null && extension.equals("mo"))
			{
				return new ModelicaSourceFile(this, (IFile)res);
			}
		}
		/* only one option left, a regular file */
		return new ModelicaFile(this, (IFile)res);

	}

	public Type getRestrictionType() throws ConnectException
	{
		/* we are a package !*/
		return IModelicaClass.Type.PACKAGE;
	}

	public Collection<IModelicaImport> getImports()
	{
		// TODO implement me
		return new LinkedList<IModelicaImport>();
	}

	public IModelicaClass getParentNamespace() 
	{
		// TODO implement me
		return null;
	}

	
}
