package org.openmodelica.modelicaml.traceability.views.model;

import java.util.ArrayList;
import java.util.List;

import org.openmodelica.modelicaml.helper.impl.VeMScenarioReqCombinationsCreator;

public class ClientItem extends TreeParent {
	private boolean isValid = false;
	private List<VeMScenarioReqCombinationsCreator> combinations = new ArrayList<VeMScenarioReqCombinationsCreator>();
	
	public ClientItem(String name) {
		super(name);
	}

	public void addCombination(VeMScenarioReqCombinationsCreator combination) {
		this.combinations.add(combination);
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

}
