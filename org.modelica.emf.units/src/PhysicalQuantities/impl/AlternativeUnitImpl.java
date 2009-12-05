/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities.impl;

import PhysicalQuantities.AlternativeUnit;
import PhysicalQuantities.PhysicalQuantitiesPackage;
import PhysicalQuantities.Unit;
import PhysicalQuantities.UnitTransformation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Alternative Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PhysicalQuantities.impl.AlternativeUnitImpl#getBasisUnit <em>Basis Unit</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.AlternativeUnitImpl#getTransformationToBasisUnit <em>Transformation To Basis Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AlternativeUnitImpl extends BaseUnitImpl implements AlternativeUnit {
	/**
	 * The cached value of the '{@link #getBasisUnit() <em>Basis Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBasisUnit()
	 * @generated
	 * @ordered
	 */
	protected Unit basisUnit;

	/**
	 * The cached value of the '{@link #getTransformationToBasisUnit() <em>Transformation To Basis Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationToBasisUnit()
	 * @generated
	 * @ordered
	 */
	protected UnitTransformation transformationToBasisUnit;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlternativeUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysicalQuantitiesPackage.Literals.ALTERNATIVE_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Unit getBasisUnit() {
		return basisUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBasisUnit(Unit newBasisUnit, NotificationChain msgs) {
		Unit oldBasisUnit = basisUnit;
		basisUnit = newBasisUnit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT, oldBasisUnit, newBasisUnit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBasisUnit(Unit newBasisUnit) {
		if (newBasisUnit != basisUnit) {
			NotificationChain msgs = null;
			if (basisUnit != null)
				msgs = ((InternalEObject)basisUnit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT, null, msgs);
			if (newBasisUnit != null)
				msgs = ((InternalEObject)newBasisUnit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT, null, msgs);
			msgs = basicSetBasisUnit(newBasisUnit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT, newBasisUnit, newBasisUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitTransformation getTransformationToBasisUnit() {
		return transformationToBasisUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransformationToBasisUnit(UnitTransformation newTransformationToBasisUnit, NotificationChain msgs) {
		UnitTransformation oldTransformationToBasisUnit = transformationToBasisUnit;
		transformationToBasisUnit = newTransformationToBasisUnit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT, oldTransformationToBasisUnit, newTransformationToBasisUnit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformationToBasisUnit(UnitTransformation newTransformationToBasisUnit) {
		if (newTransformationToBasisUnit != transformationToBasisUnit) {
			NotificationChain msgs = null;
			if (transformationToBasisUnit != null)
				msgs = ((InternalEObject)transformationToBasisUnit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT, null, msgs);
			if (newTransformationToBasisUnit != null)
				msgs = ((InternalEObject)newTransformationToBasisUnit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT, null, msgs);
			msgs = basicSetTransformationToBasisUnit(newTransformationToBasisUnit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT, newTransformationToBasisUnit, newTransformationToBasisUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT:
				return basicSetBasisUnit(null, msgs);
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT:
				return basicSetTransformationToBasisUnit(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT:
				return getBasisUnit();
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT:
				return getTransformationToBasisUnit();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT:
				setBasisUnit((Unit)newValue);
				return;
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT:
				setTransformationToBasisUnit((UnitTransformation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT:
				setBasisUnit((Unit)null);
				return;
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT:
				setTransformationToBasisUnit((UnitTransformation)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__BASIS_UNIT:
				return basisUnit != null;
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT:
				return transformationToBasisUnit != null;
		}
		return super.eIsSet(featureID);
	}

} //AlternativeUnitImpl
