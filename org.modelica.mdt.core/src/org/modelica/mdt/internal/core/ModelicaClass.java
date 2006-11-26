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

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * Superclass of all modelica class/package representation, collects 
 * generic package handling code.
 * 
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo]
 * @author Elmir Jagudin
 * @author Andreas Remar
 * @author Kent Beck
 */
abstract public class ModelicaClass extends ModelicaElement 
	implements IModelicaClass
{
	/**
	 * The namespace where this class is defined or null if 
	 * defined in top-level namespace
	 */
	protected IModelicaClass parentNamespace;
	
	/**
	 * the short name of this class
	 */
	protected String name;
	
	/**
	 * the fully qualified name of this class e.g. foo.bar.hej
	 */
	protected String fullName;

	/*
	 * the file where this class is defined, 
	 * can be null if it is unknown
	 * when the container is unknow the class is assumed to 
	 * be external e.g. defined in system library
	 */
	private IFile container;

	/* class attributes (type of restriction, encapsulated status, etc) */
	private IClassInfo classAttributes = null;

	
	public ModelicaClass(IModelicaElement parent)
	{
		super(parent);
	}

	/**
	 * calculate the base name of this package
	 */
	protected void setFullName()
	{
		if (parentNamespace == null)
		{
			/*
			 * special case for packages that are direct children of
			 * the root package
			 */
			fullName = name;
		}
		else /* general case */
		{
			fullName = parentNamespace.getFullName() + "." + name;
		}
	}
	
	public String getPrefix()
	{
		if (parentNamespace == null)
		{
			return "";
		}
		return parentNamespace.getFullName();
	}

	public String getElementName() 
	{
		return name;
	}

	public String getFullName() 
	{
		return fullName;
	}
	
	public IResource getResource() 
	{
		return container;
	}
	

	public IModelicaClass getParentNamespace() 
	{
		return parentNamespace;
	}
		
	/**
	 * handles the lazyloading of class attributes
	 */
	protected IClassInfo getAttributes() 
		throws CompilerInstantiationException, ConnectException,
			UnexpectedReplyException, CoreException, InvocationError 
	{
		if (classAttributes == null)
		{
			classAttributes = CompilerProxy.getClassInfo(fullName);
		}
		return classAttributes;
	}

	public IDefinitionLocation getLocation()
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CoreException, CompilerInstantiationException
	{
		return getAttributes().getDefinitionLocation();
	}
	
	@Override
	public String getFilePath() 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		return getAttributes().getDefinitionLocation().getPath();
	}
	
	public Restriction getRestriction() 
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException, CoreException, InvocationError
	{
		return getAttributes().getRestriction();
	}
	
	public boolean isEncapsulated()
		throws CompilerInstantiationException, ConnectException, 
			UnexpectedReplyException, CoreException, InvocationError 
	{
		return getAttributes().getEncapsulated();
	}

	@Override
	public Collection<IModelicaElementChange> reload()
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException 
	{
		classAttributes = null;
		return super.reload();
	}


}
