package org.modelica.uml.sysml.diagram2.util;

import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorPlugin;


/**
 * @generated
 */
public class ModelElementsTreeContentProvider implements
		ITreeContentProvider {

	/**
	 * @generated
	 */
	private ITreeContentProvider myWorkbenchContentProvider = new WorkbenchContentProvider();

	/**
	 * @generated
	 */
	private AdapterFactoryContentProvider myAdapterFctoryContentProvier = new AdapterFactoryContentProvider(
			SysmlDiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());
	
	private TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
	.createEditingDomain();

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if(!hasChildren(parentElement))
			return new Object[0];
		
		Object[] result = myWorkbenchContentProvider
				.getChildren(parentElement);
		if (result != null && result.length > 0) {
			return result;
		}
		if (parentElement instanceof IFile) {
			IFile modelFile = (IFile) parentElement;
			IPath resourcePath = modelFile.getFullPath();
			ResourceSet resourceSet = myEditingDomain.getResourceSet();
			try {
				Resource modelResource = resourceSet.getResource(URI
						.createPlatformResourceURI(resourcePath.toString(),
								true), true);
				return myAdapterFctoryContentProvier
						.getChildren(modelResource);
			} catch (WrappedException e) {
				SysmlDiagramEditorPlugin
						.getInstance()
						.logError(
								"Unable to load resource: " + resourcePath.toString(), e); //$NON-NLS-1$
			}
			return Collections.EMPTY_LIST.toArray();
		}
		return myAdapterFctoryContentProvier.getChildren(parentElement);
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		Object parent = myWorkbenchContentProvider.getParent(element);
		if (parent != null) {
			return parent;
		}
		if (element instanceof EObject) {
			EObject eObject = (EObject) element;
			if (eObject.eContainer() == null
					&& eObject.eResource().getURI().isFile()) {
				String path = eObject.eResource().getURI().path();
				return ResourcesPlugin.getWorkspace().getRoot()
						.getFileForLocation(new Path(path));
			}
			return myAdapterFctoryContentProvier.getParent(eObject);
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof IFile) {
			return isValidModelFile((IFile) element);
		}
		return myWorkbenchContentProvider.hasChildren(element)
				|| myAdapterFctoryContentProvier.hasChildren(element);
	}

	/**
	 * @generated
	 */
	private boolean isValidModelFile(IFile file) {
		String fileExtension = file.getFullPath().getFileExtension();
		return "sysml".equals(fileExtension); //$NON-NLS-1$
	}
	
	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		Object[] elements = myWorkbenchContentProvider
				.getElements(inputElement);
		return elements;
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkbenchContentProvider.dispose();
		myAdapterFctoryContentProvier.dispose();
		myEditingDomain.dispose();
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myWorkbenchContentProvider.inputChanged(viewer, oldInput, newInput);
		myAdapterFctoryContentProvier.inputChanged(viewer, oldInput,
				newInput);
	}

}