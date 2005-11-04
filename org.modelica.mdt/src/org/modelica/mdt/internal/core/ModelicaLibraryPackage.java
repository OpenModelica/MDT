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

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IResource;
import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaPackage;

/**
 * Represents a modelica package from the standart library. Used to
 * display package/class hierarchy in Libraries node.
 * 
 * @author Elmir Jagudin
 * @author Andreas Remar
 */
public class ModelicaLibraryPackage extends ModelicaElement implements
		IModelicaPackage 
{
	String baseName;
	String elementName;
	String fullName;
	
	List<IModelicaPackage> packages = null;
	List<IModelicaClass> classes = null;
	
	ModelicaLibraryPackage(String baseName, String elementName)
	{
		this.baseName = baseName;
		this.elementName = elementName;
		
		if(baseName == null)
		{
			fullName = elementName;
		}
		else
		{
			fullName = baseName + "." + elementName;
		}
	}
	
	/**
	 * @see org.modelica.mdt.core.IModelicaPackage#getPackages()
	 * @return the subpackages, or null if there is no subpackages 
	 * in this package
	 */
	public List<IModelicaPackage> getPackages() throws InitializationException
	{
		if(packages != null)
		{
			/* packages allready loaded */
			return packages;
		}
		packages = new Vector<IModelicaPackage>();

		String[] tokens = OMCProxy.getPackages(fullName); 

		if(tokens == null)
		{
			return packages;
		}
		
		for(String s : tokens)
		{
			if(s.equals("") == false)
			{
				addPackage(new ModelicaLibraryPackage(fullName, s));
			}
		}

		return packages;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaPackage#getClasses()
	 * @return the classes contained in this package, or null if there is no classes in this package
	 */
	public List<IModelicaClass> getClasses() throws InitializationException
	{
		if(classes != null)
		{
			/* allready loaded classes */
			return classes;
		}
		classes = new Vector<IModelicaClass>();
		
		String[] tokens = OMCProxy.getClassNames(fullName);
		
		if(tokens == null)
		{
			return classes;
		}
		
		for(String str : tokens)
		{
			addClass(new ModelicaClass(str, fullName));
		}
		
		return classes;
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaElement#getElementName()
	 */
	public String getElementName() 
	{
		return elementName;
	}

	public void addPackage(IModelicaPackage modelicaPackage)
	{
		packages.add(modelicaPackage);
	}
	
	public void addClass(IModelicaClass modelicaClass)
	{
		classes.add(modelicaClass);
	}

	public String[] parseList(String str)
	{
		/* remove whitespace before and after */
		str = str.trim();
		
		/* check that a list is parsed, otherwise this is probably an error */
		if(str.charAt(0) != '{' || str.charAt(str.length() - 1) != '}')
			return null;

		/* remove {} and split into tokens */
		str = str.substring(1, str.length() - 1);
		String[] retvals = str.split(",");
		
		return retvals;
	}

	public List<IModelicaElement> getChildren() throws InitializationException
	{
		List<IModelicaPackage> pkgs = getPackages();
		List<IModelicaClass> cls  = getClasses();
		
		List<IModelicaElement> children = new LinkedList<IModelicaElement>();
		
		children.addAll(pkgs);
		children.addAll(cls);
		
		return children;
	}

	public boolean hasChildren() throws InitializationException
	{
		return (getPackages() != null) || (getClasses() != null);
	}

	@Override
	public IResource getResource() 
	{
		// TODO implement me
		return super.getResource();
	}
}
