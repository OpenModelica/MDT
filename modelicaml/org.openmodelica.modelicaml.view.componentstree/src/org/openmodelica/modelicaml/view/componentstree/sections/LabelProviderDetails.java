package org.openmodelica.modelicaml.view.componentstree.sections;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.componentstree.Activator;


public class LabelProviderDetails implements ILabelProvider {

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
	    	if (item.isRoot()) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Class.gif");
			}
	    	if (item.getUmlElement() instanceof Port) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Port.gif");
			}
	    	if (item.getUmlElement() instanceof Property) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Property.gif");
			}
	    	if (item.getUmlElement() instanceof Signal) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Signal.gif");
			}
	    	if (item.getUmlElement() instanceof StateMachine) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/StateMachine.gif");
			}
	    	if (item.getUmlElement() instanceof Region) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/Region.gif");
			}
	    	if (item.getUmlElement() instanceof State) {
	    		return SWTResourceManager.getImage(Activator.class, "/icons/State.gif");
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
	    	if (((TreeObject)item).getDotPath().trim().equals("")) {
	    		return ((TreeObject)item).getName();
	    	}
		    return ((TreeObject)item).getDotPath();
		}
	    return " ";
		// TODO Auto-generated method stub
//		return "Component details";
	}

}
