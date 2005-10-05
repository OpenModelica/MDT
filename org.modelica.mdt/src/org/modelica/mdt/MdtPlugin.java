package org.modelica.mdt;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class MdtPlugin extends AbstractUIPlugin
{

	public static final String MODELICA_NATURE = 
		"org.modelica.mdt.ModelicaNature";
	
	//The shared instance.
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
		String[] ids= description.getNatureIds();
		String[] newIds= new String[ids.length + 1];
		System.arraycopy(ids, 0, newIds, 0, ids.length);
		newIds[ids.length]= MODELICA_NATURE;
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
}
