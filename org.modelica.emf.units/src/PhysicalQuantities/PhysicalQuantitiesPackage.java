/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see PhysicalQuantities.PhysicalQuantitiesFactory
 * @model kind="package"
 * @generated
 */
public interface PhysicalQuantitiesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "PhysicalQuantities";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.openmodelica.org/openmodelica.properties/3.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ModelicaProperties";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PhysicalQuantitiesPackage eINSTANCE = PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl.init();

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.QuantityImpl <em>Quantity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.QuantityImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getQuantity()
	 * @generated
	 */
	int QUANTITY = 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY__UNIT = 0;

	/**
	 * The feature id for the '<em><b>Alternative Units</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY__ALTERNATIVE_UNITS = 1;

	/**
	 * The feature id for the '<em><b>Derived Units</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY__DERIVED_UNITS = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY__NAME = 3;

	/**
	 * The feature id for the '<em><b>Descriptive Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY__DESCRIPTIVE_NAME = 4;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY__SYMBOL = 5;

	/**
	 * The number of structural features of the '<em>Quantity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTITY_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.UnitImpl <em>Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.UnitImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getUnit()
	 * @generated
	 */
	int UNIT = 1;

	/**
	 * The number of structural features of the '<em>Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.BaseUnitImpl <em>Base Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.BaseUnitImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getBaseUnit()
	 * @generated
	 */
	int BASE_UNIT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_UNIT__NAME = UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_UNIT__SYMBOL = UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Base Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_UNIT_FEATURE_COUNT = UNIT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.AlternativeUnitImpl <em>Alternative Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.AlternativeUnitImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getAlternativeUnit()
	 * @generated
	 */
	int ALTERNATIVE_UNIT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_UNIT__NAME = BASE_UNIT__NAME;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_UNIT__SYMBOL = BASE_UNIT__SYMBOL;

	/**
	 * The feature id for the '<em><b>Basis Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_UNIT__BASIS_UNIT = BASE_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transformation To Basis Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT = BASE_UNIT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Alternative Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_UNIT_FEATURE_COUNT = BASE_UNIT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.UnitTransformationImpl <em>Unit Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.UnitTransformationImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getUnitTransformation()
	 * @generated
	 */
	int UNIT_TRANSFORMATION = 4;

	/**
	 * The feature id for the '<em><b>Multiplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_TRANSFORMATION__MULTIPLIER = 0;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_TRANSFORMATION__OFFSET = 1;

	/**
	 * The number of structural features of the '<em>Unit Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_TRANSFORMATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.DerivedUnitImpl <em>Derived Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.DerivedUnitImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getDerivedUnit()
	 * @generated
	 */
	int DERIVED_UNIT = 5;

	/**
	 * The number of structural features of the '<em>Derived Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_UNIT_FEATURE_COUNT = UNIT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.ProductUnitImpl <em>Product Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.ProductUnitImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getProductUnit()
	 * @generated
	 */
	int PRODUCT_UNIT = 6;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_UNIT__ELEMENTS = DERIVED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Product Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_UNIT_FEATURE_COUNT = DERIVED_UNIT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.ProductUnitElementImpl <em>Product Unit Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.ProductUnitElementImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getProductUnitElement()
	 * @generated
	 */
	int PRODUCT_UNIT_ELEMENT = 7;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_UNIT_ELEMENT__UNIT = 0;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_UNIT_ELEMENT__PREFIX = 1;

	/**
	 * The feature id for the '<em><b>Power Numerator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR = 2;

	/**
	 * The feature id for the '<em><b>Power Denominator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR = 3;

	/**
	 * The number of structural features of the '<em>Product Unit Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_UNIT_ELEMENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.impl.SystemOfUnitsImpl <em>System Of Units</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.impl.SystemOfUnitsImpl
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getSystemOfUnits()
	 * @generated
	 */
	int SYSTEM_OF_UNITS = 8;

	/**
	 * The feature id for the '<em><b>Base Units</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__BASE_UNITS = 0;

	/**
	 * The feature id for the '<em><b>Quantities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS__QUANTITIES = 1;

	/**
	 * The number of structural features of the '<em>System Of Units</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OF_UNITS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link PhysicalQuantities.UnitPrefixes <em>Unit Prefixes</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PhysicalQuantities.UnitPrefixes
	 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getUnitPrefixes()
	 * @generated
	 */
	int UNIT_PREFIXES = 9;


	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.Quantity <em>Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quantity</em>'.
	 * @see PhysicalQuantities.Quantity
	 * @generated
	 */
	EClass getQuantity();

	/**
	 * Returns the meta object for the containment reference '{@link PhysicalQuantities.Quantity#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Unit</em>'.
	 * @see PhysicalQuantities.Quantity#getUnit()
	 * @see #getQuantity()
	 * @generated
	 */
	EReference getQuantity_Unit();

	/**
	 * Returns the meta object for the containment reference '{@link PhysicalQuantities.Quantity#getAlternativeUnits <em>Alternative Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Alternative Units</em>'.
	 * @see PhysicalQuantities.Quantity#getAlternativeUnits()
	 * @see #getQuantity()
	 * @generated
	 */
	EReference getQuantity_AlternativeUnits();

	/**
	 * Returns the meta object for the containment reference list '{@link PhysicalQuantities.Quantity#getDerivedUnits <em>Derived Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Derived Units</em>'.
	 * @see PhysicalQuantities.Quantity#getDerivedUnits()
	 * @see #getQuantity()
	 * @generated
	 */
	EReference getQuantity_DerivedUnits();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.Quantity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see PhysicalQuantities.Quantity#getName()
	 * @see #getQuantity()
	 * @generated
	 */
	EAttribute getQuantity_Name();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.Quantity#getDescriptiveName <em>Descriptive Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Descriptive Name</em>'.
	 * @see PhysicalQuantities.Quantity#getDescriptiveName()
	 * @see #getQuantity()
	 * @generated
	 */
	EAttribute getQuantity_DescriptiveName();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.Quantity#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see PhysicalQuantities.Quantity#getSymbol()
	 * @see #getQuantity()
	 * @generated
	 */
	EAttribute getQuantity_Symbol();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit</em>'.
	 * @see PhysicalQuantities.Unit
	 * @generated
	 */
	EClass getUnit();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.BaseUnit <em>Base Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Unit</em>'.
	 * @see PhysicalQuantities.BaseUnit
	 * @generated
	 */
	EClass getBaseUnit();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.BaseUnit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see PhysicalQuantities.BaseUnit#getName()
	 * @see #getBaseUnit()
	 * @generated
	 */
	EAttribute getBaseUnit_Name();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.BaseUnit#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see PhysicalQuantities.BaseUnit#getSymbol()
	 * @see #getBaseUnit()
	 * @generated
	 */
	EAttribute getBaseUnit_Symbol();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.AlternativeUnit <em>Alternative Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alternative Unit</em>'.
	 * @see PhysicalQuantities.AlternativeUnit
	 * @generated
	 */
	EClass getAlternativeUnit();

	/**
	 * Returns the meta object for the containment reference '{@link PhysicalQuantities.AlternativeUnit#getBasisUnit <em>Basis Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basis Unit</em>'.
	 * @see PhysicalQuantities.AlternativeUnit#getBasisUnit()
	 * @see #getAlternativeUnit()
	 * @generated
	 */
	EReference getAlternativeUnit_BasisUnit();

	/**
	 * Returns the meta object for the containment reference '{@link PhysicalQuantities.AlternativeUnit#getTransformationToBasisUnit <em>Transformation To Basis Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transformation To Basis Unit</em>'.
	 * @see PhysicalQuantities.AlternativeUnit#getTransformationToBasisUnit()
	 * @see #getAlternativeUnit()
	 * @generated
	 */
	EReference getAlternativeUnit_TransformationToBasisUnit();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.UnitTransformation <em>Unit Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit Transformation</em>'.
	 * @see PhysicalQuantities.UnitTransformation
	 * @generated
	 */
	EClass getUnitTransformation();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.UnitTransformation#getMultiplier <em>Multiplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplier</em>'.
	 * @see PhysicalQuantities.UnitTransformation#getMultiplier()
	 * @see #getUnitTransformation()
	 * @generated
	 */
	EAttribute getUnitTransformation_Multiplier();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.UnitTransformation#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see PhysicalQuantities.UnitTransformation#getOffset()
	 * @see #getUnitTransformation()
	 * @generated
	 */
	EAttribute getUnitTransformation_Offset();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.DerivedUnit <em>Derived Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Unit</em>'.
	 * @see PhysicalQuantities.DerivedUnit
	 * @generated
	 */
	EClass getDerivedUnit();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.ProductUnit <em>Product Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Unit</em>'.
	 * @see PhysicalQuantities.ProductUnit
	 * @generated
	 */
	EClass getProductUnit();

	/**
	 * Returns the meta object for the containment reference '{@link PhysicalQuantities.ProductUnit#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Elements</em>'.
	 * @see PhysicalQuantities.ProductUnit#getElements()
	 * @see #getProductUnit()
	 * @generated
	 */
	EReference getProductUnit_Elements();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.ProductUnitElement <em>Product Unit Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Unit Element</em>'.
	 * @see PhysicalQuantities.ProductUnitElement
	 * @generated
	 */
	EClass getProductUnitElement();

	/**
	 * Returns the meta object for the containment reference '{@link PhysicalQuantities.ProductUnitElement#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Unit</em>'.
	 * @see PhysicalQuantities.ProductUnitElement#getUnit()
	 * @see #getProductUnitElement()
	 * @generated
	 */
	EReference getProductUnitElement_Unit();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.ProductUnitElement#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see PhysicalQuantities.ProductUnitElement#getPrefix()
	 * @see #getProductUnitElement()
	 * @generated
	 */
	EAttribute getProductUnitElement_Prefix();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.ProductUnitElement#getPowerNumerator <em>Power Numerator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power Numerator</em>'.
	 * @see PhysicalQuantities.ProductUnitElement#getPowerNumerator()
	 * @see #getProductUnitElement()
	 * @generated
	 */
	EAttribute getProductUnitElement_PowerNumerator();

	/**
	 * Returns the meta object for the attribute '{@link PhysicalQuantities.ProductUnitElement#getPowerDenominator <em>Power Denominator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power Denominator</em>'.
	 * @see PhysicalQuantities.ProductUnitElement#getPowerDenominator()
	 * @see #getProductUnitElement()
	 * @generated
	 */
	EAttribute getProductUnitElement_PowerDenominator();

	/**
	 * Returns the meta object for class '{@link PhysicalQuantities.SystemOfUnits <em>System Of Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Of Units</em>'.
	 * @see PhysicalQuantities.SystemOfUnits
	 * @generated
	 */
	EClass getSystemOfUnits();

	/**
	 * Returns the meta object for the containment reference list '{@link PhysicalQuantities.SystemOfUnits#getBaseUnits <em>Base Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Base Units</em>'.
	 * @see PhysicalQuantities.SystemOfUnits#getBaseUnits()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EReference getSystemOfUnits_BaseUnits();

	/**
	 * Returns the meta object for the containment reference list '{@link PhysicalQuantities.SystemOfUnits#getQuantities <em>Quantities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Quantities</em>'.
	 * @see PhysicalQuantities.SystemOfUnits#getQuantities()
	 * @see #getSystemOfUnits()
	 * @generated
	 */
	EReference getSystemOfUnits_Quantities();

	/**
	 * Returns the meta object for enum '{@link PhysicalQuantities.UnitPrefixes <em>Unit Prefixes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unit Prefixes</em>'.
	 * @see PhysicalQuantities.UnitPrefixes
	 * @generated
	 */
	EEnum getUnitPrefixes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PhysicalQuantitiesFactory getPhysicalQuantitiesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.QuantityImpl <em>Quantity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.QuantityImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getQuantity()
		 * @generated
		 */
		EClass QUANTITY = eINSTANCE.getQuantity();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTITY__UNIT = eINSTANCE.getQuantity_Unit();

		/**
		 * The meta object literal for the '<em><b>Alternative Units</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTITY__ALTERNATIVE_UNITS = eINSTANCE.getQuantity_AlternativeUnits();

		/**
		 * The meta object literal for the '<em><b>Derived Units</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUANTITY__DERIVED_UNITS = eINSTANCE.getQuantity_DerivedUnits();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUANTITY__NAME = eINSTANCE.getQuantity_Name();

		/**
		 * The meta object literal for the '<em><b>Descriptive Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUANTITY__DESCRIPTIVE_NAME = eINSTANCE.getQuantity_DescriptiveName();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUANTITY__SYMBOL = eINSTANCE.getQuantity_Symbol();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.UnitImpl <em>Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.UnitImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getUnit()
		 * @generated
		 */
		EClass UNIT = eINSTANCE.getUnit();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.BaseUnitImpl <em>Base Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.BaseUnitImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getBaseUnit()
		 * @generated
		 */
		EClass BASE_UNIT = eINSTANCE.getBaseUnit();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_UNIT__NAME = eINSTANCE.getBaseUnit_Name();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_UNIT__SYMBOL = eINSTANCE.getBaseUnit_Symbol();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.AlternativeUnitImpl <em>Alternative Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.AlternativeUnitImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getAlternativeUnit()
		 * @generated
		 */
		EClass ALTERNATIVE_UNIT = eINSTANCE.getAlternativeUnit();

		/**
		 * The meta object literal for the '<em><b>Basis Unit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATIVE_UNIT__BASIS_UNIT = eINSTANCE.getAlternativeUnit_BasisUnit();

		/**
		 * The meta object literal for the '<em><b>Transformation To Basis Unit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT = eINSTANCE.getAlternativeUnit_TransformationToBasisUnit();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.UnitTransformationImpl <em>Unit Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.UnitTransformationImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getUnitTransformation()
		 * @generated
		 */
		EClass UNIT_TRANSFORMATION = eINSTANCE.getUnitTransformation();

		/**
		 * The meta object literal for the '<em><b>Multiplier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIT_TRANSFORMATION__MULTIPLIER = eINSTANCE.getUnitTransformation_Multiplier();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIT_TRANSFORMATION__OFFSET = eINSTANCE.getUnitTransformation_Offset();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.DerivedUnitImpl <em>Derived Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.DerivedUnitImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getDerivedUnit()
		 * @generated
		 */
		EClass DERIVED_UNIT = eINSTANCE.getDerivedUnit();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.ProductUnitImpl <em>Product Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.ProductUnitImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getProductUnit()
		 * @generated
		 */
		EClass PRODUCT_UNIT = eINSTANCE.getProductUnit();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_UNIT__ELEMENTS = eINSTANCE.getProductUnit_Elements();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.ProductUnitElementImpl <em>Product Unit Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.ProductUnitElementImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getProductUnitElement()
		 * @generated
		 */
		EClass PRODUCT_UNIT_ELEMENT = eINSTANCE.getProductUnitElement();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_UNIT_ELEMENT__UNIT = eINSTANCE.getProductUnitElement_Unit();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_UNIT_ELEMENT__PREFIX = eINSTANCE.getProductUnitElement_Prefix();

		/**
		 * The meta object literal for the '<em><b>Power Numerator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR = eINSTANCE.getProductUnitElement_PowerNumerator();

		/**
		 * The meta object literal for the '<em><b>Power Denominator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR = eINSTANCE.getProductUnitElement_PowerDenominator();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.impl.SystemOfUnitsImpl <em>System Of Units</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.impl.SystemOfUnitsImpl
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getSystemOfUnits()
		 * @generated
		 */
		EClass SYSTEM_OF_UNITS = eINSTANCE.getSystemOfUnits();

		/**
		 * The meta object literal for the '<em><b>Base Units</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_UNITS__BASE_UNITS = eINSTANCE.getSystemOfUnits_BaseUnits();

		/**
		 * The meta object literal for the '<em><b>Quantities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_OF_UNITS__QUANTITIES = eINSTANCE.getSystemOfUnits_Quantities();

		/**
		 * The meta object literal for the '{@link PhysicalQuantities.UnitPrefixes <em>Unit Prefixes</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see PhysicalQuantities.UnitPrefixes
		 * @see PhysicalQuantities.impl.PhysicalQuantitiesPackageImpl#getUnitPrefixes()
		 * @generated
		 */
		EEnum UNIT_PREFIXES = eINSTANCE.getUnitPrefixes();

	}

} //PhysicalQuantitiesPackage
