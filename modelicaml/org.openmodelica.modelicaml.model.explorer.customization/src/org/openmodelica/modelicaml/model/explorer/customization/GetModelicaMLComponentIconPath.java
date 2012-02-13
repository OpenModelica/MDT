/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery;
import org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;

// TODO: Auto-generated Javadoc
/**
 * returns the icon path.
 */
public class GetModelicaMLComponentIconPath implements IJavaModelQuery<Property, String> {
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery#evaluate(org.eclipse.emf.ecore.EObject, org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList)
	 */
	public String evaluate(final Property context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		if (context instanceof Port) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Port.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Component) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/component.png";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Variable) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/variable.png";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_ConnectionPort) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Port.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_RequirementInstance) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/requirementInstance.png";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_CalculatedProperty) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/calculatedProperty.png";
		}
		// EXPERINEMTAL
		if (context.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/valueMediator.png";
		}
		// EXPERINEMTAL END
		
		
		
		//return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Property.gif";
		
		return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/QuestionMark.png";
		//return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Unknown.png";
	}
}
