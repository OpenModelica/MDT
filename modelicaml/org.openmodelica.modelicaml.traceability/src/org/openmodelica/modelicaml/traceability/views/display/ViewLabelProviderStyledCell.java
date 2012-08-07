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
package org.openmodelica.modelicaml.traceability.views.display;

import java.util.HashSet;

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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.traceability.Activator;
import org.openmodelica.modelicaml.traceability.views.model.ClientItem;
import org.openmodelica.modelicaml.traceability.views.model.RequirementItem;
import org.openmodelica.modelicaml.traceability.views.model.ScenarioItem;
import org.openmodelica.modelicaml.traceability.views.model.TreeObject;
import org.openmodelica.modelicaml.traceability.views.model.TreeParent;

public class ViewLabelProviderStyledCell extends StyledCellLabelProvider {
	
	private String projectName = "";
	
	private boolean decorateItem = true;
	
	private final ImageDescriptor warningImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");
	private final ImageDescriptor okStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/success_ovr.gif");
	private final ImageDescriptor questionStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/question_ov.gif");
//	private final ImageDescriptor infoImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	
	DecorationOverlayIcon overlayIcon = null;
	Object overlayIconImage = null;
	
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
	
	// used to decorate the tree item string 
	private StyledString styledString;

	
	@Override
	public void update(ViewerCell cell) {
		String treeItemText = "";
		Object obj = cell.getElement();
		
		TreeObject item = (TreeObject)obj;
		
		// name string 
		String name = item.getName() + " ";
		
		// special case: requirement
		Element umlelElement = item.getUmlElement();
		if (umlelElement!= null && umlelElement.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
			name = (String) umlelElement.getValue(umlelElement.getAppliedStereotype(Constants.stereotypeQName_Requirement), Constants.propertyName_id) + " - " + ((NamedElement) umlelElement).getName() + " ";
		}		
		
		// number of children string 
		String numberOfChildren = "";
		if (obj instanceof TreeParent) {
			numberOfChildren = "(" + ((TreeParent)obj).getChildren().length + ")";
		}
		
		//  construct the label
		styledString = new StyledString();
		styledString.append(name);
		treeItemText = treeItemText + name;
		
		styledString.append(numberOfChildren, stylerGrey);
		treeItemText = treeItemText + numberOfChildren;
		
		// for the root (folder) add the project name
		if (obj instanceof TreeParent && ((TreeParent)obj).getName().equals(Constants.folderName_code_sync) ) {
			styledString.append(" - " + getProjectName(), stylerGrey);
			treeItemText = treeItemText + " - " + getProjectName();
		}
		
		// set text and styles
		
		if (decorateItem) {
			cell.setText( styledString.toString() );
			cell.setStyleRanges(styledString.getStyleRanges());
		}
		else {
			cell.setText(treeItemText);
		}
		
		
		
		// set image
		if ( obj instanceof TreeObject  ){
			TreeObject treeObject = (TreeObject)obj; 
			
			setImage(treeObject);
			cell.setImage((Image) overlayIconImage);
		}

		super.update(cell);
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
	
	private void setImage(TreeObject treeObject){
		
		Element umlElement = treeObject.getUmlElement();
		if (umlElement != null) {
			
			// requirement, scenarios items, i.e. classes
			if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/requirement.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/calculationModel.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Model) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/model.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Block) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/block.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Record) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/record.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Connector) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/connector.gif", null ); 
			}

			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Function) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/FunctionBehavior.gif", null ); 
			}
			
			// clients item
			else if ( umlElement instanceof Property) {
				overlayIconImage = decorateImage( treeObject , "/icons/Property.gif", null ); 
			}
			
			else {
				overlayIconImage = decorateImage( treeObject , "/icons/Class.gif", null );
			}
		}
		else {
			// if it is a info node: 
			if (treeObject.getParent() != null && treeObject.getParent().getUmlElement() != null) {
				overlayIconImage = decorateImage( treeObject , "/icons/message_info.gif", null );
			}
			else {
				overlayIconImage = decorateImage( treeObject , "/icons/unknown_obj.gif", null );
			}
		}
	}
	
	private boolean isRoot(TreeParent treeParent){
		//get the parent: if it is the invisible root node then it will not have a parent ... 
		if (treeParent.getParent() != null && treeParent.getParent().getParent() == null) {
			return true;
		}
		return false;
	}

	
	private Image decorateImage(Object element, String imagePath, Image image) {

		if (element instanceof TreeParent) {
			Image imageToBeUsed = null;
			if (image != null) {
				imageToBeUsed = image;
			}
			else {
				imageToBeUsed = SWTResourceManager.getImage(Activator.class, imagePath);	
			}
			
			if ( imageToBeUsed != null) {

				if (decorateItem) { 
					
					if (isRoot((TreeParent) element) && hasErrosInItsChildren((TreeParent) element)) {
						overlayIcon = new DecorationOverlayIcon(imageToBeUsed, warningImageDescriptor, IDecoration.BOTTOM_RIGHT);
						return overlayIcon.createImage();
					}
					
					else if (element instanceof RequirementItem) {

						// if this is a not analyzed requirement
						if (((RequirementItem)element).isUnknown()) {
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, questionStateImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
						}
						// not a valid requirement because it has unsatisfied clients
						else if (!((RequirementItem)element).isValid()) {
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, errorImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
						}
						else{
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, okStateImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
							
						}
					}
					else if (element instanceof ScenarioItem) {
						// not valid because it was discarded since it does not have appropriate providers
						if (!((ScenarioItem)element).isValid()) {
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, errorImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
						}
						// if there are requirements with unsatisfied clients
						else if (hasErrosInItsChildren((TreeParent) element)) {
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, warningImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
						}
						else{
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, okStateImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
							
						}
						
					}
					else if (element instanceof ClientItem) {
						if (!((ClientItem)element).isValid()) {
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, errorImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
						}
						else{
							overlayIcon = new DecorationOverlayIcon(imageToBeUsed, okStateImageDescriptor, IDecoration.BOTTOM_RIGHT);
							return overlayIcon.createImage();
							
						}
					}
					else if (hasErrosInItsChildren((TreeParent) element)) {
						overlayIcon = new DecorationOverlayIcon(imageToBeUsed, errorImageDescriptor, IDecoration.BOTTOM_RIGHT);
						return overlayIcon.createImage();
					}

				}
			}
		}
		
		if (image != null) {
			return image;
		}
		
		return SWTResourceManager.getImage(Activator.class, imagePath);
	}


	
	public boolean hasErrosInItsChildren(TreeParent treeParent){
		if (findChildrenErrors(treeParent).size() > 0 ) {
			return true;
		}
		return false;
	}
	
	public HashSet<TreeObject> findChildrenErrors(TreeObject item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		
		// check the item itself
		if ( hasErrors(item)) {
			list.add(item);
			return list;
		}
		
		// check its children
		if (item instanceof TreeParent) {
			TreeObject[] children = ((TreeParent) item).getChildren();
			for (int i = 0; i < children.length; i++) {
				if (hasErrors(children[i])) {
					list.add(children[i]);
					return list;
				}
				// recursive call
				else {
					list.addAll(findChildrenErrors( (TreeParent)children[i] ));	
				}
			}
		}
		
		return list;
	}
	
	private boolean hasErrors(TreeObject treeParent){
		if (treeParent instanceof RequirementItem && !((RequirementItem)treeParent).isValid()) {
			return true;
		}
		if (treeParent instanceof ScenarioItem && !((ScenarioItem)treeParent).isValid()) {
			return true;
		}
		return false;
	}
	
	public void setDecorateItem(boolean decorateItem) {
		this.decorateItem = decorateItem;
	}


	public boolean isDecorateItem() {
		return decorateItem;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
	
}
