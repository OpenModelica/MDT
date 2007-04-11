/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Linköpings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;
import org.modelica.mdt.ui.view.ModelicaConsoleView;
import org.modelica.mdt.ui.view.ModelicaProjectsView;

public class ModelicaPerspective implements IPerspectiveFactory
{

	public void createInitialLayout(IPageLayout layout)
	{
		String editorArea = layout.getEditorArea();		
		/* add 'modelica projects' view */
		IFolderLayout resources = 
			layout.createFolder("org.modelica.mdt.resourcesArea", 
					IPageLayout.LEFT, 0.25f, editorArea);
		resources.addView(ModelicaProjectsView.ID);
		resources.addPlaceholder(IPageLayout.ID_RES_NAV);
		
		/* add outline view */
		IFolderLayout outlineArea = 
			layout.createFolder("org.modelica.mdt.outlineArea", 
					IPageLayout.BOTTOM, 0.50f, "org.modelica.mdt.resourcesArea");
		outlineArea.addView(IPageLayout.ID_OUTLINE);		
		
		/* add console view */
		IFolderLayout consoleArea = 
			layout.createFolder("org.modelica.mdt.consoleArea", 
					IPageLayout.BOTTOM, 0.75f, IPageLayout.ID_EDITOR_AREA);
		consoleArea.addView(IPageLayout.ID_PROBLEM_VIEW);
		/* add modelica console view */
		consoleArea.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		consoleArea.addView(IPageLayout.ID_BOOKMARKS);
		consoleArea.addView(IProgressConstants.PROGRESS_VIEW_ID);		
						
		/* add new wizards */
		layout.addNewWizardShortcut("org.modelica.mdt.NewProjectWizard");		
		layout.addNewWizardShortcut("org.modelica.mdt.NewPackageWizard");
		layout.addNewWizardShortcut("org.modelica.mdt.NewClassWizard");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
		
		/* add view shortcuts */
		layout.addShowViewShortcut(ModelicaProjectsView.ID);
		layout.addShowViewShortcut(ModelicaConsoleView.ID);	
		// views - debugging
		layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
		// views - standard workbench
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);		
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
	}
	
	
}
