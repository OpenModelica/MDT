/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage
 * @generated
 */
public interface AlgorithmsectionFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  AlgorithmsectionFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionFactoryImpl.init();

  /**
   * Returns a new object of class '<em>algorithm clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>algorithm clause</em>'.
   * @generated
   */
  algorithm_clause createalgorithm_clause();

  /**
   * Returns a new object of class '<em>statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>statement</em>'.
   * @generated
   */
  statement createstatement();

  /**
   * Returns a new object of class '<em>Algorithm Macros GEN SIGNAL</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Algorithm Macros GEN SIGNAL</em>'.
   * @generated
   */
  Algorithm_Macros_GEN_SIGNAL createAlgorithm_Macros_GEN_SIGNAL();

  /**
   * Returns a new object of class '<em>Algorithm Macros GEN INC</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Algorithm Macros GEN INC</em>'.
   * @generated
   */
  Algorithm_Macros_GEN_INC createAlgorithm_Macros_GEN_INC();

  /**
   * Returns a new object of class '<em>Algorithm Macros GEN CHANGE</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Algorithm Macros GEN CHANGE</em>'.
   * @generated
   */
  Algorithm_Macros_GEN_CHANGE createAlgorithm_Macros_GEN_CHANGE();

  /**
   * Returns a new object of class '<em>state Output Expr List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>state Output Expr List</em>'.
   * @generated
   */
  state_OutputExprList createstate_OutputExprList();

  /**
   * Returns a new object of class '<em>state Comp Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>state Comp Ref</em>'.
   * @generated
   */
  state_Comp_Ref createstate_Comp_Ref();

  /**
   * Returns a new object of class '<em>when statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>when statement</em>'.
   * @generated
   */
  when_statement createwhen_statement();

  /**
   * Returns a new object of class '<em>while statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>while statement</em>'.
   * @generated
   */
  while_statement createwhile_statement();

  /**
   * Returns a new object of class '<em>for statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>for statement</em>'.
   * @generated
   */
  for_statement createfor_statement();

  /**
   * Returns a new object of class '<em>if statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>if statement</em>'.
   * @generated
   */
  if_statement createif_statement();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  AlgorithmsectionPackage getAlgorithmsectionPackage();

} //AlgorithmsectionFactory
