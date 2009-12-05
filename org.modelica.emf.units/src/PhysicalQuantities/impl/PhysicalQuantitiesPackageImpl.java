/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities.impl;

import PhysicalQuantities.AlternativeUnit;
import PhysicalQuantities.BaseUnit;
import PhysicalQuantities.DerivedUnit;
import PhysicalQuantities.PhysicalQuantitiesFactory;
import PhysicalQuantities.PhysicalQuantitiesPackage;
import PhysicalQuantities.ProductUnit;
import PhysicalQuantities.ProductUnitElement;
import PhysicalQuantities.Quantity;
import PhysicalQuantities.SystemOfUnits;
import PhysicalQuantities.Unit;
import PhysicalQuantities.UnitPrefixes;
import PhysicalQuantities.UnitTransformation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalQuantitiesPackageImpl extends EPackageImpl implements PhysicalQuantitiesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quantityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alternativeUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unitTransformationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass derivedUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productUnitElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemOfUnitsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unitPrefixesEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see PhysicalQuantities.PhysicalQuantitiesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PhysicalQuantitiesPackageImpl() {
		super(eNS_URI, PhysicalQuantitiesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link PhysicalQuantitiesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PhysicalQuantitiesPackage init() {
		if (isInited) return (PhysicalQuantitiesPackage)EPackage.Registry.INSTANCE.getEPackage(PhysicalQuantitiesPackage.eNS_URI);

		// Obtain or create and register package
		PhysicalQuantitiesPackageImpl thePhysicalQuantitiesPackage = (PhysicalQuantitiesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PhysicalQuantitiesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PhysicalQuantitiesPackageImpl());

		isInited = true;

		// Create package meta-data objects
		thePhysicalQuantitiesPackage.createPackageContents();

		// Initialize created meta-data
		thePhysicalQuantitiesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePhysicalQuantitiesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PhysicalQuantitiesPackage.eNS_URI, thePhysicalQuantitiesPackage);
		return thePhysicalQuantitiesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuantity() {
		return quantityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantity_Unit() {
		return (EReference)quantityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantity_AlternativeUnits() {
		return (EReference)quantityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuantity_DerivedUnits() {
		return (EReference)quantityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuantity_Name() {
		return (EAttribute)quantityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuantity_DescriptiveName() {
		return (EAttribute)quantityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuantity_Symbol() {
		return (EAttribute)quantityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnit() {
		return unitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseUnit() {
		return baseUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseUnit_Name() {
		return (EAttribute)baseUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseUnit_Symbol() {
		return (EAttribute)baseUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlternativeUnit() {
		return alternativeUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlternativeUnit_BasisUnit() {
		return (EReference)alternativeUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlternativeUnit_TransformationToBasisUnit() {
		return (EReference)alternativeUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnitTransformation() {
		return unitTransformationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnitTransformation_Multiplier() {
		return (EAttribute)unitTransformationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnitTransformation_Offset() {
		return (EAttribute)unitTransformationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDerivedUnit() {
		return derivedUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProductUnit() {
		return productUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProductUnit_Elements() {
		return (EReference)productUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProductUnitElement() {
		return productUnitElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProductUnitElement_Unit() {
		return (EReference)productUnitElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductUnitElement_Prefix() {
		return (EAttribute)productUnitElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductUnitElement_PowerNumerator() {
		return (EAttribute)productUnitElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductUnitElement_PowerDenominator() {
		return (EAttribute)productUnitElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemOfUnits() {
		return systemOfUnitsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemOfUnits_BaseUnits() {
		return (EReference)systemOfUnitsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemOfUnits_Quantities() {
		return (EReference)systemOfUnitsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnitPrefixes() {
		return unitPrefixesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalQuantitiesFactory getPhysicalQuantitiesFactory() {
		return (PhysicalQuantitiesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		quantityEClass = createEClass(QUANTITY);
		createEReference(quantityEClass, QUANTITY__UNIT);
		createEReference(quantityEClass, QUANTITY__ALTERNATIVE_UNITS);
		createEReference(quantityEClass, QUANTITY__DERIVED_UNITS);
		createEAttribute(quantityEClass, QUANTITY__NAME);
		createEAttribute(quantityEClass, QUANTITY__DESCRIPTIVE_NAME);
		createEAttribute(quantityEClass, QUANTITY__SYMBOL);

		unitEClass = createEClass(UNIT);

		baseUnitEClass = createEClass(BASE_UNIT);
		createEAttribute(baseUnitEClass, BASE_UNIT__NAME);
		createEAttribute(baseUnitEClass, BASE_UNIT__SYMBOL);

		alternativeUnitEClass = createEClass(ALTERNATIVE_UNIT);
		createEReference(alternativeUnitEClass, ALTERNATIVE_UNIT__BASIS_UNIT);
		createEReference(alternativeUnitEClass, ALTERNATIVE_UNIT__TRANSFORMATION_TO_BASIS_UNIT);

		unitTransformationEClass = createEClass(UNIT_TRANSFORMATION);
		createEAttribute(unitTransformationEClass, UNIT_TRANSFORMATION__MULTIPLIER);
		createEAttribute(unitTransformationEClass, UNIT_TRANSFORMATION__OFFSET);

		derivedUnitEClass = createEClass(DERIVED_UNIT);

		productUnitEClass = createEClass(PRODUCT_UNIT);
		createEReference(productUnitEClass, PRODUCT_UNIT__ELEMENTS);

		productUnitElementEClass = createEClass(PRODUCT_UNIT_ELEMENT);
		createEReference(productUnitElementEClass, PRODUCT_UNIT_ELEMENT__UNIT);
		createEAttribute(productUnitElementEClass, PRODUCT_UNIT_ELEMENT__PREFIX);
		createEAttribute(productUnitElementEClass, PRODUCT_UNIT_ELEMENT__POWER_NUMERATOR);
		createEAttribute(productUnitElementEClass, PRODUCT_UNIT_ELEMENT__POWER_DENOMINATOR);

		systemOfUnitsEClass = createEClass(SYSTEM_OF_UNITS);
		createEReference(systemOfUnitsEClass, SYSTEM_OF_UNITS__BASE_UNITS);
		createEReference(systemOfUnitsEClass, SYSTEM_OF_UNITS__QUANTITIES);

		// Create enums
		unitPrefixesEEnum = createEEnum(UNIT_PREFIXES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		baseUnitEClass.getESuperTypes().add(this.getUnit());
		alternativeUnitEClass.getESuperTypes().add(this.getBaseUnit());
		derivedUnitEClass.getESuperTypes().add(this.getUnit());
		productUnitEClass.getESuperTypes().add(this.getDerivedUnit());

		// Initialize classes and features; add operations and parameters
		initEClass(quantityEClass, Quantity.class, "Quantity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQuantity_Unit(), this.getUnit(), null, "unit", null, 0, 1, Quantity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQuantity_AlternativeUnits(), this.getAlternativeUnit(), null, "alternativeUnits", null, 0, 1, Quantity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQuantity_DerivedUnits(), this.getDerivedUnit(), null, "derivedUnits", null, 0, -1, Quantity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuantity_Name(), ecorePackage.getEString(), "name", null, 0, 1, Quantity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuantity_DescriptiveName(), ecorePackage.getEString(), "descriptiveName", null, 0, 1, Quantity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuantity_Symbol(), ecorePackage.getEString(), "symbol", null, 0, 1, Quantity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unitEClass, Unit.class, "Unit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(baseUnitEClass, BaseUnit.class, "BaseUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBaseUnit_Name(), ecorePackage.getEString(), "name", null, 0, 1, BaseUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBaseUnit_Symbol(), ecorePackage.getEString(), "symbol", null, 0, 1, BaseUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(alternativeUnitEClass, AlternativeUnit.class, "AlternativeUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlternativeUnit_BasisUnit(), this.getUnit(), null, "basisUnit", null, 1, 1, AlternativeUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlternativeUnit_TransformationToBasisUnit(), this.getUnitTransformation(), null, "transformationToBasisUnit", null, 1, 1, AlternativeUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unitTransformationEClass, UnitTransformation.class, "UnitTransformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnitTransformation_Multiplier(), ecorePackage.getEDouble(), "multiplier", "1", 1, 1, UnitTransformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnitTransformation_Offset(), ecorePackage.getEDouble(), "offset", "0", 1, 1, UnitTransformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(unitTransformationEClass, this.getUnitTransformation(), "getInverseTransformation", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(derivedUnitEClass, DerivedUnit.class, "DerivedUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(productUnitEClass, ProductUnit.class, "ProductUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProductUnit_Elements(), this.getProductUnitElement(), null, "elements", null, 0, 1, ProductUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productUnitElementEClass, ProductUnitElement.class, "ProductUnitElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProductUnitElement_Unit(), this.getUnit(), null, "unit", null, 0, 1, ProductUnitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductUnitElement_Prefix(), this.getUnitPrefixes(), "prefix", "NONE", 1, 1, ProductUnitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductUnitElement_PowerNumerator(), ecorePackage.getEInt(), "powerNumerator", null, 0, 1, ProductUnitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductUnitElement_PowerDenominator(), ecorePackage.getEInt(), "powerDenominator", null, 0, 1, ProductUnitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(systemOfUnitsEClass, SystemOfUnits.class, "SystemOfUnits", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystemOfUnits_BaseUnits(), this.getBaseUnit(), null, "baseUnits", null, 3, -1, SystemOfUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSystemOfUnits_Quantities(), this.getQuantity(), null, "quantities", null, 0, -1, SystemOfUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(unitPrefixesEEnum, UnitPrefixes.class, "UnitPrefixes");
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.NONE);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.YOTTA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.ZETTA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.EXA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.PETA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.TERA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.GIGA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.MEGA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.KILO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.HECTO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.DEKA);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.DECI);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.CENTI);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.MILLI);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.MICRO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.NANO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.PICO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.FEMTO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.ATTO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.ZEPTO);
		addEEnumLiteral(unitPrefixesEEnum, UnitPrefixes.YOCTO);

		// Create resource
		createResource(eNS_URI);
	}

} //PhysicalQuantitiesPackageImpl
