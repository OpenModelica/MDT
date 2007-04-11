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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ModelicaCore;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class for the org.modelica.mdt.core plugin
 * 
 * @author Sigmund Freud
 */

public class CorePlugin extends AbstractUIPlugin
{
	public static final String MODELICA_NATURE = "org.modelica.mdt.core.ModelicaNature";

	/* human readable name of the plugin */
	public static final String PLUGIN_HUMAN_NAME = "Modelica Development Tooling Plugin";

	/* extension point ID for modelica compilers */
	public static final String COMPILER_EXTENSION_ID = "org.modelica.mdt.core.compiler";
	
	public static final String UNEXPECTED_NAMESPACE_MARKER_ID = "org.modelica.mdt.core.unexpectednamespacemarker";
		
	/* The shared instance. */
	private static CorePlugin plugin;
	
	/**
	 * The constructor.
	 */
	public CorePlugin()
	{
		plugin = this;
	}

//	class LazyLoadThread extends Thread
//	{
//		private Vector<IModelicaImport> imports = null;;
//		
//		public LazyLoadThread()
//		{
//			super("LazyLoading for Class:" + getElementName());
//			this.imports = imports;
//			setPriority(Thread.MIN_PRIORITY);
//		}
//				
//		public void run()
//		{
//			/* wait a bit before loading imports */
//			try{ Thread.sleep(10000);} catch(InterruptedException e) {/* ignore */}			
//			try
//			{
//				for (IModelicaImport i : this.imports)
//				{
//					/* wait a bit between import loading */
//					try{ Thread.sleep(100);} catch(InterruptedException e) {/* ignore */}					
//					i.getImportedPackage();
//				}
//
//			}
//			catch(Exception e)
//			{
//				ErrorManager.logError(e);
//			}
//		}
//	}
//	
//	LazyLoadImport lazyLoadImports = new LazyLoadImport(new Vector<IModelicaImport>(imports));
//	lazyLoadImports.start();
	
	
	private static Map lazyLoadList = new HashMap(); 
	
	public Map getLazyLoadList()
	{
		return lazyLoadList;
	}

	public void addToLazyLoadList()
	{
		//return lazyLoadList;
	}
	
	/**
	 * 
	 * @param project the project that should be marked as Modelica project
	 * @throws CoreException
	 */
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
	 * @return the shared instance of CorePlugin
	 */
	public static CorePlugin getDefault()
	{
		return plugin;
	}
	
	IWorkbenchListener onExitListener = new IWorkbenchListener()
	{

		public void postShutdown(IWorkbench workbench) {
			/* do nothing */
		}

		public boolean preShutdown(IWorkbench workbench, boolean forced) {
			try
			{
				if (!CompilerProxy.isRunning()) return true;
			}
			catch (Exception e) { }
			
			boolean choice = 
				MessageDialog.openConfirm(
					getShell(), 
					"Modelica Development Tooling", 
					"The OpenModelica compiler is running in the background.\n " +
					"Should we stop it? If you have other clients connected choose 'Cancel'.");
			try
			{
				if (choice) CompilerProxy.sendExpression("quit()", true);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return true;
		}
	};
	
	/**
	 * This method is called upon plug-in activation
	 * @param context
	 */
	public void start(BundleContext context) throws Exception 
	{
		super.start(context);
		ModelicaCore.start();
		getWorkbench().addWorkbenchListener(onExitListener);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception 
	{
		ModelicaCore.stop();
		plugin = null;
		super.stop(context);
		getWorkbench().removeWorkbenchListener(onExitListener);		
	}

	/**
	 * @return returns this plugins symbolic name e.g. stuff like org.foo.bar
	 */
	public static String getSymbolicName()
	{
		return getDefault().getBundle().getSymbolicName();
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
	
	public static Shell getShell()
	{
		IWorkbench workbench = getDefault().getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			
		if (window == null)
		{
			return null;
		}

		return window.getShell();
	}
	
	public static Display getDisplay()
	{
		return getDefault().getWorkbench().getDisplay();
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
		
		Object[] res =  new Object[a1Len + a2Len];
		
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

	public static final String TAB = "tab"; //$NON-NLS-1$
	public static final String SPACE = "space"; //$NON-NLS-1$
	
}
