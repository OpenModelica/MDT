/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.debug.core;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;

/**
 */
public interface IMDTBreakpoint extends IBreakpoint {

	public String getFailureFilter() throws CoreException; 
			
}

