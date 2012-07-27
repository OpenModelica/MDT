package org.openmodelica.modelicaml.helper.handlers;

import org.openmodelica.modelicaml.common.constants.Constants;

public class AutomaticScenarioBasedVerificationToolbarHandler extends ScenariosToRequirementsRelationsDiscoveryToolbarHandler{

	
	@Override
	protected void openDialog() {
		super.openDialog(Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION);
	}

}
