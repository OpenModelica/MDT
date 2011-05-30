/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.core.launcher;

import java.io.File;
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
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.debug.gdb.core.GDBDebugger;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;

/**
 * @author Adeel Asghar
 *
 */
public class GDBLaunchDelegate extends LaunchConfigurationDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration, java.lang.String, org.eclipse.debug.core.ILaunch, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
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
		String debugTargetGDBPath = configuration.getAttribute(IMDTConstants.ATTR_MDT_GDB_PATH, "gdb");
		
		if (mode.equals(ILaunchManager.DEBUG_MODE)) 
		{
			try {
				GDBDebugger gdbDebugger = new GDBDebugger(debugTargetProgram, debugTargetCommandLineArguments,
						debugTargetWorkDirectory, debugTargetGDBPath);
				IProcess process = DebugPlugin.newProcess(launch, gdbDebugger.getMISession().getSessionProcess(), debugTargetProgram);
				
				IDebugTarget target = new GDBDebugTarget(launch, process, gdbDebugger.getMISession());
				launch.addDebugTarget(target);
			} catch (MIException e) {
				// TODO Auto-generated catch block
				abort(e.getMessage(), e);
			}
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
	private void abort(String message, Throwable e) throws CoreException {
		// TODO Auto-generated method stub
		throw new CoreException(new Status(IStatus.ERROR,
				IMDTConstants.ID_MDT_DEBUG_MODEL, 0, message, e));
	}
}
