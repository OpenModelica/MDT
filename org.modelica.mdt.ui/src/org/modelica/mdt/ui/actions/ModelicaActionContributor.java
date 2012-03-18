/*
 * (c) Copyright MDT Team, PELAB, 2006
 */

package org.modelica.mdt.ui.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.texteditor.BasicTextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.modelica.mdt.ui.view.ModelicaStatusLineContributor;


/**
 * Contributes actions to the desktop's Edit menu and the toolbar.
 * org.modelica.mdt.ui.actions.ModelicaActionContributor
 */
public class ModelicaActionContributor extends BasicTextEditorActionContributor {

	protected ModelicaCorrectIndentationAction correctIndentationAction;
	protected ModelicaBuildAction buildAction;
	protected ModelicaStatusLineContributor mdtStatus;

	/**
	 * Default constructor.
	 */
	public ModelicaActionContributor() {
		correctIndentationAction = ModelicaCorrectIndentationAction.getInstance();
		buildAction = ModelicaBuildAction.getInstance();
		mdtStatus = new ModelicaStatusLineContributor();
	}

	/* (non-Javadoc)
	 * Method declared on EditorActionBarContributor
	 */
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);
		IMenuManager editMenu = menuManager.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			editMenu.add(new Separator());
			editMenu.add(correctIndentationAction);
			editMenu.add(new Separator());
			editMenu.add(buildAction);			
		}
	}

	/* (non-Javadoc)
	 * Method declared on EditorActionBarContributor
	 */
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);
		toolBarManager.add(new Separator());
		toolBarManager.add(correctIndentationAction);
		toolBarManager.add(new Separator());
		toolBarManager.add(buildAction);		
	}

	/* (non-Javadoc)
	 * Method declared on EditorActionBarContributor
	 */
	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);

		ITextEditor editor= null;
		if (part instanceof ITextEditor) {
			editor= (ITextEditor) part;
		}

		correctIndentationAction.setEditor(editor);
		correctIndentationAction.update();
		
		buildAction.setEditor(editor);
		buildAction.update();		
		
		mdtStatus.update();
	}

	@Override
	public void contributeToStatusLine(IStatusLineManager statusLineManager) {
		super.contributeToStatusLine(statusLineManager);
		statusLineManager.add(new Separator());
		statusLineManager.add(mdtStatus);
	}
}
