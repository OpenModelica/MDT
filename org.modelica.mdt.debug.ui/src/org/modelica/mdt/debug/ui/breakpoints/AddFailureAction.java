/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.debug.ui.breakpoints;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.modelica.mdt.debug.core.IMDTBreakpoint;
import org.modelica.mdt.debug.core.IMDTFailureBreakpoint;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * The workbench menu action for adding an exception breakpoint
 */
public class AddFailureAction implements IViewActionDelegate, IWorkbenchWindowActionDelegate {
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {		
		AddFailureDialog dialog = new AddFailureDialog();
		if(dialog.open() == IDialogConstants.OK_ID) {
			Object[] results = dialog.getResult(); 
			if(results != null && results.length > 0) {
				try {
					createBreakpoint((String)results[0]);
				}
				catch (CoreException e) {ErrorManager.logError(e.getStatus());}
			}
		}
	}
	
	/**
	 * creates a single breakpoint of the specified type
	 * @param caught if the exception is caught
	 * @param uncaught if the exception is uncaught
	 * @param type the type of the exception
	 * @since 3.2
	 */
	private void createBreakpoint(final String filter) throws CoreException {
		final IResource resource = ResourcesPlugin.getWorkspace().getRoot();
		final Map map = new HashMap(10);
		/* BreakpointUtils.addJavaBreakpointAttributes(map, type);
	
		IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(MDTDebugCorePlugin.ID_MDT_DEBUG_MODEL);
		boolean exists = false;
		for (int j = 0; j < breakpoints.length; j++) {
			IMDTBreakpoint breakpoint = (IMDTBreakpoint) breakpoints[j];
			if (breakpoint instanceof IMDTFailureBreakpoint) {
				if (breakpoint.getFailureFilter().equals(type.getFullyQualifiedName())) {
					exists = true;
					break;
				}
			}
		}
		if (!exists) {
			new Job(BreakpointMessages.AddExceptionAction_0) {
				protected IStatus run(IProgressMonitor monitor) {
					try {
						JDIDebugModel.createExceptionBreakpoint(resource,
								type.getFullyQualifiedName(), caught,
								uncaught, isChecked(type), true, map);
						return Status.OK_STATUS;
					} catch (CoreException e) {
						return e.getStatus();
					}
				}

			}.schedule();
		}
		*/
	}
	
    
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {}
}
