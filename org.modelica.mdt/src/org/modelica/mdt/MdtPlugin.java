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

package org.modelica.mdt;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelica.mdt.core.ModelicaCore;
import org.osgi.framework.BundleContext;

public class MdtPlugin extends AbstractUIPlugin
{
	public static final String MODELICA_NATURE = 
		"org.modelica.mdt.ModelicaNature";
	
	/* status codes used for logging */ 
	private static final int INTERNAL_ERROR = 0;
	private static final int INTERNAL_WARNING = 1;
	
	/* The shared instance. */
	private static MdtPlugin plugin;
	
	/**
	 * The constructor.
	 */
	public MdtPlugin()
	{
		plugin = this;
	}

	
	public static void addModelicaNature(IProject project) throws CoreException
	{
		if (project.hasNature(MODELICA_NATURE)) 
			return;

		IProjectDescription description = project.getDescription();
		String[] ids = description.getNatureIds();
		String[] newIds = new String[ids.length + 1];
		System.arraycopy(ids, 0, newIds, 0, ids.length);
		newIds[ids.length] = MODELICA_NATURE;
		description.setNatureIds(newIds);
		project.setDescription(description, null);
	}
	
	/**
	 * Returns the shared instance.
	 */
	public static MdtPlugin getDefault()
	{
		return plugin;
	}
	
	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception 
	{
		super.start(context);
		ModelicaCore.start();
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception 
	{
		super.stop(context);
		ModelicaCore.stop();
		plugin = null;
	}

	/**
	 * @return returns this plugins symbolic name e.g. stuff like org.foo.bar
	 */
	public static String getSymbolicName()
	{
		return getDefault().getBundle().getSymbolicName();
	}

	public static void tag(Widget widget, String tag)
	{
		widget.setData("name", tag);
	}
	
	/**
	 * convinience wrapper method for loggin to plugin logger
	 */
	public static void log(IStatus stat)
	{
		plugin.getLog().log(stat);
	}


	/**
	 * Returns the standard display to be used. The method first checks, if
	 * the thread calling this method has an associated display. If so, this
	 * display is returned. Otherwise the method returns the default display.
	 */
	public static Display getStandardDisplay() 
	{
		Display display;
		display= Display.getCurrent();
		if (display == null)
			display= Display.getDefault();
		return display;		
	}
	
	/**
	 * Logs an internal error with the specified throwable
	 * 
	 * @param e the exception to be logged
	 */	
	public static void log(Throwable e) 
	{
		log(new Status(IStatus.ERROR, getSymbolicName(), 
				INTERNAL_ERROR,
				e.getMessage(), e));
	}

	/**
	 * Logs a runtime warning. A message that can be helpfull while
	 * investigating possible problems.
	 * 
	 * @param message warning message
	 */
	public static void logWarning(String message)
	{
		log(new Status(IStatus.WARNING, getSymbolicName(), 
				INTERNAL_WARNING, message, null));
					
	}

	private IWorkbenchPage internalGetActivePage() 
	{
		IWorkbenchWindow window = getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
		{
			return null;
		}
		return getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}

	
	public static IWorkbenchPage getActivePage() 
	{
		return getDefault().internalGetActivePage();
	}
	
	/**
	 * Note: This method is for internal use only. Clients should not
	 * call this method.
	 */
	public static Object[] concatenate(Object[] a1, Object[] a2) 
	{
		int a1Len = 0;
		int a2Len = 0;
		if (a1 != null)
		{
			a1Len= a1.length;			
		}
		if (a2 != null)
		{
			a2Len= a2.length;
		}
		
		Object[] res=  new Object[a1Len + a2Len];
		
		if (a1 != null)
		{
			System.arraycopy(a1, 0, res, 0, a1Len);
		}
		if (a2 != null)
		{
			System.arraycopy(a2, 0, res, a1Len, a2Len);
		}
		return res;
	}
	
	
}
