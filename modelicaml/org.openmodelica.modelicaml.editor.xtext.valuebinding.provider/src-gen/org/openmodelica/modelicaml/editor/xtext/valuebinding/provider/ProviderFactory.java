/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.provider;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.ProviderPackage
 * @generated
 */
public interface ProviderFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ProviderFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.impl.ProviderFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation</em>'.
   * @generated
   */
  ProviderOperation createProviderOperation();

  /**
   * Returns a new object of class '<em>name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>name</em>'.
   * @generated
   */
  name createname();

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
  ProviderPackage getProviderPackage();

} //ProviderFactory
