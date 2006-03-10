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
import org.modelica.mdt.core.IParameter;
import org.modelica.mdt.core.ISignature;
import org.modelica.mdt.core.IllegalVisibilityException;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.ModelicaParserException;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementInfo;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.IDefinitionLocation;
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
	/*
	 * Contents of a class are loaded lazily when they are first
	 * queried and 'cached' by storing them as member fields.
	 * 
	 * currently following contens of a modelica class are available for queries:
	 * 
	 * - restriction type of the class
	 * - encapsulated status of the class
	 * - class definition location in the source file
	 * - subclasses/subpackages
	 * - class components
	 * - special input/output components
	 * - import statments
	 *  
	 *  The above information is fetched via the following methods in CompilerProxy:
	 *  
	 *  getElements() provides:
	 * 		- subclasses/subpackages
	 * 		- class components
	 * 		- special input/output components
	 * 		- import statments
	 * 
	 * This information is callectively called for 'class components'.
	 * 
	 *  getClassInfo() provides:
	 * 		- restriction type of the class
	 * 		- encapsulated status of the class
	 * 		- class definition location in the source file
	 * This information is callectively called for 'class attributes'.
	 * 
	 * Whenver it is detected that the source file where this modelica class is
	 * defined have changed on the disk this class is notified by having it's
	 * reload() method invoked.  
	 */
	
	/* class attributes are stored here */
	private IClassInfo classAttributes = null;
	
	/* subpackages and subclasses hashed by the thier's shortname */
	private Hashtable<String, IModelicaElement> children = null;
	
	/* the import statments */
	private Collection<IModelicaImport> imports;
	
	/* input and output parameters found in the signature */
	private LinkedList<IParameter> inputParams = null;
	private LinkedList<IParameter> outputParams = null;

	public InnerClass(IModelicaSourceFile container,
					IModelicaClass parentNamespace,
					String name)
	{
		super(container);
		
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
	public InnerClass(IModelicaClass parentNamespace, 
			String name, RestrictionType restrictionType)
	{
		this(null, parentNamespace, name);
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
		
		inputParams = new LinkedList<IParameter>();
		outputParams = new LinkedList<IParameter>();
	
		for (ElementInfo info : CompilerProxy.getElements(fullName))
		{
			String elementType = info.getElementType();
			
			/* a sub package */
			if (elementType.equals("classdef"))
			{
				String className = info.getClassName();
				elements.put(className, 
					new InnerClass(getSourceFile(), this, info.getClassName()));
			}
			/* a component */
			else if (elementType.equals("component"))
			{
				/*
				 * names have following format:
				 * names={component_name,"component_comment"}
				 * we neet to get the component name
				 */ 
				
				List comp = null;
				try
				{
					comp = ModelicaParser.parseList(info.getNames());
				}
				catch(ModelicaParserException e)
				{
					throw new UnexpectedReplyException("Unable to parse " +
							"returned list: " + e.getMessage());
				}
				
				String componentName = comp.elementAt(0).toString();
				String elementFile = info.getElementFile();
				IDefinitionLocation location =
					new DefinitionLocation(elementFile, 
							info.getElementStartLine(),
							info.getElementStartColumn(),
							info.getElementEndLine(),
							info.getElementEndColumn());

				try
				{
					elements.put(componentName, 
							new ModelicaComponent(
									this,
									componentName,
									IModelicaComponent.Visibility.parse
										(info.getElementVisibility()),
									location));					
				}
				catch(IllegalVisibilityException e)
				{
					throw new UnexpectedReplyException("Unexpected visibility: "
							+ e.getMessage());
				}
				
				String typeName = info.getTypeName();
				if(info.getElementVisibility().equals("public")
						&& info.getDirection().equals("input"))
				{
					inputParams.add(new Parameter(componentName, typeName));
				}
				else if(info.getElementVisibility().equals("public")
						&& info.getDirection().equals("output"))
				{
					outputParams.add(new Parameter(componentName, typeName));
				}
			}			
			/* an import statment */
			else if (elementType.equals("import"))
			{
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
		
		/*
		 * the reload strategy is as follows:
		 */
		
		 /*
		  * all class attribute fields are just reset and 
		  * lazily reloaded as they are queried
		  */
		classAttributes = null;

		 /* 
		  * new class component are fetched and compared to
		  * the old in order to generate a change events list.
		  * 
		  * components that are not new or were removed are notified
		  * of the change to give them a chance to update thier's state
		  */
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
		return getAttributes().getDefinitionLocation().getRegion();
	}

	/**
	 * handles the lazyloading of class attributes
	 */
	private IClassInfo getAttributes() 
		throws CompilerInstantiationException, ConnectException,
			UnexpectedReplyException 
	{
		if (classAttributes == null)
		{
			classAttributes = CompilerProxy.getClassInfo(fullName);
		}
		return classAttributes;
	}

	@Override
	public String getFilePath() 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException
	{
		return getAttributes().getDefinitionLocation().getPath();
	}

	public RestrictionType getRestrictionType() 
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException
	{
		return getAttributes().getRestrictionType();
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
	
	public ISignature getSignature()
		throws ConnectException, InvocationError, UnexpectedReplyException,
			CompilerInstantiationException, CoreException
	{
		if(inputParams == null || outputParams == null)
		{
			loadElements();
		}
		
		return new Signature(inputParams.toArray(
				new IParameter[inputParams.size()]),
				outputParams.toArray(new IParameter[outputParams.size()]));
	}

	public boolean isEncapsulated()
		throws CompilerInstantiationException, ConnectException, 
			UnexpectedReplyException 
	{
		return getAttributes().getEncapsulated();
	}
}
