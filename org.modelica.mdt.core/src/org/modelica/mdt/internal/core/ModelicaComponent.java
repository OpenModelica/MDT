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
import java.util.LinkedList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * @author Homer Simpson
 */
public class ModelicaComponent extends ModelicaElement implements IModelicaComponent
{
	private String name;
	private Visibility visibility;
	private IDefinitionLocation location;
	private String typeName;
	
	/**
	 * Create class component
	 * 
	 * @param parent the class where this component is defined
	 * @param name name if this component
	 * @param visibility whatever this component is public or protected
	 * @param location location in the source code file
	 */
	public ModelicaComponent(IModelicaClass parent, String name, String typeName, Visibility visibility, IDefinitionLocation location)
	{
		super(parent);
		this.name = name;
		this.visibility = visibility;
		this.location = location;
		this.typeName = typeName;
	}

	/**
	 * Set the values from the class component from another component!!
	 * 
	 * @param IModelicaComponent
	 */
	public void setModelicaComponent(ModelicaComponent changedComponent)
	{
		this.visibility = changedComponent.getVisibility();
		this.typeName = changedComponent.getTypeName();
		try
		{
			this.location = changedComponent.getLocation();
		}
		catch(CoreException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getElementName()
	{
		return name;
	}

	public Visibility getVisibility()
	{
		return visibility;
	}
	
	public IResource getResource()
	{
		return getParent().getResource();
	}
	
	/**
	 * @see org.modelica.mdt.core.IModelicaElement#getLocation()
	 */
	public IDefinitionLocation getLocation()
		throws CoreException
	{
		return location;
	}

	@Override
	public String getFilePath() 
		throws ConnectException, UnexpectedReplyException, InvocationError
	{
		return location.getPath();
	}

	public String getFullName() 
	{
		return getParent().getFullName() + "." + name;
	}
	
	
	public String getTypeName()
	{
		return typeName;
	}
	
	/**
	 * All modelica elements that have a direct mapping between the IResource
	 * (e.g. ModelicaFile -> IFile, FolderPackage -> IFolder) will recive a
	 * call on this method when it have been detected that the underlying 
	 * IResouce have been changed.
	 * 
	 *  @param delta The resource delta which is rooted at the IResource of
	 *  this element 
	 */
	public Collection<IModelicaElementChange> update(IResourceDelta delta) 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		/* return an MODIFIED by default */
		LinkedList<IModelicaElementChange> changes = new LinkedList<IModelicaElementChange>();
		/* if only the markers have changed, don't bother! */
		if ((delta.getFlags() & IResourceDelta.MARKERS) != 0 &&
			(delta.getFlags() & IResourceDelta.OPEN) == 0	
		) return changes;	
		
		changes.add(new ModelicaElementChange(this, ChangeType.MODIFIED, delta));
		return changes;
	}

	/**
	 * This method will be invoked on modelica elements, that do not have
	 * a direct mapping to a IResource, when it is suspected that their 
	 * representation in the compiler have changed.
	 *  
	 * The element should requery the compiler and return the difference as
	 * a collection of IModelicaElementChange:s.
	 * 
	 * @return the changes to the element and it's children, or empty collection
	 * if the element and it's children are not changed.
	 */
	public Collection<IModelicaElementChange> reload()
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		/* return an MODIFIED by default */
		LinkedList<IModelicaElementChange> changes = new LinkedList<IModelicaElementChange>();
		changes.add(new ModelicaElementChange(this, ChangeType.MODIFIED, null));
		return changes;
	}	
	
}
