package org.openmodelica.modelicaml.helper.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
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
import org.openmodelica.modelicaml.common.services.ElementsCollector;
import org.openmodelica.modelicaml.common.services.StringUtls;

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
	
	
	public void generate(){
		
		if (sourceModels != null) {
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
		}
	}
	

	private void createSimulationModels(Element sourceModel){
		
		/*TODO: create dialog that lets the user select the found test scenarios and requirements
		 * and shows which test scenarios are discarded and which requirements are not selected. 
		 */
		
		PackageableElement simulationModelsPackage = (
				(Package)targetPackage).createPackagedElement(Constants.simModelsPackageNamePrefix + ((NamedElement)sourceModel).getName(),
				UMLPackage.Literals.PACKAGE);
		
		for (Element testScenario : testScenariosToBeInstantiated) {
			
			if (testScenario instanceof Classifier) {
				
				// add class
				String simulationModelName = Constants.simModelsNamePrefix + ((NamedElement)testScenario).getName();
				Class simulationModel = ((Package)simulationModelsPackage).createOwnedClass(simulationModelName, false);
				
				// TODO: here copy the simulation annotation values 
				// from the TestScenario stereotype to the Simulation (or Test) stereotype
				
				Stereotype s_model = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Model);
				Stereotype s_simulation = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Simulation);
				if (s_model != null && s_simulation != null) {
					simulationModel.applyStereotype(s_model);
					simulationModel.applyStereotype(s_simulation);
				}
				else {
					String msg = "ERROR: Cannot apply the stereotypes 'Model' and 'Simulation' to the '" 
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
					String msg = "ERROR: Cannot apply the stereotypes 'Component' to the '" 
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
					String msg = "ERROR: Cannot apply the stereotypes 'CalculatedProperty' to the '" + p_testScenario.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
					addToLog(msg);
				}

				// add requirements
				RequirementsInstantiator ri = new RequirementsInstantiator();
				for (Element requirement : tsc.getTsToReq().get(testScenario)) {
					// if this requirement was selected, i.e. was not discarded
					if (!requirementsDiscarded.contains(requirement) && requirement instanceof Class) {
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
									String msg = "ERROR: Cannot apply the stereotypes 'requirementInstantance' to the '" + p_req.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
									addToLog(msg);
								}
							}
						}
					}
				}
				
				// instantiate the class
				ClassInstantiation ci = new ClassInstantiation((Class) simulationModel, true);
				ci.createTree();
				
				// update bindings
				ValueBindingCreator vbc = new ValueBindingCreator();
				/* Note, the updateAllBindings() is called with the last argument simulateOnly = false  
				 * so that modifications are created in components.
				 */
				vbc.updateAllBindings((Package)valueBindingsPackage, ci.getTreeRoot(), ci.getTreeRoot(), false, true, false, false);
			}
		}
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
		vbc.updateAllBindings((Package)valueBindingsPackage, ciSourceModel.getTreeRoot(), simulatedInstantiationTreeRoot, false, true, false, true);

		/* If there is at least one client that uses one of the test scenario providers -> go for requirements
		 * else stop here -> this test scenario can not be used to stimulate this system model.
		 */
		
		/* NOTE: Checking if test scenario clients are all satisfied by the system model
		 * is not necessary because a test scenario must not have all clients bound to other model.
		 * If it is used to test different design alternatives then some of the test scenario clients may be specific to
		 * one design alternative and are not satisfied by another design alternative. 
		 */
		
		//TODO: Check if all required clients are satisfied! 
		
		if (treeContainsOneOf(vbc.getUsedProviders(), ciTestScenario.getTreeRoot())) {
//			System.err.println( "selected test scenario: " + ((NamedElement)testScenario).getName());
			
			// create a new simulated instantiation
			// add system model to the simulated instantiation 
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
				
				// if it was possible to derive code for all clients of this requirement using the system model providers
				if (vbc.getAllClientsFound().size() > 0 
						&& vbc.getAllClientsWithPossibleBindingCodeDerivation().containsAll(vbc.getAllClientsFound())) {
					// add to req.list to be instantiated
					requirementsToBeInstantiated.add(requirement);
//					System.err.println("selected req.: " + ((NamedElement)requirement).getName());
				}
				else {
					requirementsDiscarded.add(requirement);
//					System.err.println("discarded req.: " + ((NamedElement)requirement).getName());

					// add to log
					String msg = "DISCARDED: Requirement '" + ((NamedElement)requirement).getQualifiedName() + "'" +  
								"\nFor none of its clients the binding code can be derived using " +
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
	
	private boolean treeContainsOneOf(HashSet<TreeObject> treeItems, TreeParent treeParent){
		HashSet<TreeObject> allTreeItems = getAllTreeItems(treeParent);
		
		for (TreeObject treeObject : treeItems) {
			if (allTreeItems.contains(treeObject)) {
				return true;
			}
		}
		return false;
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
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
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
