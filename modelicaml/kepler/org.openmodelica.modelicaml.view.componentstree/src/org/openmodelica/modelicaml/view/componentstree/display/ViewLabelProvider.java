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
package org.openmodelica.modelicaml.view.componentstree.display;


import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.componentstree.Activator;


// TODO: Auto-generated Javadoc
/**
 * The Class ViewLabelProvider.
 */
public class ViewLabelProvider extends StyledCellLabelProvider {

	/** The Constant variableStereotypeQName. */
	private static final String variableStereotypeQName = Constants.stereotypeQName_Variable;
	/** The Constant functionArgumentStereotypeQName. */
	private static final String functionArgumentStereotypeQName = Constants.stereotypeQName_FunctionArgument;
	/** The Constant portStereotypeQName. */
	private static final String portStereotypeQName = Constants.stereotypeQName_ConnectionPort;
	/** The Constant componentStereotypeQName. */
	private static final String componentStereotypeQName = Constants.stereotypeQName_Component;
	/** The Constant calculatedPropertyStereotypeQName. */
	private static final String calculatedPropertyStereotypeQName = Constants.stereotypeQName_CalculatedProperty;
	/** The Constant requirementInstanceStereotypeQName. */
	private static final String requirementInstanceStereotypeQName = Constants.stereotypeQName_RequirementInstance;
	
	private final ImageDescriptor warningImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");
	private final ImageDescriptor infoImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/info_ovr.gif");
	
	private final ImageDescriptor okStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/success_ovr.gif");
	private final ImageDescriptor questionStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/question_ov.gif");

	
	private final static String requiredClientIndicator = "(mand. client)";
	private final static String clientIndicator = "(client)";
	private final static String providerIndicator = "(provider)";

	private String markerType = Constants.MARKERTYPE_COMPONENT_MODIFICATION;

	
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
			
			String arraySizeString = "";
			if (treeObject.getProperty() != null ) {

				Stereotype appliedStereotype = treeObject.getProperty().getAppliedStereotype(componentStereotypeQName);

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
			if (treeObject.isLeaf() ) {
				if (treeObject.getProperty() instanceof Port) {
					cell.setImage(decorateImage( treeObject , "/icons/port.gif" ));
				}
				else {
					cell.setImage(decorateImage( treeObject , "/icons/variable.png" ));

				}
			}
			else if (treeObject.isRoot()) {
				cell.setImage(decorateImage( treeObject , "/icons/Class.gif" ));
			}
			else {
				if (treeObject.getProperty() instanceof Port) {
					cell.setImage(decorateImage( treeObject , "/icons/port.gif" ));
				}
				else if (treeObject.getUmlElement() != null) {
					if (treeObject.getUmlElement() instanceof Signal) {
						cell.setImage(decorateImage( treeObject , "/icons/Signal.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof StateMachine) {
						cell.setImage(decorateImage( treeObject , "/icons/StateMachine.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof Region) {
						cell.setImage(decorateImage( treeObject , "/icons/Region.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof State) {
						cell.setImage(decorateImage( treeObject , "/icons/State.gif" ));
					}
					else if (treeObject.getUmlElement() instanceof Property) {
						cell.setImage(decorateImage( treeObject , "/icons/Property.gif" ));
					}
				}
				else {
					// ModelicaML Component, RequirementInstance, Calculated Property etc.
					cell.setImage(decorateImage( treeObject , "/icons/Property.gif" ));
				}
			}
			
			// #######################################################################################
			// set text
			
			if (treeObject.isRoot()) { // only for inputs or outputs root node
				StyledString styledString = new StyledString("");
				styledString.append(treeObject.toString(), StyledString.QUALIFIER_STYLER);
				cell.setText(styledString.toString());
				cell.setStyleRanges(styledString.getStyleRanges());
				super.update(cell);
			}
			
			else if (!treeObject.isLeaf() && !treeObject.isRoot() && treeObject.getChildren().length > 0) { // tree object with children  
				
				String hasRedecalaredTypeString = "";
				if (treeObject.hasRedeclaredType()) {
					hasRedecalaredTypeString = " <- redeclared ";
				}
				
				String tNameString = "";
				// add client or provider indicator
				tNameString = getValueClientOrProviderIndicatorString(treeObject) + treeObject.toString();
				
				StyledString styledString = new StyledString(tNameString + arraySizeString);
				styledString.append(hasRedecalaredTypeString, StyledString.DECORATIONS_STYLER);
				styledString.append(" (" + treeObject.getChildren().length + ")", StyledString.QUALIFIER_STYLER);
				Styler styler = new Styler() {
					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.foreground = new Color(null, new RGB(105, 105, 105)); // Dim Gray
					}
				};
				
				if (treeObject.isInherited()) {
					styledString.setStyle(0, (treeObject.toString() + arraySizeString).length(), styler);
				}
				
				cell.setText(styledString.toString());
				cell.setStyleRanges(styledString.getStyleRanges());
				
				super.update(cell);
			}
//			else if (treeObject.isRoot()) { // only for inputs or outputs root node
//				StyledString styledString = new StyledString("");
//				
//				if (treeObject.isInput()) {
//					styledString.append(treeObject.toString(), StyledString.COUNTER_STYLER);
//				}
//				else if (treeObject.isOutput()) {
//					styledString.append(treeObject.toString(), StyledString.DECORATIONS_STYLER);
//				}
//				else {
//					styledString.append(treeObject.toString(), StyledString.QUALIFIER_STYLER);
//				}
//				cell.setText(styledString.toString());
//				cell.setStyleRanges(styledString.getStyleRanges());
//				super.update(cell);
//			}
			
			else if (treeObject.isInput() || treeObject.isOutput()) {
				String tNameString = "";
				
				// add client or provider indicator
				tNameString = tNameString + getValueClientOrProviderIndicatorString(treeObject);
				
				if (treeObject.isInput()) {
					tNameString = tNameString + "input ";
					
					if (declarationString.trim().equals("") && !oneOfParentsIsPort(treeObject)) {
						declarationString = " = ?";
					}
				}
				else if (treeObject.isOutput()) {
					tNameString = tNameString + "output ";
				}

				cell.setText(tNameString + treeObject.toString() + arraySizeString + declarationString);
				if (treeObject.isInput()) {
					cell.setForeground(new Color(null, 234, 0, 0)); // darker RED
				}
				else if (treeObject.isOutput()) {
					cell.setForeground(new Color(null, 0, 187, 0)); // GREEN
				}
				super.update(cell);
			}
//			else if (treeObject.isInput()) {
//				String tNameString = "input ";
//				if (treeObject.getProperty().getType() != null) {
//					tNameString = tNameString + treeObject.getProperty().getType().getName().replaceFirst("Modelica", "") + " ";
//				}
//				//StyledString styledString = new StyledString("");
//				//styledString.append(tNameString + treeObject.toString() + declarationString, StyledString.COUNTER_STYLER);
//				//cell.setText(styledString.toString());
//				//cell.setStyleRanges(styledString.getStyleRanges());
//				
//				if (declarationString.trim().equals("")) {
//					declarationString = " = ???";
//				}
//				cell.setText(tNameString + treeObject.toString() + arraySizeString + declarationString);
//				//cell.setForeground(new Color(null, 255, 0, 0)); // RED
//				cell.setForeground(new Color(null, 234, 0, 0)); // darker RED
//				super.update(cell);
//				}
//			else if (treeObject.isOutput())  {
//				String tNameString = "output ";
//				if (treeObject.getProperty().getType() != null) {
//					tNameString = tNameString + treeObject.getProperty().getType().getName().replaceFirst("Modelica", "") + " ";
//				}
////				StyledString styledString = new StyledString("");
////				styledString.append(tNameString + treeObject.toString() + declarationString, StyledString.DECORATIONS_STYLER);
////				cell.setText(styledString.toString());
////				cell.setStyleRanges(styledString.getStyleRanges());
//				
//				cell.setText(tNameString + treeObject.toString() + arraySizeString + declarationString);
//				//cell.setForeground(new Color(null, 0, 150, 0)); // darker GREEN
//				cell.setForeground(new Color(null, 0, 187, 0)); // GREEN
//				super.update(cell);
//			}
			else if (treeObject.isLeaf())  {
				String tNameString = "";
				
				// add Value Client or Provider indicator
				tNameString = tNameString + getValueClientOrProviderIndicatorString(treeObject);

				if (treeObject.getProperty() != null) {
					if (treeObject.getProperty().getType() != null) {
						tNameString = tNameString + treeObject.getProperty().getType().getName().replaceFirst("Modelica", "") + " ";
					}
				}
				cell.setText(tNameString + treeObject.toString() + arraySizeString + declarationString);
			}
			else {
				String tNameString = "";
				
				// add client or provider indicator
				tNameString = tNameString + getValueClientOrProviderIndicatorString(treeObject);
				
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
	
	private String getValueClientOrProviderIndicatorString(TreeObject treeObject) {
		String string = "";
		if (treeObject.isValueClient_required()) {
			string = string+ requiredClientIndicator+", ";
		}
		else if (treeObject.isValueClient()) {
			string = string+ clientIndicator+", ";
		}
		
		if (treeObject.isValueProvider()) {
			string = string + providerIndicator+", ";
		}
		return string;
	}
	
	/**
	 * Checks for array size.
	 *
	 * @param element the element
	 * @param stereotype the stereotype
	 * @return the boolean
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Boolean hasArraySize(TypedElement element, Stereotype stereotype){
		Object modList = element.getValue(stereotype, Constants.propertyName_arraySize);
//		EList<String> modList = (EList<String>) element.getValue(stereotype, Constants.propertyName_arraySize);
		if (modList != null && modList instanceof List && ((List)modList).size() > 0) {
			List<String> list = (List)modList;
			for (String string : list) {
				if (!string.trim().equals("")) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	/*
	 * Validation
	 */
//	public boolean hasErrors(TreeParent treeParent) {
//		if (hasMarkers(treeParent)) {
//			return true;
//		}
//		HashSet<TreeObject> list = new HashSet<TreeObject>();
//		list.addAll(findNextInvalidItem(treeParent, false));
//		if (list.size() > 0 ) {
////			for (TreeObject treeObject : list) {
////				System.err.println(treeObject.getName());
////			}
//			return true;
//		}
//		return false;
//	}
	
	
	
	
	
//	private HashSet<TreeObject> findNextInvalidItem(TreeParent treeParent, boolean parentIsInput){
//		HashSet<TreeObject> list = new HashSet<TreeObject>();
//		boolean parentIsAnInput = false;
//		
//		if (hasMarkers(treeParent)) {
//			list.add(treeParent);
//			
//			// stop searching here
//			return list;
//		}
//		
//		// if property has no type -> indicate error
////		if (treeParent.getUmlElement() instanceof Property && treeParent.getComponentType() == null) {
//		// TODO: this is a workaround. How to deal with stateSelect 
//		if (!treeParent.getName().equals("stateSelect") && treeParent.getUmlElement() instanceof Property && treeParent.getComponentType() == null) {
//			list.add(treeParent);
//			
//			// stop searching here
//			return list;
//		}
//		
//		Element umlElement = treeParent.getUmlElement();
//		
//		// if one of the parents is input
//		if ( parentIsInput ) {
//			if (umlElement != null ) {
//				// if it is a primitive type -> indicate an error
//				if ( ((Property)umlElement).getType() instanceof PrimitiveType 
//						&& (treeParent.getDeclaration() == null && treeParent.getFinalModificationRightHand() == null)) {
//					list.add(treeParent);
//				}
//			}
//		}
//		
//		// if property is input and has no declaration and no binding equation exists for it in its first level component modification
//		if (treeParent.isInput() && treeParent.getDeclaration() == null && treeParent.getFinalModificationRightHand() == null
//				&& !oneOfParentsIsPort(treeParent)) {
//			
//			if (umlElement != null ) {
//				
//				// check if the item is a component of a port that has causality input ...
//				if (umlElement instanceof Property  && !(umlElement instanceof Port) ) { 
//
//					// if it is a primitive type -> indicate an error
//					if ( ((Property)umlElement).getType() instanceof PrimitiveType) {
//						list.add(treeParent);
//						
//						// stop here
//						return list;
//					}
//					// not of primitive type -> remember that this parent was an input in order to check its children
//					else {
//
////						System.err.println(treeParent.getDotPath() + " parentIsInput: " + parentIsInput);
//						parentIsAnInput = true;
//					}
//				}
//			}
////			// stop here
////			return list;
//		}
//		
//		// if it is a required client and there is no binding equation (modification) for it -> error
//		/*
//		 * TODO: If the actual client is a sub-component of the item then an analysis of the subcomponents is required...  
//		 */
//		if (treeParent.isLeaf() && treeParent.isValueClient_required() && treeParent.getFinalModificationRightHand() == null) {
//			list.add(treeParent);
//		}
//
//		// go on with search ...
//		TreeObject[] children = treeParent.getChildren();
//		for (int i = 0; i < children.length; i++) {
//			if (children[i] instanceof TreeParent) {
//				list.addAll(findNextInvalidItem( (TreeParent)children[i] , parentIsAnInput));
//			}
//		}
//		return list;
//	}
	

	private boolean oneOfParentsIsPort(TreeObject treeObject){
		 ArrayList<TreeObject> parents = getAllParents(treeObject, new ArrayList<TreeObject>());
		 for (TreeObject parent : parents) {
			if (isPort(parent)) {
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<TreeObject> getAllParents(TreeObject item, ArrayList<TreeObject> listOfSegments){
		TreeObject parent = item.getParent();
		if (parent != null && !parent.isRoot()) { // the root node represents the instantiated class. It should not appear in the path.  
			listOfSegments.add(parent);
			listOfSegments.addAll( getAllParents(parent, new ArrayList<TreeObject>()) );
		}
		return listOfSegments;
	}
	
	private boolean isPort(TreeObject treeObject) {
		Element type = treeObject.getComponentType();
		if (type != null && type instanceof Classifier && type.getAppliedStereotype(Constants.stereotypeQName_Connector) != null) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	/*
	 * Decoration
	 */
	public Image decorateImage(Object element, String imagePath) {
		if (element instanceof TreeObject) {
			TreeObject treeObject = (TreeObject) element;
			
			/*
			 * Priority of decoration: error, warning, info.
			 */
			if (treeObject.hasErrors()) {
				return new DecorationOverlayIcon(SWTResourceManager.getImage(Activator.class, imagePath), errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
			}
			if (treeObject.hasWarnings()) {
				return new DecorationOverlayIcon(SWTResourceManager.getImage(Activator.class, imagePath), warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
			}
			if (treeObject.hasInfo()) {
				return new DecorationOverlayIcon(SWTResourceManager.getImage(Activator.class, imagePath), infoImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
			}
			
//			if (hasErrors((TreeParent)element)) {
//				return new DecorationOverlayIcon(SWTResourceManager.getImage(Activator.class, imagePath), errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();				
//			}
		}
		return SWTResourceManager.getImage(Activator.class, imagePath);
	}
	
	
	
	/*
	 * Markers
	 */
	
	
//	public boolean hasMarkers(TreeObject item){
//		
//		Element umlElement = item.getFirstLevelComponent();
//		
//		if (umlElement instanceof NamedElement) {
//			// markers
//			ExtendedUmlModel umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
//			if (umlModel != null) {
//				String projectName = umlModel.getResource().getURI().segment(1);
//				IWorkspace workspace = ResourcesPlugin.getWorkspace();
//				IWorkspaceRoot root = workspace.getRoot();
//				IProject iProject = root.getProject(projectName);
//				
//				IMarker[] markers = null;
//				try {
//					if (iProject != null) {
//						markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
//						for (IMarker marker : markers) {
//							Object sourceId = marker.getAttribute(IMarker.SOURCE_ID);
//								if (item.getDotPath().equals(sourceId)) {
//									return true;				
//							}
//						}
//					}
//				} catch (CoreException e) {
//					//e.printStackTrace();
//				}
//			}
//		}
//		return false;
//	}
	
	
	
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