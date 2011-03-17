/*
 * 
 */
package org.openmodelica.simulation.environment.wizard.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.openmodelica.simulation.environment.wizard.newsimulationproject.NewSimulationProjectFromExistingCodeWizard;

// TODO: Auto-generated Javadoc
/**
 * The Class NewFileWizardHandler.
 */
public class NewFileWizardHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		NewSimulationProjectFromExistingCodeWizard wizard = new NewSimulationProjectFromExistingCodeWizard();
		WizardDialog dialog = new WizardDialog(HandlerUtil
				.getActiveShell(event), wizard);
		dialog.open();
		return null;
	}

}
