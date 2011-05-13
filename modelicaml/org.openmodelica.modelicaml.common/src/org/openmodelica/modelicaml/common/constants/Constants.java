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
	
	// composite constructs
	public static final String stereotypeQName_Variable 		= "ModelicaML::ModelicaCompositeConstructs::Variable";
	public static final String stereotypeQName_FunctionArgument = "ModelicaML::ModelicaCompositeConstructs::FunctionArgument";
	public static final String stereotypeQName_RequirementInstance 		= "ModelicaML::ModelicaRequirementConstructs::RequirementInstance";
	public static final String stereotypeQName_Component = "ModelicaML::ModelicaCompositeConstructs::Component";
	public static final String stereotypeQName_ConnectionPort = "ModelicaML::ModelicaCompositeConstructs::ConnectionPort";
	public static final String stereotypeQName_CalculatedProperty = "ModelicaML::ModelicaCompositeConstructs::CalculatedProperty";
	

	// relations
	public static final String stereotypeQName_ExtendsRelation = "ModelicaML::ModelicaRelationsConstructs::ExtendsRelation";
	public static final String stereotypeQName_TypeRelation 	= "ModelicaML::ModelicaRelationsConstructs::TypeRelation";
	
	// stereotype properties 
	public static final String propertyName_modification 		= "modification";
	public static final String propertyName_causality 			= "causality";
	public static final String propertyName_input 				= "input";
	public static final String propertyName_output 				= "output";

	// primarily used by the value bindings plugin  
	public static final String stereotypeQName_ValueMediatorsContainer 	= "ModelicaML::ValueBinding::ValueMediatorsContainer";
	public static final String stereotypeQName_ValueMediator 			= "ModelicaML::ValueBinding::ValueMediator";
	public static final String stereotypeQName_ValueProvider 			= "ModelicaML::ValueBinding::ValueProvider";
	public static final String stereotypeQName_ValueClient 				= "ModelicaML::ValueBinding::ValueClient";
	
	public static final String stereotypeQName_ValueClient_obtainsValueFrom 	= "obtainsValueFrom";
	public static final String stereotypeQName_ValueProvider_providesValueFor 	= "providesValueFor";
	
	public static final String propertyName_operation 							= "operation";
	
	public static final String valueClientsNodeName 		= "Clients";
	public static final String valueMediatorsNodeName 		= "Mediators";
	public static final String valueProvidersNodeName 		= "Providers";
	
	
	// used by components tree plugin
	public static final String inputsComponentName 			= "_inputs";
	public static final String inputsClassName 				= "_Inputs";
	public static final String outputsComponentName 		= "_outputs";
	public static final String outputsClassName 			= "_Outputs";
	
	
	// used by value binding provider xtext editor
	public static final String MACRO_ownPath 				= "ownPath";
	
	
}
