package org.modelica.uml.sysml.provider.descriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
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
		return cat;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection getChoiceOfValues(Object object) {
		Collection valueList = super.getChoiceOfValues(object);

		/*
		String id = this.getId(object);
		// filter only Type property
		if (id.indexOf("type") > -1) {
			if (object instanceof ModelicaProperty && valueList != null
					&& valueList.size() > 0) {
				ModelicaProperty property = (ModelicaProperty) object;

				List<Type> removeList = new ArrayList<Type>();
				Iterator iter = valueList.iterator();

				while (iter.hasNext()) {
					Object obj = iter.next();
					if (obj instanceof ModelicaType
							&& (property.getType() == null || (property
									.getType() != null && !property.getType()
									.equals(obj)))) {
						removeList.add((Type) obj);
					} else if (property.getPropertyKind().equals(
							PropertyKind.PARAMETER_LITERAL)
							&& (!(obj instanceof PrimitiveType) && !(obj instanceof Enumeration))) {
						removeList.add((Type) obj);
					} else if (property.getPropertyKind().equals(
							PropertyKind.PART_LITERAL)
							&& (obj instanceof PrimitiveType || obj instanceof Enumeration)) {
						removeList.add((Type) obj);
					} else if (property.getPropertyKind().equals(
							PropertyKind.VARIABLE_LITERAL)
							&& (!(obj instanceof PrimitiveType) && !(obj instanceof Enumeration))) {
						removeList.add((Type) obj);
					} else if (obj instanceof Association) {
						removeList.add((Type) obj);
					}
				}

				if (removeList.size() > 0) {
					valueList.removeAll(removeList);
				}
			}
		}
		*/
		return valueList;
	}

}