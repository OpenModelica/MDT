/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities.impl;

import PhysicalQuantities.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalQuantitiesFactoryImpl extends EFactoryImpl implements PhysicalQuantitiesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PhysicalQuantitiesFactory init() {
		try {
			PhysicalQuantitiesFactory thePhysicalQuantitiesFactory = (PhysicalQuantitiesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/openmodelica.properties/3.1"); 
			if (thePhysicalQuantitiesFactory != null) {
				return thePhysicalQuantitiesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PhysicalQuantitiesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalQuantitiesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PhysicalQuantitiesPackage.QUANTITY: return createQuantity();
			case PhysicalQuantitiesPackage.BASE_UNIT: return createBaseUnit();
			case PhysicalQuantitiesPackage.ALTERNATIVE_UNIT: return createAlternativeUnit();
			case PhysicalQuantitiesPackage.UNIT_TRANSFORMATION: return createUnitTransformation();
			case PhysicalQuantitiesPackage.DERIVED_UNIT: return createDerivedUnit();
			case PhysicalQuantitiesPackage.PRODUCT_UNIT: return createProductUnit();
			case PhysicalQuantitiesPackage.PRODUCT_UNIT_ELEMENT: return createProductUnitElement();
			case PhysicalQuantitiesPackage.SYSTEM_OF_UNITS: return createSystemOfUnits();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PhysicalQuantitiesPackage.UNIT_PREFIXES:
				return createUnitPrefixesFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PhysicalQuantitiesPackage.UNIT_PREFIXES:
				return convertUnitPrefixesToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quantity createQuantity() {
		QuantityImpl quantity = new QuantityImpl();
		return quantity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseUnit createBaseUnit() {
		BaseUnitImpl baseUnit = new BaseUnitImpl();
		return baseUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlternativeUnit createAlternativeUnit() {
		AlternativeUnitImpl alternativeUnit = new AlternativeUnitImpl();
		return alternativeUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitTransformation createUnitTransformation() {
		UnitTransformationImpl unitTransformation = new UnitTransformationImpl();
		return unitTransformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DerivedUnit createDerivedUnit() {
		DerivedUnitImpl derivedUnit = new DerivedUnitImpl();
		return derivedUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductUnit createProductUnit() {
		ProductUnitImpl productUnit = new ProductUnitImpl();
		return productUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductUnitElement createProductUnitElement() {
		ProductUnitElementImpl productUnitElement = new ProductUnitElementImpl();
		return productUnitElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemOfUnits createSystemOfUnits() {
		SystemOfUnitsImpl systemOfUnits = new SystemOfUnitsImpl();
		return systemOfUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitPrefixes createUnitPrefixesFromString(EDataType eDataType, String initialValue) {
		UnitPrefixes result = UnitPrefixes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnitPrefixesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalQuantitiesPackage getPhysicalQuantitiesPackage() {
		return (PhysicalQuantitiesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PhysicalQuantitiesPackage getPackage() {
		return PhysicalQuantitiesPackage.eINSTANCE;
	}

} //PhysicalQuantitiesFactoryImpl
