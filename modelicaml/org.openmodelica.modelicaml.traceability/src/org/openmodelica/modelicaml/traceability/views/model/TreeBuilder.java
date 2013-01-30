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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.traceability.views.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.helper.datacollection.VerificationScenariosCollector;
import org.openmodelica.modelicaml.helper.structures.VeMScenarioReqCombinationsCreator;
import org.openmodelica.modelicaml.traceability.views.helper.ModelComposer;

public class TreeBuilder {
	private List<TreeObject> treeItems = new ArrayList<TreeObject>(); // created tree clients

	private Element rootPackage;

	private NamedElement selectedElement;
	private Element targetPackage;
	private Element requirementsPackage; 
	private Element scenariosPackage; 
	private Element valueMediatorsPackage;
	public final int MODE_REQUIREMENTS_VIEW = 0;
	public final int MODE_SCENARIOS_VIEW = 1;

	private TreeParent reqTreeRoot;
	private TreeParent scenTreeRoot;
	
	private UmlModel umlModel;
	
	private VerificationScenariosCollector vsc;

	// prepared instantiations of model in order to avoid instantiating models several times
	private HashMap<Element, ClassInstantiation> preparedModelInstantiations = new HashMap<Element, ClassInstantiation>();
	
	/*
	 * Possible combinations, each containing an initial set (1 system model, 1 test scenario  and n requirements) and 
	 * all additional model that are required by any of the initial set models. 
	 */
	private HashMap<Element, VeMScenarioReqCombinationsCreator> scenarioToVerificationModelCombination = new HashMap<Element, VeMScenarioReqCombinationsCreator>();
	
	public HashMap<Element, VeMScenarioReqCombinationsCreator> getScenarioToVerificationModelCombination() {
		return scenarioToVerificationModelCombination;
	}



	// all requirements that were collected from the specified requirements package
	private HashSet<Element> requirementsAll = new HashSet<Element>();
	
	// all scenarios that were selected for the selected system model
	private HashSet<Element> scenariosValid = new HashSet<Element>();
	public HashSet<Element> getScenariosValid() {
		return scenariosValid;
	}



	// all test scenarios that discarded because they cannot be used for stimulating the selected system model.
	private HashSet<Element> scenariosDiscarded = new HashSet<Element>();
	
	public HashSet<Element> getScenariosDiscarded() {
		return scenariosDiscarded;
	}



	/* all requirements that were selected for instantiation because 
	 * the selected test scenarios reference them and all their clients are satisfied by the selected system model
	 */
	private HashSet<Element> requirementsValid = new HashSet<Element>();
	
	public HashSet<Element> getRequirementsValid() {
		return requirementsValid;
	}



	/* all requirements that were discarded because  
	 * some of their clients are NOT satisfied by the selected combination of system model, scenario and all required additional models.
	 */
	private HashSet<Element> requirementsWithUnsatisfiedClients = new HashSet<Element>();
	
	public HashSet<Element> getRequirementsWithUnsatisfiedClients() {
		return requirementsWithUnsatisfiedClients;
	}
	
	/* Components with clients NOT satisfied by the selected combination of system model, scenario and all required additional models.
	 */
	private HashSet<Element> componentWithUnsatisfiedClients = new HashSet<Element>();
	
	public HashSet<Element> getComponentWithUnsatisfiedClients() {
		return componentWithUnsatisfiedClients;
	}



	private HashMap<Element,HashSet<Element>> reqToScenarios = new HashMap<Element, HashSet<Element>>();
	
	private String log = "";
	
	
	public void initialize(final NamedElement selectedElement){
		
		this.selectedElement = selectedElement;

		// get the UML model that is open in Papyrus.
		umlModel = UmlUtils.getUmlModel();
		
		if (umlModel != null ) {
			try {
				
				Element root = (Element) umlModel.lookupRoot();
				
				setRootPackage(root);
				targetPackage = root;
				requirementsPackage = root;
				scenariosPackage = root;
				valueMediatorsPackage = root;
				
//				// Set requirements selection options via user dialog
//				VeMGenerationOptionsDialog dialog = new VeMGenerationOptionsDialog(
//								new Shell(), 
//								(Element) selectedElement, 
//								targetPackage, 
//								requirementsPackage, 
//								scenariosPackage, 
//								valueMediatorsPackage,
//								Constants.MODE_VEM_GENERATION);
//				dialog.open();
//				
//				targetPackage = dialog.getTargetPackge();
//				requirementsPackage = dialog.getRequirementsPackage();
//				scenariosPackage = dialog.getScenariosPackage();
//				valueMediatorsPackage = dialog.getBindingsPackage();
				
			} catch (NotFoundException e) {
				e.printStackTrace();
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
						"Packages Selection", 
						"Cannot access the root model in editor. Please open die model in editor and try it again.");
			}
		}
		
		boolean allPackagesAreSet = targetPackage != null && requirementsPackage != null && scenariosPackage != null && valueMediatorsPackage != null;
		
		if (allPackagesAreSet) {
			
			ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(ModelicaMLServices.getShell());
			try {
				progressDialog.run(false, true, new IRunnableWithProgress() {
					
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,
							InterruptedException {
						
						collectData(monitor);
						
						monitor.beginTask("Creating view ...", 3);
						
						reqTreeRoot = new TreeParent(selectedElement.getName() + " - Requirement Clients Satisfaction");
						reqTreeRoot.setUmlElement(selectedElement);
						monitor.worked(1);
						
						monitor.subTask("Creating requirements view ...");
						// build tree for requirements view
						createRequirementsList(reqTreeRoot);
						monitor.worked(2);
						
						scenTreeRoot = new TreeParent(selectedElement.getName()+ " - Possible Scenarios");
						scenTreeRoot.setUmlElement(selectedElement);
						
						// build tree for scenarios view
						monitor.subTask("Creating scenarios view ...");
						createScenariosList(scenTreeRoot);
						monitor.worked(3);

						monitor.done();

					}
				});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				MessageDialog.openError(ModelicaMLServices.getShell(), "Invocation Error", "Could not invoke the generator to create combinations.");
			} catch (InterruptedException e) {
				e.printStackTrace();
				MessageDialog.openInformation(ModelicaMLServices.getShell(), "Interruption", "Create of combinations was interrupted.");
			}
		}
	}

	
	private void collectData(IProgressMonitor monitor){
		
		// clear all lists from previous iterations
		clearAll();
		
		monitor.beginTask("Collecting data ...", 1);
		
		// find all test scenarios
		vsc = new VerificationScenariosCollector();
		vsc.collectScenariosFromPackage((Package) scenariosPackage, true);
		if (vsc.getAllScenarios().size() == 0) {
			String message = "INFO: No scenarios were found.";
			addToLog(message);
		}
		
		monitor.worked(1);
		
		requirementsAll.addAll(vsc.getAllRequirements());

		// NOTE: there is no need to recollect mediators
		preparedModelInstantiations.put(selectedElement, ModelicaMLServices.getModelInstantiation(selectedElement, preparedModelInstantiations, getVsc().getAllMediators(), false));

		monitor.beginTask("Creating combinations...", vsc.getAllScenarios().size());
		int counter = 1;
		
		for (Element scenario : ModelicaMLServices.getSortedByName(vsc.getAllScenarios()) ) {
			
			if (scenario instanceof Class) {

				monitor.subTask("Creating combination " + counter + " of " + vsc.getAllScenarios().size());
				monitor.worked(counter);
				
				Class scenarioToBeUsed = (Class) scenario;
				// NOTE: there is no need to recollect mediators
				preparedModelInstantiations.put(scenarioToBeUsed, ModelicaMLServices.getModelInstantiation(scenarioToBeUsed, preparedModelInstantiations, getVsc().getAllMediators(), false));

				// get requirements, only those to which scenarios have relations
				HashSet<Element> reqList = vsc.getScenarioToReq().get(scenarioToBeUsed);
				HashSet<Element> requirementsToBeUsed = new HashSet<Element>();
				
				if (reqList != null) {
					 for (Element req : reqList) {
						if (req instanceof Class) {
							requirementsToBeUsed.add((Class) req);
						}
						else {
							String message = "NOT VALID: Requirement '" + req.toString() + "' is not a UML::Class.";
							addToLog(message);
						}
					}
				}
				else {
					String message = "INFO: No requirements are referenced by the scenario '" + ModelicaMLServices.getQualifiedName(scenarioToBeUsed) + "'";
					addToLog(message);
				}
				
				// NOTE: there is no need to recollect mediators
				preparedModelInstantiations.putAll(ModelicaMLServices.getModelInstantiations(requirementsToBeUsed, preparedModelInstantiations, getVsc().getAllMediators(), false));
				preparedModelInstantiations.putAll(ModelicaMLServices.getModelInstantiations(vsc.getAlwaysInclude(), preparedModelInstantiations, getVsc().getAllMediators(), false));
				
				VeMScenarioReqCombinationsCreator tsmc = new VeMScenarioReqCombinationsCreator((Class) selectedElement, 
						scenarioToBeUsed, 
						requirementsToBeUsed,
						(Package) valueMediatorsPackage,
						vsc.getAlwaysInclude(),
						vsc.getModelToItsAdditionalModels(),
						preparedModelInstantiations,
						vsc);
				
				// add to map
				scenarioToVerificationModelCombination.put(scenarioToBeUsed, tsmc);
				
				// add to selected or discarded scenarios
				if (!tsmc.isDiscarded()) {
					scenariosValid.add(scenarioToBeUsed);
					
					//if NOT all mandatory clients of the scenario are satisfied by the system model, requirements or additional models. 
					if (tsmc.getModelClients(scenarioToBeUsed) != null && tsmc.getUnsatisfiedMandatoryClients(scenarioToBeUsed) != null) {
						componentWithUnsatisfiedClients.add(scenarioToBeUsed);
					}
				}
				else {
					scenariosDiscarded.add(scenarioToBeUsed);
				}
				
				// add to selected or discarded requirements
				HashSet<Element> requirements = tsmc.getRequirements();
				for (Element requirement : requirements) {
					addToMapList(reqToScenarios, requirement, scenario);
					
					
					//if NOT all mandatory are satisfied 
					if (tsmc.getModelClients(requirement) != null && tsmc.getUnsatisfiedMandatoryClients(requirement) != null) {
						requirementsWithUnsatisfiedClients.add(requirement);
					}
					// if requirement has clients at all
					else if (tsmc.getModelClients(requirement) != null) {
						requirementsValid.add(requirement);
					}
				}

				// add log messages
				addToLog(tsmc.getLog().trim());
				
				// increase the counter for the progress monitor
				counter++;
			}
			else {
				String message = "NOT VALID: Scenario '" + ModelicaMLServices.getQualifiedName(selectedElement) + "' is not a UML::Class.";
				addToLog(message);
			}
		}
		
		monitor.done();
		
	}
	
	private void createScenariosList(TreeParent parent){
		
		for (Element element : scenariosValid) {
			NamedElement scenario = ((NamedElement)element);
			
			ScenarioItem scenarioItem = new ScenarioItem( scenario.getName() );
			scenarioItem.setUmlElement(scenario);
			scenarioItem.setValid(true);
			treeItems.add(scenarioItem);
			
			parent.addChild(scenarioItem);
			
			// TODO: add unsatisfied clients for the scenario
//			for (Element element : componentWithUnsatisfiedClients) {
//			}
			
			// Add requirements
			addRequirements(scenarioItem);

			// Add models that are needed for this scenario in addition
			addAdditionalModels(scenarioItem);
		}
		
		for (Element element : scenariosDiscarded) {
			NamedElement scenario = ((NamedElement)element);
			
			ScenarioItem scenarioItem = new ScenarioItem( scenario.getName() );
			scenarioItem.setUmlElement(scenario);
			scenarioItem.setValid(false);
			treeItems.add(scenarioItem);
			
			parent.addChild(scenarioItem);
			
			// Add requirements
			addRequirements(scenarioItem);

			// Add models that are needed for this scenario in addition?
			addAdditionalModels(scenarioItem);
		}
	}
	
	
	private void addRequirements(ScenarioItem scenarioItem){
		
		// add requirements
		VeMScenarioReqCombinationsCreator combination = scenarioToVerificationModelCombination.get(scenarioItem.getUmlElement());
		HashSet<Element> requirements = combination.getRequirements();
		
		if (requirements != null && requirements.size() > 0) {
			for (Element requirement : requirements) {
				
				RequirementItem requirementItem = new RequirementItem (getRequirementName((NamedElement) requirement));
				requirementItem.setUmlElement(requirement);
				treeItems.add(requirementItem);
				
				if (requirementsWithUnsatisfiedClients.contains(requirement)) {
					requirementItem.setValid(false);

					// add unsatisfied clients
					HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject,HashSet<Element>> unsatisfiedClients = getUnsatisfiedRequirementClients(requirement);
					if (unsatisfiedClients != null) {
						for (org.openmodelica.modelicaml.common.instantiation.TreeObject client : unsatisfiedClients.keySet()) {
							ClientItem clientItem = new ClientItem(client.getDotPath() + " = ?");
							clientItem.setUmlElement(client.getUmlElement());
							clientItem.setValid(false);
							treeItems.add(clientItem);
							
							requirementItem.addChild(clientItem);
						}
					}
				}
				else {
					requirementItem.setValid(true);
				}
				scenarioItem.addChild(requirementItem);
			}
		}
	}
	
	
	private void addAdditionalModels(TreeParent treeParent) {
		Element element = treeParent.getUmlElement();
		if (element != null ) {
			HashSet<Element> addModels = vsc.getModelToItsAdditionalModels().get(element);
			if (addModels != null && addModels.size() > 0) {
				for (Element addModel : addModels) {
					
					AddModelItem addModelItem = new AddModelItem("add. model: " + ModelicaMLServices.getName(addModel));
					addModelItem.setUmlElement(addModel);
					
					/*
					 * TODO: the additional model should be checked for bindings validity.
					 * For that one need to determine the right models to be instantiated and then 
					 * see if for all clients of this additional model bindings can be derived.
					 * This is not implemented yet. 
					 */
					addModelItem.setValid(true);
					treeItems.add(addModelItem);
					
					treeParent.addChild(addModelItem);
				}
			}
		}
	}
	
	
	private void createRequirementsList(TreeParent parent){
		
		for (Element element : requirementsValid) {
			NamedElement req = ((NamedElement)element);
			
			RequirementItem reqItem = new RequirementItem( getRequirementName(req) );
			reqItem.setUmlElement(req);
			reqItem.setValid(true);
			treeItems.add(reqItem);
			
			parent.addChild(reqItem);
			
			// add scenarios
			HashSet<Element> relatedScenarios = reqToScenarios.get(req);
			if (relatedScenarios != null && relatedScenarios.size() > 0) {
				for (Element scenario : relatedScenarios) {
					if (!scenariosDiscarded.contains(scenario)) {
						ScenarioItem scenarioItem = new ScenarioItem("Possible scenario: '" + ((NamedElement)scenario).getName() + "'");
						scenarioItem.setUmlElement(scenario);
						scenarioItem.setValid(true);
						treeItems.add(scenarioItem);
						
						reqItem.addChild(scenarioItem);
					}
				}
			}
			
			// Add models that are needed for this requirement in addition
			addAdditionalModels(reqItem);
		}
		
		for (Element element : requirementsWithUnsatisfiedClients) {
			NamedElement req = ((NamedElement)element);
			
			RequirementItem reqItem = new RequirementItem( getRequirementName(req)  );
			reqItem.setUmlElement(req);
			reqItem.setValid(false);
			treeItems.add(reqItem);
			
			parent.addChild(reqItem);
			
			// Add unsatisfied clients
			HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject,HashSet<Element>> unsatisfiedClients = getUnsatisfiedRequirementClients(req);
			if (unsatisfiedClients != null) {
				for (org.openmodelica.modelicaml.common.instantiation.TreeObject client : unsatisfiedClients.keySet()) {
					ClientItem clientItem = new ClientItem(client.getDotPath() + " = ?");
					clientItem.setUmlElement(client.getUmlElement());
					clientItem.setValid(false);
					treeItems.add(clientItem);
					
					reqItem.addChild(clientItem);
					
					// add related scenarios
					HashSet<Element> scenarios = unsatisfiedClients.get(client);
					if (scenarios != null ) {
						for (Element scenario : scenarios) {
							ScenarioItem scenarioItem = new ScenarioItem("Unsatisfied with scenario '" + ((NamedElement)scenario).getName() + "'");
							scenarioItem.setUmlElement(scenario);
							treeItems.add(scenarioItem);
							
							clientItem.addChild(scenarioItem);
						}
					}
				}
			}
			
			// Add models that are needed for this requirement in addition
			addAdditionalModels(reqItem);
		}
		
		/*
		 * Add the rest of requirement.
		 * Note, these are requirements that are not linked with scenarios. 
		 */
		
		HashSet<Element> restOfRequirements = new HashSet<Element>();
		restOfRequirements.addAll(requirementsAll);
		restOfRequirements.removeAll(requirementsValid);
		restOfRequirements.removeAll(requirementsWithUnsatisfiedClients);
		
		for (Element element : restOfRequirements) {
			NamedElement req = ((NamedElement)element);
			
			RequirementItem reqItem = new RequirementItem( req.getName() );
			reqItem.setUmlElement(req);
			treeItems.add(reqItem);
			
			List<Element> modelsToBeInstantiated = new ArrayList<Element>();
			modelsToBeInstantiated.add(req);
			
			// add additional models that are needed by the requirements
			HashSet<Element> addModels = vsc.getModelToItsAdditionalModels().get(req);
			if (addModels != null && addModels.size() > 0) {
				modelsToBeInstantiated.addAll(addModels);
			}
			
			// compose the model virtually
			ModelComposer modelComposer = new ModelComposer(this.selectedElement, modelsToBeInstantiated, (Package) valueMediatorsPackage, targetPackage, preparedModelInstantiations, vsc);
			org.openmodelica.modelicaml.common.instantiation.TreeParent reqInstantiationItem = modelComposer.getModelToItsInstantiation().get(req).getTreeRoot();
			
			if (reqInstantiationItem != null) {
				
				boolean allMandClientsOK = modelComposer.areAllRequiredClientsSatisfied(modelComposer.getVirtualInstantiationTreeRoot(), reqInstantiationItem );
				
				// if not all mandatory clients are satisfied
				if ( !allMandClientsOK) {
					HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> unsatisfiedClients = modelComposer.getUnsatisfiedRequiredClients(req);
					if (unsatisfiedClients != null && unsatisfiedClients.size() > 0) {
						List<org.openmodelica.modelicaml.common.instantiation.TreeObject> sortedList = ModelicaMLServices.getSortedByDotPath(unsatisfiedClients);
						for (org.openmodelica.modelicaml.common.instantiation.TreeObject unsatisfiedClient : sortedList) {
							ClientItem clientItem = new ClientItem(unsatisfiedClient.getDotPath() + " = ?");
							clientItem.setUmlElement(unsatisfiedClient.getUmlElement());
							clientItem.setValid(false);
							treeItems.add(clientItem);
							
							reqItem.addChild(clientItem);
						}
					}
				}
				// if there are no clients at all 
				else if ( !(modelComposer.getClients(reqInstantiationItem).size() > 0) ) {
					reqItem.setValid(false);
					reqItem.setUnknown(true);
				}
				else {
					reqItem.setValid(true);
				}
			}
			
			parent.addChild(reqItem);
			
			// Add additional models
			addAdditionalModels(reqItem);
		}
	}
	
	private HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject, HashSet<Element>> getUnsatisfiedRequirementClients(Element req){
		HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject, HashSet<Element>> unsatisfiedClientsToScenarios = new HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject, HashSet<Element>>();
		
		// get the scenarios that are related to the requirements 
		HashSet<Element> relatedScenarios = reqToScenarios.get(req);
		
		if (relatedScenarios != null && relatedScenarios.size() > 0) {
			for (Element scenario : relatedScenarios) {
				
				// get the combination data
				VeMScenarioReqCombinationsCreator combination = scenarioToVerificationModelCombination.get(scenario);
				if (combination != null) {
					
					// get unsatisfied clients
					HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> unsatisfiedClients = combination.getUnsatisfiedMandatoryClients(req);
					if (unsatisfiedClients != null) {
						for (org.openmodelica.modelicaml.common.instantiation.TreeObject treeObject : unsatisfiedClients) {
							//sort it into the map
							addToMapList(unsatisfiedClientsToScenarios, treeObject, scenario);
						}
					}
				}
			}
			return unsatisfiedClientsToScenarios;
		}
		return null;
	}
	
	public void showTree(TreeParent treeRoot, int mode){
		
		// Remove the last tree
		TreeObject[] children = treeRoot.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject treeObject = children[i];
			treeRoot.removeChild(treeObject);
		}
		
		// Display trees
		if (reqTreeRoot != null && scenTreeRoot != null) {
			if (mode == MODE_REQUIREMENTS_VIEW) {
				treeRoot.addChild(reqTreeRoot);
			}
			else if (mode==MODE_SCENARIOS_VIEW) {
				treeRoot.addChild(scenTreeRoot);
			}
		}
		else {
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Data Collection Error", "Traceability trees could not be created.");
		}
	}
	
	
	// ******************************************************************************************************************************
	
	public void clearAll(){
		
		treeItems.clear();
		scenarioToVerificationModelCombination.clear();
		requirementsAll.clear();
		scenariosValid.clear();
		scenariosDiscarded.clear();
		requirementsValid.clear();
		requirementsWithUnsatisfiedClients.clear();
		reqToScenarios.clear();
	}
	
	private void addToLog(String msg){
		this.log = this.log + msg + "\n";
	}


	public List<TreeObject> findTreeItems(Object[] viewerSelection){

		List<TreeObject> foundTreeItems = new ArrayList<TreeObject>();
		
		// To avoid concurrent modifications
		ArrayList<TreeObject> items = new ArrayList<TreeObject>();
		items.addAll(getTreeItems());
		
		for (TreeObject treeItem: items) {
			
			for (Object object : viewerSelection) {
				
				if (object instanceof TreeObject) {
					Element umlElement1 = treeItem.getUmlElement();
					Element umlElement2 = ((TreeObject)object).getUmlElement();
					if (umlElement1!= null && umlElement2!=null && umlElement1.equals(umlElement2)) {
						foundTreeItems.add(treeItem);
					}
					/*
					 * The code below only works if the equal and hashCode are overridden ...
					 */
//					if ( ((TreeObject)object).equals(treeItem)) {
//						foundTreeItems.add(treeItem);
//					}
				}
				// structured selection, i.e. objects from other views
				else if (adaptSelectedElement(object) != null) {
					EObject eObj = adaptSelectedElement(object);
					if ( ((TreeObject)treeItem).getUmlElement().equals(eObj)) {
						foundTreeItems.add(treeItem);
					}
				}
			}
		}
		return foundTreeItems;
	}


	public List<TreeObject> getTreeItems() {
		return treeItems;
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToMapList(HashMap map, Element key, Element value){
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addToMapList(HashMap map, org.openmodelica.modelicaml.common.instantiation.TreeObject key, Element value){
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
	
	
	private String getRequirementName(NamedElement element){
		if (element.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
			return (String) element.getValue(element.getAppliedStereotype(Constants.stereotypeQName_Requirement), Constants.propertyName_id) + " - " + ((NamedElement) element).getName() + " ";
		}
		else {
			return element.getName();
		}
	}
	
	public boolean hasScenarios(TreeObject reqItem){
		if (reqItem instanceof TreeParent) {

			// In Scenarios View: 
			TreeParent parent = reqItem.getParent();
			if (parent instanceof ScenarioItem) {
				return true;
			}

			// In Requirements View: 
			// Check if one of the children is a scenario
			TreeObject[] children = ((TreeParent)reqItem).getChildren();
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];
				if (treeObject instanceof ScenarioItem) {
					return true;
				}
				
				// Check if there are clients that are not satisfied in a scenario 
				if (treeObject instanceof ClientItem) {
					TreeObject[] clientItemChildren = ((ClientItem)treeObject).getChildren();
					for (int j = 0; j < clientItemChildren.length; j++) {
						TreeObject clientItemChild = clientItemChildren[j];
						if (clientItemChild instanceof ScenarioItem) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	protected EObject adaptSelectedElement( Object selection) {
		EObject eObject = null;
		if(selection != null) {
			
			if (selection instanceof org.openmodelica.modelicaml.common.instantiation.TreeParent) { // this is an object from components tree view plugin
				return ((org.openmodelica.modelicaml.common.instantiation.TreeParent)selection).getProperty();
			}
			
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}
	
	//***********************************
	
	public NamedElement getSelectedElement() {
		return selectedElement;
	}


	public void setSelectedElement(NamedElement selectedElement) {
		this.selectedElement = selectedElement;
	}

	
	
	public TreeParent getReqTreeRoot() {
		return reqTreeRoot;
	}


	public void setReqTreeRoot(TreeParent reqTreeRoot) {
		this.reqTreeRoot = reqTreeRoot;
	}



	
	public TreeParent getScenTreeRoot() {
		return scenTreeRoot;
	}


	public void setScenTreeRoot(TreeParent scenTreeRoot) {
		this.scenTreeRoot = scenTreeRoot;
	}
	
	public Element getTargetPackage() {
		return targetPackage;
	}
	
	// Marker *****************************************
	
	private String markerType = Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES;
	
	public IMarker createMarker(Element elt, String sourceID, String criticality, String msg){
		if (elt != null) {
			IResource r = null;
			URI eUri = elt.eResource().getURI();
			
			if (eUri.isPlatformResource()) {
				String platformString = eUri.toPlatformString(true);
				r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
//				r = ResourcesPlugin.getWorkspace().getRoot().findMember(umlModel.getResource().getURI().toPlatformString(true));
			}
			try {
				
				IMarker marker = r.createMarker(markerType);
				marker.setAttribute(IMarker.MESSAGE, msg);
				if ( criticality.equals("error") ) 	{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);	}
				else 								{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) ; }
				marker.setAttribute(IMarker.SOURCE_ID, sourceID);
				
				if (elt instanceof NamedElement) {
					marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());	
				}
				else{
					marker.setAttribute(IMarker.LOCATION, elt.toString());
				}
				marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());

				return marker;
				
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	public void setVsc(VerificationScenariosCollector vsc) {
		this.vsc = vsc;
	}



	public VerificationScenariosCollector getVsc() {
		return vsc;
	}



	public void setUmlModel(UmlModel umlModel) {
		this.umlModel = umlModel;
	}



	public UmlModel getUmlModel() {
		return umlModel;
	}


	public Element getRootPackage() {
		return rootPackage;
	}


	public void setRootPackage(Element rootPackage) {
		this.rootPackage = rootPackage;
	}

}

