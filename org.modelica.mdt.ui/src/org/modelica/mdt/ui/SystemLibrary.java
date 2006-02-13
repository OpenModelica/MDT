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

package org.modelica.mdt.ui;

import java.util.Collection;

import org.eclipse.core.resources.IResource;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;

/**
 * The class used for displaying the system library node in
 * the a modelica project resources tree. 
 */

public class SystemLibrary extends PlatformObject 
	implements IModelicaElement, IParent
{
	public String getElementName()
	{
		return "System Library";
	}

	public IResource getResource()
	{
		return null;
	}

	public IRegion getLocation() 
	{
		return null;
	}

	public String getFilePath()
	{
		return null;
	}

	public Collection<IModelicaClass> getChildren()
		throws ConnectException, CompilerInstantiationException
	{
		return CompilerProxy.getStandardLibrary();
	}

	public boolean hasChildren()
	{
		return true;
	}

	public IModelicaSourceFile getSourceFile()
	{
		return null;
	}

}
