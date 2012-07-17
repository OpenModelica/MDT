package org.openmodelica.modelicaml.helper.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.dialogs.SelectScenariosAndRequirementsDialog;

public class VeMGeneratorScenariosBased extends Observable implements IRunnableWithProgress {
	
	/*
	 * Possible combinations, each containing an initial set (1 system model, 1 scenario and n requirements) and 
	 * all additional model that are required by any of the initial set models. 
	 */
	private HashMap<Element, VeMScenarioReqCombinationsCreator> scenarioToVerificationModelCombination = new HashMap<Element, VeMScenarioReqCombinationsCreator>();

	// the selected model to generate the simulation models for
	private HashSet<Element> systemModels = null;

	// the package for simulation models
	private Element targetPackage = null;
	
	// the package containing the requirements to be used
	private Element requirementsPackage = null;
	// the package containing the scenarios to be used
	private Element scenariosPackage = null;
	// the package containing the value bindings to be used
	private Element bindingsPackage = null;
	
	// prepared instantiations of model in order to avoid instantiating models several times
	private HashMap<Element, TreeParent> preparedModelInstantiations = new HashMap<Element, TreeParent>();
	
	// all created packages that contain generated models
	private HashSet<Element> generatedPackages = new HashSet<Element>();
	
	// all generated models
//	private List<Element> generatedModels = new ArrayList<Element>();
	
	// super class that each generated model should inherit from
	private Element superClass = null;
	
	private VerificationScenariosCollector vsc;

	// all requirements that were collected from the specified requirements package
	private HashSet<Element> allRequirements = new HashSet<Element>();
	// all test scenarios that were collected from the specified test scenarios package
	private HashSet<Element> allTestScenarios = new HashSet<Element>();
	
	// all mediators collected from the specified bindings package
	private HashSet<Element> allMediators = new HashSet<Element>();
	
	// all test scenarios that were selected for the selected system model
	private HashSet<Element> testScenariosToBeInstantiated = new HashSet<Element>();
	// all test scenarios that discarded because they cannot be used for stimulating the selected system model.
	private HashSet<Element> testScenariosDiscarded = new HashSet<Element>();
	
	// the set of test scenario after user selection
	private HashSet<Element> userSelectedTestScenarios = new HashSet<Element>();
	
	
	// For each requirement: All scenarios that have positive (<<UseToVerify>>) relations to the requirement
	/*
	 * This information is used in the "automatic mode" to check if a requirement has scenario candidates 
	 * and does not need to be considered with other scenarios
	 */
	private HashMap<Element, HashSet<Element>> requirementToScenarioCandidate = new HashMap<Element, HashSet<Element>>();
	
	/* all requirements that were selected for instantiation because 
	 * the selected test scenarios reference them and all their clients are satisfied by the selected system model
	 */
	private HashSet<Element> requirementsToBeInstantiated = new HashSet<Element>();
	/* all requirements that were discarded because  
	 * some of their clients are NOT satisfied by the selected system model.
	 */
	private HashSet<Element> requirementsDiscarded = new HashSet<Element>();
	
	// messages collected during translation
	private String log = "";
	
	// indicated if the generation was canceled by the user.
	private boolean canceled = false;
	
	
	// requirements selection options - default settings
	// include requirements that are referenced by scenarios relations stereotyped with <<UsedToVerify>>
	private boolean includeRequirementsWithPositiveRelations = true;
	// include requirements that are referenced by scenarios relations stereotyped with <<DoNotUseToVerify>>
	private boolean includeRequirementsWithNegativeRelations = false;
	// include all requirements that are not referenced by scenarios at all
	private boolean includeRequirementsWitnUnknownRelations = false;
	
	// indicates if there was at at least one model for which bindings could not be generated 
	private boolean bindingErrorsDetected = false;
	private HashSet<Element> modelsWithBindingErrors = new HashSet<Element>();
	
	// indicates if this generator is used for discovery of relations or verification models generation
	private int mode;
	
	
	// Constructor ****************************************************************************************************************
	
	
	public VeMGeneratorScenariosBased(
			HashSet<Element> sourceModels,
			Element targetPackage, 
			Element requirementsPackage,
			Element testScenariosPackage, 
			Element valueMediatorsPackage,
			Element superClass,
			boolean includeRequirementsWithPositiveRelations,
			boolean includeRequirementsWithNegativeRelations,
			boolean includeRequirementsWitnUnknownRelations,
			int mode) {
		
		super();
		
		this.systemModels = sourceModels;
		this.targetPackage = targetPackage;
		this.requirementsPackage = requirementsPackage;
		this.scenariosPackage = testScenariosPackage;
		this.bindingsPackage = valueMediatorsPackage;
		this.superClass = superClass;
		
		this.mode = mode;
		
		// options
		this.includeRequirementsWithPositiveRelations = includeRequirementsWithPositiveRelations;
		this.includeRequirementsWithNegativeRelations = includeRequirementsWithNegativeRelations;
		this.includeRequirementsWitnUnknownRelations = includeRequirementsWitnUnknownRelations;
		
		collectRequirementsAndScenarios();
	}
	
	

	public VeMGeneratorScenariosBased() {
		super();
		
		collectRequirementsAndScenarios();
	}
	
	
	
	
	
	
	
	private Model getRootModel(HashSet<Element> selectedModels){
		/*
		 * TODO: how handle that if multiple design models are selected?
		 */
		Model rootModel = null;
		for (Element selectedModel : selectedModels) {
			Element root = selectedModel.getModel();
			if (root != null) {
				rootModel = selectedModel.getModel();
			}
		}
		return rootModel;
	}
	
	
	// Combination Generation *******************************************************************************************
	
//	private void prepareInstantiations(HashSet<Element> newModels){
//		preparedModelInstantiations.putAll(Utls.getModelInstantiations(newModels, preparedModelInstantiations));
//	}
	
	private void collectRequirementsAndScenarios(){

//		/* Collect all requirements in order to be able to determine 
//		 * which are not covered by simulation models.
//		 */
//		ElementsCollector ec = new ElementsCollector();
//		ec.collectElementsFromModel(requirementsPackage, Constants.stereotypeQName_Requirement);
//		allRequirements.addAll(ec.getElements());
		
//		vsc = new VerificationScenariosCollector();
//		vsc.collectScenariosFromPackage((Package) scenariosPackage, true);
		
		// collect scenarios and requirements
		vsc = new VerificationScenariosCollector( getRootModel(this.systemModels), requirementsPackage, scenariosPackage, bindingsPackage);
		vsc.collectAll(true);
		
		if (vsc.getAllScenarios().size() == 0) {
			String message = "INFO: No verification scenarios were found.";
			addToLog(message);
		}

		/* Collect all requirements in order to be able to determine 
		 * which are not covered by simulation models.
		 */
		allRequirements.addAll(vsc.getAllRequirements());

		/* 
		 * Collect all mediators to be passed in order to avoid 
		 * several collection runs  
		 */
		allMediators.addAll(vsc.getAllMediators());
		
		
		if (mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			/*
			 * Get all requirements and fill the requirements -> scenario candidate map
			 * This information is used in the "automatic mode" to check if a requirement has scenario candidates 
			 * and does not need to be considered with other scenarios. It implies that a requirement is only included
			 * into a model with a certain scenario if and only if this scenario has an 
			 * explicitly defined relation (i.e. <<UseToVerify>> relations) to this requirement.
			 */
			for (Element scenario : vsc.getAllScenarios()) {
				HashSet<Element> reqWithPositiveRelations = getRequirements(scenario, Constants.stereotypeQName_UsedToVerify);
				for (Element requirement : reqWithPositiveRelations) {
					addToMap(requirementToScenarioCandidate, requirement, scenario);
				}
			}
		}
	}
	
	
	
	public void generate(){
		
		if (systemModels != null) {
			
			/* For each of the selected system models create a package containing classes that 
			 * instantiate all possible combinations of test scenarios and requirements that 
			 * can be tested using the given test scenario.
			 */
			for (Element systemModel : systemModels) {

				// clear all lists because the translation for each source model is individual.
				clearAllLists();
				
				Shell shell = getShell();
				
				try {
					new ProgressMonitorDialog(shell).run(true, true, this);

					// create combinations
					createCombinationsForSimulationModels(systemModel);

				} catch (InvocationTargetException e) {
					e.printStackTrace();
					MessageDialog.openError(shell, "Simulation Models Generation Process Error", "It was not possible to invoke the generation of models.");
				} catch (InterruptedException e) {
					e.printStackTrace();
					MessageDialog.openError(shell, "Simulation Models Generation Process Abort", "The generation of models was canceled.");
				}
				
				// generate simulation models for the source model 
				generateSimulationModels(systemModel);

			}
		}
	}
	

	
	private void createCombinationsForSimulationModels(Element sourceModel){
		
		monitorText1 = "Collecting data ...";
		monitorText2 = "Instantiating models and analyzing combinations ...";
		
//		for (Element sourceModel : this.systemModels) {
			
			if (sourceModel instanceof Class) {
				
				Class systemModel = (Class) sourceModel;
				// prepare instantiation so that it can be reused for other iterations
				preparedModelInstantiations.put(systemModel, ModelicaMLServices.getModelInstantiation(systemModel, preparedModelInstantiations));
				
				for (Element testScenario : vsc.getAllScenarios() ) {
					
					if (testScenario instanceof Class) {
						
						Class scenarioToBeUsed = (Class) testScenario;
						// prepare instantiation so that it can be reused for other iterations
						preparedModelInstantiations.put(scenarioToBeUsed, ModelicaMLServices.getModelInstantiation(scenarioToBeUsed, preparedModelInstantiations));


						// get requirements according to the requirements selection options settings
						HashSet<Element> reqWithPositiveRelations = getRequirements(scenarioToBeUsed, Constants.stereotypeQName_UsedToVerify);
						HashSet<Element> reqWithNegativeRelations = getRequirements(scenarioToBeUsed, Constants.stereotypeQName_DoNotUseToVerify);
						HashSet<Element> reqAll = getAllRequirements();

						/*
						 * NOTE: <<DoNotUseToVerify>> has higher priority, i.e. if there are <<UsedToVerify>> (positive) 
						 * and <<DoNotUseToVerify>> (negative) to the same requirement 
						 * then this requirement is excluded from the <<UseToVerify>> (positive) list
						 */
						if (!includeRequirementsWithNegativeRelations) {
							reqWithPositiveRelations.removeAll(reqWithNegativeRelations);
						}
						
						
						HashSet<Element> reqCollection = new HashSet<Element>();
						/*
						 * All requirements that were found, 
						 * i.e. requirements that do not have references from scenarios indicating if this 
						 * a scenario should or should not be used to verify this requirements
						 */
						if (includeRequirementsWitnUnknownRelations) {
							reqCollection.addAll(reqAll);
							
							// removed requirements with known relations if the options are selected 
							if (!includeRequirementsWithNegativeRelations) {
								reqCollection.removeAll(reqWithNegativeRelations);
							}
							if (!includeRequirementsWithPositiveRelations) {
								reqCollection.removeAll(reqWithPositiveRelations);
							}
						}
						else {
							/*
							 * Only requirements with positive or negative relations, 
							 * i.e. relations from scenarios to requirements indicating that 
							 * a scenario should or should not not be used to verify this requirements.
							 */
							if (includeRequirementsWithPositiveRelations) {
								reqCollection.addAll(reqWithPositiveRelations);
							}
							if (includeRequirementsWithNegativeRelations) {
								reqCollection.addAll(reqWithNegativeRelations);
							}
						}
						
						HashSet<Element> requirementsToBeUsed = new HashSet<Element>();
						if (reqCollection != null && reqCollection.size() > 0) {
							 for (Element requirement : reqCollection) {
								if (requirement instanceof Class) {
									
									if (mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
										/*
										 * The "MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION" implies that a requirement is only included
										 * into a model with a certain scenario if and only if this scenario has an 
										 * explicitly defined relation (i.e. <<UseToVerify>> relations) to this requirement.
										 */
										HashSet<Element> scenarioCandidates = requirementToScenarioCandidate.get(requirement);
										if (scenarioCandidates != null) {
											
											// if this scenario references this requirement then combine them!
											if ( scenarioCandidates.contains(scenarioToBeUsed) ) {
												requirementsToBeUsed.add(requirement);
											}
											/*
											 * if this scenario does not reference this requirements
											 * but there are other scenarios that reference this requirement
											 * -> do not combine this scenario with this requirement
											 */
											else if (scenarioCandidates.size() > 0 ) {
												String candidateNames = "";
												for (Element scenario : scenarioCandidates) {
													candidateNames += "              - " + ModelicaMLServices.getQualifiedName(scenario) + "\n"; 
												}
												String message = "DISCARDED: Requirement '" + ModelicaMLServices.getQualifiedName(requirement) + "' was not combined with the scenrio" +
														"'" + scenarioToBeUsed.getQualifiedName() + "' because it has other candidates: \n" + candidateNames ;
												addToLog(message);
											}

											/*
											 * if this scenario does not reference this requirements
											 * and there are no other scenarios that reference this requirement
											 * -> combine this scenario with this requirement in order to try this combination!
											 */
											else {
												requirementsToBeUsed.add(requirement);
											}
										}
										else { // if there are no scenario candidates at all -> try this requirement with this scenario
											requirementsToBeUsed.add(requirement);
										}
										
										/*
										 * In this mode also remove a requirement if there is a negative (<<DoNotUsetoVerify>>) 
										 * relation
										 */
										if (reqWithNegativeRelations.contains(requirement)) {
											requirementsToBeUsed.remove(requirement);
										}
									}
									
									
									else { // in other modes: try this requirement with this scenario
										requirementsToBeUsed.add((Class) requirement);
									}
								}
								else {
									String message = "NOT VALID: Requirement '" + requirement.toString() + "' is not a UML::Class";
									addToLog(message);
								}
							}
						}
						else {
							String message = "INFO: No requirements are referenced by the scenario '" + scenarioToBeUsed.getQualifiedName() + "'";
							addToLog(message);
						}
						
						
						// prepare instantiation so that it can be reused for other iterations
						preparedModelInstantiations.putAll(ModelicaMLServices.getModelInstantiations(requirementsToBeUsed, preparedModelInstantiations));
						
						preparedModelInstantiations.putAll(ModelicaMLServices.getModelInstantiations(vsc.getAlwaysInclude(), preparedModelInstantiations));
//						HashSet<Element> addidtionalModels = new HashSet<Element>();
//						for (Element addModel : vsc.getModelToItsAdditionalModels().keySet()) {
//							addidtionalModels.addAll(vsc.getModelToItsAdditionalModels().get(addModel));
//						}
//						preparedModelInstantiations.putAll(ModelicaMLServices.getModelInstantiations(addidtionalModels, preparedModelInstantiations));

						
						
						VeMScenarioReqCombinationsCreator tsmc = new VeMScenarioReqCombinationsCreator(systemModel, 
								scenarioToBeUsed, 
								requirementsToBeUsed,
								(Package) bindingsPackage,
								vsc.getAlwaysInclude(),
								vsc.getModelToItsAdditionalModels(),
								preparedModelInstantiations);
						
						
						
						// add to map
						scenarioToVerificationModelCombination.put(scenarioToBeUsed, tsmc);
						
						// add to selected or discarded test scenarios
						if (!tsmc.isDiscarded()) {
							testScenariosToBeInstantiated.add(scenarioToBeUsed);
						}
						else {
							testScenariosDiscarded.add(scenarioToBeUsed);
						}
						
						// add to selected or discarded requirements
						HashSet<Element> requirements = tsmc.getRequirements();
						for (Element requirement : requirements) {
							if (tsmc.getUnsatisfiedRequiredClients(requirement) != null) {
								requirementsDiscarded.add(requirement);
							}
							else {
								requirementsToBeInstantiated.add(requirement);
							}
						}

						// add log messages
						addToLog(tsmc.getLog().trim());
					}
					else {
						String message = "NOT VALID: Scenario '" + sourceModel.toString() + "' is not a UML Class.";
						addToLog(message);
					}
				}
			}
			else {
				String message = "NOT VALID: System model '" + sourceModel.toString() + "' is not a UML Class.";
				addToLog(message);
			}
//		}
	}
	
	
	
	private void generateSimulationModels(Element sourceModel){
		
		String errorTitle = "Verification Models Generation Helper";
		String errorMessage = "No scenarios were found that can be used to stimulate the model " +
				"'"+((NamedElement)sourceModel).getName()+"'.";

		if (vsc.getAllScenarios().size() > 0) {
			if (testScenariosToBeInstantiated.size() > 0) {
				// Create test simulation models
				createSimulationModels(sourceModel);
			}
			else {
				reportError(errorTitle, errorMessage);
			}
		}
		else {
			reportError(errorTitle, errorMessage);
		}
	}
	
	
	
	/*
	 * Models Creation *********************************************************************************************************
	 */
	
	
	public void createSimulationModels(Element sourceModel){
		
		/* GUI to show the collected and discarded test scenarios and requirements.
		 * It should enable a selection of test scenarios and requirements to be finally instantiated. 
		 */

		Shell shell = getShell();

		SelectScenariosAndRequirementsDialog dialog = new SelectScenariosAndRequirementsDialog(
				shell, 
				testScenariosToBeInstantiated, 
				testScenariosDiscarded, 
				requirementsToBeInstantiated, 
				requirementsDiscarded, 
				sourceModel,
				vsc,
				getLog(),
				scenarioToVerificationModelCombination,
				includeRequirementsWithPositiveRelations,
				includeRequirementsWithNegativeRelations,
				includeRequirementsWitnUnknownRelations,
				mode);
		
		dialog.open();
		
		if (dialog.getReturnCode() == 1) { // Cancel return code
			setTestSimulationModelGenerationCanceled(true); // indicate abort and stop here. 
		}
		else {
			// Get the selected test scenarios and requirements from the dialog.  
			HashMap<Element,HashSet<Element>> userSelectedTestScenariosAndRequirements = dialog.getSelectedTestScenariosWithRequirements();
			userSelectedTestScenarios.addAll(userSelectedTestScenariosAndRequirements.keySet());
			
			if (userSelectedTestScenarios.size() > 0) {
				// clear log. the tsmc objects still have all logs stored...
				// create a new (final) log using addToLog(). 
				clearLog();
				
				monitorText1 = "Preparing models to be created ...";
				monitorText2 = "Creating models...";

				String pkgName = Constants.simModelsPackageNamePrefix + ((NamedElement)sourceModel).getName();
				if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY) {
					pkgName = Constants.relationDiscoveryModelsPackageNamePrefix + ((NamedElement)sourceModel).getName();
				}
				String postFix = ModelicaMLServices.getNamePostFix((Package)targetPackage, pkgName);
				PackageableElement simulationModelsPackage = ((Package)targetPackage).createPackagedElement(pkgName + postFix,UMLPackage.Literals.PACKAGE);
			
				generatedPackages.add(simulationModelsPackage);
				
				/*
				 * NOTE: only scenarios from user selection are taken into account.
				 * Any other combination that was prepared in advance is discarded.
				 */
				List<Element> userSelectedTestScenariosSorted = ModelicaMLServices.getSortedByName(userSelectedTestScenarios);
				
				// reset the indication of if bindings could not be generated
				setBindingErrorsDetected(false);
				
				/*
				 * iterate over scenarios and generate for each valid combination a model
				 */
				for (Element testScenario : userSelectedTestScenariosSorted) {
					
					boolean scenarioHasRequirements = false;
					HashSet<Element> scenarioRequirements = userSelectedTestScenariosAndRequirements.get(testScenario);
					if (scenarioRequirements != null && scenarioRequirements.size() > 0) {
						scenarioHasRequirements = true;
					}
					
					if (testScenario instanceof Classifier && scenarioHasRequirements) {
						
						// create the verification (simulation) model class
						String simulationModelName = Constants.simModelsNamePrefix + ((NamedElement)testScenario).getName();
						if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY) {
							simulationModelName = Constants.relationDiscoveryModelsNamePrefix + ((NamedElement)testScenario).getName();
						}

						Class simulationModel = ((Package)simulationModelsPackage).createOwnedClass(simulationModelName, false);
						
						// create extends relation if specified
						if (superClass instanceof Class) {
							Generalization extendsRelation = simulationModel.createGeneralization((Classifier) superClass);
							if (extendsRelation != null) {
								Stereotype stereotypeExtends = extendsRelation.getApplicableStereotype(Constants.stereotypeQName_ExtendsRelation);
								if (stereotypeExtends != null) {
									extendsRelation.applyStereotype(stereotypeExtends);
								}
							}
						}
						
						/*
						 *  Copy the simulation settings 
						 *  from the VerificationScenario stereotype to the Simulation (or VerificationModel) stereotype 
						 */
						Stereotype s_model = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Model);
						Stereotype s_simulation = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Simulation);
						Stereotype s_test = simulationModel.getApplicableStereotype(Constants.stereotypeQName_VerificationModel);
						if (s_model != null && s_simulation != null) {
							simulationModel.applyStereotype(s_model);
							simulationModel.applyStereotype(s_simulation);
							simulationModel.applyStereotype(s_test);
							
							// Copy the simulation settings from test scenario to the test model.
							copySimulationSettings((Class) testScenario, simulationModel);
						}
						else {
							String msg = "ERROR: Cannot apply the stereotype 'Model' and 'Simulation' to '" 
								+ simulationModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
							addToLog(msg);
						}

						//************************************************************************************
						// Get and adopt the data from the test scenario simulation model combination object
						VeMScenarioReqCombinationsCreator tsmc = scenarioToVerificationModelCombination.get(testScenario);
						
						//************************************************************************************
						// Remove requirements that were unselected
						if (userSelectedTestScenariosAndRequirements.get(testScenario) != null) {
							// remove all that were unselected by user
							tsmc.removeNotUsedRequirements(userSelectedTestScenariosAndRequirements.get(testScenario));
						}
						else {
							// remove all requirements
							tsmc.removeNotUsedRequirements(new HashSet<Element>());
						}
						
						//************************************************************************************
						// add system model property
						Element systemModel = tsmc.getSystemModel();
						Property p_systemModel = simulationModel.createOwnedAttribute(
								Constants.systemModelPropertyNamePrefix 
								+ StringUtls.replaceSpecChar(((NamedElement)sourceModel).getName().toLowerCase()), 
								(Type)systemModel);
						Stereotype s_component = p_systemModel.getApplicableStereotype(Constants.stereotypeQName_Component);
						if (s_component != null) {
							p_systemModel.applyStereotype(s_component);
						}
						else {
							String msg = "ERROR: Cannot apply the stereotype 'Component' to '" 
										+ p_systemModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
							addToLog(msg);
						}

						/*
						 * Add additional models that are required by the system model.
						 * Hereby, prune all that are not used, i.e. those that don't have at least
						 * one provider used in the combination.
						 */
						
						createAdditionalModels(simulationModel, tsmc.getAdditionalSystemModelModels(true), 
								Constants.systemModelPropertyNamePrefix + Constants.additionalModelPrefix);
						
						
						//************************************************************************************
						// add scenario property
						Property p_testScenario = simulationModel.createOwnedAttribute(
								Constants.verificationScenarioPropertyNamePrefix 
								+ StringUtls.replaceSpecChar(((NamedElement)testScenario).getName().toLowerCase()), 
								(Type)testScenario);
						Stereotype s_testScenario = p_testScenario.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);
						if (s_testScenario != null) {
							p_testScenario.applyStereotype(s_testScenario);
						}
						else {
							String msg = "ERROR: Cannot apply the stereotype 'CalculatedProperty' to '" + p_testScenario.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
							addToLog(msg);
						}
						
						/*
						 * Add additional models that are required by the test scenario.
						 * Hereby, prune all that are not used, i.e. those that don't have at least
						 * one provider used in the combination.
						 */
						createAdditionalModels(simulationModel, tsmc.getAdditionalTestScenarioModels(true), 
								Constants.verificationScenarioPropertyNamePrefix + Constants.additionalModelPrefix);
						
						//************************************************************************************
						// add requirements
						RequirementsInstantiator ri = new RequirementsInstantiator();
						HashSet<Element> reqList = new HashSet<Element>();
						reqList.addAll(tsmc.getRequirements());
						List<Element> requirements_sorted = ModelicaMLServices.getSortedByName(reqList);
						
						for (Element requirement : requirements_sorted) {
							if (requirement instanceof Classifier) {
							
								// get the number of required instantiations
								int requiredNumberOfInstantions = ri.getMaxNumberOfProviders(simulationModel, (Class) requirement);
								String requiredNumberOfInstantionsString = "";
								
								for (int i = 0; i < requiredNumberOfInstantions; i++) {
								
									if (requiredNumberOfInstantions > 1) {
										requiredNumberOfInstantionsString = String.valueOf(i) + "_";
									}
									Property p_req = simulationModel.createOwnedAttribute(
											Constants.reqirementPropertyNamePrefix + 
											ModelicaMLServices.getRequirementID((NamedElement) requirement) + "_" + 
											requiredNumberOfInstantionsString +
											StringUtls.replaceSpecChar(((NamedElement)requirement).getName().toLowerCase()), 
											(Type) requirement);
									
									Stereotype s_reqInst = p_req.getApplicableStereotype(Constants.stereotypeQName_RequirementInstance);
									
									if (s_reqInst != null) {
										p_req.applyStereotype(s_reqInst);
									}
									else {
										String msg = "ERROR: Cannot apply the stereotype 'requirementInstantance' to '" + p_req.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
										addToLog(msg);
									}
								}
								
								/*
								 * Add additional models that are required by the requirement model.
								 * Hereby, prune all that are not used, i.e. those that don't have at least
								 * one provider used in the combination.
								 */
								createAdditionalModels(simulationModel, 
										tsmc.getAdditionalRequirementModels(requirement, true), 
										Constants.reqirementPropertyNamePrefix
											+ ModelicaMLServices.getRequirementID((NamedElement) requirement) 
											+ "_" + Constants.additionalModelPrefix
										);
							}
						}
						
						// add to log
						addToLog("\n" + tsmc.getLog());
						
						/* 
						 * Instantiate the created simulation model class
						 * (it now contains all components)
						 */
						ClassInstantiation ci = new ClassInstantiation((Class) simulationModel, true);
						ci.createTree();
						
						// update all bindings in the created simulation model class
						ValueBindingCreator vbc = new ValueBindingCreator();
						// pass the mediators that were already collected
						vbc.setAllMediators(vsc.getAllMediators());
						
						/* Note, the updateAllBindings() is called with the last argument simulateOnly = false  
						 * so that modifications ARE created in components.
						 */
						vbc.updateAllBindings((Package)bindingsPackage, ci.getTreeRoot(), ci.getTreeRoot(), false, true, false, false);

						// add to instantiations. May be useful for for later analysis of generated models
						preparedModelInstantiations.put(simulationModel, ci.getTreeRoot());
						
						/*
						 * Determine if there were models for which no correct bindings could be generated
						 */
						HashSet<TreeObject> allMandClients = vbc.getAllRequiredClientsFound();
						HashSet<TreeObject> allMandClientsWithPossibleBindins = vbc.getAllClientsWithPossibleBindingCodeDerivation();
						
						if (!allMandClientsWithPossibleBindins.containsAll(allMandClients)) {
							setBindingErrorsDetected(true);
							modelsWithBindingErrors.add(simulationModel);
						}
						
						/*
						 * Create verdict code
						 */
						VerificationVerdictElementsGenerator.createVerificationVerdictElements(simulationModel);
						
					}
				}
			}
			else {
				setTestSimulationModelGenerationCanceled(true); // indicate abort and stop here. 
			}
		}
	}
	
	private void createAdditionalModels(Class owningClass, HashSet<Element> additionalModels, String namePrefix){
		if (additionalModels != null && additionalModels.size() > 0) {
			List<Element> addtitionalModels_sorted = ModelicaMLServices.getSortedByName(additionalModels);
			
			for (int i = 0; i < addtitionalModels_sorted.size(); i++) {
				Element additionalModel= addtitionalModels_sorted.get(i);
				
				// create property
				Property p_additionalModel = owningClass.createOwnedAttribute(
						namePrefix + i + "_" + StringUtls.replaceSpecChar(((NamedElement)additionalModel).getName().toLowerCase()), 
						(Type)additionalModel);
				
				// apply stereotype
				Stereotype s_additionalModel= null;
				if (additionalModel.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null
						|| additionalModel.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null) {
					
					s_additionalModel = p_additionalModel.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);	
				}
				else {
					s_additionalModel = p_additionalModel.getApplicableStereotype(Constants.stereotypeQName_Component);
				}
				if (s_additionalModel != null) {
					p_additionalModel.applyStereotype(s_additionalModel);
				}
				else {
					String msg = "ERROR: Cannot apply the required stereotype to '" 
						+ p_additionalModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
					addToLog(msg);
				}
			}
		}
	}
	
	private void copySimulationSettings(Classifier testScenarioModel, Classifier simulationModel){
		Stereotype sTestScenario = testScenarioModel.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario);
		Stereotype sSimulation = simulationModel.getAppliedStereotype(Constants.stereotypeQName_Simulation);
		
		if (sTestScenario != null && sSimulation != null) {
			Object startTime = testScenarioModel.getValue(sTestScenario, Constants.propertyName_startTime);
			simulationModel.setValue(sSimulation, Constants.propertyName_startTime, startTime);
			Object stopTime = testScenarioModel.getValue(sTestScenario, Constants.propertyName_stopTime);
			simulationModel.setValue(sSimulation, Constants.propertyName_stopTime, stopTime);
			Object numberOfIntervals = testScenarioModel.getValue(sTestScenario, Constants.propertyName_numberOfIntervals);
			simulationModel.setValue(sSimulation, Constants.propertyName_numberOfIntervals, numberOfIntervals);
			Object tolerance = testScenarioModel.getValue(sTestScenario, Constants.propertyName_tolerance);
			simulationModel.setValue(sSimulation, Constants.propertyName_tolerance, tolerance);
			
			Comment annotationExperimentComment = simulationModel.createOwnedComment();
			Stereotype annotationStereotype = annotationExperimentComment.getApplicableStereotype(Constants.stereotypeQName_Annotation);
			if (annotationStereotype != null) {
				
				// apply stereotype
				annotationExperimentComment.applyStereotype(annotationStereotype);
				
				// Example of a Modelica annotation string: annotation(experiment(StartTime = 0.0, StopTime = 150));
				
				String annotationString = "experiment(";
				
				if (startTime!= null && !startTime.toString().isEmpty()) {annotationString = annotationString + "StartTime=" + startTime.toString() + ", "; }
				if (stopTime!= null && !stopTime.toString().isEmpty()) {annotationString = annotationString + "StopTime=" + stopTime.toString() + ", "; }
				//if (numberOfIntervals!= null) {annotationString = annotationString + ", Output=" + numberOfIntervals.toString(); }
				if (tolerance!= null && !tolerance.toString().isEmpty()) {annotationString = annotationString + "Tolerance=" + tolerance.toString(); }
				
				if (annotationString.length() > 2 && annotationString.trim().endsWith(",")) {
					annotationString = annotationString.substring(0, annotationString.length() - 2);
				}
				
				annotationString = annotationString + ")";
				annotationExperimentComment.setBody(annotationString);
				annotationExperimentComment.setValue(annotationStereotype, Constants.propertyName_fullAnnotationString, (Object)annotationString);
			}
			else {
//				System.err.println("Could not access the annotation stereotype");
			}
		}
	}
	
	
	
	
	
	
	private HashSet<Element> getRequirements(Element scenario, String stereotypeQName){
		HashSet<Element> requirements = new HashSet<Element>();
		
		// collect from dependencies
		EList<Dependency> depList = ((NamedElement)scenario).getClientDependencies();
		for (Dependency dependency : depList) {
			
			// Check if the dependency has the specified stereotype
			if (dependency.getAppliedStereotype(stereotypeQName) != null) {
			
				for (Element target : dependency.getSuppliers()) {
					// check if this is a requirement
					if (target instanceof Class && target.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
						requirements.add( (Class) target);
					}
				}
			}
		}
		return requirements;
	}

	private void reportError(String title, String message){
		Shell shell = getShell();
		MessageDialog.openError(shell, title, message);
	}
	
	private Shell getShell(){
		Shell shell = null;
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			if (win != null) {
				shell = win.getShell();
			}
		}
		if (shell == null) {
			shell = new Shell();
		}
		return shell;
	}
	
	private void clearAllLists(){
		testScenariosToBeInstantiated.clear();
		testScenariosDiscarded.clear();
		requirementsToBeInstantiated.clear();
		requirementsDiscarded.clear();
		
		modelsWithBindingErrors.clear();
	}
	
	
	private void addToLog(String msg){
//		this.log = this.log + "\n" + msg;
		this.log = this.log + msg + "\n";
	}

	private void clearLog(){
		this.log = "";
	}
	

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToMap(HashMap map, Element key, Element value){
		Object list = map.get(key);
		if (list instanceof HashSet) {
			((HashSet<Element>)list).add(value);
			map.put(key, list);
		}
		else{
			HashSet<Element> newList = new HashSet<Element>();
			newList.add(value);
			map.put(key, newList);
		}
	}
	
	// GETTERS ############################################
	
	public String getLog() {
		return log;
	}
	
	public HashSet<Element> getAllRequirements() {
		return allRequirements;
	}

	public void setAllRequirements(HashSet<Element> allRequirements) {
		this.allRequirements = allRequirements;
	}

	public HashSet<Element> getAllTestScenarios() {
		return allTestScenarios;
	}

	public void setAllTestScenarios(HashSet<Element> allTestScenarios) {
		this.allTestScenarios = allTestScenarios;
	}

	public HashSet<Element> getTestScenariosToBeInstantiated() {
		return testScenariosToBeInstantiated;
	}

	public void setTestScenariosToBeInstantiated(
			HashSet<Element> testScenariosToBeInstantiated) {
		this.testScenariosToBeInstantiated = testScenariosToBeInstantiated;
	}

	public HashSet<Element> getTestScenariosDiscarded() {
		return testScenariosDiscarded;
	}

	public void setTestScenariosDiscarded(HashSet<Element> testScenariosDiscarded) {
		this.testScenariosDiscarded = testScenariosDiscarded;
	}

	public HashSet<Element> getRequirementsToBeInstantiated() {
		return requirementsToBeInstantiated;
	}

	public void setRequirementsToBeInstantiated(
			HashSet<Element> requirementsToBeInstantiated) {
		this.requirementsToBeInstantiated = requirementsToBeInstantiated;
	}

	public HashSet<Element> getRequirementsDiscarded() {
		return requirementsDiscarded;
	}

	public void setRequirementsDiscarded(HashSet<Element> requirementsDiscarded) {
		this.requirementsDiscarded = requirementsDiscarded;
	}
	
	public void setTestSimulationModelGenerationCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public boolean isTestSimulationModelGenerationCanceled() {
		return canceled;
	}

	public HashSet<Element> getUserSelectedTestScenarios() {
		return userSelectedTestScenarios;
	}
	
	public VerificationScenariosCollector getVsc() {
		return vsc;
	}

	public void setVsc(VerificationScenariosCollector vsc) {
		this.vsc = vsc;
	}
	
	public Element getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(Element targetPackage) {
		this.targetPackage = targetPackage;
	}
	
	public HashMap<Element, VeMScenarioReqCombinationsCreator> getScenarioToVerificationModelCombination() {
		return scenarioToVerificationModelCombination;
	}

	public void setScenarioToVerificationModelCombination(
			HashMap<Element, VeMScenarioReqCombinationsCreator> scenarioToVerificationModelCombination) {
		this.scenarioToVerificationModelCombination = scenarioToVerificationModelCombination;
	}
	
	
	// The total sleep time
	private static final int TOTAL_TIME = 100;
	// The increment sleep time
	private static final int INCREMENT = 10;
	// process time´is unknown
	private boolean indeterminate = true; 
	
	private String progressMonitorTitle = "Scenario-Based Models Generator ";
	private String monitorText1 = "Collecting data ...";
	private String monitorText2 = "Analyzing combinations ...";
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		
		if (!this.isTestSimulationModelGenerationCanceled()) {
			monitor.beginTask(progressMonitorTitle + " is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
			
		    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
		      Thread.sleep(INCREMENT);
		      monitor.worked(INCREMENT);
		      if (total == TOTAL_TIME / 4) monitor.subTask(monitorText1);
		      if (total == TOTAL_TIME / 2) monitor.subTask(monitorText2);
		      
//		      if (total == TOTAL_TIME / 8) monitor.subTask("Collecting data ...");
//		      if (total == TOTAL_TIME / 4) monitor.subTask("Preparing simulation models to be created ...");
//		      if (total == TOTAL_TIME / 2) monitor.subTask("Creating models ...");
		    }
		    monitor.done();
		    if (monitor.isCanceled()){
		    	throw new InterruptedException(progressMonitorTitle + " was cancelled.");
		    }   
		}
	}


	public boolean isBindingErrorsDetected() {
		return bindingErrorsDetected;
	}


	public void setBindingErrorsDetected(boolean bindingErrorsDetected) {
		this.bindingErrorsDetected = bindingErrorsDetected;
	}


	public HashSet<Element> getGeneratedPackages() {
		return generatedPackages;
	}


	public HashSet<Element> getModelsWithBindingErrors() {
		return modelsWithBindingErrors;
	}
	

	public HashSet<Element> getSystemModels() {
		return systemModels;
	}

}
