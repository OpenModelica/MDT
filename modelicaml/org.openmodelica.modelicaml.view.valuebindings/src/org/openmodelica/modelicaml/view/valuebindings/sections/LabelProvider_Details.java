package org.openmodelica.modelicaml.view.valuebindings.sections;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.Activator;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;


public class LabelProvider_Details implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage(Object element) {
		Assert.isTrue(element instanceof IStructuredSelection);
	    Object input = ((IStructuredSelection) element).getFirstElement();
	    Assert.isTrue(input instanceof TreeObject);
	    TreeObject item = (TreeObject) input;
	    if (item != null) {
	    	 if (item.isModel()) {
				return SWTResourceManager.getImage(Activator.class, "/icons/Model.gif");
			}
	    	if (item.isPackage()) {
				return SWTResourceManager.getImage(Activator.class, "/icons/Package.gif");
			}
		    else if (item.isValueMediatorContainer()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/valueMediatorsContainer.png");
			}
		    else if (item.isValueMediator() || item.isValueMediatorsNode()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/valueMediator.png");
			}
		    else if (item.isValueClientsNode()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/addValueClient.png");
			}
		    else if (item.isValueProvidersNode()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/addValueProviders.png");
			}
		    else if (item.isValueClient() || item.isValueProvider()) {
		    	return SWTResourceManager.getImage(Activator.class, "/icons/Property.gif");
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		Assert.isTrue(element instanceof IStructuredSelection);
	    Object input = ((IStructuredSelection) element).getFirstElement();
	    Assert.isTrue(input instanceof TreeObject);
	    TreeObject item = (TreeObject) input;
	    if (item != null) {
	    	if (item.isValueClient()) {
	    		return ((TreeObject)item).getName() + " (Value Client)";
			}
	    	if (item.isValueMediator()) {
	    		return ((TreeObject)item).getName() + " (Value Mediator)";
			}
	    	if (item.isValueProvider()) {
	    		return ((TreeObject)item).getName() + " (Value Provider)";
			}
		    return ((TreeObject)item).getName();
		}
	    return " ";
	}

}
