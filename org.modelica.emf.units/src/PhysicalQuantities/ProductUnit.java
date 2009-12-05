/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PhysicalQuantities.ProductUnit#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getProductUnit()
 * @model
 * @generated
 */
public interface ProductUnit extends DerivedUnit {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference.
	 * @see #setElements(ProductUnitElement)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getProductUnit_Elements()
	 * @model containment="true"
	 * @generated
	 */
	ProductUnitElement getElements();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.ProductUnit#getElements <em>Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elements</em>' containment reference.
	 * @see #getElements()
	 * @generated
	 */
	void setElements(ProductUnitElement value);

} // ProductUnit
