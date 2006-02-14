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

import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaImport;

/**
 * An implementation of IModelicaImport interface.
 * 
 * This is just a basicaly struct for storning import info.
 */
public class ModelicaImport implements IModelicaImport
{
	private IModelicaClass importedPackage;
	private Type type;
	private String alias;
	
	private ModelicaImport() { }
	
	protected static ModelicaImport createRenamingImport(String alias,
			String importedElement)
	{
		ModelicaImport mi = new ModelicaImport();
		mi.type = Type.RENAMING;
		mi.alias = alias;

		return mi;
	}

	protected static ModelicaImport createUnqualifiedImport(
			String importedElement)
	{
		ModelicaImport mi = new ModelicaImport();
		mi.type = Type.UNQUALIFIED;

		return mi;
	}	
	protected static ModelicaImport createQualifiedImport(
			String importedElement)
	{
		ModelicaImport mi = new ModelicaImport();
		mi.type = Type.QUALIFIED;

		return mi;
//		
//		mi.importedPackage = containerProject.getPackage(importedElement);
//	
//		if (mi.importedPackage != null)
//		{
//			/* we are importing a package, this is a qualified import */
//			mi.type = Type.QUALIFIED;
//			return mi;
//		}
//		
//		/*
//		 *  this looks like a single definition import
//		 */
//		
//		String prefix = importedElement.substring(0,
//				importedElement.lastIndexOf('.'));
//		System.out.println(prefix);
//		
//		IModelicaClass parentPkg = containerProject.getPackage(prefix);
//
//		
//		return mi;
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
	 * @see org.modelica.mdt.core.IModelicaImport#getImportedElement()
	 */
	public IModelicaElement getImportedElement()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaImport#getAlias()
	 */
	public String getAlias()
	{
		return alias;
	}
}
