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

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.builder.SyntaxChecker;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.ElementLocation;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * A package that is defined inside a modelica file or in the system library.
 * 
 * @author Elmir Jagudin
 */
public class InnerPackage extends ModelicaPackage
{
	
	/*
	 * the file where this package is defined, 
	 * can be null if it is unknown
	 * when the container is unknow the class is assumed to 
	 * be external e.g. defined in system library
	 */
	private IFile container;
	
	private ElementLocation location = null;

	/* subpackages and subclasses hashed by the thier's shortname */
	private Hashtable<String, Object> children = null;
	
	public InnerPackage(IFile container, String prefix, String name)
	{
		this.container = container;
		this.prefix = prefix;
		this.name = name;
		setFullName();
		
	}
	
	/**
	 * Create a modelica package that is defined in unknow location, for example
	 * a system library class. This method assumes that a class named
	 * 'prefix'.'name' is loaded into OMC.
	 * 
	 * @param prefix
	 * @param name
	 */
	protected InnerPackage(String prefix, String name)
	{
		this(null, prefix, name);
	}


	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IParent#getChildren()
	 */
	public Collection<Object> getChildren() throws ConnectionException,
			UnexpectedReplyException, InvocationError, CoreException
	{
		if (children == null)
		{
			children = loadElements();
		}
		
		return children.values();
	}
	
	private Hashtable<String, Object> loadElements() 
		throws ConnectionException, UnexpectedReplyException, InvocationError 
	{
		Hashtable<String, Object> elements = new Hashtable<String, Object>();
	
		for (String name : OMCProxy.getPackages(fullName))
		{
			elements.put(name, new InnerPackage(container, fullName, name));
		}
		
		for (String name : OMCProxy.getClassNames(fullName))
		{
			elements.put(name, new ModelicaClass(container, fullName, name));
		}
	
		return elements;
	}


	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IParent#hasChildren()
	 */
	public boolean hasChildren()
		throws ConnectionException, UnexpectedReplyException, InvocationError,
		CoreException 
	{
		return !getChildren().isEmpty();
	}

	@Override
	public Collection<IModelicaElementChange> reload()
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		LinkedList<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();
		
		if (children == null)
		{
			/* if children are not loaded, then we can't reload */
			return changes;
		}


		Hashtable<String, Object> newChildren = loadElements();

		@SuppressWarnings("unchecked")
		Hashtable<String, Object> oldChildren = 
			(Hashtable<String, Object>) children.clone();
		
		
		for (Object element : newChildren.values())
		{
			ModelicaElement moElement = (ModelicaElement) element;

			ModelicaElement oldElement = (ModelicaElement)
				oldChildren.remove(moElement.getElementName());
		
			if (oldElement == null)
			{
				/* new element added */
				children.put(moElement.getElementName(), element);
				changes.add(new ModelicaElementChange(this, element));
			}
			else
			{
				/* element present before, refresh ! */
				changes.addAll(oldElement.reload());
			}
		}
		
		/* now there is only removed elements in the oldChildren table */
		for (Object element : oldChildren.values())
		{
			ModelicaElement moElement = (ModelicaElement) element;

			children.remove(moElement.getElementName());
			changes.add(new ModelicaElementChange(element, ChangeType.REMOVED));
		}
		
		return changes;
	}
	
	public IResource getResource()
	{
		return container;
	}
	
	/**
	 * @throws InvocationError 
	 * @throws CoreException 
	 * @see org.modelica.mdt.core.IModelicaElement#getLocation()
	 */
	public IRegion getLocation() 
		throws ConnectionException, UnexpectedReplyException, 
			InvocationError, CoreException
	{
		if (location == null)
		{
			loadElementLocation();
		}
		
		if (container != null)
		{
			SyntaxChecker.getLineRegion(container, location.getLine());
		}

		IRegion reg = null;
		
		try
		{
			reg = 
				SyntaxChecker.getLineRegion(location.getPath(), 
						location.getLine());
		}
		catch (FileNotFoundException e)
		{
			throw new CoreException(
					new Status(IStatus.ERROR,
								MdtPlugin.getSymbolicName(),
								IStatus.OK, 
								"could not find modelica source file " + 
									location.getPath(),
								e));
		}
		return reg;
	}

	@Override
	public String getFilePath() 
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		if (location == null)
		{
			loadElementLocation();
		}
		return location.getPath();
	}

	private void loadElementLocation()
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		location = OMCProxy.getElementLocation(fullName);
	}



}
