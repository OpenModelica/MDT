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
package org.openmodelica.modelicaml.view.valuebindings.display;

import java.util.HashSet;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.Activator;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;

public class ViewLabelProviderStyledCell extends StyledCellLabelProvider {

	private final ImageDescriptor warningImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");
	private final ImageDescriptor infoImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	public EObject umlModel = null;

	@Override
	public void update(ViewerCell cell) {
		
		Object obj = cell.getElement();
		
		// styler
		Styler stylerRed = new Styler() {
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.foreground = new Color(null, new RGB(234, 0, 0)); // darker RED
			}
		};
		Styler stylerGrey = new Styler() {
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.foreground = new Color(null, new RGB(112, 138, 144)); // Slate Gray
			}
		};
		
		
		// set text default
//		cell.setText(obj.toString()); 
		cell.setText( ((TreeObject)obj).getName());
		
		// set text styled, override the default
		if ( ((TreeObject)obj).isValueClientsNode() 
				|| ((TreeObject)obj).isValueMediatorsNode() || ((TreeObject)obj).isValueProvidersNode()) {
			
			// name string 
			String name = ((TreeObject)obj).getName() + " ";
			
			// number of children string 
			String numberOfChildren = "";
			if (obj instanceof TreeParent) {
				numberOfChildren = "(" + ((TreeParent)obj).getChildren().length + ")";
			}

			//  construct the label
			StyledString styledString = new StyledString();
			styledString.append(name);
			styledString.append(numberOfChildren, stylerGrey);
			
			// get the parent
			TreeObject item = (TreeObject)obj;
			TreeObject parent = item.getParent(); 

			// value mediator operation specification to be displayed on the providers node
			String valueMediatorOperation = "";
			if ( item.isValueProvidersNode() && parent != null && parent.isValueMediator()) {
				Element element = parent.getUmlElement();
				if (element != null) {
//					valueMediatorOperation = getOperationSpecification(element, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);					
					valueMediatorOperation = getOperationSpecification(parent, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);					
					styledString.append(valueMediatorOperation, stylerGrey);
				}
			}
			
			// value client operation specification to be displayed on the mediators node
			String valueClientOperation = "";
			if (item.isValueMediatorsNode() && parent != null && parent.isValueClient()) {
				Element element = parent.getUmlElement();
				if (element != null) {
					valueClientOperation = getOperationSpecification(element, Constants.stereotypeQName_ValueClient, Constants.propertyName_operation);
					styledString.append(valueClientOperation, stylerGrey);
				}
			}
			
			// set text and styles
			cell.setText( styledString.toString() );
			cell.setStyleRanges(styledString.getStyleRanges());
		}
		
		// override the name only if uml element is defined and it is not an instantiation tree item.
		else if ( obj instanceof TreeObject && ((TreeObject)obj).getUmlElement() != null  ){
			
			Element umlElement = ((TreeObject)obj).getUmlElement();
			TreeObject item = (TreeObject)obj;
			
			if (  umlElement instanceof NamedElement && (((TreeObject)obj).isPackage() || ((TreeObject)obj).isValueMediatorContainer() ) 
					&& !((TreeObject)obj).isInstantiatedClass() ) {
				
				// name string
				String name = item.getName();
				if (((TreeObject)obj).getInstantiationTreeObject() != null) {
					name = ((TreeObject)obj).getInstantiationTreeObject().getDotPath()  + " ";	
				}
//				else {
//					name = ((NamedElement)umlElement).getName() + " ";
//				}
				
				// number of children string 
				String numberOfChildren = "";
				if (obj instanceof TreeParent) {
					numberOfChildren = "(" + ((TreeParent)obj).getChildren().length + ")";
				}
				
				//  construct the label
				StyledString styledString = new StyledString();
				styledString.append(name);
				styledString.append(numberOfChildren, stylerGrey);
				
				// set text and styles
				cell.setText( styledString.toString() );
				cell.setStyleRanges(styledString.getStyleRanges());
			}
			
			if (umlElement instanceof Property) {

				// name string
				String name = item.getName();
				if (((TreeObject)obj).getInstantiationTreeObject() != null) {
					name = ((TreeObject)obj).getInstantiationTreeObject().getDotPath()  + "   -   ";;	
				}
				else {
					name = ((Property)umlElement).getName() + "   -   ";	
				}

				// is client, mediator or provider string
				String whatString = "";
//				if (((TreeObject)obj).isValueClient()) { 	whatString = "(c), ";}
//				if (((TreeObject)obj).isValueMediator()) { 	whatString = "(m), ";}
//				if (((TreeObject)obj).isValueProvider()) { 	whatString = "(p), ";}
				
				if (((TreeObject)obj).isValueClient()) { 	whatString = "client, ";}
				if (((TreeObject)obj).isValueClient_required()) { 	whatString = "mand. client, ";}
				if (((TreeObject)obj).isValueMediator()) { 	whatString = "mediator, ";}
				if (((TreeObject)obj).isValueProvider()) { 	whatString = "provider, ";}

				
				// type string
				Type pType = ((Property)umlElement).getType();
//				String pTypeString = "type (NOT DEFINED)";
				String pTypeString = "type is NOT DEFINED";
				if ( pType != null ) {
//					pTypeString =  "type (" + pType.getName().replaceFirst("Modelica", "") + ")";
					pTypeString =  pType.getName().replaceFirst("Modelica", "");
				}	
				
				if (((TreeObject)obj).isValueMediator() ) {
					
					// mediator operation
					String valueMediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(((TreeObject)obj).getUmlElement(), Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
					// TODO: show operation if it has no binding functions ...
					
					// numbers of clients and providers
					int numberOfClients = getNumberOfClients(obj);
					int numberOfProviders = getNumberOfProviders(obj);
					
					// string for clients and providers
					String clientsString = "clients (" + String.valueOf(numberOfClients) + ")";
					String providersString = "providers (" + String.valueOf(numberOfProviders) + ")";

					//  construct the label
					StyledString styledString = new StyledString();
					styledString.append(name);
					styledString.append(whatString, stylerGrey);
					styledString.append(pTypeString, stylerGrey);
					
					// mediators may be appended to clients OR providers nodes in CYCLIC tree.
					// if parent.parent is a providersNode then only show the number of clinets for a mediator.
					if (item.getParent() != null && item.getParent().getParent() != null && item.getParent().getParent().isValueProvider()) {
						styledString.append(", ");
						
						if (numberOfClients == 0) {styledString.append(clientsString, stylerRed); }
						else { styledString.append(clientsString); }
					}
					// if parent.parent is a clientsNode then only show the number of providers for a mediator.
					if (item.getParent() != null && item.getParent().getParent() != null && item.getParent().getParent().isValueClient()) {
						styledString.append(", ");

						// if the mediator script has only constant values, that do not depend on providers
						// -> show the mediator operation
						if (valueMediatorOperation != null && !DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(valueMediatorOperation)) {
							styledString.append(" = " + valueMediatorOperation, stylerRed);
						}
						// else show the number of providers
						else {
							if (numberOfProviders == 0) {styledString.append(providersString, stylerRed); }
							else { styledString.append(providersString); }
						}
					}

					if ( !item.isReadOnly() ) { // if it is not a read-only item then show the number of both: clients and providers
						styledString.append(", ");
						
						if (numberOfClients == 0) {styledString.append(clientsString, stylerRed); }
						else { styledString.append(clientsString); }

						styledString.append(", ");
						
						// if the mediator script has only constant values, that do not depend on providers
						// -> show the mediator operation
						if (valueMediatorOperation != null && !DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(valueMediatorOperation)) {
							styledString.append(" = " + valueMediatorOperation, stylerRed);
						}
						// else show the number of providers
						else {
							if (numberOfProviders == 0) {styledString.append(providersString, stylerRed); }
							else { styledString.append(providersString); }
						}
					}
					
					// set text and styles
					cell.setText( styledString.toString() );
					cell.setStyleRanges(styledString.getStyleRanges());
					
				}
				
				if ((((TreeObject)obj).isValueClient() || ((TreeObject)obj).isValueProvider())) {
					
//					String ownerString = "owner (" + ( (NamedElement)((Property)umlElement).getOwner()).getQualifiedName() + ")";
					String ownerString = ( (NamedElement)((Property)umlElement).getOwner()).getQualifiedName();

					//  construct the label
					StyledString styledString = new StyledString();
					styledString.append(name);
					styledString.append(whatString, stylerGrey);
					styledString.append(pTypeString, stylerGrey);
					
					styledString.append(", ");

					styledString.append(ownerString, stylerGrey);
					
					// set text and styles
					cell.setText( styledString.toString() );
					cell.setStyleRanges(styledString.getStyleRanges());
				}
			}
		}

		
		// set image
		if ( obj instanceof TreeObject  ){
			TreeObject treeObject = (TreeObject)obj; 
			if ( treeObject instanceof TreeParent && treeObject.getUmlElement() instanceof Model) {
				cell.setImage(decorateImage( treeObject , "/icons/Model.gif", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Model.gif"));
			}
			else if ( treeObject instanceof TreeParent && treeObject.getUmlElement() instanceof Package) {
				cell.setImage(decorateImage( treeObject , "/icons/Package.gif", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Package.gif"));
			}
			else if (treeObject.isValueMediatorContainer()) {
				cell.setImage(decorateImage( treeObject , "/icons/valueMediatorsContainer.png", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/valueMediatorsContainer.png"));
			}
			else if (treeObject.isValueMediator()) {
				cell.setImage(decorateImage( treeObject , "/icons/valueMediator.png", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/valueMediator.png"));
			}
			else if (treeObject.isValueClient() && treeObject.isValueClient_required()) {

				cell.setImage(decorateImage( treeObject , "/icons/Property.gif", null));
//				cell.setImage(decorateImage( treeObject , "", 
////						ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/ovr16/pinned_ovr.gif")));
//						ResourceManager.getPluginImage("org.eclipse.jdt.ui", "/icons/full/ovr16/focus_ovr.gif")));
				
//				cell.setImage(decorateImage( treeObject , "/icons/Property.gif", 
//						overLayImage(SWTResourceManager.getImage(Activator.class, "/icons/Property.gif"), 
//								ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/ovr16/pinned_ovr.gif")) ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/variable.png"));
//				cell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/Property.gif"));	
			}
			else if (treeObject.isValueClient() || treeObject.isValueProvider()) {
				cell.setImage(decorateImage( treeObject , "/icons/Property.gif", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/variable.png"));
//				cell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/Property.gif"));	
			}
			else if ( treeObject.isValueClientsNode() ) {
				cell.setImage(decorateImage( treeObject , "/icons/addValueClient.png", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueClient.png"));
			}
			else if ( treeObject.isValueMediatorsNode() ) {
				cell.setImage(decorateImage( treeObject , "/icons/valueMediator.png", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueProviders.png"));
			}
			else if ( treeObject.isValueProvidersNode() ) {
				cell.setImage(decorateImage( treeObject , "/icons/addValueProviders.png", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueProviders.png"));
			}
			else if ( treeObject.isInstantiatedClass()) {
				cell.setImage(decorateImage( treeObject , "/icons/Class.gif", null ));
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueProviders.png"));
			}
			else {
//				cell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/reload.png"));
				cell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/refresh.gif"));
			}
		}
		else {
			// default
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			String imageKey = ISharedImages.IMG_OBJ_FOLDER;
			cell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(imageKey));
		}

		super.update(cell);
	}
	
	// ################################################### VALIDATION
	
	
	public boolean hasEmptyClientsMediator(TreeParent treeParent) {
		if (treeParent.isReadOnly() ) { // read only nodes shall not be validated
			return false;
		}
		
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		list.addAll(findNextEmptyClientsMediator(treeParent));
		if (list.size() > 0 ) {
			return true;
		}
		return false;
	}
	

	
	private HashSet<TreeObject> findNextEmptyClientsMediator(TreeParent treeParent){
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		
		if (treeParent.isValueMediator()) {
			if (getNumberOfClients(treeParent) == 0) {
				list.add(treeParent);
				return list;
			}
		}
		
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i].isValueMediator()) {
				if (getNumberOfClients(children[i]) == 0) {
					list.add(treeParent);
					return list;
				}
			}
			else if (children[i] instanceof TreeParent && !children[i].isReadOnly() ) {
				list.addAll(findNextEmptyClientsMediator( (TreeParent)children[i] ));	
			}
		}
		return list;
	}
	
	
	
	public boolean allClientsAreMandatory(TreeParent mediator) {
		
		HashSet<TreeObject> listOfMandatoryClients = new HashSet<TreeObject>();
		int numberOfClients = 0;
		if (mediator instanceof TreeParent) {
			TreeObject[] chidren = ((TreeParent)mediator).getChildren();
			for (int i = 0; i < chidren.length; i++) {
				if (chidren[i].isValueClientsNode() ) {
					
					if (chidren[i] instanceof TreeParent) {

						TreeObject[] clients = ((TreeParent)chidren[i]).getChildren();
						numberOfClients = clients.length;
						
						for (int j = 0; j < clients.length; j++) {
							TreeObject client = clients[j];
							
							if (client.isValueClient_required()) {
								listOfMandatoryClients.add(client);
							}
						}
					}
				}
			}
		}
		
		if (listOfMandatoryClients.size() == numberOfClients ) {
			return true;
		}
		return false;
	}
		
	
	
	public boolean hasEmptyProvidersMediator(TreeParent treeParent) {
		if (treeParent.isReadOnly() ) { // read only nodes shall not be validated
			return false;
		}

		HashSet<TreeObject> list = new HashSet<TreeObject>();
		list.addAll(findNextEmptyProvidersMediator(treeParent));
		if (list.size() > 0 ) {
			return true;
		}
		return false;
	}
	
	// TODO: refactor this method!
	private HashSet<TreeObject> findNextEmptyProvidersMediator(TreeParent treeParent){
		HashSet<TreeObject> list = new HashSet<TreeObject>();

		if (treeParent.isValueMediator()) {
			int numberOfProviders = getNumberOfProviders(treeParent);
			
			if (numberOfProviders == 0) {
				String script = DeriveValueBindingCodeUtls.getOperationSpecification(treeParent.getUmlElement(), Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
				// if it has a script that only uses constant values 
				// -> then no providers are necessary and, actually, no providers should be there.
				if ( script != null && !DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(script) ) {
					// do nothing.
					//System.err.println(treeParent + " is ok");
				}
				else {
					// if all clients are mandatory and no provider exists-> error
					if (allClientsAreMandatory(treeParent)) {
						// add to the list of mediators with no providers.
						list.add(treeParent);
					}
				}
				return list;
			}
			else if (numberOfProviders == 1 ) {
				// if there is one provider with an invalid script -> error
				String script = DeriveValueBindingCodeUtls.getOperationSpecification(treeParent.getUmlElement(), Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
				/*
				 *  if there is only one provider but mediator has array reduction functions (functions for handling multiple providers) 
				 *  -> no error, because potentially there will be more providers in the instance hierarchy
				 */
				if ( script != null && DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(script) ) {
					// do nothing
				}
				else if ( script != null && !DeriveValueBindingCodeUtls.isValidMediatorSingleItemsScript(script) ) {
					list.add(treeParent);					
				}
			}
		}
		
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i].isValueMediator()) {
				
				int numberOfProviders = getNumberOfProviders(children[i]);
				if (numberOfProviders == 0) {
					String script = DeriveValueBindingCodeUtls.getOperationSpecification(children[i].getUmlElement(), Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
					if ( script != null && !DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(script) ) {
						// do nothing.
						//System.err.println(treeParent + " is ok");
					}
					else {
						// if all clients are mandatory and no provider exists-> error
						if (allClientsAreMandatory((TreeParent) children[i])) {
							// add to the list of mediators with no providers.
							list.add((TreeParent) children[i]);
						}
					}
					return list;
				}
				else if (numberOfProviders == 1 ) {
					// if there is one provider with an invalid script -> error
					String script = DeriveValueBindingCodeUtls.getOperationSpecification(children[i].getUmlElement(), Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
					/*
					 *  if there is only one provider but mediator has array reduction functions (functions for handling multiple providers) 
					 *  -> no error, because potentially there will be more providers in the instance hierarchy
					 */
					if ( script != null && DeriveValueBindingCodeUtls.hasMediatorBindingScriptFunctions(script) ) {
						// do nothing
					}
					else if ( script != null && !DeriveValueBindingCodeUtls.isValidMediatorSingleItemsScript(script) ) {
						list.add((TreeParent) children[i]);					
					}
				}
			}
			else if (children[i] instanceof TreeParent && !children[i].isReadOnly() ) {
				list.addAll(findNextEmptyProvidersMediator( (TreeParent)children[i] ));	
			}
		}
		return list;
	}
	

	
	
	private int getNumberOfClients(Object item){
		if (item instanceof TreeParent) {
			TreeObject[] chidren = ((TreeParent)item).getChildren();
			for (int i = 0; i < chidren.length; i++) {
				if (chidren[i].isValueClientsNode() ) {
					if (chidren[i] instanceof TreeParent) {
						return ((TreeParent)chidren[i]).getChildren().length;
					}
				}
			}
		}
		return 0;
	}
	
	
	private int getNumberOfProviders(Object item){
		if (item instanceof TreeParent) {
			TreeObject[] chidren = ((TreeParent)item).getChildren();
			for (int i = 0; i < chidren.length; i++) {
				if (chidren[i].isValueProvidersNode()) {
					if (chidren[i] instanceof TreeParent) {
						return ((TreeParent)chidren[i]).getChildren().length;
					}
				}
			}
		}
		return 0;
	}
	
	
	// Operation Validation **************************************************************
	
	private String markerType = Constants.MARKERTYPE_VALUEBINDINGS;
	
	public boolean hasMarkers(TreeObject item){
		
		Element umlElement = null;
		
		if (item.isValueMediator()) {
			umlElement = item.getUmlElement();
		}
		else if (item.isValueClient()) {
			TreeObject mediator = TreeUtls.getNearestMediator(item);
			if (mediator != null) {
				EList<Dependency> list = TreeUtls.getMediatorDependency((NamedElement)mediator.getUmlElement(), 
										(NamedElement)item.getUmlElement(), Constants.stereotypeQName_ProvidesValueFor);
				if (list!= null && list.size() > 0 ) {
					umlElement = list.get(0);
				}
			}
		}
		else if (item.isValueProvider()) {
			TreeObject mediator = TreeUtls.getNearestMediator(item);
			if (mediator != null) {
				EList<Dependency> list = TreeUtls.getMediatorDependency((NamedElement)mediator.getUmlElement(), 
						(NamedElement)item.getUmlElement(), Constants.stereotypeQName_ObtainsValueFrom);
				
				if (list!= null && list.size() > 0 ) {
					umlElement = list.get(0);
				}
			}
		}
		
		if (umlElement instanceof NamedElement) {
			// markers
			
			IProject iProject = null;
//			ExtendedUmlModel umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
//			if (umlModel != null && umlModel.getResource() != null) {
			if (umlModel != null && ((Element)umlModel).eResource() != null) {
//				String projectName = umlModel.getResource().getURI().segment(1);
				String projectName = ((Element)umlModel).eResource().getURI().segment(1);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				iProject = root.getProject(projectName);
			}
			
			IMarker[] markers = null;
			try {
				if (iProject != null) {
					markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
					for (IMarker marker : markers) {
						Object qualifiedName = marker.getAttribute(IMarker.LOCATION);
						Object sourceId = marker.getAttribute(IMarker.SOURCE_ID);
//						Object markerMessage = marker.getAttribute(IMarker.MESSAGE);

						String elementID = "";
						Resource xmiResource = umlElement.eResource();
						if( xmiResource != null ) {
							elementID = ((XMLResource) xmiResource).getID(umlElement);
						}
						
//						if (qualifiedName != null && markerMessage != null) {
						if (qualifiedName != null) {
							if (qualifiedName.equals(((NamedElement)umlElement).getQualifiedName())
									&& elementID.equals(sourceId)) {
								return true;				
							}
						}
					}
				}

			} catch (CoreException e) {
				//e.printStackTrace();
			}
		}
		return false;
	}
	
	
	private boolean hasErrorsInItemOperationCode(TreeObject item){
		if ((item.isValueClient() || item.isValueMediator() || item.isValueProvider() ) 
				&& hasMarkers(item)) {
				return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list = findNextItemWithErrorInOperationsCode((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextItemWithErrorInOperationsCode(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		TreeObject[] children = item.getChildren();
		for (TreeObject child : children) {
			if (child.isValueClient() || child.isValueMediator() || child.isValueProvider()) {
				if (hasMarkers(child)) {
					list.add(child);
					return list;
				}
			}
			if (child instanceof TreeParent) {
				list.addAll(findNextItemWithErrorInOperationsCode((TreeParent) child));
			}
		}
		return list;
	}
	
	
	// ################################################### VALIDATION END

	private String getOperationSpecification(Element element, String stereotypeQName, String propertyName){
		if (element != null) {
			Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
			if (stereotype != null) {
				Object o = element.getValue(stereotype, propertyName);
				if (o instanceof String) {
					if ( !((String)o).trim().equals("") ) {
						return "   -   " + (String)o;
					}
				}
			}
		}			
		return "";
	}
	
	private String getOperationSpecification(TreeObject item, String stereotypeQName, String propertyName){
		String prefix = "";
		Element element = null;
		
		if (item != null ) {
			element = item.getUmlElement();
		}
		if (element != null) {
			if (item.isValueMediator()) { prefix = "mediator operation: "; }
			
			Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
			if (stereotype != null) {
				Object o = element.getValue(stereotype, propertyName);
				if (o instanceof String) {
					if ( !((String)o).trim().equals("") ) {
						return "   -   " + prefix + (String)o;
					}
				}
			}
		}			
		return "";
	}
	
	
	// Image handling ********************************************************************
	
	class OverLayImageDescriptor extends ImageDescriptor{
		private Image image;
		public OverLayImageDescriptor(Image image){
			this.image = image;
		}
		@Override
		public ImageData getImageData() {
			return image.getImageData();
		}
		
	}
	
	public Image overLayImage(Image image, Image overLayImage) {
		OverLayImageDescriptor imageDescriptor = new OverLayImageDescriptor(overLayImage);
		return new DecorationOverlayIcon(image,  imageDescriptor, IDecoration.TOP_RIGHT).createImage();
	}
	
	
	public Image decorateImage(Object element, String imagePath, Image image) {

		if (element instanceof TreeParent) {
			Image imageToBeUsed = null;
			if (image != null) {
				imageToBeUsed = image;
			}
			else {
				imageToBeUsed = SWTResourceManager.getImage(Activator.class, imagePath);	
			}

			if (hasErrorsInItemOperationCode((TreeObject) element)) {
				return new DecorationOverlayIcon(imageToBeUsed, 
						errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();				
			}
			
			if ( ((TreeObject) element).isValueClient_required()) {
				return new DecorationOverlayIcon(imageToBeUsed, 
						infoImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();				
			}

			if (hasEmptyProvidersMediator((TreeParent)element)) {
				return new DecorationOverlayIcon(imageToBeUsed, 
						errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();				
			}
			if (hasEmptyClientsMediator((TreeParent)element)) {
				return new DecorationOverlayIcon(imageToBeUsed, 
						warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
			}
		}
		
		return SWTResourceManager.getImage(Activator.class, imagePath);
	}
	
	public EObject getUmlModel() {
		return umlModel;
	}

	public void setUmlModel(EObject umlModel) {
		this.umlModel = umlModel;
	}
}
