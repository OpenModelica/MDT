package org.modelica.mdt.debug.core.launcher;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;

/**
 * Launches MDT program on a MDT Debugger
 */
@SuppressWarnings("unchecked")
public class MDTLaunchDelegate extends LaunchConfigurationDelegate {

	private static int commandPort = -1;
	private static int replyPort   = -1;
	private static int eventPort   = -1;
	private static int signalPort  = -1;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration,
	 *      java.lang.String, org.eclipse.debug.core.ILaunch,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */	
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException 
	{		
		// get the program to be launched
		String debugTargetProgram = configuration.getAttribute(IMDTConstants.ATTR_MDT_PROGRAM, (String)null);
		if (debugTargetProgram == null)
		{
			abort("The executable to debug is not set.\nPlease check the debug configuration!", null);			
		}
		IPath progPath = new Path(debugTargetProgram);
		if (!progPath.toFile().exists())
		{
			abort("The executable to debug does not exist: " + progPath.toOSString() + 
					"\nPlease build/rebuild the executable before debugging!", null);
		}
		// remove the file
		progPath = progPath.removeLastSegments(1);
		String debugTargetCommandLineArguments = configuration.getAttribute(IMDTConstants.ATTR_MDT_ARGUMENTS, (String)null);
		String debugTargetWorkDirectory = configuration.getAttribute(IMDTConstants.ATTR_MDT_WORK_DIRECTORY, progPath.toOSString());
		/* TODO: USE THESE IN THE PROCESS exec(); 
		ILaunchManager.ATTR_ENVIRONMENT_VARIABLES;
		ILaunchManager.ATTR_APPEND_ENVIRONMENT_VARIABLES;
		*/
		
		
		if (mode.equals(ILaunchManager.DEBUG_MODE)) 
		{						
			List commandList = new ArrayList();
			//configuration.
			commandList.add(debugTargetProgram);

			commandPort = findFreePort();
			replyPort   = findFreePort();
			eventPort   = findFreePort();
			signalPort  = findFreePort();
			if (commandPort == -1 || eventPort == -1 || signalPort == -1 || replyPort == -1) {
				abort("Unable to find free ports to use for debugging connection.", null);
			}
			commandList.add("-dbgCmdPort="    + commandPort);
			commandList.add("-dbgReplyPort="  + replyPort);			
			commandList.add("-dbgEventPort="  + eventPort);
			commandList.add("-dbgSignalPort=" + signalPort);
			String[] extraArgs = DebugPlugin.parseArguments(debugTargetCommandLineArguments);
			for (String x : extraArgs) commandList.add(x);
			debugTargetProgram = "";
			for (int i = 0; i < commandList.size(); i++) debugTargetProgram += " " + commandList.get(i);
			
			String[] commandLine = (String[]) commandList.toArray(new String[commandList.size()]);
			
			Process process = DebugPlugin.exec(
					commandLine, 
					new File(debugTargetWorkDirectory), 
					null);
			if (process == null)
			{
				abort("Unable to run the executable: " + commandLine, null);
			}
			/* wait a bit of 10 seconds for the process to start */
			try 
			{ 
				Thread.sleep(5000);
			}
			catch (InterruptedException e) { /* ignore */ }			
			
			IProcess p = DebugPlugin.newProcess(launch, process, debugTargetProgram);
			
			IDebugTarget target = new MDTDebugTarget(launch, p, commandPort, replyPort, eventPort, signalPort);
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
	
	public boolean buildForLaunch(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor) throws CoreException {
		// do NOT build for lunch when debugging MetaModelica
		return false;
	}
}
