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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.modelicaml.view.componentstree.display;


import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.componentstree.Activator;


// TODO: Auto-generated Javadoc
/**
 * The Class ViewLabelProvider.
 */
public class ViewLabelProvider extends StyledCellLabelProvider {

	/** The Constant variableStereotypeQName. */
	private static final String variableStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::Variable";
	/** The Constant functionArgumentStereotypeQName. */
	private static final String functionArgumentStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::FunctionArgument";
	/** The Constant portStereotypeQName. */
	private static final String portStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::ConnectionPort";
	/** The Constant componentStereotypeQName. */
	private static final String componentStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::Component";
	/** The Constant calculatedPropertyStereotypeQName. */
	private static final String calculatedPropertyStereotypeQName = "ModelicaML::ModelicaCompositeConstructs::CalculatedProperty";
	/** The Constant requirementInstanceStereotypeQName. */
	private static final String requirementInstanceStereotypeQName = "ModelicaML::ModelicaRequirementConstructs::RequirementInstance";
	
	private final ImageDescriptor warningImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	private final ImageDescriptor errorImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);

	
	public String getText(Object obj) {
		return obj.toString();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StyledCellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
	 */
	public void update(ViewerCell cell) {
		Object obj = cell.getElement();
		if (obj instanceof TreeParent) {
			TreeParent treeObject = (TreeParent) obj;
			
			String declarationString = "";
			if (treeObject.getFinalModificationRightHand() != null ) {
				declarationString = " = " + treeObject.getFinalModificationRightHand();
			}
			else if (treeObject.getDeclaration() != null) {
				declarationString = treeObject.getDeclaration();
			}
			else {
				declarationString = "";
			}
			// remove all line delimiters
			declarationString = declarationString.replaceAll("\\r|\\n", "");

			
//			System.out.println("treeObject.getFinalModificationRightHand(): " + treeObject.getFinalModificationRightHand());
//			System.out.println("treeObject.getDeclaration(): " + treeObject.getDeclaration());
//			System.out.println("declarationString : " + declarationString );
			
			// limit the display length
//			if (declarationString.length() > 30) {
//				declarationString = declarationString.substring(0, 30) + " ...";
//			}
			
			String arraySizeString = "";
			if (treeObject.getProperty() != null ) {
				Stereotype appliedStereotype = treeObject.getProperty().getAppliedStereotype(componentStereotypeQName);
//				Stereotype stereotype = (Stereotype)appliedStereotype.getGeneral("InstanceOfAClass");

				if (appliedStereotype == null) { appliedStereotype = treeObject.getProperty().getAppliedStereotype(portStereotypeQName); }
				if (appliedStereotype == null) { appliedStereotype = treeObject.getProperty().getAppliedStereotype(calculatedPropertyStereotypeQName); }
				if (appliedStereotype == null) { appliedStereotype = treeObject.getProperty().getAppliedStereotype(requirementInstanceStereotypeQName); }
				if (appliedStereotype == null) { appliedStereotype = treeObject.getProperty().getAppliedStereotype(variableStereotypeQName); }
				if (appliedStereotype == null) { appliedStereotype = treeObject.getProperty().getAppliedStereotype(functionArgumentStereotypeQName); }

				if (appliedStereotype != null) {
					if ( hasArraySize(treeObject.getProperty(), appliedStereotype ) ) {
						arraySizeString = "[..]";
					}
				}
			}
			
			
			// #######################################################################################
			// set image
			
			// TODO use the icons from stereotypes.
//			Property p = treeObject.getProperty();
//			EList<Stereotype> pStereotypesList = p.getAppliedStereotypes();
//			Stereotype s = pStereotypesList.get(0);
//			EList<Image> iconsList = s.getIcons();
//			Image image = iconsList.get(0);
//			cell.setImage(image); // cannot be casted rt
			
			if (treeObject.isLeaf() ) {
				if (treeObject.getProperty() instanceof Port) {
//					cell.setImage(createImage("port.gif")); 	
					cell.setImage(decorateImage( treeObject , "/icons/port.gif" ));
				}
				else {
//					cell.setImage(createImage("variable.png"));
					cell.setImage(decorateImage( treeObject , "/icons/variable.png" ));

				}
			}
			else if (treeObject.isRoot()) {
//				cell.setImage(createImage("Class.gif")); 
				cell.setImage(decorateImage( treeObject , "/icons/Class.gif" ));
			}
			else {
				if (treeObject.getProperty() instanceof Port) {
//					cell.setImage(createImage("port.gif")); 	
					cell.setImage(decorateImage( treeObject , "/icons/port.gif" ));
				}
				else if (treeObject.getUmlElement() != null) {
					if (treeObject.getUmlElement() instanceof Signal) {
//						cell.setImage(createImage("Signal.gif"));
						cell.setImage(decorateImage( treeObject , "/icons/Signal.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof StateMachine) {
//						cell.setImage(createImage("StateMachine.gif"));
						cell.setImage(decorateImage( treeObject , "/icons/StateMachine.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof Region) {
//						cell.setImage(createImage("Region.gif"));
						cell.setImage(decorateImage( treeObject , "/icons/Region.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof State) {
//						cell.setImage(createImage("State.gif"));
						cell.setImage(decorateImage( treeObject , "/icons/State.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof Property) {
//						cell.setImage(createImage("Property.gif"));
						cell.setImage(decorateImage( treeObject , "/icons/Property.gif" ));
					}
				}
				else {
					// TODO: icons for ModelicaML Component, RequirementInstance, Calculated Property etc.
					//cell.setImage(createImage("component.png"));
//					cell.setImage(createImage("Property.gif"));
					cell.setImage(decorateImage( treeObject , "/icons/Property.gif" ));
				}
			}
			
			// #######################################################################################
			// set text
			if (!treeObject.isLeaf() && !treeObject.isRoot() && treeObject.getChildren().length > 0) { // tree with children object 
				
				String hasRedecalaredTypeString = "";
				if (treeObject.hasRedeclaredType()) {
					hasRedecalaredTypeString = " <- redeclared ";
				}
				
				StyledString styledString = new StyledString(treeObject.toString() + arraySizeString);
				styledString.append(hasRedecalaredTypeString, StyledString.DECORATIONS_STYLER);
				styledString.append(" (" + treeObject.getChildren().length + ")", StyledString.QUALIFIER_STYLER);
				Styler styler = new Styler() {
					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.foreground = new Color(null, new RGB(105, 105, 105)); // Dim Gray
					}
				};
				
				if (treeObject.isInherited()) {
//					System.err.println(treeObject.getName() + " is inherited");
					styledString.setStyle(0, (treeObject.toString() + arraySizeString).length(), styler);
				}
				
				cell.setText(styledString.toString());
				cell.setStyleRanges(styledString.getStyleRanges());
				
				super.update(cell);
			}
			else if (treeObject.isRoot()) { // only for inputs or outputs root node
				
				StyledString styledString = new StyledString("");
				
				if (treeObject.isInput()) {
					styledString.append(treeObject.toString(), StyledString.COUNTER_STYLER);
				}
				else if (treeObject.isOutput()) {
					styledString.append(treeObject.toString(), StyledString.DECORATIONS_STYLER);
				}
				else {
					styledString.append(treeObject.toString(), StyledString.QUALIFIER_STYLER);	
				}
				cell.setText(styledString.toString());
				cell.setStyleRanges(styledString.getStyleRanges());
				super.update(cell);
			}
			else if (treeObject.isInput()) {
				String tNameString = "input ";
				if (treeObject.getProperty().getType() != null) {
					tNameString = tNameString + treeObject.getProperty().getType().getName().replaceFirst("Modelica", "") + " ";
				}
				//StyledString styledString = new StyledString("");
				//styledString.append(tNameString + treeObject.toString() + declarationString, StyledString.COUNTER_STYLER);
				//cell.setText(styledString.toString());
				//cell.setStyleRanges(styledString.getStyleRanges());
				
				if (declarationString.trim().equals("")) {
					declarationString = " = ???";
				}
				cell.setText(tNameString + treeObject.toString() + arraySizeString + declarationString);
				//cell.setForeground(new Color(null, 255, 0, 0)); // RED
				cell.setForeground(new Color(null, 234, 0, 0)); // darker RED
				super.update(cell);
				}
			else if (treeObject.isOutput())  {
				String tNameString = "output ";
				if (treeObject.getProperty().getType() != null) {
					tNameString = tNameString + treeObject.getProperty().getType().getName().replaceFirst("Modelica", "") + " ";
				}
//				StyledString styledString = new StyledString("");
//				styledString.append(tNameString + treeObject.toString() + declarationString, StyledString.DECORATIONS_STYLER);
//				cell.setText(styledString.toString());
//				cell.setStyleRanges(styledString.getStyleRanges());
				
				cell.setText(tNameString + treeObject.toString() + arraySizeString + declarationString);
				//cell.setForeground(new Color(null, 0, 150, 0)); // darker GREEN
				cell.setForeground(new Color(null, 0, 187, 0)); // GREEN
				super.update(cell);
			}
			else if (treeObject.isLeaf())  {
				String tNameString = "";
				if (treeObject.getProperty() != null) {
					if (treeObject.getProperty().getType() != null) {
						tNameString = tNameString + treeObject.getProperty().getType().getName().replaceFirst("Modelica", "") + " ";
					}
				}
				cell.setText(tNameString + treeObject.toString() + arraySizeString + declarationString);
			}
			else {
				String tNameString = "";
				if (treeObject.getProperty().getType() != null) {
					tNameString = tNameString + treeObject.getProperty().getType().getName().replaceFirst("Modelica", "") + " ";
				}

				StyledString styledString = new StyledString(tNameString + treeObject.toString() + arraySizeString + declarationString);
				cell.setText(styledString.toString());
				cell.setStyleRanges(styledString.getStyleRanges());
				super.update(cell);
			}
		}
	}
	
	
	/**
	 * Checks for array size.
	 *
	 * @param element the element
	 * @param stereotype the stereotype
	 * @return the boolean
	 */
	private Boolean hasArraySize(TypedElement element, Stereotype stereotype){
		EList<String> modList = (EList<String>) element.getValue(stereotype, "arraySize");
		if (modList != null && modList.size() > 0) {
			for (String string : modList) {
				if (!string.trim().equals("")) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean hasErrors(TreeParent treeParent) {
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		list.addAll(findNextInvalidItem(treeParent));
		if (list.size() > 0 ) {
//			for (TreeObject treeObject : list) {
//				System.err.println(treeObject.getName());
//			}
			return true;
		}
		return false;
	}
	
	
	private HashSet<TreeObject> findNextInvalidItem(TreeParent treeParent){
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		
		// if property has no type 
		if (treeParent.getUmlElement() instanceof Property && treeParent.getComponentType() == null) {
			list.add(treeParent);
			return list;
		}
		
		// if property is input and has no declaration and no binding equation exists for it in its first level component modification
		if (treeParent.isInput() && treeParent.getDeclaration() == null && treeParent.getFinalModificationRightHand() == null) {
			list.add(treeParent);
			return list;
		}

		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i] instanceof TreeParent) {
				list.addAll(findNextInvalidItem( (TreeParent)children[i] ));	
			}
		}
		return list;
	}
	

	public Image decorateImage(Object element, String imagePath) {
		if (element instanceof TreeParent) {
			if (hasErrors((TreeParent)element)) {
				return new DecorationOverlayIcon(SWTResourceManager.getImage(Activator.class, imagePath), errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();				
			}
		}
		return SWTResourceManager.getImage(Activator.class, imagePath);
	}
	
//	/**
//	 * Gets the modelica predefined type name.
//	 * 
//	 * @param name
//	 *            the name
//	 * @return the modelica predefined type name
//	 */
//	private String getModelicaPredefinedTypeName(String name){
//		String typeName = name;
//		if (name.equals("ModelicaReal")) {
//			return typeName = "Real";
//		}
//		if (name.equals("ModelicaInteger")) {
//			return typeName = "Integer";
//		}
//		if (name.equals("ModelicaString")) {
//			return typeName = "String";
//		}
//		if (name.equals("ModelicaBoolean")) {
//			return typeName = "Boolean";
//		}
//
//		return typeName;
//	}
	
	/**
	 * Creates the image.
	 * 
	 * @param imagePath
	 *            the image path
	 * @return the image
	 */
//	public Image createImage(String imagePath){
//		final Bundle pluginBundle = Platform.getBundle(Activator.PLUGIN_ID);
//		final Path imageFilePath = new Path(Activator.IMAGES_PATH + imagePath);
//		final URL imageFileUrl = Platform.find(pluginBundle, imageFilePath);
//		
//		Image image = null;
//		InputStream imageFileStream = null;   	
//		try {
//  	    	imageFileStream = imageFileUrl.openStream();
//  	    	image = new Image(null, imageFileStream);
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		finally {
//			if (imageFileStream != null)
//				try { imageFileStream.close(); } catch (IOException e) {}
//		}
//
//		return image;
//    }	
}