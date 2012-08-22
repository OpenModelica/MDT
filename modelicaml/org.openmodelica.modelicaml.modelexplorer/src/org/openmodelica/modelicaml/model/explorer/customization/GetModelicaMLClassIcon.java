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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.StateMachine;
import org.openmodelica.modelicaml.common.constants.Constants;

// TODO: Auto-generated Javadoc
/**
 * returns the icon path.
 */
public class GetModelicaMLClassIcon implements IJavaModelQuery<Class, String> {
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery#evaluate(org.eclipse.emf.ecore.EObject, org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList)
	 */
	public String evaluate(final Class context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {

		if (context instanceof FunctionBehavior) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/FunctionBehavior.gif";
		}
		if (context instanceof OpaqueBehavior) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/OpaqueBehavior.gif";
		}
		if (context instanceof StateMachine) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/StateMachine.gif";
		}
		if (context instanceof Activity) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Activity.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_ModelicaClass) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Class.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_ModelicaPackage) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/package.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Operator) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/operator.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Model) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/model.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Block) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/block.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Connector) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/connector.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Record) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/record.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/calculationModel.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/requirement.gif";
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_ValueMediatorsContainer) != null) {
			return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/valueMediatorsContainer.png";
		}
		
		//return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Class.gif";
		return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/QuestionMark.png";
		//return "/org.openmodelica.modelicaml.profile/resources/icons/icons16/Unknown.png";
		
		//return null; //BUG: Does not work ...
	}
}
