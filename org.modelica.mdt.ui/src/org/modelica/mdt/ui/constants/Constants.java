
package org.modelica.mdt.ui.constants;

public class Constants {
	
	/* The values are from org.modelica.mdt.ui/plugin.xml, under "view", attribute "id". */
	public static final String MDT_UI_VIEW_PROJECTS = "org.modelica.mdt.ui.view.ModelicaProjectsView";
	public static final String MDT_UI_VIEW_CONSOLE = "org.modelica.mdt.ui.view.ModelicaConsoleView";
	
	/* The values are from org.modelica.mdt.ui/plugin.xml, under "wizard", attribute "id". */
	public static final String MDT_UI_WIZARD_NEW_CLASS = "org.modelica.mdt.NewClassWizard";
	public static final String MDT_UI_WIZARD_NEW_PACKAGE = "org.modelica.mdt.NewPackageWizard";
	public static final String MDT_UI_WIZARD_NEW_PROJECT = "org.modelica.mdt.NewProjectWizard";
	
	/* The value is from org.modelica.mdt.ui/plugin.xml, under "perspective", attribute "id". */
	public static final String MDT_UI_PERSPECTIVE_MODELICA = "org.modelica.mdt.ui.ModelicaPerspective";
	
	/* 
	 * This value is used as the category ID for the following extension points found in org.modelica.mdt.ui/plugin.xml:
	 * <extension point="org.eclipse.ui.views">
	 * <extension point="org.eclipse.ui.newWizards"> 
	 */
	public static final String MDT_UI_MODELICA_CATEGORY = "org.modelica.mdt.ModelicaCategory";
	
	public static final String MDT_UI_ACTIONS_RESOURCE_BUNDLE = "org.modelica.mdt.ui.editor.ModelicaActions";

}
