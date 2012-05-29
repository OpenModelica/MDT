package org.openmodelica.modelicaml.validation.context;

import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.uml2.uml.Element;

public class ValidationDelegateClientSelector implements IClientSelector {


	@Override
	public boolean selects(Object object) {
		return object instanceof Element;
	}
}
