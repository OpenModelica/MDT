/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities.impl;

import PhysicalQuantities.PhysicalQuantitiesPackage;
import PhysicalQuantities.ProductUnitElement;
import PhysicalQuantities.Unit;
import PhysicalQuantities.UnitPrefixes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Unit Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PhysicalQuantities.impl.ProductUnitElementImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.ProductUnitElementImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.ProductUnitElementImpl#getPowerNumerator <em>Power Numerator</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.ProductUnitElementImpl#getPowerDenominator <em>Power Denominator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProductUnitElementImpl extends EObjectImpl implements ProductUnitElement {
	/**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected Unit unit;

	/**
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final UnitPrefixes PREFIX_EDEFAULT = UnitPrefixes.NONE;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected UnitPrefixes prefix = PREFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getPowerNumerator() <em>Power Numerator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerNumerator()
	 * @generated
	 * @ordered
	 */
	protected static final int POWER_NUMERATOR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPowerNumerator() <em>Power Numerator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerNumerator()
	 * @generated
	 * @ordered
	 */
	protected int powerNumerator = POWER_NUMERATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getPowerDenominator() <em>Power Denominator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerDenominator()
	 * @generated
	 * @ordered
	 */
	protected static final int POWER_DENOMINATOR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPowerDenominator() <em>Power Denominator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowerDenominator()
	 * @generated
	 * @ordered
	 */
	protected int powerDenominator = POWER_DENOMINATOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductUnitElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysicalQuantitiesPackage.Literals.PRODUCT_UNIT_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUnit(Unit newUnit, NotificationChain msgs) {
		Unit oldUnit = unit;
		unit = newUnit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT, oldUnit, newUnit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnit(Unit newUnit) {
		if (newUnit != unit) {
			NotificationChain msgs = null;
			if (unit != null)
				msgs = ((InternalEObject)unit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT, null, msgs);
			if (newUnit != null)
				msgs = ((InternalEObject)newUnit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT, null, msgs);
			msgs = basicSetUnit(newUnit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT, newUnit, newUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitPrefixes getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefix(UnitPrefixes newPrefix) {
		UnitPrefixes oldPrefix = prefix;
		prefix = newPrefix == null ? PREFIX_EDEFAULT : newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPowerNumerator() {
		return powerNumerator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPowerNumerator(int newPowerNumerator) {
		int oldPowerNumerator = powerNumerator;
		powerNumerator = newPowerNumerator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR, oldPowerNumerator, powerNumerator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPowerDenominator() {
		return powerDenominator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPowerDenominator(int newPowerDenominator) {
		int oldPowerDenominator = powerDenominator;
		powerDenominator = newPowerDenominator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR, oldPowerDenominator, powerDenominator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT:
				return basicSetUnit(null, msgs);
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
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT:
				return getUnit();
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__PREFIX:
				return getPrefix();
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR:
				return getPowerNumerator();
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR:
				return getPowerDenominator();
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
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT:
				setUnit((Unit)newValue);
				return;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__PREFIX:
				setPrefix((UnitPrefixes)newValue);
				return;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR:
				setPowerNumerator((Integer)newValue);
				return;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR:
				setPowerDenominator((Integer)newValue);
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
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT:
				setUnit((Unit)null);
				return;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR:
				setPowerNumerator(POWER_NUMERATOR_EDEFAULT);
				return;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR:
				setPowerDenominator(POWER_DENOMINATOR_EDEFAULT);
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
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__UNIT:
				return unit != null;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__PREFIX:
				return prefix != PREFIX_EDEFAULT;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR:
				return powerNumerator != POWER_NUMERATOR_EDEFAULT;
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR:
				return powerDenominator != POWER_DENOMINATOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (prefix: ");
		result.append(prefix);
		result.append(", powerNumerator: ");
		result.append(powerNumerator);
		result.append(", powerDenominator: ");
		result.append(powerDenominator);
		result.append(')');
		return result.toString();
	}

} //ProductUnitElementImpl
