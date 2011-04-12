/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.NamedElementItemProvider;

import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.provider.descriptor.SysmlItemPropertyDescriptor;

/**
 * This is the item provider adapter for a {@link org.modelica.uml.sysml.Requirement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementItemProvider
	extends BlockItemProvider
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
	public RequirementItemProvider(AdapterFactory adapterFactory) {
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

			addLevelPropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addPriorityPropertyDescriptor(object);
			addDerivedFromPropertyDescriptor(object);
			addDerivedPropertyDescriptor(object);
			addStatusPropertyDescriptor(object);
			addSatisfiedByPropertyDescriptor(object);
			addVerifiedByPropertyDescriptor(object);
			addTracedToPropertyDescriptor(object);
			addRefinedByPropertyDescriptor(object);
			addMasterPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	@Override
	protected void addClassifierBehaviorPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsActivePropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addIsEncapsulatedPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void addOwnedPortPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * This adds a property descriptor for the Powertype Extent feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addPowertypeExtentPropertyDescriptor(Object object) {
	}

	/**
	 * This adds a property descriptor for the Representation feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addRepresentationPropertyDescriptor(Object object) {
	}
	
	/**
	 * This adds a property descriptor for the Template Parameter feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addTemplateParameterPropertyDescriptor(Object object) {
	}
	
	/**
	 * This adds a property descriptor for the Use Case feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addUseCasePropertyDescriptor(Object object) {
	}
	
	/**
	 * This adds a property descriptor for the Is Leaf feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addIsLeafPropertyDescriptor(Object object) {
	}
	
	/**
	 * This adds a property descriptor for the Is Abstract feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addIsAbstractPropertyDescriptor(Object object) {
	}
	
	/**
	 * This adds a property descriptor for the Redefined Classifier feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addRedefinedClassifierPropertyDescriptor(Object object) {
	}
	
	/**
	 * This adds a property descriptor for the Client Dependency feature.
	 * <!-- begin-user-doc -->
	 * Empty method suppresses this feature from being shown in the
	 * properties editor.
	 * <!-- end-user-doc -->
	 * @Override
	 */
	protected void addClientDependencyPropertyDescriptor(Object object) {
	}
	
	/**
	 * This adds a property descriptor for the Level feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLevelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_level_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_level_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__LEVEL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_id_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Priority feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPriorityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_priority_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_priority_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__PRIORITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Derived From feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDerivedFromPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_derivedFrom_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_derivedFrom_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__DERIVED_FROM,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Derived feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDerivedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_derived_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_derived_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__DERIVED,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Status feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStatusPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_status_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_status_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__STATUS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Satisfied By feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSatisfiedByPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_satisfiedBy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_satisfiedBy_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Verified By feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVerifiedByPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_verifiedBy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_verifiedBy_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Traced To feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTracedToPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_tracedTo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_tracedTo_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__TRACED_TO,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Refined By feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRefinedByPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_refinedBy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_refinedBy_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__REFINED_BY,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Master feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMasterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_master_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_master_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__MASTER,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Requirement_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Requirement_description_feature", "_UI_Requirement_type"),
				 SysmlPackage.Literals.REQUIREMENT__DESCRIPTION,
				 true,
				 true,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY);
			childrenFeatures.add(SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY);
			childrenFeatures.add(SysmlPackage.Literals.REQUIREMENT__TRACED_TO);
			childrenFeatures.add(SysmlPackage.Literals.REQUIREMENT__REFINED_BY);
			childrenFeatures.add(SysmlPackage.Literals.REQUIREMENT__MASTER);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Requirement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Requirement"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((Requirement)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Requirement_type") :
			getString("_UI_Requirement_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void notifyChanged(Notification notification) {
		
		updateChildren(notification);

		switch (notification.getFeatureID(Requirement.class)) {
			case SysmlPackage.REQUIREMENT__LEVEL:
			case SysmlPackage.REQUIREMENT__ID:
			case SysmlPackage.REQUIREMENT__PRIORITY:
			case SysmlPackage.REQUIREMENT__DERIVED_FROM:
			case SysmlPackage.REQUIREMENT__DERIVED:
			case SysmlPackage.REQUIREMENT__STATUS:
			case SysmlPackage.REQUIREMENT__DESCRIPTION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case SysmlPackage.REQUIREMENT__SATISFIED_BY:
			case SysmlPackage.REQUIREMENT__VERIFIED_BY:
			case SysmlPackage.REQUIREMENT__TRACED_TO:
			case SysmlPackage.REQUIREMENT__REFINED_BY:
			case SysmlPackage.REQUIREMENT__MASTER:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
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
				(SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaClass()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaModel()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaBlock()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaConnector()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaRecord()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaFunction()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaClass()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaModel()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaBlock()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaConnector()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaRecord()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY,
				 SysmlFactory.eINSTANCE.createModelicaFunction()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__TRACED_TO,
				 SysmlFactory.eINSTANCE.createModelicaClass()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__TRACED_TO,
				 SysmlFactory.eINSTANCE.createModelicaModel()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__TRACED_TO,
				 SysmlFactory.eINSTANCE.createModelicaBlock()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__TRACED_TO,
				 SysmlFactory.eINSTANCE.createModelicaConnector()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__TRACED_TO,
				 SysmlFactory.eINSTANCE.createModelicaRecord()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__TRACED_TO,
				 SysmlFactory.eINSTANCE.createModelicaFunction()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__REFINED_BY,
				 SysmlFactory.eINSTANCE.createModelicaClass()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__REFINED_BY,
				 SysmlFactory.eINSTANCE.createModelicaModel()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__REFINED_BY,
				 SysmlFactory.eINSTANCE.createModelicaBlock()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__REFINED_BY,
				 SysmlFactory.eINSTANCE.createModelicaConnector()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__REFINED_BY,
				 SysmlFactory.eINSTANCE.createModelicaRecord()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__REFINED_BY,
				 SysmlFactory.eINSTANCE.createModelicaFunction()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.REQUIREMENT__MASTER,
				 SysmlFactory.eINSTANCE.createRequirement()));
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
			childFeature == UMLPackage.Literals.CLASSIFIER__REPRESENTATION ||
			childFeature == UMLPackage.Literals.CLASSIFIER__COLLABORATION_USE ||
			childFeature == UMLPackage.Literals.CLASSIFIER__OWNED_USE_CASE ||
			childFeature == UMLPackage.Literals.CLASS__NESTED_CLASSIFIER ||
			childFeature == UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE ||
			childFeature == UMLPackage.Literals.ENCAPSULATED_CLASSIFIER__OWNED_PORT ||
			childFeature == UMLPackage.Literals.BEHAVIORED_CLASSIFIER__OWNED_BEHAVIOR ||
			childFeature == UMLPackage.Literals.BEHAVIORED_CLASSIFIER__CLASSIFIER_BEHAVIOR ||
			childFeature == SysmlPackage.Literals.REQUIREMENT__SATISFIED_BY ||
			childFeature == SysmlPackage.Literals.REQUIREMENT__VERIFIED_BY ||
			childFeature == SysmlPackage.Literals.REQUIREMENT__TRACED_TO ||
			childFeature == SysmlPackage.Literals.REQUIREMENT__REFINED_BY ||
			childFeature == SysmlPackage.Literals.REQUIREMENT__MASTER;

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
