package org.openmodelica.modelicaml.helper.handlers;

import org.eclipse.swt.widgets.Display;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.helper.dialogs.AutomaticScenarioBasedVerificationReportDialog;

public class AutomaticScenarioBasedVerificationToolbarHandler extends ScenariosToRequirementsRelationsDiscoveryToolbarHandler{

	
	@Override
	protected void openDialog() {
		super.openDialog(Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION);
	}
	
	@Override
	protected void openReport() {
		/*
		 * Open a new dialog that has a tree with scenario and requirement
		 * and one button to create a report XML file. 
		 */
		
		// Use this to open a Shell in the UI thread
 		Display.getDefault().asyncExec(new Runnable() {
 			public void run() {
				// open report dialog 
 				AutomaticScenarioBasedVerificationReportDialog dialog = new AutomaticScenarioBasedVerificationReportDialog(getShell(), gmd);
 				dialog.open();
 			}
 		});
	}
}
