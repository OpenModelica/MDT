package org.modelica.mdt.debug.core.launcher;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.variables.IValueVariable;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;
import org.modelica.mdt.debug.server.MDTDebugServer;

/**
 * Launches RML program on a RML Debugger
 */
public class MDTLaunchDelegate extends LaunchConfigurationDelegate {

	private static int commandPort = -1;	
	private static int eventPort = -1;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration,
	 *      java.lang.String, org.eclipse.debug.core.ILaunch,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */	
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException 
	{
		
		/* first launch the MDTDebugServer */
		//MDTDebugServer debugServer = new MDTDebugServer();
		//debugServer.run();
		
		// get the program to be launched
		String debugTargetProgram = configuration.getAttribute(IMDTConstants.ATTR_MDT_PROGRAM, (String)null);
		
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
								
			List commandList = new ArrayList();
			//configuration.
			commandList.add(debugTargetProgram);

			commandPort = findFreePort();
			eventPort = findFreePort();
			if (commandPort == -1 || eventPort == -1) {
				abort("Unable to find free port", null);
			}
			commandList.add("-dbgterminal=socket");
			commandList.add("-dbgcmdport=" + commandPort);
			commandList.add("-dbgeventport=" + eventPort);
			commandList.add("-dbgsocket");			
			debugTargetProgram = "";
			for (int i = 0; i < commandList.size(); i++)
				debugTargetProgram += " " + commandList.get(i);
			
			String[] commandLine = (String[]) commandList.toArray(new String[commandList.size()]);
			
			Process process = DebugPlugin.exec(commandLine, null);
			if (process == null)
			{
				abort("Unable to run the executable: ", null);
			}
			IProcess p = DebugPlugin.newProcess(launch, process, debugTargetProgram);
			
			IDebugTarget target = new MDTDebugTarget(launch, p, commandPort, eventPort);
			launch.addDebugTarget(target);
		}
	}
	
		
	/**
	 * Throws an exception with a new status containing the given message and
	 * optional exception.
	 * 
	 * @param message
	 *            error message
	 * @param e
	 *            underlying exception
	 * @throws CoreException
	 */
	private void abort(String message, Throwable e) throws CoreException 
	{
		throw new CoreException(new Status(IStatus.ERROR,
				IMDTConstants.ID_MDT_DEBUG_MODEL, 0, message, e));
	}

	/**
	 * Returns a free port number on localhost, or -1 if unable to find a free
	 * port.
	 * 
	 * @return a free port number on localhost, or -1 if unable to find a free
	 *         port
	 */
	public static int findFreePort() {
		ServerSocket socket = null;
		try 
		{
			socket = new ServerSocket(0);
			return socket.getLocalPort();
		} 
		catch (IOException e) {
		} 
		finally 
		{
			if (socket != null) 
			{
				try 
				{
					socket.close();
				} 
				catch (IOException e) 
				{
				}
			}
		}
		return -1;
	}

}
