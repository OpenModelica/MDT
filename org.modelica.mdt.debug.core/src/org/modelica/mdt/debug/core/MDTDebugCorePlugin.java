package org.modelica.mdt.debug.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class MDTDebugCorePlugin extends Plugin {

	// The shared instance.
	private static MDTDebugCorePlugin plugin;

	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * Unique identifier for the RML debug model (value
	 * <code>rml.debugModel</code>).
	 */
	public static final String ID_RML_DEBUG_MODEL = "rml.debugModel";

	/**
	 * Name of the string substitution variable that resolves to the location of
	 * a local Perl executable (value <code>perlExecutable</code>).
	 */
	public static final String VARIALBE_PERL_EXECUTABLE = "perlExecutable";

	/**
	 * Launch configuration attribute key. Value is a path to a perl program.
	 * The path is a string representing a full path to a perl program in the
	 * workspace.
	 */
	public static final String ATTR_RML_PROGRAM = ID_RML_DEBUG_MODEL
			+ ".ATTR_RML_PROGRAM";

	/**
	 * Identifier for the RML launch configuration type (value
	 * <code>rml.launchType</code>)
	 */
	public static final String ID_RML_LAUNCH_CONFIGURATION_TYPE = "rml.launchType";

	/**
	 * The constructor.
	 */
	public MDTDebugCorePlugin() {
		super();
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		resourceBundle = null;
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
		ResourceBundle bundle = MDTDebugCorePlugin.getDefault()
				.getResourceBundle();
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
				resourceBundle = ResourceBundle
						.getBundle("example.debug.core.DebugCorePluginResources");
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
			URL installURL = new URL(getDefault().getDescriptor()
					.getInstallURL(), path.toString());
			URL localURL = Platform.asLocalURL(installURL);
			return new File(localURL.getFile());
		} catch (IOException ioe) {
			return null;
		}
	}

}
