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
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.core.IModelicaExtends;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParameter;
import org.modelica.mdt.core.ISignature;
import org.modelica.mdt.core.IllegalRestrictionException;
import org.modelica.mdt.core.IllegalVisibilityException;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.ModelicaParserException;
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
public class InnerClass extends ModelicaClass {
	/*
	 * Contents of a class are loaded lazily when they are first
	 * queried and 'cached' by storing them as member fields.
	 *
	 * currently following contents of a modelica class are available for queries:
	 *
	 * - restriction of the class
	 * - encapsulated status of the class
	 * - class definition location in the source file
	 * - subclasses/subpackages
	 * - class components
	 * - special input/output components
	 * - import statements
	 *
	 *  The above information is fetched via the following methods in CompilerProxy:
	 *
	 *  getElements() provides:
	 * 		- subclasses/subpackages
	 * 		- class components
	 * 		- special input/output components
	 * 		- import statements
	 *
	 * This information is collectively called for 'class components'.
	 *
	 *  getClassInfo() provides:
	 * 		- restriction of the class
	 * 		- encapsulated status of the class
	 * 		- class definition location in the source file
	 * This information is collectively called for 'class attributes'.
	 *
	 * Whenever it is detected that the source file where this modelica class is
	 * defined have changed on the disk this class is notified by having its
	 * reload() method invoked.
	 */

	/* subpackages and subclasses hashed by the their short name */
	private Hashtable<String, IModelicaElement> children = null;

	IDefinitionLocation fLocation = null;
	Restriction fRestriction;
	/* the import statements */
	private Collection<IModelicaImport> imports;
	/* the extends statements */
	private Collection<IModelicaExtends> extendsStmt;

	/* input and output parameters found in the signature */
	private LinkedList<IParameter> inputParams = null;
	private LinkedList<IParameter> outputParams = null;

	class LazyLoadImports extends Thread {
		private Vector<IModelicaImport> imports = null;;

		public LazyLoadImports(Vector<IModelicaImport> imports) {
			super("LazyLoadingImports for Class:" + getElementName());
			this.imports = imports;
			setPriority(Thread.MIN_PRIORITY);
		}

		@Override
		public void run() {
			// wait a bit before loading imports
			try {
				Thread.sleep(10000);
			}
			catch(InterruptedException e) {
				// ignore
			}

			try {
				for (IModelicaImport i : this.imports) {
					// wait a bit between import loading
					try {
						Thread.sleep(100);
					}
					catch(InterruptedException e) {
						// ignore
					}

					i.getImportedPackage();
				}

			}
			catch(Exception e) {
				ErrorManager.logError(e);
			}
		}
	}

	public InnerClass(IModelicaSourceFile container, IModelicaClass parentNamespace, String name,
			Restriction restriction, IDefinitionLocation location) {
		super(container, restriction, location);
		this.parentNamespace = parentNamespace;
		this.name = name;
		setFullName();
		fLocation = location;
		fRestriction = restriction;
	}

	/**
	 * Create a modelica package that is defined in unknown location, for example
	 * a standard library class. This method assumes that a class named
	 * 'prefix'.'name' is loaded into OMC.
	 *
	 * @param prefix
	 * @param name
	 */
	public InnerClass(IModelicaClass parentNamespace, String name, Restriction restriction, IDefinitionLocation location) {
		this(null, parentNamespace, name, restriction, location);
	}

	/**
	 * @see org.modelica.mdt.core.IParent#getChildren()
	 */
	@Override
	public Collection<IModelicaElement> getChildren()
			throws ConnectException, UnexpectedReplyException, InvocationError, CoreException, CompilerInstantiationException {
		if (children == null) {
			children = loadElements();
			/* load attributes also */
			//getAttributes();
			if (imports.size() > 0) {
				LazyLoadImports lazyLoadImports = new LazyLoadImports(new Vector<IModelicaImport>(imports));
				lazyLoadImports.start();
			}
		}

		return children.values();
	}

	private Hashtable<String, IModelicaElement> loadElements()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		Hashtable<String, IModelicaElement> elements = new Hashtable<String, IModelicaElement>();

		imports = Collections.synchronizedList(new LinkedList<IModelicaImport>());

		inputParams = new LinkedList<IParameter>();
		outputParams = new LinkedList<IParameter>();

		extendsStmt = new LinkedList<IModelicaExtends>();

		Restriction restriction = getRestriction();

		if(restriction == IModelicaClass.Restriction.RECORD) {
			IModelicaElement p = getParentNamespace();

			if (p != null && p instanceof IModelicaClass) {
				IModelicaClass modelicaClass = (IModelicaClass)p;
				String typeName = modelicaClass.getElementName();
				Restriction parentRestriction = modelicaClass.getRestriction();

				if (parentRestriction == IModelicaClass.Restriction.UNIONTYPE) {
					Parameter parameter = new Parameter("parent", typeName);

					outputParams.add(parameter);
				}
			}
		}

		Collection<ElementInfo> fullNameElements = CompilerProxy.getElements(fullName);
		for (final ElementInfo info : fullNameElements) {
			String elementType = info.getElementType();
			IModelicaElement.Visibility vis;

			try {
				vis = IModelicaElement.Visibility.parse(info.getElementVisibility(), true);
			}
			catch(IllegalVisibilityException e) {
				throw new UnexpectedReplyException("Unexpected visibility: " + e.getMessage());
			}

			if (elementType.equals("classdef")) { // a sub package
				String elementFile = info.getElementFile();
				int startLine = info.getElementStartLine();
				int startCol = info.getElementStartColumn();
				int endLine = info.getElementEndLine();
				int endCol = info.getElementEndColumn();
				IDefinitionLocation location = new DefinitionLocation(elementFile, startLine, startCol, endLine, endCol);
				String className = info.getClassName();
				Restriction restr = null;

				try {
					restr = Restriction.parse(info.getClassRestriction());
				}
				catch(IllegalRestrictionException e) {
					restr = null;
				}

				IModelicaSourceFile modelicaSourceFile = getSourceFile();
				InnerClass innerClass = new InnerClass(modelicaSourceFile, this, className, restr, location);

				elements.put(className, innerClass);
			}
			else if (elementType.equals("component")) { // a component
				/*
				 * names have following format:
				 * names={component_name,"component_comment"}
				 * we need to get the component name
				 */

				List comp = null;

				try {
					comp = ModelicaParser.parseList(info.getNames());
				}
				catch(ModelicaParserException e) {
					throw new UnexpectedReplyException("Unable to parse returned list: " + e.getMessage());
				}

				String elementFile = info.getElementFile();
				int startLine = info.getElementStartLine();
				int startCol = info.getElementStartColumn();
				int endLine = info.getElementEndLine();
				int endCol = info.getElementEndColumn();
				IDefinitionLocation location = new DefinitionLocation(elementFile, startLine, startCol, endLine, endCol);

				String componentName = comp.elementAt(0).toString();
				String typeName = info.getTypeName();
				ModelicaComponent modelicaComponent = new ModelicaComponent(this, componentName, typeName, vis, location);

				elements.put(componentName, modelicaComponent);

				if (restriction == IModelicaClass.Restriction.FUNCTION || restriction == IModelicaClass.Restriction.BLOCK) {
					if (info.getElementVisibility().equals("public") && info.getDirection().equals("input")) {
						Parameter parameter = new Parameter(componentName, typeName);

						inputParams.add(parameter);
					}
					else if (info.getElementVisibility().equals("public") && info.getDirection().equals("output")) {
						Parameter parameter = new Parameter(componentName, typeName);

						outputParams.add(parameter);
					}
				}
				else if (restriction == IModelicaClass.Restriction.RECORD) {
					if (info.getElementVisibility().equals("public")) {
						Parameter parameter = new Parameter(componentName, typeName);

						inputParams.add(parameter);
					}
				}
			}
			else if (elementType.equals("import")) { // an import statement
				String importType = info.getKind();
				IModelicaImport imp = null;
				String path = info.getPath();

				String elementFile = info.getElementFile();
				int startLine = info.getElementStartLine();
				int startCol = info.getElementStartColumn();
				int endLine = info.getElementEndLine();
				int endCol = info.getElementEndColumn();
				IDefinitionLocation location = new DefinitionLocation(elementFile, startLine, startCol, endLine, endCol);
				IModelicaProject project = getProject();

				if (importType.equals("qualified")) {
					imp = new ModelicaImport(this, project, true, path, vis, location);
				}
				else if (importType.equals("unqualified")) {
					imp = new ModelicaImport(this, project, false, path, vis, location);
				}
				else if (importType.equals("named")) {
					String id = info.getId();

					imp = new ModelicaImport(this, project, id, path, vis, location);
				}
				else {
					ErrorManager.logBug(CorePlugin.getSymbolicName(), "import statment of unexpected type '" + importType + "' encountered");
				}

				if (imp != null) {
					imports.add(imp);
					elements.put(imp.getElementName(), imp);
				}
			}
			// an extends statement
			else if (elementType.equals("extends")) {
				String elementFile = info.getElementFile();
				int startLine = info.getElementStartLine();
				int startCol = info.getElementStartColumn();
				int endLine = info.getElementEndLine();
				int endCol = info.getElementEndColumn();
				IDefinitionLocation location = new DefinitionLocation(elementFile, startLine, startCol, endLine, endCol);
				String path = info.getPath();
				IModelicaExtends ext = new ModelicaExtends(this, path, vis, location);

				extendsStmt.add(ext);
				elements.put(ext.getElementName(), ext);
			}
		}

		return elements;
	}

	/**
	 * @see org.modelica.mdt.core.IParent#hasChildren()
	 */
	@Override
	public boolean hasChildren()
			throws ConnectException, UnexpectedReplyException, InvocationError, CoreException, CompilerInstantiationException {
		boolean hasChildren = getChildren().size() > 0;

		return hasChildren;
	}

	@Override
	public Collection<IModelicaElementChange> reload()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		// The reload strategy is as follows:
		// * all class attribute fields are just reset and
		//   lazily reloaded as they are queried
		// * new class component are fetched and compared to
		//   the old in order to generate a change events list.
		// * components that are not new or were removed are notified
		//   of the change to give them a chance to update their state
		super.reload();

		LinkedList<IModelicaElementChange> changes = new LinkedList<IModelicaElementChange>();

		// if children are not loaded, then we can't reload
		if (children != null) {
			Hashtable<String, IModelicaElement> newChildrenMap = loadElements();

			@SuppressWarnings("unchecked")
			Hashtable<String, IModelicaElement> oldChildren = (Hashtable<String, IModelicaElement>)children.clone();
			Collection<IModelicaElement> newChildren = newChildrenMap.values();

			for (IModelicaElement element : newChildren) {
				String elementName = element.getElementName();
				ModelicaElement oldElement = (ModelicaElement)oldChildren.remove(elementName);

				if (oldElement == null) {
					// new element added
					ModelicaElementChange modelicaElementChange = new ModelicaElementChange(this, element, null);
					changes.add(modelicaElementChange);
				}
				else {
					/* element present before, refresh ! */
					// adrpo 2006-10-16
					// - the IModelicaComponent doesn't have reload
					//   so we have to set it here!
					// - otherwise the image doesn't get updated
					if (oldElement instanceof IModelicaComponent && element instanceof IModelicaComponent) {
						((ModelicaComponent)oldElement).setModelicaComponent((ModelicaComponent)element);
					}

					changes.addAll(oldElement.reload());
				}
			}

			// now there is only removed elements in the oldChildren table
			for (IModelicaElement element : oldChildren.values()) {
				changes.add(new ModelicaElementChange(element, ChangeType.REMOVED, null));
			}

			if (children.size() != newChildren.size()) {
				ErrorManager.logBug("org.modelica.mdt.core", "Problem in InnerClass.reload(), children and newChildren don't have the same size.");
			}

			children = newChildrenMap;
		}

		return changes;
	}

	@Override
	public IResource getResource() {
		IModelicaSourceFile sourceFile = getSourceFile();

		if (sourceFile != null) {
			return sourceFile.getResource();
		}

		// try some magic :)
		IPath p = null;
		try {
			p = new Path(getFilePath());
		}
		catch (Exception e) {
			ErrorManager.logError(e);
		}

		IFile f = null;

		if (p != null) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			f = workspace.getRoot().getFileForLocation(p);
		}

		return f;
	}

	@Override
	public Collection<IModelicaImport> getImports()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		if (children == null) {
			children = loadElements();
		}

		return imports;
	}

	@Override
	public Collection<IModelicaExtends> getExtends()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		if (children == null) {
			children = loadElements();
		}

		return extendsStmt;
	}

	@Override
	public IModelicaElement getParent() {
		IModelicaElement p = super.getParent();

		if (p == null) {
			// the only way we can have a null parent is if we are a top-level class in the standard library
			p = ModelicaCore.getModelicaRoot().getStandardLibrary(getProject());
		}

		return p;
	}

	@Override
	public ISignature getSignature()
			throws ConnectException, InvocationError, UnexpectedReplyException, CompilerInstantiationException, CoreException {
		if(inputParams == null || outputParams == null) {
			loadElements();
		}

		IParameter[] inputParamsArray = inputParams.toArray(new IParameter[0]);
		IParameter[] outputParamsArray = outputParams.toArray(new IParameter[0]);
		Signature signature = new Signature(inputParamsArray, outputParamsArray);

		return signature;
	}

	@Override
	public IDefinitionLocation getLocation()
			throws ConnectException, UnexpectedReplyException, InvocationError, CoreException, CompilerInstantiationException {
		IDefinitionLocation location = fLocation;

		if (fLocation == null) {
			location = super.getLocation();
		}

		return location;
	}
}
