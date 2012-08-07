package org.openmodelica.modelicaml.helper.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.helper.datacollection.VerificationDataCollector;
import org.openmodelica.modelicaml.helper.generators.GeneratorVeMScenariosBased;

public class GeneratedModelsData {
	
	// generator used
	private GeneratorVeMScenariosBased generator;
	
	// generated VeMs
	private HashSet<Element> generatedModels;
	private Element generatedModelsPackage;
	

	// System models that where used for verification
	private HashSet<Element> systemModels = new HashSet<Element>();
	
	// map containing the VeM and its system models 
	private HashMap<Element, HashSet<TreeObject>> modelToSystemModel = new HashMap<Element, HashSet<TreeObject>>();
	// generated model to its contained scenarios
	private HashMap<Element, HashSet<TreeObject>> modelToScenarios = new HashMap<Element, HashSet<TreeObject>>();
	// generated model to its contained requirements
	private HashMap<Element, HashSet<TreeObject>> modelToRequirements = new HashMap<Element, HashSet<TreeObject>>();

	public final String requirementStatusPropertyName = Constants.propertyName_mStatus;
	public final static String delimiter = Constants.linkDelimiter;
	
	private VerificationDataCollector verificationDataCollector;
	
	private HashMap<Element, ClassInstantiation> preparedInstantiations;
	
	/*
	 * The rest of the data structure below is filled after simulation 
	 * and results evaluation (i.e. post-processing of simulation result files)
	 */

	// for each set the strings are: simulation model qualified name + delimiter + variable dot path
	private HashSet<String> evaluatedRequirements = new HashSet<String>();
	private HashSet<String> violatedRequirements = new HashSet<String>();
	private HashSet<String> notViolatedRequirements = new HashSet<String>();
	private HashSet<String> notEvaluatedRequirements = new HashSet<String>();


	private HashMap<Element,String> simulationResultsFile;
	
	// list of model qualified names that could not be simulated (determined by the fact if there is a simulation results file or not)
	private List<String> simulationFailedList = new ArrayList<String>();
	
	private HashSet<Element> notSimulatedModels = new HashSet<Element>();
	private HashSet<Element> simulatedModels = new HashSet<Element>();
	
	// maps with scenarios and their requirements
	private HashMap<Element, HashSet<Element>> scenarioToEvaluatedRequirements = new HashMap<Element, HashSet<Element>>();
	private HashMap<Element, HashSet<Element>> scenarioToViolatedRequirements = new HashMap<Element, HashSet<Element>>();
	private HashMap<Element, HashSet<Element>> scenarioToNotEvaluatedRequirements = new HashMap<Element, HashSet<Element>>();

	
	private HashMap<Element, HashSet<Element>> scenarioWithNewPositiveRelationsToRequirements = new HashMap<Element, HashSet<Element>>();
	private HashMap<Element, HashSet<Element>> scenarioWithNewNegativeRelationsToRequirements = new HashMap<Element, HashSet<Element>>();

	
	// positive scenario to requirements relations, i.e., scenario was used to stimulate the system mode and lead to an evaluation of the requirements
	private HashMap<TreeObject, HashSet<TreeObject>> newPositiveRelations = new HashMap<TreeObject, HashSet<TreeObject>>();

	// negative scenario to requirements relations, i.e., scenario was used to stimulate the system mode and did NOT lead to an evaluation of the requirements
	private HashMap<TreeObject, HashSet<TreeObject>> newNegativeRelations = new HashMap<TreeObject, HashSet<TreeObject>>();
	
	
	private String log = "";
	

	/*
	 * This constructor should if no generator can be passed
	 */
	public GeneratedModelsData(Element generatedModelsPackage, VerificationDataCollector verificationDataCollector) {
		
		// generated VeM package
		this.setGeneratedModelsPackage(generatedModelsPackage);
		
		// generated VeMs
		this.setGeneratedModels(getGenVerificationModels(generatedModelsPackage));
		
		// set the data collector
		this.setVerificationDataCollector(verificationDataCollector);
		
		// deduce system models
		this.setSystemModels(collectSystemModels(getVerificationDataCollector(), getGeneratedModels()));
		
		// sort other data
		sortData();
	}
	
	/*
	 * This constructor should be used when a generator was running
	 */
	public GeneratedModelsData(GeneratorVeMScenariosBased generator) {
		
		this.setGenerator(generator);
		// set the most common package that contains all generated packages
		Element generatedModelsPackage = null;
		if (generator.getGeneratedPackages().size() > 1) {
			// TODO: we assume here that all packages have the same owner. Will that lead to an issue? 
			generatedModelsPackage = ((Element) generator.getGeneratedPackages().toArray()[0]).getNearestPackage();
		}
		else {
			generatedModelsPackage = (Element) generator.getGeneratedPackages().toArray()[0];
		}
		this.setGeneratedModelsPackage(generatedModelsPackage);
		this.setGeneratedModels(generator.getGeneratedVeMs());
		this.setSystemModels(generator.getSystemModels());
		this.setVerificationDataCollector(generator.getVerificationScenariosCollector().getVerificationDataCollector());
		this.setPreparedInstantiations(generator.getPreparedModelInstantiations());

		// sort other data
		sortData();
	}
	
	
	
	private void sortData(){

		for (Element genModel : this.generatedModels) {
			// get model instantiation
			ClassInstantiation ci = ModelicaMLServices.getModelInstantiation(genModel, getPreparedInstantiations());
			
			// pass pre-collected mediators in order to avoid another search
			ci.setAllMediators(getAllFoundMediators());
			
			// fill data maps
			findScenariosAndRequirements(ci, genModel);
		}
	}

	
//	protected HashSet<Element> getGenVerificationModels(HashSet<Element> generatedPackages){
	protected HashSet<Element> getGenVerificationModels(Element generatedPackage){
		
		HashSet<Element> generatedModels = new HashSet<Element>();
		
//		for (Element genPackage : generatedPackages) {
		
			if (generatedPackage != null) {
				Iterator<EObject> i = generatedPackage.eAllContents(); // only elements that are in the name space of this element 
				while (i.hasNext()) {
					EObject object = i.next() ;
					if (object instanceof Class) {
		
						Element element  = (Element)object;
						
						if (element.getAppliedStereotype(Constants.stereotypeQName_VerificationModel) != null) {
							generatedModels.add(element);
							
							// sort VeM to system models
							setVemToSystemModel(element);
						}
					}
				}
			}
//		}
	
		return generatedModels;
	}

	protected HashSet<Element> setVemToSystemModel(Element VeM){
		
		HashSet<Element> generatedModels = new HashSet<Element>();
		
		/*
		 * NOTE: This is not a deep search. Here we are only looking 
		 * at the first level properties of the VeM. We are not 
		 * traversing the instantiated VeM graph. 
		 */
		
		EList<Element> ownedElements = VeM.getOwnedElements();
		
		for (Element ownedElement : ownedElements) {
			if (ownedElement instanceof Property) {
				Property property = (Property) ownedElement;
				Type type = property.getType();
				if (type != null) {
					if (this.getSystemModels().contains(type)) {
						addToMapList(modelToSystemModel, VeM, type);
					}
				}
			}
		}
		
		return generatedModels;
	}
	


	private void findScenariosAndRequirements(ClassInstantiation ci, Element selectedClass){
		/*
		 * Note, this is not a deep search. We only look at the first level components
		 * because by convention the system model, scenario and requirements should
		 * be created as first level components in a VeM.
		 * The main reason to not traversing the instantiation tree is efficiency. 
		 */
		TreeParent treeRoot = ci.getTreeRoot();
		
		for (TreeObject treeObject : treeRoot.getChildren()) {
			fillMap(treeObject, selectedClass);
		}
	}


	
	private void fillMap(TreeObject treeItem, Element simModel){
		
		Element umlElementType = treeItem.getComponentType();
		
		if (umlElementType instanceof Element && umlElementType.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null) {
			addToMapList(modelToScenarios, simModel, treeItem);
		}
		else if (umlElementType instanceof Element && umlElementType.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
			addToMapList(modelToRequirements, simModel, treeItem);
		}
		/*
		 * If a generator was passed then we got the system models from the generator.
		 */
		else if (umlElementType instanceof Element && this.getSystemModels().contains(umlElementType)) {
			addToMap(modelToSystemModel, simModel, treeItem);
		}
	}
	
	/*
	 * This method is used if system models are not predefined but need to be deduced from a given verification model
	 */
	private HashSet<Element> collectSystemModels(VerificationDataCollector verificationDataCollector, HashSet<Element> generatedVeM){
		/*
		 * Note, this is not a deep search. We only look at the first level components
		 * because by convention the system model, scenario and requirements should
		 * be created as first level components in a VeM.
		 * The main reason to not traversing the instantiation tree is efficiency. 
		 */
		
		HashSet<Element> systemModels = new HashSet<Element>();
		
		for (Element  VeM : generatedVeM) {
			
			EList<Element> elements = VeM.getOwnedElements();
			for (Element component : elements) {
				if (component instanceof Property) {
					
					Property property = (Property) component;
					Type type = property.getType();
					
					/*
					 * if the component is not an instance of
					 * requirement, scenario, an additional model or an requirement verdict -> then this is a system model 
					 */
						
					if (type.getAppliedStereotype(Constants.stereotypeQName_Requirement) == null 
							&& type.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) == null
							&& !verificationDataCollector.getAllAdditionalModels().contains(type)
							
							&& !property.getName().startsWith(Constants.propertyName_requirementsVerificationVerdict)) {
						
						systemModels.add(type);
					}
				}
			}
		}
		
		return systemModels;
	}
	
	
	
	
	// Utilities
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToMap(HashMap map, Element key, TreeObject value){
		Object list = map.get(key);
		if (list instanceof HashSet) {
			((HashSet<TreeObject>)list).add(value);
			map.put(key, list);
		}
		else{
			HashSet<TreeObject> newList = new HashSet<TreeObject>();
			newList.add(value);
			map.put(key, newList);
		}
	}
	

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToMapList(HashMap map, Element key, TreeObject value){
		Object list = map.get(key);
		if (list instanceof HashSet) {
			((HashSet<TreeObject>)list).add(value);
			map.put(key, list);
		}
		else{
			HashSet<TreeObject> newList = new HashSet<TreeObject>();
			newList.add(value);
			map.put(key, newList);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToMapList(HashMap map, TreeObject key, TreeObject value){
		Object list = map.get(key);
		if (list instanceof HashSet) {
			((HashSet<TreeObject>)list).add(value);
			map.put(key, list);
		}
		else{
			HashSet<TreeObject> newList = new HashSet<TreeObject>();
			newList.add(value);
			map.put(key, newList);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addToMapList(HashMap map, Element key, Element value){
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
	
	public String getModelToTreeItemKeyString(Element model, String dotPath){
		if (model instanceof NamedElement) {
			return ((NamedElement)model).getQualifiedName() + delimiter + dotPath;
		}
		return "";
	}
	
	public HashSet<Element> getAllScenarios(){
		HashSet<Element> scenarios = new HashSet<Element>();
		
		for (Element model : modelToScenarios.keySet()) {
			HashSet<TreeObject> modelScenarios = modelToScenarios.get(model);
			if (modelScenarios != null && modelScenarios.size() > 0) {
				for (TreeObject scenarioTreeObject : modelScenarios) {
					Element scenario  = scenarioTreeObject.getComponentType();
					if (scenario != null) {
						scenarios.add(scenario);
					}
				}
			}
		}
		return scenarios;
	}
	
	
	public HashSet<Element> getAllUsedRequirements(){
		HashSet<Element> requirements = new HashSet<Element>();
		
		for (Element model : modelToRequirements.keySet()) {
			HashSet<TreeObject> modelRequirements = modelToRequirements.get(model);
			if (modelRequirements != null && modelRequirements.size() > 0) {
				for (TreeObject scenarioTreeObject : modelRequirements) {
					Element requirement  = scenarioTreeObject.getComponentType();
					if (requirement != null) {
						requirements.add(requirement);
					}
				}
			}
		}
		return requirements;
	}
	
	
	/*
	 * After simulation
	 */

	public HashSet<Element> getScenariosWithViolatedRequirements(){
		HashSet<Element> allScenarios = getAllScenarios();
		HashSet<Element> scenariosWithViolatedRequirements = new HashSet<Element>();
		for (Element scenario : allScenarios) {
			if (getScenarioToViolatedRequirements().get(scenario) != null) {
				scenariosWithViolatedRequirements.add(scenario);
			}
		}
		return scenariosWithViolatedRequirements;
	}
	
	
	public HashSet<Element> getScenariosWithNotViolatedRequirements(){
		HashSet<Element> allScenarios = getAllScenarios();
		HashSet<Element> scenariosWithNotViolatedRequirements = new HashSet<Element>();
		for (Element scenario : allScenarios) {
			HashSet<Element> evaluatedRequirements = getScenarioToEvaluatedRequirements().get(scenario);
			HashSet<Element> violatedRequirements = getScenarioToViolatedRequirements().get(scenario);
			
			if (evaluatedRequirements != null) {
				
				// remove all violated requirements to get all that were evaluated and not violated
				if (violatedRequirements != null) {
					evaluatedRequirements.removeAll(violatedRequirements);
				}
			}
			
			if (getScenarioToViolatedRequirements().get(scenario) != null) {
				scenariosWithNotViolatedRequirements.add(scenario);
			}
		}
		return scenariosWithNotViolatedRequirements;
	}
	
	public HashSet<Element> getNotEvaluatedRequirementElements(){
		
		HashSet<Element> allScenarios = getAllScenarios();
		HashSet<Element> notEvaluatedRequirements = new HashSet<Element>();
		HashSet<Element> evaluatedRequirements = new HashSet<Element>();
		
		for (Element scenario : allScenarios) {
			
			// get scenarios evaluated and not evaluated requirements
			HashSet<Element> evaluatedRequirementsForScenario = getScenarioToEvaluatedRequirements().get(scenario);
			HashSet<Element> notEvaluatedRequirementsForScenario = getScenarioToNotEvaluatedRequirements().get(scenario);
			
			if (notEvaluatedRequirementsForScenario != null) {
				// add all that were not evaluated in this scenario
				notEvaluatedRequirements.addAll(notEvaluatedRequirementsForScenario);
			}
			if (evaluatedRequirementsForScenario != null) {
				evaluatedRequirements.addAll(evaluatedRequirementsForScenario);
			}
		}
		
		// remove all that were evaluated
		notEvaluatedRequirements.removeAll(evaluatedRequirements);
		
		return notEvaluatedRequirements;
	}
	
	
	
	public HashSet<Element> getScenarioWithNotViolatedRequirements(Element scenario){
			HashSet<Element> notViolatedRequirements = new HashSet<Element>();
			
			// get scenarios evaluated and not evaluated requirements
			HashSet<Element> evaluatedRequirementsForScenario = getScenarioToEvaluatedRequirements().get(scenario);
			HashSet<Element> violatedForScenario = getScenarioToViolatedRequirements().get(scenario);
			
			if (evaluatedRequirementsForScenario != null) {
				// add all that were not evaluated in this scenario
				notViolatedRequirements.addAll(evaluatedRequirementsForScenario);
			}
			if (violatedForScenario != null) {
				notViolatedRequirements.removeAll(violatedForScenario);
			}
			
		return notViolatedRequirements;
	}
	
	
	public HashSet<Element> getRequirementsViolatedInScenarios(){
		HashSet<Element> allScenarios = getAllScenarios();
		HashSet<Element> requirements = new HashSet<Element>();
		for (Element scenario : allScenarios) {
			HashSet<Element> violatedRequirementsForScenario = getScenarioToViolatedRequirements().get(scenario);
			
			if (violatedRequirementsForScenario != null) {
				// add all that were violated in this scenario
				// note, this is a HashSet -> no duplicates!
				requirements.addAll(violatedRequirementsForScenario);
			}
			
		}
		return requirements;
	}
	
	public HashSet<Element> getRequirementsNotViolatedInScenarios(){
		HashSet<Element> allScenarios = getAllScenarios();
		
		HashSet<Element> requirements = new HashSet<Element>();
		
		for (Element scenario : allScenarios) {
		
			HashSet<Element> evaluatedRequirements = getScenarioToEvaluatedRequirements().get(scenario);
			HashSet<Element> violatedRequirements = getScenarioToViolatedRequirements().get(scenario);
			
			if (evaluatedRequirements != null) {
				
				// remove all violated requirements to get all that were evaluated and not violated
				if (violatedRequirements != null) {
					evaluatedRequirements.removeAll(violatedRequirements);
				}
				
				requirements.addAll(evaluatedRequirements);
			}
		}
		return requirements;
	}
	
	
	
	public boolean isNewRelation(TreeObject scenarioItem, TreeObject requirementItem, String dependencyStereotypeQName){
		
		Element scenario = scenarioItem.getComponentType();
		Element requirement = requirementItem.getComponentType();
		
		EList<Dependency> depList = ((Class)scenario).getClientDependencies();
		for (Dependency dependency : depList) {
			if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null )  {
				EList<Element> targets = dependency.getTargets();
				if (targets.contains(requirement)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isNewRelation(Element scenario, Element requirement, String dependencyStereotypeQName){
		
		EList<Dependency> depList = ((Class)scenario).getClientDependencies();
		for (Dependency dependency : depList) {
			if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null )  {
				EList<Element> targets = dependency.getTargets();
				if (targets.contains(requirement)) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public int getNewRelationsCount(boolean isPositive) {
		HashSet<Element> newRequirementsRelations = new HashSet<Element>();
		
		// new POSITIVE relations
		if (isPositive) {
			for (Element scenario : getScenarioWithNewPositiveRelationsToRequirements().keySet()) {
				HashSet<Element> requirements = getScenarioWithNewPositiveRelationsToRequirements().get(scenario);
				if (requirements != null) {
					for (Element requirement : requirements) {
						// Note, this is a HashSet, i.e. no duplicates!
						newRequirementsRelations.add(requirement);
					}
				}
			}
		}
		// new NEGATIVE relations
		else {
			for (Element scenario : getScenarioWithNewNegativeRelationsToRequirements().keySet()) {
				HashSet<Element> requirements = getScenarioWithNewNegativeRelationsToRequirements().get(scenario);
				if (requirements != null) {
					for (Element requirement : requirements) {
						// Note, this is a HashSet, i.e. no duplicates!
						newRequirementsRelations.add(requirement);
					}
				}
			}
		}
		
		return newRequirementsRelations.size();
	}
	
	public Dependency getExistingDependency(Element scenario, Element requirement, String dependencyStereotypeQName){
		
		EList<Dependency> depList = ((Class)scenario).getClientDependencies();
		for (Dependency dependency : depList) {
			if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null )  {
				EList<Element> targets = dependency.getTargets();
				if (targets.contains(requirement)) {
					return dependency;
				}
			}
		}
		return null;
	}

	public boolean isRequirementEvaluatedInScenario(Element requirement, Element scenario){
		// if requirement was evaluated with the given scenario 
		HashSet<Element> requirements = getScenarioToEvaluatedRequirements().get(scenario);
		if (requirements != null) {
			if (requirements.contains(requirement)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isRequirementViolatedInScenario(Element requirement, Element scenario){
		// if requirement was violated with the given scenario 
		HashSet<Element> requirements = getScenarioToViolatedRequirements().get(scenario);
		if (requirements != null) {
			if (requirements.contains(requirement)) {
				return true;
			}
		}
		return false;
	}
	
	
	// Getters
	public HashSet<Element> getGeneratedModels() {
		return generatedModels;
	}
	
	public HashSet<TreeObject> getScenarios(Element simModel){
		return modelToScenarios.get(simModel);
	}
	
	public HashSet<TreeObject> getRequirements(Element simModel){
		return modelToRequirements.get(simModel);
	}

	public HashSet<String> getEvaluatedRequirements() {
		return evaluatedRequirements;
	}

	public void addToEvaluatedRequirements(String string) {
		this.evaluatedRequirements.add(string);
	}

	public HashSet<String> getViolatedRequirements() {
		return violatedRequirements;
	}

	public void addToViolatedRequirements(String string) {
		this.violatedRequirements.add(string);
	}

	public HashMap<TreeObject, HashSet<TreeObject>> getNewPositiveRelations() {
		return newPositiveRelations;
	}
	
	public void addToPositiveRelations(TreeObject key, TreeObject value) {
		addToMapList(newPositiveRelations, key, value);
	}

	public HashMap<TreeObject, HashSet<TreeObject>> getNewNegativeRelations() {
		return newNegativeRelations;
	}

	public void addToNegativeRelations(TreeObject key, TreeObject value) {
		addToMapList(newNegativeRelations, key, value);
	}

	public HashSet<String> getNotViolatedRequirements() {
		return notViolatedRequirements;
	}

	public void addToNotViolatedRequirements(String string) {
		notViolatedRequirements.add(string);
	}

	public HashSet<String> getNotEvaluatedRequirements() {
		return notEvaluatedRequirements;
	}
	
	public void addToNotEvaluatedRequirements(String string) {
		notEvaluatedRequirements.add(string);
	}

	public List<String> getSimulationFailedList() {
		return this.simulationFailedList;
	}

	public void setSimulationFailedList(List<String> simulationFailedList) {
		this.simulationFailedList = simulationFailedList;
	}

	public HashMap<Element, HashSet<Element>> getScenarioToViolatedRequirements() {
		return scenarioToViolatedRequirements;
	}

	public HashMap<Element, HashSet<Element>> getScenarioToEvaluatedRequirements() {
		return scenarioToEvaluatedRequirements;
	}
	
	public HashMap<Element, HashSet<Element>> getScenarioToNotEvaluatedRequirements() {
		return scenarioToNotEvaluatedRequirements;
	}
	
	public HashMap<Element, HashSet<Element>> getScenarioWithNewPositiveRelationsToRequirements() {
		return scenarioWithNewPositiveRelationsToRequirements;
	}

	public void setScenarioWithNewPositiveRelationsToRequirements(
			HashMap<Element, HashSet<Element>> scenarioWithNewPositiveRelationsToRequirements) {
		this.scenarioWithNewPositiveRelationsToRequirements = scenarioWithNewPositiveRelationsToRequirements;
	}

	public HashMap<Element, HashSet<Element>> getScenarioWithNewNegativeRelationsToRequirements() {
		return scenarioWithNewNegativeRelationsToRequirements;
	}

	public void setScenarioWithNewNegativeRelationsToRequirements(
			HashMap<Element, HashSet<Element>> scenarioWithNewNegativeRelationsToRequirements) {
		this.scenarioWithNewNegativeRelationsToRequirements = scenarioWithNewNegativeRelationsToRequirements;
	}
	
	public GeneratorVeMScenariosBased getGenerator() {
		return generator;
	}

	public void setGenerator(GeneratorVeMScenariosBased generator) {
		this.generator = generator;
	}
	
	public HashMap<Element, HashSet<TreeObject>> getModelToSystemModel() {
		return modelToSystemModel;
	}

	public void setModelToSystemModel(
			HashMap<Element, HashSet<TreeObject>> modelToSystemModel) {
		this.modelToSystemModel = modelToSystemModel;
	}

	public HashSet<Element> getNotSimulatedModels() {
		return notSimulatedModels;
	}

	public void setNotSimulatedModels(HashSet<Element> notSimulatedModels) {
		this.notSimulatedModels = notSimulatedModels;
	}
	
	
	public HashSet<Element> getSystemModels() {
		return systemModels;
	}
	
	public void setGeneratedModels(HashSet<Element> generatedModels) {
		this.generatedModels = generatedModels;
	}

	public void setSystemModels(HashSet<Element> systemModels) {
		this.systemModels = systemModels;
	}

	public HashSet<Element> getAllFoundRequirements() {
		return getVerificationDataCollector().getAllRequirements();
	}
	
	public HashSet<Element> getAllFoundScenarios() {
		return getVerificationDataCollector().getAllScenarios();
	}

	public HashSet<Element> getAllFoundMediators() {
		return getVerificationDataCollector().getAllMediators();
	}
	public HashMap<Element, ClassInstantiation> getPreparedInstantiations() {
		return preparedInstantiations;
	}

	public void setPreparedInstantiations(HashMap<Element, ClassInstantiation> preparedInstantiations) {
		this.preparedInstantiations = preparedInstantiations;
	}

	public VerificationDataCollector getVerificationDataCollector() {
		return verificationDataCollector;
	}

	public void setVerificationDataCollector(VerificationDataCollector verificationDataCollector) {
		this.verificationDataCollector = verificationDataCollector;
	}

	public Element getGeneratedModelsPackage() {
		return generatedModelsPackage;
	}

	public void setGeneratedModelsPackage(Element generatedModelsPackage) {
		this.generatedModelsPackage = generatedModelsPackage;
	}

	public HashMap<Element,String> getSimulationResultsFile() {
		return simulationResultsFile;
	}

	public void setSimulationResultsFile(HashMap<Element,String> simulationResultsFile) {
		this.simulationResultsFile = simulationResultsFile;
	}

	public HashSet<Element> getSimulatedModels() {
		return simulatedModels;
	}

	public void setSimulatedModels(HashSet<Element> simulatedModels) {
		this.simulatedModels = simulatedModels;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

}
