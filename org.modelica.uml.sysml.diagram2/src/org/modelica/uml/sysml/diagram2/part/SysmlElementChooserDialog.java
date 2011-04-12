package org.modelica.uml.sysml.diagram2.part;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.modelica.uml.sysml.diagram2.util.ModelElementsTreeContentProvider;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * @generated
 */
public class SysmlElementChooserDialog extends Dialog {

	/**
	 * @generated
	 */
	private TreeViewer myTreeViewer;
	
	private ITreeContentProvider myContentProvider;

	/**
	 * @generated
	 */
	private URI mySelectedModelElementURI;

	private Object mySelectedElement;
	
	private boolean asModelObject = true;
	
	/**
	 * @generated
	 */
	private View myView;

	private ViewerFilter myFilter = new ModelFilesFilter();
	
	/**
	 * @generated
	 */
	private TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
			.createEditingDomain();

	/**
	 * @generated
	 */
	public SysmlElementChooserDialog(Shell parentShell, View view) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		myView = view;
		myContentProvider = new ModelElementsTreeContentProvider();
		
//		myEditingDomain.getResourceSet().getLoadOptions().
	}

	/**
	 * @generated
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		getShell().setText(
				Messages.SysmlElementChooserDialog_SelectModelElementTitle);
		createModelBrowser(composite);
		return composite;
	}

	/**
	 * @generated
	 */
	protected Control createButtonBar(Composite parent) {
		Control buttonBar = super.createButtonBar(parent);
		setOkButtonEnabled(false);
		return buttonBar;
	}

	/**
	 * @generated
	 */
	private void createModelBrowser(Composite composite) {
		myTreeViewer = new TreeViewer(composite, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.heightHint = 300;
		layoutData.widthHint = 300;
		myTreeViewer.getTree().setLayoutData(layoutData);
		myTreeViewer.setContentProvider(myContentProvider);
		myTreeViewer.setLabelProvider(new ModelElementsTreeLabelProvider());
		myTreeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
		myTreeViewer.addFilter(myFilter);
		myTreeViewer.addSelectionChangedListener(new OkButtonEnabler());
	}

	/**
	 * @generated
	 */
	private void setOkButtonEnabled(boolean enabled) {
		getButton(IDialogConstants.OK_ID).setEnabled(enabled);
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
	public URI getSelectedModelElementURI() {
		return mySelectedModelElementURI;
	}

	/**
	 * @generated
	 */
	public int open() {
//		create a new domain the old one might have been disposed and can not be used
//		myEditingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		int result = super.open();
		for (Iterator it = myEditingDomain.getResourceSet().getResources()
				.iterator(); it.hasNext();) {
			Resource resource = (Resource) it.next();
			resource.unload();
		}
		try {
			myEditingDomain.dispose();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	/**
	 * @generated
	 */
	private class ModelElementsTreeLabelProvider implements ILabelProvider {

		/**
		 * @generated
		 */
		private WorkbenchLabelProvider myWorkbenchLabelProvider = new WorkbenchLabelProvider();

		/**
		 * @generated
		 */
		private AdapterFactoryLabelProvider myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
				SysmlDiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());

		/**
		 * @generated
		 */
		public Image getImage(Object element) {
			Image result = myWorkbenchLabelProvider.getImage(element);
			return result != null ? result : myAdapterFactoryLabelProvider
					.getImage(element);
		}

		/**
		 * @generated
		 */
		public String getText(Object element) {
			String result = myWorkbenchLabelProvider.getText(element);
			return result != null && result.length() > 0 ? result
					: myAdapterFactoryLabelProvider.getText(element);
		}

		/**
		 * @generated
		 */
		public void addListener(ILabelProviderListener listener) {
			myWorkbenchLabelProvider.addListener(listener);
			myAdapterFactoryLabelProvider.addListener(listener);
		}

		/**
		 * @generated
		 */
		public void dispose() {
			myWorkbenchLabelProvider.dispose();
			myAdapterFactoryLabelProvider.dispose();
		}

		/**
		 * @generated
		 */
		public boolean isLabelProperty(Object element, String property) {
			return myWorkbenchLabelProvider.isLabelProperty(element, property)
					|| myAdapterFactoryLabelProvider.isLabelProperty(element,
							property);
		}

		/**
		 * @generated
		 */
		public void removeListener(ILabelProviderListener listener) {
			myWorkbenchLabelProvider.removeListener(listener);
			myAdapterFactoryLabelProvider.removeListener(listener);
		}

	}

	/**
	 * @generated
	 */
	private class ModelFilesFilter extends ViewerFilter {

		/**
		 * @generated
		 */
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (element instanceof IContainer) {
				return true;
			}
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				return isValidModelFile(file);
			}
			return true;
		}

	}

	/**
	 * @generated
	 */
	private class OkButtonEnabler implements ISelectionChangedListener {

		/**
		 * @generated
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			if (event.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				if (selection.size() == 1) {
					Object selectedElement = selection.getFirstElement();
					
					if (selectedElement instanceof IWrapperItemProvider) {
						selectedElement = ((IWrapperItemProvider) selectedElement)
								.getValue();
					}
					if (selectedElement instanceof FeatureMap.Entry) {
						selectedElement = ((FeatureMap.Entry) selectedElement)
								.getValue();
					}
					mySelectedElement = selectedElement;
					if (asModelObject) {
						if (selectedElement instanceof EObject) {
							EObject selectedModelElement = (EObject) selectedElement;
							setOkButtonEnabled(ViewService
									.getInstance()
									.provides(
											Node.class,
											new EObjectAdapter(selectedModelElement),
											myView,
											null,
											ViewUtil.APPEND,
											true,
											SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT));
							mySelectedModelElementURI = EcoreUtil
							.getURI(selectedModelElement);
							return;
						}
					} else {
						setOkButtonEnabled(myFilter.select(myTreeViewer, event, selectedElement));
						return;
					}
				}
			}
			mySelectedModelElementURI = null;
			setOkButtonEnabled(false);
		}

	}

	public Object getMySelectedElement() {
		return mySelectedElement;
	}

	public void setMySelectedElement(Object mySelectedElement) {
		this.mySelectedElement = mySelectedElement;
	}

	public ViewerFilter getMyFilter() {
		return myFilter;
	}

	public void setMyFilter(ViewerFilter myFilter) {
		this.myFilter = myFilter;
	}

	public boolean isAsModelObject() {
		return asModelObject;
	}

	public void setAsModelObject(boolean asModelObject) {
		this.asModelObject = asModelObject;
	}

	public ITreeContentProvider getMyContentProvider() {
		return myContentProvider;
	}
}
