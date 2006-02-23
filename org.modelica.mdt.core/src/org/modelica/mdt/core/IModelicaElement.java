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

package org.modelica.mdt.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * Common protocol for all elements provided by the Modelica root.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * @author Elmir Jagudin
 *
 */
public interface IModelicaElement extends IAdaptable 
{
	public String getElementName();

	/**
	 * return the element's full name, that is if element's full name is
	 * foo.bar.hej then the prefix is foo.bar and short name is hej
	 * 
	 * @return the full name of this package
	 */
	public String getFullName();

	
	/**
	 * Returns the innermost resource enclosing this element. 
	 * If this element is included in an archive and this archive is not external, 
	 * this is the underlying resource corresponding to the archive. 
	 * If this element is included in an external archive, <code>null</code>
	 * is returned.
	 *
	 * If this element is stored outside of the workspace root, e.g. the
	 * system library elements, then null is returned from this method.
	 * 
	 * @return the innermost resource enclosing this element, <code>null</code> if this 
	 * element is included in an external archive
	 * @throws ConnectException 
	 * @throws UnexpectedReplyException 
	 * @since 2.0
	 */
	IResource getResource();
	
	/**
	 * For elements that are defined inside a file this method returns
	 * the region of the file where the file is defined.
	 * 
	 * If the element is not defined in a file, null is returned.
	 * 
	 * Remark: due to limitations in the OMC, the actual region returned is
	 * the begining and end of first line of the elements definition.
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws CoreException 
	 * @throws CompilerInstantiationException 
	 * 
	 */
	public IRegion getLocation() 
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CoreException, CompilerInstantiationException;

	/**
	 * If this element is external then this method returns full path
	 * to the file where this element is defined. If this element is not
	 * external (e.g. defined inside the workspace) or is not defined in a
	 * file null is returned.
	 *  
	 * @return
	 * @throws InvocationError 
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws CompilerInstantiationException 
	 */
	public String getFilePath() 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException;
	
	/**
	 * For modelica elements that are defined in a source code
	 * file inside the workspace this methods fetches the source file
	 * reference. For other modelica elements (for example folder packages,
	 * system library elements) this methods returns null.
	 * 
	 * @return the source file where this element is defined or null if
	 * no source file can be determined
	 */
	public IModelicaSourceFile getSourceFile();
	
	
	public IModelicaElement getParent();
	
	/**
	 * @return the project where this element is defined,
	 * elements from standard library returns null here
	 */
	public IModelicaProject getProject();
}
