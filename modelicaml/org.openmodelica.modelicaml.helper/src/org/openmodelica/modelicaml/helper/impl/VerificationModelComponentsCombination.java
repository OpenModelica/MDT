package org.openmodelica.modelicaml.helper.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;

public class VerificationModelComponentsCombination {
	/* 
	 * Package were to search for value mediators  
	 */
	private Package valueMediatorsPackage;
	
	/* 
	 * Selected system model to generate a simulation model.
	 * The generated simulation model will include one verification scenario, 
	 * all requirements that can be verified using this verification scenario,
	 * all models that are required in addition. 
	 */
	private Element systemModel;


	/*
	 * All required models that are referenced (directly or indirectly) by the selected system model 
	 */
	private HashSet<Element> requiredModels_systemModel = new HashSet<Element>();

	/* 
	 * verification scenario that is used to stimulate the system model
	 */
	private Element testScenario;



	/*
	 * All required models that are referenced (directly or indirectly) by the verification scenario model 
	 */
	private HashSet<Element> requiredModels_testScenario = new HashSet<Element>();
	
	/*
	 * A set of requirements to be verified
	 */
	private HashSet<Class> requirements = new HashSet<Class>();

	/*
	 * All models that are required in addition based on the given set of requirements to be verified.
	 */
	private HashMap<Element, HashSet<Element>> requiredModels_requirements = new HashMap<Element, HashSet<Element>>();

	// log messages
	private String log = "";

	//Set of models that constitutes the combination to be analyzed
	private HashSet<Element> initialSetOfModels = new HashSet<Element>();

	// All models that was found by required additional models search
	private HashSet<Element> allCollectedAdditionalModels = new HashSet<Element>();

	//Models that are found by the additional models search and that should always be instantiated.
	private HashSet<Element> alwaysInclude = new HashSet<Element>();

	//Any model that was considered for the required models search
	private HashSet<Element> alreadyConsideredForAdditionalModelsSearch = new HashSet<Element>();
	
	// all models that were found in the defined root model/package that should always be instantiated
	private HashSet<Element> allAlwaysIncludeFound = new HashSet<Element>();
	// all models that were found in the defined root model/package that have models that are required in addition
	private HashMap<Element, HashSet<Element>> allModelsAndTheirRequiredModelsFound = new HashMap<Element, HashSet<Element>>();
	
	// All instantiated models
	private HashSet<TreeParent> allModelInstantiations = new HashSet<TreeParent>();

	// All instantiated models
	private HashMap<Element, TreeParent> modelToItsInstantiation = new HashMap<Element, TreeParent>();

	// Virtual instantiation root. Its direct children are the instantiation roots of all models provided and collected. 
	private TreeParent virtualInstantiationTreeRoot;

	// contains tree objects (instantiation roots of models) with all mandatory clients that are NOT satisfied.
	private HashMap<TreeParent, HashSet<TreeObject>> requiredClients_unsatisfied = new HashMap<TreeParent, HashSet<TreeObject>>();



	/*
	 *  Indicates if this combination is discarded.
	 *  The combination should be discarded if not all mandatory clients of the system model are satisfied
	 *  or if none of the verification scenario providers is used in the virtual instantiation.
	 */
	private boolean isDiscarded = false;

	/*
	 *  Indicates that an error occurred during analysis
	 */
	private boolean isError = false;
	
	public VerificationModelComponentsCombination(Class systemModel, 
			Class testScenario, 
			HashSet<Class> requirements,
			Package valueMediatorsPackage,
			HashSet<Element> allAlwaysIncludeFound,
			HashMap<Element, HashSet<Element>> allModelsAndTheirRequiredModelsFound){
		
		this.systemModel = systemModel;
		this.testScenario = testScenario;
		this.requirements = requirements;
		this.valueMediatorsPackage = valueMediatorsPackage;
		
		this.allAlwaysIncludeFound.addAll(allAlwaysIncludeFound);
		this.allModelsAndTheirRequiredModelsFound.putAll(allModelsAndTheirRequiredModelsFound);
		
		// initial information for the log
		initializeLog();
		
		/*
		 *  Initial set of models provided.
		 *  This is used to indicate that these models should not be considered as additional models.   
		 */
		initialSetOfModels.add(systemModel);
		initialSetOfModels.add(testScenario);
		initialSetOfModels.addAll(requirements);
		
		// collect all models that are required in addition (deep search)
		requiredModels_systemModel.addAll(findAdditionModels(systemModel));
		requiredModels_testScenario.addAll(findAdditionModels(testScenario));
		
		for (Class requirement : requirements) {
			requiredModels_requirements.put(requirement, findAdditionModels(requirement));
		}

		// virtual instantiation root 
		virtualInstantiationTreeRoot = new TreeParent("Virtual Instantiation", null, null, "", false, true, null, null, true);
		
		// instantiate all models in order to enable checks
		HashSet<Element> allModels = new HashSet<Element>();
		allModels.addAll(initialSetOfModels);
		allModels.addAll(allCollectedAdditionalModels);
		instantiateAll(allModels);
		
		// validate this combination (checks only the system model and the verification scenario)
		validateCombination();
		
		// validate requirements 
		validateRequirementsConsistency();
	}
	
	
	private HashSet<Element> findAdditionModels(Class sourceElement){
		
		HashSet<Element> collectedAdditionalModels = new HashSet<Element>();
		
		// if this model was not yet considered
		if (!alreadyConsideredForAdditionalModelsSearch.contains(sourceElement)) {
			
			//Instantiate the source model in order to collect all classes that are used in its instance tree
			ClassInstantiation ci_sourceModel = new ClassInstantiation(sourceElement, true);	
			HashSet<Element> allModelsInInstantiationTree = new HashSet<Element>();
			ci_sourceModel.createTree();
			
			allModelsInInstantiationTree.addAll(getAllTreeItemsClasses(ci_sourceModel.getTreeRoot()));
			
			// mark all classes collected from the instantiation tree in order to avoid dead lock search
			alreadyConsideredForAdditionalModelsSearch.addAll(allModelsInInstantiationTree);
			
			// find additional models that are referenced directly by one of the class found in the instantiation tree
			for (Element element : allModelsInInstantiationTree) {
				if (element instanceof NamedElement) {

					HashSet<Element> requiredModels = this.allModelsAndTheirRequiredModelsFound.get(element);
					if (requiredModels != null) {
						for (Element requiredModelFound : requiredModels) {
							
							// check if this model should be discarded.
							if (initialSetOfModels.contains(requiredModelFound)) {
									if (requiredModelFound instanceof NamedElement) {
									
									String message = "DISCARDED (01): " +
											"The additional model search found '"+((NamedElement)requiredModelFound).getQualifiedName()
											+ "'\n referenced by '"+((NamedElement)element).getQualifiedName()+"'.\n"
											+ "This model was already found once. This reference is discarded.";
									
									addToLog(message);
								}
							}
							else {
								// add to the collected additional models 
								collectedAdditionalModels.add( requiredModelFound );
								
								// add to the overall list of collected additional models
								allCollectedAdditionalModels.add( requiredModelFound );
								
								if (allAlwaysIncludeFound.contains( requiredModelFound )) {
									alwaysInclude.add( requiredModelFound );
								}
							}
						}
					}
					
//					for (Element dep : ((NamedElement)element).getClientDependencies()) {
//						Stereotype s = dep.getAppliedStereotype(Constants.stereotypeQName_Requires);
//						if (s != null) {
////							Object list = dep.getValue(s, Constants.propertyName_onlyIncombinationWith);
//							Object isAlways = dep.getValue(s, Constants.propertyName_always);
//
//							Element modelFound = ((Dependency)dep).getTargets().get(0);
//							
//							// check if this model should be discarded.
//							if (initialSetOfModels.contains(modelFound)) {
//								if (modelFound instanceof NamedElement) {
//									
//									String message = "DISCARDED (01): " +
//											"The additional model search found '"+((NamedElement)modelFound).getQualifiedName()
//											+ "'\n referenced by '"+((NamedElement)element).getQualifiedName()+"'.\n"
//											+ "This model was already found once. This reference is discarded.";
//									
//									addToLog(message);
//								}
//							}
//							else {
//								// add to the collected additional models 
//								collectedAdditionalModels.add(modelFound );
//								
//								// add to the overall list of collected additional models
//								allCollectedAdditionalModels.add( modelFound );
//								
//								if (isAlways instanceof Boolean && (Boolean)isAlways) {
//									alwaysInclude.add(modelFound);
//								}
//							}
//						}
//					}
				}
			}
			
			// recursive call -> iterate over all collected models and find other models that are required in addition
			for (Element newSourceModel : collectedAdditionalModels) {
				if (newSourceModel instanceof Class) {
					collectedAdditionalModels.addAll(findAdditionModels((Class) newSourceModel));	
				}
			}
		}
		
		return collectedAdditionalModels;
	}
	
	private void instantiateAll(HashSet<Element> models){

		// remove all instantiations from the root
		for (TreeParent instantiatedModel : allModelInstantiations) {
			virtualInstantiationTreeRoot.removeChild(instantiatedModel);
		}

		// clear the list
		allModelInstantiations.clear();
		modelToItsInstantiation.clear();
		
		// new instantiations
		for (Element model : models) {
			if (model instanceof Class) {

				// instantiate model
				ClassInstantiation ci_model = new ClassInstantiation((Class) model, true);
				ci_model.createTree();
				
				// add the instantiated model to the root
				virtualInstantiationTreeRoot.addChild(ci_model.getTreeRoot());
				
				// add  the instantiation object to the map. 
				allModelInstantiations.add(ci_model.getTreeRoot());
				
				// add to model -> its instantiation map
				modelToItsInstantiation.put(model, ci_model.getTreeRoot());
			}
		}
	}
	
	private void validateCombination(){
		// Note, isDiscarded is false by default. There is no need to set it to false.
		
		TreeParent systemModelInstantiationTreeRoot = modelToItsInstantiation.get(this.systemModel);
		TreeParent testScenarioInstantiationTreeRoot = modelToItsInstantiation.get(this.testScenario);
		
		if (systemModelInstantiationTreeRoot != null && testScenarioInstantiationTreeRoot != null) {
			if (!areAllRequiredClientsSatisfied(virtualInstantiationTreeRoot, systemModelInstantiationTreeRoot)) {

				setDiscarded(true);
				String message = "DISCARDED(02): The combination " +
						"\n   - Scenario: '" + ((NamedElement)this.testScenario).getQualifiedName() + "'" +
						"\n   - System Model: '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
						"\nis discarded because the following mandatory clients of the system model are not satisfied: " +
						"\n" + getClientsDotPathAsString(requiredClients_unsatisfied.get(systemModelInstantiationTreeRoot));
				addToLog(message);
			}
			if (!isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, testScenarioInstantiationTreeRoot)) {
				
				setDiscarded(true);
				String message = "DISCARDED(03): The combination " +
						"\n   - Test Scneario: '" + ((NamedElement)this.testScenario).getQualifiedName() + "'" +
						"\n   - System Model: '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
						"\nis discarded because none of the scenario providers is used to stimulate the model. ";
				addToLog(message);
			}
		}
		else {

			String message = "NOT VALID(04): The system model or the scenario are not found.";
			addToLog(message);
			
			setDiscarded(true);
			setError(true);
		}
	}
	
	private void validateRequirementsConsistency(){
		for (Element requirement : requirements) {
			TreeParent requirementInstantiationTreeRootItem = modelToItsInstantiation.get(requirement);
			if (requirementInstantiationTreeRootItem != null) {
				// this method validates and collects the unsatisfied mandatory clients at the same time.
				areAllRequiredClientsSatisfied(virtualInstantiationTreeRoot, requirementInstantiationTreeRootItem);
				
				// add unsatisfied mandatory clients to log
				if (getUnsatisfiedRequiredClients(requirement) != null) {
					String message = "PROBLEM(05): Requirement " + ((NamedElement)requirement).getQualifiedName() + "'" +
					"\n has the following mandatory clients which are not satisfied: " +
					"\n" +getClientsDotPathAsString(getUnsatisfiedRequiredClients(requirement));
					
					addToLog(message);
				}
			}
		}
	}
	
	private boolean isAtLeastOneProviderUsed(TreeParent virtualInstantiationTreeRoot, TreeParent treeParentToStartTheCheckOn){
		
		boolean atLeastOneProviderIsUsed = false;
		TreeObject[] children = virtualInstantiationTreeRoot.getChildren();
		
		for (int i = 0; i < children.length; i++) {
			TreeObject modelInstnatiationTreeRootItem = children[i];

			// discard itself
			if (modelInstnatiationTreeRootItem != treeParentToStartTheCheckOn && modelInstnatiationTreeRootItem instanceof TreeParent) {
				ValueBindingCreator vbc = new ValueBindingCreator();
			
				/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
				 * so that no modifications are created in components because we only want to analyze possible bindings.
				 */
				vbc.updateAllBindings(valueMediatorsPackage, (TreeParent) modelInstnatiationTreeRootItem, virtualInstantiationTreeRoot, false, true, false, true);
				atLeastOneProviderIsUsed = treeContainsOneOf(vbc.getUsedProviders(),treeParentToStartTheCheckOn);

				// stop the check as soon as at least one used provider was found.
				if (atLeastOneProviderIsUsed) {
					return true;
				}
			}
		}
		
		return atLeastOneProviderIsUsed;
	}
	
	private boolean areAllRequiredClientsSatisfied(TreeParent virtualInstantiationTreeRoot, TreeParent treeParentToStartTheCheckOn){
		
		/* 
		 * Get the list of clients for which the code could be derived (even if a user interaction would be necessary) 
		*/
		ValueBindingCreator vbc = new ValueBindingCreator();
		
		/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
		 * so that no modifications are created in components because we only want to analyze possible bindings.
		 */
		boolean allRequiredClientsAreSatisfied = true;
		
		vbc.updateAllBindings(valueMediatorsPackage, treeParentToStartTheCheckOn, virtualInstantiationTreeRoot, false, true, false, true);
		
		if ( vbc.getAllRequiredClientsFound().size() > 0 
				&& !vbc.getAllClientsWithPossibleBindingCodeDerivation().containsAll(vbc.getAllRequiredClientsFound())) {
			allRequiredClientsAreSatisfied = false;
		}
		
		// Collect all mandatory clients that are not satisfied.
		HashSet<TreeObject> unsatisfiedRequiredClients = new HashSet<TreeObject>();
		unsatisfiedRequiredClients.addAll(vbc.getAllRequiredClientsFound());
		unsatisfiedRequiredClients.removeAll(vbc.getAllClientsWithPossibleBindingCodeDerivation());

		if (unsatisfiedRequiredClients.size() > 0 ) {
			requiredClients_unsatisfied.put(treeParentToStartTheCheckOn, unsatisfiedRequiredClients);
		}
		
		return allRequiredClientsAreSatisfied;
	}
	
	
	public void removeNotUsedRequirements(HashSet<Element> newRequirementsSubSet){
		
		HashSet<Element> notUsedRequirements = new HashSet<Element>();
		// add all requirements
		notUsedRequirements.addAll(this.requirements);
		// remove all form the new set in order to get the remaining requirements that are not used and should be removed. 
		notUsedRequirements.removeAll(newRequirementsSubSet);
		
		// remove all instantiations.
		for (Element requirement : notUsedRequirements) {
			TreeParent requirementInstantiation = modelToItsInstantiation.get(requirement);
			if (requirementInstantiation != null) {
				this.virtualInstantiationTreeRoot.removeChild(requirementInstantiation);	
			}
			// add to log.
			String message = "REMOVED(06): Requirement '" + ((NamedElement)requirement).getQualifiedName()+ "' was unselected and is removed.";
			addToLog(message);
		}
		// remove the elements
		this.requirements.removeAll(notUsedRequirements);
	}
	
	public HashSet<Element> getAdditionalSystemModelModels(boolean prune){
		if (!prune) {
			return this.requiredModels_systemModel;
		}
		HashSet<Element> additionalModels = new HashSet<Element>();
		for (Element additionalModel : this.requiredModels_systemModel) {
			TreeParent additionalModelInstantiation = this.modelToItsInstantiation.get(additionalModel);
			if (additionalModelInstantiation != null) {
				if (alwaysInclude.contains(additionalModel) 
						|| isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, additionalModelInstantiation)) {
					additionalModels.add(additionalModel);
				}
				else { // -> prune this model
					String message = "REMOVED(07): Additional Model '" + ((NamedElement)additionalModel).getQualifiedName() + "'" +
					"\nwas removed because none of its providers is used.";
					addToLog(message);
				}
			}
		}
		
		return additionalModels;
	}
	
	public HashSet<Element> getAdditionalTestScenarioModels(boolean prune){
		if (!prune) {
			return this.requiredModels_testScenario;
		}
		HashSet<Element> additionalModels = new HashSet<Element>();
		for (Element additionalModel : this.requiredModels_testScenario) {
			TreeParent additionalModelInstantiation = this.modelToItsInstantiation.get(additionalModel);
			if (additionalModelInstantiation != null) {
				if (alwaysInclude.contains(additionalModel) 
						|| isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, additionalModelInstantiation)) {
					additionalModels.add(additionalModel);
				}
				else { // -> prune this model
					String message = "REMOVED(08): Additional Model '" + ((NamedElement)additionalModel).getQualifiedName() + "'" +
					"\nwas removed because none of its providers is used.";
					addToLog(message);
				}
			}
		}
		
		return additionalModels;
	}
	
	public HashSet<Element> getAdditionalRequirementModels(Element requirement, boolean prune){
		if (!prune) {
			return this.requiredModels_requirements.get(requirement);
		}
		
		HashSet<Element> additionalModels = new HashSet<Element>();
		for (Element additionalModel : this.requiredModels_requirements.get(requirement)) {
			TreeParent additionalModelInstantiation = this.modelToItsInstantiation.get(additionalModel);
			if (additionalModelInstantiation != null) {
				if (alwaysInclude.contains(additionalModel) 
						|| isAtLeastOneProviderUsed(virtualInstantiationTreeRoot, additionalModelInstantiation)) {
					additionalModels.add(additionalModel);
				}
				else { // -> prune this model
					String message = "REMOVED(09): Additional Model '" + ((NamedElement)additionalModel).getQualifiedName() + "'" +
					"\nwas removed because none of its providers is used.";
					addToLog(message);
				}
			}
		}
		
		return additionalModels;
	}
	
	// Utls **********************************************************************
	
	public HashSet<TreeObject> getUnsatisfiedRequiredClients(Element model){
		if (model != null) {
			TreeParent modelInstantiation = modelToItsInstantiation.get(model);
			
			if (modelInstantiation != null) {
				HashSet<TreeObject> unsatisfiedClients = requiredClients_unsatisfied.get(modelInstantiation);

				if (unsatisfiedClients != null && unsatisfiedClients.size() > 0 ) {
					return unsatisfiedClients;
				}
			}
		}
		return null;
	}
	
	
	
	public String getClientsDotPathAsString(HashSet<TreeObject> items) {
		if (items != null) {
			String unsatisfiedRequiredClientList = "";
			
			List<String> dotPathList = new ArrayList<String>();
			for (TreeObject treeObject: items) {
				dotPathList.add(treeObject.getDotPath());
			}
			Collections.sort(dotPathList);
			
			for (String string : dotPathList) {
				unsatisfiedRequiredClientList = unsatisfiedRequiredClientList + "- " + string + "\n"; 
			}
			
			return unsatisfiedRequiredClientList;
		}
		return "";
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
		
		// If it is the root then add the root class
		if (treeParent.isRoot() && treeParent.getSelectedClass() != null) {
			allTreeItems.add(treeParent.getSelectedClass());
		}
		
		// add all classes used in children
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
	
	private void initializeLog(){
		this.log = "\n----------------------------------------------" +
		"---------------------------------------------------" +
		"--------------------------------------------------- \n" +
		"Log for the combination:" +
			"\n   - Scenario '" + ((NamedElement)this.testScenario).getQualifiedName() + "'" +
			"\n   - System Model '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
			"\n";
	}
	
	private void addToLog(String msg){
		this.log = this.log + "\n" + msg + "\n";
	}
	
	// Getter/Setter **************************************************************
	
	public Element getSystemModel() {
		return systemModel;
	}
	
	public Element getTestScenario() {
		return testScenario;
	}
	
	public String getLog() {
		return log;
	}
	
	public HashSet<Element> getRequiredModels_systemModel() {
		return requiredModels_systemModel;
	}

	public HashSet<Element> getRequiredModels_testScenario() {
		return requiredModels_testScenario;
	}

	public HashMap<Element, HashSet<Element>> getRequiredModels_requirements() {
		return requiredModels_requirements;
	}

	public HashSet<Element> getAllCollectedAdditionalModels() {
		return allCollectedAdditionalModels;
	}
	
	public HashSet<Element> getDiscardTheseModelsWhenCollectingAdditionalModels() {
		return initialSetOfModels;
	}
	
	public void setDiscardTheseModelsWhenCollectingAdditionalModels(
			HashSet<Element> discardTheseModelsWhenCollectingAdditionalModels) {
		this.initialSetOfModels = discardTheseModelsWhenCollectingAdditionalModels;
	}

	public void setDiscarded(boolean isDiscarded) {
		this.isDiscarded = isDiscarded;
	}

	public boolean isDiscarded() {
		return isDiscarded;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public boolean isError() {
		return isError;
	}
	

	public HashMap<TreeParent, HashSet<TreeObject>> getRequiredClients_unsatisfied() {
		return requiredClients_unsatisfied;
	}


	public HashSet<Class> getRequirements() {
		return requirements;
	}


	public void setRequirements(HashSet<Class> requirements) {
		this.requirements = requirements;
	}
	

	public HashMap<Element, TreeParent> getModelToItsInstantiation() {
		return modelToItsInstantiation;
	}
	
	public HashSet<Element> getAlwaysInclude() {
		return alwaysInclude;
	}
}
