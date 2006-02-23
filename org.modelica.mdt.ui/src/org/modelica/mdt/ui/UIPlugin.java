package org.modelica.mdt.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.core.IModelicaElement;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class for the org.modelica.mdt.ui plugin
 */
public class UIPlugin extends AbstractUIPlugin 
{

	/* the shared instance */
	private static UIPlugin plugin;
	
	public UIPlugin() 
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
	public static UIPlugin getDefault() 
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
	
	/**
	 * @return returns this plugins symbolic name e.g. stuff like org.foo.bar
	 */
	public static String getSymbolicName()
	{
		return getDefault().getBundle().getSymbolicName();
	}

	/**
	 * set an abbot tag, used by GUI regressions tests to find
	 * specific widgets.
	 * 
	 * @param widget the widget to tag
	 * @param tag the tag to set
	 */
	public static void tag(Widget widget, String tag)
	{
		widget.setData("name", tag);
	}		
}
