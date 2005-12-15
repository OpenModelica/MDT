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

import java.io.FileNotFoundException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.builder.SyntaxChecker;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * @author Homer Simpson
 */
public class ModelicaComponent extends ModelicaElement 
	implements IModelicaComponent
{
	private String name;
	private Visibility visibility;
	private ElementLocation location;
	private IFile container;
	
	/**
	 * Create class component
	 * 
	 * @param container The filename where this component is defined
	 * @param name name if this component
	 * @param visibility whatever this component is public or protected
	 * @param location location in the source code file
	 */
	public ModelicaComponent(IFile container, String name, Visibility visibility,
						ElementLocation location)
	{
		this.name = name;
		this.visibility = visibility;
		this.location = location;
		this.container = container;
	}

	public String getElementName()
	{
		return name;
	}

	public Visibility getVisbility()
	{
		return visibility;
	}
	
	public IResource getResource()
	{
		return container;
	}
	
	/**
	 * @throws InvocationError 
	 * @throws CoreException 
	 * @see org.modelica.mdt.core.IModelicaElement#getLocation()
	 */
	public IRegion getLocation()
		throws CoreException
//		throws ConnectionException, UnexpectedReplyException, 
//			InvocationError, CoreException
	{
		if (container != null)
		{
			SyntaxChecker.getLineRegion(container, location.getLine());
		}

		IRegion reg = null;
		
		try
		{
			reg = 
				SyntaxChecker.getLineRegion(location.getPath(), 
						location.getLine());
		}
		catch (FileNotFoundException e)
		{
			throw new CoreException(
					new Status(IStatus.ERROR,
								MdtPlugin.getSymbolicName(),
								IStatus.OK, 
								"could not find modelica source file " + 
									location.getPath(),
								e));
		}
		return reg;
	}

	@Override
	public String getFilePath() 
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		return location.getPath();
	}

}
