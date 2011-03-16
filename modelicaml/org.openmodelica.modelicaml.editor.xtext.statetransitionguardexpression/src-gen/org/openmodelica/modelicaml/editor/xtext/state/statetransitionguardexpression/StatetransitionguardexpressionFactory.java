/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage
 * @generated
 */
public interface StatetransitionguardexpressionFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  StatetransitionguardexpressionFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionFactoryImpl.init();

  /**
   * Returns a new object of class '<em>expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>expression</em>'.
   * @generated
   */
  expression createexpression();

  /**
   * Returns a new object of class '<em>primary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>primary</em>'.
   * @generated
   */
  primary createprimary();

  /**
   * Returns a new object of class '<em>Macro After</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Macro After</em>'.
   * @generated
   */
  Macro_After createMacro_After();

  /**
   * Returns a new object of class '<em>Macro BEFORE</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Macro BEFORE</em>'.
   * @generated
   */
  Macro_BEFORE createMacro_BEFORE();

  /**
   * Returns a new object of class '<em>Macro SIGNAL</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Macro SIGNAL</em>'.
   * @generated
   */
  Macro_SIGNAL createMacro_SIGNAL();

  /**
   * Returns a new object of class '<em>Macro EVENT</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Macro EVENT</em>'.
   * @generated
   */
  Macro_EVENT createMacro_EVENT();

  /**
   * Returns a new object of class '<em>Macro CHANGE</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Macro CHANGE</em>'.
   * @generated
   */
  Macro_CHANGE createMacro_CHANGE();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  StatetransitionguardexpressionPackage getStatetransitionguardexpressionPackage();

} //StatetransitionguardexpressionFactory
