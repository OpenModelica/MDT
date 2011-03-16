/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.declaration.declaration;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationPackage
 * @generated
 */
public interface DeclarationFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DeclarationFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationFactoryImpl.init();

  /**
   * Returns a new object of class '<em>component declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component declaration</em>'.
   * @generated
   */
  component_declaration createcomponent_declaration();

  /**
   * Returns a new object of class '<em>modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>modification</em>'.
   * @generated
   */
  modification createmodification();

  /**
   * Returns a new object of class '<em>conditional attribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>conditional attribute</em>'.
   * @generated
   */
  conditional_attribute createconditional_attribute();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DeclarationPackage getDeclarationPackage();

} //DeclarationFactory
