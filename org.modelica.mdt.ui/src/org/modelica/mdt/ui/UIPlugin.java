package org.modelica.mdt.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.osgi.framework.BundleContext;
import org.modelica.mdt.ui.text.ModelicaTextTools;
import java.util.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.DebugPlugin;

/**
 * The main plugin class for the org.modelica.mdt.ui plugin
 */
public class UIPlugin extends AbstractUIPlugin 
{
	private ModelicaTextTools fModelicaTextTools;
	//private List<IFile> markedFiles;
	//private MetaModelicaJobListener jobListener;
	private MetaModelicaBuildLaunchListener buildMetaModelicaLanuchListner;
    private boolean DEBUG = false;

    protected void debug(String message)
    {
    	if (DEBUG) System.out.println("UIPlugin: " + message);
    }
    
	
	public static final String METAMODELICA_BULD_MARKER_ID =
		"org.modelica.mdt.ui.MetaModelicaBuildMarker";
	
	/* the shared instance */
	private static UIPlugin plugin;
	
	public UIPlugin() 
	{
		String value = Platform.getDebugOption  /*load trace/ConsolePatternMatching flag */
		("org.modelica.mdt.ui/trace/UIPlugin");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			DEBUG = true;
		}
		
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
		//jobListener = new MetaModelicaJobListener();
		//Platform.getJobManager().addJobChangeListener(jobListener);
		buildMetaModelicaLanuchListner = new MetaModelicaBuildLaunchListener();
		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(buildMetaModelicaLanuchListner);
		//markedFiles = new Vector<IFile>();
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception 
	{
		super.stop(context);			
		plugin = null;
		//markedFiles.clear();
		//markedFiles = null;
		//Platform.getJobManager().removeJobChangeListener(jobListener);
		DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(buildMetaModelicaLanuchListner);		
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
	
	public synchronized ModelicaTextTools getModelicaTextTools() {
		if (fModelicaTextTools == null)
			fModelicaTextTools= new ModelicaTextTools();
		return fModelicaTextTools;
	}
	
	public void deleteMarkers()
	{
		debug("DeleteMarkers Called!");
		IModelicaProject[] projects = ModelicaCore.getModelicaRoot().getProjects();
		for (IModelicaProject project: projects)
		{
			if (project == null) continue;
			IProject pro = project.getWrappedProject();
			if (pro == null) continue;
			if (!pro.isOpen()) continue;
			try
			{
				pro.deleteMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE);				
				pro.deleteMarkers(UIPlugin.METAMODELICA_BULD_MARKER_ID, false, IResource.DEPTH_INFINITE);
			}
			catch(CoreException c)
			{
				c.printStackTrace();
			}
		}
		/*
		if (markedFiles == null) return;
		for (IFile file : markedFiles)
		{	
			try
			{
				file.deleteMarkers(UIPlugin.METAMODELICA_BULD_MARKER_ID, false, IResource.DEPTH_INFINITE);			
			}
			catch(Exception e)
			{	
			}
		}
		markedFiles.clear();
		*/
	}
	
//	public void addMarkedFile(IFile file)
//	{
//		markedFiles.add(file);
//	}
//	
//	public void deleteMarkedFile(IFile file)
//	{
//		markedFiles.remove(file);
//	}
//
//	public boolean containsMarkedFile(IFile file)
//	{
//		return markedFiles.contains(file);
//	}
}
