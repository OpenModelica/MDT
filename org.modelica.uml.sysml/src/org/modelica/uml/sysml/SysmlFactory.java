/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.modelica.uml.sysml.SysmlPackage
 * @generated
 */
public interface SysmlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SysmlFactory eINSTANCE = org.modelica.uml.sysml.impl.SysmlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Block</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block</em>'.
	 * @generated
	 */
	Block createBlock();

	/**
	 * Returns a new object of class '<em>Block Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block Property</em>'.
	 * @generated
	 */
	BlockProperty createBlockProperty();

	/**
	 * Returns a new object of class '<em>Distributed Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Distributed Property</em>'.
	 * @generated
	 */
	DistributedProperty createDistributedProperty();

	/**
	 * Returns a new object of class '<em>Value Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Type</em>'.
	 * @generated
	 */
	ValueType createValueType();

	/**
	 * Returns a new object of class '<em>Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unit</em>'.
	 * @generated
	 */
	Unit createUnit();

	/**
	 * Returns a new object of class '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension</em>'.
	 * @generated
	 */
	Dimension createDimension();

	/**
	 * Returns a new object of class '<em>Conform</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conform</em>'.
	 * @generated
	 */
	Conform createConform();

	/**
	 * Returns a new object of class '<em>Rationale</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rationale</em>'.
	 * @generated
	 */
	Rationale createRationale();

	/**
	 * Returns a new object of class '<em>Problem</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Problem</em>'.
	 * @generated
	 */
	Problem createProblem();

	/**
	 * Returns a new object of class '<em>Flow Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Flow Port</em>'.
	 * @generated
	 */
	FlowPort createFlowPort();

	/**
	 * Returns a new object of class '<em>Flow Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Flow Property</em>'.
	 * @generated
	 */
	FlowProperty createFlowProperty();

	/**
	 * Returns a new object of class '<em>Flow Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Flow Specification</em>'.
	 * @generated
	 */
	FlowSpecification createFlowSpecification();

	/**
	 * Returns a new object of class '<em>Item Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Item Flow</em>'.
	 * @generated
	 */
	ItemFlow createItemFlow();

	/**
	 * Returns a new object of class '<em>Modelica Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Type</em>'.
	 * @generated
	 */
	ModelicaType createModelicaType();

	/**
	 * Returns a new object of class '<em>Modelica Real</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Real</em>'.
	 * @generated
	 */
	ModelicaReal createModelicaReal();

	/**
	 * Returns a new object of class '<em>Modelica Integer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Integer</em>'.
	 * @generated
	 */
	ModelicaInteger createModelicaInteger();

	/**
	 * Returns a new object of class '<em>Modelica String</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica String</em>'.
	 * @generated
	 */
	ModelicaString createModelicaString();

	/**
	 * Returns a new object of class '<em>Modelica Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Boolean</em>'.
	 * @generated
	 */
	ModelicaBoolean createModelicaBoolean();

	/**
	 * Returns a new object of class '<em>Modelica Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Class</em>'.
	 * @generated
	 */
	ModelicaClass createModelicaClass();

	/**
	 * Returns a new object of class '<em>Modelica Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Model</em>'.
	 * @generated
	 */
	ModelicaModel createModelicaModel();

	/**
	 * Returns a new object of class '<em>Modelica Block</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Block</em>'.
	 * @generated
	 */
	ModelicaBlock createModelicaBlock();

	/**
	 * Returns a new object of class '<em>Modelica Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Connector</em>'.
	 * @generated
	 */
	ModelicaConnector createModelicaConnector();

	/**
	 * Returns a new object of class '<em>Modelica Record</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Record</em>'.
	 * @generated
	 */
	ModelicaRecord createModelicaRecord();

	/**
	 * Returns a new object of class '<em>Modelica Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Function</em>'.
	 * @generated
	 */
	ModelicaFunction createModelicaFunction();

	/**
	 * Returns a new object of class '<em>Modelica Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Property</em>'.
	 * @generated
	 */
	ModelicaProperty createModelicaProperty();

	/**
	 * Returns a new object of class '<em>Modelica Connector Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Connector Property</em>'.
	 * @generated
	 */
	ModelicaConnectorProperty createModelicaConnectorProperty();

	/**
	 * Returns a new object of class '<em>Modelica Equation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Equation Property</em>'.
	 * @generated
	 */
	ModelicaEquationProperty createModelicaEquationProperty();

	/**
	 * Returns a new object of class '<em>Modelica Composition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Composition</em>'.
	 * @generated
	 */
	ModelicaComposition createModelicaComposition();

	/**
	 * Returns a new object of class '<em>Modelica Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Connection</em>'.
	 * @generated
	 */
	ModelicaConnection createModelicaConnection();

	/**
	 * Returns a new object of class '<em>Modelica Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modelica Classifier</em>'.
	 * @generated
	 */
	ModelicaClassifier createModelicaClassifier();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SysmlPackage getSysmlPackage();

} //SysmlFactory
