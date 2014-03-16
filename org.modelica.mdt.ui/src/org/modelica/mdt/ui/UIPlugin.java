package org.modelica.mdt.ui;

import java.io.OutputStream;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.internal.core.CompilerProxy;
import org.modelica.mdt.ui.console.ModelicaTextConsole;
import org.modelica.mdt.ui.text.ModelicaTextTools;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class for the org.modelica.mdt.ui plugin
 */
public class UIPlugin extends AbstractUIPlugin
{
	private ModelicaTextTools fModelicaTextTools;
	private ModelicaTextConsole fConsole;
	private MetaModelicaBuildLaunchListener buildMetaModelicaLanuchListner;
    private boolean DEBUG = false;
    public static String PLUGIN_ID = "org.modelica.mdt.ui";
    public static String ID_PROJECTSVIEW = "org.modelica.mdt.ui.views.ProjectsView";

    protected void debug(String message)
    {
    	if (DEBUG) System.out.println("UIPlugin: " + message);
    }


	public static final String METAMODELICA_BULD_MARKER_ID = "org.modelica.mdt.ui.MetaModelicaBuildMarker";

	/* the shared instance */
	private static UIPlugin plugin;

	public UIPlugin()
	{
		/*load trace/ConsolePatternMatching flag */
		String value = Platform.getDebugOption("org.modelica.mdt.ui/trace/UIPlugin");
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
		buildMetaModelicaLanuchListner = new MetaModelicaBuildLaunchListener();
		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(buildMetaModelicaLanuchListner);

		CompilerProxy.setConsoleOutputStream(getModelicaConsoleOutputStream());
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception
	{
		super.stop(context);
		fConsole = null;
		plugin = null;
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
	}

	/**
	 * Creates the MDT plug-in's standard groups for view context menus.
	 *
	 * @param menu the menu manager to be populated
	 */
	public static void createStandardGroups(IMenuManager menu) {
		if (!menu.isEmpty())
			return;

		menu.add(new Separator("group.new"));
		menu.add(new GroupMarker("group.goto"));
		menu.add(new Separator("group.open"));
		menu.add(new GroupMarker("group.show"));
		menu.add(new Separator("group.edit"));
		menu.add(new Separator("group.reorganize"));
		menu.add(new Separator("group.generate"));
		menu.add(new Separator("group.search"));
		menu.add(new Separator("group.build"));
		menu.add(new Separator("group.build"));
		menu.add(new Separator("additions"));
		menu.add(new Separator("group.viewerSetup"));
		menu.add(new Separator("group.properties"));
	}

	public ModelicaTextConsole getModelicaTextConsole()
	{
		if (fConsole == null)
		{
			fConsole = new ModelicaTextConsole();
		}
		return fConsole;
	}

    public OutputStream getModelicaConsoleOutputStream()
    {
    	if (fConsole == null) getModelicaTextConsole();
    	if (fConsole != null)
    	{
    		return fConsole.getOutputStream();
    	}
    	return null;
    }


	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}

	public static Shell getActiveWorkbenchShell() {
		 IWorkbenchWindow window= getActiveWorkbenchWindow();
		 if (window != null) {
		 	return window.getShell();
		 }
		 return null;
	}
}
