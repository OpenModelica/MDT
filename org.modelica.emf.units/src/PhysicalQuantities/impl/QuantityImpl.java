/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities.impl;

import PhysicalQuantities.AlternativeUnit;
import PhysicalQuantities.DerivedUnit;
import PhysicalQuantities.PhysicalQuantitiesPackage;
import PhysicalQuantities.Quantity;
import PhysicalQuantities.Unit;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quantity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PhysicalQuantities.impl.QuantityImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.QuantityImpl#getAlternativeUnits <em>Alternative Units</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.QuantityImpl#getDerivedUnits <em>Derived Units</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.QuantityImpl#getName <em>Name</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.QuantityImpl#getDescriptiveName <em>Descriptive Name</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.QuantityImpl#getSymbol <em>Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuantityImpl extends EObjectImpl implements Quantity {
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
	 * The cached value of the '{@link #getAlternativeUnits() <em>Alternative Units</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlternativeUnits()
	 * @generated
	 * @ordered
	 */
	protected AlternativeUnit alternativeUnits;

	/**
	 * The cached value of the '{@link #getDerivedUnits() <em>Derived Units</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedUnits()
	 * @generated
	 * @ordered
	 */
	protected EList<DerivedUnit> derivedUnits;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescriptiveName() <em>Descriptive Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptiveName()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTIVE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescriptiveName() <em>Descriptive Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptiveName()
	 * @generated
	 * @ordered
	 */
	protected String descriptiveName = DESCRIPTIVE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected String symbol = SYMBOL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuantityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysicalQuantitiesPackage.Literals.QUANTITY;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.QUANTITY__UNIT, oldUnit, newUnit);
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
				msgs = ((InternalEObject)unit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.QUANTITY__UNIT, null, msgs);
			if (newUnit != null)
				msgs = ((InternalEObject)newUnit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.QUANTITY__UNIT, null, msgs);
			msgs = basicSetUnit(newUnit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.QUANTITY__UNIT, newUnit, newUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlternativeUnit getAlternativeUnits() {
		return alternativeUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAlternativeUnits(AlternativeUnit newAlternativeUnits, NotificationChain msgs) {
		AlternativeUnit oldAlternativeUnits = alternativeUnits;
		alternativeUnits = newAlternativeUnits;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS, oldAlternativeUnits, newAlternativeUnits);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlternativeUnits(AlternativeUnit newAlternativeUnits) {
		if (newAlternativeUnits != alternativeUnits) {
			NotificationChain msgs = null;
			if (alternativeUnits != null)
				msgs = ((InternalEObject)alternativeUnits).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS, null, msgs);
			if (newAlternativeUnits != null)
				msgs = ((InternalEObject)newAlternativeUnits).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS, null, msgs);
			msgs = basicSetAlternativeUnits(newAlternativeUnits, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS, newAlternativeUnits, newAlternativeUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DerivedUnit> getDerivedUnits() {
		if (derivedUnits == null) {
			derivedUnits = new EObjectContainmentEList<DerivedUnit>(DerivedUnit.class, this, PhysicalQuantitiesPackage.QUANTITY__DERIVED_UNITS);
		}
		return derivedUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.QUANTITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescriptiveName() {
		return descriptiveName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptiveName(String newDescriptiveName) {
		String oldDescriptiveName = descriptiveName;
		descriptiveName = newDescriptiveName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.QUANTITY__DESCRIPTIVE_NAME, oldDescriptiveName, descriptiveName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSymbol(String newSymbol) {
		String oldSymbol = symbol;
		symbol = newSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysicalQuantitiesPackage.QUANTITY__SYMBOL, oldSymbol, symbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.QUANTITY__UNIT:
				return basicSetUnit(null, msgs);
			case PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS:
				return basicSetAlternativeUnits(null, msgs);
			case PhysicalQuantitiesPackage.QUANTITY__DERIVED_UNITS:
				return ((InternalEList<?>)getDerivedUnits()).basicRemove(otherEnd, msgs);
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
			case PhysicalQuantitiesPackage.QUANTITY__UNIT:
				return getUnit();
			case PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS:
				return getAlternativeUnits();
			case PhysicalQuantitiesPackage.QUANTITY__DERIVED_UNITS:
				return getDerivedUnits();
			case PhysicalQuantitiesPackage.QUANTITY__NAME:
				return getName();
			case PhysicalQuantitiesPackage.QUANTITY__DESCRIPTIVE_NAME:
				return getDescriptiveName();
			case PhysicalQuantitiesPackage.QUANTITY__SYMBOL:
				return getSymbol();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.QUANTITY__UNIT:
				setUnit((Unit)newValue);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS:
				setAlternativeUnits((AlternativeUnit)newValue);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__DERIVED_UNITS:
				getDerivedUnits().clear();
				getDerivedUnits().addAll((Collection<? extends DerivedUnit>)newValue);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__NAME:
				setName((String)newValue);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__DESCRIPTIVE_NAME:
				setDescriptiveName((String)newValue);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__SYMBOL:
				setSymbol((String)newValue);
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
			case PhysicalQuantitiesPackage.QUANTITY__UNIT:
				setUnit((Unit)null);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS:
				setAlternativeUnits((AlternativeUnit)null);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__DERIVED_UNITS:
				getDerivedUnits().clear();
				return;
			case PhysicalQuantitiesPackage.QUANTITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__DESCRIPTIVE_NAME:
				setDescriptiveName(DESCRIPTIVE_NAME_EDEFAULT);
				return;
			case PhysicalQuantitiesPackage.QUANTITY__SYMBOL:
				setSymbol(SYMBOL_EDEFAULT);
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
			case PhysicalQuantitiesPackage.QUANTITY__UNIT:
				return unit != null;
			case PhysicalQuantitiesPackage.QUANTITY__ALTERNATIVE_UNITS:
				return alternativeUnits != null;
			case PhysicalQuantitiesPackage.QUANTITY__DERIVED_UNITS:
				return derivedUnits != null && !derivedUnits.isEmpty();
			case PhysicalQuantitiesPackage.QUANTITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PhysicalQuantitiesPackage.QUANTITY__DESCRIPTIVE_NAME:
				return DESCRIPTIVE_NAME_EDEFAULT == null ? descriptiveName != null : !DESCRIPTIVE_NAME_EDEFAULT.equals(descriptiveName);
			case PhysicalQuantitiesPackage.QUANTITY__SYMBOL:
				return SYMBOL_EDEFAULT == null ? symbol != null : !SYMBOL_EDEFAULT.equals(symbol);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", descriptiveName: ");
		result.append(descriptiveName);
		result.append(", symbol: ");
		result.append(symbol);
		result.append(')');
		return result.toString();
	}

} //QuantityImpl
