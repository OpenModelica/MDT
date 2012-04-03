/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelica.mdt.core.CompilerProxy;
import org.osgi.framework.BundleContext;

// TODO: Auto-generated Javadoc
/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

//	/** The simulation center_ non interactive. {@link SimulationCenter_NonInteractive} */
//	private static SimulationCenter_NonInteractive simulationCenter_NonInteractive = null;
	
	// The plug-in ID
	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "org.openmodelica.modelicaml.simulation"; //$NON-NLS-1$

	// The shared instance
	/** The plugin. */
	private static Activator plugin;
	
	/**
	 * The constructor.
	 */
	public Activator() {
	}

	
	//TODO: does not work yet ...
	IWorkbenchListener onExitListener = new IWorkbenchListener()
	{

		@Override
		public void postShutdown(IWorkbench workbench) {
			/* do nothing */
		}

		@Override
		public boolean preShutdown(IWorkbench workbench, boolean forced) {
			try {
				if (!CompilerProxy.isRunning()) return true;
			}
			catch (Exception e) { }
			
			boolean choice = 
				MessageDialog.openConfirm(
					getShell(), 
					"Modelica Development Tooling", 
					"The OpenModelica compiler is running in the background.\n " +
					"Should we stop it? If you have other clients connected choose 'Cancel'.");
			try {
				if (choice) CompilerProxy.sendExpression("quit()", true);
			}
			catch (Exception e) {
				// TODO: handle exception
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
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Synchronized method.
	 *
	 * @return the used SimulationCenter_NonInteractive;
	 */
//	public synchronized static SimulationCenter_NonInteractive getSimulationCenter_NonInteractive() {
//		if(simulationCenter_NonInteractive==null)
//			createSimulationCenter_NonInteractive();
//		
//		return simulationCenter_NonInteractive;
//	}
	
//	/**
//	 * Some view/editor needs a SimulationCenter_Interactive at eclipse startup.
//	 */
//	private static void createSimulationCenter_NonInteractive(){
//		simulationCenter_NonInteractive = new SimulationCenter_NonInteractive();
//	}
	
	/**
	 * It is not recommended to initialize this Object at eclipse start up
	 * To a view/editor or other component which needs this kind of object can instantiate it.
	 *
	 * @param selectedSimulationSessionConfiguration the selected simulation session configuration
	 */
//	public synchronized static void initSimulationCenter_NonInteractive(SimulationSessionConfiguration selectedSimulationSessionConfiguration) {
//		if(simulationCenter_NonInteractive==null){
//			createSimulationCenter_NonInteractive();
//		}
//	}

}
