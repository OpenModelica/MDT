package org.modelica.uml.sysml.diagram2.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.properties.GetPropertySourceOperation;
import org.eclipse.gmf.runtime.common.ui.services.properties.ICompositePropertySource;
import org.eclipse.gmf.runtime.common.ui.services.properties.IPropertiesProvider;
import org.eclipse.gmf.runtime.emf.ui.properties.providers.GenericEMFPropertiesProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;
import org.modelica.uml.sysml.diagram2.properties.SysmlPropertySource;
import org.modelica.uml.sysml.provider.SysmlItemProviderAdapterFactory;

/**
 * @generated
 */
public class SysmlPropertyProvider extends GenericEMFPropertiesProvider
		implements IPropertiesProvider {

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetPropertySourceOperation) {
			Object object = ((GetPropertySourceOperation) operation)
					.getObject();
			return getSemanticElement(object) != null;
		}
		return false;
	}

	/**
	 * @generated NOT
	 */
	public ICompositePropertySource getPropertySource(Object object) {
		EObject element = getSemanticElement(object);
		if (element != null) {
			return getPropertySourceInternal(element);
		}
		return null;
	}

	/*
	 */
	protected ICompositePropertySource createPropertySource(Object object,
			IItemPropertySource itemPropertySource) {
		return new SysmlPropertySource(object, itemPropertySource, "Modelica"); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	protected EObject getSemanticElement(Object object) {
		View view = null;
		if (object instanceof View) {
			view = (View) object;
		} else if (object instanceof EditPart) {
			EditPart editPart = (EditPart) object;
			if (editPart.getModel() instanceof View) {
				view = (View) editPart.getModel();
			}
		}
		if (view != null
				&& ModelEditPart.MODEL_ID.equals(SysmlVisualIDRegistry
						.getModelID(view))) {
			return view.getElement();
		}
		return null;
	}

	private ICompositePropertySource getPropertySourceInternal(Object object) {

		if (object instanceof ICompositePropertySource) {
			return (ICompositePropertySource) object;
		} else {
			AdapterFactory adapterFactory = null;

			boolean forType = (new SysmlItemProviderAdapterFactory())
					.isFactoryForType(object);
			if (forType) {
				adapterFactory = new SysmlItemProviderAdapterFactory();
			} else {
				adapterFactory = getAdapterFactory(object);
			}

			IItemPropertySource itemPropertySource = (IItemPropertySource) (adapterFactory
					.adapt(object, IItemPropertySource.class));
			return itemPropertySource != null ? createPropertySource(object,
					itemPropertySource) : null;
		}
	}
}
