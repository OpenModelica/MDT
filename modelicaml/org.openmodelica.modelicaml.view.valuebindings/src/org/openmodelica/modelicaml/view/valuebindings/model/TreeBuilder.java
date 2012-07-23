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
package org.openmodelica.modelicaml.view.valuebindings.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.valuebindings.helpers.MediatorsCollector;
import org.openmodelica.modelicaml.common.valuebindings.helpers.ValueBindingsDataCollector;


public class TreeBuilder {

	private HashSet<Element> valueMediatorContainers = new HashSet<Element>();
	private HashSet<Element> valueMediators = new HashSet<Element>();
	
	// only the imported mediator containers
	private HashSet<Element> importedMediatorContainers = new HashSet<Element>();
	// only the imported mediators
//	private HashSet<Element> importedMediators = new HashSet<Element>(); 
	
//	private EList<Property> valueClients = new BasicEList<Property>();
//	private EList<Property> valueProviders = new BasicEList<Property>();

	public HashMap<Element, HashSet<Element>> valueMediatorToValueClients = new HashMap<Element, HashSet<Element>>();
	public HashMap<Element, HashSet<Element>> valueMediatorToValueProviders = new HashMap<Element, HashSet<Element>>();

	private EList<TreeObject> valueClientsTreeItems = new BasicEList<TreeObject>(); // created tree clients
	private EList<TreeObject> valueProvidersTreeItems = new BasicEList<TreeObject>(); // created tree providers
	
	public EObject umlModel = null;
	
	
	
	public void buildTreeFromInstantiatedClass(TreeParent treeRoot, org.openmodelica.modelicaml.common.instantiation.ClassInstantiation classInstantiation, org.openmodelica.modelicaml.common.instantiation.TreeParent instantiatedClassTreeItem){
		
		valueClientsTreeItems.clear();
		valueProvidersTreeItems.clear();
		
		if (instantiatedClassTreeItem != null) {
			String name = instantiatedClassTreeItem.getName();
			if (instantiatedClassTreeItem.getUmlElement() instanceof NamedElement) {
				name = ((NamedElement)instantiatedClassTreeItem.getUmlElement()).getName();
			}
			TreeParent rootModelNode = new TreeParent("Mediators used in " + name);
			rootModelNode.setInstantiatedClass(true);
//			rootModelNode.setUmlElement(instantiatedClassTreeItem.getSelectedClass());
			treeRoot.addChild(rootModelNode);
			
			ValueBindingsDataCollector dc = new ValueBindingsDataCollector();
			dc.collectAll(instantiatedClassTreeItem.getSelectedClass().getModel(), classInstantiation, instantiatedClassTreeItem);

			// add mediators, clients and providers - Mediator Perspective 
			addValueMediatorsUsedInInstantaitedClass(rootModelNode, dc);
			
			// add client perspective nodes
			createClientPerspectiveNodes(treeRoot, dc, " in " + name);
			
			// add provider perspective nodes
			createProviderPerspectiveNodes(treeRoot, dc, " in "+ name );
		}
		else {
			
			// create a hint as root
			TreeParent rootTitle = new TreeParent("Instantiate a class in the Component Tree View and click on the \"(Re)load\" ...");
			treeRoot.addChild(rootTitle);
		}
	}

	
	public void addValueMediatorsUsedInInstantaitedClass(TreeParent instantiatedClassTreeItem, ValueBindingsDataCollector dc){
		
		valueMediatorToValueClients.clear();
		valueMediatorToValueProviders.clear();
		
		if (dc.getUsedMediators() != null) {
			for (Element mediator : dc.getUsedMediators()) {
				if (mediator instanceof NamedElement && mediator.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
					Element valueMediatorElement = mediator;
					TreeParent item = new TreeParent(((NamedElement)mediator).getName());
					item.setUmlElement(mediator);
					instantiatedClassTreeItem.addChild(item);

					// add clients
					TreeParent valueClientsTitle = new TreeParent(Constants.valueClientsNodeName);
					item.addChild(valueClientsTitle);
					
					EList<Element> listOfClients = getValueClients(valueMediatorElement);
					for (Element clientElement : listOfClients) {
						HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> clientTreeItemList = dc.getInstantiationTreeObjects(clientElement);
						if (clientTreeItemList != null) {
							for (org.openmodelica.modelicaml.common.instantiation.TreeObject clientTreeItem : clientTreeItemList) {
								TreeParent valueClientItem = new TreeParent(clientTreeItem.getDotPath());
								valueClientItem.setUmlElement(clientTreeItem.getUmlElement()); // set the reference to the UML model element
								valueClientItem.setIsValueClient(); 
								valueClientItem.setInstantiationTreeObject(clientTreeItem);
								valueClientsTitle.addChild(valueClientItem);
								
								// used to create the client perspective
								valueClientsTreeItems.add(valueClientItem);
								
								addToMapList(valueMediatorToValueClients, mediator, clientTreeItem.getUmlElement());							
							}
						}
					}
					
					// add providers
					TreeParent valueProvidersTitle = new TreeParent(Constants.valueProvidersNodeName);
					item.addChild(valueProvidersTitle);

					EList<Element> listOfProviders = getValueProviders(valueMediatorElement);
					for (Element providerElement : listOfProviders) {
						HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> providerTreeItemList = dc.getInstantiationTreeObjects(providerElement);
						if (providerTreeItemList != null) {
							for (org.openmodelica.modelicaml.common.instantiation.TreeObject providerTreeItem : providerTreeItemList) {
								TreeParent valueProviderItem = new TreeParent(providerTreeItem.getDotPath());
								valueProviderItem.setUmlElement(providerTreeItem.getUmlElement()); // set the reference to the UML model element
								valueProviderItem.setIsValueProvider();
								valueProviderItem.setInstantiationTreeObject(providerTreeItem);
								valueProvidersTitle.addChild(valueProviderItem);
								
								// used to create the provider perspective
								valueProvidersTreeItems.add(valueProviderItem);
								
								addToMapList(valueMediatorToValueProviders, mediator, providerTreeItem.getUmlElement());
							}
						}
					}
				}
			}
		}
	}
	
	
	public void buildTreeFromUmlModel(TreeParent treeRoot){

		// clear all lists
		valueMediatorContainers.clear();
		valueMediators.clear();
		
//		valueClients.clear();
//		valueProviders.clear();

		valueMediatorToValueClients.clear();
		valueMediatorToValueProviders.clear();
		
		valueClientsTreeItems.clear();
		valueProvidersTreeItems.clear();
		
		
		// get the UML root element, collect and sort data from the UML model
		collectElementsFromModel(null);
		sortData();
		
		if (getUmlModel() instanceof NamedElement) {
			TreeParent rootModelNode = new TreeParent("'" +  ((NamedElement)getUmlModel()).getName() + "' model value mediators");
			rootModelNode.setUmlElement((Element) getUmlModel());
			treeRoot.addChild(rootModelNode);
			
			for (Element valueBindingContainer : valueMediatorContainers) {
				
				Package nearestPackage = valueBindingContainer.getNearestPackage();
				TreeParent packageNode = null;
				
				// import indicator
				String importedPrefix = "";
				if (importedMediatorContainers.contains(valueBindingContainer)) {
					importedPrefix = "(imported) ";
				}
				
				if (nearestPackage != null && nearestPackage != getUmlModel() ) {
					
					// see if this package already exist.
					TreeObject[] children = ((TreeParent)rootModelNode).getChildren();

					for (int i = 0; i < children.length; i++) {
						if (children[i].getUmlElement() == nearestPackage ) {
							packageNode = (TreeParent) children[i];
							break;
						}
					}
					
					// if there is no package node yet then create one
					if (packageNode == null) {
						packageNode = new TreeParent(importedPrefix + nearestPackage.getName());
						// set the nearest package element and add item to tree.
						packageNode.setUmlElement(nearestPackage);
						rootModelNode.addChild(packageNode);
					}
		
					// add the container item to the tree.
//					TreeParent item = new TreeParent(importedPrefix + ((NamedElement)valueBindingContainer).getName());
					TreeParent item = new TreeParent(((NamedElement)valueBindingContainer).getName());
					item.setUmlElement(valueBindingContainer);
					packageNode.addChild(item);
					
					// add mediators, clients and providers
					addValueMediators(item);
				}
				else { // if there is no package for containers then add them to the root model node.
//					TreeParent item = new TreeParent(importedPrefix + ((NamedElement)valueBindingContainer).getName());
					TreeParent item = new TreeParent(((NamedElement)valueBindingContainer).getName());
					item.setUmlElement(valueBindingContainer);
					rootModelNode.addChild(item);
					
					// add mediators, clients and providers
					addValueMediators(item);
				}
			}

			// add client perspective nodes
			createClientPerspectiveNodes(treeRoot, null, "");
			
			// add provider perspective nodes
			createProviderPerspectiveNodes(treeRoot, null, "");
			
		}
		else {
			// create a hint as root
			TreeParent rootTitle = new TreeParent("Click on the \"(Re)load\" button to load value bindings ...");
			treeRoot.addChild(rootTitle);
		}
	}
	
	
	public void addValueMediators(TreeParent valueBindingsContainer){
		
		// TODO: this should be adopted when inheritance wil be allowed for mediators.
//		EList<Property> list = ((Class)valueBindingsContainer.getUmlElement()).getAllAttributes();
		EList<Property> list = ((Class)valueBindingsContainer.getUmlElement()).getAttributes();
		
		for (Property property : list) {
			if (property.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
				Element valueMediatorElement = property;
				TreeParent item = new TreeParent(property.getName());
				item.setUmlElement(property);
				valueBindingsContainer.addChild(item);

				// add clients
				TreeParent valueClientsTitle = new TreeParent(Constants.valueClientsNodeName);
				item.addChild(valueClientsTitle);
				
//				HashSet<Element> listOfClients = valueMediatorToValueClients.get(property);
				EList<Element> listOfClients = getValueClients(valueMediatorElement);
				EList<Element> listOfRequiredClients = getRequiredValueClients(valueMediatorElement);
				
				if (listOfClients != null ) {
					for (Element element : listOfClients) {
						if (element instanceof Property) {
							TreeParent valueClientItem = new TreeParent(((Property)element).getName());
//							TreeObject valueClientItem = new TreeObject (((Property)element).getName());
							valueClientItem.setUmlElement(element); // set the reference to the UML model element
							valueClientItem.setIsValueClient(); 
							valueClientsTitle.addChild(valueClientItem); 
							
							// set required client
							if (listOfRequiredClients.contains(element)) {
								valueClientItem.setValueClient_required(true);
							}
							
							valueClientsTreeItems.add(valueClientItem);
							
							// adds cyclic tree nodes, i.e. it adds a to a client its mediator nodes followed by providers, and vice versa
//							addReadOnlyNodes(valueClientItem); 
						}
					}
				}
				
				// add providers
				TreeParent valueProvidersTitle = new TreeParent(Constants.valueProvidersNodeName);
				item.addChild(valueProvidersTitle);

//				HashSet<Element> listOfProviders = valueMediatorToValueProviders.get(property);
				EList<Element> listOfProviders = getValueProviders(valueMediatorElement);
				if (listOfProviders != null ) {
					for (Element element : listOfProviders) {
						if (element instanceof Property) {
							TreeParent valueProviderItem = new TreeParent(((Property)element).getName());
//							TreeObject valueProviderItem = new TreeObject(((Property)element).getName());
							valueProviderItem.setUmlElement(element); // set the reference to the UML model element
							valueProviderItem.setIsValueProvider();
							valueProvidersTitle.addChild(valueProviderItem); 
							
							valueProvidersTreeItems.add(valueProviderItem);
							
							// adds cyclic tree nodes, i.e. adds a to a client its mediator nodes followed by providers, and vice versa
//							addReadOnlyNodes(valueProviderItem); 
						}
					}
				}
			}
		}
	}
	
	private void createClientPerspectiveNodes(TreeObject rootItem, ValueBindingsDataCollector dc, String titlePostFix){
		if (rootItem instanceof TreeParent) {
			// add clients
			TreeParent valueClientsTitle = new TreeParent(Constants.valueClientsNodeName + titlePostFix); 
			valueClientsTitle.setReadOnly(true);
			
			((TreeParent)rootItem).addChild(valueClientsTitle);
			
			for (TreeObject client : valueClientsTreeItems) {
				TreeParent clientCopy = new TreeParent(client.getName());
				clientCopy.setUmlElement(client.getUmlElement());
				clientCopy.setInstantiationTreeObject(client.getInstantiationTreeObject());
				clientCopy.setIsValueClient();
				clientCopy.setReadOnly(true);
				
				valueClientsTitle.addChild(clientCopy);
				if (client instanceof TreeParent) {
					addReadOnlyNodes((TreeParent)clientCopy, dc); 
				}
			}
		}
	}
	
	private void createProviderPerspectiveNodes(TreeObject rootItem, ValueBindingsDataCollector dc, String titlePostFix){
		if (rootItem instanceof TreeParent) {
			// add providers
			TreeParent valueProvidersTitle = new TreeParent(Constants.valueProvidersNodeName + titlePostFix);
			valueProvidersTitle.setReadOnly(true);
			
			((TreeParent)rootItem).addChild(valueProvidersTitle);
			
			for (TreeObject provider : valueProvidersTreeItems) {
				TreeParent providerCopy = new TreeParent(provider.getName());
				providerCopy.setUmlElement(provider.getUmlElement());
				providerCopy.setInstantiationTreeObject(provider.getInstantiationTreeObject());
				providerCopy.setIsValueProvider();
				providerCopy.setReadOnly(true);
				
				valueProvidersTitle.addChild(providerCopy);
				if (provider instanceof TreeParent) {
					addReadOnlyNodes((TreeParent)providerCopy, dc); 
				}
			}
		}
	}
	
	
	
	// Get clients or providers based on mediator dependencies 
	public EList<Element> getValueClients(Element valueMediator){
		EList<Element> clientList = new BasicEList<Element>();
		if (valueMediator instanceof Property) {
			EList<Dependency> mediatorDependencies = ((Property)valueMediator).getClientDependencies();
			for (Dependency dependency : mediatorDependencies) {
				if (dependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null) {
					clientList.addAll(dependency.getTargets());
				}
			}
		}
		return clientList;
	}
	
	public EList<Element> getRequiredValueClients(Element valueMediator){
		EList<Element> requiredClientList = new BasicEList<Element>();
		if (valueMediator instanceof Property) {
			EList<Dependency> mediatorDependencies = ((Property)valueMediator).getClientDependencies();
			for (Dependency dependency : mediatorDependencies) {
				Stereotype s_providesValueFor = dependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor); 
				if ( s_providesValueFor != null) {
					Object o = dependency.getValue(s_providesValueFor, Constants.propertyName_isRequired);
					if (o instanceof Boolean && ((Boolean)o) == true) {
						requiredClientList.addAll(dependency.getTargets());	
					}
				}
			}
		}
		return requiredClientList;
	}
	
	public EList<Element> getValueProviders(Element valueMediator){
		EList<Element> providersList = new BasicEList<Element>();
		if (valueMediator instanceof Property) {
			EList<Dependency> mediatorDependencies = ((Property)valueMediator).getClientDependencies();
			for (Dependency dependency : mediatorDependencies) {
				if (dependency.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
					providersList.addAll(dependency.getTargets());
				}
			}
		}
		return providersList;
	}
	
	
	// ###################### READ-ONLY nodes
	
	public void addReadOnlyNodes(TreeParent item, ValueBindingsDataCollector dc){
		
		Element element = item.getUmlElement();
		if (element != null) {
			List<Element> list = new ArrayList<Element>();
			if (item.isValueClient()) {
				list = ModelicaMLServices.getSortedByName(TreeUtls.getReferencingMediators(element, valueMediators, Constants.stereotypeQName_ProvidesValueFor));
			}
			if (item.isValueProvider()) {
				list = ModelicaMLServices.getSortedByName(TreeUtls.getReferencingMediators(element, valueMediators, Constants.stereotypeQName_ObtainsValueFrom));
			}

			int numberOfMediators = list.size();
			
			if (numberOfMediators > 0) { // if there are mediators at all.
				// create mediators node
//				TreeParent mediatorsNode = new TreeParent("subscribed to " + numberOfMediators + " mediators");
				TreeParent mediatorsNode = new TreeParent(Constants.valueMediatorsNodeName);
				mediatorsNode.setReadOnly(true);
				item.addChild(mediatorsNode);
				
				// add mediators
				for (Element valueMediator : list) {
					TreeParent mediator = new TreeParent(((NamedElement)valueMediator).getName() );
					mediator.setUmlElement(valueMediator);
					mediator.setReadOnly(true); // this is only for reading, not for editing.
					mediatorsNode.addChild(mediator);
					
					// add providers
					if (item.isValueClient()) {

						TreeParent valueProvidersTitle = new TreeParent(Constants.valueProvidersNodeName);
						
						// no actions are allowed -> user should switch to mediator perspective for adding/deleting items
						valueProvidersTitle.setReadOnly(true); 
						
						mediator.addChild(valueProvidersTitle);

						HashSet<Element> listOfProviders = valueMediatorToValueProviders.get(valueMediator); 
						if (listOfProviders != null ) {
							for (Element valueProvider : listOfProviders) {
								// if instantiated class mode -> get provider instances.
								if (item.getInstantiationTreeObject() != null && dc != null) {
									HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> providerTreeItemList = dc.getInstantiationTreeObjects(valueProvider);
									if (providerTreeItemList != null) {
										for (org.openmodelica.modelicaml.common.instantiation.TreeObject providerTreeItem : providerTreeItemList) {
											TreeParent valueProviderItem = new TreeParent(providerTreeItem.getDotPath());
											valueProviderItem.setUmlElement(providerTreeItem.getUmlElement()); // set the reference to the UML model element
											valueProviderItem.setIsValueProvider();
											valueProviderItem.setInstantiationTreeObject(providerTreeItem);
											valueProviderItem.setReadOnly(true);
											valueProvidersTitle.addChild(valueProviderItem); 
										}
									}
								}
								// normal uml model mode
								else {
									TreeParent valueProviderItem = new TreeParent(((Property)element).getName());
									valueProviderItem.setUmlElement(valueProvider); // set the reference to the UML model element
									valueProviderItem.setIsValueProvider();
									valueProviderItem.setReadOnly(true);
									valueProvidersTitle.addChild(valueProviderItem);
								}
							}
						}
					}
					
					// add clients
					if (item.isValueProvider()) {
						
//						TreeParent valueClientsTitle = new TreeParent(Constants.valueClientsNodeName);
						TreeParent valueClientsTitle = new TreeParent(Constants.potentialClientsNodeName);
						
						// no actions are allowed -> user should switch to mediator perspective for adding/deleting items
						valueClientsTitle.setReadOnly(true);
						
						mediator.addChild(valueClientsTitle);

						HashSet<Element> listOfClients = valueMediatorToValueClients.get(valueMediator);
						if (listOfClients != null ) {
							for (Element valueClient : listOfClients) {
								// if instantiated class mode -> get provider instances.
								if (item.getInstantiationTreeObject() != null && dc != null) {
									HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> clientTreeItemList = dc.getInstantiationTreeObjects(valueClient);
									if (clientTreeItemList != null) {
										for (org.openmodelica.modelicaml.common.instantiation.TreeObject clientTreeItem : clientTreeItemList) {
											TreeParent valueClientItem = new TreeParent(clientTreeItem.getDotPath());
											valueClientItem.setUmlElement(valueClient); // set the reference to the UML model element
											valueClientItem.setInstantiationTreeObject(clientTreeItem);
											valueClientItem.setIsValueClient();
											valueClientItem.setReadOnly(true);
											valueClientsTitle.addChild(valueClientItem); 
										}
									}
								}
								// normal uml model mode
								else {
									TreeParent valueClientItem = new TreeParent(((Property)element).getName());
									valueClientItem.setUmlElement(valueClient); // set the reference to the UML model element
									valueClientItem.setIsValueClient();
									valueClientItem.setReadOnly(true);
									valueClientsTitle.addChild(valueClientItem); 
								}
							}
						}
					}
				}
			}
		}
	}
	
	// ###################### READ-ONLY nodes END 
	
	
	// ###################### START: Collect and sort data 
	
	public void collectElementsFromModel(EObject umlRootElement){
		// null means that the the root of the UML model that is currently open in the browser will be used.
		setUmlModel(umlRootElement); // sets this.umlModel that is used in getUmlModel()
		
		if (getUmlModel() != null) {
			
			MediatorsCollector mc = new MediatorsCollector();
			mc.collectElementsFromModel(getUmlModel());
			
			valueMediatorContainers = mc.getValueMediatorContainers();
			valueMediators = mc.getValueMediators();
			
			importedMediatorContainers = mc.getImportedMediatorContainers();
//			importedMediators = mc.getImportedMediators();
		}
		else {
			// Do nothing because the the user will be informed in the viewer to reload the tree ...
//			System.err.println("Cannot access the root ModelicaML model for searching for value binding containers.");
		}
	}
		
	
	private void sortData(){
		for (Element valueMediator : valueMediators) {
			if (valueMediator instanceof Property) {
				EList<Dependency> mediatorDependencies = ((Property)valueMediator).getClientDependencies();
				for (Dependency dependency : mediatorDependencies) {
					
					// find providers
					if (dependency.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
						EList<Element> targets =dependency.getTargets();
						for (Element element : targets) {
							// add items to the overall map 
							addToMapList(valueMediatorToValueProviders, valueMediator, element);
						}
					}
					// find clients
					else if (dependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null) {
						EList<Element> targets =dependency.getTargets();
						for (Element element : targets) {
							// add items to the overall map 
							addToMapList(valueMediatorToValueClients, valueMediator, element);
						}
					}
				}
			}
		}
	}
	
	// ###################### END: Collect and sort data
	
	
	public EObject getUmlModel() {
		return umlModel;
	}

	public void setUmlModel(EObject umlRootElement) {
		if (umlRootElement == null) {
			UmlModel papyrusModel = UmlUtils.getUmlModel();
			if (papyrusModel != null ) {
				try {
					setUmlModel(papyrusModel.lookupRoot());
//					System.err.println(model);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
		}
		else {
			this.umlModel = umlRootElement;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private void addToMapList(@SuppressWarnings("rawtypes") HashMap map, Element key, Element value){
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ##################### OBSOLETE

	

//	private HashSet<Element> importedMembers = new HashSet<Element>();
	
//	public void collectElementsFromModel(EObject umlRootElement){
//		
//		// clear the imported members list.
//		importedMembers.clear();
//		
//		// null means that the the root of the UML model that is currently open in the browser will be used.
//		setUmlModel(umlRootElement); // sets this.umlModel that is used in getUmlModel()
//		
//		if (getUmlModel() != null) {
//			// collect the imported members for the root element
//			if (getUmlModel() instanceof Namespace) {
//				
//				Namespace element = (Namespace)getUmlModel();
//				EList<PackageableElement> importedElements = getImportedMembers(element);
//				if (importedElements != null && importedElements.size() > 0) {
////					importedMembers.addAll(importedElements);
//					for (PackageableElement packageableElement : importedElements) {
//						collectFromImportedMember(packageableElement);
//					}
//				}
//			}
//			
//			// get the iterator and all direct contents. 
//			Iterator<EObject> i = getUmlModel().eAllContents();
//
//			while (i.hasNext()) {
//				EObject object = i.next() ;
//				
//				// collect all imported elements 
//				if (object instanceof Namespace) {
//					EList<PackageableElement> importedElements = getImportedMembers((Namespace)object);
//					if (importedElements != null && importedElements.size() > 0) {
//						for (PackageableElement packageableElement2 : importedElements) {
//							collectFromImportedMember(packageableElement2);
//						}
//					}
//				}
//				
//				// collect mediator containers and mediators.
//				if (object instanceof Element) {
//					collectMediators((Element)object);
//				}
//				
//				// OLD
////				if (object instanceof Class && ((Element)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediatorsContainer) != null) {
////					valueMediatorContainers.add((Class) object) ;
////				}
////				else if (object instanceof Property && ((Element)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
////					valueMediators.add((Property) object);
////				}
//			}
//		}
//		else {
////			System.err.println("Cannot access the root ModelicaML model for searching for value binding containers.");
//		}
//	}
//	
//	
//	private void collectFromImportedMember(EObject importedMember) {
//		
//		// get imported members from itself
//		if (importedMember instanceof Namespace) {
//			Namespace element = (Namespace)getUmlModel();
//			EList<PackageableElement> importedElements = getImportedMembers(element);
//			
//			if (importedElements != null && importedElements.size() > 0) {
//				
//				// add to imported members in order enable recognizing cyclic runs.  
//				importedMembers.addAll(importedElements);
//				for (PackageableElement packageableElement : importedElements) {
//					// collect mediator containers and mediators.
//					if (packageableElement instanceof Element) {
//						collectMediators((Element)packageableElement);
//					}
//					// not yet considered -> recursively call
//					if (!importedMembers.contains(packageableElement)) {
//						if (packageableElement instanceof Namespace) {
//							collectFromImportedMember(packageableElement);
//						}
//					}
//				}
//			}
//		}
//
//		// iterate over all contained elements, collect mediators, and recursively collect mediators from all imported members
//		Iterator<EObject> i = importedMember.eAllContents();
//		while (i.hasNext()) {
//			EObject object = i.next() ;
//			
//			// collect mediator containers and mediators.
//			if (object instanceof Element) {
//				collectMediators((Element)object);
//			}
//			// not yet considered -> recursively call
//			if (!importedMembers.contains(object)) {
//				if (object instanceof Namespace) {
//					collectFromImportedMember(object);
//				}
//			}
//		}
//	}
//	
//	private void collectMediators(Element element){
//		// collect mediator containers and mediators
//		// avoid duplicates that can occure due to the multiple imports of the same mediators
//		
//		if (element instanceof Class && ((Element)element).getAppliedStereotype(Constants.stereotypeQName_ValueMediatorsContainer) != null
//				&& !valueMediatorContainers.contains(element)) {
//			
//			valueMediatorContainers.add((Class) element) ;
//		}
//		else if (element instanceof Property && ((Element)element).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null
//				&& !valueMediators.contains(element)) {
//			
//			valueMediators.add((Property) element);
//		}
//	}
//	
//	private EList<PackageableElement> getImportedMembers(Namespace element){
//		if (element != null) {
//			return element.getImportedMembers();
//		}
//		return null;
//	}
	
	
	
	
	
//	private void sortData(){
//		for (Element valueClient : valueClients) {
//			// Add to map
//			Stereotype stereotype = valueClient.getAppliedStereotype(Constants.stereotypeQName_ValueClient);
//			Object listOfMediators = valueClient.getValue(stereotype, Constants.stereotypeQName_ValueClient_obtainsValueFrom);
//			if (listOfMediators instanceof List) {
//				for (Object mediator : (List)listOfMediators) {
//					if (mediator instanceof EObject) {
//						EList<EObject> ref = ((EObject)mediator).eCrossReferences();
//						for (EObject eObject : ref) {
//							if (eObject instanceof Property && ((Element)eObject).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
//								addToMapList(valueMediatorToValueClients, (Element)eObject, valueClient);
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		for (Element valueProvider : valueProviders) {
//			// Add to map
//			Stereotype stereotype = valueProvider.getAppliedStereotype(Constants.stereotypeQName_ValueProvider);
//			Object listOfMediators = valueProvider.getValue(stereotype, Constants.stereotypeQName_ValueProvider_providesValueFor);
//			if (listOfMediators instanceof List) {
//				for (Object mediator : (List)listOfMediators) {
//					if (mediator instanceof EObject) {
//						EList<EObject> ref = ((EObject)mediator).eCrossReferences();
//						for (EObject eObject : ref) {
//							if (eObject instanceof Property && ((Element)eObject).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
//								addToMapList(valueMediatorToValueProviders, (Element)eObject, valueProvider);
//							}
//						}
//					}
//				}
//			}
//		}
//	}
	
	
////TODO: remove it.
//	private List<Element> getValueMediators(Element element, String stereotypeQName, String propertyName){
//		List<Element> list = new ArrayList<Element>();
//		
//		Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
//		if (stereotype != null) {
//			Object listOfMediators = element.getValue(stereotype, propertyName);
//			if (listOfMediators instanceof List) {
//				for (Object mediator : (List)listOfMediators) {
//					if (mediator instanceof EObject) {
//						list.add(UMLUtil.getBaseElement((EObject)mediator));
//					}
//				}
//			}
//		}
//		return list;
//	}
	
	
}

