package org.openmodelica.modelicaml.view.valuebindings.utls;

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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.view.valuebindings.Activator;

public class ViewLabelProviderStyledCell extends StyledCellLabelProvider {

	private final ImageDescriptor warningImageDescriptor =PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	
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
		cell.setText(obj.toString()); 
		
		// set text styled, override the default
		if ( ((TreeObject)obj).getName().equals(Constants.valueClientsTitleName) || ((TreeObject)obj).getName().equals(Constants.valueProvidersTitleName) ) {
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
			
			// set text and styles
			cell.setText( styledString.toString() );
			cell.setStyleRanges(styledString.getStyleRanges());
		}
		
		else if ( obj instanceof TreeObject && ((TreeObject)obj).getUmlElement() != null  ){
			Element umlElement = ((TreeObject)obj).getUmlElement();
			
			if (  umlElement instanceof NamedElement && (((TreeObject)obj).isPackage() || ((TreeObject)obj).isValueMediatorContainer() ) ) {
				
				// name string 
				String name = ((NamedElement)umlElement).getName() + " ";
				
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
				String name = ((Property)umlElement).getName() + "   -   ";

				// type string
				Type pType = ((Property)umlElement).getType();
				String pTypeString = "type (NOT DEFINED)";
				if ( pType != null ) {
					pTypeString =  "type (" + pType.getName().replaceFirst("Modelica", "") + ")";
				}	
				
				if (((TreeObject)obj).isValueMediator() ) {
					
					// numbers of clients and providers
					int numberOfClients = getNumberOfClients(obj);
					int numberOfProviders = getNumberOfProviders(obj);
					
					// string for clients and providers
					String clientsString = "clients (" + String.valueOf(numberOfClients) + ")";
					String providersString = "providers (" + String.valueOf(numberOfProviders) + ")";

					//  construct the label
					StyledString styledString = new StyledString();
					styledString.append(name);
					styledString.append(pTypeString, stylerGrey);
					
					styledString.append(", ");
					
					if (numberOfClients == 0) {styledString.append(clientsString, stylerRed); }
					else { styledString.append(clientsString); }

					styledString.append(", ");

					if (numberOfProviders == 0) {styledString.append(providersString, stylerRed); }
					else { styledString.append(providersString); }
					
					// set text and styles
					cell.setText( styledString.toString() );
					cell.setStyleRanges(styledString.getStyleRanges());
					
				}
				if ((((TreeObject)obj).isValueClient() || ((TreeObject)obj).isValueProvider())) {
					
					String ownerString = "owner (" + ( (NamedElement)((Property)umlElement).getOwner()).getQualifiedName() + ")";

					//  construct the label
					StyledString styledString = new StyledString();
					styledString.append(name);
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
			if ( obj instanceof TreeParent && ((TreeParent)obj).getUmlElement() instanceof Model) {
//				cell.setImage(decorateImage( obj, "/icons/Model.gif" ));
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Model.gif"));
			}
			else if ( obj instanceof TreeParent && ((TreeParent)obj).getUmlElement() instanceof Package) {
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Package.gif"));
			}
			else if (((TreeObject)obj).isValueMediatorContainer()) {
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/valueMediatorsContainer.png"));
			}
			else if (((TreeObject)obj).isValueMediator()) {
//				// numbers of clients and providers
//				int numberOfClients = getNumberOfClients(obj);
//				int numberOfProviders = getNumberOfProviders(obj);
//				if (numberOfClients == 0 || numberOfProviders == 0) {
//					cell.setImage(decorateImage( obj, "/icons/valueMediator.png" ));
//				}
//				else {
//					cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/valueMediator.png"));
//				}
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/valueMediator.png"));
			}
			
			else if (((TreeObject)obj).isValueClient() || ((TreeObject)obj).isValueProvider()) {
//				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/variable.png"));
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Property.gif"));	
			}
			else if ( ((TreeObject)obj).getName().equals(Constants.valueClientsTitleName) ) {
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueClient.png"));
			}
			else if ( ((TreeObject)obj).getName().equals(Constants.valueProvidersTitleName) ) {
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueProviders.png"));
			}
			else {
				cell.setImage(SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/reload.png"));
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
	
	
	
	
	private int getNumberOfClients(Object item){
		if (item instanceof TreeParent) {
			TreeObject[] chidren = ((TreeParent)item).getChildren();
			for (int i = 0; i < chidren.length; i++) {
				if (chidren[i].getName().equals(Constants.valueClientsTitleName)) {
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
				if (chidren[i].getName().equals(Constants.valueProvidersTitleName)) {
					if (chidren[i] instanceof TreeParent) {
						return ((TreeParent)chidren[i]).getChildren().length;
					}
				}
			}
		}
		return 0;
	}




	
	public Image decorateImage(Object element, String imagePath) {
		return new DecorationOverlayIcon(SWTResourceManager.getImage(Activator.class, imagePath), warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
}
