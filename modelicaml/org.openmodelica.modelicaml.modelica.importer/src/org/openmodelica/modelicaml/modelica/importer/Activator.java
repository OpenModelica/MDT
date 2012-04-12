package org.openmodelica.modelicaml.modelica.importer;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.omc.OMCProxy;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.openmodelica.modelicaml.modelica.importer"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private static OMCProxy omcProxy;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	
	IWorkbenchListener onExitListener = new IWorkbenchListener()
	{

		@Override
		public void postShutdown(IWorkbench workbench) {
			/* do nothing */
		}

		@Override
		public boolean preShutdown(IWorkbench workbench, boolean forced) {
			
			if (getOmcProxy() != null) {
				try {
					boolean isOMCRunning = omcProxy.isRunning();
					if (isOMCRunning) {
						
						// TODO: should the user be asked before shutting down the OMC process?
//						boolean choice = MessageDialog.openConfirm( getShell(), 
//									"Modelica Development Tooling", 
//									"The OpenModelica compiler is running in the background.\n " +
//									"Should we stop it? If you have other clients connected choose 'Cancel'.");
						
						omcProxy.sendExpression("quit()", true);	
					}
				} catch (ConnectException e) {}
			}
			
			return true;
		}
	};
	
	
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
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		getWorkbench().addWorkbenchListener(onExitListener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		getWorkbench().removeWorkbenchListener(onExitListener);	
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static OMCProxy getOmcProxy() {
		return omcProxy;
	}

	public static void setOmcProxy(OMCProxy omcProxy) {
		Activator.omcProxy = omcProxy;
	}
}
