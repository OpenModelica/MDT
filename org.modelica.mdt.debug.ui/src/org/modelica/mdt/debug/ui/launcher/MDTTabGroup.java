package org.modelica.mdt.debug.ui.launcher;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;

/**
 * Tab group for a MDT program
 */
public class MDTTabGroup extends AbstractLaunchConfigurationTabGroup {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
	 *      java.lang.String)
	 */
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		setTabs(new ILaunchConfigurationTab[] 
		            { 
						new MDTMainTab(),
						new SourceLookupTab(), 
						new CommonTab(), 
						new EnvironmentTab() 
					});
	}

}
