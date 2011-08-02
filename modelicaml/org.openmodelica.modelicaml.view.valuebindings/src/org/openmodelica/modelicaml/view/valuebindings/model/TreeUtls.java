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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView;

public class TreeUtls {

	
	
	

	
	public static String getStringPropertyFromElement(Element element, String stereotypeQName, String propertyName) {
		String stringProperty = null;
		
		Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
		if (stereotype != null) {
			Object o = element.getValue(stereotype, propertyName);
			if (o instanceof String) {
				stringProperty = (String)o;
			}
		}
		
		return stringProperty;
	}
	
	public static EList<String> getStringListPropertyFromElement(Element element, String stereotypeQName, String propertyName) {
		EList<String> stringPropertyList = null;
		
		Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
		if (stereotype != null) {
			Object o = element.getValue(stereotype, propertyName);
			if (o instanceof EList<?>) {
				stringPropertyList = (EList<String>)o;
			}
		}
		return stringPropertyList;
	}
	
	public static EList<Element> getReferencingMediators(Element targetElement, EList<Element> mediators, String dependencyStereotypeQName){
		EList<Element> referencingMediators = new BasicEList<Element>();
		for (Element mediator : mediators) {
			if (mediator instanceof NamedElement) {
				EList<Dependency> depList = ((NamedElement)mediator).getClientDependencies();
				for (Dependency dependency : depList) {
					if (dependency.getAppliedStereotype(dependencyStereotypeQName) != null) {
						for (Element target : dependency.getTargets()) {
							if (target == targetElement) {
								referencingMediators.add(mediator);
							}
						}
					}
				}
			}
		}
		return referencingMediators;
	}
	
	
	
	public static TreeObject getNearestMediator(TreeObject item) {
		TreeObject parent = item.getParent(); 
		if (parent != null) {
			if (parent.isValueMediator()) {
				return parent;
			}
			else {
				return getNearestMediator(parent);
			}
		}
		return null;
	}
	
	public static EList<Dependency> getMediatorDependency(NamedElement mediator, NamedElement target, String dependencyStereotypeQName){
		EList<Dependency> mediatorDependencies = mediator.getClientDependencies();
		EList<Dependency> dependenciesToTarget = new BasicEList<Dependency>(); 
		
		// find dependencies to the target element
		for (Dependency dependency : mediatorDependencies) {
			// if the dependency has the right stereotype (i.e., ProvidesValueFor or ObtainsValueFrom)
			if ( dependency.getAppliedStereotype(dependencyStereotypeQName) != null ) {
				for (Element element : dependency.getTargets()) {
					if (element == target) {
						dependenciesToTarget.add(dependency);
					}
				}
			}
		}
		return dependenciesToTarget;
	}
	
	
	//###################################### Store and Delete
	
	public static boolean storeMediatorReference(final Element valueMediatorElement, final EObject valueClientOrProviderElement, final int mode){

		if (valueMediatorElement instanceof Property && valueClientOrProviderElement instanceof NamedElement) {
			EList<Dependency> mediatorDependencies = ((Property)valueMediatorElement).getClientDependencies();
			boolean targetAlreadyExists = false;
			
			List<Dependency> exisitingDepToClient = new ArrayList<Dependency>();
			List<Dependency> exisitingDepToProvider = new ArrayList<Dependency>();
			
			for (Dependency dep : mediatorDependencies) {
				for (Element target : dep.getTargets()) {
					
					// dep to a provider or other mediator
					Stereotype depStereotype = dep.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom);
					if (depStereotype == null) {
						depStereotype = dep.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor);
					}
					
					if (target == valueClientOrProviderElement) {
						if (dep.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null) {
							exisitingDepToClient.add(dep);
						}
						if (dep.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
							exisitingDepToProvider.add(dep);
						}
					}
				}
			}
			
			String modeName = "";
			if (mode == Constants.MODE_ADD_CLIENT && exisitingDepToClient.size() > 0) {
				// set the indicator 
				targetAlreadyExists = true;
				modeName = "client reference";
			}
			else if (mode == Constants.MODE_ADD_PROVIDER && exisitingDepToProvider.size() > 0) {
				// set the indicator 
				targetAlreadyExists = true;
				modeName = "provider reference";
			}

			if (targetAlreadyExists) {
				// inform the user
				MessageDialog.openError(new Shell(SWT.ON_TOP), "Error", 
						"'" + ((NamedElement)valueMediatorElement).getQualifiedName() + "' \n"
						+ " already has a " + modeName + " to the element \n" +
								"'" + ((NamedElement)valueClientOrProviderElement).getQualifiedName() + "'\n"
						+ "\n\nNo reference was stored or updated."); 
				return false;
			}
			
			if ( !targetAlreadyExists ) {
				TransactionalEditingDomain editingDomain = getTransactionalEditingDomain();
				CompoundCommand cc = new CompoundCommand("Add value mediator reference");
				Command command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						Dependency dependency = ((Property)valueMediatorElement).createDependency((NamedElement) valueClientOrProviderElement);
						if (dependency != null) {
							dependency.setName(getDependecyName(mode) + ((NamedElement)valueClientOrProviderElement).getName());

							Stereotype dependencyStereotype = dependency.getApplicableStereotype(getDependecyStereotypeQName(mode));
							if (dependencyStereotype != null) {
								dependency.applyStereotype(dependencyStereotype);
								
								// Clients of a requirement should always be required. 
								// if it is a dependency from a mediator to a client 
								if (dependencyStereotype.getQualifiedName().equals(Constants.stereotypeQName_ProvidesValueFor)) {
									// if it is a client property of a requirement -> set is required
									if (valueClientOrProviderElement instanceof Property &&
									((Property)valueClientOrProviderElement).getNamespace().getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
										dependency.setValue(dependencyStereotype, Constants.propertyName_isRequired, true);
									}
								}
							}
							else {
								MessageDialog.openError(new Shell(), "Error", 
										"Could not set the Stereotype " + getDependecyStereotypeQName(mode) + " for " 
										+ ((NamedElement)valueMediatorElement).getQualifiedName() + " to reference the element "
										+ ((NamedElement)valueClientOrProviderElement).getQualifiedName()
										+ "\nCheck if the ModelicaML Value Bindings Profile is applied."
										+ "\n\nNo reference was stored or updated.");

								// delete the dependency because a stereotype must be applied ...
								dependency.destroy();
							}
						}
					}
				};
				cc.append(command);
				editingDomain.getCommandStack().execute(cc);
			}
		}
		return true;
	}
	
	private static String getDependecyStereotypeQName(int mode){
		//The stereotype for the dependency from the mediator to the selected client or provider
		
		if ( mode == Constants.MODE_ADD_CLIENT ) {
			return Constants.stereotypeQName_ProvidesValueFor;
		}
		else if (mode == Constants.MODE_ADD_PROVIDER ) {
			return Constants.stereotypeQName_ObtainsValueFrom;
		}
		return null;
	}
	
	private static String getDependecyName(int mode){
		//The stereotype for the dependency from the mediator to the selected client or provider
		
		if ( mode == Constants.MODE_ADD_CLIENT ) {
			return "Provides value for: ";
		}
		else if (mode == Constants.MODE_ADD_PROVIDER ) {
			return "Obtains value from: ";
		}
		return null;
	}
	
	
	
	public static boolean deleteDependencyFromMediator(final TreeObject dependencyTargetItem){
		
		EList<Dependency> dependeciesToBeDeleted = new BasicEList<Dependency>();

		TreeObject mediator = TreeUtls.getNearestMediator(dependencyTargetItem);
		if (mediator != null) {
			Element mediatorElement = mediator.getUmlElement();
			Element clientOrProviderElement = dependencyTargetItem.getUmlElement();
			if (mediatorElement instanceof Property && clientOrProviderElement instanceof NamedElement) {
				EList<Dependency> mediatorDependencies = ((Property)mediatorElement).getClientDependencies();
				
				// find dependencies to the selected item (client or mediator)
				for (Dependency dependency : mediatorDependencies) {
					EList<Element> targets = dependency.getTargets();
					for (Element element : targets) {
						if (element == clientOrProviderElement) {
							// TODO: should we check also if the stereotype is applied? 
							dependeciesToBeDeleted.add(dependency);
						}
					}
				}
			}
			else {
				MessageDialog.openError(new Shell(), "Error", "Invalid mediator reference for " + dependencyTargetItem.getName());
				return false;
			}
		}
		else {
			MessageDialog.openError(new Shell(), "Error", "Could not find the Mediator for " + dependencyTargetItem.getName());
			return false;
		}

		if (dependeciesToBeDeleted.size() > 0 ) {
			final EList<Dependency> dependeciesToBeDeleted_final = dependeciesToBeDeleted;
			TransactionalEditingDomain editingDomain = getTransactionalEditingDomain();
			CompoundCommand cc = new CompoundCommand("Delete mediator reference");
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					// delete dependencies
					for (Dependency dependency : dependeciesToBeDeleted_final) {
//						System.err.println("Deleting: " + dependency.getName());
						dependency.destroy();
					}
					}
			};
			cc.append(command);
			editingDomain.getCommandStack().execute(cc);
			return true;
		}
		return false;
	}
	
	
	
	private static TransactionalEditingDomain getTransactionalEditingDomain(){
		return EditorUtils.getTransactionalEditingDomain();
	}
	
	
	//############################## Preferred Providers Handling
	public static String getPreferredProviderAssignmentString(String clientPath, String providerPath){
		if (clientPath != null  && !clientPath.equals("") && providerPath != null && !providerPath.equals("") ) {
			return clientPath + Constants.preferredProvidersAssignmentSeparator + providerPath;
		}
		return null;
	}

	public static boolean addToPreferredProvider(final Element mediator, String clientPath, String providerPath){
		
		if (mediator != null && !clientPath.isEmpty() && !providerPath.isEmpty()) {
			
			EList<String> tempList = null; // working list
			
			EList<String> preferredPoviders = getStringListPropertyFromElement(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_preferredProviders);

			if (preferredPoviders != null) { // if a list exists
				
				// create the final list
				tempList = new BasicEList<String>();
				
//				// collect only items that do not start with client path
//				for (String string : preferredPoviders) {
//					if ( !startsWithPath(string, clientPath) ) {
//						tempList.add(string);
//					}
//				}
				
				tempList.addAll(preferredPoviders);
				
				// add the new assignment to the final list
				// avoid duplicates, i.e. add only the new assignment if it is not contained in the list.
				if ( !clientPreferredProviderPairExists(tempList, clientPath, providerPath)) {
					tempList.add(getPreferredProviderAssignmentString(clientPath, providerPath));	
				}
			}
			else {
				// add new list
				tempList = new BasicEList<String>();
				tempList.add(getPreferredProviderAssignmentString(clientPath, providerPath));
			}
			
			// if the list was created -> store it in the mediator
			if (tempList != null) {

				final EList<String> finalList = tempList;
				
				TransactionalEditingDomain editingDomain = getTransactionalEditingDomain();
				CompoundCommand cc = new CompoundCommand("Add preferred providers to the mediator.");
				Command command = new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						Stereotype stereotype = mediator.getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
						if (stereotype != null) {
							mediator.setValue(stereotype, Constants.propertyName_preferredProviders, finalList);
						}
					}
				};
				cc.append(command);
				editingDomain.getCommandStack().execute(cc);
				
				return true;
			}
			
		}
		return false;
	}

	
	private static boolean startsWithPath(String preferredProviderAssignment, String path){
		String[] splitted = preferredProviderAssignment.split(Constants.preferredProvidersAssignmentSeparator);
		if (splitted.length == 2 ) { // if there is a pair with the separator
			if (splitted[0].trim().equals(path)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean clientPreferredProviderPairExists(EList<String> preferredPoviders, String clientPath, String providerPath){
		for (String preferredProviderAssignment : preferredPoviders) {
			String[] splitted = preferredProviderAssignment.split(Constants.preferredProvidersAssignmentSeparator);
			if (splitted.length == 2 ) { // if there is a pair with the separator
				if (splitted[0].trim().equals(clientPath) && splitted[1].trim().equals(providerPath) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isPreferredProviderForClient(EList<String> preferredProviders, String clientPath, String providerPath){
		if (preferredProviders != null) {
			for (String preferredProviderAssignment : preferredProviders) {
				String[] splitted = preferredProviderAssignment.split(Constants.preferredProvidersAssignmentSeparator);
				if (splitted.length == 2 ) { // if there is a pair with the separator
					if (splitted[0].trim().equals(clientPath) && splitted[1].trim().equals(providerPath)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public static HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> getPreferredProvidersForClient(
			Element mediator, 
			HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> providers, 
			String clientPath ){
		
		HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> preferredProviders = new HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject>();
		
		if (mediator != null && providers != null) {
			EList<String> preferredProvidersList = TreeUtls.getStringListPropertyFromElement(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_preferredProviders);
			for ( org.openmodelica.modelicaml.common.instantiation.TreeObject treeObject : providers) {
				if (isPreferredProviderForClient(preferredProvidersList, clientPath, treeObject.getDotPath())) {
					preferredProviders.add(treeObject);
				}
			}
		}
		return preferredProviders;
	}
	
	public static org.openmodelica.modelicaml.common.instantiation.TreeObject getPreferredProviderForClient(Element mediator, 
			HashSet<org.openmodelica.modelicaml.common.instantiation.TreeObject> providers, 
			String clientPath ){
		if (mediator != null && providers != null) {
			EList<String> preferredProviders = TreeUtls.getStringListPropertyFromElement(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_preferredProviders);
			for ( org.openmodelica.modelicaml.common.instantiation.TreeObject treeObject : providers) {
				if (isPreferredProviderForClient(preferredProviders, clientPath, treeObject.getDotPath())) {
					return treeObject;
				}
			}
		}
		return null;
	}
	
	// #############################	TreeViewer management
	
	public static void removeTreeItem(EObject object, TreeParent treeRoot, TreeViewer viewer) {
		TreeObject[] items = treeRoot.getChildren();
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() != null && ((TreeObject)items[i]).getUmlElement() == object) {
					TreeParent parent = ((TreeObject)items[i]).getParent();
					if (parent != null) {
						parent.removeChild((TreeObject) items[i]);
					}
					viewer.remove(items[i]);
//					viewer.refresh();
			}
			else {
				if (items[i] instanceof TreeParent) {
					removeTreeItem((TreeParent)items[i], object, viewer);
				}
			}
		}
	}

	
	public static void removeTreeItem(TreeParent parent, EObject object, TreeViewer viewer) {
		TreeObject[] items = parent.getChildren();
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() != null && ((TreeObject)items[i]).getUmlElement() == object) {
				parent.removeChild((TreeObject) items[i]);
				viewer.remove(items[i]);
//				viewer.refresh();
			}
			else {
				if (items[i] instanceof TreeParent) {
					removeTreeItem((TreeParent)items[i], object, viewer);
				}
			}
		}
	}
	
	
	public static HashSet<Object> findTreeItems(EObject selectedElement, TreeParent parent, HashSet<Object> list) {
		TreeObject[] items = parent.getChildren();
		
		for (int i = 0; i < items.length; i++) {
			if ( ((TreeObject)items[i]).getUmlElement() == selectedElement) {
				list.add(items[i]);
			}
			else {
				if (items[i] instanceof TreeParent) {
					list.addAll(findTreeItems(selectedElement, (TreeParent)items[i], list));
				}
			}
		}
		return list;
	}
	
	public static HashSet<Object> findTreeItems(TreeObject selectedItem, TreeParent parent, HashSet<Object> list) {
		TreeObject[] items = parent.getChildren();
		
		for (int i = 0; i < items.length; i++) {
			if ( (TreeObject)items[i] == selectedItem) {
				list.add(items[i]);
			}
			else {
				if (items[i] instanceof TreeParent) {
					list.addAll(findTreeItems(selectedItem, (TreeParent)items[i], list));
				}
			}
		}
		return list;
	}
	

	public static void selectInView(Object obj, TreeParent treeRoot, TreeViewer viewer){
		if ( obj instanceof TreeObject ) {
			TreeObject item = (TreeObject)obj;
			if (item.isValueClient() || item.isValueMediator() || item.isValueProvider()) {
//				HashSet<Object> objects = findTreeItems( (TreeObject)obj , invisibleRoot, new HashSet<Object>());
				EObject eObject = item.getUmlElement();
				if (eObject != null) {
					HashSet<Object> objects = findTreeItems( eObject, treeRoot, new HashSet<Object>());
					List<Object> items = new ArrayList<Object>();
					items.addAll(objects);
					viewer.setSelection(new StructuredSelection(items), true);
				}
				else {
					viewer.expandToLevel(ValueBindingsView.DEFAULT_EXPAND_LEVEL);
				}
			}
			else {
				viewer.expandToLevel(ValueBindingsView.DEFAULT_EXPAND_LEVEL);
			}
		}
	}

	
	
	
	
	
	
	
	//#################### OBSOLETE
	
//	public static EList<Element> getAllValueProviders(List<Element> mediators){
//	EList<Element> providers = new BasicEList<Element>();
//	for (Element mediator : mediators) {
//		if (mediator instanceof NamedElement) {
//			EList<Dependency> dependencies = ((NamedElement)mediator).getClientDependencies();
//			for (Dependency dependency : dependencies) {
//				if (dependency.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
//					EList<Element> targets = dependency.getTargets();
//					providers.addAll(targets);
//				}
//			}
//		}
//	}
//	return providers;
//}

//public static EList<Element> getValueProvidersFromTree(List<Element> mediators, org.openmodelica.modelicaml.common.instantiation.TreeObject treeRoot){
//	EList<Element> providers = new BasicEList<Element>();
//
//	return providers;
//}


//public static EList<Element> getMeditorsFromUmlModel(EObject umlRootElement){
//	EList<Element> mediators = new BasicEList<Element>();
//	if (umlRootElement != null) {
//		Iterator<EObject> i = umlRootElement.eAllContents();
//		while (i.hasNext()) {
//			EObject object = i.next() ;
//			if (object instanceof Property && ((Element)object).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
//				mediators.add((Property) object);
//			}
//		}
//	}
//	else {
//		System.err.println("Cannot access the root ModelicaML model to search for Value Mediators.");
//	}
//	return mediators;
//}
	
}
