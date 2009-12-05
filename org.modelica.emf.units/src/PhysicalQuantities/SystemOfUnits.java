/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Of Units</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PhysicalQuantities.SystemOfUnits#getBaseUnits <em>Base Units</em>}</li>
 *   <li>{@link PhysicalQuantities.SystemOfUnits#getQuantities <em>Quantities</em>}</li>
 * </ul>
 * </p>
 *
 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getSystemOfUnits()
 * @model
 * @generated
 */
public interface SystemOfUnits extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Units</b></em>' containment reference list.
	 * The list contents are of type {@link PhysicalQuantities.BaseUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Units</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Units</em>' containment reference list.
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getSystemOfUnits_BaseUnits()
	 * @model containment="true" lower="3"
	 * @generated
	 */
	EList<BaseUnit> getBaseUnits();

	/**
	 * Returns the value of the '<em><b>Quantities</b></em>' containment reference list.
	 * The list contents are of type {@link PhysicalQuantities.Quantity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantities</em>' containment reference list.
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getSystemOfUnits_Quantities()
	 * @model containment="true"
	 * @generated
	 */
	EList<Quantity> getQuantities();

} // SystemOfUnits
