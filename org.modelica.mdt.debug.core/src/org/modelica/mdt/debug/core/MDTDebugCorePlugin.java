package org.modelica.mdt.debug.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class MDTDebugCorePlugin extends Plugin 
{

	public static boolean DEBUG = false;
	
	// The shared instance.
	private static MDTDebugCorePlugin plugin;

	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * Unique identifier for the MDT debug model (value
	 * <code>mdt.debugModel</code>).
	 */
	public static final String ID_MDT_DEBUG_MODEL = "mdt.debugModel";

	/**
	 * Launch configuration attribute key. Value is a path to an exe program.
	 * The path is a string representing a full path to an exe program in the
	 * workspace.
	 */
	public static final String ATTR_MDT_PROGRAM = ID_MDT_DEBUG_MODEL + ".ATTR_MDT_PROGRAM";

	/**
	 * Identifier for the MDT launch configuration type (value
	 * <code>mdt.launchType</code>)
	 */
	public static final String ID_MDT_LAUNCH_CONFIGURATION_TYPE = "org.modelica.mdt.debug.core.launchConfigurationType";
	
	{
		/* load debug options and set debug flag variables accordingly */
		/*load trace/omcCalls flag */
		String value = Platform.getDebugOption("org.modelica.mdt.debug.core/debug");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			DEBUG = true;
		}
	}

	/**
	 * The constructor.
	 */
	public MDTDebugCorePlugin() 
	{
		super();
		plugin = this;
		if (DEBUG) System.out.println("MDTDebugCorePlugin() initializing");
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		if (DEBUG) System.out.println("MDTDebugCorePlugin() starting");		
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		resourceBundle = null;
		if (DEBUG) System.out.println("MDTDebugCorePlugin() stop");
	}

	/**
	 * Returns the shared instance.
	 */
	public static MDTDebugCorePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = MDTDebugCorePlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle.getBundle("org.modelica.mdt.debug.core.MDTDebugCorePluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}

	/**
	 * Return a <code>java.io.File</code> object that corresponds to the
	 * specified <code>IPath</code> in the plugin directory, or
	 * <code>null</code> if none.
	 */
	public static File getFileInPlugin(IPath path) {
		try {
			URL installURL = new URL(getDefault().getBundle().getEntry("/"), path.toString());
			URL localURL = FileLocator.toFileURL(installURL);
			return new File(localURL.getFile());
		} catch (IOException ioe) {
			return null;
		}
	}

}
