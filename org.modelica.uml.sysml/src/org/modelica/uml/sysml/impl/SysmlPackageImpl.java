/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.Block;
import org.modelica.uml.sysml.BlockProperty;
import org.modelica.uml.sysml.Conform;
import org.modelica.uml.sysml.Dimension;
import org.modelica.uml.sysml.DistributedProperty;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.FlowPort;
import org.modelica.uml.sysml.FlowProperty;
import org.modelica.uml.sysml.FlowSpecification;
import org.modelica.uml.sysml.ItemFlow;
import org.modelica.uml.sysml.ModelicaBlock;
import org.modelica.uml.sysml.ModelicaBoolean;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaClassifier;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaConnection;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.ModelicaConnectorProperty;
import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.ModelicaFunction;
import org.modelica.uml.sysml.ModelicaInteger;
import org.modelica.uml.sysml.ModelicaModel;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.ModelicaReal;
import org.modelica.uml.sysml.ModelicaRecord;
import org.modelica.uml.sysml.ModelicaString;
import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.Problem;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.Rationale;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.Unit;
import org.modelica.uml.sysml.ValueType;
import org.modelica.uml.sysml.VariabilityKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SysmlPackageImpl extends EPackageImpl implements SysmlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass distributedPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueTypeEClass = null;

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
	private EClass dimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conformEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rationaleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass problemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowPortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itemFlowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaRealEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaIntegerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaStringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaBooleanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaBlockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaRecordEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaConnectorPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaEquationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaCompositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelicaClassifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum flowDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum variabilityKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum propertyKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum accessKindEEnum = null;

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
	 * @see org.modelica.uml.sysml.SysmlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SysmlPackageImpl() {
		super(eNS_URI, SysmlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SysmlPackage init() {
		if (isInited) return (SysmlPackage)EPackage.Registry.INSTANCE.getEPackage(SysmlPackage.eNS_URI);

		// Obtain or create and register package
		SysmlPackageImpl theSysmlPackage = (SysmlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SysmlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SysmlPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		UMLPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSysmlPackage.createPackageContents();

		// Initialize created meta-data
		theSysmlPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSysmlPackage.freeze();

		return theSysmlPackage;
	}

	public EReference getProperty_Datatype() {
		return (EReference)modelicaTypeEClass.getEStructuralFeatures().get(9);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlock() {
		return blockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlock_IsEncapsulated() {
		return (EAttribute)blockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlockProperty() {
		return blockPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDistributedProperty() {
		return distributedPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueType() {
		return valueTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValueType_Unit() {
		return (EReference)valueTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValueType_Dimension() {
		return (EReference)valueTypeEClass.getEStructuralFeatures().get(1);
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
	public EClass getDimension() {
		return dimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConform() {
		return conformEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRationale() {
		return rationaleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProblem() {
		return problemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFlowPort() {
		return flowPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowPort_IsAtomic() {
		return (EAttribute)flowPortEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowPort_Direction() {
		return (EAttribute)flowPortEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowPort_IsConjugated() {
		return (EAttribute)flowPortEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFlowProperty() {
		return flowPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowProperty_Direction() {
		return (EAttribute)flowPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowProperty_Access() {
		return (EAttribute)flowPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFlowSpecification() {
		return flowSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlowSpecification_OwnedFlowProperty() {
		return (EReference)flowSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getItemFlow() {
		return itemFlowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItemFlow_ItemProperty() {
		return (EReference)itemFlowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaType() {
		return modelicaTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaReal() {
		return modelicaRealEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Value() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Quantity() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Unit() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_DisplayUnit() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Min() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Max() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Start() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Fixed() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaReal_Nominal() {
		return (EAttribute)modelicaRealEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaInteger() {
		return modelicaIntegerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaInteger_Value() {
		return (EAttribute)modelicaIntegerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaInteger_Min() {
		return (EAttribute)modelicaIntegerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaInteger_Max() {
		return (EAttribute)modelicaIntegerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaInteger_Start() {
		return (EAttribute)modelicaIntegerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaInteger_Fixed() {
		return (EAttribute)modelicaIntegerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaString() {
		return modelicaStringEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaString_Value() {
		return (EAttribute)modelicaStringEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaString_Start() {
		return (EAttribute)modelicaStringEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaBoolean() {
		return modelicaBooleanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaBoolean_Value() {
		return (EAttribute)modelicaBooleanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaBoolean_Start() {
		return (EAttribute)modelicaBooleanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaBoolean_Fixed() {
		return (EAttribute)modelicaBooleanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaClass() {
		return modelicaClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaClass_IsEncapsulatedClass() {
		return (EAttribute)modelicaClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaModel() {
		return modelicaModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaBlock() {
		return modelicaBlockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaConnector() {
		return modelicaConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaRecord() {
		return modelicaRecordEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaFunction() {
		return modelicaFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaProperty() {
		return modelicaPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaProperty_Variability() {
		return (EAttribute)modelicaPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaProperty_IsFlow() {
		return (EAttribute)modelicaPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaProperty_Direction() {
		return (EAttribute)modelicaPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaProperty_PropertyKind() {
		return (EAttribute)modelicaPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaProperty_ArrayDimension() {
		return (EAttribute)modelicaPropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaProperty_Access() {
		return (EAttribute)modelicaPropertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelicaProperty_DataType() {
		return (EReference)modelicaPropertyEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaConnectorProperty() {
		return modelicaConnectorPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaEquationProperty() {
		return modelicaEquationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaEquationProperty_Equation() {
		return (EAttribute)modelicaEquationPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaComposition() {
		return modelicaCompositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaComposition_IsFlow() {
		return (EAttribute)modelicaCompositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaComposition_Direction() {
		return (EAttribute)modelicaCompositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaComposition_ArrayDimension() {
		return (EAttribute)modelicaCompositionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaComposition_Access() {
		return (EAttribute)modelicaCompositionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelicaComposition_Default() {
		return (EAttribute)modelicaCompositionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaConnection() {
		return modelicaConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelicaClassifier() {
		return modelicaClassifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFlowDirection() {
		return flowDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVariabilityKind() {
		return variabilityKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPropertyKind() {
		return propertyKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAccessKind() {
		return accessKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SysmlFactory getSysmlFactory() {
		return (SysmlFactory)getEFactoryInstance();
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
		blockEClass = createEClass(BLOCK);
		createEAttribute(blockEClass, BLOCK__IS_ENCAPSULATED);

		blockPropertyEClass = createEClass(BLOCK_PROPERTY);

		distributedPropertyEClass = createEClass(DISTRIBUTED_PROPERTY);

		valueTypeEClass = createEClass(VALUE_TYPE);
		createEReference(valueTypeEClass, VALUE_TYPE__UNIT);
		createEReference(valueTypeEClass, VALUE_TYPE__DIMENSION);

		unitEClass = createEClass(UNIT);

		dimensionEClass = createEClass(DIMENSION);

		conformEClass = createEClass(CONFORM);

		rationaleEClass = createEClass(RATIONALE);

		problemEClass = createEClass(PROBLEM);

		flowPortEClass = createEClass(FLOW_PORT);
		createEAttribute(flowPortEClass, FLOW_PORT__IS_ATOMIC);
		createEAttribute(flowPortEClass, FLOW_PORT__DIRECTION);
		createEAttribute(flowPortEClass, FLOW_PORT__IS_CONJUGATED);

		flowPropertyEClass = createEClass(FLOW_PROPERTY);
		createEAttribute(flowPropertyEClass, FLOW_PROPERTY__DIRECTION);
		createEAttribute(flowPropertyEClass, FLOW_PROPERTY__ACCESS);

		flowSpecificationEClass = createEClass(FLOW_SPECIFICATION);
		createEReference(flowSpecificationEClass, FLOW_SPECIFICATION__OWNED_FLOW_PROPERTY);

		itemFlowEClass = createEClass(ITEM_FLOW);
		createEReference(itemFlowEClass, ITEM_FLOW__ITEM_PROPERTY);

		modelicaTypeEClass = createEClass(MODELICA_TYPE);

		modelicaRealEClass = createEClass(MODELICA_REAL);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__VALUE);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__QUANTITY);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__UNIT);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__DISPLAY_UNIT);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__MIN);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__MAX);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__START);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__FIXED);
		createEAttribute(modelicaRealEClass, MODELICA_REAL__NOMINAL);

		modelicaIntegerEClass = createEClass(MODELICA_INTEGER);
		createEAttribute(modelicaIntegerEClass, MODELICA_INTEGER__VALUE);
		createEAttribute(modelicaIntegerEClass, MODELICA_INTEGER__MIN);
		createEAttribute(modelicaIntegerEClass, MODELICA_INTEGER__MAX);
		createEAttribute(modelicaIntegerEClass, MODELICA_INTEGER__START);
		createEAttribute(modelicaIntegerEClass, MODELICA_INTEGER__FIXED);

		modelicaStringEClass = createEClass(MODELICA_STRING);
		createEAttribute(modelicaStringEClass, MODELICA_STRING__VALUE);
		createEAttribute(modelicaStringEClass, MODELICA_STRING__START);

		modelicaBooleanEClass = createEClass(MODELICA_BOOLEAN);
		createEAttribute(modelicaBooleanEClass, MODELICA_BOOLEAN__VALUE);
		createEAttribute(modelicaBooleanEClass, MODELICA_BOOLEAN__START);
		createEAttribute(modelicaBooleanEClass, MODELICA_BOOLEAN__FIXED);

		modelicaClassEClass = createEClass(MODELICA_CLASS);
		createEAttribute(modelicaClassEClass, MODELICA_CLASS__IS_ENCAPSULATED_CLASS);

		modelicaModelEClass = createEClass(MODELICA_MODEL);

		modelicaBlockEClass = createEClass(MODELICA_BLOCK);

		modelicaConnectorEClass = createEClass(MODELICA_CONNECTOR);

		modelicaRecordEClass = createEClass(MODELICA_RECORD);

		modelicaFunctionEClass = createEClass(MODELICA_FUNCTION);

		modelicaPropertyEClass = createEClass(MODELICA_PROPERTY);
		createEAttribute(modelicaPropertyEClass, MODELICA_PROPERTY__VARIABILITY);
		createEAttribute(modelicaPropertyEClass, MODELICA_PROPERTY__IS_FLOW);
		createEAttribute(modelicaPropertyEClass, MODELICA_PROPERTY__DIRECTION);
		createEAttribute(modelicaPropertyEClass, MODELICA_PROPERTY__PROPERTY_KIND);
		createEAttribute(modelicaPropertyEClass, MODELICA_PROPERTY__ARRAY_DIMENSION);
		createEAttribute(modelicaPropertyEClass, MODELICA_PROPERTY__ACCESS);
		createEReference(modelicaPropertyEClass, MODELICA_PROPERTY__DATA_TYPE);

		modelicaConnectorPropertyEClass = createEClass(MODELICA_CONNECTOR_PROPERTY);

		modelicaEquationPropertyEClass = createEClass(MODELICA_EQUATION_PROPERTY);
		createEAttribute(modelicaEquationPropertyEClass, MODELICA_EQUATION_PROPERTY__EQUATION);

		modelicaCompositionEClass = createEClass(MODELICA_COMPOSITION);
		createEAttribute(modelicaCompositionEClass, MODELICA_COMPOSITION__IS_FLOW);
		createEAttribute(modelicaCompositionEClass, MODELICA_COMPOSITION__DIRECTION);
		createEAttribute(modelicaCompositionEClass, MODELICA_COMPOSITION__ARRAY_DIMENSION);
		createEAttribute(modelicaCompositionEClass, MODELICA_COMPOSITION__ACCESS);
		createEAttribute(modelicaCompositionEClass, MODELICA_COMPOSITION__DEFAULT);

		modelicaConnectionEClass = createEClass(MODELICA_CONNECTION);

		modelicaClassifierEClass = createEClass(MODELICA_CLASSIFIER);

		// Create enums
		flowDirectionEEnum = createEEnum(FLOW_DIRECTION);
		variabilityKindEEnum = createEEnum(VARIABILITY_KIND);
		propertyKindEEnum = createEEnum(PROPERTY_KIND);
		accessKindEEnum = createEEnum(ACCESS_KIND);
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

		// Obtain other dependent packages
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);

		// Add supertypes to classes
		blockEClass.getESuperTypes().add(theUMLPackage.getClass_());
		blockPropertyEClass.getESuperTypes().add(theUMLPackage.getProperty());
		distributedPropertyEClass.getESuperTypes().add(this.getBlockProperty());
		valueTypeEClass.getESuperTypes().add(theUMLPackage.getDataType());
		unitEClass.getESuperTypes().add(this.getValueType());
		dimensionEClass.getESuperTypes().add(this.getValueType());
		conformEClass.getESuperTypes().add(theUMLPackage.getDependency());
		rationaleEClass.getESuperTypes().add(theUMLPackage.getComment());
		problemEClass.getESuperTypes().add(theUMLPackage.getComment());
		flowPortEClass.getESuperTypes().add(theUMLPackage.getPort());
		flowPropertyEClass.getESuperTypes().add(theUMLPackage.getProperty());
		flowSpecificationEClass.getESuperTypes().add(theUMLPackage.getInterface());
		itemFlowEClass.getESuperTypes().add(theUMLPackage.getInformationFlow());
		modelicaTypeEClass.getESuperTypes().add(theUMLPackage.getPrimitiveType());
		modelicaTypeEClass.getESuperTypes().add(this.getModelicaClassifier());
		modelicaRealEClass.getESuperTypes().add(this.getModelicaType());
		modelicaIntegerEClass.getESuperTypes().add(this.getModelicaType());
		modelicaStringEClass.getESuperTypes().add(this.getModelicaType());
		modelicaBooleanEClass.getESuperTypes().add(this.getModelicaType());
		modelicaClassEClass.getESuperTypes().add(this.getBlock());
		modelicaClassEClass.getESuperTypes().add(this.getModelicaClassifier());
		modelicaModelEClass.getESuperTypes().add(this.getModelicaClass());
		modelicaBlockEClass.getESuperTypes().add(this.getModelicaClass());
		modelicaConnectorEClass.getESuperTypes().add(this.getModelicaClass());
		modelicaRecordEClass.getESuperTypes().add(this.getModelicaClass());
		modelicaFunctionEClass.getESuperTypes().add(this.getModelicaClass());
		modelicaPropertyEClass.getESuperTypes().add(this.getBlockProperty());
		modelicaConnectorPropertyEClass.getESuperTypes().add(theUMLPackage.getPort());
		modelicaConnectorPropertyEClass.getESuperTypes().add(this.getModelicaProperty());
		modelicaEquationPropertyEClass.getESuperTypes().add(this.getModelicaProperty());
		modelicaCompositionEClass.getESuperTypes().add(theUMLPackage.getAssociation());
		modelicaConnectionEClass.getESuperTypes().add(theUMLPackage.getConnector());
		modelicaClassifierEClass.getESuperTypes().add(theUMLPackage.getClassifier());

		// Initialize classes and features; add operations and parameters
		initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlock_IsEncapsulated(), theUMLPackage.getBoolean(), "isEncapsulated", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockPropertyEClass, BlockProperty.class, "BlockProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(distributedPropertyEClass, DistributedProperty.class, "DistributedProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueTypeEClass, ValueType.class, "ValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getValueType_Unit(), this.getUnit(), null, "unit", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueType_Dimension(), this.getDimension(), null, "dimension", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unitEClass, Unit.class, "Unit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dimensionEClass, Dimension.class, "Dimension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conformEClass, Conform.class, "Conform", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rationaleEClass, Rationale.class, "Rationale", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(problemEClass, Problem.class, "Problem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(flowPortEClass, FlowPort.class, "FlowPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFlowPort_IsAtomic(), theUMLPackage.getBoolean(), "isAtomic", null, 0, 1, FlowPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowPort_Direction(), this.getFlowDirection(), "direction", null, 0, 1, FlowPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowPort_IsConjugated(), theUMLPackage.getBoolean(), "isConjugated", null, 0, 1, FlowPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowPropertyEClass, FlowProperty.class, "FlowProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFlowProperty_Direction(), this.getFlowDirection(), "direction", null, 0, 1, FlowProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowProperty_Access(), this.getAccessKind(), "access", null, 0, 1, FlowProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowSpecificationEClass, FlowSpecification.class, "FlowSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFlowSpecification_OwnedFlowProperty(), this.getFlowProperty(), null, "ownedFlowProperty", null, 0, -1, FlowSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(itemFlowEClass, ItemFlow.class, "ItemFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getItemFlow_ItemProperty(), this.getBlockProperty(), null, "itemProperty", null, 0, 1, ItemFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaTypeEClass, ModelicaType.class, "ModelicaType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaRealEClass, ModelicaReal.class, "ModelicaReal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaReal_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_Quantity(), theUMLPackage.getString(), "quantity", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_Unit(), theUMLPackage.getString(), "unit", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_DisplayUnit(), theUMLPackage.getString(), "displayUnit", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_Min(), ecorePackage.getEDouble(), "min", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_Max(), ecorePackage.getEDouble(), "max", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_Start(), ecorePackage.getEDouble(), "start", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_Fixed(), theUMLPackage.getBoolean(), "fixed", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaReal_Nominal(), ecorePackage.getEDouble(), "nominal", null, 0, 1, ModelicaReal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaIntegerEClass, ModelicaInteger.class, "ModelicaInteger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaInteger_Value(), theUMLPackage.getInteger(), "value", null, 0, 1, ModelicaInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaInteger_Min(), theUMLPackage.getInteger(), "min", null, 0, 1, ModelicaInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaInteger_Max(), theUMLPackage.getInteger(), "max", null, 0, 1, ModelicaInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaInteger_Start(), theUMLPackage.getInteger(), "start", null, 0, 1, ModelicaInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaInteger_Fixed(), theUMLPackage.getBoolean(), "fixed", null, 0, 1, ModelicaInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaStringEClass, ModelicaString.class, "ModelicaString", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaString_Value(), theUMLPackage.getString(), "value", null, 0, 1, ModelicaString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaString_Start(), theUMLPackage.getString(), "start", null, 0, 1, ModelicaString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaBooleanEClass, ModelicaBoolean.class, "ModelicaBoolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaBoolean_Value(), theUMLPackage.getBoolean(), "value", null, 0, 1, ModelicaBoolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaBoolean_Start(), theUMLPackage.getBoolean(), "start", null, 0, 1, ModelicaBoolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaBoolean_Fixed(), theUMLPackage.getBoolean(), "fixed", null, 0, 1, ModelicaBoolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaClassEClass, ModelicaClass.class, "ModelicaClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaClass_IsEncapsulatedClass(), theUMLPackage.getBoolean(), "isEncapsulatedClass", null, 0, 1, ModelicaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaModelEClass, ModelicaModel.class, "ModelicaModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaBlockEClass, ModelicaBlock.class, "ModelicaBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaConnectorEClass, ModelicaConnector.class, "ModelicaConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaRecordEClass, ModelicaRecord.class, "ModelicaRecord", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaFunctionEClass, ModelicaFunction.class, "ModelicaFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaPropertyEClass, ModelicaProperty.class, "ModelicaProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaProperty_Variability(), this.getVariabilityKind(), "variability", null, 0, 1, ModelicaProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaProperty_IsFlow(), theUMLPackage.getBoolean(), "isFlow", null, 0, 1, ModelicaProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaProperty_Direction(), this.getFlowDirection(), "direction", "inout", 0, 1, ModelicaProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaProperty_PropertyKind(), this.getPropertyKind(), "propertyKind", null, 0, 1, ModelicaProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaProperty_ArrayDimension(), theUMLPackage.getString(), "arrayDimension", null, 0, 1, ModelicaProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaProperty_Access(), this.getAccessKind(), "access", null, 0, 1, ModelicaProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelicaProperty_DataType(), this.getModelicaClassifier(), null, "dataType", null, 0, 1, ModelicaProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaConnectorPropertyEClass, ModelicaConnectorProperty.class, "ModelicaConnectorProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaEquationPropertyEClass, ModelicaEquationProperty.class, "ModelicaEquationProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaEquationProperty_Equation(), theUMLPackage.getString(), "equation", null, 0, 1, ModelicaEquationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaCompositionEClass, ModelicaComposition.class, "ModelicaComposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelicaComposition_IsFlow(), theUMLPackage.getBoolean(), "isFlow", null, 0, 1, ModelicaComposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaComposition_Direction(), this.getFlowDirection(), "direction", "inout", 0, 1, ModelicaComposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaComposition_ArrayDimension(), theUMLPackage.getString(), "arrayDimension", null, 0, 1, ModelicaComposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaComposition_Access(), this.getAccessKind(), "access", null, 0, 1, ModelicaComposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelicaComposition_Default(), theUMLPackage.getString(), "default", null, 0, 1, ModelicaComposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelicaConnectionEClass, ModelicaConnection.class, "ModelicaConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelicaClassifierEClass, ModelicaClassifier.class, "ModelicaClassifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(flowDirectionEEnum, FlowDirection.class, "FlowDirection");
		addEEnumLiteral(flowDirectionEEnum, FlowDirection.IN_LITERAL);
		addEEnumLiteral(flowDirectionEEnum, FlowDirection.OUT_LITERAL);
		addEEnumLiteral(flowDirectionEEnum, FlowDirection.INOUT_LITERAL);

		initEEnum(variabilityKindEEnum, VariabilityKind.class, "VariabilityKind");
		addEEnumLiteral(variabilityKindEEnum, VariabilityKind.NONE_LITERAL);
		addEEnumLiteral(variabilityKindEEnum, VariabilityKind.CONSTANT_LITERAL);
		addEEnumLiteral(variabilityKindEEnum, VariabilityKind.DISCRETE_LITERAL);

		initEEnum(propertyKindEEnum, PropertyKind.class, "PropertyKind");
		addEEnumLiteral(propertyKindEEnum, PropertyKind.PARAMETER_LITERAL);
		addEEnumLiteral(propertyKindEEnum, PropertyKind.PART_LITERAL);
		addEEnumLiteral(propertyKindEEnum, PropertyKind.VARIABLE_LITERAL);
		addEEnumLiteral(propertyKindEEnum, PropertyKind.EQUATION_LITERAL);

		initEEnum(accessKindEEnum, AccessKind.class, "AccessKind");
		addEEnumLiteral(accessKindEEnum, AccessKind.PUBLIC_LITERAL);
		addEEnumLiteral(accessKindEEnum, AccessKind.PROTECTED_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //SysmlPackageImpl
