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
 * A representation of the model object '<em><b>Product Unit Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PhysicalQuantities.ProductUnitElement#getUnit <em>Unit</em>}</li>
 *   <li>{@link PhysicalQuantities.ProductUnitElement#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link PhysicalQuantities.ProductUnitElement#getPowerNumerator <em>Power Numerator</em>}</li>
 *   <li>{@link PhysicalQuantities.ProductUnitElement#getPowerDenominator <em>Power Denominator</em>}</li>
 * </ul>
 * </p>
 *
 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getProductUnitElement()
 * @model
 * @generated
 */
public interface ProductUnitElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' containment reference.
	 * @see #setUnit(Unit)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getProductUnitElement_Unit()
	 * @model containment="true"
	 * @generated
	 */
	Unit getUnit();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.ProductUnitElement#getUnit <em>Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' containment reference.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(Unit value);

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * The default value is <code>"NONE"</code>.
	 * The literals are from the enumeration {@link PhysicalQuantities.UnitPrefixes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see PhysicalQuantities.UnitPrefixes
	 * @see #setPrefix(UnitPrefixes)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getProductUnitElement_Prefix()
	 * @model default="NONE" required="true"
	 * @generated
	 */
	UnitPrefixes getPrefix();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.ProductUnitElement#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see PhysicalQuantities.UnitPrefixes
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(UnitPrefixes value);

	/**
	 * Returns the value of the '<em><b>Power Numerator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Power Numerator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Power Numerator</em>' attribute.
	 * @see #setPowerNumerator(int)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getProductUnitElement_PowerNumerator()
	 * @model
	 * @generated
	 */
	int getPowerNumerator();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.ProductUnitElement#getPowerNumerator <em>Power Numerator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power Numerator</em>' attribute.
	 * @see #getPowerNumerator()
	 * @generated
	 */
	void setPowerNumerator(int value);

	/**
	 * Returns the value of the '<em><b>Power Denominator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Power Denominator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Power Denominator</em>' attribute.
	 * @see #setPowerDenominator(int)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getProductUnitElement_PowerDenominator()
	 * @model
	 * @generated
	 */
	int getPowerDenominator();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.ProductUnitElement#getPowerDenominator <em>Power Denominator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power Denominator</em>' attribute.
	 * @see #getPowerDenominator()
	 * @generated
	 */
	void setPowerDenominator(int value);

} // ProductUnitElement
