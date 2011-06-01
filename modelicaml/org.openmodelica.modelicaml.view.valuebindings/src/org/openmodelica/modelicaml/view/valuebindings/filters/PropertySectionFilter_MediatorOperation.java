package org.openmodelica.modelicaml.view.valuebindings.filters;
import org.eclipse.jface.viewers.IFilter;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;


public class PropertySectionFilter_MediatorOperation implements IFilter {

	@Override
	public boolean select(Object toTest) {
		if (toTest instanceof TreeObject) {
			TreeObject item = (TreeObject)toTest;
			if (item.isValueMediator() && !item.isReadOnly()) {
				return true;
			}
		}
		return false;
	}
}
