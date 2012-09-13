
package org.modelica.mdt.ui.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;

public class CreateView extends AbstractHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("exectute");
		Shell shell = HandlerUtil.getActiveShell(event);
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		IStructuredSelection selection = (IStructuredSelection) sel;
		try {
			HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().showView("org.modelica.plugin.cross.graphview");

		} catch (PartInitException e) {
			e.printStackTrace();
		}


		/*
		Object firstElement = selection.getFirstElement();

		if (firstElement instanceof IModelicaElement) {
			MessageDialog.openInformation(shell, "Progress",
					"Creating new window");

			Platform.getExtensionRegistry().getConfigurationElementsFor("org.modelica.plugin.cross.graphview");


		} else {
			MessageDialog.openInformation(shell, "Progress",
					((IModelicaClass) firstElement).getFullName());
		}
		 */
		return null;
	}


}