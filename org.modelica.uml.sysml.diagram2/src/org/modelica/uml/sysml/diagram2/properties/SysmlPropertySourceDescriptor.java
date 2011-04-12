package org.modelica.uml.sysml.diagram2.properties;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.gmf.runtime.emf.ui.properties.descriptors.EMFCompositeSourcePropertyDescriptor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorPlugin;
import org.modelica.uml.sysml.diagram2.part.SysmlElementChooserDialog;

public class SysmlPropertySourceDescriptor extends EMFCompositeSourcePropertyDescriptor{
	
	private ModelEditPart myModel;

	/**
     * @param object
     * @param itemPropertyDescriptor
     */
    public SysmlPropertySourceDescriptor(Object object,
            IItemPropertyDescriptor itemPropertyDescriptor) {
        super(object, itemPropertyDescriptor);

        filterFlags = itemPropertyDescriptor.getFilterFlags(object);
        filterFlags = filterFlags == null ? new String[0] : filterFlags;
    }
    

    /**
     * Creates an instance of <code>EMFCompositeSourcePropertyDescriptor</code>.
     * 
     * @param object
     * @param itemPropertyDescriptor
     * @param category 
     */
    public SysmlPropertySourceDescriptor(Object object,
            IItemPropertyDescriptor itemPropertyDescriptor, String category) {
        super(object, itemPropertyDescriptor, category);    
    }
    
    /*
     * A cell editor creation method - after the assertions. Allows subclasses
     * override just the part where the editor actually is created, without
     * having to repeat preliminary assertions in every subclass
     * 
     * @param composite @return
     */
    protected CellEditor doCreateEditor(Composite composite) {
    	CellEditor result = null;
        Object genericFeature = getFeature();
        if (false)
        if(genericFeature == UMLPackage.eINSTANCE.getTypedElement_Type()) {
        	return new ExtendedDialogCellEditor(composite, getEditLabelProvider()) {
    			protected Object openDialogBox(Control cellEditorWindow) {
    				final View view = (View) myModel.getModel();
    				SysmlElementChooserDialog elementChooser = new SysmlElementChooserDialog(cellEditorWindow.getShell(), view);
    				int result = elementChooser.open();
    				if (result != Window.OK) {
    					return null;
    				}
    				URI selectedModelElementURI = elementChooser
    						.getSelectedModelElementURI();
    				final EObject selectedElement;
    				try {
    					selectedElement = myModel.getEditingDomain()
    							.getResourceSet().getEObject(selectedModelElementURI, true);
    				} catch (WrappedException e) {
    					SysmlDiagramEditorPlugin
    							.getInstance()
    							.logError(
    									"Exception while loading object: " + selectedModelElementURI.toString(), e); //$NON-NLS-1$
    					return null;
    				}

    				return selectedElement;
    			}
        	};
        }
    	return super.doCreateEditor(composite);
    }

    @Override
    public List getChoiceOfValues() {
    	extendResourceSet(getObject());
    	return super.getChoiceOfValues();
    }
    
    private void extendResourceSet(Object obj) {

		ResourceSetImpl resourceSetImpl = new ResourceSetImpl();
		IProject[] projects =ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int i = 0;i < projects.length;i++) {
			IResource[] projMembers = new IResource[0];
			try {
				projMembers = projects[i].members();
			} catch(Exception ex) {
			}

			for (int j = 0; j < projMembers.length; j++) {
				if(projMembers[j] instanceof IFile) {
					if(((IFile)projMembers[j]).getFileExtension().equals("mci")) {
						URI puri = URI.createURI("platform:/resource/"+projects[i].getName()+"/"+projMembers[j].getName());
						boolean resourceExists = false;
						resourceSetImpl.createResource(puri);

						if (obj instanceof EObject) {
							EObject new_name = (EObject) obj;
							Iterator iter = new_name.eResource().getResourceSet().getResources().iterator();
							while(iter.hasNext()) {
								Object setResource = iter.next();
								if (setResource instanceof Resource) {
									Resource iterResource = (Resource) setResource;
									if(iterResource.getURI().path().equals(puri.path()))
										resourceExists = true;

								}
							}
							if(!resourceExists)
								try {
									Resource res = new_name.eResource().getResourceSet().createResource(puri);
									res.load(new_name.eResource().getResourceSet().getLoadOptions());
								} catch (Exception e) {
									// TODO: handle exception
								}
						}
					}
				}
			}
		}


    }

	public ModelEditPart getMyModel() {
		return myModel;
	}


	public void setMyModel(ModelEditPart myModel) {
		this.myModel = myModel;
	}
}
