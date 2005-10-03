package org.modelica.mdt.test;

import java.util.Collections;
import java.util.Vector;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.console.IConsoleConstants;

import junit.framework.TestCase;

public class TestPerspective extends TestCase 
{
	private Vector<String> wizardShortcuts = new Vector<String>(4);
	private Vector<String> showViewShortcuts = new Vector<String>(2);
	
	@Override
	protected void setUp() throws Exception 
	{
		/*
		 * init vector with expected new wizards shortcuts in
		 * modelica prespective
		 */				
		assertTrue(Collections.addAll(wizardShortcuts,
				/* modelica wizards */
				"org.modelica.mdt.NewProjectWizard",
				"org.modelica.mdt.NewClassWizard",
				/* generic wizards */
				"org.eclipse.ui.wizards.new.folder",
				"org.eclipse.ui.wizards.new.file"));
		
		/*
		 * init vector with expected new wizards shortcuts in
		 * modelica prespective
		 */				
		assertTrue(Collections.addAll(showViewShortcuts,
				/* modelica views */
				"org.modelica.mdt.ProjectsView",
				/* generic views */
				IConsoleConstants.ID_CONSOLE_VIEW));
	}
	
	public void testModelicaPerspective() throws WorkbenchException
	{
		IWorkbench workbench = PlatformUI.getWorkbench(); 
		
		/*
		 * open modelica perspective
		 */
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow(); 
		workbench.showPerspective("org.modelica.mdt.perspective", window); 
		
		IWorkbenchPage page = window.getPages()[0];

		/*
		 * check that all 'new wizards' shortcuts are installed
		 */
		Vector<String> presentWizShortcuts = new Vector<String>();
		
		for (String scut : page.getNewWizardShortcuts())
		{
			presentWizShortcuts.addElement(scut);
		}
		assertTrue(presentWizShortcuts.containsAll(wizardShortcuts));
				
		/*
		 * check that all 'show view' shortcuts are installed
		 */
		Vector<String> presentShowViewShortcuts = new Vector<String>();		
		for (String scut : page.getShowViewShortcuts())
		{
			presentShowViewShortcuts.addElement(scut);
		}
		assertTrue(presentShowViewShortcuts.containsAll(showViewShortcuts));
		
		/*
		 * check that all views are present
		 */
		assertNotNull(page.findView("org.modelica.mdt.ProjectsView"));
		assertNotNull(page.findView(IConsoleConstants.ID_CONSOLE_VIEW));

	}


}
