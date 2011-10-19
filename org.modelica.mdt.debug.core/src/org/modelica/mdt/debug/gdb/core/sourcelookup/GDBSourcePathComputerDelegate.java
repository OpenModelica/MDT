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
package org.modelica.mdt.debug.gdb.core.sourcelookup;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.WorkspaceSourceContainer;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;

/**
 * @author Adeel Asghar
 *
 */
/**
 * Computes the default source lookup path for a MDT launch configuration. The
 * default source lookup path is the folder or project containing the MDT
 * program being launched. If the program is not specified, the workspace is
 * searched by default.
 */
public class GDBSourcePathComputerDelegate implements ISourcePathComputerDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate#computeSourceContainers(org.eclipse.debug.core.ILaunchConfiguration, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public ISourceContainer[] computeSourceContainers(
			ILaunchConfiguration configuration, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		String path = configuration.getAttribute(IMDTConstants.ATTR_MDT_PROGRAM, (String) null);
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
		ISourceContainer sourceContainer = null;
		try {
			if (path != null) {
				IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
				if (resource == null) 
				{
					// try via the file!
					resource = ResourcesPlugin.getWorkspace().getRoot().findMember(f.getProjectRelativePath());
				}
				if (resource != null) {
					IContainer container = resource.getProject();
					if (container.getType() == IResource.PROJECT) {
						sourceContainer = new ProjectSourceContainer((IProject) container, false);
					} else if (container.getType() == IResource.FOLDER) {
						sourceContainer = new FolderSourceContainer(container, false);
					}
				}
				else if (f != null)
				{
					IContainer container = f.getProject();
					if (container.getType() == IResource.PROJECT) {
						sourceContainer = new ProjectSourceContainer((IProject) container, false);
					} else if (container.getType() == IResource.FOLDER) {
						sourceContainer = new FolderSourceContainer(container, false);
					}
				}
			}
		} catch (Exception e) {}
		finally {
			if (sourceContainer == null) {
				sourceContainer = new WorkspaceSourceContainer();
			}
		}
		return new ISourceContainer[] { sourceContainer };
	}

}
