package org.openmodelica.modelicaml.view.valuebindings.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.view.valuebindings.constants.Constants;


public class TreeBuilder {

	private EList<Class> valueMediatorContainers = new BasicEList<Class>();
	private EList<Property> valueMediators = new BasicEList<Property>();
	private EList<Property> valueClients = new BasicEList<Property>();
	private EList<Property> valueProviders = new BasicEList<Property>();
	
	public HashMap<Element, HashSet<Element>> valueMediatorToValueClients = new HashMap<Element, HashSet<Element>>();
	public HashMap<Element, HashSet<Element>> valueMediatorToValueProviders = new HashMap<Element, HashSet<Element>>();
	
	public EObject umlModel = null;
	
	public void buildTree(TreeParent root){
		valueMediatorContainers.clear();
		valueMediators.clear();
		valueClients.clear();
		valueProviders.clear();
		valueMediatorToValueClients.clear();
		valueMediatorToValueProviders.clear();
		
		collectElementsFromModel();
		sortData();
		
		if (getUmlModel() instanceof NamedElement) {
			TreeParent rootModelNode = new TreeParent("'" +  ((NamedElement)getUmlModel()).getName() + "' model value mediators");
			rootModelNode.setUmlElement((Element) getUmlModel());
			root.addChild(rootModelNode);
			
			for (Class valueBindingContainer : valueMediatorContainers) {
				
				Package nearestPackage = valueBindingContainer.getNearestPackage();
				TreeParent packageNode = null;

				if (nearestPackage != null && nearestPackage != getUmlModel() ) {
					
					// see this package already exist.
					TreeObject[] children = ((TreeParent)rootModelNode).getChildren();

					for (int i = 0; i < children.length; i++) {
						if (children[i].getUmlElement() == nearestPackage ) {
							packageNode = (TreeParent) children[i];
							break;
						}
					}
					
					// if there is no package node yet then create one
					if (packageNode == null) {
						packageNode = new TreeParent(nearestPackage.getName());
						// set the nearest package element and add item to tree.
						packageNode.setUmlElement(nearestPackage);
						rootModelNode.addChild(packageNode);
					}
		
					// add the container item to the tree.
					TreeParent item = new TreeParent(valueBindingContainer.getName());
					item.setUmlElement(valueBindingContainer);
					packageNode.addChild(item);
					
					// add mediators
					addValueMediators(item);
				}
				else { // if there is no package for containers then add them to the root model node.
					TreeParent item = new TreeParent(valueBindingContainer.getName());
					item.setUmlElement(valueBindingContainer);
					rootModelNode.addChild(item);
					
					// add mediators
					addValueMediators(item);
				}
			}
		}
		else {
			// create a hin as root
			TreeParent rootTitle = new TreeParent("Click on the \"(Re)load\" button to load value bindings ...");
			root.addChild(rootTitle);
		}
	}
	
	public void addValueMediators(TreeParent valueBindingsContainer){
		EList<Property> list = ((Class)valueBindingsContainer.getUmlElement()).getAllAttributes();
		for (Property property : list) {
			if (property.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
				
				TreeParent item = new TreeParent(property.getName());
				item.setUmlElement(property);
				valueBindingsContainer.addChild(item);

				// add clients
				TreeParent valueClientsTitle = new TreeParent(Constants.valueClientsTitleName);
				item.addChild(valueClientsTitle);
				
				HashSet<Element> listOfClients = valueMediatorToValueClients.get(property);
				if (listOfClients != null ) {
					for (Element element : listOfClients) {
						if (element instanceof Property) {
//							TreeParent valueClientItem = new TreeParent(((Property)element).getName());
							TreeObject valueClientItem = new TreeObject (((Property)element).getName());
							valueClientItem.setUmlElement(element); // set the reference to the UML model
							valueClientsTitle.addChild(valueClientItem); 
						}
					}
				}
				
				// add providers
				TreeParent valueProvidersTitle = new TreeParent(Constants.valueProvidersTitleName);
				item.addChild(valueProvidersTitle);

				HashSet<Element> listOfProviders = valueMediatorToValueProviders.get(property);
				if (listOfProviders != null ) {
					for (Element element : listOfProviders) {
						if (element instanceof Property) {
//							TreeParent valueProviderItem = new TreeParent(((Property)element).getName());
							TreeObject valueProviderItem = new TreeObject(((Property)element).getName());
							valueProviderItem.setUmlElement(element); // set the reference to the UML model
							valueProvidersTitle.addChild(valueProviderItem); 
						}
					}
				}
			}
		}
	}
	
	public void collectElementsFromModel(){
		UmlModel papyrusModel = UmlUtils.getUmlModel();
		if (papyrusModel != null ) {
			try {
				setUmlModel(papyrusModel.lookupRoot());
//				System.err.println(model);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		
		if (umlModel != null) {
			Iterator<EObject> i = umlModel.eAllContents();
			while (i.hasNext()) {
				EObject object = i.next() ;
				if (object instanceof Class && ((Element)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediatorsContainer) != null) {
					valueMediatorContainers.add((Class) object) ;
				}
				else if (object instanceof Property && ((Element)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
					valueMediators.add((Property) object);
				}
				else if (object instanceof Property && ((Element)object).getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null) {
					valueClients.add((Property) object);
				}
				else if (object instanceof Property && ((Element)object).getAppliedStereotype(Constants.stereotypeQName_ValueProvider) != null) {
					valueProviders.add((Property) object);
				}
			}
		}
		else {
//			System.err.println("Cannot access the root ModelicaML model for searching for value binding containers.");
		}
	}
	
	
	private void sortData(){
		for (Element valueClient : valueClients) {
			// Add to map
			Stereotype stereotype = valueClient.getAppliedStereotype(Constants.stereotypeQName_ValueClient);
			Object listOfMediators = valueClient.getValue(stereotype, Constants.stereotypeQName_ValueClient_obtainsValueFrom);
			if (listOfMediators instanceof List) {
				for (Object mediator : (List)listOfMediators) {
					if (mediator instanceof EObject) {
						EList<EObject> ref = ((EObject)mediator).eCrossReferences();
						for (EObject eObject : ref) {
							if (eObject instanceof Property && ((Element)eObject).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
								addToMapList(valueMediatorToValueClients, (Element)eObject, valueClient);
							}
						}
					}
				}
			}
		}
		
		for (Element valueProvider : valueProviders) {
			// Add to map
			Stereotype stereotype = valueProvider.getAppliedStereotype(Constants.stereotypeQName_ValueProvider);
			Object listOfMediators = valueProvider.getValue(stereotype, Constants.stereotypeQName_ValueProvider_providesValueFor);
			if (listOfMediators instanceof List) {
				for (Object mediator : (List)listOfMediators) {
					if (mediator instanceof EObject) {
						EList<EObject> ref = ((EObject)mediator).eCrossReferences();
						for (EObject eObject : ref) {
							if (eObject instanceof Property && ((Element)eObject).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
								addToMapList(valueMediatorToValueProviders, (Element)eObject, valueProvider);
							}
						}
					}
				}
			}
		}
	}
	
	public EObject getUmlModel() {
		return umlModel;
	}

	public void setUmlModel(EObject umlModel) {
		this.umlModel = umlModel;
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
	
}

