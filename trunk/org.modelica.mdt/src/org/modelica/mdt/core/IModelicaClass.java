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

import org.modelica.mdt.internal.omcproxy.ConnectionException;

/**
 * @author Elmir Jagudin
 */
public interface IModelicaClass extends IModelicaElement, IParent
{
	public enum Type { CLASS, MODEL, FUNCTION, RECORD, 
		CONNECTOR, BLOCK, TYPE, PACKAGE };

	/**
	 * return the class' prefix, that is if class' full name is
	 * foo.bar.hej then the prefix is foo.bar and short name is hej
	 * 
	 * @return the prefix of this package
	 */
	public String getPrefix();

	/**
	 * return the class' full name, that is if class' full name is
	 * foo.bar.hej then the prefix is foo.bar and short name is hej
	 * 
	 * @return the full name of this package
	 */
	public String getFullName();
	
	/**
	 * @return all imports this class is making
	 */
	public IClassImport[] getImports();
	
	/**
	 * @return list of extensions this class is making
	 */
	public IClassExtend[] getExtends();
	
	/**
	 * @return list of components (variables and constans) of this class
	 */
	public IClassComponent[] getComponents();

	/**
	 * @return the restriction type of this class
	 * @throws ConnectionException 
	 */
	public Type getRestrictionType() throws ConnectionException;

	
}
