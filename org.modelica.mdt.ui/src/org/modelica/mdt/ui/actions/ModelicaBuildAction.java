/*
 * (c) Copyright MDT Team, PELAB, 2010
 */

package org.modelica.mdt.ui.actions;

import java.util.ResourceBundle;

import org.eclipse.ui.actions.GlobalBuildAction;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.texteditor.TextEditorAction;

import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

import org.eclipse.core.resources.IncrementalProjectBuilder;

/**
 * Class that defines the action to build the Modelica project coresponding to this file
 */
public class ModelicaBuildAction extends TextEditorAction {
	
	private GlobalBuildAction fullBuildAction;
	
	private static final String ACTIONS_RESOURCE_BUNDLE = "org.modelica.mdt.ui.editor.ModelicaActions";
	private static ModelicaBuildAction instance = 
		new ModelicaBuildAction();
	
		/**
		 * Constructs and updates the action.
		 */
		private ModelicaBuildAction() 
		{
			/* Editor will be set later that's why we have null here */
			super(ResourceBundle.getBundle(ACTIONS_RESOURCE_BUNDLE),
			"ModelicaBuildAction.", null);
		}

		public static ModelicaBuildAction getInstance() 
		{
			return instance;
		}
	
	/*
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() 
	{
		fullBuildAction = new GlobalBuildAction(
				 getTextEditor().getEditorSite().getWorkbenchWindow(), 
				 IncrementalProjectBuilder.FULL_BUILD);
		getTextEditor().getEditorSite().getActionBars().setGlobalActionHandler(IDEActionFactory.BUILD_PROJECT.getId(), fullBuildAction);		
		Runnable runnable= new Runnable() 
		{
			public void run()
			{
				// some comment.
				fullBuildAction.run();
			}
		};
		Display display= getTextEditor().getEditorSite().getWorkbenchWindow().getShell().getDisplay();
		BusyIndicator.showWhile(display, runnable);		
	}	
}


