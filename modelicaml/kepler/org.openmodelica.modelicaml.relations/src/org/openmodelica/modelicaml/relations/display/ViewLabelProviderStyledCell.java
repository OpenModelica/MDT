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
package org.openmodelica.modelicaml.relations.display;

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
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.relations.Activator;
import org.openmodelica.modelicaml.relations.model.TreeObject;
import org.openmodelica.modelicaml.relations.model.TreeParent;


public class ViewLabelProviderStyledCell extends StyledCellLabelProvider {
	
	private String projectName = "";
	
	private boolean decorateItem = true;
	
//	private final ImageDescriptor warningImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");
//	private final ImageDescriptor okStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/success_ovr.gif");
//	private final ImageDescriptor questionStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/question_ov.gif");
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
		

		Element umlelElement = item.getUmlElement();

//		// special case: requirement		
//		if (umlelElement!= null && umlelElement.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
//			name = (String) umlelElement.getValue(umlelElement.getAppliedStereotype(Constants.stereotypeQName_Requirement), Constants.propertyName_id) + " - " + ((NamedElement) umlelElement).getName() + " ";
//		}
		
		String ownerQName = "";
		
		if (!(item instanceof TreeParent && ((TreeParent)item).isRoot() )) {
			Element element = umlelElement;
			if (umlelElement == null) {
				element = item.getDependencyElement();
			}
			if (element == null) {
				element = item.getGeneralzationElement();
			}
			
			if (element != null && element.getOwner() != null) {
				ownerQName = "  (" + ModelicaMLServices.getQualifiedName(element.getOwner()) + ") ";
			}
		}
		
		// number of children string 
		String numberOfChildren = "";
		if (obj instanceof TreeParent) {
			numberOfChildren = " (" + ((TreeParent)obj).getChildren().length + ")";
		}
		
		//  construct the label
		styledString = new StyledString();
		styledString.append(name);
		treeItemText = treeItemText + name;

		// append number of children
		styledString.append(numberOfChildren, stylerGrey);
		treeItemText = treeItemText + numberOfChildren;
		
		// append owner qualified name
		styledString.append(ownerQName, stylerGrey);
		treeItemText = treeItemText + ownerQName;

		
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

		// The top-level first info nodes
		if (treeObject instanceof TreeParent && ((TreeParent)treeObject).isDependeciesNode() && ((TreeParent)treeObject).getDependencyElement() == null) {
			overlayIconImage = decorateImage( treeObject , "/icons/Dependency.gif", null );
		}
		else if (treeObject instanceof TreeParent && ((TreeParent)treeObject).isInheritanceNode() && ((TreeParent)treeObject).getGeneralzationElement() == null) {
			overlayIconImage = decorateImage( treeObject , "/icons/Generalization.gif", null );
		}
		
		// nodes with uml elements
		else if (umlElement != null) {
			
			if (umlElement instanceof Model) {
				overlayIconImage = decorateImage( treeObject , "/icons/Model.gif", null ); 
			}
			else if ( umlElement instanceof Dependency ) {
				overlayIconImage = decorateImage( treeObject , "/icons/Dependency.gif", null ); 
			}
			else if ( umlElement instanceof Generalization) {
				overlayIconImage = decorateImage( treeObject , "/icons/Generalization.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/requirement.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/calculationModel.gif", null ); 
			}
			else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Model) != null ) {
				overlayIconImage = decorateImage( treeObject , "/icons/modelicaModel.gif", null ); 
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
			else if ( umlElement instanceof Property) {
				overlayIconImage = decorateImage( treeObject , "/icons/Property.gif", null ); 
			}
			else  if ( umlElement instanceof PrimitiveType) {
				overlayIconImage = decorateImage( treeObject , "/icons/PrimitiveType.gif", null ); 
			}
			else {
				overlayIconImage = decorateImage( treeObject , "/icons/Class.gif", null );
			}
		}
		else {
			overlayIconImage = decorateImage( treeObject , "/icons/unknown_obj.gif", null );
		}
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
					
					if ( ((TreeObject) element).isHasErrors() ) {
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
