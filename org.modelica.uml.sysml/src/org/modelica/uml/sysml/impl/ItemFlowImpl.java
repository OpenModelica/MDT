/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.internal.impl.InformationFlowImpl;

import org.modelica.uml.sysml.BlockProperty;
import org.modelica.uml.sysml.ItemFlow;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.impl.ItemFlowImpl#getItemProperty <em>Item Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemFlowImpl extends InformationFlowImpl implements ItemFlow {
	/**
	 * The cached value of the '{@link #getItemProperty() <em>Item Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItemProperty()
	 * @generated
	 * @ordered
	 */
	protected BlockProperty itemProperty = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ItemFlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SysmlPackage.Literals.ITEM_FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockProperty getItemProperty() {
		if (itemProperty != null && itemProperty.eIsProxy()) {
			InternalEObject oldItemProperty = (InternalEObject)itemProperty;
			itemProperty = (BlockProperty)eResolveProxy(oldItemProperty);
			if (itemProperty != oldItemProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SysmlPackage.ITEM_FLOW__ITEM_PROPERTY, oldItemProperty, itemProperty));
			}
		}
		return itemProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockProperty basicGetItemProperty() {
		return itemProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItemProperty(BlockProperty newItemProperty) {
		BlockProperty oldItemProperty = itemProperty;
		itemProperty = newItemProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.ITEM_FLOW__ITEM_PROPERTY, oldItemProperty, itemProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SysmlPackage.ITEM_FLOW__ITEM_PROPERTY:
				if (resolve) return getItemProperty();
				return basicGetItemProperty();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SysmlPackage.ITEM_FLOW__ITEM_PROPERTY:
				setItemProperty((BlockProperty)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SysmlPackage.ITEM_FLOW__ITEM_PROPERTY:
				setItemProperty((BlockProperty)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SysmlPackage.ITEM_FLOW__ITEM_PROPERTY:
				return itemProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //ItemFlowImpl