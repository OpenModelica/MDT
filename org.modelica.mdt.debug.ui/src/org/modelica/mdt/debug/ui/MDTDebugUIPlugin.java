package org.modelica.mdt.debug.ui;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import java.net.URL;
import java.util.*;

public class MDTDebugUIPlugin  extends AbstractUIPlugin  
{
	
	static private boolean DEBUG = false;

	//	The shared instance.
	private static MDTDebugUIPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	private final static String ICONS_PATH = "icons/full/";//$NON-NLS-1$
	private final static String PATH_OBJECT = ICONS_PATH + "obj16/"; //Model object icons //$NON-NLS-1$
    private final static String PATH_ELOCALTOOL = ICONS_PATH + "elcl16/"; //Enabled local toolbar icons //$NON-NLS-1$
    private final static String PATH_DLOCALTOOL = ICONS_PATH + "dlcl16/"; //Disabled local toolbar icons //$NON-NLS-1$
    
    /**
     *  Toolbar action to pop data stack
     */
    public final static String IMG_ELCL_POP = "IMG_ELCL_POP";
    public final static String IMG_DLCL_POP = "IMG_DLCL_POP";
    
    /**
     * Toolbar action to push onto data stack
     */
    public final static String IMG_ELCL_PUSH = "IMG_ELCL_PUSH";
    public final static String IMG_DLCL_PUSH = "IMG_DLCL_PUSH";
    
    /**
     * MDT program image
     */
    public final static String IMG_OBJ_MDT = "IMB_OBJ_MDT";

	{
		/* load debug options and set debug flag variables accordingly */
		/*load trace/omcCalls flag */
		String value = Platform.getDebugOption("org.modelica.mdt.debug.ui/debug");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			DEBUG = true;
		}
	}    
    
	/**
	 * The constructor.
	 */
	public MDTDebugUIPlugin() 
	{
		super();
		plugin = this;
		if (DEBUG) System.out.println("MDTDebugUIPlugin() initializing");
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception 
	{
		super.start(context);
		if (DEBUG) System.out.println("MDTDebugUIPlugin() start");		
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		resourceBundle = null;
		if (DEBUG) System.out.println("MDTDebugUIPlugin() stop");		
	}

	/**
	 * Returns the shared instance.
	 */
	public static MDTDebugUIPlugin getDefault() 
	{
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) 
	{
		ResourceBundle bundle = MDTDebugUIPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() 
	{
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle.getBundle("org.modelica.mdt.debug.ui.MDTDebugUIPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
	 */
	protected void initializeImageRegistry(ImageRegistry reg) {
		declareImage(IMG_OBJ_MDT, PATH_OBJECT + "mdt.gif");
	    declareImage(IMG_ELCL_POP, PATH_ELOCALTOOL + "pop.gif");
	    declareImage(IMG_DLCL_POP, PATH_DLOCALTOOL + "pop.gif");
	    declareImage(IMG_ELCL_PUSH, PATH_ELOCALTOOL + "push.gif");
	    declareImage(IMG_DLCL_PUSH, PATH_DLOCALTOOL + "push.gif");
	}
	
    /**
     * Declares a workbench image given the path of the image file (relative to
     * the workbench plug-in). This is a helper method that creates the image
     * descriptor and passes it to the main <code>declareImage</code> method.
     * 
     * @param symbolicName the symbolic name of the image
     * @param path the path of the image file relative to the base of the workbench
     * plug-ins install directory
     * <code>false</code> if this is not a shared image
     */
    private void declareImage(String key, String path) 
    {
    	Bundle bundle = Platform.getBundle("org.modelica.mdt.debug.ui");
        if (bundle == null) {
			return;
		}    	
        URL url = FileLocator.find(bundle, new Path(path), null);
        ImageDescriptor desc = ImageDescriptor.createFromURL(url);
        getImageRegistry().put(key, desc);
    }    	
	
}