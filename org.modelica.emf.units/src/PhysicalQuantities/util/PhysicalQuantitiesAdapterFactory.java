/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities.util;

import PhysicalQuantities.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see PhysicalQuantities.PhysicalQuantitiesPackage
 * @generated
 */
public class PhysicalQuantitiesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PhysicalQuantitiesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalQuantitiesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PhysicalQuantitiesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhysicalQuantitiesSwitch<Adapter> modelSwitch =
		new PhysicalQuantitiesSwitch<Adapter>() {
			@Override
			public Adapter caseQuantity(Quantity object) {
				return createQuantityAdapter();
			}
			@Override
			public Adapter caseUnit(Unit object) {
				return createUnitAdapter();
			}
			@Override
			public Adapter caseBaseUnit(BaseUnit object) {
				return createBaseUnitAdapter();
			}
			@Override
			public Adapter caseAlternativeUnit(AlternativeUnit object) {
				return createAlternativeUnitAdapter();
			}
			@Override
			public Adapter caseUnitTransformation(UnitTransformation object) {
				return createUnitTransformationAdapter();
			}
			@Override
			public Adapter caseDerivedUnit(DerivedUnit object) {
				return createDerivedUnitAdapter();
			}
			@Override
			public Adapter caseProductUnit(ProductUnit object) {
				return createProductUnitAdapter();
			}
			@Override
			public Adapter caseProductUnitElement(ProductUnitElement object) {
				return createProductUnitElementAdapter();
			}
			@Override
			public Adapter caseSystemOfUnits(SystemOfUnits object) {
				return createSystemOfUnitsAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.Quantity <em>Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.Quantity
	 * @generated
	 */
	public Adapter createQuantityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.Unit
	 * @generated
	 */
	public Adapter createUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.BaseUnit <em>Base Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.BaseUnit
	 * @generated
	 */
	public Adapter createBaseUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.AlternativeUnit <em>Alternative Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.AlternativeUnit
	 * @generated
	 */
	public Adapter createAlternativeUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.UnitTransformation <em>Unit Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.UnitTransformation
	 * @generated
	 */
	public Adapter createUnitTransformationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.DerivedUnit <em>Derived Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.DerivedUnit
	 * @generated
	 */
	public Adapter createDerivedUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.ProductUnit <em>Product Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.ProductUnit
	 * @generated
	 */
	public Adapter createProductUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.ProductUnitElement <em>Product Unit Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.ProductUnitElement
	 * @generated
	 */
	public Adapter createProductUnitElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link PhysicalQuantities.SystemOfUnits <em>System Of Units</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see PhysicalQuantities.SystemOfUnits
	 * @generated
	 */
	public Adapter createSystemOfUnitsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PhysicalQuantitiesAdapterFactory
