package org.modelica.mdt.ui.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelica.mdt.ui.graph.ModelicaGraphView;

/**
 * This is the handler that is called when a user wants to open
 * the graph browser.
 *
 * @author: Magnus Sjöstrand
 */

public class GraphHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.modelica.mdt.ui.graph.ModelicaGraphView");
			ModelicaGraphView.selectionChanged(sel);
		} catch (PartInitException e) {
			e.printStackTrace();
		}	
		return null;
	}
}