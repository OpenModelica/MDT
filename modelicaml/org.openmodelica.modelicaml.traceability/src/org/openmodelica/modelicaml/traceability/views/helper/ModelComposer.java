package org.openmodelica.modelicaml.traceability.views.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.helper.datacollection.VerificationScenariosCollector;
import org.openmodelica.modelicaml.helper.generators.CreatorValueBinding;

public class ModelComposer {
	
	// The selected system design model 
	private Element systemModel;
	
	// Any models, e.g. requirements, that should be instantiated with the system model
	private List<Element> modelsToBeInstantiated = new ArrayList<Element>();

	// Only the requirements (sub set of models to be instantiated)
	private List<Element> requirementsToBeInstantiated = new ArrayList<Element>();

	//Any model that was considered for the required models search
	private HashSet<Element> alreadyConsideredForAdditionalModelsSearch = new HashSet<Element>();

	// all models that were found in the defined root model/package that have models that are required in addition
	private HashMap<Element, HashSet<Element>> allModelsAndTheirRequiredModelsFound = new HashMap<Element, HashSet<Element>>();

	//Set of models that constitutes the combination to be analyzed
	private HashSet<Element> initialSetOfModels = new HashSet<Element>();

	// Package within the models should be created
	private Package valueBindingsPackage;

	// All models that was found by required additional models search
	private HashSet<Element> allCollectedAdditionalModels = new HashSet<Element>();

	// Models that are found by the additional models search and that should always be instantiated.
	private HashSet<Element> alwaysInclude = new HashSet<Element>();
	
	// All models that were found in the defined root model/package that should always be instantiated
	private HashSet<Element> allAlwaysIncludeFound = new HashSet<Element>();
	
//	private VerificationDataCollector ec;

	// All instantiated models
	private HashSet<TreeParent> allModelInstantiations = new HashSet<TreeParent>();

	// All instantiated models
	private HashMap<Element, ClassInstantiation> modelToItsInstantiation = new HashMap<Element, ClassInstantiation>();

	// Virtual instantiation root. Its direct children are the instantiation roots of all models provided and collected. 
	private TreeParent virtualInstantiationTreeRoot;
	
	// contains tree objects (instantiation roots of models) with all mandatory clients that are NOT satisfied.
	private HashMap<TreeParent, HashSet<TreeObject>> requiredClients_unsatisfied = new HashMap<TreeParent, HashSet<TreeObject>>();
	
	// collector of verification data (scenarios, requirements etc.)
	private VerificationScenariosCollector collector;
	
	// internal log
	private String log;
	
	
	public ModelComposer(
			NamedElement sourceModel,
			List<Element> modelsToBeInstantiated, 
			Package valueBindingsPackage,
			Element umlRoolModel,
			HashMap<Element, ClassInstantiation> preparedModelsToInstantiations,
			VerificationScenariosCollector collector){
		
		this.systemModel = sourceModel;
		this.valueBindingsPackage = valueBindingsPackage;
		this.collector = collector;
		
		if (this.systemModel instanceof Class) {
			findAdditionModels((Class) this.systemModel);
		}
		
		// use the pre-instantiated models in order to avoid instantiating models multiple times
		if (preparedModelsToInstantiations != null) {
			getModelToItsInstantiation().putAll(preparedModelsToInstantiations);
		}
		
		this.modelsToBeInstantiated.addAll(modelsToBeInstantiated);
		// TODO: what to do if there are not only requirements?
		for (Element element : modelsToBeInstantiated) {
			if (element instanceof Class && element.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
				this.requirementsToBeInstantiated.add(element);
				findAdditionModels((Class) element);
			}
		}
		
		this.initialSetOfModels.addAll(this.modelsToBeInstantiated); 
		this.initialSetOfModels.add(systemModel);
		
		
//		this.ec = new VerificationDataCollector(umlRoolModel);
		
		// virtual instantiation root 
		virtualInstantiationTreeRoot = new TreeParent("Virtual Instantiation", null, null, "", false, true, null, null, true);
		
		// instantiate all models in order to enable checks
		HashSet<Element> allModels = new HashSet<Element>();
		allModels.addAll(initialSetOfModels);
		allModels.addAll(allCollectedAdditionalModels);
		
		instantiateAll(allModels);
		
		// internal log
		initializeLog();
	}

	
	private void instantiateAll(HashSet<Element> models){

		// remove all instantiations from the root
		for (TreeParent instantiatedModel : allModelInstantiations) {
			virtualInstantiationTreeRoot.removeChild(instantiatedModel);
		}

		// new instantiations
		for (Element model : models) {
			if (model instanceof Class) {

				TreeParent newChild = null;
				
				/*
				 * First look if the graph for this model was already created (i.e. if there is an instantiation available for that model)
				 * If not -> create a new instantiation
				 */
				if ( modelToItsInstantiation.get(model) != null) {
					newChild = getModelToItsInstantiation().get(model).getTreeRoot();
				}
				else {
					ClassInstantiation ci_model = new ClassInstantiation((Class) model, true, false, collector.getAllMediators(), false);
					ci_model.createTree();
					ci_model.collectBindingsDataFromUmlModel();
					newChild = ci_model.getTreeRoot();
					
					// add to model -> its instantiation map
					modelToItsInstantiation.put(model, ci_model);
				}

				// add the instantiated model to the root
				virtualInstantiationTreeRoot.addChild(newChild);
				
				// add  the instantiation object to the map. 
				allModelInstantiations.add(newChild);
			}
		}
	}
	
	private void addToLog(String msg){
		this.log = this.log + msg + "\n";
	}
	
	public HashSet<Element> getAdditionallModels(Element model, boolean prune){
		if (!prune) {
			return collector.getModelToItsAdditionalModels().get(model);
		}
		HashSet<Element> additionalModels = new HashSet<Element>();
		HashSet<Element> collectedAdditionalModels = collector.getModelToItsAdditionalModels().get(model);
		if (collectedAdditionalModels != null) {
			for (Element additionalModel : collectedAdditionalModels) {
				TreeParent additionalModelInstantiation = this.modelToItsInstantiation.get(additionalModel).getTreeRoot();
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
		}
		
		return additionalModels;
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
	
	
	public HashSet<TreeObject> getClients(TreeParent treeParent){
		HashSet<TreeObject> clients = new HashSet<TreeObject>();
		for (TreeObject treeObject : getAllTreeItems(treeParent)) {
			if (treeObject.isValueClient()) {
				clients.add(treeObject);
			}
		} 
		return clients;
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
	
	private boolean isAtLeastOneProviderUsed(TreeParent virtualInstantiationTreeRoot, TreeParent treeParentToStartTheCheckOn){
		
		boolean atLeastOneProviderIsUsed = false;
		TreeObject[] children = virtualInstantiationTreeRoot.getChildren();
		
		for (int i = 0; i < children.length; i++) {
			TreeObject modelInstnatiationTreeRootItem = children[i];

			// discard itself
			if (modelInstnatiationTreeRootItem != treeParentToStartTheCheckOn && modelInstnatiationTreeRootItem instanceof TreeParent) {
				CreatorValueBinding vbc = new CreatorValueBinding();
				vbc.setAllMediators(collector.getAllMediators());
				
				/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
				 * so that no modifications are created in components because we only want to analyze possible bindings.
				 */
				vbc.updateAllBindings(valueBindingsPackage, null, (TreeParent) modelInstnatiationTreeRootItem, virtualInstantiationTreeRoot, false, true, false, true);
				atLeastOneProviderIsUsed = treeContainsOneOf(vbc.getUsedProviders(),treeParentToStartTheCheckOn);

				// stop the check as soon as at least one used provider was found.
				if (atLeastOneProviderIsUsed) {
					return true;
				}
			}
		}
		
		return atLeastOneProviderIsUsed;
	}
	
	
	public boolean areAllRequiredClientsSatisfied(TreeParent virtualInstantiationTreeRoot, TreeParent treeParentToStartTheCheckOn){
		
		/* 
		 * Get the list of clients for which the code could be derived (even if a user interaction would be necessary) 
		*/
		CreatorValueBinding vbc = new CreatorValueBinding();
		vbc.setAllMediators(collector.getAllMediators());
		
		/* Note, the updateAllBindings() is called with the last argument simulateOnly = true  
		 * so that no modifications are created in components because we only want to analyze possible bindings.
		 */
		boolean allRequiredClientsAreSatisfied = true;
		
		vbc.updateAllBindings(valueBindingsPackage, null, treeParentToStartTheCheckOn, virtualInstantiationTreeRoot, false, true, false, true);
		
		if ( vbc.getAllMandatoryClientsFound().size() > 0 
				&& !vbc.getAllClientsWithPossibleBindingCodeDerivation().containsAll(vbc.getAllMandatoryClientsFound())) {
			allRequiredClientsAreSatisfied = false;
		}
		
		// Collect all mandatory clients that are not satisfied.
		HashSet<TreeObject> unsatisfiedRequiredClients = new HashSet<TreeObject>();
		unsatisfiedRequiredClients.addAll(vbc.getAllMandatoryClientsFound());
		unsatisfiedRequiredClients.removeAll(vbc.getAllClientsWithPossibleBindingCodeDerivation());

		if (unsatisfiedRequiredClients.size() > 0 ) {
			requiredClients_unsatisfied.put(treeParentToStartTheCheckOn, unsatisfiedRequiredClients);
		}
		
		return allRequiredClientsAreSatisfied;
	}
	
	
	
	
	private HashSet<Element> findAdditionModels(Class sourceElement){
		
		HashSet<Element> collectedAdditionalModels = new HashSet<Element>();
		
		// if this model was not yet considered
		if (!alreadyConsideredForAdditionalModelsSearch.contains(sourceElement)) {

			HashSet<Element> allModelsInInstantiationTree = new HashSet<Element>();

			TreeParent sourceElementInstantiated;
			/*
			 * First look if the graph for this model was already created (i.e. if there is an instantiation available for that model)
			 * If not -> create a new instantiation
			 */
			if ( modelToItsInstantiation.get(sourceElement) != null) {
				sourceElementInstantiated = getModelToItsInstantiation().get(sourceElement).getTreeRoot();
			}
			else {
				ClassInstantiation ci_model = new ClassInstantiation((Class) sourceElement, true, false, collector.getAllMediators(), false);
				ci_model.createTree();
				ci_model.collectBindingsDataFromUmlModel();
				
				sourceElementInstantiated = ci_model.getTreeRoot();
				
				// add to model -> its instantiation map
				modelToItsInstantiation.put(sourceElement, ci_model);
			}
			
			allModelsInInstantiationTree.addAll(getAllTreeItemsClasses(sourceElementInstantiated));
			
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
	
	
	public HashSet<TreeObject> getUnsatisfiedRequiredClients(Element model){
		if (model != null) {
			TreeParent modelInstantiation = modelToItsInstantiation.get(model).getTreeRoot();
			
			if (modelInstantiation != null) {
				HashSet<TreeObject> unsatisfiedClients = requiredClients_unsatisfied.get(modelInstantiation);

				if (unsatisfiedClients != null && unsatisfiedClients.size() > 0 ) {
					return unsatisfiedClients;
				}
			}
		}
		return null;
	}
	
	private void initializeLog(){
		this.log = "\n----------------------------------------------" +
		"---------------------------------------------------" +
		"--------------------------------------------------- \n" +
		"Log for:" +
			"\n   - System Model '" + ((NamedElement)this.systemModel).getQualifiedName() + "'" +
			"\n";
	}
	
	
	
	public TreeParent getVirtualInstantiationTreeRoot() {
		return virtualInstantiationTreeRoot;
	}

	public HashMap<Element, ClassInstantiation> getModelToItsInstantiation() {
		return modelToItsInstantiation;
	}
}
