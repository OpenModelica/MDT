/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.internal.impl.InterfaceImpl;

import org.modelica.uml.sysml.FlowProperty;
import org.modelica.uml.sysml.FlowSpecification;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.impl.FlowSpecificationImpl#getOwnedFlowProperty <em>Owned Flow Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FlowSpecificationImpl extends InterfaceImpl implements FlowSpecification {
	/**
	 * The cached value of the '{@link #getOwnedFlowProperty() <em>Owned Flow Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedFlowProperty()
	 * @generated
	 * @ordered
	 */
	protected EList ownedFlowProperty = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SysmlPackage.Literals.FLOW_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOwnedFlowProperty() {
		if (ownedFlowProperty == null) {
			ownedFlowProperty = new EObjectContainmentEList(FlowProperty.class, this, SysmlPackage.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY);
		}
		return ownedFlowProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SysmlPackage.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY:
				return ((InternalEList)getOwnedFlowProperty()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SysmlPackage.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY:
				return getOwnedFlowProperty();
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
			case SysmlPackage.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY:
				getOwnedFlowProperty().clear();
				getOwnedFlowProperty().addAll((Collection)newValue);
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
			case SysmlPackage.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY:
				getOwnedFlowProperty().clear();
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
			case SysmlPackage.FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY:
				return ownedFlowProperty != null && !ownedFlowProperty.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FlowSpecificationImpl