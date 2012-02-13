/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.perspective.perspectives;

import org.eclipse.papyrus.wizards.CreateModelWizard;
import org.eclipse.papyrus.wizards.NewPapyrusProjectWizard;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;


// TODO: Auto-generated Javadoc
/**
 *  This class is meant to serve as an example for how various contributions 
 *  are made to a perspective. Note that some of the extension point id's are
 *  referred to as API constants while others are hardcoded and may be subject 
 *  to change. 
 */
 
//public class PapyrusModelicaMLPerspective extends PapyrusPerspective {
public class PapyrusModelicaMLPerspective implements IPerspectiveFactory {

	/** The Constant ID_MODELEXPLORER. */
	protected static final String ID_MODELEXPLORER = "org.eclipse.papyrus.modelexplorer.modelexplorer";
	
	/** The Constant ID_COMPONENT_TREE_VIEW. */
	protected static final String ID_COMPONENT_TREE_VIEW = "org.openmodelica.modelicaml.view.componentstree";
	
	/** The Constant ID_VALIDATION_VIEW. */
	protected static final String ID_VALIDATION_VIEW = "org.openmodelica.modelicaml.gen.modelica.validation";
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}
	
	/**
	 * Define actions.
	 * 
	 * @param layout
	 *            the layout
	 */
	public void defineActions(IPageLayout layout) {
		// Add "new wizards".
		layout.addNewWizardShortcut(NewPapyrusProjectWizard.WIZARD_ID);
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut(CreateModelWizard.WIZARD_ID);
		// Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(ID_COMPONENT_TREE_VIEW);
		layout.addShowViewShortcut(ID_VALIDATION_VIEW);

		layout.addActionSet("org.eclipse.debug.ui.launchActionSet");

		// add perspectives
		layout.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective");
		layout.addPerspectiveShortcut("org.eclipse.jdt.ui.JavaPerspective");
	}
	
	

	/**
	 * Define layout.
	 * 
	 * @param layout
	 *            the layout
	 */
	public void defineLayout(IPageLayout layout) {
		// Editors are placed for free.
		String editorArea = layout.getEditorArea();

		// Place the Model Explorer and the Resource Navigator to the left of editor area.
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, (float)0.20, editorArea);
		left.addView(ID_MODELEXPLORER);
		left.addView(IPageLayout.ID_PROJECT_EXPLORER);
		// place outline under the navigator
		IFolderLayout left_bottom = layout.createFolder("left_bottom", IPageLayout.BOTTOM, (float)0.70, "left");
		left_bottom.addView(ID_COMPONENT_TREE_VIEW);
		//left_bottom.addView(IPageLayout.ID_OUTLINE);
		

		// place properties under the editor
		IFolderLayout bottom = layout.createFolder("under", IPageLayout.BOTTOM, (float)0.70, editorArea);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addView(ID_VALIDATION_VIEW);
		// bottom.addView("org.eclipse.pde.runtime.LogView");
	}
	
//	private IPageLayout factory;
//
//	public PapyrusModelicaMLPerspective() {
//		super();
//	}
//
//	public void createInitialLayout(IPageLayout factory) {
//		this.factory = factory;
//		addViews();
//		addActionSets();
//		addNewWizardShortcuts();
//		addPerspectiveShortcuts();
//		addViewShortcuts();
//	}
//
//	private void addViews() {
//		// Creates the overall folder layout. 
//		// Note that each new Folder uses a percentage of the remaining EditorArea.
//		
//		IFolderLayout bottom =
//			factory.createFolder(
//				"bottomRight", //NON-NLS-1
//				IPageLayout.BOTTOM,
//				0.75f,
//				factory.getEditorArea());
//		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
//		bottom.addView("org.eclipse.team.ui.GenericHistoryView"); //NON-NLS-1
//		bottom.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
//
//		IFolderLayout topLeft =
//			factory.createFolder(
//				"topLeft", //NON-NLS-1
//				IPageLayout.LEFT,
//				0.25f,
//				factory.getEditorArea());
//		topLeft.addView(IPageLayout.ID_RES_NAV);
//		topLeft.addView("org.eclipse.jdt.junit.ResultView"); //NON-NLS-1
//		
//		factory.addFastView("org.eclipse.team.ccvs.ui.RepositoriesView",0.50f); //NON-NLS-1
//		factory.addFastView("org.eclipse.team.sync.views.SynchronizeView", 0.50f); //NON-NLS-1
//	}
//
//	private void addActionSets() {
//		factory.addActionSet("org.eclipse.debug.ui.launchActionSet"); //NON-NLS-1
//		factory.addActionSet("org.eclipse.debug.ui.debugActionSet"); //NON-NLS-1
//		factory.addActionSet("org.eclipse.debug.ui.profileActionSet"); //NON-NLS-1
//		factory.addActionSet("org.eclipse.jdt.debug.ui.JDTDebugActionSet"); //NON-NLS-1
//		factory.addActionSet("org.eclipse.jdt.junit.JUnitActionSet"); //NON-NLS-1
//		factory.addActionSet("org.eclipse.team.ui.actionSet"); //NON-NLS-1
//		factory.addActionSet("org.eclipse.team.cvs.ui.CVSActionSet"); //NON-NLS-1
//		factory.addActionSet("org.eclipse.ant.ui.actionSet.presentation"); //NON-NLS-1
//		factory.addActionSet(JavaUI.ID_ACTION_SET);
//		factory.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
//		factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET); //NON-NLS-1
//	}
//
//	private void addPerspectiveShortcuts() {
//		factory.addPerspectiveShortcut("org.eclipse.team.ui.TeamSynchronizingPerspective"); //NON-NLS-1
//		factory.addPerspectiveShortcut("org.eclipse.team.cvs.ui.cvsPerspective"); //NON-NLS-1
//		factory.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective"); //NON-NLS-1
//	}
//
//	private void addNewWizardShortcuts() {
//		factory.addNewWizardShortcut("org.eclipse.team.cvs.ui.newProjectCheckout");//NON-NLS-1
//		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//NON-NLS-1
//		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//NON-NLS-1
//	}
//
//	private void addViewShortcuts() {
//		factory.addShowViewShortcut("org.eclipse.ant.ui.views.AntView"); //NON-NLS-1
//		factory.addShowViewShortcut("org.eclipse.team.ccvs.ui.AnnotateView"); //NON-NLS-1
//		factory.addShowViewShortcut("org.eclipse.pde.ui.DependenciesView"); //NON-NLS-1
//		factory.addShowViewShortcut("org.eclipse.jdt.junit.ResultView"); //NON-NLS-1
//		factory.addShowViewShortcut("org.eclipse.team.ui.GenericHistoryView"); //NON-NLS-1
//		factory.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
//		factory.addShowViewShortcut(JavaUI.ID_PACKAGES);
//		factory.addShowViewShortcut(IPageLayout.ID_RES_NAV);
//		factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
//		factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
//	}

}
