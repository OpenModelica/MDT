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

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.AssociationItemProvider;

import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * This is the item provider adapter for a
 * {@link org.modelica.uml.sysml.ModelicaComposition} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ModelicaCompositionItemProvider extends AssociationItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModelicaCompositionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addOwnedCommentPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwnedElementPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwnerPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addClientDependencyPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addNameExpressionPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addNamespacePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addQualifiedNamePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addVisibilityPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addElementImportPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addImportedMemberPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addMemberPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwnedMemberPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwnedRulePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addPackageImportPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addEndTypePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addIsDerivedPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addMemberEndPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addNavigableOwnedEndPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwnedEndPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addRelatedElementPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addAttributePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addCollaborationUsePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addFeaturePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addGeneralizationPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addGeneralPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addInheritedMemberPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addIsAbstractPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addIsLeafPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwnedTemplateSignaturePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwnedUseCasePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addOwningTemplateParameterPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addPackagePropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addPowertypeExtentPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addRedefinedClassifierPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addRedefinedElementPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addRedefinitionContextPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addRepresentationPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addSubstitutionPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addTemplateBindingPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addTemplateParameterPropertyDescriptor(Object arg0) {
	}

	@Override
	protected void addUseCasePropertyDescriptor(Object arg0) {
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

			addIsFlowPropertyDescriptor(object);
			addDirectionPropertyDescriptor(object);
			addArrayDimensionPropertyDescriptor(object);
			addAccessPropertyDescriptor(object);
			addDefaultPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
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
				 getString("_UI_ModelicaComposition_isFlow_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaComposition_isFlow_feature", "_UI_ModelicaComposition_type"),
				 SysmlPackage.Literals.MODELICA_COMPOSITION__IS_FLOW,
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
				 getString("_UI_ModelicaComposition_direction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaComposition_direction_feature", "_UI_ModelicaComposition_type"),
				 SysmlPackage.Literals.MODELICA_COMPOSITION__DIRECTION,
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
				 getString("_UI_ModelicaComposition_arrayDimension_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaComposition_arrayDimension_feature", "_UI_ModelicaComposition_type"),
				 SysmlPackage.Literals.MODELICA_COMPOSITION__ARRAY_DIMENSION,
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
				 getString("_UI_ModelicaComposition_access_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaComposition_access_feature", "_UI_ModelicaComposition_type"),
				 SysmlPackage.Literals.MODELICA_COMPOSITION__ACCESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Default feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDefaultPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaComposition_default_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaComposition_default_feature", "_UI_ModelicaComposition_type"),
				 SysmlPackage.Literals.MODELICA_COMPOSITION__DEFAULT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns ModelicaComposition.gif.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelicaComposition"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ModelicaComposition)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ModelicaComposition_type") :
			getString("_UI_ModelicaComposition_type") + " " + label;
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

		switch (notification.getFeatureID(ModelicaComposition.class)) {
			case SysmlPackage.MODELICA_COMPOSITION__IS_FLOW:
			case SysmlPackage.MODELICA_COMPOSITION__DIRECTION:
			case SysmlPackage.MODELICA_COMPOSITION__ARRAY_DIMENSION:
			case SysmlPackage.MODELICA_COMPOSITION__ACCESS:
			case SysmlPackage.MODELICA_COMPOSITION__DEFAULT:
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
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.ASSOCIATION__OWNED_END,
				 SysmlFactory.eINSTANCE.createBlockProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.ASSOCIATION__OWNED_END,
				 SysmlFactory.eINSTANCE.createDistributedProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.ASSOCIATION__OWNED_END,
				 SysmlFactory.eINSTANCE.createFlowPort()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.ASSOCIATION__OWNED_END,
				 SysmlFactory.eINSTANCE.createFlowProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.ASSOCIATION__OWNED_END,
				 SysmlFactory.eINSTANCE.createModelicaProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.ASSOCIATION__OWNED_END,
				 SysmlFactory.eINSTANCE.createModelicaConnectorProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.ASSOCIATION__OWNED_END,
				 SysmlFactory.eINSTANCE.createModelicaEquationProperty()));
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
