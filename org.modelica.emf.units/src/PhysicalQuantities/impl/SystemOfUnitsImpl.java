/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities.impl;

import PhysicalQuantities.BaseUnit;
import PhysicalQuantities.PhysicalQuantitiesPackage;
import PhysicalQuantities.Quantity;
import PhysicalQuantities.SystemOfUnits;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Of Units</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PhysicalQuantities.impl.SystemOfUnitsImpl#getBaseUnits <em>Base Units</em>}</li>
 *   <li>{@link PhysicalQuantities.impl.SystemOfUnitsImpl#getQuantities <em>Quantities</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemOfUnitsImpl extends EObjectImpl implements SystemOfUnits {
	/**
	 * The cached value of the '{@link #getBaseUnits() <em>Base Units</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseUnits()
	 * @generated
	 * @ordered
	 */
	protected EList<BaseUnit> baseUnits;

	/**
	 * The cached value of the '{@link #getQuantities() <em>Quantities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantities()
	 * @generated
	 * @ordered
	 */
	protected EList<Quantity> quantities;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemOfUnitsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysicalQuantitiesPackage.Literals.SYSTEM_OF_UNITS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BaseUnit> getBaseUnits() {
		if (baseUnits == null) {
			baseUnits = new EObjectContainmentEList<BaseUnit>(BaseUnit.class, this, PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__BASE_UNITS);
		}
		return baseUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Quantity> getQuantities() {
		if (quantities == null) {
			quantities = new EObjectContainmentEList<Quantity>(Quantity.class, this, PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__QUANTITIES);
		}
		return quantities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__BASE_UNITS:
				return ((InternalEList<?>)getBaseUnits()).basicRemove(otherEnd, msgs);
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__QUANTITIES:
				return ((InternalEList<?>)getQuantities()).basicRemove(otherEnd, msgs);
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
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__BASE_UNITS:
				return getBaseUnits();
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__QUANTITIES:
				return getQuantities();
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
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__BASE_UNITS:
				getBaseUnits().clear();
				getBaseUnits().addAll((Collection<? extends BaseUnit>)newValue);
				return;
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__QUANTITIES:
				getQuantities().clear();
				getQuantities().addAll((Collection<? extends Quantity>)newValue);
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
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__BASE_UNITS:
				getBaseUnits().clear();
				return;
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__QUANTITIES:
				getQuantities().clear();
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
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__BASE_UNITS:
				return baseUnits != null && !baseUnits.isEmpty();
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS__QUANTITIES:
				return quantities != null && !quantities.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SystemOfUnitsImpl
