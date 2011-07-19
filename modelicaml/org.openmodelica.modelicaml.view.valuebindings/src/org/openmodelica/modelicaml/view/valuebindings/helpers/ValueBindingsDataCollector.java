/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.view.valuebindings.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;

public class ValueBindingsDataCollector implements IRunnableWithProgress {

//	private EObject umlRootModel;
//	private TreeObject instantiationTreeRoot;
	
	private HashSet<Element> usedMediators = new HashSet<Element>();
	private HashSet<Element> referencedClients = new HashSet<Element>();
	private HashSet<TreeObject> referencedClientTreeItems = new HashSet<TreeObject>();
	
	private HashSet<Element> referencedMediators = new HashSet<Element>();
	private HashSet<Element> referencedProviders = new HashSet<Element>();
	private HashSet<TreeObject> referencedProviderTreeItems = new HashSet<TreeObject>();
	
	private HashSet<Element> umlElementsInInstantiationTree = new HashSet<Element>();
	private HashMap<Element,HashSet<TreeObject>> elementToInstantiationTreeObjects = new HashMap<Element,HashSet<TreeObject>>();
	private HashMap<Element,HashSet<Element>> clientToMediators = new HashMap<Element,HashSet<Element>>();
	private HashMap<Element,HashSet<Element>> mediatorToClients = new HashMap<Element,HashSet<Element>>();
	private HashMap<Element,HashSet<Element>> mediatorToProviders = new HashMap<Element,HashSet<Element>>();

	private String log = "";
	private boolean wasCancelled = false;
	
	// The total sleep time
	private static final int TOTAL_TIME = 100;
	// The increment sleep time
	private static final int INCREMENT = 10;
	// process time�is unknown
	private boolean indeterminate = true; 
	
//	public ValueBindingsDataCollector(Element umlRootModel, TreeObject instantiationTreeRoot){
////		this.instantiationTreeRoot = instantiationTreeRoot;
////		this.umlRootModel = umlRootModel;
////		collect();
//	}
	
	public void collectAll(Element valueBindingsPackage, TreeObject instantiationTreeRoot){
		// collect uml elements that exist in the instantiation tree
		if (instantiationTreeRoot instanceof TreeParent && valueBindingsPackage instanceof NamedElement) {
			if (instantiationTreeRoot.getSelectedClass() != null) {
				addToLog("Collecting data from the instantiated class '" + instantiationTreeRoot.getSelectedClass().getQualifiedName() + "' ... \n");	
			}
			else {
				addToLog("Collecting data from the instantiated class '" + instantiationTreeRoot.getName() + "' ... \n");	
			}
			
			collectElementsFromInstantiationTree(instantiationTreeRoot);

			// collect mediators and references to clients, other mediators and providers
			addToLog("Collecting data from the UML Model '" + ((NamedElement)valueBindingsPackage).getQualifiedName() + "'... \n");
			collectMeditorsDataFromUmlModel(valueBindingsPackage, umlElementsInInstantiationTree);
			
			// remove all mediators that have not clients in the instantiation tree.
			removeMediatorsWithNoClientsInInstantiationTree();
		}
		else {
			addToLog("ERROR: Was not able to collect data ...");
		}
	}
	
	public void removeMediatorsWithNoClientsInInstantiationTree(){
		HashSet<Element> usedMediatorsCopy = new HashSet<Element>();
		usedMediatorsCopy.addAll(usedMediators);
		
		for (Element mediator : usedMediatorsCopy) {
			if (mediatorToClients.get(mediator) != null && mediatorToClients.get(mediator).size() > 0) {
				// ok, do nothing
			}
			else {
				usedMediators.remove(mediator);
				
				// TODO: keep the information that mediators exist with providers even if they are not used because 
				// there are are clients in the instantiated tree or not.
				//mediatorToProviders.remove(mediator);
			}
		}
	}
	
	public void collectElementsFromInstantiationTree(TreeObject treeRoot){
		if (treeRoot instanceof TreeParent) {
			TreeObject[] children = ((TreeParent)treeRoot).getChildren();
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];
				Element element = treeObject.getUmlElement();
//				if (element instanceof NamedElement && !treeObject.isPredefinedModelicaProperty()) {
				if (element instanceof NamedElement) {
					umlElementsInInstantiationTree.add(element);
					addToElementToInstantiationTreeObjectMap(element, treeObject);
				}
				
				// recursive call
				if (treeObject instanceof TreeParent) {
					collectElementsFromInstantiationTree(treeObject);
				}
			}
		}
	}

	
	public void collectMeditorsDataFromUmlModel(EObject umlRootElement, HashSet<Element> umlElementsInInstantiationTree){
		if (umlRootElement instanceof NamedElement) {
			
			// collect all mediators
			MediatorsCollector mc = new MediatorsCollector();
			mc.collectElementsFromModel(umlRootElement);
			
			for ( Element object : mc.getValueMediators() ) {
				if (object instanceof NamedElement 
						&& ((NamedElement)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
					
					NamedElement mediator = (NamedElement)object;

					EList<Dependency> depList = mediator.getClientDependencies();
					for (Dependency dependency : depList) {
						
						// reference to clients
						if (dependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null ) { 
							EList<Element> targets = dependency.getTargets();
							for (Element element : targets) {
								if (element instanceof NamedElement) {
									// add only mediators and referenced elements if they are used in the instantiation tree
									if (umlElementsInInstantiationTree.contains(element)) {
										
										// Add to referenced client elements
										referencedClients.add(element);
										
										// Add to referenced client tree items
										if (elementToInstantiationTreeObjects.get(element) != null) {
											referencedClientTreeItems.addAll(elementToInstantiationTreeObjects.get(element));	
										}
										
										addToClientToMediatorsMap(element, mediator);
										
										addToMediatorToClientsMap(mediator, element);
										
										usedMediators.add(mediator);
									}
								}
							}
						}
						// reference to providers or other mediators
						else if (dependency.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null ) {
							EList<Element> targets = dependency.getTargets();
							for (Element element : targets) {
								if (element instanceof NamedElement) {
									
									// if it is a medtiator that is reused by another mediator
									if (((NamedElement)element).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
										// TODO: implement the reusage of mediators
										
										// add only mediators and referenced elements if they are used in the instantiation tree
										if (umlElementsInInstantiationTree.contains(element)) {
											referencedMediators.add(element);
											usedMediators.add(mediator);
										}
										else {
											//TODO: log that the mediator or its providers was discarded because they are not used in the instantiation tree.
										}
									}
									// it is a provider
									else {
										// add only mediators and referenced elements if they are used in the instantiation tree
										if (umlElementsInInstantiationTree.contains(element)) {
											// Add to referenced provider elements
											referencedProviders.add(element);
											// Add to referenced provider tree items
											if (elementToInstantiationTreeObjects.get(element) != null) {
												referencedProviderTreeItems.addAll(elementToInstantiationTreeObjects.get(element));	
											}
											addToMediatorToProvidersMap(mediator, element);
											usedMediators.add(mediator);
										}
										else {
											String message = "DISCARDED: Provider '" + ((NamedElement)element).getQualifiedName() + "' for mediator '"+mediator.getQualifiedName()+"' is not used in the instantiated class.";
											addToLog(message);
										}

									}
								}
							}
						}
					}
					
					if ( !usedMediators.contains(mediator) ) {
						String message = "DISCARDED: Mediator'" + mediator .getQualifiedName() + "' has no clients or providers that are used in the instantiated class.";
						addToLog(message);
					}
				}
			}
		}
	}
	
//	public void collectMeditorsDataFromUmlModel(EObject umlRootElement, HashSet<Element> umlElementsInInstantiationTree){
//		if (umlRootElement instanceof NamedElement) {
//			
//			Iterator<EObject> i = ((NamedElement)umlRootElement).eAllContents();
//			
//			while (i.hasNext()) {
//				
//				EObject object = i.next();
//				
//				if (object instanceof NamedElement 
//						&& ((NamedElement)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
//					
//					NamedElement mediator = (NamedElement)object;
//
//					EList<Dependency> depList = mediator.getClientDependencies();
//					for (Dependency dependency : depList) {
//						
//						// reference to clients
//						if (dependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null ) { 
//							EList<Element> targets = dependency.getTargets();
//							for (Element element : targets) {
//								if (element instanceof NamedElement) {
//									// add only mediators and referenced elements if they are used in the instantiation tree
//									if (umlElementsInInstantiationTree.contains(element)) {
//										
//										// Add to referenced client elements
//										referencedClients.add(element);
//										
//										// Add to referenced client tree items
//										if (elementToInstantiationTreeObjects.get(element) != null) {
//											referencedClientTreeItems.addAll(elementToInstantiationTreeObjects.get(element));	
//										}
//										
//										addToClientToMediatorsMap(element, mediator);
//										
//										addToMediatorToClientsMap(mediator, element);
//										
//										usedMediators.add(mediator);
//									}
//								}
//							}
//						}
//						// reference to providers or other mediators
//						else if (dependency.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null ) {
//							EList<Element> targets = dependency.getTargets();
//							for (Element element : targets) {
//								if (element instanceof NamedElement) {
//									
//									// if it is a medtiator that is reused by another mediator
//									if (((NamedElement)element).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
//										// TODO: implement the reusage of mediators
//										
//										// add only mediators and referenced elements if they are used in the instantiation tree
//										if (umlElementsInInstantiationTree.contains(element)) {
//											referencedMediators.add(element);
//											usedMediators.add(mediator);
//										}
//										else {
//											//TODO: log that the mediator or its providers was discarded because they are not used in the instantiation tree.
//										}
//									}
//									// it is a provider
//									else {
//										// add only mediators and referenced elements if they are used in the instantiation tree
//										if (umlElementsInInstantiationTree.contains(element)) {
//											// Add to referenced provider elements
//											referencedProviders.add(element);
//											// Add to referenced provider tree items
//											if (elementToInstantiationTreeObjects.get(element) != null) {
//												referencedProviderTreeItems.addAll(elementToInstantiationTreeObjects.get(element));	
//											}
//											addToMediatorToProvidersMap(mediator, element);
//											usedMediators.add(mediator);
//										}
//										else {
//											String message = "DISCARDED: Provider '" + ((NamedElement)element).getQualifiedName() + "' for mediator '"+mediator.getQualifiedName()+"' is not used in the instantiated class.";
//											addToLog(message);
//										}
//
//									}
//								}
//							}
//						}
//					}
//					
//					if ( !usedMediators.contains(mediator) ) {
//						String message = "DISCARDED: Mediator'" + mediator .getQualifiedName() + "' has no clients or providers that are used in the instantiated class.";
//						addToLog(message);
//					}
//				}
//			}		
//		}
//	}

	
	private void addToLog(String message) {
		log = log + message + "\n";
	}
	
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
	    monitor.beginTask("Value Bindings data collecting is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
	    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
	      Thread.sleep(INCREMENT);
	      monitor.worked(INCREMENT);
	      if (total == TOTAL_TIME / 8) monitor.subTask("Searching for Value Mediators ...");
	      if (total == TOTAL_TIME / 4) monitor.subTask("Analyzing the instantiation tree ...");
	      if (total == TOTAL_TIME / 2) monitor.subTask("Sorting data ...");
	    }
	    monitor.done();
	    if (monitor.isCanceled()){
	    	setWasCancelled(true);
	    	throw new InterruptedException("The Value Bindings data collection operation was cancelled.");
	    }   
	  }
	
	
	private void addToElementToInstantiationTreeObjectMap(Element key, TreeObject item){
		HashSet<TreeObject> set = elementToInstantiationTreeObjects.get(key);
		HashSet<TreeObject> updatedSet = new HashSet<TreeObject>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			elementToInstantiationTreeObjects.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			elementToInstantiationTreeObjects.put(key, updatedSet);
		}
	}
	
	private void addToClientToMediatorsMap(Element client, Element mediator){
		HashSet<Element> set = clientToMediators.get(client);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(mediator);
			clientToMediators.put(client, updatedSet);
		}
		else {
			updatedSet.add(mediator);
			clientToMediators.put(client, updatedSet);
		}
	}
	
	
	private void addToMediatorToClientsMap(Element mediator, Element client){
		HashSet<Element> set = mediatorToClients.get(mediator);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(client);
			mediatorToClients.put(mediator, updatedSet);
		}
		else {
			updatedSet.add(client);
			mediatorToClients.put(mediator, updatedSet);
		}
	}
	
	
	private void addToMediatorToProvidersMap(Element mediator, Element provider){
		HashSet<Element> set = mediatorToProviders.get(mediator);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(provider);
			mediatorToProviders.put(mediator, updatedSet);
		}
		else {
			updatedSet.add(provider);
			mediatorToProviders.put(mediator, updatedSet);
		}
	}
	
	
	public HashSet<Element> getUsedMediators() {
		return usedMediators;
	}
	public HashSet<Element> getReferencedClients() {
		return referencedClients;
	}
	public HashSet<Element> getReferencedMediators() {
		return referencedMediators;
	}
	public HashSet<Element> getReferencedProviders() {
		return referencedProviders;
	}
	public HashSet<Element> getUmlElementsInInstantiationTree() {
		return umlElementsInInstantiationTree;
	}
	
	public HashSet<TreeObject> getInstantiationTreeObjects(Element element){
		return elementToInstantiationTreeObjects.get(element);
	}
	
	
	public HashMap<Element, HashSet<Element>> getClientToMediators() {
		return clientToMediators;
	}
	public HashMap<Element, HashSet<Element>> getMediatorToProviders() {
		return mediatorToProviders;
	}

	
	
	public String getLog() {
		return log;
	}

	public void setWasCancelled(boolean wasCanceled) {
		this.wasCancelled = wasCanceled;
	}

	public boolean wasCancelled() {
		return wasCancelled;
	}
	
}
