package org.openmodelica.modelicaml.common.services;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

public class ModelicaMLServices {

	public static String getRequirementID(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_id);
				return o.toString();
			}
		}
		return "";
	}
	
	public static String getRequirementText(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_text);
				return o.toString();
			}
		}
		return "";
	}
	
}
