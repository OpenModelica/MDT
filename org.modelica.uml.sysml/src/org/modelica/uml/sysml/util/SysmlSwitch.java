/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.InformationFlow;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.RedefinableElement;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;

import org.modelica.uml.sysml.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.modelica.uml.sysml.SysmlPackage
 * @generated
 */
public class SysmlSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SysmlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SysmlSwitch() {
		if (modelPackage == null) {
			modelPackage = SysmlPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SysmlPackage.BLOCK: {
				Block block = (Block)theEObject;
				Object result = caseBlock(block);
				if (result == null) result = caseClass(block);
				if (result == null) result = caseEncapsulatedClassifier(block);
				if (result == null) result = caseBehavioredClassifier(block);
				if (result == null) result = caseStructuredClassifier(block);
				if (result == null) result = caseClassifier(block);
				if (result == null) result = caseNamespace(block);
				if (result == null) result = caseRedefinableElement(block);
				if (result == null) result = caseType(block);
				if (result == null) result = caseTemplateableElement(block);
				if (result == null) result = caseNamedElement(block);
				if (result == null) result = casePackageableElement(block);
				if (result == null) result = caseElement(block);
				if (result == null) result = caseParameterableElement(block);
				if (result == null) result = caseEModelElement(block);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.BLOCK_PROPERTY: {
				BlockProperty blockProperty = (BlockProperty)theEObject;
				Object result = caseBlockProperty(blockProperty);
				if (result == null) result = caseProperty(blockProperty);
				if (result == null) result = caseStructuralFeature(blockProperty);
				if (result == null) result = caseConnectableElement(blockProperty);
				if (result == null) result = caseDeploymentTarget(blockProperty);
				if (result == null) result = caseTemplateableElement(blockProperty);
				if (result == null) result = caseFeature(blockProperty);
				if (result == null) result = caseTypedElement(blockProperty);
				if (result == null) result = caseMultiplicityElement(blockProperty);
				if (result == null) result = caseParameterableElement(blockProperty);
				if (result == null) result = caseNamedElement(blockProperty);
				if (result == null) result = caseElement(blockProperty);
				if (result == null) result = caseRedefinableElement(blockProperty);
				if (result == null) result = caseEModelElement(blockProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.DISTRIBUTED_PROPERTY: {
				DistributedProperty distributedProperty = (DistributedProperty)theEObject;
				Object result = caseDistributedProperty(distributedProperty);
				if (result == null) result = caseBlockProperty(distributedProperty);
				if (result == null) result = caseProperty(distributedProperty);
				if (result == null) result = caseStructuralFeature(distributedProperty);
				if (result == null) result = caseConnectableElement(distributedProperty);
				if (result == null) result = caseDeploymentTarget(distributedProperty);
				if (result == null) result = caseTemplateableElement(distributedProperty);
				if (result == null) result = caseFeature(distributedProperty);
				if (result == null) result = caseTypedElement(distributedProperty);
				if (result == null) result = caseMultiplicityElement(distributedProperty);
				if (result == null) result = caseParameterableElement(distributedProperty);
				if (result == null) result = caseNamedElement(distributedProperty);
				if (result == null) result = caseElement(distributedProperty);
				if (result == null) result = caseRedefinableElement(distributedProperty);
				if (result == null) result = caseEModelElement(distributedProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.VALUE_TYPE: {
				ValueType valueType = (ValueType)theEObject;
				Object result = caseValueType(valueType);
				if (result == null) result = caseDataType(valueType);
				if (result == null) result = caseClassifier(valueType);
				if (result == null) result = caseNamespace(valueType);
				if (result == null) result = caseRedefinableElement(valueType);
				if (result == null) result = caseType(valueType);
				if (result == null) result = caseTemplateableElement(valueType);
				if (result == null) result = caseNamedElement(valueType);
				if (result == null) result = casePackageableElement(valueType);
				if (result == null) result = caseElement(valueType);
				if (result == null) result = caseParameterableElement(valueType);
				if (result == null) result = caseEModelElement(valueType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.UNIT: {
				Unit unit = (Unit)theEObject;
				Object result = caseUnit(unit);
				if (result == null) result = caseValueType(unit);
				if (result == null) result = caseDataType(unit);
				if (result == null) result = caseClassifier(unit);
				if (result == null) result = caseNamespace(unit);
				if (result == null) result = caseRedefinableElement(unit);
				if (result == null) result = caseType(unit);
				if (result == null) result = caseTemplateableElement(unit);
				if (result == null) result = caseNamedElement(unit);
				if (result == null) result = casePackageableElement(unit);
				if (result == null) result = caseElement(unit);
				if (result == null) result = caseParameterableElement(unit);
				if (result == null) result = caseEModelElement(unit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.DIMENSION: {
				Dimension dimension = (Dimension)theEObject;
				Object result = caseDimension(dimension);
				if (result == null) result = caseValueType(dimension);
				if (result == null) result = caseDataType(dimension);
				if (result == null) result = caseClassifier(dimension);
				if (result == null) result = caseNamespace(dimension);
				if (result == null) result = caseRedefinableElement(dimension);
				if (result == null) result = caseType(dimension);
				if (result == null) result = caseTemplateableElement(dimension);
				if (result == null) result = caseNamedElement(dimension);
				if (result == null) result = casePackageableElement(dimension);
				if (result == null) result = caseElement(dimension);
				if (result == null) result = caseParameterableElement(dimension);
				if (result == null) result = caseEModelElement(dimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.CONFORM: {
				Conform conform = (Conform)theEObject;
				Object result = caseConform(conform);
				if (result == null) result = caseDependency(conform);
				if (result == null) result = casePackageableElement(conform);
				if (result == null) result = caseDirectedRelationship(conform);
				if (result == null) result = caseNamedElement(conform);
				if (result == null) result = caseParameterableElement(conform);
				if (result == null) result = caseRelationship(conform);
				if (result == null) result = caseElement(conform);
				if (result == null) result = caseEModelElement(conform);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.RATIONALE: {
				Rationale rationale = (Rationale)theEObject;
				Object result = caseRationale(rationale);
				if (result == null) result = caseComment(rationale);
				if (result == null) result = caseElement(rationale);
				if (result == null) result = caseEModelElement(rationale);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.PROBLEM: {
				Problem problem = (Problem)theEObject;
				Object result = caseProblem(problem);
				if (result == null) result = caseComment(problem);
				if (result == null) result = caseElement(problem);
				if (result == null) result = caseEModelElement(problem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.FLOW_PORT: {
				FlowPort flowPort = (FlowPort)theEObject;
				Object result = caseFlowPort(flowPort);
				if (result == null) result = casePort(flowPort);
				if (result == null) result = caseProperty(flowPort);
				if (result == null) result = caseStructuralFeature(flowPort);
				if (result == null) result = caseConnectableElement(flowPort);
				if (result == null) result = caseDeploymentTarget(flowPort);
				if (result == null) result = caseTemplateableElement(flowPort);
				if (result == null) result = caseFeature(flowPort);
				if (result == null) result = caseTypedElement(flowPort);
				if (result == null) result = caseMultiplicityElement(flowPort);
				if (result == null) result = caseParameterableElement(flowPort);
				if (result == null) result = caseNamedElement(flowPort);
				if (result == null) result = caseElement(flowPort);
				if (result == null) result = caseRedefinableElement(flowPort);
				if (result == null) result = caseEModelElement(flowPort);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.FLOW_PROPERTY: {
				FlowProperty flowProperty = (FlowProperty)theEObject;
				Object result = caseFlowProperty(flowProperty);
				if (result == null) result = caseProperty(flowProperty);
				if (result == null) result = caseStructuralFeature(flowProperty);
				if (result == null) result = caseConnectableElement(flowProperty);
				if (result == null) result = caseDeploymentTarget(flowProperty);
				if (result == null) result = caseTemplateableElement(flowProperty);
				if (result == null) result = caseFeature(flowProperty);
				if (result == null) result = caseTypedElement(flowProperty);
				if (result == null) result = caseMultiplicityElement(flowProperty);
				if (result == null) result = caseParameterableElement(flowProperty);
				if (result == null) result = caseNamedElement(flowProperty);
				if (result == null) result = caseElement(flowProperty);
				if (result == null) result = caseRedefinableElement(flowProperty);
				if (result == null) result = caseEModelElement(flowProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.FLOW_SPECIFICATION: {
				FlowSpecification flowSpecification = (FlowSpecification)theEObject;
				Object result = caseFlowSpecification(flowSpecification);
				if (result == null) result = caseInterface(flowSpecification);
				if (result == null) result = caseClassifier(flowSpecification);
				if (result == null) result = caseNamespace(flowSpecification);
				if (result == null) result = caseRedefinableElement(flowSpecification);
				if (result == null) result = caseType(flowSpecification);
				if (result == null) result = caseTemplateableElement(flowSpecification);
				if (result == null) result = caseNamedElement(flowSpecification);
				if (result == null) result = casePackageableElement(flowSpecification);
				if (result == null) result = caseElement(flowSpecification);
				if (result == null) result = caseParameterableElement(flowSpecification);
				if (result == null) result = caseEModelElement(flowSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.ITEM_FLOW: {
				ItemFlow itemFlow = (ItemFlow)theEObject;
				Object result = caseItemFlow(itemFlow);
				if (result == null) result = caseInformationFlow(itemFlow);
				if (result == null) result = casePackageableElement(itemFlow);
				if (result == null) result = caseDirectedRelationship(itemFlow);
				if (result == null) result = caseNamedElement(itemFlow);
				if (result == null) result = caseParameterableElement(itemFlow);
				if (result == null) result = caseRelationship(itemFlow);
				if (result == null) result = caseElement(itemFlow);
				if (result == null) result = caseEModelElement(itemFlow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_TYPE: {
				ModelicaType modelicaType = (ModelicaType)theEObject;
				Object result = caseModelicaType(modelicaType);
				if (result == null) result = casePrimitiveType(modelicaType);
				if (result == null) result = caseModelicaClassifier(modelicaType);
				if (result == null) result = caseDataType(modelicaType);
				if (result == null) result = caseClassifier(modelicaType);
				if (result == null) result = caseNamespace(modelicaType);
				if (result == null) result = caseRedefinableElement(modelicaType);
				if (result == null) result = caseType(modelicaType);
				if (result == null) result = caseTemplateableElement(modelicaType);
				if (result == null) result = caseNamedElement(modelicaType);
				if (result == null) result = casePackageableElement(modelicaType);
				if (result == null) result = caseElement(modelicaType);
				if (result == null) result = caseParameterableElement(modelicaType);
				if (result == null) result = caseEModelElement(modelicaType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_REAL: {
				ModelicaReal modelicaReal = (ModelicaReal)theEObject;
				Object result = caseModelicaReal(modelicaReal);
				if (result == null) result = caseModelicaType(modelicaReal);
				if (result == null) result = casePrimitiveType(modelicaReal);
				if (result == null) result = caseModelicaClassifier(modelicaReal);
				if (result == null) result = caseDataType(modelicaReal);
				if (result == null) result = caseClassifier(modelicaReal);
				if (result == null) result = caseNamespace(modelicaReal);
				if (result == null) result = caseRedefinableElement(modelicaReal);
				if (result == null) result = caseType(modelicaReal);
				if (result == null) result = caseTemplateableElement(modelicaReal);
				if (result == null) result = caseNamedElement(modelicaReal);
				if (result == null) result = casePackageableElement(modelicaReal);
				if (result == null) result = caseElement(modelicaReal);
				if (result == null) result = caseParameterableElement(modelicaReal);
				if (result == null) result = caseEModelElement(modelicaReal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_INTEGER: {
				ModelicaInteger modelicaInteger = (ModelicaInteger)theEObject;
				Object result = caseModelicaInteger(modelicaInteger);
				if (result == null) result = caseModelicaType(modelicaInteger);
				if (result == null) result = casePrimitiveType(modelicaInteger);
				if (result == null) result = caseModelicaClassifier(modelicaInteger);
				if (result == null) result = caseDataType(modelicaInteger);
				if (result == null) result = caseClassifier(modelicaInteger);
				if (result == null) result = caseNamespace(modelicaInteger);
				if (result == null) result = caseRedefinableElement(modelicaInteger);
				if (result == null) result = caseType(modelicaInteger);
				if (result == null) result = caseTemplateableElement(modelicaInteger);
				if (result == null) result = caseNamedElement(modelicaInteger);
				if (result == null) result = casePackageableElement(modelicaInteger);
				if (result == null) result = caseElement(modelicaInteger);
				if (result == null) result = caseParameterableElement(modelicaInteger);
				if (result == null) result = caseEModelElement(modelicaInteger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_STRING: {
				ModelicaString modelicaString = (ModelicaString)theEObject;
				Object result = caseModelicaString(modelicaString);
				if (result == null) result = caseModelicaType(modelicaString);
				if (result == null) result = casePrimitiveType(modelicaString);
				if (result == null) result = caseModelicaClassifier(modelicaString);
				if (result == null) result = caseDataType(modelicaString);
				if (result == null) result = caseClassifier(modelicaString);
				if (result == null) result = caseNamespace(modelicaString);
				if (result == null) result = caseRedefinableElement(modelicaString);
				if (result == null) result = caseType(modelicaString);
				if (result == null) result = caseTemplateableElement(modelicaString);
				if (result == null) result = caseNamedElement(modelicaString);
				if (result == null) result = casePackageableElement(modelicaString);
				if (result == null) result = caseElement(modelicaString);
				if (result == null) result = caseParameterableElement(modelicaString);
				if (result == null) result = caseEModelElement(modelicaString);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_BOOLEAN: {
				ModelicaBoolean modelicaBoolean = (ModelicaBoolean)theEObject;
				Object result = caseModelicaBoolean(modelicaBoolean);
				if (result == null) result = caseModelicaType(modelicaBoolean);
				if (result == null) result = casePrimitiveType(modelicaBoolean);
				if (result == null) result = caseModelicaClassifier(modelicaBoolean);
				if (result == null) result = caseDataType(modelicaBoolean);
				if (result == null) result = caseClassifier(modelicaBoolean);
				if (result == null) result = caseNamespace(modelicaBoolean);
				if (result == null) result = caseRedefinableElement(modelicaBoolean);
				if (result == null) result = caseType(modelicaBoolean);
				if (result == null) result = caseTemplateableElement(modelicaBoolean);
				if (result == null) result = caseNamedElement(modelicaBoolean);
				if (result == null) result = casePackageableElement(modelicaBoolean);
				if (result == null) result = caseElement(modelicaBoolean);
				if (result == null) result = caseParameterableElement(modelicaBoolean);
				if (result == null) result = caseEModelElement(modelicaBoolean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_CLASS: {
				ModelicaClass modelicaClass = (ModelicaClass)theEObject;
				Object result = caseModelicaClass(modelicaClass);
				if (result == null) result = caseBlock(modelicaClass);
				if (result == null) result = caseModelicaClassifier(modelicaClass);
				if (result == null) result = caseClass(modelicaClass);
				if (result == null) result = caseClassifier(modelicaClass);
				if (result == null) result = caseEncapsulatedClassifier(modelicaClass);
				if (result == null) result = caseBehavioredClassifier(modelicaClass);
				if (result == null) result = caseNamespace(modelicaClass);
				if (result == null) result = caseRedefinableElement(modelicaClass);
				if (result == null) result = caseType(modelicaClass);
				if (result == null) result = caseTemplateableElement(modelicaClass);
				if (result == null) result = caseStructuredClassifier(modelicaClass);
				if (result == null) result = caseNamedElement(modelicaClass);
				if (result == null) result = casePackageableElement(modelicaClass);
				if (result == null) result = caseElement(modelicaClass);
				if (result == null) result = caseParameterableElement(modelicaClass);
				if (result == null) result = caseEModelElement(modelicaClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_MODEL: {
				ModelicaModel modelicaModel = (ModelicaModel)theEObject;
				Object result = caseModelicaModel(modelicaModel);
				if (result == null) result = caseModelicaClass(modelicaModel);
				if (result == null) result = caseBlock(modelicaModel);
				if (result == null) result = caseModelicaClassifier(modelicaModel);
				if (result == null) result = caseClass(modelicaModel);
				if (result == null) result = caseClassifier(modelicaModel);
				if (result == null) result = caseEncapsulatedClassifier(modelicaModel);
				if (result == null) result = caseBehavioredClassifier(modelicaModel);
				if (result == null) result = caseNamespace(modelicaModel);
				if (result == null) result = caseRedefinableElement(modelicaModel);
				if (result == null) result = caseType(modelicaModel);
				if (result == null) result = caseTemplateableElement(modelicaModel);
				if (result == null) result = caseStructuredClassifier(modelicaModel);
				if (result == null) result = caseNamedElement(modelicaModel);
				if (result == null) result = casePackageableElement(modelicaModel);
				if (result == null) result = caseElement(modelicaModel);
				if (result == null) result = caseParameterableElement(modelicaModel);
				if (result == null) result = caseEModelElement(modelicaModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_BLOCK: {
				ModelicaBlock modelicaBlock = (ModelicaBlock)theEObject;
				Object result = caseModelicaBlock(modelicaBlock);
				if (result == null) result = caseModelicaClass(modelicaBlock);
				if (result == null) result = caseBlock(modelicaBlock);
				if (result == null) result = caseModelicaClassifier(modelicaBlock);
				if (result == null) result = caseClass(modelicaBlock);
				if (result == null) result = caseClassifier(modelicaBlock);
				if (result == null) result = caseEncapsulatedClassifier(modelicaBlock);
				if (result == null) result = caseBehavioredClassifier(modelicaBlock);
				if (result == null) result = caseNamespace(modelicaBlock);
				if (result == null) result = caseRedefinableElement(modelicaBlock);
				if (result == null) result = caseType(modelicaBlock);
				if (result == null) result = caseTemplateableElement(modelicaBlock);
				if (result == null) result = caseStructuredClassifier(modelicaBlock);
				if (result == null) result = caseNamedElement(modelicaBlock);
				if (result == null) result = casePackageableElement(modelicaBlock);
				if (result == null) result = caseElement(modelicaBlock);
				if (result == null) result = caseParameterableElement(modelicaBlock);
				if (result == null) result = caseEModelElement(modelicaBlock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_CONNECTOR: {
				ModelicaConnector modelicaConnector = (ModelicaConnector)theEObject;
				Object result = caseModelicaConnector(modelicaConnector);
				if (result == null) result = caseModelicaClass(modelicaConnector);
				if (result == null) result = caseBlock(modelicaConnector);
				if (result == null) result = caseModelicaClassifier(modelicaConnector);
				if (result == null) result = caseClass(modelicaConnector);
				if (result == null) result = caseClassifier(modelicaConnector);
				if (result == null) result = caseEncapsulatedClassifier(modelicaConnector);
				if (result == null) result = caseBehavioredClassifier(modelicaConnector);
				if (result == null) result = caseNamespace(modelicaConnector);
				if (result == null) result = caseRedefinableElement(modelicaConnector);
				if (result == null) result = caseType(modelicaConnector);
				if (result == null) result = caseTemplateableElement(modelicaConnector);
				if (result == null) result = caseStructuredClassifier(modelicaConnector);
				if (result == null) result = caseNamedElement(modelicaConnector);
				if (result == null) result = casePackageableElement(modelicaConnector);
				if (result == null) result = caseElement(modelicaConnector);
				if (result == null) result = caseParameterableElement(modelicaConnector);
				if (result == null) result = caseEModelElement(modelicaConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_RECORD: {
				ModelicaRecord modelicaRecord = (ModelicaRecord)theEObject;
				Object result = caseModelicaRecord(modelicaRecord);
				if (result == null) result = caseModelicaClass(modelicaRecord);
				if (result == null) result = caseBlock(modelicaRecord);
				if (result == null) result = caseModelicaClassifier(modelicaRecord);
				if (result == null) result = caseClass(modelicaRecord);
				if (result == null) result = caseClassifier(modelicaRecord);
				if (result == null) result = caseEncapsulatedClassifier(modelicaRecord);
				if (result == null) result = caseBehavioredClassifier(modelicaRecord);
				if (result == null) result = caseNamespace(modelicaRecord);
				if (result == null) result = caseRedefinableElement(modelicaRecord);
				if (result == null) result = caseType(modelicaRecord);
				if (result == null) result = caseTemplateableElement(modelicaRecord);
				if (result == null) result = caseStructuredClassifier(modelicaRecord);
				if (result == null) result = caseNamedElement(modelicaRecord);
				if (result == null) result = casePackageableElement(modelicaRecord);
				if (result == null) result = caseElement(modelicaRecord);
				if (result == null) result = caseParameterableElement(modelicaRecord);
				if (result == null) result = caseEModelElement(modelicaRecord);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_FUNCTION: {
				ModelicaFunction modelicaFunction = (ModelicaFunction)theEObject;
				Object result = caseModelicaFunction(modelicaFunction);
				if (result == null) result = caseModelicaClass(modelicaFunction);
				if (result == null) result = caseBlock(modelicaFunction);
				if (result == null) result = caseModelicaClassifier(modelicaFunction);
				if (result == null) result = caseClass(modelicaFunction);
				if (result == null) result = caseClassifier(modelicaFunction);
				if (result == null) result = caseEncapsulatedClassifier(modelicaFunction);
				if (result == null) result = caseBehavioredClassifier(modelicaFunction);
				if (result == null) result = caseNamespace(modelicaFunction);
				if (result == null) result = caseRedefinableElement(modelicaFunction);
				if (result == null) result = caseType(modelicaFunction);
				if (result == null) result = caseTemplateableElement(modelicaFunction);
				if (result == null) result = caseStructuredClassifier(modelicaFunction);
				if (result == null) result = caseNamedElement(modelicaFunction);
				if (result == null) result = casePackageableElement(modelicaFunction);
				if (result == null) result = caseElement(modelicaFunction);
				if (result == null) result = caseParameterableElement(modelicaFunction);
				if (result == null) result = caseEModelElement(modelicaFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_PROPERTY: {
				ModelicaProperty modelicaProperty = (ModelicaProperty)theEObject;
				Object result = caseModelicaProperty(modelicaProperty);
				if (result == null) result = caseBlockProperty(modelicaProperty);
				if (result == null) result = caseProperty(modelicaProperty);
				if (result == null) result = caseStructuralFeature(modelicaProperty);
				if (result == null) result = caseConnectableElement(modelicaProperty);
				if (result == null) result = caseDeploymentTarget(modelicaProperty);
				if (result == null) result = caseTemplateableElement(modelicaProperty);
				if (result == null) result = caseFeature(modelicaProperty);
				if (result == null) result = caseTypedElement(modelicaProperty);
				if (result == null) result = caseMultiplicityElement(modelicaProperty);
				if (result == null) result = caseParameterableElement(modelicaProperty);
				if (result == null) result = caseNamedElement(modelicaProperty);
				if (result == null) result = caseElement(modelicaProperty);
				if (result == null) result = caseRedefinableElement(modelicaProperty);
				if (result == null) result = caseEModelElement(modelicaProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_CONNECTOR_PROPERTY: {
				ModelicaConnectorProperty modelicaConnectorProperty = (ModelicaConnectorProperty)theEObject;
				Object result = caseModelicaConnectorProperty(modelicaConnectorProperty);
				if (result == null) result = casePort(modelicaConnectorProperty);
				if (result == null) result = caseModelicaProperty(modelicaConnectorProperty);
				if (result == null) result = caseProperty(modelicaConnectorProperty);
				if (result == null) result = caseBlockProperty(modelicaConnectorProperty);
				if (result == null) result = caseStructuralFeature(modelicaConnectorProperty);
				if (result == null) result = caseConnectableElement(modelicaConnectorProperty);
				if (result == null) result = caseDeploymentTarget(modelicaConnectorProperty);
				if (result == null) result = caseTemplateableElement(modelicaConnectorProperty);
				if (result == null) result = caseFeature(modelicaConnectorProperty);
				if (result == null) result = caseTypedElement(modelicaConnectorProperty);
				if (result == null) result = caseMultiplicityElement(modelicaConnectorProperty);
				if (result == null) result = caseParameterableElement(modelicaConnectorProperty);
				if (result == null) result = caseNamedElement(modelicaConnectorProperty);
				if (result == null) result = caseElement(modelicaConnectorProperty);
				if (result == null) result = caseRedefinableElement(modelicaConnectorProperty);
				if (result == null) result = caseEModelElement(modelicaConnectorProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_EQUATION_PROPERTY: {
				ModelicaEquationProperty modelicaEquationProperty = (ModelicaEquationProperty)theEObject;
				Object result = caseModelicaEquationProperty(modelicaEquationProperty);
				if (result == null) result = caseModelicaProperty(modelicaEquationProperty);
				if (result == null) result = caseBlockProperty(modelicaEquationProperty);
				if (result == null) result = caseProperty(modelicaEquationProperty);
				if (result == null) result = caseStructuralFeature(modelicaEquationProperty);
				if (result == null) result = caseConnectableElement(modelicaEquationProperty);
				if (result == null) result = caseDeploymentTarget(modelicaEquationProperty);
				if (result == null) result = caseTemplateableElement(modelicaEquationProperty);
				if (result == null) result = caseFeature(modelicaEquationProperty);
				if (result == null) result = caseTypedElement(modelicaEquationProperty);
				if (result == null) result = caseMultiplicityElement(modelicaEquationProperty);
				if (result == null) result = caseParameterableElement(modelicaEquationProperty);
				if (result == null) result = caseNamedElement(modelicaEquationProperty);
				if (result == null) result = caseElement(modelicaEquationProperty);
				if (result == null) result = caseRedefinableElement(modelicaEquationProperty);
				if (result == null) result = caseEModelElement(modelicaEquationProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_COMPOSITION: {
				ModelicaComposition modelicaComposition = (ModelicaComposition)theEObject;
				Object result = caseModelicaComposition(modelicaComposition);
				if (result == null) result = caseAssociation(modelicaComposition);
				if (result == null) result = caseClassifier(modelicaComposition);
				if (result == null) result = caseRelationship(modelicaComposition);
				if (result == null) result = caseNamespace(modelicaComposition);
				if (result == null) result = caseRedefinableElement(modelicaComposition);
				if (result == null) result = caseType(modelicaComposition);
				if (result == null) result = caseTemplateableElement(modelicaComposition);
				if (result == null) result = caseElement(modelicaComposition);
				if (result == null) result = caseNamedElement(modelicaComposition);
				if (result == null) result = casePackageableElement(modelicaComposition);
				if (result == null) result = caseEModelElement(modelicaComposition);
				if (result == null) result = caseParameterableElement(modelicaComposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_CONNECTION: {
				ModelicaConnection modelicaConnection = (ModelicaConnection)theEObject;
				Object result = caseModelicaConnection(modelicaConnection);
				if (result == null) result = caseConnector(modelicaConnection);
				if (result == null) result = caseFeature(modelicaConnection);
				if (result == null) result = caseRedefinableElement(modelicaConnection);
				if (result == null) result = caseNamedElement(modelicaConnection);
				if (result == null) result = caseElement(modelicaConnection);
				if (result == null) result = caseEModelElement(modelicaConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SysmlPackage.MODELICA_CLASSIFIER: {
				ModelicaClassifier modelicaClassifier = (ModelicaClassifier)theEObject;
				Object result = caseModelicaClassifier(modelicaClassifier);
				if (result == null) result = caseClassifier(modelicaClassifier);
				if (result == null) result = caseNamespace(modelicaClassifier);
				if (result == null) result = caseRedefinableElement(modelicaClassifier);
				if (result == null) result = caseType(modelicaClassifier);
				if (result == null) result = caseTemplateableElement(modelicaClassifier);
				if (result == null) result = caseNamedElement(modelicaClassifier);
				if (result == null) result = casePackageableElement(modelicaClassifier);
				if (result == null) result = caseElement(modelicaClassifier);
				if (result == null) result = caseParameterableElement(modelicaClassifier);
				if (result == null) result = caseEModelElement(modelicaClassifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBlock(Block object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Block Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Block Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBlockProperty(BlockProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Distributed Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Distributed Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDistributedProperty(DistributedProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Value Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Value Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseValueType(ValueType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUnit(Unit object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDimension(Dimension object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Conform</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Conform</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConform(Conform object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Rationale</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Rationale</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRationale(Rationale object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseProblem(Problem object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Flow Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Flow Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFlowPort(FlowPort object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Flow Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Flow Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFlowProperty(FlowProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Flow Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Flow Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFlowSpecification(FlowSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Item Flow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Item Flow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseItemFlow(ItemFlow object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaType(ModelicaType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Real</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Real</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaReal(ModelicaReal object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Integer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Integer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaInteger(ModelicaInteger object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica String</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica String</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaString(ModelicaString object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaBoolean(ModelicaBoolean object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaClass(ModelicaClass object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaModel(ModelicaModel object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaBlock(ModelicaBlock object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaConnector(ModelicaConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Record</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Record</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaRecord(ModelicaRecord object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaFunction(ModelicaFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaProperty(ModelicaProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Connector Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Connector Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaConnectorProperty(ModelicaConnectorProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Equation Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Equation Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaEquationProperty(ModelicaEquationProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Composition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Composition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaComposition(ModelicaComposition object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaConnection(ModelicaConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Modelica Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Modelica Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModelicaClassifier(ModelicaClassifier object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEModelElement(EModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseNamespace(Namespace object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Redefinable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Redefinable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRedefinableElement(RedefinableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Parameterable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Parameterable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseParameterableElement(ParameterableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Packageable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Packageable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePackageableElement(PackageableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseType(Type object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Templateable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Templateable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTemplateableElement(TemplateableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseClassifier(Classifier object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Structured Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Structured Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseStructuredClassifier(StructuredClassifier object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Encapsulated Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Encapsulated Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEncapsulatedClassifier(EncapsulatedClassifier object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Behaviored Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Behaviored Classifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBehavioredClassifier(BehavioredClassifier object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseClass(org.eclipse.uml2.uml.Class object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFeature(Feature object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTypedElement(TypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Multiplicity Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Multiplicity Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMultiplicityElement(MultiplicityElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Structural Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Structural Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseStructuralFeature(StructuralFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Connectable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Connectable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConnectableElement(ConnectableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Deployment Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Deployment Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDeploymentTarget(DeploymentTarget object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseProperty(Property object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDataType(DataType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRelationship(Relationship object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Directed Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Directed Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDirectedRelationship(DirectedRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Dependency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDependency(Dependency object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePort(Port object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInterface(Interface object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Information Flow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Information Flow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInformationFlow(InformationFlow object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Primitive Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Primitive Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePrimitiveType(PrimitiveType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAssociation(Association object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConnector(Connector object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //SysmlSwitch
