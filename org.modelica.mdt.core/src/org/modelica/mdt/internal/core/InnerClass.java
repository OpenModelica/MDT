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
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.Element;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ListElement;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.builder.SyntaxChecker;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
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
	
	/*
	 * the file where this class is defined, can be null if it is unknown
	 * when the container is unknow the class is assumed to 
	 * be external e.g. defined in system library
	 */
	private IModelicaSourceFile sourceFile;
	
	private IElementLocation location = null;;

	/* subpackages and subclasses hashed by the thier's shortname */
	private Hashtable<String, IModelicaElement> children = null;
	
	public InnerClass(IModelicaSourceFile container, String prefix, String name,
					IElementLocation location, Type restrictionType)
	{
		this.sourceFile = container;
		this.location = location;
		this.restrictionType = restrictionType;
		typeKnown = true;
		
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
	public InnerClass(String prefix, String name, Type restrictionType)
	{
		this(null, prefix, name, null, restrictionType);
	}


	public InnerClass(IModelicaSourceFile container, String prefix, String name)
	{
		this.sourceFile = container;
		this.prefix = prefix;
		this.name = name;
		setFullName();
	}

	/**
	 * @see org.modelica.mdt.core.IParent#getChildren()
	 */
	public Collection<? extends IModelicaElement> getChildren() 
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
			CompilerInstantiationException
	{
		Hashtable<String, IModelicaElement> elements = 
			new Hashtable<String, IModelicaElement>();
	
		// 
		// TODO this parsing code should not be here, it should be pushed
		// into the compiler plugin (org.modelica.mdt.omc) somehow
		//
		String str;
		String elementType;
		String elementFile = "";
		String classLine = "";
		String classFile = "";
		String className = "";
		String classRestriction = "";
		String elementVisibility = "";
		String elementLine = "";
		String names = "";

		for (ListElement o : CompilerProxy.getElementsInfo(fullName))
		{
			 
			elementType = "";
			for (ListElement element : ((List)o))
			{
				/* parse a single the elements info list */
				str = ((Element)element).toString();
				
				if (str.startsWith("elementtype="))
				{
					elementType = str.substring(12).trim();
				}
				else if (str.startsWith("classStartLine="))
				{
					classLine = str.substring(15).trim();
				}
				else if (str.startsWith("elementStartLine="))
				{
					elementLine = str.substring(17).trim();
				}
				else if (str.startsWith("classname="))
				{
					className = str.substring(10).trim();
				}
				else if (str.startsWith("elementvisibility="))
				{
					elementVisibility = str.substring(18).trim();
				}
				else if (str.startsWith("elementfile="))
				{
					elementFile = str.substring(12).trim();
					/*
					 * remove "" around the path by removing
					 * first and last character
					 */
					elementFile = 
						elementFile.substring(1, elementFile.length() - 1);
				}
				else if (str.startsWith("classfile="))
				{
					classFile = str.substring(10).trim();
					/*
					 * remove "" around the path by removing
					 * first and last character
					 */
					classFile = 
						classFile.substring(1, classFile.length() - 1);
				}
				else if (str.startsWith("classrestriction="))
				{
					classRestriction = str.substring(17).trim();
				}
				else if (str.startsWith("names="))
				{
					names = str.substring(6).trim();
				}
			}
			
			if (elementType.equals("classdef"))
			{
				/* 'parse' line number information */ 
				IElementLocation location =
					new ElementLocation(classFile, 
									Integer.parseInt(classLine));					
				
				elements.put(className, 
						new InnerClass(sourceFile, fullName, className,
								location, 
							IModelicaClass.Type.parse(classRestriction)));
			}
			else if (elementType.equals("component"))
			{
				/*
				 * names have following format:
				 * names={component_name,"component_comment"}
				 * we neet to get the component name
				 */ 
				List comp = ModelicaParser.parseList(names);
				
				String componentName = comp.elementAt(0).toString();
				
				
				elements.put(componentName, 
						new ModelicaComponent(
								sourceFile,
								componentName,
								IModelicaComponent.Visibility.parse(elementVisibility),
								new ElementLocation(elementFile, 
										Integer.parseInt(elementLine))));					
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
			CompilerInstantiationException
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
		return sourceFile.getResource();
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

		if (sourceFile != null)
		{
			SyntaxChecker.getLineRegion((IFile)sourceFile.getResource(),
					location.getLine());
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
								CorePlugin.getSymbolicName(),
								IStatus.OK, 
								"could not find modelica source file " + 
									location.getPath(),
								e));
		}
		return reg;
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
		throws ConnectException, CompilerInstantiationException
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

	public IModelicaSourceFile getSourceFile()
	{
		return sourceFile;
	}
}
