package org.openmodelica.modelicaml.common.constants;

public class Constants {
	// primarily used by the components tree plugin
	public static final String stereotypeQName_ModelicaClass 	= "ModelicaML::ModelicaClassConstructs::ModelicaClass";
	public static final String stereotypeQName_Model 			= "ModelicaML::ModelicaClassConstructs::Model";
	public static final String stereotypeQName_Block 			= "ModelicaML::ModelicaClassConstructs::Block";
	public static final String stereotypeQName_Connector 		= "ModelicaML::ModelicaClassConstructs::Connector";
	public static final String stereotypeQName_Record 			= "ModelicaML::ModelicaClassConstructs::Record";
	public static final String stereotypeQName_Function 		= "ModelicaML::ModelicaClassConstructs::Function";
	public static final String stereotypeQName_CalculationModel = "ModelicaML::ModelicaClassConstructs::CalculationModel";
	public static final String stereotypeQName_Requirement 		= "ModelicaML::ModelicaRequirementConstructs::Requirement";	
	public static final String stereotypeQName_Simulation 		= "ModelicaML::ModelicaSimulationConstructs::Simulation";
	
	public static final String stereotypeQName_Variable 		= "ModelicaML::ModelicaCompositeConstructs::Variable";
	public static final String stereotypeQName_RequirementInstance 		= "ModelicaML::ModelicaRequirementConstructs::RequirementInstance";
	
	
	// primarily used by the value bindings plugin  
	public static final String stereotypeQName_ValueMediatorsContainer 	= "ModelicaMLTesting::ValueBinding::ValueMediatorsContainer";
	public static final String stereotypeQName_ValueMediator 	= "ModelicaMLTesting::ValueBinding::ValueMediator";
	public static final String stereotypeQName_ValueProvider 	= "ModelicaMLTesting::ValueBinding::ValueProvider";
	public static final String stereotypeQName_ValueClient 		= "ModelicaMLTesting::ValueBinding::ValueClient";
	
	public static final String stereotypeQName_ValueClient_obtainsValueFrom 	= "obtainsValueFrom";
	public static final String stereotypeQName_ValueProvider_providesValueFor 	= "providesValueFor";
	public static final String propertyName_operation 							= "operation";
	
	public static final String valueClientsNodeName 			= "Clients";
	public static final String valueMediatorsNodeName 			= "Mediators";
	public static final String valueProvidersNodeName 			= "Providers";
	
	
	
	public static final String propertyName_modification 		= "modification";
	public static final String propertyName_causality 			= "causality";
	public static final String propertyName_input 				= "input";
}
