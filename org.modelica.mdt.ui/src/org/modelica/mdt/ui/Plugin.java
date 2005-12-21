package org.modelica.mdt.ui;

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

//	/**
//	 * Returns an image descriptor for the image file at the given
//	 * plug-in relative path.
//	 *
//	 * @param path the path
//	 * @return the image descriptor
//	 */
//	public static ImageDescriptor getImageDescriptor(String path) 
//	{
//		return AbstractUIPlugin.imageDescriptorFromPlugin("org.modelica.mdt.ui", path);
//	}
}
