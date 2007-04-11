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
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaExtends;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParameter;
import org.modelica.mdt.core.ISignature;
import org.modelica.mdt.core.IllegalRestrictionException;
import org.modelica.mdt.core.IllegalVisibilityException;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.ModelicaParserException;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementInfo;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * A package that is defined inside a modelica file or in the system library.
 * 
 * @author Adrian Pop
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
	 * - restriction of the class
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
	 * 		- restriction of the class
	 * 		- encapsulated status of the class
	 * 		- class definition location in the source file
	 * This information is callectively called for 'class attributes'.
	 * 
	 * Whenver it is detected that the source file where this modelica class is
	 * defined have changed on the disk this class is notified by having it's
	 * reload() method invoked.  
	 */
		
	/* subpackages and subclasses hashed by the thier's shortname */
	private Hashtable<String, IModelicaElement> children = null;
	
	IDefinitionLocation fLocation = null;
	Restriction fRestriction;
	/* the import statments */
	private Collection<IModelicaImport> imports;
	/* the extends statments */
	private Collection<IModelicaExtends> extendsStmt;	
	
	/* input and output parameters found in the signature */
	private LinkedList<IParameter> inputParams = null;
	private LinkedList<IParameter> outputParams = null;

	class LazyLoadImports extends Thread
	{
		private Vector<IModelicaImport> imports = null;;
		
		public LazyLoadImports(Vector<IModelicaImport> imports) {
			super("LazyLoadingImports for Class:" + getElementName());
			this.imports = imports;
			setPriority(Thread.MIN_PRIORITY);
		}
				
		public void run()
		{
			/* wait a bit before loading imports */
			try{ Thread.sleep(10000);} catch(InterruptedException e) {/* ignore */}			
			try
			{
				for (IModelicaImport i : this.imports)
				{
					/* wait a bit between import loading */
					try{ Thread.sleep(100);} catch(InterruptedException e) {/* ignore */}					
					i.getImportedPackage();
				}

			}
			catch(Exception e)
			{
				ErrorManager.logError(e);
			}
		}
	}	
	
	public InnerClass(IModelicaSourceFile container, IModelicaClass parentNamespace, String name, 
			Restriction restriction, IDefinitionLocation location)
	{
		super(container, restriction, location);
		this.parentNamespace = parentNamespace;
		this.name = name;
		setFullName();
		fLocation = location;
		fRestriction = restriction;
	}
	
	/**
	 * Create a modelica package that is defined in unknow location, for example
	 * a standard library class. This method assumes that a class named
	 * 'prefix'.'name' is loaded into OMC.
	 * 
	 * @param prefix
	 * @param name
	 */
	public InnerClass(IModelicaClass parentNamespace, String name, Restriction restriction, IDefinitionLocation location)
	{
		this(null, parentNamespace, name, restriction, location);
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
			/* load attributes also */
			//getAttributes();
			if (imports.size() > 0)
			{
				LazyLoadImports lazyLoadImports = new LazyLoadImports(new Vector<IModelicaImport>(imports));
				lazyLoadImports.start();				
			}
		}
		
		return children.values();
	}
	
	private Hashtable<String, IModelicaElement> loadElements() 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		Hashtable<String, IModelicaElement> elements = 
			new Hashtable<String, IModelicaElement>();
		
		imports = Collections.synchronizedList(new LinkedList<IModelicaImport>());
		
		inputParams = new LinkedList<IParameter>();
		outputParams = new LinkedList<IParameter>();
		
		extendsStmt = new LinkedList<IModelicaExtends>();
		
		int tickImport = 0;
		int tickExtends = 0;
		
		Restriction restriction = getRestriction();
		if(restriction == IModelicaClass.Restriction.RECORD)
		{
			IModelicaElement p = getParentNamespace();
			if (p != null && p instanceof IModelicaClass)
			{
				String typeName = p.getElementName();
				Restriction parentRestriction = ((IModelicaClass)p).getRestriction();
				if (parentRestriction == IModelicaClass.Restriction.UNIONTYPE)
					outputParams.add(new Parameter("parent", typeName));
			}
		}
		
		IModelicaElement.Visibility vis;
		for (ElementInfo info : CompilerProxy.getElements(fullName))
		{
			String elementType = info.getElementType();
			try
			{
				 vis = IModelicaElement.Visibility.parse(info.getElementVisibility(), true);
			}
			catch(IllegalVisibilityException e)
			{
				throw new UnexpectedReplyException("Unexpected visibility: " + e.getMessage());
			}
			
			
			/* a sub package */
			if (elementType.equals("classdef"))
			{
				String className = info.getClassName();
				String elementFile = info.getElementFile();
				Restriction restr = null;
				try { restr = Restriction.parse(info.getClassRestriction()); }
				catch(IllegalRestrictionException e) { restr = null; }
				IDefinitionLocation location = 
					new DefinitionLocation(elementFile, 
							info.getElementStartLine(), info.getElementStartColumn(),
							info.getElementEndLine(), info.getElementEndColumn());				
				elements.put(className, new InnerClass(getSourceFile(), this, info.getClassName(), restr, location));
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
					throw new UnexpectedReplyException("Unable to parse " + "returned list: " + e.getMessage());
				}
				
				String componentName = comp.elementAt(0).toString();
				String elementFile = info.getElementFile();
				IDefinitionLocation location = 
					new DefinitionLocation(elementFile, 
							info.getElementStartLine(), info.getElementStartColumn(),
							info.getElementEndLine(), info.getElementEndColumn());

				elements.put(componentName, new ModelicaComponent(this, componentName, info.getTypeName(), vis, location));					
				
				
				if (restriction == IModelicaClass.Restriction.FUNCTION || 
					restriction == IModelicaClass.Restriction.BLOCK)
				{
					String typeName = info.getTypeName();
					if(info.getElementVisibility().equals("public") && info.getDirection().equals("input"))
					{
						inputParams.add(new Parameter(componentName, typeName));
					}
					else if(info.getElementVisibility().equals("public") && info.getDirection().equals("output"))
					{
						outputParams.add(new Parameter(componentName, typeName));
					}
				}
				else if(restriction == IModelicaClass.Restriction.RECORD)
				{
					String typeName = info.getTypeName();
					if(info.getElementVisibility().equals("public"))
					{
						inputParams.add(new Parameter(componentName, typeName));
					}
				}
			}			
			/* an import statment */
			else if (elementType.equals("import"))
			{
				String importType = info.getKind();
				IModelicaImport imp = null;
				String elementFile = info.getElementFile();
				IDefinitionLocation location = 
					new DefinitionLocation(elementFile, 
							info.getElementStartLine(), info.getElementStartColumn(),
							info.getElementEndLine(), info.getElementEndColumn());
				String path = info.getPath();
				if (importType.equals("qualified"))
				{
					imp = new ModelicaImport(this, getProject(), true, path, vis, location);
				}
				else if (importType.equals("unqualified"))
				{
					imp = new ModelicaImport(this, getProject(), false, path, vis, location);
				}
				else if (importType.equals("named"))
				{
					imp = new ModelicaImport(this, getProject(), info.getId(), path, vis, location);
				}
				else
				{
					ErrorManager.logBug(CorePlugin.getSymbolicName(), "import statment of unexpected type '" + importType + "' encountered");
				}
				if (imp != null)
				{
					imports.add(imp);
					elements.put(imp.getElementName(), imp);
					tickImport++;
				}
			}
			/* an extends statment */
			else if (elementType.equals("extends"))
			{
				IModelicaExtends ext = null;
				String elementFile = info.getElementFile();
				IDefinitionLocation location = 
					new DefinitionLocation(elementFile, 
							info.getElementStartLine(), info.getElementStartColumn(),
							info.getElementEndLine(), info.getElementEndColumn());				
				ext = new ModelicaExtends(this, info.getPath(), vis, location);
				if (ext != null)
				{
					extendsStmt.add(ext);
					elements.put(ext.getElementName(), ext);
					tickExtends++;
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
		throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException
	{
		/*
		 * the reload strategy is as follows:
		 */
		
		 /*
		  * all class attribute fields are just reset and 
		  * lazily reloaded as they are queried
		  */
		super.reload();

		 /* 
		  * new class component are fetched and compared to
		  * the old in order to generate a change events list.
		  * 
		  * components that are not new or were removed are notified
		  * of the change to give them a chance to update thier's state
		  */
		LinkedList<IModelicaElementChange> changes = new LinkedList<IModelicaElementChange>();
		
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
			ModelicaElement oldElement = (ModelicaElement)oldChildren.remove(element.getElementName());
		
			if (oldElement == null)
			{
				/* new element added */
				children.put(element.getElementName(), element);
				changes.add(new ModelicaElementChange(this, element, null));
			}
			else
			{
				/* element present before, refresh ! */
				// adrpo 2006-10-16 
				// - the IModelicaComponent doesn't have reload
				//   so whe have to set it here!
				// - otherwise the image doesn't get updated
				if (oldElement instanceof IModelicaComponent && element instanceof IModelicaComponent)
				{
					((ModelicaComponent)oldElement).setModelicaComponent((ModelicaComponent)element);
				}
				changes.addAll(oldElement.reload());
			}
		}
		
		/* now there is only removed elements in the oldChildren table */
		for (IModelicaElement element : oldChildren.values())
		{
			children.remove(element.getElementName());
			changes.add(new ModelicaElementChange(element, ChangeType.REMOVED, null));
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
		/* try some magic :) */
		IPath p = null;
		try { p = new Path(getFilePath()); }
		catch(Exception e) { ErrorManager.logError(e); }
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (p == null) /* no joy */ { return null; }
		IFile f = workspace.getRoot().getFileForLocation(p);
		return f;
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

	public Collection<IModelicaExtends> getExtends() 
	throws ConnectException, UnexpectedReplyException, InvocationError, 
		CompilerInstantiationException, CoreException
    {
		if (children == null)
		{
			children = loadElements();
		}

		return extendsStmt;
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
			return ModelicaCore.getModelicaRoot().getStandardLibrary(getProject());
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
		
	
	public IDefinitionLocation getLocation()
	throws ConnectException, UnexpectedReplyException, 
		InvocationError, CoreException, CompilerInstantiationException
	{
		if (fLocation == null)
			return super.getLocation();
		return fLocation;
	}
	
}
