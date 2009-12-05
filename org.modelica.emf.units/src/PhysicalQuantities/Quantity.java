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
 * A representation of the model object '<em><b>Quantity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PhysicalQuantities.Quantity#getUnit <em>Unit</em>}</li>
 *   <li>{@link PhysicalQuantities.Quantity#getAlternativeUnits <em>Alternative Units</em>}</li>
 *   <li>{@link PhysicalQuantities.Quantity#getDerivedUnits <em>Derived Units</em>}</li>
 *   <li>{@link PhysicalQuantities.Quantity#getName <em>Name</em>}</li>
 *   <li>{@link PhysicalQuantities.Quantity#getDescriptiveName <em>Descriptive Name</em>}</li>
 *   <li>{@link PhysicalQuantities.Quantity#getSymbol <em>Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getQuantity()
 * @model
 * @generated
 */
public interface Quantity extends EObject {
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
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getQuantity_Unit()
	 * @model containment="true"
	 * @generated
	 */
	Unit getUnit();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.Quantity#getUnit <em>Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' containment reference.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(Unit value);

	/**
	 * Returns the value of the '<em><b>Alternative Units</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternative Units</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternative Units</em>' containment reference.
	 * @see #setAlternativeUnits(AlternativeUnit)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getQuantity_AlternativeUnits()
	 * @model containment="true"
	 * @generated
	 */
	AlternativeUnit getAlternativeUnits();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.Quantity#getAlternativeUnits <em>Alternative Units</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alternative Units</em>' containment reference.
	 * @see #getAlternativeUnits()
	 * @generated
	 */
	void setAlternativeUnits(AlternativeUnit value);

	/**
	 * Returns the value of the '<em><b>Derived Units</b></em>' containment reference list.
	 * The list contents are of type {@link PhysicalQuantities.DerivedUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived Units</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived Units</em>' containment reference list.
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getQuantity_DerivedUnits()
	 * @model containment="true"
	 * @generated
	 */
	EList<DerivedUnit> getDerivedUnits();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getQuantity_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.Quantity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Descriptive Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptive Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptive Name</em>' attribute.
	 * @see #setDescriptiveName(String)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getQuantity_DescriptiveName()
	 * @model
	 * @generated
	 */
	String getDescriptiveName();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.Quantity#getDescriptiveName <em>Descriptive Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Descriptive Name</em>' attribute.
	 * @see #getDescriptiveName()
	 * @generated
	 */
	void setDescriptiveName(String value);

	/**
	 * Returns the value of the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Symbol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Symbol</em>' attribute.
	 * @see #setSymbol(String)
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getQuantity_Symbol()
	 * @model
	 * @generated
	 */
	String getSymbol();

	/**
	 * Sets the value of the '{@link PhysicalQuantities.Quantity#getSymbol <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbol</em>' attribute.
	 * @see #getSymbol()
	 * @generated
	 */
	void setSymbol(String value);

} // Quantity
