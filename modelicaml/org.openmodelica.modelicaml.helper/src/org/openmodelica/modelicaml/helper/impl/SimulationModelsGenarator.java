package org.openmodelica.modelicaml.helper.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ElementsCollector;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.dialogs.SelectTestScenariosAndRequirementsDialog;

public class SimulationModelsGenarator implements IRunnableWithProgress {
	
	public SimulationModelsGenarator(
			HashSet<Element> sourceModels,
			Element targetPackage, Element requirementsPackage,
			Element testScenariosPackage, Element valueMediatorsPackage) {
		super();
		this.sourceModels = sourceModels;
		this.targetPackage = targetPackage;
		this.requirementsPackage = requirementsPackage;
		this.testScenariosPackage = testScenariosPackage;
		this.valueBindingsPackage = valueMediatorsPackage;
	}
	
	// the selected model to generate the simulation models for
	private HashSet<Element> sourceModels = null;

	// the package for simulation models
	private Element targetPackage = null;
	
	// the package to containing the requirements to be used
	private Element requirementsPackage = null;
	// the package to containing the test scenarios to be used
	private Element testScenariosPackage = null;
	// the package to containing the value mediators to be used
	private Element valueBindingsPackage = null;
	
	private TestScenariosCollector tsc;

	// all requirements that were collected from the specified requirements package
	private HashSet<Element> allRequirements = new HashSet<Element>();
	// all test scenarios that were collected from the specified test scenarios package
	private HashSet<Element> allTestScenarios = new HashSet<Element>();
	
	// all test scenarios that were selected for the selected system model
	private HashSet<Element> testScenariosToBeInstantiated = new HashSet<Element>();
	// all test scenarios that discarded because they cannot be used for stimulating the selected system model.
	private HashSet<Element> testScenariosDiscarded = new HashSet<Element>();
	
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
	
	
	public void generate(){
		
		if (sourceModels != null) {
			
			/*
			 * TODO: Implement a GUI for selecting the packages were to search for 
			 * test scenarios, requirements, value mediators and 	
			 * for selecting the targetPackage to generate the simulation models into.
			 */
			
			// find all test scenarios
			tsc = new TestScenariosCollector();
			tsc.collectTestCasesFromPackage((Package) testScenariosPackage, true);
			allTestScenarios.addAll(tsc.getAllTS());

			/* Collect all requirements in order to be able to determine 
			 * which are not covered by simulation models.
			 */
			ElementsCollector ec = new ElementsCollector();
			ec.collectElementsFromModel(requirementsPackage, Constants.stereotypeQName_Requirement);
			allRequirements.addAll(ec.getElements());
			
			/* For each of the selected system models create a package containing classes that 
			 * instantiate all possible combinations of test scenarios and requirements that 
			 * can be tested using a given test scenario.
			 * NOTE, 
			 * 	- only test scenarios will be selected that can be used to stimulate the given system mode
			 * 	- only requirements will be selected that can be tested using the selected test scenarios 
			 * 	  and for which for all of its clients the providers inside the system model are present.
			 */
			for (Element sourceModel : sourceModels) {
				// clear all lists because the translation for each source model is individual.
				clearAllLists();
				// generate simulation models for the source model 
				generateSimulationModels(sourceModel);
			}
		}
	}
	
	private void clearAllLists(){
		testScenariosToBeInstantiated.clear();
		testScenariosDiscarded.clear();
		requirementsToBeInstantiated.clear();
		requirementsDiscarded.clear();
	}
	
	private void generateSimulationModels(Element sourceModel){
		
		String errorTitle = "Test Simulation Models Generation Helper";
		String errorMessage = "No test scenarios were found that can be used to stimulate the model " +
				"'"+((NamedElement)sourceModel).getName()+"'.";

		if (allTestScenarios.size() > 0) {
			
			// Instantiate the system model. This is used to determine the required coherence of clients and providers.
			ClassInstantiation ciSourceModel = new ClassInstantiation((Class) sourceModel, true);
			ciSourceModel.createTree();
			
			for (Element testScenario : allTestScenarios) {
				/* 1) Find all test scenarios can be used to stimulate this system model,
				 * 2) Find all requirements, that can be testing the found test scenarios and for which 
				 * 	  all requirement clients can be bound to the selected system model.
				 */
				findTestSenariosAndRequirementsToBeInstantiated(ciSourceModel, (Class) testScenario);
			}
			if (testScenariosToBeInstantiated.size() > 0 && requirementsToBeInstantiated.size() > 0) {
				// Create simulation model for each test scenario and all its referenced (and selected) requirements
				createSimulationModels(sourceModel);
			}
			else {
				informError(errorTitle, errorMessage);
			}
		}
		else {
			informError(errorTitle, errorMessage);
		}
	}
	
	private void informError(String title, String message){
		MessageDialog.openError(new Shell(), title, message);
	}
	
	private void createSimulationModels(Element sourceModel){

		/* GUI to show the collected and discarded test scenarios and requirements.
		 * It should enable a selection of test scenarios and requirements to be finally instantiated. 
		 */

		SelectTestScenariosAndRequirementsDialog dialog = new SelectTestScenariosAndRequirementsDialog(
				new Shell(), 
				testScenariosToBeInstantiated, 
				testScenariosDiscarded, 
				requirementsToBeInstantiated, 
				requirementsDiscarded, 
				sourceModel,
				tsc,
				getLog());
		
		dialog.open();
		
		if (dialog.getReturnCode() == 1) { // Cancel return code
			setCanceled(true); // indicate abort and stop here. 
		}
		else {
			// Get the selected test scenarios and requirements from the dialog.  
			HashMap<Element,HashSet<Element>> userSelectedTestScenariosAndRequirements = dialog.getSelectedTestScenariosWithRequirements();
			HashSet<Element> userSelectedTestScenarios = new HashSet<Element>();
			userSelectedTestScenarios.addAll(userSelectedTestScenariosAndRequirements.keySet());
			
			if (userSelectedTestScenarios.size() > 0) {
				String pkgName = Constants.simModelsPackageNamePrefix + ((NamedElement)sourceModel).getName();
				String postFix = getNamePostFix((Package)targetPackage, pkgName);
				PackageableElement simulationModelsPackage = ((Package)targetPackage).createPackagedElement(pkgName + postFix,UMLPackage.Literals.PACKAGE);
			
			
	//			for (Element testScenario : testScenariosToBeInstantiated) {
				for (Element testScenario : userSelectedTestScenarios) {
					
					if (testScenario instanceof Classifier) {
						
						// add class
						String simulationModelName = Constants.simModelsNamePrefix + ((NamedElement)testScenario).getName();
						Class simulationModel = ((Package)simulationModelsPackage).createOwnedClass(simulationModelName, false);
						
						/*
						 *  Copy the simulation annotation values 
						 *  from the TestScenario stereotype to the Simulation (or Test) stereotype 
						 */
						Stereotype s_model = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Model);
						Stereotype s_simulation = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Simulation);
						Stereotype s_test = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Test);
						if (s_model != null && s_simulation != null) {
							simulationModel.applyStereotype(s_model);
							simulationModel.applyStereotype(s_simulation);
							simulationModel.applyStereotype(s_test);
							
							// Copy the simulation settings from test scenario to the test model.
							copySimulatioSettings((Class) testScenario, simulationModel);
						}
						else {
							String msg = "ERROR: Cannot apply the stereotype 'Model' and 'Simulation' to '" 
								+ simulationModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
							addToLog(msg);
						}
						
						// add system model property
						Property p_systemModel = simulationModel.createOwnedAttribute(
								Constants.systemModelPropertyNamePrefix 
								+ StringUtls.replaceSpecChar(((NamedElement)sourceModel).getName().toLowerCase()), 
								(Type)sourceModel);
						Stereotype s_component = p_systemModel.getApplicableStereotype(Constants.stereotypeQName_Component);
						if (s_component != null) {
							p_systemModel.applyStereotype(s_component);
						}
						else {
							String msg = "ERROR: Cannot apply the stereotype 'Component' to '" 
								+ p_systemModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
							addToLog(msg);
						}
		
						// add test scenario property
						Property p_testScenario = simulationModel.createOwnedAttribute(
								Constants.testScenarioPropertyNamePrefix 
								+ StringUtls.replaceSpecChar(((NamedElement)testScenario).getName().toLowerCase()), 
								(Type)testScenario);
						Stereotype s_calProp = p_testScenario.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);
						if (s_calProp != null) {
							p_testScenario.applyStereotype(s_calProp);
						}
						else {
							String msg = "ERROR: Cannot apply the stereotype 'CalculatedProperty' to '" + p_testScenario.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
							addToLog(msg);
						}
		
						// add requirements
						RequirementsInstantiator ri = new RequirementsInstantiator();
	//					for (Element requirement : tsc.getTsToReq().get(testScenario)) {
						for (Element requirement : userSelectedTestScenariosAndRequirements.get(testScenario)) {
	
							// if this requirement was selected, i.e. was not discarded
	//						if (!requirementsDiscarded.contains(requirement) && requirement instanceof Class) {
							if (requirement instanceof Class) {
								if (requirement instanceof Classifier) {
									// get the number of required instantiations
									int requiredNumberOfInstantions = ri.getMaxNumberOfProviders(simulationModel, (Class) requirement);
									for (int i = 0; i < requiredNumberOfInstantions; i++) {
										Property p_req = simulationModel.createOwnedAttribute(
												Constants.reqirementPropertyNamePrefix + 
												getRequirementId((NamedElement) requirement) + "_" + 
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
								}
							}
						}
		
						/*
						 *  Based on Dependency <<Requires>>, for all test scenarios 
						 *  and requirements to be instantiated, find models that should be instantiated in addition.
						 */
						
						/*
						 *  instantiate the created simulation model class in order to search for required additional models
						 */
						ClassInstantiation ciTemp = new ClassInstantiation((Class) simulationModel, true);
						ciTemp.createTree();
						
						HashSet<Element> addtionalModels = getAdditionalModels(sourceModels, 
								testScenariosToBeInstantiated, 
								requirementsToBeInstantiated,
								ciTemp);
						
						for (Element additionalModel : addtionalModels) {
							
							// create property
							Property p_additionalModel = simulationModel.createOwnedAttribute(
									Constants.additionalModelPrefix 
									+ StringUtls.replaceSpecChar(((NamedElement)additionalModel).getName().toLowerCase()), 
									(Type)additionalModel);
							
							// apply stereotype
							Stereotype s_componentForAdditionalComponent = null;
							if (additionalModel.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null
									|| additionalModel.getAppliedStereotype(Constants.stereotypeQName_TestScenario) != null) {
								s_componentForAdditionalComponent = p_systemModel.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);	
							}
							else {
								s_componentForAdditionalComponent = p_systemModel.getApplicableStereotype(Constants.stereotypeQName_Component);
							}
							if (s_componentForAdditionalComponent != null) {
								p_additionalModel.applyStereotype(s_componentForAdditionalComponent);
							}
							else {
								String msg = "ERROR: Cannot apply the required stereotype to '" 
									+ p_additionalModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
								addToLog(msg);
							}
						}
						
						
						/* 
						 * instantiate the created simulation model class
						 * (it not contains all components)
						 */
						ClassInstantiation ci = new ClassInstantiation((Class) simulationModel, true);
						ci.createTree();
						
						// update bindings in the created simulation model class
						ValueBindingCreator vbc = new ValueBindingCreator();
						/* Note, the updateAllBindings() is called with the last argument simulateOnly = false  
						 * so that modifications ARE created in components.
						 */
						vbc.updateAllBindings((Package)valueBindingsPackage, ci.getTreeRoot(), ci.getTreeRoot(), false, true, false, false);
						
						/*
						 * Create test verdict code
						 */
						TestOracleElementsCreator.createTestEvaluationElements(simulationModel);
						
					}
				}
			}
		}
	}
	
	private void copySimulatioSettings(Classifier testScenarioModel, Classifier simulationModel){
		Stereotype sTestScenario = testScenarioModel.getAppliedStereotype(Constants.stereotypeQName_TestScenario);
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
		}
	}
	
	@SuppressWarnings("rawtypes")
	private HashSet<Element> getAdditionalModels(
			HashSet<Element> sourceModels, 
			HashSet<Element> testScenariosToBeInstantiated, 
			HashSet<Element> requirementsToBeInstantiated,
			ClassInstantiation ciTemp){
		
		HashSet<Element> listOfAdditionalModels = new HashSet<Element>();
		
//		// all models that were selected: system models, test scenarios and requirements
//		HashSet<Element> allCollectedModels = new HashSet<Element>();
//		allCollectedModels.addAll(sourceModels);
//		allCollectedModels.addAll(testScenariosToBeInstantiated);
//		allCollectedModels.addAll(requirementsToBeInstantiated);
		
		// all models that will be instantiated: system models, test scenarios and requirements
		HashSet<Element> allModelsInInstantiationTree = new HashSet<Element>();
		allModelsInInstantiationTree.addAll(getAllTreeItemsClasses(ciTemp.getTreeRoot()));
		
		for (Element element : allModelsInInstantiationTree) {
			if (element instanceof NamedElement) {
				for (Element dep : ((NamedElement)element).getClientDependencies()) {
					Stereotype s = dep.getAppliedStereotype(Constants.stereotypeQName_Requires);
					if (s != null) {
						Object list = dep.getValue(s, Constants.propertyName_onlyIncombinationWith);
						if ( list instanceof EList && ((EList)list).size() > 0) { // check restriction
							for (Object referencedModel : (EList<?>)list) {
								Element referencedModelElement = UMLUtil.getBaseElement((EObject) referencedModel);
								if (referencedModelElement instanceof Element 
										&& allModelsInInstantiationTree.contains(referencedModelElement) ) {
									// add the dependency target to the models that should be instantiated in addition
									listOfAdditionalModels.add( ((Dependency)dep).getTargets().get(0) );
								}
							}
						}
						else { // no restriction -> add the dependency target to the list. 
							listOfAdditionalModels.add( ((Dependency)dep).getTargets().get(0) );
						}
					}
				}
			}
		}
		return listOfAdditionalModels;
	}
	
	private void findTestSenariosAndRequirementsToBeInstantiated(ClassInstantiation ciSourceModel, Class testScenario){
		// add system model to the simulated instantiation of the 
		TreeParent simulatedInstantiationTreeRoot = new TreeParent("Simulated Instantiation", null, null, "", false, true, null, null, true);
		simulatedInstantiationTreeRoot.addChild(ciSourceModel.getTreeRoot());
		
		// instantiate test scenario
		ClassInstantiation ciTestScenario = new ClassInstantiation((Class) testScenario, true);
		ciTestScenario.createTree();
		
		// add test scenario to the simulated instantiation
		simulatedInstantiationTreeRoot.addChild(ciTestScenario.getTreeRoot());
		
		/* get the list of system model clients for which the code could be derived (even if a user interaction would be necessary) 
		from the test scenario providers */
		ValueBindingCreator vbc = new ValueBindingCreator();
		
		/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
		 * so that no modifications are created in components because we only want to analyze possible bindings.
		 */
		boolean allSystemModelRquiredClientsAreSatisfied = true;
		vbc.updateAllBindings((Package)valueBindingsPackage, ciSourceModel.getTreeRoot(), simulatedInstantiationTreeRoot, false, true, false, true);
		if ( vbc.getAllRequiredClientsFound().size() > 0 
				&& !vbc.getAllClientsWithPossibleBindingCodeDerivation().containsAll(vbc.getAllRequiredClientsFound())) {
			allSystemModelRquiredClientsAreSatisfied = false;
		}

		/* If there is at least one client that uses one of the test scenario providers
		 * and all test scenario required clients are satisfied by the system model -> search for associated requirements
		 * else stop here -> this test scenario is not appropriate to be used to stimulate this system model.
		 */
		
		/* NOTE: Checking if test scenario clients are all satisfied by the system model
		 * is not necessary because a test scenario must not have all clients bound to other model.
		 * If it is used to test different design alternatives then some of the test scenario clients may be specific to
		 * one design alternative and are not satisfied by another design alternative. 
		 */
		boolean atLeastOneTestScenarioProviderIsUsedInSystemModel = treeContainsOneOf(vbc.getUsedProviders(), ciTestScenario.getTreeRoot());

//		if ( treeContainsOneOf(vbc.getUsedProviders(), ciTestScenario.getTreeRoot()) ) { // if at least one test scenario providers is used to stimulate the system model.
		if ( atLeastOneTestScenarioProviderIsUsedInSystemModel && allSystemModelRquiredClientsAreSatisfied) {
//			System.err.println( "selected test scenario: " + ((NamedElement)testScenario).getName());
			
			/* Create a new simulated instantiation, 
			 * add system model to the simulated instantiation 
			 */
			TreeParent simulatedInstantiationTreeRoot_reqAndSystemModel = new TreeParent("Simulated Instantiation", null, null, "", false, true, null, null, true);
			simulatedInstantiationTreeRoot_reqAndSystemModel.addChild(ciSourceModel.getTreeRoot());
			
			HashSet<Element> requirementsToBeInstantiated = new HashSet<Element>();
			// get all requirements that can be tested using this scenario
			for (Element requirement : tsc.getTsToReq().get(testScenario)) {
				// instantiate test scenario
				ClassInstantiation ciReq = new ClassInstantiation((Class) requirement, true);
				ciReq.createTree();
				
				simulatedInstantiationTreeRoot_reqAndSystemModel.addChild(ciReq.getTreeRoot());
				/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
				 * so that no modifications are created in components because we only want to analyze possible bindings.
				 */
				vbc.updateAllBindings((Package)valueBindingsPackage, ciReq.getTreeRoot(), 
						simulatedInstantiationTreeRoot_reqAndSystemModel, false, true, false, true);
				
				// if it was possible to derive code for all (required) clients of this requirement using the system model providers
				if (vbc.getAllClientsFound().size() > 0 
						&& vbc.getAllClientsWithPossibleBindingCodeDerivation().containsAll(vbc.getAllClientsFound())) {
					// add to req.list to be instantiated
					requirementsToBeInstantiated.add(requirement);
//					System.err.println("selected req.: " + ((NamedElement)requirement).getName());
				}
				else if (vbc.getAllRequiredClientsFound().size() > 0 
						&& vbc.getAllClientsWithPossibleBindingCodeDerivation().containsAll(vbc.getAllRequiredClientsFound())) {
					// add to req.list to be instantiated
					requirementsToBeInstantiated.add(requirement);
//					System.err.println("selected req.: " + ((NamedElement)requirement).getName());
				}
				else {
					requirementsDiscarded.add(requirement);
//					System.err.println("discarded req.: " + ((NamedElement)requirement).getName());

					// add to log
					String msg = "DISCARDED: Requirement ("+ModelicaMLServices.getRequirementID(requirement)+") '" + ((NamedElement)requirement).getQualifiedName() + "'" +  
								"\nFor some of its clients the binding code could NOT be derived using " +
								"the providers of '"+ciSourceModel.getSelectedClass().getName()+"'. \n" ;
					addToLog(msg);
				}
			}
			
			//Collect the test scenario and all its referenced requirements that are selected for instantiation
			if (requirementsToBeInstantiated.size() > 0 ) {
				this.testScenariosToBeInstantiated.add(testScenario);
				this.requirementsToBeInstantiated.addAll(requirementsToBeInstantiated);
			}
			else {
				testScenariosDiscarded.add(testScenario);
//				System.err.println("discarded test scenario: " + ((NamedElement)testScenario).getName());
				String msg = "";
				
				// if there no requirements referenced by the test scenario
				if (tsc.getTsToReq().get(testScenario) == null) {
					msg = "DISCARDED: Test Scenario '" + ((NamedElement)testScenario).getQualifiedName() + "'" +  
					"\nNo requirements are found that can be tested using this test scenario " +
					"and the model '"+ciSourceModel.getSelectedClass().getName()+"'. \n" ;
				}
				else {
					msg = "DISCARDED: Test Scenario '" + ((NamedElement)testScenario).getQualifiedName() + "'" +  
					"\nFor none of the referenced requirements clients the clients are satisfied by providers of " +
					"the model '"+ciSourceModel.getSelectedClass().getName()+"'. \n" ;
				}
				// add to log
				addToLog(msg);
			}
		}
		else {
			testScenariosDiscarded.add(testScenario);
//			System.err.println("discarded test scenario: " + ((NamedElement)testScenario).getName());

			// add to log
			String msg = "DISCARDED: Test Scenario '" + ((NamedElement)testScenario).getQualifiedName() + "'" + 
						"\nNone of its providers is used to stimulate the model '"+ciSourceModel.getSelectedClass().getName()+"'. \n" ;
			addToLog(msg);
		}
	}
	
	// Utls ##########################################################
	
	private String getRequirementId(NamedElement req){
		Stereotype s_req = req.getAppliedStereotype(Constants.stereotypeQName_Requirement);
		Object o = req.getValue(s_req, Constants.propertyName_id);
		if (o instanceof String) {
			return (String) o;
		}
		return "";
	}
	
	private HashSet<TreeObject> getAllTreeItems(TreeParent treeParent){
		HashSet<TreeObject> allTreeItems = new HashSet<TreeObject>();
		allTreeItems.add(treeParent);
		
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			allTreeItems.add(children[i]);
			if (children[i] instanceof TreeParent) {
				allTreeItems.addAll(getAllTreeItems((TreeParent)children[i]));
			}
		}
		
		return allTreeItems;
	}
	
	private HashSet<Element> getAllTreeItemsClasses(TreeParent treeParent){
		HashSet<Element> allTreeItems = new HashSet<Element>();
		
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i].getUmlElement() instanceof TypedElement && ((TypedElement)children[i].getUmlElement()).getType() instanceof Classifier) {
				allTreeItems.add(((TypedElement)children[i].getUmlElement()).getType());
			}
			if (children[i] instanceof TreeParent) {
				allTreeItems.addAll(getAllTreeItemsClasses((TreeParent)children[i]));
			}
		}
		return allTreeItems;
	}
	
	
	private boolean treeContainsOneOf(HashSet<TreeObject> treeItems, TreeParent treeParent){
		HashSet<TreeObject> allTreeItems = getAllTreeItems(treeParent);
		
		for (TreeObject treeObject : treeItems) {
			if (allTreeItems.contains(treeObject)) {
				return true;
			}
		}
		return false;
	}

	private String getNamePostFix(NamedElement owner, String name){
		String postfix = "_";
		int highestPostFixNumber = 0;
	
		EList<Element> ownedElements = owner.getOwnedElements();
		for (Element element : ownedElements) {
			if (element instanceof NamedElement && ((NamedElement)element).getName().startsWith(name)) {
				
				String namePart = ((NamedElement)element).getName();
				String postFix = namePart.replaceFirst(name, "").replaceAll("_", "").trim();
				
				if (!postFix.equals("")) {
					try {
						int postFixNumber = Integer.valueOf(postFix);
						if (postFixNumber > highestPostFixNumber) {
							highestPostFixNumber = postFixNumber;
						}
					} catch (Exception e) {
						// ignore, do nothing.
					}
				}
			}
		}
		return postfix + (highestPostFixNumber + 1);	
	}

	private void addToLog(String msg){
		this.log = this.log + "\n" + msg;
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

	// The total sleep time
	private static final int TOTAL_TIME = 100;
	// The increment sleep time
	private static final int INCREMENT = 10;
	// process time´is unknown
	private boolean indeterminate = true; 
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		
		if (!isCanceled()) {
			monitor.beginTask("Simulation models generator is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
		    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
		      Thread.sleep(INCREMENT);
		      monitor.worked(INCREMENT);
		      if (total == TOTAL_TIME / 8) monitor.subTask("Collecting data ...");
		      if (total == TOTAL_TIME / 4) monitor.subTask("Preparing simulation models to be created ...");
		      if (total == TOTAL_TIME / 2) monitor.subTask("Creating models ...");
		    }
		    monitor.done();
		    if (monitor.isCanceled()){
		    	throw new InterruptedException("Simulation models generatorion was cancelled.");
		    }   
		}
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public boolean isCanceled() {
		return canceled;
	}

}
