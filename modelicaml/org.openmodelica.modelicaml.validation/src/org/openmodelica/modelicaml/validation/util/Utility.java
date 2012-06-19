/**
 * 
 */
package org.openmodelica.modelicaml.validation.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * @author Goutham Gatla
 *
 */
public class Utility {
	
	static List<IStatus> statusList = new ArrayList<IStatus>();

	/**
	 * 
	 * Check Whether the give class have applied ModelicaML Stereotype or not.
	 */
	public static boolean isElementHaveModelicaMLStereotypeApplied(Element element) {
		
		List<Stereotype> classStereotypeList = element.getAppliedStereotypes();

		for (Stereotype stereotype : classStereotypeList) {

			if (stereotype.getQualifiedName().startsWith(Constants.modelingLanguageName + "::")) {
				return true;
			}
		}
		return false;
	}
	
}
