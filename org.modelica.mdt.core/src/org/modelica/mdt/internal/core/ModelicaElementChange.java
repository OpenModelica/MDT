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

import org.eclipse.core.resources.IResourceDelta;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;

/**
 * Implementation of IModelicaElementChange interface
 * 
 * @author Elmir Jagudin
 */
public class ModelicaElementChange implements IModelicaElementChange
{
	private IModelicaElement element;
	private Object parent;
	private ChangeType type;
	private IResourceDelta delta;
	

	/**
	 * Create a element change of type ADDED.
	 * 
	 * @param parent the parent where element was added
	 * @param element the element that was added
	 */
	protected ModelicaElementChange(Object parent, IModelicaElement element, IResourceDelta delta)
	{
		this.parent = parent;
		this.element = element;
		this.delta = delta;		
		type = ChangeType.ADDED;
	}
	
	/**
	 * Create element change.
	 * 
	 * NB the constructor can not be used to create element change object of 
	 * the type ADDED. 
	 * Use ModelicaElementChange#ModelicaElementChange(IModelicaElement, ChangeType)
	 * instead.
	 * 
	 * @param element the element that was changed
	 * @param type the type of change (don't use ADDED type here)
	 * @see ModelicaElementChange#ModelicaElementChange(IModelicaElement, ChangeType)
	 */
	protected ModelicaElementChange(IModelicaElement element, ChangeType type, IResourceDelta delta)
	{
		this.element = element;
		this.type = type;
		this.delta = delta;
	}
	
	/**
	 * @see org.modelica.mdt.core.IModelicaElementChange#getElement()
	 */
	public IModelicaElement getElement()
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

	/**
	 * @see org.modelica.mdt.core.IModelicaElementChange#getDelta()
	 */
	public IResourceDelta getDelta()
	{
		return delta;
	}
	
	public Object getParent()
	{
		return parent;
	}

}
