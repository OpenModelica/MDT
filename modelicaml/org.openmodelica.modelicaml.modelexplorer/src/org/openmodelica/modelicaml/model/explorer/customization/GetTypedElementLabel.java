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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;

// TODO: Auto-generated Javadoc
/** returns the name, type name, etc. of the typed element */
public class GetTypedElementLabel implements IJavaModelQuery<TypedElement, String> {
	
	/** The Constant variableStereotypeQName. */
	private static final String variableStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::Variable";
	
	/** The Constant functionArgumentStereotypeQName. */
	private static final String functionArgumentStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::FunctionArgument";
	
	/** The Constant portStereotypeQName. */
	private static final String portStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::ConnectionPort";
	
	/** The Constant componentStereotypeQName. */
	private static final String componentStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::Component";
	
	/** The Constant calculatedPropertyStereotypeQName. */
	private static final String calculatedPropertyStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::CalculatedProperty";
	
	/** The Constant requirementInstanceStereotypeQName. */
	private static final String requirementInstanceStereotypeQName = "ModelicaML::ModelicaRequirementConstructs::RequirementInstance";
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery#evaluate(org.eclipse.emf.ecore.EObject, org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList)
	 */
	public String evaluate(final TypedElement context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		String name = context.getName();
		Stereotype stereotype = null;
		
		
		// For components or function arguments
		stereotype = context.getAppliedStereotype(variableStereotypeQName);
		if (stereotype == null) {
			stereotype = context.getAppliedStereotype(functionArgumentStereotypeQName);	
		}
		
		if (stereotype != null) {
			
			String prefixString = "";
			Object flowFlag = context.getValue(stereotype, "flowFlag");
			if (flowFlag != null ) {
				prefixString = prefixString + ( (EnumerationLiteral)flowFlag).getName() + " "; //.substring(0,1) + "/ ";
			}
			Object variability = context.getValue(stereotype, "variability");
			if (variability != null && !((EnumerationLiteral)variability).getName().equals("continuous")) {
				prefixString = prefixString + ((EnumerationLiteral)variability).getName() + " "; //.substring(0,1) + "/ ";
			}
			Object causality = context.getValue(stereotype, "causality");
			if (causality != null ) {
				prefixString = prefixString + ( (EnumerationLiteral)causality).getName() + " "; //.substring(0,1) + "/ ";
			}

			String arraySizeString = "";
			if (hasArraySize(context, stereotype)) {
				arraySizeString = "[..]";
			}
			
			Type type = context.getType();
			if (type != null) {
				//name = type.getName().replaceFirst("Modelica", "") + " " + name;
				name = prefixString + type.getName().replaceFirst("Modelica", "") + arraySizeString + " " + name;
			}
			else {
				name = prefixString + arraySizeString + " " + name;
			}
			
			if (hasModifications(context, stereotype)) {
				name = name + "(..) ";
			}

			
			Object declarationEquationOrAssignment = context.getValue(stereotype, "declarationEquationOrAssignment");
			if (declarationEquationOrAssignment != null && !declarationEquationOrAssignment.toString().trim().equals("")) {
				name = name + " " + declarationEquationOrAssignment.toString().trim();
			}
			Object conditionalExpression = context.getValue(stereotype, "conditionalExpression");
			if (conditionalExpression != null && !conditionalExpression.toString().trim().equals("")) {
				name = name + " " + conditionalExpression.toString().trim();
			}
			
			
		}
		
		
		
		// for Ports 	
		if (stereotype == null) {
			stereotype = context.getAppliedStereotype(portStereotypeQName);	
			if (stereotype != null) {
				String prefixString = "";
//				Object flowFlag = context.getValue(stereotype, "flowFlag");
//				if (flowFlag != null ) {
//					prefixString = prefixString + ( (EnumerationLiteral)flowFlag).getName() + " "; //.substring(0,1) + "/ ";
//				}
//				Object variability = context.getValue(stereotype, "variability");
//				if (variability != null && !((EnumerationLiteral)variability).getName().equals("continuous")) {
//					prefixString = prefixString + ((EnumerationLiteral)variability).getName() + " "; //.substring(0,1) + "/ ";
//				}
				Object causality = context.getValue(stereotype, "causality");
				if (causality != null ) {
					prefixString = prefixString + ( (EnumerationLiteral)causality).getName() + " "; //.substring(0,1) + "/ ";
				}

				if (hasModifications(context, stereotype)) {
					name = name + "(..)";
				}
				
				Type type = context.getType();
//				if (type != null && type instanceof PrimitiveType ) {
				if (type != null) {
					//name = type.getName().replaceFirst("Modelica", "") + " " + name;
					name = prefixString + type.getName().replaceFirst("Modelica", "") + " " + name;
				}
				else {
					name = prefixString + name;
				}
				
//				Object declarationEquationOrAssignment = context.getValue(stereotype, "declarationEquationOrAssignment");
//				if (declarationEquationOrAssignment != null && !declarationEquationOrAssignment.toString().trim().equals("")) {
//					name = name + " " + declarationEquationOrAssignment.toString().trim();
//				}
				Object conditionalExpression = context.getValue(stereotype, "conditionalExpression");
				if (conditionalExpression != null && !conditionalExpression.toString().trim().equals("")) {
					name = name + " " + conditionalExpression.toString().trim();
				}
			}
		}
		
		
		// for other stereotypes
		if (stereotype == null) {
			stereotype = context.getAppliedStereotype(componentStereotypeQName);
			if (stereotype == null) { stereotype = context.getAppliedStereotype(calculatedPropertyStereotypeQName); }
			if (stereotype == null) { stereotype = context.getAppliedStereotype(requirementInstanceStereotypeQName); }
			if (stereotype != null) {
				if (hasModifications(context, stereotype)) {
					name = name + "(..)";
				}
			}
		}
		
		
		
		int maxLength = 150;
		if (name.length() > maxLength) {
			return name.substring(0,maxLength);
		}
		
		return name;
	}
	
	/**
	 * Checks for modifications.
	 *
	 * @param element the element
	 * @param stereotype the stereotype
	 * @return the boolean
	 */
	private Boolean hasModifications(TypedElement element, Stereotype stereotype){
		EList<String> modList = (EList<String>) element.getValue(stereotype, "modification");
		if (modList != null && modList.size() > 0) {
			for (String string : modList) {
				if (!string.trim().equals("")) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks for array size.
	 *
	 * @param element the element
	 * @param stereotype the stereotype
	 * @return the boolean
	 */
	private Boolean hasArraySize(TypedElement element, Stereotype stereotype){
		EList<String> modList = (EList<String>) element.getValue(stereotype, "arraySize");
		if (modList != null && modList.size() > 0) {
			for (String string : modList) {
				if (!string.trim().equals("")) {
					return true;
				}
			}
		}
		return false;
	}
	
//	private String getModelicaMLProperties(TypedElement context){
//		String string = "";
//		Stereotype stereotype = null;
//		
//		stereotype = context.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
//		if (stereotype != null) {
//			Object variability = context.getValue(stereotype, "variability");
//			if (variability != null && !variability.toString().trim().equals("continuous")) {
//				string = string + " " + variability.toString().trim();
//			}
//			
//			Object declarationEquationOrAssignment = context.getValue(stereotype, "declarationEquationOrAssignment");
//			if (declarationEquationOrAssignment != null && !declarationEquationOrAssignment.toString().trim().equals("")) {
//				string = string + " " + declarationEquationOrAssignment.toString().trim();
//			}
//			Object conditionalExpression = context.getValue(stereotype, "conditionalExpression");
//			if (conditionalExpression != null && !conditionalExpression.toString().trim().equals("")) {
//				string = string + " " + conditionalExpression.toString().trim();
//			}
//			
//		}
//		return string;
//	}
}
