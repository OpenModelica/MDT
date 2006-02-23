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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IllegalTypeException;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementsInfo;
import org.modelica.mdt.core.compiler.IElementLocation;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * A package that is defined inside a modelica file or in the system library.
 * 
 * @author Elmir Jagudin
 */
public class InnerClass extends ModelicaClass
{
	/* our restriction type */
	private Type restrictionType;
	private boolean typeKnown = false;
	
	private IElementLocation location = null;

	/* subpackages and subclasses hashed by the thier's shortname */
	private Hashtable<String, IModelicaElement> children = null;
	
	/* the import statments */
	private Collection<IModelicaImport> imports;
	
	public InnerClass(IModelicaSourceFile container,
					IModelicaClass parentNamespace,
					String name, IElementLocation location,
					Type restrictionType)
	{
		super(container);
		this.location = location;
		this.restrictionType = restrictionType;
		typeKnown = true;
		
		this.parentNamespace = parentNamespace;
		this.name = name;
		setFullName();
	}
	
	/**
	 * Create a modelica package that is defined in unknow location, for example
	 * a standard library class. This method assumes that a class named
	 * 'prefix'.'name' is loaded into OMC.
	 * 
	 * @param prefix
	 * @param name
	 */
	public InnerClass(IModelicaClass parentNamespace, String name, Type restrictionType)
	{
		this(null, parentNamespace, name, null, restrictionType);
	}


	public InnerClass(IModelicaSourceFile container, IModelicaClass parentNamespace, String name)
	{
		super(container);
		this.parentNamespace = parentNamespace;
		this.name = name;
		setFullName();
	}

	/**
	 * @see org.modelica.mdt.core.IParent#getChildren()
	 */
	public Collection<IModelicaElement> getChildren() 
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CoreException, CompilerInstantiationException
	{
		if (children == null)
		{
			children = loadElements();
		}
		
		return children.values();
	}
	
	private Hashtable<String, IModelicaElement> loadElements() 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		Hashtable<String, IModelicaElement> elements = 
			new Hashtable<String, IModelicaElement>();
		
		imports = new LinkedList<IModelicaImport>();
	
		for (ElementsInfo info : CompilerProxy.getElementsInfo(fullName))
		{
			String elementType = info.getElementType();
			
			/* a sub package */
			if (elementType.equals("classdef"))
			{
				
				String file = info.getElementFile();

				IElementLocation location =
					new ElementLocation(file, 
							info.getElementStartLine(),
							info.getElementStartColumn(),
							info.getElementEndLine(),
							info.getElementEndColumn());
				
				String className = info.getClassName();
				
				IModelicaClass.Type type = null;
				try
				{
					type = IModelicaClass.Type.parse
						(info.getClassRestriction());
				}
				catch(IllegalTypeException e)
				{
					throw new UnexpectedReplyException("Illegal type: "
							+ e.getMessage());
				}
				
				elements.put(className, 
					new InnerClass(getSourceFile(), this, className, location, 
							type));
			}
			/* a component */
			else if (elementType.equals("component"))
			{
				/*
				 * names have following format:
				 * names={component_name,"component_comment"}
				 * we neet to get the component name
				 */ 
				List comp = ModelicaParser.parseList(info.getNames());
				
				String componentName = comp.elementAt(0).toString();
				String elementFile = info.getElementFile();
				IElementLocation location =
					new ElementLocation(elementFile, 
							info.getElementStartLine(),
							info.getElementStartColumn(),
							info.getElementEndLine(),
							info.getElementEndColumn());

				
				elements.put(componentName, 
						new ModelicaComponent(
								this,
								componentName,
								IModelicaComponent.Visibility.parse
									(info.getElementVisibility()),
								location));
			}			
			/* an import statment */
			else if (elementType.equals("import"))
			{
				System.out.println(info.getKind() + " " + info.getPath());
				String importType = info.getKind();
				
				if (importType.equals("qualified"))
				{
					imports.add(new ModelicaImport
							(getProject(), true, info.getPath()));
				}
				else if (importType.equals("unqualified"))
				{
					imports.add(new ModelicaImport
							(getProject(), false, info.getPath()));
				}
				else if (importType.equals("named"))
				{
					imports.add(new ModelicaImport
							(getProject(), info.getId(), info.getPath()));
				}
				else
				{
					ErrorManager.logBug(CorePlugin.getSymbolicName(),
							"import statment of unexpected type '" +
							importType + "' encountered");
				}
			}
		}
	
		return elements;
	}

	/** 
	 * @see org.modelica.mdt.core.IParent#hasChildren()
	 */
	public boolean hasChildren()
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CoreException, CompilerInstantiationException
	{
		return !getChildren().isEmpty();
	}

	@Override
	public Collection<IModelicaElementChange> reload()
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		LinkedList<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();
		
		if (children == null)
		{
			/* if children are not loaded, then we can't reload */
			return changes;
		}


		Hashtable<String, IModelicaElement> newChildren = loadElements();
		
		@SuppressWarnings("unchecked")
		Hashtable<String, IModelicaElement> oldChildren = 
			(Hashtable<String, IModelicaElement>) children.clone();
		
		
		for (IModelicaElement element : newChildren.values())
		{
			ModelicaElement oldElement =
				(ModelicaElement)
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
	
	public IResource getResource()
	{
		IModelicaSourceFile sourceFile = getSourceFile();
		if (sourceFile != null)
		{
			return sourceFile.getResource();
		}
		return null;
	}
	
	/**
	 * @throws InvocationError if there were errors 
	 * 	communicating with compiler
	 * @throws UnexpectedReplyException if there were errors
	 * 	 communicating with compiler
	 * @throws ConnectException if there were errors
	 * 	 communicating with compiler
	 * @throws CoreException if there were errors reading
	 * 	 the source file of this element
	 * @throws CompilerInstantiationException if there were errors
	 * 	 communicating with compiler
	 * @see org.modelica.mdt.core.IModelicaElement#getLocation()
	 */
	public IRegion getLocation()
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CoreException, CompilerInstantiationException
	{
		if (location == null)
		{
			loadClassLocation();
		}

		return location.getRegion();
	}

	@Override
	public String getFilePath() 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException
	{
		if (location == null)
		{
			loadClassLocation();
		}
		return location.getPath();
	}

	private void loadClassLocation()
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException
	{
		location = CompilerProxy.getClassLocation(fullName);
	}


	public Type getRestrictionType() 
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException
	{
		if(typeKnown == false)
		{
			Type t = CompilerProxy.getRestrictionType(fullName);
			if(t != null)
			{
				restrictionType = t;
			}
			
			typeKnown = true;
		}
	
		return restrictionType;
	}

	public Collection<IModelicaImport> getImports() 
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{
		if (children == null)
		{
			children = loadElements();
		}

		return imports;
	}

	@Override
	public IModelicaElement getParent()
	{
		IModelicaElement p = super.getParent();
		if (p == null)
		{
			/* 
			 * the only way we can have a null parent is if we are a
			 * top-level class in the standard library
			 */
			return ModelicaCore.getModelicaRoot().getStandardLibrary();
		}
		return p;
	}
}
