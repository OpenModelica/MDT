
package org.modelica.mdt.ui.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This is the handler that is called when a user wants to open
 * the detailed browser, also called optimization browser, which
 * shows the performed transformations after a simulation.
 *
 * @author: Magnus Sjöstrand
 */

public class DetailedGraphHandler extends AbstractHandler
{
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.modelica.mdt.ui.graph.ModelicaDetailedGraphView");
		} catch (PartInitException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
