/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute;

import org.eclipse.emf.ecore.EClass;
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
 * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributeFactory
 * @model kind="package"
 * @generated
 */
public interface ConditionalattributePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "conditionalattribute";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/condition/Conditionalattribute";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "conditionalattribute";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ConditionalattributePackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.ConditionalattributePackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.conditional_attributeImpl <em>conditional attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.conditional_attributeImpl
   * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.ConditionalattributePackageImpl#getconditional_attribute()
   * @generated
   */
  int CONDITIONAL_ATTRIBUTE = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_ATTRIBUTE__EXPR = 0;

  /**
   * The number of structural features of the '<em>conditional attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_ATTRIBUTE_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.conditional_attribute <em>conditional attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>conditional attribute</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.conditional_attribute
   * @generated
   */
  EClass getconditional_attribute();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.conditional_attribute#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.conditional_attribute#getExpr()
   * @see #getconditional_attribute()
   * @generated
   */
  EReference getconditional_attribute_Expr();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ConditionalattributeFactory getConditionalattributeFactory();

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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.conditional_attributeImpl <em>conditional attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.conditional_attributeImpl
     * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.ConditionalattributePackageImpl#getconditional_attribute()
     * @generated
     */
    EClass CONDITIONAL_ATTRIBUTE = eINSTANCE.getconditional_attribute();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_ATTRIBUTE__EXPR = eINSTANCE.getconditional_attribute_Expr();

  }

} //ConditionalattributePackage
