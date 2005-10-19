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

import java.util.Vector;

import org.modelica.mdt.corba.ModeqCommunicationImplementation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaPackage;

/**
 * @author Elmir Jagudin
 *
 */
public class ModelicaPackage extends ModelicaElement implements
		IModelicaPackage 
{
	String baseName;
	String elementName;
	
	Vector<IModelicaPackage> packages;
	Vector<IModelicaClass> classes;

	boolean hasReceivedPackages = false;
	
	ModelicaPackage(String baseName, String elementName)
	{
		this.baseName = baseName;
		this.elementName = elementName;
		
		packages = new Vector<IModelicaPackage>();
		classes = new Vector<IModelicaClass>();
	}
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaPackage#getPackages()
	 */
	public IModelicaPackage[] getPackages() 
	{
		if(hasReceivedPackages == true)
		{
			return (IModelicaPackage[]) packages.toArray();
		}
		
		String retval = null;
		if(baseName == null)
		{
			//System.out.println("getPackages("+elementName+")");
			retval = ModeqCommunicationImplementation.sendExpression("getPackages("+elementName+")");
		}
		else
		{
			//System.out.println("getPackages("+baseName+"."+elementName+")");
			retval = ModeqCommunicationImplementation.sendExpression("getPackages("+baseName+"."+elementName+")");			
		}
		
		/* check that a list is returned, otherwise this is probably an error */
		if(retval.charAt(0) != '{' || retval.charAt(retval.length()) != '}')
			return null;

		retval = retval.substring(1, retval.length() - 2);
		String[] retvals = retval.split(",");
		
		for(String s : retvals)
		{
			if(s.equals("") == false)
			{
				if(baseName == null && s != null)
					addPackage(new ModelicaPackage(elementName, s));
				else if(s != null)
					addPackage(new ModelicaPackage(baseName+"."+elementName, s));
			}
		}

		hasReceivedPackages = true;
		
		if(packages.size() != 0)
		{
			IModelicaPackage impPackages[] = new IModelicaPackage[packages.size()];
			return packages.toArray(impPackages);
		}
		else
		{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.core.IModelicaPackage#getClasses()
	 */
	public IModelicaClass[] getClasses() 
	{
		// TODO Call getClassNames, then compare list with list of packages, and remove matches
		return null;
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
}
