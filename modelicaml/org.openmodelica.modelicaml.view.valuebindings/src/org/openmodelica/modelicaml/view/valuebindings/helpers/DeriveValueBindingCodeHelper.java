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
package org.openmodelica.modelicaml.view.valuebindings.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.valuebindings.helpers.ValueBindingsDataCollector;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.SelectValueMediatorDialog;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.SelectValueProviderDialog;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;

public class DeriveValueBindingCodeHelper {
	
	private TreeObject clientTreeItem;
	private Element clientElement = null; // is the determined element that owns the script for the selected tree item client. 
	private String clientOperation = null; // final operation script.
	
	private boolean isRequiredClient = false; // indicates if this client is required

	
	private HashSet<Element> mediatorsForSelection = new HashSet<Element>();  // intermediate, if there are multiple mediators and the user shall select one of them
	private Element mediatorElement = null; // finally selected mediator
	private String mediatorOperation = null; // operation of the selected mediator 
	
	private HashSet<TreeObject> providersForSelection = new HashSet<TreeObject>();  // intermediate, if there are multiple providers and the user shall select one of them
	private HashSet<TreeObject> providers = new HashSet<TreeObject>(); // final providers that are derived.

	private HashSet<Element> mediatorsContainingClientOperationScript = new HashSet<Element>();

	private ValueBindingsDataCollector dataCollection;

	private String code = null; // derived code
	private String logString = ""; // logs all important information during the code derivation
	private String errorString = null; // result of validation
	private Boolean errorsDetected = false; // indicates if an error was detected.
	
	private Boolean userSelectionRequired = false; // indicates if an error was detected.

	public void initialize(Package valueBindingsPackage, TreeObject instantiationTreeRoot, HashSet<Element> preCollectedMediators, boolean showProgressMonitor){
		
		if (instantiationTreeRoot instanceof TreeParent) {
			if (valueBindingsPackage == null) {
				valueBindingsPackage = (Package) instantiationTreeRoot.getSelectedClass().getModel();
			}
			if (showProgressMonitor) {
				try {
					dataCollection = new ValueBindingsDataCollector();
					
					/*
					 * Use mediators that were already collected. If no mediators are provided -> find some in the specified bindings package
					 * If mediators are provided then the collectAll() method will use them and skip an additional search
					 */
					if (preCollectedMediators != null && preCollectedMediators.size() > 0) {
						dataCollection.setAllMediators(preCollectedMediators);
					}
					
					dataCollection.collectAll((Element) valueBindingsPackage, instantiationTreeRoot);
					
					new ProgressMonitorDialog(getShell()).run(true, true, dataCollection);
					
		        } catch (InvocationTargetException e) {
		        	MessageDialog.openError(getShell(), "Error", e.getMessage());
		        } catch (InterruptedException e) {
		        	MessageDialog.openInformation(getShell(), "Cancelled", e.getMessage());
		        }
			}
			else {
				dataCollection = new ValueBindingsDataCollector();
				
				/*
				 * Use mediators that were already collected. If no mediators are provided -> find some in the specified bindings package
				 * If mediators are provided then the collectAll() method will use them and skip an additional search
				 */
				if (preCollectedMediators != null && preCollectedMediators.size() > 0) {
					dataCollection.setAllMediators(preCollectedMediators);
				}
				
				dataCollection.collectAll((Element) valueBindingsPackage, instantiationTreeRoot);
			}
		}
		else {
			String message = "NOT VALID: No Value Bindings data could be collected from '" + instantiationTreeRoot + "'.";
			setError(message);
			addToLog(message);
		}
		
		//reset the required user interaction indicator
		setUserSelectionRequired(false);

	}
	
	
	public void deriveBindingCodeForClient(TreeObject selectedTreeItem, boolean isUserGuided, boolean isAutomaticSelectionOfPreferredProvidersEnabled){
		
		// clear all in order to delete data from the previous call
		clientElement = null;
		clientOperation = null;
		
		mediatorElement = null;
		mediatorOperation = null;
		
		mediatorsContainingClientOperationScript.clear();
		
		providersForSelection.clear();
		providers.clear();
		
		logString = "";
		errorString = null;
		
		code = null;
		
		//reset the required user interaction indicator
		setUserSelectionRequired(false);
		
		if (dataCollection != null && !dataCollection.wasCancelled()) {
			
			// find client 
			findClient(selectedTreeItem, isUserGuided);
			if (clientElement instanceof NamedElement) { // if client is set
				
				// find mediator
				findMediator(selectedTreeItem, clientElement, isUserGuided);
				if (mediatorElement instanceof NamedElement) { // if the mediator was found or selected
				
					// find providers
					findProviders(mediatorElement, isUserGuided, isAutomaticSelectionOfPreferredProvidersEnabled);
					if ( (mediatorOperation != null && !DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(mediatorOperation)) 
							|| (providers != null && providers.size() > 0)) {

						// derive code
						String derivedCode = deriveCode(clientTreeItem, clientOperation, mediatorElement, mediatorOperation, providers);
						if (derivedCode != null && !derivedCode.trim().equals("")) {
							this.code = derivedCode;
						}
						else { // set code to null in order to indicate that there was something wrong ...
							this.code = null;
						}
					}
					else {
						errorNoProvidersFound(mediatorElement, isUserGuided);
					}
				}
				else {
					errorNoMediatorsFound(selectedTreeItem, isUserGuided);
				}
			}
			else {
				errorNoMediatorsFound(selectedTreeItem, isUserGuided);
			}
		}
	}
	
	
	private String deriveCode(TreeObject clientTreeObject, String clientScriptOperation, Element mediator, String mediatorStringOperation, HashSet<TreeObject> providers){
		List<String> providerCodeItems = getProviderCodeItems(mediator, providers);
		String translatedMediatorOperation = translateMediatorScript(mediatorStringOperation, providerCodeItems); 
		String translatedClientOperation = translateClientScript(clientTreeObject, clientScriptOperation, translatedMediatorOperation);
		return translatedClientOperation;
	}
	
	public String translateClientScript(TreeObject clientTreeObject, String clientOperation, String codeDerivedFromMediator){
		if (clientTreeObject != null && clientOperation != null) {
			clientOperation = clientOperation.replaceAll(Constants.MACRO_clientPath, clientTreeObject.getDotPath());
			if (codeDerivedFromMediator != null) {
				clientOperation = clientOperation.replaceAll(Constants.MACRO_getBinding+ "\\((\\s+)?\\)", codeDerivedFromMediator);				
			}
			return clientOperation;
		}
		else {
			return codeDerivedFromMediator;
		}
	}
	
	public String translateMediatorScript(String mediatorOperation, List<String> providerCodeItems){
		if ( mediatorOperation != null ) {

			// replace AND(:) by ( (provider_1 operation) and (provider_2 operation) and ... (provider_n operation))
			mediatorOperation = mediatorOperation.replaceAll(Constants.MACRO_AND + "\\((\\s+)?\\:(\\s+)?\\)", 
					getLogicalOperatorOperation("and", providerCodeItems));
			
			// replace XOR(:) by ( not ((provider_1 operation) and (provider_2 operation) and  ... (provider_n operation)) 
			//							and ((provider_1 operation) or (provider_2 operation) or  ... (provider_n operation) ) )
			mediatorOperation = mediatorOperation.replaceAll(Constants.MACRO_XOR + "\\((\\s+)?\\:(\\s+)?\\)", 
					getXOROperation(providerCodeItems));
			
			// replace OR(:) by ( (provider_1 operation) or (provider_2 operation) or  ... (provider_n operation))
			mediatorOperation = mediatorOperation.replaceAll(Constants.MACRO_OR + "\\((\\s+)?\\:(\\s+)?\\)", 
					getLogicalOperatorOperation("or", providerCodeItems));

			// replace toArray(:) by a {p1,p2,p3,etc.}
			mediatorOperation = mediatorOperation.replaceAll(Constants.MACRO_toArray + "\\((\\s+)?\\:(\\s+)?\\)", 
					"{" + getItemsSeparated(", ", providerCodeItems) + "}" );
			
			// replace size(:) numberOfProviders
			mediatorOperation = mediatorOperation.replaceAll(Constants.MACRO_size + "\\((\\s+)?\\:(\\s+)?\\)", 
					String.valueOf(providerCodeItems.size()) );
			
			// replace avg(:) with  ( sum(:) / numberOfProviders). Note, sum(:) will be expanded below.
			mediatorOperation = mediatorOperation.replaceAll(Constants.MACRO_avg + "\\((\\s+)?\\:(\\s+)?\\)", 
					"(sum(:) / " + String.valueOf(providerCodeItems.size()) + ")");
			
			// replace ( : ) with ( i for i in {comp1.name, comp2, etc.})
			mediatorOperation = mediatorOperation.replaceAll("\\((\\s+)?\\:(\\s+)?\\)", 
					"( i for i in {" + getItemsSeparated(", ", providerCodeItems) + "})");

			// *Special case for one provider.
			// It is used by value mediator in order to enable an additional manipulation of code derived from a single provider.
			// This is only valid if codeItems has only one item, however, it is still returned as a comma-separated list in order to enable the detection of potential errors.
			mediatorOperation = mediatorOperation.replaceAll(Constants.MACRO_getSingleProvider+ "\\((\\s+)?\\)", getItemsSeparated(", ", new ArrayList<String>(providerCodeItems)) ); 
		}
		
		// if there is no mediator operation and only one provider then return the code derived from this provider.
		if (mediatorOperation == null && providerCodeItems.size() == 1) {
			return new ArrayList<String>(providerCodeItems).get(0);
		}
		else if (mediatorOperation == null && providerCodeItems.size() > 1 ) {
			// TODO: in case there are multiple providers and no code for mediator operation: 
			// Should we return "{" + getItemsSeparated(", ", providerCodeItems) + "}" 
			// or leave the "getBinding()" 
			// or ...
			String message = "NOT VALID: The there is no mediator operation defined to handle multiple the providers " + providerCodeItems;
			addToLog(message);
			setError(message);
		}
		
		return mediatorOperation;
	}
	
	
	private String getXOROperation(List<String> items){
		return "( not " + getLogicalOperatorOperation("and", items) + " and " + getLogicalOperatorOperation("or", items) + " )";
	}
	
	private String getLogicalOperatorOperation(String separator, List<String> items){
		String code = "";
		separator = " " + separator.trim() + " ";
		
		for (int i = 0; i < items.size(); i++) {
			if (i == 0) { // first item
				code = code + "(" + items.get(i) + ")";
			}
			else if (i == items.size() - 1 ) { // last item
				code = code + separator + "(" + items.get(i) + ")";
			}
			else {
				code = code + separator + "(" + items.get(i) + ")";
			}
		}
		return "(" + code + ")";
	}
	
	
	private String getItemsSeparated(String separator, List<String> items){
		String code = "";
		for (int i = 0; i < items.size(); i++) {
			if (i == 0) { // first item
				code = code + items.get(i);
			}
			else if (i == items.size() - 1 ) { // last item
				code = code + separator + items.get(i);
			}
			else {
				code = code + separator+ items.get(i);
			}
		}
		return code;
	}
	

	private List<String> getProviderCodeItems(Element mediator, HashSet<TreeObject> providers){
		List<String> codeItems = new ArrayList<String>();
		// TODO: test if this is a performance issue?  It is used in order to ensure that the code will be derived deterministically. 
		// If it leads to performance penalties then use HashSet instead even if it may lead to different order to items in the derived code.
		List<TreeObject> providersSorted = getSortedList(providers);  
		for (TreeObject providerTreeObject : providersSorted) {
			Element providerElement = providerTreeObject.getUmlElement();
			if (providerElement instanceof NamedElement && mediator instanceof NamedElement) {
				EList<Dependency> depList = TreeUtls.getMediatorDependency((NamedElement)mediator, (NamedElement)providerElement, Constants.stereotypeQName_ObtainsValueFrom);
				if (depList.size() == 1) {
					String providerOperation = DeriveValueBindingCodeUtls.getOperationSpecification(depList.get(0), Constants.stereotypeQName_ObtainsValueFrom, Constants.propertyName_operation);
					codeItems.add(deriveCodeFromProvider(providerTreeObject, providerOperation));
				}
				else {
					String message = "NOT VALID: There are multiple references between the mediator '" + ((NamedElement)mediator).getQualifiedName() + "' and the provider '"+((NamedElement)providerElement).getQualifiedName()+"'";
					setError(message);
					addToLog(message);
				}
			}
			else {
				String message = "NOT VALID: Provider '" + providerElement + "' cannot be used for binding code derivation";
				setError(message);
				addToLog(message);
			}
			
		}
		return codeItems;
	}

	
	public String deriveCodeFromProvider(TreeObject provider, String providerOperation){
		if (providerOperation != null ) {
			providerOperation = providerOperation.replaceAll(Constants.MACRO_providerPath, provider.getDotPath());
			return providerOperation;
		}
		return provider.getDotPath();
	}
	
	private List<TreeObject> getSortedList(HashSet<TreeObject> set){
		List<TreeObject> providersSorted = new ArrayList<TreeObject>(set);
		Comparator<TreeObject> c = new Comparator<TreeObject>() {
			@Override
			public int compare(TreeObject arg0, TreeObject arg1) {
				return arg0.getDotPath().compareToIgnoreCase(arg1.getDotPath());
			}
		};
		Collections.sort(providersSorted, c);
		return providersSorted;
	}
	
	public void findProviders(Element mediator, boolean isUserGuided, boolean isAutomaticSelectionOfPreferredProvidersEnabled){
		// clear the list
		providersForSelection.clear();
		providers.clear(); 
		
		HashSet<Element> providersForMediator = dataCollection.getMediatorToProviders().get(mediator);
//		List<TreeObject> providerInstances = new ArrayList<TreeObject>();
		HashSet<TreeObject> providerInstances = new HashSet<TreeObject>();
		
		// if there are providers for this mediator in the instantiated class
		if ( providersForMediator != null && providersForMediator.size() > 0 ) { 

			// get provider instances
			for (Element providerForMediator : providersForMediator) {
				HashSet<TreeObject> providerInstancesForPovider = dataCollection.getInstantiationTreeObjects(providerForMediator);
				if (providerInstancesForPovider != null && providerInstancesForPovider.size() > 0 ) {
					for (TreeObject provider : providerInstancesForPovider) {
						// a component may be client and provider at the same time.
						// avoid cyclic references.
						if (provider != clientTreeItem) {
							providerInstances.add(provider);
						}
					}
//					providerInstances.addAll(providerInstancesForPovider);
				}
			}
			
			if ( providerInstances.size() == 1 ) {
				TreeObject singleProviderInstance = new ArrayList<TreeObject>(providerInstances).get(0);
				providers.add(singleProviderInstance);
			}
			else if ( providerInstances.size() > 1 ) {
				
				// if the operation handles multiple providers then add all providers to the final list
				if (DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorOperation)) {
					providers.addAll(providerInstances);
				}
				else { // Provider selection: User selection or automatic selection
					
					// indicate that a manual selection should be done by the user
					setUserSelectionRequired(true);
					
					// Catch the fact that there were multiple providers that were used for user selection
					providersForSelection.addAll(providerInstances);
					
					//***** USER Selection
					if (isUserGuided) {
						String title = "Value Provider Selection";
						String messageText = "There are multiple providers for the mediator and no operation is defined to handle multiple items. " +
								"\nYou can select one provider that should be used to derive the code for the selected Value Mediator. " +
								"\r\nNote, you can hover the elements to get more information. \r\n";

						String groupTitle = "Value Providers";
						
						SelectValueProviderDialog dialog = new SelectValueProviderDialog(
								getShell(), 
								title, 
								clientTreeItem,
								mediatorElement,
//								toolTipTextSourceTreeItem, 
								groupTitle, 
								messageText, 
								getSortedList(providerInstances),
								dataCollection); 
						
						dialog.open();
						int result = dialog.getReturnCode();
						
						if (result == IDialogConstants.OK_ID) {
							if (dialog.getSelectedElement() != null) {
								providers.add(dialog.getSelectedElement());
								// log selection
								addToLog("SELECTED: Value Provider '"+ dialog.getSelectedElement().getDotPath()+"'.");
								
								if (dialog.isAddToPrefferedProviders()) {
									// add to preferredProviders 
									storePreferredProvider(mediatorElement, clientTreeItem, dialog.getSelectedElement());
								}
							}
						}
					}
					
					// *** AUTOMATIC Selection based on preferred providers
					// if there is a preferred provider -> select it automatically
					else if (isAutomaticSelectionOfPreferredProvidersEnabled) {
//						if (clientTreeItem != null && TreeUtls.getPreferredProviderForClient(mediator, providerInstances, clientTreeItem.getDotPath()) != null) {
//							providers.add(TreeUtls.getPreferredProviderForClient(mediator, providerInstances, clientTreeItem.getDotPath()));
//						}
						HashSet<TreeObject> preferredPoviders = TreeUtls.getPreferredProvidersForClient(mediator, providerInstances, clientTreeItem.getDotPath());
						if (clientTreeItem != null && preferredPoviders != null && preferredPoviders.size() == 1) {
							providers.add(TreeUtls.getPreferredProviderForClient(mediator, providerInstances, clientTreeItem.getDotPath()));
						}
						else if (preferredPoviders.size() > 1 ) {
							String mediatorQName = ((NamedElement)mediator).getQualifiedName();
							EList<TreeObject> pProviders = new BasicEList<TreeObject>();
							pProviders.addAll(preferredPoviders);
							String message = "ERROR: Multiple preferred providers ("+getItemsAsStringList(pProviders)+") were found for '"+mediatorQName+"'";
							setError(message);
							addToLog(message);
						}
					}
				}
			}
			// no providers were found
			else {
				String mediatorQName = ((NamedElement)mediator).getQualifiedName();
				String message = "EMPTY: No providers were found for the mediator '"+mediatorQName+"'";
				setError(message);
				addToLog(message);
			}
		}
	}
	
	
	private void storePreferredProvider(Element mediator, TreeObject client, TreeObject provider) {
		if (mediator != null && client != null && provider != null) {
//			boolean result = TreeUtls.addToPreferredProvider(mediator, client.getDotPath(), provider.getDotPath());
			TreeUtls.addToPreferredProvider(mediator, client.getDotPath(), provider.getDotPath());
		}
		else {
			MessageDialog.openError(getShell(), "Error", "Could not add the provider to preferred providers.");
		}
	}
	
//	public void findMediator(Element selectedClientElement, TreeObject selectedTreeItem, Element client, boolean isUserGuided){
	public void findMediator(TreeObject selectedTreeItem, Element client, boolean isUserGuided){
		
		//reset and clear mediators data
		mediatorsForSelection.clear();
		mediatorElement = null;
		mediatorOperation = null;
		
		// Get provider elements for mediator 
		HashSet<Element> mediatorsForClient = null;
		
//		// selectedClientElement is used to call this method without a previous search of the client.
//		if (selectedClientElement != null ) { 
//			mediatorsForClient = dataCollection.getClientToMediators().get(selectedClientElement);
//		}

//		if (clientElement == selectedTreeItem.getUmlElement()) {
//			mediatorsForClient = dataCollection.getClientToMediators().get(clientElement);
//		}
//		else {
//			mediatorsForClient = mediatorsContainingClientOperationScript;
//		}
		
		// if a search for the client was done before and there is an upper level client was found that has references to the mediators
		// -> use this list of mediators, otherwise get the mediators from the client UML element.
		if (mediatorsContainingClientOperationScript.size() == 0) { 
			mediatorsForClient = dataCollection.getClientToMediators().get(client);
		}
		else { 
			mediatorsForClient = mediatorsContainingClientOperationScript;
		}
		
		// Filter mediators based on their operation specification and provider instances that are present in the instantiated class.
		HashSet<Element> mediators = new HashSet<Element>(); // filtered mediators, no duplicates
		
		if (mediatorsForClient != null) {
			for (Element mediator : mediatorsForClient) {
				if (mediator instanceof Property) { // TODO: what if it is a state?
					
					// NOTE: 	Reducing the mediator selection by checking the type compatibility is 
					// 			NOT useful because type checking needs expression evaluation which is hard to do at ModelicaML level.

					// TODO: Implement check for variability compatibility between client and mediator?

					// Get providers for the mediator
					HashSet<Element> providersForMediator = dataCollection.getMediatorToProviders().get(mediator);
					
					// get provider instances
					List<TreeObject> providerInstances = new ArrayList<TreeObject>();
					if (providersForMediator != null) {
						for (Element providerForMediator : providersForMediator) {
							HashSet<TreeObject> providerInstancesForPovider = dataCollection.getInstantiationTreeObjects(providerForMediator);
							if (providerInstancesForPovider != null && providerInstancesForPovider.size() > 0 ) {
								providerInstances.addAll(providerInstancesForPovider);
								
								// a component may be client and provider 
								// avoid cyclic references to it self.
								if (providerInstances.contains(clientTreeItem)) {
									providerInstances.remove(clientTreeItem);
								}
								
							}
						}
					}
					
					String mediatorScript = DeriveValueBindingCodeUtls.getOperationSpecification(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
					
					// If there is mediator operation that has no provider functions and has no providers
					// -> select the mediator because it has only constants, parameter values or uses other ModelicaML/Modelica functions. 
					if (mediatorScript != null && !DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(mediatorScript) 
							&& !(providerInstances.size() > 0)) {
						mediators.add(mediator);
					}
					
					// If there are providers and no binding operation function to handle them 
					// -> report and error.
					if (providerInstances.size() > 0 && mediatorScript!= null && !DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(mediatorScript)) {
						String mediatorQName = ((NamedElement)mediator).getQualifiedName();
						String message = "WARNING: The mediator '"+mediatorQName+"' has "+providerInstances.size()+" providers " +
								"and an operation defined but no binding operation functions to handle providers. " 
//								+ "This may be on purpose in order to let the user select the provider."
								; 
						setError(message);
						addToLog(message);
					}
					
					// if there are more than 1 provider instances but no operation function to handle them 
					// -> report error
					if (providerInstances.size() > 1 && !DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorScript)) {
						String mediatorQName = ((NamedElement)mediator).getQualifiedName();
						String message = "NOT VALID: The mediator '"+mediatorQName+"' has "+providerInstances.size()+" providers " +
								"but no binding operation function to handle them."; 
						setError(message);
						addToLog(message);
					}
					
					// if there is one provider 
					// -> select it if there is no mediator operation or, if there is one, then if it has binding script functions.
					if ( providerInstances.size() == 1 && (mediatorScript == null || DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(mediatorScript))) {
						mediators.add(mediator);
					}
					
					// NOTE: the code below is not useful because it may be on purpose in order to let the user select.
//					// if there are more then 1 provider instances 
//					// -> select the mediator if it has binding operation functions to handle multiple providers.
//					if ( providerInstances.size() > 1 && DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorScript)) {
//						mediators.add(mediator);
//					}
					
					// if there are more then 1 provider instances 
					// -> select in order to enable the selection by user.
					if ( providerInstances.size() > 1) {
						mediators.add(mediator);
					}
					
					// if there are no provider instances and the mediator script does not contain constant values. 
					// -> discard
					if (providerInstances.size() == 0 && (mediatorScript == null || DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(mediatorScript))) {
						String mediatorQName = ((NamedElement)mediator).getQualifiedName();
						String message = "DISCARDED: The mediator '"+mediatorQName+"' " +
								"has no providers that are present in the instantiated class."; 
						addToLog(message);
					}
				}
			}
		}
		
		
		if (mediators.size() > 1 ) { // too many -> let the user select

			// indicate that a manual selection should be done by the user
			setUserSelectionRequired(true);

			// Store the information when there are multiple mediators. This not used in this class but by other ...
			mediatorsForSelection.addAll(mediators);
			
			String clientName = ((NamedElement)client).getQualifiedName();
			String message = "NOT VALID: Multiple mediators ("+ getItemsAsStringList(new BasicEList<Element>(mediators))+") are found for the client '"+clientName+"'." +
					"\nYou have to select one. ";
			setError(message);
			addToLog(message);
			
			//*** USER Selection
			if (isUserGuided) {
				
				String title = "Value Mediator Selection";
				String textSourceTreeItem = "";
				String toolTipTextSourceTreeItem = "";
				if (selectedTreeItem != null) {
					textSourceTreeItem = "Value Client: " + selectedTreeItem.getName();
					toolTipTextSourceTreeItem = "Client Path: " + selectedTreeItem.getDotPath() 
					+ "\r\nElement: " + ((NamedElement)clientElement).getQualifiedName();
				}
				
				String messageText = "Select a Value Mediator that should to derive the code for the selected Value Client. " +
										"\r\nNote, you can hover the elements to get more information. \r\n";
				String groupTitle = "Value Mediators";
				
				SelectValueMediatorDialog dialog = new SelectValueMediatorDialog(getShell(), title, textSourceTreeItem, toolTipTextSourceTreeItem, groupTitle, messageText, new ArrayList<Element>(mediators), dataCollection); 
				dialog.open();
				int result = dialog.getReturnCode();

				// get the dialog selection result
				if (result == IDialogConstants.OK_ID) {
					mediatorElement = dialog.getSelectedElement();
				}
				// set client and mediator operations
				if (mediatorElement != null) {
					mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(mediatorElement, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
					//log selection
//					addToLog("SELECTED: Value Mediator '"+ ((NamedElement)mediatorElement).getQualifiedName()+"'.");
					if (mediatorOperation != null) {
						addToLog("SELECTED: Value Mediator '"+ ((NamedElement)mediatorElement).getQualifiedName()+"' " +
								"\n with its mediator operation: " +
								"\n   = " + mediatorOperation + "\n");
					}
					else {
						addToLog("SELECTED: Value Mediator '"+ ((NamedElement)mediatorElement).getQualifiedName()+"' " +
								"\n which had no mediator operation.");
					}
				}
			}
			// TODO: is a preferred mediators feature sensible, similar to preferred providers? 
			// The qualified name of the mediator UML property could be used for storing the user selections for clients.  
		}
		else if (mediators.size() == 1) { // ok, set the mediator element and its operation
			Object[] m = mediators.toArray();
			mediatorElement = (Element) m[0];
			mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(mediatorElement, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
			if (mediatorOperation != null) {
				addToLog("SELECTED: Value Mediator '"+ ((NamedElement)mediatorElement).getQualifiedName()+"' " +
						"\n with its mediator operation: " +
						"\n   = " + mediatorOperation + "\n");
			}
			else {
				addToLog("SELECTED: Value Mediator '"+ ((NamedElement)mediatorElement).getQualifiedName()+"' " +
						"\n which had no mediator operation.");
			}
		}
		else { //No mediators found -> report an error
			String clientName = ((NamedElement)client).getQualifiedName();
			String message = "EMPTY: No mediators are found for the client '"+clientName+"'."; 
			if (mediatorsForClient!= null && mediatorsForClient.size() > 0) {
				message = message + "\nThere are "+mediatorsForClient.size()+" mediators:"
				+ getItemsAsStringList(new BasicEList<Element>(mediatorsForClient))
				+ "\nHowever, none of them has providers that are present in the instantiated class or an operation which does not require providers.";
			}
			setError(message);
			addToLog(message);
		}
	}
	
	
	private Shell getShell(){
		Shell shell = null;
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			if (win != null) {
				shell = win.getShell();
			}
		}
		if (shell == null) {
			shell = new Shell();
		}
		return shell;
	}
	
	public void setClient(Element mediator, Element client, boolean isUserGuided){
		if (client != null) {
			this.clientElement = client;
			
			// if the passed mediator is not null then set the cleint operation. 
			if (mediator != null ) {
				EList<Dependency> dependencies = TreeUtls.getMediatorDependency((NamedElement)mediator, (NamedElement)client, Constants.stereotypeQName_ProvidesValueFor);
				if (dependencies.size() > 1 ) {
					// stop here, this error leads to the cancellation of code derivation.
					String message = "NOT VALID: The client '"+((NamedElement)client).getQualifiedName()+"' is referenced multiple times by the mediator '"+((NamedElement)mediator).getQualifiedName()+"'. Only one reference is allowed.";
					setError(message);
					addToLog(message);
					
					if (isUserGuided) {  
						MessageDialog.openError(getShell(), "Value Client Reference Inconsistency ", message);
					}
					return; // stop here
				}
				else {
					String script = DeriveValueBindingCodeUtls.getOperationSpecification(dependencies.get(0), Constants.stereotypeQName_ProvidesValueFor, Constants.propertyName_operation);
					clientOperation = getRightHandScriptPart(script);
				}
			}
		}
		
	}

	
	private void findClient(TreeObject selectedTreeItem, Boolean isUserGuided){
		// reset
		clientElement = null;
		clientOperation = null;
		
		List<TreeObject> topDownPathSegments = getTopDownPathItems(selectedTreeItem);
		
		// top town path traverse. Determine the first tree element that either has a script for the selectedItem or is the selectedItem itself.
		for (TreeObject potentialClient : topDownPathSegments) {
			Element potentialClientElement = potentialClient.getUmlElement();
			if (potentialClientElement instanceof NamedElement) {
				// check if this is a referenced element, i.e. is referenced by mediators that are used in the instantiated class
				if (dataCollection.getReferencedClients().contains(potentialClientElement)) {
					
					HashSet<Element> mediators = dataCollection.getClientToMediators().get(potentialClientElement);
					// if there are mediators
					if (mediators != null && mediators.size() > 0) {
						for (Element mediator : mediators) {
							if (mediator instanceof NamedElement) {
								EList<Dependency> dependencies = TreeUtls.getMediatorDependency((NamedElement)mediator, (NamedElement)potentialClientElement, Constants.stereotypeQName_ProvidesValueFor);
								if (dependencies.size() > 1 ) {
									// stop here, this error leads to the cancellation of code derivation.
									String message = "NOT VALID: The client '"+((NamedElement)potentialClientElement).getQualifiedName()+"' is referenced multiple times by the mediator '"+((NamedElement)mediator).getQualifiedName()+"'. Only one reference is allowed.";
									setError(message);
									addToLog(message);
									
									if (isUserGuided) {  
										MessageDialog.openError(getShell(), "Value Client Reference Inconsistency ", message);
									}
									
									return; // stop here
								}
								else { // always take only one (i.e. the first) dependency

									// if it is the selected tree item and has NO script -> take it and its operation
									// if it is the selected tree item that has a script -> take it only if the script addresses him self.
									String potentialClientScript = DeriveValueBindingCodeUtls.getOperationSpecification(dependencies.get(0), Constants.stereotypeQName_ProvidesValueFor, Constants.propertyName_operation);
									if (potentialClient == selectedTreeItem 
											&& (potentialClientScript== null 
													|| getScriptForClient(potentialClient, selectedTreeItem, potentialClientScript) != null) ) { 
										
										// higher level definitions have higher priority. Don't overwrite if already found.
										if (clientElement == null && clientOperation == null) {
											clientTreeItem = potentialClient;
											clientElement = potentialClientElement;
											setIsRequiredClient(dependencies.get(0));
											
											String script = DeriveValueBindingCodeUtls.getOperationSpecification(dependencies.get(0), Constants.stereotypeQName_ProvidesValueFor, Constants.propertyName_operation);
											clientOperation = getScriptForClient(potentialClient, selectedTreeItem, script);
											
											return; // stop here because this is the last segment in the path. 
										}
									}
									else { // for other segments in selectedTreeItem path
										String script = DeriveValueBindingCodeUtls.getOperationSpecification(dependencies.get(0), Constants.stereotypeQName_ProvidesValueFor, Constants.propertyName_operation);
										if (script != null) { // no need to analyze the script, go to next path segment.
											String potentialClientOperation = getScriptForClient(potentialClient, selectedTreeItem, script);
											
											if (potentialClientOperation != null) {
												
												// higher level definitions have higher priority. Don't overwrite if already found.
												if (clientElement == null && clientOperation == null) {
													clientTreeItem = potentialClient;
													clientElement = potentialClientElement;
													setIsRequiredClient(dependencies.get(0));
													
													clientOperation = potentialClientOperation;
													mediatorsContainingClientOperationScript.add(mediator);
													// don't stop here, continue the search to collect all mediators that have dependencies to upper level clients with operation specifying the selected client. 
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	


	private void setIsRequiredClient(Dependency mediatorDependency){
		Stereotype sProvidesValueFor = mediatorDependency.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor);
		if (sProvidesValueFor != null) {
			Object isRequired = mediatorDependency.getValue(sProvidesValueFor, Constants.propertyName_isRequired);
			if (isRequired != null && isRequired instanceof Boolean && (Boolean)isRequired) {
				setRequiredClient(true);
			}
			else {
				setRequiredClient(false);
			}
		}
	}
	
	private String getScriptForClient(TreeObject upperClientTeeItem, TreeObject selectedClientTreeItem, String script){
		String finalScript = null;
		if (script != null) { 
			String[] scriptCodeSplitted = script.split(";");
			if ( scriptCodeSplitted.length > 0) { // if there is at least one line that ends with ";"
				for (int i = 0; i < scriptCodeSplitted.length; i++) { // the next will always overwrite the previous, i.e. the last one is always taken. 
					
					if ( !scriptCodeSplitted[i].trim().equals("") ) { // if it is not an empty line 
						String[] bindingEqationParts = scriptCodeSplitted[i].split("=");
						if (bindingEqationParts.length == 2 ) {// it is a binding equation with left and right part 
							
							// get the left and right parts and trim them
							String leftHand = bindingEqationParts[0].trim();
							String rightHand = bindingEqationParts[1].trim();
							
							// it the left part of the binding equation refers to this value client 
							//(i.e. its "ownPath") then return the right part.
							
							String expandedLeftHand = leftHand.replaceFirst(Constants.MACRO_clientPath, upperClientTeeItem.getDotPath()).trim();
//							System.err.println("expandedLeftHand: " + expandedLeftHand);
//							System.err.println("selectedClientTreeItem:" + selectedClientTreeItem.getDotPath());
							
							if (expandedLeftHand.equals(selectedClientTreeItem.getDotPath())) {
//								System.err.println(expandedLeftHand + " = " + selectedClientTreeItem.getDotPath() );
								if ( DeriveValueBindingCodeUtls.isValidClientScript(rightHand) ) {
									finalScript = rightHand;
								}
								else {
									String message = "NOT VALID: Client script does not contain mediator handling operation: " + rightHand;
									setError(message);
									addToLog(message);
								}
							}
						}
						else {
							// TODO: generate marker?
							String message = "NOT VALID: Client script:  \n   '" + scriptCodeSplitted[i] + "'";
							setError(message);
							addToLog(message);
						}
					}
				}
			}
		}
		return finalScript;
	}
	
	
	private String getRightHandScriptPart(String script){
		String finalScript = null;
		if (script != null) { 
			String[] scriptCodeSplitted = script.split(";");
			if ( scriptCodeSplitted.length > 0) { // if there is at least one line that ends with ";"
				for (int i = 0; i < scriptCodeSplitted.length; i++) { // the next will always overwrite the previous, i.e. the last one is always taken. 
					
					if ( !scriptCodeSplitted[i].trim().equals("") ) { // if it is not an empty line 
						String[] bindingEqationParts = scriptCodeSplitted[i].split("=");
						if (bindingEqationParts.length == 2 ) {// it is a binding equation with left and right part 
							
							// get the left and right parts and trim them
//							String leftHand = bindingEqationParts[0].trim();
							String rightHand = bindingEqationParts[1].trim();
							finalScript = rightHand;
						}
						else {
							String message = "NOT VALID: Client script:  \n   '" + scriptCodeSplitted[i] + "'";
							setError(message);
							addToLog(message);
						}
					}
				}
			}
		}
		return finalScript;
	}
	
	
	private List<TreeObject> getTopDownPathItems(TreeObject startItem){
		List<TreeObject> listOfSegments = new ArrayList<TreeObject>();
		listOfSegments.add(startItem);
		listOfSegments.addAll(getPathFromItem(startItem, new ArrayList<TreeObject>()) );
		
		// reverse the path to get the top-down path
		Collections.reverse(listOfSegments);

		return listOfSegments;
	}

	private ArrayList<TreeObject> getPathFromItem(TreeObject item, ArrayList<TreeObject> listOfSegments){
		TreeObject parent = item.getParent();
		if (parent != null && !parent.isRoot()) { // the root node represents the instantiated class. It should not appear in the path.  
			listOfSegments.add(parent);
			listOfSegments.addAll( getPathFromItem(parent, new ArrayList<TreeObject>()) );
		}
		return listOfSegments;
	}
	

	
	
	private void errorNoMediatorsFound(Object client, boolean isUserGuided){
		String clientName = "???";
		if (client instanceof TreeObject) {
			clientName = ((TreeObject)client).getDotPath();
		}
		else if (client instanceof NamedElement) {
			clientName = ((NamedElement)client).getQualifiedName();
		}
		
		String message = "EMPTY: No Value Mediators were found or selected for the Value Client '" + clientName + "'."; 
		setError(message);
		addToLog(message);
		
//		if (isUserGuided) {
//			MessageDialog.openError(new Shell(), "No Value Mediators found", message);	
//		}
	}
	
	private void errorNoProvidersFound(Object mediator, boolean isUserGuided){
		String mediatorName = "???";
		if (mediator instanceof NamedElement) {
			mediatorName = ((NamedElement)mediator).getQualifiedName();
		}
		
		String message = "EMPTY: No Value Providers were found or selected for the Value Mediator '" + mediatorName + "'."; 
		setError(message);
//		addToLog(message);
		
//		if (isUserGuided) {
//			MessageDialog.openError(new Shell(), "No Value Mediators found", message);	
//		}
	}
	
	
	
	
	// ******************************************************************** GETTER / SETTER

	public ValueBindingsDataCollector getDataCollection() {
		return dataCollection;
	}
	
	public void setDataCollection(ValueBindingsDataCollector dataCollection) {
		this.dataCollection = dataCollection;
	}
	
	
	public Element getClientElement() {
		return clientElement;
	}
	
	public HashSet<Element> getMediatorsForSelection() {
		return mediatorsForSelection;
	}
	
	public Element getMediatorElement() {
		return mediatorElement;
	}
	
	public HashSet<TreeObject> getProvidersForSelection() {
		return providersForSelection;
	}
	
	public HashSet<TreeObject> getProviders() {
		return providers;
	}
	
	public boolean isRequiredClient() {
		return isRequiredClient;
	}


	public void setRequiredClient(boolean isRequiredClient) {
		this.isRequiredClient = isRequiredClient;
	}

	// ******************************************************************** GETTER / SETTER

	
//	public String getErrorString() {
//		return "********************************* ERRORS ********************************* \n" + errorString 
//		+ "\n\n ********************************* LOGS   ********************************* \n" + getLogString();
//	}
	
	
	
	public String getErrorString() {
		return errorString;
	}

	public String getCode() {
		return code;
	}
	
	public Boolean errorsDetected (){
		return this.errorsDetected;
	}
	
	private void setError(String message){
		errorsDetected = true; // set the indicator 
		if (errorString == null) { // for the very first error detection 
			errorString = message + "\n";	
		}
		else { // append error string
			errorString = errorString + "\n" + message + "\n";
		}
		errorString = errorString 
			+ "-------------------------------------------------------"
			+ "------------------------------------------------------- \n";
	}

	public void addToLog(String logString) {
		this.logString = this.logString 
		+ "-------------------------------------------------------"
		+ "------------------------------------------------------- \n" 
		+ logString + "\n";
	}

	public String getLogString() {
		// get the data collection log and append this log to it.
		return dataCollection.getLog() +"\n"+ logString;
	}
	
	public void clearLog() {
		logString = "";
	}

	public void clearErorrString() {
		errorString = "";
	}

	public void clearErrorsDetected() {
		errorsDetected = false;
	}

	
	// Used only for error and log messages
	private String getItemsAsStringList(EList<?> list){
		String string = "";
		for (Object object : list) {
			if (object instanceof TreeObject ) {
				string = string + "\n         - " + ((TreeObject)object).getDotPath();	
			}
			else if (object instanceof NamedElement) {
				string = string + "\n         - " + ((NamedElement)object).getQualifiedName();	
			}
		}
		return string;
	}


	public void setUserSelectionRequired(Boolean userSelectionRequired) {
		this.userSelectionRequired = userSelectionRequired;
	}


	public Boolean isUserSelectionRequired() {
		return userSelectionRequired;
	}
	
}
