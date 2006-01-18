package org.modelica.mdt.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.*;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.core.IModelicaElement;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class
 */
public class Plugin extends AbstractUIPlugin 
{

	/* the shared instance */
	private static Plugin plugin;
	
	public Plugin() 
	{
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception 
	{
		super.start(context);
		
		IAdapterManager manager = Platform.getAdapterManager();
		IAdapterFactory factory = new ModelicaElementAdapterFactory();
		manager.registerAdapters(factory, IModelicaElement.class);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception 
	{
		super.stop(context);			
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static Plugin getDefault() 
	{
		return plugin;
	}
	
	/**
	 * Returns the standard display to be used. The method first checks, if
	 * the thread calling this method has an associated display. If so, this
	 * display is returned. Otherwise the method returns the default display.
	 */
	public static Display getStandardDisplay() 
	{
		Display display;
		display = Display.getCurrent();
		if (display == null)
			display = Display.getDefault();
		return display;		
	}
}
