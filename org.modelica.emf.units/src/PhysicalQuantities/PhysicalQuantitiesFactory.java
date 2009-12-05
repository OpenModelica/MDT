/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see PhysicalQuantities.PhysicalQuantitiesPackage
 * @generated
 */
public interface PhysicalQuantitiesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PhysicalQuantitiesFactory eINSTANCE = PhysicalQuantities.impl.PhysicalQuantitiesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Quantity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quantity</em>'.
	 * @generated
	 */
	Quantity createQuantity();

	/**
	 * Returns a new object of class '<em>Base Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Base Unit</em>'.
	 * @generated
	 */
	BaseUnit createBaseUnit();

	/**
	 * Returns a new object of class '<em>Alternative Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Alternative Unit</em>'.
	 * @generated
	 */
	AlternativeUnit createAlternativeUnit();

	/**
	 * Returns a new object of class '<em>Unit Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unit Transformation</em>'.
	 * @generated
	 */
	UnitTransformation createUnitTransformation();

	/**
	 * Returns a new object of class '<em>Derived Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derived Unit</em>'.
	 * @generated
	 */
	DerivedUnit createDerivedUnit();

	/**
	 * Returns a new object of class '<em>Product Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Unit</em>'.
	 * @generated
	 */
	ProductUnit createProductUnit();

	/**
	 * Returns a new object of class '<em>Product Unit Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Unit Element</em>'.
	 * @generated
	 */
	ProductUnitElement createProductUnitElement();

	/**
	 * Returns a new object of class '<em>System Of Units</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>System Of Units</em>'.
	 * @generated
	 */
	SystemOfUnits createSystemOfUnits();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PhysicalQuantitiesPackage getPhysicalQuantitiesPackage();

} //PhysicalQuantitiesFactory
