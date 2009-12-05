/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alternative Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PhysicalQuantities.AlternativeUnit#getBasisUnit <em>Basis Unit</em>}</li>
 *   <li>{@link PhysicalQuantities.AlternativeUnit#getTransformationToBasisUnit <em>Transformation To Basis Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getAlternativeUnit()
 * @model
 * @generated
 */
public interface AlternativeUnit extends BaseUnit {
	/**
	 * Returns the value of the '<em><b>Basis Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Basis Unit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Basis Unit</em>' containment reference.
	 * @see #setBasisUnit(Unit)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getAlternativeUnit_BasisUnit()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Unit getBasisUnit();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.AlternativeUnit#getBasisUnit <em>Basis Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Basis Unit</em>' containment reference.
	 * @see #getBasisUnit()
	 * @generated
	 */
	void setBasisUnit(Unit value);

	/**
	 * Returns the value of the '<em><b>Transformation To Basis Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformation To Basis Unit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformation To Basis Unit</em>' containment reference.
	 * @see #setTransformationToBasisUnit(UnitTransformation)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getAlternativeUnit_TransformationToBasisUnit()
	 * @model containment="true" required="true"
	 * @generated
	 */
	UnitTransformation getTransformationToBasisUnit();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.AlternativeUnit#getTransformationToBasisUnit <em>Transformation To Basis Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformation To Basis Unit</em>' containment reference.
	 * @see #getTransformationToBasisUnit()
	 * @generated
	 */
	void setTransformationToBasisUnit(UnitTransformation value);

} // AlternativeUnit
