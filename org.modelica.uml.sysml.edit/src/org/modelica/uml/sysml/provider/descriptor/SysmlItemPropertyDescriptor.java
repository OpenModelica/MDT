package org.modelica.uml.sysml.provider.descriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Model;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.PropertyKind;

public class SysmlItemPropertyDescriptor extends ItemPropertyDescriptor
		implements IItemQualifiedTextProvider {

	protected class SysmlItemDelegator extends ItemDelegator implements
			IItemQualifiedTextProvider {

		protected SysmlItemDelegator(AdapterFactory adapterFactory,
				ResourceLocator resourceLocator) {
			super(adapterFactory, resourceLocator);
		}

		public String getQualifiedText(Object object) {

			if (object instanceof EList) {
				StringBuffer text = new StringBuffer();

				for (Iterator i = ((List) object).iterator(); i.hasNext();) {
					Object child = i.next();

					if (text.length() > 0) {
						text.append(", "); //$NON-NLS-1$
					}

					text.append(getQualifiedText(child));
				}

				return text.toString();
			} else {

				String qualifiedText = UML2Util.getQualifiedText(
						(EObject) object, new UML2Util.QualifiedTextProvider() {

							public String getFeatureText(
									EStructuralFeature eStructuralFeature) {
								return "";
							}

							public String getClassText(EObject eObject) {
								return getClassText(eObject);
							}

							public String getSeparator() {
								return " ";
							}
						});

				return qualifiedText;

				/*
				 * IItemQualifiedTextProvider itemQualifiedTextProvider =
				 * (IItemQualifiedTextProvider) adapterFactory .adapt(object,
				 * IItemQualifiedTextProvider.class);
				 * 
				 * return itemQualifiedTextProvider != null ?
				 * itemQualifiedTextProvider .getQualifiedText(object) :
				 * getText(object);
				 */
			}
		}
	}

	public SysmlItemPropertyDescriptor(AdapterFactory adapterFactory,
			ResourceLocator resourceLocator, String displayName,
			String description, EStructuralFeature feature, boolean isSettable,
			boolean multiLine, boolean sortChoices, Object staticImage,
			String category, String[] filterFlags) {
		super(adapterFactory, resourceLocator, displayName, description,
				feature, isSettable, multiLine, sortChoices, staticImage,
				category == null && resourceLocator != null ? resourceLocator
						.getString("_UI_UML_category") : category, filterFlags);

		this.itemDelegator = new SysmlItemDelegator(adapterFactory,
				resourceLocator);
	}

	public String getQualifiedText(Object object) {
		return ((IItemQualifiedTextProvider) itemDelegator)
				.getQualifiedText(object);
	}

	public String getId(Object object) {
		String category = getCategory(object);
		String cat = category == null ? getQualifiedText(feature) : category
				+ getQualifiedText(feature);

		return super.getId(object);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection getChoiceOfValues(Object object) {
		Collection valueList = null;
		if(valueList == null)
		valueList = super.getChoiceOfValues(object);
		return valueList;
}

}