/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage
 * @generated
 */
public interface ClientFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ClientFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation</em>'.
   * @generated
   */
  ClientOperation createClientOperation();

  /**
   * Returns a new object of class '<em>primary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>primary</em>'.
   * @generated
   */
  primary createprimary();

  /**
   * Returns a new object of class '<em>component reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component reference</em>'.
   * @generated
   */
  component_reference createcomponent_reference();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ClientPackage getClientPackage();

} //ClientFactory
