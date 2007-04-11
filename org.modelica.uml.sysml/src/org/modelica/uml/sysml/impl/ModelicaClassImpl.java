/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.uml2.uml.Type;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.operations.ModelicaTypeOperations;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Modelica Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaClassImpl#isIsEncapsulatedClass <em>Is Encapsulated Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelicaClassImpl extends BlockImpl implements ModelicaClass {
	/**
	 * The default value of the '{@link #isIsEncapsulatedClass() <em>Is Encapsulated Class</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isIsEncapsulatedClass()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ENCAPSULATED_CLASS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEncapsulatedClass() <em>Is Encapsulated Class</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isIsEncapsulatedClass()
	 * @generated
	 * @ordered
	 */
	protected boolean isEncapsulatedClass = IS_ENCAPSULATED_CLASS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelicaClassImpl() {
		super();
	}

	public ModelicaComposition createModelicaComposition(String end1Name,
			Type end1Type, String end2Name) {
		
		return ModelicaTypeOperations.createModelicaComposition(this, end1Name,
				end1Type, end2Name);
	}
	
	public List getCompositionProperties() {
		return ModelicaTypeOperations.getCompositionProperties(this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SysmlPackage.Literals.MODELICA_CLASS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEncapsulatedClass() {
		return isEncapsulatedClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEncapsulatedClass(boolean newIsEncapsulatedClass) {
		boolean oldIsEncapsulatedClass = isEncapsulatedClass;
		isEncapsulatedClass = newIsEncapsulatedClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_CLASS__IS_ENCAPSULATED_CLASS, oldIsEncapsulatedClass, isEncapsulatedClass));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SysmlPackage.MODELICA_CLASS__IS_ENCAPSULATED_CLASS:
				return isIsEncapsulatedClass() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SysmlPackage.MODELICA_CLASS__IS_ENCAPSULATED_CLASS:
				setIsEncapsulatedClass(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SysmlPackage.MODELICA_CLASS__IS_ENCAPSULATED_CLASS:
				setIsEncapsulatedClass(IS_ENCAPSULATED_CLASS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SysmlPackage.MODELICA_CLASS__IS_ENCAPSULATED_CLASS:
				return isEncapsulatedClass != IS_ENCAPSULATED_CLASS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isEncapsulatedClass: ");
		result.append(isEncapsulatedClass);
		result.append(')');
		return result.toString();
	}

} // ModelicaClassImpl
