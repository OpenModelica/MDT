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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.modelica.mdt.internal.core.ModelicaRoot;
import org.modelica.mdt.MdtPlugin;

public class ModelicaCore 
{
	private static ModelicaRoot modelicaRoot = null;

	
	public static class CreateNewProjectRunnable implements IRunnableWithProgress
	{
		IProject newProject;
		
		public CreateNewProjectRunnable(IProject newProject)
		{
			this.newProject = newProject;
		}

		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException
		{
			monitor.beginTask("creating project " + newProject.getName(), 3);
			try
			{
				/* create the project */
				newProject.create(monitor);
				monitor.worked(1);

				/* open project */
				newProject.open(monitor);
				monitor.worked(2);

				/* add modelica nature to the project */
				MdtPlugin.addModelicaNature(newProject);
				
			}
			catch (CoreException e)
			{
				showProjectCreationError(e.getStatus());
			}			
			monitor.done();
		}

	}
	private static void showProjectCreationError(String message, Exception e)
	{
		showProjectCreationError(new Status(IStatus.ERROR, 
				MdtPlugin.getSymbolicName(), 0, message, e));
	}
	
	
	private static void showProjectCreationError(IStatus status)
	{
		ErrorDialog.openError(null, "Error", "Could not create project", status);	
	}

	/**
	 * creates a modelica project in the workspace
	 * 
	 * @return the handle to the created project or null if there were errors during 
	 * creation of the project
	 */
	public static IProject createProject(String name, IRunnableContext context)
	{
		IProject project = null;		
		try 
		{
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
			
			context.run(false, false, 
					new CreateNewProjectRunnable(project));

			/* this should be done after we know for certain that the project 
			 * have been created */
			//TODO implement select and reveal
			//BasicNewResourceWizard.selectAndReveal(project, 
			//workbench.getActiveWorkbenchWindow());
		} 
		catch (InvocationTargetException e)
		{
			showProjectCreationError("internal error during project creation", 
									 e);
		}
		catch (InterruptedException e)
		{
			/* ignore this */
		}
		
		return project;
		
	}
	
	public static IModelicaRoot getModelicaRoot()
	{
		return modelicaRoot;
	}

	public static void start()
	{
		modelicaRoot = new ModelicaRoot();
		modelicaRoot.start();
	}


	public static void stop()
	{
		modelicaRoot.stop();
		modelicaRoot = null;
	}
}
