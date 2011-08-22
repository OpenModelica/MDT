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
package org.openmodelica.modelicaml.common.constants;

public class Constants {
	
	// class constructs
	public static final String stereotypeQName_ModelicaPackage 	= "ModelicaML::ModelicaClassConstructs::ModelicaPackage";
	public static final String stereotypeQName_Operator 		= "ModelicaML::ModelicaClassConstructs::Operator";
	
	public static final String stereotypeQName_ModelicaClass 	= "ModelicaML::ModelicaClassConstructs::ModelicaClass";
	public static final String stereotypeQName_Model 			= "ModelicaML::ModelicaClassConstructs::Model";
	public static final String stereotypeQName_Block 			= "ModelicaML::ModelicaClassConstructs::Block";
	public static final String stereotypeQName_Connector 		= "ModelicaML::ModelicaClassConstructs::Connector";
	public static final String stereotypeQName_Record 			= "ModelicaML::ModelicaClassConstructs::Record";
	
	public static final String stereotypeQName_Type 			= "ModelicaML::ModelicaClassConstructs::Type";
	public static final String stereotypeQName_ModelicaEnumeration 			= "ModelicaML::ModelicaClassConstructs::ModelicaEnumeration";
	
	public static final String stereotypeQName_Function 		= "ModelicaML::ModelicaClassConstructs::Function";

	public static final String stereotypeQName_CalculationModel = "ModelicaML::ModelicaClassConstructs::CalculationModel";
	
	public static final String stereotypeQName_ModelicaModelReference 	= "ModelicaML::ModelicaClassConstructs::ModelicaModelReference";

	public static final String stereotypeQName_Requirement 		= "ModelicaML::ModelicaRequirementConstructs::Requirement";	

	public static final String stereotypeQName_Simulation 		= "ModelicaML::ModelicaSimulationConstructs::Simulation";
	
	public static final String stereotypeQName_TestScenario 	= "ModelicaML::TestDefinition::TestScenario";
	public static final String stereotypeQName_Test 			= "ModelicaML::TestDefinition::Test";
	public static final String propertyName_usedToVerify 		= "usedToVerify";

	public static final String stereotypeQName_TestSuite 		= "ModelicaML::TestDefinition::TestSuite";
	public static final String propertyName_includes 		= "includes";

	
	
	
	// composite constructs
	public static final String stereotypeQName_Variable 		= "ModelicaML::ModelicaCompositeConstructs::Variable";
	public static final String stereotypeQName_FunctionArgument = "ModelicaML::ModelicaCompositeConstructs::FunctionArgument";
	public static final String stereotypeQName_RequirementInstance 		= "ModelicaML::ModelicaRequirementConstructs::RequirementInstance";
	public static final String stereotypeQName_Component 		= "ModelicaML::ModelicaCompositeConstructs::Component";
	public static final String stereotypeQName_ConnectionPort 	= "ModelicaML::ModelicaCompositeConstructs::ConnectionPort";
	public static final String stereotypeQName_CalculatedProperty = "ModelicaML::ModelicaCompositeConstructs::CalculatedProperty";
	

	// relations
	public static final String stereotypeQName_ExtendsRelation = "ModelicaML::ModelicaRelationsConstructs::ExtendsRelation";
	public static final String stereotypeQName_TypeRelation 	= "ModelicaML::ModelicaRelationsConstructs::TypeRelation";
	public static final String stereotypeQName_ImportRelation		= "ModelicaML::ModelicaRelationsConstructs::Import";
	
	// stereotype properties 
	
	public static final String propertyName_id 					= "id";
	public static final String propertyName_text	 			= "text";
	
	public static final String propertyName_modification 		= "modification";
	public static final String propertyName_causality 			= "causality";
	public static final String propertyName_variability 		= "variability";
	public static final String propertyName_input 				= "input";
	public static final String propertyName_output 				= "output";
	
	// value bindings
	public static final String propertyName_isRequired			= "isRequired";
	

	// primarily used by the value bindings plugin  
	public static final String stereotypeQName_ValueMediatorsContainer 	= "ModelicaML::ValueBinding::ValueMediatorsContainer";
	public static final String stereotypeQName_ValueMediator 			= "ModelicaML::ValueBinding::ValueMediator";
	public static final String stereotypeQName_ValueProvider 			= "ModelicaML::ValueBinding::ValueProvider";
	public static final String stereotypeQName_ValueClient 				= "ModelicaML::ValueBinding::ValueClient";
	
	public static final String stereotypeQName_ValueClient_obtainsValueFrom 	= "obtainsValueFrom";
	public static final String stereotypeQName_ValueProvider_providesValueFor 	= "providesValueFor";

	public static final String stereotypeQName_ObtainsValueFrom 	= "ModelicaML::ValueBinding::ObtainsValueFrom";
	public static final String stereotypeQName_ProvidesValueFor 	= "ModelicaML::ValueBinding::ProvidesValueFor";

	
	public static final String propertyName_operation 				= "operation";
	public static final String propertyName_preferredProviders 		= "preferredProviders";
	public static final String preferredProvidersAssignmentSeparator	= " --> ";
	
	
	public static final String valueClientsNodeName 			= "Clients";
	public static final String potentialCalueClientsNodeName 	= "Potential Clients";
	public static final String valueMediatorsNodeName 		= "Mediators";
	public static final String valueProvidersNodeName 		= "Providers";
	public static final int MODE_ADD_CLIENT 				= 0;
	public static final int MODE_ADD_MEDIATOR 				= 1;
	public static final int MODE_ADD_PROVIDER 				= 2;
	
	
	// primarily used by test models generation plugin
	public static final String stereotypeQName_Requires 			= "ModelicaML::TestDefinition::Requires";
	public static final String propertyName_always	= "always";
	public static final String propertyName_onlyIncombinationWith	= "onlyInCombinationWith";
	
	// used by components tree plugin
	public static final String inputsComponentName 			= "_inputs";
	public static final String inputsClassName 				= "_Inputs";
	public static final String outputsComponentName 		= "_outputs";
	public static final String outputsClassName 			= "_Outputs";
	
	
	// used by value binding provider xtext editor
//	public static final String MACRO_ownPath 				= "ownPath"; // OBSOLETE
	public static final String MACRO_clientPath 			= "clientPath";
	public static final String MACRO_providerPath 			= "providerPath";
	
	public static final String MACRO_getBinding 			= "getBinding";
	public static final String MACRO_getSingleProvider 		= "getSingleProvider";
	public static final String MACRO_toArray 				= "toArray";
	public static final String MACRO_size 					= "size";
	public static final String MACRO_avg 					= "avg";
	public static final String MACRO_AND 					= "AND";
	public static final String MACRO_OR 					= "OR";
	public static final String MACRO_XOR 					= "XOR";
	
	// standard Modelica array reduction functions
	public static final String MACRO_sum 					= "sum";
	public static final String MACRO_product 				= "product";
	public static final String MACRO_min 					= "min";
	public static final String MACRO_max 					= "max";
	
	
	public final static String propertyName_startTime = "startTime";
	public final static String propertyName_stopTime = "stopTime";
	public final static String propertyName_numberOfIntervals = "numberOfIntervals";
	public final static String propertyName_tolerance = "tolerance";
	
	// simulation model generation
	public final static String simModelsPackageNamePrefix = "GenSimModels for: ";
	public final static String simModelsNamePrefix = "SiM for: ";
	public final static String systemModelPropertyNamePrefix = "sm_";
	public final static String additionalModelPrefix = "add_";
	public final static String testScenarioPropertyNamePrefix = "ts_";
	public final static String reqirementPropertyNamePrefix = "req_";
	
}

