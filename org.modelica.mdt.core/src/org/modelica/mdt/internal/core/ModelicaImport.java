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

import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * An implementation of IModelicaImport interface.
 * 
 * This is just a basicaly struct for storning import info.
 */
public class ModelicaImport implements IModelicaImport
{
	//TODO imported package loading should be done lazily
	private IModelicaClass importedPackage;
	private Type type;
	private String alias;
	
	/**
	 * Create an import of the qualified or unqualified type
	 * @param containerProject the project where the import statment is defined
	 * @param isQualified wheter if this is a qualified import
	 * @param importedElement the full name if the imported package/class
	 */
	public ModelicaImport(IModelicaProject containerProject, 
			boolean isQualified,
			String importedElement) 
		throws ConnectException, CompilerInstantiationException,
		UnexpectedReplyException, InvocationError, CoreException
	{ 
		type = isQualified ? Type.QUALIFIED : Type.UNQUALIFIED;

		if(containerProject != null)
		{
			importedPackage = containerProject.getClass(importedElement);
		}
		else
		{
			importedPackage = 
				ModelicaCore.getModelicaRoot().getStandardLibrary().
					getPackage(importedElement);			
		}
		
		if (importedPackage == null)
		{
			/* the package specified in this import statment does not exists */
			//TODO throw an exception or something
			System.out.println("omg, omg, omg ! " + importedElement);
		}		
	}
	
	/**
	 * Create an import if renaming type.
	 * 
	 * @param containerProject the project where the import statment is defined
	 * @param alias the new name of the imported package
	 * @param importedElement the full name if the imported package/class
	 */
	public ModelicaImport(IModelicaProject containerProject,
			String alias,
			String importedElement)
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException, InvocationError, CoreException
	{ 
		type = Type.RENAMING;
		this.alias = alias;

		if (containerProject != null)
		{
			importedPackage = containerProject.getClass(importedElement);
		}
		else
		{
			importedPackage = 
				ModelicaCore.getModelicaRoot().getStandardLibrary().
					getPackage(importedElement);
		}
		
		if (importedPackage == null)
		{
			/* the package specified in this import statment does not exists */
			//TODO throw an exception or something
			System.out.println("omg, (renaming) " + importedElement);
		}
		
	} 
	
	/**
	 * @see org.modelica.mdt.core.IModelicaImport#getType()
	 */
	public Type getType()
	{
		return type;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaImport#getImportedPackage()
	 */
	public IModelicaClass getImportedPackage()
	{
		return importedPackage;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaImport#getAlias()
	 */
	public String getAlias()
	{
		return alias;
	}
}
