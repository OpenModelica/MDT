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

import org.eclipse.core.runtime.CoreException;

/**
 * @author Elmir Jagudin
 */
public interface IModelicaRoot
{
	/**
	 * Presents projects in Modelica specific view. All projects are wrapped 
	 * into a IModelicaProject interface
	 *  
	 * @return All projects in the workspace wrapped into IModelicaProject
	 */
	public IModelicaProject[] getProjects();
	
	/**
	 * Get project by name. 
	 * @param name the name of the project to fetch
	 * 
	 * @return the project object or null if no project is found 
	 */
	public IModelicaProject getProject(String name);
	
	/**
	 * Create a new modelica project. This method handles all the details
	 * on creating a new modelica project.
	 * 
	 * @param name the name of the new project
	 * @return the handle to the new project
	 * @throws CoreException on errors while creating the project
	 */
	public IModelicaProject createProject(String name)
		throws CoreException;
	
	/**
	 * add a listener that will be notified on changes to the modelica elements
	 * 
	 * @param listener the listener object to notify of changes
	 */
	public void 
		addModelicaElementChangeListener(IModelicaElementChangeListener listener);
	
	/**
	 * remove a listener, the specified listener will no longer be notified on
	 * changes to modelica elements
	 * 
	 * @param listener the listener to remove
	 */
	public void 
		removeModelicaElementChangeListener
			(IModelicaElementChangeListener listener);

	/**
	 * @return the reference to the standard library
	 */
	public IStandardLibrary getStandardLibrary();
}
