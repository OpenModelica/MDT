/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unit Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PhysicalQuantities.UnitTransformation#getMultiplier <em>Multiplier</em>}</li>
 *   <li>{@link PhysicalQuantities.UnitTransformation#getOffset <em>Offset</em>}</li>
 * </ul>
 * </p>
 *
 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getUnitTransformation()
 * @model
 * @generated
 */
public interface UnitTransformation extends EObject {
	/**
	 * Returns the value of the '<em><b>Multiplier</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplier</em>' attribute.
	 * @see #setMultiplier(double)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getUnitTransformation_Multiplier()
	 * @model default="1" required="true"
	 * @generated
	 */
	double getMultiplier();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.UnitTransformation#getMultiplier <em>Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplier</em>' attribute.
	 * @see #getMultiplier()
	 * @generated
	 */
	void setMultiplier(double value);

	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(double)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getUnitTransformation_Offset()
	 * @model default="0" required="true"
	 * @generated
	 */
	double getOffset();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.UnitTransformation#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	UnitTransformation getInverseTransformation();

} // UnitTransformation
