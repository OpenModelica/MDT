/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;

import org.eclipse.uml2.uml.Classifier;

import org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl;

import org.eclipse.uml2.uml.internal.impl.DataTypeImpl;

import org.modelica.uml.sysml.ModelicaBoolean;
import org.modelica.uml.sysml.ModelicaInteger;
import org.modelica.uml.sysml.ModelicaReal;
import org.modelica.uml.sysml.ModelicaString;
import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modelica Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaTypeImpl#getTypeData <em>Type Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelicaTypeImpl extends ModelicaClassifierImpl implements ModelicaType {
	/**
	 * The cached value of the '{@link #getTypeData() <em>Type Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeData()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveType typeData = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelicaTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SysmlPackage.Literals.MODELICA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType getTypeData() {
		return typeData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeData(PrimitiveType newTypeData, NotificationChain msgs) {
		PrimitiveType oldTypeData = typeData;
		typeData = newTypeData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_TYPE__TYPE_DATA, oldTypeData, newTypeData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setTypeData(PrimitiveType newTypeData) {
		
		if(newTypeData == null)
			return;
		
		Type typeInstance = newTypeData;
		if (newTypeData instanceof PrimitiveType
				&& newTypeData.getName().equals("Real")) {
			if(!(typeInstance instanceof ModelicaReal)){
			ModelicaReal realType = SysmlFactory.eINSTANCE.createModelicaReal();
			typeInstance = newTypeData.getModel().createOwnedType("Real",
					realType.eClass());
			}
		} else if (newTypeData instanceof PrimitiveType
				&& newTypeData.getName().equals("String")) {
			if(!(typeInstance instanceof ModelicaString)){
			ModelicaString stringType = SysmlFactory.eINSTANCE
					.createModelicaString();
			typeInstance = newTypeData.getModel().createOwnedType("String",
					stringType.eClass());
			}
		} else if (newTypeData instanceof PrimitiveType
				&& newTypeData.getName().equals("Boolean")) {
			if(!(typeInstance instanceof ModelicaBoolean)){
			ModelicaBoolean booleanType = SysmlFactory.eINSTANCE
					.createModelicaBoolean();
			typeInstance = newTypeData.getModel().createOwnedType("Boolean",
					booleanType.eClass());
			}
		} else if (newTypeData instanceof PrimitiveType
				&& newTypeData.getName().equals("Integer")) {
			if(!(typeInstance instanceof ModelicaInteger)){
			ModelicaInteger integerType = SysmlFactory.eINSTANCE
					.createModelicaInteger();
			typeInstance = integerType;
			typeInstance = newTypeData.getModel().createOwnedType("Integer",
				integerType.eClass());
			}
		}

		
		if (typeInstance != typeData) {
			NotificationChain msgs = null;
			/*if (typeData != null)
				msgs = ((InternalEObject)typeData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SysmlPackage.MODELICA_TYPE__TYPE_DATA, null, msgs);
			if (typeInstance != null)
				msgs = ((InternalEObject)newTypeData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SysmlPackage.MODELICA_TYPE__TYPE_DATA, null, msgs);*/
			msgs = basicSetTypeData((PrimitiveType)typeInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_TYPE__TYPE_DATA, typeInstance, typeInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SysmlPackage.MODELICA_TYPE__TYPE_DATA:
				return basicSetTypeData(null, msgs);
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
			case SysmlPackage.MODELICA_TYPE__TYPE_DATA:
				return getTypeData();
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
			case SysmlPackage.MODELICA_TYPE__TYPE_DATA:
				setTypeData((PrimitiveType)newValue);
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
			case SysmlPackage.MODELICA_TYPE__TYPE_DATA:
				setTypeData((PrimitiveType)null);
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
			case SysmlPackage.MODELICA_TYPE__TYPE_DATA:
				return typeData != null;
		}
		return super.eIsSet(featureID);
	}

} //ModelicaTypeImpl