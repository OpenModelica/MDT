package org.openmodelica.modelicaml.traceability.views.model;

import java.util.ArrayList;
import java.util.List;

import org.openmodelica.modelicaml.helper.structures.VeMScenarioReqCombinationsCreator;

public class RequirementItem extends TreeParent {
	private boolean isValid = false;
	private boolean isUnknown = false;
	private List<VeMScenarioReqCombinationsCreator> combinations = new ArrayList<VeMScenarioReqCombinationsCreator>();
	
	public RequirementItem(String name) {
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

	public void setUnknown(boolean isUnknown) {
		this.isUnknown = isUnknown;
	}

	public boolean isUnknown() {
		return isUnknown;
	}

}
