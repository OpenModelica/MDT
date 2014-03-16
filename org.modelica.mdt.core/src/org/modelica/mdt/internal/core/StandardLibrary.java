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
import java.util.LinkedList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

public class StandardLibrary  extends ModelicaElement implements IStandardLibrary, IParent
{
	LinkedList<IModelicaClass> packages = null;

	protected StandardLibrary(IModelicaElement parent)
	{
		super(parent);
	}

	public Collection<IModelicaClass> getPackages() throws ConnectException, CompilerInstantiationException
	{
		if (packages == null)
		{
			packages = new LinkedList<IModelicaClass>();

			for (String packageName : CompilerProxy.getStandardLibrary())
			{
				packages.add(new InnerClass(null, packageName, IModelicaClass.Restriction.PACKAGE, null));
			}
		}

		return packages;
	}

	public String getElementName()
	{
		String oml = System.getenv("OPENMODELICALIBRARY");
		if (oml == null)
			return "Libraries";
		return "Libraries: " + oml;
	}

	public String getFullName()
	{
		return getElementName();
	}

	public IResource getResource()
	{
		return null;
	}

	public IDefinitionLocation getLocation()
	{
		return null;
	}

	public String getFilePath()
	{
		return null;
	}

	public IModelicaSourceFile getSourceFile()
	{
		return null;
	}

	public IModelicaClass getPackage(String packageName)
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException, InvocationError, CoreException
	{
		return ModelicaRoot.getPackage(getPackages(), packageName);
	}

	public Collection<? extends IModelicaElement> getChildren()
		throws ConnectException, CompilerInstantiationException
	{
		return getPackages();
	}

	public boolean hasChildren()
	{
		return true;
	}
}
