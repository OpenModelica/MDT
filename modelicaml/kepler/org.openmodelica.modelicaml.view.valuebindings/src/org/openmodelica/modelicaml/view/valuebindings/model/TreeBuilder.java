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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.view.valuebindings.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.valuebindings.helpers.BindingsData;
import org.openmodelica.modelicaml.common.valuebindings.helpers.BindingsDataCollector;
import org.openmodelica.modelicaml.common.valuebindings.helpers.MediatorsCollector;

public class TreeBuilder {

	private HashSet<Element> valueMediatorContainers = new HashSet<Element>();
	private HashSet<Element> valueMediators = new HashSet<Element>();

	// only the imported mediator containers
	private HashSet<Element> importedMediatorContainers = new HashSet<Element>();
	// only the imported mediators

	public HashMap<Element, HashSet<Element>> valueMediatorToValueClients = new HashMap<Element, HashSet<Element>>();
	public HashMap<Element, HashSet<Element>> valueMediatorToValueProviders = new HashMap<Element, HashSet<Element>>();

	private EList<TreeObject> valueClientsTreeItems = new BasicEList<TreeObject>(); // created
																					// tree
																					// clients
	private EList<TreeObject> valueProvidersTreeItems = new BasicEList<TreeObject>(); // created
																						// tree
																						// providers

	public EObject umlModel = null;

	public void buildTreeFromInstantiatedClass(
			TreeParent treeRoot,
			org.openmodelica.modelicaml.common.instantiation.ClassInstantiation classInstantiation,
			org.openmodelica.modelicaml.common.instantiation.TreeParent instantiatedClassTreeItem) {

		valueClientsTreeItems.clear();
		valueProvidersTreeItems.clear();

		if (instantiatedClassTreeItem != null) {
			String name = instantiatedClassTreeItem.getName();
			if (instantiatedClassTreeItem.getUmlElement() instanceof NamedElement) {
				name = ((NamedElement) instantiatedClassTreeItem
						.getUmlElement()).getName();
			}
			TreeParent rootModelNode = new TreeParent("Mediators used in "
					+ name);
			rootModelNode.setInstantiatedClass(true);
			// rootModelNode.setUmlElement(instantiatedClassTreeItem.getSelectedClass());
			treeRoot.addChild(rootModelNode);

			// NOTE: we have the mediators right here. There is no need to try
			// to re-collect them if the list is empty.
			BindingsDataCollector dc = new BindingsDataCollector(false);
			// dc.setAllMediators(valueMediators);
			// dc.collectAll(instantiatedClassTreeItem.getSelectedClass().getModel(),
			// classInstantiation, instantiatedClassTreeItem);
			dc = classInstantiation.getValueBindingsDataCollector();

			// add mediators, clients and providers - Mediator Perspective
			addValueMediatorsUsedInInstantaitedClass(rootModelNode, dc);

			// add client perspective nodes
			createClientPerspectiveNodes(treeRoot, dc, " in " + name);

			// add provider perspective nodes
			createProviderPerspectiveNodes(treeRoot, dc, " in " + name);
		} else {

			// create a hint as root
			TreeParent rootTitle = new TreeParent(
					"Instantiate a class in the Components Tree View and click on the \"(Re)load\" ...");
			treeRoot.addChild(rootTitle);
		}
	}

	public void addValueMediatorsUsedInInstantaitedClass(
			TreeParent instantiatedClassTreeItem, BindingsDataCollector dc) {

		valueMediatorToValueClients.clear();
		valueMediatorToValueProviders.clear();

		if (dc.getUsedMediators() != null) {
			for (Element mediator : dc.getUsedMediators()) {
				if (mediator instanceof NamedElement
						&& mediator
								.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
					Element valueMediatorElement = mediator;
					TreeParent item = new TreeParent(
							((NamedElement) mediator).getName());
					item.setUmlElement(mediator);
					instantiatedClassTreeItem.addChild(item);

					// add clients
					TreeParent valueClientsTitle = new TreeParent(
							Constants.valueClientsNodeName);
					item.addChild(valueClientsTitle);

					EList<Element> listOfClients = getValueClients(valueMediatorElement);
					for (Element clientElement : listOfClients) {
						HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> clientTreeItemList = dc
								.getInstantiationTreeObjects(clientElement);
						if (clientTreeItemList != null) {
							for (org.openmodelica.modelicaml.common.instantiation.TreeObject clientTreeItem : clientTreeItemList) {
								TreeParent valueClientItem = new TreeParent(
										clientTreeItem.getDotPath());
								valueClientItem.setUmlElement(clientTreeItem
										.getUmlElement()); // set the reference
															// to the UML model
															// element
								valueClientItem.setIsValueClient();
								valueClientItem
										.setInstantiationTreeObject(clientTreeItem);
								valueClientsTitle.addChild(valueClientItem);

								// used to create the client perspective
								valueClientsTreeItems.add(valueClientItem);

								addToMapList(valueMediatorToValueClients,
										mediator,
										clientTreeItem.getUmlElement());
							}
						}
					}

					// add providers
					TreeParent valueProvidersTitle = new TreeParent(
							Constants.valueProvidersNodeName);
					item.addChild(valueProvidersTitle);

					EList<Element> listOfProviders = getValueProviders(valueMediatorElement);
					for (Element providerElement : listOfProviders) {
						HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> providerTreeItemList = dc
								.getInstantiationTreeObjects(providerElement);
						if (providerTreeItemList != null) {
							for (org.openmodelica.modelicaml.common.instantiation.TreeObject providerTreeItem : providerTreeItemList) {
								TreeParent valueProviderItem = new TreeParent(
										providerTreeItem.getDotPath());
								valueProviderItem
										.setUmlElement(providerTreeItem
												.getUmlElement()); // set the
																	// reference
																	// to the
																	// UML model
																	// element
								valueProviderItem.setIsValueProvider();
								valueProviderItem
										.setInstantiationTreeObject(providerTreeItem);
								valueProvidersTitle.addChild(valueProviderItem);

								// used to create the provider perspective
								valueProvidersTreeItems.add(valueProviderItem);

								addToMapList(valueMediatorToValueProviders,
										mediator,
										providerTreeItem.getUmlElement());
							}
						}
					}
				}
			}
		}
	}

	public void buildTreeFromUmlModel(TreeParent treeRoot) {

		// clear all lists
		valueMediatorContainers.clear();
		valueMediators.clear();

		// valueClients.clear();
		// valueProviders.clear();

		valueMediatorToValueClients.clear();
		valueMediatorToValueProviders.clear();

		valueClientsTreeItems.clear();
		valueProvidersTreeItems.clear();

		// get the UML root element, collect and sort data from the UML model
		collectElementsFromModel(null);
		sortData();

		if (getUmlModel() instanceof NamedElement) {
			TreeParent rootModelNode = new TreeParent("'"
					+ ((NamedElement) getUmlModel()).getName()
					+ "' model value mediators");
			rootModelNode.setUmlElement((Element) getUmlModel());
			treeRoot.addChild(rootModelNode);

			for (Element valueBindingContainer : valueMediatorContainers) {

				Package nearestPackage = valueBindingContainer
						.getNearestPackage();
				TreeParent packageNode = null;

				// import indicator
				String importedPrefix = "";
				if (importedMediatorContainers.contains(valueBindingContainer)) {
					importedPrefix = "(imported) ";
				}

				if (nearestPackage != null && nearestPackage != getUmlModel()) {

					// see if this package already exist.
					TreeObject[] children = ((TreeParent) rootModelNode)
							.getChildren();

					for (int i = 0; i < children.length; i++) {
						if (children[i].getUmlElement() == nearestPackage) {
							packageNode = (TreeParent) children[i];
							break;
						}
					}

					// if there is no package node yet then create one
					if (packageNode == null) {
						packageNode = new TreeParent(importedPrefix
								+ nearestPackage.getName());
						// set the nearest package element and add item to tree.
						packageNode.setUmlElement(nearestPackage);
						rootModelNode.addChild(packageNode);
					}

					// add the container item to the tree.
					// TreeParent item = new TreeParent(importedPrefix +
					// ((NamedElement)valueBindingContainer).getName());
					TreeParent item = new TreeParent(
							((NamedElement) valueBindingContainer).getName());
					item.setUmlElement(valueBindingContainer);
					packageNode.addChild(item);

					// add mediators, clients and providers
					addValueMediators(item);
				} else { // if there is no package for containers then add them
							// to the root model node.
					// TreeParent item = new TreeParent(importedPrefix +
					// ((NamedElement)valueBindingContainer).getName());
					TreeParent item = new TreeParent(
							((NamedElement) valueBindingContainer).getName());
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

		} else {
			// create a hint as root
			TreeParent rootTitle = new TreeParent(
					"Click on the \"(Re)load\" button to load bindings ...");
			treeRoot.addChild(rootTitle);
		}
	}

	public void addValueMediators(TreeParent valueBindingsContainer) {

		// TODO: this should be adopted when inheritance wil be allowed for
		// mediators.
		// EList<Property> list =
		// ((Class)valueBindingsContainer.getUmlElement()).getAllAttributes();
		EList<Property> list = ((Class) valueBindingsContainer.getUmlElement())
				.getAttributes();

		for (Property property : list) {
			if (property
					.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
				Element valueMediatorElement = property;
				TreeParent item = new TreeParent(property.getName());
				item.setUmlElement(property);
				valueBindingsContainer.addChild(item);

				// add clients
				TreeParent valueClientsTitle = new TreeParent(
						Constants.valueClientsNodeName);
				item.addChild(valueClientsTitle);

				// HashSet<Element> listOfClients =
				// valueMediatorToValueClients.get(property);
				EList<Element> listOfClients = getValueClients(valueMediatorElement);
				EList<Element> listOfRequiredClients = getRequiredValueClients(valueMediatorElement);

				if (listOfClients != null) {
					for (Element element : listOfClients) {
						if (element instanceof Property) {
							TreeParent valueClientItem = new TreeParent(
									((Property) element).getName());
							// TreeObject valueClientItem = new TreeObject
							// (((Property)element).getName());
							valueClientItem.setUmlElement(element); // set the
																	// reference
																	// to the
																	// UML model
																	// element
							valueClientItem.setIsValueClient();
							valueClientsTitle.addChild(valueClientItem);

							// set required client
							if (listOfRequiredClients.contains(element)) {
								valueClientItem.setValueClient_required(true);
							}

							valueClientsTreeItems.add(valueClientItem);

							// adds cyclic tree nodes, i.e. it adds a to a
							// client its mediator nodes followed by providers,
							// and vice versa
							// addReadOnlyNodes(valueClientItem);
						}
					}
				}

				// add providers
				TreeParent valueProvidersTitle = new TreeParent(
						Constants.valueProvidersNodeName);
				item.addChild(valueProvidersTitle);

				// HashSet<Element> listOfProviders =
				// valueMediatorToValueProviders.get(property);
				EList<Element> listOfProviders = getValueProviders(valueMediatorElement);
				if (listOfProviders != null) {
					for (Element element : listOfProviders) {
						if (element instanceof Property) {
							TreeParent valueProviderItem = new TreeParent(
									((Property) element).getName());
							// TreeObject valueProviderItem = new
							// TreeObject(((Property)element).getName());
							valueProviderItem.setUmlElement(element); // set the
																		// reference
																		// to
																		// the
																		// UML
																		// model
																		// element
							valueProviderItem.setIsValueProvider();
							valueProvidersTitle.addChild(valueProviderItem);

							valueProvidersTreeItems.add(valueProviderItem);

							// adds cyclic tree nodes, i.e. adds a to a client
							// its mediator nodes followed by providers, and
							// vice versa
							// addReadOnlyNodes(valueProviderItem);
						}
					}
				}
			}
		}
	}

	private void createClientPerspectiveNodes(TreeObject rootItem,
			BindingsDataCollector dc, String titlePostFix) {
		if (rootItem instanceof TreeParent) {
			// add clients
			TreeParent valueClientsTitle = new TreeParent(
					Constants.valueClientsNodeName + titlePostFix);
			valueClientsTitle.setReadOnly(true);

			((TreeParent) rootItem).addChild(valueClientsTitle);

			for (TreeObject client : valueClientsTreeItems) {
				TreeParent clientCopy = new TreeParent(client.getName());
				clientCopy.setUmlElement(client.getUmlElement());
				clientCopy.setInstantiationTreeObject(client
						.getInstantiationTreeObject());
				clientCopy.setIsValueClient();
				clientCopy.setReadOnly(true);

				valueClientsTitle.addChild(clientCopy);
				if (client instanceof TreeParent) {
					addReadOnlyNodes((TreeParent) clientCopy, dc);
				}
			}
		}
	}

	private void createProviderPerspectiveNodes(TreeObject rootItem,
			BindingsDataCollector dc, String titlePostFix) {
		if (rootItem instanceof TreeParent) {
			// add providers
			TreeParent valueProvidersTitle = new TreeParent(
					Constants.valueProvidersNodeName + titlePostFix);
			valueProvidersTitle.setReadOnly(true);

			((TreeParent) rootItem).addChild(valueProvidersTitle);

			for (TreeObject provider : valueProvidersTreeItems) {
				TreeParent providerCopy = new TreeParent(provider.getName());
				providerCopy.setUmlElement(provider.getUmlElement());
				providerCopy.setInstantiationTreeObject(provider
						.getInstantiationTreeObject());
				providerCopy.setIsValueProvider();
				providerCopy.setReadOnly(true);

				valueProvidersTitle.addChild(providerCopy);
				if (provider instanceof TreeParent) {
					addReadOnlyNodes((TreeParent) providerCopy, dc);
				}
			}
		}
	}

	// Get clients or providers based on mediator dependencies
	public EList<Element> getValueClients(Element valueMediator) {
		EList<Element> clientList = new BasicEList<Element>();
		if (valueMediator instanceof Property) {
			EList<Dependency> mediatorDependencies = ((Property) valueMediator)
					.getClientDependencies();
			for (Dependency dependency : mediatorDependencies) {
				if (dependency
						.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null) {
					if (dependency.getTargets() != null
							&& dependency.getTargets().size() > 0) {
						clientList.addAll(dependency.getTargets());
					}
				}
			}
		}
		return clientList;
	}

	public EList<Element> getRequiredValueClients(Element valueMediator) {
		EList<Element> requiredClientList = new BasicEList<Element>();
		if (valueMediator instanceof Property) {
			EList<Dependency> mediatorDependencies = ((Property) valueMediator)
					.getClientDependencies();
			for (Dependency dependency : mediatorDependencies) {
				Stereotype s_providesValueFor = dependency
						.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor);
				if (s_providesValueFor != null) {
					Object o = dependency.getValue(s_providesValueFor,
							Constants.propertyName_isRequired);
					if (o instanceof Boolean && ((Boolean) o) == true) {
						if (dependency.getTargets() != null
								&& dependency.getTargets().size() > 0) {
							requiredClientList.addAll(dependency.getTargets());
						}
					}
				}
			}
		}
		return requiredClientList;
	}

	public EList<Element> getValueProviders(Element valueMediator) {
		EList<Element> providersList = new BasicEList<Element>();
		if (valueMediator instanceof Property) {
			EList<Dependency> mediatorDependencies = ((Property) valueMediator)
					.getClientDependencies();
			for (Dependency dependency : mediatorDependencies) {
				if (dependency
						.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
					if (dependency.getTargets() != null
							&& dependency.getTargets().size() > 0) {
						providersList.addAll(dependency.getTargets());
					}
				}
			}
		}
		return providersList;
	}

	// ###################### READ-ONLY nodes

	public void addReadOnlyNodes(TreeParent item, BindingsDataCollector dc) {

		Element element = item.getUmlElement();
		if (element != null) {
			List<Element> list = new ArrayList<Element>();
			if (item.isValueClient()) {
				list = ModelicaMLServices.getSortedByName(TreeUtls
						.getReferencingMediators(element, valueMediators,
								Constants.stereotypeQName_ProvidesValueFor));
			}
			if (item.isValueProvider()) {
				list = ModelicaMLServices.getSortedByName(TreeUtls
						.getReferencingMediators(element, valueMediators,
								Constants.stereotypeQName_ObtainsValueFrom));
			}

			int numberOfMediators = list.size();

			if (numberOfMediators > 0) { // if there are mediators at all.
				// create mediators node
				// TreeParent mediatorsNode = new TreeParent("subscribed to " +
				// numberOfMediators + " mediators");
				TreeParent mediatorsNode = new TreeParent(
						Constants.valueMediatorsNodeName);
				mediatorsNode.setReadOnly(true);
				item.addChild(mediatorsNode);

				// add mediators
				for (Element valueMediator : list) {
					TreeParent mediator = new TreeParent(
							((NamedElement) valueMediator).getName());
					mediator.setUmlElement(valueMediator);
					mediator.setReadOnly(true); // this is only for reading, not
												// for editing.
					mediatorsNode.addChild(mediator);

					// add providers
					if (item.isValueClient()) {

						TreeParent valueProvidersTitle = new TreeParent(
								Constants.valueProvidersNodeName);

						// no actions are allowed -> user should switch to
						// mediator perspective for adding/deleting items
						valueProvidersTitle.setReadOnly(true);

						mediator.addChild(valueProvidersTitle);

						HashSet<Element> listOfProviders = valueMediatorToValueProviders
								.get(valueMediator);
						if (listOfProviders != null) {
							for (Element valueProvider : listOfProviders) {
								// if instantiated class mode -> get provider
								// instances.
								if (item.getInstantiationTreeObject() != null
										&& dc != null) {
									HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> providerTreeItemList = dc
											.getInstantiationTreeObjects(valueProvider);
									if (providerTreeItemList != null) {
										for (org.openmodelica.modelicaml.common.instantiation.TreeObject providerTreeItem : providerTreeItemList) {
											TreeParent valueProviderItem = new TreeParent(
													providerTreeItem
															.getDotPath());
											valueProviderItem
													.setUmlElement(providerTreeItem
															.getUmlElement()); // set
																				// the
																				// reference
																				// to
																				// the
																				// UML
																				// model
																				// element
											valueProviderItem
													.setIsValueProvider();
											valueProviderItem
													.setInstantiationTreeObject(providerTreeItem);
											valueProviderItem.setReadOnly(true);
											valueProvidersTitle
													.addChild(valueProviderItem);
										}
									}
								}
								// normal uml model mode
								else {
									TreeParent valueProviderItem = new TreeParent(
											((Property) element).getName());
									valueProviderItem
											.setUmlElement(valueProvider); // set
																			// the
																			// reference
																			// to
																			// the
																			// UML
																			// model
																			// element
									valueProviderItem.setIsValueProvider();
									valueProviderItem.setReadOnly(true);
									valueProvidersTitle
											.addChild(valueProviderItem);
								}
							}
						}
					}

					// add clients
					if (item.isValueProvider()) {

						// TreeParent valueClientsTitle = new
						// TreeParent(Constants.valueClientsNodeName);
						TreeParent valueClientsTitle = new TreeParent(
								Constants.potentialClientsNodeName);

						// no actions are allowed -> user should switch to
						// mediator perspective for adding/deleting items
						valueClientsTitle.setReadOnly(true);

						mediator.addChild(valueClientsTitle);

						HashSet<Element> listOfClients = valueMediatorToValueClients
								.get(valueMediator);
						if (listOfClients != null) {
							for (Element valueClient : listOfClients) {
								// if instantiated class mode -> get provider
								// instances.
								if (item.getInstantiationTreeObject() != null
										&& dc != null) {
									HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> clientTreeItemList = dc
											.getInstantiationTreeObjects(valueClient);
									if (clientTreeItemList != null) {
										for (org.openmodelica.modelicaml.common.instantiation.TreeObject clientTreeItem : clientTreeItemList) {
											TreeParent valueClientItem = new TreeParent(
													clientTreeItem.getDotPath());
											valueClientItem
													.setUmlElement(valueClient); // set
																					// the
																					// reference
																					// to
																					// the
																					// UML
																					// model
																					// element
											valueClientItem
													.setInstantiationTreeObject(clientTreeItem);
											valueClientItem.setIsValueClient();
											valueClientItem.setReadOnly(true);
											valueClientsTitle
													.addChild(valueClientItem);
										}
									}
								}
								// normal uml model mode
								else {
									TreeParent valueClientItem = new TreeParent(
											((Property) element).getName());
									valueClientItem.setUmlElement(valueClient); // set
																				// the
																				// reference
																				// to
																				// the
																				// UML
																				// model
																				// element
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

	public void collectElementsFromModel(EObject umlRootElement) {
		// null means that the the root of the UML model that is currently open
		// in the browser will be used.
		setUmlModel(umlRootElement); // sets this.umlModel that is used in
										// getUmlModel()

		if (getUmlModel() != null) {

			MediatorsCollector mc = new MediatorsCollector();
			mc.collectElementsFromModel(getUmlModel());

			valueMediatorContainers = mc.getValueMediatorContainers();
			valueMediators = mc.getValueMediators();

			importedMediatorContainers = mc.getImportedMediatorContainers();
			// importedMediators = mc.getImportedMediators();
		} else {
			// Do nothing because the the user will be informed in the viewer to
			// reload the tree ...
			// System.err.println("Cannot access the root ModelicaML model for searching for value binding containers.");
		}
	}

	private void sortData() {

		// clear the data in the common service in order to
		BindingsData.clearAll();

		// collect data to pass to the common service for other views
		HashSet<Element> clients = new HashSet<Element>();
		HashSet<Element> clientsMandatory = new HashSet<Element>();
		HashSet<Element> providers = new HashSet<Element>();

		for (Element valueMediator : valueMediators) {
			if (valueMediator instanceof Property) {
				EList<Dependency> mediatorDependencies = ((Property) valueMediator)
						.getClientDependencies();
				for (Dependency dependency : mediatorDependencies) {

					// find providers
					if (dependency
							.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
						EList<Element> targets = dependency.getTargets();
						for (Element element : targets) {
							// add items to the overall map
							addToMapList(valueMediatorToValueProviders,
									valueMediator, element);

							// collect to pass to the common service
							providers.add(element);
						}
					}
					// find clients
					else if (dependency
							.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null) {
						EList<Element> targets = dependency.getTargets();
						for (Element element : targets) {
							// add items to the overall map
							addToMapList(valueMediatorToValueClients,
									valueMediator, element);

							// collect to pass to the common service
							clients.add(element);
							clientsMandatory
									.addAll(getRequiredValueClients(valueMediator));
						}
					}
				}
			}
		}

		// pass collected bindings data to the common service so that other
		// views can use it
		BindingsData.setBindingsData(clients, clientsMandatory, providers,
				valueMediators);

	}

	// ###################### END: Collect and sort data

	public EObject getUmlModel() {
		return umlModel;
	}

	public void setUmlModel(EObject umlRootElement) {

		// if the model is unknown -> get it from editor
		if (umlRootElement == null) {
			UmlModel papyrusModel;
			try {
				papyrusModel = UmlUtils.getUmlModel();
				if (papyrusModel != null) {
					try {
						setUmlModel(papyrusModel.lookupRoot());
					} catch (NotFoundException e) {
						openModelAccessError();
					}
				}
				// else {
				// openModelAccessError();
				// }
			} catch (Exception e1) {
				// openModelAccessError();
			}
		} else {
			this.umlModel = umlRootElement;
		}
	}

	private void openModelAccessError() {
		String errorTitle = "Bindings View Reload Error";
		String message = "Could not access the ModelicaML model in editor. "
				+ "Please make sure the the model is opened in edtor, click on a diagram and then reload again.";
		MessageDialog.openError(ModelicaMLServices.getShell(), errorTitle,
				message);
	}

	@SuppressWarnings({ "unchecked" })
	private void addToMapList(@SuppressWarnings("rawtypes") HashMap map,
			Element key, Element value) {
		Object list = map.get(key);
		if (list instanceof HashSet) {
			((HashSet<Element>) list).add(value);
			map.put(key, list);
		} else {
			HashSet<Element> newList = new HashSet<Element>();
			newList.add(value);
			map.put(key, newList);
		}
	}

	// ##################### GETTER

	public HashSet<Element> getValueMediators() {
		return valueMediators;
	}
}
