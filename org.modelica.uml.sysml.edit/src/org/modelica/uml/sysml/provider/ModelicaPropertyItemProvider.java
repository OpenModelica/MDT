/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.ElementItemProvider;

import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.provider.descriptor.SysmlItemPropertyDescriptor;

/**
 * This is the item provider adapter for a {@link org.modelica.uml.sysml.ModelicaProperty} object.
 * <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * @generated
 */
public class ModelicaPropertyItemProvider extends BlockPropertyItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModelicaPropertyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected ItemPropertyDescriptor createItemPropertyDescriptor(
			AdapterFactory adapterFactory, ResourceLocator resourceLocator,
			String displayName, String description, EStructuralFeature feature,
			boolean isSettable, boolean multiLine, boolean sortChoices,
			Object staticImage, String category, String[] filterFlags) {

		return new SysmlItemPropertyDescriptor(adapterFactory, resourceLocator,
				displayName, description, feature, isSettable, multiLine,
				sortChoices, staticImage, category, filterFlags);
	}

	@SuppressWarnings("unchecked")
	protected static ComposedImage composePropertyImage(Object object,
			ComposedImage composedImage) {
		ModelicaProperty property = (ModelicaProperty) object;

		composedImage.getImages().removeAll(composedImage.getImages());
		if (property.getPropertyKind().equals(PropertyKind.PARAMETER_LITERAL)) {
			composedImage.getImages().add(
					SysmlEditPlugin.INSTANCE
							.getImage("full/obj16/ModelicaProperty"));
		} else if (property.getPropertyKind().equals(PropertyKind.PART_LITERAL)) {
			composedImage.getImages().add(
					SysmlEditPlugin.INSTANCE
							.getImage("full/obj16/ModelicaProperty"));
		} else if (property.getPropertyKind().equals(
				PropertyKind.VARIABLE_LITERAL)) {
			composedImage.getImages().add(
					SysmlEditPlugin.INSTANCE
							.getImage("full/obj16/ModelicaProperty"));
		}

		if (property.getAccess() != null) {
			composedImage.getImages().add(
					SysmlEditPlugin.INSTANCE.getImage("full/ovr16/AccessKind_" //$NON-NLS-1$
							+ property.getAccess().getName()));
		}

		return composedImage;
	}

	@SuppressWarnings("unchecked")
	protected static ComposedImage composeAccessImage(Object object,
			ComposedImage composedImage) {
		ModelicaProperty property = (ModelicaProperty) object;

		if (property.getAccess() != null) {
			composedImage.getImages().add(
					SysmlEditPlugin.INSTANCE.getImage("full/ovr16/AccessKind_" //$NON-NLS-1$
							+ property.getAccess().getName()));
		}

		return composedImage;
	}

	protected ComposedImage getComposedImage(Object object, Object image) {
		return composeAccessImage(object, composePropertyImage(object, super
				.getComposedImage(object, image)));
	}

	
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TypedElement_type_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_TypedElement_type_feature", "_UI_TypedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 UMLPackage.Literals.TYPED_ELEMENT__TYPE,
				 true,
				 null,
				 null,
				 null));
	}
	
	
	@Override
	protected void addClassPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addAggregationPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addAssociationEndPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addAssociationPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addEndPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOppositePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addIsReadOnlyPropertyDescriptor(Object object) {
	}

	@Override
	protected void addVisibilityPropertyDescriptor(Object object) {
	}

	@Override
	protected void addDefaultValuePropertyDescriptor(Object object) {
	}

	@Override
	protected void addNameExpressionPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedCommentPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedElementPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnerPropertyDescriptor(Object object) {
	}

	@Override
	protected void addClientDependencyPropertyDescriptor(Object object) {
	}

	@Override
	protected void addNamespacePropertyDescriptor(Object object) {
	}

	/*
	 * @Override protected void addQualifiedNamePropertyDescriptor(Object
	 * object) { }
	 */

	@Override
	protected void addIsLeafPropertyDescriptor(Object object) {
	}

	@Override
	protected void addRedefinedElementPropertyDescriptor(Object object) {
	}

	@Override
	protected void addRedefinitionContextPropertyDescriptor(Object object) {
	}

	@Override
	protected void addFeaturingClassifierPropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsStaticPropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsOrderedPropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsUniquePropertyDescriptor(Object object) {
	}

	@Override
	protected void addLowerPropertyDescriptor(Object object) {
	}

	@Override
	protected void addLowerValuePropertyDescriptor(Object object) {
	}

	@Override
	protected void addUpperPropertyDescriptor(Object object) {
	}

	@Override
	protected void addUpperValuePropertyDescriptor(Object object) {
	}

	@Override
	protected void addDeployedElementPropertyDescriptor(Object object) {
	}

	@Override
	protected void addDeploymentPropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsCompositePropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsDerivedPropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsDerivedUnionPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedTemplateSignaturePropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwningAssociationPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwningTemplateParameterPropertyDescriptor(Object object) {
	}

	@Override
	protected void addQualifierPropertyDescriptor(Object object) {
	}

	@Override
	protected void addRedefinedPropertyPropertyDescriptor(Object object) {
	}

	@Override
	protected void addSubsettedPropertyPropertyDescriptor(Object object) {
	}

	@Override
	protected void addTemplateBindingPropertyDescriptor(Object object) {
	}

	@Override
	protected void addTemplateParameterPropertyDescriptor(Object object) {
	}

	@Override
	protected void addDatatypePropertyDescriptor(Object object) {
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addVariabilityPropertyDescriptor(object);
			addIsFlowPropertyDescriptor(object);
			addDirectionPropertyDescriptor(object);
			addPropertyKindPropertyDescriptor(object);
			addArrayDimensionPropertyDescriptor(object);
			addAccessPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Variability feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addVariabilityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaProperty_variability_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaProperty_variability_feature", "_UI_ModelicaProperty_type"),
				 SysmlPackage.Literals.MODELICA_PROPERTY__VARIABILITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Flow feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIsFlowPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaProperty_isFlow_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaProperty_isFlow_feature", "_UI_ModelicaProperty_type"),
				 SysmlPackage.Literals.MODELICA_PROPERTY__IS_FLOW,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Direction feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDirectionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaProperty_direction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaProperty_direction_feature", "_UI_ModelicaProperty_type"),
				 SysmlPackage.Literals.MODELICA_PROPERTY__DIRECTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Property Kind feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPropertyKindPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaProperty_propertyKind_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaProperty_propertyKind_feature", "_UI_ModelicaProperty_type"),
				 SysmlPackage.Literals.MODELICA_PROPERTY__PROPERTY_KIND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Array Dimension feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addArrayDimensionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaProperty_arrayDimension_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaProperty_arrayDimension_feature", "_UI_ModelicaProperty_type"),
				 SysmlPackage.Literals.MODELICA_PROPERTY__ARRAY_DIMENSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Access feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAccessPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaProperty_access_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaProperty_access_feature", "_UI_ModelicaProperty_type"),
				 SysmlPackage.Literals.MODELICA_PROPERTY__ACCESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns ModelicaProperty.gif.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelicaProperty"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ModelicaProperty)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ModelicaProperty_type") :
			getString("_UI_ModelicaProperty_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ModelicaProperty.class)) {
			case SysmlPackage.MODELICA_PROPERTY__VARIABILITY:
			case SysmlPackage.MODELICA_PROPERTY__IS_FLOW:
			case SysmlPackage.MODELICA_PROPERTY__DIRECTION:
			case SysmlPackage.MODELICA_PROPERTY__PROPERTY_KIND:
			case SysmlPackage.MODELICA_PROPERTY__ARRAY_DIMENSION:
			case SysmlPackage.MODELICA_PROPERTY__ACCESS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * This returns the label text for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getCreateChildText(Object owner, Object feature, Object child, Collection selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == UMLPackage.Literals.NAMED_ELEMENT__NAME_EXPRESSION ||
			childFeature == UMLPackage.Literals.MULTIPLICITY_ELEMENT__UPPER_VALUE ||
			childFeature == UMLPackage.Literals.MULTIPLICITY_ELEMENT__LOWER_VALUE ||
			childFeature == UMLPackage.Literals.PROPERTY__DEFAULT_VALUE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return SysmlEditPlugin.INSTANCE;
	}

}
