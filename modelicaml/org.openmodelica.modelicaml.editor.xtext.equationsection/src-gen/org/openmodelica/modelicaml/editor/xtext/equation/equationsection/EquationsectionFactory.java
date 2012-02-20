/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage
 * @generated
 */
public interface EquationsectionFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EquationsectionFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionFactoryImpl.init();

  /**
   * Returns a new object of class '<em>equation section</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>equation section</em>'.
   * @generated
   */
  equation_section createequation_section();

  /**
   * Returns a new object of class '<em>equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>equation</em>'.
   * @generated
   */
  equation createequation();

  /**
   * Returns a new object of class '<em>when equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>when equation</em>'.
   * @generated
   */
  when_equation createwhen_equation();

  /**
   * Returns a new object of class '<em>if equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>if equation</em>'.
   * @generated
   */
  if_equation createif_equation();

  /**
   * Returns a new object of class '<em>for equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>for equation</em>'.
   * @generated
   */
  for_equation createfor_equation();

  /**
   * Returns a new object of class '<em>connect clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>connect clause</em>'.
   * @generated
   */
  connect_clause createconnect_clause();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  EquationsectionPackage getEquationsectionPackage();

} //EquationsectionFactory
