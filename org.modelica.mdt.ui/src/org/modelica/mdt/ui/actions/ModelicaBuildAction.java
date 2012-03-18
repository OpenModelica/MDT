/*
 * (c) Copyright MDT Team, PELAB, 2010
 */

package org.modelica.mdt.ui.actions;

import java.util.ResourceBundle;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.GlobalBuildAction;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;
import org.modelica.mdt.ui.constants.Constants;

/**
 * Class that defines the action to build the Modelica project corresponding to this file
 */
public class ModelicaBuildAction extends TextEditorAction {
	
	private GlobalBuildAction fullBuildAction;

	private static ModelicaBuildAction instance = new ModelicaBuildAction();
	
		/**
		 * Constructs and updates the action.
		 */
		private ModelicaBuildAction() {
			/* Editor will be set later that's why we have null here */
			super(ResourceBundle.getBundle(Constants.MDT_UI_ACTIONS_RESOURCE_BUNDLE), "ModelicaBuildAction.", null);
		}

		public static ModelicaBuildAction getInstance() {
			return instance;
		}

	/*
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		ITextEditor textEditor = getTextEditor();
		IEditorSite editorSite = textEditor.getEditorSite();
		IWorkbenchWindow workbenchWindow = editorSite.getWorkbenchWindow();

		fullBuildAction = new GlobalBuildAction(workbenchWindow, IncrementalProjectBuilder.FULL_BUILD);
		
		IActionBars actionBars = editorSite.getActionBars();
		
		actionBars.setGlobalActionHandler(IDEActionFactory.BUILD_PROJECT.getId(), fullBuildAction);

		Runnable runnable= new Runnable() {
			@Override
			public void run() {
				fullBuildAction.run();
			}
		};

		Shell shell = workbenchWindow.getShell();
		Display display = shell.getDisplay();

		BusyIndicator.showWhile(display, runnable);		
	}	
}
