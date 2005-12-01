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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.ParseResults;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * Superclass of all modelica package representation, collects 
 * generic package handling code.
 * 
 * @author Elmir Jagudin
 */
abstract public class ModelicaPackage 
	extends ModelicaElement implements IModelicaPackage
{
	/**
	 * the full name of the parent package
	 */
	protected String prefix;
	
	/**
	 * the short name of this package
	 */
	protected String name;
	
	/**
	 * the fully qualified name of this package e.g. foo.bar.hej
	 */
	protected String fullName;

	/**
	 * calculate the base name of this package
	 */
	protected void setFullName()
	{
		if(prefix.equals(""))
		{
			/*
			 * special case for packages that are direct children of
			 * the root package
			 */
			fullName = name;
		}
		else /* general case */
		{
			fullName = prefix + "." + name;
		}
	}
	
	public String getPrefix()
	{
		return prefix;
	}

	public String getElementName() 
	{
		return name;
	}

	public String getFullName() 
	{
		return fullName;
	}
	
	/**
	 * Checks if the resource can represent a modelica package.
	 * @param res
	 * @return true if the res is a package, false otherwise.
	 * @throws UnexpectedReplyException 
	 * @throws ConnectionException 
	 */
	public static boolean isPackage(IResource res)
		throws ConnectionException, UnexpectedReplyException
	{
		if (res.getType() == IResource.FOLDER)
		{
			/*
			 * If a folder contains a package.mo file, and that file defines a
			 * top package with the same name as folder name,
			 * we can consider this folder a Modelica package.
			 */
			IFolder fol = (IFolder) res;
			String folderName = fol.getName();
			IFile file = fol.getFile("package.mo");
			
			if(file.exists())
			{
				/*
				 * check if package.mo defines a package (aka class) 
				 * with the same name as the parent folder
				 */
				ParseResults results = OMCProxy.loadFileInteractive(file);
				
				for (String name : results.getClasses())
				{
					if (name.equals(folderName))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

}
