package org.modelica.uml.sysml.diagram2.sheet;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.ui.services.properties.ICompositePropertySource;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditor;
import org.modelica.uml.sysml.diagram2.properties.SysmlPropertySource;
import org.modelica.uml.sysml.provider.SysmlItemProviderAdapterFactory;

/**
 * @generated
 */
public class SysmlPropertySection extends AdvancedPropertySection implements
		IPropertySourceProvider {

	private ModelEditPart myModel;

	
	/**
	 * @generated NOT
	 */
	public IPropertySource getPropertySource(Object object) {
		if (object instanceof IPropertySource) {
			return (IPropertySource) object;
		}
		AdapterFactory adapterFactory = null;

		boolean forType = (new SysmlItemProviderAdapterFactory())
				.isFactoryForType(object);
		if (forType) {
			adapterFactory = new SysmlItemProviderAdapterFactory();
		} else {
			adapterFactory = getAdapterFactory(object);
		}

		if (adapterFactory != null) {
			IItemPropertySource ips = (IItemPropertySource) adapterFactory
					.adapt(object, IItemPropertySource.class);
			if (ips != null) {
				return createPropertySource(object, ips);
			}
		}
		if (object instanceof IAdaptable) {
			return (IPropertySource) ((IAdaptable) object)
					.getAdapter(IPropertySource.class);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected IPropertySourceProvider getPropertySourceProvider() {
		return this;
	}

	/**
	 * Modify/unwrap selection.
	 * @generated
	 */
	protected Object transformSelection(Object selected) {

		if (selected instanceof EditPart) {
			Object model = ((EditPart) selected).getModel();
			return model instanceof View ? ((View) model).getElement() : null;
		}
		if (selected instanceof View) {
			return ((View) selected).getElement();
		}
		if (selected instanceof IAdaptable) {
			View view = (View) ((IAdaptable) selected).getAdapter(View.class);
			if (view != null) {
				return view.getElement();
			}
		}
		return selected;
	}

	/**
	 * @generated NOT
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if(part instanceof SysmlDiagramEditor){
			Object obj = ((SysmlDiagramEditor)part).getDiagramGraphicalViewer().getRootEditPart().getContents();
			if(obj instanceof ModelEditPart) 
				myModel = (ModelEditPart)obj;
		}
		if (selection.isEmpty()
				|| false == selection instanceof StructuredSelection) {
			super.setInput(part, selection);
			return;
		}
		final StructuredSelection structuredSelection = ((StructuredSelection) selection);
		ArrayList transformedSelection = new ArrayList(structuredSelection
				.size());
		for (Iterator it = structuredSelection.iterator(); it.hasNext();) {
			Object r = transformSelection(it.next());
			if (r != null) {
				transformedSelection.add(r);
			}
		}
		super.setInput(part, new StructuredSelection(transformedSelection));
	}

	/**
	 * @generated
	 */
	protected AdapterFactory getAdapterFactory(Object object) {
		if (getEditingDomain() instanceof AdapterFactoryEditingDomain) {
			return ((AdapterFactoryEditingDomain) getEditingDomain())
					.getAdapterFactory();
		}
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(object);
		if (editingDomain != null) {
			return ((AdapterFactoryEditingDomain) editingDomain)
					.getAdapterFactory();
		}
		return null;
	}

	public ModelEditPart getMyModel() {
		return myModel;
	}

	public void setMyModel(ModelEditPart myModel) {
		this.myModel = myModel;
	}

	/*
	 */
	protected ICompositePropertySource createPropertySource(Object object,
			IItemPropertySource itemPropertySource) {
		SysmlPropertySource source = new SysmlPropertySource(object, itemPropertySource, "Modelica"); //$NON-NLS-1$
		source.setMyModel(getMyModel());
		return source;
	}
}
