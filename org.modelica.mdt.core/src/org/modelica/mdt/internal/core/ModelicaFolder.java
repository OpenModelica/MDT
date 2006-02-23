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
import java.util.Vector;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * @author Elmir Jagudin
 */
public class ModelicaFolder extends ModelicaParent implements IModelicaFolder
{
	private IContainer container;
	
	private boolean childrenLoaded = false;
	
	private Hashtable<IResource, IModelicaElement> children = 
			new Hashtable<IResource, IModelicaElement>();
	
	protected ModelicaFolder(IModelicaElement parent, IContainer cont)
	{
		super(parent);
		this.container = cont;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaElement#getElementName()
	 */
	public String getElementName() 
	{
		return container.getName();
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
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		return update(null, delta);
	}

	/**
	 * A special update method where alternative root of the changed elements 
	 * can be specified. This is a little hack to enable ModelicaProject object
	 * to specifie itself as the parent, otherwise updating the tree in the
	 * projects view does not work. That is due to the fact that root folder
	 * of any project does not have its own node. Root folders children are 
	 * displayed as direct children of the project node. This kinda sucks,
	 * pehaps there are better design alternatives.
	 * 
	 * @param root
	 * @param delta
	 * @return
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws InvocationError 
	 * @throws CompilerInstantiationException 
	 */
	public List<IModelicaElementChange> update(IModelicaElement root, 
			IResourceDelta delta) 
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
		
		IModelicaElement parent = (root != null) ? root : this;

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
					if (checkIfMorphedIntoPackage(d))
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
						changes.add(new ModelicaElementChange(parent, element));
					}
					else
					{
						changes.addAll(((ModelicaFolder)element).update(d));
					}
				}
				else if (element instanceof FolderPackage)
				{
					if (checkIfMorphedIntoFolder(d))
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
						changes.add(new ModelicaElementChange(parent, element));
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
				else
				{
					changes.add(new ModelicaElementChange(element, 
							ChangeType.MODIFIED));
				}
				break;
				
			}
		}
		
		return changes;
	}
	
	public static boolean checkIfMorphedIntoPackage(IResourceDelta delta)
	{
		for (IResourceDelta d : 
			delta.getAffectedChildren(IResourceDelta.ADDED | IResourceDelta.CHANGED))
		{
			IResource res = d.getResource();
			
			if (res.getType() == IResource.FILE && 
					res.getName().equals("package.mo"))
			{
				try
				{
					return FolderPackage.isFolderPackage(res.getParent());
				}
				catch (CompilerException e)
				{
					ErrorManager.showCompilerError(e);
					/* let's say that we can't morph to packages on error */
					return false;
				}
			}
		}
		return false;
	}

	public static boolean checkIfMorphedIntoFolder(IResourceDelta delta)
	{
		for (IResourceDelta d : 
			delta.getAffectedChildren(IResourceDelta.REMOVED | IResourceDelta.CHANGED))
		{
			IResource res = d.getResource();
			
			if (res.getType() == IResource.FILE && 
					res.getName().equals("package.mo"))
			{
				switch (d.getKind())
				{
				case IResourceDelta.REMOVED: 
					/*
					 * if we see that a file named package.mo was removed
					 * then we know that the package is now a folder
					 */
					return true;
				case IResourceDelta.CHANGED:
					/*
					 * there is a posability that the package.mo was changed
					 * so that it does not contain a package definition any more
					 */
					try
					{
						return !FolderPackage.isFolderPackage(res.getParent());
					}
					catch (CompilerException e)
					{
						ErrorManager.showCompilerError(e);
						/* let's say that we can't morph to folder on error */
						return false;
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
	private IModelicaElement wrap(IResource res)
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		switch (res.getType())
		{
		case IResource.FOLDER:
			/* a folder is either a package or a just a folder */
			if (FolderPackage.isFolderPackage(res))
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
	
	/**
	 * the recursive function to look for package roots in this
	 * folder and the subfolders. This function is used by 
	 * IModelicaProject.getRootPackages() method. 
	 *  
	 * @return
	 * @throws ConnectException
	 * @throws CompilerInstantiationException
	 * @throws UnexpectedReplyException
	 * @throws CoreException
	 */
	protected Vector<IModelicaClass> getRootPackages() 
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException, CoreException
	{
		/* make sure children are loaded */
		if (!childrenLoaded)
		{
			loadChildren();
			childrenLoaded = true;
		}

		Vector<IModelicaClass> pkgs = new Vector<IModelicaClass>();
		IModelicaClass classElement;
		IModelicaSourceFile fileElement;
		
		/*
		 * go over the children and put all the packages in a list
		 * and search for root packages in subfolders and files in this
		 * folder
		 */
		for (Object element : children.values())
		{
			if (element instanceof IModelicaClass)
			{
				classElement = (IModelicaClass) element;
				if ((classElement.getRestrictionType() == 
					        IModelicaClass.Type.PACKAGE) &&
				    classElement.getPrefix().equals(""))
				{
					pkgs.add(classElement);
				}
			}
			else if (element instanceof ModelicaFolder)
			{
				pkgs.addAll(((ModelicaFolder)element).getRootPackages());
			}
			else if (element instanceof IModelicaSourceFile)
			{
				fileElement = (IModelicaSourceFile) element;				
				for (IModelicaClass e : fileElement.getRootPackages())
				{
					pkgs.add(e);
				}
			}
		}
		
		return pkgs;
	}

	public String getFullName() 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
