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

package org.modelica.mdt.core.compiler;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.IModelicaClass.Restriction;

/**
 * The interface to a modelica compiler.
 * 
 *  This interface must be implemented by the extender of the
 *  org.modelica.mdt.compiler point. This defines the methods used to
 *  access the modelica compiler by the MDT core plugin.
 */
public interface IModelicaCompiler
{
	/**
	 * @return symbolic name of the compiler suitable for end user consumtion
	 */
	public String getCompilerName();

	/**
	 * Load the modelica source file into the internal database. 
	 * 
	 * The file must be parsed before it is stored into the database. 
	 * The file may contain syntax and other errors that are discoverd while
	 * it is parsed. The compiler must make the best effort to parse as much
	 * as possible even if errors are discovered. Compiler should return both
	 * parse errors and the contens of the file.
	 * 
	 * @param file the modelica source code file to load
	 * @return the modelica elemenats and parsing errors found in the file
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 */
	public IParseResults loadSourceFile(IFile file)
			throws ConnectException, UnexpectedReplyException;

	/**
	 * Fetches the list of name of subclasses in a given class
	 * 
	 * @param className the name of the class where to look for subclasses
	 * @return the list of the names of subclasses
	 */
	public List getClassNames(String className)
			throws ConnectException, UnexpectedReplyException;

	/**
	 * Fetches the information on the elements of a class. Elements in
	 * a class are subclasses, components, import statments etc.
	 */
	public Collection<ElementInfo> getElements(String className)
			throws ConnectException, InvocationError, UnexpectedReplyException;

	/**
	 * Fetches the location of the definition of the class in the 
	 * source code file. 
	 * 
	 * @param className the name of the class to fetch the location of
	 * @return the line number and the file path where the class is defined
	 */
	public IDefinitionLocation getClassLocation(String className)
			throws ConnectException, UnexpectedReplyException, InvocationError;

	/**
	 * Fetches the restriction type of the class.
	 * 
	 * @param className the name of the class to fetch restriction type of
	 * @return the restriction type of the class
	 * @throws UnexpectedReplyException 
	 */
	public Restriction getRestriction(String className)
			throws ConnectException, UnexpectedReplyException;
	/**
	 * The method returns a list of top level packages in the standard library.
	 * 
	 * @return the list of names of top-level packages in the standard library
	 * @throws ConnectException
	 */	
	public String[] getStandardLibrary()
			throws ConnectException;

	/**
	 * Fetches information about a class, such as encapusulated status,
	 * class definition location and so on.
	 * 
	 * @param className the full name of the class to fetch info on
	 */
	public IClassInfo getClassInfo(String className) 
		throws ConnectException, UnexpectedReplyException;
}
