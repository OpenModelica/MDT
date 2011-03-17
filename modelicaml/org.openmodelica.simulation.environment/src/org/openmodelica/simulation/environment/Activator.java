/*
 * 
 */
package org.openmodelica.simulation.environment;

import java.io.File;
import java.io.FilenameFilter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.openmodelica.simulation.core.models.simulation.SimulationSessionConfiguration;
import org.openmodelica.simulation.environment.core.SimulationCenter_Interactive;
import org.openmodelica.simulation.environment.core.SimulationCenter_NonInteractive;
import org.openmodelica.simulation.environment.core.SimulationProjectCenter;
import org.osgi.framework.BundleContext;

// TODO: Auto-generated Javadoc
/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "org.openmodelica.simulation.environment";

	// The shared instance
	/** The plugin. */
	private static Activator plugin;
	
	/** The simulation project center. {@link SimulationProjectCenter} */
	private static SimulationProjectCenter simulationProjectCenter = null;
	
	/** The simulation center_ interactive. {@link SimulationCenter_Interactive} */
	private static SimulationCenter_Interactive simulationCenter_Interactive = null;
	
	
	/** The simulation center_ non interactive. {@link SimulationCenter_NonInteractive} */
	private static SimulationCenter_NonInteractive simulationCenter_NonInteractive = null;
	
	/**
	 * The constructor.
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		if(simulationCenter_Interactive!=null && !simulationCenter_Interactive.getSimulationStatus().equals(""))
			simulationCenter_Interactive.stopAll();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/*
	 *************************************************************************
	 * PLUG-IN SPECIFIED METHODS
	 *************************************************************************
	 */
	
	/**
	 * Synchronized method.
	 *
	 * @return the used SimulationCenter_Interactive;
	 */
	public synchronized static SimulationCenter_Interactive getSimulationCenter_Interactive() {
		if(simulationCenter_Interactive==null)
			createSimulationCenter_Interactive();
		
		return simulationCenter_Interactive;
	}
	
	/**
	 * Synchronized method.
	 *
	 * @return the used SimulationCenter_NonInteractive;
	 */
	public synchronized static SimulationCenter_NonInteractive getSimulationCenter_NonInteractive() {
		if(simulationCenter_NonInteractive==null)
			createSimulationCenter_NonInteractive();
		
		return simulationCenter_NonInteractive;
	}
	
	/**
	 * Synchronized method to return the simulationProjectCenter object.
	 *
	 * @return the used SimulationProjectCenter
	 */
	public synchronized static SimulationProjectCenter getSimulationProjectCenter() {
		
		if (simulationProjectCenter == null)
			createSimulationProjectCenter();
		
		return simulationProjectCenter;
	}

	/**
	 * It is not recommended to initialize this Object as any time at eclipse start up
	 * To a view/editor or other component which needs this kind of object can instantiate it.
	 */
	private static void createSimulationProjectCenter() {
		simulationProjectCenter = new SimulationProjectCenter();
	}
	
	/**
	 * Some view/editor needs a SimulationCenter_Interactive at eclipse startup.
	 */
	private static void createSimulationCenter_Interactive(){
		simulationCenter_Interactive = new SimulationCenter_Interactive();
	}
	
	/**
	 * Some view/editor needs a SimulationCenter_Interactive at eclipse startup.
	 */
	private static void createSimulationCenter_NonInteractive(){
		simulationCenter_NonInteractive = new SimulationCenter_NonInteractive();
	}
	
	/**
	 * It is not recommended to initialize this Object as any time at eclipse start up
	 * To a view/editor or other component which needs this kind of object can instantiate it.
	 *
	 * @param selectedSimulationSessionConfiguration the selected simulation session configuration
	 */
	public synchronized static void initSimulationCenter_Interactive(SimulationSessionConfiguration selectedSimulationSessionConfiguration) {
		if(simulationCenter_Interactive==null)
			createSimulationCenter_Interactive();
			
			simulationCenter_Interactive.initAll(selectedSimulationSessionConfiguration);
//		}
	}
	
	/**
	 * It is not recommended to initialize this Object as any time at eclipse start up
	 * To a view/editor or other component which needs this kind of object can instantiate it.
	 *
	 * @param selectedSimulationSessionConfiguration the selected simulation session configuration
	 */
	public synchronized static void initSimulationCenter_NonInteractive(SimulationSessionConfiguration selectedSimulationSessionConfiguration) {
		if(simulationCenter_NonInteractive==null){
			createSimulationCenter_NonInteractive();
		}

		simulationCenter_NonInteractive.initAll(selectedSimulationSessionConfiguration);
	}

	/**
	 * This Class can be used as a file filter using a prefix.
	 * A prefix represents the last chars in a string
	 * An extension can also be defined as a prefix for example ".txt"
	 * @author Parham Vasaiely
	 *
	 */
	public class OnlyPrefix implements FilenameFilter {
		
		/** The ext. */
		String ext;
		
		/**
		 * Instantiates a new only prefix.
		 *
		 * @param ext Prefix as string, use a dot for file extensions for example ".txt"
		 */
		public OnlyPrefix(String ext) {
			this.ext = ext;
		}
		
		/* (non-Javadoc)
		 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
		 */
		public boolean accept(File dir, String name) {
			return name.endsWith(ext);
		}
	}
	
}
