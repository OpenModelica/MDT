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
package org.openmodelica.modelicaml.traceability.views.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ElementsCollector;
import org.openmodelica.modelicaml.helper.impl.VerificationModelComponentsCombination;
import org.openmodelica.modelicaml.helper.impl.VerificationScenariosCollector;

public class TreeBuilder implements IRunnableWithProgress{

	private EList<TreeObject> treeItems = new BasicEList<TreeObject>(); // created tree clients

	private NamedElement selectedElement;
	
	private Element targetPackage;
	private Element requirementsPackage; 
	private Element testScenariosPackage; 
	private Element valueMediatorsPackage;
	public final int MODE_REQUIREMENTS_VIEW = 0;
	public final int MODE_SCENARIOS_VIEW = 1;

	private TreeParent reqTreeRoot;
	private TreeParent scenTreeRoot;
	
	/*
	 * Possible combinations, each containing an initial set (1 system model, 1 test scenario  and n requirements) and 
	 * all additional model that are required by any of the initial set models. 
	 */
	private HashMap<Element, VerificationModelComponentsCombination> scenarioToVerificationModelCombination = new HashMap<Element, VerificationModelComponentsCombination>();
	
	// all requirements that were collected from the specified requirements package
	private HashSet<Element> requirementsAll = new HashSet<Element>();
	
	// all test scenarios that were selected for the selected system model
	private HashSet<Element> scenariosValid = new HashSet<Element>();
	// all test scenarios that discarded because they cannot be used for stimulating the selected system model.
	private HashSet<Element> scenariosDiscarded = new HashSet<Element>();
	
	/* all requirements that were selected for instantiation because 
	 * the selected test scenarios reference them and all their clients are satisfied by the selected system model
	 */
	private HashSet<Element> requirementsValid = new HashSet<Element>();
	
	/* all requirements that were discarded because  
	 * some of their clients are NOT satisfied by the selected combination of system model, scenario and all required additional models.
	 */
	private HashSet<Element> requirementsWithUnsatisfiedClients = new HashSet<Element>();
	
	private HashMap<Element,HashSet<Element>> reqToScenarios = new HashMap<Element, HashSet<Element>>();
	
	private String log = "";
	
	
	public void initialize(NamedElement selectedElement){
		this.selectedElement = selectedElement;
		
		// get the uml model that is open in Papyrus.
		UmlModel papyrusModel = UmlUtils.getUmlModel();
		if (papyrusModel != null ) {
			try {
				targetPackage = (Element) papyrusModel.lookupRoot();
				requirementsPackage = (Element) papyrusModel.lookupRoot();
				testScenariosPackage = (Element) papyrusModel.lookupRoot();
				valueMediatorsPackage = (Element) papyrusModel.lookupRoot();
				
			} catch (NotFoundException e) {
				e.printStackTrace();
				MessageDialog.openError(new Shell(), "Packages Selection", "Cannot access the root model in Papyrus. Please try it again.");
			}
		}
		
		boolean allPackagesAreSet = targetPackage != null && requirementsPackage != null && testScenariosPackage != null && valueMediatorsPackage != null;
		
		if (allPackagesAreSet) {
			
			collectData();

			reqTreeRoot = new TreeParent(selectedElement.getName() + " - Requirement Clients Satisfaction");
			reqTreeRoot.setUmlElement(selectedElement);
			// TODO: build tree for req. view
			createRequirementsList(reqTreeRoot);
			
			scenTreeRoot = new TreeParent(selectedElement.getName()+ " - Possible Scenarios");
			scenTreeRoot.setUmlElement(selectedElement);
			// TODO: build tree for scen. view
		}
	}

	
	private void collectData(){
		
		clearAll();
		
		ElementsCollector ec = new ElementsCollector();
		ec.collectElementsFromModel(requirementsPackage, Constants.stereotypeQName_Requirement);
		requirementsAll.addAll(ec.getElements());
		
		// find all test scenarios
		VerificationScenariosCollector tsc = new VerificationScenariosCollector();
		tsc.collectTestCasesFromPackage((Package) testScenariosPackage, true);
		if (tsc.getAllTS().size() == 0) {
			String message = "INFO: No verification scenarios were found.";
			addToLog(message);
		}

		for (Element scenario : tsc.getAllTS() ) {
			if (scenario instanceof Class) {
				
				Class scenarioToBeUsed = (Class) scenario;

				// get requirements
				HashSet<Element> reqList = tsc.getTsToReq().get(scenarioToBeUsed);
				HashSet<Class> requirementsToBeUsed = new HashSet<Class>();
				
				if (reqList != null) {
					 for (Element req : reqList) {
						if (req instanceof Class) {
							requirementsToBeUsed.add((Class) req);
						}
						else {
							String message = "NOT VALID: Requirement '" + req.toString() + "' is not a class";
							addToLog(message);
						}
					}
				}
				else {
					String message = "INFO: No requirements are found for the test scenario '" + scenarioToBeUsed.getQualifiedName() + "'";
					addToLog(message);
				}
				
				VerificationModelComponentsCombination tsmc = new VerificationModelComponentsCombination((Class) selectedElement, 
						scenarioToBeUsed, 
						requirementsToBeUsed,
						(Package) valueMediatorsPackage,
						tsc.getAlwaysInclude(),
						tsc.getModelToItsRequiredModels());
				
				// add to map
				scenarioToVerificationModelCombination.put(scenarioToBeUsed, tsmc);
				
				// add to selected or discarded test scenarios
				if (!tsmc.isDiscarded()) {
					scenariosValid.add(scenarioToBeUsed);
				}
				else {
					scenariosDiscarded.add(scenarioToBeUsed);
				}
				
				// add to selected or discarded requirements
				HashSet<Class> requirements = tsmc.getRequirements();
				for (Element requirement : requirements) {
					addToMapList(reqToScenarios, requirement, scenario);
					if (tsmc.getUnsatisfiedRequiredClients(requirement) != null) {
						requirementsWithUnsatisfiedClients.add(requirement);
					}
					else {
						requirementsValid.add(requirement);
					}
				}

				// add log messages
				addToLog(tsmc.getLog().trim());
			}
			else {
				String message = "NOT VALID: Scenario '" + selectedElement.toString() + "' is not a UML Class.";
				addToLog(message);
			}
		}
		
	}
	
	
	private void createRequirementsList(TreeParent parent){
		
		for (Element element : requirementsValid) {
			NamedElement req = ((NamedElement)element);
			
			RequirementItem reqItem = new RequirementItem( getRequirementName(req) );
			reqItem.setUmlElement(req);
			reqItem.setValid(true);
			
			parent.addChild(reqItem);
			
			// add scenarios
			HashSet<Element> relatedScenarios = reqToScenarios.get(req);
			if (relatedScenarios != null && relatedScenarios.size() > 0) {
				for (Element scenario : relatedScenarios) {
					ScenarioItem scenarioItem = new ScenarioItem("Possible scenario: '" + ((NamedElement)scenario).getName() + "'");
					scenarioItem.setUmlElement(scenario);
					scenarioItem.setValid(true);
					reqItem.addChild(scenarioItem);
				}
			}
			
			// TODO: add models that are needed for this requirement in addition
		}
		
		for (Element element : requirementsWithUnsatisfiedClients) {
			NamedElement req = ((NamedElement)element);
			
			RequirementItem reqItem = new RequirementItem( getRequirementName(req)  );
			reqItem.setUmlElement(req);
			reqItem.setValid(false);
			
			parent.addChild(reqItem);
			
			// Add unsatisfied clients
			HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject,HashSet<Element>> unsatisfiedClients = getUnsatisfiedClients(req);
			if (unsatisfiedClients != null) {
				for (org.openmodelica.modelicaml.common.instantiation.TreeObject client : unsatisfiedClients.keySet()) {
					ClientItem clientItem = new ClientItem(client.getDotPath() + " = ?");
					clientItem.setUmlElement(client.getUmlElement());
					clientItem.setValid(false);
					
					reqItem.addChild(clientItem);
					
					// add related scenarios
					HashSet<Element> scenarios = unsatisfiedClients.get(client);
					if (scenarios != null ) {
						for (Element scenario : scenarios) {
							ScenarioItem scenarioItem = new ScenarioItem("Unsatisfied in scenario '" + ((NamedElement)scenario).getName() + "'");
							scenarioItem.setUmlElement(scenario);
							clientItem.addChild(scenarioItem);
						}
					}
				}
			}
			
			// TODO: add models that are needed for this requirement in addition
		}
		
		/*
		 * Add the rest of requirement.
		 * Note, these are requirements that are not linked with scenarios. 
		 * So at this point we don't know the scenarios, any required additional models or unsatisfied clients.
		 */
		
		HashSet<Element> restOfRequirements = new HashSet<Element>();
		restOfRequirements.addAll(requirementsAll);
		restOfRequirements.removeAll(requirementsValid);
		restOfRequirements.removeAll(requirementsWithUnsatisfiedClients);
		
		for (Element element : restOfRequirements) {
			NamedElement req = ((NamedElement)element);
			
			RequirementItem reqItem = new RequirementItem( req.getName() );
			reqItem.setUmlElement(req);
			reqItem.setValid(false);
			
			parent.addChild(reqItem);
		}
	}
	
	
	private HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject, HashSet<Element>> getUnsatisfiedClients(Element req){
		HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject, HashSet<Element>> unsatisfiedClientsToScenarios = new HashMap<org.openmodelica.modelicaml.common.instantiation.TreeObject, HashSet<Element>>();
		
		// get the scenarios that are related to the requirements 
		HashSet<Element> relatedScenarios = reqToScenarios.get(req);
		
		if (relatedScenarios != null && relatedScenarios.size() > 0) {
			for (Element scenario : relatedScenarios) {
				
				// get the combination data
				VerificationModelComponentsCombination combination = scenarioToVerificationModelCombination.get(scenario);
				if (combination != null) {
					
					// get unsatisfied clients
					HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> unsatisfiedClients = combination.getUnsatisfiedRequiredClients(req);
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
		
		clearAll();
		
		// remove the last tree
		TreeObject[] children = treeRoot.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject treeObject = children[i];
			treeRoot.removeChild(treeObject);
		}
		
		// display trees
		if (reqTreeRoot != null && scenTreeRoot != null) {
			if (mode == MODE_REQUIREMENTS_VIEW) {
				treeRoot.addChild(reqTreeRoot);
			}
			else if (mode==MODE_SCENARIOS_VIEW) {
				treeRoot.addChild(scenTreeRoot);
			}
		}
		else {
			MessageDialog.openError(new Shell(), "Data Collection Error", "Traceability trees could not be created.");
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

	private void clearLog(){
		this.log = "";
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


	private TreeObject findTreeItem(String qName){
		TreeObject foundObject = null;
		
		// to avoid concurrent modifications
		ArrayList<TreeObject> items = new ArrayList<TreeObject>();
		items.addAll(treeItems);
		
		for (TreeObject treeObject : items) {
			if (treeObject.getQName().equals(qName)) {
				foundObject = treeObject;
			}
		}
		return foundObject;
	}


	public EList<TreeObject> getTreeItems() {
		return treeItems;
	}
	
	
	
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
	
	
	

	// Progress monitor 
	
	// The total sleep time
	private static final int TOTAL_TIME = 3000;
	// The increment sleep time
	private static final int INCREMENT = 10;
	// process time´is unknown
	private boolean indeterminate = true; 
	
	private String progressMonitorTitle = "Loading Modelica Models using OMC API";
	private String monitorText1 = "Establishing connection to OMC...";
	private String monitorText2 = "Quering OMC...";
	private String monitorText3 = "Preparing tree visualization ...";
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		
		monitor.beginTask(progressMonitorTitle + " is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
	    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
	      Thread.sleep(INCREMENT);
	      monitor.worked(INCREMENT);
	      if (total == TOTAL_TIME / 100) monitor.subTask(monitorText1);
	      if (total == TOTAL_TIME / 4) monitor.subTask(monitorText2);
	      if (total == TOTAL_TIME / 2) monitor.subTask(monitorText3);
	    }
	    monitor.done();
	    if (monitor.isCanceled()){
	    	throw new InterruptedException(progressMonitorTitle + " was cancelled.");
	    }   
	}

}

