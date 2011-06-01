package org.openmodelica.modelicaml.view.valuebindings.filters;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;


public class PropertySectionFilter_ClientOperation implements IFilter {

	@Override
	public boolean select(Object toTest) {
		if (toTest instanceof TreeObject) {
			TreeObject item = (TreeObject)toTest;
			
//			Element element = item.getUmlElement();
//			if (element != null) {
//				if (element.getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null) {
//					return true;					
//				}
//			}
			
			if (item.isValueClient() && !item.isReadOnly()) {
				return true;
			}
		}
		return false;
	}
}
