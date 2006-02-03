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

import org.modelica.mdt.core.IModelicaElementChange;

/**
 * Implementation of IModelicaElementChange interface
 * 
 * @author Elmir Jagudin
 */
public class ModelicaElementChange implements IModelicaElementChange
{
	private Object element;
	private Object parent;
	private ChangeType type;
	

	/**
	 * Create a element change of type ADDED.
	 * 
	 * @param parent the parent where element was added
	 * @param element the element that was added
	 */
	protected ModelicaElementChange(Object parent, Object element)
	{
		this.parent = parent;
		this.element = element;
		type = ChangeType.ADDED;
	}
	
	protected ModelicaElementChange(Object element, ChangeType type)
	{
		this.element = element;
		this.type = type;
		if (type == ChangeType.MODIFIED && element == null)
		{
			System.out.println("null element instopppad!!!!!!!!!!!!");
		}
	}
	
	/**
	 * @see org.modelica.mdt.core.IModelicaElementChange#getElement()
	 */
	public Object getElement()
	{
		return element;
	}

	/**
	 * @see org.modelica.mdt.core.IModelicaElementChange#getChangeType()
	 */
	public ChangeType getChangeType()
	{
		return type;
	}

	public Object getParent()
	{
		return parent;
	}

}
