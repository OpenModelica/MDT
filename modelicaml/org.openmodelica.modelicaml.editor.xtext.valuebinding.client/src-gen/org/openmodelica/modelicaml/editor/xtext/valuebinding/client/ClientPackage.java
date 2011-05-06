/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;

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
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientFactory
 * @model kind="package"
 * @generated
 */
public interface ClientPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "client";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/valuebinding/Client";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "client";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ClientPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getClientOperation()
   * @generated
   */
  int CLIENT_OPERATION = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLIENT_OPERATION__EXPR = 0;

  /**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLIENT_OPERATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl <em>primary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getprimary()
   * @generated
   */
  int PRIMARY = 1;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OP = ModeleditorPackage.PRIMARY__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FACTOR = ModeleditorPackage.PRIMARY__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__PRIMARY = ModeleditorPackage.PRIMARY__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NUM = ModeleditorPackage.PRIMARY__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__INT = ModeleditorPackage.PRIMARY__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__STR = ModeleditorPackage.PRIMARY__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__BOOL = ModeleditorPackage.PRIMARY__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__COMPONENT_REFERENCE = ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OUTPUT_EXPR_LIST = ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRE_LIST = ModeleditorPackage.PRIMARY__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRESSION_LIST = ModeleditorPackage.PRIMARY__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FARGUMENTS = ModeleditorPackage.PRIMARY__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__END = ModeleditorPackage.PRIMARY__END;

  /**
   * The feature id for the '<em><b>Name Function</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NAME_FUNCTION = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Initial ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__INITIAL_REF = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPR = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Sum</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__SUM = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Prod</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__PROD = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MIN = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MAX = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Result</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__RESULT = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 7;

  /**
   * The number of structural features of the '<em>primary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY_FEATURE_COUNT = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 8;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl <em>component reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getcomponent_reference()
   * @generated
   */
  int COMPONENT_REFERENCE = 2;

  /**
   * The feature id for the '<em><b>Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__REF = ModeleditorPackage.COMPONENT_REFERENCE__REF;

  /**
   * The feature id for the '<em><b>Subscripts1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__SUBSCRIPTS1 = ModeleditorPackage.COMPONENT_REFERENCE__SUBSCRIPTS1;

  /**
   * The feature id for the '<em><b>Ref1</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__REF1 = ModeleditorPackage.COMPONENT_REFERENCE__REF1;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__SUBSCRIPTS = ModeleditorPackage.COMPONENT_REFERENCE__SUBSCRIPTS;

  /**
   * The number of structural features of the '<em>component reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE_FEATURE_COUNT = ModeleditorPackage.COMPONENT_REFERENCE_FEATURE_COUNT + 0;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation
   * @generated
   */
  EClass getClientOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getExpr()
   * @see #getClientOperation()
   * @generated
   */
  EReference getClientOperation_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>primary</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary
   * @generated
   */
  EClass getprimary();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function <em>Name Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name Function</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Name_Function();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref <em>Initial ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Initial_ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Expr();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getSum <em>Sum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sum</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getSum()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Sum();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getProd <em>Prod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Prod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getProd()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Prod();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMin()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Min();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMax()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Max();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getResult <em>Result</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Result</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getResult()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Result();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference <em>component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference
   * @generated
   */
  EClass getcomponent_reference();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ClientFactory getClientFactory();

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
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getClientOperation()
     * @generated
     */
    EClass CLIENT_OPERATION = eINSTANCE.getClientOperation();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLIENT_OPERATION__EXPR = eINSTANCE.getClientOperation_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl <em>primary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getprimary()
     * @generated
     */
    EClass PRIMARY = eINSTANCE.getprimary();

    /**
     * The meta object literal for the '<em><b>Name Function</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__NAME_FUNCTION = eINSTANCE.getprimary_Name_Function();

    /**
     * The meta object literal for the '<em><b>Initial ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__INITIAL_REF = eINSTANCE.getprimary_Initial_ref();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__EXPR = eINSTANCE.getprimary_Expr();

    /**
     * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__SUM = eINSTANCE.getprimary_Sum();

    /**
     * The meta object literal for the '<em><b>Prod</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__PROD = eINSTANCE.getprimary_Prod();

    /**
     * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__MIN = eINSTANCE.getprimary_Min();

    /**
     * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__MAX = eINSTANCE.getprimary_Max();

    /**
     * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__RESULT = eINSTANCE.getprimary_Result();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl <em>component reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getcomponent_reference()
     * @generated
     */
    EClass COMPONENT_REFERENCE = eINSTANCE.getcomponent_reference();

  }

} //ClientPackage
