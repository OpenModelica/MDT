package org.openmodelica.modelicaml.view.valuebindings.display;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.view.valuebindings.constants.Constants;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.utls.SWTResourceManager;


public class ViewLabelProvider extends LabelProvider {
	
	public String getText(Object obj) {
		
		if ( obj instanceof TreeObject && ((TreeObject)obj).getUmlElement() != null  ){
			Element umlElement = ((TreeObject)obj).getUmlElement();
			
			if (umlElement instanceof Property) {
				Type pType = ((Property)umlElement).getType();
				String pTypeString = "not defined";
				if ( pType != null ) {
					pTypeString = pType.getName();
				}	
				
				if (((TreeObject)obj).isValueMediator() ) {
					return ((Property)umlElement).getName() + "   ~   type (" + pTypeString + "), clients (" + String.valueOf(getNumberOfClients(obj)) + "), providers (" + String.valueOf(getNumberOfProviders(obj)) + ") '" ;
				}
				if ((((TreeObject)obj).isValueClient() || ((TreeObject)obj).isValueProvider())) {
					return ((Property)umlElement).getName() + "    ~   type (" + pTypeString + ") owner (" + ( (NamedElement)((Property)umlElement).getOwner()).getQualifiedName() + ")";
				}
			}
		}		
		return obj.toString();
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
	
	public Image getImage(Object obj) {
		if (obj instanceof TreeObject  ){
			if (((TreeObject)obj).isValueMediatorContainer()) {
				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/list.png");
			}
			if (((TreeObject)obj).isValueMediator()) {
				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/connect.png");
			}
			
			if (((TreeObject)obj).isValueClient() || ((TreeObject)obj).isValueProvider()) {
//				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/variable.png");
				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Property.gif");	
			}
			
			if ( ((TreeObject)obj).getName().equals(Constants.valueClientsTitleName) ) {
				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueClient.png");
			}
			if ( ((TreeObject)obj).getName().equals(Constants.valueProvidersTitleName) ) {
				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/addValueProviders.png");
			}
			if ( obj instanceof TreeParent && ((TreeParent)obj).getUmlElement() instanceof Model) {
				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Model.gif");
			}
			if ( obj instanceof TreeParent && ((TreeParent)obj).getUmlElement() instanceof Package) {
				return SWTResourceManager.getImage(ViewLabelProvider.class, "/icons/Package.gif");
			}

			
		}

		// default
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		imageKey = ISharedImages.IMG_OBJ_FOLDER;
		return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	}
}
