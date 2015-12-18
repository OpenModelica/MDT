package org.modelica.mdt.ui.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelica.mdt.ui.graph.ModelicaGraphView;

public class FindUnusedHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		try
		{
			
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.modelica.mdt.ui.view.ModelicaUnusedPublicFuncionsView");
			
			ModelicaGraphView.selectionChanged(sel);
		} catch (PartInitException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}