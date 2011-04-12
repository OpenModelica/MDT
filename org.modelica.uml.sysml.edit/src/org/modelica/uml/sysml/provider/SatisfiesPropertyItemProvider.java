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

import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.PropertyItemProvider;

import org.modelica.uml.sysml.SatisfiesProperty;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * This is the item provider adapter for a {@link org.modelica.uml.sysml.SatisfiesProperty} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SatisfiesPropertyItemProvider
	extends PropertyItemProvider
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SatisfiesPropertyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addRequirementPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Requirement feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRequirementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SatisfiesProperty_requirement_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SatisfiesProperty_requirement_feature", "_UI_SatisfiesProperty_type"),
				 SysmlPackage.Literals.SATISFIES_PROPERTY__REQUIREMENT,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}


	@Override
	protected void addAggregationPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addAssociationPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addClassPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addClientDependencyPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addDefaultPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addEndPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsDerivedPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsDerivedUnionPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsLeafPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsOrderedPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsReadOnlyPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsStaticPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsUniquePropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	protected void addLowerPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addNamePropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addRedefinedPropertyPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addSubsettedPropertyPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addTemplateParameterPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addTypePropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addUpperPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * This returns SatisfiesProperty.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SatisfiesProperty"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((SatisfiesProperty)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_SatisfiesProperty_type") :
			getString("_UI_SatisfiesProperty_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createBlockProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createDistributedProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createFlowPort()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createFlowProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createModelicaProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createModelicaConnectorProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createModelicaEquationProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createSatisfiesProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.PROPERTY__QUALIFIER,
				 SysmlFactory.eINSTANCE.createSatisfiedByProperty()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return SysmlEditPlugin.INSTANCE;
	}

}
