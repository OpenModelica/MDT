/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */

package org.openmodelica.modelicaml.helper.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.uml.tools.utils.OpaqueBehaviorUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.services.StringUtls;



public class CreatorVerificationVerdictElements {
	
	/** The shell. */
	private static Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	/** The all req evaluated. */
//	private static String allReqEvaluated = "allRequirementsEvaluated";
	private static String allReqEvaluated = Constants.propertyName_allRequirementsEvaluated;
	
	/** The some req violated. */
//	private static String someReqViolated = "someRequirementsViolated";
	private static String someReqViolated = Constants.propertyName_someRequirementsViolated;
	
	/** The some req violated once. */
//	private static String someReqViolatedOnce = "requirementsViolationDetected";
	private static String someReqViolatedOnce = Constants.propertyName_requirementsViolationDetected;
	
	/** The test passed. */
//	private static String testPassed = "testPassed";
	private static String testPassed = Constants.propertyName_testPassed;
	
	/** The results class name. */
//	public static String resultsClassName = "_ReqTestVerdict";
	public static String resultsClassName = Constants.className_requirementsVerificationVerdict;
	
	/** The results property name. */
//	public static String resultsPropertyName = "_reqTestVerdict";
	public static String resultsPropertyName = Constants.propertyName_requirementsVerificationVerdict;
	
	/** The opaque behavior name. */
	private static String opaqueBehaviorName = "// @generated: Test pass/fail defintion code";
	/** The auto generated comment body. */
	private String autoGeneratedCommentBody = "// @generated ";

	
	/**
	 * Instantiates a new test pass fail code generator.
	 * 
	 * @param selectedClass
	 *            the selected class
	 */
	public CreatorVerificationVerdictElements(final Class selectedClass) { 
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		Boolean go = MessageDialog.openQuestion(shell, "Create Verification Verdict Elements", 
				"This helper will create or update the following additional elements in the class '"+ selectedClass.getName()+"': " +
					"\n     - a nested class '"+resultsClassName+"' containing additional variables and behavior"+ 
					"\n     - a component '"+resultsPropertyName+"' of type '"+resultsClassName+"'");
		if (go) {
			//########## storing start
			TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain();
			CompoundCommand cc = new CompoundCommand("Create Verification Verdict Elements");
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					
					Boolean generateVerdict = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "generateVerdict", false, null);
					if (generateVerdict) {
						Boolean result = createVerificationVerdictElements(selectedClass);
						if (result) {
							MessageDialog.openInformation(shell, "Confirmation", "The following verification verdict elements were created or updated succesfuly:" +
									"\n     - a nested class '"+resultsClassName+"' containing additional variables and behavior"+ 
									"\n     - a component '"+resultsPropertyName+"' of type '"+resultsClassName+"'");
						}
					}
				}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			//########## storing end
		}
	}
	
	/**
	 * Creates the test evaluation elements.
	 * 
	 * @param selectedClass
	 *            the selected class
	 */
	public static Boolean createVerificationVerdictElements(Class selectedClass) {
		
		// check preferences 
		Boolean generateVerdict = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "generateVerdict", false, null);
		if (!generateVerdict) {
			return false;
		}
		
		
		String violatedExpression = "", evaluatedExpression = "";
		
		EList<Property> allAttributes = selectedClass.getAllAttributes();
		HashSet<Property> reqInstances = new HashSet<Property>();
		HashSet<Property> invalidReqInstances = new HashSet<Property>();
		
		if (allAttributes.size() > 0 ) {
			for (Property property : allAttributes) {
				Type t = property.getType();
				if (t instanceof Class) {
					Stereotype reqStereotype = t.getAppliedStereotype(Constants.stereotypeQName_Requirement);
					if (reqStereotype != null) {
//						Property violated = ((Class)t).getOwnedAttribute(Constants.propertyName_violated, null);
//						Property evaluated = ((Class)t).getOwnedAttribute(Constants.propertyName_evaluated, null);
						Property mStatus = ((Class)t).getOwnedAttribute(Constants.propertyName_mStatus, null);

//						if ( (violated != null && evaluated != null) || mStatus != null) {
						if ( mStatus != null ) {
//							Type violatedType = violated.getType();
//							Type evaluatedType = evaluated.getType();
							Type mStatusType = mStatus.getType();

//							if (violatedType != null && evaluatedType != null ) {
							if (mStatusType != null) {
							// check if the type is correct
//								if (violatedType.getName().equals(Constants.predefinedTypeName_boolean) && evaluatedType.getName().equals(Constants.predefinedTypeName_boolean)) {
								if (mStatusType.getName().equals(Constants.predefinedTypeName_integer)) {
									reqInstances.add(property);
								}
								else {
									invalidReqInstances.add(property);
								}
							}
							else {
								invalidReqInstances.add(property);
							}
						}
						else {
							invalidReqInstances.add(property);
						}						
					}
				}
			}
			
			for (Property property : reqInstances) {
				//violatedExpression = violatedExpression + Utls.replaceSpecChar(property.getName()) + ".violated, ";
				
				// TODO: use mStatus with 
				violatedExpression = violatedExpression + StringUtls.replaceSpecChar(property.getName()) + "."+Constants.propertyName_mStatus+" == 2" +" or ";
				evaluatedExpression = evaluatedExpression + StringUtls.replaceSpecChar(property.getName()) + "."+Constants.propertyName_mStatus + " > 0" +" and ";

//				violatedExpression = violatedExpression + StringUtls.replaceSpecChar(property.getName()) + "."+Constants.propertyName_violated+" or ";
//				evaluatedExpression = evaluatedExpression + StringUtls.replaceSpecChar(property.getName()) + "."+Constants.propertyName_evaluated+" and ";
			}
		}
		
		if (invalidReqInstances.size() > 0 ) {
			String invalidComponentNames = "";
			for (Property property : invalidReqInstances) {
				invalidComponentNames = invalidComponentNames + "\n                - '" + property.getName() + "'";
			}
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog.openError(shell, "Error", 
					"The following requirements do not have the mandatory attribute '"
					+Constants.propertyName_mStatus+ "' of type 'ModelicaInteger'." +
				invalidComponentNames + 
				"\n\nNo verification verdict was created in '"+selectedClass.getName()+"'.");
			return false;
		}
		else if (reqInstances.size() > 0) {
		
			String evaluatedExpressionString = evaluatedExpression.substring(0, evaluatedExpression.length() - 5);
			//String violatedExpressionString = violatedExpression.substring(0, violatedExpression.length() - 2);
			String violatedExpressionString = violatedExpression.substring(0, violatedExpression.length() - 4);

			Type booleanType = selectedClass.getModel().getAppliedProfile("ModelicaML::ModelicaPredefinedTypes").getOwnedType(Constants.predefinedTypeName_boolean);
			
			// create results class inside the selected class
			Class resultsClass = (Class) selectedClass.getNestedClassifier(resultsClassName, true, UMLPackage.Literals.CLASS, true);
//			Comment c = resultsClass.createOwnedComment();
//			c.setBody("// Auto-generated");
			
			// apply ModelicaML stereotype
			if (resultsClass.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) == null) {
				Stereotype resultsClassStereotype = resultsClass.getApplicableStereotype(Constants.stereotypeQName_CalculationModel);
				resultsClass.applyStereotype(resultsClassStereotype);
			}
			
			// create results class property and behavior code
			Property allReqEvaluatedProperty = resultsClass.getOwnedAttribute(allReqEvaluated, booleanType, true, UMLPackage.Literals.PROPERTY, true);
//			Comment commentAllReqEvaluatedProperty = allReqEvaluatedProperty.createOwnedComment();
//			commentAllReqEvaluatedProperty.setBody(autoGeneratedCommentBody);
			
			Property someReqViolatedProperty = resultsClass.getOwnedAttribute(someReqViolated, booleanType, true, UMLPackage.Literals.PROPERTY, true);
//			Comment commentSomeReqViolatedProperty = someReqViolatedProperty.createOwnedComment();
//			commentSomeReqViolatedProperty.setBody(autoGeneratedCommentBody);
			
			Property someReqViolatedOnceProperty = resultsClass.getOwnedAttribute(someReqViolatedOnce, booleanType, true, UMLPackage.Literals.PROPERTY, true);

			Property testPassedProperty = resultsClass.getOwnedAttribute(testPassed, booleanType, true, UMLPackage.Literals.PROPERTY, true);
//			Comment commentTestPassedProperty = testPassedProperty.createOwnedComment();
//			commentTestPassedProperty.setBody(autoGeneratedCommentBody);
			
			Stereotype variableStereotype = allReqEvaluatedProperty.getApplicableStereotype(Constants.stereotypeQName_Variable);
			
			// apply ModelicaML stereotype to the properties
			if ( allReqEvaluatedProperty.getAppliedStereotype(Constants.stereotypeQName_Variable) == null ) {
				allReqEvaluatedProperty.applyStereotype(variableStereotype);
				//set causality to input
				allReqEvaluatedProperty.setValue(variableStereotype, Constants.propertyName_causality, Constants.propertyName_input);
			}
			if ( someReqViolatedProperty.getAppliedStereotype(Constants.stereotypeQName_Variable) == null ) {
				someReqViolatedProperty.applyStereotype(variableStereotype);
				//set causality to input
				someReqViolatedProperty.setValue(variableStereotype, Constants.propertyName_causality, Constants.propertyName_input);
			}
			if ( someReqViolatedOnceProperty.getAppliedStereotype(Constants.stereotypeQName_Variable) == null ) {
				someReqViolatedOnceProperty.applyStereotype(variableStereotype);
			}
			if (  testPassedProperty.getAppliedStereotype(Constants.stereotypeQName_Variable) == null ) {
				testPassedProperty.applyStereotype(variableStereotype);
				//set causality to input
				testPassedProperty.setValue(variableStereotype, Constants.propertyName_causality, Constants.propertyName_output);
			}
			
			// instantiate results class
			Property resultsProperty = selectedClass.getOwnedAttribute(resultsPropertyName, resultsClass, true, UMLPackage.Literals.PROPERTY, true);
//			Comment commentResultsProperty = resultsProperty.createOwnedComment();
//			commentResultsProperty.setBody(autoGeneratedCommentBody);

			// apply ModelicaML stereotype
			Stereotype resultsPropertyStereotype = resultsProperty.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);
			if (resultsProperty.getAppliedStereotype(Constants.stereotypeQName_CalculatedProperty) == null) {
				resultsProperty.applyStereotype(resultsPropertyStereotype);
			}

			// add the modifications
			List<String> newValue = new ArrayList<String>(); // new modification value
			//newValue.add(allReqEvaluated + " = if (" + evaluatedExpressionString + ") then true else false"); 
			newValue.add(allReqEvaluated + " = " + evaluatedExpressionString); 
			//newValue.add(someReqViolated + " = when {" + violatedExpressionString  + "} then true else false"); 
			//newValue.add(someReqViolated + " = if (" + violatedExpressionString  + ") then true else false"); 
			newValue.add(someReqViolated + " = " + violatedExpressionString); 
			Collections.sort(newValue); // sort the new list
				
			resultsProperty.setValue(resultsPropertyStereotype, Constants.propertyName_modification, newValue); // set the value
			
			// create behavior in the results class
			String bodyString = 
					"when " + someReqViolated + " then \n" +
							"	" + someReqViolatedOnce + " := true; " +
					"\nend when; \n\n" + 
					testPassed +" := " + allReqEvaluated + " and not " + someReqViolatedOnce + ";\n" ;
			
			OpaqueBehavior opaqueBehavior = (OpaqueBehavior) resultsClass.getOwnedBehavior(opaqueBehaviorName, true, UMLPackage.Literals.OPAQUE_BEHAVIOR, true);
//			Comment commentOpaqueBehavior = opaqueBehavior.createOwnedComment();
//			commentOpaqueBehavior.setBody(autoGeneratedCommentBody);
			
			OpaqueBehaviorUtil.setBody(opaqueBehavior, bodyString, "Modelica");
			if (opaqueBehavior.getAppliedStereotype(Constants.stereotypeQName_Algorithm) == null) {
				Stereotype stereotype = opaqueBehavior.getApplicableStereotype(Constants.stereotypeQName_Algorithm);
				opaqueBehavior.applyStereotype(stereotype);
			}
			return true;
		}
		else {
			MessageDialog.openError(shell, "Error", "No test verdict elements were created in '"+selectedClass.getName()+"'. " +
					"because no requirement instance were found. ");
			return false;
		}
	}

	
	/**
	 * Removes the reg test eval elemenents_delete option.
	 * 
	 * @param selectedClass
	 *            the selected class
	 * @param delete
	 *            the delete
	 * @return the boolean
	 */
	public static Boolean removeRegTestEvalElemenents_deleteOption(Class selectedClass, Boolean delete){
		// get the Outputs class
		final Class resultsClass = (Class)selectedClass.getNestedClassifier(resultsClassName, true, UMLPackage.Literals.CLASS, false);
		
		if (resultsClass != null) {
			// get the corresponding property in the Outputs class
			final Property resultsProperty = selectedClass.getOwnedAttribute(resultsPropertyName, resultsClass, true, UMLPackage.Literals.PROPERTY, false);

			if (resultsProperty != null) {
				if (delete) {
					//########## storing start
					TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain();
					CompoundCommand cc = new CompoundCommand("Create Requirements Verification Verdict Elements");
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							resultsProperty.destroy();
							resultsClass.destroy();
						}
					};
					cc.append(command);
					editingDomain.getCommandStack().execute(cc);
					//########## storing end
				}
				return true;
			}
		}
		return false;
	}
}
