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
package org.openmodelica.modelicaml.modelica.importer.display;

import java.util.HashSet;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.modelica.importer.Activator;
import org.openmodelica.modelicaml.modelica.importer.model.ClassItem;
import org.openmodelica.modelicaml.modelica.importer.model.ComponentItem;
import org.openmodelica.modelicaml.modelica.importer.model.ExtendsRelationItem;
import org.openmodelica.modelicaml.modelica.importer.model.ImportRelationItem;
import org.openmodelica.modelicaml.modelica.importer.model.TreeObject;
import org.openmodelica.modelicaml.modelica.importer.model.TreeParent;


public class ViewLabelProviderStyledCell extends StyledCellLabelProvider {
	
	private String projectName = "";
	
	private boolean decorateItem = false;
	
	private final ImageDescriptor warningImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	private final ImageDescriptor errorImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	private final ImageDescriptor syncIncomingImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/overlay-synch-incoming.gif");
//	private final ImageDescriptor infoImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	
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
	
//	@Override
//	public void dispose() {
//		if (overlayIcon != null && overlayIconImage != null) {
//			overlayIcon.destroyResource(overlayIconImage);
//		}
//		super.dispose();
//	}
//	
	
	@Override
	public void update(ViewerCell cell) {
		String treeItemText = "";
		Object obj = cell.getElement();
		
		TreeObject item = (TreeObject)obj;
		
		// name string 
		String name = item.getName() + " ";
		
		// number of children string 
		String numberOfChildren = "";
		if (obj instanceof TreeParent) {
			numberOfChildren = "(" + ((TreeParent)obj).getChildren().length + ")";
		}
		
		
		//  construct the label
		styledString = new StyledString();
		styledString.append(name);
		treeItemText = treeItemText + name;

		// check if the type is resolved 
		if (item instanceof ComponentItem && !((ComponentItem)item).isEnumarationLiteral() ) {
			ComponentItem component = ((ComponentItem)item);
			if (component.getComponentTypeProxy() == null) {
				styledString.append(": " + component.getComponentTypeQame() + " ? type proxy not found", stylerRed);
				treeItemText = treeItemText + ": " + component.getComponentTypeQame() + " ? type proxy not found";
			}
			else {
				styledString.append(": " + component.getComponentTypeQame());
				treeItemText = treeItemText + ": " + component.getComponentTypeQame();
			}
		}
		
		// check if the extends relation has source and target proxy
		else if (item instanceof ExtendsRelationItem) {
			Element target  = ((ExtendsRelationItem)item).getTarget();
			
			if (target == null) {
				styledString.append("? target proxy not found", stylerRed);
				treeItemText = treeItemText + "? target proxy not found";
			}
		}
		else{
			styledString.append(numberOfChildren, stylerGrey);
			treeItemText = treeItemText + numberOfChildren;
			
			// for the root (folder) add the project name
			if (obj instanceof TreeParent && ((TreeParent)obj).getName().equals(Constants.folderName_code_sync) ) {
				styledString.append(" - " + getProjectName(), stylerGrey);
				treeItemText = treeItemText + " - " + getProjectName();
			}
			
		}
		
		// set text and styles
		
		/*
		 * TODO: fix the issue below. 
		 * For large models, e.g. Modelica Standard Library SWT gives up with "No more handles".
		 * Workaround: the user can enable or disable the decorate items option. 
		 * 	When disabled normal string is used.
		 */
		
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
			
//			if ( treeObject.getName().equals(Constants.folderName_code_sync)) {
//				cell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER));
//			}
//			
//			// components
//			else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isComponent()) {
//				cell.setImage(decorateImage( treeObject , "/icons/component.png", null ));
//			}
//
//			else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isPort()) {
//				cell.setImage(decorateImage( treeObject , "/icons/port.gif", null ));
//			}
//			else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isVariable()) {
//				cell.setImage(decorateImage( treeObject , "/icons/variable.png", null ));
//			}
//			else if ( treeObject instanceof ComponentItem 
//					&& ((ComponentItem)treeObject).isFunctionArgument() 
//					&& ((ComponentItem)treeObject).getCausality().equals("input")) {
//				cell.setImage(decorateImage( treeObject , "/icons/Parameter_in.gif", null ));
//			}
//			else if ( treeObject instanceof ComponentItem 
//					&& ((ComponentItem)treeObject).isFunctionArgument() 
//					&& ((ComponentItem)treeObject).getCausality().equals("output")) {
//				cell.setImage(decorateImage( treeObject , "/icons/Parameter_return.gif", null ));
//			}
//			else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isFunctionArgument()) {
//				cell.setImage(decorateImage( treeObject , "/icons/Parameter.gif", null ));
//			}
//			
//			//classes 
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("package")) {
//				cell.setImage(decorateImage( treeObject , "/icons/Package.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("class")) {
//				cell.setImage(decorateImage( treeObject , "/icons/modelicaClass.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("model")) {
//				cell.setImage(decorateImage( treeObject , "/icons/model.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("block")) {
//				cell.setImage(decorateImage( treeObject , "/icons/block.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().contains("connector")) {
//				cell.setImage(decorateImage( treeObject , "/icons/connector.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("record")) {
//				cell.setImage(decorateImage( treeObject , "/icons/record.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("type")) {
//				cell.setImage(decorateImage( treeObject , "/icons/type.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("function")) {
//				cell.setImage(decorateImage( treeObject , "/icons/FunctionBehavior.gif", null ));
//			}
//			else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().contains("operator")) {
//				cell.setImage(decorateImage( treeObject , "/icons/operator.gif", null ));
//			}
//			else if ( treeObject instanceof ExtendsRelationItem ) {
//				cell.setImage(decorateImage( treeObject , "/icons/Generalization.gif", null ));
//			}
//			else {
////				cell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/reload.png"));
//				cell.setImage(decorateImage( treeObject , "/icons/reload.png", null ));
//			}
//		}
//		else {
//			// default
//			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
////			String imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			cell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(imageKey));
		}

		super.update(cell);
	}
	
	// ################################################### VALIDATION
	
	
	
	// ################################################### VALIDATION END

	
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
		
		//root node - the code-sync folder
		if ( treeObject.getName().equals(Constants.folderName_code_sync)) {
//			overlayIconImage = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
			overlayIconImage = ResourceManager.decorateImage(
					PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER), 
					ResourceManager.getImage(Activator.class, "/icons/sync_ovr.gif"));
		}
		
		// components
		else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isComponent()) {
			overlayIconImage = decorateImage( treeObject , "/icons/component.png", null );
		}

		else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isPort()) {
			overlayIconImage = decorateImage( treeObject , "/icons/port.gif", null );
		}
		else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isVariable()) {
			overlayIconImage = decorateImage( treeObject , "/icons/variable.png", null );
		}
		else if ( treeObject instanceof ComponentItem 
				&& ((ComponentItem)treeObject).isFunctionArgument() 
				&& ((ComponentItem)treeObject).getCausality().equals("input")) {
			overlayIconImage = decorateImage( treeObject , "/icons/Parameter_in.gif", null );
		}
		else if ( treeObject instanceof ComponentItem 
				&& ((ComponentItem)treeObject).isFunctionArgument() 
				&& ((ComponentItem)treeObject).getCausality().equals("output")) {
			overlayIconImage = decorateImage( treeObject , "/icons/Parameter_return.gif", null );
		}
		else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isFunctionArgument()) {
			overlayIconImage = decorateImage( treeObject , "/icons/Parameter.gif", null );
		}
		else if ( treeObject instanceof ComponentItem && ((ComponentItem)treeObject).isEnumarationLiteral()) {
//			overlayIconImage = decorateImage( treeObject , "/icons/EnumerationLiteral.gif", null );
			overlayIconImage = ResourceManager.getPluginImage("org.eclipse.uml2.uml.edit", "/icons/full/obj16/EnumerationLiteral.gif");

		}

		
		//classes
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).isEnumeration()) {
			overlayIconImage = decorateImage( treeObject , "/icons/Package.gif", ResourceManager.getPluginImage("org.eclipse.uml2.uml.edit", "/icons/full/obj16/Enumeration.gif") );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("package")) {
			overlayIconImage = decorateImage( treeObject , "/icons/Package.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("class")) {
			overlayIconImage = decorateImage( treeObject , "/icons/modelicaClass.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("model")) {
			overlayIconImage = decorateImage( treeObject , "/icons/model.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("block")) {
			overlayIconImage = decorateImage( treeObject , "/icons/block.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().contains("connector")) {
			overlayIconImage = decorateImage( treeObject , "/icons/connector.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("record")) {
			overlayIconImage = decorateImage( treeObject , "/icons/record.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("type")) {
			overlayIconImage = decorateImage( treeObject , "/icons/type.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("function")) {
			overlayIconImage = decorateImage( treeObject , "/icons/FunctionBehavior.gif", null );
		}
		else if ( treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().contains("operator")) {
			overlayIconImage = decorateImage( treeObject , "/icons/operator.gif", null );
		}
		else if ( treeObject instanceof ExtendsRelationItem ) {
//			overlayIconImage = decorateImage( treeObject , "/icons/Generalization.gif", null );
			overlayIconImage = ResourceManager.getPluginImage("org.eclipse.uml2.uml.edit", "/icons/full/obj16/Generalization.gif");
		}
		else if ( treeObject instanceof ImportRelationItem ) {
//			overlayIconImage = decorateImage( treeObject , "/icons/Dependency.gif", null );
			overlayIconImage = ResourceManager.getPluginImage("org.eclipse.uml2.uml.edit", "/icons/full/obj16/ElementImport.gif");
		}
		else {
//			cell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/reload.png"));
//			overlayIconImage = decorateImage( treeObject , "/icons/reload.png", null );
			overlayIconImage = decorateImage( treeObject , "/icons/unknown_obj.gif", null );
		}
	}
	
//	private Image decorateImage(Object element, String imagePath, Image image) {
//
//		if (element instanceof TreeParent) {
//			Image imageToBeUsed = null;
//			if (image != null) {
//				imageToBeUsed = image;
//			}
//			else {
//				imageToBeUsed = SWTResourceManager.getImage(Activator.class, imagePath);	
//			}
//			
//			
//			// TODO: possible an issue :
//			/*
//			 * org.eclipse.swt.SWTError: No more handles
//				at org.eclipse.swt.SWT.error(SWT.java:4109)
//				at org.eclipse.swt.SWT.error(SWT.java:3998)
//				at org.eclipse.swt.SWT.error(SWT.java:3969)
//				at org.eclipse.swt.widgets.Display.create32bitDIB(Display.java:1005)
//				at org.eclipse.swt.widgets.Display.createIcon(Display.java:1088)
//				at org.eclipse.swt.widgets.Decorations.setImages(Decorations.java:998)
//				at org.eclipse.swt.widgets.Decorations.setImages(Decorations.java:1066)
//				at org.eclipse.jface.window.Window.configureShell(Window.java:373)
//				at org.eclipse.ui.internal.statushandlers.InternalDialog.configureShell(InternalDialog.java:198)
//				at org.eclipse.jface.window.Window.createShell(Window.java:502)
//				at org.eclipse.jface.window.Window.create(Window.java:430)
//				at org.eclipse.jface.dialogs.Dialog.create(Dialog.java:1089)
//				at org.eclipse.ui.internal.statushandlers.WorkbenchStatusDialogManagerImpl.doAddStatusAdapter(WorkbenchStatusDialogManagerImpl.java:260)
//				at org.eclipse.ui.internal.statushandlers.WorkbenchStatusDialogManagerImpl.addStatusAdapter(WorkbenchStatusDialogManagerImpl.java:197)
//				at org.eclipse.ui.statushandlers.WorkbenchStatusDialogManager.addStatusAdapter(WorkbenchStatusDialogManager.java:156)
//				at org.eclipse.ui.statushandlers.WorkbenchErrorHandler.showStatusAdapter(WorkbenchErrorHandler.java:101)
//				at org.eclipse.ui.statushandlers.WorkbenchErrorHandler.handle(WorkbenchErrorHandler.java:57)
//				at org.eclipse.ui.internal.ide.IDEWorkbenchErrorHandler.handle(IDEWorkbenchErrorHandler.java:106)
//				at org.eclipse.ui.internal.WorkbenchErrorHandlerProxy.handle(WorkbenchErrorHandlerProxy.java:36)
//				at org.eclipse.ui.statushandlers.StatusManager.handle(StatusManager.java:189)
//				at org.eclipse.ui.statushandlers.StatusManager.handle(StatusManager.java:231)
//				at org.eclipse.ui.statushandlers.StatusManager.handle(StatusManager.java:242)
//				at org.eclipse.ui.application.WorkbenchAdvisor.eventLoopException(WorkbenchAdvisor.java:326)
//				at org.eclipse.ui.internal.ExceptionHandler.handleException(ExceptionHandler.java:65)
//				at org.eclipse.ui.internal.Workbench.runEventLoop(Workbench.java:2644)
//				at org.eclipse.ui.internal.Workbench.runUI(Workbench.java:2604)
//				at org.eclipse.ui.internal.Workbench.access$4(Workbench.java:2438)
//				at org.eclipse.ui.internal.Workbench$7.run(Workbench.java:671)
//				at org.eclipse.core.databinding.observable.Realm.runWithDefault(Realm.java:332)
//				at org.eclipse.ui.internal.Workbench.createAndRunWorkbench(Workbench.java:664)
//				at org.eclipse.ui.PlatformUI.createAndRunWorkbench(PlatformUI.java:149)
//				at org.eclipse.ui.internal.ide.application.IDEApplication.start(IDEApplication.java:115)
//				at org.eclipse.equinox.internal.app.EclipseAppHandle.run(EclipseAppHandle.java:196)
//				at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.runApplication(EclipseAppLauncher.java:110)
//				at org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher.start(EclipseAppLauncher.java:79)
//				at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:369)
//				at org.eclipse.core.runtime.adaptor.EclipseStarter.run(EclipseStarter.java:179)
//				at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//				at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
//				at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
//				at java.lang.reflect.Method.invoke(Unknown Source)
//				at org.eclipse.equinox.launcher.Main.invokeFramework(Main.java:620)
//				at org.eclipse.equinox.launcher.Main.basicRun(Main.java:575)
//				at org.eclipse.equinox.launcher.Main.run(Main.java:1408)
//			 */
//			
//			if ( imageToBeUsed != null) {
//				// errors
//				if (hasMarkers((TreeObject) element)) {
//					return new DecorationOverlayIcon(imageToBeUsed, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
//				}
//				// synch status
//				if (hasEmptyProxies((TreeParent) element)) {
//					return new DecorationOverlayIcon(imageToBeUsed, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
//				}
//			}
//		}
//		
//		return SWTResourceManager.getImage(Activator.class, imagePath);
//	}
	
	
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
				
				/*
				 * TODO: overlay icons break SWT when large models are imported (e.g. MSL)
				 * Solve the problem with org.eclipse.swt.SWTError: No more handles ...
				 * maybe it is because of StyledText and not images ...
				 * workaround for the time being: decorateItem option.
				 */
	
				if (decorateItem) { // only if this decorate option is enabled
					// errors
					if (hasMarkersInItsComponents((TreeParent) element)) {
						overlayIcon = new DecorationOverlayIcon(imageToBeUsed, errorImageDescriptor, IDecoration.BOTTOM_RIGHT);
						return overlayIcon.createImage();
					}
					
					// sync status
					if ( (element instanceof ClassItem || element instanceof ComponentItem) 
							&& ((TreeParent)element).getModelicaMLProxy() == null ) {
//						overlayIcon = new DecorationOverlayIcon(imageToBeUsed, warningImageDescriptor, IDecoration.BOTTOM_RIGHT);
						overlayIcon = new DecorationOverlayIcon(imageToBeUsed, syncIncomingImageDescriptor, IDecoration.BOTTOM_RIGHT);
						return overlayIcon.createImage();
					}
					else if ((element instanceof ClassItem || isTreeRoot((TreeParent) element)) 
							&& hasEmptyProxies((TreeParent) element)) {
						overlayIcon = new DecorationOverlayIcon(imageToBeUsed, warningImageDescriptor, IDecoration.BOTTOM_RIGHT);
//						overlayIcon = new DecorationOverlayIcon(imageToBeUsed, syncIncomingImageDescriptor, IDecoration.BOTTOM_RIGHT);
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
	
	
	private boolean isTreeRoot(TreeParent treeParent){
		if (treeParent.getParent() == null) {
			return true;
		}
		if (treeParent.getName().equals(Constants.folderName_code_sync)) {
			return true;
		}
		return false;
	}
	
	private boolean hasEmptyProxies(TreeParent treeParent) {
		if (findNextEmptyProxy(treeParent).size() > 0 ) {
			return true;
		}
		return false;
	}
	
	
	private HashSet<TreeObject> findNextEmptyProxy(TreeParent treeParent){
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		
		// check the item itself
		if ( !(treeParent instanceof ExtendsRelationItem) && treeParent.getModelicaMLProxy() == null) {
			list.add(treeParent);
			return list;
		}
		else if (treeParent instanceof ExtendsRelationItem && ((ExtendsRelationItem)treeParent).getTarget() == null ) {
			list.add(treeParent);
			return list;
		}
		
		// check its children
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (!(children[i] instanceof ExtendsRelationItem || children[i] instanceof ImportRelationItem ) && children[i].getModelicaMLProxy() == null) {
				list.add(treeParent);
				return list;
			}
			// recursive call
			else if (children[i] instanceof ClassItem ) {
				list.addAll(findNextEmptyProxy( (TreeParent)children[i] ));	
			}
		}
		
		return list;
	}
	
	public boolean hasMarkersInItsComponents(TreeParent treeParent){
		if (findSubComponentMarkers(treeParent).size() > 0 ) {
			return true;
		}
		return false;
	}
	
	public HashSet<TreeObject> findSubComponentMarkers(TreeObject item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		
		// check the item itself
		if ( hasMarkers(item)) {
			list.add(item);
			return list;
		}

//		//check if component has type that exists in the loaded models
//		// exclude the primitive (predefined) types from this check.
//		if (item instanceof ComponentItem) {
//			if (!((ComponentItem)item).isPrimitive() && ((ComponentItem)item).getComponentTypeTreeItem() == null) {
//				list.add(item);
//			}
//		}
		
		// check its children
		if (item instanceof TreeParent) {
			TreeObject[] children = ((TreeParent) item).getChildren();
			for (int i = 0; i < children.length; i++) {
				if (hasMarkers(children[i])) {
					list.add(children[i]);
					return list;
				}
				// recursive call
				else {
					list.addAll(findSubComponentMarkers( (TreeParent)children[i] ));	
				}
			}
		}
		
		return list;
	}
	
	public boolean hasMarkers(TreeObject item){
		HashSet<String> markerTypes = new HashSet<String>(); 
		markerTypes.add(Constants.MARKERTYPE_MODELICA_MODELS_LOADING);
		markerTypes.add(Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES);
		
		// markers
		ExtendedUmlModel umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
		if (umlModel != null && umlModel.getResource() != null) {
			String projectName = umlModel.getResource().getURI().segment(1);
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject iProject = root.getProject(projectName);
			
			IMarker[] markers = null;
			try {
				if (iProject != null) {
					for (String markerType : markerTypes) {
						markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
						for (IMarker marker : markers) {
							Object qualifiedName = marker.getAttribute(IMarker.LOCATION);
							if (qualifiedName != null) {
								if ( qualifiedName.equals(item.getQName()) ) {
									return true;				
								}
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
