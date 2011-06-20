package org.openmodelica.modelicaml.view.valuebindings.helpers;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

public class DeriveValueBindingCodeUtls {

	public static boolean isValidClientScript(String scriptCode){
		if (scriptCode != null) {
//			if ( scriptCode.matches("(.+)getBinding\\((\\s+)?\\)(.+)?") && !hasMediatorBindingScriptFunctions(scriptCode)) {
//				return true;
//			}
			if ( scriptCode.contains("getBinding(")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidMediatorMultipleItemsScript(String scriptCode){
		if (scriptCode != null) {
			if ( scriptCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?") && !scriptCode.contains(Constants.MACRO_getSingleProvider) ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidMediatorSingleItemsScript(String scriptCode){
		if (scriptCode != null) {
			if ( !scriptCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?") && scriptCode.contains(Constants.MACRO_getSingleProvider) ) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean hasMediatorBindingScriptFunctions(String scriptCode){
		if (scriptCode != null && !scriptCode.trim().equals("")) {
			// any array reduction function
			if ( scriptCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?")) {
				return true;
			}
			if (scriptCode.contains(Constants.MACRO_getSingleProvider+ "(") ) {
				return true;
			}
		}
		return false;
	}
	
	
	public static String getOperationSpecification(Element element, String stereotypeQName, String propertyName){
		if (element != null) {
			Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
			if (stereotype != null) {
				Object o = element.getValue(stereotype, propertyName);
				if (o instanceof String) {
					if ( !((String)o).trim().equals("") ) {
						return (String)o;
					}
				}
			}
		}			
		return null;
	}
}
