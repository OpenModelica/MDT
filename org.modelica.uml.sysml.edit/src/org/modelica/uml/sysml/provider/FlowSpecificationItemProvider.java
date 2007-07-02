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

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.InterfaceItemProvider;

import org.modelica.uml.sysml.FlowSpecification;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * This is the item provider adapter for a {@link org.modelica.uml.sysml.FlowSpecification} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowSpecificationItemProvider
	extends InterfaceItemProvider
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
	public FlowSpecificationItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(SysmlPackage.Literals.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY);
		}
		return childrenFeatures;
	}

	/**
	 * This returns FlowSpecification.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FlowSpecification"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((FlowSpecification)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_FlowSpecification_type") :
			getString("_UI_FlowSpecification_type") + " " + label;
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

		switch (notification.getFeatureID(FlowSpecification.class)) {
			case SysmlPackage.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE,
				 SysmlFactory.eINSTANCE.createBlockProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE,
				 SysmlFactory.eINSTANCE.createDistributedProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE,
				 SysmlFactory.eINSTANCE.createFlowPort()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE,
				 SysmlFactory.eINSTANCE.createFlowProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE,
				 SysmlFactory.eINSTANCE.createModelicaProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE,
				 SysmlFactory.eINSTANCE.createModelicaConnectorProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE,
				 SysmlFactory.eINSTANCE.createModelicaEquationProperty()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createBlock()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createValueType()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createUnit()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createDimension()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createFlowSpecification()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaClassifier()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaType()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaPrimitiveType()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaReal()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaInteger()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaString()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaClass()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaModel()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaBlock()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaConnector()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaRecord()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaFunction()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER,
				 SysmlFactory.eINSTANCE.createModelicaComposition()));

		newChildDescriptors.add
			(createChildParameter
				(SysmlPackage.Literals.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY,
				 SysmlFactory.eINSTANCE.createFlowProperty()));
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
			childFeature == UMLPackage.Literals.INTERFACE__NESTED_CLASSIFIER ||
			childFeature == UMLPackage.Literals.INTERFACE__OWNED_ATTRIBUTE ||
			childFeature == SysmlPackage.Literals.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY ||
			childFeature == UMLPackage.Literals.INTERFACE__PROTOCOL;

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
