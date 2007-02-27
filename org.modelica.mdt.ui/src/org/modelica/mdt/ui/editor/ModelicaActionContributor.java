/*
 * (c) Copyright MDT Team, PELAB, 2006
 */

package org.modelica.mdt.ui.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.texteditor.BasicTextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.modelica.mdt.ui.actions.ModelicaCorrectIndentationAction;


/**
 * Contributes actions to the desktop's Edit menu and the toolbar.
 * org.modelica.mdt.ui.editor.ModelicaActionContributor
 */
public class ModelicaActionContributor extends BasicTextEditorActionContributor
{
	protected ModelicaCorrectIndentationAction correctIndentationAction;

	/**
	 * Default constructor.
	 */
	public ModelicaActionContributor() {
		super();
		correctIndentationAction = ModelicaCorrectIndentationAction.getInstance();
	}
	
	/* (non-Javadoc)
	 * Method declared on EditorActionBarContributor
	 */
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);
		IMenuManager editMenu = menuManager.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			editMenu.add(new Separator());
			editMenu.add(correctIndentationAction);
		}
	}
	
	/* (non-Javadoc)
	 * Method declared on EditorActionBarContributor
	 */
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);
		toolBarManager.add(new Separator());
		toolBarManager.add(correctIndentationAction);
	}
	
	/* (non-Javadoc)
	 * Method declared on EditorActionBarContributor
	 */
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);

		ITextEditor editor= null;
		if (part instanceof ITextEditor)
			editor= (ITextEditor) part;
		
		correctIndentationAction.setEditor(editor);
		correctIndentationAction.update();
	}
}
