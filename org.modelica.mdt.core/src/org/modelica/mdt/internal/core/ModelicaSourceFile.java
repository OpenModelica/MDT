/******************************************************************************
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo]
 * Copyright (c) 2002-2006, Adrian Pop [adrpo@ida.liu.se],
 * Programming Environments Laboratory (PELAB),
 * Department of Computer and Information Science (IDA), 
 * Linköpings University (LiU). 
 * All rights reserved.
 *
 * http://www.ida.liu.se/~adrpo/license/
 *
 * NON-COMMERCIAL terms and conditions [NON-COMMERCIAL setting]:
 * Permission to use, copy, modify, and distribute this software and
 * its documentation in source or binary form (including products 
 * developed or generated using this software) for NON-COMMERCIAL 
 * purposes and without fee is hereby granted, provided that this 
 * copyright notice appear in all copies and that both the copyright 
 * notice and this permission notice and warranty disclaimer appear 
 * in supporting documentation, and that the name of The Author is not 
 * to be used in advertising or publicity pertaining to distribution 
 * of the software without specific, prior written permission.
 * 
 * COMMERCIAL terms and conditions [COMMERCIAL setting]:
 * COMMERCIAL use, copy, modification and distribution in source 
 * or binary form (including products developed or generated using
 * this software) is NOT permitted without prior written agreement 
 * from Adrian Pop [adrpo@ida.liu.se].
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 *****************************************************************************/
package org.modelica.mdt.internal.core;

import java.io.File;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.ISourceRegion;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ListElement;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.builder.SyntaxChecker;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * @author Adrian Pop
 * @author Elmir Jagudin
 */
public class ModelicaSourceFile extends ModelicaElement implements IModelicaSourceFile 
{
	private IFile file;

	/* classes and packages in this file hashed by name */
	Hashtable<String, IModelicaElement> children = null;	

	/**
	 * Create a modelica file that is inside a package. All the definitions
	 * in the file are placed in the namespace under the parent package.
	 */
	public ModelicaSourceFile(FolderPackage parent, IFile file) 
	{
		super(parent);
		this.file = file;
	}

	/**
	 * Create a modelica source file that is inside a folder. All the 
	 * definitions in the file are placed in the root namespace.
	 */
	public ModelicaSourceFile(ModelicaFolder parent, IFile file) 
	{
		super(parent);
		this.file = file;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaElement#getElementName()
	 */
	public String getElementName()
	{
		return file.getName();
	}

	@Override
	public IFile getResource() 
	{
		return file;
	}

	public Collection<IModelicaElement> getChildren()
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException 
	{
		if (children == null)
		{
			children = loadElements();
		}
		
		return children.values();
	}

	private Hashtable<String, IModelicaElement> loadElements()
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		Hashtable<String, IModelicaElement> elements = 
			new Hashtable<String, IModelicaElement>();
		
		IParseResults res = SyntaxChecker.loadFileAndReportErrors(file, false);		
		
		IModelicaElement parent = getParent();
		FolderPackage parentPackage = null;
		
		/*
		 * If we're inside a package, define the classes inside the
		 * parent package.
		 */
		if(parent instanceof FolderPackage)
		{
			parentPackage = (FolderPackage)parent;
			String ppName = parentPackage.getFullName();
			String filePath = file.getFullPath().toString();

			for(String name : res.getClasses())
			{
				if(name.equals(ppName) && (filePath.endsWith(File.separator + "package.mo")))
				{
					List list = null;
					try
					{
						list = CompilerProxy.getClassNames(name);
					}
					catch(UnexpectedReplyException e)
					{
						/* Ignore, just don't add something that contains
						 * errors */
					}
					
					if(list != null)
					{
						for(ListElement element : list)
						{
							elements.put(element.toString(), new InnerClass(this, parentPackage, element.toString(), null, null));
						}
					}
					
					/* don't add the package definition to the package.mo */
					continue;
				}

				String prefix = "";

				/* If there is a . then there is a prefix! (We hope) */
				if(name.lastIndexOf('.') != -1)
				{
					prefix = name.substring(0, name.lastIndexOf('.'));
				}

				/* If the class has a prefix that equals the package that this
				 * class is in, just remove the prefix. */
				if(prefix.equals(ppName))
				{
					name = name.substring(ppName.length()+1);
					elements.put(name, new InnerClass(this, parentPackage, name, null, null));
				}
				else
				{
					/* Something is wrong, return an empty list of elements */
					return new Hashtable<String, IModelicaElement>();
				}
			}
		}
		/*
		 * If we're not inside a package, simply define the classes without
		 * a parent.
		 */
		else
		{
			if (res != null)
			{
				for(String name : res.getClasses())
				{
					elements.put(name, new InnerClass(this, null, name, null, null));
				}
			}
		}	
		
		return elements;
	}
	
	public boolean hasChildren()
		throws CoreException, ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		return !getChildren().isEmpty();
	}


	
	@Override
	public Collection<IModelicaElementChange> update(IResourceDelta delta)
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{	
		LinkedList<IModelicaElementChange> changes = new LinkedList<IModelicaElementChange>();
		
		if ((delta.getFlags() & IResourceDelta.CONTENT) == 0)
		{
			return changes;
		}
		
		/* this file have been change, add an the event that describes that */
		changes.add(new ModelicaElementChange(this, ChangeType.MODIFIED, delta));
		
		if (children == null)
		{
			/* if children are not loaded, then we can't update */
			return changes;
		}

		Hashtable<String, IModelicaElement> newChildren = loadElements();
		@SuppressWarnings("unchecked")
		Hashtable<String, IModelicaElement> oldChildren = (Hashtable<String, IModelicaElement>) children.clone();
		
		
		for (IModelicaElement element : newChildren.values())
		{
		
			ModelicaElement oldElement = (ModelicaElement)oldChildren.remove(element.getElementName());
			
			if (oldElement == null)
			{
				/* new element added */
				children.put(element.getElementName(), element);
				changes.add(new ModelicaElementChange(this, element, delta));
			}
			else
			{
				/* element present before, refresh ! */
				changes.addAll(oldElement.reload());
			}
		}
		
		/* now there is only removed elements in the oldChildren table */
		for (IModelicaElement element : oldChildren.values())
		{
			children.remove(element.getElementName());
			changes.add(new ModelicaElementChange(element, ChangeType.REMOVED, delta));
		}
		
		return changes;
	}


	public IModelicaClass[] getRootClasses() throws ConnectException, UnexpectedReplyException, CompilerInstantiationException
	{
		/* make sure the children are loaded */
		if (children == null)
		{
			children = loadElements();
		}

		Vector<IModelicaClass> pkgs = new Vector<IModelicaClass>();
		IModelicaClass classElement;
		
		for (Object element : children.values())
		{
			if (element instanceof IModelicaClass)
			{
				classElement = (IModelicaClass) element;
				if (classElement.getPrefix().equals(""))
				{
					pkgs.add(classElement);
				}
			}
		}
		
		IModelicaClass[] arry =  new IModelicaClass[pkgs.size()];

		return pkgs.toArray(arry);
	}


	public IModelicaSourceFile getSourceFile()
	{
		return this;
	}

	/**
	 * @deprecated
	 * @see IModelicaSourceFile#getClassAt(int position) 
	 */
	public IModelicaClass getClassAt(int position)
	throws ConnectException, UnexpectedReplyException, CompilerInstantiationException, InvocationError, CoreException
	{
		/* load children if needed */
		if (children == null)
		{
			children = loadElements();
		}
		
		return findClassDefAt(children.values(), position);
	}
	
	/**
	 * @see IModelicaSourceFile#getClassAt(ISourceRegion sourceRegion) 
	 */
	public IModelicaClass getClassAt(ISourceRegion sourceRegion)
	throws ConnectException, UnexpectedReplyException, CompilerInstantiationException, InvocationError, CoreException
	{
		/* load children if needed */
		if (children == null)
		{
			children = loadElements();
		}
		
		return findClassDefAt(children.values(), sourceRegion);
	}

	/**
	 * Checks if there is a class definition at specified position
	 * among provided classes.
	 * @deprecated
	 * @param elements the elements among which look for class definitions
	 * @param position the character at which look for the class definiton
	 * @return the innermost class definition found or null if no class
	 * definitions region overlaps position
	 */
	private IModelicaClass findClassDefAt(Collection<? extends IModelicaElement> elements, int position)
	
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{
		/*
		 * do basically an optimized tree search for the innermost
		 * class definition around queried position
		 */

		for (IModelicaElement el : elements)
		{
			/* skip non-class elements */
			if (!(el instanceof IModelicaClass))
			{
				continue;
			}

			IModelicaClass clazz = (IModelicaClass)el;
			IRegion reg = clazz.getLocation().getRegion();
			int start = reg.getOffset();
			int end = start + reg.getLength() - 1;
			
			if (position >= start && position <= end)
			{
				/* check if position is inside a subclass definition */
				IModelicaClass subclazz = findClassDefAt(clazz.getChildren(), position);
				
				if (subclazz != null)
				{
					return subclazz;
				}
				return clazz;
			}
			
		}
		
		return null; /* no definition found at position */
	}


	/**
	 * Checks if there is a class definition at specified position
	 * among provided classes.
	 * 
	 * @param elements the elements among which look for class definitions
	 * @param position the character at which look for the class definiton
	 * @return the innermost class definition found or null if no class
	 * definitions region overlaps position
	 */
	private IModelicaClass findClassDefAt(Collection<? extends IModelicaElement> elements, ISourceRegion sourceRegion)
	
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{
		/*
		 * do basically an optimized tree search for the innermost
		 * class definition around queried position
		 */

		for (IModelicaElement el : elements)
		{
			/* skip non-class elements */
			if (!(el instanceof IModelicaClass))
			{
				continue;
			}

			IModelicaClass clazz = (IModelicaClass)el;
			ISourceRegion reg = clazz.getLocation().getSourceRegion();
			
			if (reg.contains(sourceRegion))
			{
				/* check if position is inside a subclass definition */
				IModelicaClass subclazz = findClassDefAt(clazz.getChildren(), sourceRegion);
				
				if (subclazz != null)
				{
					return subclazz;
				}
				return clazz;
			}
			
		}
		
		return null; /* no definition found at position */
	}
	
	public String getFullName() 
	{
		return getElementName();
	}
	
}
