package org.openmodelica.modelicaml.view.valuebindings.filters;
import org.eclipse.jface.viewers.IFilter;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;


public class PropertySectionFilter_MediatorPreferredProviders implements IFilter {

	@Override
	public boolean select(Object toTest) {
		if (toTest instanceof TreeObject) {
			TreeObject item = (TreeObject)toTest;
			if (item.isValueMediator() && !item.isReadOnly()) {
				if (TreeUtls.getStringListPropertyFromElement(item.getUmlElement(), Constants.stereotypeQName_ValueMediator, Constants.propertyName_preferredProviders) != null) {
					return true;
				}
			}
		}
		return false;
	}
}
