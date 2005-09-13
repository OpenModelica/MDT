package org.modelica.mdt;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

public class PerspectiveFactory implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout)
	{
		/* add 'modelica projects' view */
		IFolderLayout resources = 
			layout.createFolder("org.modelica.mdt.resourcesArea", 
					IPageLayout.LEFT, 0.26f, IPageLayout.ID_EDITOR_AREA);
		resources.addView("org.modelica.mdt.ProjectsView");
		
		/* add console view */
		IFolderLayout consoleArea = 
			layout.createFolder("org.modelica.mdt.consoleArea", 
					IPageLayout.BOTTOM, 0.75f, IPageLayout.ID_EDITOR_AREA);
		consoleArea.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		consoleArea.addPlaceholder(IPageLayout.ID_PROBLEM_VIEW);
		
		
		/* add modelica specific new wizards */
		layout.addNewWizardShortcut("org.modelica.mdt.NewProjectWizard");
		
	}
}
