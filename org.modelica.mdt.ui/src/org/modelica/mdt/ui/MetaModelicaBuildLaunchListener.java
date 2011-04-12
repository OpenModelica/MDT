
package org.modelica.mdt.ui;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.core.resources.*;
import org.modelica.mdt.ui.util.Launchers;

public class MetaModelicaBuildLaunchListener implements ILaunchListener
{

	public String User_count;
	
	public void launchAdded(ILaunch launch)
	{
		// TODO Auto-generated method stub
		if (launch == null) return;
		ILaunchConfiguration launchConfig = launch.getLaunchConfiguration();
		if (launchConfig == null) return;		
		IFile launchFile = launchConfig.getFile();
		if (launchFile == null) return; 
		if (Launchers.launcherNameMatches(launchFile.getName()))
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
