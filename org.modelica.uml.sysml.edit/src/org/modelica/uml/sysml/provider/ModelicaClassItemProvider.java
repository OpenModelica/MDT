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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * This is the item provider adapter for a {@link org.modelica.uml.sysml.ModelicaClass} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelicaClassItemProvider extends BlockItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModelicaClassItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	protected void addIsAbstractPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addRedefinedClassifierPropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addQualifiedNamePropertyDescriptor(Object object) {
	}

	@Override
	protected void addAttributePropertyDescriptor(Object object) {
	}

	@Override
	protected void addGeneralPropertyDescriptor(Object object) {
	}

	@Override
	protected void addPackagePropertyDescriptor(Object object) {
	}

	@Override
	protected void addElementImportPropertyDescriptor(Object object) {
	}

	@Override
	protected void addImportedMemberPropertyDescriptor(Object object) {
	}

	@Override
	protected void addPackageImportPropertyDescriptor(Object object) {
	}

	@Override
	protected void addGeneralizationPropertyDescriptor(Object object) {
	}

	@Override
	protected void addInheritedMemberPropertyDescriptor(Object object) {
	}

	@Override
	protected void addMemberPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedMemberPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedRulePropertyDescriptor(Object object) {
	}

	@Override
	protected void addVisibilityPropertyDescriptor(Object object) {
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
	protected void addOwnedPortPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedAttributePropertyDescriptor(Object object) {
		// TODO Auto-generated method stub
		super.addOwnedAttributePropertyDescriptor(object);
	}

	@Override
	protected void addOwnedConnectorPropertyDescriptor(Object object) {
	}

	@Override
	protected void addPartPropertyDescriptor(Object object) {
	}

	@Override
	protected void addRolePropertyDescriptor(Object object) {
	}

	@Override
	protected void addCollaborationUsePropertyDescriptor(Object object) {
	}

	@Override
	protected void addFeaturePropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsLeafPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedTemplateSignaturePropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedUseCasePropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwningTemplateParameterPropertyDescriptor(Object object) {
	}

	@Override
	protected void addPowertypeExtentPropertyDescriptor(Object object) {
	}
/*
	@Override
	protected void addRedefinedClassifierPropertyDescriptor(Object object) {
	}

	@Override
	protected void addRedefinedElementPropertyDescriptor(Object object) {
	}
*/
	@Override
	protected void addRedefinitionContextPropertyDescriptor(Object object) {
	}

	@Override
	protected void addRepresentationPropertyDescriptor(Object object) {
	}

	@Override
	protected void addSubstitutionPropertyDescriptor(Object object) {
	}

	@Override
	protected void addTemplateBindingPropertyDescriptor(Object object) {
	}

	@Override
	protected void addTemplateParameterPropertyDescriptor(Object object) {
	}

	@Override
	protected void addUseCasePropertyDescriptor(Object object) {
	}

	@Override
	protected void addClientDependencyPropertyDescriptor(Object object) {
	}

	@Override
	protected void addNameExpressionPropertyDescriptor(Object object) {
	}

	@Override
	protected void addNamespacePropertyDescriptor(Object object) {
	}

	//b@Override
	protected void addIsEncapsulatedPropertyDescriptor(Object object) {
	}

	@Override
	protected void addClassifierBehaviorPropertyDescriptor(Object object) {
	}

	@Override
	protected void addExtensionPropertyDescriptor(Object object) {
	}

	@Override
	protected void addInterfaceRealizationPropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsActivePropertyDescriptor(Object object) {
	}

	@Override
	protected void addNestedClassifierPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedBehaviorPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedOperationPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedReceptionPropertyDescriptor(Object object) {
	}

	@Override
	protected void addOwnedTriggerPropertyDescriptor(Object object) {
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

			addIsEncapsulatedClassPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Is Encapsulated Class feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsEncapsulatedClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaClass_isEncapsulatedClass_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaClass_isEncapsulatedClass_feature", "_UI_ModelicaClass_type"),
				 SysmlPackage.Literals.MODELICA_CLASS__IS_ENCAPSULATED_CLASS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns ModelicaClass.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelicaClass"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ModelicaClass)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ModelicaClass_type") :
			getString("_UI_ModelicaClass_type") + " " + label;
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

		switch (notification.getFeatureID(ModelicaClass.class)) {
			case SysmlPackage.MODELICA_CLASS__IS_ENCAPSULATED_CLASS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
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
			childFeature == UMLPackage.Literals.CLASSIFIER__REPRESENTATION ||
			childFeature == UMLPackage.Literals.CLASSIFIER__COLLABORATION_USE ||
			childFeature == UMLPackage.Literals.CLASSIFIER__OWNED_USE_CASE ||
			childFeature == UMLPackage.Literals.CLASS__NESTED_CLASSIFIER ||
			childFeature == UMLPackage.Literals.STRUCTURED_CLASSIFIER__OWNED_ATTRIBUTE ||
			childFeature == UMLPackage.Literals.ENCAPSULATED_CLASSIFIER__OWNED_PORT ||
			childFeature == UMLPackage.Literals.BEHAVIORED_CLASSIFIER__OWNED_BEHAVIOR ||
			childFeature == UMLPackage.Literals.BEHAVIORED_CLASSIFIER__CLASSIFIER_BEHAVIOR;

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
