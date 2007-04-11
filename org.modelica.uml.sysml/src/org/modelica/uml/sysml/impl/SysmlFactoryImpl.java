/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.modelica.uml.sysml.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SysmlFactoryImpl extends EFactoryImpl implements SysmlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SysmlFactory init() {
		try {
			SysmlFactory theSysmlFactory = (SysmlFactory)EPackage.Registry.INSTANCE.getEFactory("http://modelica.org/mml"); 
			if (theSysmlFactory != null) {
				return theSysmlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SysmlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SysmlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SysmlPackage.BLOCK: return createBlock();
			case SysmlPackage.BLOCK_PROPERTY: return createBlockProperty();
			case SysmlPackage.DISTRIBUTED_PROPERTY: return createDistributedProperty();
			case SysmlPackage.VALUE_TYPE: return createValueType();
			case SysmlPackage.UNIT: return createUnit();
			case SysmlPackage.DIMENSION: return createDimension();
			case SysmlPackage.CONFORM: return createConform();
			case SysmlPackage.RATIONALE: return createRationale();
			case SysmlPackage.PROBLEM: return createProblem();
			case SysmlPackage.FLOW_PORT: return createFlowPort();
			case SysmlPackage.FLOW_PROPERTY: return createFlowProperty();
			case SysmlPackage.FLOW_SPECIFICATION: return createFlowSpecification();
			case SysmlPackage.ITEM_FLOW: return createItemFlow();
			case SysmlPackage.MODELICA_TYPE: return createModelicaType();
			case SysmlPackage.MODELICA_REAL: return createModelicaReal();
			case SysmlPackage.MODELICA_INTEGER: return createModelicaInteger();
			case SysmlPackage.MODELICA_STRING: return createModelicaString();
			case SysmlPackage.MODELICA_BOOLEAN: return createModelicaBoolean();
			case SysmlPackage.MODELICA_CLASS: return createModelicaClass();
			case SysmlPackage.MODELICA_MODEL: return createModelicaModel();
			case SysmlPackage.MODELICA_BLOCK: return createModelicaBlock();
			case SysmlPackage.MODELICA_CONNECTOR: return createModelicaConnector();
			case SysmlPackage.MODELICA_RECORD: return createModelicaRecord();
			case SysmlPackage.MODELICA_FUNCTION: return createModelicaFunction();
			case SysmlPackage.MODELICA_PROPERTY: return createModelicaProperty();
			case SysmlPackage.MODELICA_CONNECTOR_PROPERTY: return createModelicaConnectorProperty();
			case SysmlPackage.MODELICA_EQUATION_PROPERTY: return createModelicaEquationProperty();
			case SysmlPackage.MODELICA_COMPOSITION: return createModelicaComposition();
			case SysmlPackage.MODELICA_CONNECTION: return createModelicaConnection();
			case SysmlPackage.MODELICA_CLASSIFIER: return createModelicaClassifier();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SysmlPackage.FLOW_DIRECTION:
				return createFlowDirectionFromString(eDataType, initialValue);
			case SysmlPackage.VARIABILITY_KIND:
				return createVariabilityKindFromString(eDataType, initialValue);
			case SysmlPackage.PROPERTY_KIND:
				return createPropertyKindFromString(eDataType, initialValue);
			case SysmlPackage.ACCESS_KIND:
				return createAccessKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SysmlPackage.FLOW_DIRECTION:
				return convertFlowDirectionToString(eDataType, instanceValue);
			case SysmlPackage.VARIABILITY_KIND:
				return convertVariabilityKindToString(eDataType, instanceValue);
			case SysmlPackage.PROPERTY_KIND:
				return convertPropertyKindToString(eDataType, instanceValue);
			case SysmlPackage.ACCESS_KIND:
				return convertAccessKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockProperty createBlockProperty() {
		BlockPropertyImpl blockProperty = new BlockPropertyImpl();
		return blockProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistributedProperty createDistributedProperty() {
		DistributedPropertyImpl distributedProperty = new DistributedPropertyImpl();
		return distributedProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueType createValueType() {
		ValueTypeImpl valueType = new ValueTypeImpl();
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Unit createUnit() {
		UnitImpl unit = new UnitImpl();
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension createDimension() {
		DimensionImpl dimension = new DimensionImpl();
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conform createConform() {
		ConformImpl conform = new ConformImpl();
		return conform;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rationale createRationale() {
		RationaleImpl rationale = new RationaleImpl();
		return rationale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Problem createProblem() {
		ProblemImpl problem = new ProblemImpl();
		return problem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowPort createFlowPort() {
		FlowPortImpl flowPort = new FlowPortImpl();
		return flowPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowProperty createFlowProperty() {
		FlowPropertyImpl flowProperty = new FlowPropertyImpl();
		return flowProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowSpecification createFlowSpecification() {
		FlowSpecificationImpl flowSpecification = new FlowSpecificationImpl();
		return flowSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemFlow createItemFlow() {
		ItemFlowImpl itemFlow = new ItemFlowImpl();
		return itemFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaType createModelicaType() {
		ModelicaTypeImpl modelicaType = new ModelicaTypeImpl();
		return modelicaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaReal createModelicaReal() {
		ModelicaRealImpl modelicaReal = new ModelicaRealImpl();
		return modelicaReal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaInteger createModelicaInteger() {
		ModelicaIntegerImpl modelicaInteger = new ModelicaIntegerImpl();
		return modelicaInteger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaString createModelicaString() {
		ModelicaStringImpl modelicaString = new ModelicaStringImpl();
		return modelicaString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaBoolean createModelicaBoolean() {
		ModelicaBooleanImpl modelicaBoolean = new ModelicaBooleanImpl();
		return modelicaBoolean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaClass createModelicaClass() {
		ModelicaClassImpl modelicaClass = new ModelicaClassImpl();
		return modelicaClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaModel createModelicaModel() {
		ModelicaModelImpl modelicaModel = new ModelicaModelImpl();
		return modelicaModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaBlock createModelicaBlock() {
		ModelicaBlockImpl modelicaBlock = new ModelicaBlockImpl();
		return modelicaBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaConnector createModelicaConnector() {
		ModelicaConnectorImpl modelicaConnector = new ModelicaConnectorImpl();
		return modelicaConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaRecord createModelicaRecord() {
		ModelicaRecordImpl modelicaRecord = new ModelicaRecordImpl();
		return modelicaRecord;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaFunction createModelicaFunction() {
		ModelicaFunctionImpl modelicaFunction = new ModelicaFunctionImpl();
		return modelicaFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaProperty createModelicaProperty() {
		ModelicaPropertyImpl modelicaProperty = new ModelicaPropertyImpl();
		return modelicaProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaConnectorProperty createModelicaConnectorProperty() {
		ModelicaConnectorPropertyImpl modelicaConnectorProperty = new ModelicaConnectorPropertyImpl();
		return modelicaConnectorProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaEquationProperty createModelicaEquationProperty() {
		ModelicaEquationPropertyImpl modelicaEquationProperty = new ModelicaEquationPropertyImpl();
		return modelicaEquationProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaComposition createModelicaComposition() {
		ModelicaCompositionImpl modelicaComposition = new ModelicaCompositionImpl();
		return modelicaComposition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaConnection createModelicaConnection() {
		ModelicaConnectionImpl modelicaConnection = new ModelicaConnectionImpl();
		return modelicaConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelicaClassifier createModelicaClassifier() {
		ModelicaClassifierImpl modelicaClassifier = new ModelicaClassifierImpl();
		return modelicaClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowDirection createFlowDirectionFromString(EDataType eDataType, String initialValue) {
		FlowDirection result = FlowDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFlowDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityKind createVariabilityKindFromString(EDataType eDataType, String initialValue) {
		VariabilityKind result = VariabilityKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariabilityKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyKind createPropertyKindFromString(EDataType eDataType, String initialValue) {
		PropertyKind result = PropertyKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPropertyKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessKind createAccessKindFromString(EDataType eDataType, String initialValue) {
		AccessKind result = AccessKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAccessKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SysmlPackage getSysmlPackage() {
		return (SysmlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static SysmlPackage getPackage() {
		return SysmlPackage.eINSTANCE;
	}

} //SysmlFactoryImpl
