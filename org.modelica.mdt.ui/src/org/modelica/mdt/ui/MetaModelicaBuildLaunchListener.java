
package org.modelica.mdt.ui;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.core.resources.*;

public class MetaModelicaBuildLaunchListener implements ILaunchListener
{

	public void launchAdded(ILaunch launch)
	{
		// TODO Auto-generated method stub
		IFile launchFile = launch.getLaunchConfiguration().getFile();
		if (launchFile.getName().contains("OMDev") &&
		    launchFile.getName().endsWith(".launch"))
		{
			UIPlugin.getDefault().deleteMarkers();
		}
	}

	public void launchChanged(ILaunch launch)
	{
		// TODO Auto-generated method stub
	}

	public void launchRemoved(ILaunch launch)
	{
		// TODO Auto-generated method stub
	}
	

}
