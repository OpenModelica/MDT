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

import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.PrimitiveTypeItemProvider;


import org.modelica.uml.sysml.ModelicaType;

import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * This is the item provider adapter for a {@link org.modelica.uml.sysml.ModelicaType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelicaTypeItemProvider extends ModelicaClassifierItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModelicaTypeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addClientDependencyPropertyDescriptor(Object object) {
	}

	@Override
	protected void addNameExpressionPropertyDescriptor(Object object) {
	}

	/*@Override
	protected void addNamePropertyDescriptor(Object object) {
	}*/

	@Override
	protected void addNamespacePropertyDescriptor(Object object) {
	}

	@Override
	protected void addQualifiedNamePropertyDescriptor(Object object) {
	}

	@Override
	protected void addVisibilityPropertyDescriptor(Object object) {
	}

	@Override
	protected void addUseCasePropertyDescriptor(Object object) {
	}

	@Override
	protected void addCollaborationUsePropertyDescriptor(Object object) {
	}

	@Override
	protected void addGeneralizationPropertyDescriptor(Object object) {
	}

	@Override
	protected void addInheritedMemberPropertyDescriptor(Object object) {
	}

	@Override
	protected void addIsAbstractPropertyDescriptor(Object object) {
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

	@Override
	protected void addRedefinedClassifierPropertyDescriptor(Object object) {
	}

	@Override
	protected void addRedefinedElementPropertyDescriptor(Object object) {
	}

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
	
	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addTypeDataPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type Data feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypeDataPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ModelicaType_typeData_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ModelicaType_typeData_feature", "_UI_ModelicaType_type"),
				 SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA,
				 true,
				 false,
				 false,
				 null,
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
			childrenFeatures.add(SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA);
		}
		return childrenFeatures;
	}

	/**
	 * This returns ModelicaType.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelicaType"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ModelicaType)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ModelicaType_type") :
			getString("_UI_ModelicaType_type") + " " + label;
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

		switch (notification.getFeatureID(ModelicaType.class)) {
			case SysmlPackage.MODELICA_TYPE__TYPE_DATA:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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
				(SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA,
				 SysmlFactory.eINSTANCE.createModelicaPrimitiveType()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA,
				 SysmlFactory.eINSTANCE.createModelicaReal()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA,
				 SysmlFactory.eINSTANCE.createModelicaInteger()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA,
				 SysmlFactory.eINSTANCE.createModelicaString()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA,
				 SysmlFactory.eINSTANCE.createModelicaBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA,
				 UMLFactory.eINSTANCE.createPrimitiveType()));
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
			childFeature == UMLPackage.Literals.CLASSIFIER__COLLABORATION_USE;

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
