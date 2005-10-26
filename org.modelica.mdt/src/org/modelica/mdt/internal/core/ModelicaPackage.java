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

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFolder;
import org.modelica.mdt.corba.ModeqCommunicationImplementation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaPackage;

/**
 * @author Elmir Jagudin
 * @author Andreas Remar
 */
public class ModelicaPackage extends ModelicaElement implements
		IModelicaPackage 
{
	String baseName;
	String elementName;
	String fullName;
	
	List<IModelicaPackage> packages;
	List<IModelicaClass> classes;

	boolean hasReceivedPackages = false;
	boolean hasReceivedClasses = false;
	
	ModelicaPackage(String baseName, String elementName)
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
		
		packages = new Vector<IModelicaPackage>();
		classes = new Vector<IModelicaClass>();
	}
	
	ModelicaPackage(IFolder hej)
	{
		// dummy implementation
		this("oh my", hej.getName());		
	}
	
	/**
	 * @see org.modelica.mdt.core.IModelicaPackage#getPackages()
	  * @return the subpackages, or null if there is no subpackages in this package
	 */
	public List<IModelicaPackage> getPackages() 
	{
		if(hasReceivedPackages == true)
		{
			if(packages.size() != 0)
			{
				return packages;
			}
			else
			{
				return null;
			}
		}
		
		String retval = null;
		try 
		{
			retval = ModeqCommunicationImplementation.sendExpression("getPackages("+fullName+")");
		}
		catch(Exception e)
		{
			//TODO add proper error handling
			System.out.println(e.getMessage());
			return null;
		}
		
		String[] tokens = parseList(retval);

		if(tokens == null)
		{
			hasReceivedPackages = true;
			return null;
		}
		
		for(String s : tokens)
		{
			if(s.equals("") == false)
			{
				addPackage(new ModelicaPackage(fullName, s));
			}
		}

		hasReceivedPackages = true;
		
		if(packages.size() != 0)
		{
			return packages;
		}
		else
		{
			return null;
		}
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaPackage#getClasses()
	 * @return the classes contained in this package, or null if there is no classes in this package
	 */
	public List<IModelicaClass> getClasses() 
	{
		if(hasReceivedClasses == true)
		{
			if(classes.size() != 0)
			{
				return classes;
			}
			else
			{
				return null;
			}
		}
		
		String retval = null;
		try
		{
			retval = ModeqCommunicationImplementation.sendExpression("getClassNames("+fullName+")");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

		
	
		String[] tokens = parseList(retval);
		
		if(tokens == null)
		{
			hasReceivedClasses = true;
			return null;
		}
		
		for(String str : tokens)
		{
			if(str.equals(""))
				continue;
			try
			{
				retval = ModeqCommunicationImplementation.sendExpression("isPackage("+fullName+"."+str+")");
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return null;
			}

			if(retval.contains("false"))
			{
				/* fullName.str is NOT a package, add it to the list of classes */
				addClass(new ModelicaClass(str, fullName));
			}
		}

		hasReceivedClasses = true;

		if(classes.size() != 0)
		{
			return classes;
		}
		else
		{
			return null;
		}
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

	public List<IModelicaElement> getChildren()
	{
		List<IModelicaPackage> pkgs = getPackages();
		List<IModelicaClass> cls  = getClasses();
		
		List<IModelicaElement> children = new LinkedList<IModelicaElement>();
		
		children.addAll(pkgs);
		children.addAll(cls);
		
		return children;
	}

	public boolean hasChildren() 
	{
		return (getPackages() != null) || (getClasses() != null);
	}
}
