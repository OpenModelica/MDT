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
	public static final String modelingLanguageName 				= "ModelicaML";
	public static final String actionLanguageName 					= "Modelica";
	public static final String ModelicaStandardLibraryModelName 	= "Modelica";
	
	// class constructs
	public static final String stereotypeQName_ModelicaPackage 	= "ModelicaML::ModelicaClassConstructs::ModelicaPackage";
	public static final String stereotypeQName_Operator 		= "ModelicaML::ModelicaClassConstructs::Operator";
	
	public static final String stereotypeQName_ModelicaClass 	= "ModelicaML::ModelicaClassConstructs::ModelicaClass";
	public static final String stereotypeQName_Model 			= "ModelicaML::ModelicaClassConstructs::Model";
	public static final String stereotypeQName_Block 			= "ModelicaML::ModelicaClassConstructs::Block";
	public static final String stereotypeQName_Connector 		= "ModelicaML::ModelicaClassConstructs::Connector";
	public static final String stereotypeQName_Record 			= "ModelicaML::ModelicaClassConstructs::Record";
	
	public static final String stereotypeQName_Algorithm 					= "ModelicaML::ModelicaBehaviorConstructs::Algorithm(Code)";
	public static final String stereotypeQName_ConditionalAlgorithm 		= "ModelicaML::ModelicaBehaviorConstructs::ConditionalAlgorithm(Diagram)";
	
	public static final String stereotypeQName_Equations				= "ModelicaML::ModelicaBehaviorConstructs::Equations(Code)";
	public static final String stereotypeQName_ConditionalEquations		= "ModelicaML::ModelicaBehaviorConstructs::ConditionalEquations(Diagram)";
	
	public static final String propertyName_initial				= "initial";
	
	public static final String stereotypeQName_ModelicaMLStateMachine 		= "ModelicaML::ModelicaBehaviorConstructs::ModelicaMLStateMachine";

	
	public static final String stereotypeQName_Annotation 			= "ModelicaML::ModelicaAnnotationConstructs::Annotation";
	public static final String propertyName_fullAnnotationString	= "fullAnnotationString";

	
	public static final String stereotypeQName_Type 						= "ModelicaML::ModelicaClassConstructs::Type";
	public static final String stereotypeQName_ModelicaEnumeration 			= "ModelicaML::ModelicaClassConstructs::ModelicaEnumeration";
	
	public static final String stereotypeQName_Function 					= "ModelicaML::ModelicaClassConstructs::Function";

	public static final String stereotypeQName_CalculationModel 			= "ModelicaML::ModelicaClassConstructs::CalculationModel";
	
	public static final String stereotypeQName_ModelicaModelReference 		= "ModelicaML::ModelReferences::ModelicaModelReference";
	public static final String stereotypeQName_ModelicaModelProxy 			= "ModelicaML::ModelReferences::ModelicaModelProxy";
	public static final String stereotypeQName_InstalledLibrary 			= "ModelicaML::ModelReferences::InstalledLibrary";

	public static final String stereotypeQName_Requirement 					= "ModelicaML::ModelicaRequirementConstructs::Requirement";	

	public static final String stereotypeQName_Simulation 					= "ModelicaML::ModelicaSimulationConstructs::Simulation";
	
	public static final String stereotypeQName_VerificationScenario 		= "ModelicaML::Verification::VerificationScenario";
	public static final String stereotypeQName_VerificationModel 			= "ModelicaML::Verification::VerificationModel";
	public static final String stereotypeQName_UseToVerify 					= "ModelicaML::Verification::UseToVerify";
	@Deprecated
	public static final String propertyName_usedToVerify 					= "usedToVerify"; // DEPRECATED
	public static final String stereotypeQName_DoNotUseToVerify 			= "ModelicaML::Verification::DoNotUseToVerify";
	
	
	// used for verification models generation
	public static final String stereotypeQName_Requires 			= "ModelicaML::Verification::Requires";
	public static final String stereotypeQName_RequiredFor 			= "ModelicaML::Verification::RequiredFor";
	public static final String propertyName_always					= "always";
	public static final String propertyName_onlyIncombinationWith	= "onlyInCombinationWith";
	
	// Code generation
	public static final String stereotypeQName_CodeGenerationSpecification	= "ModelicaML::CodeGeneration::CodeGenerationSpecification";
	public static final String propertyName_excludeFromCG 					= "excludeFromCG";
	
	// composite constructs
	public static final String stereotypeQName_Variable 			= "ModelicaML::ModelicaCompositeConstructs::Variable";
	public static final String stereotypeQName_FunctionArgument 	= "ModelicaML::ModelicaCompositeConstructs::FunctionArgument";
	public static final String stereotypeQName_RequirementInstance 	= "ModelicaML::ModelicaRequirementConstructs::RequirementInstance";
	public static final String stereotypeQName_Component 			= "ModelicaML::ModelicaCompositeConstructs::Component";
	public static final String stereotypeQName_ConnectionPort 		= "ModelicaML::ModelicaCompositeConstructs::ConnectionPort";
	public static final String stereotypeQName_CalculatedProperty 	= "ModelicaML::ModelicaCompositeConstructs::CalculatedProperty";

	public static final String stereotypeQName_Connection 			= "ModelicaML::ModelicaCompositeConstructs::Connection";
	public static final String propertyName_explicitConnectionEnds	= "explicitConnectionEnds";
	
	
	// relations
	public static final String stereotypeQName_ExtendsRelation 	= "ModelicaML::ModelicaRelationsConstructs::ExtendsRelation";
	public static final String stereotypeQName_TypeRelation 	= "ModelicaML::ModelicaRelationsConstructs::TypeRelation";
	public static final String stereotypeQName_ImportRelation	= "ModelicaML::ModelicaRelationsConstructs::Import";
	public static final String stereotypeQName_RedeclareRelation= "ModelicaML::ModelicaRelationsConstructs::RedeclareRelation";
	public static final String stereotypeQName_PartialDerivativeOfFunctionRelation = "ModelicaML::ModelicaRelationsConstructs::PartialDerivativeOfFunctionRelation";
	
	
	public static final String propertyName_alias				= "alias";
	
	
	// stereotype properties 
	public static final String propertyName_id 					= "id";
	public static final String propertyName_text	 			= "text";
	public final static String propertyName_evaluated 			= "evaluated";
	public final static String propertyName_violated 			= "violated";
	public final static String propertyName_mStatus 			= "status"; 	/* Monitoring status: The value meaning depends on the application.
																					For a requirement 0 =not evaluated, 1=evaluated and not violated, 2=evaluated and violated
																			 	*/
	
	public static final String propertyName_encapsulated 		= "encapsulated";
	public static final String propertyName_expandable 			= "expandable";
	
	
	public static final String propertyName_partial 			= "partial";
	
	public static final String propertyName_modification 		= "modification";
	public static final String propertyName_causality 			= "causality";
	public static final String propertyName_final	 			= "final";
	public static final String propertyName_conditionalExpression  			= "conditionalExpression";
	public static final String propertyName_flowFlag  			= "flowFlag";
	public static final String propertyName_scope  				= "scope";
	public static final String propertyName_arraySize 			= "arraySize";
	public static final String propertyName_replaceable	 		= "replaceable";
	public static final String propertyName_variability 		= "variability";
	public static final String propertyName_declarationEquationOrAssignment 		= "declarationEquationOrAssignment";
	
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
	public static final String preferredProvidersAssignmentSeparator	= "##Q##";
	
	public static final String valueClientsNodeName 				= "Clients";
	public static final String potentialClientsNodeName 			= "Potential Clients";
	public static final String valueMediatorsNodeName 				= "Mediators";
	public static final String valueProvidersNodeName 				= "Providers";
	public static final int MODE_ADD_CLIENT 						= 0;
	public static final int MODE_ADD_MEDIATOR 						= 1;
	public static final int MODE_ADD_PROVIDER 						= 2;
	
	// used for verification models generation or discovery of relations between scenarios and requirements
	public final static int MODE_VEM_GENERATION = 3;
	public final static int MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY = 4;
	public final static int MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION = 5;
	
	// used by components tree plugin
	public static final String inputsComponentName 			= "_inputs";
	public static final String inputsClassName 				= "_Inputs";
	public static final String outputsComponentName 		= "_outputs";
	public static final String outputsClassName 			= "_Outputs";
	
	// used by value binding provider xtext editor
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
	public final static String propertyName_solver = "solver";
	public final static String propertyName_outputFormat = "outputFormat";
	
	
	public final static String DEFAULT_startTime = "0";
	public final static String DEFAULT_stopTime = "10";
	public final static String DEFAULT_numberOfIntervals = "500";
	public final static String DEFAULT_tolerance = "0.000001";
	public final static String DEFAULT_solver = "dassl";
	public final static String DEFAULT_outputFormat = "dassl";
	
	
	// simulation and model generation
	public final static String simModelsPackageNamePrefix = "GenVeMs for: ";
	
	public final static String automaticScenarioBasedVerificationPackageNamePrefix = "aGenVeMs for: ";
	public final static String automaticScenarioBasedVerificationModelsNamePrefix = "aVeM for: ";
	
	public final static String relationDiscoveryModelsPackageNamePrefix = "Temp_RelDiscovery using: ";
	public final static String relationDiscoveryModelsNamePrefix = "Temp_RelDiscovery for: ";
	public final static String relationDiscoveryDependeciesPackageName = "GenDiscoveredRelationsDependencies";

	
	public final static String simModelsNamePrefix = "VeM for: ";
	public final static String systemModelPropertyNamePrefix = "sm_";
	public final static String additionalModelPrefix = "add_";
	public final static String verificationScenarioPropertyNamePrefix = "vs_";
	public final static String reqirementPropertyNamePrefix = "req_";
	
	
	// Markers
	public final static String MARKERTYPE_VALUEBINDINGS 					= "org.openmodelica.modelicaml.marker.bindings";
	public final static String MARKERTYPE_COMPONENT_MODIFICATION 			= "org.openmodelica.modelicaml.marker.actionCode.componentmodification";
	public final static String MARKERTYPE_ACTION_CODE 						= "org.openmodelica.modelicaml.marker.actionCode";
	public final static String MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES = "org.openmodelica.modelicaml.marker.modelicamlproxies";
	public final static String MARKERTYPE_MODELICA_MODELS_LOADING 			= "org.openmodelica.modelicaml.marker.modelicaloading";
	public final static String MARKERTYPE_VALIDATION_PROBLEM 				= "org.openmodelica.modelicaml.marker.validation.problem";
//	public final static String MARKERTYPE_VALIDATION 						= "org.openmodelica.modelicaml.marker.validation";
	
	
	
	// verification execution report
	public final static String className_requirementsVerificationVerdict 	= "_ReqVerificationVerdict";
	public final static String propertyName_requirementsVerificationVerdict = "_reqVerificationVerdict";
	public final static String propertyName_testPassed 						= "testPassed";
	public final static String propertyName_allRequirementsEvaluated 		= "allRequirementsEvaluated";
	public final static String propertyName_someRequirementsViolated 		= "someRequirementsViolated";
	public final static String propertyName_requirementsViolationDetected 	= "requirementsViolationDetected";
	

	// verification execution report macro-keywords
	public final static String MACRO_atLeastOneTimeTrue 					= "atLeastOneTimeTrue";
	public final static String MACRO_atLeastOneTimeFalse 					= "atLeastOneTimeFalse";
	public final static String MACRO_alwaysTrue 							= "alwaysTrue";
	public final static String MACRO_alwaysFalse 							= "alwaysFalse";
	public final static String MACRO_changedItsValue 						= "changedItsValue";

	
	public final static String MACRO_hadNotAlwaysValue 						= "hadNotAlwaysValue";
	public final static String MACRO_hadAtLeastOnceValue 					= "hadAtLeastOnceValue";
	public final static String MACRO_hadNeverValue 							= "hadNeverValue";

	
	// code generation
	public final static String folderName_code_gen = "code-gen";
	public final static String folderName_code_sync = "code-sync";
	public final static String folderName_req_sync = "req-sync";
	
	public final static String folderName_relationsDiscovery = "relationsDiscovery-gen";
	public final static String fileName_relationsDiscovery = "relationsDiscovery";

	public final static String folderName_automaticScenarioBasedDesignVerificationDiscovery = "aVerification-gen";
	public final static String fileName_automaticScenarioBasedDesignVerification = "aVerification";
	
	// simulation
	public final static String folderName_sim_gen = "sim-gen";

	
	// Test Execution
	public final static String folderName_verification_gen = "verification-gen";
	public final static String folderName_verification_session = "verification-session";
	public final static String fileName_verification_session = "verification_session";

	
	// predefined type names
	public final static String predefinedTypeName_boolean = "ModelicaBoolean";
	public final static String predefinedTypeName_real = "ModelicaReal";
	public final static String predefinedTypeName_integer = "ModelicaInteger";
	public final static String predefinedTypeName_string = "ModelicaString";
	
	// predefined type qualified names
	public final static String predefinedTypesProfileQName = "ModelicaML::ModelicaPredefinedTypes";
	public final static String predefinedEnumerationsProfileQName = "ModelicaML::ModelicaPredefinedEnumerations";

	
	public final static String predefinedTypeQName_boolean = "ModelicaML::ModelicaPredefinedTypes::ModelicaBoolean";
	public final static String predefinedTypeQName_real = "ModelicaML::ModelicaPredefinedTypes::ModelicaReal";
	public final static String predefinedTypeQName_integer = "ModelicaML::ModelicaPredefinedTypes::ModelicaInteger";
	public final static String predefinedTypeQName_string = "ModelicaML::ModelicaPredefinedTypes::ModelicaString";

	
	
	/** The Constant numberOfItemsInCreateNewElementsDiologs. */
	public final static Integer numberOfItemsInCreateNewElementsDiologs = 5;
	public final static String MODELICAML_PROFILE_URI = "pathmap://PALETTE_PROFILE/ModelicaML.profile.uml"; //$NON-NLS-1$
	
	public final static String VIEW_PAPYRUS_MODELEXPLORER = "org.eclipse.papyrus.views.modelexplorer.modelexplorer";
	public final static String VIEW_COMPONENT_TREE_VIEW = "org.openmodelica.modelicaml.view.componentstree";
	
	/** The Constant ID_VALIDATION_VIEW. */
	public final static String VIEW_VALIDATION = "org.openmodelica.modelicaml.gen.modelica.validation";
	
	
	/*
	 * Encoding format for generated files
	 */
//	public final static String fileEncoding = "Windows-1252";
	public final static String fileEncoding = "Cp1252";
	public static final String encodingPackageFileNameAndExtension = "package.encoding";

	
	public static final String validationKeyWord_MISSING 		= "MISSING";
	public static final String validationKeyWord_NOT_VALID 		= "NOT VALID";
	
	public static final String UKNOWN_NAME 		= "UKNONWN";
	
	public final static String linkDelimiter = "#@#";
	
}

