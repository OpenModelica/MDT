/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.core;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.internal.omcproxy.ConnectException;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;


/**
 * Common protocol for Modelica elements that contain other Modelica elements.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
public interface IParent 
{
	/**
	 * Returns the immediate children of this element.
	 * Unless otherwise specified by the implementing element,
	 * the children are in no particular order.
	 *
	 * @return the immediate children of this element
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws InvocationError 
	 */
	Collection<Object> getChildren() 
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CoreException;
	
	/**
	 * Returns whether this element has one or more immediate children.
	 * This is a convenience method, and may be more efficient than
	 * testing whether <code>getChildren</code> is an empty array.
	 *
	 * @return true if the immediate children of this element, false otherwise
	 * @throws CoreException 
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws InvocationError 
	 */
	boolean hasChildren() 
		throws CoreException, ConnectException, UnexpectedReplyException,
			InvocationError;
}
