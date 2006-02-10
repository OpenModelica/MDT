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
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * @author Elmir Jagudin
 */
public class ModelicaSourceFile extends ModelicaElement implements IModelicaSourceFile 
{
	private IFile file;
	private FolderPackage parentPackage;

	/* classes and packages in this file hashed by name */
	Hashtable<String, IModelicaElement> children = null;	

	/**
	 * Create a modelica file that is inside a package. All the definitions
	 * in the file are placed in the namespace under the parent package.
	 * 
	 * @param parent
	 * @param file
	 */
	public ModelicaSourceFile(FolderPackage parent, IFile file) 
	{
		this.parentPackage = parent;
		this.file = file;
	}

	
	public ModelicaSourceFile(IFile file) 
	{
		this.file = file;
	}

	/* (non-Javadoc)
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

	public Collection<? extends IModelicaElement> getChildren()
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

		IParseResults res = CompilerProxy.loadSourceFile(file);
		for (String name : res.getClasses())
		{
			if (parentPackage == null)
			{
				/* we are not inside a package */
				elements.put(name, new InnerClass(this, "", name));
			}
			else
			{
				elements.put(name, 
						new InnerClass(this, parentPackage.getFullName(),
						name));
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
			CompilerInstantiationException
	{
		LinkedList<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();
		
		if (children == null)
		{
			/* if children are not loaded, then we can't update */
			return changes;
		}

		Hashtable<String, IModelicaElement> newChildren = loadElements();
		@SuppressWarnings("unchecked")
		Hashtable<String, IModelicaElement> oldChildren = 
			(Hashtable<String, IModelicaElement>) children.clone();
		
		
		for (IModelicaElement element : newChildren.values())
		{
		
			ModelicaElement oldElement = (ModelicaElement)
				oldChildren.remove(element.getElementName());
			
			if (oldElement == null)
			{
				/* new element added */
				children.put(element.getElementName(), element);
				changes.add(new ModelicaElementChange(this, element));
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
			changes.add(new ModelicaElementChange(element, ChangeType.REMOVED));
		}
		
		return changes;
	}


	public IModelicaClass[] getRootPackages() 
		throws ConnectException, UnexpectedReplyException, CompilerInstantiationException
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
				if ((classElement.getRestrictionType() == 
					        IModelicaClass.Type.PACKAGE) &&
				    classElement.getPrefix().equals(""))
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

}
